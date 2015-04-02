package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

/**
 * 
 * @author wy
 * 
 */
public class Method19 implements DealMethod {
	public final static int ID = 19;

	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 根据源列 a 判断按6秒计算b列。其中a为费用类型b为通话时长,。 
	 * 无参数时:如果 a 的值为0或1目标列=(源列b+5) /6,
	 * 如果源列值为负则目标列=(源列b-5)/6 
	 * 有且只有一个参数 x:如果 a 的值!=参数x目标列=(源列b+5)/6,
	 * 如果源列值为负则目标列=(源列b-5)/6 约束:源列时长值最大为 999999999
	 * 
	 * 
	 * a 为正 且不等于 0 ， 1 怎么办？？？？？？？？？？ b 是否一定为int
	 * 
	 * 
	 *   注意：根据比对亚联结果 现直接返回0   
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		
		String[] strArr = str.split(",");
		if (param.equalsIgnoreCase("") && strArr[0].trim().equals(param.trim())) {
				return "0";
		}

		if(param.equalsIgnoreCase("")  && ( strArr[0].trim().equals("0") || strArr[0].trim().equals("1"))){
			return "0";
		}
		
		int b = 0;
		try {
			b = Integer.parseInt(strArr[1]);
		} catch (NumberFormatException e) {
			throw new MethodDealException("请输入数字型参数");
		}

		if (b > 999999999) {
			System.out.println("通话时长超过了限制");
			return "";
		}
		
		if(b >= 0) {
			b = (b + 5) / 6;
		} else {
			b = (b - 5) / 6;
		}

		return String.valueOf(b);
	}

}
