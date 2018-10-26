package com.hotent.extension.model.bpm;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

public class TaskData {
	/**
	 * 任务ID
	 */
	private String taskId;
	/**
	 * 事项名称
	 */
	private String taskName;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 操作类型
	 */
	private Integer type;
	/**
	 * 是否已读 0 -未读 1- 已读
	 */
	private Integer hasRead;
	/**
	 * 事项对应的业务数据
	 */
	private Map<String,Object> data=new HashMap<String, Object>();

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Integer getHasRead() {
		return hasRead;
	}

	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}
	
	

}
