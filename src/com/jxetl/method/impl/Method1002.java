package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method1002 implements DealMethod {
	public static final int ID = 1002;
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	/**
	 * {if  (col4='3') or (col4='4') then 700001 else 0 }
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		if(str.equals("3") || str.equals("4")){
			return "700001";
		} else {
			return "0";
		}
	}

}
