package com.jxetl.method.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;

public class Method1005Test {

	@Test
	public void test() throws MethodDealException {
		String str = MethodFactory.getById("1005").invoke("4", "null", null, "null");
		System.out.println(str);
	}

}
