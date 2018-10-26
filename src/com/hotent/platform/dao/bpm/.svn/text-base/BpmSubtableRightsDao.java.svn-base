package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmSubtableRights;

/**
 * <pre>
 * 对象功能:子表权限 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wwz
 * 创建时间:2013-01-16 10:04:39
 * </pre>
 */
@Repository
public class BpmSubtableRightsDao extends BaseDao<BpmSubtableRights> {
	@Override
	public Class<?> getEntityClass() {
		return BpmSubtableRights.class;
	}

	/**
	 * 根据流程ID和节点ID获取子表权限配置
	 * 
	 * @param defId
	 * @param nodeId
	 * @param tableId
	 * @return
	 */
	@SuppressWarnings("all")
	public BpmSubtableRights getByDefIdAndNodeId(String actDefId, String nodeId,Long tableId) {
		Map map = new HashMap();
		map.put("actdefid", actDefId);
		map.put("nodeid", nodeId);
		map.put("tableid", tableId);
		BpmSubtableRights model = (BpmSubtableRights) this.getUnique("getByDefIdAndNodeId", map);
		return model;
	}
	
	/**
	 * 根据流程ID和节点ID获取子表权限配置
	 * @param actDefId
	 * @param nodeId
	 * @param tableId
	 * @param parentActDefId
	 * @return
	 */
	@SuppressWarnings("all")
	public BpmSubtableRights getByDefIdAndNodeId(String actDefId, String nodeId,Long tableId, String parentActDefId) {
		Map map = new HashMap();
		map.put("actdefid", actDefId);
		map.put("nodeid", nodeId);
		map.put("tableid", tableId);
		map.put("parentActDefId", parentActDefId);
		BpmSubtableRights model = (BpmSubtableRights) this.getUnique("getByDefIdAndNodeIdAndParentId", map);
		return model;
	}

	public void delByActDefId(String actDefId) {
		this.delBySqlKey("delByActDefId", actDefId);
	}

}