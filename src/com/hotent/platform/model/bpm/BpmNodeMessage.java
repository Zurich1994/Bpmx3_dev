package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.platform.model.bpm.BpmNodeMessage;
import com.hotent.core.model.BaseModel;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:流程节点邮件 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-08 18:07:00
 */
@XmlRootElement(name = "bpmNodeMessage")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmNodeMessage extends BaseModel implements Cloneable
{
	private static final long serialVersionUID = 1L;

	public static final  short MESSAGE_TYPE_MAIL			= 1;
	public static final  short MESSAGE_TYPE_INNER			= 2;
	public static final  short MESSAGE_TYPE_SMS			    = 3;
	
	// id
	@XmlAttribute
	protected Long id;
	// 流程定义ID
	@XmlAttribute
	protected String actDefId;
	// 流程节点ID
	@XmlAttribute
	protected String nodeId;
	
	//消息接收人类型
	@XmlAttribute
	protected Short messageType;
	//消息主题
	@XmlAttribute
	protected String subject;
	//消息模板
	@XmlAttribute
	protected String template;
	//确认发送 1：发送 ,0 不发送
	protected Short isSend=0;

	private List<BpmUserCondition> userConditions = new ArrayList<BpmUserCondition>();
	
	public Short getIsSend() {
		return isSend;
	}

	public void setIsSend(Short isSend) {
		this.isSend = isSend;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}
	
	/**
	 * 返回 id
	 * @return
	 */
	public Long getId() 
	{
		return id;
	}
	

	public void setActDefId(String actDefId) 
	{
		this.actDefId = actDefId;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public String getActDefId() 
	{
		return actDefId;
	}

	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 流程节点ID
	 * @return
	 */
	public String getNodeId() 
	{
		return nodeId;
	}

	
	public Short getMessageType() {
		return messageType;
	}
	public void setMessageType(Short messageType) {
		this.messageType = messageType;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	
   
	
   	public List<BpmUserCondition> getUserConditions() {
		return userConditions;
	}
	public void setUserConditions(List<BpmUserCondition> userConditions) {
		this.userConditions = userConditions;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmNodeMessage)) 
		{
			return false;
		}
		BpmNodeMessage rhs = (BpmNodeMessage) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.actDefId, rhs.actDefId)
		.append(this.nodeId, rhs.nodeId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.actDefId) 
		.append(this.nodeId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("actDefId", this.actDefId) 
		.append("nodeId", this.nodeId) 
		.toString();
	}
   
	public Object clone()
	{
		BpmNodeMessage obj=null;
		try{
			obj=(BpmNodeMessage)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
  

}