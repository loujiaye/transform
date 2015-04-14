package com.jxetl.model;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;
import com.jxetl.method.FilterMethod1;
import com.jxetl.method.FilterMethod2;
import com.jxetl.util.impl.MethodUtil;

/**
 * 
 * @author wy ���˹���ʵ���࣬������˵��߼�
 */
@SuppressWarnings("serial")
public class Filter implements Serializable {
	private String logic;
	private String methodid;
	private String columnid;
	private String match_flag;
	private String match_logic;
	private String match_val;
	private String logic_flag;
	
	public static Logger log = Logger.getRootLogger();
	
	private Filter nextFilter = null;

	public String getLogic() {
		return logic;
	}

	public void setLogic(String logic) {
		this.logic = logic;
	}

	public String getMethodid() {
		return methodid;
	}

	public void setMethodid(String methodid) {
		this.methodid = methodid;
	}

	public String getColumnid() {
		return columnid;
	}

	public void setColumnid(String columnid) {
		this.columnid = columnid;
	}

	public String getMatch_flag() {
		return match_flag;
	}

	public void setMatch_flag(String match_flag) {
		this.match_flag = match_flag;
	}

	public String getMatch_logic() {
		return match_logic;
	}

	public void setMatch_logic(String match_logic) {
		this.match_logic = match_logic;
	}

	public String getMatch_val() {
		return match_val;
	}

	public void setMatch_val(String match_val) {
		this.match_val = match_val;
	}

	public String getLogic_flag() {
		return logic_flag;
	}

	public void setLogic_flag(String logic_flag) {
		this.logic_flag = logic_flag;
	}

	public Filter getNextFilter() {
		return nextFilter;
	}

	public void setNextFilter(Filter nextFilter) {
		this.nextFilter = nextFilter;
	}

	/**
	 * 
	 * @return true ��ʾ���й�����û�д��� false��ʾû��
	 */
	public boolean hasNext() {
		if (nextFilter != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ����Դ�ļ������ݹ��˹���ж��Ƿ�Ҫʹ��һ������
	 * 
	 * @param fields
	 *            Դ�ļ��ָ����ַ�������
	 * @return true ��ʾ���� false��ʾ������
	 */
	public boolean percolate(String[] fields) {
		// {& 2|16(1,3)|14(0) 5,26 1 0 opp_dn3 0}

		// �Ƿ���˱�־
		boolean percolateString = false;

		// Դ�ֶδ���
		String str = "";
		for (String tmp : columnid.split(",")) {
			try {
				str += fields[Integer.parseInt(tmp) - 1] + ",";
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		str = str.substring(0, str.length() - 1);
	
		
		// ִ��method
		// �����ļ��У����˹���14�������⴦������ɾ��
		for (String methodTmp : methodid.split("\\|")) {
			String[] methodArr = new String[2];
			new MethodUtil().prase(methodArr, methodTmp);

			try {
				str = MethodFactory.getById(methodArr[0]).invoke(str, "null",
						methodArr[1], "null");
			} catch (MethodDealException e) {
				e.printStackTrace();
			}
		}

		if (match_flag.equals("0")) {
			percolateString = FilterMethod2.invoke(str, match_val, match_logic);
		} else if (match_flag.equals("1")) {			
			//���˵��������
			//6:70703367070336 21:799 ���ʺ�
			/*if(this.match_val.equals( "map_call_xlt" )|this.match_val.equals( "map_tt_hd_v15" )
					||this.match_val.equals( "map_wt_hd_v15" )){
				if(str.length() >14){
					return true;
				}
			}*/
			percolateString = FilterMethod1.invoke(str, match_val);
		}
/*System.out.println(str + " " + percolateString + " " + logic_flag)	;*/
		// logic_val ����
		if (logic_flag.equals("0")) {
			percolateString = !percolateString;
		}
/*System.out.println(str + " " + percolateString)	;*/
		return percolateString;

	}

}
