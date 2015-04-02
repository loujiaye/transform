package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

/**
 * 
 * @author wy
 * ÌØÊâµÄº¯Êı  
 *
 */
public class Method999  {
	public final static int ID = 999;
	
	
	public int getID() {		
		return ID;
	}

	
	public String invoke(String str, String[] src , String[] dest) {
		if(str.trim().equals("{if (col45='') then 0 else col45 }")){
			if(src[44].equals("")){
				return "0";
			} else {
				return src[44];
			}
			
		}
		
		if(str.trim().equals("{substring(col52,1,8)}")){
			if(src[51].length() >  8){
				return src[51].substring(0 , 8);
			} else {
				return src[51];
			}
			
		}
		
		return null;

	}

}
