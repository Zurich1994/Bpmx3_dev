package com.hotent.platform.service.bpm;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.MonGroup;
import com.hotent.platform.dao.bpm.MonGroupDao;
import com.hotent.platform.dao.bpm.MonOrgRoleDao;
import com.hotent.platform.model.bpm.MonGroupItem;
import com.hotent.platform.dao.bpm.MonGroupItemDao;

/**
 *<pre>
 * 对象功能:监控分组 Service类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-06-08 11:14:50
 *</pre>
 */
@Service
public class MonGroupService extends BaseService<MonGroup>
{
	@Resource
	private MonGroupDao dao;
	
	@Resource
	private MonGroupItemDao monGroupItemDao;
	
	@Resource
	private MonOrgRoleDao monOrgRoleDao;
	
	
	public MonGroupService()
	{
	}
	
	@Override
	protected IEntityDao<MonGroup, Long> getEntityDao() 
	{
		return dao;
	}
	
	private void delByPk(Long id){
		monGroupItemDao.delByMainId(id);
	}
	
	public void delAll(Long[] lAryId) {
		for(Long id:lAryId){	
			delByPk(id);
			dao.delById(id);	
			monOrgRoleDao.delByGroupId(id);
		}	
	}
	
	public void addAll(MonGroup monGroup) throws Exception{
		add(monGroup);
		addSubList(monGroup);
	}
	
	public void updateAll(MonGroup monGroup) throws Exception{
		update(monGroup);
		delByPk(monGroup.getId());
		addSubList(monGroup);
	}
	
	public void addSubList(MonGroup monGroup) throws Exception{
		List<MonGroupItem> monGroupItemList=monGroup.getMonGroupItemList();
		if(BeanUtils.isNotEmpty(monGroupItemList)){
			for(MonGroupItem monGroupItem:monGroupItemList){
				monGroupItem.setGroupid(monGroup.getId());
				monGroupItem.setId(UniqueIdUtil.genId());
				monGroupItemDao.add(monGroupItem);
			}
		}
	}
	
	public List<MonGroupItem> getMonGroupItemList(Long id) {
		return monGroupItemDao.getByMainId(id);
	}
	
	
	
}
