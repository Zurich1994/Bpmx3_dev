package com.hotent.platform.service.system;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.system.SysQuerySetting;

/**
 * <pre>
 * 对象功能:sql自定义查询 排序字段解析 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:欧晓斌
 * 创建时间:2014年6月4日15:05:28
 * </pre>
 */
@Service
public class SysQuerySortFieldParseService {
	/**
	 * 获取排序的字段和标识
	 * 
	 * @param params
	 * @param tableIdCode
	 * @return
	 */
	public Map<String, String> getSortMap(Map<String, Object> params,
			String tableIdCode) {
		Map<String, String> sortMap = new HashMap<String, String>();
		// 排序
		String sortField = null;
		String orderSeq = "DESC";
		String newSortField = null;
		if (params.get(tableIdCode + SysQuerySetting.SORTFIELD) != null)
			sortField = (String) params.get(tableIdCode
					+ SysQuerySetting.SORTFIELD);

		if (params.get(tableIdCode + SysQuerySetting.ORDERSEQ) != null)
			orderSeq = (String) params.get(tableIdCode
					+ SysQuerySetting.ORDERSEQ);

		if (params.get(tableIdCode + "__ns__") != null)
			newSortField = (String) params.get(tableIdCode + "__ns__");
		if (StringUtil.isNotEmpty(newSortField)) {
			if (newSortField.equals(sortField)) {
				if (orderSeq.equals("ASC")) {
					orderSeq = "DESC";
				} else {
					orderSeq = "ASC";
				}
			}
			sortField = newSortField;
			params.put(tableIdCode + SysQuerySetting.SORTFIELD, sortField);
			params.put(tableIdCode + SysQuerySetting.ORDERSEQ, orderSeq);

			sortMap.put("sortField", sortField);
			sortMap.put("orderSeq", orderSeq);
		}

		return sortMap;
	}

	/**
	 * 获得order by的SQL
	 * 
	 * @param sql
	 * 
	 * @param table
	 * @param sortField
	 * @param params
	 * @param sortMap
	 * @param source
	 * @return
	 */
	public String getOrderBySql(String sortField, Map<String, String> sortMap) {
		StringBuffer orderBy = new StringBuffer();
		if (BeanUtils.isNotEmpty(sortMap)) {
			orderBy.append(" ORDER BY ").append(sortMap.get("sortField"))
					.append(" ").append(sortMap.get("orderSeq"));
		} else {
			// 取设置的排序
			if (StringUtils.isNotEmpty(sortField)) {
				String sortSql = this.getSortSQL(sortField);
				if (StringUtils.isNotEmpty(sortSql))
					orderBy.append(" ORDER BY ").append(sortSql);
			}
		}
		// 如果没有排序 则用主键排序，避免分页问题
		// if (StringUtils.isEmpty(orderBy.toString()))
		// orderBy.append(" ORDER BY ").append(pkField).append(" ASC");
		return orderBy.toString();
	}

	/**
	 * 获取排序的SQL
	 * 
	 * @param sortField
	 * @param source
	 * @return
	 */
	private String getSortSQL(String sortField) {
		StringBuffer sb = new StringBuffer();
		JSONArray jsonArray = JSONArray.fromObject(sortField);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObj = (JSONObject) jsonArray.get(i);
			String name = (String) jsonObj.get("name");
			String sort = (String) jsonObj.get("sort");
			sb.append(name).append(" " + sort).append(",");
		}
		if (sb.length() > 0)
			return sb.substring(0, sb.length() - 1);
		return sb.toString();
	}
}
