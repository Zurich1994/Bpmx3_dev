package com.hotent.platform.model.system;

import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:报表参数 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-04-12 11:08:12
 */
public class ReportParam extends BaseModel
{
	// PARAMID
	protected Long PARAMID;
	// 所属报表
	protected Long REPORTID;
	// 参数名称
	protected String PARAMNAME;
	// 参数Key
	protected String PARAMKEY;
	// 缺省值
	protected String DEFAULTVAL;
	// 类型
	protected String PARAMTYPE;
	// 系列号
	protected Long SN;
	// PARAMTYPESTR
	protected String PARAMTYPESTR;

	public void setPARAMID(Long PARAMID) 
	{
		this.PARAMID = PARAMID;
	}
	/**
	 * 返回 PARAMID
	 * @return
	 */
	public Long getPARAMID() 
	{
		return PARAMID;
	}

	public void setREPORTID(Long REPORTID) 
	{
		this.REPORTID = REPORTID;
	}
	/**
	 * 返回 所属报表
	 * @return
	 */
	public Long getREPORTID() 
	{
		return REPORTID;
	}

	public void setPARAMNAME(String PARAMNAME) 
	{
		this.PARAMNAME = PARAMNAME;
	}
	/**
	 * 返回 参数名称
	 * @return
	 */
	public String getPARAMNAME() 
	{
		return PARAMNAME;
	}

	public void setPARAMKEY(String PARAMKEY) 
	{
		this.PARAMKEY = PARAMKEY;
	}
	/**
	 * 返回 参数Key
	 * @return
	 */
	public String getPARAMKEY() 
	{
		return PARAMKEY;
	}

	public void setDEFAULTVAL(String DEFAULTVAL) 
	{
		this.DEFAULTVAL = DEFAULTVAL;
	}
	/**
	 * 返回 缺省值
	 * @return
	 */
	public String getDEFAULTVAL() 
	{
		return DEFAULTVAL;
	}

	public void setPARAMTYPE(String PARAMTYPE) 
	{
		this.PARAMTYPE = PARAMTYPE;
	}
	/**
	 * 返回 类型
	 * @return
	 */
	public String getPARAMTYPE() 
	{
		return PARAMTYPE;
	}

	public void setSN(Long SN) 
	{
		this.SN = SN;
	}
	/**
	 * 返回 系列号
	 * @return
	 */
	public Long getSN() 
	{
		return SN;
	}

	public void setPARAMTYPESTR(String PARAMTYPESTR) 
	{
		this.PARAMTYPESTR = PARAMTYPESTR;
	}
	/**
	 * 返回 PARAMTYPESTR
	 * @return
	 */
	public String getPARAMTYPESTR() 
	{
		return PARAMTYPESTR;
	}

   
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ReportParam)) 
		{
			return false;
		}
		ReportParam rhs = (ReportParam) object;
		return new EqualsBuilder()
		.append(this.PARAMID, rhs.PARAMID)
		.append(this.REPORTID, rhs.REPORTID)
		.append(this.PARAMNAME, rhs.PARAMNAME)
		.append(this.PARAMKEY, rhs.PARAMKEY)
		.append(this.DEFAULTVAL, rhs.DEFAULTVAL)
		.append(this.PARAMTYPE, rhs.PARAMTYPE)
		.append(this.SN, rhs.SN)
		.append(this.PARAMTYPESTR, rhs.PARAMTYPESTR)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.PARAMID) 
		.append(this.REPORTID) 
		.append(this.PARAMNAME) 
		.append(this.PARAMKEY) 
		.append(this.DEFAULTVAL) 
		.append(this.PARAMTYPE) 
		.append(this.SN) 
		.append(this.PARAMTYPESTR) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("PARAMID", this.PARAMID) 
		.append("REPORTID", this.REPORTID) 
		.append("PARAMNAME", this.PARAMNAME) 
		.append("PARAMKEY", this.PARAMKEY) 
		.append("DEFAULTVAL", this.DEFAULTVAL) 
		.append("PARAMTYPE", this.PARAMTYPE) 
		.append("SN", this.SN) 
		.append("PARAMTYPESTR", this.PARAMTYPESTR) 
		.toString();
	}
   
  

}