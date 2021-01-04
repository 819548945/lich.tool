package lich.tool.encryptionAndDecryption;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher;
import lich.tool.encryptionAndDecryption.symmetric.SymmetricTool;



public class TestSymmetric {
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		new TestSymmetric().test();
	}
	@Test
	public void test() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		testEcb(lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher.SM4_ECB_NOPadding);
		testEcb(lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher.SM4_ECB_PKCS5Padding);
		testEcb(lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher.SM4_ECB_PKCS7Padding);
		testCbc(lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher.SM4_CBC_NOPadding);
		testCbc(lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher.SM4_CBC_PKCS5Padding);
		testCbc(lich.tool.encryptionAndDecryption.symmetric.Provider.Cipher.SM4_CBC_PKCS7Padding);
		
	}
	public void testEcb(Cipher c) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		System.out.println( "----------test:"+c+"---------------");
		String pwd="1234567812345678";
		String ori="1111111111111111";
		System.out.println("ori:"+ori);
		byte[] enc=SymmetricTool.encryptEcb(ori.getBytes(), pwd.getBytes(), c);
		System.out.println("enc:"+Hex.encodeHexString(enc));
		byte[] dec=SymmetricTool.decryptEcb(enc, pwd.getBytes(),c);
		System.out.println("dec:"+new String(dec));
		System.out.println( "----------test:"+c+" OK---------------");
		
	}
	public void testCbc(Cipher c) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		
		byte[] b= {1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};
		System.out.println( "----------test:"+c+"---------------");
		String pwd="1234567812345678";
		String ori="1111111111111111";
		System.out.println("ori:"+ori);
		byte[] enc=SymmetricTool.encryptCbc(ori.getBytes(), pwd.getBytes(), c,b);
		System.out.println("enc:"+Hex.encodeHexString(enc));
		byte[] dec=SymmetricTool.decryptCbc(enc, pwd.getBytes(),c,b);
		System.out.println("dec:"+new String(dec));
		System.out.println( "----------test:"+c+" OK---------------");
		
	}
}
