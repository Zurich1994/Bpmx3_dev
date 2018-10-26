/**
 * 对象功能:电子印章 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:raise
 * 创建时间:2012-08-29 11:26:00
 */
package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.Seal;

@Repository
public class SealDao extends BaseDao<Seal>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return Seal.class;
	}
	
	/**
	 * 
	 * 查询自己相关的印章列表
	 * 
	 * */
	public List<Seal> getSealByUserId (Object params){
		return this.getBySqlKey("getSealByUserId", params);
	};
}