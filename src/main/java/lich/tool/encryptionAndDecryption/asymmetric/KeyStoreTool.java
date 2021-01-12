package lich.tool.encryptionAndDecryption.asymmetric;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.Proxy;
import lich.tool.encryptionAndDecryption.asymmetric.OtherObj.P12Data;
import lich.tool.encryptionAndDecryption.asymmetric.OtherObj.PublicKeyInfo;
import lich.tool.object.CopyTools;
/**
 * 密钥对工具类
 * @author liuch
 *
 */
public class KeyStoreTool {
	private static Proxy keyStoreToolProxy;
	private static void init() throws EncryptionAndDecryptionException {
		try {
			if(keyStoreToolProxy==null) {
				keyStoreToolProxy=new Proxy("lich.tool.encryptionAndDecryption.core.asymmetric.KeyStoreTool");
			
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	/**
	 * 导出p12
	 * @param keyPair 密钥对
	 * @param pki 公钥信息
	 * @param alias 别名
	 * @param pwd 密码
	 * @return p12
	 * @throws EncryptionAndDecryptionException 
	 */
	public static byte[] toPKCS12(KeyPair keyPair,PublicKeyInfo pki,String alias,String pwd) throws EncryptionAndDecryptionException{
		try {
			init();
			return (byte[])keyStoreToolProxy.exec("toPKCS12", new Parameters().addParameter(keyPair).addParameter(pki).addParameter(alias).addParameter(pwd));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 *  导出p12
	 * @param prk 私钥
	 * @param certs 公钥证书
	 * @param alias 别名
	 * @param pwd 密码
	 * @return p12
	 * @throws EncryptionAndDecryptionException 
	 */
	public static byte[] toPKCS12(PrivateKey prk,Certificate[] certs,String alias,String pwd) throws EncryptionAndDecryptionException  {
		try {
			init();
			return (byte[])keyStoreToolProxy.exec("toPKCS12", new Parameters().addParameter(prk).addParameter(certs).addParameter(alias).addParameter(pwd));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * 加载p12
	 * @param p12 p12
	 * @param pwd 密码
	 * @return P12Data
	 * @throws EncryptionAndDecryptionException 
	 */
	public static P12Data loadPKCS12(byte [] p12,String pwd) throws EncryptionAndDecryptionException {
		try {
			init();
			P12Data p12Data=new P12Data();
			CopyTools.copyField(keyStoreToolProxy.exec("loadPKCS12", new Parameters().addParameter(p12).addParameter(pwd)), p12Data);
			return (p12Data);
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * 生成p10证书申请请求
	 * @param privateKey 私钥
	 * @param publicKey 公钥
	 * @param dn dn
	 * @param algorithm 签名标识
	 * @return p10der
	 * @throws EncryptionAndDecryptionException 
	 */
	public static byte[] toPKCS10(PrivateKey privateKey,PublicKey publicKey,String dn,String algorithm) throws EncryptionAndDecryptionException{
		try {
			init();
			return (byte[] )keyStoreToolProxy.exec("toPKCS10", new Parameters().addParameter(PrivateKey.class,privateKey).addParameter(PublicKey.class,publicKey).addParameter(dn).addParameter(algorithm));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * 生成p10证书申请请求
	 * @param kp 密钥对
	 * @param dn dn
	 * @param algorithm 签名标识
	 * @return p10der
	 * @throws OperatorCreationException
	 * @throws IOException
	 * @throws EncryptionAndDecryptionException
	 */
	public static byte[] toPKCS10(KeyPair kp,String dn,String algorithm) throws EncryptionAndDecryptionException  {
		return toPKCS10(kp.getPrivate(),kp.getPublic(), dn, algorithm);
	} 
	
}
