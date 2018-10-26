package com.hotent.platform.service.bpm.listener;

import java.util.Map;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.service.bpm.BpmNodeScriptService;

/**
 * 节点事件基类，采用了模板模式。
 * @author ray
 *
 */
public abstract class BaseNodeEventListener implements ExecutionListener {
	private Log logger = LogFactory.getLog(GroovyScriptEngine.class);
	public void notify(DelegateExecution execution) throws Exception {
		logger.debug("enter the node event listener.." + execution.getId());
		
		ExecutionEntity ent=(ExecutionEntity)execution;
		System.out.println("execution是什么"+execution);
//		if(ent.getActivityId()==null) return;
		
		String actDefId=ent.getProcessDefinitionId();
		String nodeId=ent.getActivityId();
		
		//执行实现类业务逻辑
		execute(execution,actDefId,nodeId);
		//只有节不为空时，才会去执行脚本。
		if(nodeId!=null){
			//获取事件类型
			Integer scriptType=getScriptType();
			//执行事件脚本
			exeEventScript(execution,scriptType,actDefId,nodeId);
		}
	}
	
	/**
	 * 实行子类的逻辑事务
	 * @param execution
	 * @param actDefId
	 * @param nodeId
	 */
	protected abstract void execute(DelegateExecution execution,String actDefId,String nodeId);
	
	/**
	 * 获取脚本类型。
	 * @return
	 */
	protected abstract Integer getScriptType();
	
	/**
	 * 执行事件脚本。
	 * @param execution
	 * @param scriptType
	 * @param actDefId
	 * @param nodeId
	 */
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
		
		logger.debug("execution script :" + script);
	}
	
	

}
