package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method1001 implements DealMethod {
	public static final int ID = 1001;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * {if (int(col17)=2)and ((col4='2') or (col4='3')) then '700001' 
	 * else if (col21='61')  then '500040' 
	 * else '0' }
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String strArr[] = str.split(",");
		int arr[] = new int[strArr.length];
		try{
			for(int i = 0 ; i < arr.length ; i++ ){
				arr[i] = Integer.parseInt(strArr[i]);
			}
		} catch(NumberFormatException e){
			new MethodDealException("参数应该为数字。ID：" + ID );
		}
		
		if(arr[1] == 2){
			if(arr[0] == 2 || arr[0] == 3){
				return "700001";
			}
		} else if(arr[2] == 61){
			return "500040";
		} else {
			return "0";
		}
		
		return null;
	}

}
