package com.hotent.Subsystemdef.service.Subsystemdef;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.Subsystemdef.model.Subsystemdef.Subsystemdef;
import com.hotent.Subsystemdef.dao.Subsystemdef.SubsystemdefDao;


@Service
public class SubsystemdefService extends BaseService<Subsystemdef>
{
	@Resource
	private SubsystemdefDao subsystemdefDao;
	public SubsystemdefService()
	{
	}
	
	
	/**
	 * 根据流程定义key获得流程定义
	 * @param defkey 
	 * @return
	 */
	public List <Subsystemdef> getByDefKey1(String sys_defkey){
		return subsystemdefDao.getByDefKey1(sys_defkey);
	}
	@Override
	protected IEntityDao<Subsystemdef,Long> getEntityDao() 
	{
		return subsystemdefDao;
	}
	public List<Subsystemdef> getDefKey(Long id){
		List<Subsystemdef> list = subsystemdefDao.getDefKey(id);
		return list;
	}		
}
