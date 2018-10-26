package com.hotent.platform.model.bus;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:查询过滤共享 Model对象 开发公司:广州宏天软件有限公司 开发人员:zxh 创建时间:2013-12-20 10:10:44
 */
public class BusQueryShare extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6818930311080038741L;
	// 主键
	protected Long id;
	// 过滤ID
	protected Long filterId;
	// 共享权限
	protected String shareRight;
	// 共享人ID
	protected Long sharerId;
	// 创建时间
	protected java.util.Date  createtime;

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

	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}

	/**
	 * 返回 过滤ID
	 * 
	 * @return
	 */
	public Long getFilterId() {
		return this.filterId;
	}

	public void setShareRight(String shareRight) {
		this.shareRight = shareRight;
	}

	/**
	 * 返回 共享权限
	 * 
	 * @return
	 */
	public String getShareRight() {
		return this.shareRight;
	}

	public void setSharerId(Long sharerId) {
		this.sharerId = sharerId;
	}

	/**
	 * 返回 共享人ID
	 * 
	 * @return
	 */
	public Long getSharerId() {
		return this.sharerId;
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

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BusQueryShare)) {
			return false;
		}
		BusQueryShare rhs = (BusQueryShare) object;
		return new EqualsBuilder().append(this.id, rhs.id)
				.append(this.filterId, rhs.filterId)
				.append(this.shareRight, rhs.shareRight)
				.append(this.sharerId, rhs.sharerId)
				.append(this.createtime, rhs.createtime).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id)
				.append(this.filterId)
				.append(this.shareRight)
				.append(this.sharerId)
				.append(this.createtime)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id)
				.append("filterId", this.filterId)
				.append("shareRight", this.shareRight)
				.append("shareId", this.sharerId)
				.append("createtime", this.createtime) .toString();
	}

}