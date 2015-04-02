package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method23 implements DealMethod {
	public final static int ID = 23;
	
	@Override
	public int getID() {
		return 23;
	}
	
	/**
	 * 	����������ָ����������: a,b�����Դ�е��ڵ�һ������ a,���滻Ϊ�ڶ������� b ����Ŀ����
	 *	
	 *	˵��:��Դ��ֵ�п�ֵ��Ҫ�滻��һ���̶�ֵʱ����ʹ�� 23(NULL,value)�� 23(null,value) 
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName)
			throws MethodDealException {
		String[] arr = param.split(",");
		String a = "";
		String b = "";
		if(arr.length == 2){
			a = arr[0];
			b = arr[1];
		} else {
			throw new MethodDealException("methdo23 �����ĸ�������");
		}
		

		//˵��:��Դ��ֵ�п�ֵ��Ҫ�滻��һ���̶�ֵʱ����ʹ�� 23(NULL,value)�� 23(null,value)
		//���Դ�е��ڵ�һ������ a,���滻Ϊ�ڶ������� b ����Ŀ����
		if(a.equalsIgnoreCase("null")){
			if(str.equals("")){
				return b;
			}
		} else if(str.equals(a)){
			return b;
		}
				
		return str;
	}

}
