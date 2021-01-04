package lich.tool.encryptionAndDecryption.asymmetric;

import java.math.BigInteger;
import java.security.PrivateKey;
import lich.tool.conflictResolution.Parameters;
import lich.tool.encryptionAndDecryption.EncryptionAndDecryptionException;
import lich.tool.encryptionAndDecryption.proxy.Proxy;

public class PrivateKeyTool{
	private static Proxy privateKeyToolProxy;
	static{
		try {
			privateKeyToolProxy=new Proxy("lich.tool.encryptionAndDecryption.represented.PrivateKeyTool");
		} catch (Exception e) {
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
			return (PrivateKey)privateKeyToolProxy.execStatic("toGMPrivateKey", new Parameters().addParameter(d).addParameter(P));
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
			return (PrivateKey)privateKeyToolProxy.execStatic("toGMPrivateKeyByEnvelopedKeyBlob", new Parameters().addParameter(doubleprvkey));
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
			return (PrivateKey)privateKeyToolProxy.execStatic("toGMPrivateKeyByEnvelopedKeyBlob", new Parameters().addParameter(doubleprvkey).addParameter(privateKey));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * GM私钥加载 （使用内置密钥解密）
	 * @param doubleprvkey signedAndEnvelopedData 私钥保护结构体 详见GM/T-0010-2012
	 * @param pukcert 待解密私钥的公钥 04|x|y
	 * @return BCECPrivateKey
	 * @throws Exception
	 */
	public static PrivateKey toGMPrivateKeyBySignedAndEnvelopedData(byte [] doubleprvkey,byte[] puk) throws Exception{
		try {
			return (PrivateKey)privateKeyToolProxy.execStatic("toGMPrivateKeyBySignedAndEnvelopedData", new Parameters().addParameter(doubleprvkey).addParameter(puk));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}
	/**
	 * GM私钥加载 
	 * @param doubleprvkey signedAndEnvelopedData 私钥保护结构体 详见GM/T-0010-2012
	 * @param privateKey 解密私钥
	 * @param pukcert 待解密私钥的公钥 04|x|y
	 * @return BCECPrivateKey
	 * @throws Exception
	 */
	public static PrivateKey toGMPrivateKeyBySignedAndEnvelopedData(byte [] doubleprvkey,PrivateKey privateKey,byte[] puk) throws Exception{
		try {
			return (PrivateKey)privateKeyToolProxy.execStatic("toGMPrivateKeyBySignedAndEnvelopedData", new Parameters().addParameter(doubleprvkey).addParameter(privateKey).addParameter(puk));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
	}

	/**
	 * GM私钥导出（使用内置密钥加密）
	 * @param gmPrivateKey BCECPrivateKey
	 * @return 
	 */
	public static  byte[] toEnvelopedKeyBlobByGMPrivateKey(PrivateKey gmPrivateKey){
		return null;
	}
	
	/**
	 * rsa pkcs8 to PrivateKey
	 * @param pk pkcs8格式私钥匙
	 * @return BCRSAPrivateKey 或null
	 * @throws EncryptionAndDecryptionException 
	 */
	public static PrivateKey toRSAPrivateKey(byte [] pk) throws EncryptionAndDecryptionException {
		try {
			return (PrivateKey)privateKeyToolProxy.execStatic("toRSAPrivateKey", new Parameters().addParameter(pk));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	private static PrivateKey toRSAPrivateKey(BigInteger n,BigInteger e,BigInteger d,BigInteger p,BigInteger q,BigInteger dP,BigInteger dQ,BigInteger qInv) throws EncryptionAndDecryptionException{
		try {
			Parameters parameters=	new Parameters().addParameter(n).addParameter(e).addParameter(d).addParameter(p).addParameter(q).addParameter(dP).addParameter(dQ).addParameter(qInv);
			return (PrivateKey)privateKeyToolProxy.execStatic("toRSAPrivateKey", parameters);
		} catch (Exception ex) {
			throw new EncryptionAndDecryptionException(ex);
		}
	}
	
	private static byte[] CbEncryptedPrivKeygetSM2EncData(byte[] b) throws EncryptionAndDecryptionException{
		try {
			return (byte[])privateKeyToolProxy.execStatic("CbEncryptedPrivKeygetSM2EncData", new Parameters().addParameter(b));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
}
