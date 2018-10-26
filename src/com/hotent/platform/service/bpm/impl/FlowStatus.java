package com.hotent.platform.service.bpm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotent.platform.dao.bpm.BpmProStatusDao;
import com.hotent.platform.model.bpm.BpmProStatus;
import com.hotent.platform.service.bpm.IFlowStatus;

public class FlowStatus implements IFlowStatus {
	
	private BpmProStatusDao bpmProStatusDao;
	
	private Map<Short, String> statusColorMap=new HashMap<Short, String>();
	
	public void setBpmProStatus(BpmProStatusDao bpmProStatusDao){
		this.bpmProStatusDao=bpmProStatusDao;
	}
	
	public void setStatusColor(Map<Short, String> tmp){
		this.statusColorMap=tmp;
	}
	
	public Map<String,String> getStatusByInstanceId(Long instanceId) {
		Map<String, String> map=new HashMap<String, String>();
		List<BpmProStatus> list= this.bpmProStatusDao.getByActInstanceId(instanceId.toString());
		for(BpmProStatus obj:list){
			String color=statusColorMap.get(obj.getStatus());
			map.put(obj.getNodeid(), color);
			//userTask2,#00ff99.
		}
		return map;
	}
	
}
