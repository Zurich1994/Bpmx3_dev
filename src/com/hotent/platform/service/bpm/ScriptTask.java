package com.hotent.platform.service.bpm;


import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmNodeScript;

/**
 * 脚本任务用于执行groovy脚本
 * @author ray
 *
 */
@Service
public class ScriptTask implements JavaDelegate {
	private Log logger = LogFactory.getLog(GroovyScriptEngine.class);
	public void execute(DelegateExecution execution) throws Exception {
		ExecutionEntity ent=(ExecutionEntity)execution;
		String nodeId=ent.getActivityId();
		String actDefId=ent.getProcessDefinitionId();
		
		BpmNodeScriptService bpmNodeScriptService=(BpmNodeScriptService)AppUtil.getBean("bpmNodeScriptService");
		//获取脚本节点的脚本对象。
		BpmNodeScript model=bpmNodeScriptService.getScriptByType(nodeId, actDefId, BpmConst.ScriptNodeScript);
		if(model==null) return;
		
		
		String script=model.getScript();
		if(StringUtil.isEmpty(script)) return;
		
		GroovyScriptEngine scriptEngine=(GroovyScriptEngine)AppUtil.getBean("scriptEngine");
		Map<String, Object> vars=execution.getVariables();
		vars.put("execution", execution);
		scriptEngine.execute(script, vars);
		
		logger.debug("execution script :" + script);
	}

}
