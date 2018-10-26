package com.hotent.platform.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.MessageReply;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.MessageReplyService;
import com.hotent.platform.service.system.MessageSendService;
import com.hotent.platform.service.system.SysUserService;

/**
 * 对象功能:消息回复 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:15:43
 */
@Controller
@RequestMapping("/platform/system/messageReply/")
public class MessageReplyController extends BaseController
{
	@Resource
	private MessageReplyService replyService;
	@Resource
	private MessageSendService sendService;
	@Resource
	private SysUserService userService;
	
	/**
	 * 取得消息回复分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看消息回复分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String returnUrl=RequestUtil.getPrePage(request);
		SysUser curUser = ContextUtil.getCurrentUser();
		Long userId=curUser.getUserId();
		Long messageId=RequestUtil.getLong(request,"messageId");
		MessageSend messageSend =sendService.getById(messageId);
		QueryFilter queryFilter=new QueryFilter(request,"messageReplyItem",false);
		queryFilter.addFilter("userId", userId);
		List<MessageReply> list=replyService.getAll(queryFilter);
		ModelAndView mv=this.getAutoView()
				.addObject("replyList",list)
				.addObject("userId", userId)
				.addObject("messageSend", messageSend)
				.addObject("returnUrl", returnUrl) 
				;
		
		return mv;
	}
	
	/**
	 * 删除消息回复
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除消息回复")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			replyService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除消息回复成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑消息回复")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Long messageId=RequestUtil.getLong(request,"messageId");
		SysUser curUser = ContextUtil.getCurrentUser();
		Long userId=curUser.getUserId();
		String returnUrl=RequestUtil.getPrePage(request);
		MessageReply messageReply=null;
		MessageSend messageSend=null;
		
		messageSend=sendService.getById(messageId);
		
		if(id!=0){
			messageReply= replyService.getById(id);
		}
		else{
			messageReply=new MessageReply();
			messageReply.setMessageId(messageId);
			messageReply.setReply(curUser.getFullname());
			messageReply.setReplyId(userId);
		}
		return getAutoView()
				.addObject("messageReply",messageReply)
				.addObject("subject",messageSend.getSubject())
				.addObject("content",messageSend.getContent())
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得消息回复明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看消息回复明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		MessageReply messageReply = replyService.getById(id);		
		return getAutoView().addObject("messageReply", messageReply);
	}
	
	@RequestMapping("reply")
	@Action(description="读取消息并回复")
	public void reply(HttpServletRequest request, HttpServletResponse response, MessageReply messageReply) throws Exception{	
		try{
			SysUser sysUser = ContextUtil.getCurrentUser();
			replyService.saveReply(messageReply, sysUser);
			writeResultMessage(response.getWriter(),"回复消息成功!",ResultMessage.Success);
		}
		catch (Exception e) {
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail,"回复消息失败:" + str);
				response.getWriter().print(resultMessage);
			} else {
				String message = ExceptionUtil.getExceptionMessage(e);
				ResultMessage resultMessage = new ResultMessage(ResultMessage.Fail, message);
				response.getWriter().print(resultMessage);
			}
		}
	}
	
}
