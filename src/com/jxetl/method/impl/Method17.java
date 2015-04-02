package com.jxetl.method.impl;

import java.text.DecimalFormat;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method17 implements DealMethod {

	public static final int ID = 17;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * Ŀ����=Դ��/����
	 * ��ʽ���ַ��� ������6λС��
	 */
	@Override
	public String invoke(String str, String defaultValue, String param, String mapName)
			throws MethodDealException {

		double src = 0;
		double par = 0;
		try{
			src = Double.parseDouble(str);
			par = Double.parseDouble(param);
			if(par == 0){
				throw new MethodDealException("method17 �г�������Ϊ0");
			}
			src = src /par;
			
			
		} catch (NumberFormatException e){
			return "0.000000";
		}
		
		return new DecimalFormat("0.000000").format(src);
	}

}
