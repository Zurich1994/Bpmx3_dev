package com.hotent.Newjsprelation.model.Newjsprelation;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:newjsprelation Model对象
 */
public class Newjsprelation extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *FORMDEFID
	 */
	protected Long  FORMDEFID;
	/**
	 *nameandvalue
	 */
	protected String  nameandvalue;
	/**
	 *name
	 */
	protected String  name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setFORMDEFID(Long FORMDEFID) 
	{
		this.FORMDEFID = FORMDEFID;
	}
	/**
	 * 返回 FORMDEFID
	 * @return
	 */
	public Long getFORMDEFID() 
	{
		return this.FORMDEFID;
	}
	public void setNameandvalue(String nameandvalue) 
	{
		this.nameandvalue = nameandvalue;
	}
	/**
	 * 返回 nameandvalue
	 * @return
	 */
	public String getNameandvalue() 
	{
		return this.nameandvalue;
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
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Newjsprelation)) 
		{
			return false;
		}
		Newjsprelation rhs = (Newjsprelation) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.FORMDEFID, rhs.FORMDEFID)
		.append(this.nameandvalue, rhs.nameandvalue)
		.append(this.name, rhs.name)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.FORMDEFID) 
		.append(this.nameandvalue) 
		.append(this.name) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("FORMDEFID", this.FORMDEFID) 
		.append("nameandvalue", this.nameandvalue) 
		.append("name", this.name) 
		.toString();
	}

}
