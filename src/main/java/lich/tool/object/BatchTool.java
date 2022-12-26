package lich.tool.object;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BatchTool<T,A> {
	
	private BatchRule br;
	public BatchTool(BatchRule br){
		this.br=br;
	}
	public List<A> conversionToList(Collection<T> c) throws Exception {
		List<A> l=new ArrayList<A>();
		Iterator<T> i= c.iterator();
		while (i.hasNext()) {
			l.add((A) br.exec(i.next()));
		}
		return l;
	}

	
	public static interface BatchRule<T,A>{
		public A exec(T t) throws Exception;
	}
}
