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
	 * ����ֵ���м�
	 * Ŀ����=Դ�� 1-Դ�� 2-....Դ�� n
	 * Լ��:��Ŀ���е�ֵ<0 ʱ,Ŀ����=0
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
			throw new MethodDealException("�������������Ͳ���");
		}
		
		if(result < 0 ){
			return "0";
		
		} else {
			return String.valueOf(result);
		}
		
	}

}
