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
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.UserPosition;
import com.hotent.platform.service.system.SysUserOrgService;
import com.hotent.platform.service.system.UserPositionService;

/**
 * 对象功能:用户所属组织或部门 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-07 18:23:24
 */
@Controller
@RequestMapping("/platform/system/sysUserOrg/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class SysUserOrgFormController extends BaseFormController
{
	@Resource
	private UserPositionService userPositionService;
	
	/**
	 * 添加或更新用户所属组织或部门。
	 * @param request
	 * @param response
	 * @param sysUserOrg 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新用户所属组织或部门",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新" +
			"<#assign entity=SysAuditLinkService.getByUserPosId(Long.valueOf(UserOrgId))/>" +
			"用户:【${entity.userName}】所属组织或部门为:【${entity.orgName}】")
	public void save(HttpServletRequest request, HttpServletResponse response, UserPosition userPosition,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("sysUserOrg", userPosition, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		boolean isadd=true;
		if(userPosition.getUserOrgId()==null){
			userPosition.setUserOrgId(UniqueIdUtil.genId());
			userPositionService.add(userPosition);
			resultMsg="添加用户所属组织或部门成功";
		}else{
			userPositionService.update(userPosition);
			resultMsg="更新用户所属组织或部门成功";
			isadd=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		SysAuditThreadLocalHolder.putParamerter("UserOrgId", userPosition.getUserOrgId());
		writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param userOrgId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected SysUserOrg getFormObject(@RequestParam("userOrgId") Long userOrgId,Model model) throws Exception {
		logger.debug("enter SysUserOrg getFormObject here....");
		SysUserOrg sysUserOrg=null;
		if(userOrgId!=null){
			sysUserOrg=userPositionService.getById(userOrgId);
		}else{
			sysUserOrg= new SysUserOrg();
		}
		return sysUserOrg;
    }

}
