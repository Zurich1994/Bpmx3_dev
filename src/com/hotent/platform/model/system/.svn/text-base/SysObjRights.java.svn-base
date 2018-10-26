package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:对象权限表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-09 17:19:22
 */
public class SysObjRights extends BaseModel{
	
	public static  String RIGHT_TYPE_INDEX_COLUMN ="indexColumn";
	public static  String RIGHT_TYPE_INDEX_MANAGE ="indexManage";
	public static  String RIGHT_TYPE_POPUP_MSG ="popupMsg";
	// 主键
	protected Long  id;
	// 对象类型
	protected String  objType;
	// 权限对象ID
	protected Long  objectId;
	// 授权人ID
	protected Long  ownerId;
	// 授权人
	protected String  owner;
	// 权限类型
	protected String  rightType;

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setObjType(String objType){
		this.objType = objType;
	}
	/**
	 * 返回 对象类型
	 * @return
	 */
	public String getObjType() {
		return this.objType;
	}
	public void setObjectId(Long objectId){
		this.objectId = objectId;
	}
	/**
	 * 返回 权限对象ID
	 * @return
	 */
	public Long getObjectId() {
		return this.objectId;
	}
	public void setOwnerId(Long ownerId){
		this.ownerId = ownerId;
	}
	/**
	 * 返回 授权人ID
	 * @return
	 */
	public Long getOwnerId() {
		return this.ownerId;
	}
	public void setOwner(String owner){
		this.owner = owner;
	}
	/**
	 * 返回 授权人
	 * @return
	 */
	public String getOwner() {
		return this.owner;
	}
	public void setRightType(String rightType){
		this.rightType = rightType;
	}
	/**
	 * 返回 权限类型
	 * @return
	 */
	public String getRightType() {
		return this.rightType;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysObjRights)) 
		{
			return false;
		}
		SysObjRights rhs = (SysObjRights) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.objType, rhs.objType)
		.append(this.objectId, rhs.objectId)
		.append(this.ownerId, rhs.ownerId)
		.append(this.owner, rhs.owner)
		.append(this.rightType, rhs.rightType)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.objType) 
		.append(this.objectId) 
		.append(this.ownerId) 
		.append(this.owner) 
		.append(this.rightType) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("objType", this.objType) 
		.append("objectId", this.objectId) 
		.append("ownerId", this.ownerId) 
		.append("owner", this.owner) 
		.append("rightType", this.rightType) 
		.toString();
	}
   
  

}