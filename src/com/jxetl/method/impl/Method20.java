package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method20 implements DealMethod {
	public final static int ID = 20;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 *  ���Դ��ֵ�ڶ������ֵ���򷵻� 1,���򷵻� 0
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] paramArr = param.split(",");
		
		for(String tmp : paramArr){
			if(str.trim().equals(tmp.trim())){
				return "1";
			} 
		}
		
		return "0";
	}

}
