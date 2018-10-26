package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:电子商务搜索表 Model对象
 */
public class ProductModels extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *地区名
	 */
	protected String  REGION;
	/**
	 *客户类型简称
	 */
	protected String  CUSTOMER_TYPE_SHORT;
	/**
	 *类别
	 */
	protected String  CATEGORY;
	/**
	 *ITEM ID
	 */
	protected String  PRODUCTID;
	/**
	 *产品名称
	 */
	protected String  PRODUCTNAME;
	/**
	 *产品特征1
	 */
	protected String  FEATURE0;
	/**
	 *产品特征2
	 */
	protected String  FEATURE1;
	/**
	 *产品特征3
	 */
	protected String  FEATURE2;
	/**
	 *产品特征4
	 */
	protected String  FEATURE3;
	/**
	 *关键字
	 */
	protected String  KEYWORDS;
	/**
	 *链接
	 */
	protected String  URL;
	/**
	 *产品描述
	 */
	protected String  DESCRIPTION;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setCATEGORY(String CATEGORY) 
	{
		this.CATEGORY = CATEGORY;
	}
	/**
	 * 返回 类别
	 * @return
	 */
	public String getCATEGORY() 
	{
		return this.CATEGORY;
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
	public void setPRODUCTNAME(String PRODUCTNAME) 
	{
		this.PRODUCTNAME = PRODUCTNAME;
	}
	/**
	 * 返回 产品名称
	 * @return
	 */
	public String getPRODUCTNAME() 
	{
		return this.PRODUCTNAME;
	}
	public void setFEATURE0(String FEATURE0) 
	{
		this.FEATURE0 = FEATURE0;
	}
	/**
	 * 返回 产品特征1
	 * @return
	 */
	public String getFEATURE0() 
	{
		return this.FEATURE0;
	}
	public void setFEATURE1(String FEATURE1) 
	{
		this.FEATURE1 = FEATURE1;
	}
	/**
	 * 返回 产品特征2
	 * @return
	 */
	public String getFEATURE1() 
	{
		return this.FEATURE1;
	}
	public void setFEATURE2(String FEATURE2) 
	{
		this.FEATURE2 = FEATURE2;
	}
	/**
	 * 返回 产品特征3
	 * @return
	 */
	public String getFEATURE2() 
	{
		return this.FEATURE2;
	}
	public void setFEATURE3(String FEATURE3) 
	{
		this.FEATURE3 = FEATURE3;
	}
	/**
	 * 返回 产品特征4
	 * @return
	 */
	public String getFEATURE3() 
	{
		return this.FEATURE3;
	}
	public void setKEYWORDS(String KEYWORDS) 
	{
		this.KEYWORDS = KEYWORDS;
	}
	/**
	 * 返回 关键字
	 * @return
	 */
	public String getKEYWORDS() 
	{
		return this.KEYWORDS;
	}
	public void setURL(String URL) 
	{
		this.URL = URL;
	}
	/**
	 * 返回 链接
	 * @return
	 */
	public String getURL() 
	{
		return this.URL;
	}
	public void setDESCRIPTION(String DESCRIPTION) 
	{
		this.DESCRIPTION = DESCRIPTION;
	}
	/**
	 * 返回 产品描述
	 * @return
	 */
	public String getDESCRIPTION() 
	{
		return this.DESCRIPTION;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ProductModels)) 
		{
			return false;
		}
		ProductModels rhs = (ProductModels) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.REGION, rhs.REGION)
		.append(this.CUSTOMER_TYPE_SHORT, rhs.CUSTOMER_TYPE_SHORT)
		.append(this.CATEGORY, rhs.CATEGORY)
		.append(this.PRODUCTID, rhs.PRODUCTID)
		.append(this.PRODUCTNAME, rhs.PRODUCTNAME)
		.append(this.FEATURE0, rhs.FEATURE0)
		.append(this.FEATURE1, rhs.FEATURE1)
		.append(this.FEATURE2, rhs.FEATURE2)
		.append(this.FEATURE3, rhs.FEATURE3)
		.append(this.KEYWORDS, rhs.KEYWORDS)
		.append(this.URL, rhs.URL)
		.append(this.DESCRIPTION, rhs.DESCRIPTION)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.REGION) 
		.append(this.CUSTOMER_TYPE_SHORT) 
		.append(this.CATEGORY) 
		.append(this.PRODUCTID) 
		.append(this.PRODUCTNAME) 
		.append(this.FEATURE0) 
		.append(this.FEATURE1) 
		.append(this.FEATURE2) 
		.append(this.FEATURE3) 
		.append(this.KEYWORDS) 
		.append(this.URL) 
		.append(this.DESCRIPTION) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("REGION", this.REGION) 
		.append("CUSTOMER_TYPE_SHORT", this.CUSTOMER_TYPE_SHORT) 
		.append("CATEGORY", this.CATEGORY) 
		.append("PRODUCTID", this.PRODUCTID) 
		.append("PRODUCTNAME", this.PRODUCTNAME) 
		.append("FEATURE0", this.FEATURE0) 
		.append("FEATURE1", this.FEATURE1) 
		.append("FEATURE2", this.FEATURE2) 
		.append("FEATURE3", this.FEATURE3) 
		.append("KEYWORDS", this.KEYWORDS) 
		.append("URL", this.URL) 
		.append("DESCRIPTION", this.DESCRIPTION) 
		.toString();
	}

}
