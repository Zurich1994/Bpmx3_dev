
package com.hotent.Subsystemdef.dao.Subsystemdef;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;

import com.hotent.BpmFormBang.model.bpmFormBang.BpmFormBang;
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;

@Repository
public class SubsystemdefDao extends BaseDao<Subsystemdef>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Subsystemdef.class;
	}
	
	public List<Subsystemdef> getByDefKey1(String sys_defkey){
		List<Subsystemdef> list = getBySqlKey("getByDefKey1", sys_defkey);
		return list;
	}
	
		public Integer getCountList(Long systemId){
			String sys_id=Long.toString(systemId);	
			Integer list =(Integer) this.getOne("tongji",sys_id);		
			return list;
		}
		
		public List<Subsystemdef> getDefKey(Long sys_id){
			List<Subsystemdef> list = getBySqlKey("getBy_F_SYS_ID",sys_id);
			return list;
		}

	
}
