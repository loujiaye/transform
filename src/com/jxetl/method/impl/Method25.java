package com.jxetl.method.impl;

import com.jxetl.manage.MapTable;
import com.jxetl.method.DealMethod;

public class Method25 implements DealMethod {
	public final static int ID = 25;
	
	@Override
	public int getID() {		
		return ID;
	}
	
	/**
	 * �ж�����ط��������һ�е�ֵ��ӳ�� prod_id ���ܹ�ӳ�䵽,��Ϊ�ط���־,
	 * �����ʣ�������Ϊ 0,�ж�Ϊ����ط�1��
	 * ����,��������ط� 0(����ר��)
	 */
	@Override
	public String invoke(String str, String defaultValue, String param  , String mapName) {
		String[] strArr = str.split(",");
		if(MapTable.maps.containsKey(mapName) && MapTable.maps.get(mapName).containsKey(strArr[0])){
			int sum = 0;
			try{
				for(int i = 1 ; i < strArr.length ; i++){
					sum += Integer.parseInt(strArr[i]);
				}
			}catch (NumberFormatException e){
				e.printStackTrace();
			}
		
			if(sum == 0){
				return "1";
			}
			
		}
	
		return "0";
	}

}
