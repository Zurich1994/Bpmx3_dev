package com.hotent.dataservice.service.formupdate;
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
import com.hotent.dataservice.model.formupdate.FormUpdate;
import com.hotent.dataservice.dao.formupdate.FormUpdateDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class FormUpdateService extends BaseService<FormUpdate>
{
	@Resource
	private FormUpdateDao dao;
	
	public FormUpdateService()
	{
	}
	
	@Override
	protected IEntityDao<FormUpdate,Long> getEntityDao() 
	{
		return dao;
	}
	
}
