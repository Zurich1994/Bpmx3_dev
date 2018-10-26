package com.hotent.ImportData.service.lc;
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
import com.hotent.ImportData.model.lc.ImportData;
import com.hotent.ImportData.dao.lc.ImportDataDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ImportDataService extends BaseService<ImportData>
{
	@Resource
	private ImportDataDao dao;
	
	public ImportDataService()
	{
	}
	
	@Override
	protected IEntityDao<ImportData,Long> getEntityDao() 
	{
		return dao;
	}
	
}
