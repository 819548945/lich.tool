package lich.tool.conflictResolution;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Removing parental dependence Classloader
 * @author liuch
 *
 */
public class CLClassloader extends URLClassLoader{
	
    public CLClassloader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
   
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                    try {
                    	c=findClass(name);
                    } catch (ClassNotFoundException e) {
                    }

                    if (c == null) {
                        long t1 = System.nanoTime();
                        c = getParent().loadClass(name);
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }
}
