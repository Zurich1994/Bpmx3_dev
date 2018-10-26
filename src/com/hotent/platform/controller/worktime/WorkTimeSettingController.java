package com.hotent.platform.controller.worktime;

import java.util.ArrayList;
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
import com.hotent.platform.model.worktime.WorkTime;
import com.hotent.platform.model.worktime.WorkTimeSetting;
import com.hotent.platform.service.worktime.WorkTimeService;
import com.hotent.platform.service.worktime.WorkTimeSettingService;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

/**
 * 对象功能:班次设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 14:57:31
 */
@Controller
@RequestMapping("/platform/worktime/workTimeSetting/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class WorkTimeSettingController extends BaseController
{
	@Resource
	private WorkTimeSettingService workTimeSettingService;
	
	@Resource
	private WorkTimeService workTimeService;
	
	/**
	 * 取得班次设置分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看班次设置分页列表",detail="查看班次设置分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<WorkTimeSetting> list=workTimeSettingService.getAll(new QueryFilter(request,"workTimeSettingItem"));
		ModelAndView mv=this.getAutoView().addObject("workTimeSettingList",list);
		
		return mv;
	}
	
	/**
	 * 删除班次设置
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除班次设置",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除班次设置：" +
					"<#list StringUtils.split(id,\",\") as item>" +
						"<#assign entity=workTimeSettingService.getById(Long.valueOf(item))/>" +
						"【${entity.name}】" +
					"</#list>"
						)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			
			for(Long settingId:lAryId){
				List<WorkTime> workTimelist = workTimeService.getBySettingId(settingId);
				for(WorkTime worktime:workTimelist){
					workTimeService.delById(worktime.getId());
				}
			}
			
			workTimeSettingService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除班次设置成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑班次设置",
			detail="<#if isAdd>添加编辑班次设置<#else>" +
					"编辑班次设置:" +
					"<#assign entity=workTimeSettingService.getById(Long.valueOf(id))/>" +
					"【${entity.name}】"+
					"</#if>"
					)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		ModelAndView mv = getAutoView();
		String returnUrl=RequestUtil.getPrePage(request);
		mv.addObject("returnUrl", returnUrl);
		
		List<WorkTime> workTimelist = new ArrayList<WorkTime>(); 
		WorkTimeSetting workTimeSetting=null;
		boolean isadd=true;
		if(id!=0){
			workTimeSetting= workTimeSettingService.getById(id);
			workTimelist = workTimeService.getBySettingId(id);
			mv.addObject("workTimelist", workTimelist);
			isadd=false;
		}else{
			workTimeSetting=new WorkTimeSetting();
		}
		//添加系统日志信息 -B
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		mv.addObject("workTimeSetting",workTimeSetting);
		
		return mv; 
	}

	/**
	 * 取得班次设置明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看班次设置明细",detail="查看班次设置明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		WorkTimeSetting workTimeSetting = workTimeSettingService.getById(id);
		
		List<WorkTime> workTimelist = workTimeService.getBySettingId(id);
		
		return getAutoView().addObject("workTimeSetting", workTimeSetting)
				.addObject("workTimelist", workTimelist).addObject("canReturn",canReturn);
	}

}
