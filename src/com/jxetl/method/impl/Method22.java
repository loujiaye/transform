package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method22 implements DealMethod {
	public final static int ID = 22;
	
	@Override
	public int getID() {
		return ID;
	}

	/** 
	 * 在源列值基础上加参数指定的前缀
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		str = param.trim() + str.trim();
		return str;
	}

}
