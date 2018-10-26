package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysQueryFieldSetting;
/**
 *<pre>
 * 对象功能:视图自定义设定 Dao类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Repository
public class SysQueryFieldSettingDao extends BaseDao<SysQueryFieldSetting>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysQueryFieldSetting.class;
	}

	public void removeBySysQueryViewId(Long viewId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("viewId", viewId);
		this.delBySqlKey("removeBySysQueryViewId", params);
	}

	public List<SysQueryFieldSetting> getBySysQueryViewId(Long viewId) {
		
		return this.getBySqlKey("getBySysQueryViewId", viewId);	
	}

	
	
	
	
}