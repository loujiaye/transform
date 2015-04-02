package com.jxetl.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.jxetl.manage.Argument;



public class TmpVerify {

	public static void main(String[] args) throws IOException {

		LocalEntry le = new LocalEntry();
		le.prepare("cdr_in_call_dm_1701");
		Argument.opTime = "2014-11-11";
		Argument.dateStamp = "1";
		
		le.br = new BufferedReader(new FileReader("/home/hadoop/transform/dest/A060092014111120301.AVL"));
		le.bw = new BufferedWriter(new FileWriter("/home/hadoop/transform/dest/dest.txt"));
		le.br2 = new BufferedReader(new FileReader( "/home/hadoop/transform/dest/cdr_in_call_dm_1701.A060092014111120301.AVL.002956245P"));
		
		le.debug();
	}

}
