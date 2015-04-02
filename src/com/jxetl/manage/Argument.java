package com.jxetl.manage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;

import com.jxetl.util.HDFSUtil;

/**
 * 
 * @author ljy 获取参数配置信息
 */
@SuppressWarnings("serial")
public class Argument implements Serializable {
	public static String cityId = null;
	public static String dateStamp = null;
	public static String opTime = "2002-01-01";
	public static String taskNo = null;
	public static String scheduleNo = null;
	public static String sourceFile = null;
	public static String destFilePath = null;
	public static String sourceObject = null;
	public static String taskName = null;
	public static String mapPath = null;
	public static String configPath = null;

	/**
	 * 默认访问 hdfs 下 /wy/config/下文件
	 * 
	 * @param taskName
	 */
	public static void hdfsArg(String path) {

		
		try {
			FSDataInputStream fdis = null;
			fdis = HDFSUtil.fs.open(new Path(path
					+ "/config/argument.properties"));
			getArg(fdis);
			fdis.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		

	}

	public static void localArg(String taskName) throws IOException {
		String path = System.getProperty("user.dir") + "/config/" + taskName + ".properties";
		InputStream is = new FileInputStream(path);
		getArg(is);
		is.close();
	}

	public static void getArg(InputStream is) {

		Properties pro = new Properties();
		try {
			pro.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cityId = pro.getProperty("cityId");
		dateStamp = pro.getProperty("dateStamp");
		opTime = pro.getProperty("opTime");
		taskNo = pro.getProperty("taskNo");
		scheduleNo = pro.getProperty("scheduleNo");
		sourceFile = pro.getProperty("sourceFile");
		destFilePath = pro.getProperty("destFilePath");
		sourceObject = pro.getProperty("sourceObject");
		mapPath = pro.getProperty("mapPath");
		configPath = pro.getProperty("configPath");
		

	}

}
