package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method11 implements DealMethod {
	public final static int ID = 11;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 *  如果源列值〉参数 1 取参数 2 的值,否则取参数 3 的值
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] paramArr = param.split(",");
		
		if(paramArr.length != 3){
			throw new MethodDealException("参数个数不正确");
		}
		
		try{
			if(Integer.parseInt(str) > Integer.parseInt(paramArr[0])){
				return paramArr[1];
			} else {
				return paramArr[2];
			}
		} catch(NumberFormatException e){
			throw new MethodDealException("请输入数字参数");
		}
	
	}

}
