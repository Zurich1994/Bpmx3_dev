<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign comment=model.tabComment>
<#assign subtables=model.subTableList>
<#assign classVar=model.variables.classVar>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
package com.hotent.${system}.controller.${package};

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hotent.core.annotion.Action;
import org.springframework.web.servlet.ModelAndView;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.util.QueryUtil;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import com.hotent.core.util.StringUtil;

import com.hotent.platform.model.bus.BusQueryRule;
import com.hotent.${system}.model.${package}.${class};
import com.hotent.${system}.service.${package}.${class}Service;
<#if subtables?exists && subtables?size != 0>
	<#list subtables as table>
import com.hotent.${system}.model.${table.variables.package}.${table.variables.class};
	</#list>
</#if>
import com.hotent.core.web.ResultMessage;
<#if model.variables.flowKey?exists>
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.core.bpm.model.ProcessCmd;
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
	<#if model.variables.flowKey?exists>
	@Resource
	private ProcessRunService processRunService;
	</#if>
	
	<#--直接绑定工作流生成-->
	<#if model.variables.flowKey?exists>
	private final String flowKey = "${model.variables.flowKey}";	//绑定流程定义
	</#if>
	
	/**
	 * 添加或更新${comment}。
	 * @param request
	 * @param response
	 * @param ${classVar} 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新${comment}")
	public void save(HttpServletRequest request, HttpServletResponse response,${class} ${classVar}) throws Exception
	{
		String resultMsg=null;		
		try{
			if(${classVar}.get${pkVar?cap_first}()==null||${classVar}.get${pkVar?cap_first}()==0){
				${classVar}.set${pkVar?cap_first}(UniqueIdUtil.genId());
				<#if subtables?exists && subtables?size != 0>
				${classVar}Service.addAll(${classVar});			
				<#else>
				${classVar}Service.add(${classVar});
				</#if>
				resultMsg=getText("添加","${comment}");
			}else{
			    <#if subtables?exists && subtables?size != 0>
			    ${classVar}Service.updateAll(${classVar});
			    <#else>
			    ${classVar}Service.update(${classVar});
			    </#if>
				resultMsg=getText("更新","${comment}");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得${comment}分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看${comment}分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String displayTagId = "${classVar}Item";
		BusQueryRule busQueryRule = QueryUtil.getBusQueryRule(displayTagId,
				request);
		List<${class}> list=${classVar}Service.getAll(new QueryFilter(request,displayTagId),busQueryRule);
		
		return this.getAutoView()
				.addObject("${classVar}List",list)
				.addObject("busQueryRule",busQueryRule);
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
			<#if subtables?exists && subtables?size != 0>
			${classVar}Service.delAll(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除${comment}及其从表成功!");
			<#else>
			${classVar}Service.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除${comment}成功!");
			</#if>			
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
		Long ${pkVar}=RequestUtil.getLong(request,"${pkVar}",<#if model.variables.flowDefKey?exists>RequestUtil.getLong(request,"id")<#else>0L</#if>);
		String returnUrl=RequestUtil.getPrePage(request);
		<#if model.variables.flowKey?exists>
		Long runId=0L;
		ProcessRun processRun=processRunService.getByBusinessKey(${pkVar}.toString());
		if(BeanUtils.isNotEmpty(processRun)){
			runId=processRun.getRunId();
		}
		</#if>
		${class} ${classVar}=${classVar}Service.getById(${pkVar});
		<#if subtables?exists && subtables?size != 0>
		    <#list subtables as table>
		    <#assign vars=table.variables>
		List<${table.variables.class}> ${table.variables.classVar}List=${classVar}Service.get${vars.classVar?cap_first}List(${pkVar});
		    </#list>
		</#if>
		
		return getAutoView().addObject("${classVar}",${classVar})
		<#if model.variables.flowKey?exists>
							.addObject("runId",runId)
		</#if>
		<#if subtables?exists && subtables?size != 0>
		    <#list subtables as table>
							.addObject("${table.variables.classVar}List",${table.variables.classVar}List)
		    </#list>
		</#if>	
							.addObject("returnUrl",returnUrl);
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
		<#if model.variables.flowKey?exists>
		Long runId=0L;
		ProcessRun processRun=processRunService.getByBusinessKey(${pkVar}.toString());
		if(BeanUtils.isNotEmpty(processRun)){
			runId=processRun.getRunId();
		}
		</#if>
		<#if subtables?exists && subtables?size != 0>
		    <#list subtables as table>
		    <#assign vars=table.variables>
		List<${table.variables.class}> ${table.variables.classVar}List=${classVar}Service.get${vars.classVar?cap_first}List(${pkVar});
		    </#list>
		return getAutoView().addObject("${classVar}",${classVar})
				<#if  model.variables.flowKey?exists>
							.addObject("runId",runId)
				</#if>
		    <#list subtables as table>
							.addObject("${table.variables.classVar}List",${table.variables.classVar}List)<#if !table_has_next>;</#if>
		    </#list>
		<#else>
		return getAutoView().addObject("${classVar}", ${classVar})<#if  model.variables.flowKey?exists>.addObject("runId",runId)</#if>;
		</#if>	
	}
	
	<#if model.variables.flowKey?exists>
	/**
	 * 流程url表单 绑定的表单明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	@Action(description="表单明细")
	public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long ${pkVar}=RequestUtil.getLong(request,"${pkVar}");
		${class} ${classVar} = ${classVar}Service.getById(${pkVar});	
		<#if subtables?exists && subtables?size != 0>
		    <#list subtables as table>
		    <#assign vars=table.variables>
		List<${table.variables.class}> ${table.variables.classVar}List=${classVar}Service.get${vars.classVar?cap_first}List(${pkVar});
		    </#list>
		return getAutoView().addObject("${classVar}",${classVar})
		    <#list subtables as table>
							.addObject("${table.variables.classVar}List",${table.variables.classVar}List)<#if !table_has_next>;</#if>
		    </#list>
		<#else>
		return getAutoView().addObject("${classVar}", ${classVar});
		</#if>	
	}
	
	/**
	 * 流程url表单 绑定的表单编辑页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modify")
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long ${pkVar}=RequestUtil.getLong(request,"${pkVar}");
		${class} ${classVar} = ${classVar}Service.getById(${pkVar});	
		<#if subtables?exists && subtables?size != 0>
		    <#list subtables as table>
		    <#assign vars=table.variables>
		List<${table.variables.class}> ${table.variables.classVar}List=${classVar}Service.get${vars.classVar?cap_first}List(${pkVar});
		    </#list>
		return getAutoView().addObject("${classVar}",${classVar})
		    <#list subtables as table>
							.addObject("${table.variables.classVar}List",${table.variables.classVar}List)<#if !table_has_next>;</#if>
		    </#list>
		<#else>
		return getAutoView().addObject("${classVar}", ${classVar});
		</#if>	
	}
	
	/**
	 * 启动流程
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("run")
	@Action(description="启动流程")
	public void run(HttpServletRequest request, HttpServletResponse response,${class} ${classVar}) throws Exception
	{
		long ${pkVar}=RequestUtil.getLong(request,"${pkVar}");
		Integer isList=RequestUtil.getInt(request, "isList",0);
		ProcessCmd processCmd=new ProcessCmd();
		processCmd.setFlowKey(flowKey);
		processCmd.setUserAccount(ContextUtil.getCurrentUser().getAccount());
		try {
			if(${pkVar}!=0L){
				processCmd.setBusinessKey(Long.toString(${pkVar}));
				processRunService.startProcess(processCmd);
				if(isList==0){
					<#if subtables?size==0>
					${classVar}Service.update(${classVar});
					<#else>
					${classVar}Service.updateAll(${classVar});
					</#if>
				}
			}else{
				Long genId=UniqueIdUtil.genId();
				processCmd.setBusinessKey(genId.toString());
				${classVar}.set${pkVar?cap_first}(genId);
				processRunService.startProcess(processCmd);
				<#if subtables?size==0>
				${classVar}Service.add(${classVar});
				<#else>
				${classVar}Service.addAll(${classVar});
				</#if>
			}
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "启动流程成功"));
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail, "启动流程失败"));
		}
	}
	
	</#if>
}
