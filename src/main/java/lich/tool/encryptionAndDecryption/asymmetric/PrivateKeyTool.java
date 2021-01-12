package lich.tool.encryptionAndDecryption.asymmetric;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;

import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.Base;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.Proxy;

public class PrivateKeyTool{
	private static Proxy privateKeyToolProxy;
	private static void init() throws EncryptionAndDecryptionException {
		try {
			if(privateKeyToolProxy==null) {
				privateKeyToolProxy=new Proxy("lich.tool.encryptionAndDecryption.core.asymmetric.PrivateKeyTool");
			
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	/**
	 * GM私钥加载
	 * @param d 私钥
	 * @param P 公钥
	 * @return BCECPrivateKey
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PrivateKey toGMPrivateKey(byte [] d,byte [] P) throws EncryptionAndDecryptionException{
		try {
			init();
			return (PrivateKey)privateKeyToolProxy.exec("toGMPrivateKey", new Parameters().addParameter(d).addParameter(P));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	
	
	/**
	 * GM私钥加载（使用内置密钥解密）
	 * @param doubleprvkey EnvelopedKeyBlob私钥保护结构体 详见GM/T-0016-2012
	 * @return BCECPrivateKey
	 * @throws Exception
	 */
	public static PrivateKey toGMPrivateKeyByEnvelopedKeyBlob(byte [] doubleprvkey) throws Exception{
		try {
			init();
			return (PrivateKey)privateKeyToolProxy.exec("toGMPrivateKeyByEnvelopedKeyBlob", new Parameters().addParameter(doubleprvkey));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * GM私钥加载
	 * @param doubleprvkey EnvelopedKeyBlob私钥保护结构体 详见GM/T-0016-2012
	 * @param privateKey 解密私钥
	 * @return BCECPrivateKey
	 * @throws Exception
	 */
	public static PrivateKey toGMPrivateKeyByEnvelopedKeyBlob(byte [] doubleprvkey,PrivateKey privateKey) throws Exception {
		try {
			init();
			return (PrivateKey)privateKeyToolProxy.exec("toGMPrivateKeyByEnvelopedKeyBlob", new Parameters().addParameter(doubleprvkey).addParameter(privateKey));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * GM私钥加载 （使用内置密钥解密）
	 * @param doubleprvkey signedAndEnvelopedData 私钥保护结构体 详见GM/T-0010-2012
	 * @param puk 待解密私钥的公钥 04|x|y
	 * @return BCECPrivateKey
	 * @throws Exception
	 */
	public static PrivateKey toGMPrivateKeyBySignedAndEnvelopedData(byte [] doubleprvkey,byte[] puk) throws Exception{
		try {
			init();
			return (PrivateKey)privateKeyToolProxy.exec("toGMPrivateKeyBySignedAndEnvelopedData", new Parameters().addParameter(doubleprvkey).addParameter(puk));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * GM私钥加载 
	 * @param doubleprvkey signedAndEnvelopedData 私钥保护结构体 详见GM/T-0010-2012
	 * @param privateKey 解密私钥
	 * @param puk 待解密私钥的公钥 04|x|y
	 * @return BCECPrivateKey
	 * @throws Exception
	 */
	public static PrivateKey toGMPrivateKeyBySignedAndEnvelopedData(byte [] doubleprvkey,PrivateKey privateKey,byte[] puk) throws Exception{
		try {
			init();
			return (PrivateKey)privateKeyToolProxy.exec("toGMPrivateKeyBySignedAndEnvelopedData", new Parameters().addParameter(doubleprvkey).addParameter(privateKey).addParameter(puk));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}

	/**
	 * GM私钥导出（使用内置密钥加密）
	 * @param gmPrivateKey BCECPrivateKey
	 * @return   EnvelopedKeyBlob
	 * @throws EncryptionAndDecryptionException 
	 */
	public static  byte[] toEnvelopedKeyBlobByGMPrivateKey(PrivateKey gmPrivateKey) throws EncryptionAndDecryptionException{
		return toEnvelopedKeyBlobByGMPrivateKey(gmPrivateKey,Base.getRootGMX509Certificate().getPublicKey());
	}
	/**
	 * GM私钥导出
	 * @param gmPrivateKey 被导出的密钥
	 * @param encGmPublicKey 加密密钥
	 * @return EnvelopedKeyBlob
	 * @throws EncryptionAndDecryptionException 
	 */
	public static  byte[] toEnvelopedKeyBlobByGMPrivateKey(PrivateKey gmPrivateKey,PublicKey encGmPublicKey) throws EncryptionAndDecryptionException{
		try {
			init();
			return (byte[])privateKeyToolProxy.exec("toEnvelopedKeyBlobByGMPrivateKey", new Parameters().addParameter(PrivateKey.class,gmPrivateKey).addParameter(PublicKey.class,encGmPublicKey));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * rsa pkcs8 to PrivateKey
	 * @param pk pkcs8格式私钥匙
	 * @return BCRSAPrivateKey 或null
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PrivateKey toRSAPrivateKey(byte [] pk) throws EncryptionAndDecryptionException {
		try {
			init();
			return (PrivateKey)privateKeyToolProxy.exec("toRSAPrivateKey", new Parameters().addParameter(pk));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	private static PrivateKey toRSAPrivateKey(BigInteger n,BigInteger e,BigInteger d,BigInteger p,BigInteger q,BigInteger dP,BigInteger dQ,BigInteger qInv) throws EncryptionAndDecryptionException{
		try {
			init();
			Parameters parameters=	new Parameters().addParameter(n).addParameter(e).addParameter(d).addParameter(p).addParameter(q).addParameter(dP).addParameter(dQ).addParameter(qInv);
			return (PrivateKey)privateKeyToolProxy.exec("toRSAPrivateKey", parameters);
		} catch (Exception ex) {
			throw new EncryptionAndDecryptionException(ex);
		}
	}
	
	private static byte[] CbEncryptedPrivKeygetSM2EncData(byte[] b) throws EncryptionAndDecryptionException{
		try {
			init();
			return (byte[])privateKeyToolProxy.exec("CbEncryptedPrivKeygetSM2EncData", new Parameters().addParameter(b));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
}
