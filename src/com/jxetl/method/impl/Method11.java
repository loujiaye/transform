package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method11 implements DealMethod {
	public final static int ID = 11;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 *  ���Դ��ֵ������ 1 ȡ���� 2 ��ֵ,����ȡ���� 3 ��ֵ
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] paramArr = param.split(",");
		
		if(paramArr.length != 3){
			throw new MethodDealException("������������ȷ");
		}
		
		try{
			if(Integer.parseInt(str) > Integer.parseInt(paramArr[0])){
				return paramArr[1];
			} else {
				return paramArr[2];
			}
		} catch(NumberFormatException e){
			throw new MethodDealException("���������ֲ���");
		}
	
	}

}
