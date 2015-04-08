package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method1 implements DealMethod {
	public final static int ID = 1;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 *  计算时长,目标列=(源列+59)/60,如果源列值为负则目标列=(源列-59)/60
	 *  
	 *  计算值为int 还是double  如果是double ，怎么格式化？？？？？？
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		int strInt = 0;
		/*System.out.println("in method1" + str);*/
		try{
			strInt = Integer.parseInt(str);
		} catch (NumberFormatException e){
			throw new MethodDealException("请输入数字参数");
		}
		
		if(strInt >= 0){
			strInt = (strInt + 59)/60;
		} else {
			strInt = (strInt - 59)/60;
		}
	/*	System.out.println(strInt);*/
		return String.valueOf(strInt);
	}

}
