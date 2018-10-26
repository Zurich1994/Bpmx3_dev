package com.hotent.SoftWare.model.SoftWarePac;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:软件表 Model对象
 */
public class Software extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *软件编号
	 */
	protected String  softWare_ID;
	/**
	 *软件名称
	 */
	protected String  software_Name;
	/**
	 *软件类型
	 */
	protected String  software_Type;
	/**
	 *开发商
	 */
	protected String  developer;
	/**
	 *发行阶段
	 */
	protected String  stage;
	/**
	 *版本
	 */
	protected String  version;
	/**
	 *备注
	 */
	protected String  remark;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSoftWare_ID(String softWare_ID) 
	{
		this.softWare_ID = softWare_ID;
	}
	/**
	 * 返回 软件编号
	 * @return
	 */
	public String getSoftWare_ID() 
	{
		return this.softWare_ID;
	}
	public void setSoftware_Name(String software_Name) 
	{
		this.software_Name = software_Name;
	}
	/**
	 * 返回 软件名称
	 * @return
	 */
	public String getSoftware_Name() 
	{
		return this.software_Name;
	}
	public void setSoftware_Type(String software_Type) 
	{
		this.software_Type = software_Type;
	}
	/**
	 * 返回 软件类型
	 * @return
	 */
	public String getSoftware_Type() 
	{
		return this.software_Type;
	}
	public void setDeveloper(String developer) 
	{
		this.developer = developer;
	}
	/**
	 * 返回 开发商
	 * @return
	 */
	public String getDeveloper() 
	{
		return this.developer;
	}
	public void setStage(String stage) 
	{
		this.stage = stage;
	}
	/**
	 * 返回 发行阶段
	 * @return
	 */
	public String getStage() 
	{
		return this.stage;
	}
	public void setVersion(String version) 
	{
		this.version = version;
	}
	/**
	 * 返回 版本
	 * @return
	 */
	public String getVersion() 
	{
		return this.version;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemark() 
	{
		return this.remark;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Software)) 
		{
			return false;
		}
		Software rhs = (Software) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.id, rhs.id)
		.append(this.softWare_ID, rhs.softWare_ID)
		.append(this.software_Name, rhs.software_Name)
		.append(this.software_Type, rhs.software_Type)
		.append(this.developer, rhs.developer)
		.append(this.stage, rhs.stage)
		.append(this.version, rhs.version)
		.append(this.remark, rhs.remark)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.id) 
		.append(this.softWare_ID) 
		.append(this.software_Name) 
		.append(this.software_Type) 
		.append(this.developer) 
		.append(this.stage) 
		.append(this.version) 
		.append(this.remark) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("id", this.id) 
		.append("softWare_ID", this.softWare_ID) 
		.append("software_Name", this.software_Name) 
		.append("software_Type", this.software_Type) 
		.append("developer", this.developer) 
		.append("stage", this.stage) 
		.append("version", this.version) 
		.append("remark", this.remark) 
		.toString();
	}

}
