package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodePrivilege;

/**
 * <pre>
 * 对象功能:节点特权 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wwz
 * 创建时间:2012-12-25 12:00:42
 * </pre>
 */
@Repository
public class BpmNodePrivilegeDao extends BaseDao<BpmNodePrivilege> {
	@Override
	public Class<?> getEntityClass() {
		return BpmNodePrivilege.class;
	}

	/**
	 * 根据发布id和流程节点id取得对象。
	 * 
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BpmNodePrivilege> getPrivilegesByDefIdAndNodeId(
			String actDefId, String nodeId) {
		Map map = new HashMap();
		map.put("actDefId", actDefId);
		map.put("nodeId", nodeId);
		List<BpmNodePrivilege> list = (List<BpmNodePrivilege>) this.getList(
				"getPrivilegesByDefIdAndNodeId", map);
		return list;
	}

	/**
	 * 根据发布id和流程节点id删除特权
	 * 
	 * @param actDefId
	 * @param nodeId
	 */
	public void delByDefIdAndNodeId(String actDefId, String nodeId) {
		Map map = new HashMap();
		map.put("actDefId", actDefId);
		map.put("nodeId", nodeId);
		delBySqlKey("delByDefIdAndNodeId", map);
	}

	/**
	 * 根据发布ID,流程节点ID和特权类型获取特权列表
	 * 
	 * @param actDefId
	 * @param nodeId
	 * @param privilegeMode
	 * @return
	 */
	public List<BpmNodePrivilege> getPrivilegesByDefIdAndNodeIdAndMode(String actDefId, String nodeId, Long privilegeMode) {
		Map map = new HashMap();
		map.put("actDefId", actDefId);
		map.put("nodeId", nodeId);
		map.put("privilegeMode", privilegeMode);
		List<BpmNodePrivilege> list = (List<BpmNodePrivilege>) this.getList("getPrivilegesByDefIdAndNodeIdAndMode", map);
		return list;
	}
	
	/**
	 * 根据act流程定义Id删除特权
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}
}