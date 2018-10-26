package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.SysIndexLayoutManage;

/**
 * <pre>
 * 对象功能:布局管理 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 15:40:13
 * </pre>
 */
@SuppressWarnings("unchecked")
@Repository
public class SysIndexLayoutManageDao extends BaseDao<SysIndexLayoutManage> {
	@Override
	public Class<?> getEntityClass() {
		return SysIndexLayoutManage.class;
	}

	/**
	 * 获取管理的布局
	 * 
	 * @param orgIds
	 * @return
	 */
	public List<SysIndexLayoutManage> getManageLayout(String orgIds, Short isDef) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgIds", orgIds);
		params.put("isDef", isDef);
		if (BeanUtils.isEmpty(orgIds))
			params.put("isNullOrg", true);

		return this.getBySqlKey("getManageLayout", params);
	}

	public List<SysIndexLayoutManage> getByUserIdFilter(QueryFilter filter) {
		return getBySqlKey("getByUserIdFilter",filter);
	}

	public void updateIsDef(Long orgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orgId", orgId);
		this.update("updateIsDef", params);
	}

}