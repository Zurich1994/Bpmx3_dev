package com.hotent.SoftWare.service.SoftWarePac;
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
import com.hotent.SoftWare.model.SoftWarePac.Software;
import com.hotent.SoftWare.dao.SoftWarePac.SoftwareDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class SoftwareService extends BaseService<Software>
{
	@Resource
	private SoftwareDao dao;
	
	public SoftwareService()
	{
	}
	
	@Override
	protected IEntityDao<Software,Long> getEntityDao() 
	{
		return dao;
	}
	
}
