package com.jxetl.method;

import java.util.Map;

import com.jxetl.manage.MapTable;

/**
 * 
 * @author wy
 * ���˵��жϺ�������Ҫƥ�䣬method_flagΪ1ʱ����
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
