package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *  
 */
public class Method13 implements DealMethod {
	private static final int ID = 13;
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	/**
	 * Ŀ����=����date_stamp,��-p�����ֵ
	 * @throws MethodDealException 
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException {
		if(Argument.dateStamp == null){
			throw new MethodDealException("ִ�����������dateStampû���ƶ�");
		}
		return Argument.dateStamp;
	}

}
