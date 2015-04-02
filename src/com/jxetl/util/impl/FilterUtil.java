package com.jxetl.util.impl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jxetl.model.Filter;
import com.jxetl.util.Prase;

public class FilterUtil implements Prase<Map<String, Filter>> {

	@Override
	public void prase(Map<String, Filter> factory, String str) {
		Filter filter = null;
		String name = "";

		String regex = "^\\s*set\\s+filter\\s*" + "\\(\\s*(\\S+)\\s*\\)\\s*";

		regex += "\\{(.*)\\}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		
		if (m.lookingAt()) {
			name = m.group(1);
			regex = "\\{\\s*(\\&|\\|)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+(\\S+)\\s+((?:\\S+)|(?:\\{.*\\}))\\s*\\}";
			p = Pattern.compile(regex);
			m = p.matcher(m.group(2));
			while (m.find()) {
				Filter filter2 = new Filter();
				filter2.setLogic(m.group(1));
				filter2.setMethodid(m.group(2));
				filter2.setColumnid(m.group(3));
				filter2.setMatch_flag(m.group(4));
				filter2.setMatch_logic(m.group(5));
				filter2.setMatch_val(m.group(6));
				filter2.setLogic_flag(m.group(7));

				if (filter == null) {
					filter = filter2;
					factory.put(name, filter);
				} else {
					filter.setNextFilter(filter2);
					filter = filter2;
				}
			}
		} else {
			System.out.println(str);
		}

	}

}
