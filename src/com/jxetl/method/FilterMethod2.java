package com.jxetl.method;

import java.util.Arrays;

/**
 * 
 * @author wy
 * 过滤的判断函数，需要匹配，method_flag为1时函数
 */
public class FilterMethod2 {
	public static boolean invoke(String str , String match_val , String match_logic){
		//匹配值
		String[] valuesString = match_val.split(",");
		/*int[] values = null ;*/
		
		
		/*values = new int[valuesString.length];
		for(int i = 0 ;i < valuesString.length ;i++){
			values[i] = Integer.parseInt(valuesString[i]);
		}*/
		
		//匹配
		/**
		 *暂时都为0 为发现1 -1状况
		 */
		/*int matchValue = Integer.parseInt(str);*/
		if(match_logic.equals("0")){
			for(String tmp : valuesString){
				if(str.equals(tmp)){
					return true;
				}
			}
		} else {
			System.out.println("出现了其他匹配逻辑 in FilterMethod2");
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
