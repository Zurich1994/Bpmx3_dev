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
import com.hotent.banking.model.banking.Payee;
import com.hotent.banking.dao.banking.PayeeDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class PayeeService extends BaseService<Payee>
{
	@Resource
	private PayeeDao dao;
	
	public PayeeService()
	{
	}
	
	@Override
	protected IEntityDao<Payee,Long> getEntityDao() 
	{
		return dao;
	}
	public Payee getUSERID(String USERID)
	{
			return dao.getUSERID(USERID);
	}
}
	

