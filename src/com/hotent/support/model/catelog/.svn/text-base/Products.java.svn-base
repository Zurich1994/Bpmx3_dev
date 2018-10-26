package com.hotent.support.model.catelog;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:support产品表 Model对象
 */
public class Products extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *产品ID
	 */
	protected Long  PRODUCTID;
	/**
	 *产品目录ID
	 */
	protected String  CATEGORYID;
	/**
	 *产品
	 */
	protected String  PRODUCT;
	/**
	 *搜索关键字
	 */
	protected String  KEYWORDS;
	protected Long  runId=0L;
	
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
	public void setPRODUCT(String PRODUCT) 
	{
		this.PRODUCT = PRODUCT;
	}
	/**
	 * 返回 产品
	 * @return
	 */
	public String getPRODUCT() 
	{
		return this.PRODUCT;
	}
	public void setKEYWORDS(String KEYWORDS) 
	{
		this.KEYWORDS = KEYWORDS;
	}
	/**
	 * 返回 搜索关键字
	 * @return
	 */
	public String getKEYWORDS() 
	{
		return this.KEYWORDS;
	}
	public Long getRunId() {
		return runId;
	}
	public void setRunId(Long runId) {
		this.runId = runId;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Products)) 
		{
			return false;
		}
		Products rhs = (Products) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.PRODUCTID, rhs.PRODUCTID)
		.append(this.CATEGORYID, rhs.CATEGORYID)
		.append(this.PRODUCT, rhs.PRODUCT)
		.append(this.KEYWORDS, rhs.KEYWORDS)
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
		.append(this.CATEGORYID) 
		.append(this.PRODUCT) 
		.append(this.KEYWORDS) 
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
		.append("CATEGORYID", this.CATEGORYID) 
		.append("PRODUCT", this.PRODUCT) 
		.append("KEYWORDS", this.KEYWORDS) 
		.toString();
	}

}
