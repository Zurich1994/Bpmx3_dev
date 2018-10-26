package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:脚本管理 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-01-05 12:01:19
 */
public class Script extends BaseModel
{
	// 主键
	protected Long id;
	// 名称
	protected String name;
	// 脚本
	protected String script;
	// 脚本分类
	protected String category;
	// 备注
	protected String memo;

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
		return id;
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

	public void setScript(String script) 
	{
		this.script = script;
	}
	/**
	 * 返回 脚本
	 * @return
	 */
	public String getScript() 
	{
		return script;
	}

	public void setCategory(String category) 
	{
		this.category = category;
	}
	/**
	 * 返回 脚本分类
	 * @return
	 */
	public String getCategory() 
	{
		return category;
	}

	public void setMemo(String memo) 
	{
		this.memo = memo;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getMemo() 
	{
		return memo;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Script)) 
		{
			return false;
		}
		Script rhs = (Script) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.script, rhs.script)
		.append(this.category, rhs.category)
		.append(this.memo, rhs.memo)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.script) 
		.append(this.category) 
		.append(this.memo) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("script", this.script) 
		.append("category", this.category) 
		.append("memo", this.memo) 
		.toString();
	}
   
  

}