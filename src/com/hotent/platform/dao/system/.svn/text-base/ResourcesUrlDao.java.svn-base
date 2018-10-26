/**
 * 对象功能:资源URL Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-12-05 17:00:58
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.ResourcesUrl;
import com.hotent.platform.model.system.ResourcesUrlExt;

/**
 * 资源url Dao。
 * @author ray
 *
 */
@Repository
public class ResourcesUrlDao extends BaseDao<ResourcesUrl>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return ResourcesUrl.class;
	}
	
	/**
	 * 根据资源id获取对应的URL数据。
	 * @param resId
	 * @return
	 */
	public List<ResourcesUrl> getByResId(long resId){
		return (List<ResourcesUrl>)this.getBySqlKey("getByResId", resId);
	}
	
	/**
	 * 根据资源id删除URL。
	 * @param resId
	 */
	public void delByResId(long resId){
		this.delBySqlKey("delByResId", resId);
	}
	
	/**
	 * 根据系统id获取系统资源的url和角色关联映射。
	 * @param systemId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ResourcesUrlExt> getUrlAndRoleBySystemId(long systemId){
		String statment=this.getIbatisMapperNamespace() + ".getUrlAndRoleBySystemId";
		return this.getSqlSessionTemplate().selectList(statment, systemId);
	}
	

	
	
	@SuppressWarnings("unchecked")
	public List<ResourcesUrlExt> getUrlAndRoleByUrlSystemAlias(String sysAlias,String url){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("sysAlias", sysAlias);
		params.put("url", url);
		
		String statment=this.getIbatisMapperNamespace() + ".getUrlAndRoleByUrlSystemAlias";
		return this.getSqlSessionTemplate().selectList(statment, params);
	}
	
	
}