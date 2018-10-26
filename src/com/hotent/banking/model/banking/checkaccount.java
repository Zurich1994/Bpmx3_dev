package com.hotent.banking.model.banking;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:账户概要 Model对象
 */
public class checkaccount extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户名
	 */
	protected String  USERID;
	/**
	 *账号
	 */
	protected String  ACCOUNTNO;
	/**
	 *类型
	 */
	protected String  TYPE;
	/**
	 *金额
	 */
	protected Long  BALANCE;
	/**
	 *总存款数
	 */
	protected Long  TOTALDEPOSIT;
	/**
	 *平均存款数
	 */
	protected Long  AVGDEPOSIT;
	/**
	 *总取款数
	 */
	protected Long  TOTALWITHDRAWL;
	/**
	 *平均取款数
	 */
	protected Long  AVGWITHDRAWAL;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUSERID(String USERID) 
	{
		this.USERID = USERID;
	}
	/**
	 * 返回 用户名
	 * @return
	 */
	public String getUSERID() 
	{
		return this.USERID;
	}
	public void setACCOUNTNO(String ACCOUNTNO) 
	{
		this.ACCOUNTNO = ACCOUNTNO;
	}
	/**
	 * 返回 账号
	 * @return
	 */
	public String getACCOUNTNO() 
	{
		return this.ACCOUNTNO;
	}
	public void setTYPE(String TYPE) 
	{
		this.TYPE = TYPE;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public String getTYPE() 
	{
		return this.TYPE;
	}
	public void setBALANCE(Long BALANCE) 
	{
		this.BALANCE = BALANCE;
	}
	/**
	 * 返回 金额
	 * @return
	 */
	public Long getBALANCE() 
	{
		return this.BALANCE;
	}
	public void setTOTALDEPOSIT(Long TOTALDEPOSIT) 
	{
		this.TOTALDEPOSIT = TOTALDEPOSIT;
	}
	/**
	 * 返回 总存款数
	 * @return
	 */
	public Long getTOTALDEPOSIT() 
	{
		return this.TOTALDEPOSIT;
	}
	public void setAVGDEPOSIT(Long AVGDEPOSIT) 
	{
		this.AVGDEPOSIT = AVGDEPOSIT;
	}
	/**
	 * 返回 平均存款数
	 * @return
	 */
	public Long getAVGDEPOSIT() 
	{
		return this.AVGDEPOSIT;
	}
	public void setTOTALWITHDRAWL(Long TOTALWITHDRAWL) 
	{
		this.TOTALWITHDRAWL = TOTALWITHDRAWL;
	}
	/**
	 * 返回 总取款数
	 * @return
	 */
	public Long getTOTALWITHDRAWL() 
	{
		return this.TOTALWITHDRAWL;
	}
	public void setAVGWITHDRAWAL(Long AVGWITHDRAWAL) 
	{
		this.AVGWITHDRAWAL = AVGWITHDRAWAL;
	}
	/**
	 * 返回 平均取款数
	 * @return
	 */
	public Long getAVGWITHDRAWAL() 
	{
		return this.AVGWITHDRAWAL;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof checkaccount)) 
		{
			return false;
		}
		checkaccount rhs = (checkaccount) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.USERID, rhs.USERID)
		.append(this.ACCOUNTNO, rhs.ACCOUNTNO)
		.append(this.TYPE, rhs.TYPE)
		.append(this.BALANCE, rhs.BALANCE)
		.append(this.TOTALDEPOSIT, rhs.TOTALDEPOSIT)
		.append(this.AVGDEPOSIT, rhs.AVGDEPOSIT)
		.append(this.TOTALWITHDRAWL, rhs.TOTALWITHDRAWL)
		.append(this.AVGWITHDRAWAL, rhs.AVGWITHDRAWAL)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.USERID) 
		.append(this.ACCOUNTNO) 
		.append(this.TYPE) 
		.append(this.BALANCE) 
		.append(this.TOTALDEPOSIT) 
		.append(this.AVGDEPOSIT) 
		.append(this.TOTALWITHDRAWL) 
		.append(this.AVGWITHDRAWAL) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("USERID", this.USERID) 
		.append("ACCOUNTNO", this.ACCOUNTNO) 
		.append("TYPE", this.TYPE) 
		.append("BALANCE", this.BALANCE) 
		.append("TOTALDEPOSIT", this.TOTALDEPOSIT) 
		.append("AVGDEPOSIT", this.AVGDEPOSIT) 
		.append("TOTALWITHDRAWL", this.TOTALWITHDRAWL) 
		.append("AVGWITHDRAWAL", this.AVGWITHDRAWAL) 
		.toString();
	}
	

}
