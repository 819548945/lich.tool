package lich.tool.encryptionAndDecryption.symmetric;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lich.tool.encryptionAndDecryption.proxy.Proxy;


public class SymmetricTool extends Proxy{
	protected SymmetricTool(String className) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, MalformedURLException, URISyntaxException {
		super(className);
	}
	public static byte[] encryptEcb(byte [] ori,byte [] pwd,lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher algorithm) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher c=Cipher.getInstance(algorithm.getAlgorithm(), BC);
		SecretKey key=new SecretKeySpec(pwd, algorithm.getKeyType());
    	c.init(Cipher.ENCRYPT_MODE, key);
		return c.doFinal(ori);
	}
	public static byte[] decryptEcb(byte [] enc,byte [] pwd,lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher algorithm) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher c=Cipher.getInstance(algorithm.getAlgorithm(), BC);
		SecretKey key=new SecretKeySpec(pwd, algorithm.getKeyType());
    	c.init(Cipher.DECRYPT_MODE, key,new SecureRandom());
		return c.doFinal(enc);
	}
	public static byte[] encryptCbc(byte [] ori,byte [] pwd,lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher algorithm,byte [] iv) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		Cipher c=Cipher.getInstance(algorithm.getAlgorithm(), BC);
		SecretKey key=new SecretKeySpec(pwd, algorithm.getKeyType());
    	c.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(iv));
		return c.doFinal(ori);
	}
	public static byte[] decryptCbc(byte [] enc,byte [] pwd,lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher algorithm,byte [] iv) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		Cipher c=Cipher.getInstance(algorithm.getAlgorithm(), BC);
		SecretKey key=new SecretKeySpec(pwd, algorithm.getKeyType());
    	c.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(iv));
		return c.doFinal(enc);
	}
}
