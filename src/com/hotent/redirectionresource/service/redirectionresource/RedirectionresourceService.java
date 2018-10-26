package com.hotent.redirectionresource.service.redirectionresource;
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
import com.hotent.redirectionresource.model.redirectionresource.Redirectionresource;
import com.hotent.redirectionresource.dao.redirectionresource.RedirectionresourceDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class RedirectionresourceService extends BaseService<Redirectionresource>
{
	@Resource
	private RedirectionresourceDao dao;
	
	public RedirectionresourceService()
	{
	}
	
	@Override
	protected IEntityDao<Redirectionresource,Long> getEntityDao() 
	{
		return dao;
	}
	
	public void addredirection(String redirectionno,String url,String alias,String resname){
		dao.addredirection(redirectionno,url,alias,resname);
		
	}
	
}
