package com.hotent.mobile.controller.bpm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.mobile.controller.base.BaseMobileController;
import com.hotent.mobile.model.bpm.BpmMobileGangedSet;
import com.hotent.mobile.model.form.BpmMobileForm;
import com.hotent.mobile.service.form.BpmMobileFormHandlerService;
import com.hotent.mobile.service.form.BpmMobileFormService;
import com.hotent.platform.dao.bpm.TaskDao;
import com.hotent.platform.model.bpm.AuthorizeRight;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmGangedSet;
import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.model.bpm.BpmProTransTo;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmGangedSetService;
import com.hotent.platform.service.bpm.BpmNodeButtonService;
import com.hotent.platform.service.bpm.BpmNodeSignService;
import com.hotent.platform.service.bpm.BpmNodeSignService.BpmNodePrivilegeType;
import com.hotent.platform.service.bpm.BpmProTransToService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.BpmTaskExeService;
import com.hotent.platform.service.bpm.BpmTaskUtil;
import com.hotent.platform.service.bpm.CommuReceiverService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskMessageService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.impl.BpmActService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.TaskReadService;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.platform.service.worktime.CalendarAssignService;

/**
 * <pre>
 * 对象功能:手机任务 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-04-02 15:09:58
 * </pre>
 */
@Controller
@RequestMapping("/mobile/bpm/bpmMobileTask/")
public class BpmMobileTaskController extends BaseMobileController {

	@Resource
	private BpmDefinitionService bpmDefinitionService;

	@Resource
	private BpmMobileFormHandlerService bpmMobileFormHandlerService;

	@Resource
	private BpmMobileFormService bpmMobileFormService;

	@Resource
	private BpmService bpmService;

	@Resource
	private ProcessRunService processRunService;
	@Resource
	private TaskReadService taskReadService;
	@Resource
	private CommuReceiverService commuReceiverService;
	@Resource
	private BpmTaskExeService bpmTaskExeService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private TaskDao taskDao;
	@Resource
	private BpmRunLogService bpmRunLogService;
	@Resource
	private SysTemplateService sysTemplateService;
	@Resource
	private BpmNodeButtonService bpmNodeButtonService;
	@Resource
	private TaskService taskService;
	@Resource
	private BpmNodeSignService bpmNodeSignService;
	@Resource
	private BpmGangedSetService bpmGangedSetService;
	@Resource
	private CalendarAssignService calendarAssignService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmProTransToService bpmProTransToService;
	@Resource
	private TaskMessageService taskMessageService;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	@Resource
	BpmActService bpmActService;
	
	/**
	 * 
	 * 获取启动流程的表单。<br/>
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("startFlowForm")
	@Action(description = "获取启动流程的表单数据")
	public void startFlowForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");

		String json = "";
		try {
			BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
			if (BeanUtils.isEmpty(bpmDefinition)) {
				this.returnCallbackErrorData(request, response, "该流程定义不存在");
				return;
			}

			if (BeanUtils.isNotEmpty(bpmDefinition.getStatus())
					&& bpmDefinition.getStatus().shortValue() == BpmDefinition.STATUS_DISABLED
							.shortValue()) {
				this.returnCallbackErrorData(request, response, "该流程定义被禁用");
				return;
			}
			// 获取表单数据
			Map<String, Object> map = bpmMobileFormHandlerService
					.getStartFlowForm(bpmDefinition.getActDefId(),
							bpmDefinition.getToFirstNode());
			json = this.getSuccess(map);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			json = this.getError(msg);
		}
		returnCallbackData(request, response, json);
	}

	/**
	 * 获取流程任务的表单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Action(description = "获取流程任务的表单")
	@RequestMapping("getStartFlowData")
	public void getStartFlowData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		String businessKey = RequestUtil.getString(request, "businessKey", "");
		String copyKey = RequestUtil.getString(request, "copyKey", "");
		Boolean isCopyFlow = false;
		Long userId = ContextUtil.getCurrentUserId();
		try {
			if (StringUtil.isNotEmpty(copyKey)) {
				businessKey = copyKey;
				isCopyFlow = true;
			}

			BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);

			Map<String,Object> map = bpmMobileFormHandlerService.getStartFlowData(
					bpmDefinition.getActDefId(),
					bpmDefinition.getDefId(), businessKey, isCopyFlow,userId);
			//通过defid获取起始节点的联动设置
			List<BpmGangedSet> bpmGangedSets = bpmGangedSetService.getStartNodeByDefId(defId);
			String  gangedSet = getBpmGangedSets(bpmGangedSets,"1");
			map.put("gangedSet", gangedSet);
			this.returnCallbackSuccessData(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			this.returnCallbackErrorData(request, response, msg);
		}

	}

	/**
	 * 获取联动设置
	 * @param bpmGangedSets
	 * @param nodeId 1，开始表单，2，全局表单
	 * @return
	 */
	private String getBpmGangedSets(List<BpmGangedSet> bpmGangedSets,String nodeId) {
		Map<String, List<BpmMobileGangedSet>> map = new HashMap<String, List<BpmMobileGangedSet>>();
		for (BpmGangedSet bpmGangedSet : bpmGangedSets) {
			String choisefield = bpmGangedSet.getChoisefield();
			String changefield = bpmGangedSet.getChangefield();
			String noid = bpmGangedSet.getNodeid();//当前节点
			if(!"2".equals(noid) && !nodeId.equals(noid) )
				continue;
			JSONArray choisefieldAry =  JSONArray.fromObject(choisefield);
			for (Object o : choisefieldAry) {
				JSONObject json =  JSONObject.fromObject(o);
				String key  = (String) json.get("key");
				String value  = (String) json.get("value");
				List<BpmMobileGangedSet> list = map.get(key);
				BpmMobileGangedSet gangedSet = new BpmMobileGangedSet();
				gangedSet.setValue(value);
				gangedSet.setChangefield(changefield);
				if(BeanUtils.isEmpty(list)){
					list = new ArrayList<BpmMobileGangedSet>();
					list.add(gangedSet);
				}else{
					list.add(gangedSet);
				}
				map.put(key, list);
			}
		}
		
		JSONArray jsonAry = new JSONArray();
		for (Map.Entry<String, List<BpmMobileGangedSet>> entry :map.entrySet()){
			String key = entry.getKey();
			String [] keyAry =  key.split(":");
			List<BpmMobileGangedSet> value = entry.getValue();
			JSONObject json = new JSONObject();
			json.accumulate("key", ("m".equals(keyAry[0])?"main.":"sub.item") +keyAry[2]);
			json.accumulate("name", keyAry[0]+"_"+keyAry[1]+"_"+keyAry[2]);
			json.accumulate("value", value);
			jsonAry.add(json);
		}
		return jsonAry.toString();
	}

	/**
	 * 获取流程任务的表单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Action(description = "获取流程任务的表单")
	@RequestMapping("getTaskForm")
	public void getTaskForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String taskId = RequestUtil.getString(request, "taskId");
		String json = "";
		try {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			Map<String, Object> map = bpmMobileFormHandlerService.getTaskForm(
					taskEntity.getProcessInstanceId(), taskEntity.getTaskDefinitionKey());
			json = this.getSuccess(map);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			json = this.getError(msg);
		}
		returnCallbackData(request, response, json);
	}

	/**
	 * 获取流程的表单数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@Action(description = "获取流程的表单")
	@RequestMapping("getProcessForm")
	public void getProcessForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long runId = RequestUtil.getLong(request, "runId");
		String json = "";
		try {
			Map<String, Object> map = bpmMobileFormHandlerService
					.getProcessForm(runId);
			json = this.getSuccess(map);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			json = this.getError(msg);
		}
		returnCallbackData(request, response, json);
	}

	/**
	 * 获取流程实例的数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getTaskFormData")
	@Action(description = "获取流程实例的数据")
	@ResponseBody
	public void getTaskFormData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser sysUser = ContextUtil.getCurrentUser();

		String taskId = RequestUtil.getString(request, "taskId");
		String instanceId = RequestUtil.getString(request, "instanceId");
		try {
			if (StringUtil.isEmpty(taskId) && StringUtil.isEmpty(instanceId)) {
				this.returnCallbackErrorData(request, response, "没有输入任务或实例ID!");
				return;
			}

			// 根据流程实例获取流程任务。
			if (StringUtil.isNotEmpty(instanceId)) {
				List<ProcessTask> list = bpmService.getTasks(instanceId);
				if (BeanUtils.isNotEmpty(list)) {
					taskId = list.get(0).getId();
				}
			}
			if (StringUtil.isEmpty(taskId)) {
				this.returnCallbackErrorData(request, response, "没有输入任务ID!");
				return;
			}
			// 查找任务节点
			TaskEntity taskEntity = bpmService.getTask(taskId);

			if (taskEntity == null) {
				// return ServiceUtil.getTipInfo("对不起,这个任务已经被处理了!");
					this.returnCallbackErrorData(request, response,
							"对不起,这个任务已经被处理了!");
					return;
			}
			instanceId = taskEntity.getProcessInstanceId();

			// 这个放在service处理

			// 更新任务为已读。
			taskReadService.saveReadRecord(Long.parseLong(instanceId),
					Long.parseLong(taskId));
			// 设置沟通人员查看状态。
			commuReceiverService.setCommuReceiverStatus(taskEntity, sysUser);

			String nodeId = taskEntity.getTaskDefinitionKey();
			Long userId = sysUser.getUserId();

			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(instanceId));	
			
			String actDefId = taskEntity.getProcessDefinitionId();
			String toBackNodeId = "";
			if(StringUtil.isNotEmpty(processRun.getStartNode())){
				toBackNodeId = processRun.getStartNode();
			}
			else{
				toBackNodeId =NodeCache.getFirstNodeId(actDefId).getNodeId();
			}
			BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
			
			Map<String, Object> map = bpmMobileFormHandlerService.getFormData(processRun,
					nodeId, userId);
			this.getBpmNodeButtons(map,taskEntity,bpmDefinition,instanceId,nodeId,actDefId,userId);
			map.put("actDefId", actDefId);
			map.put("defId", bpmDefinition.getDefId());
			map.put("toBackNodeId", toBackNodeId);
			this.returnCallbackSuccessData(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			this.returnCallbackErrorData(request, response, msg);
		}

	}
	
	/**
	 * 	获取该任务能进行哪些操作
	 * @param map
	 * @param taskEntity
	 * @param bpmDefinition
	 * @param instanceId
	 * @param nodeId
	 * @param actDefId
	 * @param userId
	 */
	private void getBpmNodeButtons(Map<String, Object> map, 
			TaskEntity taskEntity,
			BpmDefinition bpmDefinition,
			String instanceId,
			String nodeId, String actDefId, Long userId) {
		//获取该任务能进行哪些操作
		List<BpmNodeButton> bpmNodeButtons = bpmNodeButtonService.getByDefNodeId(bpmDefinition.getDefId(), nodeId);

		
		// 是否会签任务
		boolean isSignTask = bpmService.isSignTask(taskEntity);
//		if (isSignTask)
//			handleSignTask(map, instanceId, nodeId, actDefId, userId);
		// 是否支持回退
		boolean isCanBack=bpmActService.isTaskAllowBack(taskEntity.getId());
		// 是否转办
		boolean isCanAssignee = bpmTaskExeService.isAssigneeTask(taskEntity,bpmDefinition);
		if(isCanAssignee)
		isCanAssignee=BpmTaskUtil.isCanAssignee(taskEntity.getDescription());
		
		
		//是否允许转办管理员
	//	boolean isCanAssigneeManage = true;
		List<Integer> buttons = new ArrayList<Integer>();
		//沟通反馈
		if(BeanUtils.isEmpty(taskEntity.getExecutionId()) && TaskOpinion.STATUS_COMMUNICATION.toString().equals(taskEntity.getDescription())){
			buttons.add(BpmNodeButton.NODE_BUTTON_TYPE_FEEDBACK);
		}else{
			if(BeanUtils.isEmpty(bpmNodeButtons)){
				//xx会签的特权
				buttons.add(BpmNodeButton.NODE_BUTTON_TYPE_COMPLETE);
				//xx加签 删除
				if(isCanAssignee)//是否转办
					buttons.add(BpmNodeButton.NODE_BUTTON_TYPE_ASSIGNEE);
				
				if(isCanBack)//会签默认使用反对作为驳回 
					buttons.add(BpmNodeButton.NODE_BUTTON_TYPE_BACKTOSTART);
				buttons.add(BpmNodeButton.NODE_BUTTON_TYPE_COMMU);
			}else{
				for(BpmNodeButton bpmNodeButton : bpmNodeButtons){
					int operatortype =  bpmNodeButton.getOperatortype();
					if(operatortype == BpmNodeButton.NODE_BUTTON_TYPE_IMAGE ||
							operatortype == BpmNodeButton.NODE_BUTTON_TYPE_HIS)
						continue;
					if(operatortype == BpmNodeButton.NODE_BUTTON_TYPE_ABSTENT && !isSignTask)
						continue;
					if(operatortype == BpmNodeButton.NODE_BUTTON_TYPE_BACK && !isCanBack)
						continue;
					if(operatortype == BpmNodeButton.NODE_BUTTON_TYPE_BACKTOSTART && !isCanBack)
						continue;
					if(operatortype == BpmNodeButton.NODE_BUTTON_TYPE_ASSIGNEE && !isCanAssignee)
						continue;	
					buttons.add(bpmNodeButton.getOperatortype());
				}
			}
		}
		String b = "";
		for (Integer button : buttons) {
			b += ",";
			b += button;
		}
		b = b.replaceFirst(",","");
		map.put("buttons", buttons);
	}
	
	
	/**
	 * 处理会签
	 * 
	 * @param map
	 * @param instanceId
	 * @param nodeId
	 * @param actDefId
	 * @param userId
	 *            当前用户
	 */
	@SuppressWarnings("unused")
	private void handleSignTask(Map<String, Object> map, String instanceId,
			String nodeId, String actDefId, Long userId) {

		// 获取会签规则
		BpmNodeSign bpmNodeSign = bpmNodeSignService.getByDefIdAndNodeId(
				actDefId, nodeId);

		map.put("bpmNodeSign", bpmNodeSign);
		map.put("curUser", ContextUtil.getCurrentUser());
		// 获取当前组织
		Long orgId = ContextUtil.getCurrentOrgId();

		// "允许直接处理"特权
		boolean isAllowDirectExecute = bpmNodeSignService
				.checkNodeSignPrivilege(actDefId, nodeId,
						BpmNodePrivilegeType.ALLOW_DIRECT, userId, orgId);
		// "允许补签"特权
		boolean isAllowRetoactive = bpmNodeSignService.checkNodeSignPrivilege(
				actDefId, nodeId, BpmNodePrivilegeType.ALLOW_RETROACTIVE,
				userId, orgId);
		// "一票决断"特权
		boolean isAllowOneVote = bpmNodeSignService.checkNodeSignPrivilege(
				actDefId, nodeId, BpmNodePrivilegeType.ALLOW_ONE_VOTE, userId,
				orgId);
		map.put("isAllowDirectExecute", isAllowDirectExecute);
				map.put("isAllowRetoactive", isAllowRetoactive);
				map.put("isAllowOneVote", isAllowOneVote);

	}


	/**
	 * 获取流程实例的数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getProcessFormData")
	@Action(description = "获取流程实例的数据")
	@ResponseBody
	public void getProcessFormData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Long runId = RequestUtil.getLong(request, "runId");
		try {
			Long userId = ContextUtil.getCurrentUserId();

			ProcessRun processRun = processRunService.getById(runId);
			if (BeanUtils.isEmpty(processRun)) {
				this.returnCallbackErrorData(request, response, "找不到该流程实例");
				return;
			}

			BpmDefinition bpmDefinition = bpmDefinitionService.getById(processRun.getDefId());


			// 不需要外部地址流程进行审批
			// if (ProcessRun.STATUS_RECOVER.equals(processRun.getStatus()) ||
			// ProcessRun.STATUS_REJECT.equals(processRun.getStatus())) {
			// String extSubmitUrl = bpmDefinition.getExtSubmitUrl();

			Map<String,Object> map = bpmMobileFormHandlerService.getFormData(processRun,
					null, userId);
			//按钮
			List<Integer> buttons = new ArrayList<Integer>();
			if(processRun.getStatus().shortValue() == ProcessRun.STATUS_RUNNING.shortValue()){ //不是归档
				if(bpmDefinition.getAllowRevert().shortValue() == BpmDefinition.ALLOW.shortValue())
				buttons.add(BpmNodeButton.NODE_BUTTON_TYPE_REVERT);
				//催办
				buttons.add(42);
			}
			String b = "";
			for (Integer button : buttons) {
				b += ",";
				b += button;
			}
			b = b.replaceFirst(",","");
			map.put("buttons", buttons);
			this.returnCallbackSuccessData(request, response, map);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			this.returnCallbackErrorData(request, response, msg);
		}

	}

	/**
	 * 获取表单模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getFormTemplate")
	@Action(description = "获取表单模板")
	@ResponseBody
	public void getFormTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Long mobileFormId = RequestUtil.getLong(request, "mobileFormId");
		String json = "";
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			BpmMobileForm bpmMobileForm = bpmMobileFormService.getById(mobileFormId);
			if (BeanUtils.isNotEmpty(bpmMobileForm)) {
				String template = bpmMobileForm.getTemplate();
				String guid = bpmMobileForm.getGuid();
				map.put("guid", guid);
				map.put("template", template);
				json = getSuccess(map);
			}else{
				//出错的
				json = getError("未获取模板信息!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			json = getError(msg);
		}
		this.returnCallbackData(request, response, json);
	}

	/**
	 * 查看是否有新的表单模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkFormVersion")
	@Action(description = "查看是否有新的表单模板")
	@ResponseBody
	public void checkFormVersion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long mobileFormId = RequestUtil.getLong(request, "mobileFormId");
		String guid = RequestUtil.getString(request, "guid","");
		int length = RequestUtil.getInt(request, "length",0);
		
		String json = "";
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			BpmMobileForm bpmMobileForm = bpmMobileFormService.getById(mobileFormId);
			String template = "";
			String nguid = "";
			int nlength = 0;
			if (BeanUtils.isNotEmpty(bpmMobileForm)) {
				template = bpmMobileForm.getTemplate();
				nlength = template.length();
					nguid = bpmMobileForm.getGuid();
				map.put("guid", nguid);
				map.put("length", nlength);
				if (guid.equalsIgnoreCase(nguid))
					map.put("template", template);
				else if(nlength != length)
					map.put("template", template);
				json = this.getSuccess(map);
			}else{
				//出错的
				json = this.getError("未获取模板信息");
			}

		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			json = this.getError(msg);
		}
		this.returnCallbackData(request, response, json);
	}

	/**
	 * 审批历史
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("taskOpinions")
	@Action(description = "审批历史")
	@ResponseBody
	public void taskOpinions(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = RequestUtil.getString(request, "taskId");
		String runId = RequestUtil.getString(request, "runId");
		String actInstId = RequestUtil.getString(request, "actInstId");
		TaskEntity taskEntity = null;
		ProcessRun processRun = null;
		List<TaskOpinion> list = null;
		try {
			if (StringUtil.isNotEmpty(taskId)) {
				taskEntity = bpmService.getTask(taskId);
				actInstId = taskEntity.getProcessInstanceId();
				processRun = processRunService.getByActInstanceId(new Long(
						actInstId));
			} else if (StringUtils.isNotEmpty(runId)) {
				processRun = processRunService.getById(new Long(runId));
				actInstId = processRun.getActInstId();
			}

			if (StringUtil.isNotEmpty(actInstId)) {
				list = taskOpinionService.getByActInstId(actInstId, false);
			}

			for (TaskOpinion taskOpinion : list) {
				taskOpinion.setStatus(taskOpinion.getStatus());
			}
			list = taskOpinionService.setTaskOpinionExeFullname(list);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			this.returnCallbackData(request, response, getError(msg));
			return;
		}
		this.returnCallbackPageList(request, response, list);
	}

	/**
	 * 判断是否有任务可以执行
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("hashTask")
	@Action(description = "判断是否有任务可以执行")
	public void hashTask(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long actInstId = RequestUtil.getLong(request, "actInstId");
		String json = "";
		try {

			List<?> list = bpmService.getTaskByUserInstId(
					ContextUtil.getCurrentUserId(), actInstId);
			Map<String, Object> map = new HashMap<String, Object>();
			if (list.size() > 0) {
				ProcessTask processTask = (ProcessTask) list.get(0);
				map.put("isTask", true);
				map.put("task", processTask);
			} else {
				map.put("isTask", false);
			}
			json = getSuccess(map);
		} catch (Exception e) {
			e.printStackTrace();
			String msg=e.getMessage();
			json = getError(msg);
		}
		this.returnCallbackData(request, response, json.toString());
	}
	/**
	 * 判断是否可以手机审批
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("isAllowMobile")
	@Action(description = "判断是否可以手机审批")
	public void isAllowMobile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = RequestUtil.getString(request, "taskId");

		// 查找任务节点
		TaskEntity taskEntity = bpmService.getTask(taskId);

		if (taskEntity == null) {
			// return ServiceUtil.getTipInfo("对不起,这个任务已经被处理了!");
				this.returnCallbackErrorData(request, response,
						"对不起,这个任务已经被处理了!");
				return;
		}
		String actDefId = taskEntity.getProcessDefinitionId();
		Map<String,Object> map = new HashMap<String,Object>();
		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
		if(BeanUtils.isEmpty(bpmDefinition)){
			map.put("msg", "对不起,未获取流程定义");
			map.put("type", 1);
			this.returnCallbackSuccessData(request, response,
					map);
			return;
		}
	
		if( bpmDefinition.getAllowMobile().shortValue() == BpmDefinition.ALLOW.shortValue()){
			map.put("type", 0);
			this.returnCallbackSuccessData(request, response, map);
		}else{
			map.put("msg", "该任务不允许手机审批");
			map.put("type", 1);
			this.returnCallbackSuccessData(request, response,
					map);
		}


	}
	// ============TODO 以下是列表=========

	/**
	 * 待办事宜
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pendingMatters")
	@Action(description = "我的待办")
	@ResponseBody
	public void pendingMatters(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Long userId = RequestUtil.getLong(request, "userId");
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("userId", userId);
		List<?> list = bpmService.getMyTasks(filter);
		this.returnCallbackPageList(request, response, list, filter);
	}
	

	/**
	 * 待办事宜数
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pendingMattersCount")
	@Action(description = "我的待办事宜数")
	@ResponseBody
	public void pendingMattersCount(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Long userId = RequestUtil.getLong(request, "userId");
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("userId", userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", filter.getPageBean().getTotalCount());
		this.returnCallbackSuccessData(request, response, map);
	}

	/**
	 * 我的发起
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myLaunch")
	@Action(description = "我的发起")
	@ResponseBody
	public void myLaunch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		Long userId = RequestUtil.getLong(request, "userId");
		filter.addFilter("currentUserId", userId);
		filter.addFilter("proflag", 0);
		List<ProcessRun> list = processRunService.selectPro(filter);

		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 我的承接
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myUndertake")
	@Action(description = "我的承接")
	@ResponseBody
	public void myUndertake(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		Long userId = RequestUtil.getLong(request, "userId");
		filter.addFilter("currentUserId", userId);
		filter.addFilter("proflag", 1);
		List<ProcessRun> list = processRunService.selectPro(filter);

		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 已办事宜
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("alreadyMatters")
	@Action(description = "已办事宜")
	@ResponseBody
	public void alreadyMatters(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		Long userId = RequestUtil.getLong(request, "userId");
		filter.addFilter("assignee", userId);
		List<ProcessRun> list = processRunService.getAlreadyMattersList(filter);

		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 办结事宜
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("completedMatters")
	@Action(description = "办结事宜")
	@ResponseBody
	public void completedMatters(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("assignee", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService
				.getCompletedMattersList(filter);

		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 转办（代理）事宜
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("taskExe")
	@Action(description = " 转办（代理）事宜")
	@ResponseBody
	public void taskExe(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("ownerId", ContextUtil.getCurrentUserId());
		List<BpmTaskExe> list = bpmTaskExeService.accordingMattersList(filter);

		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 新建流程
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("newProcess")
	@Action(description = "新建流程")
	@ResponseBody
	public void newProcess(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, false);

		Long userId=ContextUtil.getCurrentUserId();
		String isNeedRight = "";
		Map<String,AuthorizeRight> authorizeRightMap = null;
		if(!SysUser.isSuperAdmin()){
			isNeedRight = "yes";
			//获得流程分管授权与用户相关的信息
			Map<String,Object> actRightMap = bpmDefAuthorizeService.getActRightByUserMap(userId, BPMDEFAUTHORIZE_RIGHT_TYPE.START,false,false);
			//获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			filter.addFilter("actRights", actRights);
		}
		filter.addFilter("isNeedRight", isNeedRight);
		
		
		List<BpmDefinition> list = bpmDefinitionService.getMyDefList(filter, 0L);
		Map<Long, List<BpmDefinition>> map = new HashMap<Long, List<BpmDefinition>>();
		for (BpmDefinition bpmDefinition : list) {
			Long typeId = bpmDefinition.getTypeId();
			this.setMap(map, typeId, bpmDefinition);
		}
		JSONArray jAry = new JSONArray();
		for (Iterator<Entry<Long, List<BpmDefinition>>> it = map.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<Long, List<BpmDefinition>> e = it.next();
			Long typeId = e.getKey();
			GlobalType globalType = globalTypeService.getById(typeId);
			List<BpmDefinition> l = e.getValue();
			String typeName = "无分类";
			if (BeanUtils.isNotEmpty(globalType))
				typeName = globalType.getTypeName();
			JSONObject json = new JSONObject();
			json.element("type", typeName);
			json.element("list", JSONArray.fromObject(l).toString());
			jAry.add(json);

		}
		JSONObject json = new JSONObject();
		json.accumulate("results", jAry);
		this.returnCallbackData(request, response, json.toString());
	}

	private void setMap(Map<Long, List<BpmDefinition>> map, Long key,
			BpmDefinition bpmDefinition) {
		if (BeanUtils.isEmpty(key))
			key = -1L;
		List<BpmDefinition> list = map.get(key);
		if (BeanUtils.isEmpty(list)) {
			list = new ArrayList<BpmDefinition>();
			list.add(bpmDefinition);
		} else {
			list.add(bpmDefinition);
		}
		map.put(key, list);
	}

	/**
	 * 我的草稿
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myDraftList")
	@Action(description = "我的草稿")
	@ResponseBody
	public void myDraftList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());// 只能是我的
//		List<BpmMyDraft> list = bpmMyDraftService.getMyDraftList(filter);
//
//		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 我的请求
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myRequest")
	@Action(description = "我的请求")
	@ResponseBody
	public void myRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService.getMyRequestList(filter);
		if (BeanUtils.isEmpty(list))
			return;
		// 处理是否允许驳回
		for (ProcessRun processRun : list) {
			Long instId = Long.parseLong(processRun.getActInstId());
			List<TaskOpinion> taskOpinionList = taskOpinionService
					.getCheckOpinionByInstId(instId);
			String actDefId = processRun.getActDefId();
			FlowNode flowNode = NodeCache.getFirstNodeId(actDefId);
			if (flowNode == null) {
				processRun.setAllowBackToStart(false);
			} else {
				String nodeId = flowNode.getNodeId();
				boolean isFirst = false;
				for (TaskOpinion taskOpinion : taskOpinionList) {
					isFirst = nodeId.equals(taskOpinion.getTaskKey());
					if (isFirst)
						break;
				}
				processRun.setAllowBackToStart(!isFirst);
			}
		}

		this.returnCallbackPageList(request, response, list, filter);
	}

	/**
	 * 我的办结
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myCompleted")
	@Action(description = "我的办结")
	@ResponseBody
	public void myCompleted(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService.getMyCompletedList(filter);
		if (BeanUtils.isEmpty(list))
			return;
		// 处理是否办结转发
		for (ProcessRun processRun : list) {
			BpmDefinition bpmDefinition = bpmDefinitionService
					.getById(processRun.getDefId());
			if (BeanUtils.isNotEmpty(bpmDefinition.getAllowFinishedDivert()))
				processRun.setAllowFinishedDivert(bpmDefinition
						.getAllowFinishedDivert());
		}
		this.returnCallbackPageList(request, response, list, filter);
	}

	// =====TODO 完成任务
	/**
	 * 提交表单（同意、驳回，驳回发起人）
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("complete")
	@Action(description = "提交流程表单")
	@ResponseBody
	public Object complete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("任务完成跳转....");
		String taskId = RequestUtil.getString(request, "taskId");
		Map<String,Object> map = new HashMap<String, Object>();
		TaskEntity task = bpmService.getTask(taskId);
		if (task == null){
			map.put("success", false);
			map.put("message", "任务不存在，可能已被其他人处理了!");
			return map;
		}

		SysUser sysUser = ContextUtil.getCurrentUser();
		if(sysUser==null) {
			sysUser = sysUserService.getByAccount(RequestUtil.getString(request, "curAccount"));
			ContextUtil.setCurrentUser(sysUser);
			ContextUtil.setCurrentUserAccount(sysUser.getAccount());
		}
		Boolean isAdmin = (Boolean) request.getSession()
				.getAttribute("isAdmin");
		String assignee = task.getAssignee();
		// 非管理员,并且没有任务的权限。
		if (isAdmin == null) {
			boolean rtn = taskDao.getHasRightsByTask(new Long(taskId),
					sysUser.getUserId());
			if (!rtn) {
				map.put("success", false);
				map.put("message", "对不起,你不是这个任务的执行人,不能处理此任务!");
				return map;
			}
		}
		ProcessCmd processCmd = BpmUtil.getProcessCmd(request);
		if (ServiceUtil.isAssigneeNotEmpty(assignee)
				&& !task.getAssignee().equals(sysUser.getUserId().toString())
				&& isAdmin == null) {
			map.put("success", false);
			map.put("message", "该任务已被其他人锁定!");
			return map;
		} else {
			try {
				processRunService.nextProcess(processCmd);
			} catch (Exception e) {
				e.printStackTrace();
				String str = MessageUtil.getMessage();
				if (StringUtil.isEmpty(str)) {
					str =e.getMessage();
				}
				map.put("success", false);
				map.put("message", str);
				return map;
			}
		}
		// 任务执行成功
		map.put("success", true);
		map.put("message", "任务处理成功");
		return map;
	}

	/**
	 * 从Request对象构造ProcessCmd对象
	 * 
	 * @param request
	 * @return
	 */
	private ProcessCmd getProcessCmd(HttpServletRequest request) {
		// taskId
		ProcessCmd cmd = new ProcessCmd();
//		String userAgent = request.getHeader("USER-AGENT");

//		cmd.setIsMobile(BpmRunLog.MOBILE);
//		cmd.setUserAgent(userAgent);

		String temp = request.getParameter("taskId");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setTaskId(temp);
		}
		// temp = request.getParameter("agentTask");
		//
		// if ("true".equals(temp)) {
		// cmd.setAgentTask(true);
		// }

		// 添加表单数据
		temp = request.getParameter("formData");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setFormData(temp);
		}

		Map<?, ?> paraMap = RequestUtil.getParameterValueMap(request, false,
				false);
		cmd.setFormDataMap(paraMap);

		BpmDefinition bpmDefinition = null;

		temp = request.getParameter("actDefId");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setActDefId(temp);
			bpmDefinition = bpmDefinitionService.getByActDefId(temp);
		} else {
			temp = request.getParameter("flowKey");
			if (StringUtil.isNotEmpty(temp)) {
				cmd.setFlowKey(temp);
				bpmDefinition = bpmDefinitionService
						.getMainDefByActDefKey(temp);
			}
		}

		if (BeanUtils.isNotEmpty(bpmDefinition)) {
			String informType = "";
			informType = bpmDefinition.getInformType();
			cmd.setInformType(informType);
		}

		temp = request.getParameter("destTask");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setDestTask(temp);
		}
		// 可以根据主键启动流程。
		temp = request.getParameter("businessKey");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setBusinessKey(temp);
		}

		// temp = request.getParameter("businessUrl");
		// if (StringUtil.isNotEmpty(temp)) {
		// cmd.setBusinessUrl(temp);
		// }

		String[] destTaskIds = request.getParameterValues("lastDestTaskId");
		if (destTaskIds == null) {
			destTaskIds = request.getParameterValues("lastDestTaskId[]");
		}
		if (destTaskIds != null) {
			cmd.setLastDestTaskIds(destTaskIds);
			String[] destTaskUserIds = new String[destTaskIds.length];
			for (int i = 0; i < destTaskIds.length; i++) {
				String[] userIds = request.getParameterValues(destTaskIds[i]
						+ "_userId");
				if (userIds == null) {
					userIds = request.getParameterValues(destTaskIds[i]
							+ "_userId[]");
				}
				if (userIds != null) {
					destTaskUserIds[i] = StringUtil.getArrayAsString(userIds);
				}
			}
			cmd.setLastDestTaskUids(destTaskUserIds);
		}

		// 下一节点需要为分发任务时，指定下一任务分发的人员或部门或角色或岗位的ID
		// temp = request.getParameter("forkUserUids");
		// if (StringUtils.isNotEmpty(temp)) {
		// cmd.setForkUserUids(temp);
		// }

		// temp = request.getParameter("signUserIds");
		//
		// if (StringUtil.isNotEmpty(temp)) {
		// cmd.setSignUserIds(temp);
		// }
		// 驳回
		temp = request.getParameter("back");
		if (StringUtil.isNotEmpty(temp)) {
			Integer rtn = Integer.parseInt(temp);
			cmd.setBack(rtn);
		}

		cmd.setVoteContent(request.getParameter("voteContent"));
		// cmd.setAssigneeIds(request.getParameter("assigneeIds"));

		temp = request.getParameter("stackId");
		if (StringUtils.isNotEmpty(temp)) {
			cmd.setStackId(new Long(temp));
		}
		temp = request.getParameter("voteAgree");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setVoteAgree(new Short(temp));
		}

		// 变量处理
		// Enumeration<String> paramEnums = request.getParameterNames();
		// while (paramEnums.hasMoreElements()) {
		// String paramName = paramEnums.nextElement();
		// if (!paramName.startsWith(VAR_PRE_NAME))
		// continue;
		// String[] vnames = paramName.split("[_]");
		// if (vnames == null || vnames.length != 3)
		// continue;
		// String varName = vnames[1];
		// String val = (String) request.getParameter(paramName);
		// if (val.isEmpty())
		// continue;
		//
		// Object valObj = getValue(vnames[2], val);
		// cmd.getVariables().put(varName, valObj);
		// }
		// 若当前节点的后续节点为多实例节点，需要为其后续的多实例节点准备人员数据
		// String[]destNodeIds=request.getParameterValues("lastDestTaskId");
		// if(destNodeIds!=null){
		// for(String destNodeId:destNodeIds){
		// String tmp=request.getParameter("multiInstance_"+destNodeId);
		// if("true".equals(tmp)){
		// cmd.getMultiInstanceNodeIds().add(destNodeId);
		// }
		// }
		// }

		temp = request.getParameter("isManage");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setIsManage(new Short(temp));
		}
		logger.info(ReflectionToStringBuilder.toString(cmd,
				ToStringStyle.MULTI_LINE_STYLE));
		return cmd;
	}

	/**
	 * 发送默认消息
	 * 
	 * @return
	 */
	public String getDefMessage() {
		return BpmConst.MESSAGE_TYPE_SMS + "," + BpmConst.MESSAGE_TYPE_MAIL
				+ "," + BpmConst.MESSAGE_TYPE_INNER;
	}

	/**
	 * 启动流程。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("startFlow")
	@Action(description = "启动流程")
	public void startFlow(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long userId = ContextUtil.getCurrentUserId();
		Long runId=RequestUtil.getLong(request, "runId",0L);
		String json = "";
		try {
			ProcessCmd processCmd = BpmUtil.getProcessCmd(request);
			processCmd.setCurrentUserId(userId.toString());
			if(runId!=0L){
				ProcessRun processRun=processRunService.getById(runId);
				if(BeanUtils.isEmpty(processRun)){
					json = this.getError("流程草稿不存在或已被清除");
					this.returnCallbackData(request, response, json);
					return;
				}
				processCmd.setProcessRun(processRun);
			}
			processRunService.startProcess(processCmd);
			
			String str = "mobile.complete.success";
			json = this.getSuccess(str);
			this.returnCallbackData(request, response, json);
		} catch (Exception ex) {
			ex.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				// 添加错误消息到日志
				json = this.getError(str);
				this.returnCallbackData(request, response, json);
			} else {
				String message =ex.getMessage();
				json = this.getError(message);
				returnCallbackData(request, response, json);
			}
		}
	}

	/**
	 * 产生的沟通意见任务，并发送到沟通人
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toStartCommunication")
	@Action(description = "沟通意见")
	@ResponseBody
	public void toStartCommunication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		String userAgent = request.getHeader("USER-AGENT");
		String cmpIds = request.getParameter("cmpIds");
		String taskId = request.getParameter("taskId");
		String opinion = request.getParameter("voteContent");
		String json = "";
		if(StringUtil.isEmpty(cmpIds)){
			json = this.getError("请设置沟通人");
			returnCallbackData(request, response, json);
			return;
		}else if(StringUtil.isEmpty(opinion)){
			json = this.getError("请填写沟通意见");
			returnCallbackData(request, response, json);
			return;
		}
		try {
			String informType = this.getDefMessage();
			// 保存意见
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity
							.getProcessInstanceId()));

			processRunService.saveCommuniCation(taskEntity, opinion,
					informType, cmpIds, processRun.getSubject());

			Long runId = processRun.getRunId();

			String memo = "在:【" + processRun.getSubject() + "】,节点【"
					+ taskEntity.getName() + "】,意见:" + opinion;
			bpmRunLogService.addRunLog(runId,
					BpmRunLog.OPERATOR_TYPE_ADDOPINION, memo);
		} catch (Exception e) {
			e.printStackTrace();
			String msg =e.getMessage();
			json = this.getError(msg);
			returnCallbackData(request, response, json);
			return;
		}
		json = this.getSuccess("");
		returnCallbackData(request, response, json);
	}

	/**
	 * 保存反馈意见
	 * 
	 * @param request
	 * @param response
	 * @param taskOpinion
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveOpinion")
	@Action(description = "保存反馈意见")
	@ResponseBody
	public void saveOpinion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String json = "";
		String informType=RequestUtil.getString(request, "informType");
		boolean isAgree = RequestUtil.getBoolean(request, "isAgree");
		TaskOpinion taskOpinion = new TaskOpinion();
		taskOpinion.setOpinionId(UniqueIdUtil.genId());
		SysUser sysUser = ContextUtil.getCurrentUser();
		ProcessCmd taskCmd = BpmUtil.getProcessCmd(request);
		try {
			TaskEntity taskEntity = bpmService.getTask(taskOpinion.getTaskId()
					.toString());
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));

			String description = taskEntity.getDescription();
			Integer sysTemplateType = 0;
			taskOpinion.setActDefId(taskEntity.getProcessDefinitionId());
			taskOpinion.setActInstId(taskEntity.getProcessInstanceId());
			taskOpinion.setStartTime(taskEntity.getCreateTime());
			taskOpinion.setEndTime(new Date());
			Long duration = calendarAssignService.getRealWorkTime(taskEntity.getCreateTime(),
					new Date(), sysUser.getUserId());
			taskOpinion.setDurTime(duration);
			taskOpinion.setExeUserId(sysUser.getUserId());
			taskOpinion.setExeFullname(sysUser.getFullname());
			taskOpinion.setTaskKey(taskEntity.getTaskDefinitionKey());
			taskOpinion.setTaskName(taskEntity.getName());
			if(description.equals(TaskOpinion.STATUS_TRANSTO.toString())
				||description.equals(TaskOpinion.STATUS_TRANSTO_ING.toString())) {//流转
				if(taskEntity.getAssignee().equals(sysUser.getUserId().toString())){
					taskOpinion.setCheckStatus(TaskOpinion.STATUS_REPLACE_SUBMIT);
				}
				else {
					taskOpinion.setCheckStatus(TaskOpinion.STATUS_INTERVENE);
					String opinion = taskOpinion.getOpinion();
					Long userId = Long.valueOf(taskEntity.getAssignee());
					SysUser assignee = sysUserService.getById(userId);
					opinion += "(原执行人【"+assignee.getFullname()+"】)";
					taskOpinion.setOpinion(opinion);
				}
				sysTemplateType = SysTemplate.USE_TYPE_TRANSTO_FEEDBACK;
				taskCmd.setVoteAgree(isAgree?TaskOpinion.STATUS_AGREE:TaskOpinion.STATUS_REFUSE);
			}
			else{//沟通
				taskOpinion.setCheckStatus(TaskOpinion.STATUS_NOTIFY);
				sysTemplateType = SysTemplate.USE_TYPE_FEEDBACK;
			}
			
			//保存反馈信息
			processRunService.saveOpinion(taskEntity, taskOpinion);
			//设置沟通人员或流转人员的状态为已反馈
			commuReceiverService.setCommuReceiverStatusToFeedBack(taskEntity, sysUser);
			
			//向原执行人发送任务完成提醒消息
			Map<Long, Long> usrIdTaskIds = new HashMap<Long, Long>();
			ProcessTask parentTask = processRunService.getByTaskId(Long.valueOf(taskEntity.getParentTaskId()));
			usrIdTaskIds.put(Long.valueOf(parentTask.getAssignee()), Long.valueOf(taskEntity.getParentTaskId()));
			processRunService.notifyCommu(processRun.getSubject(), usrIdTaskIds, informType, sysUser, 
					taskOpinion.getOpinion(), sysTemplateType);
			
			//添加已办历史
			processRunService.addActivityHistory(taskEntity);
			
			//处理表单
			BpmFormData bpmFormData= bpmFormHandlerService.handlerFormData(taskCmd, processRun,taskEntity.getTaskDefinitionKey());
			if(bpmFormData!=null){
				Map<String, String> optionsMap=new HashMap<String, String>();
				optionsMap.put("option", taskOpinion.getOpinion());
				// 记录意见
				updOption(optionsMap, taskCmd);
			}
			
			//判断是否流转任务
			if(description.equals(TaskOpinion.STATUS_TRANSTO.toString())
				||description.equals(TaskOpinion.STATUS_TRANSTO_ING.toString())){//流转任务
				String parentTaskId = taskEntity.getParentTaskId();
				BpmProTransTo bpmProTransTo = bpmProTransToService.getByTaskId(Long.valueOf(parentTaskId));
				processRunService.handlerTransTo(taskCmd, bpmProTransTo, sysUser, parentTaskId, isAgree);
			}

			Long runId = processRun.getRunId();

			String memo = "在:【" + processRun.getSubject() + "】,节点【"
					+ taskEntity.getName() + "】,意见:" + taskOpinion.getOpinion();
			bpmRunLogService.addRunLog(runId,BpmRunLog.OPERATOR_TYPE_ADDOPINION, memo);

			json = this.getSuccess("添加意见成功!");
			this.returnCallbackData(request, response, json);
		} catch (Exception e) {
			e.printStackTrace();
			json = this.getError("mobile.error");
			this.returnCallbackData(request, response, json);
		}
		

	
	}
	
	
	/**
	 * 更新意见。
	 * 
	 * <pre>
	 * 1.首先根据任务id查询意见，应为意见在task创建时这个意见就被产生出来，所以能直接获取到该意见。
	 * 2.获取意见，注意一个节点只允许一个意见填写，不能在同一个任务节点上同时允许两个意见框的填写，比如同时科员意见，局长意见等。
	 * 3.更新意见。
	 * </pre>
	 * 
	 * @param optionsMap
	 * @param taskId
	 */
	private void updOption(Map<String, String> optionsMap, ProcessCmd cmd) {
		if (BeanUtils.isEmpty(optionsMap)) return;

		Set<String> set = optionsMap.keySet();
		String key = set.iterator().next();
		String value = optionsMap.get(key);
		cmd.setVoteFieldName(key);
		cmd.setVoteContent(value);

	}

	/**
	 * 转办保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("assignSave")
	@Action(description = "转办保存")
	@ResponseBody
	public void assignSave(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = "";
		try {
			String taskId = RequestUtil.getString(request, "taskId");
			Long assigneeId = RequestUtil.getLong(request, "cmpIds");
			String assigneeName = RequestUtil
					.getString(request, "cmpNames");
			String memo = RequestUtil.getString(request, "voteContent");
			String informType=RequestUtil.getString(request, "informType");

			SysUser sysUser = ContextUtil.getCurrentUser();
			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (BeanUtils.isEmpty(taskEntity)){
				// 任务已经被处理!
				json = this.getError("mobile.saveOpinion.fail");
				this.returnCallbackData(request, response, json);
				return;
			}
			String assignee = taskEntity.getAssignee();
			// 任务人不为空且和当前用户不同。
			// if(ServiceUtil.isAssigneeNotEmpty(assignee) &&
			// assignee.equals(assigneeId)){
			// //不能转办给任务执行人!
			// return getError("mobile.assignSave.notAssignExecutor");
			// }
			// if(ServiceUtil.isAssigneeNotEmpty(assignee) ){
			// if(!assignee.equals(sysUser.getUserId().toString())){
			// //对不起，转办失败。您（已）不是任务的执行人。
			// return getError("mobile.assignSave.notExecutor");
			// }
			// }

			// 任务人不为空
			if (ServiceUtil.isAssigneeNotEmpty(assignee)) {
				if (!assignee.equals(sysUser.getUserId().toString())) {
					json = this.getError("mobile.assignSave.notExecutor");
					this.returnCallbackData(request, response, json);
					return;
				}
			}
			// 与当前用户不同。
			if (assignee.equals(String.valueOf(assigneeId))) {
				json = this.getError("mobile.assignSave.notAssignExecutor");
				this.returnCallbackData(request, response, json);
				return;
			}

			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity
							.getProcessInstanceId()));

			String actDefId = processRun.getActDefId();

			boolean rtn = bpmDefinitionService.allowDivert(actDefId);
			if (!rtn){
				// 任务不允许进行转办!
				json = this.getError("mobile.assignSave.notAssign");
				this.returnCallbackData(request, response, json);
				return;
			}
	

			boolean isAssign = bpmTaskExeService
					.getByIsAssign(new Long(taskId));
			if (isAssign) {
				// 任务不允许进行转办!
				json = this.getError("controller.bpmTaskExe.hasAssignee");
				this.returnCallbackData(request, response, json);
				return;
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
			json = this.getSuccess("mobile.success");
			this.returnCallbackData(request, response, json);
		} catch (Exception e) {
			e.printStackTrace();
			json = this.getError("mobile.error");
			this.returnCallbackData(request, response, json);
		}
	}
	
	
	/**
	 * 任务追回,检查当前正在运行的任务是否允许进行追回。(撤销)
	 * <pre>
	 * 需要传入的参数：
	 * runId:任务执行Id。
	 * backToStart:追回到发起人。
	 * memo:追回原因。
	 *  任务能够被追回的条件：
	 *  1.流程实例没有结束。
	 *  
	 * 	任务追回包括两种情况。
	 *  1.追回到发起人。
	 *  4.如果这个流程实例有多个流程实例的情况，那么第一个跳转到驳回节点，其他的只完成当前任务，不进行跳转。
	 *  
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("recover")
	@ResponseBody
	public void recover(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long runId = RequestUtil.getLong(request, "runId");
		String informType = getDefMessage();
		String memo = RequestUtil.getString(request, "voteContent");
		ResultMessage resultMessage=null;
		try{
			//追回
			resultMessage = processRunService.recover(runId,informType,  memo);
		}
		catch(Exception ex){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, str);
			} else {
				String message  =ex.getMessage();
				resultMessage = new ResultMessage(ResultMessage.Fail,message);
			}
		}
		String json ="";
		if(resultMessage.getResult() == ResultMessage.Success){
			json = this.getSuccess(resultMessage.getMessage());
		}else{
			json = this.getError(resultMessage.getMessage());
		}
		this.returnCallbackData(request, response, json);
	}
	/**
	 * 执行催办动作
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("urgeSubmit")
	@Action(description = "执行催办")
	public void urgeSubmit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultMessage resultMessage =null;
		try {
			Long runId = RequestUtil.getLong(request, "runId");
			String informTypes = getDefMessage();
			String opinion = RequestUtil.getString(request, "voteContent");
			
			ProcessRun processRun = processRunService.getById(runId);

			String actInstId = processRun.getActInstId();
			List<Task> taskList = taskService.createTaskQuery()
					.processInstanceId(actInstId).list();
			Map<String, String> map =sysTemplateService.getTempByFun(SysTemplate.USE_TYPE_URGE);
			taskMessageService.notify(taskList, informTypes, processRun.getSubject(), map, opinion, null);
			// "催办信息已发送成功"
			String message = getText("controller.processRun.urgeSubmit.success");
			resultMessage = new ResultMessage(
					ResultMessage.Success, message);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				//"催办信息已发送失败:"
				String msg = getText("controller.processRun.urgeSubmit.fail");
				resultMessage = new ResultMessage(
						ResultMessage.Fail, msg+":" + str);
			} else {
				String message =ex.getMessage();
				resultMessage = new ResultMessage(
						ResultMessage.Fail, message);
			}
		}
		String json ="";
		if(resultMessage.getResult() == ResultMessage.Success){
			json = this.getSuccess(resultMessage.getMessage());
		}else{
			json = this.getError(resultMessage.getMessage());
		}
		this.returnCallbackData(request, response, json);
	}

	
	/**
	 * 我的时效（移动端接口）
	 * @param request
	 * @param response
	 * @return
	 * <pre>
	 * {"task_count":"任务数", "avg_time":"平均时效", "avg_order":"排名", "count":"部门中参与处理任务的人数"}
	 * </pre>
	 * @throws Exception
	 */
	@RequestMapping("myAvgTime")
	@Action(description="我的时效")
	@ResponseBody
	public void myAvgTime(HttpServletRequest request,HttpServletResponse response) throws Exception{	
//		Long userId = RequestUtil.getLong(request, "userId", 0L);
		String json = "";
		//monthUserProcessReportService.myAvgTime(userId);
		this.returnCallbackData(request, response, json);
	}	
	
}
