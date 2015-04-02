package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method5 implements DealMethod {
	public static final int ID = 5;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 根据呼叫开始时间计算呼叫时段（不含微秒），如果分钟+秒=0, 目标列=源列小时数除24取余,否则目标列=源列小时数除24取余+1（和方法3有区别）
	 * 
	 * 现在程序仅限 前面用 6（3）规整后的字符串
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName)
			throws MethodDealException {
		String[] strArr = str.split("\\s+")[1].split(":");
		int hour = 0;
		try{
			hour = Integer.parseInt(strArr[0]);
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
		
		if(!strArr[1].equals("00") || !strArr[2].equals("00")){
			hour++;
		}
		
		return String.valueOf(hour);
		
	}

}
