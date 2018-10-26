package com.hotent.platform.controller.system;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysWsLog;
import com.hotent.platform.service.system.SysWsLogService;
import com.hotent.core.web.ResultMessage;
/**
 *<pre>
 * 对象功能:SYS_WS_LOG 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2013-05-31 10:41:48
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysWsLog/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class SysWsLogController extends BaseController
{
	@Resource
	private SysWsLogService sysWsLogService;
	
	
	/**
	 * 添加或更新SYS_WS_LOG。
	 * @param request
	 * @param response
	 * @param SysWsLog 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新SYS_WS_LOG",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>SYS_WS_LOG" +
					"【${SysAuditLinkService.getSysWsLogLink(Long.valueOf(logId))}】"
			)
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String resultMsg=null;		
		SysWsLog sysWsLog=getFormObject(request);
		try{
			SysAuditThreadLocalHolder.putParamerter("isAdd", sysWsLog.getLogId()==null);
			if(sysWsLog.getLogId()==null||sysWsLog.getLogId()==0){
				sysWsLog.setLogId(UniqueIdUtil.genId());
				sysWsLogService.add(sysWsLog);
				resultMsg=getText("SYS_WS_LOG");
			}else{
			    sysWsLogService.update(sysWsLog);
				resultMsg=getText("SYS_WS_LOG");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
			SysAuditThreadLocalHolder.putParamerter("logId", sysWsLog.getLogId().toString());
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得 SysWsLog 实体 
	 * @param request
	 * @return
	 * @throws Exception
	 */
    protected SysWsLog getFormObject(HttpServletRequest request) throws Exception {
    
    	JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher((new String[] { "yyyy-MM-dd" })));
    
		String json=RequestUtil.getString(request, "json");
		if(StringUtil.isEmpty(json))return null;
		JSONObject obj = JSONObject.fromObject(json);
		
		SysWsLog sysWsLog = (SysWsLog)JSONObject.toBean(obj, SysWsLog.class);
		
		return sysWsLog;
    }
	
	/**
	 * 取得SYS_WS_LOG分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看SYS_WS_LOG分页列表",detail="查看SYS_WS_LOG分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysWsLog> list=sysWsLogService.getAll(new QueryFilter(request,"sysWsLogItem"));
		ModelAndView mv=this.getAutoView().addObject("sysWsLogList",list);
		
		return mv;
	}
	
	/**
	 * 删除SYS_WS_LOG
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除SYS_WS_LOG",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除消息日志"+
					"<#list StringUtils.split(id,\",\") as item>" +
					"<#assign entity=sysWsLogService.getById(Long.valueOf(item))/>" +
					"【${entity.callName}】"+
				"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "logId");
			sysWsLogService.delByIds(lAryId); 
			message=new ResultMessage(ResultMessage.Success, "删除SYS_WS_LOG成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑SYS_WS_LOG
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="添加或编辑SYS_WS_LOG",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加SYS_PAUR<#else>编辑SYS_PAUR:" +
					"<#assign entity=sysWsLogService.getById(Long.valueOf(logId))/>" +
					"【${entity.clsName}】</#if>" 
					)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long logId=RequestUtil.getLong(request,"logId",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		SysWsLog sysWsLog=sysWsLogService.getById(logId);
		SysAuditThreadLocalHolder.putParamerter("isAdd", sysWsLog==null);
		return getAutoView().addObject("sysWsLog",sysWsLog)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得SYS_WS_LOG明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看SYS_WS_LOG明细",detail="查看SYS_WS_LOG明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long logId=RequestUtil.getLong(request,"logId");
		SysWsLog sysWsLog = sysWsLogService.getById(logId);	
		return getAutoView().addObject("sysWsLog", sysWsLog);
	}
	
}
