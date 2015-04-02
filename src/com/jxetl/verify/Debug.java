package com.jxetl.verify;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Debug {

	public static void main(String[] args) throws Exception {
		findLine();
		
	}
	
	public static void findLine() throws Exception{
		String name = System.getProperty("user.dir");
		System.out.println(name);
		BufferedReader br = new BufferedReader(new FileReader(name + "/log/e_transform.log"));
		String tmp = "";
		while((tmp = br.readLine()) != null){
			if(tmp.indexOf("13907016478") > 0){
				System.out.println(tmp);
				System.out.println(br.readLine());
				System.out.println(br.readLine());
				System.out.println(br.readLine());
				System.out.println(br.readLine());
				System.out.println(br.readLine());
			}
		}
	}
	
	

}
