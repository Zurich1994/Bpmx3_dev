package com.hotent.platform.controller.mobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.graph.ShapeMeta;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.AuthorizeRight;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.model.bpm.BpmProCopyto;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.BpmTaskExe;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.bpm.TaskSignData;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.mobile.MobileFormData;
import com.hotent.platform.model.mobile.MoblieImage;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.BpmNodeButtonService;
import com.hotent.platform.service.bpm.BpmNodeSignService;
import com.hotent.platform.service.bpm.BpmNodeSignService.BpmNodePrivilegeType;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmProCopytoService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.BpmTaskExeService;
import com.hotent.platform.service.bpm.CommuReceiverService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.TaskSignDataService;
import com.hotent.platform.service.bpm.impl.BpmActService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.TaskReadService;
import com.hotent.platform.service.mobile.MobileTaskService;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.util.ServiceUtil;

@Controller
@RequestMapping("/platform/mobile/mobileTask/")
public class MobileTaskController extends MobileBaseController {
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private BpmService bpmService;
	@Resource
	private MobileTaskService mobileTaskService;
	@Resource
	private CommuReceiverService commuReceiverService;
	@Resource
	private BpmNodeButtonService bpmNodeButtonService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private TaskReadService taskReadService;
	@Resource
	private TaskSignDataService taskSignDataService;
	@Resource
	private BpmNodeSignService bpmNodeSignService;
	@Resource
	private BpmTaskExeService bpmTaskExeService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private BpmRunLogService bpmRunLogService;
	@Resource
	private SysTemplateService sysTemplateService;
	@Resource
	private HistoryService historyService;
	@Resource
	private BpmFormRunService bpmFormRunService;
	@Resource
	private BpmProCopytoService bpmProCopytoService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmActService bpmActService;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;

	/**
	 * 跳转到启动流程页面。<br/>
	 * 
	 * <pre>
	 * 传入参数流程定义id：defId。 
	 * 实现方法： 
	 * 1.根据流程对应ID查询流程定义。 
	 * 2.获取流程定义的XML。
	 * 3.获取流程定义的第一个任务节点。
	 * 4.获取任务节点的流程表单定义。 
	 * 5.显示启动流程表单页面。
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("startFlowForm")
	@Action(description = "跳至启动流程页面")
	@ResponseBody
	public Object startFlowForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long defId = RequestUtil.getLong(request, "defId");

			String businessKey = RequestUtil.getString(request, "businessKey","");
			//复制表单 启动流程
			String copyKey = RequestUtil.getString(request, "copyKey", "");
		
			String ctxPath=request.getContextPath();
			//流程草稿
			Long runId=RequestUtil.getLong(request, "runId",0L);
			
			@SuppressWarnings("rawtypes")
			Map paraMap = RequestUtil.getParameterValueMap(request, false, false);
			paraMap.remove("businessKey");
			paraMap.remove("defId");

			ProcessRun processRun=null;
			BpmDefinition bpmDefinition = null;
			if(runId!=0){
				processRun=processRunService.getById(runId);
				defId=processRun.getDefId();
			}
			if(defId!=0L)
				bpmDefinition=bpmDefinitionService.getById(defId);

			if (BeanUtils.isEmpty(bpmDefinition))
				return getError("该流程定义已经被删除!");
			
			if (bpmDefinition.getStatus()==BpmDefinition.STATUS_DISABLED ||
					bpmDefinition.getStatus()==BpmDefinition.STATUS_INST_DISABLED)
				return getError("该流程已经禁用不允许启动!");
			//是否是草稿
			Boolean isDraft =  false;
			Boolean	isFormEmpty = false;
			Boolean	isExtForm= false;
			MobileFormData formdata = null;
			String actDefId = null;
			//通过草稿启动流程
			if(BeanUtils.isNotEmpty(processRun)&&processRun.getStatus()==ProcessRun.STATUS_FORM){
				businessKey = processRun.getBusinessKey();
				Long formDefId=processRun.getFormDefId();
				actDefId=processRun.getActDefId();
				int isNewVersion=RequestUtil.getInt(request, "isNewVersion",0);
				if(formDefId!=0L){
					boolean isExistsData=bpmFormHandlerService.isExistsData(processRun.getDsAlias(), processRun.getTableName(),processRun.getPkName(), processRun.getBusinessKey());
					if(!isExistsData)
						return getError("不存在表单数据!");
				}
				
				if(StringUtil.isNotEmpty(processRun.getBusinessUrl())){
					isExtForm=true;

				}else{
					if(isNewVersion==1){
						BpmFormDef defaultFormDef=bpmFormDefService.getById(formDefId);
						formDefId=bpmFormDefService.getDefaultPublishedByFormKey(defaultFormDef.getFormKey()).getFormDefId();
					}
					BpmFormDef bpmFormDef=bpmFormDefService.getById(formDefId);
					
				}
				
			}else{
				isDraft = true;
				if(StringUtil.isNotEmpty(copyKey))
					businessKey = copyKey;
				actDefId = bpmDefinition.getActDefId();
				
				// 获取表单节点
				BpmNodeSet bpmNodeSet = bpmFormRunService.getStartBpmNodeSet(bpmDefinition.getDefId(), actDefId);

				formdata = mobileTaskService.getForm(bpmNodeSet, businessKey, actDefId, ctxPath);

				if(BeanUtils.isNotEmpty(bpmNodeSet)){
					map.put("formKey", bpmNodeSet.getFormKey());
				}
			}

			// 通过defid获取起始节点的联动设置
			// List<BpmGangedSet> bpmGangedSets =
			// bpmGangedSetService.getStartNodeByDefId(defId);
			// JSONArray gangedSetJarray =
			// (JSONArray)JSONArray.fromObject(bpmGangedSets);

			// 获取按钮
			Map<String, List<BpmNodeButton>> mapButton = bpmNodeButtonService
					.getMapByStartForm(defId);
			// //帮助文档
			// SysFile sysFile=null;
			// if(BeanUtils.isNotEmpty(bpmDefinition.getAttachment()))
			// sysFile=sysFileService.getById(bpmDefinition.getAttachment());
			// 流程图
			MoblieImage moblieImage = this.flowImage(actDefId);

			map.put("success", true);
			map.put("bpmDefinition", bpmDefinition);
			map.put("defId", defId);
			map.put("form", JSONObject.fromObject(formdata));
			// map.put("bpmGangedSets", gangedSetJarray);
			map.put("mapButton", mapButton);
			map.put("businessKey", StringUtil.isNotEmpty(copyKey) ? ""
					: businessKey);
			map.put("moblieImage", moblieImage);
		} catch (Exception e) {
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isEmpty(str)) {
				str = ExceptionUtil.getExceptionMessage(e);
			}
			return getError("获取表单失败!");
		}

		return map;
	}

	/**
	 * 获取待办表单数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTaskForm")
	@Action(description = "获取待办表单数据")
	@ResponseBody
	public Object getTaskForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SysUser sysUser = ContextUtil.getCurrentUser();
		try {

			String ctxPath = request.getRequestURL().toString().split("platform/mobile")[0];
				
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			String taskId = RequestUtil.getString(request, "taskId");
			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (taskEntity == null)
				//对不起,这个任务已经被处理了!
				return this.getError("mobile.getTaskForm.deal");
			Long userId = sysUser.getUserId();
			String instanceId = taskEntity.getProcessInstanceId();
			String nodeId = taskEntity.getTaskDefinitionKey();
			String actDefId = taskEntity.getProcessDefinitionId();
			
			if(StringUtils.isNotEmpty(taskEntity.getDescription()) && taskEntity.getDescription().equals(TaskOpinion.STATUS_RESTART_TASK.toString())){
				// 更新任务为已读。
				taskReadService.saveReadRecord(Long.parseLong(instanceId),
						Long.parseLong(taskId));
				// 设置沟通人员查看状态。
				commuReceiverService.setRestartCommuReceiverStatus(taskEntity, sysUser);
				ProcessRun	processRun = 	processRunService.getByActInstanceId(new Long(instanceId));
				if(BeanUtils.isEmpty(processRun))
					//找不到该流程实例
					return this.getError("mobile..getTaskForm.notInstance");
				MobileFormData formdata = mobileTaskService.getProcessData(processRun.getRunId(),
						userId, ctxPath);
				if (!formdata.isValid())
					//"流程定义的流程表单发生了更改,数据无法显示!"
					return this.getError("mobile.getTaskForm.notShow");
				map.put("form", JSONObject.fromObject(formdata));
				map.put("isRestartTask", true);
				map.put("runId", processRun.getRunId());	
			}else{
	
				// 更新任务为已读。
				taskReadService.saveReadRecord(Long.parseLong(instanceId),
						Long.parseLong(taskId));
				// 设置沟通人员查看状态。
				commuReceiverService.setCommuReceiverStatus(taskEntity, sysUser);

				BpmDefinition bpmDefinition = bpmDefinitionService
						.getByActDefId(actDefId);

				Long defId = bpmDefinition.getDefId();

				MobileFormData formdata = mobileTaskService.getFormData(taskEntity,
						ctxPath);
				if (!formdata.isValid())
					//"流程定义的流程表单发生了更改,数据无法显示!"
					return this.getError("mobile.getTaskForm.notShow");

				// 是否会签任务
				boolean isSignTask = bpmService.isSignTask(taskEntity);
				if (isSignTask)
					handleSignTask(map, instanceId, nodeId, actDefId, userId);
				// 是否支持回退
				boolean isCanBack = bpmActService.isTaskAllowBack(taskId);

				// 是否转办
				boolean isCanAssignee = bpmTaskExeService.isAssigneeTask(
						taskEntity, bpmDefinition);
				
				// 流程按钮
				Map<String, List<BpmNodeButton>> mapButton = bpmNodeButtonService
						.getMapByDefNodeId(defId, nodeId);
				// 是否沟通
				boolean isTaskNotify = (BeanUtils.isEmpty(taskEntity
						.getExecutionId()) && taskEntity.getDescription().equals(
						TaskOpinion.STATUS_COMMUNICATION.toString()));
		
	
				map.put("form", JSONObject.fromObject(formdata));
				map.put("isTaskNotify", isTaskNotify);
				map.put("mapButton", mapButton);
				map.put("isSignTask", isSignTask);
				map.put("isCanBack", isCanBack);
				map.put("isCanAssignee", isCanAssignee);	
			}
			// 流程图
			MoblieImage moblieImage = this.userImage(request);
			// 获取保存的意见
			TaskOpinion taskOpinion = taskOpinionService.getOpinionByTaskId(
					Long.parseLong(taskId), userId);
			Boolean isFirstNode = NodeCache.isFirstNode(actDefId, nodeId);
			map.put("taskId", taskEntity.getId());	
			map.put("moblieImage", JSONObject.fromObject(moblieImage));
			map.put("taskOpinion", taskOpinion);
			map.put("isFirstNode", isFirstNode);
	
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isEmpty(str)) {
				str = ExceptionUtil.getExceptionMessage(e);
			}
			//错误消息
			return addError(request, sysUser, "mobile.getTaskForm.errorCode", str);
		}
	}
	
	

	/**
	 * 处理会签
	 * 
	 * @param mv
	 * @param instanceId
	 * @param nodeId
	 * @param actDefId
	 * @param userId
	 *            当前用户
	 */
	private void handleSignTask(Map<String, Object> map, String instanceId,
			String nodeId, String actDefId, Long userId) {

		List<TaskSignData> signDataList = taskSignDataService
				.getByNodeAndInstanceId(instanceId, nodeId);
		// 获取会签规则
		BpmNodeSign bpmNodeSign = bpmNodeSignService.getByDefIdAndNodeId(
				actDefId, nodeId);

		map.put("signDataList", signDataList);
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
	 * 通过流程定义获得流程
	 * 
	 * @param actDefId
	 * @return
	 * @throws Exception
	 */
	public MoblieImage flowImage(String actDefId) throws Exception {

		MoblieImage moblieImage = new MoblieImage();
		String defXml = null;
		// TaskEntity taskEntity = null;
		ShapeMeta shapeMeta = null;
	
		try {
			if (StringUtils.isNotEmpty(actDefId)) {
				try {
					defXml = bpmService
							.getDefXmlByProcessDefinitionId(actDefId);
				} catch (Exception ex) {
					Object[] params = {actDefId};
					String msg =getText("mobile.flowImage.delete",params);
					//读取流程定义失败,流程定义【" + actDefId + "】可能已经删除
					moblieImage.setMsg(msg);
					moblieImage.setSuccess(false);
					return moblieImage;
				}

			}
			shapeMeta = BpmUtil.transGraph(defXml);

		} catch (Exception e) {
			Object[] params = {actDefId};
			String msg =getText("mobile.flowImage.delete",params);
			//读取流程定义失败,流程定义【" + actDefId + "】可能已经删除
			moblieImage.setMsg(msg);
			moblieImage.setSuccess(false);
			return moblieImage;
		}
		if (BeanUtils.isNotEmpty(shapeMeta)) {
			moblieImage.setHeight(shapeMeta.getHeight());
			moblieImage.setWidth(shapeMeta.getWidth());
			moblieImage.setXml(shapeMeta.getXml());
			moblieImage.setActDefId(actDefId);
			moblieImage.setType(MoblieImage.type_1);
		}

		return moblieImage;

	}

	/**
	 * 保存表单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveData")
	@Action(description = "保存表单")
	@ResponseBody
	public Object saveData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = RequestUtil.getString(request, "taskId", "");
		String voteContent = RequestUtil.getString(request, "voteContent");

		if (StringUtil.isNotEmpty(taskId)) {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			processRunService.saveOpinion(taskEntity, voteContent);
		}
		//保存表单成功
		return getSuccess("mobile.saveData.success");

	}

	/**
	 * 获得流程图
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public MoblieImage userImage(HttpServletRequest request) throws Exception {
		MoblieImage moblieImage = new MoblieImage();

		String processDefinitionId = null;
		String defXml = null;
		// 这三个参数任意传入一个均可
		String taskId = request.getParameter("taskId");
		String runId = request.getParameter("runId");
		String processInstanceId = request.getParameter("processInstanceId");

		TaskEntity taskEntity = null;
		ProcessRun processRun = null;
		ShapeMeta shapeMeta = null;
		try {
			if (StringUtils.isNotEmpty(taskId)) {// 任务ID
				taskEntity = bpmService.getTask(taskId);
				if (BeanUtils.isEmpty(taskEntity)) {
					moblieImage.setSuccess(false);
					//该流程已经结束！
					moblieImage.setMsg("mobile.userImage.taskEnd");
					return moblieImage;
				}
				processDefinitionId = taskEntity.getProcessDefinitionId();
				processInstanceId = taskEntity.getProcessInstanceId();
				processRun = processRunService
						.getByActInstanceId(new Long(processInstanceId));
				try {
					defXml = bpmService
							.getDefXmlByProcessDefinitionId(processDefinitionId);
				} catch (Exception ex) {
					Object[] params = {processDefinitionId};
					String msg =getText("mobile.flowImage.delete",params);
					moblieImage.setMsg(msg);
					moblieImage.setSuccess(false);
					return moblieImage;
				}
			} else if (StringUtils.isNotEmpty(runId)) {// runID
				processRun = processRunService.getById(new Long(runId));
				request.setAttribute("processInstanceId",
						processRun.getActInstId());
				processInstanceId = processRun.getActInstId().toString();
				processDefinitionId = processRun.getActDefId();
				try {
					defXml = bpmService
							.getDefXmlByProcessDefinitionId(processDefinitionId);
				} catch (Exception ex) {
					Object[] params = {processDefinitionId};
					String msg =getText("mobile.flowImage.delete",params);
					moblieImage.setMsg(msg);
					moblieImage.setSuccess(false);
					return moblieImage;
				}
			} else if (StringUtils.isNotEmpty(processInstanceId)) {//
				processRun = processRunService
						.getByActInstanceId(new Long(processInstanceId));
				try {
					defXml = bpmService
							.getDefXmlByProcessProcessInanceId(processInstanceId);
				} catch (Exception ex) {
					Object[] params = {processDefinitionId};
					String msg =getText("mobile.flowImage.delete",params);
					moblieImage.setMsg(msg);
					moblieImage.setSuccess(false);
					return moblieImage;
				}
			}

			// 查找父流程
			if (taskEntity != null) {
				ExecutionEntity executionEntity = bpmService
						.getExecutionByTaskId(taskEntity.getId());
				if (executionEntity != null
						&& executionEntity.getSuperExecutionId() != null) {
					ExecutionEntity superExecutionEntity = bpmService
							.getExecution(executionEntity.getSuperExecutionId());
					moblieImage.setSuperInstanceId(superExecutionEntity
							.getProcessInstanceId());
				}
			}

			shapeMeta = BpmUtil.transGraph(defXml);

		} catch (Exception ex) {
			Object[] params = {processDefinitionId};
			String msg =getText("mobile.flowImage.delete",params);
			moblieImage.setMsg(msg);
			moblieImage.setSuccess(false);
			return moblieImage;
		}
		if (BeanUtils.isNotEmpty(shapeMeta)) {
			moblieImage.setHeight(shapeMeta.getHeight());
			moblieImage.setWidth(shapeMeta.getWidth());
			moblieImage.setXml(shapeMeta.getXml());
			moblieImage.setProcessInstanceId(processInstanceId);
			moblieImage.setType(MoblieImage.type_0);
		}

		return moblieImage;
	}

	/**
	 * 子流程图
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("subFlowImage")
	@Action(description = "子流程图")
	@ResponseBody
	public Object subFlowImage(HttpServletRequest request,
			HttpServletResponse response) {
		MoblieImage moblieImage = new MoblieImage();

		String subProcessDefinitionId = null;
		List<String> subProcessInstanceId = new ArrayList<String>();
		String subDefXml = null;
		String actInstanceId = request.getParameter("processInstanceId");
		String processDefinitionId = request
				.getParameter("processDefinitionId");
		String nodeId = request.getParameter("nodeId");
		// 子流程是否已经运行 0-未运行，1-已运行
		int subProcessRun = 0;
		// 取得子外部子流程的key.
		String subFlowKey = null;
		String actDefId = null;
		try {

			if (StringUtil.isNotEmpty(actInstanceId)) {
				actDefId = processRunService.getByActInstanceId(new Long(actInstanceId))
						.getActDefId();
			} else if (StringUtil.isNotEmpty(processDefinitionId)) {
				actDefId = processDefinitionId;
			}

			Map<String, FlowNode> flowNodeMap = NodeCache
					.getByActDefId(actDefId);
			Iterator<Entry<String, FlowNode>> entrySet = flowNodeMap.entrySet()
					.iterator();
			while (entrySet.hasNext()) {
				Entry<String, FlowNode> entry = entrySet.next();
				String flowNodeId = entry.getKey();
				if (flowNodeId.equals(nodeId)) {
					FlowNode flowNode = entry.getValue();
					subFlowKey = flowNode.getAttribute("subFlowKey");
					break;
				}
			}
			// 取得外部子流程的定义
			BpmDefinition subBpmDefinition = bpmDefinitionService
					.getMainDefByActDefKey(subFlowKey);
			if (subBpmDefinition.getActDeployId() != null) {
				subDefXml = bpmService.getDefXmlByDeployId(subBpmDefinition
						.getActDeployId().toString());
			} else {
				subDefXml = BpmUtil.transform(subBpmDefinition.getDefKey(),
						subBpmDefinition.getSubject(),
						subBpmDefinition.getDefXml());
			}

			// 取得所有的子流程实例
			List<HistoricProcessInstance> historicProcessInstances = historyService
					.createHistoricProcessInstanceQuery()
					.superProcessInstanceId(actInstanceId).list();
			if (BeanUtils.isNotEmpty(historicProcessInstances)) {
				// 筛选当选节点的子流程
				for (HistoricProcessInstance instance : historicProcessInstances) {
					String procDefId = instance.getProcessDefinitionId();
					BpmDefinition bpmDef = bpmDefinitionService
							.getByActDefId(procDefId);
					if (bpmDef.getDefKey().equals(subFlowKey)) {
						subProcessInstanceId.add(instance.getId());
						subProcessRun = 1;
					}
				}
			}
			if (subProcessRun == 0) {
				subProcessDefinitionId = subBpmDefinition.getActDefId();
			}

			ShapeMeta shapeMeta = BpmUtil.transGraph(subDefXml);
			ModelAndView modelAndView = getAutoView();
			modelAndView.addObject("defXml", subDefXml);
			modelAndView.addObject("subProcessRun", subProcessRun);
			if (subProcessRun == 0) {
				modelAndView.addObject("processDefinitionId",
						subProcessDefinitionId);
			} else {
				modelAndView.addObject("processInstanceIds",
						subProcessInstanceId);
			}

			if (BeanUtils.isNotEmpty(shapeMeta)) {
				moblieImage.setHeight(shapeMeta.getHeight());
				moblieImage.setWidth(shapeMeta.getWidth());
				moblieImage.setXml(shapeMeta.getXml());
				moblieImage.setType(MoblieImage.type_0);
				moblieImage.setActDefId(processDefinitionId);
			}

		} catch (Exception ex) {
			Object[] params = {processDefinitionId};
			String msg =getText("mobile.flowImage.delete",params);
			moblieImage.setMsg(msg);
			moblieImage.setSuccess(false);

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("moblieImage", moblieImage);
		return map;
	}

	/**
	 * 审批历史
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("taskOpinions")
	@Action(description = "审批历史")
	@ResponseBody
	public Object taskOpinions(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String taskId = request.getParameter("taskId");
		String runId = request.getParameter("runId");
		String actInstId = RequestUtil.getString(request,"actInstId","");
		TaskEntity taskEntity = null;
		ProcessRun processRun = null;
		if (StringUtil.isNotEmpty(taskId)) {
			taskEntity = bpmService.getTask(taskId);
			actInstId = taskEntity.getProcessInstanceId();
			processRun = processRunService.getByActInstanceId(new Long(actInstId));
		} else if (StringUtils.isNotEmpty(runId)) {
			processRun = processRunService.getById(new Long(runId));
			actInstId = processRun.getActInstId();
		}
		List<TaskOpinion> list = null;
		if ( StringUtil.isNotEmpty(actInstId)) {
			list = taskOpinionService.getByActInstId(actInstId, true);
		}

		for (TaskOpinion taskOpinion : list) {
			taskOpinion.setStatus(taskOpinion.getStatus());
		}
		list = taskOpinionService.setTaskOpinionExeFullname(list);
		return this.getPageList(list);
	}

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
		Map<String, Object> map = new HashMap<String, Object>();
		logger.debug("任务完成跳转....");

		String taskId = RequestUtil.getString(request, "taskId");
		TaskEntity task = bpmService.getTask(taskId);
		if (task == null)
			//此任务已经执行了!
			return getError("mobile.complete.deal");

		SysUser sysUser = ContextUtil.getCurrentUser();
		Boolean isAdmin = (Boolean) request.getSession()
				.getAttribute("isAdmin");
		String assignee = task.getAssignee();
		// 非管理员,并且没有任务的权限。
		if (isAdmin == null) {
			boolean rtn = processRunService.getHasRightsByTask(new Long(taskId), sysUser.getUserId());
			if (!rtn)
				//对不起,你不是这个任务的执行人,不能处理此任务!
				return getError("mobile.complete.notExecutors");
		}
		ProcessCmd processCmd = getProcessCmd(request);
		if (ServiceUtil.isAssigneeNotEmpty(assignee)
				&& !task.getAssignee().equals(sysUser.getUserId().toString())
				&& isAdmin == null) {
			//该任务已被其他人锁定!
			return getError("mobile.complete.locking");
		} else {
			try {
				mobileTaskService.complete(processCmd);
			} catch (Exception e) {
				e.printStackTrace();
				String str = MessageUtil.getMessage();
				if (StringUtil.isEmpty(str)) {
					str = ExceptionUtil.getExceptionMessage(e);
				}

				//错误消息
				return addError(request, sysUser, "mobile.complete.errorCode", str);

			}
		}
		//任务执行成功
		return getSuccess("mobile.complete.success");
	}

	/**
	 * 获取表单数据
	 * 
	 * @param request
	 * @return
	 */
	private ProcessCmd getProcessCmd(HttpServletRequest request) {
		// taskId
		ProcessCmd cmd = new ProcessCmd();
		String userAgent = request.getHeader("USER-AGENT");
		

		// TODO 默认 发送消息
		cmd.setInformType(this.getDefMessage());

		SysUser sysUser = ContextUtil.getCurrentUser();
		cmd.setUserAccount(sysUser.getAccount());
		String temp = request.getParameter("taskId");
		if (StringUtil.isNotEmpty(temp)) {
			cmd.setTaskId(temp);
		}
	

		// 添加表单数据
		Boolean particular = RequestUtil.getBoolean(request, "particular");
		
		if(particular){
			 temp = request.getParameter("formData");
			 if (StringUtil.isNotEmpty(temp)) {
				 cmd.setFormData(temp);
			 }
		 }

		Map<?, ?> paraMap = RequestUtil.getParameterValueMap(request, false,
				false);
		cmd.setFormDataMap(paraMap);

		BpmDefinitionService bpmDefinitionService = (BpmDefinitionService) AppUtil
				.getBean(BpmDefinitionService.class);
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
		/**
		 * 启动 外围系统保存的草稿时，需要从页面获取这2个属性
		 */
		// temp = request.getParameter("requestSystemId");
		// if (StringUtil.isNotEmpty(temp)) {
		// cmd.setRequestSystemId(temp);
		// }
		// temp = request.getParameter("businessDocumentKey");
		// if (StringUtil.isNotEmpty(temp)) {
		// cmd.setBusinessDocumentKey(temp);
		// }

		String[] destTaskIds = request.getParameterValues("lastDestTaskId");
		if (destTaskIds != null) {
			cmd.setLastDestTaskIds(destTaskIds);
			String[] destTaskUserIds = new String[destTaskIds.length];
			for (int i = 0; i < destTaskIds.length; i++) {
				String[] userIds = request.getParameterValues(destTaskIds[i]
						+ "_userId");
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

		cmd.setVoteContent(StringUtil.replaceNotVisable(request
				.getParameter("voteContent")));
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

		return cmd;
	}

	/**
	 * 流程明细表单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getFolwForm")
	@Action(description = "流程明细表单")
	@ResponseBody
	public Object getFolwForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Long runId = RequestUtil.getLong(request, "runId");
			String ctxPath = request.getContextPath();
			MobileFormData formdata = mobileTaskService.getProcessData(runId,
					ContextUtil.getCurrentUserId(), ctxPath);
			// 流程图
			MoblieImage moblieImage = this.userImage(request);
			map.put("form", JSONObject.fromObject(formdata));
			map.put("runId", runId);
			map.put("moblieImage", JSONObject.fromObject(moblieImage));
		} catch (Exception e) {
			e.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isEmpty(str)) {
				str = ExceptionUtil.getExceptionMessage(e);
			}
			SysUser sysUser = ContextUtil.getCurrentUser();
			return addError(request, sysUser, "mobile.getTaskForm.errorCode", str);
			
		}
		return map;
	}

	/**
	 * 我的待办
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pendingMatters")
	@Action(description = "我的待办")
	@ResponseBody
	public Object pendingMatters(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("userId", ContextUtil.getCurrentUserId());
		List<?> list = bpmService.getMyTasks(filter);
		return this.getPageList(list, filter);
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
	public Object alreadyMatters(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("assignee", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService.getAlreadyMattersList(filter);

		return this.getPageList(list, filter);
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
	public Object completedMatters(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("assignee", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService
				.getCompletedMattersList(filter);

		return this.getPageList(list, filter);
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
	public Object taskExe(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("ownerId", ContextUtil.getCurrentUserId());
		List<BpmTaskExe> list = bpmTaskExeService.accordingMattersList(filter);

		return this.getPageList(list, filter);
	}

	/**
	 * 新建流程
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("newProcess")
	@Action(description = "新建流程")
	@ResponseBody
	public Object newProcess(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QueryFilter filter = new QueryFilter(request, true);
	
		//增加流程分管授权的启动权限分配查询判断
		Long userId=ContextUtil.getCurrentUserId();
		String isNeedRight = "";
		
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
		
		return list;
	}

	/**
	 * 我的草稿
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myDraft")
	@Action(description = "我的草稿")
	@ResponseBody
	public Object myDraft(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("userId",ContextUtil.getCurrentUserId());// 只能是我的
		List<ProcessRun> list=processRunService.getMyDraft(filter);
		return this.getPageList(list, filter);
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
	public Object myRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService.getMyRequestList(filter);

		// 处理是否允许驳回
		if (BeanUtils.isNotEmpty(list)) {
			for (ProcessRun processRun : list) {
				String instId = processRun.getActInstId();
				List<TaskOpinion> taskOpinionList = taskOpinionService
						.getCheckOpinionByInstId(new Long(instId));
				String actDefId = processRun.getActDefId();
				FlowNode flowNode = null;
			    //判断初始节点后是否有多条路径
			    if(NodeCache.isMultipleFirstNode(actDefId)){
				    flowNode = NodeCache.getNodeByActNodeId(actDefId, processRun.getStartNode());
			    }else{
				    flowNode = NodeCache.getFirstNodeId(actDefId); 
			    }
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
		}
		return this.getPageList(list, filter);
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
	public Object myCompleted(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		QueryFilter filter = new QueryFilter(request, true);
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService.getMyCompletedList(filter);

		// 处理是否办结转发
		if (BeanUtils.isNotEmpty(list)) {
			for (ProcessRun processRun : list) {
				BpmDefinition bpmDefinition = bpmDefinitionService
						.getById(processRun.getDefId());
				if (BeanUtils
						.isNotEmpty(bpmDefinition.getAllowFinishedDivert()))
					processRun.setAllowFinishedDivert(bpmDefinition
							.getAllowFinishedDivert());
			}
		}
		return this.getPageList(list, filter);
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
	public Object assignSave(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try {
			String taskId = RequestUtil.getString(request, "taskId");
			Long assigneeId = RequestUtil.getLong(request, "assigneeId");
			String assigneeName = RequestUtil
					.getString(request, "assigneeName");
			String memo = RequestUtil.getString(request, "memo");

			SysUser sysUser = ContextUtil.getCurrentUser();
			
			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (BeanUtils.isEmpty(taskEntity)) 
				//任务已经被处理!
				return getError("mobile.assignSave.deal");
			String assignee = taskEntity.getAssignee();
			//任务人不为空且和当前用户不同。
			if(ServiceUtil.isAssigneeNotEmpty(assignee) && assignee.equals(assigneeId)){
				//不能转办给任务执行人!
				return getError("mobile.assignSave.notAssignExecutor");
			}
			if(ServiceUtil.isAssigneeNotEmpty(assignee) ){	
				if(!assignee.equals(sysUser.getUserId().toString())){
					//对不起，转办失败。您（已）不是任务的执行人。
					return getError("mobile.assignSave.notExecutor");
				}
			}
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));

			String actDefId = processRun.getActDefId();
			memo = sysUser.getFullname() + ",将流程【"
					+ processRun.getSubject() + "】转办给你处理! 原因为："+memo;
			boolean rtn = bpmDefinitionService.allowDivert(actDefId);
			if (!rtn)
				//任务不允许进行转办!
				return getError("mobile.assignSave.notAssign");

			BpmTaskExe bpmTaskExe = new BpmTaskExe();
			bpmTaskExe.setId(UniqueIdUtil.genId());
			bpmTaskExe.setTaskId(new Long(taskId));
			bpmTaskExe.setAssigneeId(assigneeId);
			bpmTaskExe.setAssigneeName(assigneeName);
			bpmTaskExe.setOwnerId(sysUser.getUserId());
			bpmTaskExe.setOwnerName(sysUser.getFullname());
			bpmTaskExe.setSubject(processRun.getSubject());
			bpmTaskExe.setStatus(BpmTaskExe.STATUS_INIT);
			bpmTaskExe.setCratetime(new Date());
			bpmTaskExe
					.setActInstId(new Long(taskEntity.getProcessInstanceId()));
			bpmTaskExe.setTaskDefKey(taskEntity.getTaskDefinitionKey());
			bpmTaskExe.setTaskName(taskEntity.getName());
			bpmTaskExe.setAssignType(BpmTaskExe.TYPE_TRANSMIT);
			bpmTaskExe.setRunId(processRun.getRunId());
			bpmTaskExe.setTypeId(processRun.getTypeId());

			bpmTaskExe.setMemo(memo);
//			SysTemplate sysTemplate = sysTemplateService
//					.getDefaultByUseType(SysTemplate.USE_TYPE_DELEGATE);
			
			bpmTaskExe.setInformType(this.getDefMessage());
			bpmTaskExeService.assignSave(bpmTaskExe);
		} catch (Exception e) {
			e.printStackTrace();
			return getError("mobile.error");
		}
		return getSuccess("mobile.success");
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
	public Object toStartCommunication(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String userAgent = request.getHeader("USER-AGENT");
		String cmpIds = request.getParameter("cmpIds");
		String taskId = request.getParameter("taskId");
		String opinion = request.getParameter("opinion");
		try {
			// String informType = RequestUtil.getString(request, "informType");
			String informType = this.getDefMessage();
			// 保存意见
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));

			processRunService.saveCommuniCation(taskEntity, opinion,
					informType, cmpIds, processRun.getSubject());

			Long runId = processRun.getRunId();

			String memo = "在:【" + processRun.getSubject() + "】,节点【"
					+ taskEntity.getName() + "】,意见:" + opinion;
			bpmRunLogService.addRunLog(runId,
					BpmRunLog.OPERATOR_TYPE_ADDOPINION, memo);
		} catch (Exception e) {
			e.printStackTrace();
			return getError("mobile.error");
		}
		return getSuccess("mobile.success");
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
	public Object saveOpinion(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		SysUser sysUser = ContextUtil.getCurrentUser();
		Long taskId = RequestUtil.getLong(request, "taskId");
		String opinion = RequestUtil.getString(request, "opinion");
		String userAgent = request.getHeader("USER-AGENT");
		// String informType = RequestUtil.getString(request, "informType");
		String informType = this.getDefMessage();

		TaskOpinion taskOpinion = new TaskOpinion();
		taskOpinion.setTaskId(taskId);
		taskOpinion.setOpinion(opinion);
		TaskEntity taskEntity = bpmService.getTask(taskId.toString());

		if (taskEntity == null)
			//需要沟通的任务已经处理!
			return getError("mobile.saveOpinion.deal");
		// 获取父任务
		String parentTaskId = taskEntity.getParentTaskId();
		TaskEntity parentTaskEntity = bpmService.getTask(parentTaskId);
		// 任务已经删除，不能再进行反馈。
		if (parentTaskEntity == null)
			//沟通的任务已完成,不能进行反馈了!
			return getError("mobile.saveOpinion.end");

		try {
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			// 保存沟通意见。
//			processRunService.saveCommuFeedBack(taskId.toString(), sysUser,
//					parentTaskEntity, taskOpinion, informType,
//					processRun.getSubject());

			Long runId = processRun.getRunId();
			String memo = "在:【" + processRun.getSubject() + "】,节点【"
					+ taskEntity.getName() + "】,意见:" + taskOpinion.getOpinion();
			bpmRunLogService.addRunLog(runId,
					BpmRunLog.OPERATOR_TYPE_ADDOPINION, memo);
		} catch (Exception e) {
			e.printStackTrace();
			return getError("mobile.saveOpinion.fail");
		}
		return getSuccess("mobile.saveOpinion.success");
	}

	/**
	 * 补签
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addSign")
	@ResponseBody
	public Object addSign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String taskId = RequestUtil.getString(request, "taskId");
		String signUserIds = RequestUtil.getString(request, "signUserIds");
		if (StringUtils.isNotEmpty(taskId)
				&& StringUtils.isNotEmpty(signUserIds)) {
			taskSignDataService.addSign(signUserIds, taskId,null,null);
			return getSuccess("mobile.addSign.success");
		} else {
			return getError("mobile.addSign.selectUser");
		}

	}

	/**
	 * 取消转办、代理
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("cancelTaskExe")
	@ResponseBody
	public Object cancelTaskExe(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		

		Long id = RequestUtil.getLong(request, "id");
		String opinion = RequestUtil.getString(request, "opinion");
		String informType = this.getDefMessage();
		SysUser sysUser = ContextUtil.getCurrentUser();
		//代理
		String bpmTaskExeType = getText("mobile.cancelTaskExe.assignee");
		String userAgent = request.getHeader("USER-AGENT");

		try {
			BpmTaskExe bpmTaskExe = bpmTaskExeService.getById(id);
			if (BpmTaskExe.TYPE_ASSIGNEE == bpmTaskExe.getAssignType())//代理
				bpmTaskExeType = getText("mobile.cancelTaskExe.assignee");
			 else if (BpmTaskExe.TYPE_TRANSMIT == bpmTaskExe.getAssignType())//转办
				bpmTaskExeType = getText("mobile.cancelTaskExe.transmit");


			Long taskId = bpmTaskExe.getTaskId();
			TaskEntity taskEntity = bpmService.getTask(taskId.toString());
			if (taskEntity == null)
				//任务已经结束!
				return getError("mobile.cancelTaskExe.taskEnd");
			// 取消转办/代理。
			bpmTaskExeService.cancel(bpmTaskExe, sysUser, opinion, informType);
			// 记录日志
			ProcessRun processRun = processRunService.getByActInstanceId(bpmTaskExe
					.getActInstId());
			String memo = "流程:" + processRun.getSubject() + ", 【"
					+ sysUser.getFullname() + "】将转办任务【"
					+ bpmTaskExe.getSubject() + "】 取消";
			bpmRunLogService.addRunLog(processRun.getRunId(),
					BpmRunLog.OPERATOR_TYPE_BACK, memo);
		} catch (Exception e) {
			e.printStackTrace();
			Object[] params = {bpmTaskExeType};
			//任务取消{0}失败!
			return getError("mobile.cancelTaskExe.fail",params);
		}
		return getSuccess("mobile.cancelTaskExe.success");
	}

	/**
	 * 查看流程抄送人
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getCopyUserByInstId")
	@Action(description = "查看流程抄送人")
	@ResponseBody
	public Object getCopyUserByInstId(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, true);
		List<BpmProCopyto> list = bpmProCopytoService
				.getUserInfoByRunId(filter);
		return getPageList(list, filter);
	}
	
	/**
	 * 保存重启任务
	 * @param request
	 * @param response
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("saveRestartTask")
	@Action(description = "保存重启任务")
	@ResponseBody
	public Map<String, Object> saveRestartTask(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		int voteAgree = RequestUtil.getInt(request, "voteAgree",0);
		String voteContent = RequestUtil.getString(request, "voteContent");
		Long taskId = RequestUtil.getLong(request, "taskId");
		Long runId = RequestUtil.getLong(request, "runId");

		String userAgent = request.getHeader("USER-AGENT");
		String informTypes= getDefMessage();
		try {
			ProcessRun processRun = processRunService.getById(runId);
//			processRunService.saveRestartTask(taskId,runId,new Long(processRun.getActInstId()),informTypes,voteAgree,voteContent);
			
		} catch (ActivitiException e) {
			e.printStackTrace();
			return getError("mobile.saveRestartTask.success");
		}

		return getSuccess(voteAgree==0?"mobile.saveRestartTask.agree":"mobile.saveRestartTask.opposition");

	}	
}
