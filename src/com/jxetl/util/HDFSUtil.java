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
	
	
	
	
	//检查是否存在路径，返回路径下的文件名
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
			log.error("路径错误");
		}	
		return list;
	}
	
	//检查文件是不是存在
	public static boolean existFile(String name){
		Path path = new Path(name);
		
		try {
			if(fs.exists(path) && fs.isFile(path)){
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			log.error("检查文件时出错");
		}
		return false;
		
	}
	
	//检查目录是不是存在
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
			log.error("检查文件时出错");
		}
		return false;
		
	}
	//删除文件
	public static void rm(String name){
		try {
			/*System.out.println("删除文件夹" +  name + "(y/n)");
			scanner = new Scanner(System.in);
			String commond = scanner.next();
			if(commond.equalsIgnoreCase("y") || commond.equalsIgnoreCase("yes")){
				fs.delete(new Path(name) ,true);
				log.info("删除文件 :" + name);
			} else {
				String name2 = name.substring(0 , name.length() -1) + new Date().getTime() + "/";
				fs.rename(new Path(name), new Path(name2));
				log.info("文件 :" + name + "  已经重命名为 :" + name2);
			}*/
			fs.delete(new Path(name) ,true);
			log.info("删除文件 :" + name);
		} catch (IOException e) {
			log.error("删除  " + name + " 时出现错误");
			e.printStackTrace();
		}
	}
	
	//从本地上传文件
	public static void copyFormLocal(String name1 , String name2){
		try {
			fs.copyFromLocalFile(new Path(name1), new Path(name2));
		} catch (IOException e) {
			//可能要上传的文件不存在
			log.error("上传文件时出错");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
}
