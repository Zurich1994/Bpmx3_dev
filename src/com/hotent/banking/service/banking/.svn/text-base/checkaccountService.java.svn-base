package com.hotent.banking.service.banking;
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
import com.hotent.banking.model.banking.checkaccount;
import com.hotent.banking.dao.banking.checkaccountDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class checkaccountService extends BaseService<checkaccount>
{
	@Resource
	private checkaccountDao dao;
	
	public checkaccountService()
	{
	}
	
	@Override
	protected IEntityDao<checkaccount,Long> getEntityDao() 
	{
		return dao;
	}
	
}
