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
	 * ӳ��������ӳ�䲻��,ȡ default ֵ����� default Ϊ��ȡ 0��
	 * ����0:��ӳ��
	 * ����1:map1 �� boss_value Ӧ�ö���һ����Χֵ���硯[0,4]��, 
	 * ��ʾ���ڵ��� 0 С�� 4,����()��ʾ������,�� ��[]��ʾ�����䡣
	 * ����2:��ɢӳ��.map2 ��Ӧ�ð������ֵ����'a,b,c,d'��
	 * ����3:����Դ�г���ӳ�䡣����Դ��ֵ=abcdef, map3��
	 * ��Ӧ�� boss_value=abc,��Դ��ֵ��ǰ��λ��ͬ,��ӳ��ɹ�
	 * ����4:��ӳ�䡢���ӳ�䲻��,ȡԴ��ֵ
	 * 
	 * ʵ���� 0 3 4
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {

		Map<String , String > map = MapTable.getMapByName(mapName);
		if(map == null){
			throw new MethodDealException("û��mapֵ");
		}
		
		if(param.equals("0")){
			if(map.containsKey(str)){
				return map.get(str);
			} 
		} else if(param.equals("1")){
			
		} else if(param.equals("2")){
			
		} else if(param.equals("3")){
			//��791869ƥ��Ϊ7 ������ʾ����0 �����޳�
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
		
		//ӳ�䲻��ȡĬ��ֵ
		if(defaultValue.equalsIgnoreCase("null")){
			return "0";
		} else {
			return defaultValue;
		}
	}

}
