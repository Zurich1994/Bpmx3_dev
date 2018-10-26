package com.hotent.platform.service.bpm;


import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.BpmNodeScript;
import com.hotent.platform.dao.bpm.BpmNodeScriptDao;

/**
 * 对象功能:节点运行脚本 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2011-12-30 14:31:20
 */
@Service
public class BpmNodeScriptService extends BaseService<BpmNodeScript>
{
	@Resource
	private BpmNodeScriptDao dao;
	
	public BpmNodeScriptService()
	{
	}
	
	@Override
	protected IEntityDao<BpmNodeScript, Long> getEntityDao() 
	{
		return dao;
	}
	/**
	 * 根据节点id获取脚本。
	 * @param nodeId
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeScript>  getByNodeScriptId(String nodeId,String actDefId){

		return dao.getByBpmNodeScriptId(nodeId, actDefId);
	}
	
	/**
	 * 根据流程定义id和流程节点id获取脚本放到一个map中。
	 * @param nodeId
	 * @param actDefId
	 * @return
	 */
	public Map<String,BpmNodeScript> getMapByNodeScriptId(String nodeId,String actDefId){
		 List<BpmNodeScript> list=this.getByNodeScriptId(nodeId, actDefId);
		 Map<String, BpmNodeScript> map=new HashMap<String, BpmNodeScript>();
		 for(BpmNodeScript script:list){
			 map.put("type" + script.getScriptType(), script);
		 }
		 
		 return map;
	}
	
	/**
	 * 根据节点和事件类型获取脚本。
	 * @param nodeId
	 * @param actDefId
	 * @param scriptType
	 * @return
	 */
	public BpmNodeScript getScriptByType(String nodeId,String actDefId,Integer scriptType){
		
		return dao.getScriptByType(nodeId, actDefId, scriptType);
	}
	/**
	 * 保存流程节点定义
	 * @param defId
	 * @param nodeId
	 * @param list
	 * @throws Exception 
	 */
	public void saveScriptDef(String defId,String nodeId,List<BpmNodeScript> list) throws Exception{
		//先删除
		this.dao.delByDefAndNodeId( defId,nodeId);
		//再重新添加
		for(BpmNodeScript script:list){
			long id=UniqueIdUtil.genId();
			script.setId(id);
			script.setActDefId(defId);
			script.setNodeId(nodeId);
			dao.add(script);
		}
		
	}

}
