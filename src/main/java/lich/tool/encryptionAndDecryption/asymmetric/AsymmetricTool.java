package lich.tool.encryptionAndDecryption.asymmetric;


import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.Proxy;


public class AsymmetricTool{
	private static Proxy asymmetricToolProxy;
	private static void init() throws EncryptionAndDecryptionException {
		try {
			if(asymmetricToolProxy==null) {
				asymmetricToolProxy=new Proxy("lich.tool.encryptionAndDecryption.core.asymmetric.AsymmetricTool");
			
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	/**
	 * 公钥加密
	 * GM模式 加密数据结构为C1C2C3
	 * @param data 待加密数据
	 * @param publicKey 公钥
	 * @param algorithm  摘要算法
	 * @return 加密数据
	 * @throws EncryptionAndDecryptionException
	 */
	public static byte [] encrypt(byte[] data, PublicKey publicKey,String algorithm) throws EncryptionAndDecryptionException {
		try {
			init();
			return (byte [])asymmetricToolProxy.exec("encrypt", new Parameters().addParameter(data).addParameter(PublicKey.class,publicKey).addParameter(algorithm));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
    }
	/**
	 * 私钥解密
	 * GM模式 加密数据结构为C1C2C3
	 * @param encodedataByte 加密数据
	 * @param privateKey 私钥
	 * @param algorithm 摘要算法
	 * @return 解密数据
	 * @throws EncryptionAndDecryptionException 
	 
	 */
    public static byte [] decrypt(byte[]  encodedataByte, PrivateKey privateKey,String algorithm) throws EncryptionAndDecryptionException {
    	try {
    		init();
			return (byte [])asymmetricToolProxy.exec("decrypt", new Parameters().addParameter(encodedataByte).addParameter(PrivateKey.class,privateKey).addParameter(algorithm));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
    }
    /**
     * 签名
     * @param ori 原文
     * @param privateKey 私钥
     * @return 签名值
     * @throws EncryptionAndDecryptionException 
     */
    public static byte [] sign(byte[] ori, PrivateKey privateKey,String algorithm) throws EncryptionAndDecryptionException {
    	try {
    		init();
			return (byte [])asymmetricToolProxy.exec("sign", new Parameters().addParameter(ori).addParameter(PrivateKey.class,privateKey).addParameter(algorithm));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
    }
    /**
     * 签名
     * @param ori 原文
     * @param privateKey 私钥
     * @param cert 公钥证书
     * @return 签名值
     * @throws EncryptionAndDecryptionException 
     */
    public static byte [] sign(byte[] ori, PrivateKey privateKey,X509Certificate cert) throws EncryptionAndDecryptionException {
    	return sign( ori, privateKey,cert.getSigAlgName()); 
    }
   
   /**
    * 验签
    * @param sign 签名值
    * @param ori 原文
    * @param publicKey 公钥
    * @param algorithm 摘要算法
    * @return true false
    * @throws EncryptionAndDecryptionException 
    */
    public static boolean verify(byte[] sign,byte[] ori, PublicKey publicKey,String algorithm) throws EncryptionAndDecryptionException {
    	try {
    		init();
			return (Boolean)asymmetricToolProxy.exec("verify", new Parameters().addParameter(sign).addParameter(ori).addParameter(PublicKey.class,publicKey).addParameter(algorithm));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
    }
    /** 
     * 验签
     * @param sign 签名值
     * @param ori 原文
     * @param cert 公钥证书
     * @return true false
     * @throws EncryptionAndDecryptionException
     */
    public static boolean verify(byte[] sign,byte[] ori,X509Certificate cert) throws EncryptionAndDecryptionException  {
    	return verify(sign,ori, cert.getPublicKey(),cert.getSigAlgName());
    }
    
    /**
	 * SM2加密数据格式转换 
	 * @param b
	 * @return c1c2c3
     * @throws EncryptionAndDecryptionException 
	 */
	public static byte[] SM2CipherToSM2EncDataC1C2C3(byte[] b)throws EncryptionAndDecryptionException {	
		try {
			init();
			return (byte[])asymmetricToolProxy.exec("SM2CipherToSM2EncDataC1C2C3", new Parameters().addParameter(b));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * SM2加密数据格式转换 
	 * @param b
	 * @return SM2Cipher
	 * @throws EncryptionAndDecryptionException 
	 */
	public static byte[] SM2EncDataC1C2C3ToSM2Cipher(byte[] b) throws EncryptionAndDecryptionException {	
		try {
			init();
			return (byte[])asymmetricToolProxy.exec("SM2EncDataC1C2C3ToSM2Cipher", new Parameters().addParameter(b));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * SM2签名数据格式转换 
	 * @param b
	 * @return rs
	 * @throws EncryptionAndDecryptionException 
	 * @throws IOException
	 */
	public static byte[]  SM2SignatureToRS(byte[] b) throws EncryptionAndDecryptionException  {
		try {
			init();
			return (byte[])asymmetricToolProxy.exec("SM2SignatureToRS", new Parameters().addParameter(b));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * SM2签名数据格式转换
	 * @param b
	 * @return SM2Signature
	 * @throws EncryptionAndDecryptionException 
	 */
	public static byte[] RSToSM2Signature(byte[] b) throws EncryptionAndDecryptionException {
		try {
			init();
			return (byte[])asymmetricToolProxy.exec("RSToSM2Signature", new Parameters().addParameter(b));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	} 
    
	/**
   	 * SM2加密数据格式转换 
   	 * @param b SM2Cipher
   	 * @return 国密C1C3C2
	 * @throws EncryptionAndDecryptionException 
   	 */
     public static byte[]  SM2CipherTOGMC1C3C2(byte[] b) throws  EncryptionAndDecryptionException{
    	 try {
 			init();
 			return (byte[])asymmetricToolProxy.exec("SM2CipherTOGMC1C3C2", new Parameters().addParameter(b));
 		} catch (Exception e) {
 			throw new EncryptionAndDecryptionException(e);
 		}
     }
     /**
	 * SM2加密数据格式转换 
	 * @param b 国密C1C3C2
	 * @return SM2Cipher
	 * @throws IOException
	 */
     public static byte[]   GMC1C3C2TOSM2Cipher(byte[] b) throws  EncryptionAndDecryptionException{
    	 try {
  			init();
  			return (byte[])asymmetricToolProxy.exec("GMC1C3C2TOSM2Cipher", new Parameters().addParameter(b));
  		} catch (Exception e) {
  			throw new EncryptionAndDecryptionException(e);
  		}
     }
}
