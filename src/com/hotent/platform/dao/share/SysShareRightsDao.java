
package com.hotent.platform.dao.share;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.share.SysShareRights;

@Repository
public class SysShareRightsDao extends BaseDao<SysShareRights>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysShareRights.class;
	}

}