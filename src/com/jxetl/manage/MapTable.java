package com.jxetl.manage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;




import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;

import com.jxetl.util.HDFSUtil;

/**
 * 
 * @author wy
 * 处理map映射表逻辑
 */
public class MapTable {
	public static Map<String, Map<String, String>> maps = new HashMap<String, Map<String, String>>();
	
	
	/**
	 * 对map表解析
	 * 
	 * @param br
	 */
	public static void hdfsMap() {
		try {
			FSDataInputStream fdis =HDFSUtil.fs.open(new Path("/user/root/e_transform/maptable/USYS_PARA_CLEAN_MAP"));
			setMap(fdis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 本地初始化maptable入口
	 * @throws IOException
	 */
	public static void localMap() throws IOException{
		String path ="/home/hdfs/jxetl/dispatch/e_transform/maptable/USYS_PARA_CLEAN_MAP";
		InputStream is = new FileInputStream(path);
		setMap(is);
		is.close();
	}
	
	/**
	 * 设置map
	 * @param is
	 */
	public static void setMap(InputStream is){
		BufferedReader br = null;
		
		try {
			
			
			br = new BufferedReader(new InputStreamReader(is));
			String str;
			String[] array;
			Map<String, String> map = null;
			
			while ((str = br.readLine()) != null) {
				array = str.split("\\s+");
				
				if (maps.containsKey(array[0])) {
					map = maps.get(array[0]);
				} else {
					map = new HashMap<String, String>();
					maps.put(array[0], map);
				}
				if(array.length == 3){
					map.put(array[1], array[2]);
				} else if(array.length == 2){
					map.put(array[1], null);
				}
				
	
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String ,String > getMapByName(String name){	
		if(maps.containsKey(name)){
			return maps.get(name);
		} else {
			return null;
		}
	}
}
