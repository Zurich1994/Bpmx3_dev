/**
 * 对象功能:子系统资源 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-05 17:00:54
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.Resources;
import com.hotent.platform.model.system.ResourcesUrlExt;

@Repository
public class ResourcesDao extends BaseDao<Resources>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return Resources.class;
	}
	
	/**
	 * 根据父id获取资源数据。
	 * @param parentId
	 * @return
	 */
	public List<Resources> getByParentId(long parentId){
		return this.getBySqlKey("getByParentId", parentId);
	}
	
	
	/**
	 * 根据系统id获取所有的资源列表。
	 * @param systemId
	 * @return
	 */
	public List<Resources> getBySystemId(long systemId){
		return this.getBySqlKey("getBySystemId", systemId);
	}
	
	
	/**
	 * 根据系统id和当前用户id获取资源菜单。
	 * @param systemId
	 * @param userId
	 * @return
	 */
	public List<Resources> getNormMenu(Long systemId,Long userId){
		Map<String, Long> p=new HashMap<String, Long>();
		p.put("systemId", systemId);
		p.put("userId", userId);
		return this.getBySqlKey("getNormMenu", p);
	}
	
	/**
	 * 根据系统id和当前用户角色获取资源菜单
	 * @param systemId
	 * @param roles
	 * @return
	 */
	public List<Resources> getNormMenuByRole(long systemId,Long userId ){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("systemId", systemId);
		p.put("userId", userId);
		return this.getBySqlKey("getNormMenuByRole", p);
	}
	
	/**
	 * 根据系统id和当前用户角色(自己和所属于部门的所有角色)
	 * @param systemId
	 * @param roles
	 * @return
	 */
	public List<Resources> getNormMenuByAllRole(long systemId,String rolealias ){
		Map<String, Object> p=new HashMap<String, Object>();
		p.put("systemId", systemId);
		p.put("rolealias", rolealias);
		return this.getBySqlKey("getNormMenuByAllRole", p);
	}
	
	/**
	 * 根据系统id获取全部的菜单。
	 * @param systemId
	 * @return
	 */
	public List<Resources> getSuperMenu(Long systemId){
		return this.getBySqlKey("getSuperMenu", systemId);
	}
	
	/**
	 * 根据系统id获取资源的默认URL和角色映射对象列表。
	 * @param systemId	系统id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResourcesUrlExt> getDefaultUrlAndRoleBySystemId(long systemId){
		String stament=this.getIbatisMapperNamespace() + ".getDefaultUrlAndRoleBySystemId";
		return this.getSqlSessionTemplate().selectList(stament, systemId);
	}
	
	/**
	 * 根据系统id获取功能和角色的映射。
	 * @param systemId		系统ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResourcesUrlExt> getFunctionAndRoleBySystemId(long systemId){
		String stament=this.getIbatisMapperNamespace() + ".getFunctionAndRoleBySystemId";
		return this.getSqlSessionTemplate().selectList(stament, systemId);
	}
	

	
	/**
	 * 根据子系统的别名获取系统功能、角色映射列表
	 * @param alias
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public List<ResourcesUrlExt> getSubSystemsFuncByAlias(String alias){
//		String stament=this.getIbatisMapperNamespace() + ".getSubSystemsFuncByAlias";
//		return this.getSqlSessionTemplate().selectList(stament, alias);
//	}
	

	/**
	 * 根据子系统别名获取系统资源URL和角色的映射列表
	 * @param alias 子系统别名
	 * @return
	 */
//	@SuppressWarnings("unchecked")
//	public List<ResourcesUrlExt> getSubSystemResByAlias(String alias){
//		String stament=this.getIbatisMapperNamespace() + ".getSubSystemResByAlias";
//		return this.getSqlSessionTemplate().selectList(stament, alias);
//	}
	
	/**
	 * 判断别名在该系统中是否存在。
	 * @param systemId	系统id
	 * @param alias		系统别名
	 * @return
	 */
	public Integer isAliasExists(Long systemId,String alias){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("alias", alias);
		params.put("systemId", systemId);
		return (Integer)this.getOne("isAliasExists", params);
	}
	
	
	/**
	 * 判断别名是否存在。
	 * @param systemId
	 * @param resId
	 * @param alias
	 * @return
	 */
	public Integer isAliasExistsForUpd(Long systemId,Long resId, String alias){
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("alias", alias);
		params.put("systemId", systemId);
		params.put("resId", resId);
		return (Integer)this.getOne("isAliasExistsForUpd", params);
	}
	
	/**
	 * 根据路径取得资源实体列表
	 * @param url
	 * @return
	 */
	public List<Resources> getByUrl(String url) {
		return this.getBySqlKey("getByUrl", url);
	}

	public List<Resources> getBySystemIdAndParentId(long systemId, long parentId) {
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("parentId", parentId);
		params.put("systemId", systemId);
		return this.getBySqlKey("getBySystemIdAndParentId", params);
	}
	

	public void updSn(Long resId, long sn) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("resId", resId);
		map.put("sn", sn);
		this.update("updSn", map);
	}
	
	/**
	 * 根据系统别名和url获取url和角色的映射。
	 * @param systemAlias
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResourcesUrlExt> getDefaultUrlAndRoleByUrlSystemAlias(String systemAlias,String url){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("sysAlias", systemAlias);
		params.put("url", url);
		String stament=this.getIbatisMapperNamespace() + ".getDefaultUrlAndRoleByUrlSystemAlias";
		return this.getSqlSessionTemplate().selectList(stament, params);
	}
	/**
	 * 根据系统id获取功能和角色的映射。
	 * @param systemId		系统ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResourcesUrlExt> getFunctionAndRoleBySystemAlias(String sysAlias,String func){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("sysAlias", sysAlias);
		map.put("func", func);
		String stament=this.getIbatisMapperNamespace() + ".getFunctionAndRoleBySystemAlias";
		return this.getSqlSessionTemplate().selectList(stament, map);
	}
	
	
	/**
	 * 根据系统Id和别名获取资源。
	 * @param systemId
	 * @param alias
	 * @return
	 */
	public Resources getByAlias(Long systemId,String alias){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("systemId",systemId);
		map.put("alias",alias);
		
		
		return this.getUnique("getByAlias", map);
	}
	
	
	/**
	 * 根据父id和用户id获取下级tab资源。
	 * @param resId
	 * @param userId
	 * @return
	 */
	public List<Resources> getByParentUserId(Long resId,Long userId){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("resId",resId);
		map.put("userId",userId);
		
		return this.getBySqlKey("getByParentUserId", map);
	}
	
	
	
	

}