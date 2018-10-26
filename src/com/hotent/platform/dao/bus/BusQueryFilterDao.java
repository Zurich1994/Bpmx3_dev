package com.hotent.platform.dao.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bus.BusQueryFilter;
/**
 *<pre>
 * 对象功能:查询过滤条件 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-17 11:23:06
 *</pre>
 */
@Repository
public class BusQueryFilterDao extends BaseDao<BusQueryFilter>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BusQueryFilter.class;
	}

	public List<BusQueryFilter> getMyFilterList(String tableName, Long userId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tableName", tableName);
		params.put("userId", userId);
		return this.getBySqlKey("getMyFilterList", params);
	}

	public List<BusQueryFilter> getShareFilterList(String tableName, Long userId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("tableName", tableName);
		params.put("userId", userId);
		return this.getBySqlKey("getShareFilterList", params);
	}

}