package com.hotent.platform.service.system;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysBulletinColumnDao;
import com.hotent.platform.model.system.SysBulletinColumn;


@Service
public class SysBulletinColumnService extends BaseService<SysBulletinColumn>
{
	@Resource
	private SysBulletinColumnDao dao;
	
	public SysBulletinColumnService()
	{
	}
	
	@Override
	protected IEntityDao<SysBulletinColumn,Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 保存 公告栏目 信息
	 * @param sysBulletinColumn
	 */

	public void save(SysBulletinColumn sysBulletinColumn){
		Long id=sysBulletinColumn.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			sysBulletinColumn.setId(id);
			this.add(sysBulletinColumn);
		}
		else{
			this.update(sysBulletinColumn);
		}
	}
	
	/**
	 * 获取栏目
	 * @return
	 */
	public List<SysBulletinColumn> getColumn(Long companyId,Boolean isSuperAdmin) {
		return dao.getColumn(companyId,isSuperAdmin);
	}

}
