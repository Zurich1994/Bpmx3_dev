package com.hotent.platform.controller.worktime;

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

import com.hotent.platform.model.worktime.WorkTime;
import com.hotent.platform.service.worktime.WorkTimeService;

/**
 * 对象功能:班次时间 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-02-22 16:58:15
 */
@Controller
@RequestMapping("/platform/worktime/workTime/")
public class WorkTimeFormController extends BaseFormController
{
	@Resource
	private WorkTimeService workTimeService;
	
	/**
	 * 添加或更新班次时间。
	 * @param request
	 * @param response
	 * @param workTime 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新班次时间")
	public void save(HttpServletRequest request, HttpServletResponse response, WorkTime workTime,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("workTime", workTime, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(workTime.getId()==null){
			workTime.setId(UniqueIdUtil.genId());
			workTimeService.add(workTime);
			resultMsg="添加班次时间成功";
		}else{
			workTimeService.update(workTime);
			resultMsg="更新班次时间成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected WorkTime getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter WorkTime getFormObject here....");
		WorkTime workTime=null;
		if(id!=null){
			workTime=workTimeService.getById(id);
		}else{
			workTime= new WorkTime();
		}
		return workTime;
    }

}
