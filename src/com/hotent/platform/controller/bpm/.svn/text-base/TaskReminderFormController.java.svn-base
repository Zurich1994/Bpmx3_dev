package com.hotent.platform.controller.bpm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;

import com.hotent.platform.model.bpm.TaskReminder;
import com.hotent.platform.service.bpm.TaskReminderService;

/**
 * 对象功能:任务节点催办时间设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 13:56:53
 */
@Controller
@RequestMapping("/platform/bpm/taskReminder/")
public class TaskReminderFormController extends BaseFormController
{
	@Resource
	private TaskReminderService taskReminderService;
	
	/**
	 * 添加或更新任务节点催办时间设置。
	 * @param request
	 * @param response
	 * @param taskReminder 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新任务节点催办时间设置")
	public void save(HttpServletRequest request, HttpServletResponse response, TaskReminder taskReminder,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("taskReminder", taskReminder, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		int reminderStartDay=RequestUtil.getInt(request, "reminderStartDay");
		int reminderStartHour=RequestUtil.getInt(request, "reminderStartHour");
		int reminderStartMinute=RequestUtil.getInt(request, "reminderStartMinute");
		//计算任务催办开始时间为多少个工作日，以分钟计算
		int reminderStart=(reminderStartDay*24+reminderStartHour)*60+reminderStartMinute;
		
	    int reminderEndDay=RequestUtil.getInt(request, "reminderEndDay");
	    int reminderEndHour=RequestUtil.getInt(request, "reminderEndHour");
	    int reminderEndMinute=RequestUtil.getInt(request, "reminderEndMinute");
	    //计算任务催办结束时间为多少个工作日，以分钟计算
	    int reminderEnd=(reminderEndDay*24+reminderEndHour)*60+reminderEndMinute;
	    
	    int completeTimeDay=RequestUtil.getInt(request,"completeTimeDay");
	    int completeTimeHour=RequestUtil.getInt(request,"completeTimeHour");
	    int completeTimeMinute=RequestUtil.getInt(request, "completeTimeMinute");
	    //计算办结时间为多少个工作日，以分钟计算
	    int completeTime=(completeTimeDay*24+completeTimeHour)*60+completeTimeMinute;
	    
	    taskReminder.setReminderStart(reminderStart);
		taskReminder.setReminderEnd(reminderEnd);
		taskReminder.setCompleteTime(completeTime);
		
		try {
			if (taskReminder.getTaskDueId() == null || taskReminder.getTaskDueId() == 0){
				taskReminder.setTaskDueId(UniqueIdUtil.genId());
				taskReminderService.add(taskReminder);
				resultMsg="添加任务节点催办设置成功";
			}else{
				taskReminderService.update(taskReminder);
				resultMsg="更新任务节点催办设置成功";
			}
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}
}
