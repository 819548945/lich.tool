package lich.tool.object;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TestSimple {
	@Test
	public void test () {
		List l=new SimpleArrayList<String>().sadd("1").sadd("2").sadd("3");
		Map m=new SimpleHashMap<String,String>().sput("1", "1").sput("2", "2").sput("3", "3");
	}
}
