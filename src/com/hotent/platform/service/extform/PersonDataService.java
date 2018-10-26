package com.hotent.platform.service.extform;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.extform.PersonData;
import com.hotent.platform.dao.extform.PersonDataDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class PersonDataService extends BaseService<PersonData>
{
	@Resource
	private PersonDataDao dao;
	
	public PersonDataService()
	{
	}
	
	@Override
	protected IEntityDao<PersonData,Long> getEntityDao() 
	{
		return dao;
	}
	
}
