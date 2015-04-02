package com.jxetl.manage;

import java.util.HashMap;
import java.util.Map;

import com.jxetl.method.DealMethod;
/**
 * 
 * @author wy
 * Method的工厂类，制造Method。
 * 
 */
public class MethodFactory {
	
	public static Map<String ,DealMethod> methods = new HashMap<String , DealMethod>();
	/**
	 * 
	 * @param name  method的名字 
	 * @return method实例
	 * 当map中有时从map中取，没有时创建
	 * method必须是com.jxetl.method.impl包里的类
	 */
	public static DealMethod get(String name){
		DealMethod method = null;
		name = "com.jxetl.method.impl." + name;
		if(methods.containsKey(name)){
			method = methods.get(name);
		} else {
			try {
				Object obj =  Class.forName(name).newInstance();
				if(obj instanceof DealMethod){
					method = (DealMethod)obj;
					methods.put(name, method);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("method参数名错误，请检查");
				e.printStackTrace();
			}
		}
		return method;
	}
	
	public static DealMethod getById(String id){
		return MethodFactory.get("Method" + id);
	}
	
}
