/**
 * 对象功能:流程分管授权限用户中间表明细 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xucx
 * 创建时间:2014-03-05 10:10:53
 */
package com.hotent.platform.dao.bpm;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmDefUser;

@Repository
public class BpmDefUserDao extends BaseDao<BpmDefUser>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmDefUser.class;
	}
	
	/**
	 * 获取所有的授权的对象用户
	 * @param params
	 * @return
	 */
	public  List<BpmDefUser>  getUserByMap(Map<String,Object> params){
		return getBySqlKey("getAll", params); 
	}
	
	/**
	 * 根据授权ID删除流程用户子表的权限信息
	 * @param typeId
	 * @return
	 */
	public void delByAuthorizeId(Long authorizeId){
		getBySqlKey("delByAuthorizeId", authorizeId);
	}
}