package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method37 implements DealMethod {
	public final static int ID = 37;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * ����ָ����Դ�� a,b ��Դ�� b ��ѡ�����ָ���� n ��λ�õ��ַ�����´��ӵ�Դ�� a ����
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr = str.trim().split(",");
		char[] charArr = strArr[1].trim().toCharArray();
		String[] paramArr = param.split(",");

		
		if(strArr.length != 2){
			throw new MethodDealException("Դ�и�������");
		}
		
		try{
			int tmp ;
			strArr[0] = strArr[0].trim();
			for(int i = 0 ;i < paramArr.length ; i++){
				tmp = Integer.parseInt(paramArr[i]);
				if(tmp > (charArr.length -1)){
					continue;
				}
				strArr[0] += charArr[tmp - 1];
			}
		} catch (NumberFormatException e){
			throw new MethodDealException("���������ֲ���");
		}
		
		return strArr[0];
	}

}
