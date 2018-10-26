/**
 * 对象功能:常用语管理 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-03-16 10:53:20
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.TaskApprovalItems;

@Repository
public class TaskApprovalItemsDao extends BaseDao<TaskApprovalItems> {
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return TaskApprovalItems.class;
	}


	/**
	 * 取流程常用语
	 * 
	 * @param defKey
	 * @param TypeId
	 * @param curUserId
	 * @return
	 */
	public List<TaskApprovalItems> getApprovalByDefKeyAndTypeId(String defKey,
			Long TypeId,Long curUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("defKey", defKey);
		map.put("typeId", TypeId);
		map.put("curUserId", curUserId);
		return this.getBySqlKey("getApprovalByDefKeyAndTypeId", map);
	}


	public List<TaskApprovalItems> getByUserAndAdmin(Long currUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currUserId", currUserId);
		return this.getBySqlKey("getByUserAndAdmin", map);
	}


	//本人的，系统全局的，和流程下的常用语
	public List<TaskApprovalItems> getByDefKeyAndUserAndSys(String defKey, Long curUserId) {
		Map<String, Object>map =new HashMap<String, Object>();
		map.put("defKey", defKey);
		map.put("curUserId", curUserId);
		return this.getBySqlKey("getByDefKeyAndUserAndSys", map);
	}


	public List<TaskApprovalItems> getByType(Short type) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", type);
		return this.getBySqlKey("getByType", map);
	}
}