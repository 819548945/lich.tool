package tool.test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lich.tool.object.ConversionInfo;
import lich.tool.object.CopyTools;

public class Test {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		HashMap<String,String> m=new HashMap<String,String>();
		HashMap<String,String> m1=new HashMap<String,String>();
		T1 mt=new T1();
		List<HashMap> l=new ArrayList<HashMap>();
		/*m.put("testAssAB", "testAssAB");
		m.put("testBssAB", 1);*/
		m.put("TEST_ASS_A_B", "testAssAB");
		m.put("TEST_BSS_A_B", 1+"");
		
		l.add(m);
	   
	    
		List l1=new ArrayList();
		
		
		
        ConversionInfo cif=  new ConversionInfo<String, Integer>() {

			@Override
			public Integer exec(String t) {
				System.out.println("1234");
				return 1;
			}
		};
		
		//Type type=((ParameterizedType)cif.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
       // System.out.println(type.getTypeName());
		CopyTools.addCustomRules(cif);
		CopyTools.copyField(m, mt,CopyTools.Mode.UNDERLINE_TO_HUMP_AUTO);
		System.out.println(m);
		System.out.println(mt);
	//	System.out.println(tt.getTestBssAB());
		//System.out.println(l1);
	}

}
