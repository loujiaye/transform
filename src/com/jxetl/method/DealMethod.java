package com.jxetl.method;

import com.jxetl.exception.MethodDealException;

/**
 * 
 * @author ljy
 * �������ӿ� ��ȡ������ID��ִ�з��������ش������ַ���
 */
public interface DealMethod {
	/**
	 * 
	 * @return ����������ϢID��
	 */
	int getID();
	/**
	 * 
	 * @param str	Ҫ������ַ���
	 * @param defaultValue Ĭ��ֵ
	 * @param param	�����Ĳ���
	 * @return �������ַ���
	 * @throws MethodDealException 
	 */
	String invoke(String str ,String defaultValue ,  String param,String mapName) throws MethodDealException;
}
