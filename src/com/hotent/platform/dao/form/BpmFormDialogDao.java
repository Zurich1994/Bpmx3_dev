/**
 * 对象功能:通用表单对话框 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-06-25 11:05:09
 */
package com.hotent.platform.dao.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.BpmFormTemplate;

@Repository
public class BpmFormDialogDao extends BaseDao<BpmFormDialog>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmFormDialog.class;
	}
	
	/**
	 * 根据别名获取对话框对象。
	 * @param alias		对话框别名。
	 * @return
	 */
	public BpmFormDialog getByAlias(String alias){
		return this.getUnique("getByAlias", alias);
	}
	
	/**
	 * 根据别名获取是否存在。
	 * @param alias
	 * @return
	 */
	public Integer  isExistAlias(String alias){
		return (Integer)this.getOne("isExistAlias", alias);
	}
	
	/**
	 * 根据别名判断是否存在，用于更新判断。
	 * @param id
	 * @param alias
	 * @return
	 */
	public Integer isExistAliasForUpd(Long id,String alias){
		Map map=new HashMap();
		map.put("id",id);
		map.put("alias", alias);
		return (Integer)this.getOne("isExistAliasForUpd", map);
	}
	
}