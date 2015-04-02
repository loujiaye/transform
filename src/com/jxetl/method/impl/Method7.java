package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method7 implements DealMethod {
	public final static int ID = 7;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * �ϲ����� Ŀ����=Դ�� 1 Դ�� 2....Դ�� n
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		str = str.replaceAll(", " , "").replaceAll("\\s+", "");
		return str;
	}

}
