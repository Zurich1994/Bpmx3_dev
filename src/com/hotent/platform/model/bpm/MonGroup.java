package com.hotent.platform.model.bpm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
/**
 * 对象功能:监控分组 Model对象
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-06-08 11:14:50
 */
public class MonGroup extends BaseModel
{
	// 主键
	protected Long  id;
	// 分组名称
	protected String  name;
	// 1.  标题2.  显示流程实例信息 3.  可以干预
	protected Long  grade;
	// 是否禁用
	protected Long  enabled=1L;
	// 创建人ID
	protected Long  creatorid;
	// 创建人
	protected String  creator;
	// 创建时间
	protected java.util.Date  createtime;
	//监控流程项目列表
	protected List<MonGroupItem> monGroupItemList=new ArrayList<MonGroupItem>();
	public void setId(Long id) 
	{
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() 
	{
		return this.id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	/**
	 * 返回 分组名称
	 * @return
	 */
	public String getName() 
	{
		return this.name;
	}
	public void setGrade(Long grade) 
	{
		this.grade = grade;
	}
	/**
	 * 返回 1.  标题2.  显示流程实例信息3.  可以干预
	 * @return
	 */
	public Long getGrade() 
	{
		return this.grade;
	}
	public void setEnabled(Long enabled) 
	{
		this.enabled = enabled;
	}
	/**
	 * 返回 是否禁用
	 * @return
	 */
	public Long getEnabled() 
	{
		return this.enabled;
	}
	public void setCreatorid(Long creatorid) 
	{
		this.creatorid = creatorid;
	}
	/**
	 * 返回 创建人ID
	 * @return
	 */
	public Long getCreatorid() 
	{
		return this.creatorid;
	}
	public void setCreator(String creator) 
	{
		this.creator = creator;
	}
	/**
	 * 返回 创建人
	 * @return
	 */
	public String getCreator() 
	{
		return this.creator;
	}
	public void setCreatetime(java.util.Date createtime) 
	{
		this.createtime = createtime;
	}
	/**
	 * 返回 创建时间
	 * @return
	 */
	public java.util.Date getCreatetime() 
	{
		return this.createtime;
	}
	public void setMonGroupItemList(List<MonGroupItem> monGroupItemList) 
	{
		this.monGroupItemList = monGroupItemList;
	}
	/**
	 * 返回 监控流程项目列表
	 * @return
	 */
	public List<MonGroupItem> getMonGroupItemList() 
	{
		return this.monGroupItemList;
	}


   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof MonGroup)) 
		{
			return false;
		}
		MonGroup rhs = (MonGroup) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.grade, rhs.grade)
		.append(this.enabled, rhs.enabled)
		.append(this.creatorid, rhs.creatorid)
		.append(this.creator, rhs.creator)
		.append(this.createtime, rhs.createtime)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.name) 
		.append(this.grade) 
		.append(this.enabled) 
		.append(this.creatorid) 
		.append(this.creator) 
		.append(this.createtime) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("name", this.name) 
		.append("grade", this.grade) 
		.append("enabled", this.enabled) 
		.append("creatorid", this.creatorid) 
		.append("creator", this.creator) 
		.append("createtime", this.createtime) 
		.toString();
	}
   
  

}