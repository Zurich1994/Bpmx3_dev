package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysObjLog;
/**
 *<pre>
 * 对象功能:SYS_OBJ_LOG Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-27 11:09:44
 *</pre>
 */
@Repository
public class SysObjLogDao extends BaseDao<SysObjLog>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysObjLog.class;
	}

	
	
	
	
}