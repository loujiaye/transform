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
	 *  ����ʱ��,Ŀ����=(Դ��+59)/60,���Դ��ֵΪ����Ŀ����=(Դ��-59)/60
	 *  
	 *  ����ֵΪint ����double  �����double ����ô��ʽ��������������
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		int strInt = 0;
		/*System.out.println("in method1" + str);*/
		try{
			strInt = Integer.parseInt(str);
		} catch (NumberFormatException e){
			throw new MethodDealException("���������ֲ���");
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
