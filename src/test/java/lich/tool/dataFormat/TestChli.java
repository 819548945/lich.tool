package lich.tool.dataFormat;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.Test;

public class TestChli {
	@Test
	public void test() throws UnsupportedEncodingException {
		String a="123";
		byte b=2;
		byte[] c= {1,2,3,4};
		byte[] enc=new Chli().addData(a.getBytes("utf-8")).addData(b).addData(c).encodeV1();
		Chli chli=new Chli().decode(enc);
		List<byte[]> l=	chli.getDatas();
		System.out.println(new String(l.get(0),"utf-8"));//"123"
		System.out.println(l.get(1)[0]);//2
		System.out.println(Hex.encodeHex(l.get(2)));//1,2,3,4
	}
}
