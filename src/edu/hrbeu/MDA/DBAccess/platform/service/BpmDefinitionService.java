package edu.hrbeu.MDA.DBAccess.platform.service;



import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.model.bpm.BpmDefinition;

/**
 * 对象功能:流程定义 Service类
 * 开发公司:
 * 开发人员:zl
 * 创建时间:2015-04-17 20:17:46
 */
@Service
public class BpmDefinitionService extends BaseService<BpmDefinition>
{
	@Resource
	private BpmDefinitionDao dao;	
	
	public BpmDefinitionService()
	{
	}
	
	@Override
	protected IEntityDao<BpmDefinition, Long> getEntityDao() {
		return dao;
	}
	
	
}
