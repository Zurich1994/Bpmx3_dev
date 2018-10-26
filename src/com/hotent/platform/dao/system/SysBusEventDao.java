package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysBusEvent;
/**
 *<pre>
 * 对象功能:sys_bus_event Dao类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-05-22 11:14:30
 *</pre>
 */
@Repository
public class SysBusEventDao extends BaseDao<SysBusEvent>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SysBusEvent.class;
	}

	/**
	 * 根据表单KEY取得对象。
	 * @param formKey
	 * @return
	 */
	public SysBusEvent getByFormKey(String formKey){
		return this.getUnique("getByFormKey", formKey);
	}
	
}