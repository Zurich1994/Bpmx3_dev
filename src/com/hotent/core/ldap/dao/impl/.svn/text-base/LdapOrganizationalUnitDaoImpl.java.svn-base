package com.hotent.core.ldap.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.naming.InvalidNameException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.ContextMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.LikeFilter;
import org.springframework.stereotype.Repository;

import com.hotent.core.util.StringUtil;
import com.hotent.core.ldap.dao.LdapOrganizationalUnitDao;
import com.hotent.core.ldap.map.LdapOrganizationalUnitAssembler;
import com.hotent.core.ldap.model.LdapOrganizationalUnit;

@Repository
public class LdapOrganizationalUnitDaoImpl implements LdapOrganizationalUnitDao {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Resource
	LdapTemplate ldapTemplate;

	@Override
	public List<LdapOrganizationalUnit> getAll() {
		return get(getDn(""));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LdapOrganizationalUnit> get(DistinguishedName dn) {
		EqualsFilter filter = new EqualsFilter("objectcategory",LdapOrganizationalUnit.OBJECTCLASS);
		logLdapQuey(filter.encode());
		return ldapTemplate.search(dn, filter.encode(), getContextMapper());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LdapOrganizationalUnit> getByName(String orgName) {
		// 过滤组织单元
		
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectcategory",LdapOrganizationalUnit.OBJECTCLASS));
		filter.and(new LikeFilter(LdapOrganizationalUnit.KEY_NAME, orgName));
		logLdapQuey(filter.encode());
		return ldapTemplate.search(getDn(""), filter.encode(),
				getContextMapper());
	}
	
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<LdapOrganizationalUnit> get(Filter filter, DistinguishedName dn) {
		AndFilter andFilter=new AndFilter();
		andFilter.and(new EqualsFilter("objectcategory",LdapOrganizationalUnit.OBJECTCLASS));
		andFilter.and(filter);
		logLdapQuey(andFilter.encode());
		try{
			return ldapTemplate.search(dn, andFilter.encode(), getContextMapper());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<LdapOrganizationalUnit> get(Filter filter) {
		return this.get(filter, getDn(null));
	}

	private ContextMapper getContextMapper() {
		return new LdapOrganizationalUnitAssembler();
	}

	private DistinguishedName getDn(String dnstr) {
		DistinguishedName dn = new DistinguishedName();
		try {
			if (!StringUtil.isEmpty(dnstr)) {
				dn.add(dnstr);
			}
		} catch (InvalidNameException e) {
			e.printStackTrace();
		}
		return dn;
	}
	
	private void logLdapQuey(String message,Object... args){
		String formatStr = String.format(message, args);
		logger.info("LDAP query statement:"+formatStr);
	}
}
