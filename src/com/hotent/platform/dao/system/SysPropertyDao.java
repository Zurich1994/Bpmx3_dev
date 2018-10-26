package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysProperty;

/**
 * <pre>
 * 对象功能:系统配置参数表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-04-16 11:20:41
 * </pre>
 */
@Repository
public class SysPropertyDao extends BaseDao<SysProperty> {
	@Override
	public Class<?> getEntityClass() {
		return SysProperty.class;
	}
	
	/**
	 * 根据别名获取对象
	 * @param alias
	 * @return
	 */
	public SysProperty getByAlias(String alias) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("alias", alias);
		return (SysProperty) this.getOne("getByAlias", params);
	}

}