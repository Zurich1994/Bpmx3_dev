package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 对象功能:人员参数属性 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-24 10:04:50
 */
public class SysUserParam extends SysBaseParam
{

	// 用户ID
	protected Long userId;
	
	
	public SysUserParam(){}
	
	public SysUserParam(SysParam param){
		this.paramId=param.getParamId();
		this.paramName=param.getParamName();
		this.dataType=param.getDataType();
		this.sourceKey=param.getSourceKey();
		this.sourceType=param.getSourceType();
		this.description=param.getDescription();
		this.status_=param.getStatus_();
	}
	
	
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public Long getUserId() 
	{
		return userId;
	}

	
	
	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysUserParam)) 
		{
			return false;
		}
		SysUserParam rhs = (SysUserParam) object;
		return new EqualsBuilder()
		.append(this.valueId, rhs.valueId)
		.append(this.userId, rhs.userId)
		.append(this.paramId, rhs.paramId)
		.append(this.paramValue, rhs.paramValue)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.valueId) 
		.append(this.userId) 
		.append(this.paramId) 
		.append(this.paramValue) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("valueId", this.valueId) 
		.append("userId", this.userId) 
		.append("paramId", this.paramId) 
		.append("paramValue", this.paramValue) 
		.toString();
	}
}