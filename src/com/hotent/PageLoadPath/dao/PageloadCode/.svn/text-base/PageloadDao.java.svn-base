
package com.hotent.PageLoadPath.dao.PageloadCode;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.PageLoadPath.model.PageloadCode.Pageload;

@Repository
public class PageloadDao extends BaseDao<Pageload>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Pageload.class;
	}
	public void delByIds(String defid,String nodeid){
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("defid", defid);
		map.put("nodeid", nodeid);
		getUnique("delByIds", map);
    }
}
