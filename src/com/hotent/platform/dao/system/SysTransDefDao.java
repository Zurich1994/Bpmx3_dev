package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysTransDef;
/**
 *<pre>
 * 对象功能:sys_trans_def Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-16 11:15:55
 *</pre>
 */
@Repository
public class SysTransDefDao extends BaseDao<SysTransDef>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysTransDef.class;
	}

	
	
	
	
}