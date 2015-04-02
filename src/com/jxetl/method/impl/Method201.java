package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;


/**
 * 
 * @author wy
 *  因方法列表中没有这个方法 所以假做  以后后来修改
 */
public class Method201 implements DealMethod {

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		
		String[] strArr = str.split(",");
		String b = "";
		
		//暂时处理
		if(strArr.length == 2){
			b = strArr[1];
		} else {
			b = "";
		}
		
		return b + new Method2().invoke(str, defaultValue, param, mapName);
		
	}

}
