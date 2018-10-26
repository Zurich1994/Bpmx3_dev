package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;


/**
 * 对象功能:PageTest Model对象
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-02 22:29:22
 */
public class PageTest extends BaseModel{
	// id
	protected Long id;
	// category
	protected String category;
	// name
	protected String name;
	// amount
	protected Long amount;

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 id
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setCategory(String category){
		this.category = category;
	}
	/**
	 * 返回 category
	 * @return
	 */
	public String getCategory() {
		return this.category;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setAmount(Long amount){
		this.amount = amount;
	}
	/**
	 * 返回 amount
	 * @return
	 */
	public Long getAmount() {
		return this.amount;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof PageTest)) 
		{
			return false;
		}
		PageTest rhs = (PageTest) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.category, rhs.category)
		.append(this.name, rhs.name)
		.append(this.amount, rhs.amount)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.category) 
		.append(this.name) 
		.append(this.amount) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("category", this.category) 
		.append("name", this.name) 
		.append("amount", this.amount) 
		.toString();
	}
   
  

}