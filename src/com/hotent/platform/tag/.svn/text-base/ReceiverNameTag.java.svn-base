package com.hotent.platform.tag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import com.hotent.core.util.AppUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.MessageReceiver;
import com.hotent.platform.service.system.MessageReceiverService;
import com.hotent.platform.service.system.SysUserOrgService;

public class ReceiverNameTag extends BodyTagSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public int doStartTag() throws JspTagException {
		return EVAL_BODY_BUFFERED;
	}
	
	private Long messageId=0L;
	
	

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	private String getReceiverName(){
		HttpServletRequest request=(HttpServletRequest) pageContext.getRequest() ;
		//通过用户Id获取用户名称
		StringBuffer str=new StringBuffer();
		if(this.messageId!=0){
			MessageReceiverService messageReceiverService=(MessageReceiverService) AppUtil.getBean("messageReceiverService");
			List<MessageReceiver>messageReceiversList=messageReceiverService.getByMessageId(messageId);
			if(messageReceiversList.size()!=0){
				MessageReceiver messageReceiver=null;
				for (int i = 0; i < messageReceiversList.size(); i++) {
					messageReceiver=(MessageReceiver) messageReceiversList.get(i);
					if(messageReceiver.getReceiveType()==MessageReceiver.TYPE_USER){
						str.append("<img src='" + request.getContextPath() + "/styles/default/images/bpm/user-16.png'>&nbsp;<a href='"
								+request.getContextPath()+"/platform/system/sysUser/get.ht?userId="+messageReceiver.getReceiverId()+"&hasClose=true&canReturn=-1' target='_blank'>"+messageReceiver.getReceiver()+"</a>") ;
					}else {
						String imgSrc = "<img src='" + request.getContextPath() + "/styles/default/images/bpm/user-16.png'>&nbsp;" ;
						str.append(imgSrc);
						SysUserOrgService sysUserOrgService = (SysUserOrgService)AppUtil.getBean("sysUserOrgService") ;
						List list=sysUserOrgService.getUserByOrgIds(messageReceiver.getReceiverId().toString());
						if(list!=null)
							str.append( "<a href='#' onclick='return false;' orgId='"+messageReceiver.getReceiverId()+"' >"+messageReceiver.getReceiver()+"</a><input type='hidden' name='candidateUsersJson' value='"+JSONArray.fromObject(list)+"'>");
					}
				}
			}
		}
		return StringUtils.isNotEmpty(str.toString()) ? str.toString() : "暂无";
	}
	
	public int doEndTag() throws JspTagException {

		try {
			String str = getReceiverName();
			pageContext.getOut().print(str);
		} catch (Exception e) {
			throw new JspTagException(e.getMessage());
		}
		return SKIP_BODY;
	}
	


	
}
