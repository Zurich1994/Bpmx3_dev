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
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;

import com.hotent.platform.model.system.MessageRead;
import com.hotent.platform.service.system.MessageReadService;

/**
 * 对象功能:接收状态 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:14:52
 */
@Controller
@RequestMapping("/platform/system/messageRead/")
public class MessageReadFormController extends BaseFormController
{
	@Resource
	private MessageReadService messageReadService;
	
	/**
	 * 添加或更新接收状态。
	 * @param request
	 * @param response
	 * @param messageRead 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新接收状态")
	public void save(HttpServletRequest request, HttpServletResponse response, MessageRead messageRead,BindingResult bindResult) throws Exception
	{
		
		ResultMessage resultMessage=validForm("messageRead", messageRead, bindResult, request);
		//add your custom validation rule here such as below code:
		//bindResult.rejectValue("name","errors.exist.student",new Object[]{"jason"},"重复姓名");
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		String resultMsg=null;
		if(messageRead.getId()==null){
			messageRead.setId(UniqueIdUtil.genId());
			messageReadService.add(messageRead);
			resultMsg="添加接收状态成功";
		}else{
			messageReadService.update(messageRead);
			resultMsg="更新接收状态成功";
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
    protected MessageRead getFormObject(@RequestParam("id") Long id,Model model) throws Exception {
		logger.debug("enter MessageRead getFormObject here....");
		MessageRead messageRead=null;
		if(id!=null){
			messageRead=messageReadService.getById(id);
		}else{
			messageRead= new MessageRead();
		}
		return messageRead;
    }

}
