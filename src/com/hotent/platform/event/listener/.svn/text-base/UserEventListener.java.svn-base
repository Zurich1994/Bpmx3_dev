/**
 * 监听器，
 */
package com.hotent.platform.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.hotent.core.cache.ICache;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.platform.event.def.UserEvent;

@Service
public class UserEventListener implements ApplicationListener<UserEvent> {

	public void onApplicationEvent(UserEvent ev) {
		int action=ev.getAction();
		Long userId=ev.getUserId();
		
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		
		String companyKey=ContextUtil.CurrentCompany + userId;
		
		
		String orgKey=ContextUtil.getOrgKey(userId);
		String positionKey=ContextUtil.getPositionKey(userId);
		
		//删除缓存数据
		iCache.delByKey(companyKey);
		iCache.delByKey(orgKey);
		iCache.delByKey(positionKey);
	}

}
