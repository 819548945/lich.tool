package lich.tool.encryptionAndDecryption.asymmetric;

import java.security.KeyPair;
import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.Proxy;

/**
 * 密钥对生成工具
 * @author liuch
 *
 */
public class KeyPairTool{
	private static Proxy keyPairToolProxy;
	private static void init() throws EncryptionAndDecryptionException {
		try {
			if(keyPairToolProxy==null) {
				keyPairToolProxy=new Proxy("lich.tool.encryptionAndDecryption.core.asymmetric.KeyPairTool");
			
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	
	/**
	 * SM2密钥对生成
	 * @return SM2密钥对
	 * @throws EncryptionAndDecryptionException 
	 */
	public static KeyPair generateGMKeyPair() throws EncryptionAndDecryptionException {
		try {
			init();
			return (KeyPair)keyPairToolProxy.exec("generateGMKeyPair");
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
			init();
			return (KeyPair)keyPairToolProxy.exec("generateRSAKeyPair", new Parameters().addParameter(int.class,keySize));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	
	
	
}
