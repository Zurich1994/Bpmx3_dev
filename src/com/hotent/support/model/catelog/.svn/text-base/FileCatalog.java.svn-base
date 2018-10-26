package com.hotent.support.model.catelog;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:文件目录 Model对象
 */
public class FileCatalog extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *产品ID
	 */
	protected Long  PRODUCTID;
	/**
	 *文件目录
	 */
	protected String  FILECATALOG;
	
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
	public void setFILECATALOG(String FILECATALOG) 
	{
		this.FILECATALOG = FILECATALOG;
	}
	/**
	 * 返回 文件目录
	 * @return
	 */
	public String getFILECATALOG() 
	{
		return this.FILECATALOG;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof FileCatalog)) 
		{
			return false;
		}
		FileCatalog rhs = (FileCatalog) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.PRODUCTID, rhs.PRODUCTID)
		.append(this.FILECATALOG, rhs.FILECATALOG)
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
		.append(this.FILECATALOG) 
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
		.append("FILECATALOG", this.FILECATALOG) 
		.toString();
	}

}
