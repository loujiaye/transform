package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method1003 implements DealMethod {
	public static final int ID = 1003;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * {if int(col29)=104 then 201901 else if int(col29)=105 then 201911 
	 * else if int(col29)=304 then 201906 else 201901}
	 * 
	 * if int(col29)=104 then 201901 else if int(col29)=105 then 201911 else if int(col29)=304 then 201906 else 201901
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		if(str.equals("104")){
			return "201901";
		} else if (str.equals("105")){
			return "201911";
		} else if (str.equals("304")){
			return "201906";
		} else {
			return "201901";
		}
	}

}
