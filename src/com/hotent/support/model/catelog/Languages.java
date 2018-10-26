package com.hotent.support.model.catelog;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:Support语言表 Model对象
 */
public class Languages extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *语言
	 */
	protected String  LANGUAGE;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setLANGUAGE(String LANGUAGE) 
	{
		this.LANGUAGE = LANGUAGE;
	}
	/**
	 * 返回 语言
	 * @return
	 */
	public String getLANGUAGE() 
	{
		return this.LANGUAGE;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof Languages)) 
		{
			return false;
		}
		Languages rhs = (Languages) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.LANGUAGE, rhs.LANGUAGE)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.LANGUAGE) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("LANGUAGE", this.LANGUAGE) 
		.toString();
	}

}
