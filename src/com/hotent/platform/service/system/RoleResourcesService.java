package com.hotent.platform.service.system;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.RoleResourcesDao;
import com.hotent.platform.model.system.RoleResources;

/**
 * 对象功能:角色资源映射 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-08 11:23:15
 */
@Service
public class RoleResourcesService extends BaseService<RoleResources>
{
	@Resource
	private RoleResourcesDao roleResourcesDao;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SubSystemService subSystemService;
	
	
	public RoleResourcesService()
	{
	}
	
	@Override
	protected IEntityDao<RoleResources, Long> getEntityDao() 
	{
		return roleResourcesDao;
	}
	
	/**
	 * 添加系统和角色和资源的映射关系。
	 * <pre>
	 * 	1.根据系统id和角色id删除资源映射关系。
	 * 	2.根据角色和系统删除角色和系统的映射关系。
	 * 	3.添加角色和系统的映射关系。
	 * 	4.添加角色和资源的映射关系。
	 * </pre>
	 * @param systemId	系统ID
	 * @param roleId	角色id
	 * @param resIds	资源id数组。
	 * @throws Exception
	 */
	public void update( Long systemId, Long roleId,Long[] resIds) throws Exception{
		//删除
		roleResourcesDao.delByRoleAndSys(systemId, roleId);
		//添加角色和资源的映射。
		if(systemId>0&&roleId>0&&resIds!=null&&resIds.length>0){
			for(long resId:resIds){
				RoleResources rores=new RoleResources();
				rores.setRoleResId(UniqueIdUtil.genId());
				rores.setResId(resId);
				rores.setSystemId(systemId);
				rores.setRoleId(roleId);
				add(rores);
			}
		}
	
	}

	public void saveBatch(Long systemId, Long[] roleIds, Long[] resIds) {
		//删除
		roleResourcesDao.delByRoleAndSysAndRes(systemId, roleIds,resIds);
		//添加角色和资源的映射。
		if(systemId>0 && roleIds!=null && roleIds.length>0 && resIds!=null && resIds.length>0){
			
			for(long resId:resIds){
				for (long roleId:roleIds) {
					RoleResources rores=new RoleResources();
					rores.setRoleResId(UniqueIdUtil.genId());
					rores.setResId(resId);
					rores.setSystemId(systemId);
					rores.setRoleId(roleId);
					add(rores);
				}
			}
		}
		
	}
}
