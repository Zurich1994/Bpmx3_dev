package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:用户定制 Model对象
 */
public class Component extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *ITEM ID
	 */
	protected String  PRODUCTID;
	/**
	 *页数
	 */
	protected Long  PAGE;
	/**
	 *组件类型
	 */
	protected String  COMPONENTTYPE;
	/**
	 *组件名
	 */
	protected String  COMPONENTNAME;
	/**
	 *组件ID
	 */
	protected String  COMPONENTID;
	/**
	 *价格
	 */
	protected Long  PRICE;
	/**
	 *货币
	 */
	protected String  CURRENCY;
	
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
	public void setPAGE(Long PAGE) 
	{
		this.PAGE = PAGE;
	}
	/**
	 * 返回 页数
	 * @return
	 */
	public Long getPAGE() 
	{
		return this.PAGE;
	}
	public void setCOMPONENTTYPE(String COMPONENTTYPE) 
	{
		this.COMPONENTTYPE = COMPONENTTYPE;
	}
	/**
	 * 返回 组件类型
	 * @return
	 */
	public String getCOMPONENTTYPE() 
	{
		return this.COMPONENTTYPE;
	}
	public void setCOMPONENTNAME(String COMPONENTNAME) 
	{
		this.COMPONENTNAME = COMPONENTNAME;
	}
	/**
	 * 返回 组件名
	 * @return
	 */
	public String getCOMPONENTNAME() 
	{
		return this.COMPONENTNAME;
	}
	public void setCOMPONENTID(String COMPONENTID) 
	{
		this.COMPONENTID = COMPONENTID;
	}
	/**
	 * 返回 组件ID
	 * @return
	 */
	public String getCOMPONENTID() 
	{
		return this.COMPONENTID;
	}
	public void setPRICE(Long PRICE) 
	{
		this.PRICE = PRICE;
	}
	/**
	 * 返回 价格
	 * @return
	 */
	public Long getPRICE() 
	{
		return this.PRICE;
	}
	public void setCURRENCY(String CURRENCY) 
	{
		this.CURRENCY = CURRENCY;
	}
	/**
	 * 返回 货币
	 * @return
	 */
	public String getCURRENCY() 
	{
		return this.CURRENCY;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Component)) 
		{
			return false;
		}
		Component rhs = (Component) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.PRODUCTID, rhs.PRODUCTID)
		.append(this.PAGE, rhs.PAGE)
		.append(this.COMPONENTTYPE, rhs.COMPONENTTYPE)
		.append(this.COMPONENTNAME, rhs.COMPONENTNAME)
		.append(this.COMPONENTID, rhs.COMPONENTID)
		.append(this.PRICE, rhs.PRICE)
		.append(this.CURRENCY, rhs.CURRENCY)
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
		.append(this.PAGE) 
		.append(this.COMPONENTTYPE) 
		.append(this.COMPONENTNAME) 
		.append(this.COMPONENTID) 
		.append(this.PRICE) 
		.append(this.CURRENCY) 
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
		.append("PAGE", this.PAGE) 
		.append("COMPONENTTYPE", this.COMPONENTTYPE) 
		.append("COMPONENTNAME", this.COMPONENTNAME) 
		.append("COMPONENTID", this.COMPONENTID) 
		.append("PRICE", this.PRICE) 
		.append("CURRENCY", this.CURRENCY) 
		.toString();
	}

}
