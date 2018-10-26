package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysQueryView;
/**
 *<pre>
 * 对象功能:视图定义 Dao类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Repository
public class SysQueryViewDao extends BaseDao<SysQueryView>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysQueryView.class;
	}

	
	/**
	 * 根据别名获取有权限的视图页面。
	 * @param relationMap
	 * @param sqlAlias
	 * @return
	 */
	public List<SysQueryView> getHasRights(Map<String,List<Long>> relationMap,String sqlAlias){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("relationMap", relationMap);
		params.put("objType", sqlAlias);
		
		List<SysQueryView> list=this.getBySqlKey("getHasRights", params);
		return list;
		
	}
	
	/**
	 * 根据别名获取列表视图。
	 * @param sqlAlias
	 * @param alias
	 * @return
	 */
	public SysQueryView getBySqlView(String sqlAlias,String alias){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("sqlAlias", sqlAlias);
		params.put("alias", alias);
		SysQueryView queryView=this.getUnique("getBySqlView", params);
		return queryView;
	}
	/**
	 * 根据别名获取列表视图。
	 * @param sqlAlias
	 * @param alias
	 * @return
	 */
	public List<SysQueryView> getListBySqlAlias(String sqlAlias){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("sqlAlias", sqlAlias);
		return this.getBySqlKey("getListBySqlAlias", params);
	}
	
	public void removeBySQLAlias(String sqlAlias) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sqlAlias", sqlAlias);
		this.delBySqlKey("removeBySQLAlias", params);
	}
}