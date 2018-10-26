package com.hotent.platform.service.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysOrgRoleManageDao;
import com.hotent.platform.model.system.SysOrgRoleManage;

/**
 * 对象功能:组织可以授权的角色范围(用于分级授权) Service类
 * 开发公司:宏天
 * 开发人员:ray
 * 创建时间:2012-11-02 15:03:27
 */
@Service
public class SysOrgRoleManageService extends BaseService<SysOrgRoleManage>
{
	@Resource
	private SysOrgRoleManageDao dao;
	
	public SysOrgRoleManageService()
	{
	}
	
	@Override
	protected IEntityDao<SysOrgRoleManage, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 添加组织可以管理的角色。
	 * 如果已经添加则跳过。
	 * @param orgId
	 * @param roleIds
	 */
	public boolean addOrgRole(Long orgId,String roleIds,Integer grade){
		
		boolean rtn=false;
		String[] aryRoles=roleIds.split(",");
		for(String sRoleId:aryRoles){
			long roleId=Long.parseLong(sRoleId);
			if(dao.isOrgRoleExists(orgId, roleId)){
				continue;
			}
			long id=UniqueIdUtil.genId();
			SysOrgRoleManage orgRole=new SysOrgRoleManage();
			orgRole.setId(id);
			orgRole.setOrgid(orgId);
			orgRole.setRoleid(roleId);
			orgRole.setCanDel(grade);
			dao.add(orgRole);
			rtn=true;
		}
		return rtn;
	}
	
	
	
}
