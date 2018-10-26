package com.hotent.platform.controller.form;

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
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.form.BpmFormRule;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.form.BpmFormRuleService;

/**
 * 对象功能:表单验证规则 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2012-01-11 10:53:15
 */
@Controller
@RequestMapping("/platform/form/bpmFormRule/")
@Action(ownermodel=SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormRuleFormController extends BaseFormController
{
	@Resource
	private BpmFormRuleService bpmFormRuleService;
	
	/**
	 * 添加或更新表单验证规则。
	 * @param request
	 * @param response
	 * @param bpmFormRule 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新表单验证规则",
			detail="<#if StringUtil.isNotEmpty(isAdd)>" +
					"<#if isAdd==0>添加<#else>更新</#if>" +
					"表单验证规则【${SysAuditLinkService.getBpmFormRuleLink(Long.valueOf(ruleId))}】成功" +
					"<#else>添加或更新表单验证规则【${name}】失败</#if>")
	public void save(HttpServletRequest request, HttpServletResponse response, BpmFormRule bpmFormRule,BindingResult bindResult) throws Exception
	{
		ResultMessage resultMessage=validForm("bpmFormRule", bpmFormRule, bindResult, request);
		String isAdd="0";
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(bpmFormRule.getId()==null){
			bpmFormRule.setId(UniqueIdUtil.genId());
			bpmFormRuleService.add(bpmFormRule);
			resultMsg="保存表单验证规则成功";
		}else{
			isAdd="1";
			bpmFormRuleService.update(bpmFormRule);
			resultMsg="更新表单验证规则成功";
		}
		//重新生成js验证文件。
		bpmFormRuleService.generateJS();
		SysAuditThreadLocalHolder.putParamerter("isAdd", isAdd);
		SysAuditThreadLocalHolder.putParamerter("ruleId", bpmFormRule.getId().toString());
		SysAuditThreadLocalHolder.putParamerter("name", bpmFormRule.getName());
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected BpmFormRule getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter BpmFormRule getFormObject here....");
		BpmFormRule bpmFormRule=null;
		if(id!=null){
			bpmFormRule=bpmFormRuleService.getById(id);
		}else{
			bpmFormRule= new BpmFormRule();
		}
		return bpmFormRule;
    }

}
