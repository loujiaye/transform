package com.jxetl.method.impl;

import java.text.DecimalFormat;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

/**
 * 
 * @author wy
 * 
 */
public class Method46 implements DealMethod {
	public final static int ID = 46;

	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 四舍五入算法:参数指定最终小数点尾数。当为0时为根据 小数点后一位的值四舍五入,如0.4 取0;0.5 取1。如果输入值为负值,
	 * 
	 * 为负值怎样？？？
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		double strDouble = 0;
		int paramInt = 0;
		String formatStr = "0";

		try {
			strDouble = Double.parseDouble(str);
			paramInt = Integer.parseInt(param);
		} catch (NumberFormatException e) {
			throw new MethodDealException("请输入数字参数");
		}

		if (strDouble >= 0) {
			if (paramInt > 0) {
				formatStr += ".";
				for (int i = 0; i < paramInt; i++) {
					formatStr += "0";

				}
			}
		} else {

		}
		return new DecimalFormat(formatStr).format(strDouble);
	}

}
