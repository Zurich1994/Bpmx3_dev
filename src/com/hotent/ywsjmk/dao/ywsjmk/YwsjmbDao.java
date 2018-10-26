
package com.hotent.ywsjmk.dao.ywsjmk;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.core.db.BaseDao;

import com.hotent.ywsjmk.model.ywsjmk.Ywsjmb;

@Repository
public class YwsjmbDao extends BaseDao<Ywsjmb>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Ywsjmb.class;
	}
    public void delByFormkey(String formkey)
    {
    	delBySqlKey("delByFormkey",formkey);
    }
}