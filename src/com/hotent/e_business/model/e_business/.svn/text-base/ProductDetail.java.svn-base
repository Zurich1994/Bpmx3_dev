package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:商品详细表 Model对象
 */
public class ProductDetail extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *ITEM ID
	 */
	protected String  PRODUCTID;
	/**
	 *客户类型简称
	 */
	protected String  CUSTOMER_TYPE_SHORT;
	/**
	 *产品细节
	 */
	protected String  DETAIL;
	/**
	 *地区名
	 */
	protected String  REGION;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPRODUCTID(String PRODUCTID) 
	{
		this.PRODUCTID = PRODUCTID;
	}
	/**
	 * 返回 ITEM ID
	 * @return
	 */
	public String getPRODUCTID() 
	{
		return this.PRODUCTID;
	}
	public void setCUSTOMER_TYPE_SHORT(String CUSTOMER_TYPE_SHORT) 
	{
		this.CUSTOMER_TYPE_SHORT = CUSTOMER_TYPE_SHORT;
	}
	/**
	 * 返回 客户类型简称
	 * @return
	 */
	public String getCUSTOMER_TYPE_SHORT() 
	{
		return this.CUSTOMER_TYPE_SHORT;
	}
	public void setDETAIL(String DETAIL) 
	{
		this.DETAIL = DETAIL;
	}
	/**
	 * 返回 产品细节
	 * @return
	 */
	public String getDETAIL() 
	{
		return this.DETAIL;
	}
	public void setREGION(String REGION) 
	{
		this.REGION = REGION;
	}
	/**
	 * 返回 地区名
	 * @return
	 */
	public String getREGION() 
	{
		return this.REGION;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ProductDetail)) 
		{
			return false;
		}
		ProductDetail rhs = (ProductDetail) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.PRODUCTID, rhs.PRODUCTID)
		.append(this.CUSTOMER_TYPE_SHORT, rhs.CUSTOMER_TYPE_SHORT)
		.append(this.DETAIL, rhs.DETAIL)
		.append(this.REGION, rhs.REGION)
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
		.append(this.CUSTOMER_TYPE_SHORT) 
		.append(this.DETAIL) 
		.append(this.REGION) 
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
		.append("CUSTOMER_TYPE_SHORT", this.CUSTOMER_TYPE_SHORT) 
		.append("DETAIL", this.DETAIL) 
		.append("REGION", this.REGION) 
		.toString();
	}

}
