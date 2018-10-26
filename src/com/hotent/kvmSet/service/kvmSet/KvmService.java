package com.hotent.kvmSet.service.kvmSet;
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
import com.hotent.kvmSet.model.kvmSet.Kvm;
import com.hotent.kvmSet.dao.kvmSet.KvmDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.firewallSet.model.firewallSet.Firewall;


@Service
public class KvmService extends BaseService<Kvm>
{
	@Resource
	private KvmDao dao;
	
	public KvmService()
	{
	}
	
	@Override
	protected IEntityDao<Kvm,Long> getEntityDao() 
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
	public List<Kvm> getByNodeIdANDActdefId(String actdefID,String nodeID){
		return dao.getByNodeIdANDActdefId(actdefID, nodeID);
	}
}
