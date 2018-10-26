package com.hotent.excelTempData_lc.model.excelTempData;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:excel 数据临时表 Model对象
 */
public class ExcelsjlsbLc extends BaseModel
{
	//主键
	protected Long id;
	/**
	 *时间
	 */
	protected String  sj;
	/**
	 *发生量
	 */
	protected String  fsl;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setSj(String sj) 
	{
		this.sj = sj;
	}
	/**
	 * 返回 时间
	 * @return
	 */
	public String getSj() 
	{
		return this.sj;
	}
	public void setFsl(String fsl) 
	{
		this.fsl = fsl;
	}
	/**
	 * 返回 发生量
	 * @return
	 */
	public String getFsl() 
	{
		return this.fsl;
	}
   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof ExcelsjlsbLc)) 
		{
			return false;
		}
		ExcelsjlsbLc rhs = (ExcelsjlsbLc) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.sj, rhs.sj)
		.append(this.fsl, rhs.fsl)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id)
		.append(this.sj) 
		.append(this.fsl) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id",this.id)
		.append("sj", this.sj) 
		.append("fsl", this.fsl) 
		.toString();
	}

}
