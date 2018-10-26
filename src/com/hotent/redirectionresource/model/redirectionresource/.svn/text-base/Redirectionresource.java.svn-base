package com.hotent.redirectionresource.model.redirectionresource;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:redirectionresource Model对象
 */
public class Redirectionresource extends BaseModel
{
	//主键
	protected Long redirectionno;
	/**
	 *url
	 */
	protected String  url;
	/**
	 *name
	 */
	protected String  name;
	/**
	 *alias
	 */
	protected String  alias;
	
	public Long getRedirectionno() {
		return redirectionno;
	}
	public void setRedirectionno(Long redirectionno) {
		this.redirectionno = redirectionno;
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
		return this.url;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 name
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setAlias(String alias) 
	{
		this.alias = alias;
	}
	/**
	 * 返回 alias
	 * @return
	 */
	public String getAlias() 
	{
		return this.alias;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Redirectionresource)) 
		{
			return false;
		}
		Redirectionresource rhs = (Redirectionresource) object;
		return new EqualsBuilder()
		.append(this.redirectionno,rhs.redirectionno)
		.append(this.url, rhs.url)
		.append(this.name, rhs.name)
		.append(this.alias, rhs.alias)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.redirectionno)
		.append(this.url) 
		.append(this.name) 
		.append(this.alias) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("redirectionno",this.redirectionno)
		.append("url", this.url) 
		.append("name", this.name) 
		.append("alias", this.alias) 
		.toString();
	}

}
