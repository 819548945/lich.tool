package lich.tool.conflictResolution;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import lich.tool.object.CopyTools;
/**
 * Loading jars proxy class using a separate classloader
 * @author liuch
 *
 */
public class ClassProxy {
	protected  Object obj;
	protected  Class cls;
	protected  CLClassloader classLoader;
	private static CLClassloader toCLClassloader(ClassLoader cl) throws ConflictResolutionException {
		if(cl==null)throw new ConflictResolutionException("ClassLoader不可为null");
		if(cl.getClass()==CLClassloader.class) {
			return (CLClassloader)cl;
		}else {
			return new CLClassloader(new URL[0],cl)  ;
		}	
	}
	
	/**
	 * 
	 * @param className className
	 * @param jarBasePaths jar paths
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	protected ClassProxy(String className,URL... jarBasePaths) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=initClassLoader(jarBasePaths);
		this.cls  =  classLoader.loadClass(className);
	}
	/**
	 * 
	 * @param className
	 * @param classLoader
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 * @throws ConflictResolutionException 
	 */
	@Deprecated
	protected ClassProxy(String className,URLClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException, ConflictResolutionException {
		this.classLoader=toCLClassloader(classLoader);
		this.cls  =  classLoader.loadClass(className);
	}
	protected ClassProxy(String className,ClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException, ConflictResolutionException {
		this.classLoader=toCLClassloader(classLoader);
		this.cls  =  classLoader.loadClass(className);
	}
	protected ClassProxy(String className,ClassLoader classLoader,InputStream in) throws ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException, IOException, ConflictResolutionException {
		this.classLoader=toCLClassloader(classLoader);
		this.cls=this.classLoader.loadMyClass(className, in);
	}
	/**
	 * 
	 * @param obj obj
	 * @param jarBasePaths jar paths
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws MalformedURLException
	 * @throws URISyntaxException
	 */
	protected ClassProxy(Object obj,URL... jarBasePaths) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=initClassLoader(jarBasePaths);
		this.cls  =  obj.getClass();
		this.obj=obj;
	}
	@Deprecated
	protected ClassProxy(Object obj,URLClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException, ConflictResolutionException {
		this.classLoader=toCLClassloader(classLoader);
		this.cls  =  obj.getClass();
		this.obj=obj;
	}
	protected ClassProxy(Object obj,ClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException, ConflictResolutionException {
		this.classLoader=toCLClassloader(classLoader);
		this.cls  =  obj.getClass();
		this.obj=obj;
	}
	/**
	 * getClassLoader
	 * @return classLoader
	 */
	public ClassLoader getClassLoader() {
		return classLoader;
	}
	
	/**
	 * 
	 * exec method
	 * @param methodName method Name
	 * @param d Parameters
	 * @return exec return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws ConflictResolutionException 
	 */
	public Object exec(String methodName,Object ... d) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException, ConflictResolutionException {
		Parameters p=switchClassLoader(	new Parameters(d));
		Method method1 =cls.getMethod(methodName,p.getClssArray());
		return method1.invoke(obj,p.getObjArray());
	}
	/**
	 * exec method
	 * @param methodName method Name
	 * @param p Parameters
	 * @return  exec return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws ConflictResolutionException 
	 */
	public Object exec(String methodName,Parameters p) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException, ConflictResolutionException {
		p=switchClassLoader(p);
		Method method1 =this.cls.getMethod(methodName,p.getClssArray());
		return method1.invoke(obj,p.getObjArray());
	}
	private Parameters switchClassLoader(Parameters p) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, ConflictResolutionException {
		Object[] os=p.getObjArray();
		Class [] cl=p.getClssArray();
		for (int i = 0; i < os.length; i++) {
			cl[i]=switchLoadClass(cl[i]);
			if(os[i]!=null) {
				Class cls=switchLoadClass(os[i].getClass());
				if(os[i].getClass().getClassLoader()!=cls.getClassLoader()) {
					try {
						Constructor c=	cls.getDeclaredConstructor();
						c.setAccessible(true);
						Object o=c.newInstance();
						CopyTools.copyField(os[i], o);
						os[i]=o;
					} catch (Exception e) {
						throw new ConflictResolutionException(cls.getName()+"无法调用无参构造自动转换，请手动转换");
					}	
				}					
			}
		}
		p.reload(cl, os);
		return p;
	}
	private Class switchLoadClass(Class co) throws IOException {
		if(co.getClassLoader()==null)return co;
		if(co.getClassLoader()!=classLoader) {
			Class cls;
			String cn=co.getName();
			try {
				cls=classLoader.loadClass(cn);
			} catch (Exception e) {
				InputStream in=	co.getResourceAsStream(cn.substring(cn.lastIndexOf(".")+1)+".class");
				cls=classLoader.loadMyClass(cn, in);
			}
			return cls;
		}else return co;
		
	}
	
	/**
	 * 实例化代理类
	 * @param o Parameters
	 * @return this
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IOException 
	 * @throws ConflictResolutionException 
	 */
	public ClassProxy newInstance(Object ... o) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, ConflictResolutionException {
		if(o.length==0)this.obj=cls.newInstance();
		else {
			Parameters p=	switchClassLoader(new Parameters(o));
			obj=cls.getConstructor(p.getClssArray()).newInstance(p.getObjArray());
		}
		return this;
	}
	/**
	 * 实例化代理类
	 * @param p 参数列表
	 * @return this
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IOException 
	 * @throws ConflictResolutionException 
	 */
	public ClassProxy newInstance(Parameters p) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, ConflictResolutionException {
		switchClassLoader(p);
		obj=cls.getConstructor(p.getClssArray()).newInstance(p.getObjArray());
		return this;
	}
	/**
	 * Gets the proxy object
	 * @return original object
	 */
	public Object getObj() {
		return obj;
	}
	protected static CLClassloader initClassLoader(URL... jarBasePaths) {
		try {
			if(jarBasePaths!=null&&jarBasePaths.length>0) {
				ArrayList<URL> jarUrls=new ArrayList<URL>();
				for(URL jarBasePath:jarBasePaths) {
					File f=new File(jarBasePath.toURI());
					if(	f.isFile()) {
						jarUrls.add(jarBasePath);
					}else {
						File [] jarArray = new File(jarBasePath.toURI()).listFiles();
						for(int i=0;i<jarArray.length;i++)jarUrls.add(jarArray[i].toURI().toURL());
					}
				}
				return new CLClassloader(jarUrls.toArray(new URL[jarUrls.size()]),ClassProxy.class.getClassLoader());
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}			
	}
	public Object getFieldValue(String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f=cls.getDeclaredField(fieldName);
		f.setAccessible(true);
		return f.get(obj);
	}
	public void setFieldValue(String fieldName,Object value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f=cls.getDeclaredField(fieldName);
		f.setAccessible(true);
		f.set(obj, value);
	}
	public Class<?> getClsss() {
		return cls;
	}
}
