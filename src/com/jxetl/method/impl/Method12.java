package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author ljy
 * @description 方法12 
 *     目标列=参数op_time,即-t后面的值
 */
public class Method12 implements DealMethod {
	public final static int ID = 12;

	@Override
	public int getID() {

		return ID;
	}

	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException{
		if(Argument.opTime == null){
			throw new MethodDealException("执行命令档参数中 -t没有指定");
		}
		return Argument.opTime;
	}

}
