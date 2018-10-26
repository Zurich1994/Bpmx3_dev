/**
 * 对象功能:流水号生成 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-03 14:40:59
 */
package com.hotent.platform.dao.system;


import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.core.db.GenericDao;
import com.hotent.platform.model.system.Identity;


@Repository
public class IdentityDao extends BaseDao<Identity>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return Identity.class;
	}
	
	
	/**
	 * 根据别名获取键值。
	 * @param alias
	 * @return
	 */
	public Identity getByAlias(String alias)
	{
		Identity obj=(Identity)this.getUnique("getByAlias", alias);
		return obj;
	}
	
	/**
	 * 查看别名是否存在。
	 * @param alias
	 * @return
	 */
	public boolean isAliasExisted(String alias){
		return (Integer) this.getOne("isAliasExisted", alias)>0;
	}
	
	/**
	 * 查看别名是否存在。
	 * @param identity
	 * @return
	 */
	public boolean isAliasExistedByUpdate(Identity identity){
		return (Integer) this.getOne("isAliasExistedByUpdate", identity)>0;
	}
	
	
	public List<Identity> getList(){
		return this.getBySqlKey("getList");
	}
	
	
	public int updateVersion(Identity identity){
		return this.update("updateVersion", identity);
	}
	
	

}