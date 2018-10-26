package com.hotent.core.jms;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.util.BeanUtils;
import com.hotent.platform.service.jms.IJmsHandler;

/**
 * 从消息队列中读取对象，并且进行消息发送。
 * 
 * @author zxh
 * 
 */
public class MessageConsumer {

	/**
	 * 处理消息
	 */
	private Map<String, IJmsHandler> handlers = new HashMap<String, IJmsHandler>();

	protected Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
	public void setHandlers(Map<String, IJmsHandler> handlers) {
		this.handlers = handlers;
	}

	/**
	 * 发送消息
	 * 
	 * @param model
	 *            发送的对象
	 * @throws Exception
	 */
	public void sendMessage(Object model) throws Exception {
		if (BeanUtils.isNotEmpty(handlers) && BeanUtils.isNotEmpty(model)) {
			IJmsHandler jmsHandler = handlers.get(model.getClass().getName());
			if(jmsHandler!=null){
				jmsHandler.handMessage(model);
			}
			else{
				logger.info(model.toString());
			}
		} else {
			throw new Exception("Object:[" + model + "] is not  entity Object ");
		}
	}
}
