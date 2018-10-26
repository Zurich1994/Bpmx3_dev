package com.hotent.platform.controller.worktime;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
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
public class CalendarSettingController extends BaseController
{
	@Resource
	private CalendarSettingService calendarSettingService;
	
	/**
	 * 取得日历设置分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看日历设置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<CalendarSetting> list=calendarSettingService.getAll(new QueryFilter(request,"calendarSettingItem"));
		ModelAndView mv=this.getAutoView().addObject("calendarSettingList",list);
		
		return mv;
	}
	
	/**
	 * 删除日历设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除日历设置")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			calendarSettingService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除日历设置成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑日历设置")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		CalendarSetting calendarSetting=null;
		if(id!=0){
			 calendarSetting= calendarSettingService.getById(id);
		}else{
			calendarSetting=new CalendarSetting();
		}
		return getAutoView().addObject("calendarSetting",calendarSetting).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得日历设置明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看日历设置明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		CalendarSetting calendarSetting = calendarSettingService.getById(id);		
		return getAutoView().addObject("calendarSetting", calendarSetting);
	}

}
