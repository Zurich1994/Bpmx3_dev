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
 * 对象功能:流程定义权限明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-19 09:00:53
 */
@XmlRootElement(name = "bpmDefRights")
@XmlAccessorType(XmlAccessType.NONE)
public class BpmDefRights extends BaseModel implements Cloneable
{
	
	/**所有用户 */
	public static final short RIGHT_TYPE_ALL=0;
	/**用户 */
	public static final short RIGHT_TYPE_USER=1;
	/**角色*/
	public static final short RIGHT_TYPE_ROLE=2;
	/**组织(本层级)*/
	public static final short RIGHT_TYPE_ORG=3;
	/**岗位*/
	public static final short RIGHT_TYPE_POSITION=4;
	/**组织（包含子组织)*/
	public static final short RIGHT_TYPE_ORG_GRANT=7;
	
	/**流程*/
	public static final short SEARCH_TYPE_DEF=0;
	/**分类*/
	public static final short SEARCH_TYPE_GLT=1;
	// id
	@XmlAttribute
	protected Long id;
	// 定义ID
	@XmlAttribute
	protected String defKey;
	// 流程分类
	@XmlAttribute
	protected Long flowTypeId;
	// 1,用户      2.角色            3.组织 
	@XmlAttribute
	protected Short rightType;
	// 权限所有者ID
	@XmlAttribute
	protected Long ownerId;
	// 权限所有者
	@XmlAttribute
	protected String ownerName;
	// 0.分类,1.流程
	@XmlAttribute
	protected Short searchType;

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

	public void setDefKey(String defKey) 
	{
		this.defKey = defKey;
	}
	/**
	 * 返回 定义ID
	 * @return
	 */
	public String getDdefKey() 
	{
		return defKey;
	}

	public void setFlowTypeId(Long flowTypeId) 
	{
		this.flowTypeId = flowTypeId;
	}
	/**
	 * 返回 流程分类
	 * @return
	 */
	public Long getFlowTypeId() 
	{
		return flowTypeId;
	}

	public void setRightType(Short rightType) 
	{
		this.rightType = rightType;
	}
	/**
	 * 返回 1,用户      2.角色            3.组织 
	 * @return
	 */
	public Short getRightType() 
	{
		return rightType;
	}

	public void setOwnerId(Long ownerId) 
	{
		this.ownerId = ownerId;
	}
	/**
	 * 返回 权限所有者ID
	 * @return
	 */
	public Long getOwnerId() 
	{
		return ownerId;
	}

	public void setOwnerName(String ownerName) 
	{
		this.ownerName = ownerName;
	}
	/**
	 * 返回 权限所有者
	 * @return
	 */
	public String getOwnerName() 
	{
		return ownerName;
	}

	public void setSearchType(Short searchType) 
	{
		this.searchType = searchType;
	}
	/**
	 * 返回 0.分类,2.流程
	 * @return
	 */
	public Short getSearchType() 
	{
		return searchType;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmDefRights)) 
		{
			return false;
		}
		BpmDefRights rhs = (BpmDefRights) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.defKey, rhs.defKey)
		.append(this.flowTypeId, rhs.flowTypeId)
		.append(this.rightType, rhs.rightType)
		.append(this.ownerId, rhs.ownerId)
		.append(this.ownerName, rhs.ownerName)
		.append(this.searchType, rhs.searchType)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.defKey) 
		.append(this.flowTypeId) 
		.append(this.rightType) 
		.append(this.ownerId) 
		.append(this.ownerName) 
		.append(this.searchType) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("defId", this.defKey) 
		.append("flowTypeId", this.flowTypeId) 
		.append("rightType", this.rightType) 
		.append("ownerId", this.ownerId) 
		.append("ownerName", this.ownerName) 
		.append("searchType", this.searchType) 
		.toString();
	}
	public Object clone()
	{
		BpmDefRights obj=null;
		try{
			obj=(BpmDefRights)super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
  

}