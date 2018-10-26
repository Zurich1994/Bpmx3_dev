package com.hotent.core.ldap.dao;

import java.util.List;
import java.util.Map;

import org.springframework.ldap.control.PagedResult;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.filter.Filter;

import com.hotent.core.ldap.model.LdapUser;

public interface LdapUserDao {
	/**
	 * 取得所有的用户列表
	 * @return
	 */
	public List<LdapUser> getAll();
	
	/**
	 * @return
	 */
	List<LdapUser> get();

	public List<LdapUser> get(Filter filter);
	
	/**通过DN取得用户列表
	 * @param dn
	 * @return
	 */
	public List<LdapUser> get(DistinguishedName dn);
	
	/**通过DN取得用户列表
	 * @param dn
	 * @return
	 */
	public List<LdapUser> get(Filter filter,DistinguishedName dn);
	
	/**分页查询
	 * @param cookie
	 * @param pageSize
	 * @return
	 */
	PagedResult get(PagedResultsCookie cookie, int pageSize);

	/**用户认证
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean authenticate(String userId,String password);
	
	/**添加用户
	 * @param user
	 */
	public void addUser(LdapUser user);
	/**
	 * @param params
	 * @return
	 */
	List<LdapUser> getAll(Map<String, Object> params);

	PagedResult get(Filter filter, DistinguishedName dn,
			PagedResultsCookie cookie, int pageSize);
}
