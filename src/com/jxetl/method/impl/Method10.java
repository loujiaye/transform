package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *  
 */
public class Method10 implements DealMethod {
	public final static int ID = 10; 
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * ����Դ�� a,b,c �ж�,��� a ֵ=����ȡ b ��ֵ,����ȡ c ��ֵ 
	 * ����������3����ʱ�򱨴�
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr = str.split(",");
		
		if(strArr.length != 3){
			throw new MethodDealException("Դ�ļ��и�������ȷ");
		}
		
		if(strArr[0].trim().equals(param.trim())){
			return strArr[1];
		} else {
			return strArr[2];
		}
		
	}

}
