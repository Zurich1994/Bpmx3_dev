package com.hotent.platform.service.jms;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Session;

import com.hotent.core.jms.MessageProducer;
import com.hotent.core.util.AppConfigUtil;
import java.util.Collection;
import java.util.Iterator;

import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.broker.jmx.DestinationViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.pool.PooledSession;
import org.apache.activemq.web.MessageQuery;
import org.apache.activemq.web.QueueBrowseQuery;
import org.apache.activemq.web.RemoteJMXBrokerFacade;
import org.apache.activemq.web.SessionPool;
import org.apache.activemq.web.config.SystemPropertiesConfiguration;
/**
 * <pre>
 * 对象功能:jms监控
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-06-04 15:37:54
 * </pre>
 */
public class QueuesService  {
	@Resource
	protected MessageProducer messageProducer;
	@Resource
	protected ConnectionFactory simpleJmsConnectionFactory;
	
	private RemoteJMXBrokerFacade brokerFacade;
	
	private String jmxHostIp = "127.0.0.1";
	private String jmxPort="1099";
	
	private RemoteJMXBrokerFacade getBrokerFacade(){
		if(brokerFacade==null){
			brokerFacade = new RemoteJMXBrokerFacade();
			jmxHostIp = AppConfigUtil.get("jms.ip");
			jmxPort = AppConfigUtil.get("jmx.port", jmxPort);
			System.setProperty("webconsole.jmx.url","service:jmx:rmi:///jndi/rmi://"+jmxHostIp+":"+ jmxPort +"/jmxrmi");
			//创建配置  
			SystemPropertiesConfiguration configuration = new SystemPropertiesConfiguration();
			//创建链接  
			brokerFacade.setConfiguration(configuration);
		}
		return brokerFacade;
	}
	
	/**
	 * 根据queueName获取queue相关的信息
	 * @param name
	 * @return
	 * @throws Exception
	 */
	private QueueViewMBean getQueue(String name) throws Exception {
		QueueViewMBean qvb = null;
		qvb = (QueueViewMBean) getDestinationByName(getQueues(), name);
		return qvb;
	}
	
	/**
	 * 从队列集合中获取与name匹配的队列
	 * @param collection
	 * @param name
	 * @return
	 */
	private DestinationViewMBean getDestinationByName(Collection<? extends DestinationViewMBean> collection, String name) {
		Iterator<? extends DestinationViewMBean> iter = collection.iterator();
		while (iter.hasNext()) {
			DestinationViewMBean destinationViewMBean = iter.next();
			if (name.equals(destinationViewMBean.getName())) {
				return destinationViewMBean;
			}
		}
		return null;
	}
	
	/**
	 * 获取连接session
	 * @return
	 * @throws Exception
	 */
	private SessionPool getSessionPool() throws Exception{
		SessionPool sp=new SessionPool();
		sp.setConnectionFactory(simpleJmsConnectionFactory);
		sp.setConnection(sp.getConnection());
		Session session =sp.borrowSession();
		ActiveMQSession am = null;
		if(session instanceof ActiveMQSession){
			am = (ActiveMQSession)(session);
		}
		if(session instanceof PooledSession){
			PooledSession pooledSession = (PooledSession)session;
			am = pooledSession.getInternalSession();
		}
		sp.returnSession(am);
		return sp;
	}
	
	public Collection<QueueViewMBean> getQueues() throws Exception{
		return getBrokerFacade().getQueues();
	}
	
	/**
	 * 清空队列
	 * @param JMSDestination
	 * @throws Exception
	 */
	public void purgeDestination(String JMSDestination) throws Exception {
		getBrokerFacade().purgeQueue(ActiveMQDestination.createDestination(JMSDestination, ActiveMQDestination.QUEUE_TYPE));
    }
	
	 /**
	  * 删除消息
	  * @param JMSDestination
	  * @param messageId
	  * @throws Exception
	  */
	public void removeMessage(String JMSDestination,String messageId) throws Exception {
		 QueueViewMBean queueView = getQueue(JMSDestination);
		 queueView.removeMessage(messageId);
    }
	
	/**
	 * 查看队列中的未消费消息
	 * @param JMSDestination
	 * @return
	 * @throws Exception
	 */
	public QueueBrowseQuery getQueueBrowseQuery(String JMSDestination) throws Exception{
		QueueBrowseQuery qbq = new QueueBrowseQuery(getBrokerFacade(),getSessionPool());
		qbq.setJMSDestination(JMSDestination);
		return qbq;
	}
	
	/**
	 * 查看消息详情
	 * @param id
	 * @param JMSDestination
	 * @return
	 * @throws Exception
	 */
	public MessageQuery getMessageQuery(String id,String JMSDestination) throws Exception{
		MessageQuery mq=new MessageQuery(getBrokerFacade(),getSessionPool());
		mq.setJMSDestination(JMSDestination);
		mq.setId(id);
		return mq;
	}
	
	/**
	 * 删除队列
	 * @param JMSDestination
	 * @throws Exception
	 */
	public void removeDestinationQueue(String JMSDestination) throws Exception {
		getBrokerFacade().getBrokerAdmin().removeQueue(JMSDestination);
    }
}
