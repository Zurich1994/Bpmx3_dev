package com.hotent.projectinfo.service.projectinfo;
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
import com.hotent.projectinfo.model.projectinfo.projectinfo;
import com.hotent.projectinfo.dao.projectinfo.projectinfoDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class projectinfoService extends BaseService<projectinfo>
{
	@Resource
	private projectinfoDao dao;
	
	public projectinfoService()
	{
	}
	
	@Override
	protected IEntityDao<projectinfo,Long> getEntityDao() 
	{
		return dao;
	}
	
}
