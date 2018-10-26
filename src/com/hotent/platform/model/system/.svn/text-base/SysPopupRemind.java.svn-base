package com.hotent.platform.model.system;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hotent.core.model.BaseModel;
import com.hotent.core.util.StringUtil;

/**
 * 对象功能:sys_popup_remind Model对象 开发公司:广州宏天软件有限公司 开发人员:liyj 创建时间:2015-03-18 11:36:24
 */
public class SysPopupRemind extends BaseModel {
	// ID
	protected Long id;
	// 主题 弹框时的信息
	protected String subject;
	// 点击该提醒的跳转URL
	protected String url;
	// SQL 返回数据大小
	protected String sql;
	// 数据源别名
	protected String dsalias;
	// 排序
	protected Long sn;
	// 是否启用 0:否   1:是
	protected Short enabled;
	// createTime
	protected Date createTime;
	// 创建人 json.id,json.name
	protected String creator;
	//描叙字段
	protected String desc;
	
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 返回 ID
	 * 
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 返回 主题 弹框时的信息
	 * 
	 * @return
	 */
	public String getSubject() {
		return this.subject;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 返回 点击该提醒的跳转URL
	 * 
	 * @return
	 */
	public String getUrl() {
		return this.url;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * 返回 SQL 返回数据大小
	 * 
	 * @return
	 */
	public String getSql() {
		return this.sql;
	}

	public void setDsalias(String dsalias) {
		this.dsalias = dsalias;
	}

	/**
	 * 返回 数据源别名
	 * 
	 * @return
	 */
	public String getDsalias() {
		return this.dsalias;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	/**
	 * 返回 排序
	 * 
	 * @return
	 */
	public Long getSn() {
		return this.sn;
	}

	public void setEnabled(Short enabled) {
		this.enabled = enabled;
	}

	/**
	 * 返回 是否启用
	 * 
	 * @return
	 */
	public Short getEnabled() {
		return this.enabled;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 返回 createTime
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * 返回 创建人 json.id,json.name
	 * 
	 * @return
	 */
	public String getCreator() {
		return this.creator;
	}
	
	/**
	 * 把creator字段翻译成json然后返回ID
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getCreatorId() {
		if (StringUtil.isEmpty(creator))
			return "";
		return JSONObject.fromObject(creator).getString("id");
	}

	/**
	 * 把creator字段翻译成json然后返回NAME
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getCreatorName() {
		if (StringUtil.isEmpty(creator))
			return "";
		return JSONObject.fromObject(creator).getString("name");
	}

	/**
	 * desc
	 * @return  the desc
	 * @since   1.0.0
	 */
	
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SysPopupRemind)) {
			return false;
		}
		SysPopupRemind rhs = (SysPopupRemind) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.subject, rhs.subject).append(this.url, rhs.url).append(this.sql, rhs.sql).append(this.dsalias, rhs.dsalias).append(this.sn, rhs.sn).append(this.enabled, rhs.enabled).append(this.createTime, rhs.createTime).append(this.creator, rhs.creator).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.subject).append(this.url).append(this.sql).append(this.dsalias).append(this.sn).append(this.enabled).append(this.createTime).append(this.creator).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("subject", this.subject).append("url", this.url).append("sql", this.sql).append("dsalias", this.dsalias).append("sn", this.sn).append("enabled", this.enabled).append("createTime", this.createTime).append("creator", this.creator).toString();
	}

}