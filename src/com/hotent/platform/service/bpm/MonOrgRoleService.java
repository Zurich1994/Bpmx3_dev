package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.MonOrgRole;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.dao.bpm.MonOrgRoleDao;
import com.hotent.platform.dao.system.SysOrgDao;

/**
 *<pre>
 * 对象功能:监控组权限分配 Service类
 * 开发公司:SF
 * 开发人员:xxx
 * 创建时间:2013-06-17 18:38:16
 *</pre>
 */
@Service
public class MonOrgRoleService extends BaseService<MonOrgRole>
{
	@Resource
	private MonOrgRoleDao dao;
	
	@Resource
	private SysOrgDao sysOrgDao;
	
	
	
	public MonOrgRoleService()
	{
	}
	
	@Override
	protected IEntityDao<MonOrgRole, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存权限。
	 * @param groupId
	 * @param roleIds
	 * @param orgIds
	 */
	public void saveAuth(Long groupId,String roleIds,String orgIds){
		dao.delByGroupId(groupId);
		List<Long> orgList= getOrgs(orgIds);
		String[] aryRole=roleIds.split(",");
		for(String roleId:aryRole){
			Long role=new Long(roleId);
			for(Long orgId:orgList){
				MonOrgRole monOrgRole=new MonOrgRole();
				monOrgRole.setId(UniqueIdUtil.genId());
				monOrgRole.setRoleid(role);
				monOrgRole.setOrgid(orgId);
				monOrgRole.setGroupid(groupId);
				dao.add(monOrgRole);
			}
		}
	}
	
	
	/**
	 * 根据指定的组织ID 返回上层的树ID,如果一个组织包含其他的组织，那么这个不会返回包含的组织。
	 * @param orgIds
	 * @return
	 */
	public List<Long> getOrgs(String orgIds){
		String[] aryIds=orgIds.split(",");
		List<Long> orgIdList=new ArrayList<Long>();
		for(String orgId:aryIds){
			orgIdList.add(new Long(orgId));
		}
		List<Long> rtnList=new ArrayList<Long>();
		String preOrg="";
		List<SysOrg> orgList= sysOrgDao.getByOrgIds(orgIdList);
		for(SysOrg org:orgList){
			if(preOrg.equals("")){
				rtnList.add(org.getOrgId());
				preOrg=org.getPath();
			}
			else{
				String curPath=org.getPath();
				if(curPath.indexOf(preOrg)==-1){
					rtnList.add(org.getOrgId());
					preOrg=org.getPath();
				}
			}
			
		}
		return rtnList;
	}
	
}
