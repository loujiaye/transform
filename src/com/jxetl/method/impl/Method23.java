package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method23 implements DealMethod {
	public final static int ID = 23;
	
	@Override
	public int getID() {
		return 23;
	}
	
	/**
	 * 	本方法必须指定两个参数: a,b。如果源列等于第一个参数 a,则替换为第二个参数 b 赋给目标列
	 *	
	 *	说明:当源列值有空值需要替换成一个固定值时可以使用 23(NULL,value)或 23(null,value) 
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName)
			throws MethodDealException {
		String[] arr = param.split(",");
		String a = "";
		String b = "";
		if(arr.length == 2){
			a = arr[0];
			b = arr[1];
		} else {
			throw new MethodDealException("methdo23 参数的个数不对");
		}
		

		//说明:当源列值有空值需要替换成一个固定值时可以使用 23(NULL,value)或 23(null,value)
		//如果源列等于第一个参数 a,则替换为第二个参数 b 赋给目标列
		if(a.equalsIgnoreCase("null")){
			if(str.equals("")){
				return b;
			}
		} else if(str.equals(a)){
			return b;
		}
				
		return str;
	}

}
