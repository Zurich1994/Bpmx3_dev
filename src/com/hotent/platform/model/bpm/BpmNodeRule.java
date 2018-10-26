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
 * 对象功能:流程节点规则 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2011-12-14 15:41:52
 */
@XmlRootElement(name = "bpmNodeRule")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmNodeRule extends BaseModel implements Cloneable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//表名称
	public static final String TABLE_NAME="BPM_NODE_RULE";
	// 主键
	@XmlAttribute
	protected Long ruleId=0L;
	// 规则名称
	@XmlAttribute
	protected String ruleName="";
	// 规则表达式
	@XmlAttribute
	protected String conditionCode="";
	// Act流程发布ID
	@XmlAttribute
	protected String actDefId="";
	//流程节点ID
	@XmlAttribute
	protected String nodeId="";
	// 优先级别
	@XmlAttribute
	protected Long priority=0L;
	// 跳转节点
	@XmlAttribute
	protected String targetNode="";
	// 跳转节点名称
	@XmlAttribute
	protected String targetNodeName="";
	// 备注
	@XmlAttribute
	protected String memo=" ";

	public void setRuleId(Long ruleId) 
	{
		this.ruleId = ruleId;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getRuleId() 
	{
		return ruleId;
	}

	public void setRuleName(String ruleName) 
	{
		this.ruleName = ruleName;
	}
	/**
	 * 返回 规则名称
	 * @return
	 */
	public String getRuleName() 
	{
		return ruleName;
	}

	public void setConditionCode(String conditionCode) 
	{
		this.conditionCode = conditionCode;
	}
	/**
	 * 返回 规则表达式
	 * @return
	 */
	public String getConditionCode() 
	{
		return conditionCode;
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

	public void setPriority(Long priority) 
	{
		this.priority = priority;
	}
	/**
	 * 返回 优先级别
	 * @return
	 */
	public Long getPriority() 
	{
		return priority;
	}

	public void setTargetNode(String targetNode) 
	{
		this.targetNode = targetNode;
	}
	/**
	 * 返回 跳转节点
	 * @return
	 */
	public String getTargetNode() 
	{
		return targetNode;
	}

	public void setTargetNodeName(String targetNodeName) 
	{
		this.targetNodeName = targetNodeName;
	}
	/**
	 * 返回 跳转节点名称
	 * @return
	 */
	public String getTargetNodeName() 
	{
		return targetNodeName;
	}

	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() 
	{
		return memo;
	}

	public String getNodeId()
	{
		return nodeId;
	}
	public void setNodeId(String nodeId)
	{
		this.nodeId = nodeId;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmNodeRule)) 
		{
			return false;
		}
		BpmNodeRule rhs = (BpmNodeRule) object;
		return new EqualsBuilder()
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
		.append("ruleId", this.ruleId) 
		.append("ruleName", this.ruleName) 
		.append("conditionCode", this.conditionCode) 
		.append("actDefId", this.actDefId)
		.append("nodeId", this.nodeId)
		.append("priority", this.priority) 
		.append("targetNode", this.targetNode) 
		.append("targetNodeName", this.targetNodeName) 
		.append("memo", this.memo) 
		.toString();
	}

    
	public Object clone()
	{
		BpmNodeRule obj=null;
		try{
			obj=(BpmNodeRule)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
  

}