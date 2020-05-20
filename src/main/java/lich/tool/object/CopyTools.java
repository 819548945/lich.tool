package lich.tool.object;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * copy tools
 * @author liuch
 *
 */
public class CopyTools {
	
	private static Map<String,Map<String,ConversionInfo>> conversions=new HashMap<String, Map<String,ConversionInfo>>();
	/**
	 * field type change Rules
	 * @param cif ConversionInfo
	 */
	public static void addCustomRules(ConversionInfo cif) {
		Type[] types=((ParameterizedType)cif.getClass().getGenericSuperclass()).getActualTypeArguments();
		String tname=((Class)types[0]).getName();
		if(!conversions.containsKey(tname))conversions.put(tname, new HashMap<String,ConversionInfo>());
		Map<String,ConversionInfo> zMap=conversions.get(tname);
		zMap.put(((Class)types[1]).getName(), cif);
		if(types[1]==Byte.class||types[1]==Long.class||types[1]==Short.class||types[1]==Double.class||types[1]==Float.class||types[1]==Boolean.class) {
			String typeName=((Class)types[1]).getName();
			zMap.put(typeName.substring(typeName.lastIndexOf(".")).toLowerCase(), cif);	
		}else if(types[1]==Integer.class){
			zMap.put("int", cif);	
		}else if(types[1]==Character.class){
			zMap.put("char", cif);	
		}	
	}
	/**
	 * copy original field to target field for list
	 * @param original original
	 * @param target target
	 * @param modes mode
	 * @return true success false error
	 */
	 public static boolean copyFieldList(List original,List target,Class targetClass,Mode... modes) {
		 try {
			 for(int i=0;i<original.size();i++) {
				Object o= original.get(i);
				Object to=	targetClass.newInstance();
				copyField(o,to, modes);
				target.add(to);
			 }
			 return true;
		} catch (Exception e) {
			 return false;
		}	 
	 }
	/**
	 * copy original field to target field
	 * support Map<String,Object> to Map<String,Object>
	 * Map<String,Object> to Object
	 * @param original original
	 * @param target target
	 * @param modes mode
	 * @return true success false error
	 */
	 public static boolean copyField(Object original,Object target,Mode... modes)  {
		  try {
			   Class  oClass = original.getClass();
			   Class tClass= target.getClass();
			   if(oClass==HashMap.class&&tClass==HashMap.class) {
				   HashMap oc1= (HashMap)original ;
				   HashMap tc1=  (HashMap)target;
				   Iterator iterator= oc1.keySet().iterator();
				   boolean b=false;
					  for(int i=0;i<modes.length;i++) 
						  if(modes[i]==Mode.UNDERLINE_TO_HUMP_UP||modes[i]==Mode.UNDERLINE_TO_HUMP_LO||modes[i]==Mode.UNDERLINE_TO_HUMP_AUTO) {
							  b=true;
							  break;
						  }
					while (iterator.hasNext()) {
						if(b) {
							String o1 = (String) iterator.next();
							Object object = o1;
							String[] tl=o1.split("_");
							o1=tl[0].toLowerCase();
							for(int i=1;i<tl.length;i++) {
								o1+=tl[i].substring(0, 1).toUpperCase()+tl[i].substring(1).toLowerCase();
							}
							tc1.put(o1, oc1.get(object));
						}else {
						   Object object = (Object) iterator.next();
						   tc1.put(object, oc1.get(object));
						}
						
				  }
				}else if(oClass==HashMap.class&&tClass!=HashMap.class){
				   HashMap oc1= (HashMap)original ;
				   Field[] fields =tClass.getDeclaredFields();
				   for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					String mName=field.getName();
					if(modes.length==0)setValue(field,target,typeConversion(oc1.get(mName),field.getType()));	
					for(int z=0;z<modes.length;z++) {
						Mode mode=modes[z];
						if(mode==Mode.UNDERLINE_TO_HUMP_UP||mode==Mode.UNDERLINE_TO_HUMP_LO||mode==Mode.UNDERLINE_TO_HUMP_AUTO) {
							String[] ss = mName.split("(?<!^)(?=[A-Z])");
							 mName="";
							 if(mode==Mode.UNDERLINE_TO_HUMP_UP)for(int x = 0 ;x < ss.length; x ++) mName+="_"+ss[x].toUpperCase();
							 if(mode==Mode.UNDERLINE_TO_HUMP_LO) for(int x = 0 ;x < ss.length; x ++) mName+="_"+ss[x].toLowerCase();
							 if(mode==Mode.UNDERLINE_TO_HUMP_AUTO) {
								 String oc1Key= oc1.keySet().iterator().next().toString();
								 if(autoStringCheck(oc1Key,0)) 
									 for(int x = 0 ;x < ss.length; x ++) mName+="_"+ss[x].toUpperCase();
								 else 
									 for(int x = 0 ;x < ss.length; x ++) mName+="_"+ss[x].toLowerCase();	 
							 }
							 mName=mName.substring(1);
						}
						if(oc1.containsKey(mName))setValue(field,target,typeConversion(oc1.get(mName),field.getType()));	
					}
					
				}
			   }else {
				   throw new ConvertException("Conversion type not implemented");
			   }
			   return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}  
	   }
	 
	 	private static void setValue(Field field,Object target,Object v) throws IllegalArgumentException, IllegalAccessException {
	 		if(v!=null) {
	 			field.setAccessible(true);
	 			field.set(target,v);
	 		}	
	 	}
	   private static boolean autoStringCheck(String s,int index) throws ConvertException {
		   if((index+1)>s.length()) {
			  throw new ConvertException("Can't confirm case");
		   }
		   char c= s.charAt(index);
		   
		   if(c>64&&c<91) {
			   return true;
		   }else if(c>96&&c<123){
			   return false;
		   }else {
			  return autoStringCheck( s,index+1);
		   }
	   }
	   private static Object typeConversion(Object original,Class tClass) throws ClassCastException  {
		   	   if(original==null) {
		   		  return null;
		   	   }
			   Class  oClass = original.getClass();
			  
			   if(oClass==tClass) {
				   return original;
			   }else if(conversions.containsKey(oClass.getName())&&conversions.get(oClass.getName()).containsKey(tClass.getName())){
				  return conversions.get(oClass.getName()).get(tClass.getName()).exec(original);
			   }else if(tClass==String.class){
				   return original.toString();
			   }else if(oClass==BigInteger.class&&tClass==long.class){
				   return ((BigInteger)original).longValue();
			   }else if(Integer.class==oClass&&tClass.getName().equals("int")||
					    Boolean.class==oClass&&tClass.getName().equals("boolean")||
					    Character.class==oClass&&tClass.getName().equals("char")||
						Float.class==oClass&&tClass.getName().equals("float")||
						Double.class==oClass&&tClass.getName().equals("double")||
						Long.class==oClass&&tClass.getName().equals("long")||
						Byte.class==oClass&&tClass.getName().equals("byte")||
						Short.class==oClass&&tClass.getName().equals("short")||
						Integer.class==tClass&&oClass.getName().equals("int")||
					    Boolean.class==tClass&&oClass.getName().equals("boolean")||
					    Character.class==tClass&&oClass.getName().equals("char")||
						Float.class==tClass&&oClass.getName().equals("float")||
						Double.class==tClass&&oClass.getName().equals("double")||
						Long.class==tClass&&oClass.getName().equals("long")||
						Byte.class==tClass&&oClass.getName().equals("byte")||
						Short.class==tClass&&oClass.getName().equals("short")
				) return original;
			   else if(oClass==String.class&&(tClass==Integer.class||tClass.getName().equals("int")))return Integer.parseInt((String)original);
			   else if(oClass==String.class&&(tClass==Boolean.class||tClass.getName().equals("boolean")))return Boolean.parseBoolean((String)original);
			   else if(oClass==String.class&&(tClass==Character.class||tClass.getName().equals("char")))return ((String)original).charAt(0);
			   else if(oClass==String.class&&(tClass==Float.class||tClass.getName().equals("float")))return Float.parseFloat((String)original);
			   else if(oClass==String.class&&(tClass==Double.class||tClass.getName().equals("double")))return Double.parseDouble((String)original);
			   else if(oClass==String.class&&(tClass==Long.class||tClass.getName().equals("long")))return Long.parseLong((String)original);
			   else if(oClass==String.class&&(tClass==Byte.class||tClass.getName().equals("byte")))return Byte.parseByte((String)original);
			   else if(oClass==String.class&&(tClass==Short.class||tClass.getName().equals("short")))return Short.parseShort((String)original); 
			   else{ 
					return tClass.cast(original);
			   }	   
		}
	   /**
	    *  copy original field to target field Mode
	    * @author liuch
	    *
	    */
	   public static enum  Mode{
		   	/**
			 *UNDERLINE_TO_HUMP_UP
			 */
	   		UNDERLINE_TO_HUMP_UP,
	   		/**
			 *UNDERLINE_TO_HUMP_LO
			 */
	   		UNDERLINE_TO_HUMP_LO,
	   		/**
			 *NOTHING
			 */
	   		NOTHING,
	   		/**
			 *UNDERLINE_TO_HUMP_AUTO
			 */
	   		UNDERLINE_TO_HUMP_AUTO
	   }
}
