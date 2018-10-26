package com.hotent.platform.controller.ats;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsCalendarTempl;
import com.hotent.platform.model.ats.AtsLegalHoliday;
import com.hotent.platform.model.ats.AtsWorkCalendar;
import com.hotent.platform.service.ats.AtsCalendarTemplService;
import com.hotent.platform.service.ats.AtsLegalHolidayService;
import com.hotent.platform.service.ats.AtsWorkCalendarService;
/**
 *<pre>
 * 对象功能:工作日历 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-17 15:46:19
 *</pre>
 */
@Controller
@RequestMapping("/platform/ats/atsWorkCalendar/")
public class AtsWorkCalendarController extends BaseController
{
	@Resource
	private AtsWorkCalendarService atsWorkCalendarService;
	@Resource
	private AtsCalendarTemplService atsCalendarTemplService;
	@Resource
	private AtsLegalHolidayService atsLegalHolidayService;
	
	/**
	 * 添加或更新工作日历。
	 * @param request
	 * @param response
	 * @param atsWorkCalendar 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新工作日历")
	public void save(HttpServletRequest request, HttpServletResponse response,AtsWorkCalendar atsWorkCalendar) throws Exception
	{
		String resultMsg=null;		
		try{
			if(atsWorkCalendar.getId()==null||atsWorkCalendar.getId()==0){
				resultMsg=getText("添加","工作日历");
			}else{
				resultMsg=getText("更新","工作日历");
			}
		    atsWorkCalendarService.save(atsWorkCalendar);
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得工作日历分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看工作日历分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsWorkCalendar> list=atsWorkCalendarService.getAll(new QueryFilter(request,"atsWorkCalendarItem"));
		for (AtsWorkCalendar atsWorkCalendar : list) {
			setAtsWorkCalendar(atsWorkCalendar);
		}
		return this.getAutoView().addObject("atsWorkCalendarList",list);
	}
	
	/**
	 * 删除工作日历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除工作日历")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			atsWorkCalendarService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除工作日历成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑工作日历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑工作日历")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		AtsWorkCalendar atsWorkCalendar=atsWorkCalendarService.getById(id);
		setAtsWorkCalendar(atsWorkCalendar);
		return getAutoView().addObject("atsWorkCalendar",atsWorkCalendar)
							.addObject("returnUrl",returnUrl);
	}
	


	private void setAtsWorkCalendar(AtsWorkCalendar atsWorkCalendar) {
		if(BeanUtils.isEmpty(atsWorkCalendar))
			return;
		if(BeanUtils.isNotEmpty(atsWorkCalendar.getCalendarTempl())){
			AtsCalendarTempl atsCalendarTempl = atsCalendarTemplService.getById(atsWorkCalendar.getCalendarTempl());
			if(BeanUtils.isNotEmpty(atsCalendarTempl))
				atsWorkCalendar.setCalendarTemplName(atsCalendarTempl.getName());
		}
		if(BeanUtils.isNotEmpty(atsWorkCalendar.getLegalHoliday())){
			AtsLegalHoliday atsLegalHoliday = atsLegalHolidayService.getById(atsWorkCalendar.getLegalHoliday());
			if(BeanUtils.isNotEmpty(atsLegalHoliday))
				atsWorkCalendar.setLegalHolidayName(atsLegalHoliday.getName());
		}
	}


	/**
	 * 取得工作日历明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看工作日历明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		AtsWorkCalendar atsWorkCalendar = atsWorkCalendarService.getById(id);	
		return getAutoView().addObject("atsWorkCalendar", atsWorkCalendar);
	}
	/**
	 * 取得工作日历分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description="查看工作日历分页列表")
	public ModelAndView selector(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<AtsWorkCalendar> list=atsWorkCalendarService.getAll(new QueryFilter(request,"atsWorkCalendarItem"));
		for (AtsWorkCalendar atsWorkCalendar : list) {
			setAtsWorkCalendar(atsWorkCalendar);
		}
		return this.getAutoView().addObject("atsWorkCalendarList",list);
	}
}

