package com.hotent.nodetimeandcount.service.nodetimeandcount;
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
import com.hotent.nodetimeandcount.model.nodetimeandcount.Nodetimeandcount;
import com.hotent.nodetimeandcount.dao.nodetimeandcount.NodetimeandcountDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class NodetimeandcountService extends BaseService<Nodetimeandcount>
{
	@Resource
	private NodetimeandcountDao dao;
	
	public NodetimeandcountService()
	{
	}
	
	@Override
	protected IEntityDao<Nodetimeandcount,Long> getEntityDao() 
	{
		return dao;
	}
	
}
