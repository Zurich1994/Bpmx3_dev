package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysPaur;
/**
 *<pre>
 * 对象功能:SYS_PAUR Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-24 14:41:59
 *</pre>
 */
@Repository
public class SysPaurDao extends BaseDao<SysPaur>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysPaur.class;
	}
	
	/**
	 * 通过当前用户和别名获得但其皮肤
	 * @param userId
	 * @param aliasName
	 * @return
	 */
	public SysPaur getByUserAndAlias(Long userId,String aliasName){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("aliasName", aliasName);
		SysPaur obj=(SysPaur)this.getOne("getByUserAndAlias", map);
		return obj;
	}

}