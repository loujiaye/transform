package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;


/**
 * 
 * @author wy
 *  �򷽷��б���û��������� ���Լ���  �Ժ�����޸�
 */
public class Method201 implements DealMethod {

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		
		String[] strArr = str.split(",");
		String b = "";
		
		//��ʱ����
		if(strArr.length == 2){
			b = strArr[1];
		} else {
			b = "";
		}
		
		return b + new Method2().invoke(str, defaultValue, param, mapName);
		
	}

}
