package com.hotent.core.ldap.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.InvalidNameException;
import javax.naming.directory.SearchControls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.control.PagedResult;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.ContextAssembler;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.stereotype.Repository;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.ldap.dao.LdapUserDao;
import com.hotent.core.ldap.map.LdapUserAssembler;
import com.hotent.core.ldap.model.LdapUser;

@Repository
public class LdapUserDaoImpl implements LdapUserDao {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Resource
	LdapTemplate ldapTemplate;

	@Override
	public List<LdapUser> getAll() {
		return get(getDn(""));
	}
	
	@Override
	public List<LdapUser> getAll(Map<String,Object> params) {
		AndFilter filter=new AndFilter();
		if(BeanUtils.isNotEmpty(params)){
			Iterator<String> keys = params.keySet().iterator();
			while(keys.hasNext()){
				String key=keys.next();
				filter.and(new LikeFilter(key, params.get(key)+"*"));
			}
	
		}
		logLdapQuey(filter.encode());
		return get(filter,getDn(""));
	}
	
	/**
	 * 通过分页查询所有的用户
	 * (non-Javadoc)
	 * @see com.hotent.core.ldap.dao.LdapUserDao#get()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LdapUser> get(){
		List<LdapUser> ldapUserList=new ArrayList<LdapUser>();
		int pageSize=100;
		PagedResult pagedResult=this.get(null, pageSize);
		List<LdapUser> users = pagedResult.getResultList();
		ldapUserList.addAll(users);
		byte[]  bytesCookie= pagedResult.getCookie().getCookie();
		while(users.size()==pageSize && bytesCookie!=null){
			pagedResult=this.get(pagedResult.getCookie(), pageSize);
			users=pagedResult.getResultList();
			ldapUserList.addAll(users);
			bytesCookie=pagedResult.getCookie().getCookie();
		}
		return ldapUserList;
	}
	
	/**
	 * 通过分页，查询用户
	 *  (non-Javadoc)
	 * @see com.hotent.core.ldap.dao.LdapUserDao#get(org.springframework.ldap.control.PagedResultsCookie, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public PagedResult get(PagedResultsCookie cookie,int pageSize){
		PagedResultsDirContextProcessor pagePagedResultsDirContextProcessor = new PagedResultsDirContextProcessor(
				pageSize, cookie);
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", LdapUser.OBJECTCLASS));
		
		List<LdapUser> list = ldapTemplate.search(getDn(null), filter.encode(),
				searchControls,getContextMapper() , pagePagedResultsDirContextProcessor);
		PagedResult pageResult = new PagedResult(list,
				pagePagedResultsDirContextProcessor.getCookie());
		return pageResult;
	}
	@Override
	public List<LdapUser> get(Filter filter) {
		return get(filter, getDn(null));
	}

	@Override
	public List<LdapUser> get(DistinguishedName dn) {
		EqualsFilter filter = new EqualsFilter("objectcategory",LdapUser.OBJECTCLASS);
		logLdapQuey(filter.encode());
		return this.get(filter,dn);
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<LdapUser> get(Filter filter, DistinguishedName dn) {
		List<LdapUser> ldapUserList=new ArrayList<LdapUser>();
		int pageSize=2;
		logLdapQuey(filter.encode());
		PagedResult pagedResult=this.get(filter,getDn(null),null, pageSize);
		List<LdapUser> users = pagedResult.getResultList();
		ldapUserList.addAll(users);
		while(users.size()==pageSize){
			pagedResult=this.get(filter,getDn(null),pagedResult.getCookie(), pageSize);
			users=pagedResult.getResultList();
			ldapUserList.addAll(users);
		}
		return ldapUserList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public PagedResult get(Filter filter, DistinguishedName dn,PagedResultsCookie cookie,int pageSize){
		PagedResultsDirContextProcessor pagePagedResultsDirContextProcessor = new PagedResultsDirContextProcessor(
				pageSize, cookie);
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		AndFilter andfilter = new AndFilter();
		andfilter.and(filter);
		andfilter.and(new EqualsFilter("objectclass", LdapUser.OBJECTCLASS));
	
		List<LdapUser> list = ldapTemplate.search(dn, andfilter.encode(),
				searchControls,getContextMapper() , pagePagedResultsDirContextProcessor);
		PagedResult pageResult = new PagedResult(list,
				pagePagedResultsDirContextProcessor.getCookie());
		return pageResult;
	}

	@Override
	public void addUser(LdapUser user){
		DirContextAdapter ctx=new DirContextAdapter(getDn("ou=HR,ou=hotent"));
		getContextMapper().mapToContext(user, ctx);
		ldapTemplate.bind(ctx);
	}
	
	@Override
	public boolean authenticate(String userId,String password){
		AndFilter filter = new AndFilter();
		 filter.and(new EqualsFilter("objectcategory",LdapUser.OBJECTCLASS))
		 .and(new EqualsFilter(LdapUser.KEY_SAMACCOUNTNAME,userId));
		 boolean authenticated = ldapTemplate.authenticate(getDn(null), filter.encode(), password);
		 return authenticated;
	}

	private ContextAssembler getContextMapper() {
		
//		return new UserMapper();
		return new LdapUserAssembler();
	}

	private DistinguishedName getDn(String dnstr) {
		DistinguishedName dn = new DistinguishedName();
		try {
			if (!StringUtil.isEmpty(dnstr)) {
				dn.addAll(new DistinguishedName(dnstr));
			}
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		logger.info(dn.encode());
		return dn;
	}
	
	
	
	private void logLdapQuey(String message,Object... args){
		String formatStr = String.format(message, args);
		logger.info("LDAP query statement:"+formatStr);
	}
}
