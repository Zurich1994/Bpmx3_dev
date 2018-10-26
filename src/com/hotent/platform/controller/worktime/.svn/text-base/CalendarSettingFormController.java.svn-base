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

import com.hotent.platform.model.worktime.CalendarSetting;
import com.hotent.platform.service.worktime.CalendarSettingService;

/**
 * 对象功能:日历设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
@Controller
@RequestMapping("/platform/worktime/calendarSetting/")
public class CalendarSettingFormController extends BaseFormController
{
	@Resource
	private CalendarSettingService calendarSettingService;
	
	/**
	 * 添加或更新日历设置。
	 * @param request
	 * @param response
	 * @param calendarSetting 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新日历设置")
	public void save(HttpServletRequest request, HttpServletResponse response, CalendarSetting calendarSetting,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("calendarSetting", calendarSetting, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(calendarSetting.getId()==null){
			calendarSetting.setId(UniqueIdUtil.genId());
			calendarSettingService.add(calendarSetting);
			resultMsg="添加日历设置成功";
		}else{
			calendarSettingService.update(calendarSetting);
			resultMsg="更新日历设置成功";
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
    protected CalendarSetting getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter CalendarSetting getFormObject here....");
		CalendarSetting calendarSetting=null;
		if(id!=null){
			calendarSetting=calendarSettingService.getById(id);
		}else{
			calendarSetting= new CalendarSetting();
		}
		return calendarSetting;
    }

}
