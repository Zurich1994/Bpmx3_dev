package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysQueryMetaField;
/**
 *<pre>
 * 对象功能:自定义SQL字段定义 Dao类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Repository
public class SysQueryMetaFieldDao extends BaseDao<SysQueryMetaField>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysQueryMetaField.class;
	}

	/**
	 * 根据sqlID获取字段元数据。
	 * @param sqlId
	 * @return
	 */
	public List<SysQueryMetaField> getListBySqlId(Long sqlId){
		List<SysQueryMetaField> list=this.getBySqlKey("getListBySqlId", sqlId);
		return list;
	}
	
	/**
	 * 根据别名获取字段列表。
	 * @param alias
	 * @return
	 */
	public List<SysQueryMetaField> getListBySqlAlias(String alias){
		List<SysQueryMetaField> list=this.getBySqlKey("getListBySqlAlias", alias);
		return list;
	}
	
	
	/**
	 * 根据SQLID删除设置字段。
	 * @param sqlId
	 */
	public void removeBySQLId(Long sqlId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sqlId", sqlId);
		this.delBySqlKey("removeBySQLId", params);
	}
	
	/**
	 * 根据视图id获取视图的列数据。
	 * @param viewId
	 * @return
	 */
	public List<SysQueryMetaField> getListByView(Long viewId,int type) {
		Map params=new HashMap();
		params.put("viewId", viewId);
		params.put("type", type);
		return  this.getBySqlKey("getListByView", params);
	}
	
	
	
}