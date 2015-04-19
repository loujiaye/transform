package com.jxetl.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;







import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;

import com.jxetl.exception.SrcFileException;
import com.jxetl.util.HDFSUtil;


/**
 * 
 * @author wy
 * @description ����װ��Ŀ���ļ���Դ�ļ������ļ��Ĳ����� ע�⣺���ֶ�����ʱҪ��������������
 */
@SuppressWarnings("serial")
public class MyTask implements Serializable{

	private String name;
	private SrcFile srcfile;
	private DestFile destfile;
	private Operation operation ;
	private Filter filter ;
	// ���������Լ��
	private int complete = 0;
	
	private boolean debug = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SrcFile getSrcfile() {
		return srcfile;
	}

	public void setSrcfile(SrcFile srcfile) {
		complete |= 1;
		this.srcfile = srcfile;
	}

	public DestFile getDestfile() {
		return destfile;
	}

	public void setDestfile(DestFile destfile) {
		complete |= 2;
		this.destfile = destfile;
	}

   

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		complete |= 4;
		this.operation = operation;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		complete |= 8;
		this.filter = filter;
	}

	// ���ֵҪ�����ֶ���ʱ��
	public  boolean isComplete() {
		if (complete == 15) {
			return true;
		}
		return false;
	}
	
	/**
	 * ͨ��hdfs��ȡtask�����ļ�
	 * @param path
	 * @return
	 */
	public static  MyTask hdfsGetTask(String name) {	
		FSDataInputStream fdis = null;
		try {
			 fdis = HDFSUtil.fs.open(new Path("hdfs://bch/user/root/e_transform/config/" + name + ".file"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getTask(fdis);
	}
	
	/**
	 * ���ػ�ȡ�����ļ�
	 * @param taskName
	 * @return
	 */
	public static MyTask localGetTask(String taskName){
		String name =   "/home/hdfs/jxetl/dispatch/e_transform/config/" + taskName + ".file";
		//String name =   "/home/ljy/workspace/transform1/config/" + taskName + ".file";
		InputStream is = null;
		try {
			is = new FileInputStream(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getTask(is);
		
	}
	
	/**
	 * �����л������ļ�
	 * @param is
	 * @return
	 */
	public static MyTask getTask(InputStream is){
		//�����л�����
				MyTask task = null;
				try {
					
					
					ObjectInputStream ois = new ObjectInputStream(is);
					task = (MyTask) ois.readObject();	
					
					if(ois != null){
						ois.close();
					}
				} catch (FileNotFoundException e) {
					System.out.println("����δ����");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("��д����");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}	
				return task;
	}
	
	
	public  String convert(String str ) {
		
		try {
			if(str != ""){
				getSrcfile().separate(str);
				if(debug){
					int i = 0;
					for(String tmp :getSrcfile().fields){
						System.out.println(++i  + "  " + tmp);
					}
				}
			}		
			
			if(filter != null && needFilter()){	
				
				return null;
			}	
			
			
			getOperation().handle(getSrcfile().fields, getDestfile().fields);
			
			if(debug){
				int i = 0;
				for(String tmp :getDestfile().fields){
					System.out.println(++i  + "  " + tmp);
				}
			}
			
			return getDestfile().getLine();
		} catch (SrcFileException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public String convert(String str , boolean debug){
		this.debug = debug;
		return convert(str);
	}
	
	private boolean needFilter(){
		boolean flag = false;
		boolean orFlag = false;
		boolean orBool = true;
		Filter filter2 = filter;
		
		while(filter2 != null){
			flag = filter2.percolate(getSrcfile().fields);
	
			if(filter2.getLogic().equals("&")){
				
				if(flag){
					return true;
				}		
			} else {
				orFlag = true;
				if(!flag){
					orBool = false;
				}
			}
			
			filter2 = filter2.getNextFilter();

		}
	
		if(orFlag && orBool){
			return true;
		} else {
			return false;
		}
		
		
	}
	
	
	
}
