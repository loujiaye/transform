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
	 * ����Դ�� a �жϰ�6�����b�С�����aΪ��������bΪͨ��ʱ��,�� 
	 * �޲���ʱ:��� a ��ֵΪ0��1Ŀ����=(Դ��b+5) /6,
	 * ���Դ��ֵΪ����Ŀ����=(Դ��b-5)/6 
	 * ����ֻ��һ������ x:��� a ��ֵ!=����xĿ����=(Դ��b+5)/6,
	 * ���Դ��ֵΪ����Ŀ����=(Դ��b-5)/6 Լ��:Դ��ʱ��ֵ���Ϊ 999999999
	 * 
	 * 
	 * a Ϊ�� �Ҳ����� 0 �� 1 ��ô�죿������������������ b �Ƿ�һ��Ϊint
	 * 
	 * 
	 *   ע�⣺���ݱȶ�������� ��ֱ�ӷ���0   
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
			throw new MethodDealException("�����������Ͳ���");
		}

		if (b > 999999999) {
			System.out.println("ͨ��ʱ������������");
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
