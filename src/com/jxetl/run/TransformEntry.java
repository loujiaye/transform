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
	//e_transform�ĸ�Ŀ¼

	public static Logger log = Logger.getRootLogger();
	private static List<Path> files = null;
	
	/**
	 * 
	 * @param args
	 * 	����1��������
	 * 	����2��ʱ�� 2014-09-12
	 * 	����3���ӿ��ļ���
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		log.info("����ʼ����");
		String name = null;
		String time = null;
		String interfaceName = null;
		
		if(args.length < 3){
			log.error("��������3��");
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
		log.info("�ļ�׼�����");
				
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
		log.info("����ʼִ��" );
		System.out.println(job.waitForCompletion(true)? 0 : 1);
		log.info(args[0] + "����ִ�н���;ִ��ʱ�䣺" + ( (new Date().getTime() - begin)/1000) + "s");
	}
	
	/**
	 * ���Դ�ļ��������ļ��������ļ��ǲ��Ǵ��ڣ������������ϴ������Ŀ���ļ����Ƿ����
	 * ������ɾ��
	 * 
	 */
	public static void prepare(String srcName  , String resultPath , String name){
		//���Դ�ļ�
		
		

		/*if(!HDFSUtil.existFile(srcName)){
			log.info(name+ "����û��Դ�ļ������ϴ�");
			System.exit(2);
		} else{
			log.info(name + "����Դ�ļ�׼������"); 
		}*/

		//����Ŀ���ļ�·�������ļ���ɾ��
		
		if(HDFSUtil.existDir(resultPath)){		
			HDFSUtil.rm(resultPath);				
		}
		log.info(name+ "����Ŀ���ļ���׼������");
		
		/*//�����ļ�
		if(!HDFSUtil.existFile(rootPath  + "/config/argument.properties" ) ){
			HDFSUtil.copyFormLocal(localPath + taskName + ".properties", rootPath  + "/config/argument.properties");			
		}
		log.info(taskName + "��������ļ�׼������");*/
		
		//�����ļ�
		if(!HDFSUtil.existFile( "hdfs://bch/user/root/e_transform/config/" + name + ".file" ) ){
			HDFSUtil.copyFormLocal("/home/hdfs/jxetl/dispatch/e_transform/config/" + name + ".file", "/user/root/e_transform/config/" + name + ".file" );			
		}
		log.info(name + "���������ļ�׼������");
		
		//ӳ���
		if(!HDFSUtil.existFile( "hdfs://bch/user/root/e_transform/maptable/USYS_PARA_CLEAN_MAP" ) ){
			HDFSUtil.copyFormLocal("/home/hdfs/jxetl/dispatch/e_transform/maptable/USYS_PARA_CLEAN_MAP", "/user/root/e_transform/maptable/USYS_PARA_CLEAN_MAP");			
		}
		log.info("ӳ����ļ�׼������");

	}

	/**
	 * map ��
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
			
			//��ʼ����Ҫ����
			/*Argument.hdfsArg(path);*/
			Argument.opTime = conf.get("time");
			Argument.dateStamp = "1";
			
			//��ʼ��mapTable
			MapTable.hdfsMap();
			
			//��ʼ��task
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
		 * ��ʼ��
		 */ 
		public void reduce ( Text key, Iterable<Text> values, Context context ) throws IOException, InterruptedException
		{
			
			for(Text value : values){
				context.write(key,null );
			}			
		}		
	}

}
