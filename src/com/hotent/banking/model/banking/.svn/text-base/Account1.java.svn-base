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
public class Account1 extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *用户名
	 */
	protected String  USERID;
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	/**
	 *账号
	 */
	protected String  ACCOUNTNO;
	/**
	 *类型：1-Checking、2-Saving、其它-Other
	 */
	protected String   TYPE;
	/**
	 *金额
	 */
	protected double  BALANCE;
	/**
	 *总存款数
	 */
	protected double  TOTALDEPOSIT;
	/**
	 *平均存款数
	 */
	protected double AVGDEPOSIT;
	/**
	 *总取款数
	 */
	protected double  TOTALWITHDRAWL;
	/**
	 *平均取款数
	 */
	protected double  AVGWITHDRAWAL;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	 * 返回 类型：1-Checking、2-Saving、其它-Other
	 * @return
	 */
	public String getTYPE() 
	{
		
		return this.TYPE;
	}
	public void setBALANCE(double BALANCE) 
	{
		this.BALANCE = BALANCE;
	}
	/**
	 * 返回 金额
	 * @return
	 */
	public double getBALANCE() 
	{
		return this.BALANCE;
	}
	public void setTOTALDEPOSIT(double TOTALDEPOSIT) 
	{
		this.TOTALDEPOSIT = TOTALDEPOSIT;
	}
	/**
	 * 返回 总存款数
	 * @return
	 */
	public double getTOTALDEPOSIT() 
	{
		return this.TOTALDEPOSIT;
	}
	public void setAVGDEPOSIT(double AVGDEPOSIT) 
	{
		this.AVGDEPOSIT = AVGDEPOSIT;
	}
	/**
	 * 返回 平均存款数
	 * @return
	 */
	public double getAVGDEPOSIT() 
	{
		return this.AVGDEPOSIT;
	}
	public void setTOTALWITHDRAWL(double TOTALWITHDRAWL) 
	{
		this.TOTALWITHDRAWL = TOTALWITHDRAWL;
	}
	/**
	 * 返回 总取款数
	 * @return
	 */
	public double getTOTALWITHDRAWL() 
	{
		return this.TOTALWITHDRAWL;
	}
	public void setAVGWITHDRAWAL(double AVGWITHDRAWAL) 
	{
		this.AVGWITHDRAWAL = AVGWITHDRAWAL;
	}
	/**
	 * 返回 平均取款数
	 * @return
	 */
	public double getAVGWITHDRAWAL() 
	{
		return this.AVGWITHDRAWAL;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Account1)) 
		{
			return false;
		}
		Account1 rhs = (Account1) object;
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
