package com.hotent.platform.dao.ats;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsAttenceCalculateSet;
/**
 *<pre>
 * 对象功能:考勤计算设置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-06-03 14:46:19
 *</pre>
 */
@Repository
public class AtsAttenceCalculateSetDao extends BaseDao<AtsAttenceCalculateSet>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsAttenceCalculateSet.class;
	}

	
	
	
	
}