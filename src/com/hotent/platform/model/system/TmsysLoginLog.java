package com.hotent.platform.model.system;


import java.util.Date;

import com.hotent.core.model.BaseModel;

/**
 * 对象功能:登陆日志 Model对象
 * 创建时间:2013-04-16 15:44:21
 */
public class TmsysLoginLog extends BaseModel
{																							//弹窗                      冒泡
        private static final long serialVersionUID = 133L;
	protected Long  logId;
	protected Long  userId;
	protected String  userAccount;
	protected String  fullName;
	protected String  userOrg;
	protected Date  loginTime;
	protected String  loginIp;
	protected String  remark1;
	protected String  remark2;
	public Long getLogId() {
	    return logId;
	}
	public void setLogId(Long logId) {
	    this.logId = logId;
	}
	public Long getUserId() {
	    return userId;
	}
	public void setUserId(Long userId) {
	    this.userId = userId;
	}
	public String getUserAccount() {
	    return userAccount;
	}
	public void setUserAccount(String userAccount) {
	    this.userAccount = userAccount;
	}
	public String getFullName() {
	    return fullName;
	}
	public void setFullName(String fullName) {
	    this.fullName = fullName;
	}
	public String getUserOrg() {
	    return userOrg;
	}
	public void setUserOrg(String userOrg) {
	    this.userOrg = userOrg;
	}
	
	public Date getLoginTime() {
	    return loginTime;
	}
	public void setLoginTime(Date loginTime) {
	    this.loginTime = loginTime;
	}
	public String getLoginIp() {
	    return loginIp;
	}
	public void setLoginIp(String loginIp) {
	    this.loginIp = loginIp;
	}
	public String getRemark1() {
	    return remark1;
	}
	public void setRemark1(String remark1) {
	    this.remark1 = remark1;
	}
	public String getRemark2() {
	    return remark2;
	}
	public void setRemark2(String remark2) {
	    this.remark2 = remark2;
	}
	
	
}