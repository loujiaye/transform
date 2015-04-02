package com.jxetl.method.impl;

import com.jxetl.manage.MapTable;
import com.jxetl.method.DealMethod;

public class Method25 implements DealMethod {
	public final static int ID = 25;
	
	@Override
	public int getID() {		
		return ID;
	}
	
	/**
	 * 判断免费特服。如果第一列的值在映射 prod_id 中能够映射到,则为特服标志,
	 * 如果将剩余列相加为 0,判断为免费特服1。
	 * 否则,不是免费特服 0(江西专用)
	 */
	@Override
	public String invoke(String str, String defaultValue, String param  , String mapName) {
		String[] strArr = str.split(",");
		if(MapTable.maps.containsKey(mapName) && MapTable.maps.get(mapName).containsKey(strArr[0])){
			int sum = 0;
			try{
				for(int i = 1 ; i < strArr.length ; i++){
					sum += Integer.parseInt(strArr[i]);
				}
			}catch (NumberFormatException e){
				e.printStackTrace();
			}
		
			if(sum == 0){
				return "1";
			}
			
		}
	
		return "0";
	}

}
