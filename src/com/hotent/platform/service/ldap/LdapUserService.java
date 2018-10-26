package com.hotent.platform.service.ldap;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import com.hotent.core.ldap.dao.LdapUserDao;
import com.hotent.core.ldap.model.LdapUser;
import com.hotent.core.util.BeanUtils;

@Service
public class LdapUserService {
	@Resource
	private Properties configproperties;

	@Resource
	private LdapUserDao dao;
	
	/**
	 * 取得所有的AD用户
	 * @return
	 */
	public List<LdapUser> getAll(){
		return dao.getAll();
	}
	/**
	 * 要把DN取得相应DN下的所有的Ad用户
	 * @param dn
	 * @return
	 */
	public List<LdapUser> get(DistinguishedName dn){
		return dao.get(dn);
	}
	/**根据DN路径path，取得path下的所有AD用户
	 * @param path
	 * @return
	 */
	public List<LdapUser> get(String path){
		String baseDn=carefulDn(configproperties.getProperty("ldapBase"));
		path=carefulDn(path);
		if(path.endsWith(baseDn)){
			path=path.substring(0, path.length()-baseDn.length()-1);
		}
		DistinguishedName dn=new DistinguishedName(path);
		return dao.get(dn);
	}
	
	/**
	 * 根据过滤条件queryFilter，取得符合条件的所有AD用户
	 * @param queryFilter
	 * @return
	 */
	public List<LdapUser> getAll(Map<String, Object> queryFilter){
		return 	dao.getAll(queryFilter);
	}
	
	/**
	 * 通过账号，取得AD用户
	 * @param account
	 * @return
	 */
	public LdapUser getByAccount(String account){
		List<LdapUser> ldapUsers= dao.get(new EqualsFilter(LdapUser.KEY_SAMACCOUNTNAME, account));
		if(BeanUtils.isNotEmpty(ldapUsers)){
			return ldapUsers.get(0);
		}
		return null;
		
	}
	
	/**
	 * 取得严谨的表示DN的String。
	 * @param dn
	 * @return
	 */
	private static String carefulDn(String dn){
		StringBuffer sb=new StringBuffer();
		dn=dn.trim();
		dn = dn.replaceAll("\\s*,\\s*", ",");
		dn = dn.replaceAll("\\s*=\\s*", "=");
		String[] strs = dn.split(",");
		for(String str:strs){
			String[] strs2 = str.split("=");
			sb.append(strs2[0].toUpperCase());
			sb.append("=");
			sb.append(strs2[1]);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	/**用户认证
	 * @param userId
	 * @param password
	 * @return
	 */
	public boolean authenticate(String userId,String password){
		return dao.authenticate(userId, password);
	}
}
