<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign comment=model.tabComment>
<#assign subtables=model.subTableList>
<#assign classVar=model.variables.classVar>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
package ${vars.packagePre}.${system}.controller.${package};

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ${vars.packagePre}.core.annotion.Action;
import ${vars.packagePre}.core.page.PageList;
import ${vars.packagePre}.core.util.BeanUtils;
import ${vars.packagePre}.core.web.ResultMessage;
import ${vars.packagePre}.core.web.controller.BaseController;
import ${vars.packagePre}.core.web.query.QueryFilter;
import ${vars.packagePre}.core.web.util.RequestUtil;
import ${vars.packagePre}.core.util.ContextUtil;
import ${vars.packagePre}.platform.model.system.SysUser;

<#if func.supportFlow(model)>
import ${vars.packagePre}.platform.model.bpm.ProcessRun;
import ${vars.packagePre}.platform.service.bpm.ProcessRunService;
import ${vars.packagePre}.core.bpm.BpmAspectUtil;
</#if>

import ${vars.packagePre}.${system}.model.${package}.${class};
import ${vars.packagePre}.${system}.service.${package}.${class}Service;
<#if func.isSubTableExist( subtables)>
	<#list subtables as table>
import ${vars.packagePre}.${system}.model.${table.variables.package}.${table.variables.class};
	</#list>
</#if>


/**
 *<pre>
 * 对象功能:${comment} 控制器类
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 *</pre>
 */
@Controller
@RequestMapping("/${system}/${package}/${classVar}/")
public class ${class}Controller extends BaseController
{
	@Resource
	private ${class}Service ${classVar}Service;
	<#--直接绑定工作流生成-->
	<#if func.supportFlow(model)>
	private final String flowKey = "${model.variables.flowKey}";	//绑定流程定义
	@Resource
	private ProcessRunService processRunService;
	</#if>
	
	/**
	 * 取得${comment}分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getList")
	@Action(description="查看${comment}分页列表")
	@ResponseBody
	public Object getList(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		PageList list=(PageList)${classVar}Service.getAll(new QueryFilter(request,true));
		return getMapByPageList(list);
	}
	
	/**
	 * 删除${comment}
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除${comment}")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "${pkVar}");
			${classVar}Service.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除${comment}成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	
	/**
	 * 	编辑${comment}
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑${comment}")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		<#if func.supportFlow(model)>
		Long taskId=RequestUtil.getLong(request,"taskId",0L);
		Long runId=RequestUtil.getLong(request,"runId",0L);
		</#if>
		Long ${pkVar}=RequestUtil.getLong(request,"${pkVar}",0L);
		String returnUrl=RequestUtil.getPrePage(request);
		
		${class} ${classVar}=${classVar}Service.getById(${pkVar});
		
		<#if func.isSubTableExist( subtables)>
		<#list subtables as table>
		    <#assign vars=table.variables>
		List<${table.variables.class}> ${table.variables.classVar}List=${classVar}Service.get${vars.classVar?cap_first}List(${pkVar});
		    </#list>
		</#if>
		
		
		ModelAndView mv=getAutoView();
		mv.addObject("${classVar}",${classVar})
		<#if func.isSubTableExist( subtables)>
		     <#list subtables as table>
							.addObject("${table.variables.classVar}List",${table.variables.classVar}List)
		    </#list>
		</#if>
							.addObject("returnUrl",returnUrl)
							<#if func.supportFlow(model)>
							.addObject("flowKey",flowKey)
							.addObject("taskId",taskId)
							</#if>;
		<#if func.supportFlow(model)>
		//审批
		if(taskId>0){
			Map<String,Object> flowMap= BpmAspectUtil.getModelByTaskId(taskId);
			mv.addAllObjects(flowMap);
		}
		//草稿的状态
		else if(runId>0){
			Map<String,Object> flowMap= BpmAspectUtil.getModelByRunId(runId);
			mv.addAllObjects(flowMap);
		}
		</#if>
		return mv;
	}
	
	/**
	 * 取得${comment}明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看${comment}明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long ${pkVar}=RequestUtil.getLong(request,"${pkVar}");
		${class} ${classVar} = ${classVar}Service.getById(${pkVar});	
		<#if func.supportFlow(model)>
		ProcessRun processRun=processRunService.getByBusinessKey(id.toString());
		</#if>
		
		ModelAndView mv= getAutoView().addObject("${classVar}",${classVar});
		<#if func.isSubTableExist( subtables)>
		<#list subtables as table>
		    <#assign vars=table.variables>
		List<${vars.class}> ${vars.classVar}List=${classVar}Service.get${vars.class}List(${pkVar});
		mv.addObject("${vars.classVar}List",${vars.classVar}List);
		    </#list>
		</#if>
		
		
		<#if func.supportFlow(model)>
		if(processRun!=null){
			mv.addObject("processRun",processRun);
		}
		</#if>
		return mv;
	}
	
	<#if func.supportFlow(model)>
	
	@RequestMapping("getMyTodoTaskJson")
	@Action(description="查看我的${comment}任务分页列表")
	@ResponseBody
	public Map<String,Object> getMyTodoTaskJson(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		SysUser sysUser=ContextUtil.getCurrentUser();
		Long userId=sysUser.getUserId();
		PageList list=(PageList) ${classVar}Service.getMyTodoTask(userId, new QueryFilter(request,true));
		
		return getMapByPageList(list);
	}
	
	@RequestMapping("getMyDraftJson")
	@Action(description="查看我的${comment}草稿")
	@ResponseBody
	public Map<String,Object> getMyDraftJson(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		SysUser sysUser=ContextUtil.getCurrentUser();
		Long userId=sysUser.getUserId();
		PageList list=(PageList) ${classVar}Service.getMyDraft(userId, new QueryFilter(request,true));
		
		return getMapByPageList(list);
	}
	
	@RequestMapping("getMyEndJson")
	@Action(description="查看我结束的${comment}实例")
	@ResponseBody
	public Map<String,Object> getMyEndJson(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		SysUser sysUser=ContextUtil.getCurrentUser();
		Long userId=sysUser.getUserId();
		PageList list=(PageList) ${classVar}Service.getMyEnd(userId, new QueryFilter(request,true));
		return getMapByPageList(list);
	}
	
	</#if>
	
}
