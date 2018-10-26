package com.hotent.platform.controller.worktime;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
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
public class WorkTimeController extends BaseController
{
	@Resource
	private WorkTimeService workTimeService;
	
	/**
	 * 取得班次时间分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看班次时间分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String setId = request.getParameter("settingId");
		Long settingId = new Long(request.getParameter("settingId"));
		QueryFilter query = new QueryFilter(request,"workTimeItem");
		query.addFilter("settingId", settingId);
		List<WorkTime> list=workTimeService.getAll(query);
		ModelAndView mv=this.getAutoView().addObject("workTimeList",list);
		
		return mv;
	}
	
	/**
	 * 删除班次时间
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除班次时间")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			workTimeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除班次时间成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑班次时间")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		WorkTime workTime=null;
		if(id!=0){
			 workTime= workTimeService.getById(id);
		}else{
			workTime=new WorkTime();
		}
		return getAutoView().addObject("workTime",workTime).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得班次时间明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看班次时间明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		WorkTime workTime = workTimeService.getById(id);		
		return getAutoView().addObject("workTime", workTime);
	}

}
