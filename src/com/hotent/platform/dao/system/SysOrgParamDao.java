/**
 * 对象功能:组织参数属性 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-24 10:04:50
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.fr.base.Inter;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysOrgParam;

@Repository
public class SysOrgParamDao extends BaseDao<SysOrgParam>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysOrgParam.class;
	}
	
	/**
	 * 根据组织id删除组织关联的参数。
	 * @param orgId
	 * @return
	 */
	public int delByOrgId(long orgId){
		return this.delBySqlKey("delByOrgId", orgId);
	}
	@SuppressWarnings("unchecked")
	public List<SysOrgParam> getByOrgId(Long orgId) {
		return this.getBySqlKey("getByOrgId", orgId);
	}
	
	/**
	 * 
	 * @param paramKey
	 * @param orgId
	 * @return
	 */
	public SysOrgParam getByParamKeyAndOrgId(String paramKey, Long orgId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("paramKey", paramKey);
		params.put("orgId", orgId);
		return this.getUnique("getByParamKeyAndOrgId", params);
	}

}