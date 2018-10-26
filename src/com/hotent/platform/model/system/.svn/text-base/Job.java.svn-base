package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:职务表 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-28 16:17:48
 */
public class Job extends BaseModel
{
	public static final String NODE_KEY = "zwjb";	// 数据字典职务字段分类
	// 职务ID
	protected Long  jobid;
	// 职务名称
	protected String  jobname;
	// 职务代码
	protected String  jobcode;
	// 职务描述
	protected String  jobdesc;
	// 设置码
	protected Long  setid=0L;
	// 是否删除
	protected Long  isdelete=0L;
	// 等级
	protected Short  grade=0;
	
	public void setJobid(Long jobid) 
	{
		this.jobid = jobid;
	}
	/**
	 * 返回 职务ID
	 * @return
	 */
	public Long getJobid() 
	{
		return this.jobid;
	}
	public void setJobname(String jobname) 
	{
		this.jobname = jobname;
	}
	/**
	 * 返回 职务名称
	 * @return
	 */
	public String getJobname() 
	{
		return this.jobname;
	}
	public void setJobcode(String jobcode) 
	{
		this.jobcode = jobcode;
	}
	/**
	 * 返回 职务代码
	 * @return
	 */
	public String getJobcode() 
	{
		return this.jobcode;
	}
	public void setJobdesc(String jobdesc) 
	{
		this.jobdesc = jobdesc;
	}
	/**
	 * 返回 职务描述
	 * @return
	 */
	public String getJobdesc() 
	{
		return this.jobdesc;
	}
	public void setSetid(Long setid) 
	{
		this.setid = setid;
	}
	/**
	 * 返回 设置码
	 * @return
	 */
	public Long getSetid() 
	{
		return this.setid;
	}
	public void setIsdelete(Long isdelete) 
	{
		this.isdelete = isdelete;
	}
	/**
	 * 返回 是否删除
	 * @return
	 */
	public Long getIsdelete() 
	{
		return this.isdelete;
	}

   	public Short getGrade() {
		return grade;
	}
	public void setGrade(Short grade) {
		this.grade = grade;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Job)) 
		{
			return false;
		}
		Job rhs = (Job) object;
		return new EqualsBuilder()
		.append(this.jobid, rhs.jobid)
		.append(this.jobname, rhs.jobname)
		.append(this.jobcode, rhs.jobcode)
		.append(this.jobdesc, rhs.jobdesc)
		.append(this.setid, rhs.setid)
		.append(this.isdelete, rhs.isdelete)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.jobid) 
		.append(this.jobname) 
		.append(this.jobcode) 
		.append(this.jobdesc) 
		.append(this.setid) 
		.append(this.isdelete) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("jobid", this.jobid) 
		.append("jobname", this.jobname) 
		.append("jobcode", this.jobcode) 
		.append("jobdesc", this.jobdesc) 
		.append("setid", this.setid) 
		.append("isdelete", this.isdelete) 
		.toString();
	}
   
  

}