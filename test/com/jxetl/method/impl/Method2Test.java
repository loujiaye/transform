package com.jxetl.method.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;

public class Method2Test {

	@Test
	public void test() throws MethodDealException {
		String str = MethodFactory.getById("2").invoke("0579114,579", "null", "null", "null");
		assertEquals("114", str);
		str = MethodFactory.getById("2").invoke("133870070601,579", "null", "null", "null");
		assertEquals("13387007060", str);
		str = MethodFactory.getById("2").invoke("07016287888,", "null", "null", "null");
		assertEquals("7016287888" , str);		
		str=MethodFactory.getById("2").invoke("008679296202,792,", "null", "null", "null");
		assertEquals("79296202" , str);
		
		
		//7960982    ÑÇÁª  0982
	}

}
