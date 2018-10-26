
package com.hotent.TbaActivityInfoPath.dao.TbaActivityInfoCode;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.TbaActivityInfoPath.model.TbaActivityInfoCode.TbaActivityInfo;

@Repository
public class TbaActivityInfoDao extends BaseDao<TbaActivityInfo>
{
	@Override
	public Class<?> getEntityClass()
	{
		return TbaActivityInfo.class;
	}

}
