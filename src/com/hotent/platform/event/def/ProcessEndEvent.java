package com.hotent.platform.event.def;

import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.context.ApplicationEvent;

import com.hotent.platform.model.bpm.ProcessRun;


/**
 * 流程结束事件。
 * @author ray
 */
public class ProcessEndEvent extends ApplicationEvent {
	
	private ExecutionEntity executionEntity;

	/**
	 * 
	 */
	private static final long serialVersionUID = -3737454342482409864L;

	public ProcessEndEvent(ProcessRun source) {
		super(source);
	}
	
	public void setExecutionEntity(ExecutionEntity ent){
		this.executionEntity=ent;
	}
	
	public ExecutionEntity getExecutionEntity(){
		return this.executionEntity;
	}

	
	

}
