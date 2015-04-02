package com.jxetl.util.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jxetl.model.DestFile;
import com.jxetl.model.MyTask;
import com.jxetl.util.Prase;

public class DestFileUtil implements Prase<Map<String, MyTask> > {

	@Override
	public void prase(Map<String, MyTask> factory, String str) {
		DestFile destFile = new DestFile();
		//set destfile(cdr_newbusi_ds_1405) {31 \t 1405 0 NULL}
		//set destfile(cdr_newbusi_ds_1405)
		String regex = "^\\s*set\\s+destfile\\s*" + "\\(\\s*(\\S+)\\s*\\)\\s*";
		//  {31 \t 1405 0 NULL}
		regex += "\\{\\s*(\\d+)\\s+?((?:\\S+))\\s+?(\\d+)\\s+(\\S+)\\s+(\\S+)\\}";
				
		Pattern p = Pattern.compile(regex , Pattern.COMMENTS | Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(str);
		
		//获取匹配的字符串
		String[] param = new String[matcher.groupCount() + 1];
		if(matcher.lookingAt()){
			for(int i = 0 ; i <= matcher.groupCount() ; i++){
				param[i] = matcher.group(i);
			}
		}
		
		destFile.setName(param[1]);
		try{

			destFile.setFieldNum(Integer.parseInt(param[2]));
			
			
		} catch(NumberFormatException e){
			System.out.println("解析字符串是：" + str);
			System.out.println("解析设置目标文件的文件字段个数或过滤id应该是数字类型");
			e.printStackTrace();
			return ;
		}
		destFile.setFilertId(param[4]);
		
		//需要对所有的转移字符进行替换
		param[3] = param[3].replace("\\t", "\t");
		destFile.setDelimiter(param[3]);


		destFile.setSeparate(param[5]);
		destFile.setDay(param[6]);
		
		MyTask task = null;
		if(factory.containsKey(destFile.getName())){
			task = factory.get(destFile.getName());
		} else {
			task = new MyTask();
			task.setName(destFile.getName());		
			factory.put(destFile.getName(), task);
		}
		task.setDestfile(destFile);
	}

}
