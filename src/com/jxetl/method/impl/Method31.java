package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

/**
 * @author wy
 *
 */
public class Method31 implements DealMethod{
	public final static int ID = 31;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 从源列字符串中取参数指定的标示字符后的字符串
	 * 这里以第一个字符为1 并且不包括指定字符
	 * ??
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		int paramInt = 0;
		try{
			paramInt = Integer.parseInt(param);
		} catch (NumberFormatException e){
			throw new MethodDealException("请输入数字型参数");
		}
		str = str.trim().substring(paramInt);
		return str;
	}

}
