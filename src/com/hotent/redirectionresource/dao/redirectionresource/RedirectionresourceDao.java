
package com.hotent.redirectionresource.dao.redirectionresource;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.redirectionresource.model.redirectionresource.Redirectionresource;

@Repository
public class RedirectionresourceDao extends BaseDao<Redirectionresource>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Redirectionresource.class;
	}

	public void addredirection(String redirectionno,String url,String alias,String resname){
	HashMap<String, Object> map =new HashMap<String, Object>();
	map.put("redirectionno", redirectionno);
	map.put("url", url);
	map.put("alias", alias);
	map.put(resname, resname);
	String Statement=getIbatisMapperNamespace() + ".add";
	getSqlSessionTemplate().insert(Statement, map);
	}
}
