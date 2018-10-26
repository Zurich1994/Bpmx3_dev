package com.hotent.platform.dao.system;

import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Repository;



import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.AliasScript;
/**
 *<pre>
 * 对象功能:自定义别名脚本表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 *</pre>
 */
@Repository
public class AliasScriptDao extends BaseDao<AliasScript>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AliasScript.class;
	}
	
	public AliasScript getAliasScriptByName(Long userId, Map map){		
		AliasScript as= this.getUnique("getByName", map);
		return as;
		
	}
	
	public List<AliasScript> getByReturnValue(Short returnValue){
		return this.getBySqlKey("getByReturnValue", returnValue);
	}


}