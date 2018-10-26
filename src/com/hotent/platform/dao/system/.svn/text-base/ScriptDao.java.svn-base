/**
 * 对象功能:脚本管理 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-01-05 12:01:20
 */
package com.hotent.platform.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.Script;

@Repository
public class ScriptDao extends BaseDao<Script>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return Script.class;
	}
	
	/**
	 * 返回所有脚本的分类
	 * @return
	 */
	public List<String> getDistinctCategory(){
		List list= getBySqlKey("getDistinctCategory");
		return list;
	}
	
	/**
	 * 返回所有脚本的分类
	 * @return
	 */
	public Integer isExistWithName(String name){
		return (Integer)getOne("isExistWithName",name);
	}
	
}