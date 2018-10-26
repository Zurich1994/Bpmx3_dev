package com.hotent.platform.webservice.api.util.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

/**
 *GrantedAuthority适配器，负责解析GrantedAuthority接口为可用于xml传输的实体类。
 */
public class GrantedAuthorityAdapter extends XmlAdapter<GrantedAuthorityValue, GrantedAuthority>{
	/**
	 *将GrantedAuthority接口转换为可用于xml传输的GrantedAuthorityValue类。
	 *@param	GrantedAuthorityValue	Spring角色接口。
	 *@return	GrantedAuthority	GrantedAuthority接口对应的值存储类GrantedAuthorityValue。	
	 */
	@Override
	public GrantedAuthorityValue marshal(GrantedAuthority v) throws Exception {

		return new GrantedAuthorityValue(v.getAuthority());
	}
	/**
	 *将可用于xml传输的GrantedAuthorityValue类转换为GrantedAuthority接口。
	 *@param	GrantedAuthorityValue	GrantedAuthority接口对应的值存储类GrantedAuthorityValue。
	 *@return	GrantedAuthority	Spring角色接口。
	 */
	@Override
	public GrantedAuthority unmarshal(GrantedAuthorityValue v) throws Exception {
	
		return new GrantedAuthorityImpl(v.authority);
		
	}

	
	
	
}
