package com.hotent.e_business.service.e_business;
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
import com.hotent.e_business.model.e_business.UserInfo;
import com.hotent.e_business.model.e_business.registration;
import com.hotent.e_business.dao.e_business.registrationDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class registrationService extends BaseService<registration>
{
	@Resource
	private registrationDao dao;
	
	public registrationService()
	{
	}
	
	@Override
	protected IEntityDao<registration,Long> getEntityDao() 
	{
		return dao;
	}
	public registration getByEMAIL(String eMAIL) {
		// TODO Auto-generated method stub
		return dao.save(eMAIL);
	}
}
