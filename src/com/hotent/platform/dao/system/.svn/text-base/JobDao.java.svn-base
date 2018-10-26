package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.Job;
/**
 *<pre>
 * 对象功能:职务表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-28 16:17:48
 *</pre>
 */
@Repository
public class JobDao extends BaseDao<Job>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Job.class;
	}

	public void deleteByUpdateFlag(Long id){
		this.update("deleteByUpdateFlag", id);
	}
	
	/**
	 * 根据用户id获取职务列表。
	 * @param userId
	 * @return
	 */
	public List<Job> getByUserId(Long userId){
		return this.getBySqlKey("getByUserId", userId);
	}
	
	public Job getByJobCode(String jobCode){
		return this.getUnique("getByJobCode", jobCode);
	}
	public boolean isExistJobName(String jobname) {
		Integer count=(Integer)this.getOne("isExistJobName", jobname);
		return count>0;
	}
	/**
	 * 判断职务名称是否存在
	 * @param jobname
	 * @return
	 */
	public boolean isExistJobCode(String jobCode) {
		Integer count=(Integer)this.getOne("isExistJobCode", jobCode);
		return count>0;
	}
	
	/**
	 * 判断职务是否存在，更新时使用。
	 * @param jobCode
	 * @param jobId
	 * @return
	 */
	public boolean isExistJobCodeForUpd(String jobCode,Long jobId) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("jobCode", jobCode);
		params.put("jobid", jobId);
		Integer count=(Integer)this.getOne("isExistJobCodeForUpd", params);
		return count>0;
	}
}