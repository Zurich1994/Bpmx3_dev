package com.hotent.platform.webservice.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hotent.core.cache.ICache;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.dao.system.SysUserDao;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysPaurService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.webservice.api.UserDetailsService;

/**
 * 子系人员统登陆服务
 * 
 * @author Administrator
 * 
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	@Resource
	SysUserDao sysUserDao;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysPaurService sysPaurService;

	/**
	 * 取得登陆子系统的user
	 * 
	 * @param userName
	 * @return
	 * @throws UsernameNotFoundException
	 * @throws DataAccessException
	 */
	public SysUser loadUserByUsername(String account) throws Exception {
		UserDetails user = sysUserDao.loadUserByUsername(account);
		if (user instanceof SysUser) {
			SysUser sysUser = (SysUser) user;
			return sysUser;
		} else
			return null;
	}

	/**
	 * 通过用户名取得登陆子系统的用户角色信息
	 */
	public String loadRoleByUsername(String userName){
		SysUser user = sysUserDao.getByAccount(userName);	
		Long userId=user.getUserId();
		if(userId==null) return "";
		
		//根据用户的ID来获取用户的当前组织，优先从缓存中取
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		//直接从缓存中读取
		SysOrg org = (SysOrg)iCache.getByKey(ContextUtil.CurrentOrg + userId);
		
		Long orgId=org==null?0:org.getOrgId();
		List<String> list = sysRoleService.getRolesByUserIdAndOrgId(userId, orgId);
		String roles="";
		for(String role:list){
			if(!StringUtil.isEmpty(roles))
				roles+=",";
			roles+=role;
		}
		return roles;
		
	}
	
	
	/**
	 * 根据用户工号返回这个用户的组织列表。
	 */
	public List<SysOrg> loadOrgsByUsername(String account) {
		SysUser user = sysUserDao.getByAccount(account);	
		Long userId=user.getUserId();
		List<SysOrg> sysOrgs = sysOrgService.getOrgsByUserId(userId);
		return sysOrgs;
	}

	/**
	 * 获取当前用户的组织。
	 */
	public SysOrg loadCurOrgByUsername(String account) {
		SysUser user = sysUserDao.getByAccount(account);	
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		String key=ContextUtil.CurrentOrg + user.getUserId();
		if(iCache.getByKey(key)!=null){
			return (SysOrg)iCache.getByKey(key);
		}
		else{
			SysOrg org= sysOrgService.getDefaultOrgByUserId(user.getUserId());
			if(org!=null){
				iCache.add(key, org);
				return org;
			}
		}
		return null;
	}
	
	
	public void setCurOrg(String username, Long orgId) {
		SysUser user = sysUserDao.getByAccount(username);	
		Long userId=user.getUserId();
		ContextUtil.setCurrentUser(user);
	
		SysOrg sysOrg = sysOrgService.getById(orgId);
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		iCache.add(ContextUtil.CurrentOrg + userId, sysOrg);
		
	}
	
	public String getCurrentUserSkin(Long userId) {
		ICache iCache=(ICache)AppUtil.getBean(ICache.class);
		String key = "skinStyle_"+userId;
		if(iCache.getByKey(key)!=null){
			return iCache.getByKey(key).toString();
		}else{
			String skin = sysPaurService.getCurrentUserSkin(userId);
			if(!skin.isEmpty()){
				iCache.add(key, skin);
				return skin;
			}
		}
		return "default";
	}
}
