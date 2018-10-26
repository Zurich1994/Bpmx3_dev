package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.page.PageBean;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.BpmTaskExe;

/**
 *<pre>
 * 对象功能:任务转办代理 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-03-27 12:02:35
 *</pre>
 */
@Repository
public class BpmTaskExeDao extends BaseDao<BpmTaskExe>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmTaskExe.class;
	}

	/**
	 * 根据任务ID获得任务转办代理
	 * @param taskId 任务ID
	 * @return
	 */
	public List<BpmTaskExe> getByTaskId(Long taskId) {
		return this.getBySqlKey("getByTaskId", taskId);	
	}

	/**
	 * 根据任务ID获得任务转办代理
	 * @param taskId 任务ID
	 * @return
	 */
	public List<BpmTaskExe> getByTaskIdStatus(Long taskId, Short status) {
		Map<String,Object> map =  new HashMap<String,Object>();
		map.put("taskId", taskId);
		map.put("status", status);
		return this.getBySqlKey("getByTaskIdStatus", map);
	}

	public List<BpmTaskExe> getByRunId(Long runId) {
		return this.getBySqlKey("getByRunId", runId);
	}

	public List<BpmTaskExe> getByActInstId(Long actInstId) {
		return this.getBySqlKey("getByActInstId", actInstId);
	}

	public List<BpmTaskExe> accordingMattersList(QueryFilter filter) {
		return getBySqlKey("accordingMattersList", filter);
	}
	public List<BpmTaskExe> accordingMattersList(Long userId,PageBean pb) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return getBySqlKey("accordingMattersList",userId, pb);
	}
	/**
	 * 根据任务id获取是否已经转办。
	 * @param taskId
	 * @return
	 */
	public Integer getByIsAssign(Long taskId){
		return (Integer) this.getOne("getByIsAssign", taskId);
	}

	public int delByRunId(Long runId) {
		return this.delBySqlKey("delByRunId", runId);
	}
	
}