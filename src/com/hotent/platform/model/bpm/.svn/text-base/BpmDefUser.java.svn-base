package com.hotent.platform.model.bpm;

import com.hotent.core.model.BaseModel;


/**
 * 对象功能:流程授权主表明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xucx
 * 创建时间:2014-03-05 09:00:53
 */
public class BpmDefUser extends BaseModel implements Cloneable
{
	
	public final static class BPMDEFUSER_RIGHT_TYPE{
		
		/**所有用户 */
		public static final String ALL="all";
		/**用户 */
		public static final String USER="user";
		/**角色*/
		public static final String ROLE="role";
		/**组织(本层级)*/
		public static final String ORG="org";
		/**岗位*/
		public static final String POSITION="position";
		/**组织（包含子组织)*/
		public static final String GRANT="grant";

	}
	
	
	// id
	protected Long id;
	
	// 流程分管权限主表ID
	protected Long authorizeId;
	
	// 权限所有者ID
	protected Long ownerId;
	
	// 权限所有者
	protected String ownerName;
	
	// 权限类型
	protected String rightType;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getAuthorizeId()
	{
		return authorizeId;
	}

	public void setAuthorizeId(Long authorizeId)
	{
		this.authorizeId = authorizeId;
	}

	public Long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(Long ownerId)
	{
		this.ownerId = ownerId;
	}

	public String getOwnerName()
	{
		return ownerName;
	}

	public void setOwnerName(String ownerName)
	{
		this.ownerName = ownerName;
	}

	public String getRightType()
	{
		return rightType;
	}

	public void setRightType(String rightType)
	{
		this.rightType = rightType;
	}

}