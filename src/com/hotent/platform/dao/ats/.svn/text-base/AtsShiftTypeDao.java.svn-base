package com.hotent.platform.dao.ats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsShiftType;
/**
 *<pre>
 * 对象功能:班次类型 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-16 21:44:00
 *</pre>
 */
@Repository
public class AtsShiftTypeDao extends BaseDao<AtsShiftType>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsShiftType.class;
	}

	public List<AtsShiftType> getListByStatus(Short status) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("status", status);
		return this.getBySqlKey("getListByStatus", params);
	}

	
	
	
	
}