package com.hotent.platform.model.bpm;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:节点特权 privilegemodel对象 开发公司:广州宏天软件有限公司 开发人员:wwz 创建时间:2012-12-25 12:00:41
 */
public class BpmNodePrivilege extends BaseModel {
	// PRIVILEGEID
	protected Long privilegeid;
	// Act流程发布ID
	protected String actdefid;
	// 流程节点ID
	protected String nodeid;
	// 0=拥有所有特权
	// 1=允许直接处理
	// 2=允许一票制
	// 3=允许补签
	protected Long privilegemode;
	// 0=发起人
	// 1=用户
	// 2=角色
	// 3=组织
	// 4=组织负责人
	// 5=岗位
	// 6=上下级
	// 7=用户属性
	// 8=组织属性
	// 9=本部门
	// 10=为某个节点的执行人
	// 11=动态计算(如来自指定的方法或接口，如可允许来自上个表单的用户选择)
	protected Long usertype;
	// 特权人
	protected String cmpnames;
	// CMPIDS
	protected String cmpids;

	public void setPrivilegeid(Long privilegeid) {
		this.privilegeid = privilegeid;
	}

	/**
	 * 返回 PRIVILEGEID
	 * 
	 * @return
	 */
	public Long getPrivilegeid() {
		return this.privilegeid;
	}

	public void setActdefid(String actdefid) {
		this.actdefid = actdefid;
	}

	/**
	 * 返回 Act流程发布ID
	 * 
	 * @return
	 */
	public String getActdefid() {
		return this.actdefid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	/**
	 * 返回 流程节点ID
	 * 
	 * @return
	 */
	public String getNodeid() {
		return this.nodeid;
	}

	public void setPrivilegemode(Long privilegemode) {
		this.privilegemode = privilegemode;
	}

	/**
	 * 返回 0=拥有所有特权 1=允许直接处理 2=允许一票制 3=允许补签
	 * 
	 * @return
	 */
	public Long getPrivilegemode() {
		return this.privilegemode;
	}

	public void setUsertype(Long usertype) {
		this.usertype = usertype;
	}

	/**
	 * 返回 0=发起人 1=用户 2=角色 3=组织 4=组织负责人 5=岗位 6=上下级 7=用户属性 8=组织属性 9=本部门
	 * 10=为某个节点的执行人 11=动态计算(如来自指定的方法或接口，如可允许来自上个表单的用户选择)
	 * 
	 * @return
	 */
	public Long getUsertype() {
		return this.usertype;
	}

	public void setCmpnames(String cmpnames) {
		this.cmpnames = cmpnames;
	}

	/**
	 * 返回 特权人
	 * 
	 * @return
	 */
	public String getCmpnames() {
		return this.cmpnames;
	}

	public void setCmpids(String cmpids) {
		this.cmpids = cmpids;
	}

	/**
	 * 返回 CMPIDS
	 * 
	 * @return
	 */
	public String getCmpids() {
		return this.cmpids;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof BpmNodePrivilege)) {
			return false;
		}
		BpmNodePrivilege rhs = (BpmNodePrivilege) object;
		return new EqualsBuilder().append(this.privilegeid, rhs.privilegeid)
				.append(this.actdefid, rhs.actdefid)
				.append(this.nodeid, rhs.nodeid)
				.append(this.privilegemode, rhs.privilegemode)
				.append(this.usertype, rhs.usertype)
				.append(this.cmpnames, rhs.cmpnames)
				.append(this.cmpids, rhs.cmpids).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.privilegeid).append(this.actdefid)
				.append(this.nodeid).append(this.privilegemode)
				.append(this.usertype).append(this.cmpnames)
				.append(this.cmpids).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("privilegeid", this.privilegeid)
				.append("actdefid", this.actdefid)
				.append("nodeid", this.nodeid)
				.append("privilegemode", this.privilegemode)
				.append("usertype", this.usertype)
				.append("cmpnames", this.cmpnames)
				.append("cmpids", this.cmpids).toString();
	}

}