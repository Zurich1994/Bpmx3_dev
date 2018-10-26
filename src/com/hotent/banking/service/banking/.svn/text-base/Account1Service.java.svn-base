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
import com.hotent.banking.model.banking.Account1;
import com.hotent.banking.dao.banking.Account1Dao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.BpmNodeSet;


@Service
public class Account1Service extends BaseService<Account1>
{
	@Resource
	private Account1Dao dao;
	
	public Account1Service()
	{
	}
	
	@Override
	protected IEntityDao<Account1,Long> getEntityDao() 
	{
		return dao;
	}
	public List<Account1> getUSERID(String USERID) {
		return dao.getUSERID(USERID);
	}
}
