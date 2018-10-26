package com.hotent.platform.service.bpm.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.service.bpm.BpmNodeScriptService;

/**
 *子流程最后完成。 
 * @author ray
 *
 */
public class SubProcessEndListener implements ExecutionListener {

	public void notify(DelegateExecution execution) throws Exception {
		Integer nrOfInstances=(Integer)execution.getVariable("nrOfInstances");
		Integer nrOfCompletedInstances=(Integer)execution.getVariable("nrOfCompletedInstances");
		//子流程第一次执行
		if(nrOfInstances==null || (nrOfCompletedInstances!=null &&  nrOfInstances.equals(nrOfCompletedInstances))){
			String actDefId=execution.getProcessDefinitionId();
			String nodeId=execution.getCurrentActivityId();
			exeEventScript(execution,BpmConst.EndScript,actDefId,nodeId);
		}
		
	}

	private void exeEventScript(DelegateExecution execution,int scriptType,String actDefId,String nodeId ){
		BpmNodeScriptService bpmNodeScriptService=(BpmNodeScriptService)AppUtil.getBean("bpmNodeScriptService");
		BpmNodeScript model=bpmNodeScriptService.getScriptByType(nodeId, actDefId, scriptType);
		if(model==null) return;

		String script=model.getScript();
		if(StringUtil.isEmpty(script)) return;
		
		GroovyScriptEngine scriptEngine=(GroovyScriptEngine)AppUtil.getBean("scriptEngine");
		Map<String, Object> vars=execution.getVariables();
		vars.put("execution", execution);
		scriptEngine.execute(script, vars);
	}

}
