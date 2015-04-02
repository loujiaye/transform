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
		
		//��ȡƥ����ַ���
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
			System.out.println("�����ַ����ǣ�" + str);
			System.out.println("��������Ŀ���ļ����ļ��ֶθ��������idӦ������������");
			e.printStackTrace();
			return ;
		}
		destFile.setFilertId(param[4]);
		
		//��Ҫ�����е�ת���ַ������滻
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
