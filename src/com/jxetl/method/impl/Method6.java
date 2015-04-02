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
	 * ʱ���ʽת����
	 * ����������ʱ���ʽ��mm/dd/yyyy hh:mm:ssת��Ϊ��ʽyyyy-mm-dd hh:mm:ss(10)
	 * ����������ʱ���ʽ��mm/dd/yyyy hh:mm:ssת��Ϊ��ʽyyyy-mm-dd (10)
	 * ����������ʱ���ʽ��yyyymmdd,hhmmssת��Ϊ��ʽyyyy-mm-dd hh:mm:ss(10),Ҫ���ļ��ķָ�����Ϊ���š�
	 * ����3������ʽyyyymmddhhmmssת��Ϊ��ʽyyyy-mm-dd hh:mm:ss(32)
	 */
	@Override
	public String invoke(String str, String defaultValue, String param , String mapName)
			throws MethodDealException {
		Pattern p = null;
		if(param.equals("0")){
			//����������ʱ���ʽ��mm/dd/yyyy hh:mm:ssת��Ϊ��ʽyyyy-mm-dd hh:mm:ss(10)
			p = Pattern.compile("(\\d\\d)/(\\d\\d)/(\\d\\d\\d\\d)\\s+(\\d\\d:\\d\\d:\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(3) + "-" + m.group(1) + "-" + m.group(2) + " " + m.group(4);
			} else {
				throw new MethodDealException("method6 ��������ڲ���");
			}
		} else if(param.equals("1")){
			//����������ʱ���ʽ��mm/dd/yyyy hh:mm:ssת��Ϊ��ʽyyyy-mm-dd (10)
			p = Pattern.compile("(\\d\\d)/(\\d\\d)/(\\d\\d\\d\\d)\\s+(\\d\\d:\\d\\d:\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(3) + "-" + m.group(1) + "-" + m.group(2);
			} else {
				throw new MethodDealException("method6 ��������ڲ���");
			}
		} else if(param.equals("2")){
			//����������ʱ���ʽ��yyyymmdd,hhmmssת��Ϊ��ʽyyyy-mm-dd hh:mm:ss(10),Ҫ���ļ��ķָ�����Ϊ���š�
			p = Pattern.compile("(\\d\\d\\d\\d)(\\d\\d)(\\d\\d),(\\d\\d)(\\d\\d)(\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(1) + "-" + m.group(2) + "-" + m.group(3) + " " 
						+ m.group(4) + ":" + m.group(5) + ":" + m.group(6);
			} else {
				throw new MethodDealException("method6 ��������ڲ���" + str);
			}
		} else if(param.trim().equals("3")){
			//����3������ʽyyyymmddhhmmssת��Ϊ��ʽyyyy-mm-dd hh:mm:ss(32)
			//str   -3   ת�� -3--::
			p = Pattern.compile("(\\d\\d\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)(\\d\\d)?(\\d\\d)");
			Matcher m = p.matcher(str.trim());
			if(m.matches()){
				return m.group(1) + "-" + m.group(2) + "-" + m.group(3)
						+ " " + m.group(4) + ":" + m.group(5) + ":" + m.group(6);
			} else {
				//��ʱ����
				return str+"-- ::";
			}
		} else{
			throw new MethodDealException("method6 ��������");
		}
		
		
	}

}
