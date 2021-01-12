package lich.tool.encryptionAndDecryption;

import org.junit.Test;

public class TestBase {
	
	@Test
	public void test() throws EncryptionAndDecryptionException, ClassNotFoundException{
		System.out.println(TestBase.class.getResource("lib"));
		Proxy.init(TestBase.class.getResource("lib"));
		System.out.println(Base.getRootGMPrivateKey());
		System.out.println(Base.getRootGMX509Certificate());
		System.out.println(Base.getRootRSAPrivateKey());
		System.out.println(Base.getRootRSAX509Certificate());
		
	}
	
}
