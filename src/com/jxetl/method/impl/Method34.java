package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method34 implements DealMethod {
	public final static int ID = 34;

	@Override
	public int getID() {

		return ID;
	}

	/**
	 * Ŀ����=���� src_file,��-s �����ֵ
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException{
		if(Argument.sourceFile  == null){
			throw new MethodDealException("ִ����������� -sû��ָ��");
		}
		return Argument.sourceFile ;
	}

}
