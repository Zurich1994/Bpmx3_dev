package com.hotent.platform.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.scheduler.SchedulerService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysJobLog;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.SysJobLogService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

@Controller
@RequestMapping("/platform/system/scheduler/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class SchedulerController extends BaseController{
	
	@Resource
	SchedulerService schedulerService;
	@Resource
	SysJobLogService sysJobLogService;

	/**
	 * 添加任务
	 * @param response
	 * @param request
	 * @param viewName
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/addJob{viewName}")
	@Action(description="添加定时计划作业",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if STEP1>进入 添加定时计划作业  编辑页面<#else>添加定时计划【${name}】</#if>"
			)
	public ModelAndView addJob(HttpServletResponse response, HttpServletRequest request,@PathVariable("viewName") String viewName) throws Exception 
	{
		
		try {
			SysAuditThreadLocalHolder.putParamerter("STEP1",STEP1.equals(viewName));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		PrintWriter out=response.getWriter();
		ModelAndView mv=new ModelAndView();
		if(STEP1.equals(viewName))
		{
			mv.setViewName("/platform/scheduler/jobAdd.jsp");
			return mv;
		}
		else if(STEP2.equals(viewName))
		{
			try
				{
						
					String className= RequestUtil.getString(request, "className");
					String jobName=RequestUtil.getString(request, "name"); 
					String parameterJson=RequestUtil.getString(request, "parameterJson");
					String description=RequestUtil.getString(request,"description");
					boolean isExist=schedulerService.isJobExists(jobName);
					if(isExist)
					{
						ResultMessage obj=new ResultMessage(ResultMessage.Fail,"任务名称已经存在，添加失败!");
						out.print(obj.toString());
					}
					else
					{
						schedulerService.addJob(jobName, className, parameterJson,description);
						ResultMessage obj=new ResultMessage(ResultMessage.Success,"添加任务成功!");
						out.print(obj.toString());
					}
				}
				catch(ClassNotFoundException ex)
				{
					ResultMessage obj=new ResultMessage(ResultMessage.Fail,"添加指定的任务类不存在，添加失败!");
					out.print(obj.toString());
					
				}
				catch (Exception ex)
				{
					String str = MessageUtil.getMessage();
					if (StringUtil.isNotEmpty(str)) {
						ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"添加任务失败:" + str);
						response.getWriter().print(resultMessage);
					} else {
						String message = ExceptionUtil.getExceptionMessage(ex);
						ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
						response.getWriter().print(resultMessage);
					}
				}
				return null;
		}
		return null;
	}
    /**
     * 任务列表
     * @param response
     * @param request
     * @return
     * @throws SchedulerException
     */
	@RequestMapping("getJobList")
	public ModelAndView getJobList(HttpServletResponse response, HttpServletRequest request) throws SchedulerException  
	{
		boolean isInStandbyMode = schedulerService.isInStandbyMode();
		List<JobDetail> list= schedulerService.getJobList();
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/platform/scheduler/jobList.jsp");
		mv.addObject("jobList",list);
		mv.addObject("isStandby",isInStandbyMode);
		return mv;
	}
	/**
	 * 删除任务
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/delJob")
	@Action(description="删除定时计划作业",
	detail= "删除定时计划作业" +
			"<#list StringUtils.split(jobName,\",\") as item>" +
				"【${item}】" +
			"</#list>"
			)
	public void delJob(HttpServletResponse response, HttpServletRequest request) throws IOException, SchedulerException, ClassNotFoundException 
	{
		ResultMessage message=null;
		try {
			String jobName=RequestUtil.getString(request, "jobName");
			schedulerService.delJob(jobName);
			message=new ResultMessage(ResultMessage.Success,"删除任务成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除任务失败:"+e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}
	/**
	 * 添加计划
	 * @param response
	 * @param request
	 * @param viewName
	 * @return
	 * @throws IOException
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	@RequestMapping("/addTrigger{viewName}")
	@Action(
			description="添加定时计划",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if STEP1>进入 添加定时计划  编辑页面<#else>添加定时计划作业【${jobName}】计划【${name}】</#if>"		
	)
	public ModelAndView addTrigger(HttpServletResponse response, HttpServletRequest request, @PathVariable("viewName") String viewName) throws IOException, SchedulerException, ParseException 
	{
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("STEP1",STEP1.equals(viewName));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
								
		PrintWriter out=response.getWriter();
		ModelAndView mv=new ModelAndView();
		if(STEP1.equals(viewName))
		{
			String jobName=RequestUtil.getString(request,"jobName");
			mv.setViewName("/platform/scheduler/triggerAdd.jsp");
			mv.addObject("jobName", jobName);
			return mv;
		}
		else if(STEP2.equals(viewName))
		{
			String trigName=RequestUtil.getString(request, "name");
			String jobName=RequestUtil.getString(request,"jobName");
			
			String planJson=RequestUtil.getString(request,"planJson");
			//判断触发器是否存在
			boolean rtn=schedulerService.isTriggerExists(trigName);
			if(rtn)
			{
				ResultMessage obj=new ResultMessage(ResultMessage.Fail,"指定的计划名称已经存在!");
				out.print(obj.toString());
			}
			try {
				schedulerService.addTrigger(jobName, trigName, planJson);
				ResultMessage obj=new ResultMessage(ResultMessage.Success,"添加计划成功!");
				out.print(obj.toString());
			} catch (SchedulerException e) {
				String str = MessageUtil.getMessage();
				if (StringUtil.isNotEmpty(str)) {
					ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"添加计划失败:" + str);
					response.getWriter().print(resultMessage);
				} else {
					String message = ExceptionUtil.getExceptionMessage(e);
					ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
					response.getWriter().print(resultMessage);
				}
			}
			return null;
		}
		return null;
	}
	/**
	 * 计划列表
	 * @param response
	 * @param request
	 * @return
	 * @throws SchedulerException
	 */
	@RequestMapping("/getTriggersByJob")
	public ModelAndView getTriggersByJob(HttpServletResponse response, HttpServletRequest request) throws SchedulerException  
	{
		
		String jobName=RequestUtil.getString(request,"jobName");
		
		List<Trigger> list= schedulerService.getTriggersByJob(jobName);
		HashMap<String, Trigger.TriggerState> mapState=schedulerService.getTriggerStatus(list);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/platform/scheduler/triggerList.jsp");
		mv.addObject("list",list);
		mv.addObject("mapState", mapState);
		mv.addObject("jobName", jobName);
		
		return mv;
	}
	/**
	 * 执行任务
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SchedulerException 
	 */
	@RequestMapping("executeJob")
	public void executeJob(HttpServletRequest request, HttpServletResponse response) throws IOException, SchedulerException{

		String jobName=RequestUtil.getString(request,"jobName");
		schedulerService.executeJob(jobName);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}
	/**
	 *验证类
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("validClass")
	public void validClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		String className = RequestUtil.getString(request, "className", "");
		boolean rtn = BeanUtils.validClass(className);
		if (rtn) {
			ResultMessage obj = new ResultMessage(ResultMessage.Success, "验证类成功!");
			out.println(obj.toString());
		} else {
			ResultMessage obj = new ResultMessage(ResultMessage.Fail, "验证类失败!");
			out.println(obj.toString());
		}
	}
	/**
	 * 删除触发器
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws SchedulerException
	 * @throws ClassNotFoundException
	 */
	@RequestMapping("/delTrigger")
	@Action(
			description="删除定时计划作业计划" ,
			execOrder=ActionExecOrder.BEFORE ,
			detail="删除定时计划作业计划：" +
			"<#list StringUtils.split(name,\",\") as item>" +
				"<#assign entity=SysAuditLinkService.getTrigger(item)/>" +
				"【作业：${entity.jobKey.name}，计划：${item}】" +
			"</#list>"
	)
	public void delTrigger(HttpServletResponse response, HttpServletRequest request) throws IOException, SchedulerException, ClassNotFoundException 
	{
		ResultMessage message=null;
		try {
			String name=RequestUtil.getString(request,"name");
			schedulerService.delTrigger(name);
			message=new ResultMessage(ResultMessage.Success,"删除计划成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除计划失败:"+e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}
	/**
	 * 启用或禁用
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws SchedulerException
	 */
	@RequestMapping("/toggleTriggerRun")
	@Action(
			description="启用或禁用定时计划作业计划" ,
			execOrder=ActionExecOrder.AFTER ,
			detail="设置定时计划作业【${jobName}】的计划状态：" +
				"<#list StringUtils.split(triggerName,\",\") as item>" +
					"【${item}：" +
					"	<#if TriggerState.NORMAL==striggerStatus[item]>启用" +
					" 	<#elseif TriggerState.PAUSED==striggerStatus[item]>禁用 "+
					" 	<#elseif TriggerState.ERROR==striggerStatus[item]>执行出错" +
					" 	<#elseif TriggerState.COMPLETE==striggerStatus[item]>完成" +
					" 	<#elseif TriggerState.BLOCKED==striggerStatus[item]>正在执行" +
					" 	<#elseif TriggerState.NONE==striggerStatus[item]>未启动" +
					" 	<#elseif TriggerState.PAUSED==striggerStatus[item]>禁用" +
					"	<#else>未知</#if>" +
					"】" +
				"</#list>"
	)
	public void toggleTriggerRun(HttpServletResponse response, HttpServletRequest request) throws IOException, SchedulerException 
	{
		
		String name=RequestUtil.getString(request, "name");
		schedulerService.toggleTriggerRun(name);
		response.sendRedirect(RequestUtil.getPrePage(request));
	}
	/**
	 * 任务执行日志列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getLogList")
	public ModelAndView selector(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String jobName=RequestUtil.getString(request, "jobName");
		String trigName=RequestUtil.getString(request, "trigName");
		List<SysJobLog> list=sysJobLogService.getAll(new QueryFilter(request,"sysJobLogItem"));
		ModelAndView mv=new ModelAndView("/platform/scheduler/sysJobLogList.jsp");
		mv.addObject("sysJobLogList", list);
		mv.addObject("jobName", jobName);
		mv.addObject("trigName", trigName);
		
		return mv;
		
	}
	/**
	 * 删除任务日志
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("delJobLog")
	@Action(
			description="删除定时计划作业执行日志",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除定时计划作业执行日志" +
					"<#list StringUtils.split(logId,\",\") as item>" +
						"<#assign entity=sysJobLogService.getById(Long.valueOf(item))/>" +
						"【作业：${entity.jobName}，计划：${entity.trigName}，内容：${entity.content}】" +
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ResultMessage message=null;
		String preUrl= RequestUtil.getPrePage(request);
		try {
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "logId");
			sysJobLogService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除任务日志成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除任务日志失败:"+e.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 修改定时器的状态
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("changeStart")
	public void changeStart(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ResultMessage message=null;
		String resultMsg ="";
		boolean isStandby = RequestUtil.getBoolean(request, "isStandby");
		try {
			//如果是挂起状态就启动，否则就挂起
			if (isStandby) {
				schedulerService.start();
				resultMsg="启动定时器成功!";
			}else {
				schedulerService.shutdown();
				resultMsg="停止定时器成功!";
			}
			message=new ResultMessage(ResultMessage.Success,resultMsg);
		} catch (Exception e) {
			e.printStackTrace();
			if (isStandby) {
				resultMsg="启动定时器失败:";
			}else {
				resultMsg="停止定时器失败:";
			}
			message=new ResultMessage(ResultMessage.Fail,resultMsg+e.getMessage());
		}
		writeResultMessage(response.getWriter(), message);
	}
}
