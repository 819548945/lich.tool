package lich.tool.object;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
/**
 * Replace object properties according to rules
 * @author liuch
 *
 */
@SuppressWarnings("unchecked")
public class ReplaceObject {
	private HashMap<String,ReplaceObjectRule> rules=new HashMap();
	private SimpleHashMap<Class,String> ignoreType=new SimpleHashMap();
	
	public ReplaceObject() {
		  ignoreType.sput(String.class, "")
					.sput(Integer.class, "").sput(int.class, "")
					.sput(Long.class,  "").sput(long.class, "")
					.sput(Double.class, "").sput(double.class, "")
					.sput(Float.class, "").sput(float.class, "")
					.sput(Boolean.class, "").sput(boolean.class, "")
					.sput(Byte.class, "").sput(byte.class, "")
					.sput(Character.class, "").sput(char.class, "")
					.sput(Short.class, "").sput(short.class, "");
	}
	/**
	 * set ignore type
	 * @param c ignore Class
	 */
	
	public void addIgnoreType(Class c){
		ignoreType.sput(c, "");
	}
	
	/**
	 * add rule (Generics type are necessary )
	 * @param x rule;
	 * @throws ReplaceObjectException rule add error
	 * @return this
	 */
	public ReplaceObject addRule(ReplaceObjectRule x) throws ReplaceObjectException {
		try {
			ParameterizedType parameterizedType = (ParameterizedType) x.getClass().getGenericInterfaces()[0];
			Type actualTypeArguments = parameterizedType.getActualTypeArguments()[0];
			if(rules.containsKey(actualTypeArguments.toString())) {
				throw new ReplaceObjectException(actualTypeArguments+" Already exists");
			}else {
				rules.put(actualTypeArguments.toString(), x);
			}
		} catch (Exception e) {
			if(e instanceof ReplaceObjectException) {
				throw (ReplaceObjectException)e;
			}else {
				throw new ReplaceObjectException("Please specify a generic type");
			}
			
		}
		return this;
	}
	/**
	 * replace Data
 	 * @param x Data
	 * @param rm mod
	 * @throws Exception  Exception
	 */
	public void replace(Object x,ReplaceMod rm,String... ignores) throws Exception {
		SimpleHashMap ignoreMap= new SimpleHashMap<String,String>().arrayToMap(ignores);
		Class xClass=x.getClass();
	//	this.addIgnoreType(xClass);
		Field[] fields=xClass.getDeclaredFields(); 
		
		for(Field field:fields) {
			field.setAccessible(true);  
	        Object val = field.get(x); 
	        if(val!=null) {
	        	if(!ignoreMap.containsKey(field.getName())) {
	        		if(rules.containsKey(val.getClass().toString())) {
		        		ReplaceObjectRule ror=rules.get(val.getClass().toString());
		        		Object newValue=ror.exec(val);
		        		field.set(x,newValue) ;   
		        	}else {
		        		if(rm==ReplaceMod.RECURSION&&!ignoreType.containsKey(val.getClass())&&xClass!=val.getClass()&&!Modifier.isFinal(field.getModifiers())) {
		        			replace(val,rm,ignores);
		        		}
		        	}
	        	}
	        }
	        field.setAccessible(false);  
		}	
	}
	
	
	public static interface ReplaceObjectRule<T>{
		public T exec(T t) throws Exception;
	}
	
	public enum ReplaceMod{
		RECURSION,MONOLAYER
	}
	/**
	 *  ReplaceObject Exception
	 * @author liuch
	 *
	 */
	public static class ReplaceObjectException extends Exception{

		public ReplaceObjectException(String string) {
			super(string);
		}
		
	}
}
