package com.hotent.platform.controller.mail;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.mail.OutMail;
import com.hotent.platform.model.mail.OutMailUserSeting;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.model.system.SysFile;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.mail.OutMailService;
import com.hotent.platform.service.mail.OutMailUserSetingService;
import com.hotent.platform.service.util.ServiceUtil;
/**
 * 对象功能:邮件 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-09 14:16:18
 */
@Controller
@RequestMapping("/platform/mail/outMail/")
@Action(ownermodel=SysAuditModelType.USER_MANAGEMENT)
public class OutMailFormController extends BaseFormController
{
	@Resource
	private OutMailService outMailService;
	
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	
	/**
	 * 添加或更新邮件。
	 * @param request
	 * @param response
	 * @param outMail 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("send")
	@Action(description="发送邮件",detail="发送邮件")
	public void save(HttpServletRequest request, HttpServletResponse response, OutMail outMail,BindingResult bindResult) throws Exception
	{
		ResultMessage resultMessage=validForm("outMail", outMail, bindResult, request);
		if(resultMessage.getResult()==ResultMessage.Fail)
		{
			writeResultMessage(response.getWriter(),resultMessage);
			return;
		}
		int type=outMail.getTypes();
		long userId=ContextUtil.getCurrentUserId();
		long mailId=RequestUtil.getLong(request, "mailId",0);
		int isReply=RequestUtil.getInt(request, "isReply",0);
		
		OutMailUserSeting outMailUserSeting=outMailUserSetingService.getMailByAddress(outMail.getSenderAddresses());
		outMail.setMailDate(new Date());
		outMail.setSenderName(outMailUserSeting.getUserName());
		outMail.setIsReply(isReply);
		outMail.setUserId(userId);
		outMail.setSetId(outMailUserSeting.getId());
		String context=request.getContextPath();
		//String basePath=configproperties.getProperty("file.upload");
		String basePath=ServiceUtil.getBasePath();
		try{
			if(type==2){
				outMailService.sendMail(outMail,userId,mailId,isReply,context,basePath);
				writeResultMessage(response.getWriter(),new ResultMessage(ResultMessage.Success,"发送邮件成功"));
			}else{
				if(mailId==0){
					outMail.setMailId(UniqueIdUtil.genId());
					outMailService.add(outMail);
				}else{
					outMailService.update(outMail);
				}
				writeResultMessage(response.getWriter(),new ResultMessage(ResultMessage.Success,"保存邮件成功"));
			}
		}catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				resultMessage = new ResultMessage(ResultMessage.Fail,"发送邮件失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}

	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * @param mailId
	 * @param model
	 * @return
	 * @throws Exception
	 */
    @ModelAttribute
    protected OutMail getFormObject(@RequestParam("mailId") Long mailId,Model model) throws Exception {
		logger.debug("enter OutMail getFormObject here....");
		OutMail outMail=null;
		if(mailId!=null){
			outMail=outMailService.getById(mailId);
		}else{
			outMail= new OutMail();
		}
		return outMail;
    }
}
