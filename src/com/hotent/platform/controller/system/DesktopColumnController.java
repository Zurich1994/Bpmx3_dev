package com.hotent.platform.controller.system;


import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmFormQuery;
import com.hotent.platform.model.form.QueryResult;
import com.hotent.platform.model.system.DesktopColumn;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.bpm.BpmFormQueryService;
import com.hotent.platform.service.system.DesktopColumnService;

/**
 * 对象功能:桌面栏目 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
@Controller
@RequestMapping("/platform/system/desktopColumn/")
@Action(ownermodel=SysAuditModelType.DESKTOP_MANAGEMENT)
public class DesktopColumnController extends BaseController
{
	@Resource 
	private FreemarkEngine freemarkEngine;
	@Resource
	private DesktopColumnService desktopColumnService;
	@Resource
	private BpmFormQueryService bpmFormQueryService;
	
	/**
	 * 取得桌面栏目分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看桌面栏目分页列表",detail="查看桌面栏目分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<DesktopColumn> list=desktopColumnService.getAll(new QueryFilter(request,"desktopColumnItem"));
		ModelAndView mv=this.getAutoView().addObject("desktopColumnList",list);
		
		return mv;
	}
	
	/**
	 * 删除桌面栏目
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除桌面栏目",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除桌面栏目:" +
					"<#list StringUtils.split(id,\",\") as item>" +
					"<#assign entity=desktopColumnService.getById(Long.valueOf(item))/>" +
					"【${entity.columnName}】" +
				"</#list>"
			)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			desktopColumnService.delDesktopColumn(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除桌面栏目成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑桌面栏目
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("edit")
	@Action(description="编辑桌面栏目",
			detail="<#if isAdd>添加桌面栏目<#else>" +
					"编辑桌面栏目:" +
					"<#assign entity=desktopColumnService.getById(Long.valueOf(id))/>" +
					"【${entity.columnName}】"+
					"</#if>"
					)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		boolean isadd=true;
		DesktopColumn desktopColumn=null;
		if(id!=0){
			 desktopColumn= desktopColumnService.getById(id);
			 isadd=false;
		}else{
			desktopColumn=new DesktopColumn();
			isadd=true;
		} 
		try {
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		
		return getAutoView().addObject("desktopColumn",desktopColumn).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得桌面栏目明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看桌面栏目明细",detail="查看桌面栏目明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		String returnUrl=RequestUtil.getPrePage(request);
		DesktopColumn desktopColumn = desktopColumnService.getById(id);		
		return getAutoView().addObject("desktopColumn", desktopColumn).addObject("returnUrl", returnUrl).addObject("canReturn",canReturn);
	}
	
	/**
	 * 取得桌面栏目模板
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getTemp")
	public ModelAndView getTemp(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		DesktopColumn desktopColumn = desktopColumnService.getById(id);
		
		return getAutoView().addObject("column", desktopColumn);
	}
	
	/**
	 * 获取栏目数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getColumnData")
	@ResponseBody
	public Map getColumnData(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		String ctxPath=request.getContextPath();
		DesktopColumn desktopColumn = desktopColumnService.getById(id);
		String html="";
		Object model=null;
		QueryResult queryResult=null;
		if(desktopColumn.getMethodType()==0){
			String handler=desktopColumn.getServiceMethod();//接口方法
			model= desktopColumnService.getModelByHandler(handler);
		}else{
			String alias=desktopColumn.getQueryAlias();
			BpmFormQuery query=bpmFormQueryService.getByAlias(alias);
			queryResult=bpmFormQueryService.getData(query, null, 0, 0);
		}
		Map map=new HashMap();
		map.put("column", desktopColumn);
		if(model!=null){
			map.put("model",model);//栏目名称
			map.put("ctxPath",ctxPath );
			html=freemarkEngine.parseByStringTemplate(map, desktopColumn.getHtml());
			map.put("html", html);
		}else{
			map.put("result", queryResult);
		}
		return map;
	}
	
	/**
	 * 取得初始化模板信息
	 */
	@RequestMapping("init")
	@Action(description="初始化模板",detail="初始化模板")
	public void init(HttpServletRequest request,HttpServletResponse response)throws Exception
	{	
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			desktopColumnService.initAllTemplate(ContextUtil.getCurrentUserId());
			message=new ResultMessage(ResultMessage.Success, "初始化表单模板成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "初始化表单模板失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
}
