package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author ljy
 * @description ����12 
 *     Ŀ����=����op_time,��-t�����ֵ
 */
public class Method12 implements DealMethod {
	public final static int ID = 12;

	@Override
	public int getID() {

		return ID;
	}

	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException{
		if(Argument.opTime == null){
			throw new MethodDealException("ִ����������� -tû��ָ��");
		}
		return Argument.opTime;
	}

}
