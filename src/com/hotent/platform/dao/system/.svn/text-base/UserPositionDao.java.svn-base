package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.UserPosition;
/**
 *<pre>
 * 对象功能:SYS_USER_POS Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-11-27 10:19:23
 *</pre>
 */
@Repository
public class UserPositionDao extends BaseDao<UserPosition>
{
	@Override
	public Class<?> getEntityClass()
	{
		return UserPosition.class;
	}
	/**
	 * 取得某个职位下的所有用户ID
	 * @param posId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Long> getUserIdsByPosId(Long posId)
	{
		List list=getBySqlKey("getUserIdsByPosId", posId);
		return list;
	}
	
	
	
	/**
	 * 根据用户id删除用户和岗文的关系。
	 * @param userId	用户ID
	 */
	public void delByUserId(Long userId){
		this.update("delByUserId", userId);
	}
	
	/**
	 * 根据userId得到岗位
	 * @param param
	 * @return
	 */
	public List<UserPosition> getByUserId(Long userId)
	{ 
		return getBySqlKey("getByUserId", userId);
	}	
	
	
	/**
	 * 根据posId得到岗位
	 * @param param
	 * @return
	 */
	public List<UserPosition> getByPosId(Long userId)
	{ 
		return getBySqlKey("getByPosId", userId);
	}
	
	/**
	 * 根据userId得到组织，组织不能重复
	 * @param param
	 * @return
	 */
	public List<UserPosition> getOrgListByUserId(Long userId)
	{ 
		return getBySqlKey("getOrgListByUserId", userId);
	}	
	
	
	/**
	 * 根据用户id将用户的主岗位属性更新为非主岗位。
	 * @param userId
	 */
	public void updNotPrimaryByUser(Long userId){
		this.update("updNotPrimaryByUser", userId);
	}
	
	/**
	 * 根据用户ID得到用户的主岗位
	 * @param userId
	 * @return
	 */
	public UserPosition getPrimaryUserPositionByUserId(Long userId){
		return this.getUnique("getPrimaryUserPositionByUserId", userId);
	}
	
	/**
	 * 根据组织id串得到用户和组织及岗位的关系
	 * @author hjx
	 * @version 创建时间：2013-11-27  下午3:16:17
	 * @param orgIds
	 * @return
	 */
	public List<UserPosition> getUserByOrgIds(String orgIds){
		Map param =new HashMap();
		param.put("orgIds", orgIds);
		return  this.getBySqlKey("getUserByOrgIds", param);
	}
	
	/**
	 * 根据组织id获取组织负责人。
	 * @param orgId
	 * @return
	 */
	public List<UserPosition> getChargeByOrgId(Long orgId)	{
		return this.getBySqlKey("getChargeByOrgId", orgId);
	}	
	
	/**
	 * 根据用户ID获取可以负责的组织列表。
	 * @param userId
	 * @return
	 */
	public List<UserPosition> getChargeOrgByUserId(Long userId){
		return this.getBySqlKey("getChargeOrgByUserId", userId);
	}
	
	
	
	/**
	 * 根据组织ID删除组织用户关系。
	 * @param orgId
	 */
	public void delByOrgId(Long orgId){
		 this.update("delByOrgId",orgId);
	}
	
	/**
	 * 根据组织路径，逻辑删除该组织及其子组织与岗位的关系
	 * @param path 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delByOrgPath(String path){
		this.update("delByOrgPath", path);
	}
	
	/**
	 * 根据岗位ID删除用户岗位关系
	 * @param param
	 * @return
	 */
	public void delByPosId(Long posId){
		this.update("delByPosId", posId);
	}
	/**
	 * 根据组织ID获取对应关系
	 * @author hjx
	 * @version 创建时间：2013-11-28  上午9:59:35
	 * @param orgId
	 * @return
	 */
	public List<UserPosition> getByOrgId(Long orgId){
		return this.getBySqlKey("getByOrgId", orgId);
	}
	
	/**
	 * 根据组织删除负责人。
	 * @param orgId
	 */
	public void delChargeByOrgId(Long orgId){
		this.update("delChargeByOrgId", orgId);
	}
	
	
	/**
	 * 对象功能：查找该条件的用户组织的实体
	 * 开发公司:广州宏天软件有限公司
	 * 开发人员:pkq
     * 创建时间:2011-11-08 12:04:22 
	 */
	public UserPosition getUserPosModel(Long userId,Long posId)	
	{
		Map param =new HashMap();
		param.put("userId", userId);
		param.put("posId", posId);
		UserPosition userPosition=(UserPosition)getUnique("getUserPosModel", param);
		return userPosition;
	}	
	
	/**
	 * 更新用户组织为非主组织。
	 * @param userId
	 */
	public void updNotPrimaryByUserId(Long userId){
		this.update("updNotPrimaryByUserId", userId);
	}
	
	/**
	 * 根据UserId获取未删除的用户岗位信息
	 * @param userId
	 * @return
	 */
	public List<UserPosition> getPosIdByUserId(Long userId) {
		List<UserPosition> userPosition = this.getBySqlKey("getPosByUserId", userId);
		return userPosition;
	}
	public void delByUserIdAndPosId(Long userId, Long posId) {
		Map param = new HashMap();
		param.put("userId", userId);
		param.put("posId", posId);
		this.update("delByUserIdAndPosId", param);
	}

	

}