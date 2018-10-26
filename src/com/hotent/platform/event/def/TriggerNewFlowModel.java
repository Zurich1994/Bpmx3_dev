package com.hotent.platform.event.def;

import com.hotent.core.bpm.model.ProcessCmd;


public class TriggerNewFlowModel {
	private String action;
	private ProcessCmd processCmd;
	private String nodeId;
	
	public TriggerNewFlowModel(String action,String nodeId, ProcessCmd processCmd) {
		super();
		this.nodeId = nodeId;
		this.action = action;
		this.processCmd = processCmd;
	}
	
	public TriggerNewFlowModel(){}
	
	

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ProcessCmd getProcessCmd() {
		return processCmd;
	}

	public void setProcessCmd(ProcessCmd processCmd) {
		this.processCmd = processCmd;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
