package lich.tool.encryptionAndDecryption;


import java.security.KeyPair;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import lich.tool.encryptionAndDecryption.asymmetric.KeyPairTool;
import lich.tool.encryptionAndDecryption.asymmetric.KeyStoreTool;
import lich.tool.encryptionAndDecryption.asymmetric.Provider;

public class TestPKCS10 {
	
	public static void main(String[] args) throws Exception {
		new TestPKCS10().testGMP10();
	}
	@Test
	public void testRSAP10() throws Exception {
		KeyPair k=KeyPairTool.generateRSAKeyPair(1204);
		byte[] b=KeyStoreTool.toPKCS10(k, "C=CN,O=lich", Provider.RSA.Signature.SHA1WithRSA);
		System.out.println("RSAP10:"+Base64.encodeBase64String(b));
	}
	@Test
	public void testGMP10() throws Exception {
		KeyPair k=KeyPairTool.generateGMKeyPair();
		byte[] b=KeyStoreTool.toPKCS10(k, "C=CN,O=lich", Provider.GM.Signature.SM3WITHSM2);
		System.out.println("GMP10:"+Base64.encodeBase64String(b));	
	}
}
