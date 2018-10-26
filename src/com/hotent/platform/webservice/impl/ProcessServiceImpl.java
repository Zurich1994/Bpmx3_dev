package com.hotent.platform.webservice.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.security.core.GrantedAuthority;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.Dom4jUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.bpm.BpmProStatusDao;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.model.bpm.AgentSetting;
import com.hotent.platform.model.bpm.AssignUsers;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmProCopyto;
import com.hotent.platform.model.bpm.BpmProTransTo;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.BpmUserCondition;
import com.hotent.platform.model.bpm.ExecutionStack;
import com.hotent.platform.model.bpm.NodeTranUser;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskFork;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;

import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.AgentSettingService;
import com.hotent.platform.service.bpm.AssignUsersService;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeSignService;
import com.hotent.platform.service.bpm.BpmProCopytoService;
import com.hotent.platform.service.bpm.BpmProTransToService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.BpmTaskExeService;
import com.hotent.platform.service.bpm.BpmUserConditionService;
import com.hotent.platform.service.bpm.ExecutionStackService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskApprovalItemsService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.TaskSignDataService;
import com.hotent.platform.service.bpm.impl.BpmActService;

import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.platform.webservice.api.ProcessService;
import com.hotent.platform.webservice.impl.util.AgentSettingUtil;
import com.hotent.platform.webservice.impl.util.BpmNodeUtil;
import com.hotent.platform.webservice.impl.util.DefRevocationUtil;
import com.hotent.platform.webservice.impl.util.GsonUtil;
import com.hotent.platform.webservice.model.BpmFinishTask;

/**
 * 流程对外服务接口实现类
 * @author csx
 *
 */
public class ProcessServiceImpl implements ProcessService {
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private BpmService bpmService;
	@Resource
	private TaskService taskService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	BpmActService bpmActService;
	@Resource
	private TaskSignDataService taskSignDataService;
	@Resource
	private TaskApprovalItemsService taskApprovalItemsService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private ExecutionStackService executionStackService;
	@Resource
	private BpmProStatusDao bpmProStatusDao;
	@Resource
	private TaskDao taskDao;
	@Resource
	private BpmNodeSignService bpmNodeSignService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	@Resource
	private BpmUserConditionService bpmUserConditionService;
	@Resource
	private BpmTaskExeService bpmTaskExeService;
	@Resource
	private BpmProCopytoService bpmProCopytoService;
	@Resource
	private BpmProTransToService bpmProTransToService;
	@Resource
	private AgentSettingService agentSettingService;
	@Resource
	private BpmRunLogService bpmRunLogService;
	@Resource
	private AssignUsersService assignUsersService;

	public String addSignUsers(String signUserIds, String taskId) {
		if (StringUtils.isNotEmpty(taskId) && StringUtils.isNotEmpty(signUserIds)) {
			try {
				taskSignDataService.addSign(signUserIds, taskId, null, null);
				return genMessage(true, "成功增加会签人员");
			} catch (Exception e) {
				return genMessage(false, e.getMessage());
			}
		} else {
			return genMessage(false, "signUserIds或taskId不能为空");
		}
	}
	
	public String canSelectedUser(String taskId){
		boolean isHidePath=false;
		TaskEntity taskEntity =bpmService.getTask(taskId);
		String nodeId = taskEntity.getTaskDefinitionKey();
		String actDefId = taskEntity.getProcessDefinitionId();
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(actDefId, nodeId);
		if(BpmNodeSet.HIDE_PATH.equals(bpmNodeSet.getIsHidePath())){
			isHidePath=true;
		}
		return genMessage(isHidePath, "");
	}
	
	public String doNext(String xml){
		ContextUtil.clearAll();
		
		Document doc= Dom4jUtil.loadXml(xml);
		Element root=doc.getRootElement();
		String taskId=root.attributeValue("taskId");
		String userAccount=root.attributeValue("account");
		String voteAgree=root.attributeValue("voteAgree");
		String voteContent=root.attributeValue("voteContent");
		String nextNodeId = root.attributeValue("nextNodeId");
		String nextUser = root.attributeValue("nextUser");
		String businessKey = root.attributeValue("businessKey");
		String isBack = root.attributeValue("isBack");
		
		if(StringUtil.isEmpty(taskId)){
			String errorMesage=genMessage(false,"流程任务ID必须填写!");
			String rtnXml=genMessage(false,errorMesage);
			return rtnXml;
		}
		
		ProcessCmd processCmd=new ProcessCmd();
		
		if(StringUtil.isNotEmpty(nextNodeId)){
			processCmd.setLastDestTaskIds(new String[]{nextNodeId});
			processCmd.setDestTask(nextNodeId);
		}
		
		if(StringUtil.isNotEmpty(businessKey)){
			processCmd.setBusinessKey(businessKey);
		}

		if (StringUtil.isNotEmpty(nextUser)) {
			StringBuffer sb = new StringBuffer();
			for (String uac : nextUser.split(",")) {
				SysUser sysUser = sysUserService.getByAccount(uac);
				String str = "user" + "^" + sysUser.getUserId() + "^" + sysUser.getFullname();
				if (sb.length() > 0) {
					sb.append("#");
				}
				sb.append(str);
			}
			processCmd.setLastDestTaskUids(new String[] { sb.toString() });
		}
		
		if(StringUtil.isNotEmpty(taskId)){
			processCmd.setTaskId(taskId);
		}
		//处理用户账号必填。
		if(StringUtil.isEmpty(userAccount)){
			String errorMesage=genMessage(false,"处理用户账号必填!");
			String rtnXml=genMessage(false,errorMesage);
			return rtnXml;
		}
		
		processCmd.setUserAccount(userAccount);
		
		if(StringUtil.isNotEmpty(voteAgree)){
			processCmd.setVoteAgree(Short.parseShort(voteAgree));
		}
		
		if(StringUtil.isNotEmpty(voteContent)){
			processCmd.setVoteContent(voteContent);
		}
		
		if(StringUtil.isNotEmpty(isBack)&&!"null".equals(isBack)){
			processCmd.setBack(Integer.parseInt(isBack));
		}
		
		try{
			//计算流程变量。
			List<Element> vars=root.elements("var");
			if(BeanUtils.isNotEmpty(vars)){
				Map<String,Object> variables=addVars(vars);
				processCmd.setVariables(variables);
			}
			SysUser user= ContextUtil.getCurrentUser();
			if(user==null){
				ContextUtil.setCurrentUserAccount(userAccount);
			}
			Element formDataEl= root.element("formData");
			if(formDataEl!=null){
				String formData=formDataEl.getStringValue();
				processCmd.setFormData(formData);
			}
			
			processRunService.nextProcess(processCmd);
			return genMessage(true, "任务处理成功");
		} catch (Exception ex) {
			return genMessage(false, ex.getMessage());
		}
	}
	
	public String endProcessByTaskId(String taskId){
		try{
			bpmActService.endProcessByTaskId(taskId);
			return genMessage(true, "成功结束流程实例");
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String getApprovalItems(String taskId) {
		TaskEntity taskEntity = bpmService.getTask(taskId);
		if (taskEntity == null)
			return null;
		ProcessRun processRun = processRunService.getByActInstanceId(Long.parseLong(taskEntity.getProcessInstanceId()));
		if (processRun == null)
			return null;
		String actDefId = processRun.getActDefId();
		String nodeId = taskEntity.getTaskDefinitionKey();

		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
		List<String> list = taskApprovalItemsService.getApprovalByDefKeyAndTypeId(bpmDefinition.getDefKey(), bpmDefinition.getTypeId());
		if (list == null)
			return null;
		Document createDoc = DocumentHelper.createDocument();
		Element docRoot = createDoc.addElement("list");
		for(String appItem:list){
			Element elements = docRoot.addElement("item");
			elements.addAttribute("word", appItem.toString());
		}
		return Dom4jUtil.docToPrettyString(createDoc);
	}
	
	public String getBpmDefinition(String xml){
		Document doc= Dom4jUtil.loadXml(xml);
		Element root=doc.getRootElement();
		String account=root.attributeValue("account"); //用户账号
		String pageSize=root.attributeValue("pageSize");//页面记录数大小
		String currentPage=root.attributeValue("currentPage");//当前页   
		SysUser sysUser=sysUserService.getByAccount(account);
		if(sysUser==null){
			return genMessage(false,"不存在该帐号的用户");
		}
		String userId=sysUser.getUserId().toString();
		PageBean pb=new PageBean();
		if(StringUtil.isNotEmpty(pageSize)){
			pb.setPagesize(new Integer(pageSize));
		}
		if(StringUtils.isNotEmpty(currentPage)){
			pb.setCurrentPage(new Integer(currentPage));
		}

		if (StringUtils.isEmpty(userId))
			return null;
		String isNeedRight = "yes";
		String actRights = " ";
		if (SysUser.isSuperAdmin(sysUser)) {
			isNeedRight = "";
		}else{
			//获得流程分管授权与用户相关的信息
			Map<String,Object> actRightMap = bpmDefAuthorizeService.getActRightByUserMap(new Long(userId), BPMDEFAUTHORIZE_RIGHT_TYPE.MANAGEMENT,false,false);
			//获得流程分管授权与用户相关的信息集合的流程KEY
			actRights = (String) actRightMap.get("authorizeIds");
		}
		
		try{
			List<BpmDefinition> bpmDefinitionList = bpmDefinitionService.getUserBpmList(actRights,isNeedRight);
			return BpmDefinition2Xml(bpmDefinitionList);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	/**
	 * 检查是否是超级管理员
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	private boolean checkSuperAdmin(SysUser user){
		Collection<GrantedAuthority> auths= user.getAuthorities();
		//是否是超级管理员
		if(auths!=null&&auths.size()>0&&auths.contains(SystemConst.ROLE_GRANT_SUPER)){
			return true;
		}
		return false;
	}

	public String getByBusinessKey(String businessKey) {
		try{
			ProcessRun processRun = processRunService.getByBusinessKey(businessKey); 
			Element element = ProcessRun2Xml(null, processRun);
			return Dom4jUtil.docToPrettyString(element.getDocument());
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	@Override
	public String getDestNodeHandleUsers(String taskId, String destNodeId) {
		try {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			String instanceId = taskEntity.getProcessInstanceId();
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("executionId", taskEntity.getExecutionId());
			Set<TaskExecutor> userSet=bpmService.getNodeHandlerUsers(instanceId, destNodeId,vars);
			return TaskExecutorSet2Xml(userSet);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String getFinishTask(String xml) {
		Document doc= Dom4jUtil.loadXml(xml);
		Element root=doc.getRootElement();
		String account=root.attributeValue("account"); //用户账号
		String taskName=root.attributeValue("taskName"); //任务名称
		String subject=root.attributeValue("subject");//任务标题		
		String pageSize=root.attributeValue("pageSize");//页面记录数大小
		String currentPage=root.attributeValue("currentPage");//当前页
		SysUser sysUser=sysUserService.getByAccount(account);
		if(sysUser==null){
			return genMessage(false,"不存在该帐号的用户");
		}
		String userId=sysUser.getUserId().toString();
		PageBean pb=new PageBean();
		if(StringUtil.isNotEmpty(pageSize)){
			pb.setPagesize(new Integer(pageSize));
		}
		if(StringUtils.isNotEmpty(currentPage)){
			pb.setCurrentPage(new Integer(currentPage));
		}
		try{
			List<BpmFinishTask> bpmFinishTaskList=taskOpinionService.getByFinishTask(Long.parseLong(userId), subject, taskName, pb);
			return BpmFinishTask2Xml(bpmFinishTaskList);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String getFinishTaskDetailUrl(String actInstId,String nodeKey){
		ProcessRun processRun =processRunService.getByActInstanceId(new Long(actInstId));
		BpmNodeSet bpmNodeSet=bpmNodeSetService.getByMyActDefIdNodeId(processRun.getActDefId(), nodeKey);
		
		String detailUrl=bpmNodeSet.getDetailUrl();
		if(StringUtils.isNotEmpty(detailUrl)){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("pk", processRun.getBusinessKey());
			detailUrl=StringUtil.formatParamMsg(detailUrl, map);
			return genMessage(true, detailUrl);
		} else {
			return genMessage(false, "未获取到明细地址");
		}
	}

	public String getMyProcessRunByXml(String xml) {
		Document doc= Dom4jUtil.loadXml(xml);
		Element root=doc.getRootElement();
		String account=root.attributeValue("account"); //用户账号
		String subject=root.attributeValue("subject");//任务标题
		String status=root.attributeValue("status");//流程实例状态		
		String pageSize=root.attributeValue("pageSize");//页数
		String currentPage=root.attributeValue("currentPage");//当前页
		SysUser sysUser=sysUserService.getByAccount(account);
		if(sysUser==null){
			return genMessage(false,"不存在该帐号的用户");
		}
		String userId=sysUser.getUserId().toString();
		Short sts=1;
		if(StringUtils.isNotEmpty(status)){
			sts=Short.parseShort(status);
		}
		PageBean pb=new PageBean();
		if(StringUtil.isNotEmpty(pageSize)){
			pb.setPagesize(new Integer(pageSize));
		}
		if(StringUtils.isNotEmpty(currentPage)){
			pb.setCurrentPage(new Integer(currentPage));
		}
		try{
			List<ProcessRun> processList=processRunService.getMyProcessRun(Long.parseLong(userId), subject, sts, pb);
			return ProcessRunList2Xml(processList);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String getNextFlowNodes(String taskId, String account) {
		SysUser sysUser=sysUserService.getByAccount(account);
		if(sysUser==null){
			return genMessage(false,"不存在该帐号的用户");
		}
		Long userId = sysUser.getUserId();
		List<NodeTranUser> list = bpmService.getNodeTaskUserMap(taskId, userId, true);
		
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("list");
		for(NodeTranUser node : list){
			Element nodeElement = nodesElement.addElement("node");
			Dom4jUtil.addAttribute(nodeElement, "nodeId", node.getNodeId());
			Dom4jUtil.addAttribute(nodeElement, "nodeName", node.getNodeName());
		}
		return Dom4jUtil.docToPrettyString(document);
	}
	
	public String getProcessOpinionByActInstId(String actInstId) {
		try{
			List<TaskOpinion> taskOpinionList=taskOpinionService.getByActInstId(actInstId);
			return TaskOpinionList2Xml(taskOpinionList);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String getProcessOpinionByRunId(String runId) {
		if(StringUtil.isEmpty(runId)){
			return genMessage(false, "runId不能为空");
		}
		try{
			ProcessRun processRun=processRunService.getById(Long.parseLong(runId));
			List<TaskOpinion> taskOpinionList= taskOpinionService.getByActInstId(processRun.getActInstId());
			return TaskOpinionList2Xml(taskOpinionList);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String getProcessRun(String xml){		
		Document doc= Dom4jUtil.loadXml(xml);
		Element root=doc.getRootElement();
		String defId=root.attributeValue("defId"); //流程定义ID
		String actDefId=root.attributeValue("actDefId"); //act流程定义ID
		String defKey =root.attributeValue("defKey"); //流程定义Key		
		String pageSize=root.attributeValue("pageSize");//页面记录数大小
		String currentPage=root.attributeValue("currentPage");//当前页数		
		
		//流程定义ID、act流程定义ID、流程定义Key三选一。
		if(StringUtil.isEmpty(defId) && StringUtil.isEmpty(actDefId) && StringUtil.isEmpty(defKey)){
			return genMessage(false,"流程定义ID、act流程定义ID、流程定义Key必须填写一个!");
		}
		
		if(StringUtils.isNotEmpty(defKey)){
			BpmDefinition bpmDefinition=bpmDefinitionService.getMainByDefKey(defKey); 
			if(bpmDefinition!=null){
				actDefId=bpmDefinition.getActDefId();
			}			   
		}
		
		if(StringUtils.isNotEmpty(defId)){
			BpmDefinition bpmDefinition=bpmDefinitionService.getById(Long.parseLong(defId)); 
			if(bpmDefinition!=null){
				actDefId=bpmDefinition.getActDefId();
			}
		}		
		
		PageBean pb=new PageBean();
		if(StringUtil.isNotEmpty(pageSize)){
			pb.setPagesize(new Integer(pageSize));
		}else{
			pb.setPagesize(20);
		}
		if(StringUtils.isNotEmpty(currentPage)){
			pb.setCurrentPage(new Integer(currentPage));
		}else{
			pb.setCurrentPage(1);
		}
		try{
			List<ProcessRun> processList = processRunService.getByActDefId(actDefId, pb);
			return ProcessRunList2Xml(processList);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getProcessRunByRunId(String runId) {
		if(StringUtil.isEmpty(runId)){
			return genMessage(false, "runId不能为空");
		}
		try{
			ProcessRun processRun = processRunService.getById(Long.parseLong(runId)); 
			Element element = ProcessRun2Xml(null,processRun);
			return Dom4jUtil.docToPrettyString(element.getDocument());
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String getProcessRunByTaskId(String taskId){
		try{
			TaskEntity taskEntity =bpmService.getTask(taskId);
			ProcessRun processRun=processRunService.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			Element element = ProcessRun2Xml(null,processRun);
			return Dom4jUtil.docToPrettyString(element.getDocument());
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getStatusByRunidNodeId(String runId, String nodeId) {
		try{
			Integer status = bpmProStatusDao.getStatusByRunidNodeid(runId, nodeId);
			if(status==null){
				return genMessage(false, "未获取到该实例该节点的状态");
			} else {
				return genMessage(true, status.toString());
			}
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getTaskByTaskId(String taskId) {
		try{
			ProcessTask processTask=taskDao.getByTaskId(taskId);
			constructTaskUrl(processTask);
			Element element = ProcessTask2Xml(null,processTask); 
			return Dom4jUtil.docToPrettyString(element.getDocument());
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getTaskFormUrl(String taskId) {
		try {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (taskEntity == null)
				return null;
			Map<String, Object> variables = taskService.getVariables(taskId);
			String bussinessKey = variables.get("businessKey").toString();
			if(StringUtil.isEmpty(bussinessKey)){
				bussinessKey=(String)taskService.getVariable(taskId, "businessKey");
			}
			String form = "";
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(taskEntity.getProcessDefinitionId(), taskEntity.getTaskDefinitionKey());
			if (bpmNodeSet == null)
				return null;
			String formUrl = bpmNodeSet.getFormUrl();
			// 表单的URL和表单key不为空。
			if (StringUtil.isNotEmpty(formUrl) && StringUtil.isNotEmpty(bussinessKey)) {
				// 替换表单的主键。
				// 例如：get.ht?id={pk}&flowRunId={flowRunId}
				form = formUrl.replaceFirst(BpmConst.FORM_PK_REGEX, bussinessKey);
				if (variables != null) {
					Pattern regex = Pattern.compile("\\{(.*?)\\}");
					Matcher regexMatcher = regex.matcher(form);
					while (regexMatcher.find()) {
						String toreplace = regexMatcher.group(0);
						String varName = regexMatcher.group(1);
						if (!variables.containsKey(varName))
							continue;
						form = form.replace(toreplace, variables.get(varName).toString());
					}
				}
			}
			if(StringUtil.isEmpty(form)){
				return genMessage(false, "未获取到任务对应的url地址");
			} else {
				return genMessage(true, form);
			}
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getTaskNameByTaskId(String taskId) {
		ProcessTask processTask = taskDao.getByTaskId(taskId);
		if(processTask!=null){
			return genMessage(true, processTask.getName());
		} else {
			return genMessage(false, "未获取到该taskId的任务");
		}
	}

	public String getTaskNode(String taskId) {
		try {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (taskEntity == null)
				return genMessage(false, "未获取到该taskId的任务");
			ProcessDefinitionEntity pde = bpmService.getProcessDefinitionEntity(taskEntity.getProcessDefinitionId());
			if (pde == null)
				return genMessage(false, "未获取流程定义");
			ActivityImpl curAct = pde.findActivity(taskEntity.getTaskDefinitionKey());
			if (curAct == null)
				return genMessage(false, "未获取节点定义");
			String nodeName = (String) curAct.getProperty("name");
			String nodeType = (String) curAct.getProperty("type");
			String nodeId = curAct.getId();

			Document createDoc = DocumentHelper.createDocument();
			Element docRoot = createDoc.addElement("node");
			docRoot.addAttribute("nodeId", nodeId);
			docRoot.addAttribute("nodeName", nodeName);
			docRoot.addAttribute("nodeType", nodeType);
			return Dom4jUtil.docToPrettyString(createDoc);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getTaskOutNodes(String taskId) {
		TaskEntity taskEntity = bpmService.getTask(taskId);
		if (taskEntity == null)
			return null;
		FlowNode flowNode = NodeCache.getByActDefId(taskEntity.getProcessDefinitionId()).get(taskEntity.getTaskDefinitionKey());
		if (flowNode == null)
			return null;
		Document createDoc = DocumentHelper.createDocument();
		Element docRoot = createDoc.addElement("list");
		for (FlowNode fnode : flowNode.getNextFlowNodes()) {
			Element elements = docRoot.addElement("node");
			elements.addAttribute("nodeId",fnode.getNodeId());
			elements.addAttribute("nodeName",fnode.getNodeName());
			elements.addAttribute("nodeType",fnode.getNodeType());
		}
		return Dom4jUtil.docToPrettyString(createDoc);
	}

	public String getTasksByAccount(String xml) {
		try{
			Document doc= Dom4jUtil.loadXml(xml);
			Element root=doc.getRootElement();
			String account=root.attributeValue("account");
			
			if(StringUtil.isEmpty(account)){
				return genMessage(false, "必须传入用户账号");
			}
			SysUser user = sysUserService.getByAccount(account);
			if(user==null){
				return genMessage(false, "不存在该账号的用户");
			}
			
			String taskName = Dom4jUtil.getString(root, "taskNodeName", true);
			String subject = Dom4jUtil.getString(root, "subject", true);
			String processName = Dom4jUtil.getString(root, "processName", true);
			String orderField = Dom4jUtil.getString(root, "orderField");
			String orderSeq = Dom4jUtil.getString(root, "orderSeq");
			String pageSize = Dom4jUtil.getString(root,"pageSize");
			String currentPage = Dom4jUtil.getString(root,"currentPage");
			
			PageBean pb = new PageBean();
				
			if(StringUtil.isNotEmpty(pageSize)){
				pb.setPagesize(Integer.parseInt(pageSize));
			}
			if(StringUtil.isNotEmpty(currentPage)){
				pb.setCurrentPage(Integer.parseInt(currentPage));
			}
			
			List<ProcessTask> processTasks = taskDao.getTasks(user.getUserId(), taskName, subject, processName, orderField, orderSeq, pb);
			constructTaskUrls(processTasks);
			return ProcessTaskList2Xml(processTasks);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getTasksByRunId(String runId){
		String str="";
		try {
			if(StringUtil.isEmpty(runId)){
				return genMessage(false, "runId不能为空");
			}
			List<ProcessTask> list = taskDao.getTasksByRunId(Long.parseLong(runId));
			str= ProcessTaskList2Xml(list);
			return str;
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getVariablesByRunId(String runId){
		if(StringUtil.isEmpty(runId)){
			return genMessage(false, "runId不能为空");
		}
		try{
			ProcessRun processRun=processRunService.getById(Long.parseLong(runId));
			Map<String,Object> varMap=runtimeService.getVariables(processRun.getActInstId().toString());
			Iterator<Map.Entry<String, Object>> it= varMap.entrySet().iterator();
			Document createDoc = DocumentHelper.createDocument();			
			Element docRoot = createDoc.addElement("list");	
			while(it.hasNext()){
				Map.Entry<String, Object> entry=it.next();
				Element elements = docRoot.addElement("var");
				elements.addAttribute("varName", entry.getKey());
				if(entry.getValue()!=null){
					elements.addAttribute("varVal", entry.getValue().toString());
				}				
			}
			return Dom4jUtil.docToPrettyString(createDoc);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String getVariablesByTaskId(String taskId) {
		try {
			Task task = bpmService.getTask(taskId);
			Map<String, Object> varMap = runtimeService.getVariables(task.getExecutionId());
			return VarMap2Xml(varMap);
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String isAllowAddSign(String account,String taskId){
		try{
			SysUser sysUser=sysUserService.getByAccount(account);
			if(sysUser==null){
				return genMessage(false,"不存在该帐号的用户");
			}
			Long userId=sysUser.getUserId();
			TaskEntity taskEntity =bpmService.getTask(taskId);
			boolean isAllowAddSign=false;
			ProcessRun processRun=processRunService.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			boolean isSignTask=bpmService.isSignTask(taskEntity);
			if(isSignTask){
				SysOrg sysOrg = sysOrgService.getDefaultOrgByUserId(userId);
				if(sysOrg!=null){
					Long orgId = sysOrg.getOrgId();
					isAllowAddSign = bpmNodeSignService.checkNodeSignPrivilege(processRun.getActDefId(),taskEntity.getTaskDefinitionKey(), BpmNodeSignService.BpmNodePrivilegeType.ALLOW_RETROACTIVE, userId, orgId);
				}
			}
			return genMessage(isAllowAddSign, "");
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String isAllowBack(String taskId){
		try{
			TaskEntity taskEntity =bpmService.getTask(taskId);
			boolean isAllowBack=false;
			String taskToken=(String)taskService.getVariableLocal(taskEntity.getId(),TaskFork.TAKEN_VAR_NAME);
			// 设置了允许回退处理
			ExecutionStack executionStack = executionStackService.getLastestStack(taskEntity.getProcessInstanceId(), taskEntity.getTaskDefinitionKey(), taskToken);
			if (executionStack == null)
				return genMessage(false, "");

			if (executionStack.getParentId() != null && executionStack.getParentId() != 0) {
				ExecutionStack parentStack = executionStackService.getById(executionStack.getParentId());
				if (parentStack != null) {
					isAllowBack = true;
				}
			}
			return genMessage(isAllowBack, "");
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String isSelectPath(String taskId){
		try{
			TaskEntity taskEntity=bpmService.getTask(taskId);
			String actDefId=taskEntity.getProcessDefinitionId();
			String nodeId=taskEntity.getTaskDefinitionKey();
			BpmNodeSet bpmNodeSet=bpmNodeSetService.getByMyActDefIdNodeId(actDefId, nodeId);
			String jumpType=bpmNodeSet.getJumpType();
			if(StringUtil.isNotEmpty(bpmNodeSet.getJumpType())){
				if(jumpType.indexOf("2")!=-1){
					return genMessage(true, "");
				}
			}
			return genMessage(false, "");
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}

	public String setTaskVars(String xml) {
		try{
			Document doc= Dom4jUtil.loadXml(xml);
			Element root=doc.getRootElement();
			String taskId=root.attributeValue("taskId");
			List<Element> vars = root.elements("var");
			if(BeanUtils.isNotEmpty(vars)){
				Map<String,Object> variables=addVars(vars);
				
				for(Iterator<String> it = variables.keySet().iterator();it.hasNext();){
					String varName = it.next();
					taskService.setVariable(taskId, varName, variables.get(varName));
				}
			}
			return genMessage(true, "");
		} catch (Exception e) {
			return genMessage(false, e.getMessage());
		}
	}
	
	public String start(String xml) {
		
		ContextUtil.clearAll();
		
		Document doc= Dom4jUtil.loadXml(xml);
		Element root=doc.getRootElement();
		String actDefId=root.attributeValue("actDefId");
		String flowKey=root.attributeValue("flowKey");
		String subject=root.attributeValue("subject");
		String startUser=root.attributeValue("account");
		String businessKey = root.attributeValue("businessKey");
		//流程key和定义id二选一。
		if(StringUtil.isEmpty(actDefId) && StringUtil.isEmpty(flowKey)){
			String errorMesage=genMessage(false,"流程定义ID和流程key必须填写一个!");
			return errorMesage;
		}
		//启动账户必填。
		if(StringUtil.isEmpty(startUser)){
			String errorMesage=genMessage(false,"启动用户账号必填!");
			return errorMesage;
		}
		ProcessCmd processCmd=new ProcessCmd();
		
		if(StringUtil.isNotEmpty(actDefId)){
			processCmd.setActDefId(actDefId);
		}
		if(StringUtil.isNotEmpty(businessKey)){
			processCmd.setBusinessKey(businessKey);
		}
		if(StringUtil.isNotEmpty(flowKey)){
			processCmd.setFlowKey(flowKey);
		}
		if(StringUtil.isNotEmpty(subject)){
			processCmd.setSubject(subject);
		}
		processCmd.setUserAccount(startUser);
		//表单数据
		List<Element> datas = root.elements("data");
		
		if(BeanUtils.isNotEmpty(datas)){
			Element data = datas.get(0);
			String formData = data.getText();
			if(StringUtil.isNotEmpty(formData)){
				processCmd.setFormData(formData);
			}
		}
		
		try{
			//流程变量
			List<Element> vars = root.elements("var");
			if(BeanUtils.isNotEmpty(vars)){
				Map<String,Object> variables=addVars(vars);
				processCmd.setVariables(variables);
			}
			SysUser user= ContextUtil.getCurrentUser();
			if(user==null){
				ContextUtil.setCurrentUserAccount(startUser);
			}
			
			String runIdStr= root.attributeValue("runId");
			if(StringUtil.isNotEmpty(runIdStr)){
				Long runId = Long.parseLong(runIdStr);
				ProcessRun processRun=processRunService.getById(runId);
				if(BeanUtils.isEmpty(processRun)){
					throw new Exception("草稿已被删除");
				}
				processCmd.setProcessRun(processRun);
			}
			
			ProcessRun processRun = processRunService.startProcess(processCmd);
			Element element = ProcessRun2Xml(null,processRun);
			return Dom4jUtil.docToPrettyString(element.getDocument());
		} catch (Exception e) {
			e.printStackTrace();
			// 返回错误消息。
			return genMessage(false, e.getMessage());
		}
	}

	private String ProcessRunList2Xml(List<ProcessRun> processList) throws Exception {
		if (processList == null)
			return null;
		Document createDoc = DocumentHelper.createDocument();
		Element docRoot = createDoc.addElement("list");
		for (ProcessRun processRun : processList) {
			ProcessRun2Xml(docRoot, processRun);
		}
	    return Dom4jUtil.docToPrettyString(createDoc);
	}

	private void constructTaskUrl(ProcessTask processTask) {
		if (processTask == null)
			return;
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getByMyActDefIdNodeId(processTask.getProcessDefinitionId(), processTask.getTaskDefinitionKey());
		Map<String, Object> varsMap = taskService.getVariables(processTask.getId());

		if (bpmNodeSet == null)
			return;

		String formUrl = bpmNodeSet.getFormUrl();
		if (StringUtil.isNotEmpty(formUrl)) {
			formUrl = StringUtil.formatParamMsg(formUrl, varsMap);
			processTask.setTaskUrl(formUrl);
		}
	}
	
	/**
	 * 构建表单的执行URL。
	 * @param processTask
	 */
	private void constructTaskUrls(List<ProcessTask> processTaskList){
		for(ProcessTask processTask :processTaskList){
			constructTaskUrl(processTask);
		}
	}
	
	/**
	 * 加入流程变量。
	 * @param vars
	 * @return
	 */
	private Map<String,Object> addVars(List<Element> vars)throws Exception{
		Map<String,Object> varMap=new HashMap<String, Object>();
		for(Element var :vars){
			String name=var.attributeValue("varName");
			String value=var.attributeValue("varVal");
			if(StringUtil.isEmpty(name)){
				throw new Exception("流程变量的变量名不能为空");
			}
			if(StringUtil.isEmpty(value)){
				throw new Exception("流程变量的变量值不能为空");
			}
			String dataType=var.attributeValue("varType");
			String dateFormat = var.attributeValue("dateFormat");
			Object obj=convertType(value, dataType,dateFormat);
			varMap.put(name, obj);
		}
		return varMap;
	}
	
	/**
	 * 将数据进行转型
	 * @param value 值
	 * @param dataType 数据类型
	 * @param dateFormat 日期格式
	 * @return
	 */
	private Object convertType(String value,String dataType,String dateFormat){
		if("int".equals(dataType)){
			return new Integer(value);
		}else if("long".equals(dataType)){
			return new Long(value);
		}else if("double".equals(dataType)){
			return new Double(value);
		}else if("date".equals(dataType)){
			if(StringUtil.isEmpty(dateFormat)){
				dateFormat = "yyyy-MM-dd HH:mm:ss";
			}
			return TimeUtil.convertString(value,dateFormat);
		}
		return value;
	}
	
	/**
	 * 构建返回的xml消息
	 * @param result true:表示成功  false:表示失败
	 * @param message 消息内容
	 * @return
	 */
	private String genMessage(Boolean result,String message){
		return "<result result=\""+result.toString()+"\" message=\""+message +"\"/>";
	}
	
	private String BpmFinishTask2Xml(List<BpmFinishTask> bpmFinishTaskList)throws Exception{
		Document createDoc = DocumentHelper.createDocument();			
		Element docRoot = createDoc.addElement("list");	
		for(BpmFinishTask finishTask:bpmFinishTaskList){
			Element elements = docRoot.addElement("task");
			Dom4jUtil.addAttribute(elements,"actInstId",finishTask.getActInstId());
		    Dom4jUtil.addAttribute(elements,"businessKey",finishTask.getBusinessKey());
		    Dom4jUtil.addAttribute(elements,"exeFullname",finishTask.getExeFullname());
			Dom4jUtil.addAttribute(elements,"flowName",finishTask.getFlowName());
			Dom4jUtil.addAttribute(elements,"formUrl",finishTask.getFormUrl());
			Dom4jUtil.addAttribute(elements,"opinion",finishTask.getOpinion());
			Dom4jUtil.addAttribute(elements,"subject",finishTask.getSubject());
			Dom4jUtil.addAttribute(elements,"taskKey",finishTask.getTaskKey());
			Dom4jUtil.addAttribute(elements,"taskName",finishTask.getTaskName());
			Dom4jUtil.addAttribute(elements, "checkStatus", finishTask.getCheckStatus());
			Dom4jUtil.addAttribute(elements, "durTime", finishTask.getDurTime());
			Dom4jUtil.addAttribute(elements, "endTime", finishTask.getEndTime());
			Dom4jUtil.addAttribute(elements, "exeUserId", finishTask.getExeUserId());
			Dom4jUtil.addAttribute(elements, "opinionId", finishTask.getOpinionId());
			Dom4jUtil.addAttribute(elements, "startTime", finishTask.getStartTime());
			Dom4jUtil.addAttribute(elements, "taskId", finishTask.getTaskId());
		}
		return Dom4jUtil.docToPrettyString(createDoc);
	}
	
	private String BpmDefinition2Xml(List<BpmDefinition> list)throws Exception{
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("list");
		for (BpmDefinition bp : list) {
			Element nodeElement = nodesElement.addElement("definition");
			Dom4jUtil.addAttribute(nodeElement,"actDefId",bp.getActDefId());			
		    Dom4jUtil.addAttribute(nodeElement,"actDefKey",bp.getActDefKey());
		    Dom4jUtil.addAttribute(nodeElement,"canChoicePath",bp.getCanChoicePath());
			Dom4jUtil.addAttribute(nodeElement,"defKey",bp.getDefKey());		
			Dom4jUtil.addAttribute(nodeElement,"defXml",bp.getDefXml());
			Dom4jUtil.addAttribute(nodeElement,"descp",bp.getDescp());
			Dom4jUtil.addAttribute(nodeElement,"formDetailUrl",bp.getFormDetailUrl());
			Dom4jUtil.addAttribute(nodeElement,"reason",bp.getReason());
			Dom4jUtil.addAttribute(nodeElement,"subject",bp.getSubject());
			Dom4jUtil.addAttribute(nodeElement,"taskNameRule",bp.getTaskNameRule());
			Dom4jUtil.addAttribute(nodeElement,"typeName",bp.getTypeName());
			Dom4jUtil.addAttribute(nodeElement,"actDeployId", bp.getActDeployId());
			Dom4jUtil.addAttribute(nodeElement,"createBy", bp.getCreateBy());
			Dom4jUtil.addAttribute(nodeElement,"createtime", bp.getCreatetime());
			Dom4jUtil.addAttribute(nodeElement,"defId", bp.getDefId());
			Dom4jUtil.addAttribute(nodeElement,"isMain", bp.getIsMain());
			Dom4jUtil.addAttribute(nodeElement,"isUseOutForm", bp.getIsUseOutForm());
			Dom4jUtil.addAttribute(nodeElement,"parentDefId", bp.getParentDefId());
			Dom4jUtil.addAttribute(nodeElement,"showFirstAssignee", bp.getShowFirstAssignee());
			Dom4jUtil.addAttribute(nodeElement,"status", bp.getStatus());
			Dom4jUtil.addAttribute(nodeElement,"toFirstNode", bp.getToFirstNode());
			Dom4jUtil.addAttribute(nodeElement,"typeId", bp.getTypeId());
			Dom4jUtil.addAttribute(nodeElement,"updateBy", bp.getUpdateBy());
			Dom4jUtil.addAttribute(nodeElement,"updatetime", bp.getUpdatetime());
			Dom4jUtil.addAttribute(nodeElement,"versionNo", bp.getVersionNo());
		}
		return Dom4jUtil.docToPrettyString(document);
	}
	
	private String TaskOpinionList2Xml(List<TaskOpinion> list) throws Exception{
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("list");
		for (TaskOpinion pt : list) {
			Element nodeElement = nodesElement.addElement("task");
			Dom4jUtil.addAttribute(nodeElement,"opinionId", pt.getOpinionId());
			Dom4jUtil.addAttribute(nodeElement,"actInstId", pt.getActInstId());
			Dom4jUtil.addAttribute(nodeElement,"taskName", pt.getTaskName());
			Dom4jUtil.addAttribute(nodeElement,"taskKey", pt.getTaskKey());
			Dom4jUtil.addAttribute(nodeElement,"taskId", pt.getTaskId());
			Dom4jUtil.addAttribute(nodeElement,"startTime", pt.getStartTime());
			Dom4jUtil.addAttribute(nodeElement,"endTime", pt.getEndTime());
			Dom4jUtil.addAttribute(nodeElement,"durTime", pt.getDurTime());
			Dom4jUtil.addAttribute(nodeElement,"exeUserId", pt.getExeUserId());
			Dom4jUtil.addAttribute(nodeElement,"exeFullname", pt.getExeFullname());
			Dom4jUtil.addAttribute(nodeElement,"opinion", pt.getOpinion());
		}
		return Dom4jUtil.docToPrettyString(document);
	}
	
    /**
     * ProcessTaskList转 xml
     * @param list
     * @return
     * @throws Exception
     */
	private String ProcessTaskList2Xml(List<ProcessTask> list) throws Exception {
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("list");
		for (ProcessTask pt : list) {
			ProcessTask2Xml(nodesElement,pt);
		}
		return Dom4jUtil.docToPrettyString(document);
	}
	
	private Element ProcessTask2Xml(Element parentElement,ProcessTask pt){
		Element nodeElement;
		if(BeanUtils.isEmpty(parentElement)){
			Document document = DocumentHelper.createDocument();
			nodeElement = document.addElement("task");
		} else {
			nodeElement = parentElement.addElement("task");
		}
		Dom4jUtil.addAttribute(nodeElement,"id", pt.getId());
		Dom4jUtil.addAttribute(nodeElement,"name", pt.getName());
		Dom4jUtil.addAttribute(nodeElement,"subject", pt.getSubject());
		Dom4jUtil.addAttribute(nodeElement,"assignee", pt.getAssignee());
		Dom4jUtil.addAttribute(nodeElement,"executionId", pt.getExecutionId());
		Dom4jUtil.addAttribute(nodeElement,"owner", pt.getOwner());
		Dom4jUtil.addAttribute(nodeElement,"priority", pt.getPriority());
		Dom4jUtil.addAttribute(nodeElement,"processDefinitionId",pt.getProcessDefinitionId());
		Dom4jUtil.addAttribute(nodeElement,"processInstanceId",pt.getProcessInstanceId());
		Dom4jUtil.addAttribute(nodeElement,"processName", pt.getProcessName());
		Dom4jUtil.addAttribute(nodeElement,"taskDefinitionKey",pt.getTaskDefinitionKey());
		Dom4jUtil.addAttribute(nodeElement,"createTime", pt.getCreateTime());
		Dom4jUtil.addAttribute(nodeElement,"typeId",pt.getTypeId() );
		Dom4jUtil.addAttribute(nodeElement,"typeName", pt.getTypeName());
		Dom4jUtil.addAttribute(nodeElement,"type", pt.getDescription());
		Dom4jUtil.addAttribute(nodeElement,"status", pt.getTaskStatus());
		
		return nodeElement;
	}
	
	private Element ProcessRun2Xml(Element parentElement,ProcessRun processRun)throws Exception{
		Element docRoot;
		if(BeanUtils.isEmpty(parentElement)){
			Document document = DocumentHelper.createDocument();
			docRoot = document.addElement("run");
		} else {
			docRoot = parentElement.addElement("run");
		}
		Dom4jUtil.addAttribute(docRoot,"actDefId", processRun.getActDefId());
		Dom4jUtil.addAttribute(docRoot,"actInstId", processRun.getActInstId());
		Dom4jUtil.addAttribute(docRoot,"busDescp", processRun.getBusDescp());
		Dom4jUtil.addAttribute(docRoot,"businessKey", processRun.getBusinessKey());
		Dom4jUtil.addAttribute(docRoot,"businessUrl", processRun.getBusinessUrl());
		Dom4jUtil.addAttribute(docRoot,"creator", processRun.getCreator());
		Dom4jUtil.addAttribute(docRoot,"processName", processRun.getProcessName());
		Dom4jUtil.addAttribute(docRoot,"startOrgName", processRun.getStartOrgName());
		Dom4jUtil.addAttribute(docRoot,"subject", processRun.getSubject());
		Dom4jUtil.addAttribute(docRoot, "createtime", processRun.getCreatetime());
		Dom4jUtil.addAttribute(docRoot, "creatorId", processRun.getCreatorId());
		Dom4jUtil.addAttribute(docRoot, "defId", processRun.getDefId());
		Dom4jUtil.addAttribute(docRoot, "duration", processRun.getDuration());
		Dom4jUtil.addAttribute(docRoot, "endTime", processRun.getEndTime());
		Dom4jUtil.addAttribute(docRoot, "formDefId", processRun.getFormDefId());
		Dom4jUtil.addAttribute(docRoot, "parentId", processRun.getParentId());
		Dom4jUtil.addAttribute(docRoot, "recover", processRun.getRecover());
		Dom4jUtil.addAttribute(docRoot, "runId", processRun.getRunId());
		Dom4jUtil.addAttribute(docRoot, "startOrgId", processRun.getStartOrgId());
		Dom4jUtil.addAttribute(docRoot, "status", processRun.getStatus());
		Dom4jUtil.addAttribute(docRoot, "updateBy", processRun.getUpdateBy());
		Dom4jUtil.addAttribute(docRoot, "updatetime", processRun.getUpdatetime());
		return docRoot;
	}
	
	private String VarMap2Xml(Map<String,Object> map)throws Exception{
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("list");
		Iterator<Map.Entry<String, Object>> it= map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Object> entry=it.next();
			Element nodeElement = nodesElement.addElement("var");
			Dom4jUtil.addAttribute(nodeElement, "varName", entry.getKey());
			Dom4jUtil.addAttribute(nodeElement, "varVal", entry.getValue());
		}
		return Dom4jUtil.docToPrettyString(document);
	}
	
	private String TaskExecutorSet2Xml(Set<TaskExecutor> taskExecurot)throws Exception{
		Document document = DocumentHelper.createDocument();
		Element nodesElement = document.addElement("list");
		for(TaskExecutor executor : taskExecurot){
			Element nodeElement = nodesElement.addElement("executor");
			Dom4jUtil.addAttribute(nodeElement, "type", executor.getType());
			Dom4jUtil.addAttribute(nodeElement, "executeId", executor.getExecuteId());
			Dom4jUtil.addAttribute(nodeElement, "executor", executor.getExecutor());
			Dom4jUtil.addAttribute(nodeElement, "exactType", executor.getExactType());
		}
		return Dom4jUtil.docToPrettyString(document);
	}

	@Override
	public String getXml() {
		// TODO Auto-generated method stub
		return "<root><rtn name='ray' age='1'><rtn name='laowang' age='23'></root>";
	}

	@Override
	public String getBpmAllNode(String defId) {
		JSONObject result = new JSONObject();

		Long id = null;
		try {
			id = new Long(defId);
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "请正确输入流程定义ID");
			return result.toString();
		}
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(id);
		String actDefId = bpmDefinition.getActDefId();
		List<BpmNodeSet> nodeSetList = bpmNodeSetService.getByDefId(id);
		FlowNode startFlowNode = NodeCache.getStartNode(actDefId);
		BpmNodeUtil.removeListInFlowNode(startFlowNode);
		List<FlowNode> endFlowNodeList = NodeCache.getEndNode(actDefId);
		for (FlowNode flowNode : endFlowNodeList) {
			BpmNodeUtil.removeListInFlowNode(flowNode);
		}

		result.put("nodeSetList", nodeSetList);
		result.put("startFlowNode", startFlowNode);
		result.put("endFlowNodeList", endFlowNodeList);

		result.put("state", "0");
		result.put("msg", "请求成功");
		// System.out.println(result.toString());
		return result.toString();
	}

	@Override
	public String saveNode(String json) {
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);

			Long defId = jsonObject.getLong("defId");// 流程id
			Long conditionId = jsonObject.getLong("conditionId");// 人员设置条件的id
			String actDefId = jsonObject.getString("actDefId");//
			String nodeId = jsonObject.getString("nodeId");
			String condition = jsonObject.get("condition") != null ? jsonObject.getString("condition") : "";
			String users = jsonObject.get("users") != null ? jsonObject.getString("users") : "";
			String conditionShow = jsonObject.get("conditionShow") != null ? jsonObject.getString("conditionShow") : "";// 在列表中的展示内容

			int conditionType = jsonObject.getInt("conditionType");
			Long sn = jsonObject.getLong("sn");
			String formIdentity = jsonObject.getString("formIdentity");
			String parentActDefId = jsonObject.get("parentActDefId") != null ? jsonObject.getString("parentActDefId") : "";

			BpmUserCondition bpmUserCondition = null;

			bpmUserCondition = bpmUserConditionService.getById(conditionId);
			if (bpmUserCondition == null) {
				bpmUserCondition = new BpmUserCondition();
			}
			bpmUserCondition.setNodeid(nodeId);
			bpmUserCondition.setActdefid(actDefId);
			bpmUserCondition.setSn(sn);
			bpmUserCondition.setCondition(condition);
			bpmUserCondition.setFormIdentity(formIdentity);

			// 如果节点不为空,获取setId设置到用户条件当中。
			if (StringUtil.isNotEmpty(nodeId)) {
				BpmNodeSet bpmNodeSet = null;
				if (StringUtil.isEmpty(parentActDefId)) {
					bpmNodeSet = bpmNodeSetService.getByDefIdNodeId(defId, nodeId);
				} else {
					bpmNodeSet = bpmNodeSetService.getByDefIdNodeId(defId, nodeId, parentActDefId);
				}
				if (BeanUtils.isNotEmpty(bpmNodeSet)) {
					bpmUserCondition.setSetId(bpmNodeSet.getSetId());
				}
			}

			bpmUserCondition.setConditionShow(conditionShow);
			bpmUserCondition.setConditionType(conditionType);
			bpmUserCondition.setParentActDefId(parentActDefId);
			bpmUserConditionService.saveConditionAndUser(bpmUserCondition, users);

			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		// System.out.println(result.toString());
		return result.toString();
	}

	@Override
	public String getAlreadyMattersList(String userId, String json) {
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json != null ? json : "{}");
			QueryFilter filter = new QueryFilter(jsonObject, "processRunItem", false, PageBean.DEFAULT_PAGE_SIZE);

			if (StringUtil.isEmpty(userId)) {// 没传用户就报错
				throw new Exception("请输入需要查找的用户id");
			}
			filter.addFilter("assignee", userId);// 用户id

			if (jsonObject.get("nodePath") != null) {
				filter.getFilters().put("nodePath", jsonObject.getString("nodePath") + "%");
			}

			List<ProcessRun> list = processRunService.getAlreadyMattersList(filter);

			for (ProcessRun processRun : list) {
				if (processRun.getStatus().shortValue() != ProcessRun.STATUS_FINISH.shortValue()) {
					// 1.查找当前用户是否有最新审批的任务
					TaskOpinion taskOpinion = taskOpinionService.getLatestUserOpinion(processRun.getActInstId(), new Long(userId));
					if (BeanUtils.isNotEmpty(taskOpinion))
						processRun.setRecover(ProcessRun.STATUS_RECOVER);
				}
			}

			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", list);
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}
		return result.toString();
	}

	@Override
	public String getPendingMattersList(String userId, String json) {

		JSONObject result = new JSONObject();
		try {
			if (StringUtil.isEmpty(userId)) {// 没传用户就报错
				throw new Exception("请输入需要查找的用户id");
			}

			JSONObject jsonObject = JSONObject.fromObject(json != null ? json : "{}");

			QueryFilter filter = new QueryFilter(jsonObject, "taskItem", false, PageBean.DEFAULT_PAGE_SIZE);
			if (jsonObject.get("nodePath") != null) {
				filter.getFilters().put("nodePath", jsonObject.getString("nodePath") + "%");
			}

			ContextUtil.setCurrentUserAccount(userId);
			List<TaskEntity> list = bpmService.getMyTasks(filter);

			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", list);
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}
		return result.toString();
	}

	@Override
	public String getCompletedMattersList(String userId, String json) {
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json != null ? json : "{}");
			QueryFilter filter = new QueryFilter(jsonObject, "processRunItem", false, PageBean.DEFAULT_PAGE_SIZE);

			if (jsonObject.get("nodePath") != null) {
				filter.getFilters().put("nodePath", jsonObject.getString("nodePath") + "%");
			}

			if (StringUtil.isEmpty(userId)) {// 没传用户就报错
				throw new Exception("请输入需要查找的用户id");
			}
			filter.addFilter("assignee", userId);// 用户id

			List<ProcessRun> list = processRunService.getCompletedMattersList(filter);
			for (ProcessRun processRun : list) {
				BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(processRun.getActDefId());
				if (BeanUtils.isNotEmpty(bpmDefinition) && bpmDefinition.getIsPrintForm() == 1) {
					processRun.setIsPrintForm(bpmDefinition.getIsPrintForm());
				}
			}

			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", list);
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}
		return result.toString();
	}

	@Override
	public String getAccordingMattersList(String userId, String json) {
		JSONObject result = new JSONObject();
		JSONObject jsonObject = JSONObject.fromObject(json != null ? json : "{}");
		try {

			QueryFilter filter = new QueryFilter(jsonObject, "taskItem", false, PageBean.DEFAULT_PAGE_SIZE);

			if (StringUtil.isEmpty(userId)) {// 没传用户就报错
				throw new Exception("请输入需要查找的用户id");
			}
			filter.addFilter("ownerId", userId);// 用户id

			if (jsonObject.get("nodePath") != null) {
				filter.getFilters().put("nodePath", jsonObject.getString("nodePath") + "%");
			}

			List<BpmTaskExe> list = bpmTaskExeService.accordingMattersList(filter);

			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", list);
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}
		return result.toString();
	}

	@Override
	public String getProCopyList(String userId, String json) {
		JSONObject result = new JSONObject();
		JSONObject jsonObject = JSONObject.fromObject(json != null ? json : "{}");
		try {

			QueryFilter filter = new QueryFilter(jsonObject, "bpmProCopytoItem", false, PageBean.DEFAULT_PAGE_SIZE);

			if (StringUtil.isEmpty(userId)) {// 没传用户就报错
				throw new Exception("请输入需要查找的用户id");
			}
			filter.addFilter("ccUid", userId);// 用户id

			if (jsonObject.get("nodePath") != null) {
				filter.getFilters().put("nodePath", jsonObject.getString("nodePath") + "%");
			}

			List<BpmProCopyto> list = bpmProCopytoService.getMyList(filter);

			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", list);

		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}
		return result.toString();
	}

	@Override
	public String getProTransMattersList(String userId, String json) {
		JSONObject result = new JSONObject();
		JSONObject jsonObject = JSONObject.fromObject(json != null ? json : "{}");
		try {

			QueryFilter filter = new QueryFilter(jsonObject, "bpmProTransToItem", false, PageBean.DEFAULT_PAGE_SIZE);

			if (jsonObject.get("nodePath") != null) {
				filter.getFilters().put("nodePath", jsonObject.getString("nodePath") + "%");
			}

			filter.addFilter("exceptDefStatus", 3);

			if (StringUtil.isEmpty(userId)) {// 没传用户就报错
				throw new Exception("请输入需要查找的用户id");
			}
			filter.addFilter("createUserId", userId);// 用户id

			List<BpmProTransTo> list = bpmProTransToService.mattersList(filter);

			result.put("state", "0");
			result.put("msg", "操作成功");
			result.put("list", list);
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}
		return result.toString();
	}

	@Override
	public String setAgent(String json) {
		JSONObject result = new JSONObject();
		try {
			AgentSetting agentSetting = AgentSettingUtil.createAgenSettingByJson(json);
			if (agentSetting.getId() == null || agentSetting.getId() == 0) {
				agentSetting.setId(UniqueIdUtil.genId());
				agentSettingService.addAll(agentSetting);
			} else {
				agentSettingService.updateAll(agentSetting);
			}
			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	@Override
	public String getBpmDefinitionByDefId(String defId) {
		JSONObject result = new JSONObject();
		try {
			BpmDefinition bpmDefinition = bpmDefinitionService.getById(new Long(defId));
			result.put("bpmDefinition", bpmDefinition);
			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	@Override
	public String setDefOtherParam(String defId,String json) {
		JSONObject result = new JSONObject();
		try {
			JSONObject jsonObject = JSONObject.fromObject(json);

			BpmDefinition bpmDefinition = bpmDefinitionService.getById(Long.valueOf(defId));

			// 表单变量
			Object obj = jsonObject.get("taskNameRule");
			if (obj != null) {
				bpmDefinition.setTaskNameRule(obj.toString());
			}
			// 跳过第一个任务
			obj = jsonObject.get("toFirstNode");
			if (obj != null) {
				bpmDefinition.setToFirstNode(Short.valueOf(obj.toString()));
			}
			// 流程启动选择执行人
			obj = jsonObject.get("showFirstAssignee");
			if (obj != null) {
				bpmDefinition.setShowFirstAssignee(Short.valueOf(obj.toString()));
			}
			// 表单明细Url
			obj = jsonObject.get("formDetailUrl");
			if (obj != null) {
				bpmDefinition.setFormDetailUrl(obj.toString());
			}
			// 提交是否需要确认
			obj = jsonObject.get("submitConfirm");
			if (obj != null) {
				bpmDefinition.setSubmitConfirm(Integer.valueOf(obj.toString()));
			}
			// 是否允许转办
			obj = jsonObject.get("allowDivert");
			if (obj != null) {
				bpmDefinition.setAllowDivert(Integer.valueOf(obj.toString()));
			}
			// 是否允许我的办结转发
			obj = jsonObject.get("allowFinishedDivert");
			if (obj != null) {
				bpmDefinition.setAllowFinishedDivert(Integer.valueOf(obj.toString()));
			}
			// 归档时发送消息给发起人
			obj = jsonObject.get("informStart");
			if (obj != null) {
				bpmDefinition.setInformStart(obj.toString());
			}
			// 通知类型
			obj = jsonObject.get("informType");
			if (obj != null) {
				bpmDefinition.setInformType(obj.toString());
			}
			// 是否允许办结抄送
			obj = jsonObject.get("allowFinishedCc");
			if (obj != null) {
				bpmDefinition.setAllowFinishedCc(Integer.valueOf(obj.toString()));
			}
			// 流程实例归档后是否允许打印表单
			obj = jsonObject.get("isPrintForm");
			if (obj != null) {
				bpmDefinition.setIsPrintForm(Short.valueOf(obj.toString()));
			}
			// 流程帮助 的附件
			obj = jsonObject.get("attachment");
			if (obj != null) {
				bpmDefinition.setAttachment(Long.valueOf(obj.toString()));
			}
			// 流程状态
			obj = jsonObject.get("status");
			if (obj != null) {
				bpmDefinition.setStatus(Short.valueOf(obj.toString()));
			}
			// 相邻任务节点人员相同是自动跳过
			obj = jsonObject.get("sameExecutorJump");
			if (obj != null) {
				bpmDefinition.setSameExecutorJump(Short.valueOf(obj.toString()));
			}
			// 允许API调用
			obj = jsonObject.get("isUseOutForm");
			if (obj != null) {
				bpmDefinition.setIsUseOutForm(Short.valueOf(obj.toString()));
			}
			// 流程参考
			obj = jsonObject.get("allowRefer");
			if (obj != null) {
				bpmDefinition.setAllowRefer(Integer.valueOf(obj.toString()));
			}
			// 参考条数
			obj = jsonObject.get("instanceAmount");
			if (obj != null) {
				bpmDefinition.setInstanceAmount(Integer.valueOf(obj.toString()));
			}
			// 直接启动流程。
			obj = jsonObject.get("directstart");
			if (obj != null) {
				bpmDefinition.setDirectstart(Integer.valueOf(obj.toString()));
			}
			// 抄送消息类型
			obj = jsonObject.get("ccMessageType");
			if (obj != null) {
				bpmDefinition.setCcMessageType((String) obj);
			}

			// 测试标签
			obj = jsonObject.get("testStatusTag");
			if (obj != null) {
				bpmDefinition.setTestStatusTag((String) obj);
			}

			// 是否手机审批
			obj = jsonObject.get("allowMobile");
			if (obj != null) {
				bpmDefinition.setAllowMobile(Integer.valueOf(obj.toString()));
			}

			int count = bpmDefinitionService.saveParam(bpmDefinition);
			if (count <= 0) {
				throw new Exception("参数设置失败");
			}

			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	@Override
	public String taskAssign(String json) {
		JSONObject result = new JSONObject();

		try {

			JSONObject jsonObject = JSONObject.fromObject(json);

			SysUser sysUser = sysUserService.getById(jsonObject.getLong("userId"));
			if (sysUser == null) {
				throw new Exception("用户不存在");
			}
			ContextUtil.setCurrentUser(sysUser);

			String taskId = jsonObject.getString("taskId");
			Long assigneeId = jsonObject.getLong("assigneeId");
			String assigneeName = jsonObject.getString("assigneeName");
			String memo = jsonObject.getString("memo");
			String informType = jsonObject.getString("informType");

			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (BeanUtils.isEmpty(taskEntity)) {
				throw new Exception("任务已经被处理");
			}
			String assignee = taskEntity.getAssignee();
			// 任务人不为空且和当前用户不同。
			if (ServiceUtil.isAssigneeNotEmpty(assignee) && assignee.equals(assigneeId)) {
				throw new Exception("不能转办给任务执行人");
			}
			if (ServiceUtil.isAssigneeNotEmpty(assignee)) {
				if (!assignee.equals(sysUser.getUserId().toString())) {
					throw new Exception("对不起，转办失败。您（已）不是任务的执行人");
				}
			}

			ProcessRun processRun = processRunService.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			String actDefId = processRun.getActDefId();
			boolean rtn = bpmDefinitionService.allowDivert(actDefId);
			if (!rtn) {
				throw new Exception("任务不允许进行转办");
			}

			BpmTaskExe bpmTaskExe = new BpmTaskExe();
			bpmTaskExe.setId(UniqueIdUtil.genId());
			bpmTaskExe.setTaskId(new Long(taskId));
			bpmTaskExe.setAssigneeId(assigneeId);
			bpmTaskExe.setAssigneeName(assigneeName);
			bpmTaskExe.setOwnerId(sysUser.getUserId());
			bpmTaskExe.setOwnerName(sysUser.getFullname());
			bpmTaskExe.setSubject(processRun.getSubject());
			bpmTaskExe.setStatus(BpmTaskExe.STATUS_INIT);
			bpmTaskExe.setMemo(memo);
			bpmTaskExe.setCratetime(new Date());
			bpmTaskExe.setActInstId(new Long(taskEntity.getProcessInstanceId()));
			bpmTaskExe.setTaskDefKey(taskEntity.getTaskDefinitionKey());
			bpmTaskExe.setTaskName(taskEntity.getName());
			bpmTaskExe.setAssignType(BpmTaskExe.TYPE_TRANSMIT);
			bpmTaskExe.setRunId(processRun.getRunId());
			bpmTaskExe.setTypeId(processRun.getTypeId());
			bpmTaskExe.setInformType(informType);
			
			bpmTaskExeService.assignSave(bpmTaskExe);

			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	@Override
	public String taskCountersign(String userId, String json) {
		JSONObject result = new JSONObject();

		try {
			SysUser sysUser = sysUserService.getById(Long.valueOf(userId));
			if (sysUser == null) {
				throw new Exception("找不到指定用户");
			}
			ContextUtil.setCurrentUser(sysUser);
			JSONObject jsonObject = JSONObject.fromObject(json);
			String cmpIds = jsonObject.getString("cmpIds");
			String taskId = jsonObject.getString("taskId");
			String opinion = jsonObject.getString("opinion");
			String informType = jsonObject.getString("informType");
			String transType = jsonObject.getString("transType");
			String action = jsonObject.getString("action");
			// 保存意见
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			processRunService.saveTransTo(taskEntity, opinion, informType, cmpIds, transType, action, processRun);
			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}
	
	@Override
	public String defRecover(String userId,String json) {
		JSONObject result = new JSONObject();
		try {
			SysUser sysUser =sysUserService.getById(Long.valueOf(userId));
			if(sysUser==null){
				throw new Exception("找不到指定用户");
			}
			ContextUtil.setCurrentUser(sysUser);
			
			JSONObject jsonObject = JSONObject.fromObject(json);
			
			Long runId = jsonObject.getLong("runId");
			String informType = jsonObject.getString("informType");
			String memo = jsonObject.getString("opinion");
			int backToStart = jsonObject.getInt("backToStart");

			ResultMessage resultMessage = null;

			// 先检查是否可以撤销
			resultMessage = DefRevocationUtil.checkRecover(runId);
			if (resultMessage.getResult() == ResultMessage.Fail) {
				throw new Exception(resultMessage.getMessage());
			}

			if (backToStart == 1) {
				// 撤销
				resultMessage = processRunService.recover(runId, informType, memo);
			} else {
				// 追回
				resultMessage = processRunService.redo(runId, informType, memo);
			}

			if (resultMessage.getResult() == ResultMessage.Fail) {
				throw new Exception(resultMessage.getMessage());
			}

			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	@Override
	public String taskEndProcess(String userId, String json) {
		JSONObject result = new JSONObject();
		try {
			SysUser sysUser = sysUserService.getById(Long.valueOf(userId));
			if (sysUser == null) {
				throw new Exception("用户id不存在");
			}
			ContextUtil.setCurrentUser(sysUser);

			JSONObject jsonObject = JSONObject.fromObject(json);
			Long taskId = jsonObject.getLong("taskId");
			TaskEntity taskEnt = bpmService.getTask(taskId.toString());
			if (taskEnt == null) {
				throw new Exception("任务已不存在");
			}
			String memo = jsonObject.getString("memo");

			String instanceId = taskEnt.getProcessInstanceId();
			String nodeId = taskEnt.getTaskDefinitionKey();
			ProcessRun processRun = bpmService.endProcessByInstanceId(new Long(instanceId), nodeId, memo);
			memo = "结束流程:" + processRun.getSubject() + ",结束原因:" + memo;
			bpmRunLogService.addRunLog(processRun.getRunId(), BpmRunLog.OPERATOR_TYPE_ENDTASK, memo);

			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	@Override
	public String getFreeJump(String taskId) {
		JSONObject result = new JSONObject();
		try {
			Map<String, Map<String, String>> jumpNodesMap = bpmService.getJumpNodes(taskId);
			result.put("jumpNodesMap", jumpNodesMap);
			result.put("state", "0");
			result.put("msg", "操作成功");
		} catch (Exception e) {
			result.put("state", "-1");
			result.put("msg", "操作失败：" + e.getMessage());
		}

		return result.toString();
	}

	@Override
	public String assignUsers(String json) {
		JsonObject result = new JsonObject();
		try {
			JsonElement jsonElement = GsonUtil.parse(json);
			if(!jsonElement.isJsonArray()||jsonElement.isJsonNull()){
				throw new Exception("请输入json格式：[{defKey :\"defkey\",nodeId:\"node1\",userId:\"id1,id2\",startTime:\"yyyy-MM-dd HH:mm:ss\",endTime:\"yyyy-MM-dd HH:mm:ss\"}]");
			}
			JsonArray jsonArray = jsonElement.getAsJsonArray();
			Map<String, String> nodeMap = new HashMap<String, String>();
			List<AssignUsers> assignUserslList = new ArrayList<AssignUsers>();
			for (JsonElement jsonElement2 : jsonArray) {
				JsonObject asJsonObject = jsonElement2.getAsJsonObject();
				//获取一个json对象里面个各个数据
				JsonElement defKeyJson = asJsonObject.get("defKey");
				JsonElement nodeIdJson = asJsonObject.get("nodeId");
				JsonElement userIdJson = asJsonObject.get("userId");
				JsonElement startTimeJson = asJsonObject.get("startTime");
				JsonElement endTimeJson = asJsonObject.get("endTime");
				//验证输入json数据不能为空
				if (!defKeyJson.isJsonPrimitive()) {
					throw new Exception("defKey不能为空");
				}else if (!nodeIdJson.isJsonPrimitive()) {
					throw new Exception("nodeId不能为空");
				}else if (!userIdJson.isJsonPrimitive()) {
					throw new Exception("userId不能为空");
				}else if (!startTimeJson.isJsonPrimitive()) {
					throw new Exception("startTime不能为空");
				}else if (!endTimeJson.isJsonPrimitive()) {
					throw new Exception("endTime不能为空");
				}
				
				//把数据转换类型
				String defKey = defKeyJson.getAsString();
				String nodeId = nodeIdJson.getAsString();
				String userId = userIdJson.getAsString();
				Date startTime = new Date();
				Date endTime = new Date();
				try {
					startTime = GsonUtil.toBean(startTimeJson.toString(), Date.class);
					endTime = GsonUtil.toBean(endTimeJson.toString(), Date.class);
				} catch (Exception e) {
					throw new Exception("请检查startTime或者endTime的格式是否为：yyyy-MM-dd HH:mm:00形式");
				}
				
				//判断输入的各个用户Id都存在着对象
				String[] userIdAry = userId.split(",");
				Map<Long, String>  userMap = new HashMap<Long, String>();
				for (int i =0; i<userIdAry.length;i++) {
					SysUser sysuser = sysUserService.getById(new Long(userIdAry[i]));
					if (BeanUtils.isEmpty(sysuser)) {
						throw new Exception("userId："+userIdAry[i]+",不存在对应用户！");
					}
					userMap.put(sysuser.getUserId(), sysuser.getFullname());
				}
				
				//根据defKey获取对应的流程是否存心
				BpmDefinition mainByDefKey = bpmDefinitionService.getMainByDefKey(defKey);
				if (BeanUtils.isEmpty(mainByDefKey)) {
					throw new Exception("defKey："+defKey+",不存在对应的流程！");
				}
				//根据流程获取对应的节点，保存一份
				if (BeanUtils.isEmpty(nodeMap)) {
					List<BpmNodeSet> bpmNodeSetList = bpmNodeSetService.getByDefId(mainByDefKey.getDefId());
					for (BpmNodeSet bpmNodeset : bpmNodeSetList) {
						nodeMap.put(bpmNodeset.getNodeId(),bpmNodeset.getNodeName());
					}
				}
				//判断输入的nodeId是否是存在流程中的节点
				Boolean isExist = nodeMap.containsKey(nodeId);
				if (!isExist) {
					throw new Exception("defKey："+defKey+"的流程不存在nodeId为："+nodeId+"!");
				}
				
				
				for (Long userid : userMap.keySet()) {
					AssignUsers assignUsers = new AssignUsers();
					assignUsers.setDefKey(defKey);
					assignUsers.setNodeId(nodeId);
					assignUsers.setNodeName(nodeMap.get(nodeId));
					assignUsers.setUserId(userid);
					assignUsers.setUserName(userMap.get(userid));
					assignUsers.setStartTime(startTime);
					assignUsers.setEndTime(endTime);
					assignUserslList.add(assignUsers);
				}
				
				
			}
			Long runId = assignUsersService.addAssignUser(assignUserslList);
			result.addProperty("result", "true");
			result.addProperty("runId", runId);
		} catch (Exception e) {
			e.printStackTrace();
			result.addProperty("result", "false");
			result.addProperty("message", ExceptionUtils.getRootCauseMessage(e));
		}
		return result.toString();
	}
	
	

	@Override
	public String getNodesByDefKey(String defKey) {
		BpmDefinition mainByDefKey = bpmDefinitionService.getMainByDefKey(defKey);
		Map<String, FlowNode> nodeMap = NodeCache.getByActDefId(mainByDefKey.getActDefId());
		return this.getNodElement("",nodeMap).toString();
	}
	
	private  JsonElement getNodElement(String supNodeId,Map<String, FlowNode> nodeMap) {
		JsonElement jsonArray = new JsonArray();
		for (String nodeId : nodeMap.keySet()) {
			FlowNode flowNode = nodeMap.get(nodeId);
			if (flowNode.getIsSubProcess()||flowNode.getIsCallActivity()) {
				Map<String, FlowNode> subNodeMap=flowNode.getSubProcessNodes();
				jsonArray.getAsJsonArray().add(this.getNodElement(nodeId,subNodeMap));
			}else {
				String supNodeIdTemp = StringUtil.isNotEmpty(supNodeId)?supNodeId:nodeId; 
				String nodeName = flowNode.getNodeName();
				String nodeType = flowNode.getNodeType();
				String preNode = this.getPreOrNextNaodeId(flowNode.getPreFlowNodes());
				String nextNode = this.getPreOrNextNaodeId(flowNode.getNextFlowNodes());
				JsonElement jsonObj = new JsonObject();
				jsonObj.getAsJsonObject().addProperty("nodeId", nodeId);
				jsonObj.getAsJsonObject().addProperty("nodeName", nodeName);
				jsonObj.getAsJsonObject().addProperty("nodeType", nodeType);
				jsonObj.getAsJsonObject().addProperty("preNode", StringUtil.isNotEmpty(preNode)?preNode:supNodeIdTemp);
				jsonObj.getAsJsonObject().addProperty("nextNode",StringUtil.isNotEmpty(nextNode)?nextNode:supNodeIdTemp);
				jsonArray.getAsJsonArray().add(jsonObj);
			}
		}
		return jsonArray;
	}

	private String getPreOrNextNaodeId(List<FlowNode> flowNodes) {
		if (BeanUtils.isEmpty(flowNodes)) return "";
		List<String> nodeId = new ArrayList<String>();
		for (FlowNode flowNode : flowNodes) {
			nodeId.add(flowNode.getNodeId());
		}
		return "["+StringUtil.getArrayAsString(nodeId)+"]";
	}
	
	
}
