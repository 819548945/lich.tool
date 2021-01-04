package lich.tool.encryptionAndDecryption.asymmetric;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.proxy.Proxy;

/**
 * 密钥对生成工具
 * @author liuch
 *
 */
public class KeyPairTool{
	private static Proxy keyPairToolProxy;
	static{
		try {
			keyPairToolProxy=new Proxy("lich.tool.encryptionAndDecryption.represented.KeyPairTool");
		} catch (Exception e) {
		}
	}
  
	/**
	 * SM2密钥对生成
	 * @return SM2密钥对
	 * @throws EncryptionAndDecryptionException 
	 */
	public static KeyPair generateGMKeyPair() throws EncryptionAndDecryptionException {
		try {
			return (KeyPair)keyPairToolProxy.execStatic("generateGMKeyPair");
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * RSA密钥对生成
	 * @param keySize 密钥长度
	 * @return RSA密钥对
	 * @throws EncryptionAndDecryptionException 
	 */
	public static  KeyPair generateRSAKeyPair(int keySize) throws EncryptionAndDecryptionException {
		try {
			return (KeyPair)keyPairToolProxy.execStatic("generateRSAKeyPair", new Parameters().addParameter(keySize));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	
	
	
}
