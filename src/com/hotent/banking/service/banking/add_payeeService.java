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
import com.hotent.banking.model.banking.add_payee;
import com.hotent.banking.dao.banking.add_payeeDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class add_payeeService extends BaseService<add_payee>
{
	@Resource
	private add_payeeDao dao;
	
	public add_payeeService()
	{
	}
	
	@Override
	protected IEntityDao<add_payee,Long> getEntityDao() 
	{
		return dao;
	}
	public add_payee getByUSERID(String USERID){
		return dao.getByUSERID(USERID);
	}
	
}
