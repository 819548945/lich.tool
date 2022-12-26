package lich.tool.encryptionAndDecryption;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;

import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.ext.SM1Ext;
import lich.tool.encryptionAndDecryption.ext.SM4Ext;
/**
 * lich.tool.encryptionAndDecryption.core.Base代理
 * @author liuch
 *
 */
public class Base {
	private static Proxy baseProxy;
	private static void init() throws EncryptionAndDecryptionException {
		try {
			if(baseProxy==null) {  
				baseProxy=new Proxy("lich.tool.encryptionAndDecryption.core.Base");
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
		
	}
	public static Provider getBC() throws EncryptionAndDecryptionException {
		try {
			init();
			return (Provider)baseProxy.getFieldValue("BC");
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
		
		
	}
	
	
	/**
	 * 配置SM1实现
	 * @param  sm1Ext
	 */
	public static void setSM1Ext(SM1Ext sm1Ext) throws EncryptionAndDecryptionException{
		try {
			init();
			String cn=sm1Ext.getClass().getName();
			InputStream in=	sm1Ext.getClass().getResourceAsStream(cn.substring(cn.lastIndexOf(".")+1)+".class");
			Proxy obj=new Proxy(cn,in);
			baseProxy.exec("setSm1ext",  new Parameters().addParameter(new Proxy("lich.tool.encryptionAndDecryption.ext.SM1Ext").getClsss(),obj.newInstance().getObj())  );
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}		
	}
	/**
	 * 配置SM4实现
	 * @param sm4Ext
	 * @throws EncryptionAndDecryptionException 
	 */
	public static void setSM4Ext(SM4Ext sm4Ext) throws EncryptionAndDecryptionException{
		try {
			init();
			String cn=sm4Ext.getClass().getName();
			InputStream in=	sm4Ext.getClass().getResourceAsStream(cn.substring(cn.lastIndexOf(".")+1)+".class");
			Proxy obj=new Proxy(cn,in);
			baseProxy.exec("setSm4ext",  new Parameters().addParameter(new Proxy("lich.tool.encryptionAndDecryption.ext.SM4Ext").getClsss(),obj.newInstance().getObj())  );
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}	
	/**
	 * 配置GM根证
	 * @param pk PrivateKey
	 * @param cert X509Certificate
	 * @throws EncryptionAndDecryptionException 
	 */
	public void setGMroot(PrivateKey pk,X509Certificate cert) throws EncryptionAndDecryptionException  {
		try {
			init();
			baseProxy.setFieldValue("rootGMPrivateKey", pk);
			baseProxy.setFieldValue("rootGMX509Certificate", cert);
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
		
	}
	/**
	 * 配置RSA根证
	 * @param pk PrivateKey
	 * @param cert X509Certificate
	 * @throws EncryptionAndDecryptionException 
	 */
	public void setRSAroot(PrivateKey pk,X509Certificate cert) throws EncryptionAndDecryptionException {
		try {
			init();
			baseProxy.setFieldValue("rootRSAPrivateKey", pk);
			baseProxy.setFieldValue("rootRSAX509Certificate", cert);
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	
	/**
	 * 获取GM根 PrivateKey
	 * @return PrivateKey
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PrivateKey getRootGMPrivateKey() throws EncryptionAndDecryptionException {
		try {
			init();
			return (PrivateKey)baseProxy.getFieldValue("rootGMPrivateKey");
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * 获取GM根 X509Certificate
	 * @return X509Certificate
	 * @throws EncryptionAndDecryptionException 
	 */
	public static X509Certificate getRootGMX509Certificate() throws EncryptionAndDecryptionException {
		try {
			init();
			return (X509Certificate)baseProxy.getFieldValue("rootGMX509Certificate");
		} catch (Exception e) {
			e.printStackTrace();
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * 获取RSA根 PrivateKey
	 * @return PrivateKey
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PrivateKey getRootRSAPrivateKey() throws EncryptionAndDecryptionException {
		try {
			init();
			return (PrivateKey)baseProxy.getFieldValue("rootRSAPrivateKey");
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * 获取RSA根 X509Certificate
	 * @return X509Certificate
	 * @throws EncryptionAndDecryptionException 
	 */
	public static X509Certificate getRootRSAX509Certificate() throws EncryptionAndDecryptionException {
		try {
			init();
			return (X509Certificate)baseProxy.getFieldValue("rootRSAX509Certificate");
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	
}
