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
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

import com.jxetl.manage.Argument;
import com.jxetl.manage.MapTable;
import com.jxetl.model.MyTask;
import com.jxetl.util.HDFSUtil;

/**
 * 
 * @author wangyu
 *  验证程序
 *  
 */
public class VerifyEntry {
	public static Logger log = Logger.getRootLogger();

	/**
	 * 
	 * @param args
	 * 	参数1：任务名
	 * 	参数2：时间 2014-09-12
	 * 	参数3：时间段  [0 1 2]
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		log.info("程序开始启动");
		String name = null;
		String time = null;
		String durtion = null;
		
		if(args.length < 3){
			log.error("参数不够3个");
			System.exit(1);
		} else {
			name = args[0];
			time = args[1];
			durtion = args[2];
		}
		String baseName =  "/user/root/e_tranform/" + name + "/" + time.substring(0 , 4)  + time.substring(5 , 7)+ "/" 
				+ time.substring(8) + "/" + durtion ;
		prepare(baseName , name);
		log.info("文件准备完成");
				
		Configuration conf = new Configuration();		
		conf.set("taskname", name);
		conf.set("time" , time);
		Job job =new Job(conf , name+"_" + time + "_" + durtion + "_vertify");
		
		job.setJarByClass(VerifyEntry.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		
		FileInputFormat.addInputPath(job , new Path(baseName + "/transform/part-r-00000"));
		FileInputFormat.addInputPath(job , new Path(baseName + "/result/" + name + ".dat"));
		
		
		FileOutputFormat.setOutputPath(job, new Path(baseName+ "/dest/"));
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
	public static void prepare(String baseName  , String name){
		
		//建立目标文件路径，有文件就删除
		String destPath = baseName + "/dest/";
		if(HDFSUtil.existDir(destPath)){		
			HDFSUtil.rm(destPath);				
		}
		log.info(name+ "任务目标文件夹准备就绪");
		

		
		//配置文件
		if(!HDFSUtil.existFile( "/user/root/e_tranform/config/" + name + ".file" ) ){
			HDFSUtil.copyFormLocal("/home/hadoop/jxetl/dispatch/e_transform/config/" + name + ".file", "/user/root/e_tranform/config/" + name + ".file" );			
		}
		log.info(name + "任务配置文件准备就绪");



	}

	/**
	 * map 类
	 * @author wy
	 *
	 */
	public static class Map extends Mapper<LongWritable , Text ,Text ,Text>{
		
		private  MyTask task = null;
		private String type = null;
		private String delimiter  = null;
		
		@Override
		protected void setup(Context context) throws IOException,
				InterruptedException {
		
			//获取分隔符
			Configuration conf = context.getConfiguration();			
			String taskName = conf.get("taskname");	
			task = MyTask.hdfsGetTask(taskName);
			this.delimiter = task.getDestfile().getDelimiter();
			conf.set("delimiter", this.delimiter);
			
			//查看文件是自己程序生成的结果，还是抽取的结果
			FileSplit fs =(FileSplit) context.getInputSplit();
			String name = fs.getPath().getName();
			if(name.startsWith("part")){
				type = "jxetl";
			} else {
				type =  "asis";
			}
			
			super.setup(context);
		}

		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {		
			//生成key
			String tmp  = value.toString();
			//key 根据不同字段组合
			context.write(new Text(tmp), new Text(type + "," + key.toString()));
		}	
		
		
	}
	
	public static class Reduce extends Reducer<Text, Text, NullWritable, Text> 
	{
	

		/**  
		 * 初始化
		 */ 
		
		

		public void reduce ( Text key, Iterable<Text> values, Context context ) throws IOException, InterruptedException
		{
			
			int jxetl = 0;
			int remote = 0;
			String jxetlLine = "";
			String remoteLine  ="";
			
			for ( Text value : values )
			{
				String tmp = value.toString();
				String arr[] = tmp.split(",");
				
				if(arr[0].equals("jxetl")){
					jxetl ++;
					jxetlLine +=arr[1] + " ";
				} else {
					remote++;
					remoteLine += arr[1] + " ";
				}
			}
			
			if(jxetl != remote){
				context.write(null, new Text(key.toString() + "   .6结果行号：" + remoteLine + "  hdfs结果行号："  + jxetlLine));
			}			
		}		
	}
}
