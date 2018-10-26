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
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.MessageSendService;

/**
 * 对象功能:发送消息 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:10:58
 */
@Controller
@RequestMapping("/platform/system/messageSend/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class MessageSendFormController extends BaseFormController
{
	@Resource
	private MessageSendService messageSendService;
	
	/**
	 * 添加或更新发送消息。
	 * @param request
	 * @param response
	 * @param messageSend 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新发送消息",execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>发送消息" +
					"【${SysAuditLinkService.getMessageSendLink(Long.valueOf(mesendId))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, MessageSend messageSend,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("messageSend", messageSend, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String receiverId=RequestUtil.getString(request, "receiverId");
		String receiverName=RequestUtil.getString(request, "receiverName");
		String receiverOrgId=RequestUtil.getString(request, "receiverOrgId");
		String receiverOrgName=RequestUtil.getString(request, "receiverOrgName");
		
		SysUser curUser = ContextUtil.getCurrentUser();
		
		
		String resultMsg=null;
		boolean isadd=true;
		if(messageSend.getId()==null)
		{
			resultMsg="添加发送消息成功";
		}
		else
		{
			resultMsg="更新发送消息成功";
			isadd=false;
		}
		messageSendService.addMessageSend(messageSend, curUser,
				receiverId, receiverName, receiverOrgId, receiverOrgName);
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		SysAuditThreadLocalHolder.putParamerter("mesendId", messageSend.getId().toString());
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
    protected MessageSend getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter MessageSend getFormObject here....");
		MessageSend messageSend=null;
		if(id!=null){
			messageSend=messageSendService.getById(id);
		}else{
			messageSend= new MessageSend();
		}
		return messageSend;
    }

}
