package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysOrgRole;
import com.hotent.platform.model.system.SysOrgRoleManage;
/**
 * 对象功能:组织可以授权的角色范围(用于分级授权) Dao类
 * 开发公司:宏天
 * 开发人员:ray
 * 创建时间:2012-11-02 15:03:27
 */
@Repository
public class SysOrgRoleManageDao extends BaseDao<SysOrgRoleManage>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysOrgRoleManage.class;
	}
	
	/**
	 * 根据组织ID获取可分配的权限。
	 * @param orgId
	 * @return
	 */
	public List<SysOrgRole> getAssignRoleByOrgId(Long orgId){
		String statment= this.getIbatisMapperNamespace() +".getAssignRoleByOrgId" ;
		return  this.getSqlSessionTemplate().selectList(statment, orgId);
	}
	
	
	/**
	 * 判断orgId，roleId是否已经进行分配。
	 * @param orgId
	 * @param roleId
	 * @return
	 */
	public boolean isOrgRoleExists(Long orgId,Long roleId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("roleId", roleId);
		Integer rtn=(Integer)this.getOne("isOrgRoleExists", map);
		return rtn>0;
	}

	

}