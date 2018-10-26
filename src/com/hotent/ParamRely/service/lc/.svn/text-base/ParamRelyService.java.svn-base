package com.hotent.ParamRely.service.lc;
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
import com.hotent.ParamRely.model.lc.ParamRely;
import com.hotent.ParamRely.dao.lc.ParamRelyDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ParamRelyService extends BaseService<ParamRely>
{
	@Resource
	private ParamRelyDao dao;
	
	public ParamRelyService()
	{
	}
	
	@Override
	protected IEntityDao<ParamRely,Long> getEntityDao() 
	{
		return dao;
	}
	
}
