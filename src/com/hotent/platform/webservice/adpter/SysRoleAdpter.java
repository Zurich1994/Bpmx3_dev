package com.hotent.platform.webservice.adpter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import com.hotent.platform.model.system.SysRole;

public class SysRoleAdpter extends XmlAdapter<SysRole,SysRole>{

	@Override
	public SysRole marshal(SysRole arg0) throws Exception {
		return (SysRole)arg0;
	}

	@Override
	public SysRole unmarshal(SysRole arg0) throws Exception {
		return (SysRole)arg0;
	}	
}
