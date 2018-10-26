package com.hotent.platform.model.system;

import java.util.ArrayList;
import java.util.List;
import com.hotent.core.model.BaseModel;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 对象功能:SYS_DATA_SOURCE Model对象 开发公司:广州宏天软件有限公司 开发人员:Aschs 创建时间:2014-08-21 10:50:18
 */
public class SysDataSourceL extends BaseModel {
	// 主键
	protected Long id;
	// 名称
	protected String name;
	// 别名
	protected String alias;
	// 数据源的类型-mysql,oracle,sqlserver
	protected String dbType;
	// 设置字段
	protected String settingJson;
	// 开始服务器时启动
	protected Boolean initOnStart;
	// 是否可用
	protected Boolean enabled;
	// 类路径
	protected String classPath;
	// 初始化方法
	protected String initMethod;
	// 关闭方法
	protected String closeMethod;
	protected Long runId = 0L;

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
	 * 返回 名称
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 返回 别名
	 * 
	 * @return
	 */
	public String getAlias() {
		return this.alias;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	/**
	 * 返回 数据源的类型-mysql,oracle,sqlserver
	 * 
	 * @return
	 */
	public String getDbType() {
		return this.dbType;
	}

	public void setSettingJson(String settingJson) {
		this.settingJson = settingJson;
	}

	/**
	 * 返回 设置字段
	 * 
	 * @return
	 */
	public String getSettingJson() {
		return this.settingJson;
	}


	/**
	 * initOnStart
	 * @return  the initOnStart
	 * @since   1.0.0
	 */
	
	public Boolean getInitOnStart() {
		return initOnStart;
	}

	/**
	 * @param initOnStart the initOnStart to set
	 */
	public void setInitOnStart(Boolean initOnStart) {
		this.initOnStart = initOnStart;
	}

	/**
	 * enabled
	 * @return  the enabled
	 * @since   1.0.0
	 */
	
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	/**
	 * 返回 类路径
	 * 
	 * @return
	 */
	public String getClassPath() {
		return this.classPath;
	}

	public void setInitMethod(String initMethod) {
		this.initMethod = initMethod;
	}

	/**
	 * 返回 初始化方法
	 * 
	 * @return
	 */
	public String getInitMethod() {
		return this.initMethod;
	}

	public void setCloseMethod(String closeMethod) {
		this.closeMethod = closeMethod;
	}

	/**
	 * 返回 关闭方法
	 * 
	 * @return
	 */
	public String getCloseMethod() {
		return this.closeMethod;
	}

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SysDataSourceL)) {
			return false;
		}
		SysDataSourceL rhs = (SysDataSourceL) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.name, rhs.name).append(this.alias, rhs.alias).append(this.dbType, rhs.dbType).append(this.settingJson, rhs.settingJson).append(this.initOnStart, rhs.initOnStart).append(this.enabled, rhs.enabled).append(this.classPath, rhs.classPath).append(this.initMethod, rhs.initMethod).append(this.closeMethod, rhs.closeMethod).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.id).append(this.name).append(this.alias).append(this.dbType).append(this.settingJson).append(this.initOnStart).append(this.enabled).append(this.classPath).append(this.initMethod).append(this.closeMethod).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", this.id).append("name", this.name).append("alias", this.alias).append("dbType", this.dbType).append("settingJson", this.settingJson).append("initOnStart", this.initOnStart).append("enabled", this.enabled).append("classPath", this.classPath).append("initMethod", this.initMethod).append("closeMethod", this.closeMethod).toString();
	}

}