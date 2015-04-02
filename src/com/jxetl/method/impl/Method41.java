package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method41 implements DealMethod {
	public final static int ID = 41;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 将源文件的两列分别为(yyyymmdd 和 hhmmss)转换为目标列(yyyy-mm-dd hh:mm:ss)
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr = str.trim().split(",");
		
		
		if(strArr.length != 2 || strArr[0].trim().length() != 8 || strArr[1].trim().length() != 6){
			throw new MethodDealException("参数个数或者格式错误");
		}
		
		strArr[0]  = strArr[0].trim();
		strArr[1]  = strArr[1].trim();
		
		str = strArr[0].substring(0, 4) + "-" + strArr[0].substring(4, 6) + "-" 
				+ strArr[0].substring(6) + " " + strArr[1].substring(0, 2) + ":" 
				+ strArr[1].substring(2, 4) + ":"  + strArr[1].substring(4) ;
		return str;
	}

}
