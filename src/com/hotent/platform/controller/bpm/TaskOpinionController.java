
package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskOpinion;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskOpinionService;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 对象功能:流程任务审批意见控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-12 09:59:09
 */
@Controller
@RequestMapping("/platform/bpm/taskOpinion/")
public class TaskOpinionController extends BaseController
{
	@Resource
	private TaskOpinionService taskOpinionService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	Map<String, IMessageHandler> handlersMap;	
	/**
	 * 取得某个流程任务审批意见分页列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看流程任务审批意见分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	

		String preUrl=RequestUtil.getPrePage(request);
		Long runId=RequestUtil.getLong(request, "runId", 0L);
		if(runId.equals(0L)){
			return ServiceUtil.getTipInfo("请输入流程运行ID");
		}
		ProcessRun	processRun=processRunService.getById(runId);
		//取得关联的流程实例ID
		List<TaskOpinion> list=taskOpinionService.getByRunId(runId);
		//设置代码执行人
		list = taskOpinionService.setTaskOpinionExecutor(list);
		
		ModelAndView mv=this.getAutoView().addObject("taskOpinionList",list)
							.addObject("processRun",processRun)
							.addObject("returnUrl", preUrl);
						
		return mv;
	}
	
	
	
	/**
	 * 取得某个流程任务审批意见分页列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getList")
	@Action(description="查看流程任务审批意见分页列表")
	public ModelAndView getList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		
		String runId=request.getParameter("runId");
		ProcessRun processRun=null;
		String actInstId="";
		if(StringUtils.isNotEmpty(runId)){
			processRun=processRunService.getById(new Long(runId));
			actInstId=processRun.getActInstId();
		}

		List<TaskOpinion> list=taskOpinionService.getByActInstId(actInstId);
		if(processRun==null){
			processRun=processRunService.getByActInstanceId(new Long(actInstId));
		}
		ModelAndView mv=this.getAutoView().addObject("taskOpinionList",list)
							.addObject("processRun",processRun);
		return mv;
	}
	
	
	
	/**
	 * 在表单中显示审批历史
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listform")
	@Action(description="在表单中显示审批历史")
	public ModelAndView listform(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String actInstId=request.getParameter("actInstId");
		List<TaskOpinion> list=taskOpinionService.getByActInstId(actInstId);
		ModelAndView mv=this.getAutoView().addObject("taskOpinionList",list);
		return mv;
	}
	
	@RequestMapping("dialog")
	@Action(description="编辑流程抄送转发")
	public ModelAndView forward(HttpServletRequest request) throws Exception
	{
		Map<String, IMessageHandler> handlersMap=ServiceUtil.getHandlerMap();
		return getAutoView()
				.addObject("handlersMap",handlersMap);
				
	}
	
}
