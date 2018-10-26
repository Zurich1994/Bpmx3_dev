package com.hotent.banking.model.banking;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:账户信息表 Model对象
 */
public class Account extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户名
	 */
	protected String  userid;
	/**
	 *账号
	 */
	protected String  accountno;
	/**
	 *类型
	 */
	protected Long  type;
	/**
	 *金额
	 */
	protected Long  balance;
	/**
	 *总存款数
	 */
	protected Long  totaldeposit;
	/**
	 *平均存款数
	 */
	protected Long  avgdeposit;
	/**
	 *总取款数
	 */
	protected Long  totalwithdrawl;
	/**
	 *平均取款数
	 */
	protected Long  avgwithdrawl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUSERID(String USERID) 
	{
		this.userid = USERID;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUSERID() 
	{
		return this.userid;
	}
	public void setACCOUNTNO(String ACCOUNTNO) 
	{
		this.accountno = ACCOUNTNO;
	}
	/**
	 * 返回 账号
	 * @return
	 */
	public String getACCOUNTNO() 
	{
		return this.accountno;
	}
	public void setTYPE(Long TYPE) 
	{
		this.type = TYPE;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public Long getTYPE() 
	{
		return this.type;
	}
	public void setBALANCE(Long BALANCE) 
	{
		this.balance = BALANCE;
	}
	/**
	 * 返回 金额
	 * @return
	 */
	public Long getBALANCE() 
	{
		return this.balance;
	}
	public void setTOTALDEPOSIT(Long TOTALDEPOSIT) 
	{
		this.totaldeposit = TOTALDEPOSIT;
	}
	/**
	 * 返回 总存款数
	 * @return
	 */
	public Long getTOTALDEPOSIT() 
	{
		return this.totaldeposit;
	}
	public void setAVGDEPOSIT(Long AVGDEPOSIT) 
	{
		this.avgdeposit = AVGDEPOSIT;
	}
	/**
	 * 返回 平均存款数
	 * @return
	 */
	public Long getAVGDEPOSIT() 
	{
		return this.avgdeposit;
	}
	public void setTOTALWITHDRAWL(Long TOTALWITHDRAWL) 
	{
		this.totalwithdrawl = TOTALWITHDRAWL;
	}
	/**
	 * 返回 总取款数
	 * @return
	 */
	public Long getTOTALWITHDRAWL() 
	{
		return this.totalwithdrawl;
	}
	public void setAVGWITHDRAWAL(Long AVGWITHDRAWAL) 
	{
		this.avgwithdrawl = AVGWITHDRAWAL;
	}
	/**
	 * 返回 平均取款数
	 * @return
	 */
	public Long getAVGWITHDRAWAL() 
	{
		return this.avgwithdrawl;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Account)) 
		{
			return false;
		}
		Account rhs = (Account) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userid, rhs.userid)
		.append(this.accountno, rhs.accountno)
		.append(this.type, rhs.type)
		.append(this.balance, rhs.balance)
		.append(this.totaldeposit, rhs.totaldeposit)
		.append(this.avgdeposit, rhs.avgdeposit)
		.append(this.totalwithdrawl, totalwithdrawl)
		.append(this.avgwithdrawl, avgwithdrawl)
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
		.append(this.type) 
		.append(this.balance) 
		.append(this.totaldeposit) 
		.append(this.avgdeposit) 
		.append(this.totalwithdrawl) 
		.append(this.avgwithdrawl) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("USERID", this.userid) 
		.append("ACCOUNTNO", this.accountno) 
		.append("TYPE", this.type) 
		.append("BALANCE", this.balance) 
		.append("TOTALDEPOSIT", this.totaldeposit) 
		.append("AVGDEPOSIT", this.avgdeposit) 
		.append("TOTALWITHDRAWL", this.totalwithdrawl) 
		.append("AVGWITHDRAWAL", this.avgwithdrawl) 
		.toString();
	}

}
