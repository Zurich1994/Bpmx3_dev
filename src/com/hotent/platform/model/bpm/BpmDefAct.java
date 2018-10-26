package com.hotent.platform.model.bpm;


import com.hotent.core.model.BaseModel;

/**
 * 对象功能:流程授权主表明细 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xucx
 * 创建时间:2014-03-05 10:04:50
 */

public class BpmDefAct extends BaseModel implements Cloneable
{
	
	// id
	protected Long id;
	
	// 流程分管权限主表ID
	protected Long authorizeId;
	
	// 流程KEY
	protected String defKey;
	
	// 流程名称
	protected String defName;
	
	// 流程权限内容
	protected String rightContent;

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

	public String getDefKey()
	{
		return defKey;
	}

	public void setDefKey(String defKey)
	{
		this.defKey = defKey;
	}

	public String getDefName()
	{
		return defName;
	}

	public void setDefName(String defName)
	{
		this.defName = defName;
	}

	public String getRightContent()
	{
		return rightContent;
	}

	public void setRightContent(String rightContent)
	{
		this.rightContent = rightContent;
	}

}