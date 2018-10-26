package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysIndexColumn;

/**
 * <pre>
 * 对象功能:首页栏目 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:hugh
 * 创建时间:2015-03-18 11:22:46
 * </pre>
 */
@Repository
public class SysIndexColumnDao extends BaseDao<SysIndexColumn> {
	@Override
	public Class<?> getEntityClass() {
		return SysIndexColumn.class;
	}

	/**
	 * 通过别名获取栏目
	 * 
	 * @param columnAlias
	 * @return
	 */
	public SysIndexColumn getByColumnAlias(String alias) {
		return (SysIndexColumn) getOne("getByColumnAlias", alias);
	}

	/**
	 * 获取当前用户有权限的栏目
	 * @param params
	 * @return
	 */
	public List<SysIndexColumn> getByUserIdFilter(Map<String, Object> params) {
		return getBySqlKey("getByUserIdFilter",params);
	}

	public Integer getCounts() {
		return (Integer)this.getOne("getCounts", null);
	}

	public Boolean isExistAlias(String alias, Long id) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("alias", alias);
    	map.put("id", id);
    	Integer count=(Integer)this.getOne("isExistAlias", map);
    	return count>0;
	}

}