package com.hotent.platform.controller.bpm;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.bpm.TaskVars;
import com.hotent.platform.service.bpm.BpmService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.bpm.TaskVarsService;
import com.hotent.platform.service.bpm.thread.MessageUtil;

/**
 * 对象功能:任务流程变量定义 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-01 16:50:07
 */
@Controller
@RequestMapping("/platform/bpm/taskVars/")
public class TaskVarsController extends BaseController
{
	@Resource
	private TaskVarsService taskVarsService;
	@Resource
	private BpmService bpmService;
	@Resource
	private ProcessRunService processRunService;
	/**
	 * 任务流程变量管理
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public ModelAndView taskVars(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String taskId=RequestUtil.getString(request, "taskId");
		Long runId=RequestUtil.getLong(request, "runId");
		String preUrl=RequestUtil.getPrePage(request);
		ProcessRun processRun=null;
		QueryFilter queryFilter =new QueryFilter(request);
		ModelAndView mv=this.getAutoView();
		if(StringUtil.isNotEmpty(taskId)){
			Task task=bpmService.getTask(taskId);
			String executionId=task.getExecutionId();
			if(executionId!=null){
				queryFilter.getFilters().put("executionId", executionId);
			}
			processRun=processRunService.getByActInstanceId(new Long(task.getProcessInstanceId()));
			mv.addObject("task", task);
		}else{
			processRun=processRunService.getById(runId);
			queryFilter.getFilters().put("runId", runId);
		}
       
		List<TaskVars> list=taskVarsService.getVars(queryFilter);
	    mv.addObject("taskVarsList",list).addObject("taskId", taskId).addObject("processRun", processRun).addObject("returnUrl", preUrl);
		return mv;
	}
	/**
	 * 修改流程变量的值
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateVars")
	public void updateTaskVars(HttpServletRequest request,HttpServletResponse response,TaskVars po) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String varsValue=RequestUtil.getString(request, "varsValue");
		
		TaskVars taskVars=taskVarsService.getById(id);
		
		String type=taskVars.getType();
		try {
			if(type.equals("string")){
				taskVars.setTextValue(varsValue);
			}else if(type.equals("double")){
				taskVars.setDoubleValue(Double.parseDouble(varsValue));
			}else{
				taskVars.setTextValue(varsValue);
				taskVars.setLongValue(Long.parseLong(varsValue));
			}
			taskVarsService.update(taskVars);
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "更新成功"));
		} catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"变量类型不匹配:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
}
