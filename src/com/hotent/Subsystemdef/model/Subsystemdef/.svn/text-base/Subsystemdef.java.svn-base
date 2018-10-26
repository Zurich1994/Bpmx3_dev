package com.hotent.Subsystemdef.model.Subsystemdef;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:子系统 Model对象
 */
public class Subsystemdef extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *sys_id
	 */
	protected Long  sys_id;
	/**
	 *sys_defkey
	 */
	protected String  sys_defkey;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSys_id(Long sys_id) 
	{
		this.sys_id = sys_id;
	}
	/**
	 * 返回 sys_id
	 * @return
	 */
	public Long getSys_id() 
	{
		return this.sys_id;
	}
	public void setSys_defkey(String sys_defkey) 
	{
		this.sys_defkey = sys_defkey;
	}
	/**
	 * 返回 sys_defkey
	 * @return
	 */
	public String getSys_defkey() 
	{
		return this.sys_defkey;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Subsystemdef)) 
		{
			return false;
		}
		Subsystemdef rhs = (Subsystemdef) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.sys_id, rhs.sys_id)
		.append(this.sys_defkey, rhs.sys_defkey)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.sys_id) 
		.append(this.sys_defkey) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("sys_id", this.sys_id) 
		.append("sys_defkey", this.sys_defkey) 
		.toString();
	}

}
