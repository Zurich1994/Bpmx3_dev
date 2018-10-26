/**
 * 对象功能:自定义工具条 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-07-25 18:26:12
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeButton;

@Repository
public class BpmNodeButtonDao extends BaseDao<BpmNodeButton>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmNodeButton.class;
	}
	
	/**
	 * 根据流程定义ID获取操作按钮列表。
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeButton> getByDefId(Long defId){
		
		List<BpmNodeButton> list=this.getBySqlKey("getByDefId", defId);
		return list;
	}
	
	/**
	 * 根据节点ID获取操作按钮列表。
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeButton> getByNodeId(String nodeId){
		
		List<BpmNodeButton> list=this.getBySqlKey("getByNodeId", nodeId);
		return list;
	}
	
	/**
	 * 根据流程定义Id和节点Id获取节点操作的按钮。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public List<BpmNodeButton> getByDefNodeId(Long defId,String nodeId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("nodeId", nodeId);
		List<BpmNodeButton> list=this.getBySqlKey("getByDefNodeId", params);
		return list;
	}
	
	/**
	 * 根据流程定义ID获取起始表单的操作按钮数据。
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeButton> getByStartForm(Long defId){
		List<BpmNodeButton> list=this.getBySqlKey("getByStartForm", defId);
		return list;
	}
	
	/**
	 * 根据流程定义和操作类型判断操作类型是否存在。
	 * @param actDefId
	 * @param operatortype
	 * @return
	 */
	public Integer isStartFormExist(Long defId,Integer operatortype){
		Map params=new HashMap();
		params.put("defId", defId);
		params.put("operatortype", operatortype);
		Integer rtn=(Integer)this.getOne("isStartFormExist", params);
		return rtn;
	}
	
	/**
	 * 根据流程定义和节点操作类型判断是否已经存在。
	 * @param actDefId		流程定义ID
	 * @param nodeId		流程节点ID
	 * @param operatortype	操作类型
	 * @return
	 */
	public Integer isExistByNodeId(Long defId,String nodeId,Integer operatortype){
		Map params=new HashMap();
		params.put("defId", defId);
		
		params.put("nodeId", nodeId);
		params.put("operatortype", operatortype);
		Integer rtn=(Integer)this.getOne("isExistByNodeId", params);
		return rtn;
	}
	
	/**
	 * 根据流程定义和操作类型判断操作类型是否存在。
	 * @param actDefId
	 * @param operatortype
	 * @return
	 */
	public Integer isStartFormExistForUpd(Long defId,Integer operatortype,Long id){
		Map params=new HashMap();
		params.put("defId", defId);

		params.put("operatortype", operatortype);
		params.put("id", id);
		Integer rtn=(Integer)this.getOne("isStartFormExistForUpd", params);
		return rtn;
	}
	
	/**
	 * 根据流程定义和节点操作类型判断是否已经存在。
	 * @param actDefId		流程定义ID
	 * @param nodeId		流程节点ID
	 * @param operatortype	操作类型
	 * @return
	 */
	public Integer isExistByNodeIdForUpd(Long defId,String nodeId,Integer operatortype,Long id){
		Map params=new HashMap();
		params.put("defId", defId);
		params.put("nodeId", nodeId);
		params.put("operatortype", operatortype);
		params.put("id", id);
		Integer rtn=(Integer)this.getOne("isExistByNodeIdForUpd", params);
		return rtn;
	}
	
	/**
	 * 更新排序字段。
	 * @param id	主键。
	 * @param sn	排序。
	 */
	public void updSn(Long id,Long sn){
		Map params=new HashMap();
		params.put("id", id);
		params.put("sn", sn);
		this.update("updSn", params);
	}
	
	/**
	 * 根据流程定义Id删除流程启动按钮。
	 * @param deId
	 */
	public void delByStartForm(Long defId){
		this.delBySqlKey("delByStartForm", defId);
	}
	
	/**
	 * 根据流程定义Id和节点ID删除按钮。
	 * @param defId	流程定义ID。
	 * @param nodeId	流程节点ID。
	 */
	public void delByNodeId(Long defId,String nodeId){
		Map params=new HashMap();
		params.put("defId", defId);
		params.put("nodeId", nodeId); 
		this.delBySqlKey("delByNodeId", params);
	}
	
	/**
	 * 根据流程定义id删除按钮。
	 * @param defId
	 */
	public void delByDefId(Long defId){
		this.delBySqlKey("delByDefId", defId);
	}
	
	/**
	 * 根据act流程定义Id删除按钮
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}

}