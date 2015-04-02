package com.jxetl.verify;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;


/**
 * 
 * @author wy
 *  ��֤��
 *
 */
public abstract class Verify {
	int length = 0;
	int[] verifyArr;
	
	/**
	 * 
	 * @param length  �ֶθ���
	 */
	public Verify(int length){
		this.length = length;
		verifyStrategy();
		
	}
	/**
	 * ���������е��ֶ��������֤
	 * @param field
	 */
	public void except(int[] field){
		verifyArr = new int[length - field.length];
		int j = 0;
		for(int i = 0 ; i < length ; i++){
			if( Arrays.binarySearch(field, i) < 0){
				verifyArr[j] = i;
				j++;
			}		
		}
	}
	
	/**
	 * �����ֶζ���֤
	 */
	public void all(){
		verifyArr = new int[length];
		for(int i = 0 ; i < length ; i++){
			verifyArr[i] = i;
		}
		
	}
	
	/**
	 * ֻ��֤�����е��ֶ�
	 * @param field
	 */
	public void include(int[] field){
		verifyArr = field;
	}
	
	/**
	 * ���󷽷�����̳������֤����
	 * @param dest ���ɽ���ļ�����
	 * @param src  ԭ����ļ�����
	 * @return �Ƿ�ͨ��
	 */
	public abstract void verifyStrategy();
	
	public boolean check(	String[] dest , String[] result ){
		boolean verify = true;
		int tmp = 0;
		for(int i = 0 ; i < this.verifyArr.length ; i++){
			tmp = verifyArr[i];
			if(! dest[tmp].equals(result[tmp])){
				System.out.println( tmp + 1 + "|" +  dest[tmp] + "|" + result[tmp]);
				verify = false;
			}
		}
		
		return verify;
	}
	
	public boolean check(	String[] dest , String[] result  , BufferedWriter bw) throws IOException{
		boolean verify = true;
		int tmp = 0;
		for(int i = 0 ; i < this.verifyArr.length ; i++){
			tmp = verifyArr[i];
			
			/*for(int j = 0 ; j < dest.length ; j++){
				System.out.println(j+ 1 + " " + dest[j]);
			}*/
			if(! dest[tmp].equals(result[tmp])){
				bw.write( tmp + 1 + "|" +  dest[tmp] + "|" + result[tmp]);
				bw.newLine();
				verify = false;
			}
		}
		
		return verify;
	}
	
	
}
