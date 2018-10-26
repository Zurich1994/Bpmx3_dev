package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.system.SysOrgRoleDao;
import com.hotent.platform.dao.system.SysOrgRoleManageDao;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysOrgRole;

/**
 * 对象功能:组织角色授权信息 Service类
 * 开发公司:宏天
 * 开发人员:hotent
 * 创建时间:2012-10-30 09:55:49
 */
@Service
public class SysOrgRoleService extends BaseService<SysOrgRole>
{
	@Resource
	private SysOrgRoleDao dao;
	
	@Resource
	private SysOrgDao sysOrgDao;
	
	@Resource
	private SysOrgRoleManageDao sysOrgRoleManageDao;
	
	public SysOrgRoleService()
	{
	}
	
	@Override
	protected IEntityDao<SysOrgRole, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 根据组织ID获取组织路径。
	 * @param orgId		组织ID。
	 * @return
	 */
	private String getPathByOrgId(Long orgId){
		SysOrg sysOrg=sysOrgDao.getById(orgId);
		if(sysOrg==null) return "";
		String path=sysOrg.getPath();
		if(StringUtil.isEmpty(path)) return "";
		//去掉当前Id。
		path=path.replace("." + orgId +".", "");
		//去掉维度Id。
		int pos=path.indexOf(".");
		path=path.substring(pos+1);
		return path;
	}
	
	/**
	 * 根据组织ID获取可以分配的角色列表。
	 * @param orgId
	 * @return
	 */
	public List<SysOrgRole> getAssignRoleByOrgId(Long orgId){
		List<SysOrgRole> list=sysOrgRoleManageDao.getAssignRoleByOrgId(orgId);
		if(BeanUtils.isNotEmpty(list)){
			return list;
		}
		String path=getPathByOrgId(orgId);
		String[] aryPath=path.split("[.]");
		if(aryPath.length==0){
			return list;
		}
		//从后往前找。
		for(int i=aryPath.length-1;i>=0;i--){
			String tmpOrgId=aryPath[i];
			Long lTmpOrgId=Long.parseLong(tmpOrgId);
			list= sysOrgRoleManageDao.getAssignRoleByOrgId(lTmpOrgId);;
			//如果找到了则停止循环。
			if(BeanUtils.isNotEmpty(list)){
				break;
			}
		}
		if(BeanUtils.isNotEmpty(list)){
			return list;
		}
		list= getRolesByOrgId( orgId);
		//设置来源为组织的分配角色
		for(SysOrgRole sysOrgRole:list)
			sysOrgRole.setFromType(1);
		return list;
	}
	
	
	
	/**
	 * 根据组织ID获取所有角色。
	 * <pre>
	 *  1.首先根据ORGID查询，当前组织是否授权。
	 *  2.当前组织没有角色权限则往上查询角色权限。
	 *  	如果找到了就立即返回。
	 * </pre>
	 * @param orgId
	 * @return
	 */
	public List<SysOrgRole> getRolesByOrgId(Long orgId){
		//根据组织id获取。
		List<SysOrgRole> list= dao.getRolesByOrgId(orgId);
		if(BeanUtils.isNotEmpty(list)) return list;
		//沿着路径往上查找。
		list=new ArrayList<SysOrgRole>();
		String path=getPathByOrgId(orgId);
		if(StringUtil.isEmpty(path)) return list;
		String[] aryPath=path.split("[.]");
		//从后往前找。
		for(int i=aryPath.length-1;i>=0;i--){
			String tmpOrgId=aryPath[i];
			Long lTmpOrgId=Long.parseLong(tmpOrgId);
			list= dao.getRolesByOrgId(lTmpOrgId);
			//如果找到了则停止循环。
			if(BeanUtils.isNotEmpty(list)){
				break;
			}
		}
		return list;
	}
	
	/**
	 * 组织授权角色
	 * @param roles
	 * @param orgId
	 * @param grade
	 * @return
	 */
	public String addOrgRole(Long[] roles,Long orgId,Integer grade){
		String str="";
		for(Long role:roles){
			if(dao.getCountByOrgidRoleid(orgId, role)){
				str=",但添加的角色中包含已经授权的角色。";
				continue;
			}
			SysOrgRole sysOrgRole=new SysOrgRole();
			sysOrgRole.setId(UniqueIdUtil.genId());
			sysOrgRole.setOrgid(orgId);
			sysOrgRole.setRoleid(role);
			sysOrgRole.setCanDel(grade);
			dao.add(sysOrgRole);
		}
		return str;
	}
	
	/**
	 * 根据id删出角色和组织的关联。
	 * @param ids
	 * @return
	 */
	public Long delByOrgRoleIds(Long[] ids){
		Set<Long> set=new HashSet<Long>();
		if(BeanUtils.isEmpty(ids)) return 0L;
		Long orgId=0L;
		for(Long id:ids){
			SysOrgRole  sysOrgRole=dao.getById(id);
			orgId=sysOrgRole.getOrgid();
			
			dao.delById(id);
		}
		return orgId;
	}
	
	/**
	 * 根据角色ID删除组织角色授权信息
	 * @param roleId
	 */
	public void delByRoleId(Long roleId){
		dao.delByRoleId(roleId);
	}
	
	/**
	 * 根据组织ID删除组织角色授权信息
	 * @param orgId
	 */
	public void delByOrgId(Long orgId){
		dao.delByOrgId(orgId);
	}
	
	/**
	 * 根据组织路径删除组织与角色的绑定关系
	 */
	public void delByOrgPath(String path){
		dao.delByOrgPath(path);
	}
	public void delByOrgIdAndRoleId(Long orgId, Long roleId) {
		dao.delByOrgIdAndRoleId(orgId,roleId);
	}
}
