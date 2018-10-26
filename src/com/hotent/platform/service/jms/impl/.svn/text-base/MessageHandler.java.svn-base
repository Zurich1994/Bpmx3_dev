package com.hotent.platform.service.jms.impl;

import javax.annotation.Resource;

import com.hotent.platform.service.jms.IJmsHandler;
import com.hotent.platform.service.jms.IMessageHandler;
import com.hotent.platform.service.jms.MessageHandlerContainer;
import com.hotent.platform.service.jms.MessageModel;

public class MessageHandler implements IJmsHandler {
	
	@Resource
	MessageHandlerContainer messageHandlerContainer;

	public void handMessage(Object model) {
		MessageModel msgModel=(MessageModel)model;
		String type=msgModel.getInformType();
		IMessageHandler handler= messageHandlerContainer.getHandler(type);
		handler.handMessage(msgModel);

	}

}
