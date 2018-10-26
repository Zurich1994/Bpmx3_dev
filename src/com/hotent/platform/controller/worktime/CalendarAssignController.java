package com.hotent.platform.controller.worktime;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.worktime.CalendarAssign;
import com.hotent.platform.model.worktime.SysCalendar;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.worktime.CalendarAssignService;
import com.hotent.platform.service.worktime.SysCalendarService;
import com.hotent.core.annotion.ActionExecOrder;

/**
 * 对象功能:日历分配 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:51
 */
@Controller
@RequestMapping("/platform/worktime/calendarAssign/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class CalendarAssignController extends BaseController
{
	@Resource
	private CalendarAssignService calendarAssignService;
	@Resource
	private SysCalendarService sysCalendarService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysOrgService sysOrgService;
	
	/**
	 * 取得日历分配分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看日历分配分页列表",detail="查看日历分配分页类表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<CalendarAssign> list=calendarAssignService.getAll(new QueryFilter(request,"calendarAssignItem"));
		ModelAndView mv=this.getAutoView().addObject("calendarAssignList",list);

		// 判断是否存在工作日历
		String flag = RequestUtil.getString(request, "flag");
		if(flag.equals("1")){
			mv.addObject("flag", flag);
		}
		
		return mv;
	}
	
	/**
	 * 删除日历分配。
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除日历分配",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除日历分配：" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign calendarAssign=calendarAssignService.getById(Long.valueOf(item))/>" +
						"<#assign sysCalendar=sysCalendarService.getById(calendarAssign.canlendarId)/>" +
						"<#if calendarAssign.assignType==1>" +
							"<#assign assign=sysUserService.getById(calendarAssign.assignId)/>" +
						"<#elseif calendarAssign.assignType==2>" +
							"<#assign assign=sysOrgService.getById(calendarAssign.assignId)/>" +
						"</#if>" +
						"【" +
						"工作日历:${sysCalendar.name} , " +
						"被分配人类型：<#if calendarAssign.assignType==1>人员<#elseif calendarAssign.assignType==2>组织</#if> , "+
						"被分配人名称：<#if calendarAssign.assignType==1>${assign.fullname}<#elseif calendarAssign.assignType==2>${assign.orgName}</#if>" +
						"】"+
					"</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			calendarAssignService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除日历分配成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑日历分配",detail="编辑日历分配")
	public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		CalendarAssign calendarAssign=null;
		if(id!=0){
			calendarAssign= calendarAssignService.getById(id);
			if(calendarAssign.getAssignType()==1){// 用户
				SysUser sysUser = sysUserService.getById(calendarAssign.getAssignId());
				calendarAssign.setAssignUserName(sysUser.getFullname());
			}else{// 组织
				SysOrg sysOrg = sysOrgService.getById(calendarAssign.getAssignId());
				calendarAssign.setAssignUserName(sysOrg.getOrgName());
			}
		}else{
			calendarAssign=new CalendarAssign();
		}
		
		List<Map> typelist = calendarAssignService.getAssignUserType();
		
		List<SysCalendar> callist = sysCalendarService.getAll();
		
		if(callist.size()>0){
			return getAutoView().addObject("calendarAssign",calendarAssign).addObject("returnUrl", returnUrl)
					.addObject("callist", callist).addObject("typelist", typelist);
		}else{

			response.sendRedirect(request.getContextPath()+"/platform/worktime/calendarAssign/list.ht?flag=1");
			return null;
		}
	}

	/**
	 * 取得日历分配明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看日历分配明细",detail="查看日历分配明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		CalendarAssign calendarAssign = calendarAssignService.getById(id);
		SysCalendar sysCal = sysCalendarService.getById(calendarAssign.getCanlendarId());
		calendarAssign.setCalendarName(sysCal.getName());
		if(calendarAssign.getAssignType()==1){// 用户
			SysUser sysUser = sysUserService.getById(calendarAssign.getAssignId());
			calendarAssign.setAssignUserName(sysUser.getFullname());
		}else{// 组织
			SysOrg sysOrg = sysOrgService.getById(calendarAssign.getAssignId());
			calendarAssign.setAssignUserName(sysOrg.getOrgName());
		}
		return getAutoView().addObject("calendarAssign", calendarAssign).addObject("canReturn",canReturn);
	}

}
