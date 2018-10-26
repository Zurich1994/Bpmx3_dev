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
 * 对象功能:流程变量定义 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-02 13:23:25
 */
@XmlRootElement(name = "bpmDefVar")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmDefVar extends BaseModel implements Cloneable
{
	//表名称
	public static final String TABLE_NAME = "BPM_DEF_VARS";
	// 变量ID
	@XmlAttribute
	protected Long varId;
	// 流程定义ID
	@XmlAttribute
	protected Long defId;
	// 变量名称
	@XmlAttribute
	protected String varName;
	// 变量Key
	@XmlAttribute
	protected String varKey;
	// 变量数据类型
	@XmlAttribute
	protected String varDataType;
	// 默认值
	@XmlAttribute
	protected String defValue;
	// 节点名称
	@XmlAttribute
	protected String nodeName;
	// 流程历史版本
	@XmlAttribute
	protected Long versionId;
	// actDeployId
	@XmlAttribute
	protected Long actDeployId;
	// 类型
	@XmlAttribute
	protected String nodeId;
	// 作用域
	@XmlAttribute
	protected String varScope;
	
	//wyx 
	// 变量类型：输入、输出
	@XmlAttribute
	protected String varType;

	public String getVarType() {
		return varType;
	}
	public void setVarType(String varType) {
		this.varType = varType;
	}
	public void setVarId(Long varId) 
	{
		this.varId = varId;
	}
	/**
	 * 返回 变量ID
	 * @return
	 */
	public Long getVarId() 
	{
		return varId;
	}

	public void setDefId(Long defId) 
	{
		this.defId = defId;
	}
	/**
	 * 返回 流程定义ID
	 * @return
	 */
	public Long getDefId() 
	{
		return defId;
	}

	public void setVarName(String varName) 
	{
		this.varName = varName;
	}
	/**
	 * 返回 变量名称
	 * @return
	 */
	public String getVarName() 
	{
		return varName;
	}

	public void setVarKey(String varKey) 
	{
		this.varKey = varKey;
	}
	/**
	 * 返回 变量Key
	 * @return
	 */
	public String getVarKey() 
	{
		return varKey;
	}

	public void setVarDataType(String varDataType) 
	{
		this.varDataType = varDataType;
	}
	/**
	 * 返回 变量数据类型
	 * @return
	 */
	public String getVarDataType() 
	{
		return varDataType;
	}

	public void setDefValue(String defValue) 
	{
		this.defValue = defValue;
	}
	/**
	 * 返回 默认值
	 * @return
	 */
	public String getDefValue() 
	{
		return defValue;
	}

	public void setNodeName(String nodeName) 
	{
		this.nodeName = nodeName;
	}
	/**
	 * 返回 节点名称
	 * @return
	 */
	public String getNodeName() 
	{
		return nodeName;
	}

	public void setVersionId(Long versionId) 
	{
		this.versionId = versionId;
	}
	/**
	 * 返回 流程历史版本
	 * @return
	 */
	public Long getVersionId() 
	{
		return versionId;
	}

	public void setActDeployId(Long actDeployId) 
	{
		this.actDeployId = actDeployId;
	}
	/**
	 * 返回 actDeployId
	 * @return
	 */
	public Long getActDeployId() 
	{
		return actDeployId;
	}

	public void setNodeId(String nodeId) 
	{
		this.nodeId = nodeId;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public String getNodeId() 
	{
		return nodeId;
	}

	public void setVarScope(String varScope) 
	{
		this.varScope = varScope;
	}
	/**
	 * 返回 作用域
	 * @return
	 */
	public String getVarScope() 
	{
		return varScope;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmDefVar)) 
		{
			return false;
		}
		BpmDefVar rhs = (BpmDefVar) object;
		return new EqualsBuilder()
		.append(this.varId, rhs.varId)
		.append(this.defId, rhs.defId)
		.append(this.varName, rhs.varName)
		.append(this.varKey, rhs.varKey)
		.append(this.varDataType, rhs.varDataType)
		.append(this.defValue, rhs.defValue)
		.append(this.nodeName, rhs.nodeName)
		.append(this.versionId, rhs.versionId)
		.append(this.actDeployId, rhs.actDeployId)
		.append(this.nodeId, rhs.nodeId)
		.append(this.varScope, rhs.varScope)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.varId) 
		.append(this.defId) 
		.append(this.varName) 
		.append(this.varKey) 
		.append(this.varDataType) 
		.append(this.defValue) 
		.append(this.nodeName) 
		.append(this.versionId) 
		.append(this.actDeployId) 
		.append(this.nodeId) 
		.append(this.varScope) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("varId", this.varId) 
		.append("defId", this.defId) 
		.append("varName", this.varName) 
		.append("varKey", this.varKey) 
		.append("varDataType", this.varDataType) 
		.append("defValue", this.defValue) 
		.append("nodeName", this.nodeName) 
		.append("versionId", this.versionId) 
		.append("actDeployId", this.actDeployId) 
		.append("nodeId", this.nodeId) 
		.append("varScope", this.varScope) 
		.toString();
	}
   
	public Object clone()
	{
		BpmDefVar obj=null;
		try{
			obj=(BpmDefVar)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

}