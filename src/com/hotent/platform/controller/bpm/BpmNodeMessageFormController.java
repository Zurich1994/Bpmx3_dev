package com.hotent.platform.controller.bpm;

import java.util.ArrayList;
import java.util.List;

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
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.BpmNodeMessage;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.BpmNodeMessageService;

/**
 * 对象功能:流程节点邮件 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-12-31 15:48:59
 */
@Controller
@RequestMapping("/platform/bpm/bpmNodeMessage/")
public class BpmNodeMessageFormController extends BaseFormController
{	
	@Resource
	private BpmNodeMessageService bpmNodeMessageService;
	/**
	 * 添加或更新流程节点邮件。
	 * @param request
	 * @param response
	 * @param bpmNodeMessage 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程节点邮件")
	public void save(HttpServletRequest request, HttpServletResponse response, BpmNodeMessage bpmNodeMessage,BindingResult bindResult) throws Exception
	{		
		String actDefId=RequestUtil.getString(request,"actDefId");
		String nodeId=RequestUtil.getString(request,"nodeId");
		String subject_mail=RequestUtil.getString(request,"subject_mail");
		String template_mail=RequestUtil.getString(request,"template_mail");
		
		String subject_inner=RequestUtil.getString(request,"subject_inner");
		String template_inner=RequestUtil.getString(request,"template_inner");

		String template_mobile=RequestUtil.getString(request,"template_mobile");
		
		Short sendMail=RequestUtil.getShort(request, "sendMail");
		Short sendInner=RequestUtil.getShort(request, "sendInner");
		Short sendMobile=RequestUtil.getShort(request, "sendMobile");
		Long mailMessageId = RequestUtil.getLong(request, "mailMessageId");
		Long innerMessageId = RequestUtil.getLong(request, "innerMessageId");
		Long smsMessageId = RequestUtil.getLong(request, "smsMessageId");
		
		List<BpmNodeMessage> messages=new ArrayList<BpmNodeMessage>();
		
		BpmNodeMessage mailMessage=new BpmNodeMessage();
		mailMessage.setId(mailMessageId);
		mailMessage.setActDefId(actDefId);
		mailMessage.setNodeId(nodeId);
		mailMessage.setSubject(subject_mail);
		mailMessage.setMessageType(BpmNodeMessage.MESSAGE_TYPE_MAIL);
		mailMessage.setTemplate(template_mail);
		mailMessage.setIsSend(sendMail);
		
		BpmNodeMessage innerMessage=new BpmNodeMessage();
		innerMessage.setId(innerMessageId);
		innerMessage.setActDefId(actDefId);
		innerMessage.setNodeId(nodeId);
		innerMessage.setSubject(subject_inner);
		innerMessage.setMessageType(BpmNodeMessage.MESSAGE_TYPE_INNER);
		innerMessage.setTemplate(template_inner);
		innerMessage.setIsSend(sendInner);
		
		BpmNodeMessage smsMessage=new BpmNodeMessage();
		smsMessage.setId(smsMessageId);
		smsMessage.setActDefId(actDefId);
		smsMessage.setNodeId(nodeId);
		smsMessage.setMessageType(BpmNodeMessage.MESSAGE_TYPE_SMS);
		smsMessage.setTemplate(template_mobile);
		smsMessage.setIsSend(sendMobile);
		
		messages.add(mailMessage);
		messages.add(innerMessage);
		messages.add(smsMessage);
		
		for(BpmNodeMessage message:messages){
			if(message.getId().intValue()==0){
				message.setId(UniqueIdUtil.genId());
				bpmNodeMessageService.add(message);
			}else{
				bpmNodeMessageService.update(message);
			}
		}
		writeResultMessage(response.getWriter(),"消息节点设置成功！",ResultMessage.Success);
	}
	
	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected BpmNodeMessage getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter BpmNodeMessage getFormObject here....");
		BpmNodeMessage bpmNodeMessage=null;
		if(id!=null){
			bpmNodeMessage=bpmNodeMessageService.getById(id);
		}else{
			bpmNodeMessage= new BpmNodeMessage();
		}
		return bpmNodeMessage;
    }

}
