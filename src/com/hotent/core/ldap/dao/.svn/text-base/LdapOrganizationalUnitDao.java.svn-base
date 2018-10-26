package com.hotent.core.ldap.dao;

import java.util.List;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.filter.Filter;

import com.hotent.core.ldap.model.LdapOrganization;
import com.hotent.core.ldap.model.LdapOrganizationalUnit;

public interface LdapOrganizationalUnitDao {
	/**
	 * 取得所有的组织单元
	 * @return
	 */
	public List<LdapOrganizationalUnit> getAll();
	/**
	 * @param dn
	 * @return
	 */
	public List<LdapOrganizationalUnit> get(DistinguishedName dn);
	public List<LdapOrganizationalUnit> get(Filter filter,DistinguishedName dn);
	public List<LdapOrganizationalUnit> get(Filter filter);
	/**
	 * @param orgName
	 * @return
	 */
	public List<LdapOrganizationalUnit> getByName(String orgName);
}
