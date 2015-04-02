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
 *  ��֤����
 *  
 */
public class VerifyEntry {
	public static Logger log = Logger.getRootLogger();

	/**
	 * 
	 * @param args
	 * 	����1��������
	 * 	����2��ʱ�� 2014-09-12
	 * 	����3��ʱ���  [0 1 2]
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		log.info("����ʼ����");
		String name = null;
		String time = null;
		String durtion = null;
		
		if(args.length < 3){
			log.error("��������3��");
			System.exit(1);
		} else {
			name = args[0];
			time = args[1];
			durtion = args[2];
		}
		String baseName =  "/user/root/e_tranform/" + name + "/" + time.substring(0 , 4)  + time.substring(5 , 7)+ "/" 
				+ time.substring(8) + "/" + durtion ;
		prepare(baseName , name);
		log.info("�ļ�׼�����");
				
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
		log.info("����ʼִ��" );
		System.out.println(job.waitForCompletion(true)? 0 : 1);
		log.info(args[0] + "����ִ�н���;ִ��ʱ�䣺" + ( (new Date().getTime() - begin)/1000) + "s");
	}
	
	/**
	 * ���Դ�ļ��������ļ��������ļ��ǲ��Ǵ��ڣ������������ϴ������Ŀ���ļ����Ƿ����
	 * ������ɾ��
	 * 
	 */
	public static void prepare(String baseName  , String name){
		
		//����Ŀ���ļ�·�������ļ���ɾ��
		String destPath = baseName + "/dest/";
		if(HDFSUtil.existDir(destPath)){		
			HDFSUtil.rm(destPath);				
		}
		log.info(name+ "����Ŀ���ļ���׼������");
		

		
		//�����ļ�
		if(!HDFSUtil.existFile( "/user/root/e_tranform/config/" + name + ".file" ) ){
			HDFSUtil.copyFormLocal("/home/hadoop/jxetl/dispatch/e_transform/config/" + name + ".file", "/user/root/e_tranform/config/" + name + ".file" );			
		}
		log.info(name + "���������ļ�׼������");



	}

	/**
	 * map ��
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
		
			//��ȡ�ָ���
			Configuration conf = context.getConfiguration();			
			String taskName = conf.get("taskname");	
			task = MyTask.hdfsGetTask(taskName);
			this.delimiter = task.getDestfile().getDelimiter();
			conf.set("delimiter", this.delimiter);
			
			//�鿴�ļ����Լ��������ɵĽ�������ǳ�ȡ�Ľ��
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
			//����key
			String tmp  = value.toString();
			//key ���ݲ�ͬ�ֶ����
			context.write(new Text(tmp), new Text(type + "," + key.toString()));
		}	
		
		
	}
	
	public static class Reduce extends Reducer<Text, Text, NullWritable, Text> 
	{
	

		/**  
		 * ��ʼ��
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
				context.write(null, new Text(key.toString() + "   .6����кţ�" + remoteLine + "  hdfs����кţ�"  + jxetlLine));
			}			
		}		
	}
}
