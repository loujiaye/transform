package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method1006 implements DealMethod {
	public static final int ID = 1002;
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	/**
	 * {if  (col3='2') or (col3='3') then 700001 else 0 }
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		if(str.equals("2") || str.equals("3")){
			return "700001";
		} else {
			return "0";
		}
	}

}
