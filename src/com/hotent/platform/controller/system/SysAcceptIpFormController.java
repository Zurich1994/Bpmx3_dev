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

import com.hotent.platform.model.system.SysAcceptIp;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.SysAcceptIpService;

/**
 * 对象功能:可访问地址 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-20 17:35:46
 */
@Controller
@RequestMapping("/platform/system/sysAcceptIp/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class SysAcceptIpFormController extends BaseFormController
{
	@Resource
	private SysAcceptIpService sysAcceptIpService;
	
	/**
	 * 添加或更新可访问地址。
	 * @param request
	 * @param response
	 * @param sysAcceptIp 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新可访问地址",
			execOrder=ActionExecOrder.AFTER,
			detail= "<#if isAdd>添加<#else>更新</#if>可访问地址" +
			"【标题：${SysAuditLinkService.getSysAcceptIpLink(Long.valueOf(acceptId))}】" 
			)
	public void save(HttpServletRequest request, HttpServletResponse response, SysAcceptIp sysAcceptIp,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("sysAcceptIp", sysAcceptIp, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", sysAcceptIp.getAcceptId()==null);
		String resultMsg=null;
		if(sysAcceptIp.getAcceptId()==null){
			sysAcceptIp.setAcceptId(UniqueIdUtil.genId());
			sysAcceptIpService.add(sysAcceptIp);
			resultMsg="添加可访问地址成功";
		}else{
			sysAcceptIpService.update(sysAcceptIp);
			resultMsg="更新可访问地址成功";
		}
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		SysAuditThreadLocalHolder.putParamerter("acceptId", sysAcceptIp.getAcceptId().toString());
					
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param acceptId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected SysAcceptIp getFormObject(@RequestParam("acceptId") Long acceptId,Model model) throws Exception {
		logger.debug("enter SysAcceptIp getFormObject here....");
		SysAcceptIp sysAcceptIp=null;
		if(acceptId!=null){
			sysAcceptIp=sysAcceptIpService.getById(acceptId);
		}else{
			sysAcceptIp= new SysAcceptIp();
		}
		return sysAcceptIp;
    }

}
