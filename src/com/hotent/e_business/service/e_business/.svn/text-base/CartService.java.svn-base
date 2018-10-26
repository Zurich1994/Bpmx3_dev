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
import com.hotent.e_business.model.e_business.Cart;
import com.hotent.e_business.dao.e_business.CartDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class CartService extends BaseService<Cart>
{
	@Resource
	private CartDao dao;
	
	public CartService()
	{
	}
	
	@Override
	protected IEntityDao<Cart,Long> getEntityDao() 
	{
		return dao;
	}
	
}
