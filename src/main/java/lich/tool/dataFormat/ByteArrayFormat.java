package lich.tool.dataFormat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * Byte arrays are converted to other data types
 * 
 * @author liuch
 */
public class ByteArrayFormat {
	/**
	 * Long to byte array
	 * @param l long data
	 * @return byte array data
	 */
	public static byte[] LongToBytes(long l) {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		try {
			 baos = new ByteArrayOutputStream();
			 dos =new DataOutputStream(baos);
			 dos.writeLong(l);
			 return baos.toByteArray();
		} catch (Exception e) {
			return null;
		}finally {
			try {
				baos.close();
				dos.close();
			} catch (IOException e) {
			}
			
		}
	}
	/**
	 * byte array to Long
	 * @param b byte array data
	 * @return long data
	 */
	public static Long BytesToLong(byte[] b) {
		ByteArrayInputStream baos = null;
		DataInputStream dos = null;
		try {
			 baos = new ByteArrayInputStream(b);
			 dos =new DataInputStream(baos);
			 return dos.readLong();
		} catch (Exception e) {
			return null;
		}finally {
			try {
				baos.close();
				dos.close();
			} catch (IOException e) {
			}
			
		}
	}
}
