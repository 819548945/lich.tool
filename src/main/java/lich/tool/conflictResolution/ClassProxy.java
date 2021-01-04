package lich.tool.conflictResolution;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * Loading jars proxy class using a separate classloader
 * @author liuch
 *
 */
public class ClassProxy {
	protected  Object obj;
	protected  Class cls;
	protected  ClassLoader classLoader;
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
	//	this.obj=cls.newInstance();
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
	 */
	@Deprecated
	protected ClassProxy(String className,URLClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=classLoader;
		this.cls  =  classLoader.loadClass(className);
	//	this.obj=cls.newInstance();
	}
	protected ClassProxy(String className,ClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=classLoader;
		this.cls  =  classLoader.loadClass(className);
		//this.obj=cls.newInstance();
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
	protected ClassProxy(Object obj,URLClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=classLoader;
		this.cls  =  obj.getClass();
		this.obj=obj;
	}
	protected ClassProxy(Object obj,ClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=classLoader;
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
	 */
	public Object exec(String methodName,Object ... d) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Parameters p=	new Parameters(d);
		Method method1 =cls.getMethod(methodName,p.getClssArray());
		if(obj==null) this.obj=cls.newInstance();
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
	 */
	public Object exec(String methodName,Parameters p) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Method method1 =cls.getMethod(methodName,p.getClssArray());
		if(obj==null) this.obj=cls.newInstance();
		return method1.invoke(obj,p.getObjArray());
	}
	/**
	 * exec static method
	 * @param methodName method Name
	 * @param p Parameters
	 * @return exec return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public Object execStatic(String methodName,Parameters p) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Method method1 =cls.getMethod(methodName,p.getClssArray());
		return method1.invoke(null,p.getObjArray());
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
	 */
	public Object execStatic(String methodName,Object ... d) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		Parameters p=	new Parameters(d);
		Method method1 =cls.getMethod(methodName,p.getClssArray());
		return method1.invoke(null,p.getObjArray());
	}
	/**
	 * 
	 * @param o Parameters
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public ClassProxy newInstance(Object ... o) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		if(o.length==0)this.obj=cls.newInstance();
		else {
			Class[] c=new Class[o.length];
			for(int i=0;i<o.length;i++)c[i]=o.getClass();
			cls.getConstructor(c).newInstance(o);
		}
		return this;
	}
	/**
	 * 
	 * @param p
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public ClassProxy newInstance(Parameters p) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		cls.getConstructor(p.getClssArray()).newInstance(p.getObjArray());
		return this;
	}
	/**
	 * Gets the proxy object
	 * @return original object
	 */
	public Object getObj() {
		return obj;
	}
	protected static URLClassLoader initClassLoader(URL... jarBasePaths) {
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
}
