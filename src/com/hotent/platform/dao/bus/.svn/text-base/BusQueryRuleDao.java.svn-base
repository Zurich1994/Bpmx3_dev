package com.hotent.platform.dao.bus;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bus.BusQueryRule;
/**
 *<pre>
 * 对象功能:高级查询规则 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-09 14:19:29
 *</pre>
 */
@Repository
public class BusQueryRuleDao extends BaseDao<BusQueryRule>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BusQueryRule.class;
	}

	/**
	 * 通过表名获取定义的查询规则
	 * @param tableName 表名
	 * @return
	 */
	public BusQueryRule getByTableName(String tableName) {
		return getUnique("getByTableName",tableName);
	}

	public Integer getCountByTableName(String tableName) {
		return (Integer)this.getOne("getCountByTableName", tableName);
	}

}