package lich.tool.encryptionAndDecryption;

public class EncryptionAndDecryptionException extends Exception{
	public EncryptionAndDecryptionException(String msg) {
		super(msg);	
	}
	public EncryptionAndDecryptionException(Exception e) {
		super(e);	
	}
}
