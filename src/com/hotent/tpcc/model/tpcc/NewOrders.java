package com.hotent.tpcc.model.tpcc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:new_orders Model对象
 */
public class NewOrders extends BaseModel
{
	//主键
	protected Long no_o_id;
	/**
	 *no_d_id
	 */
	protected Long  no_d_id;
	/**
	 *no_w_id
	 */
	protected Long  no_w_id;
	
	public Long getNo_o_id() {
		return no_o_id;
	}
	public void setNo_o_id(Long no_o_id) {
		this.no_o_id = no_o_id;
	}
	
	public void setNo_d_id(Long no_d_id) 
	{
		this.no_d_id = no_d_id;
	}
	/**
	 * 返回 no_d_id
	 * @return
	 */
	public Long getNo_d_id() 
	{
		return this.no_d_id;
	}
	public void setNo_w_id(Long no_w_id) 
	{
		this.no_w_id = no_w_id;
	}
	/**
	 * 返回 no_w_id
	 * @return
	 */
	public Long getNo_w_id() 
	{
		return this.no_w_id;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof NewOrders)) 
		{
			return false;
		}
		NewOrders rhs = (NewOrders) object;
		return new EqualsBuilder()
		.append(this.no_o_id,rhs.no_o_id)
		.append(this.no_d_id, rhs.no_d_id)
		.append(this.no_w_id, rhs.no_w_id)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.no_o_id)
		.append(this.no_d_id) 
		.append(this.no_w_id) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("no_o_id",this.no_o_id)
		.append("no_d_id", this.no_d_id) 
		.append("no_w_id", this.no_w_id) 
		.toString();
	}

}
