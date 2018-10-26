package com.hotent.tpcc.model.tpcc;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:orders Model对象
 */
public class Orders extends BaseModel
{
	//主键
	protected Long o_id;
	/**
	 *o_d_id
	 */
	protected Long  o_d_id;
	/**
	 *o_w_id
	 */
	protected Long  o_w_id;
	/**
	 *o_c_id
	 */
	protected Long  o_c_id;
	/**
	 *o_entry_d
	 */
	protected java.util.Date  o_entry_d;
	/**
	 *o_carrier_id
	 */
	protected Long  o_carrier_id;
	/**
	 *o_ol_cnt
	 */
	protected Long  o_ol_cnt;
	/**
	 *o_all_local
	 */
	protected Long  o_all_local;
	
	public Long getOl_i_id() {
		return ol_i_id;
	}
	public void setOl_i_id(Long olIId) {
		ol_i_id = olIId;
	}
	public Long getOl_supply_w_id() {
		return ol_supply_w_id;
	}
	public void setOl_supply_w_id(Long olSupplyWId) {
		ol_supply_w_id = olSupplyWId;
	}
	public Long getOl_quantity() {
		return ol_quantity;
	}
	public void setOl_quantity(Long olQuantity) {
		ol_quantity = olQuantity;
	}
	public Long getOl_amount() {
		return ol_amount;
	}
	public void setOl_amount(Long olAmount) {
		ol_amount = olAmount;
	}
	public java.util.Date getOl_delivery_d() {
		return ol_delivery_d;
	}
	public void setOl_delivery_d(java.util.Date olDeliveryD) {
		ol_delivery_d = olDeliveryD;
	}

	protected Long  ol_i_id;
	
	protected Long ol_supply_w_id;
	
	protected Long ol_quantity;
		
	protected Long ol_amount;
			
	protected java.util.Date ol_delivery_d;
	protected Long no_o_id;
	
	public Long getNo_o_id() {
		return no_o_id;
	}
	public void setNo_o_id(Long noOId) {
		no_o_id = noOId;
	}
	public Long getO_id() {
		return o_id;
	}
	public void setO_id(Long o_id) {
		this.o_id = o_id;
	}
	
	public void setO_d_id(Long o_d_id) 
	{
		this.o_d_id = o_d_id;
	}
	/**
	 * 返回 o_d_id
	 * @return
	 */
	public Long getO_d_id() 
	{
		return this.o_d_id;
	}
	public void setO_w_id(Long o_w_id) 
	{
		this.o_w_id = o_w_id;
	}
	/**
	 * 返回 o_w_id
	 * @return
	 */
	public Long getO_w_id() 
	{
		return this.o_w_id;
	}
	public void setO_c_id(Long o_c_id) 
	{
		this.o_c_id = o_c_id;
	}
	/**
	 * 返回 o_c_id
	 * @return
	 */
	public Long getO_c_id() 
	{
		return this.o_c_id;
	}
	public void setO_entry_d(java.util.Date o_entry_d) 
	{
		this.o_entry_d = o_entry_d;
	}
	/**
	 * 返回 o_entry_d
	 * @return
	 */
	public java.util.Date getO_entry_d() 
	{
		return this.o_entry_d;
	}
	public void setO_carrier_id(Long o_carrier_id) 
	{
		this.o_carrier_id = o_carrier_id;
	}
	/**
	 * 返回 o_carrier_id
	 * @return
	 */
	public Long getO_carrier_id() 
	{
		return this.o_carrier_id;
	}
	public void setO_ol_cnt(Long o_ol_cnt) 
	{
		this.o_ol_cnt = o_ol_cnt;
	}
	/**
	 * 返回 o_ol_cnt
	 * @return
	 */
	public Long getO_ol_cnt() 
	{
		return this.o_ol_cnt;
	}
	public void setO_all_local(Long o_all_local) 
	{
		this.o_all_local = o_all_local;
	}
	/**
	 * 返回 o_all_local
	 * @return
	 */
	public Long getO_all_local() 
	{
		return this.o_all_local;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Orders)) 
		{
			return false;
		}
		Orders rhs = (Orders) object;
		return new EqualsBuilder()
		.append(this.o_id,rhs.o_id)
		.append(this.o_d_id, rhs.o_d_id)
		.append(this.o_w_id, rhs.o_w_id)
		.append(this.o_c_id, rhs.o_c_id)
		.append(this.o_entry_d, rhs.o_entry_d)
		.append(this.o_carrier_id, rhs.o_carrier_id)
		.append(this.o_ol_cnt, rhs.o_ol_cnt)
		.append(this.o_all_local, rhs.o_all_local)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.o_id)
		.append(this.o_d_id) 
		.append(this.o_w_id) 
		.append(this.o_c_id) 
		.append(this.o_entry_d) 
		.append(this.o_carrier_id) 
		.append(this.o_ol_cnt) 
		.append(this.o_all_local) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("o_id",this.o_id)
		.append("o_d_id", this.o_d_id) 
		.append("o_w_id", this.o_w_id) 
		.append("o_c_id", this.o_c_id) 
		.append("o_entry_d", this.o_entry_d) 
		.append("o_carrier_id", this.o_carrier_id) 
		.append("o_ol_cnt", this.o_ol_cnt) 
		.append("o_all_local", this.o_all_local) 
		.toString();
	}

}
