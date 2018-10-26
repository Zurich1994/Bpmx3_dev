package com.hotent.platform.model.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:会签任务投票规则 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2011-12-14 08:41:54
 */
@XmlRootElement(name = "bpmNodeSign")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmNodeSign extends BaseModel implements Cloneable
{
	//表名称
	public static final String TABLE_NAME="BPM_NODE_SIGN";
	//投票类型 百分比数
	public static final Short VOTE_TYPE_PERCENT=1;
	//投票类型 绝对票数
	public static final Short VOTE_TYPE_ABSOLUTE=2;
	
	/**
	 * 后续处理模式--直接处理=1
	 */
	public static final Short FLOW_MODE_DIRECT=1;
	/**
	 * 后续处理模式--等待所有人投票=2
	 */
	public static final Short FLOW_MODE_WAITALL=2;
	
	/**
	 * 会签决策类型--通过=1
	 */
	public static final Short DECIDE_TYPE_PASS=1;
	/**
	 * 会签决策类型--反对=2
	 */
	public static final Short DECIDE_TYPE_REFUSE=2;
	
	// signId
	@XmlAttribute
	protected Long signId=0L;

	// 流程节点ID
	@XmlAttribute
	protected String nodeId=" ";
	// 票数
	@XmlAttribute
	protected Long voteAmount=0L;
	// 决策方式
	@XmlAttribute
	protected Short decideType=0;
	// 1=百分比
	@XmlAttribute
	protected Short voteType=0;
	// Act流程发布ID
	@XmlAttribute
	protected String actDefId=" ";
	
	/**
	 * 后续处理模式
	 */
	@XmlAttribute
	protected Short flowMode;

	public Short getFlowMode() {
		return flowMode;
	}
	
	public void setFlowMode(Short flowMode) {
		this.flowMode = flowMode;
	}
	
	public void setSignId(Long signId) 
	{
		this.signId = signId;
	}
	/**
	 * 返回 signId
	 * @return
	 */
	public Long getSignId() 
	{
		return signId;
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

	public void setVoteAmount(Long voteAmount) 
	{
		this.voteAmount = voteAmount;
	}
	/**
	 * 返回 票数
	 * @return
	 */
	public Long getVoteAmount() 
	{
		return voteAmount;
	}

	public void setDecideType(Short decideType) 
	{
		this.decideType = decideType;
	}
	/**
	 * 返回 决策方式
	 * @return
	 */
	public Short getDecideType() 
	{
		return decideType;
	}

	public void setVoteType(Short voteType) 
	{
		this.voteType = voteType;
	}
	/**
	 * 返回 1=百分比
	 * @return
	 */
	public Short getVoteType() 
	{
		return voteType;
	}

	public void setActDefId(String actDefId) 
	{
		this.actDefId = actDefId;
	}
	/**
	 * 返回 Act流程发布ID
	 * @return
	 */
	public String getActDefId() 
	{
		return actDefId;
	}
   
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmNodeSign)) 
		{
			return false;
		}
		BpmNodeSign rhs = (BpmNodeSign) object;
		return new EqualsBuilder()
		.append(this.signId, rhs.signId)
		
		.append(this.nodeId, rhs.nodeId)
		.append(this.voteAmount, rhs.voteAmount)
		.append(this.decideType, rhs.decideType)
		.append(this.voteType, rhs.voteType)
		.append(this.actDefId, rhs.actDefId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.signId) 
	
		.append(this.nodeId) 
		.append(this.voteAmount) 
		.append(this.decideType) 
		.append(this.voteType) 
		.append(this.actDefId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("signId", this.signId) 
		
		.append("nodeId", this.nodeId) 
		.append("voteAmount", this.voteAmount) 
		.append("decideType", this.decideType) 
		.append("voteType", this.voteType) 
		.append("actDeployId", this.actDefId) 
		.toString();
	}
   
	
	public Object clone()
	{
		BpmNodeSign obj=null;
		try{
			obj=(BpmNodeSign)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

}