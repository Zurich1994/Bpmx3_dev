package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysQueryField;

/**
 * <pre>
 * 对象功能:SYS_QUERY_FIELD Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-22 12:47:40
 * </pre>
 */
@Repository
public class SysQueryFieldDao extends BaseDao<SysQueryField> {
	@Override
	public Class<?> getEntityClass() {
		return SysQueryField.class;
	}

	public List<SysQueryField> getDisplayFieldListBySqlId(Long sqlId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sqlId", sqlId);
		return (List<SysQueryField>) this.getBySqlKey("getDisplayFieldListBySqlId", params);
	}

	public List<SysQueryField> getConditionFieldListBySqlId(Long sqlId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sqlId", sqlId);
		return (List<SysQueryField>) this.getBySqlKey("getConditionFieldListBySqlId", params);
	}

	public void delBySqlId(Long sqlId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("sqlId", sqlId);
		this.delBySqlKey("delBySqlId", params);
	}

}