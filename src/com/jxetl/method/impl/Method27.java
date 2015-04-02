package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method27 implements DealMethod {
	public final static int ID = 27;
	
	@Override
	public int getID() {
		return ID;
	}

	/** 
	 * ��Դ��ֵ�����ϼӲ���ָ���ĺ�׺
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		str = str.trim() + param.trim() ;
		return str;
	}

}
