package com.hotent.formParcel.model.formParcel;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:表单数据包对应关系表 Model对象
 */
public class FormParcel extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *表单名
	 */
	protected String  form_name;
	/**
	 *数据包ID
	 */
	protected Long  parcelID;
	/**
	 *数据包名
	 */
	protected String  parcel_name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setForm_name(String form_name) 
	{
		this.form_name = form_name;
	}
	/**
	 * 返回 表单名
	 * @return
	 */
	public String getForm_name() 
	{
		return this.form_name;
	}
	public void setParcelID(Long parcelID) 
	{
		this.parcelID = parcelID;
	}
	/**
	 * 返回 数据包ID
	 * @return
	 */
	public Long getParcelID() 
	{
		return this.parcelID;
	}
	public void setParcel_name(String parcel_name) 
	{
		this.parcel_name = parcel_name;
	}
	/**
	 * 返回 数据包名
	 * @return
	 */
	public String getParcel_name() 
	{
		return this.parcel_name;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof FormParcel)) 
		{
			return false;
		}
		FormParcel rhs = (FormParcel) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.form_name, rhs.form_name)
		.append(this.parcelID, rhs.parcelID)
		.append(this.parcel_name, rhs.parcel_name)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.form_name) 
		.append(this.parcelID) 
		.append(this.parcel_name) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("form_name", this.form_name) 
		.append("parcelID", this.parcelID) 
		.append("parcel_name", this.parcel_name) 
		.toString();
	}

}
