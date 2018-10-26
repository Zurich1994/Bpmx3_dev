package com.hotent.Xlkcs.service.Xlkcs;
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
import com.hotent.Xlkcs.model.Xlkcs.Xlkcs;
import com.hotent.Xlkcs.dao.Xlkcs.XlkcsDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class XlkcsService extends BaseService<Xlkcs>
{
	@Resource
	private XlkcsDao dao;
	
	public XlkcsService()
	{
	}
	
	@Override
	protected IEntityDao<Xlkcs,Long> getEntityDao() 
	{
		return dao;
	}
	
}
