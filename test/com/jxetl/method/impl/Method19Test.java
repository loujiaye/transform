package com.jxetl.method.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;

public class Method19Test {

	@Test
	public void test() throws MethodDealException {
		String str = MethodFactory.getById("19").invoke("0,68", "null", "null", "null");
		assertEquals("0" , str);
	}

}
