package com.hotent.BusinessCollectCot.service.lc;
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
import com.hotent.BusinessCollectCot.model.lc.BusinessCollectCot;
import com.hotent.BusinessCollectCot.dao.lc.BusinessCollectCotDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class BusinessCollectCotService extends BaseService<BusinessCollectCot>
{
	@Resource
	private BusinessCollectCotDao dao;
	
	public BusinessCollectCotService()
	{
	}
	
	@Override
	protected IEntityDao<BusinessCollectCot,Long> getEntityDao() 
	{
		return dao;
	}
	
}
