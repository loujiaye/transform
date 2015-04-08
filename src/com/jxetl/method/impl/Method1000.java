package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

/**
 * 
 * @author wy
 * �Զ��庯����ʵ��999��������չ
 * 
 */
public class Method1000 implements DealMethod {
	
	public final static int ID = 1000;
	@Override
	public int getID() {		
		return ID;
	}

	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		//System.out.println("in 1000 method" + str);
		if(str.equals("")){
			return "0";
		} else {
			return str;
		}
	}

}
