package com.hotent.tpcc.model.tpcc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:item Model对象
 */
public class Item extends BaseModel
{
	//主键
	protected Long i_id;
	/**
	 *i_im_id
	 */
	protected Long  i_im_id;
	/**
	 *i_name
	 */
	protected String  i_name;
	/**
	 *i_price
	 */
	protected Long  i_price;
	/**
	 *i_data
	 */
	protected String  i_data;
	
	public Long getI_id() {
		return i_id;
	}
	public void setI_id(Long i_id) {
		this.i_id = i_id;
	}
	
	public void setI_im_id(Long i_im_id) 
	{
		this.i_im_id = i_im_id;
	}
	/**
	 * 返回 i_im_id
	 * @return
	 */
	public Long getI_im_id() 
	{
		return this.i_im_id;
	}
	public void setI_name(String i_name) 
	{
		this.i_name = i_name;
	}
	/**
	 * 返回 i_name
	 * @return
	 */
	public String getI_name() 
	{
		return this.i_name;
	}
	public void setI_price(Long i_price) 
	{
		this.i_price = i_price;
	}
	/**
	 * 返回 i_price
	 * @return
	 */
	public Long getI_price() 
	{
		return this.i_price;
	}
	public void setI_data(String i_data) 
	{
		this.i_data = i_data;
	}
	/**
	 * 返回 i_data
	 * @return
	 */
	public String getI_data() 
	{
		return this.i_data;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Item)) 
		{
			return false;
		}
		Item rhs = (Item) object;
		return new EqualsBuilder()
		.append(this.i_id,rhs.i_id)
		.append(this.i_im_id, rhs.i_im_id)
		.append(this.i_name, rhs.i_name)
		.append(this.i_price, rhs.i_price)
		.append(this.i_data, rhs.i_data)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.i_id)
		.append(this.i_im_id) 
		.append(this.i_name) 
		.append(this.i_price) 
		.append(this.i_data) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("i_id",this.i_id)
		.append("i_im_id", this.i_im_id) 
		.append("i_name", this.i_name) 
		.append("i_price", this.i_price) 
		.append("i_data", this.i_data) 
		.toString();
	}

}
