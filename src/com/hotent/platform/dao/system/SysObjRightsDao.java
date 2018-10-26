package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysObjRights;
/**
 *<pre>
 * 对象功能:对象权限表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-09 17:19:22
 *</pre>
 */
@Repository
public class SysObjRightsDao extends BaseDao<SysObjRights>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysObjRights.class;
	}

	
	
	
	
}