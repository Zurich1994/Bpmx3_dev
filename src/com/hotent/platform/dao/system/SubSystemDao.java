/**
 * 对象功能:子系统管理 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-21 12:22:06
 */
package com.hotent.platform.dao.system;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SubSystem;


@Repository
public class SubSystemDao extends BaseDao<SubSystem>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SubSystem.class;
	}
	

	
	public List<SubSystem> getByRoles(String roleNames){
		Map<String,String> map=new HashMap<String,String>();
		map.put("roleNames", roleNames);
		return this.getBySqlKey("getByRoles", map);
	}
	
	/**
	 * 取得本地的系统列表。
	 * @return
	 */
	public List<SubSystem> getLocalSystem(){
		return  this.getBySqlKey("getLocalSystem", SubSystem.isLocal_Y);
	
	}
	
	/**
	 * 获取激活的系统。
	 * @return
	 */
	public List<SubSystem> getActiveSystem(){
		return  this.getBySqlKey("getActiveSystem");
	}
	
	/**
	 * 判断系统别名是否已存在。
	 * @param alias
	 * @return
	 */
	public Integer isAliasExist(String alias){
		Integer rtn=(Integer)this.getOne("isAliasExist", alias);
		return rtn;
	}
	
	/**
	 * 根据系统别名查询系统数据。
	 * @param alias
	 * @return
	 */
	public SubSystem getByAlias(String alias){
		SubSystem rtn=this.getUnique("getByAlias", alias);
		return rtn;
	}
	
	/**
	 * 更新时判断别名是否存在。
	 * @param alias		别名
	 * @param systemId	系统ID
	 * @return
	 */
	public Integer isAliasExistForUpd(String alias,Long systemId){
		Map  map=new HashMap();
		map.put("alias", alias);
		map.put("systemId", systemId);
		Integer rtn=(Integer)this.getOne("isAliasExistForUpd", map);
		return rtn;
	}



    public List<SubSystem> getAll2() {
		
		return  this.getBySqlKey("getAll2");
	}



    public void update1(SubSystem subSystemForm) {   //logo连接更改信息 年
		Map  map=new HashMap();
		map.put("sysName", subSystemForm.getSysName());
		map.put("contacts", subSystemForm.getContacts());
		map.put("phoneNumber", subSystemForm.getPhoneNumber());
		map.put("platIntroduce", subSystemForm.getPlatIntroduce());
		 this.getBySqlKey("update1",map);
		
	}
	
	
}