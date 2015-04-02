package com.jxetl.method.impl;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method37 implements DealMethod {
	public final static int ID = 37;
	
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 根据指定的源列 a,b 从源列 b 中选择参数指定的 n 个位置的字符组成新串加到源列 a 后面
	 */
	@Override
	public String invoke(String str, String defaultValue, String param,
			String mapName) throws MethodDealException {
		String[] strArr = str.trim().split(",");
		char[] charArr = strArr[1].trim().toCharArray();
		String[] paramArr = param.split(",");

		
		if(strArr.length != 2){
			throw new MethodDealException("源列个数不够");
		}
		
		try{
			int tmp ;
			strArr[0] = strArr[0].trim();
			for(int i = 0 ;i < paramArr.length ; i++){
				tmp = Integer.parseInt(paramArr[i]);
				if(tmp > (charArr.length -1)){
					continue;
				}
				strArr[0] += charArr[tmp - 1];
			}
		} catch (NumberFormatException e){
			throw new MethodDealException("请输入数字参数");
		}
		
		return strArr[0];
	}

}
