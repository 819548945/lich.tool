package lich.tool.conflictResolution;

import java.io.File;
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
	protected  URLClassLoader classLoader;
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
		this.obj=cls.newInstance();
	}
	protected ClassProxy(String className,URLClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=classLoader;
		this.cls  =  classLoader.loadClass(className);
		this.obj=cls.newInstance();
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
	protected ClassProxy(Object obj,URLClassLoader classLoader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException {
		this.classLoader=classLoader;
		this.cls  =  obj.getClass();
		this.obj=obj;
	}
	/**
	 * getClassLoader
	 * @return classLoader
	 */
	protected URLClassLoader getClassLoader() {
		return classLoader;
	}
	
	/**
	 * exec method
	 * @param methodName method Name
	 * @param d Parameters
	 * @return exec return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public Object exec(String methodName,Object ... d) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Parameters p=	new Parameters(d);
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
	 */
	public Object exec(String methodName,Parameters p) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method method1 =cls.getMethod(methodName,p.getClssArray());
		return method1.invoke(obj,p.getObjArray());
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
				return new URLClassLoader(jarUrls.toArray(new URL[jarUrls.size()]));
			}else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
