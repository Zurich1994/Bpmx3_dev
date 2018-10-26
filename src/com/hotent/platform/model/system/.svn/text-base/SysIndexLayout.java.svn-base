package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:首页布局 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 17:30:22
 */
public class SysIndexLayout extends BaseModel{
	// 主键
	protected Long  id;
	// 布局名称
	protected String  name;
	// 布局描述
	protected String  memo;
	// 模版内容
	protected String  templateHtml;
	// 排序
	protected Long  sn;

	public void setId(Long id){
		this.id = id;
	}
	/**
	 * 返回 主键
	 * @return
	 */
	public Long getId() {
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 返回 布局名称
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	public void setMemo(String memo){
		this.memo = memo;
	}
	/**
	 * 返回 布局描述
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}
	public void setTemplateHtml(String templateHtml){
		this.templateHtml = templateHtml;
	}
	/**
	 * 返回 模版内容
	 * @return
	 */
	public String getTemplateHtml() {
		return this.templateHtml;
	}
	public void setSn(Long sn){
		this.sn = sn;
	}
	/**
	 * 返回 排序
	 * @return
	 */
	public Long getSn() {
		return this.sn;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysIndexLayout)) 
		{
			return false;
		}
		SysIndexLayout rhs = (SysIndexLayout) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.name, rhs.name)
		.append(this.memo, rhs.memo)
		.append(this.templateHtml, rhs.templateHtml)
		.append(this.sn, rhs.sn)
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
		.append(this.memo) 
		.append(this.templateHtml) 
		.append(this.sn) 
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
		.append("memo", this.memo) 
		.append("templateHtml", this.templateHtml) 
		.append("sn", this.sn) 
		.toString();
	}
   
  

}