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
	 * ���������㷨:����ָ������С����β������Ϊ0ʱΪ���� С�����һλ��ֵ��������,��0.4 ȡ0;0.5 ȡ1���������ֵΪ��ֵ,
	 * 
	 * Ϊ��ֵ����������
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
			throw new MethodDealException("���������ֲ���");
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
