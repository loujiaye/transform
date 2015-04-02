package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method15 implements DealMethod {
	public final static int ID = 15;

	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 目标列=参数 city_id,即-c 后面的值
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		if(Argument.cityId  == null){
			throw new MethodDealException("执行命令档参数中 -c没有指定");
		}
		return Argument.cityId ;
	}

}
