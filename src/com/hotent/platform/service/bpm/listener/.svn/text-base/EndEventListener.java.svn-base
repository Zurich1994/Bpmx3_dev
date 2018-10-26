package com.hotent.platform.service.bpm.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.bpm.BpmBusLinkDao;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.bpm.BpmFormRunDao;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.event.def.ProcessEndEvent;
import com.hotent.platform.model.bpm.BpmBusLink;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmProCopytoService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskMessageService;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.system.SysTemplateService;

/**
 * 结束事件监听器。
 * @author ray
 *
 */
@Service
public class EndEventListener extends BaseNodeEventListener {
	
	@Resource
	ProcessRunService processRunService;
	@Resource
	BpmFormRunDao bpmFormRunDao;
	@Resource
	TaskDao taskDao;
	@Resource
	BpmProCopytoService bpmProCopytoService;
	@Resource
	SysUserDao sysUserDao;
	@Resource
	SysTemplateService sysTemplateService;
	@Resource
	TaskMessageService taskMessageService;
	@Resource
	BpmBusLinkDao bpmBusLinkDao;
	@Resource
	BpmDefinitionDao bpmDefinitiondao;
	
	@Override
	protected void execute(DelegateExecution execution, String actDefId,String nodeId) {
		ExecutionEntity ent=(ExecutionEntity)execution;
		if(!ent.isEnded()) return;
		
		//当前的excutionId和主线程相同时。
		if(ent.getId().equals(ent.getProcessInstanceId())  && ent.getParentId()==null){
			System.out.println("++++++++++++++++++++++++++++++"+new Long(ent.getProcessInstanceId()));
			ProcessRun processRun =processRunService.getByActInstanceId(new Long(ent.getProcessInstanceId()));
			System.out.println("processRun:"+processRun);
			if(processRun==null){
				System.out.println("执行了................");
				
			}else{
				handEnd(ent,processRun);
				//发布流程结束事件。
				ProcessEndEvent ev=new ProcessEndEvent(processRun);
				ev.setExecutionEntity(ent);
				//发布流程结束事件。
				AppUtil.publishEvent(ev);
			}
		}
	}
	
	
	private void handEnd(ExecutionEntity ent,ProcessRun processRun){
		//更新流程实例状态。
		updProcessRunStatus(processRun);
		//删除知会任务。
		delNotifyTask(ent);
		
		//更新业务中间表。
		updBusLink(processRun);
		//发送操送
		copyTo(ent,processRun);
		
	}
	
	/**
	 * 抄送。
	 * @param ent
	 * @param processRun
	 */
	private void copyTo(ExecutionEntity ent,ProcessRun processRun){
		ProcessCmd processCmd= TaskThreadService.getProcessCmd();
		//根据主键值获取流程定义
		BpmDefinition bpmDefinition= bpmDefinitiondao.getById(processRun.getDefId());
		//获取流程变量
		Map<String,Object> vars = ent.getVariables();
		//添加抄送任务以及发送提醒
		try {
			bpmProCopytoService.handlerCopyTask(processRun, vars, processCmd.getCurrentUserId().toString(),bpmDefinition);
			//处理发送提醒消息给发起人
			if(StringUtil.isNotEmpty(bpmDefinition.getInformStart())){
				handSendMsgToStartUser(processRun,bpmDefinition);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * 更新业务中间表数据状态。
	 * @param processRun
	 */
	private void updBusLink(ProcessRun processRun){
		Long businessKey=new Long( processRun.getBusinessKey());
		BpmBusLink bpmBusLink=new BpmBusLink();
		SysUser user=ContextUtil.getCurrentUser();
		if(user!=null){
			bpmBusLink.setBusUpdid(user.getUserId());
			bpmBusLink.setBusUpd(user.getFullname());
		}
		bpmBusLink.setBusStatus(BpmBusLink.BUS_STATUS_END);
		bpmBusLink.setBusUpdtime(new Date());
		bpmBusLink.setBusPk(businessKey);
		bpmBusLinkDao.updateStatus(bpmBusLink);
	}
	
	/**
	 * 处理发送提醒消息给发起人
	 * @throws Exception 
	 */
	private void handSendMsgToStartUser(ProcessRun processRun,BpmDefinition bpmDefinition) throws Exception{
		String informStart=bpmDefinition.getInformStart();
		if(StringUtil.isEmpty(informStart))return;
		
		String subject = processRun.getSubject();
		if(BeanUtils.isEmpty(processRun))return;
		Long startUserId = processRun.getCreatorId();
		SysUser user = sysUserDao.getById(startUserId);
		List<SysUser> receiverUserList = new ArrayList<SysUser>();
		receiverUserList.add(user);
		
		Map<String,String> msgTempMap = sysTemplateService.getTempByFun(SysTemplate.USE_TYPE_NOTIFY_STARTUSER);	
		taskMessageService.sendMessage(null, receiverUserList, informStart, msgTempMap, subject, "", null,processRun.getRunId(),null);
	}
	
	/**
	 * 流程终止时删除流程任务。
	 * <pre>
	 * 	1.删除流程实例任务。
	 *  2.删除任务的参与者。
	 *  3.删除流程表单运行情况
	 * </pre>
	 * @param ent
	 */
	private void delNotifyTask(ExecutionEntity ent){
		Long instanceId=new Long( ent.getProcessInstanceId());
		//删除知会任务
		taskDao.delSubCustTaskByInstId(instanceId);
		//删除流程表单运行情况
		bpmFormRunDao.delByInstanceId(String.valueOf(instanceId));
	}
	
	
	/**
	 * 更新流程运行状态。
	 * <pre>
	 * 1.更新流程运行状态为完成。
	 * 2.计算流程过程的时间。
	 * </pre>
	 * @param ent
	 */
	private void updProcessRunStatus(ProcessRun processRun){
		if(BeanUtils.isEmpty(processRun)) return;
		//设置流程状态为完成。
		processRun.setStatus(ProcessRun.STATUS_FINISH);
		processRunService.update(processRun);
	}

	@Override
	protected Integer getScriptType() {
		return BpmConst.EndScript;
	}

}
