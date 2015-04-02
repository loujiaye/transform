package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method16 implements DealMethod {
	public static final int ID = 16;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 取字串，根据参数a,b目标列1=源列的第a位开始取b位的子串
	 * 设计 是从第一个字符算作1
	 * 当超长是抛出异常
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName)
			throws MethodDealException {
		int a = 0;
		int b = 0;
		try{
			a = Integer.parseInt(param.split(",")[0]);
			b = Integer.parseInt(param.split(",")[1]);
		} catch(NumberFormatException e){
			throw new MethodDealException("方法16中传入的参数应为数字");
		}
		
		//？？？ 当超过时怎么处理
		if(  (a+ b ) > str.length() + 1){
			

		} else{
			//??? a位是怎么算的 有么有第0位
			str = str.substring(a -1,  a - 1 + b);
		}
		
		return str;

	}

}
