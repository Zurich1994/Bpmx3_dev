package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmGangedSet;
/**
 * 
 *<pre>
 * 对象功能:联动设置表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2012-12-28 16:50:37
 *</pre>
 */
@Repository
public class BpmGangedSetDao extends BaseDao<BpmGangedSet>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmGangedSet.class;
	}
	
	public List<BpmGangedSet> getByDefId(Long defId){
		return this.getBySqlKey("getByDefId", defId);
	}
	
	/**
	 * 获取某个节点的联动设置（包括全局联动设置）
	 * @param defId
	 * @param nodeId
	 * @return
	 */
	public List<BpmGangedSet> getByDefIdAndNodeId(Long defId,String nodeId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("defId", defId);
		map.put("nodeId", nodeId);
		return this.getBySqlKey("getByDefIdAndNodeId",map);
	}
	
	/**
	 * 通过DEFID删除联动设置（除了指定setid的记录以外）
	 * @param defId
	 * @param setIds
	 */
	public void delByDefIdExceptSetId(Long defId,String setIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("defId", defId);
		map.put("setId", setIds);
		this.update("delByDefIdExceptSetId",map);
	}
	
	public void delByDefId(Long defId){
		this.update("delByDefId", defId);
	}
}