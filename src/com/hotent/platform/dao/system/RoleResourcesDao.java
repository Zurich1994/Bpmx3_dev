/**
 * 对象功能:角色资源映射 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-08 11:23:15
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.RoleResources;

@Repository
public class RoleResourcesDao extends BaseDao<RoleResources>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return RoleResources.class;
	}
	
	/**
	 * 根据系统id和角色id查询角色和资源的映射关系。
	 * @param systemId 系统id
	 * @param roleId	角色id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResources> getBySysAndRole(Long systemId, Long roleId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemId", systemId);
		params.put("roleId", roleId);
		return this.getBySqlKey("getBySysAndRes", params);
	}
	
	/**
	 * 根据系统和角色删除角色和资源映射关系。
	 * @param systemId
	 * @param roleId
	 */
	public void delByRoleAndSys(Long systemId, Long roleId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemId", systemId);
		params.put("roleId", roleId);
		this.delBySqlKey("delByRoleAndSys", params);
		
	}
	
	/**
	 * 根据资源id删除资源和角色的映射。
	 * @param resId
	 */
	public void delByResId(Long resId){
		this.delBySqlKey("delByResId", resId);
	}
	
	/**
	 * 根据角色id查询角色和资源的映射关系
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResources> getRoleRes(Long roleId){
		 
		return this.getBySqlKey("getRoleRes", roleId);
	}
	
	/**
	 * 根据资源id查询角色和资源的映射关系。
	 * @param resId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RoleResources> getByResId(Long resId){
		
		return this.getBySqlKey("getByResId", resId);
	}

	public void delByRoleAndSysAndRes(Long systemId, Long[] roleIds, Long[] resIds) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("systemId", systemId);
		params.put("roleIds", roleIds);
		params.put("resIds", resIds);
		this.delBySqlKey("delByRoleAndSysAndRes",params);
		
	}
}