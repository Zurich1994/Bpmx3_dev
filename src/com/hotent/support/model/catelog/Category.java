package com.hotent.support.model.catelog;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:Support类别表 Model对象
 */
public class Category extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *产品目录ID
	 */
	protected String  CATEGORYID;
	/**
	 *产品目录
	 */
	protected String  CATEGORYNAME;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCATEGORYID(String CATEGORYID) 
	{
		this.CATEGORYID = CATEGORYID;
	}
	/**
	 * 返回 产品目录ID
	 * @return
	 */
	public String getCATEGORYID() 
	{
		return this.CATEGORYID;
	}
	public void setCATEGORYNAME(String CATEGORYNAME) 
	{
		this.CATEGORYNAME = CATEGORYNAME;
	}
	/**
	 * 返回 产品目录
	 * @return
	 */
	public String getCATEGORYNAME() 
	{
		return this.CATEGORYNAME;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Category)) 
		{
			return false;
		}
		Category rhs = (Category) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.CATEGORYID, rhs.CATEGORYID)
		.append(this.CATEGORYNAME, rhs.CATEGORYNAME)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.CATEGORYID) 
		.append(this.CATEGORYNAME) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("CATEGORYID", this.CATEGORYID) 
		.append("CATEGORYNAME", this.CATEGORYNAME) 
		.toString();
	}

}
