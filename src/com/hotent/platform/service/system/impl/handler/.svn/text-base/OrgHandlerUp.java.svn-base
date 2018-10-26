package com.hotent.platform.service.system.impl.handler;

import javax.annotation.Resource;

import com.hotent.core.util.ContextUtil;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.system.IOrgHandler;
import com.hotent.platform.service.system.SysOrgService;

public class OrgHandlerUp implements IOrgHandler{

	@Resource
	SysOrgService orgService;
	
	public SysOrg getByType(String type) {
		SysOrg sysOrg=ContextUtil.getCurrentOrg();
		if (sysOrg.getOrgSupId()!=1) {
			sysOrg = orgService.getById(sysOrg.getOrgSupId());
		}
		return sysOrg;
	}
}
