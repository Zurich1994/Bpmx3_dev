package com.hotent.platform.service.jms;


/**
 * 消息处理接口用于处理jms中的MessageModel对象。
 * <pre>
 * 在系统中可以提供不同的实现，处理不同类型的消息。
 * 不同类型的消息使用不同的处理器进行处理，这样增加一种类型的消息，配置到spring文件中。
 * 就不用修改系统代码，只需要增加一种消息处理器即可。
 * </pre>
 * @author hjx
 *
 */
public interface IMessageHandler{ 

	 /**
	  * 实际消息处理器的名称
	  * @return
	  */
     public String getTitle(); 
     /**
      * 这种类型的消息处理器是否为系统默认的处理器。
      * @return
      */
     public boolean getIsDefaultChecked();
     /**
 	 * 发送消息处理
 	 * 
 	 * @param model 发送消息的model
 	 */
     public void handMessage(MessageModel model); 

} 
