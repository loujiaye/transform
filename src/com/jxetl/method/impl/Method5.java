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
	 * ���ݺ��п�ʼʱ��������ʱ�Σ�����΢�룩���������+��=0, Ŀ����=Դ��Сʱ����24ȡ��,����Ŀ����=Դ��Сʱ����24ȡ��+1���ͷ���3������
	 * 
	 * ���ڳ������ ǰ���� 6��3����������ַ���
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
