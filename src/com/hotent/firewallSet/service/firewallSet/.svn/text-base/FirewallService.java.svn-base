package com.hotent.firewallSet.service.firewallSet;
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
import com.hotent.firewallSet.model.firewallSet.Firewall;
import com.hotent.firewallSet.dao.firewallSet.FirewallDao;
import com.hotent.serviceSet.model.serviceSet.DeviceServer;
import com.hotent.core.util.UniqueIdUtil;


@Service
public class FirewallService extends BaseService<Firewall>
{
	@Resource
	private FirewallDao dao;
	
	public FirewallService()
	{
	}
	
	@Override
	protected IEntityDao<Firewall,Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 获取指定节点记录。
	 * @param defId
	 * @param creatorId
	 * @param instanceAmount
	 * @return
	 */
	//zl...............................................
	public List<Firewall> getByNodeIdANDActdefId(String actdefID,String nodeID){
		return dao.getByNodeIdANDActdefId(actdefID, nodeID);
	}
}
