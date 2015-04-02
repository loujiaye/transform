package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *  
 */
public class Method13 implements DealMethod {
	private static final int ID = 13;
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	/**
	 * 目标列=参数date_stamp,即-p后面的值
	 * @throws MethodDealException 
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException {
		if(Argument.dateStamp == null){
			throw new MethodDealException("执行命令档参数中dateStamp没有制定");
		}
		return Argument.dateStamp;
	}

}
