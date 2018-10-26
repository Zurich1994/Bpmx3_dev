package com.hotent.projectinfo.model.projectinfo;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:projectInfo Model对象
 */
public class projectinfo extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *项目名称
	 */
	protected String  projectName;
	/**
	 *description
	 */
	protected String  description;
	/**
	 *开始时间
	 */
	protected java.util.Date  stime;
	/**
	 *结束时间
	 */
	protected java.util.Date  etime;
	/**
	 *实际开始时间
	 */
	protected java.util.Date  astime;
	/**
	 *实际结束时间
	 */
	protected java.util.Date  aetime;
	/**
	 *删除开关
	 */
	protected String  deleteFlag;
	/**
	 *项目报文
	 */
	protected String  projectXml;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setProjectName(String projectName) 
	{
		this.projectName = projectName;
	}
	/**
	 * 返回 项目名称
	 * @return
	 */
	public String getProjectName() 
	{
		return this.projectName;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	/**
	 * 返回 description
	 * @return
	 */
	public String getDescription() 
	{
		return this.description;
	}
	public void setStime(java.util.Date stime) 
	{
		this.stime = stime;
	}
	/**
	 * 返回 开始时间
	 * @return
	 */
	public java.util.Date getStime() 
	{
		return this.stime;
	}
	public void setEtime(java.util.Date etime) 
	{
		this.etime = etime;
	}
	/**
	 * 返回 结束时间
	 * @return
	 */
	public java.util.Date getEtime() 
	{
		return this.etime;
	}
	public void setAstime(java.util.Date astime) 
	{
		this.astime = astime;
	}
	/**
	 * 返回 实际开始时间
	 * @return
	 */
	public java.util.Date getAstime() 
	{
		return this.astime;
	}
	public void setAetime(java.util.Date aetime) 
	{
		this.aetime = aetime;
	}
	/**
	 * 返回 实际结束时间
	 * @return
	 */
	public java.util.Date getAetime() 
	{
		return this.aetime;
	}
	public void setDeleteFlag(String deleteFlag) 
	{
		this.deleteFlag = deleteFlag;
	}
	/**
	 * 返回 删除开关
	 * @return
	 */
	public String getDeleteFlag() 
	{
		return this.deleteFlag;
	}
	public void setProjectXml(String projectXml) 
	{
		this.projectXml = projectXml;
	}
	/**
	 * 返回 项目报文
	 * @return
	 */
	public String getProjectXml() 
	{
		return this.projectXml;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof projectinfo)) 
		{
			return false;
		}
		projectinfo rhs = (projectinfo) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.projectName, rhs.projectName)
		.append(this.description, rhs.description)
		.append(this.stime, rhs.stime)
		.append(this.etime, rhs.etime)
		.append(this.astime, rhs.astime)
		.append(this.aetime, rhs.aetime)
		.append(this.deleteFlag, rhs.deleteFlag)
		.append(this.projectXml, rhs.projectXml)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.projectName) 
		.append(this.description) 
		.append(this.stime) 
		.append(this.etime) 
		.append(this.astime) 
		.append(this.aetime) 
		.append(this.deleteFlag) 
		.append(this.projectXml) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("projectName", this.projectName) 
		.append("description", this.description) 
		.append("stime", this.stime) 
		.append("etime", this.etime) 
		.append("astime", this.astime) 
		.append("aetime", this.aetime) 
		.append("deleteFlag", this.deleteFlag) 
		.append("projectXml", this.projectXml) 
		.toString();
	}

}
