package com.jxetl.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

public class HDFSUtil {
	public static Logger log = Logger.getRootLogger();
	private static Configuration conf =new Configuration();
	public static FileSystem fs ;
	private static Scanner scanner;
	static{
		try {
			fs = FileSystem.get(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//����Ƿ����·��������·���µ��ļ���
	public static List<Path> getFiles(String name){
		Path path =new Path(name);
		List<Path> list = new ArrayList<Path>();
		try {
			FileStatus fStatus = fs.getFileStatus(path);
			if(fStatus.isDir()){
				FileStatus[] fStatues = fs.listStatus(path);
				for(FileStatus fStatus2 : fStatues){
					if(!fStatus2.isDir()){
						list.add(fStatus.getPath());
					}
				}
			}
		} catch (IOException e) {
			log.error("·������");
		}	
		return list;
	}
	
	//����ļ��ǲ��Ǵ���
	public static boolean existFile(String name){
		Path path = new Path(name);
		
		try {
			if(fs.exists(path) && fs.isFile(path)){
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			log.error("����ļ�ʱ����");
		}
		return false;
		
	}
	
	//���Ŀ¼�ǲ��Ǵ���
	public static boolean existDir(String name){
		FileStatus fStatus = null;
		Path path = new Path(name);
		try {
			if(fs.exists(path)){
				fStatus = fs.getFileStatus(new Path(name));
			} else {
				return false;
			}
			
			if(fStatus.isDir()){
				return true;
			}
		} catch (IOException e) {
			log.error("����ļ�ʱ����");
		}
		return false;
		
	}
	//ɾ���ļ�
	public static void rm(String name){
		try {
			/*System.out.println("ɾ���ļ���" +  name + "(y/n)");
			scanner = new Scanner(System.in);
			String commond = scanner.next();
			if(commond.equalsIgnoreCase("y") || commond.equalsIgnoreCase("yes")){
				fs.delete(new Path(name) ,true);
				log.info("ɾ���ļ� :" + name);
			} else {
				String name2 = name.substring(0 , name.length() -1) + new Date().getTime() + "/";
				fs.rename(new Path(name), new Path(name2));
				log.info("�ļ� :" + name + "  �Ѿ�������Ϊ :" + name2);
			}*/
			fs.delete(new Path(name) ,true);
			log.info("ɾ���ļ� :" + name);
		} catch (IOException e) {
			log.error("ɾ��  " + name + " ʱ���ִ���");
			e.printStackTrace();
		}
	}
	
	//�ӱ����ϴ��ļ�
	public static void copyFormLocal(String name1 , String name2){
		try {
			fs.copyFromLocalFile(new Path(name1), new Path(name2));
		} catch (IOException e) {
			//����Ҫ�ϴ����ļ�������
			log.error("�ϴ��ļ�ʱ����");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}
