/**
 * 对象功能:人员参数属性 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-24 10:04:50
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysUserParam;

@Repository
public class SysUserParamDao extends BaseDao<SysUserParam>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysUserParam.class;
	}
	
	
	/**
	 * 根据用户ID删除参数。
	 * @param userId
	 * @return
	 */
	public int delByUserId(long userId){
		return this.delBySqlKey("delByUserId", userId);
		
	}
	
	/**
	 * 根据用户取得参数列表。
	 * @param userId
	 * @return
	 */
	public List<SysUserParam> getByUserId(long userId){
		return this.getBySqlKey("getByUserId", userId);
	}
	
	/**
	 * 根据用户和参数key获取用户数据。
	 * @param userId
	 * @param paramKey
	 * @return
	 */
	public SysUserParam getByParaUserId(long userId,String paramKey){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("paramKey", paramKey);
		return this.getUnique("getByParaUserId", params);
	}

	/**
	 * 根据参数Key和参数Value获取用户集合
	 * @param paramKey
	 * @param paramValue
	 * @return
	 */
	public List<SysUserParam> getByParamKeyValue(String paramKey,
			Object paramValue) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("paramKey", paramKey);
		params.put("paramValue", paramValue);
		return this.getBySqlKey("getByParamKeyValue", params);
	}
	
}