package com.hotent.platform.model.bpm;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能: 用户节点的上下级 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-02 10:07:45
 */
@XmlRootElement(name = "bpmNodeUserUplow")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmNodeUserUplow extends BaseModel
{
	public static Map<String,Short> UPLOWTYPE_MAP = new HashMap<String,Short>(); 
	static{
		UPLOWTYPE_MAP.put("上级",(short)1);
		UPLOWTYPE_MAP.put("下级",(short)-1);
		UPLOWTYPE_MAP.put("同级",(short)0);
	}
	
	//是否负责人
	@XmlAttribute
	protected int isCharge;
	// nodeUserId
	@XmlAttribute
	protected Long nodeUserId;
	// 维度Id	@XmlAttribute
	protected Long demensionId;
	// demensionName
	@XmlAttribute
	protected String demensionName;
	// 上下级层次
	@XmlAttribute
	protected Integer upLowLevel;
	// 上下级类型	@XmlAttribute
	protected Short upLowType;

	//返回isCharge
	public int getIsCharge() {
		return isCharge;
	}
	public void setIsCharge(int isCharge) {
		this.isCharge = isCharge;
	}
	

	public void setNodeUserId(Long nodeUserId) 
	{
		this.nodeUserId = nodeUserId;
	}
	/**
	 * 返回 nodeUserId
	 * @return
	 */
	public Long getNodeUserId() 
	{
		return nodeUserId;
	}

	public void setDemensionId(Long demensionId) 
	{
		this.demensionId = demensionId;
	}
	/**
	 * 返回 维度Id
	 * @return
	 */
	public Long getDemensionId() 
	{
		return demensionId;
	}

	public void setDemensionName(String demensionName) 
	{
		this.demensionName = demensionName;
	}
	/**
	 * 返回 demensionName
	 * @return
	 */
	public String getDemensionName() 
	{
		return demensionName;
	}

	public void setUpLowLevel(Integer upLowLevel) 
	{
		this.upLowLevel = upLowLevel;
	}
	/**
	 * 返回 上下级层次
	 * @return
	 */
	public Integer getUpLowLevel() 
	{
		return upLowLevel;
	}

	public void setUpLowType(Short upLowType) 
	{
		this.upLowType = upLowType;
	}
	/**
	 * 返回 上下级类型
	 * @return
	 */
	public Short getUpLowType() 
	{
		return upLowType;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmNodeUserUplow)) 
		{
			return false;
		}
		BpmNodeUserUplow rhs = (BpmNodeUserUplow) object;
		return new EqualsBuilder()
		.append(this.nodeUserId, rhs.nodeUserId)
		.append(this.demensionId, rhs.demensionId)
		.append(this.demensionName, rhs.demensionName)
		.append(this.upLowLevel, rhs.upLowLevel)
		.append(this.upLowType, rhs.upLowType)
		.append(this.isCharge, rhs.isCharge)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.nodeUserId) 
		.append(this.demensionId) 
		.append(this.demensionName) 
		.append(this.upLowLevel) 
		.append(this.upLowType)
		.append(this.isCharge)
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("nodeUserId", this.nodeUserId) 
		.append("demensionId", this.demensionId) 
		.append("demensionName", this.demensionName) 
		.append("upLowLevel", this.upLowLevel) 
		.append("upLowType", this.upLowType)
		.append("isCharge", this.isCharge)
		.toString();
	}
   
  

}