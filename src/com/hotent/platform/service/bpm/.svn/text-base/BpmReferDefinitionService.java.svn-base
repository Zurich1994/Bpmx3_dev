package com.hotent.platform.service.bpm;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.bpm.BpmReferDefinitionDao;
import com.hotent.platform.model.bpm.BpmReferDefinition;

/**
 * <pre>
 * 对象功能:BpmReferDefinition Service类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:tgl 
 * 创建时间:2011-12-15 17:20:40
 * </pre>
 */

@Service
public class BpmReferDefinitionService extends BaseService<BpmReferDefinition> {
	@Resource
	private BpmReferDefinitionDao dao;

	public BpmReferDefinitionService() {
	}

	@Override
	protected IEntityDao<BpmReferDefinition, Long> getEntityDao() {
		return dao;
	}

	public java.util.List<BpmReferDefinition> getByDefId(Long defId){
		return dao.getBySqlKey("getByDefId", defId);
	}
	
	public void saveReferDef(BpmReferDefinition refers){
		dao.add(refers);
	}
	
	public int delReferDef(Long refers){
		return dao.delById(refers);
	}
	
	public int updateReferDef(BpmReferDefinition refers){
		return dao.update(refers);
	}
	
	public java.util.List<BpmReferDefinition> getReferList(QueryFilter queryFilter){
		return dao.getAll(queryFilter);
	}
	
	public BpmReferDefinition getById(Long id){
		return  dao.getById(id);
	}
}