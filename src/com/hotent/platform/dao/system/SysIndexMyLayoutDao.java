package com.hotent.platform.dao.system;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysIndexMyLayout;

/**
 * <pre>
 * 对象功能:我的布局 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 15:39:48
 * </pre>
 */
@SuppressWarnings("unchecked")
@Repository
public class SysIndexMyLayoutDao extends BaseDao<SysIndexMyLayout> {
	@Override
	public Class<?> getEntityClass() {
		return SysIndexMyLayout.class;
	}

	public SysIndexMyLayout getByUserId(Long userId) {
		return this.getUnique("getByUserId", userId);
	}

}