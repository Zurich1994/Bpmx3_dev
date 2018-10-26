package com.hotent.platform.service.bpm;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmNodeUserDao;
import com.hotent.platform.dao.bpm.BpmUserConditionDao;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.bpm.BpmUserCondition;


/**
 * <pre>
 * 对象功能:节点下的人员的配置规则   Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-31 15:26:17
 * </pre>
 */
@Service
public class BpmUserConditionService extends BaseService<BpmUserCondition> {
	@Resource
	private BpmUserConditionDao dao;
	
	@Resource
	private BpmNodeUserDao bpmNodeUserDao;
	


	public BpmUserConditionService() {
	}

	@Override
	protected IEntityDao<BpmUserCondition, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 根据流程set获得条件
	 * 
	 * @param setId
	 * @return
	 */
	public List<BpmUserCondition> getBySetId(Long setId) {
		return dao.getBySetId(setId);
	}

	/**
	 * 通过流程定义ID获得用户设置条件
	 * 
	 * @param actDefId 流程定义ID
	 * @return
	 */
	public List<BpmUserCondition> getByActDefId(String actDefId) {
		return dao.getByActDefId(actDefId);
	}
	
	/**
	 * 通过流程定义ID获得子流程中的用户设置条件
	 * 
	 * @param actDefId 流程定义ID
	 * @param parentActDefId 父流程定义ID
	 * @return
	 */
	public List<BpmUserCondition> getByActDefIdWithParent(String actDefId, String parentActDefId) {
		return dao.getByActDefIdWithParent(actDefId, parentActDefId);
	}
	
	/**
	 * 保存人员设置和规则设置。
	 * @param bpmUserCondition
	 * @param users
	 */
	public void saveConditionAndUser(BpmUserCondition bpmUserCondition,String users){
		Long conditionId = 0L;
		JSONArray jarray = JSONArray.fromObject(users);		
		if(bpmUserCondition.getId()>0){
			conditionId = bpmUserCondition.getId();
			dao.update(bpmUserCondition);
			//根据条件ID删除组合条件。
			bpmNodeUserDao.delByConditionId(conditionId);
		}
		else{
			conditionId = UniqueIdUtil.genId();
			bpmUserCondition.setId(conditionId);
			dao.add(bpmUserCondition);
		}
		for(Object obj : jarray){
			JSONObject jobject = (JSONObject)obj;
			String cmpIds=jobject.getString("cmpIds");
			
			Long nodeUserId=UniqueIdUtil.genId();
			BpmNodeUser nodeUser = new BpmNodeUser();
			nodeUser.setNodeUserId(nodeUserId);
		
			String assignType= jobject.getString("assignType");
			nodeUser.setAssignType(assignType);
			
			nodeUser.setCmpIds(cmpIds);
			nodeUser.setCmpNames(jobject.getString("cmpNames"));
			Short extractUser = 0;
			if(jobject.has("extractUser"))
				extractUser = Short.parseShort(jobject.getString("extractUser"));
			nodeUser.setExtractUser(extractUser);
			nodeUser.setCompType(Short.parseShort(jobject.getString("compType")));
			nodeUser.setConditionId(conditionId);
			
			bpmNodeUserDao.add(nodeUser);
		}
	}

	/**
	 * 通过流程定义ID获得抄送用户设置条件
	 * 
	 * @param actDefId 流程定义ID
	 * @return
	 */
	public List<BpmUserCondition> getCcByActDefId(String actDefId) {
		return dao.getCcByActDefId(actDefId);
	}
	
	/**
	 * 通过流程定义ID和父流程定义ID获得抄送用户设置条件
	 * 
	 * @param actDefId 流程定义ID
	 * @param parentActDefId 父流程定义ID
	 * @return
	 */
	public List<BpmUserCondition> getCcByActDefId(String actDefId, String parentActDefId) {
		return dao.getCcByActDefId(actDefId, parentActDefId);
	}
	
	/**
	 * 通过流程定义ID获得抄送用户设置条件
	 * @param actDefId 流程定义ID
	 * @return
	 */
	public List<BpmUserCondition> getByActDefIdAndNodeId(String actDefId,String nodeId) {
		return dao.getByActDefIdAndNodeId(actDefId,nodeId);
	}
	
	/**
	 * 通过流程定义ID和节点ID，获取消息节点的邮件接收人
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmUserCondition> getReceiverMailConditions(String actDefId,String nodeId) {
		return dao.getByActDefIdAndNodeIdAndType(actDefId,nodeId,BpmUserCondition.CONDITION_TYPE_MSG_MAIL_RECEIVER);
	}
	
	/**
	 * 通过流程定义ID和节点ID，获取消息节点的邮件抄送人
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmUserCondition> getCopyToMailConditions(String actDefId,String nodeId) {
		return dao.getByActDefIdAndNodeIdAndType(actDefId,nodeId,BpmUserCondition.CONDITION_TYPE_MSG_MAIL_COPYTO);
	}
	
	/**
	 * 通过流程定义ID和节点ID，获取消息节点的邮件密送人
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmUserCondition> getBccMailConditions(String actDefId,String nodeId) {
		return dao.getByActDefIdAndNodeIdAndType(actDefId,nodeId,BpmUserCondition.CONDITION_TYPE_MSG_MAIL_BCC);
	}
	
	/**
	 * 通过流程定义ID和节点ID，获取消息节点的内部消息接收人
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmUserCondition> getReceiverInnerConditions(String actDefId,String nodeId) {
		return dao.getByActDefIdAndNodeIdAndType(actDefId,nodeId,BpmUserCondition.CONDITION_TYPE_MSG_INNER_RECEIVER);
	}
	
	/**
	 * 通过流程定义ID和节点ID，获取消息节点的短信接收人
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmUserCondition> getReceiverSmsConditions(String actDefId,String nodeId) {
		return dao.getByActDefIdAndNodeIdAndType(actDefId,nodeId,BpmUserCondition.CONDITION_TYPE_MSG_MOBILE_RECEIVER);
	}
	
	/**
	 * 通过流程定义ID和节点ID，获取获取触发新流程  发起人
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmUserCondition> getTriggerNewFlowStartUserConditions(String actDefId,String nodeId) {
		return dao.getByActDefIdAndNodeId(actDefId,BpmUserCondition.TRIGGER_NEWFLOW_STARTUSER_NODE_PRDFIX+  nodeId);
	}
	public void delConditionById(Long[] id){
		for(int i=0;i<id.length;i++){
			long conditionId=id[i];
			//BPM_NODE_USER
			bpmNodeUserDao.delByConditionId(conditionId);
			//BPM_USER_CONDITION
			dao.delById(conditionId);
		}
	}
}
