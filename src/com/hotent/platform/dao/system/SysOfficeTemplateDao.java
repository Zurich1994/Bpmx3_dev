/**
 * 对象功能:office模版 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-05-25 10:16:16
 */
package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.SysOfficeTemplate;

@Repository
public class SysOfficeTemplateDao extends BaseDao<SysOfficeTemplate>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysOfficeTemplate.class;
	}

	public List<SysOfficeTemplate> getOfficeTemplateByUserId(QueryFilter params) {
		
		return this.getBySqlKey("getOfficeTemplateByUserId", params);
	}
}