
package com.hotent.platform.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.SysAudit;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.SysAuditService;

/**
 * 对象功能:系统日志 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-26 11:35:04
 */
@Controller
@RequestMapping("/platform/system/sysAudit/")
@Action(ownermodel = SysAuditModelType.SYSTEM_SETTING)
public class SysAuditController extends BaseController
{
	@Resource
	private SysAuditService sysAuditService;
	
	
	/**
	 * 取得系统日志分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	//@Action(description="查看系统日志分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysAudit> list=sysAuditService.getAll(new QueryFilter(request,"sysAuditItem"));
		ModelAndView mv=this.getAutoView().addObject("sysAuditList",list);
		
		return mv;
	}
	
	/**
	 * 删除系统日志
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除系统日志",execOrder = ActionExecOrder.BEFORE,
	detail = "删除系统附件" + "<#list StringUtils.split(auditId,\",\") as item>"
			+ "<#assign entity=sysAuditService.getById(Long.valueOf(item))/>" + "【${entity.opName}】" + "</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ResultMessage message=null;
		String preUrl= RequestUtil.getPrePage(request);
		try {
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "auditId");
			sysAuditService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除系统日志成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除系统日志失败");
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 添加SysAudit实体
	 * @param sysAudit
	 * @throws Exception
	 */
	//@Action(description="添加系统日志")
	public void add(SysAudit sysAudit) throws Exception 
	{
		sysAudit.setAuditId(UniqueIdUtil.genId());
		sysAuditService.add(sysAudit);
	}
	/**
	 * 更新SysAudit实体
	 * @param sysAudit
	 * @throws Exception
	 */
	//@Action(description="更新系统日志")
	public void upd(SysAudit sysAudit) throws Exception 
	{
		sysAuditService.update(sysAudit);
	}
	
	@RequestMapping("edit")
	//@Action(description="编辑系统日志")
	public ModelAndView edit(HttpServletRequest request, @RequestParam(value="auditId") Long auditId) throws Exception
	{
		String returnUrl=RequestUtil.getPrePage(request);
		SysAudit po=null;
		if(auditId!=null){
			 po= sysAuditService.getById(auditId);
		}else{
			po=new SysAudit();
		}
		return getAutoView().addObject("sysAudit",po).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得系统日志明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	//@Action(description="查看系统日志明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"auditId");
		SysAudit po = sysAuditService.getById(id);		
		return getAutoView().addObject("sysAudit", po);
		
	}
}
