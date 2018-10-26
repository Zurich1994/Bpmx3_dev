/**
 * 对象功能:SYS_JOBLOG Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-28 17:01:51
 */
package com.hotent.platform.dao.system;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysJobLog;

@Repository
public class SysJobLogDao extends BaseDao<SysJobLog>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysJobLog.class;
	}
}