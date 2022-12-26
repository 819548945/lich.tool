package lich.tool.object;

import java.util.HashSet;

/**
 * 
 * @author lich
 *
 * @param <V>
 */
public class SimpleHashSet < V> extends HashSet<V>{
	public SimpleHashSet sadd(V value) {
		super.add(value);
		return this;
	}
}
