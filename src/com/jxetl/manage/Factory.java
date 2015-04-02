package com.jxetl.manage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.jxetl.model.*;
import com.jxetl.util.impl.*;

/**
 * 
 * @author wy
 * @description 读取配置文件并且解析成mapreduce程序
 */
public class Factory {
	public static Map<String, MyTask> tasks = new HashMap<String, MyTask>();
	
	private static Map<String, Filter> filter = new HashMap<String, Filter>();

	/**
	 * 默认路径在工程下的config文件件
	 * @return
	 */
	public static boolean create(){
		String configPath = System.getProperty("user.dir") + "/config/";
		String outPath = System.getProperty("user.dir") + "/config/";
		return create(configPath + "config.txt" , outPath);
	}
	
	/**
	 * 根据路径读取，解析，并保存解析结果
	 * @param inPath 配置文件位置
	 * @param outPath  输出配置的路径
	 * @return 成功返回true
	 */
	public static boolean create(String inPath , String  outPath){
		if(readConfig(inPath ) && writeConfig(outPath)){
			
			return true;
		} else {
			return false;
		}
		
	}
	/**
	 * 从文件中读取配置文件信息
	 */
	private static  boolean readConfig(String taskFilePath) {
		BufferedReader br = null;
		File taskFile = new File(taskFilePath);

		try {
			br = new BufferedReader(new FileReader(taskFile));
			setTask(br);		
		} catch (FileNotFoundException e) {
			System.out.println("配置文件不存在，请确认配置文件位置");
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("文件不能关闭");
			}
		}

		//将filter与task关联
		for (Map.Entry<String, MyTask> entry : Factory.tasks.entrySet()) {
			MyTask tmpTask = entry.getValue();
			Filter tmpFilter = null;
			tmpFilter = filter.get(tmpTask.getDestfile().getFilertId());
			tmpTask.setFilter(tmpFilter);
		}
		
		return true;
	}

	/**
	 * 对配置文件解析
	 * 
	 * @param br
	 *            配置文件BufferedReader
	 */
	private  static void setTask(BufferedReader br) {
		String str = null;
		String tmp = "";
		try {
			while ((str = br.readLine()) != null) {

				if (str.startsWith("#")) {
					if (!tmp.equals("")) {
						if (tmp.startsWith("set srcfile")) {
							new SrcFileUtil().prase(Factory.tasks, tmp);
						} else if (tmp.startsWith("set destfile")) {
							new DestFileUtil().prase(Factory.tasks, tmp);
						} else if (tmp.startsWith("set col")) {
							new OperationUtil().prase(Factory.tasks, tmp);
						} else if (tmp.startsWith("set filter")) {
							new FilterUtil().prase(filter, tmp);
						}
					}
					tmp = "";
				} else {
					if (!str.equals("")) {
						tmp += str.trim();
					}
				}
			}

			if (tmp.startsWith("set srcfile")) {
				new SrcFileUtil().prase(Factory.tasks, tmp);
			} else if (tmp.startsWith("set destfile")) {
				new DestFileUtil().prase(Factory.tasks, tmp);
			} else if (tmp.startsWith("set col")) {
				new OperationUtil().prase(Factory.tasks, tmp);
			} else if (tmp.startsWith("set filter")) {
				new FilterUtil().prase(filter, tmp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static boolean writeConfig(String outPath){

		try {
			for(String str : tasks.keySet()){
				
				if(!tasks.get(str).isComplete()){
					System.out.println("配置" + str + "不完整");
					continue;
				}
				
				//写入文件
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outPath + str + ".file"));
				MyTask task = tasks.get(str);
				oos.writeObject(task);
				oos.flush();
				
				try {
					if (oos != null) {
						oos.close();
					}
				} catch (IOException e) {
					System.out.println("文件不能关闭");
				}
			}
		} catch (IOException e) {
			System.out.println("写入错误");
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}
	

	/**
	 * 打印读取的配置文件，调试用
	 */
	public static void printFactory() {
		Scanner sc =new Scanner(System.in);
		for (Map.Entry<String, MyTask> entry : Factory.tasks.entrySet()) {
			
			MyTask config = entry.getValue();
			
			printConfig(config);
			
			sc.next();
		}
	}
	
	public static void printFactory(String name){
		MyTask config = tasks.get(name);
		printConfig(config);
	}
	
	public static void printConfig(MyTask config){
		System.out.println("名字是：" + config.getName());
		System.out.println("------------------------------------------");
		if (config.getSrcfile() != null) {
			SrcFile srcfile = config.getSrcfile();
			System.out.println("src name : " + srcfile.getName());
			System.out.println("src fieldNum :" + srcfile.getFieldNum());
			System.out.println("src delimiter :" + srcfile.getDelimiter());
			System.out.println();
		}
		if (config.getDestfile() != null) {
			DestFile dest = config.getDestfile();
			System.out.println("dest name : " + dest.getName());
			System.out.println("dest fieldNum :" + dest.getFieldNum());
			System.out.println("dest delimiter :" + dest.getDelimiter());
			System.out.println("dest filterid :" + dest.getFilertId());
			System.out.println("dest Sepatate :" + dest.getSeparate());
			System.out.println("dest day :" + dest.getDay());
			System.out.println();
		}
		if (config.getOperation() != null) {
			Operation operation = config.getOperation();
			while (operation != null) {
				System.out.println();
				System.out.println("operation destid :"
						+ operation.getDestId());
				System.out.println("operation srcid :"
						+ operation.getSrcId());
				System.out.println("operation defaultValue :"
						+ operation.getDefaultValue());
				System.out.println("operation method :"
						+ operation.getMethod());
				System.out.println("operation map name:"
						+ operation.getMapName());
				System.out.println();
				operation = operation.getNextOperation();
			}
		}
		if (config.getFilter() != null) {
			Filter fr = config.getFilter();
			while (fr != null) {
				System.out
						.println("------------------------------------------");
				System.out.println(fr.getLogic());
				System.out.println(fr.getMethodid());
				System.out.println(fr.getColumnid());
				System.out.println(fr.getMatch_flag());
				System.out.println(fr.getMatch_logic());
				System.out.println(fr.getMatch_val());
				System.out.println(fr.getLogic_flag());
				System.out.println();
				System.out
						.println("------------------------------------------");
				fr = fr.getNextFilter();

			}
		}
		System.out.println("------------------------------------------");
	}

}
