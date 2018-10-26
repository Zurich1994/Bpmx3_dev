package com.hotent.platform.service.jms;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 消息处理容器类 
 * @author lenovo
 *
 */

public class MessageHandlerContainer {
	protected Logger logger = LoggerFactory.getLogger(MessageHandlerContainer.class);
	// LinkedHashMap
	// 是HashMap的一个子类，保存了记录的插入顺序，在用Iterator遍历LinkedHashMap时，先得到的记录肯定是先插入的.
	private Map<String, IMessageHandler> handlersMap = new LinkedHashMap<String, IMessageHandler>();

	public Map<String, IMessageHandler> getHandlerMap() {
		return handlersMap;
	}
	
	public void setHandlersMap(Map<String, IMessageHandler> map){
		this.handlersMap=map;
	}

	public IMessageHandler getHandler(String key) {
		return handlersMap.get(key);
	}
	

}