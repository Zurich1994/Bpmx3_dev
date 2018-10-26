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
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.worktime.CalendarAssign;
import com.hotent.platform.service.worktime.CalendarAssignService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

/**
 * 对象功能:日历分配 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:51
 */
@Controller
@RequestMapping("/platform/worktime/calendarAssign/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class CalendarAssignFormController extends BaseFormController
{
	@Resource
	private CalendarAssignService calendarAssignService;
	
	/**
	 * 添加或更新日历分配。
	 * @param request
	 * @param response
	 * @param calendarAssign 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(
			description="添加或更新日历分配",
			execOrder=ActionExecOrder.AFTER,
			detail= "<#if isAdd>添加<#else>更新</#if>日历分配" +
					"<#assign sysCalendar=sysCalendarService.getById(calendarId)/>" +
					"【" +
					"工作日历:${SysAuditLinkService.getSysCalendarLink(Long.valueOf(calendarId))}, " +
					"被分配人类型：<#if calendarAssign.assignType==1>人员," +
					"被分配人名称：${calendarAssign.assignUserName}" +
					"<#elseif calendarAssign.assignType==2>组织,"+
					"被分配组织名称：${calendarAssign.assignUserName}</#if>" +
					"】"
	)
	public void save(HttpServletRequest request, HttpServletResponse response, CalendarAssign calendarAssign,BindingResult bindResult) throws Exception
	{
		//添加系统日志信息 -B
				try {
					SysAuditThreadLocalHolder.putParamerter("calendarAssign", calendarAssign);
					SysAuditThreadLocalHolder.putParamerter("calendarId", calendarAssign.getCanlendarId().toString());
					SysAuditThreadLocalHolder.putParamerter("isAdd", calendarAssign.getId()==null);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
		ResultMessage resultMessage=validForm("calendarAssign", calendarAssign, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg="";
		if(calendarAssign.getId()==null){
			String userid = RequestUtil.getString(request, "assignUid");
			String assignUserName = RequestUtil.getString(request, "assignUserName");
			String[] userids = userid.split(",");
			String[] userNames = assignUserName.split(",");
			for(int idx=0;idx<userids.length;idx++){
				CalendarAssign calAss = calendarAssignService.getbyAssignId(new Long(userids[idx]));
				if(calAss==null){
					CalendarAssign ca = new CalendarAssign();
					ca.setId(UniqueIdUtil.genId());
					ca.setCanlendarId(calendarAssign.getCanlendarId());
					ca.setAssignType(calendarAssign.getAssignType());
					ca.setAssignId(new Long(userids[idx]));
					calendarAssignService.add(ca);
				}else{
					
					resultMsg += userNames[idx]+",";
				}
			}
		}
		
		if(resultMsg.length()==0){
			
			resultMsg="日历分配成功";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}else{
			
			resultMsg += "已分配工作日历";
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
		}
		
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected CalendarAssign getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter CalendarAssign getFormObject here....");
		CalendarAssign calendarAssign=null;
		if(id!=null){
			calendarAssign=calendarAssignService.getById(id);
		}else{
			calendarAssign= new CalendarAssign();
		}
		return calendarAssign;
    }

}
