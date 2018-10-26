package com.hotent.platform.dao.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.form.SubTableSort;
/**
 *<pre>
 * 对象功能:bpm_subtable_sort Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-03-12 13:56:01
 *</pre>
 */
@Repository
public class SubTableSortDao extends BaseDao<SubTableSort>
{
	@Override
	public Class<?> getEntityClass()
	{
		return SubTableSort.class;
	}

	public List<SubTableSort> getByActDefKeyAndTableName(String actDefKey,
			List<String> tableName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("actDefKey", actDefKey);
		params.put("tableNameList", tableName);
		return this.getBySqlKey("getByActDefKeyAndTableName", params);
	}

	public void delByTableNameAndActKey(List<String> tableNameList,
			String actDefKey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("actDefKey", actDefKey);
		params.put("tableNameList", tableNameList);
		this.delBySqlKey("delByTableNameAndActKey", params);
	}

	public SubTableSort getByAKeyAndTName(String actDefKey, String tableName) {
		List<String> tableNameList = new ArrayList<String>();
		tableNameList.add(tableName);
		List<SubTableSort> list=this.getByActDefKeyAndTableName(actDefKey, tableNameList);
		if(BeanUtils.isEmpty(list)){
			return null;
		}
		
		
		return list.get(0);
	}

	
	
	
	
}