package com.hotent.platform.controller.worktime;

import java.util.Calendar;
import java.util.HashMap;
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
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.worktime.CalendarSetting;
import com.hotent.platform.model.worktime.SysCalendar;
import com.hotent.platform.model.worktime.WorkTimeSetting;
import com.hotent.platform.service.worktime.CalendarAssignService;
import com.hotent.platform.service.worktime.CalendarSettingService;
import com.hotent.platform.service.worktime.SysCalendarService;
import com.hotent.platform.service.worktime.VacationService;
import com.hotent.platform.service.worktime.WorkTimeSettingService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.platform.model.system.SysAuditModelType;


/**
 * 对象功能:系统日历 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:32
 */
@Controller
@RequestMapping("/platform/worktime/sysCalendar/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class SysCalendarController extends BaseController
{
	@Resource
	private SysCalendarService sysCalendarService;
	
	@Resource
	private WorkTimeSettingService workTimeSettingService;
	
	@Resource
	private CalendarSettingService calendarSettingService;
	
	@Resource
	private VacationService vacationService;
	
	@Resource
	private CalendarAssignService calendarAssignService;
	
	/**
	 * 取得系统日历分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看系统日历分页列表",detail="查看系统日历分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysCalendar> list=sysCalendarService.getAll(new QueryFilter(request,"sysCalendarItem"));
		ModelAndView mv=this.getAutoView().addObject("sysCalendarList",list);
		
		return mv;
	}
	
	/**
	 * 删除系统日历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(
			description="删除系统日历",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除系统日历：" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=sysCalendarService.getById(Long.valueOf(item))/>" +
						"【${entity.name}】" +
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			calendarAssignService.delByCalId(lAryId);
			calendarSettingService.delByCalId(lAryId);
			sysCalendarService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除系统日历成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	

	@RequestMapping("edit")
	@Action(description="编辑系统日历",
			detail="<#if isAdd>添加系统日历 <#else>"+
					"编辑系统日历:" +
					"<#assign entity=sysCalendarService.getById(Long.valueOf(id))/>" +
					"【${entity.name}】</#if>" 
					)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id = RequestUtil.getLong(request,"id");

		Calendar cal = Calendar.getInstance();
		int defalutYear=cal.get(Calendar.YEAR);
		int defaultMon=cal.get(Calendar.MONTH) + 1;

		int year = RequestUtil.getInt(request,"wtYear",defalutYear);
		int mon = RequestUtil.getInt(request,"wtMon",defaultMon);
		String flag = RequestUtil.getString(request,"flag");
		
		if(StringUtils.isNotEmpty(flag)){
			if(flag.equals("next")){
				year = mon==12?year+=1:year;
				mon = mon==12?mon=1:mon+1;
			}else{
				year = mon==1?year-1:year;
				mon = mon==1?mon=12:mon-1;
			}
		}
		//取得本月的天数
		int mondDays =TimeUtil.getDaysOfMonth(year, mon);
		//取得当月第一天为星期几
		int firstDay=TimeUtil.getWeekDayOfMonth(year, mon);
	
		SysCalendar sysCalendar=new SysCalendar();
		// 取法定节假日
		Map<Integer, String> vacationMap = vacationService.getByYearMon(year+"-"+mon+"-01", year+"-"+mon+"-"+mondDays);
		
		boolean monthFlag = false;;
		
		Map<Integer,CalendarSetting> settingMap=new HashMap<Integer, CalendarSetting>();
		boolean isadd=true;
		if(id!=0){
			sysCalendar= sysCalendarService.getById(id);
			//获取当月日历
			List<CalendarSetting> calSetlist   = calendarSettingService.getCalByIdYearMon(id, year, mon);
			if(calSetlist!=null&&calSetlist.size()>0) monthFlag=true;
			settingMap=convertList2Map(calSetlist);
			isadd=false;
		}
		// 取班次
		List<WorkTimeSetting> wtSetting = workTimeSettingService.getAll();
		// 年下拉框
		List<String> yearlist = DateUtil.getUpDownFiveYear(cal);
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return getAutoView()
				.addObject("sysCalendar",sysCalendar)
				.addObject("settingMap", settingMap)
				.addObject("vacationMap", vacationMap)
				.addObject("wtYear", year)
				.addObject("wtMon", mon)
				.addObject("wtSetting",wtSetting)
				.addObject("yearlist",yearlist)
				.addObject("monthFlag", monthFlag)
				.addObject("mondDays", mondDays)
				.addObject("firstDay", firstDay);
	}
	
	private Map<Integer,CalendarSetting> convertList2Map(List<CalendarSetting> calSetlist){
		Map<Integer,CalendarSetting> map=new HashMap<Integer, CalendarSetting>();
		for(CalendarSetting setting:calSetlist){
			map.put((int)setting.getDays(),setting);
		}
		return map;
	}

	/**
	 * 取得系统日历明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看系统日历明细",detail="查看系统日历明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		SysCalendar sysCalendar = sysCalendarService.getById(id);		
		return getAutoView().addObject("sysCalendar", sysCalendar).addObject("canReturn",canReturn);
	}
	
	/**
	 * 设置默认日历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("setDefault")
	@Action(description="设置默认日历")
	public void setDefault(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		Long id = RequestUtil.getLong(request, "id");
		sysCalendarService.setDefaultCal(id);
		addMessage(new ResultMessage(ResultMessage.Success,"设置默认日历成功!"), request);
		response.sendRedirect(preUrl);
	}
	
}
