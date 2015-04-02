package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method4 implements DealMethod {
	public static final int ID = 4;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * ����0: ���Ŀ����=Դ��1+Դ��2+��+Դ��n >0,����1�����򷵻�0��
	 * ����1: Ŀ����=Դ��1+Դ��2+��+Դ��n
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) 
			throws MethodDealException {
		String[] tmp = str.split(",");
		double result = 0;

		try{
			for(int i = 0 ;i < tmp.length ;i++){
				result += Double.parseDouble(tmp[i]); 
			}
		} catch (NumberFormatException e){
			throw new MethodDealException("method4 �д���Ĳ���ӦΪ����");
		}
		
			
		if(param.equals("0")){
			if(result > 0){
				return String.valueOf(1);
			} else {
				return String.valueOf(0);
			}
		} else if(param.equals("1")){
			return String.valueOf(result);
		} else {
			throw new MethodDealException("method4 ��������");
		}
		
	}

}
