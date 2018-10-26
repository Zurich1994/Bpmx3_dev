package com.hotent.platform.controller.bpm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.ActivitiInclusiveGateWayException;
import org.activiti.engine.ActivitiVarNotFoundException;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.freehep.graphics2d.font.FontUtilities.ShowString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fr.data.dao.JdbcDaoTemplate;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.model.ProcessTaskHistory;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.util.AppUtil;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.AuthorizeRight;
import com.hotent.platform.model.bpm.BpmDefAuthorizeType.BPMDEFAUTHORIZE_RIGHT_TYPE;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmGangedSet;
import com.hotent.platform.model.bpm.BpmNodeButton;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmNodeSign;
import com.hotent.platform.model.bpm.BpmProTransTo;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.CommuReceiver;
import com.hotent.platform.model.bpm.ExecutionStack;
import com.hotent.platform.model.bpm.FormModel;
import com.hotent.platform.model.bpm.NodeTranUser;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskFork;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.bpm.TaskSignData;
import com.hotent.platform.model.form.BpmDataTemplate;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.model.system.SysProperty;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.model.util.WarningSetting;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.BpmGangedSetService;
import com.hotent.platform.service.bpm.BpmNodeButtonService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmNodeSignService;
import com.hotent.platform.service.bpm.BpmNodeSignService.BpmNodePrivilegeType;
import com.hotent.platform.service.bpm.BpmNodeUserService;
import com.hotent.platform.service.bpm.BpmProTransToService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.BpmTaskExeService;
import com.hotent.platform.service.bpm.CommuReceiverService;
import com.hotent.platform.service.bpm.ExecutionStackService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskApprovalItemsService;
import com.hotent.platform.service.bpm.TaskHistoryService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.TaskReminderService;
import com.hotent.platform.service.bpm.TaskSignDataService;
import com.hotent.platform.service.bpm.TaskUserService;
import com.hotent.platform.service.bpm.impl.BpmActService;
import com.hotent.platform.service.bpm.impl.ScriptImpl;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.bpm.thread.TaskThreadService;
import com.hotent.platform.service.form.BpmDataTemplateService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.TaskReadService;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.system.SysErrorLogService;
import com.hotent.platform.service.system.SysFileService;
import com.hotent.platform.service.system.SysPropertyService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.platform.service.worktime.CalendarAssignService;

import edu.hrbeu.MDA.bean.NodeSet;
import edu.hrbeu.MDA.util.ParseXML;

/**
 * 后台任务管理控制类
 * 
 * @author csx
 * 
 */
@Controller
@RequestMapping("/platform/bpm/task/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class TaskController extends BaseController {
	protected Logger logger = LoggerFactory.getLogger(TaskController.class);
	@Resource
	private BpmService bpmService;
	@Resource
	private TaskService taskService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private TaskSignDataService taskSignDataService;
	@Resource
	private BpmNodeSignService bpmNodeSignService;
	@Resource
	private ExecutionStackService executionStackService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmNodeUserService bpmNodeUserService;
	@Resource
	private TaskUserService taskUserService;
	@Resource
	private BpmFormRunService bpmFormRunService;
	@Resource
	private TaskApprovalItemsService taskAppItemService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmNodeButtonService bpmNodeButtonService;
	@Resource
	private BpmRunLogService bpmRunLogService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskReadService taskReadService;
	@Resource
	private CommuReceiverService commuReceiverService;
	@Resource
	private BpmGangedSetService bpmGangedSetService;
	@Resource
	private BpmTaskExeService bpmTaskExeService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private SysFileService sysFileService;
	@Resource
	private SysErrorLogService sysErrorLogService;
	@Resource
	private TaskHistoryService taskHistoryService;
	@Resource
	private BpmProTransToService bpmProTransToService;
	@Resource
	private CalendarAssignService calendarAssignService;
	@Resource
	Map<String, IMessageHandler> handlersMap;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	@Resource
	private BpmActService bpmActService;
	@Resource
	private SysPropertyService sysPropertyService;
	@Resource
	private TaskReminderService reminderService;
	@Resource
	private BpmDataTemplateService bpmDataTemplateService;

	/**
	 * 取得起始表单。
	 * 
	 * @param bpmNodeSet
	 * @param path
	 * @return
	 * @throws Exception
	 */
	private FormModel getForm(BpmNodeSet bpmNodeSet, String businessKey,
			String actDefId, String ctxPath) throws Exception {
		FormModel formModel = new FormModel();
		if (bpmNodeSet == null||bpmNodeSet.getFormType()==-1) return formModel;
		if (bpmNodeSet.getFormType() == BpmConst.OnLineForm) {
			String formKey = bpmNodeSet.getFormKey();
			if (StringUtil.isNotEmpty(formKey)) {
				BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(formKey);
				if (bpmFormDef != null) {
					BpmFormTable bpmFormTable = bpmFormTableService.getById(bpmFormDef.getTableId());
					bpmFormDef.setTableName(bpmFormTable.getTableName());

					String formHtml = bpmFormHandlerService.obtainHtml(bpmFormDef, ContextUtil.getCurrentUserId(), businessKey, "", 

actDefId, bpmNodeSet.getNodeId(), ctxPath, "", false);
					formModel.setFormHtml(formHtml);
				}
			}
		} else {
			String formUrl = bpmNodeSet.getFormUrl();
			// 替换主键。
			formUrl = formUrl.replaceFirst(BpmConst.FORM_PK_REGEX, businessKey);
			if (!formUrl.startsWith("http")) {
				formUrl = ctxPath + formUrl;
			}
			formModel.setFormType(BpmConst.UrlForm);
			formModel.setFormUrl(formUrl);
		}
		return formModel;
	}
	
	private Map getPageParam(HttpServletRequest request){
		Map paraMap = RequestUtil.getParameterValueMap(request, false, false);
		paraMap.remove("businessKey");
		paraMap.remove("defId");
		return paraMap;
	}
	
	private ModelAndView getNotValidView(BpmDefinition bpmDefinition,String businessKey){
		if (BeanUtils.isEmpty(bpmDefinition))
			return ServiceUtil.getTipInfo("该流程定义已经被删除!");	
			if (bpmDefinition.getStatus().equals(BpmDefinition.STATUS_DISABLED)
					|| bpmDefinition.getStatus().equals(BpmDefinition.STATUS_INST_DISABLED))
				return ServiceUtil.getTipInfo("该流程定义已经被禁用!");
			//判断该业务主键是否已绑定流程实例
			boolean isProcessInstanceExisted = processRunService.isProcessInstanceExisted(businessKey);
			if(isProcessInstanceExisted){
				return ServiceUtil.getTipInfo("对不起，该流程实例已存在，不需要再次启动!");
			}
			return null;
	}

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
	@SuppressWarnings("rawtypes")
	@RequestMapping("startFlowForm")
	@Action(description = "跳至启动流程页面")
	public ModelAndView startFlowForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long defId = RequestUtil.getLong(request, "defId");
		String typeId = RequestUtil.getString(request, "typeId");
		System.out.println("typeId在后台！！！！！！::"+typeId);
		String businessKey = RequestUtil.getString(request, "businessKey", "");
		long id = RequestUtil.getLong(request, "id");
		// 复制表单 启动流程
		String copyKey = RequestUtil.getString(request, "copyKey", "");
		SysUser sysUser = ContextUtil.getCurrentUser();
		String ctxPath = request.getContextPath();
		ModelAndView mv = getAutoView();
		

		// 流程草稿传入
		Long runId = RequestUtil.getLong(request, "runId", 0L);
		// 从已经完成的流程实例启动流程。
		Long relRunId = RequestUtil.getLong(request, "relRunId", 0L);

		// 构建参数到到JSP页面。
		Map paraMap = getPageParam(request);

		ProcessRun processRun = null;
		BpmDefinition bpmDefinition = null;

		if (runId != 0) {
			processRun = processRunService.getById(runId);
			defId = processRun.getDefId();
		}

		if (defId != 0L)
			bpmDefinition = bpmDefinitionService.getById(defId);

		ModelAndView tmpView = getNotValidView(bpmDefinition, businessKey);
		if (tmpView != null)
			return tmpView;

		// 根据已经完成的流程实例取得业务主键。
		String pk = processRunService.getBusinessKeyByRelRunId(relRunId);
		if (StringUtil.isNotEmpty(businessKey)) {
			businessKey = pk;
		}
		Boolean isFormEmpty = false;
		Boolean isExtForm = false;
		String form = "";
		String actDefId = "";
		// 通过草稿启动流程
		if (BeanUtils.isNotEmpty(processRun) && processRun.getStatus().equals(ProcessRun.STATUS_FORM)) {
			mv.addObject("isDraft", false);
			businessKey = processRun.getBusinessKey();
			Long formDefId = processRun.getFormDefId();
			actDefId = processRun.getActDefId();
			int isNewVersion = RequestUtil.getInt(request, "isNewVersion", 0);
			if (formDefId != 0L) {
				boolean isExistsData = bpmFormHandlerService.isExistsData(processRun.getDsAlias(), processRun.getTableName(), 

processRun.getPkName(), processRun.getBusinessKey());
				if (!isExistsData)
					return new ModelAndView("redirect:noData.ht");
			}

			if (StringUtil.isNotEmpty(processRun.getBusinessUrl())) {
				isExtForm = true;
				form = processRun.getBusinessUrl();
				// 替换主键。
				form = processRun.getBusinessUrl().replaceFirst(BpmConst.FORM_PK_REGEX, businessKey);
				if (!form.startsWith("http")) {
					form = ctxPath + form;
				}
			} else {
				if (isNewVersion == 1) {
					BpmFormDef defaultFormDef = bpmFormDefService.getById(formDefId);
					formDefId = bpmFormDefService.getDefaultPublishedByFormKey(defaultFormDef.getFormKey()).getFormDefId();
				}
				String nodeId = "";// 流程第一个节点
				FlowNode flowNode = NodeCache.getFirstNodeId(actDefId);
				if (flowNode != null) {
					nodeId = flowNode.getNodeId();
				}
				BpmFormDef bpmFormDef = bpmFormDefService.getById(formDefId);
				form = bpmFormHandlerService.obtainHtml(bpmFormDef, sysUser.getUserId(), businessKey, "", actDefId, nodeId, ctxPath, "", 

false);
			}
			// 流程定义里面的启动
		} else {
			if (StringUtil.isNotEmpty(copyKey)) {
				businessKey = copyKey;
			}
			mv.addObject("isDraft", true);
			actDefId = bpmDefinition.getActDefId();

			// 获取表单节点
			BpmNodeSet bpmNodeSet = bpmFormRunService.getStartBpmNodeSet(bpmDefinition.getDefId(), actDefId);

			FormModel formModel = getForm(bpmNodeSet, businessKey, actDefId, ctxPath);
			// 是外部表单
			isFormEmpty = formModel.isFormEmpty();
			isExtForm = formModel.getFormType() > 0;

			if (isExtForm) {
				form = formModel.getFormUrl();
			} else if (formModel.getFormType() == 0) {
				form = formModel.getFormHtml();
			}
			if (BeanUtils.isNotEmpty(bpmNodeSet)) {
				mv.addObject("formKey", bpmNodeSet.getFormKey());
			}
		}
		// 获取按钮
		Map<String, List<BpmNodeButton>> mapButton = bpmNodeButtonService.getMapByStartForm(defId);
		// 帮助文档
		SysFile sysFile = null;
		if (BeanUtils.isNotEmpty(bpmDefinition.getAttachment()))
			sysFile = sysFileService.getById(bpmDefinition.getAttachment());

		// 通过defid和nodeId获取联动设置
		List<BpmGangedSet> bpmGangedSets = bpmGangedSetService.getByDefIdAndNodeId(defId, BpmGangedSet.START_NODEID);
		JSONArray gangedSetJarray = (JSONArray) JSONArray.fromObject(bpmGangedSets);

		if (NodeCache.isMultipleFirstNode(actDefId)) {
			mv.addObject("flowNodeList", NodeCache.getFirstNode(actDefId)).addObject("isMultipleFirstNode", true);
		}

		mv.addObject("bpmDefinition", bpmDefinition)
		.addObject("isExtForm", isExtForm)
		.addObject("isFormEmpty", isFormEmpty)
		.addObject("mapButton", mapButton)
		.addObject("defId", defId)
		.addObject("paraMap", paraMap)
		.addObject("form", form)
		.addObject("runId", runId)
		.addObject("businessKey", StringUtil.isEmpty(copyKey) ? businessKey : "")
		.addObject("sysFile", sysFile)
		.addObject("bpmGangedSets", gangedSetJarray)
		.addObject("curUserId", sysUser.getUserId().toString())
		.addObject("curUserName", sysUser.getFullname());
		return mv;
	}

	/**
	 * 启动流程。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="startFlow",method=RequestMethod.POST)
	@Action(description = "启动流程")
	public void startFlow(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		PrintWriter out = response.getWriter();
		Long runId=RequestUtil.getLong(request, "runId",0L);
		String typeId = RequestUtil.getString(request, "typeId");
		System.out.println("typeId:::::::"+typeId);
		try {
			ProcessCmd processCmd = BpmUtil.getProcessCmd(request);
			
			Long userId = ContextUtil.getCurrentUserId();
			System.out.println("userId++："+userId.toString());
			processCmd.setCurrentUserId(userId.toString());
			System.out.println("runId+++++++++"+runId);
			if(runId!=0L){	
				ProcessRun processRun=processRunService.getById(runId);
				if(BeanUtils.isEmpty(processRun)){
					out.print(new ResultMessage(ResultMessage.Fail,"流程草稿不存在或已被清除"));
					return ;
				}
				processCmd.setProcessRun(processRun);
				System.out.println("processCmd+++++++++++++++++++:"+processCmd.getVariables());
			}
			System.out.println("processCMD++："+processCmd);
			ProcessRun processRunresult = processRunService.startProcess(processCmd);
			
			
            System.out.println("成功的取到了procinstid！！！！！"+processRunresult.getActInstId());
            String taskId=null;
            if(!typeId.equals("10000006460068")){
            	String query_sql="select id_  from act_hi_taskinst where proc_inst_id_='"+processRunresult.getActInstId()+"'";
        		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
        		List<Map<String, Object>> resultTaskId=jdbcTemplate.queryForList(query_sql);
        		for(int i=0;i<resultTaskId.size();i++){
        			System.out.println("查询结果为：："+resultTaskId.get(i));
        		}
        		//String taskId = RequestUtil.getString(request, "taskId");
        		//String taskId = (String) resultTaskId.get(resultTaskId.size()-1).get("id_");
                String taskresult = resultTaskId.get(0).toString();
                taskId = taskresult.substring(5, taskresult.length()-1);
        		System.out.println("成功的取到了taskId！！！！！:::::"+taskId);
                
        		String actDefId = RequestUtil.getString(request, "actDefId");
        		TaskEntity taskEntity = bpmService.getTask(taskId);
        		System.out.println("taskEntity::::::::"+taskEntity);
        		System.out.println("taskEntitygetExecutionId::::::::"+taskEntity.getExecutionId());
        		System.out.println("taskEntitygetExecution::::::::"+runtimeService.getVariables(processRunresult.getActInstId()));
        		
        	    String nodeId = taskEntity.getTaskDefinitionKey();
        		System.out.println("nodeId::::::"+nodeId);
        		 System.out.println("taskEntitygetExecution:::11:::::"+runtimeService.getVariables(taskEntity.getProcessInstanceId()));
            }          

 	  		ResultMessage resultMessage = new ResultMessage(ResultMessage.Success, "启动流程成功!"+taskId);
 	  		out.print(resultMessage);
 	  		//System.out.println("CMD++++++++11111:"+processCmd.getVariables());

		} catch (Exception ex) {
			logger.debug("startFlow:" + ex.getMessage());
			ex.printStackTrace();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"启动流程失败:\r\n" + str);
				out.print(resultMessage);
			} else {
				//String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, ex.getMessage());
				out.print(resultMessage);
			}
		
		}
	}
	
	
	/**
	 * 跳转到启动操作图页面。<br/>
	
	 * @param request
	 * @param response
	 * @return 
	 * @return
	 */
	 @RequestMapping({"startOperate"})
	  @Action(description="启动操作图")
	  public ModelAndView startOperate(HttpServletRequest request, HttpServletResponse response)
	    throws Exception
	  {
	    ModelAndView mv = new ModelAndView();
	    System.out.println("成功的进到了后台的Controller中");	  
	    ProcessCmd processCmd = BpmUtil.getProcessCmd(request);
	   // System.out.println("Another:"+processCmd.getActDefId());
	    Long defId = Long.valueOf(RequestUtil.getLong(request, "defId"));
	    String actDefId =null;
	    actDefId=processCmd.getActDefId();
	    if(actDefId==null)
	    {
	    	String url=RequestUtil.getString(request, "url");
	    	String array[]=url.split("\\?");
	    	String array2[]=array[1].split("&");
	    	String array3[]=array2[0].split("=");
	        actDefId=array3[1];
	    }
	    System.out.println("actdefId::::::"+actDefId);
	    String taskId=null;
	    taskId = RequestUtil.getSecureString(request, "taskId");
	    String nodeIdandnodeName = RequestUtil.getString(request, "nodeIdandnodeName");
	    System.out.println("nodeIdandnodeName:"+nodeIdandnodeName);
		String ssresult[] = nodeIdandnodeName.split(":");
		for(int i=0;i<ssresult.length;i++){
			System.out.println("大小是这样:"+ssresult[i]);
		}
        System.out.println("taskId在后台！！！！！！！！:::::"+taskId );
		Long runId = processCmd.getRunId();
		String nodeId = null;
	    TaskEntity taskEntity = null;
	    // 根据任务id获取任务
		taskEntity = bpmService.getTask(taskId);
		System.out.println("taskEntity::::::::"+taskEntity);
		if(taskEntity==null){
			nodeId = ssresult[1];
		}
		else{
			nodeId = taskEntity.getTaskDefinitionKey();
		}
		Map<String,String> flowNodes = NodeCache.getNextNodes(nodeId,actDefId);
		 for (String key : flowNodes.keySet()) {
			   System.out.println("他后面的节点有这些key= "+ key + " and value= " + flowNodes.get(key));
			  }
		System.out.println("nodeID最后是这样的:::::"+nodeId);
		String html =null;
		//if(nodeId.equals("UserTask1")){
			JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
			System.out.println("信息1："+taskEntity.getProcessInstanceId()); 
			String query_sql="select F_html from w_act_hi_html where F_actInstId ='"+taskEntity.getProcessInstanceId()+"'";
			
			List<Map<String,Object>>result=jdbcTemplate.queryForList(query_sql);
			
			System.out.println("查询结果为2222："+result.get(0));
			// System.out.println("taskEntitygetExecution:::11:::::"+taskService.getVariables());
	        //System.out.println("taskEntitygetExecution::::::::"+runtimeService.getVariables(taskEntity.get));
			//System.out.println(taskEntity.getExecution().getVariables());
			System.out.println("nodeId::::::"+nodeId);
		        String templateId= bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(actDefId, nodeId).getTemplateId();
		        /*System.out.println("templateId::::::"+templateId);
		        System.out.println("actDefId:"+actDefId);
		        System.out.println("defId:"+defId);	   
			    String alias=templateId;
		        String url="dataList_" + alias +".ht";
		  		String toReplaceUrl="getDisplay_" + alias +".ht";
		  		String __baseURL = request.getRequestURI().replace(url, toReplaceUrl);
		  		Map<String,Object> params = RequestUtil.getQueryMap(request);
		  		System.out.println("params:"+params);
		  		Map<String,Object> queryParams =RequestUtil.getQueryParams(request);
		  		System.out.println("queryParams:"+queryParams);
		  		System.out.println("看看这是什么！"+queryParams);
		  		//取得传入参数ID
		  		params.put("__baseURL", __baseURL);
		  		params.put(BpmDataTemplate.PARAMS_KEY_CTX, request.getContextPath());
		  		params.put("__tic", "bpmDataTemplate");
		  		String html = bpmDataTemplateService.getDisplaybyKey(alias, params, queryParams);*/
		  		//System.out.println("HTML!:"+html);	
		        html = (String) result.get(0).get("F_html");
		//}
		//else{
			/*ProcessRun processRun = processRunService.getByActInstanceId(Long.valueOf(taskEntity.getProcessInstanceId()));
			System.out.println("processRun取到得是什么:::::"+processRun);
			Long userId = sysUser.getUserId();
			html = bpmFormHandlerService.getFormDetail(processRun.getFormDefId(),processRun.getBusinessKey(),userId,processRun,ctxPath,false);
	        */
		//	BpmNodeSet bpmNodeSet = bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(actDefId, nodeId);
		//	System.out.println("节点绑定的表单key:::::"+bpmNodeSet.getFormKey());
		//	Long formDefId = bpmFormDefService.getFormDefIdByFormKey(bpmNodeSet.getFormKey());
		//	System.out.println("节点绑定的表单DEFID:::::"+formDefId);
		//	BpmFormDef bpmFormDef = bpmFormDefService.getDefaultPublishedByFormKey(bpmNodeSet.getFormKey());
		//	System.out.println("节点绑定的表单:::::"+bpmFormDef);
		//	String pkValue = request.getParameter("pkValue");
		//	String returnUrl = RequestUtil.getPrePage(request);
		//	String ctxPath=request.getContextPath();
		//	html = bpmFormHandlerService.obtainHtml(bpmFormDef, ContextUtil.getCurrentUserId(), pkValue, "", "#formPrev", "",ctxPath, "",false);
		//	System.out.println("节点绑定的表单形成的HTML:::::"+html);
		//}
	  		mv.addObject("html", html);
	  		mv.addObject("taskId",taskId);
	  		mv.setViewName("/platform/form/bpmDataTemplatePreview.jsp");
	  			  	        	    
	    return  mv;
	  }
	
	//刘文月解析 
	 @RequestMapping("findNodeMessage")
		@Action(description = "解析xml"	)
		public   Map<String,FlowNode> findNodeMessage(String actDefId){
			boolean accessOrder = false;
			Map<String ,FlowNode> flowNode=new  LinkedHashMap<String ,FlowNode>(20,0.80f,accessOrder);
			Map<String,FlowNode> map=NodeCache.readFromXml(actDefId);
			Set<String> keys = map .keySet();//索引键集
	        if(keys != null){
				 Iterator iterator = keys.iterator();  
		            while(iterator.hasNext()){ 
		            	Object kess = iterator.next();
		            	FlowNode value = (FlowNode)map.get(kess); 
		            	String key=kess.toString(); 
		            	if(key.contains("UserTask")){
		            		flowNode.put(key,value);
		            	}
		            	if(key.contains("ScriptTask")){
		            		flowNode.put(key,value);
		            	}
		            }
		    }
			 return  flowNode;
		}
	 public String findFormInfo(String actDefId)
	  {
	    
		 BpmNodeSet bpmNodeSet = new BpmNodeSet();
		 List<BpmNodeSet>list=bpmNodeSetService.getByActDefId(actDefId);
	    return list.get(0).getFormKey();

	  }


	 public BpmFormDef findFormInf(String actDefId, String nodeId)
	  {
	    BpmFormDef bpmFormDef = null;
	    BpmNodeSet bpmNodeSet = new BpmNodeSet();

	    bpmNodeSet = this.bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(actDefId, nodeId);

	    String formKey = bpmNodeSet.getFormKey();
	   // System.out.println("formKey:"+formKey);
	    List bpmFormDefList = new ArrayList();

	    bpmFormDefList = this.bpmFormDefService.getByFormKey(formKey);

	    int x = bpmFormDefList.size();
	    Integer bpmFormNo1 = Integer.valueOf(0);
	    Integer bpmFormNo = Integer.valueOf(0);
	    if (bpmFormDefList.size() != 0)
	    {
	      bpmFormNo = ((BpmFormDef)bpmFormDefList.get(0)).getVersionNo();
	      bpmFormDef = (BpmFormDef)bpmFormDefList.get(0);
	      for (int i = 1; i < x; i++) {
	        bpmFormNo1 = ((BpmFormDef)bpmFormDefList.get(i)).getVersionNo();
	        if (bpmFormNo1.intValue() <= bpmFormNo.intValue())
	          continue;
	        bpmFormNo = bpmFormNo1;
	        bpmFormDef = (BpmFormDef)bpmFormDefList.get(i);
	      }

	    }
//System.out.println("函数中的对象："+bpmFormDef);
	    return bpmFormDef;
	  }
	/**
	 * 保存草稿
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("saveForm")
	@Action(description = "保存草稿"	)
	public void saveForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		ProcessCmd processCmd = BpmUtil.getProcessCmd(request);
		Long userId = ContextUtil.getCurrentUserId();
		processCmd.setCurrentUserId(userId.toString());
		
		ResultMessage resultMessage = null;
		try {
			processRunService.saveForm(processCmd);
			resultMessage = new ResultMessage(ResultMessage.Success,"保存草稿成功！");
		} catch (Exception e) {
			String message = ExceptionUtil.getExceptionMessage(e);
			resultMessage = new ResultMessage(ResultMessage.Fail, message);
		}
		out.print(resultMessage);
	}
	
	
	/**
	 * 保存表单数据。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("saveData")
	@Action(description = "保存表单数据",
			detail="<#if StringUtils.isNotEmpty(taskId)>" +
						"保存流程【${SysAuditLinkService.getProcessRunLink(taskId)}】的表单数据" +
				   "<#elseif StringUtils.isNotEmpty(defId)>" +
				   		"保存流程定义【${SysAuditLinkService.getBpmDefinitionLink(Long.valueOf(defId))}】至草稿" +
		   		   "</#if>"
	)
	public void saveData(HttpServletRequest request,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String userId = ContextUtil.getCurrentUserId().toString();
		ProcessCmd processCmd=BpmUtil.getProcessCmd(request);
		Long runId=RequestUtil.getLong(request, "runId", 0L);
		if(runId!=0L){
			ProcessRun processRun=processRunService.getById(runId);
			processCmd.setProcessRun(processRun);
		}
		processCmd.setCurrentUserId(userId);
		ResultMessage resultMessage = null;
		try {
			processRunService.saveData(processCmd);
			resultMessage = new ResultMessage(ResultMessage.Success,"保存表单数据成功！");
		} catch (Exception ex) { 
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,
						"保存表单数据成功！:" + str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		out.print(resultMessage);
	}
	
	

	@RequestMapping("saveOpinion")
	@ResponseBody
	@Action(description = "保存流程沟通或流转意见")
	public String saveOpinion(HttpServletRequest request,HttpServletResponse response, TaskOpinion taskOpinion)throws Exception {
		JSONObject jobject = new JSONObject();
		String informType=RequestUtil.getString(request, "informType");
		boolean isAgree = RequestUtil.getBoolean(request, "isAgree");
		taskOpinion.setOpinionId(UniqueIdUtil.genId());
		SysUser sysUser = ContextUtil.getCurrentUser();
		ProcessCmd taskCmd = BpmUtil.getProcessCmd(request);
		taskCmd.setVoteContent(taskOpinion.getOpinion());//设置意见
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
		//	processRunService.saveOpinion(taskEntity, taskOpinion);
			//设置沟通人员或流转人员的状态为已反馈
		//	commuReceiverService.setCommuReceiverStatusToFeedBack(taskEntity, sysUser);
			
			//向原执行人发送任务完成提醒消息
			Map<Long, Long> usrIdTaskIds = new HashMap<Long, Long>();
			ProcessTask parentTask = processRunService.getByTaskId(Long.valueOf(taskEntity.getParentTaskId()));
			usrIdTaskIds.put(Long.valueOf(parentTask.getAssignee()), Long.valueOf(taskEntity.getParentTaskId()));
			processRunService.notifyCommu(processRun.getSubject(), usrIdTaskIds, informType, sysUser, 
					taskOpinion.getOpinion(), sysTemplateType);
			
			//添加已办历史
		//	processRunService.addActivityHistory(taskEntity);
			
			//处理表单
			BpmFormData bpmFormData= bpmFormHandlerService.handlerFormData(taskCmd, processRun,taskEntity.getTaskDefinitionKey());
			if(bpmFormData!=null){
				Map<String, String> optionsMap=new HashMap<String, String>();
				optionsMap.put("option", taskOpinion.getOpinion());
				// 记录意见
				updOption(optionsMap, taskCmd);
			}

			// 保存反馈信息 这里面会删除task的数据
			processRunService.saveOpinion(taskEntity, taskOpinion);
			// 设置沟通人员或流转人员的状态为已反馈
			commuReceiverService.setCommuReceiverStatusToFeedBack(taskEntity, sysUser);

			// 添加已办历史
			processRunService.addActivityHistory(taskEntity);

			// 判断是否流转任务
			if (description.equals(TaskOpinion.STATUS_TRANSTO.toString()) || description.equals(TaskOpinion.STATUS_TRANSTO_ING.toString())) 

{// 流转任务
				String parentTaskId = taskEntity.getParentTaskId();
				BpmProTransTo bpmProTransTo = bpmProTransToService.getByTaskId(Long.valueOf(parentTaskId));
				processRunService.handlerTransTo(taskCmd, bpmProTransTo, sysUser, parentTaskId, isAgree);
			}

			Long runId = processRun.getRunId();

			String memo = "在:【" + processRun.getSubject() + "】,节点【"
					+ taskEntity.getName() + "】,意见:" + taskOpinion.getOpinion();
			bpmRunLogService.addRunLog(runId,
					BpmRunLog.OPERATOR_TYPE_ADDOPINION, memo);

			jobject.accumulate("result", ResultMessage.Success)
			   .accumulate("message", "添加意见成功!");
			return jobject.toString();
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				jobject.accumulate("result", ResultMessage.Fail)
				   .accumulate("message", "添加意见失败:"+ str);
				return jobject.toString();
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				jobject.accumulate("result", ResultMessage.Fail)
				   .accumulate("message", message);
				return jobject.toString();
			}
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
	 * 显示任务回退的执行路径
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("back")
	public ModelAndView back(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");

		if (StringUtils.isEmpty(taskId))
			return getAutoView();

		TaskEntity taskEntity = bpmService.getTask(taskId);
		String taskToken = (String) taskService.getVariableLocal(
				taskEntity.getId(), TaskFork.TAKEN_VAR_NAME);
		ExecutionStack executionStack = executionStackService.getLastestStack(
				taskEntity.getProcessInstanceId(),
				taskEntity.getTaskDefinitionKey(), taskToken);
		if (executionStack != null && executionStack.getParentId() != null
				&& executionStack.getParentId() != 0) {
			ExecutionStack parentStack = executionStackService
					.getById(executionStack.getParentId());
			String assigneeNames = "";
			if (StringUtils.isNotEmpty(parentStack.getAssignees())) {
				String[] uIds = parentStack.getAssignees().split("[,]");
				int i = 0;
				for (String uId : uIds) {
					SysUser sysUser = sysUserService.getById(new Long(uId));
					if (sysUser == null)
						continue;
					if (i++ > 0) {
						assigneeNames += ",";
					}
					assigneeNames += sysUser.getFullname();
				}
			}
			request.setAttribute("assigneeNames", assigneeNames);
			request.setAttribute("parentStack", parentStack);
		}

		request.setAttribute("taskId", taskId);

		return getAutoView();
	}

	/**
	 * 任务回退
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("jumpBack")
	public ModelAndView jumpBack(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ProcessCmd processCmd = BpmUtil.getProcessCmd(request);
		processCmd.setCurrentUserId(ContextUtil.getCurrentUserId().toString());
		processRunService.nextProcess(processCmd);
		return new ModelAndView("redirect:list.ht");
	}

	/**
	 * 跳至会签页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toSign")
	public ModelAndView toSign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");
		ModelAndView modelView = getAutoView();

		if (StringUtils.isNotEmpty(taskId)) {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			String nodeId = bpmService
					.getExecution(taskEntity.getExecutionId()).getActivityId();
			String actInstId = taskEntity.getProcessInstanceId();

			List<TaskSignData> signDataList = taskSignDataService
					.getByNodeAndInstanceId(actInstId, nodeId);

			// 获取会签规则
			BpmNodeSign bpmNodeSign = bpmNodeSignService.getByDefIdAndNodeId(
					taskEntity.getProcessDefinitionId(), nodeId);

			modelView.addObject("signDataList", signDataList);
			modelView.addObject("task", taskEntity);
			modelView.addObject("curUser", ContextUtil.getCurrentUser());
			modelView.addObject("bpmNodeSign", bpmNodeSign);
		}

		return modelView;
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
	public String addSign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");
		String signUserIds = request.getParameter("signUserIds");
		String opinion = request.getParameter("opinion");
		String informType=RequestUtil.getString(request, "informType");
		if (StringUtils.isNotEmpty(taskId)
				&& StringUtils.isNotEmpty(signUserIds) && StringUtils.isNotEmpty(opinion)) {
			//保存意见
			taskSignDataService.addSign(signUserIds, taskId,opinion,informType);
		}
		return SUCCESS;
	}

	/**
	 * 任务自由跳转
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("jump")
	@ResponseBody
	public String jump(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskId = request.getParameter("taskId");
		String destTask = request.getParameter("destTask");
		TaskEntity taskEnt= bpmService.getTask(taskId);
		bpmService.transTo(taskEnt, destTask);

		return SUCCESS;
	}

	/**
	 * 跳至会签页
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");
		String isAgree = request.getParameter("isAgree");
		String content = request.getParameter("content");

		taskSignDataService.signVoteTask(taskId, content, new Short(isAgree));

		return SUCCESS;
	}

	@SuppressWarnings("unused")
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "taskItem");

		//增加按新的流程分管授权中任务类型的权限获取流程的任务
		Long userId=ContextUtil.getCurrentUserId();
		String isNeedRight = "";

		Map<String, AuthorizeRight> authorizeRightMap = null;
		if (!SysUser.isSuperAdmin()) {
			isNeedRight = "yes";
			//获得流程分管授权与用户相关的信息
			Map<String,Object> actRightMap = bpmDefAuthorizeService.getActRightByUserMap(userId, 

BPMDEFAUTHORIZE_RIGHT_TYPE.TASK,false,false);
			//获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			filter.addFilter("actRights", actRights);
		}
		filter.addFilter("isNeedRight", isNeedRight);
		
		List<TaskEntity> list = bpmService.getTasks(filter);
		
		request.getSession().setAttribute("isAdmin", true);

		// 是否有全局流水号
		boolean hasGlobalFlowNo = SysPropertyService.getBooleanByAlias(SysProperty.GlobalFlowNo);

		ModelAndView mv = getAutoView().addObject("taskList", list).addObject("hasGlobalFlowNo", hasGlobalFlowNo);

		return mv;
	}
	
	/**
	 * 检查是否是超级管理员
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
//	private boolean checkSuperAdmin(){
//		Collection<GrantedAuthority> auths= (Collection<GrantedAuthority>) ContextUtil.getCurrentUser().getAuthorities();
		//是否是超级管理员
//		if(auths!=null&&auths.size()>0&&auths.contains(SystemConst.ROLE_GRANT_SUPER)){
//			return true;
//		}
//		return false;
//	}

	@SuppressWarnings("rawtypes")
	@RequestMapping("forMe")
	public ModelAndView forMe(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "taskItem");
		List<?> list = bpmService.getMyTasks(filter);
		Map<String, String> candidateMap = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		if (BeanUtils.isNotEmpty(list)) {
			for (int i = 0; i < list.size(); i++) {
				ProcessTask task = (ProcessTask) list.get(i);
				if (i == 0) {
					sb.append(task.getId());
				} else {
					sb.append("," + task.getId());
				}
			}
			List<Map> userMapList = bpmService.getHasCandidateExecutor(sb
					.toString());
			for (Iterator<Map> it = userMapList.iterator(); it.hasNext();) {
				Map map = it.next();
				candidateMap.put(map.get("TASKID").toString(), "1");
			}
		}
		ModelAndView mv = getAutoView().addObject("taskList", list).addObject(
				"candidateMap", candidateMap);
		return mv;
	}

	/**
	 * 待办事项 flex 返回格式eg: [ { "id":"10000005210157", // 项id "type":"1", //
	 * 类型，如任务、消息 "startTime":"12/07/2012 00:00:00 AM", // 开始时间
	 * "endTime":"12/08/2012 00:00:00 AM", // 结束时间
	 * "title":"测试流程变量-admin-2012-10-17 11:55:07", // 标题 } ]
	 * 
	 * @throws Exception
	 */
	@RequestMapping("myEvent")
	public void myEvent(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("mode", RequestUtil.getString(request, "mode"));
		param.put("startDate", RequestUtil.getString(request, "startDate"));
		param.put("endDate", RequestUtil.getString(request, "endDate"));
		response.getWriter().print(bpmService.getMyEvents(param));
	}

	@RequestMapping("detail")
	public ModelAndView detail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long taskId = RequestUtil.getLong(request, "taskId");
		Task task = taskService.createTaskQuery().taskId(taskId.toString())
				.singleResult();
		if (task == null) {
			return new ModelAndView("redirect:notExist.ht");
		}
		String returnUrl = RequestUtil.getPrePage(request);
		// get the process run instance from task
		ProcessRun processRun = processRunService.getByActInstanceId(new Long(task
				.getProcessInstanceId()));

		BpmDefinition processDefinition = bpmDefinitionService
				.getByActDefId(processRun.getActDefId());
		ModelAndView modelView = getAutoView();
		modelView.addObject("taskEntity", task)
				.addObject("processRun", processRun)
				.addObject("processDefinition", processDefinition)
				.addObject("returnUrl", returnUrl);
		if (StringUtils.isNotEmpty(processRun.getBusinessUrl())) {
			String businessUrl = StringUtil.formatParamMsg(
					processRun.getBusinessUrl(), processRun.getBusinessKey())
					.toString();
			modelView.addObject("businessUrl", businessUrl);
		}
		return modelView;
	}
	
	/**
	 * 启动任务界面。 根据任务ID获取流程实例，根据流程实例获取表单数据。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toStart")
	public ModelAndView toStart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv =this.getAutoView();
		return getToStartView(request, response,mv,0);
	}
	
	/**
	 * 管理员使用的页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doNext")
	public ModelAndView doNext(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		boolean isTableOrTemplete = RequestUtil.getBoolean(request, "isTableOrTemplete");
		System.out.println("生成html是需要的isTableOrTemplete:::"+isTableOrTemplete);
		String instanceId = RequestUtil.getString(request, "instanceId");
		String taskId = RequestUtil.getString(request, "taskId");
		System.out.println("生成html是需要的taskId:::"+taskId);
		if(!isTableOrTemplete){
			String html =null;
				JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
				System.out.println("生成html是需要的instanceId："+instanceId); 
				String query_sql="select F_html from w_act_hi_html where F_actInstId ='"+instanceId+"'";
				
				List<Map<String,Object>>result=jdbcTemplate.queryForList(query_sql);
				
				System.out.println("生成html是需要的查询html结果为："+result.get(0));
				 html = (String) result.get(0).get("F_html");
				 mv.addObject("html", html);
				 mv.addObject("taskId",taskId);
				 mv.setViewName("/platform/form/bpmDataTemplatePreview.jsp");
		}
		else{
			mv.setViewName("/platform/bpm/taskToStart.jsp");
			mv= getToStartView(request, response,mv,1);	
		}
		
		return mv;
	}

	/**
	 * 获取actdefid对应的按钮集
	 * @author 史欣慧
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getBtnSet")
	public String getBtnSet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String actid = RequestUtil.getString(request, "8");
		System.out.println(actid);
		JdbcTemplate jdbctemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
		List<Map<String,Object>> list = jdbctemplate.queryForList("SELECT ID,OPERATORTYPE,BTNNAME FROM bpm_node_btn WHERE ACTDEFID = '"+actid

+"'");
		System.out.println("listSize:"+list.size());
		StringBuilder pw = new StringBuilder("[");
		for(int i = 0; i < list.size(); i++){
			Map<String,Object> obj = list.get(i);
			String id = obj.get("ID").toString();
			String type = obj.get("OPERATORTYPE").toString();
			String name = obj.get("BTNNAME").toString();
			pw.append("{\"ID\":"+id+",\"TYPE\":"+type+",\"NAME\":\""+name+"\"}");
			if(i!=list.size()-1)
				pw.append(",");
		}
		pw.append("]");
		PrintWriter writer = response.getWriter();
		writer.write(pw.toString());
		writer.flush();
		return null;
	}
	
	/**
	 * ButtonID和ButtonType传递到controller中
	 * @author 史欣慧
	 * @param request
	 * @param response
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("ajaxBtn")
	public ProcessRun ajaxBtn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String btnid = RequestUtil.getString(request, "btnid");
		String btntype = RequestUtil.getString(request, "btntype");
		//TODO
		System.out.println("ButtonID: "+btnid+"\nButtonType:"+btntype);
		JdbcTemplate jdbctemplate = (JdbcTemplate)AppUtil.getBean("jdbcTemplate");
		Long defid = jdbctemplate.queryForLong("SELECT DEFID FROM  bpm_node_btn WHERE ID = "+btnid);
		String defbid = jdbctemplate.queryForList("SELECT F_defbID FROM  w_eventgraphrelation WHERE F_eventID = "+btnid).get(0).get

("F_defbID").toString();
		String defkey = jdbctemplate.queryForList("SELECT DEFKEY FROM  bpm_definition WHERE DEFID = "+defbid).get(0).get("DEFKEY").toString();
		ScriptImpl scriptImpl = AppUtil.getBean(com.hotent.platform.service.bpm.impl.ScriptImpl.class);
		return scriptImpl.startFlow(defkey,defbid,null);
		//System.out.println("DEFID:"+defid);
		//ParseXML parser = new ParseXML();
		//List<NodeSet> list = parser.analysisXmlReturnList(defid);
		//for(NodeSet node : list){
		//	System.out.println("Node Name: "+node.getNodeName());
		//}
		
	}
	
	/**
	 * 流程启动页面（修改这个方法请修改手机的页面MobileTaskController.getMyTaskForm()）
	 * @param request
	 * @param response
	 * @param mv
	 * @param isManage
	 * @return
	 * @throws Exception
	 */
	private ModelAndView getToStartView(HttpServletRequest request,HttpServletResponse response,ModelAndView mv,int isManage) throws Exception{
		String ctxPath = request.getContextPath();
		SysUser sysUser=ContextUtil.getCurrentUser();
		String taskId = RequestUtil.getString(request, "taskId");
		String instanceId = RequestUtil.getString(request, "instanceId");
		String pk = null;
		pk = RequestUtil.getString(request, "pk");
		if(StringUtil.isEmpty(taskId) && StringUtil.isEmpty(instanceId)){
			return ServiceUtil.getTipInfo("没有输入任务或实例ID!");
		}
		
		//根据流程实例获取流程任务。
		if(StringUtil.isNotEmpty(instanceId)){
			List<ProcessTask> list= bpmService.getTasks(instanceId);
			if(BeanUtils.isNotEmpty(list)){
				taskId=list.get(0).getId();
			}
		}
		// 查找任务节点
		TaskEntity taskEntity = bpmService.getTask(taskId);
		
		if (taskEntity == null) {
			ProcessTaskHistory taskHistory= taskHistoryService.getById(Long.valueOf(taskId));
			if(taskHistory==null){
				if(StringUtil.isEmpty(taskId) && StringUtil.isEmpty(instanceId)){
					return ServiceUtil.getTipInfo("任务ID错误!");
				}
			}
			String actInstId = taskHistory.getProcessInstanceId();
			if(StringUtils.isEmpty(actInstId) && taskHistory.getDescription().equals(TaskOpinion.STATUS_COMMUNICATION.toString())){
				return ServiceUtil.getTipInfo("此任务为沟通任务,并且此任务已经处理!");
			}
			ProcessRun processRun = processRunService.getByActInstanceId(new Long(actInstId));
			if(processRun==null){//实例数据已被删除
				return ServiceUtil.getTipInfo("任务不存在!");
			}
			String url = request.getContextPath()+"/platform/bpm/processRun/info.ht?link=1&runId="+processRun.getRunId();
			response.sendRedirect(url);
			return null;
		}
		
		if(TaskOpinion.STATUS_TRANSTO_ING.toString().equals(taskEntity.getDescription()) && 
				taskEntity.getAssignee().equals(sysUser.getUserId().toString())){
			return ServiceUtil.getTipInfo("对不起,这个任务正在流转中,不能处理此任务!");
		}
		
		instanceId=taskEntity.getProcessInstanceId();

		if(isManage==0){
			boolean hasRights=processRunService.getHasRightsByTask(new Long(taskEntity.getId()), sysUser.getUserId());
			if(!hasRights&&sysUser.getUserId()!=1){
				return ServiceUtil.getTipInfo("对不起,你不是这个任务的执行人,不能处理此任务!");
			}
		}
		//更新任务为已读。
		taskReadService.saveReadRecord( Long.parseLong(instanceId),Long.parseLong( taskId));
		//设置沟通人员或流转人员查看状态。
		commuReceiverService.setCommuReceiverStatus(taskEntity, sysUser);
		
		String nodeId = taskEntity.getTaskDefinitionKey();
		String actDefId = taskEntity.getProcessDefinitionId();
		Long userId = ContextUtil.getCurrentUserId();

		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
		ProcessRun processRun = processRunService.getByActInstanceId(new Long(instanceId));

		Long defId = bpmDefinition.getDefId();
		
		//通过defid和nodeId获取联动设置
	//	List<BpmGangedSet> bpmGangedSets = bpmGangedSetService.getByDefIdAndNodeId(defId, nodeId);
	//	JSONArray gangedSetJarray = (JSONArray)JSONArray.fromObject(bpmGangedSets);
		/**
		 * 使用API调用的时候获取表单的url进行跳转。
		 */
		if (bpmDefinition.getIsUseOutForm() == 1) {
			String formUrl = bpmFormDefService.getFormUrl(taskId, defId,nodeId, processRun.getBusinessKey(), ctxPath);
			System.out.println("formUrl这个东西是干什么用的!!!!"+formUrl);
			if(StringUtils.isEmpty(formUrl)){			
				ModelAndView rtnModel=ServiceUtil.getTipInfo("请设置API调用时表单的url!");
				return rtnModel;
			}
			response.sendRedirect(formUrl);
		}

		// 通过defid和nodeId获取联动设置
		List<BpmGangedSet> bpmGangedSets = bpmGangedSetService.getByDefIdAndNodeId(defId, nodeId);
		JSONArray gangedSetJarray = (JSONArray) JSONArray.fromObject(bpmGangedSets);

		Map<String, Object> variables = taskService.getVariables(taskId);
		
		BpmNodeSet bpmNodeSet = null;
		if(variables.containsKey(BpmConst.FLOW_PARENT_ACTDEFID)){//判断当前是否属于子流程任务
			String parentActDefId = variables.get(BpmConst.FLOW_PARENT_ACTDEFID).toString();
			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
		}else{
			bpmNodeSet = bpmNodeSetService.getByActDefIdNodeId(actDefId, nodeId);
		}
		

		String toBackNodeId = "";
		if(StringUtil.isNotEmpty(processRun.getStartNode())){
			toBackNodeId = processRun.getStartNode();
		}
		else{
			toBackNodeId =NodeCache.getFirstNodeId(actDefId).getNodeId();
		}
		String form = "";

		Long tempLaunchId=userId;
		//在沟通和加签的时候 把当前用户对于当前表单的权限设置为传递者的权限。
		if(StringUtils.isEmpty(taskEntity.getExecutionId())){
			if(taskEntity.getDescription().equals(TaskOpinion.STATUS_TRANSTO.toString())){
				List<TaskOpinion> taskOpinionList=taskOpinionService.getByActInstId(instanceId);
				if(BeanUtils.isNotEmpty(taskOpinionList)){
					TaskOpinion taskOpinion=taskOpinionList.get(taskOpinionList.size()-1);
					List<CommuReceiver> commuReceiverList =commuReceiverService.getByOpinionId(taskOpinion.getOpinionId());
					if(BeanUtils.isNotEmpty(commuReceiverList)){
						tempLaunchId=taskOpinion.getExeUserId();
					}
				}
			}
		}
		processRun.setBusinessKey(pk);
		System.out.println("getBusinessKey这个东西是干什么用的!!!!"+processRun.getBusinessKey());
		FormModel formModel = bpmFormDefService.getForm(processRun, nodeId,tempLaunchId, ctxPath, variables);
		//如果是沟通任务 那么不允许沟通者有编辑表单的权限
		if(taskEntity.getDescription().equals(TaskOpinion.STATUS_COMMUNICATION.toString())){
			form=bpmFormHandlerService.getFormDetail(processRun.getFormDefId(),processRun.getBusinessKey(),tempLaunchId,processRun,ctxPath,false);
			formModel.setFormHtml(form);
		}
		if(!formModel.isValid()){
			ModelAndView rtnModel=ServiceUtil.getTipInfo("流程定义的流程表单发生了更改,数据无法显示!");
			return rtnModel;
		}

		String detailUrl = formModel.getDetailUrl();

		Boolean isExtForm = (Boolean) (formModel.getFormType() > 0);

		if (formModel.getFormType() == 0)
			form = formModel.getFormHtml();
		else
			form = formModel.getFormUrl();

		Boolean isEmptyForm = formModel.isFormEmpty();

		
		// 是否会签任务
		boolean isSignTask = bpmService.isSignTask(taskEntity);
		if (isSignTask){
			handleSignTask(mv, instanceId, nodeId, actDefId, userId);
		}
			
		// 是否支持回退
		boolean isCanBack=bpmActService.isTaskAllowBack(taskId);
		// 是否转办
		boolean isCanAssignee = bpmTaskExeService.isAssigneeTask(taskEntity,bpmDefinition);
		
		// 是否执行隐藏路径
		boolean isHidePath = getIsHidePath(bpmNodeSet.getIsHidePath());

		//是否是执行选择路径跳转
		boolean isHandChoolse=false;
		if (!isHidePath) {
			boolean canChoicePath = bpmService.getCanChoicePath(actDefId, taskId);
			Long startUserId = ContextUtil.getCurrentUserId();
			List<NodeTranUser>nodeTranUserList = bpmService.getNodeTaskUserMap(taskId,
					startUserId, canChoicePath);
			if (nodeTranUserList.size()>1) {
				isHandChoolse=true;
			}
		}
		
		// 获取页面显示的按钮
		Map<String, List<BpmNodeButton>> mapButton = bpmNodeButtonService.getMapByDefNodeId(defId, nodeId);
		 for (String key : mapButton.keySet()) {
			   System.out.println("是这样的页面按钮:::::key= "+ key + " and value= " + mapButton.get(key));
			  }

		// 取常用语
		List<String> taskAppItems = taskAppItemService.getApprovalByDefKeyAndTypeId(bpmDefinition.getDefKey(),bpmDefinition.getTypeId());
		//获取保存的意见
		TaskOpinion taskOpinion=taskOpinionService.getOpinionByTaskId(Long.parseLong(taskId),userId);
		
		//帮助文档
		SysFile sysFile=null;
		if (BeanUtils.isNotEmpty(bpmDefinition.getAttachment())) {
				sysFile=sysFileService.getById(bpmDefinition.getAttachment());
		}

		// 是否有全局流水号
		boolean hasGlobalFlowNo = SysPropertyService.getBooleanByAlias(SysProperty.GlobalFlowNo);

		return mv.addObject("task", taskEntity).addObject("bpmNodeSet", bpmNodeSet).addObject("processRun", processRun).addObject

("bpmDefinition", bpmDefinition).addObject("isSignTask", isSignTask).addObject("isCanBack", isCanBack).addObject("isCanAssignee", 

isCanAssignee).addObject("isHidePath", isHidePath).addObject("toBackNodeId", toBackNodeId).addObject("form", form).addObject("isExtForm", 

isExtForm).addObject("isEmptyForm", isEmptyForm).addObject("taskAppItems", taskAppItems).addObject("mapButton", mapButton).addObject("detailUrl", 

detailUrl).addObject("isManage", isManage).addObject("bpmGangedSets", gangedSetJarray).addObject("sysFile", sysFile).addObject("taskOpinion", 

taskOpinion).addObject("isHandChoolse", isHandChoolse).addObject("curUserId", sysUser.getUserId().toString())
				.addObject("curUserName", sysUser.getFullname()).addObject("hasGlobalFlowNo", hasGlobalFlowNo).addObject("formKey", 

bpmNodeSet.getFormKey());
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
	public void toStartCommunication(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		String cmpIds = request.getParameter("cmpIds");
		if (StringUtil.isEmpty(cmpIds)) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "请输入通知人!");
			out.print(resultMessage);
			return;
		}
		try {
			String taskId = request.getParameter("taskId");
			String opinion = request.getParameter("opinion");
			String informType = RequestUtil.getString(request, "informType");
			// 保存意见
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));

			processRunService.saveCommuniCation(taskEntity, opinion, informType, cmpIds, processRun.getSubject());
			ProcessCmd taskCmd = BpmUtil.getProcessCmd(request);
			bpmFormHandlerService.handlerFormData(taskCmd, processRun, taskEntity.getTaskDefinitionKey());

			Long runId = processRun.getRunId();

			String memo = "在:【" + processRun.getSubject() + "】,节点【" + taskEntity.getName() + "】,意见:" + opinion;
			bpmRunLogService.addRunLog(runId, BpmRunLog.OPERATOR_TYPE_ADDOPINION, memo);

			resultMessage = new ResultMessage(ResultMessage.Success, "成功完成了该任务!");
		} catch (Exception e) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "完成任务失败!");
		}
		out.print(resultMessage);
	}

	/**
	 * 产生的流转任务，并发送到流转人
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toStartTransTo")
	public void toStartTransTo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		String cmpIds = request.getParameter("cmpIds");
		if (StringUtil.isEmpty(cmpIds)) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "请输入通知人!");
			out.print(resultMessage);
			return;
		}
		try {
			String taskId = request.getParameter("taskId");
			String opinion = request.getParameter("opinion");
			String informType = RequestUtil.getString(request, "informType");
			String transType = request.getParameter("transType");
			String action = request.getParameter("action");
			// 保存意见
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));

			processRunService.saveTransTo(taskEntity, opinion, informType, cmpIds, transType, action, processRun);

			// 同时保存表单数据。
			ProcessCmd taskCmd = BpmUtil.getProcessCmd(request);
			taskCmd.setVoteContent(opinion);
			bpmFormHandlerService.handlerFormData(taskCmd, processRun, taskEntity.getTaskDefinitionKey());

			Long runId = processRun.getRunId();

			String memo = "在:【" + processRun.getSubject() + "】,节点【" + taskEntity.getName() + "】,意见:" + opinion;
			bpmRunLogService.addRunLog(runId, BpmRunLog.OPERATOR_TYPE_ADDOPINION, memo);

			resultMessage = new ResultMessage(ResultMessage.Success, "成功完成了该任务!");
		} catch (Exception e) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "完成任务失败!");
			e.printStackTrace();
		}
		out.print(resultMessage);
	}

	/**
	 * 是否执行隐藏路径
	 * 
	 * @param isHidePath
	 * @return
	 */
	private boolean getIsHidePath(Short isHidePath) {
		if (BeanUtils.isEmpty(isHidePath))
			return false;
		if (BpmNodeSet.HIDE_PATH.shortValue() == isHidePath.shortValue())
			return true;
		return false;
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
	private void handleSignTask(ModelAndView mv, String instanceId,
			String nodeId, String actDefId, Long userId) {

		List<TaskSignData> signDataList = taskSignDataService
				.getByNodeAndInstanceId(instanceId, nodeId);
		// 获取会签规则
		BpmNodeSign bpmNodeSign = bpmNodeSignService.getByDefIdAndNodeId(
				actDefId, nodeId);

		mv.addObject("signDataList", signDataList);
		mv.addObject("bpmNodeSign", bpmNodeSign);
		mv.addObject("curUser", ContextUtil.getCurrentUser());
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
		mv.addObject("isAllowDirectExecute", isAllowDirectExecute)
				.addObject("isAllowRetoactive", isAllowRetoactive)
				.addObject("isAllowOneVote", isAllowOneVote);

	}


	@RequestMapping("getForm")
	public ModelAndView getForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ctxPath = request.getContextPath();
		String taskId = RequestUtil.getString(request, "taskId");
		String returnUrl = RequestUtil.getPrePage(request);
		// 查找任务节点
		TaskEntity taskEntity = bpmService.getTask(taskId);
		String action = RequestUtil.getString(request, "action", "");
		if (taskEntity == null) {
			return new ModelAndView("redirect:notExist.ht");
		}
		String instanceId = taskEntity.getProcessInstanceId();
		String taskName = taskEntity.getTaskDefinitionKey();
		String actDefId = taskEntity.getProcessDefinitionId();
		Long userId = ContextUtil.getCurrentUserId();

		BpmDefinition bpmDefinition = bpmDefinitionService
				.getByActDefId(actDefId);

		ProcessRun processRun = processRunService
				.getByActInstanceId(new Long(instanceId));

		String form = "";
		Boolean isExtForm = false;
		Boolean isEmptyForm = false;

		Map<String, Object> variables = taskService.getVariables(taskId);

		if (bpmDefinition != null) {
			FormModel formModel = bpmFormDefService.getForm(processRun,
					taskName, userId, ctxPath, variables);

			isExtForm = formModel.getFormType() > 0;
			if (formModel.getFormType() == 0) {     //在线表单
				form = formModel.getFormHtml();
			} else if (formModel.getFormType() == 1) { //url表单
				form = formModel.getFormUrl();
			}
			else if (formModel.getFormType() == 2) {  //有明细url
				form = formModel.getDetailUrl();
			}

			isEmptyForm = formModel.isFormEmpty();
		}

		return getAutoView().addObject("task", taskEntity)
				.addObject("form", form)
				.addObject("bpmDefinition", bpmDefinition)
				.addObject("isExtForm", isExtForm)
				.addObject("isEmptyForm", isEmptyForm)
				.addObject("action", action)
				.addObject("processRun", processRun)
				.addObject("returnUrl", returnUrl);
	}

	

	

	/**
	 * 完成任务
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("complete")
	public void complete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.debug("任务完成跳转....");
		
		SysUser curUser = ContextUtil.getCurrentUser();
		
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		String taskId = RequestUtil.getString(request, "taskId");
		TaskEntity task = bpmService.getTask(taskId);
		if(task==null){
			resultMessage = new ResultMessage(ResultMessage.Fail, "此任务已经执行了!");
			out.print(resultMessage);
			return;
		}
		String actDefId=task.getProcessDefinitionId();
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		if(BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition.getStatus())){
			resultMessage = new ResultMessage(ResultMessage.Fail, "流程实例已经禁止，该任务不能办理！");
			out.print(resultMessage);
			return;
		}
		Long userId = curUser.getUserId();
		
		ProcessCmd taskCmd = BpmUtil.getProcessCmd(request);
		
		taskCmd.setCurrentUserId(userId.toString());
		
		String assignee = task.getAssignee();
		//非管理员,并且没有任务的权限。
		boolean isAdmin=taskCmd.getIsManage().shortValue()==1;
		if(!isAdmin){
			boolean rtn=processRunService.getHasRightsByTask(new Long(taskId), userId);
			if(!rtn){
				resultMessage = new ResultMessage(ResultMessage.Fail, "对不起,你不是这个任务的执行人,不能处理此任务!");
				out.print(resultMessage);
				return;
			}
		}
		
		//记录日志。
		logger.info(taskCmd.toString());
		if (ServiceUtil.isAssigneeNotEmpty( assignee ) && !task.getAssignee().equals(userId.toString())&& !isAdmin  ) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "该任务已被其他人锁定");
		} 
		else {
			String errorUrl = RequestUtil.getErrorUrl(request);
		    String ip =RequestUtil.getIpAddr(request);
			try {
				processRunService.nextProcess(taskCmd);
				
				resultMessage = new ResultMessage(ResultMessage.Success,"任务成功完成!");
			} 
			catch(ActivitiVarNotFoundException ex){
				resultMessage = new ResultMessage(ResultMessage.Fail,"请检查变量是否存在:" + ex.getMessage() );
				//添加错误消息到日志
				sysErrorLogService.addError(curUser.getAccount(), ip, ex.getMessage(), errorUrl);
			}
			catch(ActivitiInclusiveGateWayException ex){
				resultMessage = new ResultMessage(ResultMessage.Fail,ex.getMessage());
				//添加错误消息到日志
				sysErrorLogService.addError(curUser.getAccount(), ip, ex.getMessage(), errorUrl);
			}
			catch (Exception ex) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					resultMessage = new ResultMessage(ResultMessage.Fail, str);
					//添加错误消息到日志
					sysErrorLogService.addError(curUser.getAccount(), ip, str, errorUrl);
				} 
				else {
					String message = ExceptionUtil.getExceptionMessage(ex);
					resultMessage = new ResultMessage(ResultMessage.Fail,message);
					//添加错误消息到日志
					sysErrorLogService.addError(curUser.getAccount(), ip, message, errorUrl);
				}
			}
		}
		out.print(resultMessage);
	}

	/**
	 * 完成任务
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("complete2")
	public void complete2(HttpServletRequest request,
			HttpServletResponse response) throws Exception { 
		
	
		logger.debug("任务完成跳转....");
		System.out.println("!!!!进到新写函数了！！！");
		SysUser curUser = ContextUtil.getCurrentUser();
		String nodeId = null;
		String actdefIdsher = null;	
		String nodeIdandnodeName = null;
		String dataGet = null;
		boolean isTableOrTemplete = true;
		BpmNodeSet bpmNodeSet;
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		dataGet = RequestUtil.getString(request, "dataGet");
		System.out.println("dataGet:::::::"+dataGet);
		actdefIdsher = RequestUtil.getString(request, "actdefIdsher");
		System.out.println("actdefIdsher:::::::"+actdefIdsher);
		
		nodeIdandnodeName = RequestUtil.getString(request, "nodeIdandnodeName");
		System.out.println("得到的nodeIdandnodeName:"+nodeIdandnodeName);
		if(!nodeIdandnodeName.equals("")){
			String ssresult[] = nodeIdandnodeName.split(":");
			for(int i=0;i<ssresult.length;i++){
				System.out.println("大小是这样!!!!!!!!!!!:"+ssresult[i]);
			}
			nodeId = ssresult[1];
		}
		else{
			actdefIdsher = RequestUtil.getString(request, "actdefid");
			System.out.println("actdefIdsher:::::::"+actdefIdsher);
			nodeIdandnodeName = RequestUtil.getString(request, "nodeid");
		    System.out.println("得到的nodeIdandnodeName:"+nodeIdandnodeName);
			nodeId = nodeIdandnodeName;
			System.out.println("得到的nodeId:"+nodeId);
		}
		
		
		Map<String,String> flowNodes = NodeCache.getNextNodes(nodeId,actdefIdsher);
		 for (String key : flowNodes.keySet()) {
			   System.out.println("key= "+ key + " and value= " + flowNodes.get(key));
			  }
		String taskId=RequestUtil.getString(request, "taskId");
		System.out.println("神奇的taskID：：：：：："+taskId);
		TaskEntity task = bpmService.getTask(taskId);
		System.out.println("神奇的TaskEntity：：：：：："+task);
		if(task==null){
			resultMessage = new ResultMessage(ResultMessage.Fail, "此任务已经执行了!");
			out.print(resultMessage);
		
		}
		//String actDefId=task.getProcessDefinitionId();
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actdefIdsher);                  
		if(BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition.getStatus())){
			resultMessage = new ResultMessage(ResultMessage.Fail, "流程实例已经禁止，该任务不能办理！");
			out.print(resultMessage);
			
		}
		Long userId = curUser.getUserId();
		System.out.println("userId:::::::::"+userId);
		ProcessCmd taskCmd = BpmUtil.getProcessCmd(request);
		taskCmd.setCurrentUserId(userId.toString());
		if(taskCmd.getCurrentUserId().equals("1")){
			taskCmd.setIsManage(userId.shortValue());
		}
		String assignee = task.getAssignee();
		//非管理员,并且没有任务的权限。
		boolean isAdmin=taskCmd.getIsManage().shortValue()==1;
		if(!isAdmin){
			boolean rtn=processRunService.getHasRightsByTask(new Long(taskId), userId);
			if(!rtn){ 
				resultMessage = new ResultMessage(ResultMessage.Fail, "对不起,你不是这个任务的执行人,不能处理此任务!");
				out.print(resultMessage);
			
			}
		}
		
		//记录日志。
		logger.info(taskCmd.toString());
		if (ServiceUtil.isAssigneeNotEmpty( assignee ) && !task.getAssignee().equals(userId.toString())&& !isAdmin  ) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "该任务已被其他人锁定");
		} 
		else {
			String errorUrl = RequestUtil.getErrorUrl(request);
		    String ip =RequestUtil.getIpAddr(request);
			try {
				String nextNodeId = flowNodes.keySet().toString().substring(1, flowNodes.keySet().toString().length()-1);
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("request", dataGet);
				taskCmd.setVariables(map);
				List<Map<String,String>> nextTaskNodes = NodeCache.getNextTaskNodes(nodeId,actdefIdsher);
				Collections.reverse(nextTaskNodes);
				for(Map<String,String> nextnode : nextTaskNodes){
					System.out.println("流程变量得到的后续任务节点nodeId和nextnodeId分别为：："+nextnode);
				}
				List<Map<String,String>> nextScriptNodes = NodeCache.getNextScriptNodes(nodeId, actdefIdsher);
				Collections.reverse(nextScriptNodes);
				for(Map<String,String> nextnode : nextScriptNodes){
					System.out.println("流程变量得到的后续事物图节点nodeId和nextnodeId分别为：："+nextnode);
				}
				if(nodeId.contains("ScriptTask")){
					taskCmd.setDestTask(nodeId);
				}
				else{ 
					taskCmd.setDestTask(nextNodeId);
				}
				System.out.println("流程变量得到的nodeId和nextnodeId分别为：："+nodeId+":"+nextNodeId);
				System.out.println("这样取MAP::::"+nextTaskNodes.get(0).toString());
				System.out.println("流程变量得到的CMD是这样的::"+taskCmd);
			    System.out.println("流程变量得到的结果是这样的::"+taskCmd.getVariables());
				ProcessRun processRunnext  = processRunService.nextProcess(taskCmd) ;
				System.out.println("果然是这样！！！！！：：：：："+processRunnext.getActInstId()); 
                if(nextNodeId.contains("UserTask")){
                	System.out.println("这些值都是神马！！！！:"+processRunnext.getActInstId()+"  :"+nextNodeId);
                	bpmNodeSet = bpmNodeSetService.getBpmNodeSetByActDefIdNodeId(actdefIdsher, nextNodeId);
                	System.out.println("为什么取到了模板Id::::::"+bpmNodeSet.getTemplateId());
                	if(!bpmNodeSet.getTemplateId().equals("")){
                		isTableOrTemplete = false;
                	}
    				String query_sql="select id_  from act_hi_taskinst where proc_inst_id_='"+processRunnext.getActInstId()+"' and task_def_key_ ='"+nextNodeId+"' and proc_def_id_='"+actdefIdsher+"'";
            		JdbcTemplate jdbcTemplate = (JdbcTemplate) AppUtil.getBean("jdbcTemplate");
            		List<Map<String, Object>> resultTaskId=jdbcTemplate.queryForList(query_sql);
            		System.out.println("!!!!!!!!zhegejigeguo:::"+resultTaskId.size());
            		for(int i=0;i<resultTaskId.size();i++){
            			System.out.println("查询结果为果然是这样：："+resultTaskId.get(i));
            		}
            		String nexttaskresult = resultTaskId.get(0).toString();
                    taskId = nexttaskresult.substring(5, nexttaskresult.length()-1);
				}
				resultMessage = new ResultMessage(ResultMessage.Success,"任务成功完成!"+taskId+"!"+processRunnext.getActInstId()+"!"+isTableOrTemplete);
			} 
			catch(ActivitiVarNotFoundException ex){
				resultMessage = new ResultMessage(ResultMessage.Fail,"请检查变量是否存在:" + ex.getMessage() );
				//添加错误消息到日志
				sysErrorLogService.addError(curUser.getAccount(), ip, ex.getMessage(), errorUrl);
			}
			catch(ActivitiInclusiveGateWayException ex){
				resultMessage = new ResultMessage(ResultMessage.Fail,ex.getMessage());
				//添加错误消息到日志
				sysErrorLogService.addError(curUser.getAccount(), ip, ex.getMessage(), errorUrl);
			}
			catch (Exception ex) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					resultMessage = new ResultMessage(ResultMessage.Fail, str);
					//添加错误消息到日志
					sysErrorLogService.addError(curUser.getAccount(), ip, str, errorUrl);
				} 
				else {
					String message = ExceptionUtil.getExceptionMessage(ex);
					resultMessage = new ResultMessage(ResultMessage.Fail,message);
					//添加错误消息到日志
					sysErrorLogService.addError(curUser.getAccount(), ip, message, errorUrl);
				}
			}
		}
		out.print(resultMessage);
		
	}
	
	/**
	 * 完成任务
	 * @author 史欣慧
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("complete1")
	public void complete1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String buttonId = "10000008190077"; 
		String operatorType = "18";
		
		logger.debug("任务完成跳转....");
		
		SysUser curUser = ContextUtil.getCurrentUser();
		
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		String taskId = RequestUtil.getString(request, "taskId");
		TaskEntity task = bpmService.getTask(taskId);
		if(task==null){
			resultMessage = new ResultMessage(ResultMessage.Fail, "此任务已经执行了!");
			out.print(resultMessage);
			return;
		}
		String actDefId=task.getProcessDefinitionId();
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		if(BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition.getStatus())){
			resultMessage = new ResultMessage(ResultMessage.Fail, "流程实例已经禁止，该任务不能办理！");
			out.print(resultMessage);
			return;
		}
		Long userId = curUser.getUserId();
		
		ProcessCmd taskCmd = BpmUtil.getProcessCmd(request);
		
		taskCmd.setCurrentUserId(userId.toString());
		
		String assignee = task.getAssignee();
		//非管理员,并且没有任务的权限。
		boolean isAdmin=taskCmd.getIsManage().shortValue()==1;
		if(!isAdmin){
			boolean rtn=processRunService.getHasRightsByTask(new Long(taskId), userId);
			if(!rtn){
				resultMessage = new ResultMessage(ResultMessage.Fail, "对不起,你不是这个任务的执行人,不能处理此任务!");
				out.print(resultMessage);
				return;
			}
		}
		
		//记录日志。
		logger.info(taskCmd.toString());
		if (ServiceUtil.isAssigneeNotEmpty( assignee ) && !task.getAssignee().equals(userId.toString())&& !isAdmin  ) {
			resultMessage = new ResultMessage(ResultMessage.Fail, "该任务已被其他人锁定");
		} 
		else {
			String errorUrl = RequestUtil.getErrorUrl(request);
		    String ip =RequestUtil.getIpAddr(request);
			try {
				processRunService.nextProcess(taskCmd);
				resultMessage = new ResultMessage(ResultMessage.Success,"任务成功完成!");
			} 
			catch(ActivitiVarNotFoundException ex){
				resultMessage = new ResultMessage(ResultMessage.Fail,"请检查变量是否存在:" + ex.getMessage() );
				//添加错误消息到日志
				sysErrorLogService.addError(curUser.getAccount(), ip, ex.getMessage(), errorUrl);
			}
			catch(ActivitiInclusiveGateWayException ex){
				resultMessage = new ResultMessage(ResultMessage.Fail,ex.getMessage());
				//添加错误消息到日志
				sysErrorLogService.addError(curUser.getAccount(), ip, ex.getMessage(), errorUrl);
			}
			catch (Exception ex) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					resultMessage = new ResultMessage(ResultMessage.Fail, str);
					//添加错误消息到日志
					sysErrorLogService.addError(curUser.getAccount(), ip, str, errorUrl);
				} 
				else {
					String message = ExceptionUtil.getExceptionMessage(ex);
					resultMessage = new ResultMessage(ResultMessage.Fail,message);
					//添加错误消息到日志
					sysErrorLogService.addError(curUser.getAccount(), ip, message, errorUrl);
				}
			}
		}
		out.print(resultMessage);
	}
	
	
	
	/**
	 * 锁定任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("claim")
	@Action(description = "锁定任务")
	public void claim(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskId = RequestUtil.getString(request, "taskId");
		//int isAgent = RequestUtil.getInt(request, "isAgent");
		String preUrl = RequestUtil.getPrePage(request);
		String assignee = ContextUtil.getCurrentUserId().toString();
		// 代理任务，则设置锁定的assignee为代理人
		
		try {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			Long runId = processRun.getRunId();
			taskService.claim(taskId, assignee);
			String memo = "流程:" + processRun.getSubject() + ",锁定任务，节点【"
					+ taskEntity.getName() + "】";
			bpmRunLogService.addRunLog(runId, BpmRunLog.OPERATOR_TYPE_LOCK,
					memo);
			saveSuccessResultMessage(request.getSession(), "成功锁定任务!");
		} catch (Exception ex) {
			saveSuccessResultMessage(request.getSession(), "任务已经完成或被其他用户锁定!");
		}
		response.sendRedirect(preUrl);
	}

	/**
	 * 解锁任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("unlock")
	@Action(description = "解锁任务")
	public ModelAndView unlock(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");

		if (StringUtils.isNotEmpty(taskId)) {
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			Long runId = processRun.getRunId();
			bpmService.updateTaskAssigneeNull(taskId);
			String memo = "流程:" + processRun.getSubject() + ",解锁任务，节点【"
					+ taskEntity.getName() + "】";
			bpmRunLogService.addRunLog(runId, BpmRunLog.OPERATOR_TYPE_UNLOCK,
					memo);
			saveSuccessResultMessage(request.getSession(), "任务已经成功解锁!");
		}
		return new ModelAndView("redirect:forMe.ht");
	}

	/**
	 * 任务跳转窗口显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("freeJump")
	public ModelAndView freeJump(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String taskId = RequestUtil.getString(request, "taskId");
		// 获取当前节点的选择器限定配置
		ExecutionEntity execution = bpmService.getExecutionByTaskId(taskId);
		String superExecutionId = execution.getSuperExecutionId();
		String parentActDefId = "";
		if (StringUtil.isNotEmpty(superExecutionId)) {
			ExecutionEntity supExecution = bpmService.getExecution(superExecutionId);
			parentActDefId = supExecution.getProcessDefinitionId();
		}
		String nodeId = execution.getActivityId();
		String processDefinitionId = execution.getProcessDefinitionId();
		BpmNodeSet bpmNodeSet = bpmNodeSetService.getScopeByNodeIdAndActDefId(nodeId, processDefinitionId, parentActDefId);
		String scope = bpmNodeSet.getScope();

		Map<String, Map<String, String>> jumpNodesMap = bpmService.getJumpNodes(taskId);
		ModelAndView view = this.getAutoView();
		view.addObject("jumpNodeMap", jumpNodesMap).addObject("scope", scope);
		return view;
	}
	
	/**
	 * 动态创建任务加载显示页
	 * @param request
	 * @param response
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dynamicCreate")
	public ModelAndView dynamicCreate(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String taskId=request.getParameter("taskId");
		TaskEntity task=bpmService.getTask(taskId);
		return this.getAutoView().addObject("task",task);
	}

	/**
	 * 获取某个流程实例上某个节点的配置执行人员
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTaskUsers")
	@ResponseBody
	public List<TaskExecutor> getTaskUsers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 任务Id
		String taskId = request.getParameter("taskId");

		TaskEntity taskEntity = bpmService.getTask(taskId);
		
		String nodeId = RequestUtil.getString(request, "nodeId");  // 所选择的节点Id
		
		if(StringUtil.isEmpty(nodeId)){
			 nodeId = taskEntity.getTaskDefinitionKey();  // 目标节点Id
		}
		
		String actDefId=taskEntity.getProcessDefinitionId();
		
		String actInstId=taskEntity.getProcessInstanceId();
		
		Map<String, Object> vars = runtimeService.getVariables(taskEntity.getExecutionId());
		
		String startUserId=vars.get(BpmConst.StartUser).toString();
		
		@SuppressWarnings("unchecked")
		List<TaskExecutor> taskExecutorList =bpmNodeUserService.getExeUserIds(actDefId, actInstId, nodeId, startUserId, "", vars);
		
		return taskExecutorList;
	}

	/**
	 * 指派任务所属人
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@Action(description = "任务指派所属人",
			detail="<#if StringUtils.isNotEmpty(taskIds)>" +
						"流程" +
						"<#list StringUtils.split(taskIds,\",\") as item>" +
							"【${SysAuditLinkService.getProcessRunLink(item)}】" +
						"</#list>" +
						"的任务指派给【${SysAuditLinkService.getSysUserLink(Long.valueOf(userId))}】" +
				   "</#if>"
	)
	@RequestMapping("assign")
	public ModelAndView assign(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskIds = request.getParameter("taskIds");
		String userId = request.getParameter("userId");

		if (StringUtils.isNotEmpty(taskIds)) {
			String[] tIds = taskIds.split("[,]");
			if (tIds != null) {
				for (String tId : tIds) {
					bpmService.assignTask(tId, userId);
				}
			}
		}
		saveSuccessResultMessage(request.getSession(), "成功为指定任务任务分配执行人员!");
		return new ModelAndView("redirect:list.ht");
	}

	/**
	 * 任务交办设置任务的执行人。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delegate")
	@Action(description ="任务交办",
			detail="<#if StringUtils.isNotEmpty(taskId) && StringUtil.isNotEmpty(userId)>" +
						"交办流程【${SysAuditLinkService.getProcessRunLink(taskId)}】的任务【${taskName}】" +
						"给用户【${SysAuditLinkService.getSysUserLink(Long.valueOf(userId))}】" +
				   "</#if>"
	)
	public void delegate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		String taskId = request.getParameter("taskId");
		String userId = request.getParameter("userId");
//		String delegateDesc = request.getParameter("memo");
		ResultMessage message = null;
		//TODO ZYG 任务交办
		if (StringUtils.isNotEmpty(taskId) && StringUtil.isNotEmpty(userId)) {
			SysAuditThreadLocalHolder.putParamerter("taskName", bpmService.getTask(taskId).getName());
//			processRunService.delegate(taskId, userId, delegateDesc);
			message = new ResultMessage(ResultMessage.Success, "任务交办成功!");
		} else {
			message = new ResultMessage(ResultMessage.Fail, "没有传入必要的参数");
		}
		writer.print(message);
	}
	
	@RequestMapping("changeAssignee")
	@Action(description = "更改任务执行人")
	public ModelAndView changeAssignee(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");
		TaskEntity taskEntity = bpmService.getTask(taskId);
		SysUser curUser = ContextUtil.getCurrentUser();
		Map<String, IMessageHandler> handlersMap = ServiceUtil.getHandlerMap();

		return getAutoView().addObject("taskEntity", taskEntity).addObject("curUser", curUser).addObject("handlersMap", handlersMap);
	}
	
	

	@RequestMapping("setAssignee")
	@Action(description = "任务指派")
	public void setAssignee(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter writer = response.getWriter();
		String taskId = request.getParameter("taskId");
		String userId = request.getParameter("userId");
		
		String voteContent=RequestUtil.getString(request, "voteContent");
		String informType=RequestUtil.getString(request, "informType");
		ResultMessage message = null;
		try {
			message=processRunService.updateTaskAssignee(taskId,userId,voteContent,informType);
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, e.getMessage());
		}
		writer.print(message);
	}

	
	/**
	 * 设置任务的执行人
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setDueDate")
	@Action(description = "设置任务到期时间")
	public ModelAndView setDueDate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskIds = request.getParameter("taskIds");
		String dueDates = request.getParameter("dueDates");
		if (StringUtils.isNotEmpty(taskIds) && StringUtils.isNotEmpty(dueDates)) {
			String[] tIds = taskIds.split("[,]");
			String[] dates = dueDates.split("[,]");
			if (tIds != null) {
				for (int i = 0; i < dates.length; i++) {
					if (StringUtils.isNotEmpty(dates[i])) {
						Date dueDate = DateUtils.parseDate(dates[i],
								new String[] { "yyyy-MM-dd HH:mm:ss" });
						bpmService.setDueDate(tIds[i], dueDate);
					}
				}
			}
		}
		return new ModelAndView("redirect:list.ht");
	}

	/**
	 * 删除任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	@Action(description = "删除任务",
			execOrder=ActionExecOrder.BEFORE,
			detail="<#if StringUtils.isNotEmpty(taskId)>" +
						"<#assign entity1=bpmService.getTask(taskId)/>" +
						"用户删除了任务【${entity1.name}】,该任务属于流程【${SysAuditLinkService.getProcessRunLink(taskId)}】" +
				   "</#elseif StringUtils.isNotEmpty(id)>" +
					   "<#list StringUtils.split(id,\",\") as item>" +
						   "<#assign entity2=bpmService.getTask(item)/>" +
						   "用户删除了任务【${entity2.name}】,该任务属于流程【${SysAuditLinkService.getProcessRunLink(item)}】" +
					   "</#list>" +
				   "</#if>"
	)
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResultMessage message = null;
		try {
			String taskId = request.getParameter("taskId");
			String[] taskIds = request.getParameterValues("id");
			if (StringUtils.isNotEmpty(taskId)) {
				bpmService.deleteTask(taskId);

				TaskEntity task = bpmService.getTask(taskId);
				ProcessRun processRun = processRunService
						.getByActInstanceId(new Long(task.getProcessInstanceId()));
				String memo = "用户删除了任务[" + task.getName() + "],该任务属于["
						+ processRun.getProcessName() + "]流程。";
				bpmRunLogService.addRunLog(processRun.getRunId(),
						BpmRunLog.OPERATOR_TYPE_DELETETASK, memo);
				taskService.deleteTask(taskId);

			} else if (taskIds != null && taskIds.length != 0) {
				bpmService.deleteTasks(taskIds);
				for (int i = 0; i < taskIds.length; i++) {
					String id = taskIds[i];
					TaskEntity task = bpmService.getTask(id);
					ProcessRun processRun = processRunService
							.getByActInstanceId(new Long(task.getProcessInstanceId()));
					String memo = "用户删除了任务[" + task.getName() + "],该任务属于["
							+ processRun.getProcessName() + "]流程。";
					bpmRunLogService.addRunLog(processRun.getRunId(),
							BpmRunLog.OPERATOR_TYPE_DELETETASK, memo);
					taskService.deleteTask(id);
				}
			}
			message = new ResultMessage(ResultMessage.Success, "删除任务成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "删除任务失败");
		}
		addMessage(message, request);
		return new ModelAndView("redirect:list.ht");
	}

	/**
	 * 返回某个某个用户代理给当前用户的任务列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("forAgent")
//	public ModelAndView forAgent(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		ModelAndView mv = getAutoView();
//		Long userId = RequestUtil.getLong(request, "userId");
//		QueryFilter filter = new QueryFilter(request, "taskItem");
//		Calendar cal = Calendar.getInstance();
//		Date curTime = cal.getTime();
//		cal.add(Calendar.DATE, -1);
//		Date yesterday = cal.getTime();
//
//		filter.addFilter("curTime", curTime);
//		filter.addFilter("yesterday", yesterday);
//		List<TaskEntity> list = null;
//		SysUserAgent sysUserAgent = null;
//		// 具体人员的代理
//		if (userId != 0) {
//			sysUserAgent = sysUserAgentService.getById(userId);
//		}
//		if (sysUserAgent != null) {
//
//			// 代理设置是否过期
//			if (sysUserAgent.getStarttime() != null) {
//				int result = sysUserAgent.getStarttime().compareTo(curTime);
//				if (result > 0) {
//					list = new ArrayList<TaskEntity>();
//					mv = getAutoView().addObject("taskList", list).addObject(
//							"userId", userId);
//					return mv;
//				}
//			}
//			if (sysUserAgent.getEndtime() != null) {
//				cal.add(Calendar.DATE, -1);
//				int result = sysUserAgent.getEndtime().compareTo(yesterday);
//				if (result <= 0) {
//					list = new ArrayList<TaskEntity>();
//					mv = getAutoView().addObject("taskList", list).addObject(
//							"userId", userId);
//					return mv;
//				}
//			}
//			if (sysUserAgent.getIsall().intValue() == SysUserAgent.IS_ALL_FLAG) {// 全部代理
//				list = bpmService.getTaskByUserId(
//						sysUserAgent.getAgentuserid(), filter);
//			} else {// 部分代理
//				StringBuffer actDefId = new StringBuffer("");
//				List<String> notInBpmAgentlist = bpmAgentService
//						.getNotInByAgentId(sysUserAgent.getAgentid());
//				for (String ba : notInBpmAgentlist) {
//					actDefId.append("'").append(ba).append("',");
//				}
//				if (notInBpmAgentlist.size() > 0) {
//					actDefId.deleteCharAt(actDefId.length() - 1);
//				}
//				list = bpmService.getAgentTasks(sysUserAgent.getAgentuserid(),
//						actDefId.toString(), filter);
//			}
//		} else {
//			list = bpmService.getAllAgentTask(ContextUtil.getCurrentUserId(),
//					filter);
//		}
//		mv = getAutoView().addObject("taskList", list).addObject("userId",
//				userId);
//
//		return mv;
//	}

	/**
	 * 返回目标节点及其节点的处理人员映射列表。
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("tranTaskUserMap")
	public ModelAndView tranTaskUserMap(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int isStart = RequestUtil.getInt(request, "isStart", 0);
		String taskId = request.getParameter("taskId");
		String actDefId = request.getParameter("actDefId");

		String scope = "";
		if (StringUtil.isEmpty(taskId)) {
			List<FlowNode> firstNode = NodeCache.getFirstNode(actDefId);
			if (BeanUtils.isNotEmpty(firstNode)) {
				FlowNode flowNode = firstNode.get(0);
				String nodeId = flowNode.getNodeId();
				BpmNodeSet bpmNodeSet = bpmNodeSetService.getScopeByNodeIdAndActDefId(nodeId, actDefId, "");
				if (BeanUtils.isNotEmpty(bpmNodeSet))
					scope = bpmNodeSet.getScope();
			}
		} else {
			// 获取当前节点的选择器限定配置
			ExecutionEntity execution = null;

			TaskEntity taskEntity = bpmService.getTask(taskId);
			if (taskEntity.getDescription().equals(TaskOpinion.STATUS_TRANSTO.toString())) {// 加签任务
				execution = bpmService.getExecutionByTaskId(taskEntity.getParentTaskId());// 获取它的parentTaskId，这里是放着的加签任务生成的源任务
				

			} else {
				// 获取当前节点的选择器限定配置
				execution = bpmService.getExecutionByTaskId(taskId);
			}

			String superExecutionId = execution.getSuperExecutionId();
			String parentActDefId = "";
			if (StringUtil.isNotEmpty(superExecutionId)) {
				ExecutionEntity supExecution = bpmService.getExecution(superExecutionId);
				parentActDefId = supExecution.getProcessDefinitionId();
			}
			String nodeId = execution.getActivityId();
			String processDefinitionId = execution.getProcessDefinitionId();
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getScopeByNodeIdAndActDefId(nodeId, processDefinitionId, parentActDefId);
			if (BeanUtils.isNotEmpty(bpmNodeSet))
				scope = bpmNodeSet.getScope();
		}

		int selectPath = RequestUtil.getInt(request, "selectPath", 1);

		boolean canChoicePath = bpmService.getCanChoicePath(actDefId, taskId);

		Long startUserId = ContextUtil.getCurrentUserId();
		List<NodeTranUser> nodeTranUserList = null;
		if (isStart == 1) {
			Map<String, Object> vars = new HashMap<String, Object>();
			nodeTranUserList = bpmService.getStartNodeUserMap(actDefId,
					startUserId, vars);
		} else {
			nodeTranUserList = bpmService.getNodeTaskUserMap(taskId,
					startUserId, canChoicePath);
		}

		return getAutoView().addObject("nodeTranUserList", nodeTranUserList).addObject("selectPath", selectPath).addObject("scope", 

scope).addObject("canChoicePath", canChoicePath);
	}

	/**
	 * 结合前台任务管理列表，点击某行任务时，显示的任务简单明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("miniDetail")
	public ModelAndView miniDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");

		TaskEntity taskEntity = bpmService.getTask(taskId);

		if (taskEntity == null) {
			return new ModelAndView("/platform/bpm/taskNotExist.jsp");
		}

		// 取到任务的侯选人员
		Set<TaskExecutor> candidateUsers = taskUserService
				.getCandidateExecutors(taskId);

		ProcessRun processRun = processRunService.getByActInstanceId(new Long(taskEntity
				.getProcessInstanceId()));

		BpmDefinition definition = bpmDefinitionService
				.getByActDefId(taskEntity.getProcessDefinitionId());

		List<ProcessTask> curTaskList = bpmService.getTasks(taskEntity
				.getProcessInstanceId());

		return getAutoView().addObject("taskEntity", taskEntity)
				.addObject("processRun", processRun)
				.addObject("candidateUsers", candidateUsers)
				.addObject("processDefinition", definition)
				.addObject("curTaskList", curTaskList);
	}

	/**
	 * 准备更新任务的路径
	 * 
	 * @param request
	 * @param response
	 * @returngetTaskUsers
	 * @throws Exception
	 */
	@RequestMapping("changePath")
	public ModelAndView changePath(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId = request.getParameter("taskId");
		TaskEntity taskEntity = bpmService.getTask(taskId);
		Map<String, String> taskNodeMap = bpmService.getTaskNodes(taskEntity.getProcessDefinitionId(), taskEntity.getTaskDefinitionKey());
		Map<String, IMessageHandler> handlersMap = ServiceUtil.getHandlerMap();

		return this.getAutoView().addObject("taskEntity", taskEntity).addObject("taskNodeMap", taskNodeMap).addObject("curUser", 

ContextUtil.getCurrentUser()).addObject("handlersMap", handlersMap);
	}

	/**
	 * 保存变更路径
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveChangePath")
	@ResponseBody
	public String saveChangePath(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ProcessCmd processCmd = BpmUtil.getProcessCmd(request);
		processRunService.nextProcess(processCmd);
		saveSuccessResultMessage(request.getSession(), "更改任务执行的路径!");
		return SUCCESS;
	}

	/**
	 * 结束流程任务
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("end")
	@Action(description="结束流程任务",
    		detail="结束流程【${SysAuditLinkService.getProcessRunLink(taskId)}】的任务"
    )
	public void end(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultMessage resultMessage = null;
		try {
			String taskId = request.getParameter("taskId");
			TaskEntity taskEntity = bpmService.getTask(taskId);
			ProcessRun processRun = processRunService
					.getByActInstanceId(new Long(taskEntity.getProcessInstanceId()));
			String voteContent = "由"
					+ ContextUtil.getCurrentUser().getFullname() + "进行完成操作！";
			ProcessCmd cmd = new ProcessCmd();
			cmd.setTaskId(taskId);
			cmd.setVoteAgree((short) 0);
			cmd.setVoteContent(voteContent);
			cmd.setOnlyCompleteTask(true);
			processRunService.nextProcess(cmd);
			Long runId = processRun.getRunId();
			String memo = "结束了:" + processRun.getSubject();
			bpmRunLogService.addRunLog(runId, BpmRunLog.OPERATOR_TYPE_ENDTASK,
					memo);
			resultMessage = new ResultMessage(ResultMessage.Success,
					"成功完成了该任务!");
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, "完成任务失败:"
						+ str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
			}
		}
		response.getWriter().print(resultMessage);
	}

	/**
	 * 根据任务结束流程实例。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("endProcess")
	@Action(description="根据任务结束流程实例")
	public void endProcess(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		
		Long taskId = RequestUtil.getLong(request, "taskId");
		String memo=RequestUtil.getString(request, "memo");
		//Long curUserId=ContextUtil.getCurrentUserId();
		TaskEntity taskEnt = bpmService.getTask(taskId.toString());
		if(taskEnt==null){
			writeResultMessage(out, "此任务已经完成!", ResultMessage.Fail);
		}

		String instanceId = taskEnt.getProcessInstanceId();
		ResultMessage message = null;
		try {
			String nodeId=taskEnt.getTaskDefinitionKey();
			ProcessRun processRun = bpmService.endProcessByInstanceId(new Long(instanceId), nodeId,memo);
			memo = "结束流程:" + processRun.getSubject() +",结束原因:" + memo;
			bpmRunLogService.addRunLog(processRun.getRunId(),BpmRunLog.OPERATOR_TYPE_ENDTASK, memo);
			message = new ResultMessage(ResultMessage.Success, "结束流程实例成功!");
			writeResultMessage(out, message);
		} catch (Exception ex) {
			ex.printStackTrace();
			message = new ResultMessage(ResultMessage.Fail, ExceptionUtil.getExceptionMessage(ex));
			writeResultMessage(out, message);
		}
	}

	
	
	/**
	 * 待办事宜
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("pendingMattersList")
	public ModelAndView pendingMattersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "taskItem");
		String nodePath = RequestUtil.getString(request, "nodePath");
		if (StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath", nodePath + "%");
		List<TaskEntity> list = bpmService.getMyTasks(filter);
		Map<Integer, WarningSetting> warningSetMap = reminderService.getWaringSetMap();
		return getAutoView().addObject("taskList", list).addObject("warningSet", warningSetMap);

	}
	
	/**
	 * 批量审批任务.
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("batComplte")
	public void batComplte(HttpServletRequest request,HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		ResultMessage resultMessage = null;
		String taskIds = RequestUtil.getString(request, "taskIds");
		String opinion=RequestUtil.getString(request, "opinion");
		try {
			processRunService.nextProcessBat(taskIds, opinion);
			String message= MessageUtil.getMessage();
			resultMessage = new ResultMessage(ResultMessage.Success,message);
		} 
		catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail,message);
			}
		}
		out.print(resultMessage);
	}
	
	
	/**
	 * 检测任务是否存在。
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("isTaskExsit")
	public void isTaskExsit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Long taskId = RequestUtil.getLong(request, "taskId");
		PrintWriter out=response.getWriter();
		TaskEntity taskEnt = bpmService.getTask(taskId.toString());
		if(taskEnt==null){
			writeResultMessage(out, "此任务已经完成!", ResultMessage.Fail);
		}
		else{
			writeResultMessage(out, "任务存在!", ResultMessage.Success);
		}
	}
	
	@RequestMapping("dialog")
	@Action(description = "编辑流程抄送转发")
	public ModelAndView forward(HttpServletRequest request) throws Exception {
		Map<String, IMessageHandler> handlersMap = ServiceUtil.getHandlerMap();
		return getAutoView().addObject("handlersMap", handlersMap);

	}
	
	@RequestMapping("toStartCommunicate")
	@Action(description = "编辑流程抄送转发")
	public ModelAndView toStartCommunicate(HttpServletRequest request) throws Exception {
		Map<String, IMessageHandler> handlersMap = ServiceUtil.getHandlerMap();
		return getAutoView().addObject("handlersMap", handlersMap);

	}

	@RequestMapping("toTransTo")
	@Action(description = "编辑流程抄送转发")
	public ModelAndView toTransTo(HttpServletRequest request) throws Exception {
		Map<String, IMessageHandler> handlersMap = ServiceUtil.getHandlerMap();
		return getAutoView().addObject("handlersMap", handlersMap);

	}

	@RequestMapping("transToOpinionDialog")
	@Action(description = "编辑流程抄送转发")
	public ModelAndView transToOpinionDialog(HttpServletRequest request) throws Exception {
		Map<String, IMessageHandler> handlersMap = ServiceUtil.getHandlerMap();
		return getAutoView().addObject("handlersMap", handlersMap);

	}

	@RequestMapping("selExecutors")
	@Action(description = "启动流程时可以配置下一个执行人获取第一个节点的配置")
	public ModelAndView selExecutors(HttpServletRequest request) throws Exception {
		String actDefId = RequestUtil.getString(request, "actDefId");
		List<FlowNode> firstNode = NodeCache.getFirstNode(actDefId);
		String scope = "";
		if (BeanUtils.isNotEmpty(firstNode)) {
			FlowNode flowNode = firstNode.get(0);
			String nodeId = flowNode.getNodeId();
			BpmNodeSet bpmNodeSet = bpmNodeSetService.getScopeByNodeIdAndActDefId(nodeId, actDefId, "");
			if (BeanUtils.isNotEmpty(bpmNodeSet))
				scope = bpmNodeSet.getScope();
		}
		return getAutoView().addObject("scope", scope);
	}

	@RequestMapping("opDialog")
	@Action(description = "填写意见")
	public ModelAndView opDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String isRequired = RequestUtil.getString(request, "isRequired");
		String actDefId = RequestUtil.getString(request, "actDefId");
		BpmDefinition bpmDefinition = bpmDefinitionService.getByActDefId(actDefId);
		// 获取常用语
		List<String> taskAppItems = taskAppItemService.getApprovalByDefKeyAndTypeId(bpmDefinition.getDefKey(), bpmDefinition.getTypeId());
		return getAutoView().addObject("isRequired", isRequired).addObject("taskAppItems", taskAppItems);
	}
}