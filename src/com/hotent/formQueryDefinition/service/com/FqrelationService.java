package com.hotent.formQueryDefinition.service.com;


import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;


import com.hotent.core.service.BaseService;

import com.hotent.formQueryDefinition.model.com.Fqrelation;
import com.hotent.formQueryDefinition.dao.com.FqrelationDao;



@Service
public class FqrelationService extends BaseService<Fqrelation>
{
	@Resource
	private FqrelationDao dao;
	
	public FqrelationService()
	{
	}
	
	@Override
	protected IEntityDao<Fqrelation,Long> getEntityDao() 
	{
		return dao;
	}
	

	public List<Fqrelation> getQueryMsg(String nodeId,String defId)
	{
		return  dao.getQueryMsg(nodeId, defId);
	}
    public void delByfqId(Long fqId){
    	dao.delByfqId(fqId);
    }
}
