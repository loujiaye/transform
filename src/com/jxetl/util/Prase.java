package com.jxetl.util;

/**
 * 
 * @author wy
 * @description 解析字符串
 *
 */
public interface Prase<T> {
	/**
	 * 
	 * @param t   容器类1
	 * @param str 需要处理的字符串
	 */
	public void prase(T t , String str);


}
