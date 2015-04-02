package com.jxetl.method;

import java.util.Map;

import com.jxetl.manage.MapTable;

/**
 * 
 * @author wy
 * 过滤的判断函数，需要匹配，method_flag为1时函数
 */
public class FilterMethod1 {
	public static boolean invoke(String str , String mapName){
		
		Map<String , String > map = MapTable.getMapByName(mapName);
		for(String key : map.keySet()){
			if(str.startsWith(key)){
				return true;
			}
		}
		return false;
	}
}
