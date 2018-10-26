package com.hotent.redirection.service.redirection;
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
import com.hotent.redirection.model.redirection.Redirection;
import com.hotent.redirection.dao.redirection.RedirectionDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class RedirectionService extends BaseService<Redirection>
{
	@Resource
	private RedirectionDao dao;
	
	public RedirectionService()
	{
	}
	
	@Override
	protected IEntityDao<Redirection,Long> getEntityDao() 
	{
		return dao;
	}
	
	public Redirection search(Object redirection){
		return dao.search(redirection);
	}
	
	public void updateredirectionurl(Object redirection){
		dao.updateredirectionurl(redirection);
		
	}
	
	public void delByIds(String defid,String nodeid){
		dao.delByIds(defid, nodeid);
    }

	public Redirection getByactDefId(String defID, String nodeID) {
		return dao.getByactDefId(defID,nodeID);
	}

	public int getByTwo(String defid, String nodeid) {
		return dao.getByTwo(defid,nodeid);
	}
}
