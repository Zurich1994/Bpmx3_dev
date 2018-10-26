package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.PersonScript;
/**
 *<pre>
 * 对象功能:系统条件脚本 Dao类
 * 开发公司:hotent
 * 开发人员:heyifan
 * 创建时间:2013-04-05 11:34:56
 *</pre>
 */
@Repository
public class PersonScriptDao extends BaseDao<PersonScript>
{
	@Override
	public Class<?> getEntityClass()
	{
		return PersonScript.class;
	}

	public List<PersonScript> getPersonScript(){
		return this.getBySqlKey("getPersonScript");
	}
}