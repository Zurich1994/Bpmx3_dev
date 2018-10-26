package com.hotent.platform.dao.bus;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bus.BusQuerySetting;
/**
 *<pre>
 * 对象功能:查询设置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-01-20 15:31:03
 *</pre>
 */
@Repository
public class BusQuerySettingDao extends BaseDao<BusQuerySetting>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BusQuerySetting.class;
	}

	public BusQuerySetting getByTableNameUserId(String tableName, Long userId) {
		Map<String,Object> params =  new HashMap<String,Object>();
		params.put("tableName", tableName);
		params.put("userId", userId);
		return (BusQuerySetting) this.getOne("getByTableNameUserId", params);
	}

}