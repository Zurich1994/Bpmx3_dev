package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.page.PageBean;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.system.SysBulletinDao;
import com.hotent.platform.model.system.SysBulletin;

@Service
public class SysBulletinService extends BaseService<SysBulletin> {
	@Resource
	private SysBulletinDao dao;

	public SysBulletinService() {
	}

	@Override
	protected IEntityDao<SysBulletin, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 公告表 信息
	 * 
	 * @param sysBulletin
	 */
	public void save(SysBulletin sysBulletin) {
		Long id = sysBulletin.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			sysBulletin.setId(id);
			this.add(sysBulletin);
		} else {
			this.update(sysBulletin);
		}
	}

	/**
	 * 根据当前用户的分公司拿到list
	 * 
	 * @param queryFilter
	 * @param tenantId
	 * @return
	 */
	/*
	 * public List<SysBulletin> getByCompany(QueryFilter queryFilter, Long
	 * tenantId) { queryFilter.addFilter("ispublic", "0");
	 * queryFilter.addFilter("tenantId", tenantId); return
	 * dao.getBySqlKey("getByCompany", queryFilter); }
	 */

	/**
	 * 根据栏目id取得list
	 * 
	 * @param columnId
	 */
	public void delByColumnId(Long columnId) {
		dao.delByColumnId(columnId);
	}
	
	/**
	 * 根据别名取list
	 * 
	 * @param tenantId
	 * @param alias
	 * @param pb
	 * @return
	 */
	public List<SysBulletin> getAllByAlias(String alias) {
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		pb.setShowTotal(false);
		List<SysBulletin> list = dao.getAllByAlias(alias, pb);
		return list;
	}
	
	/**
	 * 
	 * @param queryFilter
	 * @return
	 */
	public List<SysBulletin> getAllByAlias(QueryFilter queryFilter) {
		return dao.getAllByAlias(queryFilter);
	}
	
	/**
	 * 根据别名取list
	 * 
	 * @param tenantId
	 * @param alias
	 * @param pb
	 * @return
	 */
/*	public List<SysBulletin> getAllByAlias(Long tenantId, String alias) {
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		pb.setShowTotal(false);
		List<SysBulletin> list = dao.getAllByAlias(alias, pb);
		return list;
	}
	
	/**
	 * 根据别名取list
	 * 
	 * @param tenantId
	 * @param alias
	 * @param pb
	 * @return
	 */

	/*public List<SysBulletin> getByCumlunAlias(Long tenantId, String alias) {
		PageBean pb = new PageBean();
		pb.setCurrentPage(1);
		pb.setPagesize(10);
		pb.setShowTotal(false);
		List<SysBulletin> list = null;
		if (tenantId == 0) {
			list = dao.getAllListForDesktop(alias, pb);
		} else {
			list = dao.getListForDesktop(tenantId, alias, pb);
		}
		return list;
	}*/

}
