package com.hotent.platform.controller.bpm;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.model.ProcessTask;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmProTransTo;
import com.hotent.platform.model.bpm.BpmProTransToAssignee;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmProTransToService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.util.ServiceUtil;
/**
 * <pre>
 * 对象功能:加签（转办） 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:helh
 * 创建时间:2014-04-04 17:05:35
 * </pre>
 */
@Controller
@RequestMapping("/platform/bpm/bpmProTransTo/")
@Action(ownermodel=SysAuditModelType.PROCESS_MANAGEMENT)
public class BpmProTransToController extends BaseController {
	@Resource
	private BpmProTransToService bpmProTransToService;
	@Resource
	Map<String, IMessageHandler> handlersMap;
	@Resource
	private ProcessRunService processRunService;
	
	/**
	 * 流转事宜列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("mattersList")
	@Action(description = "流转事宜列表")
	public ModelAndView mattersList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter filter = new QueryFilter(request, "bpmProTransToItem");
		String nodePath = RequestUtil.getString(request, "nodePath");
		if(StringUtils.isNotEmpty(nodePath))
			filter.getFilters().put("nodePath",nodePath + "%");
		filter.addFilter("exceptDefStatus", 3);
		Long userId = ContextUtil.getCurrentUserId();
		filter.addFilter("createUserId", userId);
		List<BpmProTransTo> list = bpmProTransToService.mattersList(filter);
		ModelAndView mv = this.getAutoView().addObject("bpmProTransToList", list)
				.addObject("curUserId", userId);

		return mv;
	}
	
	/**
	 * 查看流转人
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("showAssignee")
	@Action(description = "查看流转人")
	public ModelAndView showAssignee(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String taskId  = RequestUtil.getString(request, "taskId");
		BpmProTransTo bpmProTransTo = bpmProTransToService.getByTaskId(new Long(taskId));
		if(BeanUtils.isEmpty(bpmProTransTo)){
			return ServiceUtil.getTipInfo("流转过程已结束,毋须再添加流转人!");
		}
		List<BpmProTransToAssignee> list = bpmProTransToService.getAssignee(taskId, bpmProTransTo.getAssignee());
		ModelAndView mv = this.getAutoView().addObject("bpmProTransToAssigneeList", list);
		return mv;
	}
	
	/**
	 * 添加流转人
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("add")
	@Action(description = "添加流转人")
	@ResponseBody
	public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		ResultMessage resultMessage=null;
		String userIds = request.getParameter("cmpIds");
		try{
			Long taskId = RequestUtil.getLong(request, "taskId");
			String opinion = request.getParameter("opinion");
			String informType=RequestUtil.getString(request, "informType");
			BpmProTransTo bpmProTransTo = bpmProTransToService.getByTaskId(taskId);
			if(BeanUtils.isEmpty(bpmProTransTo)){
				resultMessage=new ResultMessage(ResultMessage.Fail, "流转过程已结束,毋须再添加流转人!");
			}else{
				bpmProTransToService.addTransTo(bpmProTransTo, taskId.toString(), opinion, userIds, informType);
				resultMessage=new ResultMessage(ResultMessage.Success, "添加流转人成功!");
			}
		}catch (Exception e) {
			resultMessage=new ResultMessage(ResultMessage.Fail, "添加流转人失败!");
			e.printStackTrace();
		}
		out.print(resultMessage);
	}
	
	@RequestMapping("addDialog")
	@Action(description="批量取消")
	public ModelAndView addDialog(HttpServletRequest request) throws Exception
	{
		Long taskId = RequestUtil.getLong(request, "taskId");
		BpmProTransTo bpmProTransTo = bpmProTransToService.getByTaskId(taskId);
		if(BeanUtils.isEmpty(bpmProTransTo)){
			return ServiceUtil.getTipInfo("流转过程已结束,不能继续添加了!");
		}
		Map<String, IMessageHandler> handlersMap=ServiceUtil.getHandlerMap();
		
		return getAutoView().addObject("handlersMap",handlersMap)
				.addObject("bpmProTransTo", bpmProTransTo);
				
	}
	
	/**
	 * 取消流转任务
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("cancel")
	@Action(description = "取消流转任务")
	@ResponseBody
	public void cancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PrintWriter out=response.getWriter();
		ResultMessage resultMessage=null;
		String taskId = RequestUtil.getString(request, "taskId");
		String opinion = RequestUtil.getString(request, "opinion");
		String userId = RequestUtil.getString(request, "userId");
		String informType = RequestUtil.getString(request, "informType");
		try{
			ProcessTask processTask = processRunService.getTaskByParentIdAndUser(taskId, userId);
			if(processTask==null){
				resultMessage=new ResultMessage(ResultMessage.Fail, "此流转任务已被审批!");
			}else{
				bpmProTransToService.cancel(processTask, opinion, informType);
				resultMessage=new ResultMessage(ResultMessage.Success, "取消流转任务成功!");
			}
		}catch(Exception e){
			resultMessage=new ResultMessage(ResultMessage.Fail, "取消流转任务失败!");
			e.printStackTrace();
		}
		out.print(resultMessage);
	}
	
	/**
	 * 获取流转人及任务情况
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getByAssignee")
	@Action(description = "获取流转人及任务情况")
	@ResponseBody
	public List<BpmProTransToAssignee> getByAssignee(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String parentTaskId  = RequestUtil.getString(request, "parentTaskId");
		String assignee = RequestUtil.getString(request, "assignee");
		return bpmProTransToService.getAssignee(parentTaskId, assignee);
	}
	
	@RequestMapping("cancelDialog")
	@Action(description="批量取消")
	public ModelAndView cancelDialog(HttpServletRequest request) throws Exception
	{
		Map<String, IMessageHandler> handlersMap=ServiceUtil.getHandlerMap();
		return getAutoView()
				.addObject("handlersMap",handlersMap);
				
	}
}