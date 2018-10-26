package com.hotent.platform.service.bpm;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.bpm.BpmDefVarDao;
import com.hotent.platform.model.bpm.BpmDefVar;

/**
 * 对象功能:流程变量定义 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-01 16:50:07
 */
@Service
public class BpmDefVarService extends BaseService<BpmDefVar>
{
	@Resource
	private BpmDefVarDao dao;
	

	
	public BpmDefVarService()
	{
	}
	
	@Override
	protected IEntityDao<BpmDefVar, Long> getEntityDao() 
	{
		return dao;
	}
	
	public boolean isVarNameExist(String varName,String varKey,Long defId){
		return dao.isVarNameExist(varName,varKey,defId);
	}
	
	/**
	 * 根据流程发布和节点id取得流程变量列表。
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	public List<BpmDefVar> getByDeployAndNode(String deployId,String nodeId){
		return dao.getByDeployAndNode(deployId, nodeId);
		
	}
	
	/**
	 *  根据流程定义ID获取流程变量。
	 * @param defId
	 * @return
	 */
	public List<BpmDefVar> getVarsByFlowDefId(long defId){
		return dao.getVarsByFlowDefId(defId);
	}
	

	
}
