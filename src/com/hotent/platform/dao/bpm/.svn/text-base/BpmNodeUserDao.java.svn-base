/**
 * 对象功能:InnoDB free: 11264 kB Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2011-12-05 17:20:40
 */
package com.hotent.platform.dao.bpm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmNodeUser;

@Repository
public class BpmNodeUserDao extends BaseDao<BpmNodeUser>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmNodeUser.class;
	}
	
	
	
	/**
	 * 根据流程定义ID获得流程节点人员
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeUser> getByActDefId(String actDefId) {
		return getBySqlKey("getByActDefId", actDefId);
	} 
	
	/**
	 * 
	 * @param actDefId
	 */
	public void delByActDefId(String actDefId){
		delBySqlKey("delByActDefId", actDefId);
	}
	
	
	/**
	 * 
	 * @Methodname: delByConditionId
	 * @Discription: 
	 * @param conditionId
	 * @Author HH
	 * @Time 2012-12-19 下午7:34:42
	 */
	public void delByConditionId(Long conditionId)
	{
		getBySqlKey("delByConditionId",conditionId);
	}
	
	/**
	 * 修复数据
	 * @return
	 */
	public List<BpmNodeUser> selectNull(){
		return (List<BpmNodeUser>)getBySqlKey("selectNull");
	}
	
	/**
	 * 根据条件用户条件规则ID，获取节点用户
	 * @param conditionId
	 * @return
	 */
	public List<BpmNodeUser> getByConditionId(Long conditionId) {
		
		return (List<BpmNodeUser>)getBySqlKey("getByConditionId",conditionId);
	}



	public List<BpmNodeUser> getByActDefIdWithParent(String actDefId,
			String parentActdefId) {
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("parentActdefId", parentActdefId);
		return (List<BpmNodeUser>)getBySqlKey("getByActDefIdWithParent",params);
	}

}