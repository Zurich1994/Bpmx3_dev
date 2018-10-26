package com.hotent.platform.job;

import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.service.ldap.LdapDbSync;
import com.hotent.platform.service.ldap.SysOrgSyncService;
import com.hotent.platform.service.ldap.SysUserSyncService;

public class LdapDbSyncJob extends BaseJob {

	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		LdapDbSync orgDataSyncService = (LdapDbSync) AppUtil.getBean(SysOrgSyncService.class);
		LdapDbSync userDataSyncService = (LdapDbSync) AppUtil.getBean(SysUserSyncService.class);
	
		orgDataSyncService.syncLodapToDb();
		userDataSyncService.syncLodapToDb();
		
	}

}
