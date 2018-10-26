package com.hotent.ServerRoom.service.ServerRoomPac;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.ServerRoom.model.ServerRoomPac.Serverroom;
import com.hotent.ServerRoom.dao.ServerRoomPac.ServerroomDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ServerroomService extends BaseService<Serverroom>
{
	@Resource
	private ServerroomDao dao;
	
	public ServerroomService()
	{
	}
	
	@Override
	protected IEntityDao<Serverroom,Long> getEntityDao() 
	{
		return dao;
	}
	
}
