package com.jxetl.method.impl;

import java.util.Map;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MapTable;
import com.jxetl.method.DealMethod;

/**
 * 
 * @author wy

 */
public class Method14 implements DealMethod {
	public static final int ID = 14;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 映射操作如果映射不到,取 default 值、如果 default 为空取 0。
	 * 参数0:简单映射
	 * 参数1:map1 中 boss_value 应该定义一个范围值。如’[0,4]’, 
	 * 表示大于等于 0 小于 4,其中()表示开区间,作 而[]表示闭区间。
	 * 参数2:离散映射.map2 中应该包括多个值。如'a,b,c,d'。
	 * 参数3:根据源列长度映射。假设源列值=abcdef, map3中
	 * 对应的 boss_value=abc,和源列值的前三位相同,则映射成功
	 * 参数4:简单映射、如果映射不到,取源列值
	 * 
	 * 实现了 0 3 4
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {

		Map<String , String > map = MapTable.getMapByName(mapName);
		if(map == null){
			throw new MethodDealException("没有map值");
		}
		
		if(param.equals("0")){
			if(map.containsKey(str)){
				return map.get(str);
			} 
		} else if(param.equals("1")){
			
		} else if(param.equals("2")){
			
		} else if(param.equals("3")){
			//因791869匹配为7 但是显示都是0 所以剔除
			//54|7|0    a:079186975860  b:791
			if(mapName.equals("map_call_opposite")){
				map.remove("791869");
			}
			
			for(String key : map.keySet()){
				if(str.startsWith(key)){
					return map.get(key);
				}
			}
		} else if(param.equals("4")){
			if(map.containsKey(str)){
				return map.get(str);
			} else{
				return str;
			}
		} 
		
		//映射不到取默认值
		if(defaultValue.equalsIgnoreCase("null")){
			return "0";
		} else {
			return defaultValue;
		}
	}

}
