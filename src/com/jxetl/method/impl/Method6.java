package com.jxetl.method.impl;

import java.util.regex.*;

import com.jxetl.exception.MethodDealException;
import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 *
 */
public class Method6 implements DealMethod {
	public static final int ID = 6;
	@Override
	public int getID() {
		return ID;
	}

	/**
	 * 时间格式转换：
	 * 参数０：将时间格式由mm/dd/yyyy hh:mm:ss转换为格式yyyy-mm-dd hh:mm:ss(10)
	 * 参数１：将时间格式由mm/dd/yyyy hh:mm:ss转换为格式yyyy-mm-dd (10)
	 * 参数２：将时间格式由yyyymmdd,hhmmss转换为格式yyyy-mm-dd hh:mm:ss(10),要求文件的分隔符不为逗号。
	 * 参数3：将格式yyyymmddhhmmss转换为格式yyyy-mm-dd hh:mm:ss(32)
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName)
			throws MethodDealException {
		Pattern p = null;
		if(param.equals("0")){
			//参数０：将时间格式由mm/dd/yyyy hh:mm:ss转换为格式yyyy-mm-dd hh:mm:ss(10)
			p = Pattern.compile("(\\d\\d)/(\\d\\d)/(\\d\\d\\d\\d)\\s+(\\d\\d:\\d\\d:\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(3) + "-" + m.group(1) + "-" + m.group(2) + " " + m.group(4);
			} else {
				throw new MethodDealException("method6 输入的日期不对");
			}
		} else if(param.equals("1")){
			//参数１：将时间格式由mm/dd/yyyy hh:mm:ss转换为格式yyyy-mm-dd (10)
			p = Pattern.compile("(\\d\\d)/(\\d\\d)/(\\d\\d\\d\\d)\\s+(\\d\\d:\\d\\d:\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(3) + "-" + m.group(1) + "-" + m.group(2);
			} else {
				throw new MethodDealException("method6 输入的日期不对");
			}
		} else if(param.equals("2")){
			//参数２：将时间格式由yyyymmdd,hhmmss转换为格式yyyy-mm-dd hh:mm:ss(10),要求文件的分隔符不为逗号。
			p = Pattern.compile("(\\d\\d\\d\\d)(\\d\\d)(\\d\\d),(\\d\\d)(\\d\\d)(\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(1) + "-" + m.group(2) + "-" + m.group(3) + " " 
						+ m.group(4) + ":" + m.group(5) + ":" + m.group(6);
			} else {
				throw new MethodDealException("method6 输入的日期不对" + str);
			}
		} else if(param.trim().equals("3")){
			//参数3：将格式yyyymmddhhmmss转换为格式yyyy-mm-dd hh:mm:ss(32)
			//str   -3   转换 -3--::
			p = Pattern.compile("(\\d\\d\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)?(\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(1) + "-" + m.group(2) + "-" + m.group(3)
						+ " " + m.group(4) + ":" + m.group(5) + ":" + m.group(6);
			} else {
				str = str.trim();
				int len=str.length();
				String tmp="";
				if(len >= 14){
					return str.substring(0,4) + "-" + str.substring(4,6)   + "-" + str.substring(6,8)  +  " " + str.substring(8,10)  + ":" + str.substring(10,12) +  ":" + str.substring(12 ,14); 
				}else if(len >= 12){
					return str.substring(0,4) + "-" + str.substring(4,6)   + "-" + str.substring(6,8)  +  " " + str.substring(8,10)  + ":" + str.substring(10,12) +  ":" + str.substring(12 ,len); 
				}else if(len >= 10){
					return str.substring(0,4) + "-" + str.substring(4,6)   + "-" + str.substring(6,8)  +  " " + str.substring(8,10)  + ":" + str.substring(10,len) +  ":" ;
				}else if(len >= 8){
					return str.substring(0,4) + "-" + str.substring(4,6)   + "-" + str.substring(6,8)  +  " " + str.substring(8,len)  + ":"  +  ":" ;
				}else if(len >= 6){
					return str.substring(0,4) + "-" + str.substring(4,6)   + "-" + str.substring(6,len)  +  " " + ":" +  ":" ;
				}else if(len >= 4){
					return str.substring(0,4) + "-" + str.substring(4,len)   + "-" +  " " + ":" +  ":" ;
				} else if( len > 0) {
					return str.substring(0,len) + "--" +  " " + ":" +  ":" ;
				} else {
					return "";
				}

			}
		} else{
			throw new MethodDealException("method6 参数不对");
		}
		
		
	}

}
