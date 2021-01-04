package lich.tool.encryptionAndDecryption.ext;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lich.tool.encryptionAndDecryption.symmetric.Provider;
import lich.tool.encryptionAndDecryption.symmetric.SymmetricTool;

/**
 * SM4扩展接口
 * @author liuch
 *
 */
public interface SM4Ext {
	/**
	 * SM4加密
	 * @param in 原文
	 * @param keyBytes 密钥
	 * @return 加密数据
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	  public default byte[] encrypt(byte[] in, byte[] keyBytes) throws Exception {
		  return  SymmetricTool.encryptEcb(in, keyBytes, Provider.Cipher.SM4_ECB_NOPadding);
	  }
	  /**
	   * SM4解密
	   * @param in 密文
	   * @param keyBytes 密钥
	   * @return 姐猕猴数据
	
	   */
	  public default  byte[] decrypt(byte[] in, byte[] keyBytes) throws Exception {
		  return  SymmetricTool.decryptEcb(in, keyBytes, Provider.Cipher.SM4_ECB_NOPadding);
	  }
}
