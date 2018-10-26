package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:资源URL Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-05 17:00:58
 */
public class ResourcesUrl extends BaseModel
{
	// resUrlId
	protected Long resUrlId;
	// 资源ID
	protected Long resId;
	// 名称
	protected String name;
	// url
	protected String url;
	
	
	

	public void setResUrlId(Long resUrlId) 
	{
		this.resUrlId = resUrlId;
	}
	/**
	 * 返回 resUrlId
	 * @return
	 */
	public Long getResUrlId() 
	{
		return resUrlId;
	}

	public void setResId(Long resId) 
	{
		this.resId = resId;
	}
	/**
	 * 返回 资源ID
	 * @return
	 */
	public Long getResId() 
	{
		return resId;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 名称
	 * @return
	 */
	public String getName() 
	{
		return name;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}
	/**
	 * 返回 url
	 * @return
	 */
	public String getUrl() 
	{
		return url;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ResourcesUrl)) 
		{
			return false;
		}
		ResourcesUrl rhs = (ResourcesUrl) object;
		return new EqualsBuilder()
		.append(this.resUrlId, rhs.resUrlId)
		.append(this.resId, rhs.resId)
		.append(this.name, rhs.name)
		.append(this.url, rhs.url)
		
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.resUrlId) 
		.append(this.resId) 
		.append(this.name) 
		.append(this.url) 
	
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("resUrlId", this.resUrlId) 
		.append("resId", this.resId) 
		.append("name", this.name) 
		.append("url", this.url) 
		
		.toString();
	}
   
  

}