package com.hotent.platform.dao.form;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.form.BpmPrintTemplate;
/**
 *<pre>
 * 对象功能:表打 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-12-31 10:01:30
 *</pre>
 */
@Repository
public class BpmPrintTemplateDao extends BaseDao<BpmPrintTemplate>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmPrintTemplate.class;
	}

	public int getCountByFormKey(String formKey) {
		
		return (Integer)this.getOne("getCountByFormKey", formKey);
	}

	public void updateIsNotDefault(Long formKey) {
		this.update("updateIsNotDefault", formKey);
	}

	public BpmPrintTemplate getDefaultByFormKey(String formKey) {
		return this.getUnique("getDefaultByFormKey", formKey);
	}

	public void delByFormKey(Long formKey) {
		this.delBySqlKey("delByFormKey", formKey);
	}

	public List<BpmPrintTemplate> getByFormKey(Long formKey) {
		return getBySqlKey("getByFormKey", formKey);
	}

}