package com.jxetl.method;

import java.util.Arrays;

/**
 * 
 * @author wy
 * ���˵��жϺ�������Ҫƥ�䣬method_flagΪ1ʱ����
 */
public class FilterMethod2 {
	public static boolean invoke(String str , String match_val , String match_logic){
		//ƥ��ֵ
		String[] valuesString = match_val.split(",");
		/*int[] values = null ;*/
		
		
		/*values = new int[valuesString.length];
		for(int i = 0 ;i < valuesString.length ;i++){
			values[i] = Integer.parseInt(valuesString[i]);
		}*/
		
		//ƥ��
		/**
		 *��ʱ��Ϊ0 Ϊ����1 -1״��
		 */
		/*int matchValue = Integer.parseInt(str);*/
		if(match_logic.equals("0")){
			for(String tmp : valuesString){
				if(str.equals(tmp)){
					return true;
				}
			}
		} else {
			System.out.println("����������ƥ���߼� in FilterMethod2");
		}
			/*else if(match_logic.equals("1")){	
		}
			for(int i : values){
				if(matchValue >  i){
					 return true;
				} else {
					return false;
				}
			}	
		} else if(match_logic.equals("-1")){			
			for(int i : values){
				if(matchValue <  i){
					 return true;			 
				}else {
					return false;
				}
			}		
		}*/
				
		return false;
	}
	
	
}
