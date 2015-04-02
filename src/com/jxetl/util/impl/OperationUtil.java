package com.jxetl.util.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jxetl.model.Operation;
import com.jxetl.model.MyTask;
import com.jxetl.util.Prase;

public class OperationUtil implements Prase<Map<String, MyTask>> {

	@Override
	public void prase(Map<String, MyTask> factory, String str) {
		MyTask task = null;
		Operation operation = null;

		// Set srcfile(file_name)
		String regex = "^\\s*set\\s+col\\s*" + "\\(\\s*(\\S+)\\s*\\)\\s*";
		// {field_num delimiter
		regex += "\\{(.+)\\}";
		Pattern p = Pattern.compile(regex, Pattern.COMMENTS
				| Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(str);

		if (matcher.lookingAt()) {

			if (factory.containsKey(matcher.group(1))) {
				task = factory.get(matcher.group(1));
			} else {
				task = new MyTask();
				task.setName(matcher.group(1));
				factory.put(matcher.group(1), task);
			}
			
		}

		regex = "\\{\\s*(\\S+)\\s+((?:\\S+)|(?:\\{.+?\\}))\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s*\\}";
		p = Pattern.compile(regex, Pattern.COMMENTS | Pattern.CASE_INSENSITIVE);
		str = matcher.group(2);
		matcher = p.matcher(str);
		while (matcher.find()) {
			Operation operation2 = new Operation();

			operation2.setDestId(matcher.group(1));
			operation2.setSrcId(matcher.group(2));
			operation2.setDefaultValue(matcher.group(3));
			operation2.setMethod(matcher.group(4));
			operation2.setMapName(matcher.group(5));
			
			if (operation == null) {
				operation = operation2;
				task.setOperation(operation);
				
			} else {
				operation.setNextOperation(operation2);
				operation = operation2;
			}
			

		}

	}

}
