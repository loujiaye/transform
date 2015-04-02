package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method28 implements DealMethod {
	public final static int ID = 28;
	
	@Override
	public int getID() {
		return 28;
	}

	/**
	 * ����Դ�� a,b,c �ж�,��� a ֵΪ 11,12,13,MO,mo(������
	 * ������ a ֵ 01,��������)�е�һ��,ȡ b �е�ֵ,����ȡc �е�ֵ
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] tmpArr = { "11" , "12" ,"13" , "MO" , "mo"};
		String[] strArr = str.split(",");
		
		if(strArr.length != 3){
			throw new MethodDealException("Դ�ļ��и�������ȷ");
		}
		
		for(String tmp : tmpArr){
			if(strArr[0].trim().equals(tmp.trim())){
				return strArr[1];
			}
		}
		return  strArr[2];
	}

}
