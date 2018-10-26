package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.service.system.SysTemplateService;
import com.hotent.platform.xml.util.MsgUtil;

/**
 * 对象功能:模版管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-28 14:04:30
 */
@Controller
@RequestMapping("/platform/system/sysTemplate/")
@Action(ownermodel=SysAuditModelType.PROCESS_AUXILIARY)
public class SysTemplateController extends BaseController
{
	@Resource
	private SysTemplateService sysTemplateService;
	
	/**
	 * 取得模版管理分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看模版管理分页列表",exectype="管理日志",detail="查看模版管理分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysTemplate> list=sysTemplateService.getAll(new QueryFilter(request,"sysTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("sysTemplateList",list);
	
		return mv;
	}
	@RequestMapping("dialog")
	@Action(description="查看模版管理分页列表",detail="查看模版管理分页列表")
	public ModelAndView dialog(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysTemplate> list=sysTemplateService.getAll(new QueryFilter(request,"sysTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("sysTemplateList",list);
		return mv;
	}
	
	
	/**
	 * 删除模版管理
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(
			description="删除模版管理",
			execOrder=ActionExecOrder.BEFORE,
			detail= "删除短信邮件模版：" +
					"<#list StringUtils.split(templateId,\",\") as item>" +
						"<#assign entity=sysTemplateService.getById(Long.valueOf(item))/>" +
						"【${entity.name}】" +
					"</#list>"
	)
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ResultMessage message=null;
		String preUrl= RequestUtil.getPrePage(request);
		try {
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "templateId");
			sysTemplateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success,"删除模板成功");
		} catch (Exception e) {
			message=new ResultMessage(ResultMessage.Fail,"删除模板失败,"+e.getMessage());
		}
		
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="添加或编辑模版管理",
			exectype="管理日志",
			detail="<#if isAdd>添加新模板<#else>" +
					"编译模板:" +
					"<#assign entity=sysTemplateService.getById(Long.valueOf(templateId))/>" +
					"【${entity.name}】</#if>" 
					)
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long templateId = RequestUtil.getLong(request,"templateId");
		String returnUrl=RequestUtil.getPrePage(request);
		SysTemplate sysTemplate=null;
		boolean isadd=true;
		if(templateId!=0){
			 sysTemplate= sysTemplateService.getById(templateId);
			 isadd=false;
		}else{
			sysTemplate=new SysTemplate();
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		return getAutoView().addObject("sysTemplate",sysTemplate).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得模版管理明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看模版管理明细",exectype="管理日志")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"templateId");
		long canReturn=RequestUtil.getLong(request,"canReturn",0);
		SysTemplate sysTemplate = sysTemplateService.getById(id);		
		return getAutoView().addObject("sysTemplate", sysTemplate).addObject("canReturn",canReturn);
	}

	/**
	 * 更改默认模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("setDefault")
	@Action(
			description="设置每种模板类型的默认模板",
			detail= "设置模板【${sysTemplateService.getById(Long.valueOf(templateId)).name}】为默认模板"
	)
	public void setDefault(HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		Long templateId=RequestUtil.getLong(request, "templateId");		
		try{
			sysTemplateService.setDefault(templateId);
			message=new ResultMessage(ResultMessage.Success, "设置成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "设置失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	
	
	/**
	 * 导出系统模板xml。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportXml")
	@Action(description = "导出系统模板")
	public void exportXml(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Long[] ids = RequestUtil.getLongAryByStr(request, "ids");

		if (BeanUtils.isNotEmpty(ids)) {
			String fileName = "SysTemplate_"+ DateFormatUtil.getNowByString("yyyyMMddHHmmss");
			String strXml = sysTemplateService.exportXml(ids);
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName + ".xml");
			response.getWriter().write(strXml);
			response.getWriter().flush();
			response.getWriter().close();
		}
	}

	/**
	 * 导入系统模板XML。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importXml")
	@Action(description = "导入系统模板")
	public void importXml(MultipartHttpServletRequest request,HttpServletResponse response) throws Exception {
		MultipartFile fileLoad = request.getFile("xmlFile");
		boolean clearAll = RequestUtil.getBoolean(request, "clearAll",false);
		boolean setDefault = RequestUtil.getBoolean(request, "setDefault",false);
		ResultMessage message = null;
		try {
			List<SysTemplate> sysTemplates = sysTemplateService.unmarshal(fileLoad.getInputStream());
			for(SysTemplate sysTemplate:sysTemplates){
				sysTemplate.setTemplateId(UniqueIdUtil.genId());
			}
			
			if(clearAll){
				for(SysTemplate sysTemplate:sysTemplates){
					sysTemplateService.delByUseType(sysTemplate.getUseType());
				}
			}
			
			for(SysTemplate sysTemplate:sysTemplates){
				if(sysTemplate.getIsDefault()!=null && SysTemplate.IS_DEFAULT_YES.intValue()==sysTemplate.getIsDefault().intValue()){
					SysTemplate temp = sysTemplateService.getDefaultByUseType(sysTemplate.getUseType());
					if(temp!=null){
						sysTemplate.setIsDefault(SysTemplate.IS_DEFAULT_NO);
					}
				}
				sysTemplateService.add(sysTemplate);
				MsgUtil.addMsg(MsgUtil.SUCCESS,"模板：【" + sysTemplate.getName()+ "】成功导入！");
			}
			
			if(setDefault){
				for(SysTemplate sysTemplate:sysTemplates){
					sysTemplateService.setDefault(sysTemplate.getTemplateId());
				}
			}
			message = new ResultMessage(ResultMessage.Success,MsgUtil.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			e.getStackTrace();
			message = new ResultMessage(ResultMessage.Fail,"导入出错了，请检查导入格式是否正确或者导入的数据是否有问题！");
		}
		writeResultMessage(response.getWriter(), message);
	}
}
