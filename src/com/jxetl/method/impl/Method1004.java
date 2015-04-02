package com.jxetl.method.impl;

import java.text.DecimalFormat;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method1004 implements DealMethod {
	public static final int ID = 1004;
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * {if (int(col17)=1 or int(col17)=0) then col19/1000 else 0}
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String paramArr[] = param.split(",");
		String strArr[] = str.split(",");
		
		try{
			for(String tmp : paramArr){
				if(strArr[0].equals(tmp)){
					double result = Double.parseDouble(strArr[1]) / 1000;
					return new DecimalFormat("0.000000").format(result);
				}
			}
			return "0";
		} catch (NumberFormatException e){
			throw new MethodDealException("第二个源字段应为数字类型");
		}
	}

}
