package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;

public class Method42 implements DealMethod {
	public final static int ID = 42;
	@Override
	public int getID() {
		
		return ID;
	}

	/**
	 * 
	 * 计算分档时段,首先根据呼叫开始时间计算呼叫时段(不含微秒),
	 * 如果分钟+秒=0, 目标列=源列小时数除 24 取余,
	 * 否则目标列=源列小时数除 24 取余+1,
	 * 然后根据计算出的时段按以下规则进行分档:
	 * [00:00:00-02:00:00] 时段=1;
	 * [02:00:01-07:00:00] 时段=2;
	 * [07:00:01-11:00:00] 时段=3;
	 * [11:00:01-17:00:00] 时段=4;
	 * [17:00:01-21:00:00] 时段=5;
	 * [21:00:01-23:59:59] 时段=6
	 * 参数 0:时段不含微秒
	 * 参数 1:时段含微秒
	 * 约束:本方法不对微秒进行判断
	 * 
	 * 
	 * 
	 * 注：现按之前一6（3)规整记  且仅有参数0处理
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr = str.split("\\s+")[1].split(":");
	
		//计算小时数
		int hour = 0;
		try{
			hour = Integer.parseInt(strArr[0]) % 24;
		}catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		if(param.trim().equals("0")){
			//判断是否加1
			if(!strArr[1].equals("00") || !strArr[2].equals("00")){
				hour++;
			}
		} else if(param.trim().equals("1")){
			
		}
		
		if(hour >=0 && hour <= 2){
			return "1";
		} else if ( hour <= 7) {
			return "2";
		} else if( hour <=11){
			return "3";
		} else if (hour <= 17){
			return "4";
		} else if (hour <= 21){
			return "5";
		} else if( hour <= 24){
			return "6";
		}
		
		return null;
	}

}
