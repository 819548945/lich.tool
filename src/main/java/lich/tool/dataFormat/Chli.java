package lich.tool.dataFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
/**
 * Simple data encapsulation format
 * @author liuch
 */
public  class Chli{
	 private List<byte[]> decodeData=new ArrayList<byte[]>();
	/**
	 * clear Chli data
	 * @return oneself
	 */
	 public Chli clear() {
		 decodeData.clear();
		 return this;
	 }
	 /**
	  * reLoad data
	  * @param data encData
	  * @return oneself
	  */
	 public Chli reLoadData(List<byte[]> data) {
		 if(data==null) {
			 throw new NullPointerException("data is null");
		 }
		 decodeData=data;
		 return this;
	 }
	 /**
	  * add data
	  * @param b encData
	  * @return oneself
	  */
	 public Chli addData(byte b){
		 byte [] b1= {b};
		 decodeData.add(b1);
		 return this;
	 }
	 /**
	  * add data
	  * @param b encData
	  * @return oneself
	  */
	 public Chli addData(byte[] b){
		 decodeData.add(b);
		 return this;
	 }
	 /**
	  * add data by index
	  * @param index index
	  * @param b encData
	  * @return oneself
	  */
	 public Chli addData(int index,byte[] b){
		 decodeData.add(index,b);
		 return this;
	 }
	 /**
	  * add datas
	  * @param c encDatas
	  * @return oneself
	  */
	 public Chli addDatas(Collection<byte[]> c){
		 decodeData.addAll(c);
		 return this;
	 }
	 /**
	  * Get pending data
	  * @return pending datas
	  */
	 public List<byte[]> getDatas(){
		 return decodeData;
	 }
	 /**
	  * to enc Version 1 data
	  * @return enc data
	  */
	 public byte[] encodeV1() {
		 int size=3;
		 for(int i=0;i<decodeData.size();i++) {
			 size=size+decodeData.get(i).length+1;
		 }
		 byte [] b=new byte[size];
		 b[0]=(byte)0x0A;
		 b[1]=(byte)0xF9;
		 b[2]=(byte)0x01;
		 for(int i=0,j=3;i<decodeData.size();i++) {
			 b[j]=(byte)decodeData.get(i).length;
			 System.arraycopy(decodeData.get(i), 0, b, j+1, decodeData.get(i).length);
			 j=j+1+decodeData.get(i).length;
		 }
		 
		 return b;
	 }
	 /**
	  * decode enc data
	  * @param encodeData enc data
	  * @return oneself
	  */
	 public Chli decode(byte[] encodeData) { 
		 if(!((encodeData[0]==(byte)0x0A)&&(encodeData[1]==(byte)0xF9))) {
			 throw new NumberFormatException("format not chli");
		 }
		 if(encodeData[2]==(byte)0x01){
			 decodeData.clear();
			 for(int i=3;i<encodeData.length;i++) {
				 int x=encodeData[i]<0?encodeData[i]+256:encodeData[i];
				 byte [] b=new byte[x];
				 System.arraycopy(encodeData, i+1, b, 0,x);
				 decodeData.add(b);
				 i=i+x;
				 
			 }
		 }else {
			 throw new NumberFormatException("version is not definition");
		 }
		 return this;
	 }
	 
 }
