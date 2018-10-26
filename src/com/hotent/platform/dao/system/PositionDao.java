package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.Position;
/**
 *<pre>
 * 对象功能:系统岗位表，实际是部门和职务的对应关系表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-27 10:19:23
 *</pre>
 */
@Repository
public class PositionDao extends BaseDao<Position>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Position.class;
	}

	/**
	 * 根据用户ID得到该用户的主岗位名称。
	 * 获取用户主岗位，一个用户只有一个主岗位
	 * @param userId
	 * @return
	 */
	public Position getPrimaryPositionByUserId(Long userId){
		return (Position)this.getUnique("getPrimaryPositionByUserId", userId);
	}
	
	/**
	 * 根据用户ID得到该用户的主岗位名称。
	 * @param userId
	 * @return
	 */
	public Position getPosByUserId(Long userId){
		return getPrimaryPositionByUserId(userId);
	}
	
	/**
	 * 根据用户id获取岗位列表。
	 * @param userId
	 * @return
	 */
	public List<Position> getByUserId(Long userId){
		return this.getBySqlKey("getByUserId", userId);
	}
	
	/**
	 * 根据岗位名称获得岗位信息
	 * @param posName
	 * @return
	 */
	public List<Position> getByPosName(String posName) {
		return this.getBySqlKey("getByPosName", posName);
	}
	
	/**
	 * 根据组织id串得到用户和组织及岗位的关系
	 * @author hjx
	 * @version 创建时间：2013-11-27  下午3:16:17
	 * @param orgIds
	 * @return
	 */
	public List<Position> getOrgPosListByOrgIds(String orgIds){
		if(StringUtil.isEmpty(orgIds))return null;
		Map map =new HashMap();
		map.put("orgIds", orgIds);
		return  this.getBySqlKey("getOrgPosListByOrgIds", map);
	}
	
	/**
	 * 根据组织id串,得到组织集合
	 * @author hjx
	 * @version 创建时间：2013-11-27  下午3:16:17
	 * @param orgIds
	 * @return
	 */
	public List<Position> getOrgListByOrgIds(String orgIds){
		if(StringUtil.isEmpty(orgIds))return null;
		Map map =new HashMap();
		map.put("orgIds", orgIds);
		return  this.getBySqlKey("getOrgListByOrgIds", map);
	}
	
	public void deleteByUpdateFlag(Long posId){
		this.update("deleteByUpdateFlag", posId);
	}


	public Position getByPosCode(String posCode){
		return this.getUnique("getByPosCode", posCode);
	}
	
	
	public List<Position> getBySupOrgId(QueryFilter queryFilter) {
		return  this.getBySqlKey("getBySupOrgId", queryFilter);
	}
	
	/**
	 * 根据组织和职务id取得岗位。
	 * @param orgId	组织ID	
	 * @param jobId	职务ID
	 * @return
	 */
	public Position getByOrgJobId(Long orgId,Long jobId){
		Map<String,Long> params=new HashMap<String, Long>();
		params.put("orgId", orgId);
		params.put("jobId", jobId);
		return (Position)this.getUnique("getByOrgJobId", params);
	}
	

}