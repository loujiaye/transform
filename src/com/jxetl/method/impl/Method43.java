package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method43 implements DealMethod {
	public final static int ID = 43;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * ����ֵ��� Ŀ����=Դ�� 1*Դ�� 2*....Դ�� n
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr  = str.split(",");
		double result = 0;
		try{
			result = Double.parseDouble(strArr[0]);
			for(int i = 1 ;i < strArr.length ;i++){
				result  *= Double.parseDouble(strArr[i]);
			}
		} catch (NumberFormatException e){
			throw new MethodDealException("�������������Ͳ���");
		}
		
		
		return String.valueOf(result);
	
	}

}
