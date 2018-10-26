package com.hotent.platform.service.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.scan.SearchCache;
import com.hotent.core.web.query.scan.TableEntity;
import com.hotent.core.web.query.scan.TableField;
import com.hotent.core.web.query.util.QueryUtil;
import com.hotent.platform.dao.bus.BusQueryFilterDao;
import com.hotent.platform.dao.bus.BusQueryRuleDao;
import com.hotent.platform.dao.bus.BusQueryShareDao;
import com.hotent.platform.model.bus.BusQueryFilter;
import com.hotent.platform.model.bus.BusQueryRule;
import com.hotent.platform.model.bus.BusQueryShare;

/**
 * <pre>
 * 对象功能:查询过滤条件 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-17 11:23:06
 * </pre>
 */
@Service
public class BusQueryFilterService extends BaseService<BusQueryFilter> {
	@Resource
	private BusQueryFilterDao dao;
	@Resource
	private BusQueryRuleDao busQueryRuleDao;
	@Resource
	private BusQueryShareDao busQueryShareDao;

	public BusQueryFilterService() {
	}

	@Override
	protected IEntityDao<BusQueryFilter, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存过滤条件
	 * 
	 * @param busQueryFilter
	 * @param queryMap
	 */
	public void saveFilter(BusQueryFilter busQueryFilter,
			Map<String, Object> queryMap,String sortParameter) {
		TableEntity tableEntity = SearchCache.getTableEntityMap().get(
				busQueryFilter.getTableName());
		BusQueryRule busQueryRule = busQueryRuleDao
				.getByTableName(busQueryFilter.getTableName());
		busQueryFilter.setId(UniqueIdUtil.genId());
		busQueryFilter.setRuleId(busQueryRule.getId());
		busQueryFilter.setQueryParameter(getQueryParameter(tableEntity,
				queryMap));
		busQueryFilter.setSortParameter(sortParameter);
		dao.add(busQueryFilter);
	}

	private String getQueryParameter(TableEntity tableEntity,
			Map<String, Object> map) {
		if (BeanUtils.isEmpty(map))
			return null;
		JSONArray jsonAry = new JSONArray();

		Map<String, String> fieldMap = getFieldMap(tableEntity
				.getTableFieldList());

		for (Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Map.Entry<String, Object> e = (Map.Entry<String, Object>) it.next();
			String key = e.getKey();
			String paraName = key.substring(2, key.lastIndexOf("_"));
			String type = key.substring(key.lastIndexOf("_") + 1);
			Object value = e.getValue();
			JSONObject json = new JSONObject();
			json.accumulate("key", key);
			json.accumulate("paraName", paraName);
			json.accumulate("type", type);
			json.accumulate("value", value);
			json.accumulate("desc", fieldMap.get(paraName.toLowerCase()));
			jsonAry.add(json);
		}
		return jsonAry.toString();
	}

	private Map<String, String> getFieldMap(List<TableField> tableFieldList) {
		Map<String, String> map = new HashMap<String, String>();
		for (TableField tableField : tableFieldList) {
			map.put(tableField.getVar().toLowerCase(), tableField.getDesc());
		}
		return map;
	}

	/**
	 * 
	 * @param tableName
	 * @param userId
	 * @return
	 */
	public List<BusQueryFilter> getMyFilterList(String tableName, Long userId) {
		return dao.getMyFilterList(tableName, userId);
	}

	public List<BusQueryFilter> getShareFilterList(String tableName, Long userId) {
		List<BusQueryFilter> list1 =dao.getShareFilterList(tableName, userId);
		Map<String, Object> rightMap = QueryUtil.getRightMap();
		if(BeanUtils.isEmpty(rightMap))
			return list1;
		List<BusQueryFilter> list = new ArrayList<BusQueryFilter>();
		for (BusQueryFilter busQueryFilter : list1) {
			BusQueryShare busQueryShare =busQueryShareDao.getByFilterId(busQueryFilter.getId());
			if(BeanUtils.isEmpty(busQueryShare))
				continue;
			JSONObject permission =JSONObject.fromObject(busQueryShare.getShareRight());
			if(	QueryUtil.hasRight(permission, rightMap))
				list.add(busQueryFilter);
		}
		
		return list;
	}

}
