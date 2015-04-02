package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *  
 */
public class Method10 implements DealMethod {
	public final static int ID = 10; 
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 根据源列 a,b,c 判断,如果 a 值=参数取 b 的值,否则取 c 的值 
	 * 当参数不是3个的时候报错
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr = str.split(",");
		
		if(strArr.length != 3){
			throw new MethodDealException("源文件列个数不正确");
		}
		
		if(strArr[0].trim().equals(param.trim())){
			return strArr[1];
		} else {
			return strArr[2];
		}
		
	}

}
