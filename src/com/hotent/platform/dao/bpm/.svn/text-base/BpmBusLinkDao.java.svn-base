package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.AppConfigUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.platform.model.bpm.BpmBusLink;
/**
 *<pre>
 * 对象功能:业务数据关联表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2013-08-21 16:51:49
 *</pre>
 */
@Repository
public class BpmBusLinkDao extends BaseDao<BpmBusLink>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmBusLink.class;
	}
	
	public BpmBusLink getByPk(Long pk){
		return this.getUnique("getByPk", pk);
	}

	public BpmBusLink getByPkStr(String pk){
		return this.getUnique("getByPkStr", pk);
	}
	
	public void delByPk(Long pk){
		this.delBySqlKey("delByPk", pk);
	}
	
	public void delByPkStr(String pk){
		this.delBySqlKey("delByPkStr", pk);
	}
	private static Set<String> partitions = new HashSet<String>();
	///oracle,mysql,mssql
	public void createTablePartition(String tableName) {
		if(!BpmBusLink.isSupportPartition()) return;
		
		String partitionName ="P_"+ tableName.toUpperCase();
		if(partitions.contains(partitionName)) return;
		
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("partitionName", partitionName);
			map.put("tableName", tableName);  
			
			Long count = (Long) getOne("isExsitPartition_"+this.getDbType(),map);
			if(count == 0) update("createPartition_"+this.getDbType(),map);
			partitions.add(partitionName);
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("数据库类型："+this.getDbType()+" BPM_BUS_LINK 表，操作分区失败！");
		}
		
	}
	
	
	/**
	 * 更新业务数据状态。
	 * @param bpmBusLink
	 */
	public void updateStatus(BpmBusLink bpmBusLink){
		this.update("updateStatus", bpmBusLink);
	}
	

/**
 * 根据流程运行ID更新流程实例ID.
 * @param runId
 * @param actInstId
 */
//public void updInstByRunId(Long runId,Long actInstId){
//	Map<String,Long> params=new HashMap<String, Long>();
//	params.put("busFlowRunid", runId);
//	params.put("busProcInstId", actInstId);
//	this.update("updInstByRunId", params);
//}

/**
 * 判断业务主键是否在中间表中有数据，如果没有有表示数据为新增。
 * @param businessKey
 * @return
 */
public boolean checkBusExist(String businessKey) {

	Integer count = (Integer)this.getOne("checkBusExist", businessKey);
	if(count == 0)
			return false;
	return true;
	
}
}
