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
import com.hotent.e_business.model.e_business.ProductModel;
import com.hotent.e_business.dao.e_business.ProductModelDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class ProductModelService extends BaseService<ProductModel>
{
	@Resource
	private ProductModelDao dao;
	
	public ProductModelService()
	{
	}
	
	@Override
	protected IEntityDao<ProductModel,Long> getEntityDao() 
	{
		return dao;
	}
	
}
