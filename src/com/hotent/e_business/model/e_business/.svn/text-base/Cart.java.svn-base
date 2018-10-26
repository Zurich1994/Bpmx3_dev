package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:购物车 Model对象
 */
public class Cart extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *EMAIL地址
	 */
	protected String  EMAIL;
	/**
	 *ITEMID
	 */
	protected String  PRODUCTID;
	/**
	 *ITEM名称
	 */
	protected String  ITEMName;
	/**
	 *组件类型
	 */
	protected String  COMPONENTTYPE;
	/**
	 *组件id
	 */
	protected String  COMPONENTID;
	/**
	 *数量
	 */
	protected Long  QUANTITY;
	/**
	 *单价
	 */
	protected String  UNITPRICE;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEMAIL(String EMAIL) 
	{
		this.EMAIL = EMAIL;
	}
	/**
	 * 返回 EMAIL地址
	 * @return
	 */
	public String getEMAIL() 
	{
		return this.EMAIL;
	}
	public void setPRODUCTID(String PRODUCTID) 
	{
		this.PRODUCTID = PRODUCTID;
	}
	/**
	 * 返回 ITEMID
	 * @return
	 */
	public String getPRODUCTID() 
	{
		return this.PRODUCTID;
	}
	public void setITEMName(String ITEMName) 
	{
		this.ITEMName = ITEMName;
	}
	/**
	 * 返回 ITEM名称
	 * @return
	 */
	public String getITEMName() 
	{
		return this.ITEMName;
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
	public void setCOMPONENTID(String COMPONENTID) 
	{
		this.COMPONENTID = COMPONENTID;
	}
	/**
	 * 返回 组件id
	 * @return
	 */
	public String getCOMPONENTID() 
	{
		return this.COMPONENTID;
	}
	public void setQUANTITY(Long QUANTITY) 
	{
		this.QUANTITY = QUANTITY;
	}
	/**
	 * 返回 数量
	 * @return
	 */
	public Long getQUANTITY() 
	{
		return this.QUANTITY;
	}
	public void setUNITPRICE(String UNITPRICE) 
	{
		this.UNITPRICE = UNITPRICE;
	}
	/**
	 * 返回 单价
	 * @return
	 */
	public String getUNITPRICE() 
	{
		return this.UNITPRICE;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Cart)) 
		{
			return false;
		}
		Cart rhs = (Cart) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.EMAIL, rhs.EMAIL)
		.append(this.PRODUCTID, rhs.PRODUCTID)
		.append(this.ITEMName, rhs.ITEMName)
		.append(this.COMPONENTTYPE, rhs.COMPONENTTYPE)
		.append(this.COMPONENTID, rhs.COMPONENTID)
		.append(this.QUANTITY, rhs.QUANTITY)
		.append(this.UNITPRICE, rhs.UNITPRICE)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.EMAIL) 
		.append(this.PRODUCTID) 
		.append(this.ITEMName) 
		.append(this.COMPONENTTYPE) 
		.append(this.COMPONENTID) 
		.append(this.QUANTITY) 
		.append(this.UNITPRICE) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("EMAIL", this.EMAIL) 
		.append("PRODUCTID", this.PRODUCTID) 
		.append("ITEMName", this.ITEMName) 
		.append("COMPONENTTYPE", this.COMPONENTTYPE) 
		.append("COMPONENTID", this.COMPONENTID) 
		.append("QUANTITY", this.QUANTITY) 
		.append("UNITPRICE", this.UNITPRICE) 
		.toString();
	}

}
