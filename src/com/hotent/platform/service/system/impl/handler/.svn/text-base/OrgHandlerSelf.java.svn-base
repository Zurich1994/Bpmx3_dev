package com.hotent.platform.service.system.impl.handler;

import com.hotent.core.util.ContextUtil;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.system.IOrgHandler;

public class OrgHandlerSelf  implements IOrgHandler {

	public SysOrg getByType(String type) {
		SysOrg sysOrg=ContextUtil.getCurrentOrg();
		return sysOrg;
	}

}
