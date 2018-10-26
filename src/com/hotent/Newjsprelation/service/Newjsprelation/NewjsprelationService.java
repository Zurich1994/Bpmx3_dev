package com.hotent.Newjsprelation.service.Newjsprelation;
import java.util.ArrayList;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;


import com.hotent.core.service.BaseService;

import com.hotent.Newjsprelation.model.Newjsprelation.Newjsprelation;
import com.hotent.Newjsprelation.dao.Newjsprelation.NewjsprelationDao;



@Service
public class NewjsprelationService extends BaseService<Newjsprelation>
{
	@Resource
	private NewjsprelationDao dao;
	
	public NewjsprelationService()
	{
	}
	
	@Override
	protected IEntityDao<Newjsprelation,Long> getEntityDao() 
	{
		return dao;
	}
	public String  getFormDefIdByFormDefId(long formidl) 
	{
		  return dao.getFormDefIdByFormDefId(formidl);
			
	}
	
	public void updatenameandvalueByFormDefId(Long formDefId,ArrayList<String> names ,ArrayList<String> values)
	{
		dao.updatenameandvalueByFormDefId(formDefId, names, values);
	}
}
