package com.hotent.banking.model.banking;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:银行订单表 Model对象
 */
public class OrderCheck extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户id
	 */
	protected String  userid;
	/**
	 *账号
	 */
	protected String  accountno;
	/**
	 *总价格
	 */
	protected Long  totalprice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUserid(String userid) 
	{
		this.userid = userid;
	}
	/**
	 * 返回 用户id
	 * @return
	 */
	public String getUserid() 
	{
		return this.userid;
	}
	public void setAccountno(String accountno) 
	{
		this.accountno = accountno;
	}
	/**
	 * 返回 账号
	 * @return
	 */
	public String getAccountno() 
	{
		return this.accountno;
	}
	public void setTotalprice(Long totalprice) 
	{
		this.totalprice = totalprice;
	}
	/**
	 * 返回 总价格
	 * @return
	 */
	public Long getTotalprice() 
	{
		return this.totalprice;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof OrderCheck)) 
		{
			return false;
		}
		OrderCheck rhs = (OrderCheck) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userid, rhs.userid)
		.append(this.accountno, rhs.accountno)
		.append(this.totalprice, rhs.totalprice)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.userid) 
		.append(this.accountno) 
		.append(this.totalprice) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("userid", this.userid) 
		.append("accountno", this.accountno) 
		.append("totalprice", this.totalprice) 
		.toString();
	}

}
