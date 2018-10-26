package com.hotent.platform.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * <pre>
 * 对象功能:布局管理 Model对象
 *  开发公司:广州宏天软件有限公司 
 *  开发人员:hugh
 *  创建时间:2015-03-18 15:40:13
 *  </pre>
 */
public class SysIndexLayoutManage extends BaseModel {
	
	// 主键
	protected Long id;
	// 布局名称
	protected String name;
	// 布局描述
	protected String memo;
	// 组织ID
	protected Long orgId;
	// 模版内容
	protected String templateHtml;
	// 设计模版
	protected String designHtml;
	// 是否是默认
	protected Short isDef;
	// 组织名称
	protected String orgName;

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 主键
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回 布局名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 返回 布局描述
	 * 
	 * @return
	 */
	public String getMemo() {
		return this.memo;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
	 * 返回 组织ID
	 * 
	 * @return
	 */
	public Long getOrgId() {
		return this.orgId;
	}

	public void setTemplateHtml(String templateHtml) {
		this.templateHtml = templateHtml;
	}

	/**
	 * 返回 模版内容
	 * 
	 * @return
	 */
	public String getTemplateHtml() {
		return this.templateHtml;
	}

	public void setDesignHtml(String designHtml) {
		this.designHtml = designHtml;
	}

	/**
	 * 返回 设计模版
	 * 
	 * @return
	 */
	public String getDesignHtml() {
		return this.designHtml;
	}

	public void setIsDef(Short isDef) {
		this.isDef = isDef;
	}

	/**
	 * 返回 是否是默认
	 * 
	 * @return
	 */
	public Short getIsDef() {
		return this.isDef;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SysIndexLayoutManage)) {
			return false;
		}
		SysIndexLayoutManage rhs = (SysIndexLayoutManage) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.name, rhs.name).append(this.memo, rhs.memo)
				.append(this.orgId, rhs.orgId)
				.append(this.templateHtml, rhs.templateHtml)
				.append(this.designHtml, rhs.designHtml)
				.append(this.isDef, rhs.isDef).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id)
				.append(this.name).append(this.memo).append(this.orgId)
				.append(this.templateHtml).append(this.designHtml)
				.append(this.isDef).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("name", this.name).append("memo", this.memo)
				.append("orgId", this.orgId)
				.append("templateHtml", this.templateHtml)
				.append("designHtml", this.designHtml)
				.append("isDef", this.isDef).toString();
	}

}