package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.CommuReceiver;
/**
 *<pre>
 * 对象功能:沟通接收人 Dao类
 * 开发公司:hotent
 * 开发人员:ray
 * 创建时间:2013-04-09 19:44:59
 *</pre>
 */
@Repository
public class CommuReceiverDao extends BaseDao<CommuReceiver>
{
	@Override
	public Class<?> getEntityClass()
	{
		return CommuReceiver.class;
	}
	
	/**
	 * 根据意见ID获取接收人。
	 * @param opinionId
	 * @return
	 */
	public List<CommuReceiver> getByOpinionId(Long opinionId){
		return this.getBySqlKey("getByOpinionId", opinionId);
	}
	
	/**
	 * 根据意见ID和接收人获取对象。
	 * @param opinionId
	 * @param recevierId
	 * @return
	 */
	public CommuReceiver getByTaskReceiver(Long taskId,Long recevierId){
		Map<String,Long> params=new HashMap<String, Long>(); 
		params.put("taskId", taskId);
		params.put("recevierid", recevierId);
		CommuReceiver commuReceiver=this.getUnique("getByTaskReceiver", params);
		return commuReceiver;
	}

	public int delByTaskId(Long taskId) {
		return this.delBySqlKey("delByTaskId", taskId);
	}
}