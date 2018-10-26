/**
 * 对象功能:用户表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hotent
 * 创建时间:2011-11-03 16:02:46
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;

import com.hotent.platform.model.system.SysUser;

@Repository
public class SysUserDao extends BaseDao<SysUser> implements UserDetailsService {
	@Resource
	SysRoleDao sysRoleDao;

	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass() {
		return SysUser.class;
	}

	/**
	 * 重写UserDetailService的接口
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		SysUser sysUser = getByAccount(username);
		if (sysUser == null)
			throw new UsernameNotFoundException("用户不存在");
		return sysUser;
	}

	/**
	 * 根据用户账号查询系统用户
	 * 
	 * @param account
	 * @return
	 */
	public SysUser getByAccount(String account) {
		SysUser sysUser = (SysUser) getUnique("getByAccount", account);

		return sysUser;
	}

	/**
	 * 获取没有分配组织的用户
	 * 
	 * @return
	 */
	public List<SysUser> getUserNoOrg(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserNoOrg", queryFilter);
	}

	/**
	 * 对象功能：根据组织id查询员工
	 */
	public List<SysUser> getUserByOrgId(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByOrgId", queryFilter);
	}

	/**
	 * 取到某个组织下的所有用户
	 * 
	 * @param orgId
	 * @return
	 */
	public List<SysUser> getByOrgId(Long orgId) {

		return this.getBySqlKey("getByOrgId", orgId);
	}

	/**
	 * 取到某个岗位下的所有用户
	 * 
	 * @param posId
	 * @return
	 */
	public List<SysUser> getByPosId(Long posId) {

		return this.getBySqlKey("getByPosId", posId);
	}
	
	/**
	 * 取到某个职位下的所有用户
	 * 
	 * @param jobId
	 * @return
	 */
	public List<SysUser> getByJobId(Long jobId) {

		return this.getBySqlKey("getByJobId", jobId);
	}

	/**
	 * 取到某个角色下的所有用户
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysUser> getByRoleId(Long roleId) {

		return this.getBySqlKey("getByRoleId", roleId);
	}

	/**
	 * 对象功能：根据组织id查询员工
	 */
	public List<SysUser> getUserByPath(String path) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("path", path);
		return this.getBySqlKey("getUserByOrgId", param);
	}

	/**
	 * 对象功能：根据查询条件查询用户(用于人员选择器)
	 */
	public List<SysUser> getUserByQuery(QueryFilter queryFilter) {
		if (this.getDbType().equals(SqlTypeConst.ORACLE)
				|| this.getDbType().equals(SqlTypeConst.SQLSERVER)
				|| this.getDbType().equals(SqlTypeConst.MYSQL)) {
			return this.getBySqlKey("getUserByQuery_" + this.getDbType(),
					queryFilter);
		} else {
			return this.getBySqlKey("getUserByQuery", queryFilter);
		}
	}
	
	/**
	 * 对象功能：根据查询条件查询用户(用于用户管理)
	 */
	public List<SysUser> getUsersByQuery(QueryFilter queryFilter) {
		if (this.getDbType().equals(SqlTypeConst.ORACLE)
				|| this.getDbType().equals(SqlTypeConst.SQLSERVER)
				|| this.getDbType().equals(SqlTypeConst.MYSQL)) {
			return this.getBySqlKey("getUsersByQuery_" + this.getDbType(),
					queryFilter);
		} else {
			return this.getBySqlKey("getUsersByQuery", queryFilter);
		}
	}

	/**
	 * 返回某个角色的所有用户Id
	 * 
	 * @param roleId
	 * @return
	 */
	public List<Long> getUserIdsByRoleId(Long roleId) {
		String statement = getIbatisMapperNamespace() + ".getUserIdsByRoleId";

		List list = getSqlSessionTemplate().selectList(statement, roleId);

		return list;
	}

	/**
	 * 根据角色id查询员
	 */
	public List<SysUser> getUserByRoleId(QueryFilter queryFilter) {
		return this.getBySqlKey("getUserByRoleId", queryFilter);
	}

	/**
	 * 根据组织id查询员工
	 */
	public List<SysUser> getDistinctUserByPosPath(QueryFilter queryFilter) {
		return this.getBySqlKey("getDistinctUserByPosPath", queryFilter);
	}

	/**
	 * 根据组织path查询员工
	 */
	public List<SysUser> getDistinctUserByOrgPath(QueryFilter queryFilter) {
		return this.getBySqlKey("getDistinctUserByOrgPath", queryFilter);
	}

	/**
	 * 判断是否存在该账号
	 */
	public boolean isAccountExist(String account) {
		Integer rtn = (Integer) this.getOne("isAccountExist", account);
		return rtn > 0;
	}

	/**
	 * 更新时判定这个帐号是否已经存在。
	 * 
	 * @param userId
	 * @param account
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean isAccountExistForUpd(Long userId, String account) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("account", account);
		Integer rtn = (Integer) this.getOne("isAccountExistForUpd", map);
		return rtn > 0;
	}

	/**
	 * 根据用户参数属性查询用户。
	 * 
	 * @param property
	 * @return
	 */
	public List<SysUser> getByUserOrParam(Map<String, String> property) {
		List<SysUser> list = this.getBySqlKey(
				"getByUserOrParam_" + this.getDbType(), property);
		return list;
	}

	/**
	 * 根据组织属性查询用户。
	 * 
	 * @param property
	 * @return
	 */
	public List<SysUser> getByOrgOrParam(Map<String, String> property) {
		return this
				.getBySqlKey("getByOrgOrParam_" + this.getDbType(), property);
	}

	/**
	 * 根据上下级关系取得用户。
	 * 
	 * <pre>
	 * 输入参数 :path 岗位路径。
	 * 			 condition：上级或下级。
	 * </pre>
	 * 
	 * @param p
	 * @return
	 */
	public List<SysUser> getUpLowPost(Map<String, Object> p) {
		return this.getBySqlKey("getUpLowPost", p);
	}

	/**
	 * 根据上下级组织取得用户列表。
	 * 
	 * <pre>
	 * 	输入参数：
	 * 		demensionId：维度ID。
	 * 		path:		路径。
	 * 		condition：	上级或下级。
	 * 		depth：		上几级或下几级。
	 * </pre>
	 * 
	 * @param p
	 * @return
	 */
	public List<SysUser> getUpLowOrg(Map<String, Object> p) {
		return this.getBySqlKey("getUpLowOrg", p);
	}

	/**
	 * 按ID列表取得用户列表，每个用户ID使用逗号分隔。
	 * 
	 * @param idSet
	 *            用户id使用逗号分隔。
	 * @return
	 */
	public List<SysUser> getByIdSet(Set idSet) {
		Map<String, Object> params = new HashMap<String, Object>();
		if (idSet == null || idSet.size() == 0) {
			params.put("ids", -1);
		} else {
			params.put("ids", StringUtil.getSetAsString(idSet));
		}
		return this.getBySqlKey("getByIdSet", params);
	}

	/**
	 * 根据邮件帐号获取用户对象。
	 * 
	 * @param address
	 * @return
	 */
	public SysUser getByMail(String address) {
		return this.getUnique("getByMail", address);
	}

	/**
	 * 更新密码。
	 * 
	 * @param userId
	 *            用户ID
	 * @param pwd
	 *            加密过的密码
	 */
	public void updPwd(Long userId, String pwd) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("password", pwd);
		this.update("updPwd", map);
	}

	/**
	 * 更新用户的状态。
	 * 
	 * @param userId
	 *            用户id
	 * @param status
	 *            1，激活，0，禁用，-1，删除
	 * @param isLock
	 *            0，未锁定，1，锁定
	 */
	public void updStatus(Long userId, Short status, Short isLock) {
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("status", status);
		map.put("isLock", isLock);
		this.update("updStatus", map);
	}

	public List<SysUser> getDirectLeaderByOrgId(Long orgId) {
		List<SysUser> users = this.getBySqlKey("getDirectLeaderByOrgId", orgId);
		return users;
	}

	/**
	 * 根据下属Id获得上级用户信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<SysUser> getUserByUnderUserId(Long orgId) {
		List<SysUser> users = this.getBySqlKey("getUserByUnderUserId", orgId);
		return users;
	}
	/**
	 * 根据用户Id获取其下属信息
	 * 
	 * @param orgId
	 * @return
	 */
	public List<SysUser> getUnderUserByUserId(Long userId) {
		List<SysUser> users = this.getBySqlKey("getUnderUserByUserId", userId);
		return users;
	}
	
	
	


	/**
	 * 根据数据来源，取得用户信息
	 * 
	 * @param type
	 * @return
	 */
	public List<SysUser> getByFromType(short type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fromType", type);
		return this.getBySqlKey("getByFromType", params);
	}

	public List<SysUser> getAllIncludeOrg() {
		return this.getBySqlKey("getAllIncludeOrg");
	}
	
	/**
	 * 根据流程实例ID获取已审批的人员。
	 * @param actInstId
	 * @return
	 */
	public List<SysUser> getExeUserByInstnceId(Long actInstId){
		return this.getBySqlKey("getExeUserByInstnceId", actInstId);
	}
	
	/**
	 * 根据传入的组织获取用户列表。
	 * @param list
	 * @return
	 */
	public List<SysUser> getByOrgIds(List<Long> list){
		return this.getBySqlKey("getByOrgIds", list);
	}
	
	/**
	 * 根据传入的组织ID获取组织负责人。
	 * @param list
	 * @return
	 */
	public List<SysUser> getMgrByOrgIds(List<Long> list){
		return this.getBySqlKey("getMgrByOrgIds", list);
	}
	
	/**
	 * 根据角色列表获取人员。
	 * @param list
	 * @return
	 */
	public List<SysUser> getByRoleIds(List<Long> list){
		return this.getBySqlKey("getByRoleIds", list);
	}
	
	/**
	 * 根据职务列表获取人员。
	 * @param list
	 * @return
	 */
	public List<SysUser> getByJobIds(List<Long> list){
		return this.getBySqlKey("getByJobIds", list);
	}
	
	/**
	 * 根据岗位取用户。
	 * @param list
	 * @return
	 */
	public List<SysUser> getByPos(List<Long> list){
		return this.getBySqlKey("getByPos", list);
	}

	/**
	 * 根据用户和组织ID获取上级。
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<SysUser> getSuperiorByUserId(Long userId,Long orgId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("orgId", orgId);
		return this.getBySqlKey("getSuperiorByUserId", map);
	}

	
	/**
	 * 获取手机查询的用户
	 * @param filter
	 * @return
	 */
	public List<SysUser> getAllMobile(QueryFilter filter) {
		return this.getBySqlKey("getAllMobile", filter);
	}
	
	/**
	 * 根据传入的userID获取组织负责人。
	 * @param list
	 * @return
	 */
	public List<SysUser> getOrgMainUser(Long userId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userId", userId);
		return this.getBySqlKey("getOrgMainUser", map);
	}
	
	/**
	 * 取得某个部门下有某个角色的人员列表
	 * @param roleId
	 * @param orgId
	 * @return
	 */
	public List<SysUser> getUserByRoleIdOrgId(Long roleId,Long orgId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("orgId", orgId);
		return this.getBySqlKey("getUserByRoleIdOrgId",params);
	}
	/**
	 * 取得某个部门下有某个岗位的人员列表 
	 * @param orgId
	 * @param posId
	 * @return
	 */
	public List<SysUser> getByOrgIdPosId(Long orgId,Long posId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("posId", posId);
		params.put("orgId", orgId);
		return this.getBySqlKey("getByOrgIdPosId",params);
	}

	public List<SysUser> getDistinctUserByOrgId(QueryFilter queryFilter) {
		return this.getBySqlKey("getDistinctUserByOrgId", queryFilter);
	}

	public List<SysUser> getDistinctUserByPosId(QueryFilter queryFilter) {
		return this.getBySqlKey("getDistinctUserByPosId", queryFilter);
	}
	
	
	/**
	 * 得到相同岗位的用户
	 * @param param
	 * @return
	 */
	public List<SysUser> getSamePositionUsersByUserId(Long userId)
	{ 
		return getBySqlKey("getSamePositionUsersByUserId", userId);
	}
	
	/**
	 * 得到相同职务的用户
	 * @param param
	 * @return
	 */
	public List<SysUser> getSameJobUsersByUserId(Long userId)
	{ 
		return getBySqlKey("getSameJobUsersByUserId", userId);
	}
	/**
	 * 根据组织ID和角色ID查询用户列表。
	 * @param orgId
	 * @param roleId
	 * @return
	 */
	public List<SysUser> getByOrgRole(Long orgId,Long roleId)
	{ 
		Map<String,Long> params=new HashMap<String, Long>();
		params.put("orgId", orgId);
		params.put("roleId", roleId);
		return getBySqlKey("getByOrgRole", params);
	}

	/**
	 * 根据分公司ID和角色ID查询用户列表。
	 * @param orgId
	 * @param roleId
	 * @return
	 */
	public List<SysUser> getByCompanyRole(Long companyId,Long roleId)
	{ 
		Map<String,Long> params=new HashMap<String, Long>();
		params.put("companyId", companyId);
		params.put("roleId", roleId);
		return getBySqlKey("getByCompanyRole", params);
	}

	/**
	 * 未建立考勤的人员
	 * @param queryFilter
	 * @return
	 */
	public List<SysUser> getDisUserList(QueryFilter queryFilter) {
		return this.getBySqlKey("getDisUserList", queryFilter);
	}
	
	
	
	

	
}