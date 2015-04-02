package com.jxetl.util.impl;

import java.util.regex.*;

import com.jxetl.util.Prase;

public class MethodUtil implements Prase <String[]> {
	@Override
	public void prase(String[]  params , String str){
		
		String regex = "(\\d+)\\(?(\\S*?)\\)?";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if(m.matches()){
			params[0] = m.group(1);
			params[1] = m.group(2);
		}

	}
}
