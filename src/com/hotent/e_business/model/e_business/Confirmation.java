package com.hotent.e_business.model.e_business;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:confirmation Model对象
 */
public class Confirmation extends BaseModel
{
	//主键
	protected Long id;
	protected String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	protected String  price;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getCustomizations() {
		return customizations;
	}

	public void setCustomizations(String customizations) {
		this.customizations = customizations;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getShippingname() {
		return shippingname;
	}

	public void setShippingname(String shippingname) {
		this.shippingname = shippingname;
	}

	public String getShippingaddress() {
		return shippingaddress;
	}

	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}

	public String getShippingcity() {
		return shippingcity;
	}

	public void setShippingcity(String shippingcity) {
		this.shippingcity = shippingcity;
	}

	public String getShippingstate() {
		return shippingstate;
	}

	public void setShippingstate(String shippingstate) {
		this.shippingstate = shippingstate;
	}

	public String getShippingzip() {
		return shippingzip;
	}

	public void setShippingzip(String shippingzip) {
		this.shippingzip = shippingzip;
	}

	public String getShippingphone() {
		return shippingphone;
	}

	public void setShippingphone(String shippingphone) {
		this.shippingphone = shippingphone;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getBillingname() {
		return billingname;
	}

	public void setBillingname(String billingname) {
		this.billingname = billingname;
	}

	public String getBillingaddress() {
		return billingaddress;
	}

	public void setBillingaddress(String billingaddress) {
		this.billingaddress = billingaddress;
	}

	public String getBillingcity() {
		return billingcity;
	}

	public void setBillingcity(String billingcity) {
		this.billingcity = billingcity;
	}

	public String getBillingstate() {
		return billingstate;
	}

	public void setBillingstate(String billingstate) {
		this.billingstate = billingstate;
	}

	public String getBillingzip() {
		return billingzip;
	}

	public void setBillingzip(String billingzip) {
		this.billingzip = billingzip;
	}

	public String getBillingphone() {
		return billingphone;
	}

	public void setBillingphone(String billingphone) {
		this.billingphone = billingphone;
	}

	public String getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(String creditcard) {
		this.creditcard = creditcard;
	}
	/**
	 *itemname
	 */
	protected String  itemname;
	/**
	 *定制
	 */
	protected String  customizations;
	/**
	 *数量
	 */
	protected String  quantity;
	/**
	 *价格
	 */
	/**
	 *运输姓名
	 */
	protected String  shippingname;
	/**
	 *运输地址
	 */
	protected String  shippingaddress;
	/**
	 *运输城市
	 */
	protected String  shippingcity;
	/**
	 *运输州代码
	 */
	protected String  shippingstate;
	/**
	 *运输邮编
	 */
	protected String  shippingzip;
	/**
	 *运输电话
	 */
	protected String  shippingphone;
	/**
	 *运输类型
	 */
	protected String  shipping;
	/**
	 *账单姓名
	 */
	protected String  billingname;
	/**
	 *账单地址
	 */
	protected String  billingaddress;
	/**
	 *账单城市
	 */
	protected String  billingcity;
	/**
	 *账单州代码
	 */
	protected String  billingstate;
	/**
	 *账单邮编
	 */
	protected String  billingzip;
	/**
	 *账单电话
	 */
	protected String  billingphone;
	/**
	 *信用卡
	 */
	protected String  creditcard;
	
	
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Confirmation)) 
		{
			return false;
		}
		Confirmation rhs = (Confirmation) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.itemname, rhs.itemname)
		.append(this.email, rhs.email)
		.append(this.customizations, rhs.customizations)
		.append(this.quantity, rhs.quantity)
		.append(this.price, rhs.price)
		.append(this.shippingname, rhs.shippingname)
		.append(this.shippingaddress, rhs.shippingaddress)
		.append(this.shippingcity, rhs.shippingcity)
		.append(this.shippingstate, rhs.shippingstate)
		.append(this.shippingzip, rhs.shippingzip)
		.append(this.shippingphone, rhs.shippingphone)
		.append(this.shipping, rhs.shipping)
		.append(this.billingname, rhs.billingname)
		.append(this.billingaddress, rhs.billingaddress)
		.append(this.billingcity, rhs.billingcity)
		.append(this.billingstate, rhs.billingstate)
		.append(this.billingzip, rhs.billingzip)
		.append(this.billingphone, rhs.billingphone)
		.append(this.creditcard, rhs.creditcard)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.itemname) 
		.append(this.customizations) 
		.append(this.quantity) 
		.append(this.price)
		.append(this.email) 
		.append(this.shippingname) 
		.append(this.shippingaddress) 
		.append(this.shippingcity) 
		.append(this.shippingstate) 
		.append(this.shippingzip) 
		.append(this.shippingphone) 
		.append(this.shipping) 
		.append(this.billingname) 
		.append(this.billingaddress) 
		.append(this.billingcity) 
		.append(this.billingstate) 
		.append(this.billingzip) 
		.append(this.billingphone) 
		.append(this.creditcard) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("itemname", this.itemname) 
		.append("Customizations", this.customizations) 
		.append("Quantity", this.quantity) 
		.append("email", this.email) 
		.append("price", this.price)
		.append("shippingname", this.shippingname) 
		.append("shippingaddress", this.shippingaddress) 
		.append("shippingcity", this.shippingcity) 
		.append("shippingstate", this.shippingstate) 
		.append("shippingzip", this.shippingzip) 
		.append("shippingphone", this.shippingphone) 
		.append("shipping", this.shipping) 
		.append("billingname", this.billingname) 
		.append("billingaddress", this.billingaddress) 
		.append("billingcity", this.billingcity) 
		.append("billingstate", this.billingstate) 
		.append("billingzip", this.billingzip) 
		.append("billingphone", this.billingphone) 
		.append("creditcard", this.creditcard) 
		.toString();
	}

}
