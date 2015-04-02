package com.jxetl.util.impl;


import java.util.Map;
import java.util.regex.*;

import com.jxetl.model.*;
import com.jxetl.util.Prase;

public class SrcFileUtil implements Prase<Map<String , MyTask>> {


	/**
	 * @param <MyTask> 
	 * @param str 要解析的字符串
	 */
	@Override
	public void prase(Map<String , MyTask> factory ,String str) {
		SrcFile srcFile = new SrcFile();		
		//解析字符串
		//Set srcfile(file_name)  
		//Set srcfile(file_name)  
				String regex = "^\\s*set\\s+srcfile\\s*" + "\\(\\s*(\\S+)\\s*\\)\\s*";
				//  {field_num    delimiter
				regex += "\\{\\s*(\\d+)\\s+?((\\{\\s+\\})|(?:\\S+)|(?:\\t))\\s+?";
				//{cdr_comp_call_ds_1300}}
				regex += "\\{(.+)+\\}\\}";
		Pattern p = Pattern.compile(regex , Pattern.COMMENTS | Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(str);
		
		//获取匹配的字符串
		String[] param = new String[matcher.groupCount() + 1];
		if(matcher.lookingAt()){
			for(int i = 0 ; i <= matcher.groupCount() ; i++){
				param[i] = matcher.group(i);
			}
		}
		
		//给SrcFile类赋值
		srcFile.setName(param[1]);
		try{
			int fieldNum = Integer.parseInt(param[2]);
			srcFile.setFieldNum(fieldNum);
		} catch(NumberFormatException e){
			System.out.println("解析字符串是：" + str);
			System.out.println("解析设置源文件的文件字段个数应该是数字类型");
			e.printStackTrace();
			return ;
		}
		
		//分割字段使用正则表达式返回  空格用 \\s+ 表示
		//处理了正则表达是的特殊字符 \ . ( ) | ^ $ * + ?  有待测试
		if(param[4] != null ){
			srcFile.setDelimiter("\\s+");
		} else if(param[3] != null){
			boolean right = true;
			if(param[3].equals("{")){
				right = false;
			}
			if(param[3].equals("}")){
				right = false;
			}
			if(param[3].equals("[")){
				right = false;
			}
			if(param[3].equals("]")){
				right = false;
			}
			if(param[3].equals("\"")){
				right = false;
			}
			
			if(!right){
				System.out.println("解析字符串是：" + str);
				System.out.println("解析设置源文件的分割字段出现错误");
				return ;
			}
			
			if(param[3].equals("(")){
				srcFile.setDelimiter("\\("); 
			}else if(param[3].equals(")")){
				srcFile.setDelimiter("\\)"); 
			}else if(param[3].equals("?")){
				srcFile.setDelimiter("\\?"); 
			}else if(param[3].equals("+")){
				srcFile.setDelimiter("\\+"); 
			}else if(param[3].equals("*")){
				srcFile.setDelimiter("\\*"); 
			}else if(param[3].equals("\\")){
				srcFile.setDelimiter("\\\\"); 
			}else if(param[3].equals(".")){
				srcFile.setDelimiter("\\."); 
			} else if(param[3].equals("|")){
				srcFile.setDelimiter("\\|"); 
			}else if(param[3].equals("|")){
				srcFile.setDelimiter("\\|"); 
			}else if(param[3].equals("^")){
				srcFile.setDelimiter("\\^"); 
			}else if(param[3].equals("$")){
				srcFile.setDelimiter("\\$"); 
			} else if(param[3].equals("\t")){
				srcFile.setDelimiter("\\s+");
			}else{
				srcFile.setDelimiter(param[3]);
			}
			
		}
		
		for(String s : param[5].split("\\s+")){
			MyTask task= null;
			if(factory.containsKey(s)){
				task= factory.get(s);
				task.setSrcfile(srcFile);
			} else {
				task = new MyTask();
				task.setName(s);
				task.setSrcfile(srcFile);
				factory.put(s, task);
			}
			
		}

	}

	
}
