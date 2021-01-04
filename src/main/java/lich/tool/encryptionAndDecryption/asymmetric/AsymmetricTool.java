package lich.tool.encryptionAndDecryption.asymmetric;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;

import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.asymmetric.OtherObj.PublicKeyInfo;
import lich.tool.encryptionAndDecryption.proxy.Proxy;

public class AsymmetricTool{
	private static Proxy asymmetricToolProxy;
	static{
		try {
			asymmetricToolProxy=new Proxy("lich.tool.encryptionAndDecryption.represented.AsymmetricTool");
		} catch (Exception e) {
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
			return (byte [])asymmetricToolProxy.execStatic("encrypt", new Parameters().addParameter(data).addParameter(PublicKey.class,publicKey).addParameter(algorithm));
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
			return (byte [])asymmetricToolProxy.execStatic("decrypt", new Parameters().addParameter(encodedataByte).addParameter(PrivateKey.class,privateKey).addParameter(algorithm));
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
			return (byte [])asymmetricToolProxy.execStatic("sign", new Parameters().addParameter(ori).addParameter(PrivateKey.class,privateKey).addParameter(algorithm));
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
			return (boolean)asymmetricToolProxy.execStatic("verify", new Parameters().addParameter(sign).addParameter(ori).addParameter(PublicKey.class,publicKey).addParameter(algorithm));
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

}
