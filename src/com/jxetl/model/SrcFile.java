package com.jxetl.model;

import java.io.Serializable;
import java.util.Arrays;

import com.jxetl.exception.SrcFileException;


/**
 * 
 * @author wy
 * @description Ŀ���ļ�ʵ����
 */
@SuppressWarnings("serial")
public class SrcFile implements Serializable{
	private String name;
	private int fieldNum;
	private String delimiter;
	
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
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * �����ݶ��룬�ָ������
	 * @param str  ���ָ��ַ���
	 * �����׳��쳣���ֶ�������ʱ��߲��մ���
	 */
	public void separate(String str) throws SrcFileException{
		String fields2[] = str.split(delimiter);

		if(fields2.length <  fieldNum){
			fields = new String[fieldNum];
			Arrays.fill(fields, "");
			for(int i = 0 ;i < fields2.length ; i++){
				fields[i] = fields2[i];
			}
		} else if(fields2.length == fieldNum) {
			fields = fields2;
		} else {
			fields =new String[fieldNum];
			for(int i = 0 ;i < fieldNum ;i++){
				fields[i] = fields2[i];
			}
		}
		
		/*for(int i = 0 ; i < fields.length ; i++){
			System.out.println(i + 1 + " " + fields[i]);
		}*/
	}


}
