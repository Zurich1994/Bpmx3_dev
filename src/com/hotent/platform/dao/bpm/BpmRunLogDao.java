/**
 * 对象功能:流程运行日志 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2012-08-06 13:56:42
 */
package com.hotent.platform.dao.bpm;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmRunLog;

@Repository
public class BpmRunLogDao extends BaseDao<BpmRunLog>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmRunLog.class;
	}
	
	/**
	 * 通过用户ID获取用户操作的流程日志
	 * @param userId 用户ID
	 * @return
	 */
	public List<BpmRunLog> getByUserId(Long userId){		
		List list=getBySqlKey("getByUserId",userId);
		return list;
	}
	
	/**
	 * 通过流程运行ID获取流程的操作日志
	 * @param runId 流程运行ID
	 * @return
	 */
	public List<BpmRunLog> getByRunId(Long runId){		
		List list=getBySqlKey("getByRunId",runId);
		return list;
	}
	
	/**
	 * 根据流程运行ID删除流程操作日志
	 * @param runId
	 */
	public void delByRunId(Long runId) {
		this.delBySqlKey("delByRunId", runId);
	}
}