package com.hotent.platform.controller.system;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysTemplate;
import com.hotent.platform.service.system.SysTemplateService;

/**
 * 对象功能:模版管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-28 14:04:30
 */
@Controller
@RequestMapping("/platform/system/sysTemplate/")
@Action(ownermodel=SysAuditModelType.PROCESS_AUXILIARY)
public class SysTemplateFormController extends BaseFormController
{
	@Resource
	private SysTemplateService sysTemplateService;
	
	/**
	 * 添加或更新模版管理。
	 * @param request
	 * @param response
	 * @param sysTemplate 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(
			description="添加或更新短信邮件模版",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>短信邮件模版【${SysAuditLinkService.getSysTemplateLink(Long.valueOf(templateId))}】"
	)
	public void save(HttpServletRequest request, HttpServletResponse response, SysTemplate sysTemplate,BindingResult bindResult) throws Exception
	{
		
		
		//添加系统日志信息 -E
		boolean isadd=true;
		ResultMessage resultMessage=validForm("sysTemplate", sysTemplate, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(sysTemplate.getTemplateId()==null)
		{
			sysTemplate.setTemplateId(UniqueIdUtil.genId());
			sysTemplate.setIsDefault(0);
			sysTemplateService.add(sysTemplate);
			resultMsg="添加模版管理成功";
		}else{
			sysTemplateService.update(sysTemplate);
			resultMsg="更新模版管理成功";
			isadd=false;
		}
		//添加系统日志信息 -B
				try {
					SysAuditThreadLocalHolder.putParamerter("templateId", sysTemplate.getTemplateId().toString());
					SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param templateId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected SysTemplate getFormObject(@RequestParam("templateId") Long templateId,Model model) throws Exception {
		logger.debug("enter SysTemplate getFormObject here....");
		SysTemplate sysTemplate=null;
		if(templateId!=null){
			sysTemplate=sysTemplateService.getById(templateId);
		}else{
			sysTemplate= new SysTemplate();
		}
		return sysTemplate;
    }

}
