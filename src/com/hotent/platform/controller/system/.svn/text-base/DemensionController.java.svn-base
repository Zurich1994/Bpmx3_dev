package com.hotent.platform.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.Demension;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.DemensionService;

/**
 * 对象功能:SYS_DEMENSION 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-01 14:23:26
 */
@Controller
@RequestMapping("/platform/system/demension/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class DemensionController extends BaseController
{
	@Resource
	private DemensionService demensionService;
	
	/**
	 * 取得SYS_DEMENSION分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看SYS_DEMENSION分页列表",
			execOrder=ActionExecOrder.AFTER,
			detail="查看SYS_DEMENSION分页列表",
			exectype="管理日志")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Demension> list=demensionService.getDemenByQuery(new QueryFilter(request,"demensionItem"));
		ModelAndView mv=this.getAutoView().addObject("demensionList",list);
		
		return mv;
	}
	
	/**
	 * 删除SYS_DEMENSION
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除SYS_DEMENSION",execOrder=ActionExecOrder.BEFORE,
			detail="删除SYS_DEMENSION"+
					"<#list StringUtils.split(demId,\",\") as item>" +
					"<#assign entity=demensionService.getById(Long.valueOf(item))/>" +
					"【${entity.demName}】"+
					"</#list>",
			exectype="管理日志")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ResultMessage message=null;
		String preUrl= RequestUtil.getPrePage(request);
		try {
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "demId");
			demensionService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除维度成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除维度失败");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="添加或编辑SYS_DEMENSION",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加SYS_DEMENSION<#else>编辑SYS_DEMENSION" +
					"<#assign entity=demensionService.getById(Long.valueOf(demId))/>" +
					"【${entity.demName}】</#if>",
			exectype="管理日志")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long demId=RequestUtil.getLong(request,"demId");
		Demension demension=null;
		boolean isadd=true;
		if(demId!=0){
			 demension= demensionService.getById(demId);
			 isadd=false;
		}else{
			demension=new Demension();
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return getAutoView().addObject("demension",demension)
				.addObject("demId", demId);
	}

	/**
	 * 取得SYS_DEMENSION明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看SYS_DEMENSION明细",
			execOrder=ActionExecOrder.AFTER,
			detail="查看SYS_DEMENSION明细",
			exectype="管理日志")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"demId");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		Demension demension = demensionService.getById(id);		
		return getAutoView().addObject("demension", demension).addObject("canReturn", canReturn);
	}

}
