package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method1005 implements DealMethod {
	public static final int ID = 1005;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * {{if (col3='2') or (col3='3') then '700001' else '500040' }
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		if(str.equals("2") || str.equals("3")){
			return "700001";
		} else {
			return "500040";
		}
	}

}
