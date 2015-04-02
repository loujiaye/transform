package com.jxetl.model;

import java.io.Serializable;

/**
 * 
 * @author wy
 * @description Ŀ���ļ�ʵ����
 */
@SuppressWarnings("serial")
public class DestFile implements Serializable{
	
	private String name;
	private int fieldNum;
	private String delimiter;
	//����ҪΪfilter��
	private String filertId;
	// �ָ��־������boolean
	private String separate;
	// �շָ������enum
	private String day;

	public String[] fields;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFieldNum() {
		return fieldNum;
	}

	public void setFieldNum(int fieldNum) {
		this.fieldNum = fieldNum;
		fields  = new String[fieldNum];
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
		
	}

	public String getFilertId() {
		return filertId;
	}

	public void setFilertId(String filertId) {
		this.filertId = filertId;
	}

	public String getSeparate() {
		return separate;
	}

	public void setSeparate(String separate) {
		this.separate = separate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}	
	
	public String getLine(){
		
		StringBuffer sb = new StringBuffer();
		sb.append(fields[0]);
		for(int i = 1 ;i < fields.length ;i++){
			sb.append(this.delimiter + fields[i]);
		}
		
		return sb.toString();
	}
	


}
