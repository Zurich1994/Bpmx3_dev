/**
 * 对象功能:桌面栏目 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-03-20 16:39:01
 */
package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.system.DesktopColumn;

@Repository
public class DesktopColumnDao extends BaseDao<DesktopColumn>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return DesktopColumn.class;
	}
	
	/**
	 * 删除数据库中所有的栏目实体
	 */
	public void delAll(){
		this.delBySqlKey("delAll", null);
	}


	/**
	 * 删除数据库中所有系统的栏目实体。
	 */
	public void delBySysColumn() {
		//getBySqlKey("delBySysColumn",null);
		delBySqlKey("delBySysColumn", null);
		
	}	
	
	/**获取系统所有的系统栏目实体。
	 * @return 所有的系统栏目
	 */
	public List<DesktopColumn> getSysColumn(){
		return getBySqlKey("getSysColumn");
	}
	
	
	/**
	 * 根据栏目名称找相应的唯一的栏目实体。
	 * @param name 所要查找栏目实体的栏目名
	 * @return 栏目名对应的栏目实体。未找到返回<code>null</code>。
	 */
	public DesktopColumn getByTemplateId(String templateId){
		return this.getUnique("getByTemplateId", templateId);
	}
	

	/**
	 * 取得桌面栏目记录数量
	 * @return
	 */
	public Integer getCounts() {
		return (Integer)this.getOne("getCounts", null);
	}	

}