package com.hotent.core.web.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.security.access.ConfigAttribute;

public class UrlPararRole{
	
	private Map<String, Collection<ConfigAttribute>> 	urlRoleMap=new HashMap<String, Collection<ConfigAttribute>>();
	private Map<String, Set<String>> urlParaMap =new HashMap<String, Set<String>>();
	
	public Map<String, Collection<ConfigAttribute>> getUrlRoleMap() {
		return urlRoleMap;
	}
	public void setUrlRoleMap(Map<String, Collection<ConfigAttribute>> urlRoleMap) {
		this.urlRoleMap = urlRoleMap;
	}
	public Map<String, Set<String>> getUrlParaMap() {
		return urlParaMap;
	}
	public void setUrlParaMap(Map<String, Set<String>> urlParaMap) {
		this.urlParaMap = urlParaMap;
	}
	
	
	
}
