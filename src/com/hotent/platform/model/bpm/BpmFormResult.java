package com.hotent.platform.model.bpm;

/**
 * 这个类用于检查流程表单定义。
 * <pre>
 * 	result:
 * 	0:有表
 *  1:流程定义对应了多张表。
 *  2:没有对应的表
 * </pre>
 * @author ray
 *
 */
public class  BpmFormResult{
	//表名
	private String tableName="";
	//0，有表的情况，1，对应多张表，2，没有对应表。
	private int result=0;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}