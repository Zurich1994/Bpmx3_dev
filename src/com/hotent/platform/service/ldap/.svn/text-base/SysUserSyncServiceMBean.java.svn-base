package com.hotent.platform.service.ldap;

import java.util.Date;
import java.util.List;

import com.hotent.platform.model.system.SysUser;


public interface SysUserSyncServiceMBean {
	
	public Date getLastSyncTime();
	
	public Long getLastSyncTakeTime() ;

	public List<SysUser> getNewFromLdapUserList();

	public List<SysUser> getDeleteLocalUserList() ;

	public List<SysUser> getUpdateLocalUserList();
	
	void reset();
}
