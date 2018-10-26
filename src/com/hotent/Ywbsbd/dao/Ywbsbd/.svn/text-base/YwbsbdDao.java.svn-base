
package com.hotent.Ywbsbd.dao.Ywbsbd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.Ywbsbd.model.Ywbsbd.Ywbsbd;

@Repository
public class YwbsbdDao extends BaseDao<Ywbsbd>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Ywbsbd.class;
	}

	public boolean exist(String defId, String nodeId, String service,
			String systemId) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("defId", defId);
		map.put("nodeId", nodeId);
		map.put("service", service);
		map.put("systemId", systemId);
		Object temp=null;
		try{
			temp=getOne("exist",map);
		}
		catch(Exception e){
			System.out.println(e.getMessage()+e);
			System.out.println("temp:"+temp);
		}
		if(temp!=null)
			return true;
		
		return false;
	}

	public List<Ywbsbd> getBySystemId(String systemId) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("systemId", systemId);
		 List<Ywbsbd> list=getBySqlKey("getBySystemId",map);
		return list;
	}

}