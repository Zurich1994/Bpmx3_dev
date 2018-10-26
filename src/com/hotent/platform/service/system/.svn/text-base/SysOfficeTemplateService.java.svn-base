package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.system.SysOfficeTemplateDao;
import com.hotent.platform.model.system.SysOfficeTemplate;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;

/**
 * 对象功能:office模版 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-05-25 10:16:16
 */
@Service
public class SysOfficeTemplateService extends BaseService<SysOfficeTemplate>
{
	@Resource
	private SysOfficeTemplateDao dao;
	
	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysOrgService sysOrgService;
	
	public SysOfficeTemplateService()
	{
	}
	
	@Override
	protected IEntityDao<SysOfficeTemplate, Long> getEntityDao() 
	{
		return dao;
	}

	public List<SysOfficeTemplate> getOfficeTemplateByUserId(Long userId, QueryFilter filter) {
		//直接分配到自己ID
		filter.addFilter("userId", userId);
		
		
		//用户角色查询
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		if(BeanUtils.isNotEmpty(roles) && roles.size()>0){
			String roleIds = "";
			for (SysRole sysRole : roles)
			{
				roleIds += sysRole.getRoleId()+",";
			}
			roleIds =roleIds.substring(0, roleIds.length()-1);
			filter.addFilter("roleIds", roleIds);
		}
		
		//用户组织查询
		List<SysOrg> orgs = sysOrgService.getOrgsByUserId(userId);
		if(BeanUtils.isNotEmpty(orgs) && orgs.size()>0){
			String orgIds = "";
			for (SysOrg sysOrg : orgs)
			{
				orgIds += sysOrg.getOrgId()+",";
			}
			orgIds =orgIds.substring(0, orgIds.length()-1);
			filter.addFilter("orgIds", orgIds);
		}
		return dao.getOfficeTemplateByUserId(filter);
	}
}
