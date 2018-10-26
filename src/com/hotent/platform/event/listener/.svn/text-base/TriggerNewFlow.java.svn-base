package com.hotent.platform.event.listener;

import java.util.List;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.util.JRStyledText.Run;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hotent.core.bpm.WorkFlowException;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.event.def.TriggerNewFlowEvent;
import com.hotent.platform.event.def.TriggerNewFlowModel;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNewFlowTrigger;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNewFlowTriggerService;
import com.hotent.platform.service.bpm.BpmNodeUserService;
import com.hotent.platform.service.bpm.BpmUserConditionService;
import com.hotent.platform.service.bpm.ProcessRunService;

/**
 * 触发新流程事件。
 * 
 * @author ray
 * 
 */
@Service @Scope("prototype")
public class TriggerNewFlow  implements Runnable  {
	@Resource
	ProcessRunService processRunService;
	
	ProcessCmd cmd ;
	SysUser user ;


	public void run() {
		if(cmd == null || user == null)  throw new WorkFlowException("触发新流程失败！ new flow Cmd or starUser cannot be null");
		try {
			ContextUtil.setCurrentUser(user);
			processRunService.startProcess(cmd);
		} catch (Exception e) {
			throw new WorkFlowException("触发新流程失败："+e.getMessage());
		}
	}

	public void setCmd(ProcessCmd cmd) {
		this.cmd = cmd;
	}
	
	public void setUser(SysUser user) {
		this.user = user;
	}

}