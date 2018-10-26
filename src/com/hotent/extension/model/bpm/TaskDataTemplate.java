package com.hotent.extension.model.bpm;

import java.util.ArrayList;
import java.util.List;

import com.hotent.platform.model.form.DialogField;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TaskDataTemplate {
	// 是否分页
	protected Integer needpage = 1;
	// 分页常量
	public static final String Page = "p";
	// 分页大小常量。
	public static final String PageSize = "z";
	/**
	 * 显示字段列表
	 */
	private List<DialogField> displayField=new ArrayList<DialogField>();
	
	/**
	 * 查询条件字段列表
	 */
	private List<DialogField> conditionField=new ArrayList<DialogField>();
	
	/**
	 * 排序字段列表
	 */
	private List<DialogField> sortField=new ArrayList<DialogField>();
	
	/**
	 * 事项业务数据列表
	 */
	private List<TaskData> taskDataList=new ArrayList<TaskData>();

	public List<DialogField> getDisplayField() {
		return displayField;
	}

	public void setDisplayField(List<DialogField> displayField) {
		this.displayField = displayField;
	}

	public List<DialogField> getConditionField() {
		return conditionField;
	}

	public void setConditionField(List<DialogField> conditionField) {
		this.conditionField = conditionField;
	}

	public List<DialogField> getSortField() {
		return sortField;
	}

	public void setSortField(List<DialogField> sortField) {
		this.sortField = sortField;
	}

	public List<TaskData> getTaskDataList() {
		return taskDataList;
	}

	public void setTaskDataList(List<TaskData> taskDataList) {
		this.taskDataList = taskDataList;
	}
	
	
	

}
