package com.jxetl.run;



import java.io.IOException;
import java.util.Date;
import java.util.List;





import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.jxetl.exception.SrcFileException;
import com.jxetl.manage.Argument;
import com.jxetl.manage.MapTable;
import com.jxetl.model.MyTask;
import com.jxetl.model.Operation;
import com.jxetl.util.HDFSUtil;

public class TransformEntry {
	//e_transform的根目录

	public static Logger log = Logger.getRootLogger();
	private static List<Path> files = null;
	
	/**
	 * 
	 * @param args
	 * 	参数1：任务名
	 * 	参数2：时间 2014-09-12
	 * 	参数3：接口文件名
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		log.info("程序开始启动");
		String name = null;
		String time = null;
		String interfaceName = null;
		
		if(args.length < 3){
			log.error("参数不够3个");
			System.exit(1);
		} else {
			name = args[0];
			time = args[1];
			interfaceName = args[2];
		}
		String srcName =  "hdfs://bch/user/root/e_transform/" +  interfaceName + "/" + time.substring(0 , 4)  + time.substring(5 , 7)+ "/" 
				+ time.substring(8) + "/extract/*" ;
		String resultPath = "hdfs://bch/user/root/e_transform/" + name + "/" + time.substring(0 , 4)  + time.substring(5 , 7)+ "/" 
				+ time.substring(8) + "/transform/" ;
		prepare(srcName , resultPath , name);
		log.info("文件准备完成");
				
		Configuration conf = new Configuration();		
		conf.set("taskname", name);
		conf.set("time" , time);
		Job job =new Job(conf , name+"_" + time );
		
		job.setJarByClass(TransformEntry.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		FileInputFormat.addInputPath(job , new Path(srcName));
		
		
		FileOutputFormat.setOutputPath(job, new Path(resultPath));
		long begin = new Date().getTime();
		log.info("任务开始执行" );
		System.out.println(job.waitForCompletion(true)? 0 : 1);
		log.info(args[0] + "任务执行结束;执行时间：" + ( (new Date().getTime() - begin)/1000) + "s");
	}
	
	/**
	 * 检查源文件，配置文件，参数文件是不是存在，若不存在则上传。检查目标文件夹是否存在
	 * 存在则删除
	 * 
	 */
	public static void prepare(String srcName  , String resultPath , String name){
		//检查源文件
		
		

		/*if(!HDFSUtil.existFile(srcName)){
			log.info(name+ "任务没有源文件，请上传");
			System.exit(2);
		} else{
			log.info(name + "任务源文件准备就绪"); 
		}*/

		//建立目标文件路径，有文件就删除
		
		if(HDFSUtil.existDir(resultPath)){		
			HDFSUtil.rm(resultPath);				
		}
		log.info(name+ "任务目标文件夹准备就绪");
		
		/*//参数文件
		if(!HDFSUtil.existFile(rootPath  + "/config/argument.properties" ) ){
			HDFSUtil.copyFormLocal(localPath + taskName + ".properties", rootPath  + "/config/argument.properties");			
		}
		log.info(taskName + "任务参数文件准备就绪");*/
		
		//配置文件
		if(!HDFSUtil.existFile( "hdfs://bch/user/root/e_transform/config/" + name + ".file" ) ){
			HDFSUtil.copyFormLocal("/home/hdfs/jxetl/dispatch/e_transform/config/" + name + ".file", "/user/root/e_transform/config/" + name + ".file" );			
		}
		log.info(name + "任务配置文件准备就绪");
		
		//映射表
		if(!HDFSUtil.existFile( "hdfs://bch/user/root/e_transform/maptable/USYS_PARA_CLEAN_MAP" ) ){
			HDFSUtil.copyFormLocal("/home/hdfs/jxetl/dispatch/e_transform/maptable/USYS_PARA_CLEAN_MAP", "/user/root/e_transform/maptable/USYS_PARA_CLEAN_MAP");			
		}
		log.info("映射表文件准备就绪");

	}

	/**
	 * map 类
	 * @author wy
	 *
	 */
	public static class Map extends Mapper<LongWritable , Text ,Text ,Text>{
		
		private  MyTask task = null;
		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
		
			
			Configuration conf = context.getConfiguration();
			
			String taskName = conf.get("taskname");	
			
			//初始化必要参数
			/*Argument.hdfsArg(path);*/
			Argument.opTime = conf.get("time");
			Argument.dateStamp = "1";
			
			//初始化mapTable
			MapTable.hdfsMap();
			
			//初始化task
			task = MyTask.hdfsGetTask(taskName);
	
			super.setup(context);
		}

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			line = task.convert(line );
			if(line != null){
				context.write(new Text(line), new Text(""));
			}
			
			
		}		
	}
	
	public static class Reduce extends Reducer<Text, Text, Text, NullWritable> 
	{
	

		/**  
		 * 初始化
		 */ 
		public void reduce ( Text key, Iterable<Text> values, Context context ) throws IOException, InterruptedException
		{
			
			for(Text value : values){
				context.write(key,null );
			}			
		}		
	}

}
