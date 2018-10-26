package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeWebService;

/**
 * <pre>
 * 对象功能:流程WebService节点 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-21 10:23:36
 * </pre>
 */
@Repository
public class BpmNodeWebServiceDao extends BaseDao<BpmNodeWebService> {
	@Override
	public Class<?> getEntityClass() {
		return BpmNodeWebService.class;
	}

	public List<BpmNodeWebService> getAllByNodeIdActDefId(String nodeId, String actDefId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		return this.getBySqlKey("getAllBpmNodeWebService", params);
	}
	
	public BpmNodeWebService getByNodeIdActDefId(String nodeId, String actDefId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		return getUnique("getBpmNodeWebService", params);
	}

	
	public List<BpmNodeWebService> getByActDefId(String actDefId) {
		return this.getBySqlKey("getByActDefId", actDefId);
	}
	
	public void delByActDefId(String actDefId){
		this.delBySqlKey("delByActDefId", actDefId);
	}
}