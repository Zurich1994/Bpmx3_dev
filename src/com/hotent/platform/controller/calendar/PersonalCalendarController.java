package com.hotent.platform.controller.calendar;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.json.DateJsonValueProcessor;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.calendar.PersonalCalendar;
import com.hotent.platform.service.calendar.PersonalCalendarService;
/**
 *<pre>
 * 对象功能:个人日历 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-03-12 14:50:14
 *</pre>
 */
@Controller
@RequestMapping("/platform/calendar/personalCalendar/")
public class PersonalCalendarController extends BaseController
{
	@Resource
	private PersonalCalendarService personalCalendarService;
	
	
	/**
	 * 添加或更新个人日历。
	 * @param request
	 * @param response
	 * @param personalCalendar 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新个人日历")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		try{
			PersonalCalendar personalCalendar= getFormObject(request);
			personalCalendar.setCreateBy(ContextUtil.getCurrentUserId());
			if(personalCalendar.getId()==null||personalCalendar.getId()==0){
				personalCalendar.setId(UniqueIdUtil.genId());
				personalCalendar.setCreatetime(new Date());
				personalCalendarService.add(personalCalendar);
				resultMsg=getText("添加","个人日历");
			}else{
				
			    personalCalendarService.update(personalCalendar);
				resultMsg=getText("更新","个人日历");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	private PersonalCalendar getFormObject(HttpServletRequest request) {
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] {"HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" })));
        
		String json=RequestUtil.getString(request, "json");
		JSONObject obj = JSONObject.fromObject(json);
		
		PersonalCalendar personalCalendar = (PersonalCalendar)JSONObject.toBean(obj, PersonalCalendar.class);
		
		return personalCalendar;
	}


	/**
	 * 取得个人日历分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看个人日历分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<PersonalCalendar> list=personalCalendarService.getAll(new QueryFilter(request,"personalCalendarItem"));
		ModelAndView mv=this.getAutoView().addObject("personalCalendarList",list);
		
		return mv;
	}
	
	/**
	 * 删除个人日历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除个人日历")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			personalCalendarService.delByIds(lAryId);
			writeResultMessage(response.getWriter(),"删除个人日历成功!",ResultMessage.Success);
		}catch(Exception ex){
			 ex.getMessage();
			writeResultMessage(response.getWriter(),"删除失败!",ResultMessage.Fail);
		}
	}
	
	/**
	 * 	编辑个人日历
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑个人日历")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		PersonalCalendar personalCalendar=personalCalendarService.getById(id);
		
		return getAutoView().addObject("personalCalendar",personalCalendar)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得个人日历明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看个人日历明细")
	public void get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		PersonalCalendar personalCalendar = personalCalendarService.getById(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		response.getWriter().print(JSONObject.fromObject(personalCalendar,jsonConfig));
	}
	
}
