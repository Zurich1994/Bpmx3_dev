package com.hotent.platform.controller.form;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.form.BpmFormTemplate;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.form.BpmFormTemplateService;

/**
 * 对象功能:表单模板 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-01-09 16:35:25
 */
@Controller
@RequestMapping("/platform/form/bpmFormTemplate/")
@Action(ownermodel=SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormTemplateController extends BaseController
{
	@Resource
	private BpmFormTemplateService bpmFormTemplateService;
	
	/**
	 * 取得表单模板分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看表单模板分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmFormTemplate> list=bpmFormTemplateService.getAll(new QueryFilter(request,"bpmFormTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmFormTemplateList",list);
		
		return mv;
	}
	
	//此方法找不到对应的jsp路径啊
	@RequestMapping("selector")
	@Action(description="查看表单模板分页列表")
	public ModelAndView selector(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<BpmFormTemplate> list=bpmFormTemplateService.getAll(new QueryFilter(request,"bpmFormTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("bpmFormTemplateList",list);
		
		return mv;
	}
	
	/**
	 * 删除表单模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除表单模板",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除表单模板：" +
					"<#list StringUtils.split(templateId,\",\") as item>" +
						"<#assign entity=bpmFormTemplateService.getById(Long.valueOf(item))/>" +
						"【${entity.templateName}】" +
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "templateId");
			bpmFormTemplateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除表单模板成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑表单模板")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long templateId=RequestUtil.getLong(request,"templateId");
		String returnUrl=RequestUtil.getPrePage(request);
		BpmFormTemplate bpmFormTemplate=null;
		if(templateId!=0){
			 bpmFormTemplate= bpmFormTemplateService.getById(templateId);
		}else{
			bpmFormTemplate=new BpmFormTemplate();
		}
		List<BpmFormTemplate> macroTemplates = bpmFormTemplateService.getAllMacroTemplate();
		return getAutoView().addObject("bpmFormTemplate",bpmFormTemplate)
							.addObject("returnUrl", returnUrl)
							.addObject("macroTemplates", macroTemplates);
	}
	
	
	/**
	 * 取得表单模板明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看表单模板明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"templateId");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		BpmFormTemplate bpmFormTemplate = bpmFormTemplateService.getById(id);		
		return getAutoView().addObject("bpmFormTemplate", bpmFormTemplate).addObject("canReturn", canReturn);
	}
	
	/**
	 * 取得初始化模板信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("init")
	@Action(description="初始化模板",
			detail="初始化表单模板"
	)
	public void init(HttpServletRequest request,HttpServletResponse response)throws Exception
	{	
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			bpmFormTemplateService.initAllTemplate();
			message=new ResultMessage(ResultMessage.Success, "初始化表单模板成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "初始化表单模板失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 将用户自定义模板备份
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("backUp")
	@Action(description="备份模板",
			detail="备份表单模板【${bpmFormTemplateService.getById(Long.valueOf(templateId)).getTemplateName()}】"
	)
	public void backUp(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		long id=RequestUtil.getLong(request, "templateId");
		String preUrl=RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			bpmFormTemplateService.backUpTemplate(id);
			message=new ResultMessage(ResultMessage.Success, "模板备份成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "模板备份失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 *  复制模板信息
	 * @param request
	 * @param response
	 * @param template
	 * @throws Exception
	 */
	@RequestMapping("copyTemplate")
	@Action(description="复制模板",
			detail="<#if isExist>" +
					   "复制表单模板【${bpmFormTemplateService.getById(Long.valueOf(templateId)).getTemplateName()}】," +
					   "新表单模板名称为【${newTemplateName}】" +
				   "</#if>"
	)
	public void copyTemplate(HttpServletRequest request,HttpServletResponse response,BpmFormTemplate template) throws Exception
	{   
		long id=RequestUtil.getLong(request, "templateId");
		BpmFormTemplate bpmFormTemplate=bpmFormTemplateService.getById(id);
		String name=RequestUtil.getString(request, "newTemplateName");
		String newAlias=RequestUtil.getString(request, "newAlias");
		boolean isExist=bpmFormTemplateService.isExistAlias(newAlias);
		SysAuditThreadLocalHolder.putParamerter("isExist", isExist);
		String resultMsg=null;
		if(isExist){
		   writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Fail,"该别名已被使用"));
		}else{
			long newId=UniqueIdUtil.genId();
			template.setTemplateId(newId);
			template.setTemplateName(name);
			template.setAlias(newAlias);
			template.setCanEdit(1);
			template.setHtml(bpmFormTemplate.getHtml());
			template.setMacroTemplateAlias(bpmFormTemplate.getMacroTemplateAlias());
			template.setTemplateDesc(bpmFormTemplate.getTemplateDesc());
			template.setTemplateType(bpmFormTemplate.getTemplateType());
			bpmFormTemplateService.add(template);
			writeResultMessage(response.getWriter(), new ResultMessage(ResultMessage.Success, "复制模板成功"));
		}
	}
	
}
