package com.jxetl.exception;
/**
 * 
 * @author wy
 * 当源文件出错时抛出这个异常
 */
@SuppressWarnings("serial")
public class SrcFileException extends Exception {

	public SrcFileException() {
		super();
	}

	public SrcFileException(String message) {
		super(message);
	}
	
}
