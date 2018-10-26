package com.hotent.platform.controller.worktime;

import java.util.Date;
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
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.worktime.OverTime;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.worktime.OverTimeService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

/**
 * 对象功能:加班情况 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:52
 */
@Controller
@RequestMapping("/platform/worktime/overTime/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class OverTimeController extends BaseController
{
	@Resource
	private OverTimeService overTimeService;
	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 取得加班情况分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看加班情况分页列表",detail="查看加班情况分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<OverTime> list=overTimeService.getAll(new QueryFilter(request,"overTimeItem"));
		ModelAndView mv=this.getAutoView().addObject("overTimeList",list);
		
		return mv;
	}
	
	/**
	 * 删除加班情况
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(
			description="删除加班请假管理",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除加班请假管理" +
			"<#list StringUtils.split(id,\",\") as item>" +
				"<#assign entity=overTimeService.getById(Long.valueOf(item))/>" +
				"【${entity.subject}】" +
			"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			overTimeService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除加班情况成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑加班情况",
			detail="<#if isAdd>添加加班情况<#else>" +
					"编辑加班情况:" +
					"<#assign entity=overTimeService.getById(Long.valueOf(id))/>" +
					"【${entity.subject}】</#if>"
					)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		OverTime overTime=null;
		boolean isadd=true;
		if(id!=0){
			overTime= overTimeService.getById(id);
			SysUser sysUser = sysUserService.getById(overTime.getUserId());
			overTime.setUserName(sysUser.getFullname());
			isadd=false;
		}else{
			overTime=new OverTime();
			Date curdate = new Date();
			overTime.setStartTime(curdate);
			overTime.setEndTime(curdate);
		}
		List typelist = overTimeService.getWorkType();
		//添加系统日志信息 -B
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return getAutoView().addObject("overTime",overTime).addObject("returnUrl", returnUrl)
				.addObject("typelist", typelist);
	}

	/**
	 * 取得加班情况明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看加班情况明细",detail="查看加班情况明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		OverTime overTime = overTimeService.getById(id);
		SysUser sysUser = sysUserService.getById(overTime.getUserId());
		overTime.setUserName(sysUser.getFullname());
		return getAutoView().addObject("overTime", overTime).addObject("canReturn",canReturn);
	}
}
