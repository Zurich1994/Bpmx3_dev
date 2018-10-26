package com.hotent.Stukq.model.Stukq;

import java.util.ArrayList;
import java.util.List;

import com.hotent.Stukq.model.Stuzh.Xskqzhb;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:学生考勤表 Model对象
 */
public class Xskqb extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *日期
	 */
	protected java.util.Date  rq;
	/**
	 *考勤表一
	 */
	protected String  kqby;
	/**
	 *考勤表二
	 */
	protected String  kqbe;
	/**
	 *备注
	 */
	protected String  bz;
	
	/**
	 *列表
	 */
	protected List<Xskqzhb> xskqzhbList=new ArrayList<Xskqzhb>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setRq(java.util.Date rq) 
	{
		this.rq = rq;
	}
	/**
	 * 返回 日期
	 * @return
	 */
	public java.util.Date getRq() 
	{
		return this.rq;
	}
	public void setKqby(String kqby) 
	{
		this.kqby = kqby;
	}
	/**
	 * 返回 考勤表一
	 * @return
	 */
	public String getKqby() 
	{
		return this.kqby;
	}
	public void setKqbe(String kqbe) 
	{
		this.kqbe = kqbe;
	}
	/**
	 * 返回 考勤表二
	 * @return
	 */
	public String getKqbe() 
	{
		return this.kqbe;
	}
	public void setBz(String bz) 
	{
		this.bz = bz;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getBz() 
	{
		return this.bz;
	}
	public void setXskqzhbList(List<Xskqzhb> xskqzhbList) 
	{
		this.xskqzhbList = xskqzhbList;
	}
	/**
	 * 返回 列表
	 * @return
	 */
	public List<Xskqzhb> getXskqzhbList() 
	{
		return this.xskqzhbList;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Xskqb)) 
		{
			return false;
		}
		Xskqb rhs = (Xskqb) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.rq, rhs.rq)
		.append(this.kqby, rhs.kqby)
		.append(this.kqbe, rhs.kqbe)
		.append(this.bz, rhs.bz)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.rq) 
		.append(this.kqby) 
		.append(this.kqbe) 
		.append(this.bz) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("rq", this.rq) 
		.append("kqby", this.kqby) 
		.append("kqbe", this.kqbe) 
		.append("bz", this.bz) 
		.toString();
	}

}
