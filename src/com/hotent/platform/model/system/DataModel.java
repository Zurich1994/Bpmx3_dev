package com.hotent.platform.model.system;

import java.util.List;
import java.util.Map;

public class DataModel{
	//是否列表
	private boolean isList=true;
    //单条结果
	private Map returnObj;
    //多条结果
	private List<Map> returnList;
    //是否返回了数据
	private boolean hasData=true;
    //没有返回数据的原因
    private String errorMessage="";
	public Map getReturnObj() {
		return returnObj;
	}
	public void setReturnObj(Map returnObj) {
		this.returnObj = returnObj;
	}
	public List<Map> getReturnList() {
		return returnList;
	}
	public void setReturnList(List<Map> returnList) {
		this.returnList = returnList;
	}
	public boolean isHasData() {
		return hasData;
	}
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean getIsList() {
		return isList;
	}
	public void setIsList(boolean isList) {
		this.isList = isList;
	}

}
