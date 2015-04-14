package com.jxetl.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.jxetl.manage.Argument;
import com.jxetl.manage.MapTable;
import com.jxetl.model.DestFile;
import com.jxetl.model.Filter;
import com.jxetl.model.MyTask;
import com.jxetl.model.Operation;
import com.jxetl.model.SrcFile;
import com.jxetl.verify.Verify;
import com.jxetl.verify.Verify1402;

public class LocalEntry {
	
	public static Logger log = Logger.getRootLogger();
	
	BufferedReader br = null;
	BufferedReader br2 = null;
	BufferedWriter bw = null;
	BufferedWriter bw2= null;
	

	MyTask task = null;
	
	/**
	 * 
	 * @param args
	 *      ������   0   ������
	 *      	         1   ʱ��
	 *      	         2  ·��  �� / ��β
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 3 ) {
			System.out.println("��������ȷ");
			System.exit(1);
		} 
		LocalEntry le = new LocalEntry();
		le.prepare(args[0]);
		Argument.opTime = args[1];
		Argument.dateStamp = "1";
		//le.printConfig(le.task);
		
		//׼��ӳ���
		//le.prepare(args[0]);
		le.begin(args[2] , args[0]);
	}
	
	/**
	 * ����·����ȡԴ�ļ�������ļ� ������Ŀ���ļ�
	 * @param path
	 * @param taskName
	 * @throws IOException 
	 */
	public void begin(String path  , final String taskName) throws IOException{
		//ȡ���ļ��б�
		File filePath = new File(path);
		File[] files = filePath.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir,
					String name) {
				if(name.startsWith(taskName)){
					return true;
				}
				return false;
			}
			
		});
		//ѭ��
		String srcName = "";
		String resultName = "";
		String destName = "";
		String errorName = "";
		
		if(files == null){
			System.exit(1);
		}
		
		for(File file :files){
			resultName = file.getName();
			srcName = resultName.split("\\.")[1] + ".AVL";
			destName = "dest_" +resultName  ;
			errorName = "error_" + resultName  ;
			
			
			
			br = new BufferedReader(new FileReader(path.replace("result", "src")  + srcName ));
			bw = new BufferedWriter(new FileWriter(path.replace("result", "dest")  + destName));
			br2 = new BufferedReader(new FileReader( path+ resultName));
			
			if(!debug()){
			//if(true){
				br.close();
				br = null;
				br = new BufferedReader(new FileReader(path.replace("result", "src")  + srcName ));
				bw2 = new BufferedWriter(new FileWriter(path.replace("result", "dest")  + errorName));			
				deal();
				bw2.close();
			}
			close();
			
		}
		       //�����ļ�
		     //ִ��
		    //�ر�
	}

	/**
	 * ӳ������������ļ�
	 * ���㣬Ӧ���������ļ���ʽָ���ļ�λ��
	 * @param taskName
	 * @throws IOException
	 */
	public void prepare(String taskName) throws IOException  {
		
		MapTable.localMap();
		task = MyTask.localGetTask(taskName);
	}


	/**
	 * ���ɽ���ļ�����Ŀ���ļ���
	 * @throws IOException
	 */
	public void deal() throws IOException {
		/*String str = "0|13907016478|460007016204557|1|5|07012192110|20140625083111|17||8613442921|79a4|d6f8,1100|0|||4000|00|50001|701|701|9|701|701|0|0|0|0|0|0|0||0|0|0|0|||400|0|0|0||860454000055470|1|0|0|810055|20140625082844|0|<11>|101000048428|81000055<40100004:81000055:61000055,>|0|0|||94243ea13c||DR_GSM_701_20140625";
		task.convert(str);*/
		
		String tmp = null;
		int line = 0;
		while ((tmp = br.readLine()) != null) {	
			//log.debug(tmp);
			tmp = task.convert(tmp);	
			if (tmp != null) {
				line++;
				bw2.write(tmp);
				bw2.newLine();
			}
			//log.debug("-------------------------");
		}
		//System.out.println(line);
	}

	/**
	 * ��֤Դ�ļ������ļ���ͬ������dest.txt��
	 * @throws IOException
	 */
	public boolean debug() throws IOException {
		Verify ver = new Verify1402(task.getDestfile().getFieldNum());
		String src = null;
		String dest = null;
		String result = null;
		String resultArr[] = new String[task.getDestfile().getFieldNum()];
		String destArr[] = null;
		
		int line = 0;
		
		boolean error = false;
	
		while ((src = br.readLine()) != null) {							
			//Դ�ļ�����
			dest = task.convert(src);			
			if(dest == null){
				continue;
			}
//System.out.println("dest" + dest);		
			line++;			
			
			//Ŀ�����鸳ֵ
			destArr = task.getDestfile().fields;
			Arrays.fill(resultArr, "");
			int i = 0;
			result = br2.readLine();	
//System.out.println("result" + result);
			if(result == null){
				bw.write("line is not suit");
				bw.newLine();
				bw.write("the result:FAILD");
				bw.newLine();
				bw.close();
				return false;
			}
			for (String tmp : result.split(task.getDestfile().getDelimiter())) {
				resultArr[i] = tmp;
				i++;			
			}		
			
			if (!ver.check(destArr, resultArr , bw)) {
				error = true;
				
				bw.write(String.valueOf(line));
				bw.newLine();
				int k = 0;
				for(String tmp : task.getSrcfile().fields){
					bw.write(k + 1 + " :" + tmp + "  ");
					k++;
				}
				bw.newLine();
				bw.write("-------------------------------");
				bw.newLine();
				bw.flush();
			}
			
		}
		
		//��ȷ����ļ������ǳ���ת���Ķ�
		result = br2.readLine();	
		if(result != null){
			bw.write("line is not suit");
			bw.newLine();
			bw.write("the result:FAILD");
			bw.newLine();
			bw.close();
			return false;
		}
		
		//���ȫ����ȷ
		if(!error){		
			bw.write("the result:SUCCESSED");
			bw.newLine();
		} else {
			bw.write("the result:FAILD");
			bw.newLine();
		}
		
		return true;
	}

	/**
	 * ���е���
	 * @param str
	 */
	public void single (String str){
		str = task.convert(str , true);
		if(str != null){
			System.out.println(str);
		}
	}

	/**
	 * �ر��ļ���
	 * @throws IOException
	 */
	public void close() throws IOException {
		bw.close();
		br.close();
		if (br2 != null) {
			br2.close();
		}
	}

	/**
	 * ��ӡ�����ļ������ڼ�������ļ�����ȷ�� 
	 * @param config
	 */
	public void printConfig(MyTask config) {
		System.out.println("�����ǣ�" + config.getName());
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
				System.out
						.println("operation destid :" + operation.getDestId());
				System.out.println("operation srcid :" + operation.getSrcId());
				System.out.println("operation defaultValue :"
						+ operation.getDefaultValue());
				System.out
						.println("operation method :" + operation.getMethod());
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
