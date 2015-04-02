package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.Argument;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method34 implements DealMethod {
	public final static int ID = 34;

	@Override
	public int getID() {

		return ID;
	}

	/**
	 * 目标列=参数 src_file,即-s 后面的值
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName) throws MethodDealException{
		if(Argument.sourceFile  == null){
			throw new MethodDealException("执行命令档参数中 -s没有指定");
		}
		return Argument.sourceFile ;
	}

}
