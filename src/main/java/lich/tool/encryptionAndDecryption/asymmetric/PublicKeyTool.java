package lich.tool.encryptionAndDecryption.asymmetric;


import java.security.PublicKey;
import java.security.cert.Certificate;
import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.asymmetric.OtherObj.PublicKeyInfo;
import lich.tool.encryptionAndDecryption.proxy.Proxy;


public class PublicKeyTool{
	private static Proxy publicKeyToolProxy;
	static{
		try {
			publicKeyToolProxy=new Proxy("lich.tool.encryptionAndDecryption.represented.PublicKeyTool");
		} catch (Exception e) {
		}
	}
	
	
	
	public static PublicKey x509CertificateToPublicKey(byte [] x509Certificate) throws  EncryptionAndDecryptionException{
		try {
			return (PublicKey)publicKeyToolProxy.execStatic("x509CertificateToPublicKey", new Parameters().addParameter(x509Certificate));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	/**
	 * 生成公钥证书
	 * @param pki 公钥信息
	 * @param pk 公钥
	 * @param ii 钱发人信息
	 * @return 公钥证书
	 * @throws EncryptionAndDecryptionException 
	 */
	public static Certificate getX509Certificate(PublicKeyInfo pki,PublicKey pk) throws EncryptionAndDecryptionException{
		try {
			return (Certificate)publicKeyToolProxy.execStatic("getX509Certificate", new Parameters().addParameter(pki).addParameter(pk));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	/**
	 * GM公钥生成publicKey
	 * @param P 04|x|y
	 * @return PublicKey
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PublicKey toGMPublicKey(byte[] P) throws EncryptionAndDecryptionException {
		try {
			return (PublicKey)publicKeyToolProxy.execStatic("toGMPublicKey", new Parameters().addParameter(P));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
			
	}
	/**
	 * RSA公钥生成公钥证书 参数N 65537
	 * @param E 公钥
	 * @return BCRSAPublicKey
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PublicKey toRSAPublicKey(byte[] N) throws EncryptionAndDecryptionException {
		byte[] E= {0x01,0x00,0x01};
		return toRSAPublicKey(N,E);	
	}
	/**
	 * RSA公钥生成公钥证书
	 * @param E 公钥
	 * @param N 公钥参数
	 * @return BCRSAPublicKey
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PublicKey toRSAPublicKey(byte[] N,byte[] E) throws EncryptionAndDecryptionException {
		try {
			return (PublicKey)publicKeyToolProxy.execStatic("toGMPublicKey", new Parameters().addParameter(N).addParameter(E));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
			
	}
	/**
	 * 获取公钥byte
	 * @param publicKey公钥
	 * @return GM P 04|x|y
	 * 		   RSA N 
	 * @throws EncryptionAndDecryptionException
	 */
	public static byte[] getPublicKeyByte(PublicKey publicKey) throws EncryptionAndDecryptionException {
		try {
			return (byte[])publicKeyToolProxy.execStatic("getPublicKeyByte", new Parameters().addParameter(publicKey));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	
	
}
