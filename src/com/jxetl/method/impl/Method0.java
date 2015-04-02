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
	 * ȡȱʡֵ��ȱʡֵΪ��ȡԴ��ֵ
	 * defaultValue ����ֵҪ��null �� NULL
	 * 
	 * ���ڲο������ļ� ���ִ���ӳ�䣬���Ը���Ϊ�����ӳ�䣬ȡ����Ӧֵ��ӳ���Ӧ��ֵ
	 *  ����������������������
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
