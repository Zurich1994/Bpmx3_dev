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
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.MessageRead;
import com.hotent.platform.model.system.MessageReply;
import com.hotent.platform.model.system.MessageSend;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.MessageReadService;
import com.hotent.platform.service.system.MessageReplyService;
import com.hotent.platform.service.system.MessageSendService;

/**
 * 对象功能:接收状态 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-01-14 15:14:52
 */
@Controller
@RequestMapping("/platform/system/messageRead/")
public class MessageReadController extends BaseController
{
	@Resource
	private MessageReadService readService;
	@Resource
	private MessageSendService sendService;
	@Resource
	private MessageReplyService replyService;
	/**
	 * 取得接收状态分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看接收状态分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		SysUser curUser = ContextUtil.getCurrentUser();
		Long userId=curUser.getUserId();
		String returnUrl=RequestUtil.getPrePage(request);
		Long messageId=RequestUtil.getLong(request,"messageId");
		
		MessageSend messageSend =new MessageSend();
		messageSend=sendService.getById(messageId);
		QueryFilter queryFilter=new QueryFilter(request,"messageReplyItem",false);
		queryFilter.addFilter("userId", userId);
		List<MessageReply> list=replyService.getAll(queryFilter);
		
		// 插入已读信息表
		readService.addMessageRead(messageId, curUser);
		
		ModelAndView mv=this.getAutoView()
				.addObject("replyList",list.size()>0?list:null)
				.addObject("userId", userId)
				.addObject("messageSend", messageSend)
				.addObject("returnUrl", returnUrl);
		
		return mv;
	}
	
	/**
	 * 删除接收状态
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除接收状态")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			readService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除接收状态成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="编辑接收状态")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		String returnUrl=RequestUtil.getPrePage(request);
		MessageRead messageRead=null;
		if(id!=0){
			 messageRead= readService.getById(id);
		}else{
			messageRead=new MessageRead();
		}
		return getAutoView().addObject("messageRead",messageRead).addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得接收状态明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看接收状态明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"id");
		MessageRead messageRead = readService.getById(id);		
		return getAutoView().addObject("messageRead", messageRead);
	}

}
