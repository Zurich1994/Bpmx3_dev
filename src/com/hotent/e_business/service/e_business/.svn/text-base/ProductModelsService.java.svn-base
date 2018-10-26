package com.hotent.e_business.service.e_business;
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
import com.hotent.e_business.model.e_business.ProductModels;
import com.hotent.e_business.dao.e_business.ProductModelsDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ProductModelsService extends BaseService<ProductModels>
{
	@Resource
	private ProductModelsDao dao;
	
	public ProductModelsService()
	{
	}
	
	@Override
	protected IEntityDao<ProductModels,Long> getEntityDao() 
	{
		return dao;
	}
public List<ProductModels> getProductModels(String Q_REGION_S,String Q_KEYWORDS_S,String Q_CATEGORY_S){
		
		return dao.getProductModels(Q_REGION_S,Q_KEYWORDS_S,Q_CATEGORY_S);
	}
public List<ProductModels> getProductModelsall(String Q_REGION_S,String Q_KEYWORDS_S,String Q_CATEGORY_S){
	
	return dao.getProductModelsall(Q_REGION_S,Q_KEYWORDS_S,Q_CATEGORY_S);
}
}
