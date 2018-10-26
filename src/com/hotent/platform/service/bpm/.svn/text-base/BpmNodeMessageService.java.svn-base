package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.bpm.BpmNodeMessageDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeMessage;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.system.SysUser;

/**
 * 对象功能:流程节点邮件 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2012-01-04 09:31:50
 */
@Service
public class BpmNodeMessageService extends BaseService<BpmNodeMessage>
{
	@Resource
	private BpmNodeMessageDao dao;
	
	
	
	@Resource 
	private BpmDefinitionService bpmDefinitionService;
	
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	@Resource
	private BpmUserConditionService bpmUserConditionService;
	
	@Resource
	private BpmNodeUserService bpmNodeUserService;
	
	public BpmNodeMessageService()
	{
	}
	
	/**
	 * 根据ACT流程定义id获取流程定义。
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeMessage> getByActDefId(String actDefId)
	{
		return dao.getByActDefId(actDefId);
	}
	
	@Override
	protected IEntityDao<BpmNodeMessage, Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 通过流程发布ID及节点id获取流程设置节点列表
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public List<BpmNodeMessage> getListByActDefIdNodeId(String actDefId,String nodeId)
	{
		return dao.getMessageByActDefIdNodeId(actDefId, nodeId);
	}

	/**
	 * 获取邮件收件人
	 * @param actDefId
	 * @param nodeId
	 * @param execution
	 * @param preTaskUserId
	 * @return
	 */
	public List<SysUser> getMailReceiver(DelegateExecution execution,Long preTaskUserId){
		return getMessageReceiver(execution, BpmUserCondition.CONDITION_TYPE_MSG_MAIL_RECEIVER, preTaskUserId);
	}
	public List<SysUser> getMailCopyTo(DelegateExecution execution,Long preTaskUserId){
		return getMessageReceiver(execution, BpmUserCondition.CONDITION_TYPE_MSG_MAIL_COPYTO, preTaskUserId);
	}
	public List<SysUser> getMailBcc(DelegateExecution execution,Long preTaskUserId){
		return getMessageReceiver(execution, BpmUserCondition.CONDITION_TYPE_MSG_MAIL_BCC, preTaskUserId);
	}
	public List<SysUser> getInnerReceiver(DelegateExecution execution,Long preTaskUserId){
		return getMessageReceiver(execution, BpmUserCondition.CONDITION_TYPE_MSG_INNER_RECEIVER, preTaskUserId);
	}
	public List<SysUser> getSmsReceiver(DelegateExecution execution,Long preTaskUserId){
		return getMessageReceiver(execution, BpmUserCondition.CONDITION_TYPE_MSG_MOBILE_RECEIVER,  preTaskUserId);
	}

	public List<BpmNodeMessage> getNodeMessages(String actDefId, String nodeId) {
		return dao.getByActDefIdAndNodeId(actDefId,nodeId);
	}
	
	/**
	 * 获取节点消息接收人
	 * @param execution 
	 * @param type 
	 * @param actDefId
	 * @param nodeId
	 * @param preTaskUserId
	 * @return
	 */
	public List<SysUser> getMessageReceiver(DelegateExecution execution,Integer type,Long preTaskUserId){
		String actDefId = execution.getProcessDefinitionId();
		String nodeId = execution.getCurrentActivityId();
		BpmDefinition definition = bpmDefinitionService.getByActDefId(actDefId);
		Long defId = definition.getDefId();
		BpmNodeSet nodeSet = bpmNodeSetService.getBySetType(defId, BpmNodeSet.SetType_GloabalForm);
		if(nodeSet==null){
			return null;
		}
		List<BpmUserCondition> bpmUserConditions =null;
		if(type==null){
			type=-1;
		}
		switch (type) {
		case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_RECEIVER:
			bpmUserConditions = bpmUserConditionService.getReceiverMailConditions(actDefId, nodeId);	
			break;
		case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_COPYTO:
			bpmUserConditions = bpmUserConditionService.getCopyToMailConditions(actDefId, nodeId);	
			break;
		case BpmUserCondition.CONDITION_TYPE_MSG_MAIL_BCC:
			bpmUserConditions = bpmUserConditionService.getBccMailConditions(actDefId, nodeId);	
			break;
		case BpmUserCondition.CONDITION_TYPE_MSG_INNER_RECEIVER:
			bpmUserConditions = bpmUserConditionService.getReceiverInnerConditions(actDefId, nodeId);	
			break;
		case BpmUserCondition.CONDITION_TYPE_MSG_MOBILE_RECEIVER:
			bpmUserConditions = bpmUserConditionService.getReceiverSmsConditions(actDefId, nodeId);	
			break;
		default:
			bpmUserConditions = new ArrayList<BpmUserCondition>();
			break;
		}
		
		List<SysUser> executors = bpmNodeUserService.getTaskExecutors(execution, bpmUserConditions, preTaskUserId);
		
		return executors;
	}
	
}
