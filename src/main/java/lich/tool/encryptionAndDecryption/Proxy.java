package lich.tool.encryptionAndDecryption;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Provider;

import lich.tool.conflictResolution.CLClassloader;
import lich.tool.conflictResolution.ClassProxy;
import lich.tool.conflictResolution.ConflictResolutionException;



public class Proxy extends ClassProxy{
	protected  static  CLClassloader classLoader=initClassLoader();
	
	public Proxy(String className) throws EncryptionAndDecryptionException, ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException, ConflictResolutionException{
		
		super(className, classLoader);		
	}
	public Proxy(Object o) throws ClassNotFoundException, InstantiationException, IllegalAccessException, MalformedURLException, URISyntaxException, ConflictResolutionException {
		super(o,classLoader);
	}
	
	public Proxy(String className,InputStream in) throws ClassNotFoundException, InstantiationException, IllegalAccessException, URISyntaxException, IOException, ConflictResolutionException {
		super(className,classLoader,in);
	}
	/**
	 * 初始化组件
	 * @param url jar包路径
	 */
	public static void init(URL url) throws EncryptionAndDecryptionException{
		try {
			if(classLoader==null) {
				classLoader=initClassLoader(url);
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
		
	}	
	
}
