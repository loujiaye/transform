package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method15 implements DealMethod {
	public final static int ID = 15;

	@Override
	public int getID() {
		return ID;
	}

	/**
	 * Ŀ����=���� city_id,��-c �����ֵ
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		if(Argument.cityId  == null){
			throw new MethodDealException("ִ����������� -cû��ָ��");
		}
		return Argument.cityId ;
	}

}
