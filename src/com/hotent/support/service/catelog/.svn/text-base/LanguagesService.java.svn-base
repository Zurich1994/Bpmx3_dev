package com.hotent.support.service.catelog;
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
import com.hotent.support.model.catelog.Languages;
import com.hotent.support.dao.catelog.LanguagesDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class LanguagesService extends BaseService<Languages>
{
	@Resource
	private LanguagesDao dao;
	
	public LanguagesService()
	{
	}
	
	@Override
	protected IEntityDao<Languages,Long> getEntityDao() 
	{
		return dao;
	}
	
}
