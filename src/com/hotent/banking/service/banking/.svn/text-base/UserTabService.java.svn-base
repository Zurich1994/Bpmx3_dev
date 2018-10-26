package com.hotent.banking.service.banking;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.hotent.banking.dao.banking.UserTabDao;
import com.hotent.banking.model.banking.UserTab;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class UserTabService extends BaseService<UserTab>
{
	@Resource
	private UserTabDao dao;
	
	public UserTabService()
	{
	}
	
	@Override
	protected IEntityDao<UserTab,Long> getEntityDao() 
	{
		return dao;
	}
	
}
