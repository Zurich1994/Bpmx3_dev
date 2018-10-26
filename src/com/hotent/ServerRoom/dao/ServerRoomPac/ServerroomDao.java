
package com.hotent.ServerRoom.dao.ServerRoomPac;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.ServerRoom.model.ServerRoomPac.Serverroom;

@Repository
public class ServerroomDao extends BaseDao<Serverroom>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Serverroom.class;
	}

}
