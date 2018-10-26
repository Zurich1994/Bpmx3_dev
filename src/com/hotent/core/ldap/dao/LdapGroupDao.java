package com.hotent.core.ldap.dao;

import java.util.List;
import org.springframework.ldap.core.DistinguishedName;
import com.hotent.core.ldap.model.LdapGroup;

public interface LdapGroupDao {
	/**
	 * 取得所有的用户组
	 * @return
	 */
	public List<LdapGroup> getAll();
	/**
	 * @param dn
	 * @return
	 */
	public List<LdapGroup> getByDN(DistinguishedName dn);

}
