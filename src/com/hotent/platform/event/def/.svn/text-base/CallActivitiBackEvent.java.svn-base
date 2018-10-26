package com.hotent.platform.event.def;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.context.ApplicationEvent;

import com.hotent.platform.service.bpm.cmd.CallActivityRejectCmd;

/**
 * 子流程驳回时定义的事件。
 * {@linkplain CallActivityRejectCmd 子流程驳回命令。}
 * @author ray
 *
 */
public class CallActivitiBackEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -908644350863377514L;
	
	private String taskId="";
	

	public CallActivitiBackEvent(List<TaskEntity> list) {
		super(list);
	}
	
	public void setCurTaskId(String taskId){
		this.taskId=taskId;
	}
	
	public String getCurTaskId(){
		return this.taskId;
	}
	

}
