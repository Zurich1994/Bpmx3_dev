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
import com.hotent.banking.model.banking.Checkyan;
import com.hotent.banking.dao.banking.CheckyanDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class CheckyanService extends BaseService<Checkyan>
{
	@Resource
	private CheckyanDao dao;
	
	public CheckyanService()
	{
	}
	
	@Override
	protected IEntityDao<Checkyan,Long> getEntityDao() 
	{
		return dao;
	}
	
}
