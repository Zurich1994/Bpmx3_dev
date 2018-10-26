package com.hotent.platform.service.ldap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hotent.core.ldap.dao.LdapUserDao;
import com.hotent.core.ldap.model.LdapUser;
import com.hotent.core.ldap.until.OrgHelper;
import com.hotent.core.ldap.until.UserHelper;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.model.system.SystemConst;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysUserOrgService;
import com.hotent.platform.service.system.SysUserService;

@Service
public class SysUserSyncService implements LdapDbSync, SysUserSyncServiceMBean{
	@Resource
	Properties configproperties;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private LdapUserDao userDao;
	
	protected List<SysUser> totalDbUsers;
	protected List<LdapUser> totalLdapUsers;
	protected List<SysOrg> totalDbOrgs;
	
	protected Date lastSyncTime;
	protected Long lastSyncTakeTime;
	protected final List<SysUser> newFromLdapUserList=new ArrayList<SysUser>();
	protected final List<SysUser> deleteLocalUserList=new ArrayList<SysUser>();;
	protected final List<SysUser> updateLocalUserList=new ArrayList<SysUser>();;
	protected Logger logger = LoggerFactory.getLogger(SysUserSyncService.class);
	public int syncLodapToDb() {
		reset();
		int result = 0;
		lastSyncTime=new Date();
		long startTime=System.currentTimeMillis();
		int strategy = Integer.valueOf((String) configproperties.get("ldapStrategy"));
		switch (strategy) {
		case 1:
			result = syncLdapAprior();
			break;
		case 2:
			result = syncLdapImport();
			break;
		default:
			throw new  UnsupportedClassVersionError("未找到实现同步Ldap用户到本地操作策略");
		}
		lastSyncTakeTime=System.currentTimeMillis()-startTime;
		return result;
	}
	/**
	 * 以Ldap服务器数据为优先进行同步。
	 * @return
	 */
	protected int syncLdapAprior() {
		totalDbUsers = sysUserService.getAllIncludeOrg();
		totalLdapUsers=userDao.getAll();
		totalDbOrgs=sysOrgService.getAll();
		/* AD中已经删除，而数据库中未同步的用户进行删除操作
		 */
		syncLdapDelUser(totalLdapUsers);
		/* 同步用户组织关系--AD与DB之前已经同步，而之后AD又更新了用户所有组织。
		 */
		syncLdapUserOrgRelationship(totalLdapUsers);
		/* AD中新添加，而数据库中未同步的组织进行插入操作
		 */
		syncLdapNewUser(totalLdapUsers);
		/*同步更改的用户信息，AD与DB之前已经同步，而之后AD又更新了的数据。
		 */
		syncLdapChangedUser(totalLdapUsers);
		return deleteLocalUserList.size()+updateLocalUserList.size()+newFromLdapUserList.size();
	}
	
	/**导入Ldap服务器数据，进行同步。
	 * @return
	 */
	protected int syncLdapImport(){
		totalDbUsers = sysUserService.getAllIncludeOrg();
		totalLdapUsers=userDao.getAll();
		totalDbOrgs=sysOrgService.getAll();
		/* AD中新添加，而数据库中未同步的组织进行插入操作
		 */
		syncLdapNewUser(totalLdapUsers);
		return newFromLdapUserList.size();
		
	}
	
	/**计算需要从本地删除的用户
	 * @param totalLdapUsers
	 * @return
	 */
	protected List<SysUser> calDelUserList(List<LdapUser> totalLdapUsers){
		List<SysUser> ldapDbUsers = UserHelper.getSysUsersByFromType(totalDbUsers,SystemConst.FROMTYPE_AD);
		return UserHelper.execptSysLdapUsers(ldapDbUsers, totalLdapUsers);
	}
	
	/**
	 * Ldap删除的用户，同步删除本地用户
	 * @param totalLdapUsers
	 * @return 删除的用户
	 */
	protected  List<SysUser> syncLdapDelUser(List<LdapUser> totalLdapUsers){
		List<SysUser> delSysUserList = calDelUserList(totalLdapUsers);
		deleteLocalUserList.clear();
		deleteLocalUserList.addAll(delSysUserList);
		for(SysUser sysUser:deleteLocalUserList){
			sysUserService.delById(sysUser.getUserId());
		}
		return delSysUserList;
	}
	
	/**计算需要添加到本地的用户
	 * @param totalLdapUsers
	 * @return
	 */
	protected List<SysUser> calNewUserList(List<LdapUser> totalLdapUsers){
		List<LdapUser> addLdapUsers=UserHelper.execptLdapSysUsers(totalLdapUsers, totalDbUsers);
		return UserHelper.covertLdapToSysUsrs(addLdapUsers,totalDbOrgs);
	}
	
	/**
	 * Ldap新添加的用户，同步添加到本地
	 * @param totalLdapUsers
	 */
	protected List<SysUser> syncLdapNewUser(List<LdapUser> totalLdapUsers){
		newFromLdapUserList.clear();
		List<SysUser> newSysUserList = calNewUserList(totalLdapUsers);
		newFromLdapUserList.addAll(newSysUserList);
		for(SysUser sysUser:newFromLdapUserList){
			logger.info(sysUser.toString());
			sysUserService.add(sysUser);
			
			try {
				sysUserOrgService.addOrgUser(new Long[]{sysUser.getUserId()},sysUser.getUserOrgId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newFromLdapUserList;
	}
	
	/**计算已更新的本地用户
	 * @param totalLdapUsers
	 * @return
	 */
	protected List<LdapUser> calUpdateLdapUserList(List<LdapUser> totalLdapUsers){
		List<LdapUser> updateLdapUserList=new ArrayList<LdapUser>();
		List<LdapUser> intersectUserList=UserHelper.intersectLdapSysUser(totalLdapUsers, totalDbUsers);
		for(LdapUser ldapUser:intersectUserList){
			List<SysUser> sysUsers = UserHelper.getSysUserByAccount(totalDbUsers,ldapUser.getsAMAccountName());
			if(BeanUtils.isEmpty(sysUsers)){
				continue;
			}
			SysUser sysUser = sysUsers.get(0);
			/**
			 * 约定，如果有同名的DB非来自Ad的用户，同名的Ad用户信息将不再同步到DB中
			 */
			if(sysUser.getFromType()!=1){
				continue;
			}
			if(!UserHelper.isSysLdapUsrEqualIngoreOrg(sysUser, ldapUser)){
				updateLdapUserList.add(ldapUser);
			}
		}
		return intersectUserList;
	}
	
	/**计算需要更新到本地的用户
	 * @param totalLdapUsers
	 * @return
	 */
	protected List<SysUser> calUpdateUserList(List<LdapUser> totalLdapUsers){
		List<SysUser> updateSysUserList=new ArrayList<SysUser>();
		List<LdapUser> intersectUserList=UserHelper.intersectLdapSysUser(totalLdapUsers, totalDbUsers);
		for(LdapUser ldapUser:intersectUserList){
			List<SysUser> sysUsers = UserHelper.getSysUserByAccount(totalDbUsers,ldapUser.getsAMAccountName());
			if(BeanUtils.isEmpty(sysUsers)){
				continue;
			}
			SysUser sysUser = sysUsers.get(0);
			/**
			 * 约定，如果有同名的DB非来自Ad的用户，同名的Ad用户信息将不再同步到DB中
			 */
			if(sysUser.getFromType()!=1){
				continue;
			}
			if(!UserHelper.isSysLdapUsrEqualIngoreOrg(sysUser, ldapUser)){
				sysUser.setAccount(ldapUser.getsAMAccountName());
				sysUser.setEmail(ldapUser.getMail());
				String sn = ldapUser.getSn();
				String givenName= ldapUser.getGivenName();
				String fullname = (sn==null?"":sn)+(givenName==null?"":givenName);
				fullname=fullname.equals("")?null:fullname;
				sysUser.setFullname(fullname);
				sysUser.setPhone(ldapUser.getHomePhone());
				sysUser.setMobile(ldapUser.getTelephoneNumber());
				sysUser.setStatus(ldapUser.isAccountDisable()?SystemConst.STATUS_NO:SystemConst.STATUS_OK);
				updateSysUserList.add(sysUser);
			}
		}
		return updateSysUserList;
	}
	
	
	/**Ldap更新的用户，同步更新到本地
	 * @param totalLdapUsers
	 */
	protected List<SysUser> syncLdapChangedUser(List<LdapUser> totalLdapUsers){
		/*
		 *一 .更新用户信息
		 */
		List<SysUser> updateSysUserList=calUpdateUserList(totalLdapUsers);
		updateLocalUserList.clear();
		updateLocalUserList.addAll(updateSysUserList);
		for(SysUser sysUser:updateSysUserList){
			sysUserService.update(sysUser);
		}
		
		
		return updateSysUserList;
	}
	
	protected void syncLdapUserOrgRelationship(List<LdapUser> totalLdapUsers){
		for(LdapUser ldapUser:totalLdapUsers){
			List<SysUser> sysUsers =  UserHelper.getSysUserByAccount(totalDbUsers, ldapUser.getsAMAccountName());
			if(BeanUtils.isEmpty(sysUsers)){
				continue;
			}
			SysUser sysUser =sysUsers.get(0);
			/**
			 * 约定，如果有同名的DB非来自Ad的用户，同名的Ad用户信息将不再同步到DB中
			 */
			if(sysUser.getFromType()!=1){
				continue;
			}
			//与该用户相关的组织
			List<SysOrg> sysOrgs = sysOrgService.getOrgsByUserId(sysUser.getUserId());
			
			boolean flag=false;
			
			//删除与AD不同步的用户组织关系
			for(SysOrg org:sysOrgs){
				String namePath = OrgHelper.getOrgsNamePath(org, this.totalDbOrgs);
				String path=OrgHelper.getPathByDn(ldapUser.getDistinguishedName());
				if(namePath.equals(path)){
					flag=true;
				}else{
					SysUserOrg sysUserOrg = sysUserOrgService.getUserOrgModel(sysUser.getUserId(), org.getOrgId());
					sysUserOrgService.delById(sysUserOrg.getUserOrgId());
				}
			}
			
			if(!flag){
				//添加新的用户组织关系。
				SysOrg sysOrg=OrgHelper.getOrgByDn(ldapUser.getDistinguishedName(),this.totalDbOrgs);
				if(sysOrg==null){
					continue;
				}
				try {
					sysUserOrgService.addOrgUser(new Long[]{sysUser.getUserId()}, sysOrg.getOrgId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public Date getLastSyncTime() {
		return this.lastSyncTime;
	}
	public Long getLastSyncTakeTime() {
		return this.lastSyncTakeTime;
	}
	public List<SysUser> getNewFromLdapUserList() {
		return this.newFromLdapUserList;
	}
	public List<SysUser> getDeleteLocalUserList() {
		return this.deleteLocalUserList;
	}
	public List<SysUser> getUpdateLocalUserList() {
		return this.updateLocalUserList;
	}
	public void reset() {
		this.newFromLdapUserList.clear();
		this.deleteLocalUserList.clear();
		this.updateLocalUserList.clear();
		this.lastSyncTakeTime=null;
		this.lastSyncTime=null;
	}
	
}