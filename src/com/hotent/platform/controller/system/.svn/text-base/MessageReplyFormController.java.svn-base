package com.hotent.platform.controller.system;

import java.util.Date;

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
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.system.MessageReply;
import com.hotent.platform.service.system.MessageReplyService;

/**
 * 对象功能:消息回复 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:15:43
 */
@Controller
@RequestMapping("/platform/system/messageReply/")
public class MessageReplyFormController extends BaseFormController
{
	@Resource
	private MessageReplyService messageReplyService;
	
	/**
	 * 添加或更新消息回复。
	 * @param request
	 * @param response
	 * @param messageReply 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新消息回复")
	public void save(HttpServletRequest request, HttpServletResponse response, MessageReply messageReply,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("messageReply", messageReply, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		Date now=new Date();
		if(messageReply.getId()==null){
			messageReply.setId(UniqueIdUtil.genId());
			messageReply.setReplyTime(now);
			messageReplyService.add(messageReply);
			resultMsg="添加消息回复成功";
		}else{
			messageReplyService.update(messageReply);
			resultMsg="更新消息回复成功";
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
    protected MessageReply getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter MessageReply getFormObject here....");
		MessageReply messageReply=null;
		if(id!=null){
			messageReply=messageReplyService.getById(id);
		}else{
			messageReply= new MessageReply();
		}
		return messageReply;
    }

}
