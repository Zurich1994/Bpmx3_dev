package com.hotent.core.web.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.model.OnlineUser;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;

/**
 * 监听用户登录事件和会话过期事件。
 * 管理在线用户情况。
 * @author csx
 *
 */
public class UserSessionListener implements HttpSessionAttributeListener{

	protected Logger logger = LoggerFactory.getLogger(UserSessionListener.class);
	/**
	 * 进入系统,添加在线用户
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		if(!"SPRING_SECURITY_LAST_USERNAME".equals(event.getName())) return;
		SysUser user=ContextUtil.getCurrentUser();
		if(user==null){
			return;
		}
		Long userId=user.getUserId();
		if(AppUtil.getOnlineUsers().containsKey(userId)) return;
		OnlineUser onlineUser=new OnlineUser();
		
		onlineUser.setUserId(user.getUserId());
		onlineUser.setUsername(user.getUsername());
		SysOrg org=ContextUtil.getCurrentOrg();
		if(org!=null){
			onlineUser.setOrgId(org.getOrgId());
			onlineUser.setOrgName(org.getOrgName());
		}
		
		AppUtil.getOnlineUsers().put(user.getUserId(),onlineUser);
				
	}

	/**
	 * 退出系统，或者系统超时时+-删除在线用户
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		if("SPRING_SECURITY_LAST_USERNAME".equals(event.getName())){
			SysUser user=ContextUtil.getCurrentUser();
			
			if(user!=null){
				AppUtil.getOnlineUsers().remove(user.getUserId());
			}
		
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		logger.info(event.getName());
		
	}

	


}
