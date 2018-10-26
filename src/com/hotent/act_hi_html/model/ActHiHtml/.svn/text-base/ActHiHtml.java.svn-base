package com.hotent.act_hi_html.model.ActHiHtml;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.hotent.core.model.BaseModel;
/**
 * 对象功能:act_hi_html Model对象
 */
public class ActHiHtml extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *操作图实例化ID
	 */
	protected String  actInstId;
	/**
	 *html
	 */
	protected String  html;
	/**
	 *模板，表单
	 */
	protected String  type;
	/**
	 *HTML创建时间
	 */
	protected java.util.Date  createtime;
	/**
	 *生成HTML所需的ID
	 */
	protected String  createId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setActInstId(String actInstId) 
	{
		this.actInstId = actInstId;
	}
	/**
	 * 返回 操作图实例化ID
	 * @return
	 */
	public String getActInstId() 
	{
		return this.actInstId;
	}
	public void setHtml(String html) 
	{
		this.html = html;
	}
	/**
	 * 返回 html
	 * @return
	 */
	public String getHtml() 
	{
		return this.html;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	/**
	 * 返回 模板，表单
	 * @return
	 */
	public String getType() 
	{
		return this.type;
	}
	public void setCreatetime(java.util.Date createtime) 
	{
		this.createtime = createtime;
	}
	/**
	 * 返回 HTML创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() 
	{
		return this.createtime;
	}
	public void setCreateId(String createId) 
	{
		this.createId = createId;
	}
	/**
	 * 返回 生成HTML所需的ID
	 * @return
	 */
	public String getCreateId() 
	{
		return this.createId;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ActHiHtml)) 
		{
			return false;
		}
		ActHiHtml rhs = (ActHiHtml) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.actInstId, rhs.actInstId)
		.append(this.html, rhs.html)
		.append(this.type, rhs.type)
		.append(this.createtime, rhs.createtime)
		.append(this.createId, rhs.createId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.actInstId) 
		.append(this.html) 
		.append(this.type) 
		.append(this.createtime) 
		.append(this.createId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("actInstId", this.actInstId) 
		.append("html", this.html) 
		.append("type", this.type) 
		.append("createtime", this.createtime) 
		.append("createId", this.createId) 
		.toString();
	}

}