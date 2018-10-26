package com.hotent.core.ldap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.hotent.core.util.AppUtil;
import com.hotent.core.util.StringUtil;


public class LdapService {

	private LdapSettingModel ldapSettingModel;
	

	public LdapSettingModel getLdapSettingModel() {
		return ldapSettingModel;
	}

	public void setLdapSettingModel(LdapSettingModel ldapSettingModel) {
		this.ldapSettingModel = ldapSettingModel;
	}
	
	/**
	 * 登录用户。
	 * @param account
	 * @param password
	 * @return
	 */
	public boolean login(String account,String password){
		try {
			Hashtable env=getEnvironment(account,password);
			LdapContext ctx = new InitialLdapContext(env, null);
			ctx.close();
			return true;
		} catch (NamingException err) {
			err.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 同步用户。
	 */
	public void syncUser(){
		List<Map<String,String>> list=getLdapObjects(true);
		LdapObjectEvent events=new LdapObjectEvent(list);
		events.setUser(true);
		AppUtil.publishEvent(events);
	}
	
	/**
	 * 同步组织。
	 */
	public void syncOrg(){
		List<Map<String,String>> list=getLdapObjects(false);
		LdapObjectEvent events=new LdapObjectEvent(list);
		events.setUser(false);
		AppUtil.publishEvent(events);
	}
	
	private List getLdapObjects(boolean isUser) {
		Hashtable env=getEnvironment();
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		try {
			LdapContext ctx = new InitialLdapContext(env, null);
			// 域节点
			List<String> arySearchScope=getSearchScope();
			for(String scope:arySearchScope){
				List<Map<String,String>> tmplist= getByScope(isUser,  ctx, scope);
				list.addAll(tmplist);
			}
			
			ctx.close();
		} catch (NamingException e) {
			e.printStackTrace();
			System.err.println("Throw Exception : " + e);
		}
		return list;

	}
	
	private List<String> getSearchScope(){
		String str=ldapSettingModel.getOrgNames();
		String domain=ldapSettingModel.getLdapBase();
		List<String> list=new ArrayList<String>();
		if(StringUtil.isEmpty(str)){
			list.add(domain);
		}
		else{
			String[] aryOrgs=str.split("[,]");
			for(String tmp:aryOrgs){
				String ou="OU=" + tmp +"," + domain;
				list.add(ou);
			}
		}
		return list;
		
	}
	
	

	private List<Map<String,String>>  getByScope(boolean isUser, 
			LdapContext ctx, String searchBase) throws NamingException {
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();
		// LDAP搜索过滤器类
		String searchFilter = isUser ? "(&(objectClass=user))"
				: "(&(objectClass=organizationalUnit))";
		// 搜索控制器
		SearchControls searchCtls = new SearchControls(); // Create the
		// 创建搜索控制器
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE); // Specify
		// 定制返回属性
		String fields=isUser? ldapSettingModel.getUserFields(): ldapSettingModel.getOrgFields();
		String[] returnedAtts=fields.split("[.]");
		
		searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
		// 根据设置的域节点、过滤器类和搜索控制器搜索LDAP得到结果
		NamingEnumeration answer = ctx.search(searchBase, searchFilter,searchCtls);// Search for objects using the filter
		while (answer.hasMoreElements()) {// 遍历结果集
			SearchResult sr = (SearchResult) answer.next();// 得到符合搜索条件的DN
			
			Attributes attrs = sr.getAttributes();// 得到符合条件的属性集
			if (attrs == null)  continue;
			Map<String,String> entMap=new HashMap<String, String>();
			
			for (NamingEnumeration ne = attrs.getAll(); ne.hasMore();) {
				Attribute attr = (Attribute) ne.next();// 得到下一个属性
				String key=attr.getID();
				String val=attr.get().toString();
				entMap.put(key, val);
			}
			list.add(entMap);
		}
		return list;
	}
	
	
	private Hashtable getEnvironment(){
		String account=ldapSettingModel.getUserDn();
		String url = new String(ldapSettingModel.getUrl());
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, account); // 不带邮箱后缀名的话，会报错，具体原因还未探究。高手可以解释分享。
		env.put(Context.SECURITY_CREDENTIALS, ldapSettingModel.getPassword());
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url);
		return env;
	}
	
	private Hashtable getEnvironment(String account,String password){
		int idx=ldapSettingModel.getUserDn().indexOf("@");
		account=account +ldapSettingModel.getUserDn().substring(idx);
		String url = new String( ldapSettingModel.getUrl());
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, account); // 不带邮箱后缀名的话，会报错，具体原因还未探究。高手可以解释分享。
		env.put(Context.SECURITY_CREDENTIALS, password);
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, url);
		return env;
	}
	

}
