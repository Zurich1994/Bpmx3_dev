package com.hotent.platform.controller.bpm;

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

import com.hotent.platform.model.bpm.ReminderState;
import com.hotent.platform.service.bpm.ReminderStateService;

/**
 * 对象功能:任务催办执行情况 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-17 17:17:37
 */
@Controller
@RequestMapping("/platform/bpm/reminderState/")
public class ReminderStateFormController extends BaseFormController
{
	@Resource
	private ReminderStateService reminderStateService;
	
	/**
	 * 添加或更新任务催办执行情况。
	 * @param request
	 * @param response
	 * @param reminderState 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新任务催办执行情况")
	public void save(HttpServletRequest request, HttpServletResponse response, ReminderState reminderState,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("reminderState", reminderState, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(reminderState.getId()==null){
			reminderState.setId(UniqueIdUtil.genId());
			reminderStateService.add(reminderState);
			resultMsg="添加任务催办执行情况成功";
		}else{
			reminderStateService.update(reminderState);
			resultMsg="更新任务催办执行情况成功";
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
    protected ReminderState getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter ReminderState getFormObject here....");
		ReminderState reminderState=null;
		if(id!=null){
			reminderState=reminderStateService.getById(id);
		}else{
			reminderState= new ReminderState();
		}
		return reminderState;
    }

}
