package com.jxetl.exception;

/**
 * 
 * @author ljy
 * 当处理数据方法有错误时 ，抛出这个异常
 */
@SuppressWarnings("serial")
public class MethodDealException extends Exception {

	public MethodDealException() {
		super();
	}

	public MethodDealException(String message) {
		super(message);
	}

	


}
