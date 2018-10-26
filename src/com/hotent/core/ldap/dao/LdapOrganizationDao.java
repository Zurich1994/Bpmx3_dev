package com.hotent.core.ldap.dao;

import java.util.List;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.filter.Filter;

import com.hotent.core.ldap.model.LdapOrganization;

public interface LdapOrganizationDao {
	/**
	 * 取得所有的组织单元
	 * @return
	 */
	public List<LdapOrganization> getAll();
	/**
	 * @param dn
	 * @return
	 */
	public List<LdapOrganization> get(DistinguishedName dn);
	
	/**
	 * @param filter
	 * @return
	 */
	public List<LdapOrganization> get(Filter filter);
	
	/**
	 * @param filter
	 * @param dn
	 * @return
	 */
	public List<LdapOrganization> get(Filter filter,DistinguishedName dn);
	
	
	
	
	/**
	 * @param orgName
	 * @return
	 */
	public List<LdapOrganization> getByName(String orgName);
}
