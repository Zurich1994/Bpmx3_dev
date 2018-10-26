package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
/**
 * 对象功能:我的布局 Model对象
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 15:39:48
 */
public class SysIndexMyLayout extends BaseModel{
	// 主键
	protected Long  id;
	// 用户ID
	protected Long  userId;
	// 模版内容
	protected String  templateHtml;
	// 设计模版
	protected String  designHtml;
	// 布局管理ID
	protected Long  layoutId;

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
	public void setUserId(Long userId){
		this.userId = userId;
	}
	/**
	 * 返回 用户ID
	 * @return
	 */
	public Long getUserId() {
		return this.userId;
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
	public void setDesignHtml(String designHtml){
		this.designHtml = designHtml;
	}
	/**
	 * 返回 设计模版
	 * @return
	 */
	public String getDesignHtml() {
		return this.designHtml;
	}
	public void setLayoutId(Long layoutId){
		this.layoutId = layoutId;
	}
	/**
	 * 返回 布局管理ID
	 * @return
	 */
	public Long getLayoutId() {
		return this.layoutId;
	}
	

   	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) 
	{
		if (!(object instanceof SysIndexMyLayout)) 
		{
			return false;
		}
		SysIndexMyLayout rhs = (SysIndexMyLayout) object;
		return new EqualsBuilder()
		.append(this.id, rhs.id)
		.append(this.userId, rhs.userId)
		.append(this.templateHtml, rhs.templateHtml)
		.append(this.designHtml, rhs.designHtml)
		.append(this.layoutId, rhs.layoutId)
		.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() 
	{
		return new HashCodeBuilder(-82280557, -700257973)
		.append(this.id) 
		.append(this.userId) 
		.append(this.templateHtml) 
		.append(this.designHtml) 
		.append(this.layoutId) 
		.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		.append("id", this.id) 
		.append("userId", this.userId) 
		.append("templateHtml", this.templateHtml) 
		.append("designHtml", this.designHtml) 
		.append("layoutId", this.layoutId) 
		.toString();
	}
   
  

}