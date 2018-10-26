/**
 * 对象功能:维度信息 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:pkq
 * 创建时间:2011-11-08 12:04:22
 */
package com.hotent.platform.dao.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.Demension;

@Repository
public class DemensionDao extends BaseDao<Demension>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return Demension.class;
	}
	
	
	public boolean getNotExists(Map params)
	{
		int cnt= (Integer)this.getOne("getExists", params);
		return cnt==0;
	}	
	
	/**
	 * 对象功能：根据查询条件查询维度的记录
	 * 开发公司:广州宏天软件有限公司
	 * 开发人员:pkq
     * 创建时间:2011-11-08 12:04:22 
	 */
	public List<Demension> getDemenByQuery(QueryFilter queryFilter)
	{
		return this.getBySqlKey("getDemenByQuery", queryFilter);
	}
}