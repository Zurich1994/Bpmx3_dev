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
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SubSystem;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.system.SysRoleService;

/**
 * 对象功能:系统角色表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-28 11:39:03
 */
@Controller
@RequestMapping("/platform/system/sysRole/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class SysRoleFormController extends BaseFormController
{
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SubSystemService subSystemService;

	
	/**
	 * 添加或更新系统角色表。
	 * @param request
	 * @param response
	 * @param sysRole 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新系统角色表",
			detail="<#if isExist>角色${roleName}已存在!<#else><#if isAdd>添加<#else>更新</#if>" +
			"系统角色表【${SysAuditLinkService.getSysRoleLink(sysRoleService.getById(Long.valueOf(roleId)))}】</#if>")
	public void save(HttpServletRequest request, HttpServletResponse response, SysRole sysRole,BindingResult bindResult) throws Exception
	{
		Long systemId=RequestUtil.getLong(request, "systemId");
		String systemAlias="";
		if(systemId>0){
			SubSystem subSystem=subSystemService.getById(systemId);
			systemAlias=subSystem.getAlias() +"_";
		}else {
			sysRole.setSystemId(0L);
		}
		ResultMessage resultMessage=validForm("sysRole", sysRole, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		boolean isExist=false;
		boolean isadd=true;
		if(sysRole.getRoleId()==null){
			String tmpAlias=sysRole.getAlias();
			String alias=systemAlias +tmpAlias;
			//判断别名是否存在。
			isExist=sysRoleService.isExistRoleAlias(alias);
			if(!isExist){
				sysRole.setRoleId(UniqueIdUtil.genId());
				sysRole.setAlias(alias);
				sysRoleService.add(sysRole);
				resultMsg="添加系统角色成功";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
			}else{
				resultMsg="角色别名：["+tmpAlias+"]已存在";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
			}
		}else{
			String tmpAlias=sysRole.getAlias();
			String alias=systemAlias +tmpAlias;
			Long roleId=sysRole.getRoleId();
			isExist=sysRoleService.isExistRoleAliasForUpd(alias, roleId);
			if(isExist){
				resultMsg="角色别名：["+tmpAlias+"]已存在";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
			}
			else{
				sysRole.setAlias(alias);
				sysRoleService.update(sysRole);
				resultMsg="更新系统角色成功";
				writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
			}
			isadd=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		SysAuditThreadLocalHolder.putParamerter("isExist", isExist);
		if(!isExist){
			SysAuditThreadLocalHolder.putParamerter("roleId", sysRole.getRoleId().toString());
		}
		
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param roleId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected SysRole getFormObject(@RequestParam("roleId") Long roleId,Model model) throws Exception {
		logger.debug("enter SysRole getFormObject here....");
		SysRole sysRole=null;
		if(roleId!=null){
			sysRole=sysRoleService.getById(roleId);
		}else{
			sysRole= new SysRole();
		}
		return sysRole;
    }

}
