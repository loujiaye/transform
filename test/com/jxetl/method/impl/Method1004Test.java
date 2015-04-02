package com.jxetl.method.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;

public class Method1004Test {

	@Test
	public void test() throws MethodDealException {
		String str = MethodFactory.getById("1004").invoke("0,0", "null", "1,0", "null");
		System.out.println(str);
	}

}
