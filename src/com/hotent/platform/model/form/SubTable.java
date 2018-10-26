package com.hotent.platform.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 子表数据
 * @author ray
 *
 */
public class SubTable{
	
	/**
	 * 表名
	 */
	private String tableName="";
	
	/**
	 * 主键名称。
	 */
	private String pkName="";
	
	/**
	 * 外键名称。
	 */
	private String fkName="";
	
	
	/**
	 * 子表数据。
	 */
	private List<Map<String, Object>> dataList=new ArrayList<Map<String,Object>>();
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	
	public void addRow(Map<String, Object> row){
		this.dataList.add(row);
	}
	
	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getFkName() {
		return fkName;
	}

	public void setFkName(String fkName) {
		this.fkName = fkName;
	}

	
	
	
}