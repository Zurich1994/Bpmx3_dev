
package com.hotent.ServerSoftware.dao.ServerSoftwarePac;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.ServerSoftware.model.ServerSoftwarePac.ServerSoftware;

@Repository
public class ServerSoftwareDao extends BaseDao<ServerSoftware>
{
	@Override
	public Class<?> getEntityClass()
	{
		return ServerSoftware.class;
	}

}
