package lich.tool.encryptionAndDecryption.asymmetric;


import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.Proxy;
import lich.tool.encryptionAndDecryption.asymmetric.OtherObj.PublicKeyInfo;


public class PublicKeyTool{
	private static Proxy publicKeyToolProxy;
	private static void init() throws EncryptionAndDecryptionException {
		try {
			if(publicKeyToolProxy==null) {
				publicKeyToolProxy=new Proxy("lich.tool.encryptionAndDecryption.core.asymmetric.PublicKeyTool");
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}

	public static PublicKey x509CertificateToPublicKey(byte [] x509Certificate) throws  EncryptionAndDecryptionException{
		try {
			init();
			return (PublicKey)publicKeyToolProxy.exec("x509CertificateToPublicKey", new Parameters().addParameter(x509Certificate));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	/**
	 * 加载X509证书
	 * @param x509Certificate
	 * @return x509Certificate
	 * @throws EncryptionAndDecryptionException
	 */
	public static Certificate 	loadX509Certificate(byte [] x509Certificate) throws EncryptionAndDecryptionException {
		try {
			init();
			return (Certificate)publicKeyToolProxy.exec("loadX509Certificate", new Parameters().addParameter(x509Certificate));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	
	/**
	 * 生成公钥证书
	 * @param pki 公钥信息
	 * @param pk 公钥
	 * @return 公钥证书
	 * @throws EncryptionAndDecryptionException 
	 */
	public static X509Certificate getX509Certificate(PublicKeyInfo pki,PublicKey pk) throws EncryptionAndDecryptionException{
		try {
			init();
			return (X509Certificate)publicKeyToolProxy.exec("getX509Certificate", new Parameters().addParameter(pki).addParameter(PublicKey.class,pk));
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
			init();
			return (PublicKey)publicKeyToolProxy.exec("toGMPublicKey", new Parameters().addParameter(P));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
			
	}
	/**
	 * RSA公钥生成公钥证书 参数N 65537
	 * @param N 公钥
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
			init();
			return (PublicKey)publicKeyToolProxy.exec("toGMPublicKey", new Parameters().addParameter(N).addParameter(E));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
			
	}
	/**
	 * 获取公钥byte
	 * @param publicKey 公钥
	 * @return GM P 04|x|y
	 * 		   RSA N 
	 * @throws EncryptionAndDecryptionException
	 */
	public static byte[] getPublicKeyByte(PublicKey publicKey) throws EncryptionAndDecryptionException {
		try {
			init();
			return (byte[])publicKeyToolProxy.exec("getPublicKeyByte", new Parameters().addParameter(PublicKey.class,publicKey));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	
	/**
	 * 获取p7b证书链上所有证书
	 * @param p7b p7b p7c bytes
	 * @param isCheckChain 是否校验证书链
	 * @return  X509Certificate [] 
	 * @throws EncryptionAndDecryptionException 证书链校验失败
	 */
	public static  X509Certificate[] loadP7bToChain(byte [] p7b,boolean isCheckChain) throws EncryptionAndDecryptionException {
		try {
			init();
			return (X509Certificate[])publicKeyToolProxy.exec("loadP7bToChain", new Parameters().addParameter(byte [].class,p7b).addParameter(boolean.class,isCheckChain));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
		
	}
	/**
	 *  获取p7b证书链上的证书
	 * @param p7b p7b p7c bytes
	 * @param isCheckChain 是否校验证书链
	 * @return X509Certificate
	 * @throws EncryptionAndDecryptionException 证书链校验失败
	 */
	public static X509Certificate loadP7bToX509Certificate(byte [] p7b,boolean isCheckChain) throws  EncryptionAndDecryptionException {
		try {
			init();
			return (X509Certificate)publicKeyToolProxy.exec("loadP7bToX509Certificate", new Parameters().addParameter(byte [].class,p7b).addParameter(boolean.class,isCheckChain));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 *  证书列表转换证书链
	 * @param certificateChain 证书列表
	 * @param isCheckChain 是否校验证书链
	 * @return X509Certificate
	 * @throws EncryptionAndDecryptionException 证书链校验失败
	 */
	public static byte[] certificateChainToP7b(X509Certificate [] certificateChain,boolean isCheckChain) throws  EncryptionAndDecryptionException {
		try {
			init();
			return (byte [])publicKeyToolProxy.exec("certificateChainToP7b", new Parameters().addParameter(X509Certificate [].class,certificateChain).addParameter(boolean.class,isCheckChain));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * 证书列表转换证书链
	 * @param certificateChain [i][证书bytes]
	 * @param isCheckChain 是否校验证书链
	 * @return p7b bytes
	 * @throws EncryptionAndDecryptionException 证书链校验失败
	 */
	public static byte[] certificateChainToP7b(byte [][] certificateChain,boolean isCheckChain) throws EncryptionAndDecryptionException {
		try {
			init();
			return (byte [])publicKeyToolProxy.exec("certificateChainToP7b", new Parameters().addParameter(byte [][].class,certificateChain).addParameter(boolean.class,isCheckChain));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
}
