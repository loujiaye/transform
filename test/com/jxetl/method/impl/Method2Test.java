package com.jxetl.method.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;

public class Method2Test {

	@Test
	public void test() throws MethodDealException {
		/*String str = MethodFactory.getById("2").invoke("0579114,579", "null", "null", "null");
		assertEquals("114", str);
		str = MethodFactory.getById("2").invoke("133870070601,579", "null", "null", "null");
		assertEquals("13387007060", str);
		str = MethodFactory.getById("2").invoke("07016287888,", "null", "null", "null");
		assertEquals("7016287888" , str);		
		//str=MethodFactory.getById("2").invoke("008679296202,792,", "null", "null", "null");
		//assertEquals("79296202" , str);
		str=MethodFactory.getById("2").invoke("1790118970298765,792", "null", "null", "null");
		assertEquals("18970298765" , str);
		//System.out.println(str);
*/		
		String str = MethodFactory.getById("2").invoke("07967071807,796", "null", "null", "null");

		str = MethodFactory.getById("2").invoke("18607999218,21", "null", "null", "null");
		assertEquals("18607999218" ,str);
		
		str = MethodFactory.getById("2").invoke("8618018,794", "null", "null", "null");
		assertEquals("",str);
		str = MethodFactory.getById("2").invoke("18605686485,558", "null", "null", "null");
		assertEquals("18605686485",str);
		str = MethodFactory.getById("2").invoke("00867907201289,790", "null", "null", "null");
		assertEquals("7201289",str);
		str = MethodFactory.getById("2").invoke("017853908282,790", "null", "null", "null");
		assertEquals("017853908282",str);
		str = MethodFactory.getById("2").invoke("12580,213", "null", "null", "null");
		assertEquals("12580",str);
		str = MethodFactory.getById("2").invoke("86861802,213", "null", "null", "null");
		assertEquals("",str);
		str = MethodFactory.getById("2").invoke("86791830,791", "null", "null", "null");
		assertEquals("791830",str);
		str = MethodFactory.getById("2").invoke("00867971212121212290,797", "null", "null", "null");
		assertEquals("7971212121212290",str);
		
		
		
		
		//7960982    ÑÇÁª  0982
	}



}
