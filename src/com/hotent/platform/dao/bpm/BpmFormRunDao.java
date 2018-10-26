/**
 * 对象功能:流程表单运行情况 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-05-21 16:28:40
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmFormRun;

@Repository
public class BpmFormRunDao extends BaseDao<BpmFormRun>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmFormRun.class;
	}
	
	/**
	 * 根据流程实例取得表单运行数据。
	 * @param actInstanceId
	 * @param actNodeId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public BpmFormRun getByInstanceAndNode(String actInstanceId,String actNodeId){
		Map params=new HashMap();
		params.put("actInstanceId", actInstanceId);
		params.put("actNodeId", actNodeId);
		return this.getUnique("getByInstanceAndNode", params);
	}
	
	/**
	 * 根据流程实例ID，流程实例的运行表单列表
	 * @param actInstanceId
	 * @return
	 */
	public List<BpmFormRun> getByInstanceId(String actInstanceId){
		return this.getBySqlKey("getByInstanceId", actInstanceId);
	}
	
	/**
	 * 根据流程实例取得全局表单。
	 * @param actInstanceId
	 * @return
	 */
	public BpmFormRun getGlobalForm(String actInstanceId){
		return this.getUnique("getGlobalForm", actInstanceId);
	}
	/**
	 * 根据流程实例ID，获取流程实例运行时开始表单
	 * @param actInstanceId 流程实例ID
	 * @return
	 */
	public BpmFormRun getStartFormRun(String actInstanceId) {
		return this.getUnique("getStartFormRun", actInstanceId);
	}
	
	/**
	 * 根据流程实例删除数据。
	 * @param actInstanceId
	 */
	public void delByInstanceId(String actInstanceId){
		this.delBySqlKey("delByInstanceId", actInstanceId);
	}
	/**
	 * 根据act流程定义Id删除数据
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}
}