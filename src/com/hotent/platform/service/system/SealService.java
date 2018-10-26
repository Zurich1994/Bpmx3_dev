package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.Seal;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.dao.system.SealDao;
import com.hotent.platform.dao.system.SysFileDao;

/**
 * 对象功能:电子印章 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:raise
 * 创建时间:2012-08-29 11:26:00
 */
@Service
public class SealService extends BaseService<Seal>
{
	@Resource
	private SealDao dao;
	
	@Resource
	private SysRoleService sysRoleService;

	@Resource
	private SysOrgService sysOrgService;
	
	@Resource 
	private SysFileDao sysFileDao;
	
	public SealService()
	{
	}
	
	@Override
	protected IEntityDao<Seal, Long> getEntityDao() 
	{
		return dao;
	}
	
	@Override
	public void delByIds(Long[] ids) {
		if(BeanUtils.isEmpty(ids)) return;
		for (Long p : ids){
			Seal seal = this.getById(p);
			delById(p);
			if(BeanUtils.isNotEmpty(seal.getAttachmentId())){
				sysFileDao.delById(seal.getAttachmentId());
			}
			if(BeanUtils.isNotEmpty(seal.getShowImageId())){
				sysFileDao.delById(seal.getShowImageId());
			}
		}
	}
	
	/**
	 * 查询与自己相关的印章列表
	 * 
	 * */
	public List<Seal> getSealByUserId(Long userId,String sealName){
		Map map = new HashMap();
		//直接分配到自己ID
		map.put("userId", userId);
		
		//电子印章名称查询
		if(StringUtil.isNotEmpty(sealName)){
			map.put("sealName", "%"+sealName+"%");
		}
		
		//用户角色查询
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		if(BeanUtils.isNotEmpty(roles) && roles.size()>0){
			String roleIds = "";
			for (SysRole sysRole : roles)
			{
				roleIds += sysRole.getRoleId()+",";
			}
			roleIds =roleIds.substring(0, roleIds.length()-1);
			map.put("roleIds", roleIds);
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
			map.put("orgIds", orgIds);
		}
		return dao.getSealByUserId(map);
	}
	
}
