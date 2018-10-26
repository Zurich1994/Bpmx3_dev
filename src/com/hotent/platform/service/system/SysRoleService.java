package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.system.ResourcesDao;
import com.hotent.platform.dao.system.ResourcesUrlDao;
import com.hotent.platform.dao.system.RoleResourcesDao;
import com.hotent.platform.dao.system.SysRoleDao;
import com.hotent.platform.dao.system.UserRoleDao;
import com.hotent.platform.model.system.OrgAuth;
import com.hotent.platform.model.system.ResourcesUrlExt;
import com.hotent.platform.model.system.RoleResources;
import com.hotent.platform.model.system.SysOrgRole;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.UserRole;

/**
 * 对象功能:系统角色表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-18 16:24:10
 */
@Service
public class SysRoleService extends BaseService<SysRole>
{
	@Resource
	private ResourcesDao resourcesDao;
	@Resource
	private ResourcesUrlDao resourcesUrlDao;
	
	@Resource
	UserRoleDao userRoleDao;
	@Resource
	private RoleResourcesDao roleResourcesDao;
	@Resource
	private SysOrgRoleService sysOrgRoleService;
	@Resource
	private OrgAuthService orgAuthService;
	
	@Resource
	private SysRoleDao dao;
	

	@Override
	protected IEntityDao<SysRole, Long> getEntityDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	

	
	public SysRoleService()
	{
	}
	
	
	
	/**
	 * 判断角色是否存在
	 * @param roleName
	 * @return
	 */
	public boolean isExistRoleAlias(String alias){
		return dao.isExistRoleAlias(alias);
	}
	
	/**
	 * 判断角色是否存在。
	 * 用于更新时判断。
	 * @param alias	角色别名
	 * @param roleId	角色id。
	 * @return
	 */
	public boolean isExistRoleAliasForUpd(String alias,Long roleId){
		return dao.isExistRoleAliasForUpd(alias,roleId);
	}
	
	/**
	 * 查询所有角色及子系统
	 * @param queryFilter
	 * @return
	 */
	public List<SysRole> getRoleList(QueryFilter queryFilter){
		return dao.getRole(queryFilter);
	}
	
	/**
	 * 根据UserId取得系统角色
	 * @param userId
	 * @return
	 */
	public List<SysRole> getByUserId(Long userId){
		return dao.getByUserId(userId);
	}
	
	/**
	 * 根据系统id获取角色。
	 * @param systemId
	 * @return
	 */
	public List<SysRole> getBySystemId(Long systemId){
		return dao.getBySystemId(systemId);
	}
	
	/**
	 * 返回某个用户的所有角色列表Id
	 * @param userId
	 * @return
	 */
	public String getRoleIdsByUserId(Long userId){
		StringBuffer sb=new StringBuffer("");
		List<SysRole> sysRoleList=getByUserId(userId);
		for(SysRole sysRole:sysRoleList){
			sb.append(sysRole.getRoleId()).append(",");
		}
		if(sysRoleList.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		return sb.toString();
	}
	
	/**
	 * 返回某个用户的所有角色列表Id
	 * @param userId
	 * @return
	 */
	public List<Long> getRoleIdsByUserIdLong(Long userId){
		List<SysRole> sysRoleList=getByUserId(userId);
		List<Long> list=new ArrayList<Long>();
		for(SysRole role:sysRoleList){
			list.add(role.getRoleId());
		}
		return list;
	}
	
	
	/**
	 * 获取所有子系统的角色
	 * @param queryFilter
	 * @return
	 */
	public List<SysRole> getRoleTree(QueryFilter queryFilter){
		return dao.getRole(queryFilter);
		
	}
	

	

	/**
	 * 根据系统ID获取资源的URL和角色映射对象。
	 * <br>
	 * <pre>
	 * 	ResourcesUrlExt 的url字段和role 别名。
	 * 		1.从SYS_RES 的 defaultUrl获取。
	 * 		2.从SYS_RESURL 表中获取。
	 * </pre>
	 * @param systemId
	 * @return
	 */
	public Map<String, Object> getUrlRightMap(String sysAlias,String url){
		Map<String, Object> map=new HashMap<String, Object>();
		
		Set<String> roleSet=new HashSet<String>();
	
		//根据resourcesUrl的url
		List<ResourcesUrlExt> urlList=resourcesUrlDao.getUrlAndRoleByUrlSystemAlias(sysAlias, url);
		List<ResourcesUrlExt> defaultUrlList=resourcesDao.getDefaultUrlAndRoleByUrlSystemAlias(sysAlias, url);
	
		if(urlList.size()>0 || defaultUrlList.size()>0){
			map.put("isUrlExist", true);
		}
		else{
			map.put("isUrlExist", false);
		}
		
		for(ResourcesUrlExt urlExt:urlList){
			if(StringUtil.isEmpty(urlExt.getRole())) continue;
			roleSet.add(urlExt.getRole());
		}
		for(ResourcesUrlExt urlExt:defaultUrlList){
			if(StringUtil.isEmpty(urlExt.getRole())) continue;
			roleSet.add(urlExt.getRole());
		}
		List<String> roleList=new ArrayList<String>();
		roleList.addAll(roleSet);
		
		map.put("roleList", roleList);
		
		return map;
	}
	/**
	 * 根据系统id获取系统的功能和角色别名映射对象。
	 * 用于在页面判断功能是否可用。
	 * @param systemId
	 * @return
	 */
	public List<ResourcesUrlExt> getFunctionRoleList(String sysAlias,String function){
		List<ResourcesUrlExt> defaultUrlList=resourcesDao.getFunctionAndRoleBySystemAlias(sysAlias, function);
		return defaultUrlList;
	}
	
//	public List<ResourcesUrlExt> getFunctionRoleList(Long systemId){
//		List<ResourcesUrlExt> defaultUrlList=resourcesDao.getFunctionAndRoleBySystemId(systemId);
//		return defaultUrlList;
//	}
	
//	/**
//	 * 根据系统的URL获取功能。
//	 * @param defaultUrl
//	 * @return
//	 */
//	public List<ResourcesUrlExt> getSubSystemFunction(String alias){
//		List<ResourcesUrlExt> defaultUrlList=resourcesDao.getSubSystemsFuncByAlias(alias);
//		return defaultUrlList;
//	}
	
	/**
	 * 根据子系统的访问地址获取子系统的资源。
	 * @param defaultUrl
	 * @return
	 */
//	public List<ResourcesUrlExt> getSubSystemResources(String alias){
//		//根据resourcesUrl的url
//		List<ResourcesUrlExt> urlList=resourcesUrlDao.getSubSystemResByAlias(alias);
//		//resources的defaultUrl
//		List<ResourcesUrlExt> defaultUrlList=resourcesDao.getSubSystemResByAlias(alias);
//		
//		
//		List<ResourcesUrlExt> returnList=new ArrayList<ResourcesUrlExt>();
//		if(urlList!=null&&urlList.size()>0)
//			returnList.addAll(urlList);
//		if(defaultUrlList!=null&&defaultUrlList.size()>0)
//			returnList.addAll(defaultUrlList);
//		return returnList;
//	}
	
	/**
	 * 复制原角色下的所有特性。
	 * <pre>
	 * 	1.添加角色。
	 *  2.用户角色映射表。
	 *  3.添加角色和资源映射。
	 *  4.添加角色和系统的映射。
	 * </pre>
	 * @param sysRole
	 * @param userRoleList
	 * @throws Exception
	 */
	public void copyRole(SysRole sysRole,long oldRoleId) throws Exception{
		//查询原用户拥有的系统资源特性
		List<RoleResources> roleResourcesList=roleResourcesDao.getRoleRes(oldRoleId);
		//查询原用户下拥有的人员
		List<UserRole> userRoleList=userRoleDao.getUserRoleByRoleId(oldRoleId);
		
		Long newRoleId=sysRole.getRoleId();
		//向角色表中添加数据
		dao.add(sysRole);
	    //向用户角色映射表中添加数据
		for(UserRole userRole:userRoleList){
			UserRole ur=(UserRole) userRole.clone();
			ur.setUserRoleId(UniqueIdUtil.genId());
			ur.setRoleId(newRoleId);
			userRoleDao.add(ur);
		}
		//向角色资源映射表中添加数据
		for(RoleResources rores:roleResourcesList){
			RoleResources roleres=(RoleResources)rores.clone();
			roleres.setRoleResId(UniqueIdUtil.genId());
			roleres.setRoleId(newRoleId);
			roleResourcesDao.add(roleres);
		}
		
	}
	
	/**
	 *取得子系统角色资源
	 * @param defaultUrl
	 * @return
	 */
	public List<SysRole> loadSecurityRole(String systemId, String roleName ){
		return dao.getRoleBySystemId(new Long( systemId), roleName);
	}
	
	/**
	 * 根据用户ID和组织ID获取用户角色
	 * <pre>
	 * 1、用户和角色有映射关系，可以获取到用户所拥有的角色；
	 * 2、组织和角色有映射关系，可以获取到组织所拥有的角色；
	 * 3、用户可以切换当前组织，根据用户ID和当前组织ID可以获取到2个角色列表的并集。
	 * </pre>
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<String> getRolesByUserIdAndOrgId(Long userId,Long orgId){
		List<String> totalRoles=new ArrayList<String>();
		//根据用户获取
		List<String> userRoles= getUserRoles(userId);
		
		if(BeanUtils.isNotEmpty(userRoles)){
			totalRoles.addAll(userRoles);
		}
		//根据组织获取。
		if(orgId>0){
			List<String> roleRoles= getOrgRoles(orgId);
			if(BeanUtils.isNotEmpty(roleRoles)){
				totalRoles.addAll(roleRoles);
			}
		}
		return totalRoles;
	}
	
	/**
	 * 根据用户角色关系获取角色。
	 * @param userId
	 * @return
	 */
	private List<String> getUserRoles(Long userId){
		//获取用户角色。
		List<SysRole> userRoleList= dao.getByUserId(userId);
		List<String> userRoles=new ArrayList<String>();
		if(BeanUtils.isNotEmpty(userRoleList)){
			for(SysRole role:userRoleList){
				userRoles.add(role.getAlias());
			}
		}
		return userRoles;
	}
	
	/**
	 * 根据组织Id获取角色。
	 * @param orgId
	 * @return
	 */
	private List<String> getOrgRoles(Long orgId){
//		ICache iCache = (ICache) AppUtil.getBean(ICache.class);
//		String orgKey=SecurityUtil.OrgRole + orgId;
//		List<String> list=(List<String>)iCache.getByKey(orgKey);
		//从缓存获取
//		if(BeanUtils.isNotEmpty(list))
//			return list;
		//根据缓存读取。
		List<SysOrgRole> orgRoles = sysOrgRoleService.getRolesByOrgId(orgId);
		
		List<String> roles=new ArrayList<String>();
		if(BeanUtils.isNotEmpty(orgRoles)){
			for(SysOrgRole sysOrgRole:orgRoles){
				SysRole role=sysOrgRole.getRole();
				if(role==null) continue;
				roles.add(role.getAlias());
			}
			//加入缓存。
//			iCache.add(orgKey, roles);
		}
		return roles;
	}
	
	/**
	 * 根据角色名称查询角色
	 * @param roleName
	 * @return
	 */
	public  List<SysRole> getByRoleName(String roleName) {
		return dao.getByRoleName(roleName);
	}

	/**
	 * 按角色别名取得角色实体
	 * @param roleAlias
	 * @return
	 */
	public SysRole getByRoleAlias(String roleAlias){
		return dao.getByRoleAlias(roleAlias);
	}
	
	/**
	 * 根据组获取角色列表。
	 * @param groupId
	 * @return
	 */
	public List<SysRole> getByOrgMonGroup(Long groupId){
		return dao.getByOrgMonGroup(groupId);
	}


	/**
	 * 通过授权ID获取角色，（分级授权管理员设置）
	 * @return
	 */
	public List<SysRole> getByAuthId(Long authId) {
		return dao.getByAuthId(authId);
	}


	/**
	 * 通过分级管理员获取他拥有的可分配角色
	 * @param userId
	 * @return
	 */
	public List<SysRole> getByUser(long userId) {
		HashMap<String, SysRole> roleMap = new HashMap<String, SysRole>();
		List<OrgAuth> orgAuthList = orgAuthService.getByUserId(userId);
		for(OrgAuth orgAuth : orgAuthList){
			List<SysRole> roleList = getByAuthId(orgAuth.getId());
			for(SysRole role : roleList){
				roleMap.put(role.getAlias(), role);
			}
		}
		return new ArrayList<SysRole>(roleMap.values());
	}


	/**
	 * 查询当前用户的可分配角色。
	 * @return
	 */
	public List<SysRole> getUserAssignRole(QueryFilter queryFilter) {
		Map params = queryFilter.getFilters();
		params.put("userId", ContextUtil.getCurrentUserId());
		return dao.getUserAssignRole(params);
	}

}
