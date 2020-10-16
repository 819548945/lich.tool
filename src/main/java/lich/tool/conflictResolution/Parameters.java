package lich.tool.conflictResolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Parameters of classproxy exec method
 * @author liuch
 *
 */
public class Parameters {
	private List<Class> clss=new ArrayList<Class>();
	private List<Object> objs=new ArrayList<Object>();
	public Parameters() {
		
	}
	/**
	 * @param objs parameters
	 */
	public Parameters(Object... objs) {
		for(Object obj:objs) {
			this.addParameter(obj);
		}
		
	}
	
	/**
	 * add Paramete
	 * @param obj parameter
	 * @return  Oneself
	 */
	public Parameters addParameter(Object obj) {
		clss.add(obj.getClass());
		objs.add(obj);
		return this;
	}
	/**
	 * add Paramete
	 * @param cls parameter Class 
	 * @param obj parameter
	 * @return Oneself
	 */
	public Parameters addParameter(Class cls,Object obj) {
		clss.add(cls);
		objs.add(obj);
		return this;
	}
	/**
	 *  add Paramete
	 * @param objs parametes
	 * @return Oneself
	 */
	public Parameters addParametes(Object... objs) {
		for(Object obj:objs) {
			this.addParameter(obj);
		}
		return this;
	}
	Class[] getClssArray(){
		return clss.toArray(new Class[clss.size()]);
	}
	Object[] getObjArray(){
		return objs.toArray(new Object[objs.size()]);
	}
}
