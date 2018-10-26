package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/**
 * 对象功能:组织参数属性 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-24 10:04:50
 */
public class SysOrgParam extends SysBaseParam
{

	// 组织ID
	protected Long orgId;

	public SysOrgParam(){}

	public SysOrgParam(SysParam param){
		this.paramId=param.getParamId();
		this.paramName=param.getParamName();
		this.dataType=param.getDataType();
		this.sourceKey=param.getSourceKey();
		this.sourceType=param.getSourceType();
		this.description=param.getDescription();
		this.status_=param.getStatus_();
	}

	

	public void setOrgId(Long orgId) 
	{
		this.orgId = orgId;
	}
	/**
	 * 返回 组织ID
	 * @return
	 */
	public Long getOrgId() 
	{
		return orgId;
	}

	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysOrgParam)) 
		{
			return false;
		}
		SysOrgParam rhs = (SysOrgParam) object;
		return new EqualsBuilder()
		.append(this.valueId, rhs.valueId)
		.append(this.orgId, rhs.orgId)
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
		.append(this.orgId) 
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
		.append("orgId", this.orgId) 
		.append("paramId", this.paramId) 
		.append("paramValue", this.paramValue) 
		.toString();
	}
   
  

}