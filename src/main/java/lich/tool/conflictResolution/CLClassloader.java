package lich.tool.conflictResolution;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    public Class loadMyClass(String className,InputStream in) throws IOException  {
    	
    	  byte[] buffer = new byte[1024];
          int len = 0;
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          while((len = in.read(buffer)) != -1) {
              bos.write(buffer, 0, len);
          }
          byte[] bytes =bos.toByteArray();
		  return defineClass(className, bytes, 0, bytes.length);           
    }
}
