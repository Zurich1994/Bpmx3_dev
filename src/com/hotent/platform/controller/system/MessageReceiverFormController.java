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

import com.hotent.platform.model.system.MessageReceiver;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.MessageReceiverService;

/**
 * 对象功能:消息接收者 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:13:00
 */
@Controller
@RequestMapping("/platform/system/messageReceiver/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class MessageReceiverFormController extends BaseFormController
{
	@Resource
	private MessageReceiverService messageReceiverService;
	
	/**
	 * 添加或更新消息接收者。
	 * @param request
	 * @param response
	 * @param messageReceiver 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新消息接收者",detail="<#if isAdd>添加<#else>更新</#if>消息接收者" +
			"【${SysAuditLinkService.getMessageReceiverLink(Long.valueOf(mereId))}】")
	public void save(HttpServletRequest request, HttpServletResponse response, MessageReceiver messageReceiver,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("messageReceiver", messageReceiver, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail){
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		boolean isadd=true;
		if(messageReceiver.getId()==null){
			messageReceiver.setId(UniqueIdUtil.genId());
			messageReceiverService.add(messageReceiver);
			resultMsg="添加消息接收者成功";
		}else{
			messageReceiverService.update(messageReceiver);
			resultMsg="更新消息接收者成功";
			isadd=false;
		}
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		SysAuditThreadLocalHolder.putParamerter("mereId", messageReceiver.getId().toString());
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
    protected MessageReceiver getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter MessageReceiver getFormObject here....");
		MessageReceiver messageReceiver=null;
		if(id!=null){
			messageReceiver=messageReceiverService.getById(id);
		}else{
			messageReceiver= new MessageReceiver();
		}
		return messageReceiver;
    }

}
