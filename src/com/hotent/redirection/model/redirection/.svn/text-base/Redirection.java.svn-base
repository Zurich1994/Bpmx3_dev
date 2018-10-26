package com.hotent.redirection.model.redirection;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:redirection Model对象
 */
public class Redirection extends BaseModel
{
	//主键
	protected Long defid;
	/**
	 *nodeID
	 */
	protected String  nodeid;
	/**
	 *redirectionURL
	 */
	protected String  redirectionurl;
	
	public Long getDefid() {
		return defid;
	}
	public void setDefid(Long defid) {
		this.defid = defid;
	}
	
	public void setNodeid(String nodeid) 
	{
		this.nodeid = nodeid;
	}
	/**
	 * 返回 nodeID
	 * @return
	 */
	public String getNodeid() 
	{
		return this.nodeid;
	}
	public void setRedirectionurl(String redirectionurl) 
	{
		this.redirectionurl = redirectionurl;
	}
	/**
	 * 返回 redirectionURL
	 * @return
	 */
	public String getRedirectionurl() 
	{
		return this.redirectionurl;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Redirection)) 
		{
			return false;
		}
		Redirection rhs = (Redirection) object;
		return new EqualsBuilder()
		.append(this.defid,rhs.defid)
		.append(this.nodeid, rhs.nodeid)
		.append(this.redirectionurl, rhs.redirectionurl)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.defid)
		.append(this.nodeid) 
		.append(this.redirectionurl) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("defid",this.defid)
		.append("nodeid", this.nodeid) 
		.append("redirectionurl", this.redirectionurl) 
		.toString();
	}

}
