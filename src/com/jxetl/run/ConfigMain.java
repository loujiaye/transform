package com.jxetl.run;

import com.jxetl.manage.Factory;

/**
 * 
 * @author wy
 * �����������л��ļ�
 */
public class ConfigMain {

	/**
	 * @param args  ���Դ�����������  ��һ�������������ļ���λ�ã�
	 * 		�ڶ����������������ļ�λ��
	 */
	public static void main(String[] args) {
		if(args.length == 2){
			Factory.create(args[0], args[1]);
		} else {
			Factory.create();
		}
		//Factory.printFactory("cdr_call_yyyymmdd_1000");

	}

}
