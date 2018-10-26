package com.hotent.platform.model.bpmCommonWsParams;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 对象功能:通用webservice调用参数 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-10-17 10:09:20
 */
public class BpmCommonWsParams extends BaseModel
{
	/**
	 * 参数类型(字符串)
	 */
	public final static Integer PARAM_TYPE_STRING = 1;
	/**
	 * 参数类型(数字)
	 */
	public final static Integer PARAM_TYPE_NUMBER = 2;
	/**
	 * 参数类型(列表)
	 */
	public final static Integer PARAM_TYPE_LIST = 3;
	/**
	 * 参数类型(日期)
	 */
	public final static Integer PARAM_TYPE_DATE = 4;
	
	// 主键
	protected Long  id;
	// webservice设置ID
	protected Long  setid;
	// 参数名
	protected String  name;
	// 参数类型
	protected Integer paramType;
	// 参数说明
	protected String  description;
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setSetid(Long setid) 
	{
		this.setid = setid;
	}
	/**
	 * 返回 webservice设置ID
	 * @return
	 */
	public Long getSetid() 
	{
		return this.setid;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 参数名
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setParamType(Integer paramType) 
	{
		this.paramType = paramType;
	}
	/**
	 * 返回 参数类型
	 * @return
	 */
	public Integer getParamType() 
	{
		return this.paramType;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	/**
	 * 返回 参数说明
	 * @return
	 */
	public String getDescription() 
	{
		return this.description;
	}

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof BpmCommonWsParams)) 
		{
			return false;
		}
		BpmCommonWsParams rhs = (BpmCommonWsParams) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.setid, rhs.setid)
		.append(this.name, rhs.name)
		.append(this.paramType, rhs.paramType)
		.append(this.description, rhs.description)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.setid) 
		.append(this.name) 
		.append(this.paramType) 
		.append(this.description) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("setid", this.setid) 
		.append("name", this.name) 
		.append("paramType", this.paramType) 
		.append("description", this.description) 
		.toString();
	}
   
  

}