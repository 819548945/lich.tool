package lich.tool.encryptionAndDecryption;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lich.tool.conflictResolution.ConflictResolutionException;
import lich.tool.conflictResolution.Parameters;

/**
 * 摘要工具类
 * @author liuch
 *
 */
public class DigestTool{
	
	private static Proxy digestTool;
	private static void init() throws EncryptionAndDecryptionException {
		try {
			if(digestTool==null) {
				digestTool=new Proxy("lich.tool.encryptionAndDecryption.core.DigestTool");	
			}
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}	
	}
	
	/**
	 * 获取摘要
	 * @param ori 原文
	 * @param algorithm 摘要算法
	 * @return 摘要
	 * @throws EncryptionAndDecryptionException 
	 */
	public static byte[] getDigest(byte [] ori,String algorithm) throws EncryptionAndDecryptionException  {
		
		try {
			init();
			return (byte[])digestTool.exec("getDigest",new Parameters().addParameter(byte[].class,ori).addParameter(algorithm));
		} catch (Exception e) {
			throw new EncryptionAndDecryptionException(e);
		}
		
	}
	
	
	
	
	
	
}
