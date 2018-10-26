
package com.hotent.Virtual.dao.VirtualPac;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.Virtual.model.VirtualPac.DeviceVirtual;

@Repository
public class DeviceVirtualDao extends BaseDao<DeviceVirtual>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DeviceVirtual.class;
	}

}
