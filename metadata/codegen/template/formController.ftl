<#import "function.ftl" as func>
<#assign package=model.variables.package>
<#assign class=model.variables.class>
<#assign system=vars.system>
<#assign comment=model.tabComment>
<#assign classVar=model.variables.classVar>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
<#assign pkVarFistUp=pkVar?cap_first >


<#assign subtables=model.subTableList>
package ${vars.packagePre}.${system}.controller.${package};


import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ${vars.packagePre}.core.annotion.Action;
import ${vars.packagePre}.core.util.UniqueIdUtil;
import ${vars.packagePre}.core.web.ResultMessage;
import ${vars.packagePre}.core.web.util.RequestUtil;
import ${vars.packagePre}.core.web.controller.BaseFormController;
import ${vars.packagePre}.${system}.model.${package}.${class};
import ${vars.packagePre}.${system}.service.${package}.${class}Service;

<#if func.isSubTableExist( subtables)>
<#list subtables as table>
import ${vars.packagePre}.${system}.model.${package}.${table.variables.class};
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
public class ${class}FormController extends BaseFormController
{
	@Resource
	private ${class}Service ${classVar}Service;
	
	/**
	 * 添加或更新项目信息。
	 * @param request
	 * @param response
	 * @param projectInfo 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新项目信息")
	public void save(HttpServletRequest request, HttpServletResponse response,${class} ${classVar}) throws Exception
	{
		String resultMsg=null;		
		try{
			if(${classVar}.get${pkVarFistUp}()==null||${classVar}.get${pkVarFistUp}()==0){
				${classVar}Service.save(${classVar});
				resultMsg=getText("添加","项目信息");
			}else{
				${classVar}Service.save(${classVar});
				resultMsg=getText("更新","项目信息");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param projectId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected ${class} getObject(@RequestParam("${pkVar}") Long ${pkVar}) throws Exception {
		logger.debug("enter ${class} getFormObject here....");
		${class} ${classVar}=null;
		if(${pkVar}!=null){
			${classVar}=${classVar}Service.getById(${pkVar});
		}else{
			${classVar}= new ${class}();
		}
		return ${classVar};
    }
   
}







