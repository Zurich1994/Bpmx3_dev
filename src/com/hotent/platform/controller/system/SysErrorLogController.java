package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysErrorLog;
import com.hotent.platform.service.system.SysErrorLogService;
/**
 *<pre>
 * 对象功能:系统错误日志 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-07-12 16:42:11
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysErrorLog/")
@Action(ownermodel = SysAuditModelType.SYSTEM_SETTING)
public class SysErrorLogController extends BaseController
{
	@Resource
	private SysErrorLogService sysErrorLogService;
	
	
	

	
	/**
	 * 取得系统错误日志分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看系统错误日志分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysErrorLog> list=sysErrorLogService.getAll(new QueryFilter(request,"sysErrorLogItem"));
		ModelAndView mv=this.getAutoView().addObject("sysErrorLogList",list);
		
		return mv;
	}
	
	/**
	 * 删除系统错误日志
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除系统错误日志",execOrder = ActionExecOrder.BEFORE,
	detail = "删除系统附件" + "<#list StringUtils.split(id,\",\") as item>"
			+ "<#assign entity=sysErrorLogService.getById(Long.valueOf(item))/>" + "【${entity.hashcode}】" + "</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysErrorLogService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除系统错误日志成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	


	/**
	 * 取得系统错误日志明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看系统错误日志明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		SysErrorLog sysErrorLog = sysErrorLogService.getById(id);	
		return getAutoView().addObject("sysErrorLog", sysErrorLog);
	}
	
	@RequestMapping("geterror")
	@ResponseBody
	public String geterror(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		SysErrorLog sysErrorLog = sysErrorLogService.getById(id);
		JSONObject jObject = JSONObject.fromObject(sysErrorLog);
		return jObject.toString();
	}
	
}
