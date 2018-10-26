package com.hotent.platform.controller.worktime;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.worktime.SysCalendarService;
import com.hotent.core.annotion.ActionExecOrder;

/**
 * 对象功能:系统日历 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
@Controller
@RequestMapping("/platform/worktime/sysCalendar/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class SysCalendarFormController extends BaseFormController
{
	@Resource
	private SysCalendarService sysCalendarService;
	
	
	
	/**
	 * 添加或更新系统日历。
	 * @param request
	 * @param response
	 * @param sysCalendar 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(
			description="添加或更新系统日历",
			execOrder=ActionExecOrder.AFTER,
			detail= "<#assign entity=calJson?eval />" +
					"<#if entity.id==0>添加<#else>更新</#if>系统日历" +
					"【${entity.name}】"
	)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String json=RequestUtil.getString(request,"calJson");
		ResultMessage resultMessage=null;
		try
		{
			sysCalendarService.saveCalendar(json);
			resultMessage=new ResultMessage(ResultMessage.Success, "编辑日历成功!");
		}
		catch(Exception ex){
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"编辑日历失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(ex);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
		writeResultMessage(response.getWriter(), resultMessage);	
	}
	

}
