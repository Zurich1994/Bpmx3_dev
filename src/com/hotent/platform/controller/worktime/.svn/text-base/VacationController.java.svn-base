package com.hotent.platform.controller.worktime;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.worktime.Vacation;
import com.hotent.platform.service.worktime.VacationService;

/**
 * 对象功能:法定假期设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:49
 */
@Controller
@RequestMapping("/platform/worktime/vacation/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class VacationController extends BaseController
{
	@Resource
	private VacationService vacationService;
	
	/**
	 * 取得法定假期设置分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看法定假期设置分页列表",detail="查看法定假期设置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Vacation> list=vacationService.getAll(new QueryFilter(request,"vacationItem"));
		ModelAndView mv=this.getAutoView().addObject("vacationList",list);
		
		return mv;
	}
	
	/**
	 * 删除法定假期设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除法定假期设置",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除法定假期设置：" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=vacationService.getById(Long.valueOf(item))/>" +
						"【${entity.name}】" +
					"</#list>"
						)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			vacationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除法定假期设置成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑法定假期设置",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加法定假期设置<#else>" +
					"编辑法定假期设置" +
					"<#assign entity=vacationService.getById(Long.valueOf(id))/>" +
					"【${entity.name}】" +
					"</#if>"
	)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		Vacation vacation=null;
		boolean isadd=true;
		if(id!=0){
			 vacation= vacationService.getById(id);
			 isadd=false;
		}else{
			vacation=new Vacation();
		}
		//添加系统日志信息 -B
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return getAutoView().addObject("vacation",vacation).addObject("returnUrl", returnUrl)
				.addObject("year", year);
	}

	/**
	 * 取得法定假期设置明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看法定假期设置明细",detail="查看法定假期设置明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		Vacation vacation = vacationService.getById(id);
		vacation.setsTime(TimeUtil.getDateTimeString(vacation.getStatTime(), "yyyy-MM-dd"));
		vacation.seteTime(TimeUtil.getDateTimeString(vacation.getEndTime(), "yyyy-MM-dd"));
		return getAutoView().addObject("vacation", vacation).addObject("canReturn",canReturn);
	}

}
