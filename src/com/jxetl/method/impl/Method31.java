package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

/**
 * @author wy
 *
 */
public class Method31 implements DealMethod{
	public final static int ID = 31;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * ��Դ���ַ�����ȡ����ָ���ı�ʾ�ַ�����ַ���
	 * �����Ե�һ���ַ�Ϊ1 ���Ҳ�����ָ���ַ�
	 * ??
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		int paramInt = 0;
		try{
			paramInt = Integer.parseInt(param);
		} catch (NumberFormatException e){
			throw new MethodDealException("�����������Ͳ���");
		}
		str = str.trim().substring(paramInt);
		return str;
	}

}
