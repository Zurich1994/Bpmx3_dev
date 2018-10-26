package com.hotent.platform.controller.bpm;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.Markovchain.model.Markovchain.Markovchain;
import com.hotent.Markovchain.model.Markovchain.NodeSet;
import com.hotent.Markovchain.model.Markovchain.SequenceFlow;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.bpm.graph.ShapeMeta;
import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.bpm.util.BpmUtil;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.encrypt.Base64;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.model.TaskExecutor;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ClassLoadUtil;
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
import com.hotent.platform.model.bpm.BpmFormRun;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.BpmRunLog;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskExeStatus;
import com.hotent.platform.model.bpm.TaskNodeStatus;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.BpmPrintTemplate;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysProperty;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.bpm.BpmDefAuthorizeService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.bpm.BpmProCopytoService;
import com.hotent.platform.service.bpm.BpmRunLogService;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskMessageService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.bpm.TaskUserService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.form.BpmPrintTemplateService;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.TaskReadService;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.system.SysPropertyService;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.service.system.SysUserOrgService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 对象功能:流程实例扩展控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-03 09:33:06
 */
@Controller
@RequestMapping("/platform/bpm/processRun/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class ProcessRunController extends BaseController
{
	protected Logger log = LoggerFactory.getLogger(ProcessRunService.class);
	
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private BpmService bpmService;
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private TaskService taskService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private SysTemplateService sysTemplateService;
	@Resource
	private HistoryService historyService;
	@Resource
	private TaskReadService taskReadService;
	@Resource
	private TaskUserService taskUserService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmProCopytoService bpmProCopytoService;
	@Resource
	private BpmRunLogService bpmRunLogService;
	@Resource
	private BpmFormRunService bpmFormRunService;
	@Resource
	private BpmPrintTemplateService bpmPrintTemplateService;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private TaskMessageService taskMessageService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	Map<String, IMessageHandler> handlersMap;
	@Resource
	private BpmDefAuthorizeService bpmDefAuthorizeService;
	@Resource
	private SysPropertyService sysPropertyService;
	/**
	 * 取得流程实例扩展分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("list")
	@Action(description = "查看流程实例扩展分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "processRunItem");
		// 过滤掉草稿实例
		filter.addFilter("exceptStatus", 4);
		// 过滤流程定义状态为"禁用实例" 的流程实例
		filter.addFilter("exceptDefStatus", 3);

		// 增加按新的流程分管授权中任务类型的权限获取流程的任务
		Long userId = ContextUtil.getCurrentUserId();
		String isNeedRight = "";
		Map<String, AuthorizeRight> authorizeRightMap = null;
		if (!SysUser.isSuperAdmin()) {
			isNeedRight = "yes";
			//获得流程分管授权与用户相关的信息
			Map<String,Object> actRightMap = bpmDefAuthorizeService.getActRightByUserMap(userId, BPMDEFAUTHORIZE_RIGHT_TYPE.INSTANCE,true,false);
			//获得流程分管授权与用户相关的信息集合的流程KEY
			String actRights = (String) actRightMap.get("authorizeIds");
			filter.addFilter("actRights", actRights);
			//获得流程分管授权与用户相关的信息集合的流程权限内容
			authorizeRightMap = (Map<String,AuthorizeRight>) actRightMap.get("authorizeRightMap");
		}
		filter.addFilter("isNeedRight", isNeedRight);		
		
		List<ProcessRun> list=processRunService.getAll(filter);
		
		//把前面获得的流程分管授权的权限内容设置到流程管理列表
		if(authorizeRightMap != null){
			for (ProcessRun processRun : list){
				processRun.setAuthorizeRight(authorizeRightMap.get(processRun.getFlowKey()));
			}
		}else{
			//如果需要所有权限的就直接虚拟一个有处理权限的对象
			AuthorizeRight authorizeRight = new AuthorizeRight();
			authorizeRight.setRightByAuthorizeType("Y", BPMDEFAUTHORIZE_RIGHT_TYPE.INSTANCE);
			for (ProcessRun processRun : list){
				processRun.setAuthorizeRight(authorizeRight);
			}
		}
		
		ModelAndView mv=this.getAutoView().addObject("processRunList",list);
		return mv;
	}
	
	
	/**
	 * 检查是否是超级管理员
	 * @return 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	private boolean checkSuperAdmin(){
		Collection<GrantedAuthority> auths= (Collection<GrantedAuthority>) ContextUtil.getCurrentUser().getAuthorities();
		//是否是超级管理员
		if(auths!=null&&auths.size()>0&&auths.contains(SystemConst.ROLE_GRANT_SUPER)){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 是否允许撤销
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("checkRecover")
	public void checkRecover(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		Long runId = RequestUtil.getLong(request, "runId");		
		//是否驳回到发起人
		ResultMessage result=processRunService.checkRecover(runId);
		writeResultMessage(response.getWriter(), result);
	}
	
	/**
	 * 是否允许撤销
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("checkRedo")
	public void checkRedo(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		Long runId = RequestUtil.getLong(request, "runId");
		//子流程是否撤销由父流程决定
		// ProcessRun processRun=	processRunService.getById(runId);		
		 ResultMessage result;
	     result=processRunService.checkRecoverByStart(runId);		 
		writeResultMessage(response.getWriter(), result);
	}
	
	@RequestMapping("monitor")
	@Action(description = "流程监控")
	public ModelAndView monitor(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "processRunItem");
		filter.addFilter("curUser", ContextUtil.getCurrentUserId());
		List<ProcessRun> list = processRunService.getMonitor(filter);
		return  this.getAutoView()
			        .addObject("processRunList", list);
	}
	
	/**
	 * 显示撤销对话框。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("recoverDialog")
	public ModelAndView recoverDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long runId=RequestUtil.getLong(request,"runId",0);
		int backToStart=RequestUtil.getInt(request,"backToStart",0);
		ModelAndView mv=getAutoView();
		mv.addObject("runId", runId);
		Map<String, IMessageHandler> handlersMap=ServiceUtil.getHandlerMap();
		mv.addObject("backToStart", backToStart)
			.addObject("handlersMap",handlersMap);
		
		
		return mv;
	}
	
	/**
	 * 显示追回对话框。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("redoDialog")
	public ModelAndView redoDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long runId=RequestUtil.getLong(request,"runId",0);
		int backToStart=RequestUtil.getInt(request,"backToStart",0);
		ModelAndView mv=getAutoView();
		mv.addObject("runId", runId);
		Map<String, IMessageHandler> handlersMap=ServiceUtil.getHandlerMap();
		mv.addObject("backToStart", backToStart).addObject("handlersMap", handlersMap);

		return mv;
	}

	/**
	 * 任务追回,检查当前正在运行的任务是否允许进行追回。
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
	public void recover(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		Long runId = RequestUtil.getLong(request, "runId");
		String informType = RequestUtil.getStringValues(request, "informType");
		String memo = RequestUtil.getString(request, "opinion");
		int backToStart = RequestUtil.getInt(request, "backToStart");
		ResultMessage resultMessage=null;
		try{
			if(backToStart==1){
				//撤销
				resultMessage=processRunService.recover(runId,informType,  memo);
			}
			else{
				//追回
				resultMessage=processRunService.redo(runId,informType,  memo);
			}
		}
		catch(Exception ex){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail, str);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail,message);
			}
		}
		writeResultMessage(out, resultMessage);
	}
	

		
	
	/**
	 * 查看流程实例历史列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("history")
	@Action(description="查看流程实例历史列表")
	public ModelAndView history(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter queryFilter=new QueryFilter(request,"processRunItem");
		List<ProcessRun> list=processRunService.getAllHistory(queryFilter);
		ModelAndView mv=this.getAutoView().addObject("processRunList",list);
		return mv;
	}
	
	/**
	 * 取得流程实例扩展分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myStart")
	@Action(description="查看我发起的流程列表")
	public ModelAndView myStart(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter filter=new QueryFilter(request,"processRunItem");
		filter.addFilter("creatorId",ContextUtil.getCurrentUserId());
		List<ProcessRun> list=processRunService.getAll(filter);
		
		if (BeanUtils.isNotEmpty(list)) {
			for (ProcessRun processRun : list) {
				BpmDefinition bpmDefinition = bpmDefinitionService
						.getByActDefId(processRun.getActDefId());
				if (bpmDefinition.getIsPrintForm() == 1) {
					processRun.setIsPrintForm(bpmDefinition.getIsPrintForm());
				}
			}
		}
		
		ModelAndView mv=this.getAutoView().addObject("processRunList",list);
		
		return mv;
	}
	
	/**
	 * 催促执行人、所属人（优先催促执行人，没有执行人就催促所属人）
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("urgeOwner")
	@Action(description="打开催办界面")
	public ModelAndView urgeOwner(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String actInstId=RequestUtil.getString(request, "actInstId");
		String inner="";
		String mail="";
		String shortmsg="";
		ProcessRun processRun = processRunService.getByActInstanceId(new Long(actInstId));
		
		SysTemplate temp = sysTemplateService.getDefaultByUseType(SysTemplate.USE_TYPE_URGE);
		if(BeanUtils.isNotEmpty(temp)){
			inner=temp.getHtmlContent();
			mail=temp.getHtmlContent();
			shortmsg=temp.getPlainContent();
		}
		ModelAndView mv = this.getAutoView().addObject("processSubject", processRun.getSubject()).addObject("actInstId", actInstId).addObject("inner", inner).addObject("mail", mail).addObject("shortMsg", shortmsg);
		return mv;
	}
	
	/**
	 * 执行催办动作
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("urgeSubmit")
	@Action(description = "执行催办")
	public void urgeSubmit(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			String instanceId = RequestUtil.getString(request, "actInstId");
			String messgeType = RequestUtil.getStringAry(request, "messgeType");
			ProcessRun processRun=processRunService.getByActInstanceId(Long.parseLong(instanceId));
			String parentActDefId = processRunService.getParentProcessRunActDefId(processRun);
			String subject = RequestUtil.getString(request, "subject");
			String processSubject = RequestUtil.getString(request,"processSubject");
			String opinion = RequestUtil.getString(request, "opinion");
			BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(processRun.getActDefId());
			if(BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition.getStatus())){
				response.getWriter().print( new ResultMessage(ResultMessage.Fail, "对不起,此流程定义已禁用实例!"));
				return;
			}
			Boolean userProcessSubject = RequestUtil.getBoolean(request,"userProcessSubject");
			if (userProcessSubject || StringUtils.isEmpty(subject))
				subject = processSubject;
			Map<String, String> map =sysTemplateService.getTempByFun(SysTemplate.USE_TYPE_URGE);
	
			List<Task> taskList = taskService.createTaskQuery().processInstanceId(instanceId).list();
			taskMessageService.notify(taskList, messgeType, subject, map, opinion, parentActDefId);
			writeResultMessage(response.getWriter(), "催办信息已发送成功!",ResultMessage.Success);
		} catch (Exception ex) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				String msg = "催办信息已发送失败";
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, msg+":" + str);
				response.getWriter().print(resultMessage);
			}
			else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
	/**
	 * 查看我参与审批流程列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myAttend")
	@Action(description="查看我参与审批流程列表")
	public ModelAndView myAttend(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		QueryFilter filter=new QueryFilter(request,"processRunItem");
		filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
		List<ProcessRun> list=processRunService.getMyAttend(filter);
		for (ProcessRun processRun : list) {
			if(processRun.getStatus().shortValue() != ProcessRun.STATUS_FINISH.shortValue()){
				//1.查找当前用户是否有最新审批的任务
				TaskOpinion taskOpinion=taskOpinionService.getLatestUserOpinion(processRun.getActInstId(), ContextUtil.getCurrentUserId());
				if(BeanUtils.isNotEmpty(taskOpinion))
					processRun.setRecover(ProcessRun.RECOVER);
			}
		}
		ModelAndView mv=this.getAutoView().addObject("processRunList",list);
		return mv;
	}
	
	/**
	 * 删除流程实例扩展
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除流程实例扩展",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除流程实例" +
					"<#list StringUtils.split(runId,\",\") as item>" +
					"<#assign entity=processRunService.getById(Long.valueOf(item))/>" +
						"【${entity.subject}】" +
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ResultMessage message=null;
		String preUrl= RequestUtil.getPrePage(request);
		try {
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "runId");
			processRunService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除流程实例成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除流程实例失败");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 异步删除流程实例扩展
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delAjax")
	@ResponseBody
	@Action(description = "删除流程实例扩展", execOrder = ActionExecOrder.BEFORE, detail = "删除流程实例" + "<#list StringUtils.split(runId,\",\") as item>" + "<#assign entity=processRunService.getById(Long.valueOf(item))/>" + "【${entity.subject}】" + "</#list>")
	public ResultMessage delAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "runId");
			processRunService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除流程实例成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "删除流程实例失败");
		}
		return message;
	}

	/**
	 * 编辑流程实例
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑流程实例扩展")
	public ModelAndView edit(HttpServletRequest request) throws Exception{
		Long runId=RequestUtil.getLong(request,"runId");
		String returnUrl=RequestUtil.getPrePage(request);
		ProcessRun processRun=null;
		if(runId!=0){
			 processRun= processRunService.getById(runId);
		}else{
			processRun=new ProcessRun();
		}
		return getAutoView().addObject("processRun",processRun).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得流程实例扩展明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看流程实例扩展明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long runId=RequestUtil.getLong(request,"runId",0L);
		String taskId=RequestUtil.getString(request, "taskId");
		String preUrl=RequestUtil.getPrePage(request);
		ProcessRun processRun=null;
		//String actInstId="";
		if(runId!=0L){
			processRun = processRunService.getById(runId);
		}else{
			TaskEntity task=bpmService.getTask(taskId);
			processRun=processRunService.getByActInstanceId(new Long( task.getProcessInstanceId()));
		}
	
		if (processRun == null)
			throw new Exception("实例不存在");
		List<HistoricTaskInstance> hisTasks = bpmService.getHistoryTasks(processRun.getActInstId());
		return getAutoView().addObject("processRun", processRun)
				.addObject("isReturn", request.getParameter("isReturn"))
				.addObject("hisTasks", hisTasks).addObject("returnUrl", preUrl);
	}
	
	/**
	 * 取得流程实例扩展明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@Action(description="查看流程实例扩展明细")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long runId=RequestUtil.getLong(request,"runId",0L);
		String preUrl=RequestUtil.getPrePage(request);
		ProcessRun processRun=null;
		//String actInstId="";
		if(runId!=0L){
			processRun = processRunService.getById(runId);
		}
		
		//List<HistoricTaskInstance> hisTasks=bpmService.getHistoryTasks(processRun.getActInstId());
		return getAutoView().addObject("processRun", processRun)
							.addObject("returnUrl", preUrl);
	}
	
	
	/**
	 * 任务办理页面的 流程示意图对话框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("processImage")
	public ModelAndView processImage(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		ModelAndView modelAndView = getAutoView();
		
		String action=request.getParameter("action");
		String runId=request.getParameter("runId");
		String actInstanceId=null;
		ProcessRun processRun=null;
		if(StringUtils.isNotEmpty(runId)){
			processRun = processRunService.getById(new Long(runId));
			actInstanceId=processRun.getActInstId();
		}else {
			actInstanceId = request.getParameter("actInstId");
			processRun = processRunService.getByActInstanceId(new Long(actInstanceId));
			
		}
		String defXml  = bpmService.getDefXmlByProcessDefinitionId(processRun.getActDefId());
		System.out.println("defXML:          "+defXml);
		ExecutionEntity executionEntity = bpmService.getExecution(actInstanceId);
		
		if (executionEntity != null && executionEntity.getSuperExecutionId() != null) {
			ExecutionEntity superExecutionEntity = bpmService.getExecution(executionEntity.getSuperExecutionId());
			modelAndView.addObject("superInstanceId",superExecutionEntity.getProcessInstanceId());
		}
		

		ShapeMeta shapeMeta = BpmUtil.transGraph(defXml);
		modelAndView.addObject("notShowTopBar", request.getParameter("notShowTopBar"))
		.addObject("defXml", defXml)
		.addObject("processInstanceId", actInstanceId)
		.addObject("shapeMeta", shapeMeta).addObject("processRun", processRun).addObject("action", action);
		System.out.println("processRun   :"+processRun);
		System.out.println("actInstanceId   :"+actInstanceId);
		System.out.println("shapeMeta   :"+shapeMeta);
		System.out.println("action   :"+action);
		System.out.println("request.getParameter('notShowTopBar')   :"+request.getParameter("notShowTopBar"));
		return modelAndView;
	
	}
	
	
	/**
	 * 查看子流程。
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("subFlowImage")
	public ModelAndView subFlowImage(HttpServletRequest request,HttpServletResponse response)throws Exception{
		String subProcessDefinitionId=null;
		List<String> subProcessInstanceId=new ArrayList<String>();
		String subDefXml=null;
		String actInstanceId=request.getParameter("processInstanceId");
		String processDefinitionId=request.getParameter("processDefinitionId");
		String nodeId=request.getParameter("nodeId");
		//子流程是否已经运行 0-未运行，1-已运行
		int subProcessRun=0;
		//取得子外部子流程的key.
		String subFlowKey = null;
		String actDefId = null;
		if(StringUtil.isNotEmpty(actInstanceId)){
			actDefId = processRunService.getByActInstanceId(new Long(actInstanceId)).getActDefId();
		}else if(StringUtil.isNotEmpty(processDefinitionId)){
			actDefId = processDefinitionId;
		}
		
		
		Map<String, FlowNode> flowNodeMap = NodeCache.getByActDefId(actDefId);
		Iterator<Entry<String, FlowNode>> entrySet = flowNodeMap.entrySet().iterator();
		while(entrySet.hasNext()){
			Entry<String,FlowNode> entry=entrySet.next();
			String flowNodeId=entry.getKey();
			if(flowNodeId.equals(nodeId)){
				FlowNode flowNode=entry.getValue();
				subFlowKey=flowNode.getAttribute("subFlowKey");
				break;
			}
		}
		//取得外部子流程的定义
		BpmDefinition subBpmDefinition = bpmDefinitionService.getMainDefByActDefKey(subFlowKey);
		if (subBpmDefinition.getActDeployId() != null) {
			subDefXml = bpmService.getDefXmlByDeployId(subBpmDefinition
					.getActDeployId().toString());
		} else {
			subDefXml = BpmUtil
					.transform(subBpmDefinition.getDefKey(),
							subBpmDefinition.getSubject(),
							subBpmDefinition.getDefXml());
		}
		
		//取得所有的子流程实例
		List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().superProcessInstanceId(actInstanceId).list();
		if(BeanUtils.isNotEmpty(historicProcessInstances)){
			//筛选当选节点的子流程
			for(HistoricProcessInstance instance:historicProcessInstances){
				String procDefId = instance.getProcessDefinitionId();
				BpmDefinition bpmDef = bpmDefinitionService.getByActDefId(procDefId);
				if(bpmDef.getDefKey().equals(subFlowKey)){
					subProcessInstanceId.add(instance.getId());
					subProcessRun=1;
				}
			}
		}
		if(subProcessRun==0){
			subProcessDefinitionId=subBpmDefinition.getActDefId();
		}
		
		ShapeMeta subShapeMeta=BpmUtil.transGraph(subDefXml);
		ModelAndView modelAndView=getAutoView();		
		modelAndView.addObject("defXml", subDefXml);
		modelAndView.addObject("subProcessRun",subProcessRun);
		if(subProcessRun==0){
			modelAndView.addObject("processDefinitionId", subProcessDefinitionId);
		}else{
			modelAndView.addObject("processInstanceIds", subProcessInstanceId);
		}
		modelAndView.addObject("shapeMeta", subShapeMeta);
		return modelAndView;
		
	}
	
	/**
	 * 根据流程实例id获取流程的状态。
	 * @param request
	 * @return
	 */
	@RequestMapping("getFlowStatusByInstanceId")
	@ResponseBody
	public List<TaskNodeStatus> getFlowStatusByInstanceId(HttpServletRequest request){
		String instanceId=RequestUtil.getString(request, "instanceId");
		List<TaskNodeStatus> list= bpmService.getNodeCheckStatusInfo(instanceId);
		return list;
	}
	
	
	/**
	 * 显示流程实例任务中某个任务节点上的执行人员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/**
	 * 显示流程实例任务中某个任务节点上的执行人员。
	 * <pre>
	 * 根据节点ID和流程实例ID获取节点的执行人。
	 * </pre>
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("taskUser")
	public ModelAndView taskUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 当前用户执行人员ID
		Set<TaskExecutor> assignees = new HashSet<TaskExecutor>();
		// 当前节点的候选执行人员ID
		Set<TaskExecutor> candidateUsers = new HashSet<TaskExecutor>();

		String nodeId = request.getParameter("nodeId");
		String processInstanceId = request.getParameter("processInstanceId");

		ModelAndView modelAndView = getAutoView();
		modelAndView.addObject("assignees", assignees).addObject("candidateUsers", candidateUsers);
		boolean found = false;

		// 1.检查该流程实例中的当前任务列表，若nodeId对应的任务存在，即直接从该任务里获取指定的人员或候选用户
		List<ProcessTask> taskList = bpmService.getTasks(processInstanceId);
		// nodeId对应的任务节点是否有任务实例
		for (ProcessTask task : taskList) {
			if (task.getTaskDefinitionKey().equals(nodeId)) {
				found = true;
				if (ServiceUtil.isAssigneeNotEmpty(task.getAssignee())) {// 存在指定的用户
					Long assignee = Long.parseLong(task.getAssignee());
					SysUser sysUser = sysUserService.getById(assignee);
					assignees.add(TaskExecutor.getTaskUser(task.getAssignee(),sysUser.getFullname()));
				} else {// 获取该任务的候选用户列表
					Set<TaskExecutor> cUIds = taskUserService.getCandidateExecutors(task.getId());
					for (TaskExecutor taskExecutor : cUIds) {
						candidateUsers.add(taskExecutor);
					}
				}
			}
		}
		if (found) return modelAndView;
		// 2.检查审批历史表里是否存在该节点的审核人
		List<TaskOpinion> list = taskOpinionService.getByActInstIdTaskKey(new Long( processInstanceId), nodeId);
		for (TaskOpinion taskOpinion : list) {
			if (taskOpinion.getExeUserId() != null) {
				assignees.add(TaskExecutor.getTaskUser(taskOpinion.getExeUserId().toString(), taskOpinion.getExeFullname()));
			}
		}
		return modelAndView;
	}
	
	/**
	 * 返回流程表单列表包括全局表单
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getForm")
public ModelAndView getForm(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Long runId=RequestUtil.getLong(request, "runId",0L);
		String ctxPath=request.getContextPath();
		//Long currUserId=ContextUtil.getCurrentUserId();
//		Map<String,Object> formMap=processRunService.getFormByRunId(runId, ctxPath);
		String form = processRunService.getFormDetailByRunId(runId, ctxPath);
		ProcessRun processRun=processRunService.getById(runId);
		boolean isExtForm = false;
		if(processRun.getFormDefId()==0L&&StringUtil.isNotEmpty(processRun.getBusinessUrl())){
			isExtForm=true;
		}

		ModelAndView view = this.getAutoView().addObject("processRun", processRun).addObject("isFormEmpty", StringUtil.isEmpty(form)).addObject("isExtForm", isExtForm).addObject("form", form);
		return view;
	}
	
	/**
	 * 我的流程草稿列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myForm")
	@Action(description="我的流程草稿列表")
	public ModelAndView myForm(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		Long userId=ContextUtil.getCurrentUserId();
		QueryFilter filter=new QueryFilter(request,"processRunItem");
		filter.addFilter("userId",userId);
		List<ProcessRun> list=processRunService.getMyDraft(filter);
		ModelAndView mv=this.getAutoView().addObject("processRunList",list);
		return mv;
	}
	
	/**
	 * 删除流程草稿同时删除业务数据
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delDraft")
	@Action(description="删除流程草稿",execOrder=ActionExecOrder.BEFORE,
			detail="删除流程草稿" +
					"<#list StringUtils.split(runId,\",\") as item>" +
					"<#assign entity=processRunService.getById(Long.valueOf(item))/>" +
						"【${entity.subject}】" +
					"</#list>")
	public void delDraft(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ResultMessage message=null;
		Long [] runIdAry=RequestUtil.getLongAryByStr(request, "runId");
		String preUrl=RequestUtil.getPrePage(request); 
		try {
			for (long runId : runIdAry) {
				ProcessRun processRun = processRunService.getById(runId);
				String dsAlias = processRun.getDsAlias();
				String tableName = processRun.getTableName();
				String businessKey = processRun.getBusinessKey();
				if (StringUtil.isNotEmpty(tableName)) {
					if (StringUtil.isEmpty(dsAlias) || DataSourceUtil.DEFAULT_DATASOURCE.equals(dsAlias)) {
						if(!tableName.startsWith(TableModel.CUSTOMER_TABLE_PREFIX)){
							tableName+=TableModel.CUSTOMER_TABLE_PREFIX;
						}
						bpmFormHandlerService.delByIdTableName(businessKey, tableName);
					} else {
						bpmFormHandlerService.delByDsAliasAndTableName(dsAlias, tableName, businessKey);
					}
				}
				bpmRunLogService.addRunLog(runId, BpmRunLog.OPERATOR_TYPE_DELETEFORM, "删除草稿");
				processRunService.delById(runId);
			}
			message=new ResultMessage(ResultMessage.Success, "删除流程草稿成功");
		} catch (Exception e) {
			e.printStackTrace();
			message=new ResultMessage(ResultMessage.Fail, "删除流程草稿失败："+e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	@RequestMapping("checkForm")
	@Action(description="检查草稿表单是否发生变化")
	@ResponseBody
	public boolean checkForm(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long runId=RequestUtil.getLong(request, "runId");
		ProcessRun processRun=processRunService.getById(runId);
		Long formDefId=processRun.getFormDefId();
		if(formDefId==0L)return false;
		BpmFormDef bpmFormDef=bpmFormDefService.getById(formDefId);
		BpmFormDef defaultFormDef=bpmFormDefService.getDefaultPublishedByFormKey(bpmFormDef.getFormKey());
		if(defaultFormDef.getFormDefId().equals(formDefId)){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 复制我的草稿
	 */
	@RequestMapping("copyDraft")
	@Action(description="复制草稿",execOrder=ActionExecOrder.AFTER,
			detail="复制草稿" +
					"<#assign entity=processRunService.getById(Long.valueOf(runId))/>" +
					"【${entity.subject}】" +
					"<#if isSuccess>成功<#else>失败</#if>")
	public void copyDraft(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long runId=RequestUtil.getLong(request, "runId");
		boolean isSuccess=true;
		try {
			processRunService.copyDraft(runId);
			writeResultMessage(response.getWriter(), new ResultMessage(
					ResultMessage.Success, "复制草稿成功！"));
			isSuccess=true;
		}catch (Exception e) {
			isSuccess=false;
			e.printStackTrace();
			writeResultMessage(response.getWriter(), new ResultMessage(
					ResultMessage.Fail, "复制草稿失败！"+e.getMessage()));
		}
		SysAuditThreadLocalHolder.putParamerter("isSuccess", isSuccess);
	}
	
	/**
	 * 我的请求列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myRequestList")
	@Action(description = "查看我的请求列表")
	public ModelAndView myRequestList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "processRunItem");
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());
		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");
		List<ProcessRun> list = processRunService.getMyRequestList(filter);
		
		ModelAndView mv=this.getAutoView().addObject("processRunList",list);
		return mv;
	}
	
	/**
	 * 我的请求列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myRequest")
	@Action(description = "查看我的请求列表")
	public ModelAndView myRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
	      Long porIndex = RequestUtil.getLong(request,"porIndex");
	      Long tabIndex = RequestUtil.getLong(request,"tabIndex");
	      ModelAndView mv=this.getAutoView()
		        .addObject("porIndex",porIndex)
		        .addObject("tabIndex",tabIndex);
	      return mv;
	}
	
	/**
	 * 我的办结
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myCompletedList")
	@Action(description = "查看我的办结列表")
	public ModelAndView myCompletedList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "processRunItem");
		filter.addFilter("creatorId", ContextUtil.getCurrentUserId());
		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");
		List<ProcessRun> list = processRunService.getMyCompletedList(filter);
		return this.getAutoView().addObject("processRunList", list);
	}
	
	/**
	 * 查看已办事宜流程列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("alreadyMattersList")
	@Action(description = "查看已办事宜流程列表")
	public ModelAndView alreadyMattersList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "processRunItem");
		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");

		filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
		List<ProcessRun> list = processRunService.getAlreadyMattersList(filter);
		
		for (ProcessRun processRun : list) {
			if (processRun.getStatus().shortValue() != ProcessRun.STATUS_FINISH.shortValue()) {
				// 1.查找当前用户是否有最新审批的任务
				TaskOpinion taskOpinion = taskOpinionService.getLatestUserOpinion(processRun.getActInstId(),ContextUtil.getCurrentUserId());
				if (BeanUtils.isNotEmpty(taskOpinion))
					processRun.setRecover(ProcessRun.STATUS_RECOVER);
			}
		}
		
		return this.getAutoView().addObject("processRunList", list);
	}
	
	/**
	 * 查看办结事宜流程列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("completedMattersList")
	@Action(description = "查看办结事宜流程列表")
	public ModelAndView completedMattersList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "processRunItem");
		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");
		filter.addFilter("assignee", ContextUtil.getCurrentUserId().toString());
		List<ProcessRun> list = processRunService.getCompletedMattersList(filter);
		
		for (ProcessRun processRun : list) {
			BpmDefinition bpmDefinition = bpmDefinitionService
					.getByActDefId(processRun.getActDefId());
			if (BeanUtils.isNotEmpty(bpmDefinition) && bpmDefinition.getIsPrintForm() == 1) {
				processRun.setIsPrintForm(bpmDefinition.getIsPrintForm());
			}
		}
		
		ModelAndView mv = this.getAutoView().addObject("processRunList", list);
		return mv;
	}
	/**
	 * 返回流程的详细信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("info")
	public ModelAndView info(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long curUserId = ContextUtil.getCurrentUserId();
		String runId = request.getParameter("runId");
		String copyId = request.getParameter("copyId");
		String prePage = RequestUtil.getString(request, "prePage");
		String preUrl = RequestUtil.getPrePage(request);
		String ctxPath=request.getContextPath();
		ProcessRun processRun = processRunService.getById(Long.parseLong(runId));
		String form = processRunService.getFormDetailByRunId(Long.parseLong(runId), ctxPath);
		boolean isExtForm = false;
		if(processRun.getFormDefId()==0L&&StringUtil.isNotEmpty(processRun.getBusinessUrl())){
			isExtForm=true;
		}
	
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(processRun.getDefId());
		// 是否是第一个节点
		boolean isFirst = this.isFirst(processRun);
		// 是否允许办结转发
		boolean isFinishedDiver = this.isFinishedDiver(bpmDefinition, processRun);

		// 是否允许追回到发起人
		boolean isCanRecover = this.isCanRecover(processRun, isFirst, curUserId);
		// 是否允许追回
		boolean isCanRedo = this.isCanRedo(processRun, isFirst, curUserId);
		
		boolean isCopy=this.isCopy(bpmDefinition);

		if(null == prePage || "".equals(prePage)){//抄送事宜修改状态
		     try {
			  //适用于冒泡不能传copyId
			  if(null==copyId || "".equals(copyId))
			  {
			       ProcessRun run  = processRunService.getCopyIdByRunid(Long.parseLong(runId));
			       copyId = run.getCopyId().toString();
			  }
			  bpmProCopytoService.markCopyStatus(copyId);
		     } catch (Exception e) {
			  logger.info("update copy matter state failed!");
		     }
		}
		
		//是否允许打印
		boolean isPrintForm=this.isPrintForm(processRun,bpmDefinition,prePage,copyId);

	// 是否有全局流水号
		boolean hasGlobalFlowNo = SysPropertyService.getBooleanByAlias(SysProperty.GlobalFlowNo);
		return this.getAutoView()
				.addObject("bpmDefinition", bpmDefinition)
				.addObject("processRun", processRun)
				.addObject("form", form)
				.addObject("isPrintForm",isPrintForm)
				.addObject("isExtForm", isExtForm)
				.addObject("isFirst",isFirst)
				.addObject("isCanRedo",isCanRedo)
				.addObject("isCanRecover",isCanRecover)
				.addObject("isFinishedDiver",isFinishedDiver)
				.addObject("isCopy", isCopy)
				.addObject(RequestUtil.RETURNURL, preUrl)
				.addObject("isOpenDialog",RequestUtil.getInt(request, "isOpenDialog",0));
    		

	}
	
	private boolean isCopy(BpmDefinition bpmDefinition){
		Short status=bpmDefinition.getStatus();
		if(BpmDefinition.STATUS_DISABLED.equals(status))return false;
		if(BpmDefinition.STATUS_INST_DISABLED.equals(status))return false;
		return true;
	}
	
	/**
	 *是否允许办结转办
	 * @param bpmDefinition
	 * @param processRun
	 * @return
	 */
	private boolean isFinishedDiver(BpmDefinition bpmDefinition, ProcessRun processRun) {
		if(BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition.getStatus()))return false;
		if(BeanUtils.isNotEmpty(bpmDefinition.getAllowFinishedDivert()))
			return bpmDefinition.getAllowFinishedDivert().shortValue()==BpmDefinition.ALLOW.shortValue()&& processRun.getStatus().shortValue()== ProcessRun.STATUS_FINISH.shortValue();
		
		return false;
	}

	/**
	 * 是否能撤销
	 *  <pre>
	 *  	不是第一节点，是当前执行人并且是正在运行的流程 对应流程定义不能为禁用流程实例状态
	 *  </pre>
	 * @param processRun
	 * @param isFirst
	 * @param curUserId
	 * @return
	 */
	private boolean isCanRecover(ProcessRun processRun, boolean isFirst, Long curUserId) {
		String actDefId=processRun.getActDefId();
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		if(BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition.getStatus()))return false;
		return 	!isFirst&&curUserId.equals(processRun.getCreatorId())&&processRun.getStatus().shortValue()==ProcessRun.STATUS_RUNNING;
	}
	
	/**
	 * 是否可以追回
	 * <pre>
	 * 是否可以追回，有以下几个判定条件：
	 *  1、流程正在运行；
	 *  2、流程非第一个节点；
	 *  3、上一步执行人是当前用户；
	 *  4、上一步操作是同意；
	 *  5、目前该实例只有一个任务。
	 *  6、 对应流程定义不能为禁用流程实例状态
	 * </pre>
	 * @param processRun
	 * @param isFirst
	 * @param curUserId
	 * @return
	 */
	private boolean isCanRedo(ProcessRun processRun,boolean isFirst,Long curUserId){
		if(!processRun.getStatus().equals(ProcessRun.STATUS_RUNNING)||isFirst)return false;
		String actDefId=processRun.getActDefId();
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		if(BpmDefinition.STATUS_INST_DISABLED.equals(bpmDefinition.getStatus()))return false;
		String instanceId = processRun.getActInstId();
		TaskOpinion taskOpinion = taskOpinionService.getLatestUserOpinion(instanceId, curUserId);
		if(taskOpinion == null) return false;
		
		Short checkStatus = taskOpinion.getCheckStatus();
		
		if (!TaskOpinion.STATUS_AGREE.equals(checkStatus)) return false;
		
		String taskKey = taskOpinion.getTaskKey();
		FlowNode flowNode = NodeCache.getNodeByActNodeId(processRun.getActDefId(), taskKey);
		if (flowNode != null) {
			List<FlowNode> nextNodes = flowNode.getNextFlowNodes();
			List<String> nodeKeys = new ArrayList<String>();
			for (FlowNode node : nextNodes) {
				findUserNode(node, nodeKeys);
			}
			List<ProcessTask> tasks = bpmService.getTasks(instanceId);
			if (tasks.size() != 1) return false;
			
			if(nodeKeys.contains(tasks.get(0).getTaskDefinitionKey())){
				return true;
			}
		}
		return false;
	}
	
	// 递归找到用户节点
	private void findUserNode(FlowNode flowNode, List<String> nodeKeys) {
		if (flowNode.getNodeType().equals(FlowNode.TYPE_USERTASK)) {// 用户节点
			nodeKeys.add(flowNode.getNodeId());
		} else if (flowNode.getNodeType().equals("exclusiveGateway")) {
			List<FlowNode> nextNodes = flowNode.getNextFlowNodes();
			for (FlowNode node : nextNodes) {
				findUserNode(node, nodeKeys);
			}
		}
	}

	/**
	 * 是否是第一个节点
	 * @param processRun
	 * @return
	 * @throws Exception
	 */
	private boolean isFirst(ProcessRun processRun) throws Exception {
		boolean isFirst = false;
		if(BeanUtils.isEmpty(processRun))
			return isFirst;
		Long instId=Long.parseLong(processRun.getActInstId());
		String actDefId=processRun.getActDefId();
		List<TaskOpinion> taskOpinionList= taskOpinionService.getCheckOpinionByInstId(instId);
		String nodeId = "";
		//判断起始节点后是否有多个节点
		if(NodeCache.isMultipleFirstNode(actDefId)){
			nodeId = processRun.getStartNode();
		}else{
			FlowNode flowNode= NodeCache.getFirstNodeId(actDefId);
			if(flowNode==null)
				return isFirst;
			nodeId = flowNode.getNodeId();
		}
		for(TaskOpinion taskOpinion:taskOpinionList){
			isFirst = nodeId.equals(taskOpinion.getTaskKey());
			if(isFirst)break;
		}	
		return isFirst;
	}

	/**
	 * 是否允许打印表单
	 * <pre>
	 * 	1.允许打印表单
	 * 		是我的办结
	 * 	2.允许
	 * 		办结打印
	 * 	3 允许
	 * 		抄送打印
	 * 
	 * </pre>
	 * @param processRun
	 * @param bpmDefinition
	 * @param prePage
	 * @param copyId 
	 * @return
	 */
	private boolean isPrintForm(ProcessRun processRun, BpmDefinition bpmDefinition, String prePage, String copyId) {
		if(bpmDefinition.getIsPrintForm() == null 
			|| bpmDefinition.getIsPrintForm().intValue()!=1 
			|| processRun.getStatus().shortValue() != ProcessRun.STATUS_FINISH.shortValue()){
			return false;
		}else {
			return true;
		}
		//我的办结
//		if(("myComplete").equals(prePage)  && ContextUtil.getCurrentUserId().equals(processRun.getCreatorId()) )
//			return true;
//		//办结打印
//		if(("completedMatter").equals(prePage) && bpmDefinition.getAllowEndPrint().intValue() == 1 )
//			return true;
//		//抄送允许打印
//		if(("cc").equals(prePage) && bpmDefinition.getAllowCcPrint().intValue() == 1 )
//			return true;
		
	}
	
	/**
	 * 逻辑删除流程实例。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("logicDelete")
	@Action(description = "逻辑删除流程实例")
	public void logicDelete(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ResultMessage message = null;
		Long instanceId = RequestUtil.getLong(request, "instanceId");
		PrintWriter writer=response.getWriter();
		String memo = RequestUtil.getString(request, "opinion");
		if (instanceId != null && instanceId > 0) {
			try{
				ProcessRun processRun= bpmService.delProcessByInstanceId(instanceId, memo);
				Long runId=processRun.getRunId();
				String tmp = "逻辑删除了:" + processRun.getSubject() +",删除原因!";
				bpmRunLogService.addRunLog(runId,BpmRunLog.OPERATOR_TYPE_DELETEINSTANCE, tmp);
				message = new ResultMessage(ResultMessage.Success, ContextUtil.getMessages("controller.processRun.del.success"));
			}
			catch(Exception ex){
				String msg=ExceptionUtil.getExceptionMessage(ex);
				message = new ResultMessage(ResultMessage.Fail, msg);
			}
		} else {
			message = new ResultMessage(ResultMessage.Fail, ContextUtil.getMessages("controller.processRun.del.fail"));
		}
		writeResultMessage(writer, message);
	}
	
	/**
	 * update 2013-1-1 start 打印流程表单
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("printForm")
	public ModelAndView printForm(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long runId = RequestUtil.getLong(request, "runId");
		// int genDefault =RequestUtil.getInt(request, "genDefault");
		ProcessRun processRun = processRunService.getByHistoryId(runId);
		Long formId=processRun.getFormDefId();
		BpmFormDef formDef=bpmFormDefService.getById(formId);
		String formKey=formDef.getFormKey();
//		String parentActDefId = processRunService.getParentProcessRunActDefId(processRun);
//		List<BpmFormRun> bpmFormRunList = bpmFormRunService.getByInstanceId(processRun.getActInstId());
//		String formKey = "";
//		if (BeanUtils.isNotEmpty(bpmFormRunList)) {
//			for (BpmFormRun bpmFormRun : bpmFormRunList) {
//				if (BeanUtils.isNotEmpty(bpmFormRun.getFormdefKey())) {
//					formKey = bpmFormRun.getFormdefKey();
//				}
//			}
//		}
//		if (StringUtil.isNotEmpty(formKey )) {
//			String actDefId = processRun.getActDefId();
//			List<BpmNodeSet> list = null;
//			if (StringUtil.isEmpty(parentActDefId)) {
//				list = bpmNodeSetService.getByActDefId(actDefId);
//			} else {
//				list = bpmNodeSetService.getByActDefId(actDefId, parentActDefId);
//			}
//			for (BpmNodeSet bpmNodeSet : list) {
//				if (StringUtil.isNotEmpty(bpmNodeSet.getFormKey()) ) {
//					formKey = bpmNodeSet.getFormKey();
//				}
//			}
//		}
		String ctx = request.getContextPath();
		BpmPrintTemplate bpmPrintTemplate = bpmPrintTemplateService.getDefaultByFormKey(formKey);
		if (BeanUtils.isEmpty(bpmPrintTemplate)) {
//			if(genDefault==1){
				bpmPrintTemplate = bpmPrintTemplateService.getDefaultPrintTemplateByFormKey(formKey);
				Long tableId = bpmPrintTemplate.getTableId();
				BpmFormTable bpmFormTable = bpmFormTableService.getById(tableId);
				String tableName = bpmFormTable.getTableName();
				String tableComment = bpmFormTable.getTableDesc();
				String template=FormUtil.getPrintFreeMarkerTemplate(bpmPrintTemplate.getHtml(),tableName,tableComment);
				bpmPrintTemplate.setTemplate(template);
				bpmPrintTemplate.setId(UniqueIdUtil.genId());
				bpmPrintTemplate.setIsDefault((short)1);
				bpmPrintTemplateService.add(bpmPrintTemplate);
//			}
//			return getAutoView().addObject("html", "").addObject("formKey",
//					formKey);
		}
		String html = bpmPrintTemplateService.parseTempalte(bpmPrintTemplate, processRun, ctx);
		return getAutoView().addObject("html", html);
	}
	
	
	@RequestMapping("getRefList")
	@Action(description = "流程参考")
	public ModelAndView getRefList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long defId = RequestUtil.getLong(request, "defId", 0);
		Integer type = RequestUtil.getInt(request, "type", 0);
		BpmDefinition bpmDefinition = bpmDefinitionService.getById(defId);
		Long creatorId=ContextUtil.getCurrentUserId();
		Integer instanceAmount= bpmDefinition.getInstanceAmount();
		if(instanceAmount==null || instanceAmount<=0){
			instanceAmount=5;
		}

		List<ProcessRun> list = processRunService.getRefList(defId, creatorId, instanceAmount, type);
		return this.getAutoView().addObject("processRunList", list).addObject("type", type);

	}
	
	
	/**
	 * 根据流程实例id获取流程的状态。
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("getFlowStatusByInstanceIdAndNodeId")
	@ResponseBody
	public TaskNodeStatus getFlowStatusByInstanceIdAndNodeId(HttpServletRequest request) {
		String instanceId = RequestUtil.getString(request, "instanceId");
		String nodeId = RequestUtil.getString(request, "nodeId");
		TaskNodeStatus taskNodeStatus = bpmService.getNodeCheckStatusInfo(instanceId,nodeId);
		List<TaskOpinion> a=taskNodeStatus.getTaskOpinionList();		
		if(BeanUtils.isNotEmpty(taskNodeStatus.getTaskExecutorList())){
			List<TaskExecutor> tempTaskExecutors=taskNodeStatus.getTaskExecutorList();
			for(TaskExecutor taskExecutor:tempTaskExecutors){
				taskExecutor.setMainOrgName(getMainOrgName(taskExecutor.getExecuteId()));
			}
		}
		for(TaskOpinion taskOpinion:taskNodeStatus.getTaskOpinionList()){
			if(!TaskOpinion.STATUS_CHECKING.equals(taskOpinion.getCheckStatus())) continue;
			Long taskId=taskOpinion.getTaskId();
			ProcessTask processTask=processRunService.getByTaskId(taskId);
			//执行人为空
			String assignee=processTask.getAssignee();
			if(ServiceUtil.isAssigneeNotEmpty(assignee)){
				TaskExeStatus taskExeStatus = new TaskExeStatus();
				String fullname =  sysUserService.getById(new Long(processTask.getAssignee())).getFullname();
				taskExeStatus.setExecutor(fullname);
				taskExeStatus.setExecutorId(assignee);
				taskExeStatus.setMainOrgName(getMainOrgName(assignee));
				boolean isRead = taskReadService.isTaskRead(Long.valueOf(processTask.getId()), Long.valueOf(assignee));
				taskExeStatus.setRead(isRead);
				taskOpinion.setTaskExeStatus(taskExeStatus);
			}
			//获取候选人
			else{
				 Set<TaskExecutor> set= taskUserService.getCandidateExecutors(processTask.getId());
				 List<TaskExeStatus> candidateUserStatusList=new ArrayList<TaskExeStatus>();
				 for(Iterator<TaskExecutor> it=set.iterator();it.hasNext();){
					 TaskExecutor taskExe=it.next();
					 TaskExeStatus taskExeStatus = new TaskExeStatus();
					 taskExeStatus.setExecutorId(taskExe.getExecuteId().toString());
					 taskExeStatus.setMainOrgName(getMainOrgName(taskExeStatus.getExecutorId()));
					 taskExeStatus.setType(taskExe.getType());
					 taskExeStatus.setCandidateUser(taskExe.getExecutor());
					 String executorId=taskExe.getExecuteId();
					 if(TaskExecutor.USER_TYPE_USER.equals(taskExe.getType())){
						 boolean isRead = taskReadService.isTaskRead(Long.valueOf(processTask.getId()), new Long(executorId));
						 taskExeStatus.setRead(isRead);
					 }
					 candidateUserStatusList.add(taskExeStatus);
				 }
				 taskOpinion.setCandidateUserStatusList(candidateUserStatusList);
			}
		}
		return taskNodeStatus;
	}
	public String getMainOrgName(String userId){
		List<SysUserOrg> orgs=sysUserOrgService.getOrgByUserId(Long.parseLong(userId));
		String mainOrgName="";
		for(SysUserOrg org:orgs){
			if(org.getIsPrimary().equals(SysUserOrg.PRIMARY_YES)){
				mainOrgName=org.getOrgName();
				break;
			}
		}
		return mainOrgName;
	}
	
	/**
	 * 导出成word文档
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("downloadToWord")
	@Action(description="导出流程成word文档",
	exectype="管理日志",
	detail="导出流程【${subject}】"
			)
	public  void downloadToWord(HttpServletRequest request, HttpServletResponse response) throws IOException   {
		response.reset();
		response.setCharacterEncoding("utf-8");
		String agent = (String)request.getHeader("USER-AGENT");     
		String form = RequestUtil.getString(request, "form");
		long runId=RequestUtil.getLong(request, "runId");
		ProcessRun processRun=processRunService.getById(runId);
		String subject = processRun.getSubject().replaceAll("<(?:(?:/([^>]+)>)|(?:!--([\\S|\\s]*?)-->)|(?:([^\\s/>]+)\\s*((?:(?:\"[^\"]*\")|(?:'[^']*')|[^\"'<>])*)/?>))", "");
		
		String attachPath=ServiceUtil.getBasePath();
		String name=UniqueIdUtil.genId() +".doc";
		
		String fileName= createFilePath(attachPath + File.separator +"tmpdownload", name);
		
		boolean isIe= true;
		if(agent!=null && agent.indexOf("MSIE")==-1 && agent.indexOf("Trident") == -1){
			isIe=false;
		}
		//生成文件
		genFile(form,fileName);
		
		//下载文件
		donwload(fileName,response,subject,isIe);
		//删除文件
		File file=new File(fileName);
		file.delete();
		
	}
	
	/**
	 * 生成文件夹
	 * @param dir
	 * @param name
	 * @return
	 */
	private String createFilePath(String dir,String name){
		File file=new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return dir + File.separator + name;
	}
	
	/**
	 * 生成文件。
	 * @param content
	 * @param fileName
	 */
	@SuppressWarnings("unused")
	private void genFile(String content,String fileName){
		ByteArrayInputStream bais = null;
		FileOutputStream fos = null;
		POIFSFileSystem poifs = null;
		byte b[];
		try {
			String resultString = content.replaceAll("(?si)<script[\\s]*[^>]*>.*?</script>", "");
			b = resultString.getBytes("gbk");
			bais = new ByteArrayInputStream(b);
			poifs = new POIFSFileSystem();
			DirectoryEntry directory = poifs.getRoot();
			DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
			fos = new FileOutputStream(fileName);
			poifs.writeFilesystem(fos);
			
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				bais.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 下载文件。
	 * @param filePath
	 * @param response
	 * @param fileName
	 * @param isIE
	 */
	private void donwload(String filePath,HttpServletResponse response,String fileName,boolean isIE) {
		String filedisplay = fileName+".doc";
		
		response.reset();
		response.setContentType("application/x-msdownload;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		try {
			
			if(!isIE) {   
				String enableFileName = "=?UTF-8?B?" + (new String(Base64.getBase64(filedisplay))) + "?=";     
			    response.setHeader("Content-Disposition", "attachment;filename=" + enableFileName);    
			}
			else{
				filedisplay=URLEncoder.encode(filedisplay,"utf-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
			}
		
			OutputStream sops = response.getOutputStream();// 不同类型的文件对应不同的MIME类型
			InputStream ips = new FileInputStream(filePath);
			byte[] content = new byte[ips.available()];
			int length = -1;
			while ((length = ips.read(content)) != -1) {
				sops.write(content, 0, length); // 读入流,保存在Byte数组中
			}
			ips.close();
			sops.flush();
			sops.close();
		} catch (UnsupportedEncodingException e) {
			log.debug(e.getMessage(), e);
		
		} catch (IOException e) {
			log.debug(e.getMessage(), e);
		}
		

	}
	
	/**
	 * flex展现。
	 * @param content
	 * @param fileName
	 * @throws Exception 
	 */
	@RequestMapping("flexShow")
	public ModelAndView flexShow(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String runId=request.getParameter("runId");
		String tab=request.getParameter("tab");
		String rand=request.getParameter("rand");
		// 此处用于检测授权限制
		ClassLoadUtil.transform("", "", "");
		ProcessRun processRun=null;
		processRun = processRunService.getById(new Long(runId));
		long defId = 0L;
		defId=processRun.getDefId();
		String typeName="flexShow";
		SysAuditThreadLocalHolder.putParamerter("isExist", defId);
		if (defId > 0) {
			BpmDefinition po = bpmDefinitionService.getById(defId);
			// long name=po.getTypeId();
			// System.out.println("name:="+name);
			request.setAttribute("proDefinition", po);
			request.setAttribute("subject", po.getSubject());
		} else {
			request.setAttribute("subject", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId(); // 当前用户id
		
		request.setAttribute("uId", uId); // 当前用户id
		request.setAttribute("defId", defId); // defId流程定义id
		request.setAttribute("runId", runId);
		request.setAttribute("typeName", typeName);
		return getAutoView();
	}
	//nxx加载流程历史展现内容
	@RequestMapping("flex110")
	@Action(description = "马尔科夫链预览",detail = "设计并保存流程定义【${SysAuditLinkService.getBpmDefinitionLinkByKey(defKey)}】,"
		+ "</#if>")
	public ModelAndView flex110(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/xml");
		String runId=request.getParameter("runId");
		String actInstanceId=null;
		ProcessRun processRun=null;
		processRun = processRunService.getById(new Long(runId));
		actInstanceId=processRun.getActInstId();
		System.out.println("********"+actInstanceId);
		String defXml  = bpmService.getDefXmlByProcessDefinitionId(processRun.getActDefId());
		System.out.println("我的defXML:          "+defXml);
		PrintWriter out = response.getWriter();
		List<NodeSet> nodes = new ArrayList<NodeSet>();
		List<SequenceFlow> sequenceflow = new ArrayList<SequenceFlow>();
		Document document = DocumentHelper.parseText(defXml);
		Element root = document.getRootElement();
		List<Element> elementList=root.elements();
		System.out.println("elementList.size()"+elementList.size());
		int num=0;//节点标号
		for(Element element : elementList){
			System.out.println(element.getName());
			System.out.println(element.attribute("id").getText());
			
			List<Element> eles = element.elements();
				for(Element ele : eles){
					System.out.println(ele.getName());
					if(ele.getName().contains("sequenceFlow")){
							String id = ele.attribute("id").getText();			
							String sourceRef = ele.attributeValue("sourceRef");
							String targetRef = ele.attributeValue("targetRef");
							System.out.println("id:"+id+" sourceRef:"+sourceRef+" targetRef:"+targetRef);
							sequenceflow.add(new SequenceFlow(id, sourceRef, targetRef));
						}
					if(ele.getName().contains("startEvent")||ele.getName().contains("userTask")||ele.getName().contains("endEvent")||ele.getName().contains("serviceTask")||ele.getName().contains("Gateway")){
							String id = ele.attribute("id").getText();
							String label = ele.attributeValue("name");
							System.out.println("id:"+id+" label:"+label);
							NodeSet nodeset = new NodeSet(id,label,num);
							nodes.add(nodeset);	
							num++;
					}
				}
		}
		List<TaskOpinion> list=taskOpinionService.getByRunId(Long.parseLong(runId));
		List<Map<String,Object>> nodeMsg = new ArrayList<Map<String,Object>>();
		
		for(TaskOpinion b:list)
		{
			System.out.println("^^^^^^^"+b.getTaskKey());
			List<Map<String,Object>>  temp=getNodeMsg(actInstanceId,b.getTaskKey());
			for(Map<String,Object> c:temp)
			{
				nodeMsg.add(c);
			}
		}
		String xml=setxml(nodeMsg);
		
		String lastTaskKey=list.get(list.size()-1).getTaskKey();
		for(SequenceFlow s:sequenceflow)
		{
			if(s.getSourceRef().equals(lastTaskKey))
			{
				if(s.getTargetRef().contains("EndEvent"))
				{
					xml=xml+"<node id='"+s.getTargetRef()+"' lable='结束' executor='' candidate='' startTime='' endTime='' durTime='' status='' opinion=''/>";
				}
			}
		}
		xml=xml+"</flowdata>";
		try{
			xml=xml.replaceAll("\"", "\'");
			xml=xml.replaceAll("null", "");
			System.out.println("构建的XML为:"+xml);
			out.print(xml);
		}
		catch(Exception e){
			out.print(e);
		}
        return null;        
        
	}
	public List<Map<String,Object>> getNodeMsg(String instanceId,String nodeId)
	{
		TaskNodeStatus taskNodeStatus = bpmService.getNodeCheckStatusInfo(instanceId,nodeId);
		List<Map<String, Object>> listMap=new ArrayList<Map<String,Object>>();
		//结点执行状态为“未执行”时的执行人信息
		List<TaskExecutor> b1=taskNodeStatus.getTaskExecutorList();
		if(b1.size()>0)
		{
			Map<String,Object> map2=new HashMap<String, Object>();
			map2.put("id", nodeId);
			StringBuffer buf = new StringBuffer(); 
			for(TaskExecutor a:b1){
				System.out.println("executor "+a.getExecutor());
				buf.append(a.getExecutor()).append("  "); 
			}
			String str = buf.toString();
			map2.put("executor", str);
			map2.put("taskName", "");
			map2.put("startTime", "");
			map2.put("endTime", "");
			map2.put("durTime", "");
			map2.put("candidate", "");
			map2.put("status", "");
			map2.put("opinion", "");
			listMap.add(map2);
		}
		else{
			List<TaskOpinion> b=taskNodeStatus.getTaskOpinionList();
			for(TaskOpinion a:b)
			{
				Map<String,Object> map1=new HashMap<String, Object>();
				map1.put("id", nodeId);
				String taskName=a.getTaskName();
				String executor=a.getExeFullname();
				Date startTimetemp=a.getStartTime();
				DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				String startTime=df2.format(startTimetemp);
				System.out.println("开始时间"+df2.format(startTimetemp));
				Date endTimetemp=a.getEndTime();
				String endTime=df2.format(endTimetemp);
				String durTime=a.getDurTimeStr();
				String candidate=a.getCandidateUser();
				String status=a.getStatus();
				String opinion=a.getOpinion();
				if(status!=null) 
				{
					System.out.println("status "+getChar(status));
					map1.put("status", getChar(status));
				}
				else{
					System.out.println("status "+"");
					map1.put("status", status);
				}
				if(opinion!=null&&-1!=opinion.indexOf("<a")&&-1!=opinion.indexOf("</a>"))
				{
						System.out.println("opinion "+getChar(opinion));
						map1.put("opinion", getChar(opinion));
				}
				else{
					System.out.println("opinion "+opinion);
					map1.put("opinion", opinion);
				}
				map1.put("taskName", taskName);
				map1.put("executor", executor);
				map1.put("candidate", candidate);
				map1.put("startTime", startTime);
				map1.put("endTime", endTime);
				map1.put("durTime", durTime);
				listMap.add(map1);
				//状态为正在审批，则继续执行
				if(!TaskOpinion.STATUS_CHECKING.equals(a.getCheckStatus())) continue;
				Long taskId=a.getTaskId();
				ProcessTask processTask=processRunService.getByTaskId(taskId);
				//执行人不为空
				String assignee=processTask.getAssignee();
				if(ServiceUtil.isAssigneeNotEmpty(assignee)){
					TaskExeStatus taskExeStatus = new TaskExeStatus();
					executor =  sysUserService.getById(new Long(processTask.getAssignee())).getFullname();
					String isRead;
					if (taskReadService.isTaskRead(Long.valueOf(processTask.getId()), Long.valueOf(assignee))) {
						isRead="（已读）";
					}
					else{
						isRead="（未读）";
					}
					System.out.println("executor "+executor+isRead);
					map1.put("executor", executor+isRead);
				}
				//执行人为空时，获取候选人
				else{
					 Set<TaskExecutor> set= taskUserService.getCandidateExecutors(processTask.getId());
					 for(Iterator<TaskExecutor> it=set.iterator();it.hasNext();){
						 TaskExecutor taskExe=it.next();
						 String executorId=taskExe.getExecuteId();
						 if(TaskExecutor.USER_TYPE_USER.equals(taskExe.getType())){
							 boolean isRead = taskReadService.isTaskRead(Long.valueOf(processTask.getId()), new Long(executorId));
							 System.out.println(isRead);
						 }
						 candidate=taskExe.getExecutor();
						 System.out.println("candidate "+candidate);
						 map1.put("candidate", candidate);
					 }
				}
			}
		}
		System.out.println("listMap集合的大小："+listMap.size());
		return listMap;
		}
	public String getChar(String s) {
        String arr[] = s.split("<[^>]+>|</[^>]>");
        String s1="";
        for(int i=1;i<arr.length;i++){
            s1=s1+arr[i];
        }
        return s1;
	}
	public String setxml(List<Map<String,Object>> i){
		String start = "<flowdata><node id='StartEvent1' lable='开始' executor='' candidate='' startTime='' endTime='' durTime='' status='' opinion=''/>";
		String end = "</flowdata>";
		String flowset = "";
		String string="/>";
		String node ="<node ";
		System.out.println(i.size());
		System.out.println(i);
		for(Map<String,Object> map : i)
		{
			Set keys=map.keySet();
			Iterator iterator=keys.iterator();
			while(iterator.hasNext())
			{
				String key= (String) iterator.next();
				Object value= map.get(key);
				if(value=="")
				{
					map.put(key, null);
				}
				
			}
			flowset=flowset+node;
			flowset = flowset + "id="+"\"" + map.get("id")+"\" lable="+"\"" +map.get("taskName")+"\" " +
					"executor="+"\"" +map.get("executor")+"\" candidate="+"\"" +map.get("candidate")+"\" startTime="
					+"\"" +map.get("startTime")+"\" endTime="+"\"" +map.get("endTime")+"\" durTime="+"\"" +
					map.get("durTime")+"\" status="+"\"" +map.get("status")+"\" opinion="+"\"" +map.get("opinion")+"\"" ;
			flowset=flowset+string;
				System.out.println("flowset:"+flowset);
			
		}
		flowset = start + flowset ;
		System.out.println(flowset);
		return flowset;
	}
}
