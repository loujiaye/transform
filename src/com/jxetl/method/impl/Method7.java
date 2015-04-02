package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method7 implements DealMethod {
	public final static int ID = 7;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 合并多列 目标列=源列 1 源列 2....源列 n
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		str = str.replaceAll(", " , "").replaceAll("\\s+", "");
		return str;
	}

}
