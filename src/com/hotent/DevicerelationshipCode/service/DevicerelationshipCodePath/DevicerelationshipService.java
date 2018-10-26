package com.hotent.DevicerelationshipCode.service.DevicerelationshipCodePath;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;

import com.hotent.core.service.GenericService;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.DevicerelationshipCode.model.DevicerelationshipCodePath.Devicerelationship;
import com.hotent.DevicerelationshipCode.dao.DevicerelationshipCodePath.DevicerelationshipDao;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class DevicerelationshipService extends BaseService<Devicerelationship>
{
	@Resource
	private DevicerelationshipDao dao;
	
	public DevicerelationshipService()
	{
	}
	
	@Override
	protected IEntityDao<Devicerelationship,Long> getEntityDao() 
	{
		return dao;
	}
	
//	public List<HashMap> getByDevAndOpp(String f_dev_id,String f_opp_id)
//	{
//		return dao.getByDevAndOpp(f_dev_id,f_opp_id);
//	}
	//zl更新actdefid...............................................
	public int updateActdefid(String oldactdefID, String newactdefid){
		Map<String, String> params = new HashMap<String, String>();
		params.put("oldactdefID", oldactdefID);
		params.put("newactdefid", newactdefid);
		return dao.updateActdefid(params);
	}
}
