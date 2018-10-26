package com.hotent.platform.service.jms;

/**
 * 发送消息处理接口
 * 
 * <pre>
 * 	 需要实现这个接口并在app-jms.xml 配置处理消息的model及实现的类
 * 	目前实现了 1.发送邮件消息。 2.发送短消息。 3.发送内部消息。
 * </pre>
 * 
 * @author zxh
 * 
 */
public interface IJmsHandler {

	/**
	 * 发送消息处理
	 * 
	 * @param model
	 *            发送消息的model
	 */
	public void handMessage(Object model);

}
