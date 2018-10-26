package com.hotent.tpcc.model.tpcc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:history2 Model对象
 */
public class History2 extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *h_c_id
	 */
	protected Long  h_c_id;
	/**
	 *h_c_d_id
	 */
	protected Long  h_c_d_id;
	/**
	 *h_d_id
	 */
	protected Long  h_d_id;
	/**
	 *h_w_id
	 */
	protected Long  h_w_id;
	/**
	 *h_date
	 */
	protected java.util.Date  h_date;
	/**
	 *h_amount
	 */
	protected Long  h_amount;
	/**
	 *h_data
	 */
	protected String  h_data;
	/**
	 *h_c_w_id
	 */
	protected Long  h_c_w_id;
//	public History2()
//	{
//		
//	}
//	public History2(Long h_c_id, Long h_c_d_id, Long h_c_w_id, Long h_d_id, Long h_w_id, Date h_date, 
//			Long h_amount, String h_data){
//		this.h_c_d_id = h_c_d_id;
//		this.h_c_id = h_c_id;
//		this.h_c_w_id = h_c_w_id;
//		this.h_d_id = h_d_id;
//		this.h_w_id = h_w_id;
//		this.h_date = h_date;
//		this.h_amount = h_amount;
//		this.h_data = h_data;
//	}
	
	public Long getId() {
		return id;
	}
	public History2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public History2(Long id, Long hCId, Long hCDId, Long hDId, Long hWId,
			Date hDate, Long hAmount, String hData, Long hCWId) {
		super();
		this.id = id;
		h_c_id = hCId;
		h_c_d_id = hCDId;
		h_d_id = hDId;
		h_w_id = hWId;
		h_date = hDate;
		h_amount = hAmount;
		h_data = hData;
		h_c_w_id = hCWId;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setH_c_id(Long h_c_id) 
	{
		this.h_c_id = h_c_id;
	}
	/**
	 * 返回 h_c_id
	 * @return
	 */
	public Long getH_c_id() 
	{
		return this.h_c_id;
	}
	public void setH_c_d_id(Long h_c_d_id) 
	{
		this.h_c_d_id = h_c_d_id;
	}
	/**
	 * 返回 h_c_d_id
	 * @return
	 */
	public Long getH_c_d_id() 
	{
		return this.h_c_d_id;
	}
	public void setH_d_id(Long h_d_id) 
	{
		this.h_d_id = h_d_id;
	}
	/**
	 * 返回 h_d_id
	 * @return
	 */
	public Long getH_d_id() 
	{
		return this.h_d_id;
	}
	public void setH_w_id(Long h_w_id) 
	{
		this.h_w_id = h_w_id;
	}
	/**
	 * 返回 h_w_id
	 * @return
	 */
	public Long getH_w_id() 
	{
		return this.h_w_id;
	}
	public void setH_date(java.util.Date h_date) 
	{
		this.h_date = h_date;
	}
	/**
	 * 返回 h_date
	 * @return
	 */
	public java.util.Date getH_date() 
	{
		return this.h_date;
	}
	public void setH_amount(Long h_amount) 
	{
		this.h_amount = h_amount;
	}
	/**
	 * 返回 h_amount
	 * @return
	 */
	public Long getH_amount() 
	{
		return this.h_amount;
	}
	public void setH_data(String h_data) 
	{
		this.h_data = h_data;
	}
	/**
	 * 返回 h_data
	 * @return
	 */
	public String getH_data() 
	{
		return this.h_data;
	}
	public void setH_c_w_id(Long h_c_w_id) 
	{
		this.h_c_w_id = h_c_w_id;
	}
	/**
	 * 返回 h_c_w_id
	 * @return
	 */
	public Long getH_c_w_id() 
	{
		return this.h_c_w_id;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof History2)) 
		{
			return false;
		}
		History2 rhs = (History2) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.h_c_id, rhs.h_c_id)
		.append(this.h_c_d_id, rhs.h_c_d_id)
		.append(this.h_d_id, rhs.h_d_id)
		.append(this.h_w_id, rhs.h_w_id)
		.append(this.h_date, rhs.h_date)
		.append(this.h_amount, rhs.h_amount)
		.append(this.h_data, rhs.h_data)
		.append(this.h_c_w_id, rhs.h_c_w_id)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.h_c_id) 
		.append(this.h_c_d_id) 
		.append(this.h_d_id) 
		.append(this.h_w_id) 
		.append(this.h_date) 
		.append(this.h_amount) 
		.append(this.h_data) 
		.append(this.h_c_w_id) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("h_c_id", this.h_c_id) 
		.append("h_c_d_id", this.h_c_d_id) 
		.append("h_d_id", this.h_d_id) 
		.append("h_w_id", this.h_w_id) 
		.append("h_date", this.h_date) 
		.append("h_amount", this.h_amount) 
		.append("h_data", this.h_data) 
		.append("h_c_w_id", this.h_c_w_id) 
		.toString();
	}

}
