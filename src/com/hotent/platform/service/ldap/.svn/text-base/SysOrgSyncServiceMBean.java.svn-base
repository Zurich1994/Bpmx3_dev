package com.hotent.platform.service.ldap;

import java.util.Date;
import java.util.List;

import com.hotent.platform.model.system.SysOrg;

public interface SysOrgSyncServiceMBean {
	
	public Date getLastSyncTime();
	
	public Long getLastSyncTakeTime() ;

	public List<SysOrg> getNewFromLdapOrgList();

	public List<SysOrg> getDeleteLocalOrgList() ;

	public List<SysOrg> getUpdateLocalOrgList();
	
	void reset();
}
