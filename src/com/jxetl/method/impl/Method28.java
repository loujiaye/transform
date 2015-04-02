package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method28 implements DealMethod {
	public final static int ID = 28;
	
	@Override
	public int getID() {
		return 28;
	}

	/**
	 * 根据源列 a,b,c 判断,如果 a 值为 11,12,13,MO,mo(贵州需
	 * 求增加 a 值 01,代表主叫)中的一个,取 b 列的值,否则取c 列的值
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] tmpArr = { "11" , "12" ,"13" , "MO" , "mo"};
		String[] strArr = str.split(",");
		
		if(strArr.length != 3){
			throw new MethodDealException("源文件列个数不正确");
		}
		
		for(String tmp : tmpArr){
			if(strArr[0].trim().equals(tmp.trim())){
				return strArr[1];
			}
		}
		return  strArr[2];
	}

}
