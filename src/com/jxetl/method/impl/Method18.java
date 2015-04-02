package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method18 implements DealMethod {
	public final static int ID = 18;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 多列值逐列减
	 * 目标列=源列 1-源列 2-....源列 n
	 * 约束:当目标列的值<0 时,目标列=0
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr  = str.split(",");
		double result = 0;
		try{
			result = Double.parseDouble(strArr[0]);
			for(int i = 1 ;i < strArr.length ;i++){
				result  -= Double.parseDouble(strArr[i]);
			}
		} catch (NumberFormatException e){
			throw new MethodDealException("请输入数字类型参数");
		}
		
		if(result < 0 ){
			return "0";
		
		} else {
			return String.valueOf(result);
		}
		
	}

}
