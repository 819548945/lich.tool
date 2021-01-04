package lich.tool.encryptionAndDecryption.proxy;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;

import lich.tool.conflictResolution.ClassProxy;


public class Proxy extends ClassProxy{
	
	private final static URL url=Proxy.class.getResource("lib/");
	protected final static  ClassLoader classLoader=initClassLoader(url);
	public static Provider BC;
	public static PrivateKey  rootGMPrivateKey;
	public static PublicKey  rootGMPublicKey;
	public static PrivateKey  rootRSAPrivateKey;
	public static PublicKey  rootRSAPublicKey;
	public Proxy(String className) throws ClassNotFoundException, InstantiationException,
		IllegalAccessException, MalformedURLException, URISyntaxException {
		super(className, classLoader);
	}
	static {
			try {
				Proxy baseProxy=new Proxy("lich.tool.encryptionAndDecryption.represented.Base");
				BC=(Provider)baseProxy.getFieldValue("BC");
				rootGMPrivateKey=(PrivateKey)baseProxy.getFieldValue("rootGMPrivateKey");
				rootGMPublicKey=(PublicKey)baseProxy.getFieldValue("rootGMPublicKey");
				rootRSAPrivateKey=(PrivateKey)baseProxy.getFieldValue("rootRSAPrivateKey");
				rootRSAPublicKey=(PublicKey)baseProxy.getFieldValue("rootRSAPublicKey");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}	
	
	
	
	
}
