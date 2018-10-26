package com.hotent.platform.controller.jms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.web.MessageQuery;
import org.apache.activemq.web.QueueBrowseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.jms.QueuesService;

/**
 * <pre>
 * 对象功能:ACTIVEMQ_LOCK 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-06-04 15:37:54
 * </pre>
 */
@Controller
@RequestMapping("/platform/jms/queues/")
@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
public class QueuesController extends BaseController {
	protected Logger logger = LoggerFactory.getLogger(QueuesController.class);
	@Resource
	private QueuesService queuesService;

	/**
	 * 取得queues分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看队列分页列表",detail="查看队列分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*如果查询不到队列详细信息，可做如下测试
		1，注释掉 app-jms.xml中的 ，消息监听容器 messageListenerContainer
        2，注释掉 app-jms.xml中的， 邮件消息消费监听器 messageMsgListener
		queuesService.sendMailQueue();//3，取消此行注释 ，测试用
        */
		Collection<QueueViewMBean> list = queuesService.getQueues();
		ModelAndView mv = this.getAutoView().addObject("queuesList", list);
		return mv;
	}

	@RequestMapping("browse")
	@SuppressWarnings("unused")
	@Action(description = "查看消息分页列表",detail="查看消息分页列表")
	public ModelAndView browse(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Collection<?> list = null;
		List<Message> messageList = new ArrayList<Message>();
		String JMSDestination = RequestUtil.getString(request, "JMSDestination");

		QueueBrowseQuery qbq = queuesService.getQueueBrowseQuery(JMSDestination);
		
		javax.jms.QueueBrowser queueBrowser = null;
		queueBrowser = qbq.getBrowser();
		Enumeration iter = queueBrowser.getEnumeration();
		while (iter.hasMoreElements()) {
			javax.jms.Message message = (javax.jms.Message) iter.nextElement();
			logger.info(message.toString());
			messageList.add(message);
		}
		ModelAndView mv = this.getAutoView()
		        .addObject("browseList",messageList)
				.addObject("JMSDestination",JMSDestination);
		return mv;
	}
	
    /**
     * 
     * @author hjx
     * @version 创建时间：2013-6-8  下午2:37:48
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping("message")
	@SuppressWarnings("unused")
	@Action(description = "查看message内容分页列表",detail="查看message内容分页列表")
	public ModelAndView message(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Message> messageList = new ArrayList<Message>();
		String id = RequestUtil.getString(request, "id");
		String JMSDestination = RequestUtil.getString(request, "JMSDestination");
		
		MessageQuery messageQuery = queuesService.getMessageQuery(id,JMSDestination);
		Message msg=messageQuery.getMessage();
		ModelAndView mv = this.getAutoView().addObject("messageQuery", messageQuery);
		return mv;
	}
	/**
	 * 删除队列
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除队列",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除队列" +
					"<#list StringUtils.split(lAryId,\",\") as item>" +
					"【${item}】" +
				"</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			String[] lAryId = RequestUtil.getStringAryByStr(request, "JMSDestination");
			
			for (String name : lAryId){
				queuesService.removeDestinationQueue(name);
			}
			SysAuditThreadLocalHolder.putParamerter("lAryId", lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除队列成功!");
		} catch (Exception ex) {
			ex.printStackTrace();
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
/**
 * 删除消息
 * @author hjx
 * @version 创建时间：2013-6-18  下午10:11:42
 * @param request
 * @param response
 * @throws Exception
 */
	@RequestMapping("delMessage")
	@Action(description = "删除消息",execOrder=ActionExecOrder.BEFORE,
			detail="删除消息")
	public void delMessage(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			String JMSDestination = RequestUtil.getString(request, "JMSDestination");
			
			String messageId = RequestUtil.getString(request, "messageId");
			queuesService.removeMessage(JMSDestination,messageId);
			
			message = new ResultMessage(ResultMessage.Success,
					"删除队列成功!");
		} catch (Exception ex) {
			ex.printStackTrace();
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 清空队列
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("purge")
	@Action(description = "清空队列",detail="清空队列")
	public void purge(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			String[] lAryId = RequestUtil.getStringAryByStr(request, "JMSDestination");
			
			for (String name : lAryId){
				queuesService.purgeDestination(name);
			}
			message = new ResultMessage(ResultMessage.Success,
					"清空队列成功!");
		} catch (Exception ex) {
			ex.printStackTrace();
			message = new ResultMessage(ResultMessage.Fail, "清空队列失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
}
