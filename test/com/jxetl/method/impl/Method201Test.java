package com.jxetl.method.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;

public class Method201Test {

	@Test
	public void test() throws MethodDealException {
		String str = MethodFactory.getById("201").invoke("7046667,798", "null", "null", "null");
		System.out.println(str);
	
		
		
		
		//7960982    ÑÇÁª  0982
	}



}
