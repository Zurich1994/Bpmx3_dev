package com.hotent.platform.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.service.system.IOrgHandler;
import com.hotent.platform.service.system.IOrgService;
import com.hotent.platform.service.system.SysOrgService;

public class OrgServiceImpl implements IOrgService {
	
	@Resource
	GroovyScriptEngine groovyScriptEngine;
	@Resource
	SysOrgService orgService;
	
	public static Long TopOrgId=0L;
	
	private Map<String, IOrgHandler> handMap=new HashMap<String, IOrgHandler>();
	
	public void setHandMap(Map<String, IOrgHandler> map) {
		handMap=map;
	}
	


	public SysOrg getSysOrgByScope(String type, String value) {
		SysOrg sysOrg=null;
		if("system".equals(type)){
			IOrgHandler handler=handMap.get(value);
			sysOrg = handler.getByType(value);
		}
		else{
			try {
				Long orgId=(Long) groovyScriptEngine.executeObject(value, null);
				if(TopOrgId.equals(orgId)){
					sysOrg= OrgHelper.getTopOrg();
				}
				else{
					sysOrg=orgService.getById(orgId);
				}
			} catch (Exception e) {
				e.printStackTrace();
				sysOrg= OrgHelper.getTopOrg();
			}
		}
		if (BeanUtils.isEmpty(sysOrg)) sysOrg= OrgHelper.getTopOrg();
		return sysOrg;
	}
	
	

}
