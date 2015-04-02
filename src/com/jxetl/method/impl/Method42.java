package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method42 implements DealMethod {
	public final static int ID = 42;
	@Override
	public int getID() {
		
		return ID;
	}

	/**
	 * 
	 * ����ֵ�ʱ��,���ȸ��ݺ��п�ʼʱ��������ʱ��(����΢��),
	 * �������+��=0, Ŀ����=Դ��Сʱ���� 24 ȡ��,
	 * ����Ŀ����=Դ��Сʱ���� 24 ȡ��+1,
	 * Ȼ����ݼ������ʱ�ΰ����¹�����зֵ�:
	 * [00:00:00-02:00:00] ʱ��=1;
	 * [02:00:01-07:00:00] ʱ��=2;
	 * [07:00:01-11:00:00] ʱ��=3;
	 * [11:00:01-17:00:00] ʱ��=4;
	 * [17:00:01-21:00:00] ʱ��=5;
	 * [21:00:01-23:59:59] ʱ��=6
	 * ���� 0:ʱ�β���΢��
	 * ���� 1:ʱ�κ�΢��
	 * Լ��:����������΢������ж�
	 * 
	 * 
	 * 
	 * ע���ְ�֮ǰһ6��3)������  �ҽ��в���0����
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr = str.split("\\s+")[1].split(":");
	
		//����Сʱ��
		int hour = 0;
		try{
			hour = Integer.parseInt(strArr[0]) % 24;
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		if(param.trim().equals("0")){
			//�ж��Ƿ��1
			if(!strArr[1].equals("00") || !strArr[2].equals("00")){
				hour++;
			}
		} else if(param.trim().equals("1")){
			
		}
		
		if(hour >=0 && hour <= 2){
			return "1";
		} else if ( hour <= 7) {
			return "2";
		} else if( hour <=11){
			return "3";
		} else if (hour <= 17){
			return "4";
		} else if (hour <= 21){
			return "5";
		} else if( hour <= 24){
			return "6";
		}
		
		return null;
	}

}
