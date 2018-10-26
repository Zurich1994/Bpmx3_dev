package com.hotent.platform.service.system;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.OrgAuthDao;
import com.hotent.platform.model.system.AuthRole;
import com.hotent.platform.model.system.OrgAuth;

/**
 *<pre>
 * 对象功能:SYS_ORG_AUTH Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:miao
 * 创建时间:2014-08-08 10:19:21
 *</pre>
 */
@Service
public class OrgAuthService extends BaseService<OrgAuth>
{
	@Resource
	private OrgAuthDao dao;
	@Resource
	private AuthRoleService authRoleService;
	
	
	public OrgAuthService()
	{
	}
	
	@Override
	protected IEntityDao<OrgAuth, Long> getEntityDao() 
	{
		return dao;
	}

	public void add(OrgAuth orgAuth, Long[] roleIds) {
		checkOrgAuthIsExist(orgAuth.getUserId(), orgAuth.getOrgId());
		this.add(orgAuth);
		saveAuthRoles(roleIds,orgAuth.getId());
		
	}
	private void checkOrgAuthIsExist(Long userId, Long orgId) {
		if (dao.checkOrgAuthIsExist(userId,orgId)) {
			throw new RuntimeException("当前组织的分级管理员已经存在，请勿重复添加！");
		}
	}
	/**
	 * 先删除后新增
	 * @param roleIds
	 * @param authId
	 */
	private void saveAuthRoles(Long[] roleIds, Long authId) {
		authRoleService.delByAuthId(authId);
		if(BeanUtils.isEmpty(roleIds)) return;
		
		for(long roleId : roleIds){
			authRoleService.add(new AuthRole(UniqueIdUtil.genId(),authId,roleId)); 
		}
	}

	public void update(OrgAuth orgAuth, Long[] roleIds) {
		this.update(orgAuth);
		saveAuthRoles(roleIds,orgAuth.getId());
	}
	
	@Override
	public void delById(Long id) {
		authRoleService.delByAuthId(id);
		super.delById(id);
	}
	/**
	 * 通过用户获取所有授权的组
	 */
	public List<OrgAuth> getByUserId(long userId) {
		List<OrgAuth> groupAuthList = dao.getByUserId(userId);
		List<OrgAuth> authList = new ArrayList<OrgAuth>();
		
		for(OrgAuth auth : groupAuthList){
			boolean isChild = false;
			for(OrgAuth groupAuth : groupAuthList){
				if((auth.getId()!= groupAuth.getId()) && auth.getDimId().equals(groupAuth.getDimId()) && auth.getOrgPath().startsWith(groupAuth.getOrgPath()))
					isChild = true;
			}
			if(!isChild)authList.add(auth); 
		}
		
		return authList;
	}
	/**
	 * 通过用户，和组织id 获取他的权限
	 * @param orgId
	 * @param userId
	 * @return
	 */
	public OrgAuth getUserIdDimId(Long dimId, Long userId) {
		return dao.getUserIdDimId(dimId,userId);
	}
	public boolean checkIsExist(Long userId, Long orgId) {
		return dao.checkOrgAuthIsExist(userId, orgId);
	}


	public OrgAuth getByUserIdAndOrgId(long userId, long orgId) {
		return dao.getByUserIdAndOrgId(userId,orgId);
	}
	
}
