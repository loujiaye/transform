package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method16 implements DealMethod {
	public static final int ID = 16;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * ȡ�ִ������ݲ���a,bĿ����1=Դ�еĵ�aλ��ʼȡbλ���Ӵ�
	 * ��� �Ǵӵ�һ���ַ�����1
	 * ���������׳��쳣
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName)
			throws MethodDealException {
		int a = 0;
		int b = 0;
		try{
			a = Integer.parseInt(param.split(",")[0]);
			b = Integer.parseInt(param.split(",")[1]);
		} catch(NumberFormatException e){
			throw new MethodDealException("����16�д���Ĳ���ӦΪ����");
		}
		
		//������ ������ʱ��ô����
		if(  (a+ b ) > str.length() + 1){
			

		} else{
			//??? aλ����ô��� ��ô�е�0λ
			str = str.substring(a -1,  a - 1 + b);
		}
		
		return str;

	}

}
