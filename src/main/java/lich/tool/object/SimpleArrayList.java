package lich.tool.object;

import java.util.ArrayList;
import java.util.Collection;
/**
 * simple HashMap
 * @author liuch
 * @param <E> Class
 * @see ArrayList
 */
public class SimpleArrayList<E> extends ArrayList<E> {
	/**
	 * simple add Return to yourself
	 * @param e element to be appended to this list
	 * @return this
	 */
	public SimpleArrayList sadd(E e) {
		super.add(e);
		return this;
	}
	
	

}
