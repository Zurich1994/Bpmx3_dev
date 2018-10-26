package com.hotent.platform.service.system;

import com.hotent.platform.model.system.SysOrg;

public interface IOrgService {
	
	/**
	 * 根据scope取得组织。
	 * @param type	system 或script
	 * @param value	
	 * @return
	 */
	SysOrg getSysOrgByScope(String type,String value);

}
