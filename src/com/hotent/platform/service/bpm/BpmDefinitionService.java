package com.hotent.platform.service.bpm;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.Deployment;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotent.CodeTemplate.service.CodeTemplate.CodeTemplateService;
import com.hotent.core.bpm.cache.ActivitiDefCache;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.bpmn20.entity.CallActivity;
import com.hotent.core.bpmn20.entity.FlowElement;
import com.hotent.core.bpmn20.entity.Process;
import com.hotent.core.bpmn20.entity.UserTask;
import com.hotent.core.bpmn20.entity.ht.BPMN20HtExtConst;
import com.hotent.core.bpmn20.util.BPMN20Util;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.page.PageBean;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.XmlBeanUtil;
import com.hotent.core.util.ZipUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.bpm.BpmDao;
import com.hotent.platform.dao.bpm.BpmDefActDao;
import com.hotent.platform.dao.bpm.BpmDefVarDao;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.bpm.BpmFormRunDao;
import com.hotent.platform.dao.bpm.BpmGangedSetDao;
import com.hotent.platform.dao.bpm.BpmNodeButtonDao;
import com.hotent.platform.dao.bpm.BpmNodeMessageDao;
import com.hotent.platform.dao.bpm.BpmNodePrivilegeDao;
import com.hotent.platform.dao.bpm.BpmNodeRuleDao;
import com.hotent.platform.dao.bpm.BpmNodeScriptDao;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.dao.bpm.BpmNodeSignDao;
import com.hotent.platform.dao.bpm.BpmNodeUserDao;
import com.hotent.platform.dao.bpm.BpmNodeWebServiceDao;
import com.hotent.platform.dao.bpm.BpmNodeWsParamsDao;
import com.hotent.platform.dao.bpm.BpmProStatusDao;
import com.hotent.platform.dao.bpm.BpmReferDefinitionDao;
import com.hotent.platform.dao.bpm.BpmSubtableRightsDao;
import com.hotent.platform.dao.bpm.BpmUserConditionDao;
import com.hotent.platform.dao.bpm.ExecutionDao;
import com.hotent.platform.dao.bpm.ExecutionStackDao;
import com.hotent.platform.dao.bpm.ReminderStateDao;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.dao.bpm.TaskOpinionDao;
import com.hotent.platform.dao.bpm.TaskReminderDao;
import com.hotent.platform.dao.bpm.TaskSignDataDao;
import com.hotent.platform.dao.form.BpmFormDefDao;
import com.hotent.platform.dao.form.BpmFormRightsDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.dao.system.GlobalTypeDao;
import com.hotent.platform.model.bpm.BpmDefVar;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormResult;
import com.hotent.platform.model.bpm.BpmGangedSet;
import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.model.bpm.BpmNodeMessage;
import com.hotent.platform.model.bpm.BpmNodeRule;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.model.bpm.BpmNodeUser;
import com.hotent.platform.model.bpm.BpmNodeUserUplow;
import com.hotent.platform.model.bpm.BpmNodeWebService;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskApprovalItems;
import com.hotent.platform.model.bpm.TaskReminder;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormRights;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.Identity;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.TaskReadService;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.platform.xml.bpm.BpmDefinitionXml;
import com.hotent.platform.xml.bpm.BpmDefinitionXmlList;
import com.hotent.platform.xml.form.BpmFormDefXml;
import com.hotent.platform.xml.table.BpmFormTableXml;
import com.hotent.platform.xml.util.MsgUtil;
import com.hotent.platform.xml.util.XmlUtil;

/**
 * 对象功能:流程定义扩展 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-21 22:50:46
 */
@Service
public class BpmDefinitionService extends BaseService<BpmDefinition>
{
	@Resource
	private BpmDefinitionDao dao;	
	@Resource
	private BpmNodeSetDao bpmNodeSetDao;	
	@Resource
	private BpmDefVarDao bpmDefVarDao;		
	@Resource
	private BpmService bpmService;
	
	@Resource
	private BpmNodeSignDao bpmNodeSignDao;
	@Resource
	private BpmNodeRuleDao bpmNodeRuleDao;
	@Resource
	private TaskSignDataDao taskSignDataDao;
	@Resource
	private BpmNodeMessageDao bpmNodeMessageDao;
	@Resource
	private BpmDefVarDao bpmDefVarsDao;
	@Resource
	private ExecutionStackDao executionStackDao;
	@Resource
	private BpmNodeUserDao bpmNodeUserDao;
	@Resource
	private BpmNodeScriptDao bpmNodeScriptDao;
	
	@Resource
	private BpmNodeButtonDao bpmNodeButtonDao;
	@Resource
	private TaskReminderDao taskReminderDao;
	
	@Resource
	private BpmUserConditionDao bpmUserConditionDao;
	@Resource
	private GlobalTypeDao globalTypeDao;
	@Resource
	private BpmDao bpmDao;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmFormDefDao bpmFormDefDao;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormRunDao bpmFormRunDao;
	@Resource
	private BpmNodePrivilegeDao bpmNodePrivilegeDao;
	@Resource
	private BpmNodeWebServiceDao bpmNodeWebServiceDao;
	@Resource
	private BpmProStatusDao bpmProStatusDao;
	@Resource
	private TaskForkService taskForkService;
	@Resource
	private TaskOpinionDao taskOpinionDao;
	@Resource
	private ReminderStateDao reminderStateDao;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private TaskDao taskDao;
	@Resource
	private ExecutionDao executionDao;
	@Resource
	private SysUserService sysUserService;
	@Resource
	protected Properties configproperties;
	@Resource
	protected SysRoleService sysRoleService;
	@Resource
	protected BpmFormRightsDao bpmFormRightsDao;
	@Resource
	protected SysOrgService sysOrgService;
	@Resource
	protected PositionService positionService;
	@Resource
	protected BpmFormTableDao bpmFormTableDao;
	
	@Resource
	protected TaskService taskService;
	@Resource
	protected TaskVarsService taskVarsService;
	@Resource
	protected BpmFormHandlerService bpmFormHandlerService;
	@Resource
	protected BpmRunLogService bpmRunLogService;
	@Resource
	protected RuntimeService runtimeService;
	@Resource
	protected BpmNodeWsParamsDao bpmNodeWsParamsDao;
	@Resource
	protected BpmReferDefinitionDao bpmReferDefinitionDao;
	@Resource
	protected BpmSubtableRightsDao bpmSubtableRightsDao;
	@Resource
	protected BpmProCopytoService bpmProCopytoService;
	@Resource
	protected BpmTaskExeService bpmTaskExeService;
	@Resource
	protected CommuReceiverService commuReceiverService;
	@Resource
	protected TaskReadService taskReadService;
	@Resource
	private SysFileService sysFileService;
	@Resource
	private BpmGangedSetDao bpmGangedSetDao;	
	@Resource
	private BpmDefActDao bpmDefActDao;
	@Resource
	private CodeTemplateService codeTemplateService;
	@Resource
	private BpmDefinitionDao bpmDefinitionDao;
	public BpmDefinitionService()
	{
	}
	
	@Override
	protected IEntityDao<BpmDefinition, Long> getEntityDao() {
		return dao;
	}
	
	/**
	 * 发布流程数据。
	 * @param bpmDefinition
	 * @param actFlowDefXml
	 * @throws Exception 
	 */
	public void deploy(BpmDefinition bpmDefinition,String actFlowDefXml) throws Exception{
		Deployment deployment=bpmService.deploy(bpmDefinition.getSubject(), actFlowDefXml);
		ProcessDefinitionEntity ent= bpmService.getProcessDefinitionByDeployId(deployment.getId());
		bpmDefinition.setActDeployId(new Long(deployment.getId()));
		bpmDefinition.setActDefId(ent.getId());
		bpmDefinition.setActDefKey(ent.getKey());
		bpmDefinition.setStatus(BpmDefinition.STATUS_TEST);
		dao.update(bpmDefinition);
		
		saveOrUpdateNodeSet(actFlowDefXml,bpmDefinition,false);
	}
	
	
	/**
	 * 保存及更新流程定义
	 * @param bpmDefinition 流程定义实体
	 * @param isDeploy 是否发布新流程
	 * @param actFlowDefXml 流程定义bpmn文档
	 * @throws Exception
	 */
	public void saveOrUpdate(BpmDefinition bpmDefinition,boolean isDeploy,String actFlowDefXml) throws Exception
	{
		Long   oldDefId=bpmDefinition.getDefId();
		
		Long   newDefId=bpmDefinition.getDefId();
		
		boolean isUpdate=false;
		
		//新增加的流程
		if(bpmDefinition.getDefId()==null || bpmDefinition.getDefId()==0)
		{
			if(isDeploy)//发布定义
			{
				Deployment deployment=bpmService.deploy(bpmDefinition.getSubject(), actFlowDefXml);
				ProcessDefinitionEntity ent= bpmService.getProcessDefinitionByDeployId(deployment.getId());
				bpmDefinition.setActDeployId(new Long(deployment.getId()));
				bpmDefinition.setActDefId(ent.getId());
				bpmDefinition.setActDefKey(ent.getKey());
			}
			bpmDefinition.setVersionNo(1);
			
			bpmDefinition.setDefId(UniqueIdUtil.genId());
			//主版本
			bpmDefinition.setIsMain(BpmDefinition.MAIN);
			bpmDefinition.setCreatetime(new Date());
			bpmDefinition.setUpdatetime(new Date());
			bpmDefinition.setToFirstNode((short)1);
			bpmDefinition.setInformStart(ServiceUtil.getDefaultSelectInfoType());
			Short status=isDeploy?BpmDefinition.STATUS_TEST:BpmDefinition.STATUS_UNDEPLOYED;
			bpmDefinition.setStatus(status);
			add(bpmDefinition);
			
			if(isDeploy){
				//设置流程节点信息
				saveOrUpdateNodeSet(actFlowDefXml,bpmDefinition,true);
			}
		}
		//更新流程定义
		else{
			//发布了新的版本定义
			if(isDeploy){
				newDefId=UniqueIdUtil.genId();
				dao.updateSubVersions(newDefId,bpmDefinition.getDefKey());
				
				Deployment deployment=bpmService.deploy(bpmDefinition.getSubject(), actFlowDefXml);
				ProcessDefinitionEntity ent= bpmService.getProcessDefinitionByDeployId(deployment.getId());
				String actDefId=ent.getId();
				//原bpmFinition
				BpmDefinition newBpmDefinition=(BpmDefinition)bpmDefinition.clone();
				//增加版本号
				newBpmDefinition.setVersionNo(ent.getVersion());
				newBpmDefinition.setActDeployId(new Long(deployment.getId()));
				newBpmDefinition.setActDefId(actDefId);
				newBpmDefinition.setActDefKey(ent.getKey());
				//发布新版本后，需要生成新的发布记录
				newBpmDefinition.setDefId(newDefId);
				newBpmDefinition.setParentDefId(newDefId);
				newBpmDefinition.setUpdatetime(new Date());
				//newBpmDefinition.setStatus(BpmDefinition.STATUS_ENABLED);
				//设置新的流程序为主版本
				newBpmDefinition.setIsMain(BpmDefinition.MAIN);
				//添加新版本的流程定义
				add(newBpmDefinition);
				
				isUpdate=true;
				//设置流程节点信息
				saveOrUpdateNodeSet(actFlowDefXml,newBpmDefinition,true);
				//同步起始节点全局表单的配置情况。
				syncStartGlobal(oldDefId,newDefId,actDefId);			
			}
			else{
				//直接修改定义
				if(bpmDefinition.getActDeployId()!=null){
					bpmService.wirteDefXml(bpmDefinition.getActDeployId().toString(), actFlowDefXml);
					//设置流程节点信息
					saveOrUpdateNodeSet(actFlowDefXml,bpmDefinition,false);
					
					String actDefId=bpmDefinition.getActDefId();
					//清除节点的缓存
					NodeCache.clear(actDefId);
					//清除流程缓存。
					ActivitiDefCache.clearByDefId(actDefId);
					
				}
				update(bpmDefinition);
			}
		}
		
		if(isUpdate){//发布了新的版本定义
			saveOrUpdateBpmDefSeting(newDefId,oldDefId,actFlowDefXml, bpmDefinition.getDefKey());
		}
		//Locale locale = ContextUtil.getLocale();
		//saveLanguageInfo(bpmDefinition.getActDefId(), actFlowDefXml, locale.toString());
	}
	
	
	/**
	 * 保存流程定义的节点国际化资源信息
	 * 
	 * @param actDefId
	 * @param actFlowDefXml
	 * @param language
	 */
//	private void saveLanguageInfo(String actDefId, String actFlowDefXml,
//			String language) {
//		if (BeanUtils.isEmpty(actDefId))
//			return;
//		Document doc = Dom4jUtil.loadXml(actFlowDefXml);
//		List<Node> list = doc.selectNodes("/definitions//*[@name]");
//
//		Map<String, Map<String, String>> reskeyMap = new HashMap<String, Map<String,String>>();
//		List<String> reskeyList=new ArrayList<String>();
//		String local = ContextUtil.getLocale().toString();
//		for (Node node : list) {
//			Element element = (Element) node;
//			String id = element.attributeValue("id");
//			String name = element.attributeValue("name");
//			if (StringUtil.isEmpty(id) || StringUtil.isEmpty(name))
//				continue;
//			reskeyList.add(id);
//			Map<String, String> map = new HashMap<String, String>();
//			map.put(local, name);
//			reskeyMap.put(id, map);
//		}
//		for (int i = 0; i < reskeyList.size(); i++) {
//			bpmFormLanguageService.batchSaveUpdate(actDefId, reskeyList.get(i), reskeyMap.get(reskeyList.get(i)), BpmFormLanguage.BPM_DEFINITION_TYPE);
//		}
//	}
	
	/**
	 * 设置流程节点属性。
	 * @param actFlowDefXml
	 * @param bpmDefinition
	 * @param isAdd
	 * @throws Exception
	 */
	private void saveOrUpdateNodeSet(String actFlowDefXml,BpmDefinition bpmDefinition,boolean isAdd) throws Exception{
//		Long defId=bpmDefinition.getDefId();
		List<Process> processes = BPMN20Util.getProcess(actFlowDefXml);
		if(processes.size()==0){
			return;
		}
		@SuppressWarnings("unchecked")
		Class<FlowElement>[] classes= new Class[]{
				UserTask.class,
				CallActivity.class
		};
		List<FlowElement> flowElements = BPMN20Util.getFlowElementByType(processes.get(0),true,classes);
		if(isAdd){
			for(FlowElement flowElement:flowElements){
				addNodeSet(bpmDefinition,flowElement);
			}
		}else{
			delAndUpdNodeSet(bpmDefinition, flowElements);
		}
	}
	
	/**
	 * 删除无用的节点、添加新增的节点
	 * @param bpmDefinition
	 * @param flowElements
	 * @param parentActDefId
	 * @throws Exception
	 * @author helh
	 */
	private void delAndUpdNodeSet(BpmDefinition bpmDefinition, List<FlowElement> flowElements) throws Exception{
		Map<String,BpmNodeSet> nodeSetMap = bpmNodeSetDao.getMapByDefId(bpmDefinition.getDefId());;
		//获取父流程中的外部子流程节点
		List<BpmNodeSet> callActiviti = bpmNodeSetDao.getByParentActDefId(bpmDefinition.getActDefId());
		//删除无用的节点
		delNodeSet(nodeSetMap, callActiviti, flowElements);
		//添加新增的节点
		updNodeSet(bpmDefinition, nodeSetMap, callActiviti, flowElements);
	}
	
	/**
	 * 
	 * @param bpmDefinition
	 * @return
	 * @throws Exception
	 * @developer helh
	 */
	private List<FlowElement> getFlowElements(BpmDefinition bpmDefinition) throws Exception{
		String actFlowDefXml = BpmUtil.transform(bpmDefinition.getDefKey(),
				bpmDefinition.getSubject(), bpmDefinition.getDefXml());
		List<Process> processes = BPMN20Util.getProcess(actFlowDefXml);
		if(processes.size()==0){
			return new ArrayList<FlowElement>();
		}
		@SuppressWarnings("unchecked")
		Class<FlowElement>[] classes= new Class[]{
				UserTask.class,
				CallActivity.class
		};
		return BPMN20Util.getFlowElementByType(processes.get(0),true,classes);
	}
	
	/**
	 * 删除当前流程定义中没有的节点。
	 * @param oldSetMap
	 * @param curNodeMap
	 * @param bpmDefinition
	 */
	@SuppressWarnings("unused")
	private void delNodeSet(Map<String,BpmNodeSet> oldSetMap,Map<String,String> curNodeMap){
		Iterator<String> keys=oldSetMap.keySet().iterator();
		while(keys.hasNext()){
			String nodeId=keys.next();
			if(curNodeMap.containsKey(nodeId)) continue;
			BpmNodeSet bpmNodeSet=oldSetMap.get(nodeId);
			bpmNodeSetDao.delById(bpmNodeSet.getSetId());
		}
	}
	
	/**
	 * 原来流程定义中没有的节点则添加流程节点定义。
	 * @param oldSetMap
	 * @param curNodeMap
	 * @param bpmDefinition
	 * @throws Exception
	 */
	private void updNodeSet(BpmDefinition bpmDefinition,Map<String,BpmNodeSet> oldSetMap, List<BpmNodeSet> callActiviti, List<FlowElement> flowNodes) throws Exception{
		for(FlowElement flowNode:flowNodes){
			if(flowNode instanceof CallActivity){
				//若为新添加的外部子流程节点，则添加子流程的节点数据
				String defKey = ((CallActivity) flowNode).getCalledElement();
				boolean inflag=false;
				if(callActiviti!=null){
					for(BpmNodeSet nodeSet : callActiviti){
						String actDefId=nodeSet.getActDefId();
						String[] defKeys = actDefId.split(":");
						if(defKey.equals(defKeys[0])){
							inflag=true;
							break;
						}
					}
				}
				if(inflag) continue;
				BpmDefinition calledBpmDefinition = getMainByDefKey(defKey);
				List<FlowElement> flowElements = getFlowElements(calledBpmDefinition);
				for(FlowElement flowElement : flowElements){
					addNodeSet(calledBpmDefinition, flowElement, bpmDefinition.getActDefId());
				}
			}else{
				if(BeanUtils.isNotEmpty(oldSetMap) && oldSetMap.containsKey(flowNode.getId())){
					Integer nodeOrder = 0;
					List<Object> extensions = BPMN20Util.getFlowElementExtension(flowNode, BPMN20HtExtConst._Order_QNAME);
					
					if(BeanUtils.isNotEmpty(extensions)){
						nodeOrder=(Integer) extensions.get(0);
					}
					BpmNodeSet bpmNodeSet=oldSetMap.get(flowNode.getId());
					bpmNodeSet.setNodeName(flowNode.getName());
					bpmNodeSet.setNodeOrder(nodeOrder);
					bpmNodeSetDao.update(bpmNodeSet);
					Long defId=bpmDefinition.getDefId();
					//当子流程节点更新时，更新父流程中的子流程数据
					List<BpmNodeSet> list = bpmNodeSetDao.getParentByDefIdAndNodeId(defId, flowNode.getId());
					updateParentNodeSet(list, flowNode);
				}else{
					addNodeSet(bpmDefinition, flowNode);
					addParentNodeSet(bpmDefinition, flowNode);
				}
			}
		}
	}
	
	/**
	 * 原来流程定义中没有的节点则添加流程节点定义。
	 * @param oldSetMap
	 * @param curNodeMap
	 * @param bpmDefinition
	 * @throws Exception
	 */
//	private void updNodeSet(Map<String,BpmNodeSet> oldSetMap,Map<String,String> curNodeMap,BpmDefinition bpmDefinition) throws Exception{
//		Iterator<String> keys=curNodeMap.keySet().iterator();
//		while(keys.hasNext()){
//			String nodeId=keys.next();
//			String actNodeName=curNodeMap.get(nodeId);
//			if(oldSetMap.containsKey(nodeId)) {
//				BpmNodeSet bpmNodeSet=oldSetMap.get(nodeId);
//				String nodeName=curNodeMap.get(nodeId);
//				bpmNodeSet.setNodeName(nodeName);
//				bpmNodeSetDao.update(bpmNodeSet);
//			}
//			else{
//				addNodeSet(bpmDefinition,nodeId,actNodeName);
//			}
//		}
//	}
	
	/**
	 * 删除当前流程定义中没有的节点。
	 * @param oldSetMap  原有流程节点
	 * @param flowNodes 新流程节点
	 */
	private void delNodeSet(Map<String,BpmNodeSet> oldSetMap, List<BpmNodeSet> callActiviti, List<FlowElement> flowNodes){
		if(callActiviti!=null){//删除子流程节点数据
			for(BpmNodeSet nodeSet : callActiviti){
				String actDefId=nodeSet.getActDefId();
				String[] defKeys = actDefId.split(":");
				boolean inflag=false;
				for(FlowElement flowNode:flowNodes){
					if(flowNode instanceof CallActivity){
						String defKey = ((CallActivity) flowNode).getCalledElement();
						if(defKey.equals(defKeys[0])){
							inflag=true;
							break;
						}
					}
				}
				if(inflag) continue;
				bpmNodeSetDao.delByDefIdAndParentActDefId(nodeSet.getDefId(), nodeSet.getParentActDefId());
			}
		}
		
		Iterator<String> keys=oldSetMap.keySet().iterator();
		while(keys.hasNext()){
			String nodeId=keys.next();
			boolean inflag=false;
			for(FlowElement flowNode:flowNodes){
				if(flowNode.getId().equals(nodeId)){
					inflag=true;
					break;
				}
			}
			if(inflag) continue;
			BpmNodeSet bpmNodeSet=oldSetMap.get(nodeId);
			//删除本节点和作为子流程的节点数据
			bpmNodeSetDao.delByDefIdNodeId(bpmNodeSet.getNodeId(), bpmNodeSet.getDefId());
		}
	}
	
	/**
	 * 添加流程定义节点设置。
	 * @param bpmDefinition 流程定义
	 * @param flowNode 流程节点
	 * @throws Exception
	 */
	private void addNodeSet(BpmDefinition bpmDefinition,FlowElement flowNode) throws Exception{
		if(flowNode instanceof CallActivity){//外部子流程
			String defKey = ((CallActivity) flowNode).getCalledElement();
			BpmDefinition calledBpmDefinition = getMainByDefKey(defKey);
			List<FlowElement> flowElements = getFlowElements(calledBpmDefinition);
			for(FlowElement flowElement : flowElements){
				addNodeSet(calledBpmDefinition, flowElement, bpmDefinition.getActDefId());
			}
		}else{
			Long defId=bpmDefinition.getDefId();
			String actDefId=bpmDefinition.getActDefId();
			Integer nodeOrder = 0;
			List<Object> extensions = BPMN20Util.getFlowElementExtension(flowNode, BPMN20HtExtConst._Order_QNAME);
			
			if(BeanUtils.isNotEmpty(extensions)){
				nodeOrder=(Integer) extensions.get(0);
			}
			
			BpmNodeSet bpmNodeSet=new BpmNodeSet();
			bpmNodeSet.setSetId(UniqueIdUtil.genId());
			bpmNodeSet.setFormType((short)-1);
			bpmNodeSet.setActDefId(actDefId);
			bpmNodeSet.setDefId(defId);
			bpmNodeSet.setNodeId(flowNode.getId());
			bpmNodeSet.setNodeName(flowNode.getName());
			bpmNodeSet.setNodeOrder(nodeOrder);
			bpmNodeSetDao.add(bpmNodeSet);
		}
	}
	
	/**
	 * 添加流程定义节点设置。
	 * @param bpmDefinition 流程定义
	 * @param flowNode 流程节点
	 * @throws Exception
	 * @author helh
	 */
	private void addNodeSet(BpmDefinition bpmDefinition,FlowElement flowNode, String parentActDefId) throws Exception{
		if(!(flowNode instanceof CallActivity)){
			Long defId=bpmDefinition.getDefId();
			String actDefId=bpmDefinition.getActDefId();
			Integer nodeOrder = 0;
			List<Object> extensions = BPMN20Util.getFlowElementExtension(flowNode, BPMN20HtExtConst._Order_QNAME);
			
			if(BeanUtils.isNotEmpty(extensions)){
				nodeOrder=(Integer) extensions.get(0);
			}
			
			BpmNodeSet bpmNodeSet=new BpmNodeSet();
			bpmNodeSet.setSetId(UniqueIdUtil.genId());
			bpmNodeSet.setFormType((short)-1);
			bpmNodeSet.setActDefId(actDefId);
			bpmNodeSet.setDefId(defId);
			bpmNodeSet.setNodeId(flowNode.getId());
			bpmNodeSet.setNodeName(flowNode.getName());
			bpmNodeSet.setNodeOrder(nodeOrder);
			bpmNodeSet.setParentActDefId(parentActDefId);
			bpmNodeSetDao.add(bpmNodeSet);
		}
	}
	
	/**
	 * 子流程添加节点时，调用该子流程的父流程做相应的节点数据的添加
	 * @param bpmDefinition
	 * @param flowNode
	 * @throws Exception
	 * @author helh
	 */
	private void addParentNodeSet(BpmDefinition bpmDefinition, FlowElement flowNode) throws Exception {
		Long defId = bpmDefinition.getDefId();
		List<BpmNodeSet> list = bpmNodeSetDao.getParentIdByDefId(defId);
		if (BeanUtils.isNotEmpty(list)) {
			for (BpmNodeSet nodeSet : list) {
				if (StringUtil.isNotEmpty(nodeSet.getParentActDefId())) {
					String actDefId = bpmDefinition.getActDefId();
					Integer nodeOrder = 0;
					List<Object> extensions = BPMN20Util.getFlowElementExtension(flowNode, BPMN20HtExtConst._Order_QNAME);

					if (BeanUtils.isNotEmpty(extensions)) {
						nodeOrder = (Integer) extensions.get(0);
					}

					BpmNodeSet bpmNodeSet = new BpmNodeSet();
					bpmNodeSet.setSetId(UniqueIdUtil.genId());
					bpmNodeSet.setFormType((short) -1);
					bpmNodeSet.setActDefId(actDefId);
					bpmNodeSet.setDefId(defId);
					bpmNodeSet.setNodeId(flowNode.getId());
					bpmNodeSet.setNodeName(flowNode.getName());
					bpmNodeSet.setNodeOrder(nodeOrder);
					bpmNodeSet.setParentActDefId(nodeSet.getParentActDefId());
					bpmNodeSetDao.add(bpmNodeSet);
				}
			}
		}
	}
	
	/**
	 * 更新父流程中子流程的节点数据
	 * @param nodeSetList
	 * @param flowNode
	 * @author helh
	 */
	private void updateParentNodeSet(List<BpmNodeSet> nodeSetList, FlowElement flowNode){
		if(BeanUtils.isNotEmpty(nodeSetList)){
			for(BpmNodeSet bpmNodeSet : nodeSetList){
				Integer nodeOrder = 0;
				List<Object> extensions = BPMN20Util.getFlowElementExtension(flowNode, BPMN20HtExtConst._Order_QNAME);
				
				if(BeanUtils.isNotEmpty(extensions)){
					nodeOrder=(Integer) extensions.get(0);
				}
				bpmNodeSet.setNodeName(flowNode.getName());
				bpmNodeSet.setNodeOrder(nodeOrder);
				bpmNodeSetDao.update(bpmNodeSet);
			}
		}
	}
	
//	private void addNodeSet(BpmDefinition bpmDefinition,String actNodeId,String actNodeName) throws Exception{
//		Long defId=bpmDefinition.getDefId();
//		String actDefId=bpmDefinition.getActDefId();
//		BpmNodeSet bpmNodeSet=new BpmNodeSet();
//		bpmNodeSet.setSetId(UniqueIdUtil.genId());
//		bpmNodeSet.setFormType((short)-1);
//		bpmNodeSet.setActDefId(actDefId);
//		bpmNodeSet.setDefId(defId);
//		bpmNodeSet.setNodeId(actNodeId);
//		bpmNodeSet.setNodeName(actNodeName);
//		bpmNodeSetDao.add(bpmNodeSet);
//	}
	
	/**
	 * 保存及更新流程定义的相关配置
	 * @param newDefId
	 * @param oldDefId
	 * @throws Exception 
	 */
	private void saveOrUpdateBpmDefSeting(Long newDefId,Long oldDefId,String actFlowDefXml,String defKey) throws Exception{
		
		if(oldDefId==null || oldDefId<=0) return;
		
		BpmDefinition newDef =getById(newDefId);
		BpmDefinition oldDef =getById(oldDefId);
		
		String newActDefId=newDef.getActDefId();
		String oldActDefId=oldDef.getActDefId();
		if(oldActDefId==null) return;
		
		
		//BPM_AGENT	流程代理配置
//		List<BpmAgent> agentList=bpmAgentDao.getByDefKey(defKey);
//		if(BeanUtils.isNotEmpty(agentList)){
//			for(BpmAgent o:agentList){
//				BpmAgent n=(BpmAgent)o.clone();
//				n.setId(UniqueIdUtil.genId());
//				n.setDefKey(defKey);
//				bpmAgentDao.add(n);
//			}
//		}
		
		//BPM_DEF_RIGHT	流程定义权限
//		List<BpmDefRights> defRight=bpmDefRightDao.getByDefKey(defKey);
//		if(BeanUtils.isNotEmpty(defRight)){
//			for(BpmDefRights o:defRight){
//				BpmDefRights n=(BpmDefRights)o.clone();
//				n.setId(UniqueIdUtil.genId());
//				n.setDefKey(defKey);
//				bpmDefRightDao.add(n);
//			}
//		}
		
		//BPM_DEF_VARS	流程变量定义 OK
		List<BpmDefVar> defVarList=bpmDefVarsDao.getByDefId(oldDefId);
		if(BeanUtils.isNotEmpty(defVarList)){
			for(BpmDefVar o:defVarList){
				BpmDefVar n=(BpmDefVar)o.clone();
				n.setVarId(UniqueIdUtil.genId());
				n.setDefId(newDefId);
				bpmDefVarsDao.add(n);
			}
		}

		//BPM_NODE_SCRIPT	流程开始结束节点事件脚本 OK
		List<BpmNodeScript> nodeScripts= bpmNodeScriptDao.getByActDefId(oldActDefId);
		Map<String, Map<String, String>> taskActivitysMap = BpmUtil.getTaskActivitys(actFlowDefXml);
		Map<String,String> startActivitysMap = taskActivitysMap.get("开始节点");
		Map<String,String> endActivitysMap = taskActivitysMap.get("结束节点");
		Map<String,String> seActivitysMap=new HashMap<String, String>();
		if(!BeanUtils.isEmpty(startActivitysMap)){
			seActivitysMap.putAll(startActivitysMap);
		}
		if(!BeanUtils.isEmpty(endActivitysMap)){
			seActivitysMap.putAll(endActivitysMap);
		}
		Iterator<String> seNodeIds=seActivitysMap.keySet().iterator();
		while(seNodeIds.hasNext()){
			String nodeId=seNodeIds.next();
			for(BpmNodeScript script:nodeScripts){
				if(script.getNodeId().equals(nodeId)){
					BpmNodeScript newScript=(BpmNodeScript)script.clone();
					newScript.setId(UniqueIdUtil.genId());
					newScript.setActDefId(newActDefId);
					bpmNodeScriptDao.add(newScript);
				}
			}
		}
		
		
		/*//BPM_APPROVAL_ITEM 全局常用语
		TaskApprovalItems globalTApproval=taskApprovalItemsDao.getFlowApproval(oldActDefId, TaskApprovalItems.global);
		if(BeanUtils.isNotEmpty(globalTApproval)){
			globalTApproval.setActDefId(newActDefId);
			globalTApproval.setItemId(UniqueIdUtil.genId());
			taskApprovalItemsDao.add(globalTApproval);
		}*/
		
		//BPM_NODE_SET	流程节点配置 OK
		List<BpmNodeSet> newNodeSetList= bpmNodeSetDao.getByDefId(newDefId);
		Map<String, BpmNodeSet> oldNodeSetMap= bpmNodeSetDao.getMapByDefId(oldDefId);
		if(BeanUtils.isEmpty(newNodeSetList) || BeanUtils.isEmpty(oldNodeSetMap)) return ;
		for(BpmNodeSet bpmNodeSet:newNodeSetList){
			String nodeId=bpmNodeSet.getNodeId();
			if(!oldNodeSetMap.containsKey(nodeId)) continue;
			
			BpmNodeSet oldBpmNodeSet=oldNodeSetMap.get(nodeId);
			Long oldSetId=oldBpmNodeSet.getSetId();
			
			//更新当前的节点配置信息
			bpmNodeSet.setAfterHandler(oldBpmNodeSet.getAfterHandler());
			bpmNodeSet.setBeforeHandler(oldBpmNodeSet.getBeforeHandler());
			bpmNodeSet.setFormDefId(oldBpmNodeSet.getFormDefId());
			bpmNodeSet.setFormDefName(oldBpmNodeSet.getFormDefName());
			bpmNodeSet.setFormKey(oldBpmNodeSet.getFormKey());
			bpmNodeSet.setFormType(oldBpmNodeSet.getFormType());
			bpmNodeSet.setFormUrl(oldBpmNodeSet.getFormUrl());
			bpmNodeSet.setIsHideOption(oldBpmNodeSet.getIsHideOption());
			bpmNodeSet.setIsHidePath(oldBpmNodeSet.getIsHidePath());
			bpmNodeSet.setIsJumpForDef(oldBpmNodeSet.getIsJumpForDef());
			bpmNodeSet.setJoinTaskKey(oldBpmNodeSet.getJoinTaskKey());
			bpmNodeSet.setJoinTaskName(oldBpmNodeSet.getJoinTaskName());
			bpmNodeSet.setJumpType(oldBpmNodeSet.getJumpType());
			bpmNodeSet.setOldFormKey(oldBpmNodeSet.getOldFormKey());
			bpmNodeSetDao.update(bpmNodeSet);
	
			
			//BPM_NODE_RULE	流程节点规则 OK
			List<BpmNodeRule> nodeRuleList=bpmNodeRuleDao.getByDefIdNodeId(oldActDefId, nodeId);
			if(BeanUtils.isNotEmpty(nodeRuleList)){
				for(BpmNodeRule oR:nodeRuleList){
					BpmNodeRule nR=(BpmNodeRule)oR.clone();
					nR.setRuleId(UniqueIdUtil.genId());
					nR.setActDefId(newActDefId);
					bpmNodeRuleDao.add(nR);
				}
			}
			
			//BPM_NODE_SCRIPT	流程节点事件脚本 OK
			List<BpmNodeScript> nodeScriptList= bpmNodeScriptDao.getByBpmNodeScriptId(nodeId,oldActDefId);
			if(BeanUtils.isNotEmpty(nodeScriptList)){
				for(BpmNodeScript oS:nodeScriptList){
					BpmNodeScript nS=(BpmNodeScript)oS.clone();
					nS.setId(UniqueIdUtil.genId());
					nS.setActDefId(newActDefId);
					bpmNodeScriptDao.add(nS);
				}
			}
			
			//BPM_USER_CONDITION 流程节点人员规则 OK
			List<BpmUserCondition> userConditionList = bpmUserConditionDao.getBySetId(oldSetId);
			if(BeanUtils.isNotEmpty(userConditionList)){
				for(BpmUserCondition oC:userConditionList){
					BpmUserCondition nC=(BpmUserCondition)oC.clone();
					nC.setId(UniqueIdUtil.genId());
					nC.setActdefid(newActDefId);
					nC.setSetId(bpmNodeSet.getSetId());
					bpmUserConditionDao.add(nC);
					//BPM_NODE_USER 	流程节点人员 OK
					List<BpmNodeUser> nodeUserList = bpmNodeUserDao.getByConditionId( oC.getId());
					if(BeanUtils.isNotEmpty(nodeUserList)){
						for(BpmNodeUser oU:nodeUserList){
							BpmNodeUser nU=(BpmNodeUser)oU.clone();
							
							nU.setNodeUserId(UniqueIdUtil.genId());
							
							nU.setConditionId(nC.getId());
							bpmNodeUserDao.add(nU);
						}
					}
				}
			}
			
			//BPM_NODE_MESSAGE	流程消息节点 OK
			List<BpmNodeMessage> nodeMessageList=bpmNodeMessageDao.getByActDefId(oldActDefId);
			if(BeanUtils.isNotEmpty(nodeMessageList)){
				for(BpmNodeMessage oM:nodeMessageList){
					BpmNodeMessage nM=(BpmNodeMessage)oM.clone();
					nM.setId(UniqueIdUtil.genId());
					nM.setActDefId(newActDefId);
					bpmNodeMessageDao.add(nM);
				}
			}
			
			//BPM_NODE_SIGN	任务会签设置 OK
			BpmNodeSign nodeSign= bpmNodeSignDao.getByDefIdAndNodeId(oldActDefId,nodeId);
			if(BeanUtils.isNotEmpty(nodeSign)){
				BpmNodeSign newSign=(BpmNodeSign)nodeSign.clone();
				newSign.setSignId(UniqueIdUtil.genId());
				newSign.setActDefId(newActDefId);
				bpmNodeSignDao.add(newSign);
			}
			
			//BPM_NODE_BTN 操作按钮节点设置
			List<BpmNodeButton> nodeButtonList = bpmNodeButtonDao.getByDefNodeId(oldDefId, nodeId);
			if(BeanUtils.isNotEmpty(nodeButtonList)){
				for(BpmNodeButton oB:nodeButtonList){
					BpmNodeButton nB=oB;
					nB.setId(UniqueIdUtil.genId());
					nB.setActdefid(newActDefId);
					nB.setDefId(newDefId);
					bpmNodeButtonDao.add(nB);
				}
			}
			/*//BPM_APPROVAL_ITEM 非全局常用语
			TaskApprovalItems approvalItems=taskApprovalItemsDao.getTaskApproval(oldActDefId, nodeId, TaskApprovalItems.notGlobal);
			if(BeanUtils.isNotEmpty(approvalItems)){
				approvalItems.setActDefId(newActDefId);
				approvalItems.setSetId(bpmNodeSet.getSetId());
				approvalItems.setItemId(UniqueIdUtil.genId());	
				taskApprovalItemsDao.add(approvalItems);
			}*/
			//BPM_TASK_DUE 催办
			
		//	TaskReminder taskReminder = taskReminderDao.getByActDefAndNodeId(oldActDefId, nodeId);
//			TaskReminder taskReminder = null;
			List<TaskReminder> taskReminders = taskReminderDao.getByActDefAndNodeId(oldActDefId, nodeId);
			for(TaskReminder taskReminder:taskReminders){
				taskReminder.setActDefId(newActDefId);
				taskReminder.setTaskDueId(UniqueIdUtil.genId());
				taskReminderDao.add(taskReminder);
			}
		}
		
	}
	
	/**
	 * 同步起始节点表单的配置情况。
	 * @param oldDefId
	 * @param newDefId
	 * @param newActDefId
	 * @throws Exception
	 */
	private void  syncStartGlobal(Long oldDefId,Long newDefId,String newActDefId) throws Exception{
		//同步流程起始表单和默认表单配置。
		List<BpmNodeSet> list=bpmNodeSetDao.getByOther(oldDefId);
		for(BpmNodeSet nodeSet:list){
			nodeSet.setSetId(UniqueIdUtil.genId());
			nodeSet.setDefId(newDefId);
			nodeSet.setActDefId(newActDefId);
			bpmNodeSetDao.add(nodeSet);
		}
	}
	
	

	
	/**
	 * 取得某个流程下的所有历史版本的定义
	 * @param defId
	 * @return
	 */
	public List<BpmDefinition> getAllHistoryVersions(Long defId)
	{
		return dao.getByParentDefIdIsMain(defId,BpmDefinition.NOT_MAIN);
	}
	
	/**
	 * 根据ACT流程定义id获取流程定义。
	 * @param actDefId
	 * @return
	 */
	public BpmDefinition getByActDefId(String actDefId)
	{
		return dao.getByActDefId(actDefId);
	}
	
	/**
	 * 根据分类Id得到流程定义
	 * @param typeId
	 * @return
	 */
	public List<BpmDefinition> getByTypeId(Long typeId)
	{
		return dao.getByTypeId(typeId);
	}
	
	/**
	 * 用于查询管理员下的所有流程
	 * @param queryFilter
	 * @return
	 */
	public List<BpmDefinition> getAllForAdmin(QueryFilter queryFilter)
	{
		return dao.getAllForAdmin(queryFilter);
	}
	
	/**
	 * 设置标题规则。
	 * @param defId
	 * @param taskNameRule
	 * @return
	 */
	public int saveParam(BpmDefinition bpmDefinition){
		return dao.saveParam(bpmDefinition);
	}
	/**
	 * 删除流程定义
	 * @param flowDefId
	 * @param isOnlyVersion 是否仅删除本版本，不包括其他历史版本
	 */
	public void delDefbyDeployId(Long flowDefId,boolean isOnlyVersion){
		
		 if(BeanUtils.isEmpty(flowDefId))return;
	        
        BpmDefinition definition=dao.getById(flowDefId);
        //若该版本尚没有发布
        if(definition.getActDeployId()==null){
        	delById(definition.getDefId());
        	return;
        }
        
        if(isOnlyVersion){//仅删除该版本
        	delBpmDefinition(definition);
        	return;
        }
        
        String actFlowKey=definition.getActDefKey();
        
        List<BpmDefinition> list=dao.getByActDefKey(actFlowKey);
        
       
		//删除流程系统表
		for(BpmDefinition bpmDefinition:list){
		    
			delBpmDefinition(bpmDefinition);
		}
        
		//删除分管授权的流程权限表内容
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("defKey", definition.getDefKey());
		bpmDefActDao.delByMap(params);
		
	}
	
	private void delBpmDefinition(BpmDefinition bpmDefinition){
		Long actDeployId=bpmDefinition.getActDeployId();	
		Long defId=bpmDefinition.getDefId();
		String actDefId=bpmDefinition.getActDefId();
		if(StringUtil.isNotEmpty(actDefId)){
			
			//删除流程运行实例表单数据BPM_FORM_RUN 
			bpmFormRunDao.delByActDefId(actDefId);
			//删除节点操作的按钮BPM_NODE_BTN
			bpmNodeButtonDao.delByActDefId(actDefId);
			//删除节点消息BPM_NODE_MESSAGE
    		bpmNodeMessageDao.delByActDefId(actDefId);
			//删除节点特权BPM_NODE_PRIVILEGE 
			bpmNodePrivilegeDao.delByActDefId(actDefId);
			//删除节点规则BPM_NODE_RULE
    		bpmNodeRuleDao.delByActDefId(actDefId);
    		//删除节点运行脚本BPM_NODE_SCRIPT
    		bpmNodeScriptDao.delByActDefId(actDefId);
    		//关联BPM_NODE_WEBSERVICE删除BPM_NODE_WS_PARAMS
    		List<BpmNodeWebService> bpmNodeWebServiceList =  bpmNodeWebServiceDao.getByActDefId(actDefId);
    		for(BpmNodeWebService bpmNodeWebService:bpmNodeWebServiceList){
    			bpmNodeWsParamsDao.delByWebserviceId(bpmNodeWebService.getId());
    		}
    		//删除webservice节点设置BPM_NODE_WEBSERVICE
    		bpmNodeWebServiceDao.delByActDefId(actDefId);
    		//删除流程节点状态BPM_PRO_STATUS
    		bpmProStatusDao.delByActDefId(actDefId);
    		//删除定义催办信息BPM_TASK_DUE
    		taskReminderDao.delByActDefId(actDefId);
    		//删除分发实体BPM_TASK_FORK
    		taskForkService.delByActDefId(actDefId);
    		//删除流程运行实例BPM_PRO_RUN同时删除交办任务BPM_TASK_ASSIGNEE,BPM_PRO_RUN_HIS,ACT_RU_IDENTITYLINK
    		//ACT_RU_TASK,ACT_RU_VARIABLE,ACT_RU_EXECUTION,BPM_PRO_CPTO,BPM_TASK_EXE,BPM_COMMU_RECEIVER,BPM_TASK_READ
			processRunService.delByActDefId(actDefId);
			//删除流程意见信息BPM_TASK_OPINION 
			taskOpinionDao.delByActDefId(actDefId);
			//删除任务提醒状态数据BPM_TASK_REMINDERSTATE 
			reminderStateDao.delByActDefId(actDefId);
			//关联BPM_USER_CONDITION删除BPM_NODE_USER
			List<BpmUserCondition> bpmUserConditionList = bpmUserConditionDao.getByActDefIdForDel(actDefId);
			for(BpmUserCondition bpmUserCondition:bpmUserConditionList){
				bpmNodeUserDao.delByConditionId(bpmUserCondition.getId());
			}
			//删除节点人员规则BPM_USER_CONDITION 
			bpmUserConditionDao.delByActDefId(actDefId);
    		//删除BPM_EXE_STACK
    		executionStackDao.delByActDefId(actDefId);
    		//删除BPM_TKSIGN_DATA
    		taskSignDataDao.delByIdActDefId(actDefId);
    		//删除BPM_NODE_SIGN会签规则
    		bpmNodeSignDao.delActDefId(actDefId);
    		//删除BPM_REFER_DEFINITION流程引用
    		bpmReferDefinitionDao.delByDefId(defId);
    		//删除BPM_SUBTABLE_RIGHTS子表数据权限
    		bpmSubtableRightsDao.delByActDefId(actDefId);
    		//删除BMP_FORM_RIGHTS表单数据权限
    		bpmFormRightsDao.delByActDefId(actDefId, true, actDefId,PlatformType.undefine);
		}
		if(actDeployId!=null && actDeployId>0){
			//删除删除流程定义数据表ACT_GE_BYTEARRAY，ACT_RE_DEPLOYMENT，ACT_RE_PROCDEF
		    dao.delProcDefByActDeployId(actDeployId);
		}
		
		
		//删除bpm_def_vars
		bpmDefVarsDao.delByDefId(defId);
		//删除流程(包括直接子流程)的节点数据BPM_NODE_SET 
		bpmNodeSetDao.delByDefId(defId, actDefId);
		
		dao.delById(defId);
	}
	

	
	// =========== TODO 导入数据库====
	/**
	 * 导入压缩文件
	 * @param fileLoad
	 * @throws Exception
	 */
	public void importZip(MultipartFile fileLoad) throws Exception {

		String realFilePath = StringUtil.trimSufffix(
				ServiceUtil.getBasePath().toString(), File.separator)
				+ File.separator
				+ "attachFiles"
				+ File.separator
				+ "tempUnZip"
				+ File.separator;
		// 解压文件
		String fileDir = ZipUtil.unZipFile(fileLoad, realFilePath);
		MsgUtil.addFilePath(realFilePath);
		realFilePath = realFilePath+fileDir+File.separator ;
		String xmlStr = FileUtil.readFile(realFilePath+fileDir+ ".flow.xml");
		if (StringUtils.isEmpty(xmlStr))
			throw new Exception("导入的未按指定的格式");

		Document doc = Dom4jUtil.loadXml(xmlStr);
		Element root = doc.getRootElement();
		// 验证格式是否正确
		XmlUtil.checkXmlFormat(root, "bpm", "bpmDefinitions");
		this.importXml(xmlStr, realFilePath);
		// 删除临时解压文件目录
		FileUtil.deleteDir(new File(realFilePath));
		
	}
	
	/**
	 * 导入数据库
	 * 
	 * @param importFilePath
	 * 
	 * @param inputStream
	 * @param bpmDefinition
	 * @throws Exception
	 */
	public void importXml(String xmlStr, String unzipFilePath) throws Exception {
		BpmDefinitionXmlList bpmDefinitionXmlList = (BpmDefinitionXmlList) XmlBeanUtil
				.unmarshall(xmlStr, BpmDefinitionXmlList.class);
		List<BpmDefinitionXml> list = bpmDefinitionXmlList
				.getBpmDefinitionXmlList();
		// 保存相关信息
		for (BpmDefinitionXml bpmDefinitionXml : list) {
			this.importBpmDefinitionXml(bpmDefinitionXml, unzipFilePath,null);
			MsgUtil.addSplit();
		}

	}

	/**
	 * 解析相关信息，导入相关list并保持数据库
	 * 
	 * @param bpmDefinitionXml
	 * @param unzipFilePath
	 * @param flag
	 * @throws Exception
	 */
	private void importBpmDefinitionXml(BpmDefinitionXml bpmDefinitionXml,
			String unzipFilePath,String parentActDefId) throws Exception {

		// 导入自定义表
		List<BpmFormTableXml> bpmFormTableXmlList = bpmDefinitionXml
				.getBpmFormTableXmlList();
		if (BeanUtils.isNotEmpty(bpmFormTableXmlList)) {
			Set<Identity> identitySet = new HashSet<Identity>();

			for (BpmFormTableXml bpmFormTableXml : bpmFormTableXmlList) {
				// 导入表，并解析相关信息
				bpmFormTableService.importBpmFormTableXml(bpmFormTableXml);
				bpmFormTableService.setIdentity(
						bpmFormTableXml.getIdentityList(), identitySet);
			}
			bpmFormTableService.importIdentity(identitySet);
		}
		List<Map<Long, Long>> mapFormKeyList = new ArrayList<Map<Long, Long>>();
		//
		List<Map<Long, Long>> mapFormIdList = new ArrayList<Map<Long, Long>>();
		// 导入自定义表单
		List<BpmFormDefXml> bpmFormDefXmlList = bpmDefinitionXml.getBpmFormDefXmlList();
		if (BeanUtils.isNotEmpty(bpmFormDefXmlList))
			for (BpmFormDefXml bpmFormDefXml : bpmFormDefXmlList) {
				Map<String, Map> mapForm = bpmFormDefService.importBpmFormDef(bpmFormDefXml);
				Map mapFormKey = mapForm.get("key");
				mapFormKeyList.add(mapFormKey);
				Map<Long, Long> mapFormId = mapForm.get("id");
				mapFormIdList.add(mapFormId);
			}
		// 导入流程定义
		this.importBpmDefinition(bpmDefinitionXml, mapFormKeyList,
				mapFormIdList, unzipFilePath,parentActDefId);
	}

	/**
	 * 解析相关信息，导入相关list并保持数据库
	 * 
	 * <pre>
	 * 导入以下信息:
	 * ■ 流程定义 bpmDefinition
	 * ■ 历史版本 bpmDefinitionHistory
	 * 	
	 * ■ 流程节点设置 bpmNodeSet
	 * ■ 节点下的人员设置  bpmNodeUser
	 * ■ 节点下的人员的配置规则 bpmUserCondition
	 * ■ 节点下的人员上下级设置 bpmNodeUserUplow
	 * 	
	 * ■ 流程定义权限 bpmDefRights
	 * ■ 常用语设置 taskApprovalItems
	 * 	
	 * ■ 流程跳转规则  bpmNodeRule
	 * ■ 流程事件脚本  bpmNodeScript
	 * 	
	 * ■ 流程操作按钮设置 bpmNodeButton
	 * ■ 流程变量  bpmDefVar
	 * 	 
	 * ■ 流程消息  bpmNodeMessage
	 * ■ 流程会签规则  bpmNodeSign
	 * 
	 * ■ 任务节点催办时间设置 taskReminder
	 * ■ 内（外）部子流程 subBpmDefinition
	 * </pre>
	 * 
	 * @param bpmDefinitionXml
	 *            导入的流程定义的xml
	 * @param mapFormKeyList
	 * @param mapFormIdList
	 * @param unzipFilePath
	 * @param parentDefId
	 *            主流程定义的id
	 * @param flag
	 * @throws Exception
	 */
	private void importBpmDefinition(BpmDefinitionXml bpmDefinitionXml,
			List<Map<Long, Long>> mapFormKeyList,
			List<Map<Long, Long>> mapFormIdList, String unzipFilePath,
			String parentActDefId)
			throws Exception {
		// 附件
		List<SysFile> sysFileList = bpmDefinitionXml.getSysFileList();
		if (BeanUtils.isNotEmpty(sysFileList))
			this.importSysFile(sysFileList, unzipFilePath);
		BpmDefinition bpmDefinition = bpmDefinitionXml.getBpmDefinition();
		// 流程定义
		bpmDefinition = this.importDefinition(bpmDefinition);
		String actDefId = bpmDefinition.getActDefId();
		Long defId = bpmDefinition.getDefId();
		String defKey = bpmDefinition.getDefKey();

		// Long actDeployId = bpmDefinition.getActDeployId();
		// 流程定义历史版本
//		List<BpmDefinitionXml> bpmDefinitionXmlList = bpmDefinitionXml.getBpmDefinitionXmlList();
//		if (BeanUtils.isNotEmpty(bpmDefinitionXmlList)) {
//			// 历史版本版本导入
//			for (BpmDefinitionXml definitionXml : bpmDefinitionXmlList) {
//				this.importBpmDefinition(definitionXml, null,null,null);
//			}
//		}

		// 含有子流程
		 List<BpmDefinitionXml> subBpmDefinitionXmlList = bpmDefinitionXml
		 .getSubBpmDefinitionXmlList();
		
		 if (BeanUtils.isNotEmpty(subBpmDefinitionXmlList)) {
		 // 子流程导入
			 for (BpmDefinitionXml definitionXml : subBpmDefinitionXmlList) {
				 this.importBpmDefinitionXml(definitionXml, unzipFilePath,actDefId);
			 }
		 }

	

		// 表单权限
		List<BpmFormRights> bpmFormRightsList = bpmDefinitionXml.getBpmFormRightsList();
		if (BeanUtils.isNotEmpty(bpmFormRightsList))
			this.importBpmFormRights(bpmFormRightsList, mapFormKeyList,actDefId,parentActDefId);
		
		// 流程跳转规则
		List<BpmNodeRule> bpmNodeRuleList = bpmDefinitionXml
				.getBpmNodeRuleList();
		if (BeanUtils.isNotEmpty(bpmNodeRuleList))
			this.importBpmNodeRule(bpmNodeRuleList, actDefId);

		// 流程事件脚本
		List<BpmNodeScript> bpmNodeScriptList = bpmDefinitionXml
				.getBpmNodeScriptList();
		if (BeanUtils.isNotEmpty(bpmNodeScriptList))
			this.importBpmNodeScript(bpmNodeScriptList, actDefId);

		// 流程变量
		List<BpmDefVar> bpmDefVarList = bpmDefinitionXml.getBpmDefVarList();
		if (BeanUtils.isNotEmpty(bpmDefVarList))
			this.importBpmDefVar(bpmDefVarList, defId);

		// 流程会签规则
		List<BpmNodeSign> bpmNodeSignList = bpmDefinitionXml
				.getBpmNodeSignList();
		if (BeanUtils.isNotEmpty(bpmNodeSignList))
			this.importBpmNodeSign(bpmNodeSignList, actDefId);

		// 流程消息
		List<BpmNodeMessage> bpmNodeMessageList = bpmDefinitionXml
				.getBpmNodeMessageList();
		if (BeanUtils.isNotEmpty(bpmNodeMessageList))
			this.importBpmNodeMessage(bpmNodeMessageList, actDefId);
//		 List<Message> messageList =
//		 messageDao.getByActDefId(actDefId);

		// 导入节点的相关信息（包含：节点，节点人员条件，节点人员，节点人员的上下级，常用语）
		this.importBpmNodeSet(bpmDefinitionXml, defId, actDefId, mapFormIdList,parentActDefId);

		// 流程操作按钮设置
		List<BpmNodeButton> bpmNodeButtonList = bpmDefinitionXml
				.getBpmNodeButtonList();
		if (BeanUtils.isNotEmpty(bpmNodeButtonList))
			this.importBpmNodeButton(bpmNodeButtonList, defId, actDefId);

		// 任务节点催办时间设置
		List<TaskReminder> taskReminderList = bpmDefinitionXml
				.getTaskReminderList();
		if (BeanUtils.isNotEmpty(taskReminderList))
			this.importTaskReminder(taskReminderList, actDefId);

		// 流程联动设置
		List<BpmGangedSet> bpmGangedSetList = bpmDefinitionXml
				.getBpmGangedSetList();
		if (BeanUtils.isNotEmpty(bpmGangedSetList))
			this.importBpmGangedSet(bpmGangedSetList, defId);
//		//导入流程引用
//		List<BpmReferDefinition> bpmReferDefinitionList=bpmDefinitionXml.
//				getBpmReferDefinitionList();
//		if (BeanUtils.isNotEmpty(bpmReferDefinitionList)) {
//			this.importBpmReferDefinition(bpmReferDefinitionList);
//		}
	}

	/**
	 * 流程联动设置
	 * 
	 * @param bpmGangedSetList
	 * @param defId
	 */
	private void importBpmGangedSet(List<BpmGangedSet> bpmGangedSetList,
			Long defId) {
		for (BpmGangedSet bpmGangedSet : bpmGangedSetList) {
			Long id = bpmGangedSet.getId();
//			Object [] args= {id};
			BpmGangedSet gangedSet = bpmGangedSetDao.getById(id);
			if (BeanUtils.isEmpty(gangedSet)) {
				bpmGangedSet.setDefid(defId);
				bpmGangedSetDao.add(bpmGangedSet);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程联动设置,ID:" + id + ",该记录成功导入!");
			} else {
				bpmGangedSet.setId(UniqueIdUtil.genId());
				bpmGangedSet.setDefid(defId);
				bpmGangedSetDao.add(bpmGangedSet);
				MsgUtil.addMsg(MsgUtil.WARN, "流程联动设置,ID:" + id
						+ "已经存在,重新发布新版本!");
			}
		}
	}
	
/*/**
	 * 流程引用的导入
	 * @param bpmReferDefinitionList
	 * @param actDefId
	 *//*
	private void importBpmReferDefinition(List<BpmReferDefinition> bpmReferDefinitionList) throws Exception {
		for (BpmReferDefinition bpmReferDefinition : bpmReferDefinitionList) {
			Long id=bpmReferDefinition.getDefId();
			BpmReferDefinition referDefinition=bpmReferDefinitionService.getById(id);
			if (BeanUtils.isEmpty(referDefinition)) {
				bpmReferDefinitionDao.add(bpmReferDefinition);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程引用设置,ID:" + id + ",该记录成功导入！");
			}else {
				MsgUtil.addMsg(MsgUtil.WARN, "流程引用设置,ID:" + id
						+ "已经存在,该记录终止导入！");
			}
			
		}
	}
*/
	/**
	 * 表单权限
	 * 
	 * @param bpmFormRightsList
	 * @param mapFormKeyList
	 * @param actDefId
	 * @throws Exception
	 */
	private void importBpmFormRights(List<BpmFormRights> bpmFormRightsList,
			List<Map<Long, Long>> mapFormKeyList, String actDefId,String parentActDefId)
			throws Exception {
		bpmFormRightsDao.delByActDefId(actDefId, true,PlatformType.undefine);
		for (BpmFormRights bpmFormRights : bpmFormRightsList) {
			Long formKey = getFormKey(mapFormKeyList,
					bpmFormRights.getFormDefId());
			if (BeanUtils.isEmpty(formKey))
				continue;
			this.importBpmFormRights(bpmFormRights, formKey, actDefId,parentActDefId);
		}

	}
	private Long getFormKey(List<Map<Long, Long>> mapFormKeyList, Long formKey) {
		Long deseFormKey = null;
		for (Map<Long, Long> map : mapFormKeyList) {
			deseFormKey = map.get(formKey);
			if (BeanUtils.isNotEmpty(deseFormKey))
				break;
		}
		return formKey;
	}
	
	/**
	 * 保存 表单权限
	 * 
	 * @param bpmFormRights
	 * @param formKey
	 * @param formDefId
	 * @param msg
	 * @return
	 */
	private void importBpmFormRights(BpmFormRights bpmFormRights, Long formKey,
			String actDefId,String parentActDefId) throws Exception {
		BpmFormRights formRights = bpmFormRightsDao.getById(bpmFormRights
				.getId());
		bpmFormRights.setFormDefId(formKey);
		bpmFormRights.setActDefId(actDefId);
		if (StringUtil.isNotEmpty(bpmFormRights.getParentActDefId())) {
			bpmFormRights.setParentActDefId(parentActDefId);
		}
		if(bpmFormRights.getType() != BpmFormRights.TableShowRights)
			bpmFormRights.setPermission(bpmFormDefService.parsePermission(
				bpmFormRights.getPermission(), false));
		if (BeanUtils.isNotEmpty(formRights)) {
			bpmFormRightsDao.update(bpmFormRights);
			MsgUtil.addMsg(MsgUtil.WARN,
					"表单权限已经存在,表单权限ID：" + bpmFormRights.getId()
							+ ",已经存在，表单权限进行更新!");
		} else {
			bpmFormRightsDao.add(bpmFormRights);
			MsgUtil.addMsg(MsgUtil.SUCCESS,
					" 表单权限:表单权限ID:" + bpmFormRights.getId() + ",该记录成功导入!");
		}
	}
	
	/**
	 * 附件
	 * 
	 * @param sysFileList
	 * @param unzipFilePath
	 */
	private void importSysFile(List<SysFile> sysFileList, String unzipFilePath) {
		String realPath = StringUtil.trimSufffix(
				ServiceUtil.getBasePath().toString(), File.separator);
		for (SysFile sysFile : sysFileList) {
			Long id = sysFile.getFileId();
			SysFile file = sysFileService.getById(id);
			sysFile = this.parseSysFile(sysFile, realPath, unzipFilePath);
			if (BeanUtils.isEmpty(file)) {
				sysFileService.add(sysFile);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "附件,ID:" + id + ",该记录成功导入!");
			} else {
				BeanUtils.copyNotNullProperties(file, sysFile);
				file.setCreatetime(sysFile.getCreatetime());
				sysFileService.update(file);
				MsgUtil.addMsg(MsgUtil.WARN, "附件,ID:" + id + "已经存在,该记录进行更新!");
			}
		}

	}
	
	/**
	 * 处理附件
	 * 
	 * @param sysFile文件
	 * @param realPath
	 *            文件路径
	 * @param unzipFilePath
	 *            解压的文件路径
	 * @return
	 */
	private SysFile parseSysFile(SysFile sysFile, String realPath,
			String unzipFilePath) {
		SysUser sysUser = ContextUtil.getCurrentUser();
		sysFile.setCreatorId(sysUser.getUserId());
		sysFile.setCreator(sysUser.getAccount());

		this.copyToSysFile(sysFile, realPath, unzipFilePath);
		return sysFile;
	}
	
	/**
	 * 把文件复制到指定路径
	 * 
	 * @param filePath
	 * @param path
	 * @param unzipFilePath
	 */
	private void copyToSysFile(SysFile sysFile, String realPath,
			String unzipFilePath) {
		try {
			realPath = realPath + File.separator
					+ sysFile.getFilePath().replace("/", File.separator);
			String fileName = sysFile.getFileId() + "." + sysFile.getExt();
			String filePath = unzipFilePath + fileName;
			// 复制到指定文件
			File file = new File(filePath);
			if (file.exists()) {
				FileUtil.createFolderFile(realPath);
				FileUtil.copyFile(filePath, realPath);
			} else {
				MsgUtil.addMsg(MsgUtil.ERROR, "导入附件中不存在:" + fileName + ",请检查!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导入流程定义
	 * 
	 * @param bpmDefinition
	 * 
	 * @return
	 */
	private BpmDefinition importDefinition(BpmDefinition bpmDefinition)
			throws Exception {
		// 处理xml
		bpmDefinition = parseBpmDefinition(bpmDefinition, false);
		String actFlowDefXml = BpmUtil.transform(bpmDefinition.getDefKey(),
				bpmDefinition.getSubject(), bpmDefinition.getDefXml());
		this.saveBpmDefinition(bpmDefinition, actFlowDefXml);
		return bpmDefinition;
	}
	
	/**
	 * 导入 节点，节点人员条件，节点人员，节点人员的上下级，常用语
	 * 
	 * @param bpmDefinitionXml
	 * @param defId
	 * @param actDefId
	 * @param mapFormKeyList
	 * @param parentActDefId
	 * @throws Exception
	 */
	private void importBpmNodeSet(BpmDefinitionXml bpmDefinitionXml,
			Long defId, String actDefId, List<Map<Long, Long>> mapFormIdList,String parentActDefId)
			throws Exception {
		// 流程节点设置
		List<BpmNodeSet> bpmNodeSetList = bpmDefinitionXml.getBpmNodeSetList();
		// 常用语设置
//		List<TaskApprovalItems> taskApprovalItemsList = bpmDefinitionXml
//				.getTaskApprovalItemsList();
		// 节点规则
		List<BpmUserCondition> bpmUserConditionList = bpmDefinitionXml
				.getBpmUserConditionList();

	//	this.parseBpmUserCondition(bpmUserConditionList, mapFormIdList);

		// 节点下的人员设置
		List<BpmNodeUser> bpmNodeUserList = bpmDefinitionXml
				.getBpmNodeUserList();

		// 用户节点的上下级设置
		List<BpmNodeUserUplow> bpmNodeUserUplowList = bpmDefinitionXml
				.getBpmNodeUserUplowList();

		// 流程人员条件设置
		List<BpmUserCondition> userConditionList = new ArrayList<BpmUserCondition>();
		// 抄送的人员条件
		List<BpmUserCondition> copyToUserConditionList = new ArrayList<BpmUserCondition>();

		// 其他类型人员条件
		List<BpmUserCondition> otherUserConditionList = new ArrayList<BpmUserCondition>();
		if (BeanUtils.isNotEmpty(bpmUserConditionList)) {
			for (BpmUserCondition bpmUserCondition : bpmUserConditionList) {
				
				if (bpmUserCondition.getConditionType().shortValue() == BpmUserCondition.CONDITION_TYPE_EXEUSER){
					userConditionList.add(bpmUserCondition);
				}else if (bpmUserCondition.getConditionType().shortValue() == BpmUserCondition.CONDITION_TYPE_COPYUSER){
					copyToUserConditionList.add(bpmUserCondition);
				}else{ 
					otherUserConditionList.add(bpmUserCondition);
				}
					
			}
		}

		//TODO 这里导入人员有些问题，需要修改。
		// 节点人员
		List<BpmNodeUser> nodeUserList = new ArrayList<BpmNodeUser>();
		// 抄送的人员
		List<BpmNodeUser> otherBpmNodeUserList = new ArrayList<BpmNodeUser>();
		if (BeanUtils.isNotEmpty(bpmNodeUserList)) {
			for (BpmNodeUser bpmNodeUser : bpmNodeUserList) {
				Long  conditionId=bpmNodeUser.getConditionId();
				for (BpmUserCondition bpmUserCondition : bpmUserConditionList) {
					if (conditionId.longValue() ==bpmUserCondition.getId().longValue()) {
						if (BeanUtils.isNotEmpty(bpmUserCondition.getSetId())
								&& bpmUserCondition.getSetId() > 0)
							nodeUserList.add(bpmNodeUser);
						else
							otherBpmNodeUserList.add(bpmNodeUser);
					}
				}
				

				
			}
		}

		// 导入节点、规则条件、人员
		for (BpmNodeSet bpmNodeSet : bpmNodeSetList) {
			Long origSetId = bpmNodeSet.getSetId();
			// 流程节点设置
			Long destSetId = this.importBpmNodeSet(bpmNodeSet, defId, actDefId,
					origSetId,parentActDefId);
			// 规则条件
			this.importBpmUserConditionInfo(userConditionList, nodeUserList,
					bpmNodeUserUplowList, actDefId,
					origSetId, destSetId,parentActDefId);
		}
		
		// 抄送
		if (BeanUtils.isNotEmpty(copyToUserConditionList)) {
			this.importBpmUserConditionBpmNodeUser(copyToUserConditionList, actDefId,
					otherBpmNodeUserList, bpmNodeUserUplowList, 0L,parentActDefId);
		}
		
		// 其他类型人员设置导入
		if (BeanUtils.isNotEmpty(otherUserConditionList)) {
			this.importBpmUserConditionBpmNodeUser(otherUserConditionList, actDefId,
					otherBpmNodeUserList, bpmNodeUserUplowList, 0L,parentActDefId);
		}

	}
	
	/**
	 * 人员设置导入
	 * @param bpmUserConditionList
	 * @param actDefId
	 * @param bpmNodeUserList
	 * @param bpmNodeUserUplowList
	 * @param bpmComsiteUserList
	 * @param destSetId
	 * @return
	 * @throws Exception
	 */
	private Map<Long, Long> importBpmUserConditionBpmNodeUser(
			List<BpmUserCondition> bpmUserConditionList, String actDefId,
			List<BpmNodeUser> bpmNodeUserList,
			List<BpmNodeUserUplow> bpmNodeUserUplowList,Long destSetId,String parentActDefId) throws Exception {
		Map<Long, Long> conditionMap = new HashMap<Long, Long>();
		if(BeanUtils.isEmpty(bpmUserConditionList))
			return conditionMap;
			
		for (BpmUserCondition bpmUserCondition : bpmUserConditionList) {
			Long origConditionId = bpmUserCondition.getId();
			Long conditionId = this.importBpmUserCondition(bpmUserCondition, actDefId, destSetId,parentActDefId);
			this.importBpmNodeUserInfo(bpmNodeUserList,
					bpmNodeUserUplowList,
					origConditionId, conditionId, actDefId, destSetId);
			conditionMap.put(origConditionId, conditionId);
		}
		return conditionMap;
	}
	
	
	/**
	 * 处理bpmUserCondition
	 * 
	 * @param bpmUserConditionList
	 * @param mapFormKeyList
	 */
	private void parseBpmUserCondition(
			List<BpmUserCondition> bpmUserConditionList,
			List<Map<Long, Long>> mapFormIdList) {
		if(BeanUtils.isEmpty(bpmUserConditionList))
			return;
		for (BpmUserCondition bpmUserCondition : bpmUserConditionList) {
			if (StringUtil.isEmpty(bpmUserCondition.getFormIdentity()))
				continue;
//			String formIdentity = getFormKey(mapFormIdList,
//					bpmUserCondition.getFormIdentity());
//			if (BeanUtils.isNotEmpty(formId))
//				bpmUserCondition.setTableId(formId);

		}

	}
	
	/**
	 * 导入人员规则条件设置
	 * 
	 * @param userConditionList
	 * @param nodeUserList
	 * @param bpmNodeUserUplowList
	 * @param bpmComsiteUserList
	 * @param actDefId
	 * @param origSetId
	 * @param destSetId
	 * @throws Exception
	 */
	private void importBpmUserConditionInfo(
			List<BpmUserCondition> userConditionList,
			List<BpmNodeUser> nodeUserList,
			List<BpmNodeUserUplow> bpmNodeUserUplowList,String actDefId,
			Long origSetId, Long destSetId,String parentActDefId) throws Exception {
		// 人员规则条件
		for (BpmUserCondition bpmUserCondition : userConditionList) {
			if (BeanUtils.isEmpty(bpmUserCondition.getSetId()))
				continue;
			if (origSetId.longValue() == bpmUserCondition.getSetId().longValue()) {
				Long origConditionId = bpmUserCondition.getId();
				Long conditionId = this.importBpmUserCondition(
						bpmUserCondition, actDefId, destSetId,parentActDefId);

				this.importBpmNodeUserInfo(nodeUserList, bpmNodeUserUplowList, origConditionId, conditionId,
						actDefId, destSetId);
			}
		}
	}
	
	/**
	 * 导入人员设置
	 * 
	 * @param nodeUserList
	 * @param bpmNodeUserUplowList
	 * @param bpmComsiteUserList
	 * @param origConditionId
	 * @param userConditionId
	 * @param actDefId
	 * @param destSetId
	 * @throws Exception
	 */
	private void importBpmNodeUserInfo(List<BpmNodeUser> nodeUserList,
			List<BpmNodeUserUplow> bpmNodeUserUplowList, Long origConditionId,
			Long conditionId, String actDefId, Long destSetId) throws Exception {
		for (BpmNodeUser bpmNodeUser : nodeUserList) {
			if (BeanUtils.isEmpty(bpmNodeUser.getConditionId()))
				continue;
			if (origConditionId.longValue() == bpmNodeUser.getConditionId()
					.longValue()) {
				Long origNodeUserId = bpmNodeUser.getNodeUserId();
				// 节点下的人员设置
				Long nodeUserId = this.importBpmNodeUser(bpmNodeUser, actDefId,
						destSetId, conditionId);
				// if (BeanUtils.isEmpty(bpmNodeUserUplowList))
				// 用户节点的上下级设置
				// for (BpmNodeUserUplow bpmNodeUserUplow :
				// bpmNodeUserUplowList) {
				// if (origNodeUserId.longValue() == bpmNodeUserUplow
				// .getNodeUserId().longValue())
				// // 用户节点的上下级设置
				// this.importBpmNodeUserUplow(bpmNodeUserUplow,
				// nodeUserId);
				//
				// }
//				if (BeanUtils.isNotEmpty(bpmComsiteUserList)) {
//					for (BpmComsiteUser bpmComsiteUser : bpmComsiteUserList) {
//						if (origNodeUserId.longValue() == bpmComsiteUser
//								.getNodeuserid().longValue())
//							// 组合条件
//							this.importBpmComsiteUser(bpmComsiteUser,
//									nodeUserId);
//
//					}
//				}
			}
		}
	}

	
	/**
	 * 流程规则
	 * 
	 * @param bpmUserCondition
	 * @param actDefId
	 * @param setId
	 */
	private Long importBpmUserCondition(BpmUserCondition bpmUserCondition,
			String actDefId, Long setId,String parentActDefId) {
		String msg="";
		Long id = bpmUserCondition.getId();
		BpmUserCondition userCondition = bpmUserConditionDao.getById(id);
		if (BeanUtils.isEmpty(userCondition)) {
			msg = "流程规则,ID:" + id + ",该记录成功导入!";
		} else {
			id = UniqueIdUtil.genId();
			bpmUserCondition.setId(id);
			msg = "流程规则,ID:" + id + "已经存在,重新发布新版本!";
		}
		//如果是子流程的人员设置，则更新最新的父流程的actDefId
		if (StringUtil.isNotEmpty(bpmUserCondition.getParentActDefId())) {
			bpmUserCondition.setParentActDefId(parentActDefId);
		}
		bpmUserCondition.setActdefid(actDefId);
		bpmUserCondition.setSetId(setId);
		bpmUserConditionDao.add(bpmUserCondition);
		MsgUtil.addMsg(MsgUtil.WARN, msg);
		return id;

	}
	
	/**
	 * 常用语设置
	 * 
	 * @param taskApprovalItems
	 * @param actDefId
	 */
	@SuppressWarnings("unused")
	private void importTaskApprovalItems(TaskApprovalItems taskApprovalItems,
			String actDefId) throws Exception {
	/*	Long id = taskApprovalItems.getItemId();
		TaskApprovalItems approvalItems = taskApprovalItemsDao.getById(id);
		Object[] args={id};
		if (BeanUtils.isEmpty(approvalItems)) {
			taskApprovalItems.setActDefId(actDefId);
			taskApprovalItemsDao.add(taskApprovalItems);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "常用语设置,ID:" + id + ",该记录成功导入!");
		} else {
			taskApprovalItems.setItemId(UniqueIdUtil.genId());
			taskApprovalItems.setActDefId(actDefId);
			taskApprovalItemsDao.add(taskApprovalItems);
			MsgUtil.addMsg(MsgUtil.WARN, "常用语设置,ID:" + id + "已经存在,重新发布新版本!");
		}*/
	}

	/**
	 * 保存及更新流程定义
	 * 
	 * @param bpmDefinition
	 *            流程定义实体
	 * @param actFlowDefXml
	 *            流程定义bpmn文档
	 * @throws Exception
	 */
	private void saveBpmDefinition(BpmDefinition bpmDefinition,
			String actFlowDefXml) throws Exception {
		Long id = bpmDefinition.getDefId();
		BpmDefinition definition = getMainByDefKey(bpmDefinition.getDefKey());     // getById(id);///应该getByKey
		long newDefId=UniqueIdUtil.genId();
		// 新增加的流程
		Deployment deployment = bpmService.deploy(
				bpmDefinition.getSubject(), actFlowDefXml);
		ProcessDefinitionEntity ent = bpmService
				.getProcessDefinitionByDeployId(deployment.getId());
		// 设置分类
		bpmDefinition = this.setTypeId(bpmDefinition);
		if (BeanUtils.isEmpty(definition)) {
			bpmDefinition.setDefId(newDefId);
			bpmDefinition.setActDeployId(new Long(deployment.getId()));
			bpmDefinition.setActDefId(ent.getId());
			bpmDefinition.setActDefKey(ent.getKey());
			//bpmDefinition.setStatus(BpmDefinition.STATUS_ENABLED);
			bpmDefinition.setUpdatetime(new Date());
			bpmDefinition.setIsMain(BpmDefinition.MAIN);
			bpmDefinition.setVersionNo(1);
			add(bpmDefinition);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "流程定义:" + bpmDefinition.getSubject() + ",该记录成功导入！");
		} else {
			dao.updateSubVersions(newDefId,bpmDefinition.getDefKey());
			bpmDefinition.setDefId(newDefId);
			bpmDefinition.setActDeployId(new Long(deployment.getId()));
			bpmDefinition.setActDefId(ent.getId());
			bpmDefinition.setActDefKey(ent.getKey());
			//bpmDefinition.setStatus(BpmDefinition.STATUS_ENABLED);
			bpmDefinition.setUpdatetime(new Date());
			bpmDefinition.setIsMain(BpmDefinition.MAIN);
			bpmDefinition.setVersionNo(ent.getVersion());
			add(bpmDefinition);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "流程定义:" + bpmDefinition.getSubject() + ",已经存在,重新发布新版本!");

		}

	}


	/**
	 * 设置分类
	 * 
	 * @param bpmFormDef
	 * @return
	 */
	private BpmDefinition setTypeId(BpmDefinition bpmDefinition)
			throws Exception {
		if (BeanUtils.isEmpty(bpmDefinition.getTypeId()))
			return bpmDefinition;
		GlobalType globalType = globalTypeDao
				.getById(bpmDefinition.getTypeId());
		if (BeanUtils.isEmpty(globalType))
			bpmDefinition.setTypeId(null);
		return bpmDefinition;
	}

	/**
	 * 任务节点催办时间设置
	 * 
	 * @param taskReminderList
	 * @param actDefId
	 */
	private void importTaskReminder(List<TaskReminder> taskReminderList,
			String actDefId) throws Exception {
		for (TaskReminder taskReminder : taskReminderList) {
			Long id = taskReminder.getTaskDueId();
			taskReminder.setMailContent(parseScript(taskReminder.getMailContent(), false));
			taskReminder.setMsgContent(parseScript(taskReminder.getMsgContent(), false));
			taskReminder.setSmsContent(parseScript(taskReminder.getSmsContent()	, false));
			taskReminder.setCondExp(parseScript(taskReminder.getCondExp(), false));
			taskReminder.setScript(parseScript(taskReminder.getScript(), false));
			TaskReminder reminder = taskReminderDao.getById(id);
			if (BeanUtils.isEmpty(reminder)) {
				taskReminder.setActDefId(actDefId);
				taskReminderDao.add(taskReminder);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "任务节点催办时间设置,ID:" + id
						+ ",该记录成功导入！");
			} else {
				taskReminder.setTaskDueId(UniqueIdUtil.genId());
				taskReminder.setActDefId(actDefId);
				taskReminderDao.add(taskReminder);
				MsgUtil.addMsg(MsgUtil.WARN, "任务节点催办时间设置,ID:" + id
						+ "已经存在,重新发布新版本!");
			}
		}
	}

	/**
	 * 流程操作按钮设置
	 * 
	 * @param bpmNodeButtonList
	 * @param defId
	 * @param actDefId
	 */
	private void importBpmNodeButton(List<BpmNodeButton> bpmNodeButtonList,
			Long defId, String actDefId) throws Exception {
		for (BpmNodeButton bpmNodeButton : bpmNodeButtonList) {
			Long id = bpmNodeButton.getId();
			BpmNodeButton nodeButton = bpmNodeButtonDao.getById(id);
			if (BeanUtils.isEmpty(nodeButton)) {
				bpmNodeButton.setDefId(defId);
				bpmNodeButton.setActdefid(actDefId);
				bpmNodeButtonDao.add(bpmNodeButton);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程操作按钮设置,ID:" + id
						+ ",该记录成功导入！");
			} else {
				bpmNodeButton.setId(UniqueIdUtil.genId());
				bpmNodeButton.setDefId(defId);
				bpmNodeButton.setActdefid(actDefId);
				bpmNodeButtonDao.add(bpmNodeButton);
				MsgUtil.addMsg(MsgUtil.WARN, "流程操作按钮设置,ID:" + id
						+ "已经存在,重新发布新版本!");
			}
		}
	}

	/**
	 * 流程节点设置
	 * 
	 * @param bpmNodeSetList
	 * @param bpmNodeUserList
	 * @param bpmNodeUserUplowList
	 * @param taskApprovalItemsList
	 * @param defId
	 * @param actDefId
	 * @param formKeyMap
	 */
	@SuppressWarnings("unused")
	private void importBpmNodeSet(List<BpmNodeSet> bpmNodeSetList,
			List<BpmNodeUser> bpmNodeUserList,
			List<TaskApprovalItems> taskApprovalItemsList, Long defId,
			String actDefId) throws Exception {

		/*List<TaskApprovalItems> approvalItemsList = new ArrayList<TaskApprovalItems>();
		if (BeanUtils.isNotEmpty(taskApprovalItemsList)) {
			for (TaskApprovalItems taskApprovalItems : taskApprovalItemsList) {
				if (BeanUtils.isNotEmpty(taskApprovalItems.getSetId()))
					approvalItemsList.add(taskApprovalItems);
				else
					this.importTaskApprovalItems(taskApprovalItems, actDefId,
							null);
			}
		}*/
		//TODO 这里导入人员有些问题。
		for (BpmNodeSet bpmNodeSet : bpmNodeSetList) {
			Long setId = bpmNodeSet.getSetId();
			// 流程节点设置
			Long nodeSetId = this.importBpmNodeSet(bpmNodeSet, defId, actDefId,
					setId,null);

			for (BpmNodeUser bpmNodeUser : bpmNodeUserList) {
				
					Long userId = bpmNodeUser.getNodeUserId();
					// 节点下的人员设置
					Long nodeUserId = this.importBpmNodeUser(bpmNodeUser);
//					if(bpmNodeUserUplowList!=null){
//						for (BpmNodeUserUplow bpmNodeUserUplow : bpmNodeUserUplowList) {
//							if (userId.longValue() == bpmNodeUserUplow
//									.getNodeUserId().longValue()) {
//								// 用户节点的上下级设置
//								this.importBpmNodeUserUplow(bpmNodeUserUplow,
//										nodeUserId);
//							}
//						}
//					}
				
			}
			/*// 常用语设置
			for (TaskApprovalItems taskApprovalItems : approvalItemsList) {
				if (setId.longValue() == taskApprovalItems.getSetId()
						.longValue()) {
					this.importTaskApprovalItems(taskApprovalItems, actDefId,
							nodeSetId);
				}
			}*/
		}
	}

	/**
	 * 常用语设置
	 * 
	 * @param taskApprovalItems
	 * @param actDefId
	 * @param setId
	 */
	/*private void importTaskApprovalItems(TaskApprovalItems taskApprovalItems,
			String actDefId, Long setId) throws Exception {
		Long id = taskApprovalItems.getItemId();
		TaskApprovalItems approvalItems = taskApprovalItemsDao.getById(id);
		Object[] args={id};
		if (BeanUtils.isEmpty(approvalItems)) {
			taskApprovalItems.setSetId(setId);
			taskApprovalItems.setActDefId(actDefId);
			taskApprovalItemsDao.add(taskApprovalItems);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "常用语设置,ID:" + id + ",该记录成功导入！");
		} else {
			taskApprovalItems.setItemId(UniqueIdUtil.genId());
			taskApprovalItems.setActDefId(actDefId);
			taskApprovalItemsDao.add(taskApprovalItems);
			MsgUtil.addMsg(MsgUtil.WARN, "常用语设置,ID:" + id + "已经存在,重新发布新版本!");
		}
	}*/

	/**
	 * 流程节点设置
	 * 
	 * @param bpmNodeSet
	 * @param defId
	 * @param actDefId
	 * @param setId
	 * @return
	 */
	private Long importBpmNodeSet(BpmNodeSet bpmNodeSet, Long defId,
			String actDefId, Long setId,String parentActDefId) throws Exception {

		BpmNodeSet nodeSet = bpmNodeSetDao.getById(setId);
		String Msg="";
		if (BeanUtils.isEmpty(nodeSet)) {
			Msg="流程节点设置,ID:" + setId + ",该记录成功导入！";
		} else {
			setId = UniqueIdUtil.genId();
			bpmNodeSet.setSetId(setId);
			Msg="流程节点设置,ID:" + setId + "已经存在,重新发布新版本!";
		}
		bpmNodeSet.setActDefId(actDefId);
		bpmNodeSet.setDefId(defId);
		if(StringUtil.isNotEmpty(bpmNodeSet.getParentActDefId())){
			bpmNodeSet.setParentActDefId(parentActDefId);
		}
		bpmNodeSetDao.add(bpmNodeSet);
		MsgUtil.addMsg(MsgUtil.SUCCESS, Msg);
		return setId;
	}

	/**
	 * 流程节点设置
	 * 
	 * @param bpmUserConditionList
	 * @param defId
	 * @param actDefId
	 * @return
	 */
	@SuppressWarnings("unused")
	private void importBpmUserCondition(
			List<BpmUserCondition> bpmUserConditionList, Long defId,
			String actDefId) throws Exception {
		for (BpmUserCondition bpmUserCondition : bpmUserConditionList) {
			Long id = bpmUserCondition.getId();
			BpmUserCondition userCondition = bpmUserConditionDao.getById(id);
			Object[] args={id};
			if (BeanUtils.isEmpty(userCondition)) {
				bpmUserCondition.setActdefid(actDefId);
				bpmUserConditionDao.add(bpmUserCondition);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程节点设置,ID:" + id + ",该记录成功导入！");
			} else {
				id = UniqueIdUtil.genId();
				bpmUserCondition.setId(id);
				bpmUserConditionDao.add(bpmUserCondition);
				MsgUtil.addMsg(MsgUtil.WARN, "流程规则,ID:" + id + "已经存在,重新发布新版本!");
			}
		}

	}

	/**
	 * 节点下的人员设置
	 * 
	 * @param bpmNodeUser
	 * @param actDefId
	 * @param setId
	 * @return
	 */
	private Long importBpmNodeUser(BpmNodeUser bpmNodeUser) throws Exception {
		Long id = bpmNodeUser.getNodeUserId();
		BpmNodeUser nodeUser = bpmNodeUserDao.getById(id);
		if (BeanUtils.isEmpty(nodeUser)) {
			
			bpmNodeUserDao.add(bpmNodeUser);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "节点下的人员设置,ID:" + id + ",该记录成功导入！");
		} else {
			id = UniqueIdUtil.genId();
			bpmNodeUser.setNodeUserId(id);
			bpmNodeUserDao.add(bpmNodeUser);
			MsgUtil.addMsg(MsgUtil.WARN, "节点下的人员设置,ID:" + id + "已经存在,重新发布新版本!");
		}
		return id;
	}
	
	/**
	 * 人员设置
	 * 
	 * @param bpmNodeUser
	 * @param actDefId
	 * @param setId
	 * @param conditionId
	 * @return
	 */
	@SuppressWarnings("unused")
	private Long importBpmNodeUser(BpmNodeUser bpmNodeUser, String actDefId,
			Long setId, Long conditionId) {
		bpmNodeUser = this.parseBpmNodeUser(bpmNodeUser);
		bpmNodeUser.setConditionId(conditionId);
		Long id = bpmNodeUser.getNodeUserId();
		BpmNodeUser nodeUser = bpmNodeUserDao.getById(id);
		if (BeanUtils.isEmpty(nodeUser)) {
			bpmNodeUserDao.add(bpmNodeUser);
			MsgUtil.addMsg(MsgUtil.SUCCESS, "人员设置,ID:" + id + ",该记录成功导入!");
		} else {
			id = UniqueIdUtil.genId();
			bpmNodeUser.setNodeUserId(id);
			bpmNodeUserDao.add(bpmNodeUser);
			MsgUtil.addMsg(MsgUtil.WARN, "人员设置,ID:" + id + "已经存在,重新发布新版本!");
		}
		return id;
	}
	
	/**
	 * 
	 * @param bpmNodeUser
	 * @return
	 */
	private BpmNodeUser parseBpmNodeUser(BpmNodeUser bpmNodeUser) {
		String cmpName = bpmNodeUser.getCmpNames();
		if (StringUtils.isEmpty(cmpName))
			return bpmNodeUser;
		String[] cmpNamesAry = cmpName.split(",");
		String msg = "人员设置";
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, bpmNodeUser.getCmpIds());
		map.put(1, bpmNodeUser.getCmpNames());
		// 0,9,10,11,13不需要检查设置
		String assignType=bpmNodeUser.getAssignType();
		if ( BpmNodeUser.ASSIGN_TYPE_USER.equals(assignType)) {// 用户1
			map = this.parseSysUser(cmpNamesAry, msg);
		} else if ( BpmNodeUser.ASSIGN_TYPE_ROLE.equals(assignType)) {// 角色2
			map = this.parseSysRole(cmpNamesAry, msg);
		} else if (BpmNodeUser.ASSIGN_TYPE_ORG.equals(assignType)  ) {// 组织3
			map = this.parseSysOrg(cmpNamesAry, msg);
		} else if ( BpmNodeUser.ASSIGN_TYPE_ORG_CHARGE.equals(assignType) ) {// 组织负责人4
			map = this.parseSysOrg(cmpNamesAry, msg);
		} else if (BpmNodeUser.ASSIGN_TYPE_POS.equals(assignType) ) {// 岗位5
			map = this.parsePosition(cmpNamesAry, msg);
		} 
		bpmNodeUser.setCmpIds(map.get(0));
		bpmNodeUser.setCmpNames(map.get(1));
		return bpmNodeUser;
	}

//	/**
//	 * 用户节点的上下级设置
//	 * 
//	 * @param bpmNodeUserUplow
//	 * @param nodeUserId
//	 */
//	private void importBpmNodeUserUplow(BpmNodeUserUplow bpmNodeUserUplow,
//			Long nodeUserId) throws Exception {
//		Long id = bpmNodeUserUplow.getID();
//		BpmNodeUserUplow nodeUserUplow = bpmNodeUserUplowDao.getById(id);
//		if (BeanUtils.isEmpty(nodeUserUplow)) {
//			bpmNodeUserUplow.setNodeUserId(nodeUserId);
//			bpmNodeUserUplowDao.add(bpmNodeUserUplow);
//			MsgUtil.addMsg(MsgUtil.SUCCESS, "用户节点的上下级设置,ID:" + id + ",该记录成功导入！");
//		} else {
//			MsgUtil.addMsg(MsgUtil.WARN, "用户节点的上下级设置,ID:" + id
//					+ "已经存在,该记录终止导入！");
//		}
//	}

	/**
	 * 流程消息设置
	 * 
	 * @param bpmNodeMessageList
	 * @param actDefId
	 */
	private void importBpmNodeMessage(List<BpmNodeMessage> bpmNodeMessageList,
			String actDefId) throws Exception {
		for (BpmNodeMessage bpmNodeMessage : bpmNodeMessageList) {
			Long id = bpmNodeMessage.getId();
			BpmNodeMessage nodeMessage = bpmNodeMessageDao.getById(id);
			if (BeanUtils.isEmpty(nodeMessage)) {
				bpmNodeMessage.setActDefId(actDefId);
				bpmNodeMessageDao.add(bpmNodeMessage);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程消息设置,ID:" + id + ",该记录成功导入！");
			} else {
				bpmNodeMessage.setId(UniqueIdUtil.genId());
				bpmNodeMessage.setActDefId(actDefId);
				bpmNodeMessageDao.add(bpmNodeMessage);
				MsgUtil.addMsg(MsgUtil.WARN, "流程消息设置,ID:" + id
						+ "已经存在,重新发布新版本!");
			}
		}

	}

	/**
	 * 流程会签规则
	 * 
	 * @param bpmNodeSignList
	 * @param actDefId
	 */
	private void importBpmNodeSign(List<BpmNodeSign> bpmNodeSignList,
			String actDefId) throws Exception {
		for (BpmNodeSign bpmNodeSign : bpmNodeSignList) {
			Long id = bpmNodeSign.getSignId();
			BpmNodeSign nodeSign = bpmNodeSignDao.getById(id);
			if (BeanUtils.isEmpty(nodeSign)) {
				bpmNodeSign.setActDefId(actDefId);
				bpmNodeSignDao.add(bpmNodeSign);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程会签规则,ID:" + id + ",该记录成功导入！");
			} else {
				bpmNodeSign.setSignId(UniqueIdUtil.genId());
				bpmNodeSign.setActDefId(actDefId);
				bpmNodeSignDao.add(bpmNodeSign);
				MsgUtil.addMsg(MsgUtil.WARN, "流程会签规则,ID:" + id
						+ "已经存在,重新发布新版本!");
			}
		}

	}

	/**
	 * 流程变量
	 * 
	 * @param bpmDefVarList
	 * @param defId
	 */
	private void importBpmDefVar(List<BpmDefVar> bpmDefVarList, Long defId)
			throws Exception {
		for (BpmDefVar bpmDefVar : bpmDefVarList) {
			Long id = bpmDefVar.getVarId();
			BpmDefVar defVar = bpmDefVarDao.getById(id);
			if (BeanUtils.isEmpty(defVar)) {
				bpmDefVar.setDefId(defId);
				bpmDefVarDao.add(bpmDefVar);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程变量,ID:" + id + ",该记录成功导入！");
			} else {
				bpmDefVar.setVarId(UniqueIdUtil.genId());
				bpmDefVar.setDefId(defId);
				bpmDefVarDao.add(bpmDefVar);
				MsgUtil.addMsg(MsgUtil.WARN, "流程变量,ID:" + id + "已经存在,重新发布新版本!");
			}
		}

	}

	/**
	 * 流程事件脚本
	 * 
	 * @param bpmNodeScriptList
	 * @param actDefId
	 */
	private void importBpmNodeScript(List<BpmNodeScript> bpmNodeScriptList,
			String actDefId) throws Exception {
		for (BpmNodeScript bpmNodeScript : bpmNodeScriptList) {
			Long id = bpmNodeScript.getId();
			bpmNodeScript.setScript(parseScript(bpmNodeScript.getScript(), false));
			BpmNodeScript nodeScript = bpmNodeScriptDao.getById(id);
			if (BeanUtils.isEmpty(nodeScript)) {
				bpmNodeScript.setActDefId(actDefId);
				bpmNodeScriptDao.add(bpmNodeScript);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程事件脚本,ID:" + id + ",该记录成功导入！");
			} else {
				bpmNodeScript.setId(UniqueIdUtil.genId());
				bpmNodeScript.setActDefId(actDefId);
				bpmNodeScriptDao.add(bpmNodeScript);
				MsgUtil.addMsg(MsgUtil.WARN, "流程事件脚本,ID:" + id
						+ "已经存在,重新发布新版本!");
			}
		}

	}

	/**
	 * 流程跳转规则
	 * 
	 * @param bpmNodeRuleList
	 * @param actDefId
	 */
	private void importBpmNodeRule(List<BpmNodeRule> bpmNodeRuleList,
			String actDefId) throws Exception {
		for (BpmNodeRule bpmNodeRule : bpmNodeRuleList) {
			Long id = bpmNodeRule.getRuleId();
			bpmNodeRule.setConditionCode(parseScript(bpmNodeRule.getConditionCode(), false));
			BpmNodeRule nodeRule = bpmNodeRuleDao.getById(id);
			if (BeanUtils.isEmpty(nodeRule)) {
				bpmNodeRule.setActDefId(actDefId);
				bpmNodeRuleDao.add(bpmNodeRule);
				MsgUtil.addMsg(MsgUtil.SUCCESS, "流程跳转规则,ID:" + id + ",该记录成功导入！");
			} else {
				bpmNodeRule.setRuleId(UniqueIdUtil.genId());
				bpmNodeRule.setActDefId(actDefId);
				bpmNodeRuleDao.add(bpmNodeRule);
				MsgUtil.addMsg(MsgUtil.WARN, "流程跳转规则,ID:" + id
						+ "已经存在,重新发布新版本!");
			}
		}
	}

	
	
	/**
	 * 解析岗位
	 * 
	 * @param cmpNamesAry
	 *            名称数组
	 * @param msg
	 *            提示消息
	 * @return
	 */
	private Map<Integer, String> parsePosition(String[] cmpNamesAry, String msg) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		String cmpIds = "";
		String cmpNames = "";
		for (String name : cmpNamesAry) {
			List<Position> positionList = positionService.getByPosName(name);
			if (BeanUtils.isEmpty(positionList)) {
				MsgUtil.addMsg(MsgUtil.ERROR, msg + "中的岗位名称：" + name
						+ ",不存在!请检查!");

			} else if (positionList.size() > 1) {
				MsgUtil.addMsg(MsgUtil.ERROR, msg + "中的岗位名称：" + name
						+ ",多于一条记录!请检查!");
			} else {
				Position position = positionList.get(0);
				cmpIds += position.getPosId() + ",";
				cmpNames += position.getPosName() + ",";
			}
		}
		return this.trimInfo(map, cmpIds, cmpNames);
	}

	/**
	 * 解析组织
	 * 
	 * @param cmpNamesAry
	 *            名称数组
	 * @param msg
	 *            提示消息
	 * @return
	 */
	private Map<Integer, String> parseSysOrg(String[] cmpNamesAry, String msg) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		String cmpIds = "";
		String cmpNames = "";
		for (String name : cmpNamesAry) {
			List<SysOrg> sysOrgList = sysOrgService.getByOrgName(name);
			if (BeanUtils.isEmpty(sysOrgList)) {
				MsgUtil.addMsg(MsgUtil.ERROR, msg + "中的组织名称：" + name
						+ ",不存在!请检查!");
			} else if (sysOrgList.size() > 1) {
				MsgUtil.addMsg(MsgUtil.ERROR, msg + "中的组织名称：" + name
						+ ",多于一条记录!请检查!");
			} else {
				SysOrg sysOrg = (SysOrg)sysOrgList.get(0);
				cmpIds += sysOrg.getOrgId() + ",";
				cmpNames += sysOrg.getOrgName() + ",";
			}
		}
		return this.trimInfo(map, cmpIds, cmpNames);
	}

	
	/**
	 * 解析角色
	 * 
	 * @param cmpNamesAry
	 *            名称数组
	 * @param msg
	 *            提示消息
	 * @return
	 */
	private Map<Integer, String> parseSysRole(String[] cmpNamesAry, String msg) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		String cmpIds = "";
		String cmpNames = "";
		for (String name : cmpNamesAry) {
			List<SysRole> sysRoleList =sysRoleService.getByRoleName(name);
			if (BeanUtils.isEmpty(sysRoleList)) {
				MsgUtil.addMsg(MsgUtil.ERROR, msg + "中的角色名称：" + name
						+ ",不存在!请检查!");
			} else if (sysRoleList.size() > 1) {
				MsgUtil.addMsg(MsgUtil.ERROR, msg + "中的角色名称：" + name
						+ ",多于一条记录!请检查!");
			} else {
				SysRole sysRole = (SysRole)sysRoleList.get(0);
				cmpIds += sysRole.getRoleId() + ",";
				cmpNames += sysRole.getRoleName() + ",";
			}
		}

		return this.trimInfo(map, cmpIds, cmpNames);
	}

	/**
	 * 解析用户
	 * 
	 * @param cmpNamesAry
	 *            名称数组
	 * @param msg
	 *            提示消息
	 * @return
	 */
	private Map<Integer, String> parseSysUser(String[] cmpNamesAry, String msg) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		String cmpIds = "";
		String cmpNames = "";
		for (String name : cmpNamesAry) {
			SysUser sysUser = (SysUser)sysUserService.getByAccount(name);
			if (BeanUtils.isEmpty(sysUser)) {
				MsgUtil.addMsg(MsgUtil.ERROR, msg + "中的用户工号：" + name
						+ ",不存在!请检查!");
				continue;
			} else {
				cmpIds += sysUser.getUserId() + ",";
				cmpNames += sysUser.getFullname() + ",";
			}
		}
		return this.trimInfo(map, cmpIds, cmpNames);
	}
	/**
	 * 
	 * @param map
	 * @param cmpIds
	 * @param cmpNames
	 * @return
	 */
	private Map<Integer, String> trimInfo(Map<Integer, String> map,
			String cmpIds, String cmpNames) {
		if (cmpIds.length() > 0)
			cmpIds = cmpIds.substring(0, cmpIds.length() - 1);
		if (cmpNames.length() > 0)
			cmpNames = cmpNames.substring(0, cmpNames.length() - 1);

		map.put(0, cmpIds);
		map.put(1, cmpNames);
		return map;
	}

	//TODO 导出exportXML
	/**
	 * 导出XML。
	 * 
	 * <pre>
	 * 导出以下 信息:
	 * 	
	 * ■ 流程定义 bpmDefinition
	 * ■ 历史版本 bpmDefinitionHistory
	 * 	
	 * ■ 流程节点设置 bpmNodeSet
	 * ■ 节点下的人员的配置规则 bpmUserCondition
	 * ■ 节点下的人员设置  bpmNodeUser
	 * ■ 节点下的人员上下级设置 bpmNodeUserUplow
	 * 	
	 * ■ 流程定义权限 bpmDefRights
	 * ■ 常用语设置 taskApprovalItems
	 * 	
	 * ■ 流程跳转规则  bpmNodeRule
	 * ■ 流程事件脚本  bpmNodeScript
	 * 	
	 * ■ 流程操作按钮设置 bpmNodeButton
	 * ■ 流程变量  bpmDefVar
	 * 	 
	 * ■ 流程消息  bpmNodeMessage
	 * ■ 流程会签规则  bpmNodeSign
	 * 
	 * ■ 任务节点催办时间设置 taskReminder
	 * ■ 内（外）部子流程 subBpmDefinition
	 * </pre>
	 * 
	 * @param Long
	 *            [] bpmDefIds
	 * @param map
	 * @param filePath
	 * @return
	 */
	public String exportXml(Long[] bpmDefIds, Map<String, Boolean> map,
			String filePath) throws Exception {
		BpmDefinitionXmlList bpmDefinitionXmlList = new BpmDefinitionXmlList();
		List<BpmDefinitionXml> list = new ArrayList<BpmDefinitionXml>();
		for (int i = 0; i < bpmDefIds.length; i++) {
			// 流程定义
			BpmDefinition definition = dao.getById(bpmDefIds[i]);
			BpmDefinitionXml bpmDefinitionXml = this.exportBpmDefinition(
					definition, BpmDefinition.MAIN, map, filePath,null);

			list.add(bpmDefinitionXml);
		}
		bpmDefinitionXmlList.setBpmDefinitionXmlList(list);
		return XmlBeanUtil.marshall(bpmDefinitionXmlList,
				BpmDefinitionXmlList.class);
	}
	
	

	/**
	 * 导出流程定义
	 * 
	 * @param definition
	 * @param isMain
	 * @param filePath
	 * @return
	 */
	private BpmDefinitionXml exportBpmDefinition(BpmDefinition definition,
			Short isMain, Map<String, Boolean> map, String filePath,String parentActdefId)
			throws Exception {
		BpmDefinitionXml bpmDefinitionXml = new BpmDefinitionXml();
		if (BeanUtils.isEmpty(definition))
			return bpmDefinitionXml;
		if (BeanUtils.isEmpty(map))
			return bpmDefinitionXml;
		definition = parseBpmDefinition(definition, true);
		Long defId = definition.getDefId();
		String actDefId = definition.getActDefId();
		Long actDeployId = definition.getActDeployId();
		String defKey = definition.getDefKey();
		
		//把发布状态改成测试状态输出
		if(BpmDefinition.STATUS_ENABLED==definition.getStatus()){
			definition.setStatus(BpmDefinition.STATUS_TEST);
		}
		// 流程定义
		bpmDefinitionXml.setBpmDefinition(definition);

		// 设置流程定义 历史版本
		if (map.get("bpmDefinitionHistory")
				&& isMain.shortValue() == BpmDefinition.MAIN.shortValue())
			this.exportBpmDefinitionHistory(defId, bpmDefinitionXml, map,
					filePath);

		// 内（外）部子流程
		if (map.get("subBpmDefinition") & BeanUtils.isNotEmpty(actDeployId))
			this.exportSubBpmDefinition(actDeployId, bpmDefinitionXml, map,
					filePath,actDefId);

		

		// 流程跳转规则
		if (map.get("bpmNodeRule") && BeanUtils.isNotEmpty(actDefId)) {
			List<BpmNodeRule> list = bpmNodeRuleDao
					.getByActDefId(actDefId);
			List<BpmNodeRule> bpmNodeRuleList=new ArrayList<BpmNodeRule>();
			for(BpmNodeRule bpmNodeRule:list){
				bpmNodeRule.setConditionCode(parseScript(bpmNodeRule.getConditionCode(), true));
				bpmNodeRuleList.add(bpmNodeRule);
			}
			bpmDefinitionXml.setBpmNodeRuleList(bpmNodeRuleList);
		}

		// 流程事件脚本
		if (map.get("bpmNodeScript") && BeanUtils.isNotEmpty(actDefId)) {
			List<BpmNodeScript> list = bpmNodeScriptDao
					.getByBpmNodeScriptId("", actDefId);
			List<BpmNodeScript> bpmNodeScriptList=new ArrayList<BpmNodeScript>();
			for (BpmNodeScript bpmNodeScript:list) {
				bpmNodeScript.setScript(parseScript(bpmNodeScript.getScript(), true));
				bpmNodeScriptList.add(bpmNodeScript);
			}
			bpmDefinitionXml.setBpmNodeScriptList(bpmNodeScriptList);
		}

		// 流程会签规则
		if (map.get("bpmNodeSign") && BeanUtils.isNotEmpty(actDefId)) {
			List<BpmNodeSign> bpmNodeSignList = bpmNodeSignDao
					.getByActDefId(actDefId);
			bpmDefinitionXml.setBpmNodeSignList(bpmNodeSignList);
		}

		// 流程消息
		if (map.get("bpmNodeMessage") && BeanUtils.isNotEmpty(actDefId)) {
			List<BpmNodeMessage> bpmNodeMessageList = bpmNodeMessageDao
					.getByActDefId(actDefId);
			bpmDefinitionXml.setBpmNodeMessageList(bpmNodeMessageList);
		}

//		 List<Message> messageList =
//		 messageDao.getByActDefId(actDefId);
//		bpmDefinitionXml.setMessageList(messageList);

		// 流程变量
		if (map.get("bpmDefVar") && BeanUtils.isNotEmpty(defId)) {
			List<BpmDefVar> bpmDefVarList = bpmDefVarDao.getByDefId(defId);
			bpmDefinitionXml.setBpmDefVarList(bpmDefVarList);
		}

		// 流程节点设置
		if (map.get("bpmNodeSet") && BeanUtils.isNotEmpty(defId)) {
			// 流程节点设置
			List<BpmNodeSet> bpmNodeSetList = bpmNodeSetDao
					.getAllByDefId(defId);
			if (StringUtil.isNotEmpty(parentActdefId)) {
				List<BpmNodeSet> bpmNodeSets  = bpmNodeSetDao.getByDefIdAndParentActDefId(defId, parentActdefId,true);
				bpmNodeSetList.addAll(bpmNodeSets);
			}
			if (!map.get("bpmFormDef")) {
				for (BpmNodeSet bpmNodeSet : bpmNodeSetList) {
					bpmNodeSet.setFormKey("0l");
					bpmNodeSet.setFormDefName("");
				}
			}
			bpmDefinitionXml.setBpmNodeSetList(bpmNodeSetList);

			if (map.get("bpmNodeUser") && BeanUtils.isNotEmpty(actDefId)) {

				// 节点下的人员的配置规则(包含的抄送的设置也导出)
				List<BpmUserCondition> bpmUserConditionList = bpmUserConditionDao
						.getByActDefIdExport(actDefId);
				if (StringUtil.isNotEmpty(parentActdefId)) {
					List<BpmUserCondition> bpmUserConditions = bpmUserConditionDao.getByActDefIdWithParent(actDefId, parentActdefId);
					bpmUserConditionList.addAll(bpmUserConditions);
				}
				if (BeanUtils.isNotEmpty(bpmUserConditionList))
					bpmDefinitionXml
							.setBpmUserConditionList(bpmUserConditionList);

				// 节点下的人员设置
				List<BpmNodeUser> bpmNodeUserList = bpmNodeUserDao
						.getByActDefId(actDefId); 
				if (StringUtil.isNotEmpty(parentActdefId)) {
					List<BpmNodeUser> bpmNodeUsers = bpmNodeUserDao.getByActDefIdWithParent(actDefId,parentActdefId); 
					bpmNodeUserList.addAll(bpmNodeUsers);
				}
				if (BeanUtils.isNotEmpty(bpmNodeUserList)) {
					this.checkBpmNodeUserList(bpmNodeUserList);
					bpmDefinitionXml.setBpmNodeUserList(bpmNodeUserList);
				}
			}

			// 自定义表单
			if (map.get("bpmFormDef") && BeanUtils.isNotEmpty(bpmNodeSetList)) {
				Set<Long> tableIdSet = this.exportBpmFormDef(bpmNodeSetList,
						bpmDefinitionXml);
				// 自定义表
				if (map.get("bpmFormTable") && BeanUtils.isNotEmpty(tableIdSet))
					this.exportBpmFormTable(tableIdSet, bpmDefinitionXml);
			}
		}

		// 表单权限
		if(BeanUtils.isNotEmpty(actDefId)){
			List<BpmFormRights> bpmFormRightsList = bpmFormRightsDao
					.getFormRightsByActDefId(actDefId,PlatformType.undefine);
			if (StringUtil.isNotEmpty(parentActdefId)) {
				List<BpmFormRights> bpmFormRights = bpmFormRightsDao.getFormRightsByActDefIdWithParent(actDefId,parentActdefId,PlatformType.undefine);
				bpmFormRightsList.addAll(bpmFormRights);
			}
			if (BeanUtils.isNotEmpty(bpmFormRightsList)){
				bpmFormRightsList = bpmFormDefService
						.exportBpmFormRightsUser(bpmFormRightsList);
				bpmDefinitionXml.setBpmFormRightsList(bpmFormRightsList);
			}
		}
		// 流程操作按钮设置
		if (map.get("bpmNodeButton") && BeanUtils.isNotEmpty(defId)) {
			List<BpmNodeButton> bpmNodeButtonList = bpmNodeButtonDao
					.getByDefId(defId);
			bpmDefinitionXml.setBpmNodeButtonList(bpmNodeButtonList);
		}

		// 任务节点催办时间设置
		if (map.get("taskReminder") && BeanUtils.isNotEmpty(actDefId)) {
			List<TaskReminder> list = taskReminderDao
					.getByActDefId(actDefId);
			List<TaskReminder> taskReminderList=new ArrayList<TaskReminder>();
			for (TaskReminder taskReminder:list) {
				taskReminder.setMailContent(parseScript(taskReminder.getMailContent(), true));
				taskReminder.setMsgContent(parseScript(taskReminder.getMsgContent(), true));
				taskReminder.setSmsContent(parseScript(taskReminder.getSmsContent()	, true));
				taskReminder.setCondExp(parseScript(taskReminder.getCondExp(), true));
				taskReminder.setScript(parseScript(taskReminder.getScript(), true));
				taskReminderList.add(taskReminder);
			}
			bpmDefinitionXml.setTaskReminderList(taskReminderList);
		}
		
		// 流程联动设置
		if (map.get("bpmGangedSet") && BeanUtils.isNotEmpty(defId)) {
			
			List<BpmGangedSet> bpmGangedSetList = bpmGangedSetDao
					.getByDefId(defId);
			bpmDefinitionXml.setBpmGangedSetList(bpmGangedSetList);

		}
		
//		// 流程引用设置
//		if (map.get("bpmReferDefinition") && BeanUtils.isNotEmpty(defId)) {
//			
//			List<BpmReferDefinition> bpmReferDefinitionList= bpmReferDefinitionDao.getByDefId(defId);
//			bpmDefinitionXml.setBpmReferDefinitionList(bpmReferDefinitionList);
//		}
//		
		
		List<SysFile> sysFileList = new ArrayList<SysFile>();
		// 处理表单附件
		List<BpmFormDefXml> bpmFormDefXmlList = bpmDefinitionXml.getBpmFormDefXmlList();
		if (BeanUtils.isNotEmpty(bpmFormDefXmlList)) {
			for (BpmFormDefXml bpmFormDefXml : bpmFormDefXmlList) {
				if (BeanUtils.isNotEmpty(bpmFormDefXml.getSysFileList()))
					sysFileList.addAll(bpmFormDefXml.getSysFileList());
			}
		}
		// 流程帮助
		if (BeanUtils.isNotEmpty(definition.getAttachment())) {
			SysFile sysFile = sysFileService.getById(definition.getAttachment());
			if (BeanUtils.isNotEmpty(sysFile))
				sysFileList.add(sysFile);
		}
		// 附件
		if (BeanUtils.isNotEmpty(sysFileList)) {
			bpmDefinitionXml.setSysFileList(sysFileList);
			String path = StringUtil
					.trimSufffix(ServiceUtil.getBasePath().toString(),
							File.separator);
			for (SysFile sysFile : sysFileList) {
				this.copySysFile(sysFile, filePath, path);
			}
		}
		
		

		return bpmDefinitionXml;
	}
	

	private BpmDefinition parseBpmDefinition(BpmDefinition definition,
			boolean flag) {
		definition.setDefXml(StringUtil.convertScriptLine(
				definition.getDefXml(), flag));
		return definition;
	}
	
	private String parseScript(String  script,
			boolean flag) {
		return 	StringUtil.convertScriptLine(script, flag);
	}

	/**
	 * 导出自定义表
	 * 
	 * @param tableIdSet
	 * @param bpmDefinitionXml
	 */
	private void exportBpmFormTable(Set<Long> tableIdSet,
			BpmDefinitionXml bpmDefinitionXml) {
		Map<String, Boolean> map = bpmFormTableService
				.getDefaultExportMap(null);
		List<BpmFormTableXml> bpmFormTableXmlList = new ArrayList<BpmFormTableXml>();
		for (Long tableId : tableIdSet) {
			BpmFormTable formTable = bpmFormTableService.getById(tableId);
			BpmFormTableXml bpmFormTableXml = bpmFormTableService.exportTable(
					formTable, map);
			bpmFormTableXmlList.add(bpmFormTableXml);
		}
		if (BeanUtils.isNotEmpty(bpmFormTableXmlList))
			bpmDefinitionXml.setBpmFormTableXmlList(bpmFormTableXmlList);
	}
	
	/**
	 * 复制附件到指定文件
	 * 
	 * @param sysFile
	 * @param filePath
	 * @param path
	 *            复制到的路径
	 */
	public void copySysFile(SysFile sysFile, String filePath, String path) {
		try {
			String realPath = path + File.separator
					+ sysFile.getFilePath().replace("/", File.separator);
			String fileName = sysFile.getFileId() + "." + sysFile.getExt();
			// 复制到指定文件
			File file = new File(realPath);
			if (file.exists()) {
				filePath = filePath + File.separator + fileName;
				FileUtil.createFolderFile(filePath);
				FileUtil.copyFile(realPath, filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理节点人员
	 * 
	 * @param bpmNodeUserList
	 */
	private void checkBpmNodeUserList(List<BpmNodeUser> bpmNodeUserList) {
		for (BpmNodeUser bpmNodeUser : bpmNodeUserList) {
			if (BeanUtils.isEmpty(bpmNodeUser.getAssignType()))
				continue;
			if (BeanUtils.isEmpty(bpmNodeUser.getCmpNames()))
				continue;
			if (BpmNodeUser.ASSIGN_TYPE_USER.equals(bpmNodeUser.getAssignType() ) ) {
				String cmpIds = bpmNodeUser.getCmpIds();
				String[] cmpIdAry = cmpIds.split(",");
				String[] cmpNames = new String[cmpIdAry.length];
				for (int i = 0; i < cmpIdAry.length; i++) {
					SysUser sysUser =(SysUser) sysUserService.getById(new Long(cmpIdAry[i]));
					if (BeanUtils.isNotEmpty(sysUser))
						cmpNames[i] = sysUser.getAccount();
				}
				bpmNodeUser.setCmpNames(StringUtils.join(cmpNames, ","));
			}
		}
	}

	/**
	 * 导出自定义表单
	 * 
	 * @param bpmNodeSetList
	 * @param bpmDefinitionXml
	 * @return
	 */
	private Set<Long> exportBpmFormDef(List<BpmNodeSet> bpmNodeSetList,
			BpmDefinitionXml bpmDefinitionXml) {
		Map<String, Boolean> map = bpmFormDefService.getDefaultExportMap(null);
		// 不出现重复的formKey,取得流程定义key
		Set<String> formKeySet = new HashSet<String>();
		for (BpmNodeSet bpmNodeSet : bpmNodeSetList) {
			if(StringUtils.isNotEmpty(bpmNodeSet.getFormKey()) )
				formKeySet.add(bpmNodeSet.getFormKey());
		}

		Set<Long> tableIdSet = new HashSet<Long>();
		// 自定义表单
		List<BpmFormDefXml> bpmFormDefXmlList = new ArrayList<BpmFormDefXml>();
		for (String formKey : formKeySet) {
			// 设置自定义表单
			List<BpmFormDef> bpmFormDefList = bpmFormDefDao
					.getByFormKeyIsDefault(formKey.toString(), BpmFormDef.IS_DEFAULT);
			if (BeanUtils.isNotEmpty(bpmFormDefList)) {
				BpmFormDef bpmFormDef = bpmFormDefList.get(0);
				BpmFormDefXml bpmFormDefXml = bpmFormDefService
						.exportBpmFormDef(bpmFormDef, BpmFormDef.IS_DEFAULT,
								map);
				bpmFormDefXmlList.add(bpmFormDefXml);
				// 关联的自定义表ID
				if (BeanUtils.isNotEmpty(bpmFormDef)
						&& BeanUtils.isNotEmpty(bpmFormDef.getTableId())) {
					tableIdSet.add(bpmFormDef.getTableId());
				}
			}
		}
		if (BeanUtils.isNotEmpty(bpmFormDefXmlList))
			bpmDefinitionXml.setBpmFormDefXmlList(bpmFormDefXmlList);
		return tableIdSet;
	}
	
	

	/**
	 * 导出内（外）部子流程
	 * 
	 * @param actDeployId
	 * @param bpmDefinitionXml
	 * @param map
	 * @throws Exception
	 */
	private void exportSubBpmDefinition(Long actDeployId,
			BpmDefinitionXml bpmDefinitionXml, Map<String, Boolean> map,
			String filePath,String parentActdefId) throws Exception {
		if (BeanUtils.isEmpty(actDeployId))
			return;
		List<BpmDefinitionXml> subBpmDefinitionXmlList = new ArrayList<BpmDefinitionXml>();
		String xml = bpmDao.getDefXmlByDeployId(actDeployId.toString());
		Set<String> keySet = NodeCache.getSubKeysByXml(xml);
		for (String flowKey : keySet) {
			BpmDefinition bpmDefinition = dao.getByActDefKeyIsMain(flowKey);
			subBpmDefinitionXmlList.add(exportBpmDefinition(bpmDefinition,
					BpmDefinition.MAIN, map, filePath,parentActdefId));
		}

		if (BeanUtils.isNotEmpty(subBpmDefinitionXmlList))
			bpmDefinitionXml
					.setSubBpmDefinitionXmlList(subBpmDefinitionXmlList);

	}

	/**
	 * 导出流程定义历史版本
	 * 
	 * @param defId
	 * @param bpmDefinitionXml
	 * @param map
	 * @throws Exception
	 */
	private void exportBpmDefinitionHistory(Long defId,
			BpmDefinitionXml bpmDefinitionXml, Map<String, Boolean> map,
			String filePath) throws Exception {
		List<BpmDefinition> bpmDefinitionList = this
				.getAllHistoryVersions(defId);
		if (BeanUtils.isEmpty(bpmDefinitionList))
			return;
		List<BpmDefinitionXml> bpmDefinitionXmlList = new ArrayList<BpmDefinitionXml>();
		for (BpmDefinition bpmDefinition : bpmDefinitionList) {
			BpmDefinitionXml definitionXml = exportBpmDefinition(bpmDefinition,
					BpmDefinition.NOT_MAIN, map, filePath,null);
			bpmDefinitionXmlList.add(definitionXml);
		}
		bpmDefinitionXml.setBpmDefinitionXmlList(bpmDefinitionXmlList);

	}
	
	/**
	 * 根据流程key取得流程定义数据。
	 * @param flowKey
	 * @return
	 */
	public BpmDefinition getMainDefByActDefKey(String actDefKey){
		return dao.getByActDefKeyIsMain(actDefKey);
	}
	
	
	
	
	/**
	 * 判断流程key是否存在。
	 * @param key
	 * @return
	 */
	public boolean isActDefKeyExists(String key){
		return dao.isActDefKeyExists(key);
	}
	
	/**
	 * 判断defkey是否存在。
	 * @param key
	 * @return
	 */
	public boolean isDefKeyExists(String defkey){
		return dao.isDefKeyExists(defkey);
	}
	
	/**
	 * 通过标题模糊查询所有发布的、默认版本的流程
	 * @param subject
	 * @return
	 */
	public List<BpmDefinition> getAllPublished(String subject){
		return dao.getAllPublished(subject);
	}
	
	/**
	 * 通过类型ID查询所有发布的、默认版本的流程
	 * @param typeId
	 * @return
	 */
	public List<BpmDefinition> getPublishedByTypeId(String typeId){
		return dao.getPublishedByTypeId(typeId);
	}
	
	/**
	 * 根据流程定义key获得当前最新版本的流程定义
	 * @param defkey 
	 * @return
	 */
	public BpmDefinition getMainByDefKey(String defkey){
		return dao.getMainByDefKey(defkey);
	}
	
	/**
	 * 更新流程启动状态
	 * @param defId
	 * @param disableStatus
	 * @return
	 */
	public int updateDisableStatus(Long defId,Short disableStatus)
	{
		return dao.updateDisableStatus(defId, disableStatus);
	}

	/**
	 * 根据用户ID，获该用户所创建的流程定义
	 * @param userId 用户ID
	 * @param pb 分页Bean
	 * @return
	 */
	public List<BpmDefinition> getByUserId(Long userId,Map<String,Object> params,PageBean pb) {
		return dao.getByUserId(userId,params,pb);
	}
	
	/**
	 * 清除流程相关数据
	 * @param defId
	 * @throws Exception 
	 */
	public void cleanData(Long defId) throws Exception {
		BpmDefinition bpmDefinition=dao.getById(defId);
		String actDefId=bpmDefinition.getActDefId();
		//清除子流程数据
		this.cleanSubData(actDefId);
		//获取测试状态流程实例
		List<ProcessRun> processRunList=processRunService.getTestRunsByActDefId(actDefId);
		for(ProcessRun processRun:processRunList){
			Long runId=processRun.getRunId();
			String businessKey=processRun.getBusinessKey();
			String dsAlias=processRun.getDsAlias();
			String tableName=processRun.getTableName();
			//删除业务数据
			if(StringUtil.isNotEmpty(tableName)){
				if(StringUtil.isEmpty(dsAlias)||dsAlias.equals(DataSourceUtil.DEFAULT_DATASOURCE)){
					tableName=tableName.replaceFirst(TableModel.CUSTOMER_TABLE_PREFIX, "");
					BpmFormTable bpmFormTable=bpmFormTableService.getByTableName(tableName);
					bpmFormHandlerService.delById(businessKey, bpmFormTable);
				}else{
					bpmFormHandlerService.delByDsAliasAndTableName(dsAlias,tableName,businessKey);
				}
			}
			//非草稿状态 清除act流程数据
			if(!ProcessRun.STATUS_FORM.equals(processRun.getStatus())){
				Long actInstId=Long.parseLong(processRun.getActInstId());
				if (ProcessRun.STATUS_FINISH != processRun.getStatus()) {
					executionDao.delVariableByProcInstId(actInstId);
					taskDao.delCandidateByInstanceId(actInstId);
					taskDao.delByInstanceId(actInstId);
					executionDao.delExecutionByProcInstId(actInstId);
				}
				//删除节点状态
				bpmProStatusDao.delByActInstId(actInstId);
				//删除堆栈信息
				executionStackDao.delByActDefId(processRun.getActDefId());
				//删除审批意见
				taskOpinionDao.delByActInstId(processRun.getActInstId());
				//任务已读信息
				taskReadService.delByActInstId(actInstId);
			}
			//删除流程操作日志
			bpmRunLogService.delByRunId(runId);
			//删除抄送转发事宜
			bpmProCopytoService.delByRunId(runId);
			//代理转办数据BPM_TASK_EXE删除 BPM_COMMU_RECEIVER:通知接收人，BPM_TASK_READ：任务是否已读
			List<BpmTaskExe> bpmTaskExeList = bpmTaskExeService.getByRunId(processRun.getRunId());
			if(BeanUtils.isNotEmpty(bpmTaskExeList)){
				for(BpmTaskExe bpmTaskExe:bpmTaskExeList){
					commuReceiverService.delByTaskId(bpmTaskExe.getTaskId());
				}
				//删除转办代理事宜
				bpmTaskExeService.delByRunId(runId);
			}
			//删除流程实例
			processRunService.delById(runId);
		}
	}
	
	private void cleanSubData(String actDefId) throws Exception{
		if(NodeCache.hasExternalSubprocess(actDefId)){
			Map<String,FlowNode> flowNodes=NodeCache.getByActDefId(actDefId);
			Set<String> keyset=flowNodes.keySet();
			for(Iterator<String>it=keyset.iterator();it.hasNext();){
				FlowNode flowNode=flowNodes.get(it.next());
				if("callActivity".equals(flowNode.getNodeType())){
					String flowKey=flowNode.getAttribute("subFlowKey");
					BpmDefinition subDefinition=dao.getMainByDefKey(flowKey);
					this.cleanData(subDefinition.getDefId());
				}
			}
		}
	}

	
	
	
	/**
	 * 按分管授权的内容查找我能访问的所有流程定义
	 * 
	 * @param filter
	 * @param typeId
	 * @return
	 */
	public List<BpmDefinition> getMyDefList(QueryFilter filter, Long typeId) {
		if (typeId != 0) {
			GlobalType globalType = globalTypeDao.getById(typeId);
			if (BeanUtils.isNotEmpty(globalType)){
				// 查找某一分类下包含其子类的所有定义
				filter.addFilter("nodePath",globalType.getNodePath() + "%");
			}
		}
		// 根据流程授权获取流程。
		return dao.getMyDefList(filter);
	}
	
	
	
	
	private List<Long> replacePath(String path) {
		if(StringUtil.isEmpty(path)) return new ArrayList<Long>();
		path = StringUtil.trimSufffix(path, ".");
		String[] aryPath=path.split("\\.");
		List<Long> list=new ArrayList<Long>();
		for(String tmp:aryPath){
			list.add(new Long(tmp));
		}
		return list;
	}
	
	/**
	 * 判断流程是否允许转办。
	 * 
	 * @param actDefId
	 * @return
	 */
	public boolean allowDivert(String actDefId) {
		BpmDefinition bpmDefinition = dao.getByActDefId(actDefId);
		return bpmDefinition.getAllowDivert() == 1;
	}
	

	
	/**
	 * 根据流程定义获取表单结果。
	 * @param defId
	 * @return
	 */
	public BpmFormResult getBpmFormResult(Long defId){
		List<BpmFormTable> list= bpmFormTableDao.getTableNameByDefId(defId);
		BpmFormResult bpmFormResult=new BpmFormResult();
		//没有找到对应的表单
		if(list.size()==0){
			bpmFormResult.setResult(2);
		}
		else if(list.size()>1){
			bpmFormResult.setResult(1);
		}
		else{
			BpmFormTable bpmFormTable=(BpmFormTable)list.get(0);
			String tableName=bpmFormTable.getTableName();
			String dsName=bpmFormTable.getDsAlias();
			Integer isexternal=bpmFormTable.getIsExternal();
			String name=tableName;
			if(isexternal==1){
				name=dsName +"_" + tableName;
			}
			bpmFormResult.setTableName(name);
		}
		
		return bpmFormResult;
	}
	
	/**
	 * 根据流程定义和父流程定义ID获取表单结果。
	 * @param defId
	 * @param parentActDefId
	 * @return
	 */
	public BpmFormResult getBpmFormResult(Long defId, String parentActDefId){
		List<BpmFormTable> list= bpmFormTableDao.getTableNameByDefId(defId, parentActDefId);
		BpmFormResult bpmFormResult=new BpmFormResult();
		//没有找到对应的表单
		if(list.size()==0){
			bpmFormResult.setResult(2);
		}
		else if(list.size()>1){
			bpmFormResult.setResult(1);
		}
		else{
			BpmFormTable bpmFormTable=(BpmFormTable)list.get(0);
			String tableName=bpmFormTable.getTableName();
			String dsName=bpmFormTable.getDsAlias();
			Integer isexternal=bpmFormTable.getIsExternal();
			String name=tableName;
			if(isexternal==1){
				name=dsName +"_" + tableName;
			}
			bpmFormResult.setTableName(name);
		}
		
		return bpmFormResult;
	}
    public void updCategory(Long typeId, List<String> defKeylist) {
		dao.updCategory(typeId, defKeylist);
	}
	/**
	 * 根据流程定义key获得流程定义
	 * @param defkey 
	 * @return
	 */
	public List<BpmDefinition> getByDefKey(String defkey){
		return dao.getByDefKey(defkey);
	}
	
	public List<BpmDefinition> getBpmDefinitionByFormKey(String formKey){
		return dao.getBpmDefinitionByFormKey(formKey);
	}

	/**
	 * 桌面的新建流程
	 * @param actRights
	 * @return
	 */
	public List<BpmDefinition> getMyDefListForDesktop(String actRights) {
		return dao.getMyDefListForDesktop(actRights);
	}
	
	/**
	 * 用户流程列表
	 * @param actRights
	 * @param isNeedRight
	 * @return
	 */
	public List<BpmDefinition> getUserBpmList(String actRights,String isNeedRight){
		return dao.getUserBpmList(actRights, isNeedRight);
	}
	
	public BpmDefinition getDefinitionById(String defId){
		List<BpmDefinition> defList = dao.getDefinitionById(defId);
		BpmDefinition bpmDefinition = defList.get(0);
		return bpmDefinition;
		
	}
   public int bpmDefinitionCreater(BpmDefinition bpmDefinition,String templateName,int flags,Boolean isDeploy){
		String defId = bpmDefinition.getDefId().toString();
		System.out.println("shuchu###############"+bpmDefinition.getDefXml());
		String actFlowDefXml = "";
		String nodeName =null;
		String nodes = null;
		Long type = null;
		String name = null;
		String addNode = null;
		int j=0;
		if(flags==0){
			 nodeName = "subDefKey";
			 nodes = "startSub";
			 name = "_transaction";
			 type=10000006460068l;
			 addNode= "subDefKey";
			 j=8;
		}
			
		else{
			 nodeName="subDefKey";
			 nodes = "task";
			 name = "_operate";
			 j=4;
			 type=10000041550186l;
			 addNode= "subDefKey";
		}
		try {
			Long defIdLong = codeTemplateService.getByBm(templateName);//通过（写死的）操作图模板别名得到模板id
			String templateDefId = String.valueOf(defIdLong);
			System.out.println("**templateDefId**:"+templateDefId);
			BpmDefinition templateBpmDefinition = getDefinitionById(templateDefId);//取得模板bpmDefinition对象
			String templateDefXml = templateBpmDefinition.getDefXml();//得到模板的defXml
			SysAuditThreadLocalHolder.putParamerter("defKey", templateBpmDefinition.getDefKey());
			List<String> addDefKeyList=new ArrayList<String>();//生成的图的defKeyList（添加到Xml文件中，实现节点和生成的图绑定）
			if(bpmDefinition.getDefId()!= null){
			    String defXml = bpmDefinition.getDefXml();
				int f=0;
				String subject = bpmDefinition.getSubject();
				System.out.println(defXml);
				Map<String,Object> map=yesOrNoHave(defXml,nodeName);
				Set<String>keys = map.keySet();
				 if(keys != null){
					 Iterator iterator = keys.iterator();  
			            while(iterator.hasNext()){ 
			            	Object key = iterator.next();  
			            	Object value = map.get(key); 
			            	String n = key.toString();
				            String number=n.substring((n.length()-1), n.length());
			            	System.out.println("标记："+value);
			            	if(value.toString()=="false") {
								BpmDefinition createrBpmDefinition = (BpmDefinition) bpmDefinition.clone();
								System.out.println("--operateBpmDefinition----:"+createrBpmDefinition.getDefId());
								System.out.println("---typeId--:"+createrBpmDefinition.getTypeId());
								createrBpmDefinition.setDefXml(templateDefXml);
								long createrDefId = 0;
								String defKey = bpmDefinition.getDefKey();
								String createrDefKey = defKey+name+number;
								createrBpmDefinition.setDefKey(createrDefKey);
								createrBpmDefinition.setDescp(createrDefKey); 
								createrBpmDefinition.setDefId(createrDefId);
								createrBpmDefinition.setSubject(subject+name+number);
								createrBpmDefinition.setTypeId(type);//设置分类
								createrBpmDefinition.setActDefId(null);
								createrBpmDefinition.setActDefKey(null);
								Long actDep = null;
								createrBpmDefinition.setActDeployId(actDep);
								actFlowDefXml = BpmUtil.transform(createrBpmDefinition.getDefKey(),createrBpmDefinition.getSubject(),templateDefXml);
								saveOrUpdate(createrBpmDefinition, isDeploy,actFlowDefXml);
								System.out.println("****defId*******"+createrBpmDefinition.getDefId());
								System.out.println("新建的图的DefKey"+createrBpmDefinition.getDefKey());
								addDefKeyList.add(createrBpmDefinition.getDefKey().toString());
								System.out.println("addDefKeyList######"+addDefKeyList.size());
							}
			            	else
			            	{
			            		System.out.println("该图中的节点已经全部生成过操作图了!!!");
			            		return 0;
			            	}
						}
				 }
				 String xml=bpmDefinition.getDefXml();
					//解析xml文件，并将写入操作图Id，name更新defxml文件
				 String updataDefXml=bangOperateByWriteDefxml(xml,addDefKeyList,addNode);
				  bpmDefinition.setDefXml(updataDefXml);
				 updataDefXml(defId,updataDefXml);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}	
		return 1;
   }
   /**
	 * 更新流程图defXml字段
	 * @param defxml，defid
	 * @return xmlStr
	 */
	public void updataDefXml(String defId,String defXml){
		dao.updataDefXml(defId, defXml);
	}
	public List<BpmDefinition> getByDefId(Long defId){
		return dao.getByDefId(defId);
		}
	/**
	 * 子流程节点信息
	 * @param xmlstr，defid
	 * @return idList流程图中的节点名称
	 */
	public List<String> findNodeId(String xmlStr, String nodeName) {
		List<String> idList=new ArrayList<String>();
		try {
			//System.out.println(xmlStr);
			Document document = DocumentHelper.parseText(xmlStr);
			Element root = document.getRootElement();
			System.out.println("输出根节点"+root);
			List<Element> elementList = root.elements();
			System.out.println(elementList.size());
				for (Element element : elementList) {
					System.out.println("外层111"+element.getName());
					System.out.println("外层222"+element.attribute("id").getText());
					List<Element> eles = element.elements();
					for (Element ele : eles) {
						System.out.println("neiceng ####"+ele.getName());
						if(ele.getName().contains(nodeName)){
							String id = ele.attribute("id").getText();
							String label = ele.attributeValue("name");
							System.out.println("id:" + id + " label:" + label);
							idList.add(id);
						}
					}
				}
			} catch (DocumentException e) {
			e.printStackTrace();
		}
		return idList;
	}
	/**
	 * 子流程节点信,用于统计
	 * @param xmlstr
	 * @return idList
	 */
	public List<String> findNodeName(String xmlStr, String nodeName) {
		List<String> nodeNameList=new ArrayList<String>();
		try {
			//System.out.println(xmlStr);
			Document document = DocumentHelper.parseText(xmlStr);
			Element root = document.getRootElement();
			//System.out.println("输出根节点"+root);
			List<Element> elementList = root.elements();
			//System.out.println(elementList.size());
				for (Element element : elementList) {
					//System.out.println("外层111"+element.getName());
					//System.out.println("外层222"+element.attribute("id").getText());
					String name = element.attribute("id").getText();
					//System.out.println("name1"+name);
						if(name.contains(nodeName)){
							//System.out.println("name2"+name);
							nodeNameList.add(name);
						}
				}
			} catch (DocumentException e) {
			e.printStackTrace();
		}
		return nodeNameList;
	}
	/**
	 * 查找原图节点中有没有绑定的subDefKey
	 * @param defXml，nodeName
	 */
	public Map yesOrNoHave(String defXml,String nodeName) {
		System.out.println(defXml);
		Map<String,Object> have =new HashMap<String,Object>();
		try {
			Document document = DocumentHelper.parseText(defXml);
			Element root = document.getRootElement();
			System.out.println("查看有没有输出根节点"+root);
			List<Element> elementList = root.elements();
				for (Element element : elementList) {
					if(element.getName().contains("Task")){
					     System.out.println("外层#####"+element.getName());
					     System.out.println("外层222"+element.attribute("id").getText());
					     List<Element> eles = element.elements();
					     for (int i = 0; i<eles.size();i++) {
						        System.out.println("内层######"+eles.get(i).getName());
						        if(eles.get(i).getName().contains(nodeName)&&eles.get(i).getText()!=""){       
							           have.put(element.attribute("id").getText(),true);
							           System.out.println("有y标签");
							            break;
						         }
						         if(i==(eles.size()-1)) {
							           have.put(element.attribute("id").getText(),false);
							           System.out.println("map的长度"+have.size());
						         } 
					     }	
					}
				}
		}catch (DocumentException e) {
				e.printStackTrace();
		 }
		System.out.println(have.size());		
		return have;
	}
	/**
	 *用dom4j解析defxml文件，实现绑定
	 * @param defxml，defid
	 * @return xmlStr 
	 */
	public String bangOperateByWriteDefxml(String xmlStr,List<String> operateIdList,String addNode){
		try {
			//System.out.println("**********解析之前xml***********"+xmlStr);
			Document document = DocumentHelper.parseText(xmlStr);
			Element root = document.getRootElement();
			//System.out.println("输出根节点"+root.getName());
			List<Element> elementList = root.elements();
			int m=0;
				for (int i=0;i<elementList.size();i++) {
					System.out.println("***外层循环***"+elementList.get(i).getName());
					System.out.println("外层"+elementList.get(i).attribute("id").getText());
					if(elementList.get(i).getName().contains("Task")||elementList.get(i).getName().contains("UserTask")){
						System.out.println("Task:"+elementList.get(i).attribute("id").getText());
						Element userTaskElement = elementList.get(i);
						System.out.println(userTaskElement.getName());
						Element operateIdElement = userTaskElement.addElement(addNode);
						System.out.println("OPERATE#########"+operateIdList.size());
						operateIdElement.setText(operateIdList.get(m++));
						
					}
					xmlStr = document.asXML();
					}
				System.out.println("**********解析之后xml***********"+xmlStr);
			} catch (DocumentException e){
			e.printStackTrace();
		}
		return xmlStr;
	}
	
	/**
	 * 白晓帆解析前序节点
	 * 
	 */
	public List<Map> findBeforeNodeId(String defId,String nodeId){
		List<Map> nodeIdList=new ArrayList<Map>();
		BpmDefinition bpmDefinition = getDefinitionById(defId);
		String defXml = bpmDefinition.getDefXml();
		try {
			Document document = DocumentHelper.parseText(defXml);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
				for (Element element : elementList) {
					String nodeName = element.attribute("id").getText();
						if(nodeName.equals(nodeId)){//当节点为当前节点时返回List
							return nodeIdList;
						}
						else if(nodeName.contains("Event")||nodeName.contains("sequenceFlow")){//排除开始，结束，线
							System.out.println("该节点为开始节点或者结束节点或者线");
						}
						else{
							List<Element> eles = element.elements();
						     for (int i = 0; i<eles.size();i++) {
						    	 if(eles.get(i).getName().equals("label")){
						    		 Map<String,Object> nodeMap =new HashMap<String,Object>();
						    		 String NodeLabel = eles.get(i).getText(); 
						    		 //System.out.println("NodeLabel:"+NodeLabel);
						    		 nodeMap.put(nodeName,NodeLabel);
						    		 nodeIdList.add(nodeMap);
						    		// System.out.println("Map:"+nodeMap);
						    	 }
						     }
						}
				}
			} catch (DocumentException e) {
			e.printStackTrace();
		}
		return nodeIdList;
	}
	/**
	 * 批量生成调用的方法xhmethod
	 * @param 
	 * @return a
	 */
	public String xhmethod(Boolean isDeploy, Long[] ids,String typeName,Long[] status){    //*** 
		int su=0,fail1=0;
		String a="";
		if (typeName.equals("flowChart"))
		{
		   for(int i=0;i<ids.length;i++){
			System.out.println(ids[i]+","+i);			
			BpmDefinition bpmDefinition=bpmDefinitionDao.getById(ids[i]);
			System.out.println(bpmDefinition.getTypeId());
			if(status[i]==0||status[i]==2||status[i]==3)
				{System.out.println("status+"+status);
					fail1++;continue;	
				}

				String templateName ="cz_model";
				int flags=1;
				int f=this.bpmDefinitionCreater(bpmDefinition,templateName,flags,isDeploy);
				if(f!=0)
				{
					
					su++;
				}
				else
				{	
					fail1++;
				}
		      }	
			}
			else if(typeName.equals("operationChart"))
			{
				for(int i=0;i<ids.length;i++){
					System.out.println(ids[i]+","+i);			
					BpmDefinition bpmDefinition=bpmDefinitionDao.getById(ids[i]);
					System.out.println(bpmDefinition.getTypeId());
				if(status[i]==0||status[i]==2||status[i]==3)
				{System.out.println("status+"+status);
					fail1++;continue;	
				}
				String templateName ="sw_model";
				int flags=0;
				int f=this.bpmDefinitionCreater(bpmDefinition,templateName,flags,isDeploy);
				if(f!=0)
					
					su++;
				else
				{
					fail1++;								
				}				
				
			}
		}   
		if(typeName.equals("flowChart"))
		 a=+su+("生成操作图成功")+fail1+("生成操作图失败");
		else if(typeName.equals("operationChart"))
		 a=+su+("生成事物图成功")+fail1+("生成事物图失败");
		return a;

	}  
	public 	List<BpmDefinition> getBySubjectAndTypeId(String subject,String typeId)
	{
		return dao.getBySubjectAndTypeId(subject,typeId);
	}
}
