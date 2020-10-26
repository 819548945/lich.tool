package lich.tool.dynamicPassword;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lich.tool.dataFormat.Hex;

public  class Password {
	private static MessageDigest digest;
	private int length;
	private Password(int length) {
			if(digest==null) {
				  try {
					 digest= MessageDigest.getInstance("SHA-256");
				} catch (NoSuchAlgorithmException e) {	
					e.printStackTrace();
			}
			
		}
		
		this.length=length;
	}
	public static Password newInstance(int length) {
		return new Password(length);	
	}
	public String getDynamicPassword(String ori) throws UnsupportedEncodingException {
		long time=System.currentTimeMillis()/1000/60;
			byte[] b=(ori+""+time).getBytes("utf-8");
			byte[] sha256=digest.digest(b);
			byte[] re=	new byte[length];
			for(int i=0,j=0;i<sha256.length;i++,j++) {
				re[j]=(byte) (i%2==0?re[j]|sha256[i]:re[j]&sha256[i]);
				if(j==length-1)j=0;
			}
			String s="";
			for(int i=0;i<length;i++) {
				if(re[i]<0||re[i]>9) {
					re[i]=(byte) (re[i]&0x07);
				}
				s+=re[i];
			}
			return s;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		/* digest= MessageDigest.getInstance("SHA-256");
		 byte[] sha256=digest.digest("ceshi".getBytes("utf-8"));	
		 for (int i = 0; i < sha256.length; i++) {
			System.out.println(sha256[i]+"\n");
		}
		 System.out.println(Hex.encodeHex(sha256));*/
		 Password pw= Password.newInstance(8);
		System.out.println(pw.getDynamicPassword("151427256809"));	;
	}
	
	
}
