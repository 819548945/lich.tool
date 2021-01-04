package lich.tool.encryptionAndDecryption.symmetric;

public class Provider {
	
		public enum Cipher{	
			SM4_CBC_NOPadding("SM4/CBC/NOPadding","SM4"),
			SM4_CBC_PKCS5Padding("SM4/CBC/PKCS5Padding","SM4"),
			SM4_CBC_PKCS7Padding("SM4/CBC/PKCS7Padding","SM4"),
			SM4_ECB_NOPadding("SM4/ECB/NOPadding","SM4"),
			SM4_ECB_PKCS5Padding("SM4/ECB/PKCS5Padding","SM4"),
			SM4_ECB_PKCS7Padding("SM4/ECB/PKCS7Padding","SM4");
			private  Cipher(String algorithm,String keyType) {
				this.algorithm=algorithm;
				this.keyType=keyType;
			}
			private String algorithm;
			private String keyType;
			public String getAlgorithm() {
				return algorithm;
			}
			public String getKeyType() {
				return keyType;
			}
		}
		
	
}
