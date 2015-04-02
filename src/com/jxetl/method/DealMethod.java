package com.jxetl.method;

import com.jxetl.exception.MethodDealException;

/**
 * 
 * @author ljy
 * 处理方法接口 获取方法的ID，执行方法，返回处理后的字符串
 */
public interface DealMethod {
	/**
	 * 
	 * @return 返回配置信息ID号
	 */
	int getID();
	/**
	 * 
	 * @param str	要处理的字符串
	 * @param defaultValue 默认值
	 * @param param	方法的参数
	 * @return 处理后的字符串
	 * @throws MethodDealException 
	 */
	String invoke(String str ,String defaultValue ,  String param,String mapName) throws MethodDealException;
}
