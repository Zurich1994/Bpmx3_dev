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
import com.hotent.support.model.catelog.FileCatalog;
import com.hotent.support.dao.catelog.FileCatalogDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class FileCatalogService extends BaseService<FileCatalog>
{
	@Resource
	private FileCatalogDao dao;
	
	public FileCatalogService()
	{
	}
	
	@Override
	protected IEntityDao<FileCatalog,Long> getEntityDao() 
	{
		return dao;
	}
	public List<FileCatalog> getByIdFromFilecatalog(Long productid){
		return dao.getByIdFromFilecatalog(productid);
	}	
	public List<FileCatalog> getCatalog(String productid){
		return dao.getCatalog(productid);
	}
}
