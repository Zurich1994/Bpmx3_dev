/**
 * 对象功能:表单模板 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2012-01-09 16:35:25
 */
package com.hotent.platform.dao.form;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.form.BpmFormTemplate;

@Repository
public class BpmFormTemplateDao extends BaseDao<BpmFormTemplate>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmFormTemplate.class;
	}
	
	/**
	 * 获取所有的数据。
	 * @param params
	 * @return
	 */
	public List<BpmFormTemplate> getAll(Map params){
		return this.getBySqlKey("getAll",params);
	}
	
	/**
	 * 插入一条数据
	 */
	public void add(BpmFormTemplate bpmFormTemplate){
		this.getBySqlKey("add",bpmFormTemplate);
	}
	
	/**
	 * 删除所有的数据
	 */
	public void delSystem(){
		this.delBySqlKey("delSystem", null);
	}
	
	/**
	 * 根据别名获取模版。
	 * @param alias
	 * @return
	 */
	public BpmFormTemplate getByTemplateAlias(String alias){
		return this.getUnique("getByTemplateAlias", alias);
	}
	
	/**
	 * 获取模版是否有数据。
	 * @return
	 */
	public Integer getHasData(){
		return (Integer)this.getOne("getHasData", null);
	}
}