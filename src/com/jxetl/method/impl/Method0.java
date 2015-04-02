package com.jxetl.method.impl;

import com.jxetl.manage.MapTable;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method0 implements DealMethod {
	public final static int ID = 0;
	@Override
	public int getID() {		
		return ID;
	}

	/**
	 * 取缺省值，缺省值为空取源列值
	 * defaultValue 表达空值要用null 或 NULL
	 * 
	 * 由于参看配置文件 发现存在映射，所以更改为如果有映射，取出对应值的映射对应的值
	 *  ？？？？？？？？？？？
	 */
	@Override
	public String invoke(String str, String defaultValue, String param  , String mapName) {
		if(!mapName.equalsIgnoreCase("null")){
			if(MapTable.maps.containsKey(mapName)){
				if(MapTable.maps.get(mapName).containsKey(str)){
					return MapTable.maps.get(mapName).get(str);
				}
			} 
			if(defaultValue.equalsIgnoreCase("null")){
				return "0";
				
			} else {
				return defaultValue;
			}
		}
		
		if(defaultValue.equalsIgnoreCase("null")){
			if(str.equalsIgnoreCase("null")){
				return "";
			}else {
				return str;
			}
			
		} else {
			return defaultValue;
		}
		
		
	}

}
