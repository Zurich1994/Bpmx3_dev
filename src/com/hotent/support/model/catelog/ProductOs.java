package com.hotent.support.model.catelog;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:Support操作系统 Model对象
 */
public class ProductOs extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *产品ID
	 */
	protected Long  PRODUCTID;
	/**
	 *操作系统
	 */
	protected String  OS;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPRODUCTID(Long PRODUCTID) 
	{
		this.PRODUCTID = PRODUCTID;
	}
	/**
	 * 返回 产品ID
	 * @return
	 */
	public Long getPRODUCTID() 
	{
		return this.PRODUCTID;
	}
	public void setOS(String OS) 
	{
		this.OS = OS;
	}
	/**
	 * 返回 操作系统
	 * @return
	 */
	public String getOS() 
	{
		return this.OS;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ProductOs)) 
		{
			return false;
		}
		ProductOs rhs = (ProductOs) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.PRODUCTID, rhs.PRODUCTID)
		.append(this.OS, rhs.OS)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.PRODUCTID) 
		.append(this.OS) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("PRODUCTID", this.PRODUCTID) 
		.append("OS", this.OS) 
		.toString();
	}

}
