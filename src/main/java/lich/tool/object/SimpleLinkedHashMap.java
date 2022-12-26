package lich.tool.object;

import java.util.LinkedHashMap;

public class SimpleLinkedHashMap<V, K>  extends LinkedHashMap<K, V>{
	/*
	 *sput value pre-treatment
	 */
	public PreExec vpe;
	/**
	 * simple put Return to yourself
	 * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
	 * @return this
	 */
	public SimpleLinkedHashMap<V, K> sput(K key, V value) {
		if(vpe!=null)value=(V)vpe.exec(value);
		super.put(key, value);
		return this;
	}
	/**
	 * set Value pre-treatment
	 * @param p  pre-treatment obj
	 * @return this
	 */
	public SimpleLinkedHashMap<V, K> setValuePreExec(PreExec p) {
		vpe=p;
		return this;
	}
	/**
	 * arrat to Map key
	 * @param a array
	 * @return this
	 */
	public  SimpleLinkedHashMap<V, K> arrayToMap(K [] a) {
		for(K a1:a)super.put(a1, null);
		return this;
	}
}
