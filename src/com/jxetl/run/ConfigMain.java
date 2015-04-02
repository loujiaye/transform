package com.jxetl.run;

import com.jxetl.manage.Factory;

/**
 * 
 * @author wy
 * 生成配置序列化文件
 */
public class ConfigMain {

	/**
	 * @param args  可以传入两个参数  第一个是生成配置文件的位置，
	 * 		第二个参数生成配置文件位置
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
