package com.hotent.platform.dao.bus;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bus.BusQueryShare;

/**
 * <pre>
 * 对象功能:查询过滤共享 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-20 10:10:44
 * </pre>
 */
@Repository
public class BusQueryShareDao extends BaseDao<BusQueryShare> {
	@Override
	public Class<?> getEntityClass() {
		return BusQueryShare.class;
	}

	public BusQueryShare getByFilterId(Long filterId) {
		return (BusQueryShare) this.getOne("getByFilterId", filterId);
	}

}