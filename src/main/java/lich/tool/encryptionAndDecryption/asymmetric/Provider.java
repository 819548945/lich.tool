package lich.tool.encryptionAndDecryption.asymmetric;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;


public class Provider {
	public static interface Type{}
	
	public static class Check{
		private static Map<String,Set<String>> values=new ConcurrentHashMap<String, Set<String>>();
		public static boolean contains(Class o,String s) {
				String	on=o.getName();
				if(values.containsKey(on)) {
					return values.get(on).contains(s.toUpperCase());
				}else {
					try {
						synchronized (values) {
							Set<String> ss=	new ConcurrentSkipListSet();
							Field[] fields = o.getDeclaredFields();
							for(Field field: fields)ss.add(((String)field.get(null)).toUpperCase());	
							values.put(on,ss);
							return values.get(on).contains(s.toUpperCase());
						}
					} catch (Exception e) {
						e.printStackTrace();
						return false;
					}
					
				}	
		}
	}
	public static class KeyStore implements Type{
		public static String PKCS12="pkcs12";
	}
	public static class RSA implements Type{	
		
		public static class KeyPairGenerator  implements Type{
			public static String RSA="RSA";
			public static String RSASSA_PSS="RSASSA_PSS";
			
		}
		public static class  Signature  implements Type{
			public static String DEFAULT="SHA1WithRSA";	
			public static String RSASSA_PSS="RSASSA-PSS";
			public static String RAWRSASSA_PSS="RAWRSASSA-PSS";	
			public static String SHA1WithRSA="SHA1WithRSA";	
			public static String SHA224WithRSA="SHA224WithRSA";	
			public static String SHA256WithRSA="SHA256WithRSA";
			public static String SHA384WithRSA="SHA384WithRSA";
			public static String SHA512WithRSA="SHA512WithRSA";
			public static String SHA3_224WithRSA="SHA3-224WithRSA";
			public static String SHA3_256WithRSA="SHA3-256WithRSA";
			public static String SHA3_384WithRSA="SHA3-384WithRSA";
			public static String SHA3_512WithRSA="SHA3-512WithRSA";
		}
		public static class  Cipher  implements Type{
			public static String RSA="RSA";
			//public static String RSA_RAW="RSA/RAW";
		//	public static String RSA_CBC_NOPADDING="RSA/CBC/NOPadding";
			public static String RSA_ECB_NOPADDING="RSA/ECB/NOPadding";
			public static String RSA_ECB_PKCS1PADDING="RSA/ECB/PKCS1Padding";
			//public static String RSA_ECB_PKCS5PADDING="RSA/ECB/PKCS5Padding";
			//public static String RSA_1="RSA/1";
			//public static String RSA_2="RSA_2";
			//public static String RSA_OAEP="RSA/OAEP";
			//public static String RSA_ISO9796_1="RSA/ISO9796-1";
		}
	};
	public static class GM  implements Type{
		public static class KeyPairGenerator  implements Type{
			public static String  EC="EC";
		}
		public static class  Signature  implements Type{
			public static String DEFAULT="SM3WITHSM2";	
			public static String  SHA256WITHSM2="SHA256WITHSM2";
			public static String  SM3WITHSM2="SM3WITHSM2";
			
		}
		public static class  Cipher  implements Type{
		
			public static String SM2="SM2";
			public static String SM2WITHSM3="SM2WITHSM3";
			public static String SM2WITHBLAKE2B="SM2WITHBLAKE2B";
			public static String SM2WITHBLAKE2S="SM2WITHBLAKE2S";
			public static String SM2WITHWHIRLPOOL="SM2WITHWHIRLPOOL";
			public static String SM2WITHMD5="SM2WITHMD5";
			public static String SM2WITHRIPEMD160="SM2WITHRIPEMD160";
			public static String SM2WITHSHA1="SM2WITHSHA1";
			public static String SM2WITHSHA224="SM2WITHSHA224";
			public static String SM2WITHSHA256="SM2WITHSHA256";
			public static String SM2WITHSHA384="SM2WITHSHA384";
			public static String SM2WITHSHA512="SM2WITHSHA512";
			
		}
		
	}
}
