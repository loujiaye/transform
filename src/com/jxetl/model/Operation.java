package com.jxetl.model;

import java.io.Serializable;

import com.jxetl.exception.MethodDealException;
import com.jxetl.manage.MethodFactory;
import com.jxetl.method.impl.Method999;
import com.jxetl.util.impl.MethodUtil;

/**
 * 
 * @author wy
 * @description 操作实体类
 */
@SuppressWarnings("serial")
public class Operation implements Serializable{
	private String destId;
	private String srcId;
	private String defaultValue;
	private String method;
	private String mapName;
	private Operation nextOperation = null;
	
	public String getDestId() {
		return destId;
	}
	public void setDestId(String destId) {
		this.destId = destId;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMapName() {
		return mapName;
	}
	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
	public Operation getNextOperation() {
		return nextOperation;
	}
	public void setNextOperation(Operation nextOperation) {
		this.nextOperation = nextOperation;
	}
	
	public boolean hasNext(){
		if(nextOperation != null){
			return true;
		} else {
			return false;
		}
	}
	
	public void handle(String[] src , String[] dest){
		//重组src
		String srcStr = "";
		
		//999函数特殊处理
		if(this.method.equals("999")){
			Method999 m999 = new Method999();
			srcStr = m999.invoke(srcId, src, dest);
		} else {
			if(!srcId.equalsIgnoreCase("null")){
				for(String tmp : srcId.split(",")){
					try{
						srcStr += src[Integer.parseInt(tmp) -1] + ",";
					} catch (NumberFormatException e){
						e.printStackTrace();
					}	
				}
				
				srcStr = srcStr.substring(0, srcStr.length() - 1);
			} else {
				srcStr = srcId;
			}
			
			
			//执行method
			for(String methodTmp :method.split("\\|")){
				String[] methodArr = new String[2];
				new MethodUtil().prase(methodArr, methodTmp);
				
				try {
					srcStr= MethodFactory.getById(methodArr[0] ).invoke(srcStr, defaultValue, methodArr[1], mapName);
				} catch (MethodDealException e) {
					e.printStackTrace();
				} 
			}
		}
		
		
		
		try{
			dest[Integer.parseInt(destId) - 1] = srcStr;
		} catch (NumberFormatException e){
			e.printStackTrace();
		}
		
		
		//下一个操作处理
		if(nextOperation != null){
			nextOperation.handle(src, dest);
		}
		
	}
	
	
	
	
}
