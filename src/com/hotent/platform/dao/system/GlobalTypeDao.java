/**
 * 对象功能:总分类表
 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ljf
 * 创建时间:2011-11-23 11:07:27
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.GlobalType;

@Repository
public class GlobalTypeDao extends BaseDao<GlobalType> {
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return GlobalType.class;
	}



	/**
	 * 根据路径取子结点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<GlobalType> getByNodePath(String nodePath) {
		Map<String, String> params=new HashMap<String, String>();
		params.put("nodePath", nodePath+"%");
		return this.getBySqlKey("getByNodePath", params);
	}



	
	/**
	 * 根据路径取子结点
	 * @param parentId
	 * @return
	 */
	public List<GlobalType> getByParentId(long parentId) {
		return this.getBySqlKey("getByParentId", parentId);
	
	}
	
	/**
	 * 判断nodekey在某分类中是否存在。
	 * @param catKey
	 * @param nodeKey
	 * @return
	 */
	public boolean isNodeKeyExists(String catKey,String nodeKey)
	{
		Map<String,String> params=new HashMap<String, String>();
		params.put("catkey", catKey);
		params.put("nodeKey", nodeKey);
		int rtn= (Integer)this.getOne("isNodeKeyExists", params);
		return rtn>0;
	}	
	
	/**
	 * 判断nodekey 在该大类下是否存在。
	 * @param typeId
	 * @param catKey
	 * @param nodeKey
	 * @return
	 */
	public boolean isNodeKeyExistsForUpdate(Long typeId,String catKey,String nodeKey){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("typeId", typeId);
		params.put("catkey", catKey);
		params.put("nodeKey", nodeKey);
		int rtn= (Integer)this.getOne("isNodeKeyExistsForUpdate", params);
		return rtn>0;
	}
	
	/**
	 * 更新在分类中的排序数据。
	 * @param typeId
	 * @param sn
	 */
	public void updSn(Long typeId,Long sn){
		GlobalType globalType=new GlobalType();
		globalType.setTypeId(typeId);
		globalType.setSn(sn);
		this.update("updSn", globalType);
	}
	
	/**
	 * 根据catkey获取数据。
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByCatKey(String catKey){
		
		return this.getBySqlKey("getByCatKey", catKey);
	}
	
	public List<GlobalType> getByCatKey1(String catKey){
	  	 
		return this.getBySqlKey("getByCatKey1", catKey);
	}
	/**
	 * 根据nodekey获取字典的分类类型。
	 * @param nodeKey 分类表的nodekey。
	 * @return
	 */
	public GlobalType getByDictNodeKey(String nodeKey){
		GlobalType globalType=   this.getUnique("getByDictNodeKey", nodeKey);
		return globalType;
	}
	
	/**
	 * 根据nodekey获取字典的分类类型。
	 * @param nodeKey 分类表的nodekey。
	 * @return
	 */
	public GlobalType getByDictNodeKey1(String nodeKey){
		GlobalType globalType=   this.getUnique("getByDictNodeKey1", nodeKey);
		return globalType;
	}
	
	/**
	 * 根据nodekey获取字典的分类类型。
	 * @param nodeKey 分类表的nodekey。
	 * @return
	 */
	public GlobalType getByCateKeyAndNodeKey(String catKey,String nodeKey){
		Map params=new HashMap();
		params.put("catKey", catKey);
		params.put("nodeKey", nodeKey);
		GlobalType globalType=  this.getUnique("getByCateKeyAndNodeKey", params);
		return globalType;
	}
	
	/**

	 * 取得个人的分类类型。
	 * @param catKey	分类ID
	 * @param userId	用户ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<GlobalType> getPersonType(String catKey,Long userId){
		@SuppressWarnings("rawtypes")
		Map params=new Hashtable();
		params.put("catkey", catKey);
		params.put("userId", userId);
		List<GlobalType> list=this.getBySqlKey("getPersonType", params);
		return list;
	}
	
	/**
	 * 按用户权限取得分类列表
	 * @param catKey 分类Key
	 * @param userId 用户ID
	 * @param roleIds 角色IDS 格式如 1,2
	 * @param orgIds  组织IDs 格式如 1,2
	 * @return
	 */
	public List<GlobalType> getByBpmRights(String catKey,Long userId,String roleIds,String orgIds){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("ownerId", userId);
		params.put("catKey", catKey);
		if(StringUtils.isNotEmpty(roleIds)){
			params.put("roleIds", roleIds);
		}
		if(StringUtils.isNotEmpty(orgIds)){
		params.put("orgIds",orgIds);
		}
		return getBySqlKey("getByBpmRights",params);
	}
	/**
	 * 按用户取得表单分类列表
	 * @param catKey 分类key
	 * @param userId 用户ID
	 * @param roleIds 角色IDS 格式如 1,2
	 * @param orgIds 组织IDs 格式如 1,2
	 * @return
	 */
	public List<GlobalType> getByFormRights(String catKey,Long userId,String roleIds,String orgIds){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("ownerId", userId);
		params.put("catKey", catKey);
		if(StringUtils.isNotEmpty(roleIds)){
			params.put("roleIds", roleIds);
		}
		if(StringUtils.isNotEmpty(orgIds)){
		params.put("orgIds",orgIds);
		}
		return getBySqlKey("getByFormRights",params);
	}
	
}