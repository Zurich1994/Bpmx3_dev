package com.hotent.platform.controller.mail;

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
import com.hotent.core.encrypt.EncryptUtil;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.mail.OutMailUserSetingService;

/**
 * 对象功能:邮箱设置 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-09 13:43:51
 */
@Controller
@RequestMapping("/platform/mail/outMailUserSeting/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class OutMailUserSetingFormController extends BaseFormController
{
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	
	/**
	 * 添加或更新邮箱。
	 * @param request
	 * @param response
	 * @param outMailUserSeting 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新外部邮箱",execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>外部邮箱" +
			"【${SysAuditLinkService.getOutMailUserSetingLink(Long.valueOf(id))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, OutMailUserSeting outMailUserSeting,BindingResult bindResult) throws Exception
	{
		ResultMessage resultMessage=validForm("outMailUserSeting", outMailUserSeting, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		long id=RequestUtil.getLong(request, "id");
		if(outMailUserSetingService.isExistMail(id,outMailUserSeting)==true){
			resultMsg="该邮箱地址已经设置过，不能重复设置";
		}else{
			boolean isadd=true;
			if(id==0L){
				outMailUserSeting.setId(UniqueIdUtil.genId());
				long userId=ContextUtil.getCurrentUserId();
				outMailUserSeting.setUserId(userId);
				//设置密码加密
				outMailUserSeting.setMailPass(EncryptUtil.encrypt(outMailUserSeting.getMailPass()));
				int count=outMailUserSetingService.getCountByUserId(userId);
				if(count==0){
					outMailUserSeting.setIsDefault(1);
				}else{
				outMailUserSeting.setIsDefault(0);}
				outMailUserSetingService.add(outMailUserSeting);
				resultMsg="添加邮箱设置成功";
			}else{
				//设置密码加密
				String mailPass = outMailUserSeting.getMailPass();
				if(StringUtil.isEmpty(mailPass)){
					outMailUserSeting.setMailPass(outMailUserSetingService.getById(id).getMailPass());
				}else {
					outMailUserSeting.setMailPass(EncryptUtil.encrypt(mailPass));
				}
				outMailUserSetingService.update(outMailUserSeting);
				resultMsg="更新邮箱设置成功";
				isadd=false;
			}
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		}
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
    protected OutMailUserSeting getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter OutMailUserSeting getFormObject here....");
		OutMailUserSeting outMailUserSeting=null;
		if(id!=null){
			outMailUserSeting=outMailUserSetingService.getById(id);
		}else{
			outMailUserSeting= new OutMailUserSeting();
		}
		return outMailUserSeting;
    }
}
