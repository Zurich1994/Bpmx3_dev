package com.hotent.platform.service.bpm.listener;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.model.bpm.BpmBusLink;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bpm.BpmBusLinkService;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.form.BpmFormDefService;

/**
 * 开始事件监听器。
 * @author ray
 *
 */
public class StartEventListener extends BaseNodeEventListener {

	private final static Log logger=LogFactory.getLog(StartEventListener.class);
	@Resource
	BpmDefinitionDao bpmDefinitionDao;
	@Resource
	ProcessRunService processRunService;
	@Resource
	BpmNodeSetService bpmNodeSetService;
	@Resource
	BpmFormRunService bpmFormRunService;

	
	
	@Override
	protected void execute(DelegateExecution execution, String actDefId,String nodeId) {
		logger.debug("nodeId" + nodeId);
		handExtSubProcess(execution);
	}
	
	/**
	 * 处理子流程。
	 * <pre>
	 * 1.如果流程变量当中有“innerPassVars”变量，那么这个是一个外部子流程的调用。
	 * 2.创建processrun记录。
	 * 3.准备流程实例id为初始化执行堆栈做准备。
	 * 4.处理运行表单。
	 * 5.通知任务执行人。
	 * </pre>
	 * @param execution
	 * @throws Exception 
	 */
	private void handExtSubProcess(DelegateExecution execution) {
		ExecutionEntity ent= (ExecutionEntity)execution;
		//非子流程调用直接返回。
		if( execution.getVariable(BpmConst.PROCESS_INNER_VARNAME)==null) return ;
		
		Map<String, Object> variables=(Map<String, Object>)ent.getVariable(BpmConst.PROCESS_INNER_VARNAME);
		//是否外部调用（这里指的是通过 webservice接口调用)
		Boolean isExtCall=(Boolean) variables.get(BpmConst.IS_EXTERNAL_CALL);
		String parentActDefId = (String)variables.get(BpmConst.FLOW_PARENT_ACTDEFID);
		
		String instanceId= ent.getProcessInstanceId();
		//准备流程实例id为初始化执行堆栈做准备。
		TaskThreadService.addExtSubProcess( instanceId);
		
		String actDefId= ent.getProcessDefinitionId();
		
		Long parentRunId=(Long)variables.get(BpmConst.FLOW_RUNID);
		ProcessRun parentProcessRun = processRunService.getById(parentRunId);
		//添加processRun。
		ProcessRun processRun=initProcessRun(actDefId,instanceId,variables,parentProcessRun);
		
		Long runId=processRun.getRunId();
		//设置流程运行变量
		execution.setVariable(BpmConst.FLOW_RUNID, processRun);

		execution.setVariables(variables);
		
		//处理运行时表单。
		if(isExtCall!=null && !isExtCall){
			bpmFormRunService.addFormRun(actDefId, runId,instanceId, parentActDefId);
		}
	}
	
	
	
	
	/**
	 * 插入流程运行记录。
	 * @param actDefId
	 * @param instanceId
	 * @param variables
	 */
	private ProcessRun initProcessRun(String actDefId,String instanceId,Map<String, Object> variables,ProcessRun parentProcessRun) {
		String businessKey=(String)variables.get(BpmConst.FLOW_BUSINESSKEY);
		
		String parentActDefId = (String)variables.get(BpmConst.FLOW_PARENT_ACTDEFID);
		
		BpmDefinition  bpmDefinition= bpmDefinitionDao.getByActDefId(actDefId);
		ProcessRun processRun = new ProcessRun();
		
		//获取子流程配置的表单
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getStartBpmNodeSet(bpmDefinition.getDefId(), actDefId, parentActDefId);
		if(bpmNodeSet==null){
			String msg = ContextUtil.getMessages("外部子流程【"+bpmDefinition.getSubject()+"】未设置表单!");
			MessageUtil.addMsg(msg);
			throw new RuntimeException(msg);
		}else{
			Long formDefId = getExtSubProcessFormKey(bpmNodeSet.getFormKey(), parentProcessRun.getFormDefId(),
					bpmDefinition.getSubject());
			processRun.setFormDefId(formDefId);
		}
		SysUser curUser = ContextUtil.getCurrentUser();
		if(curUser!=null){
			processRun.setCreator(curUser.getFullname());
			processRun.setCreatorId(curUser.getUserId());
		}
		else{
			processRun.setCreator(SystemConst.SYSTEMUSERNAME);
			processRun.setCreatorId(SystemConst.SYSTEMUSERID);
		}
		if(BpmDefinition.STATUS_TEST.equals(bpmDefinition.getStatus())){
			processRun.setIsFormal(ProcessRun.TEST_RUNNING);
		}else{
			processRun.setIsFormal(ProcessRun.FORMAL_RUNNING);
		}
		
		processRun.setActDefId(bpmDefinition.getActDefId());
		processRun.setDefId(bpmDefinition.getDefId());
		processRun.setProcessName(bpmDefinition.getSubject());
		
		processRun.setActInstId(instanceId);
		
		String subject=(String)variables.get(BpmConst.FLOW_RUN_SUBJECT);
		processRun.setSubject(subject);
		processRun.setBusinessKey(businessKey);
		
		processRun.setCreatetime(new Date());
		processRun.setStatus(ProcessRun.STATUS_RUNNING);
		processRun.setRunId(UniqueIdUtil.genId());
		processRun.setParentId(parentProcessRun.getRunId());
		processRun.setTypeId(bpmDefinition.getTypeId());//设置分类ID
		
		processRunService.add(processRun);
		
		return processRun;
	}
	
	/**
	 * 获取子流程表单,要求子、父流程的表单对应的主表相同
	 * @param formDefId
	 * @param parentFormDefId
	 * @param subject
	 * @return
	 * @author helh
	 */
	private Long getExtSubProcessFormKey(String formKey, Long parentFormDefId, String subject){
		BpmFormDefService bpmFormDefService = (BpmFormDefService)AppUtil.getBean(BpmFormDefService.class);
		BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(formKey);
		Long formDefId=bpmFormDef.getFormDefId();
		if(formDefId.equals(parentFormDefId)){//子流程与父流程的表单相同
			return formDefId;
		}else{
			BpmFormDef parentBpmFormDef = bpmFormDefService.getById(parentFormDefId);
			if(bpmFormDef.getTableId().equals(parentBpmFormDef.getTableId())){//表相同
				return formDefId;
			}else{
				String msg = ContextUtil.getMessages("外部子流程【"+subject+"】设置的表单的主表与父流程的必须相同!");
				MessageUtil.addMsg(msg);
				throw new RuntimeException(msg);
			}
		}
	}

	@Override
	protected Integer getScriptType() {
		
		return BpmConst.StartScript;
	}

}
