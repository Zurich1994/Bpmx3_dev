package com.hotent.platform.webservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class BpmUser {
	// userOrgId
	protected Long userOrgId;
	// userId
	protected Long userId;
	// 姓名
	protected String fullname;
	// 帐号
	protected String account;
	// 密码
	protected String password;
	// 是否过期
	protected Short isExpired;
	// 是否锁定
	protected Short isLock;
	// 创建时间
	protected java.util.Date createtime;
	// 状态
	protected Short status;
	// 邮箱
	protected String email;
	// 手机
	protected String mobile;
	// 电话
	protected String phone;

	// 性别
	protected String sex;
	// 照片
	protected String picture;

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Short getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Short isExpired) {
		this.isExpired = isExpired;
	}

	public Short getIsLock() {
		return isLock;
	}

	public void setIsLock(Short isLock) {
		this.isLock = isLock;
	}

	public java.util.Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public BpmUser() {

	}

}
