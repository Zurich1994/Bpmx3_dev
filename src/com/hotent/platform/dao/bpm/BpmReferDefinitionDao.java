/**
 * 
 */
package com.hotent.platform.dao.bpm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmReferDefinition;

/**
 * <pre>
 * 对象功能:BpmReferDefinition dao类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:tgl 
 * 创建时间:2011-12-15 17:20:40
 * </pre>
 *
 */
@Repository
public class BpmReferDefinitionDao extends BaseDao<BpmReferDefinition> {

	@Override
	public Class<BpmReferDefinition> getEntityClass(){
		return BpmReferDefinition.class;
	}
	
	public void saveReferDef(BpmReferDefinition refers){
		add(refers);
	}
	
	public int delReferDef(Long refers){
		return delById(refers);
	}
	
	public int delByDefId(Long defId){
		return this.delBySqlKey("delByDefId", defId);
	}
	
	public int updateReferDef(BpmReferDefinition refers){
		return update("update", refers);
	}

	public List<BpmReferDefinition> getByDefId(Long defId) {
		return this.getBySqlKey("getByDefId", defId);
	}
}
