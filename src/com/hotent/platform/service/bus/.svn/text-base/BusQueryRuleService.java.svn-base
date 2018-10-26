package com.hotent.platform.service.bus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.scan.TableEntity;
import com.hotent.core.web.query.scan.TableField;
import com.hotent.platform.dao.bus.BusQueryRuleDao;
import com.hotent.platform.model.bus.BusQueryRule;
import com.hotent.platform.model.util.FieldPool;

/**
 * <pre>
 * 对象功能:高级查询规则 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2013-12-09 14:19:29
 * </pre>
 */
@Service
public class BusQueryRuleService extends BaseService<BusQueryRule> {
	@Resource
	private BusQueryRuleDao dao;

	public BusQueryRuleService() {
	}

	@Override
	protected IEntityDao<BusQueryRule, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 通过表名获取定义的查询规则
	 * 
	 * @param tableName
	 *            表名
	 * @return
	 */
	public BusQueryRule getByTableName(String tableName) {
		return dao.getByTableName(tableName);
	}

	/**
	 * 通过表名获取查询规则
	 * 
	 * @param tableName
	 * @return
	 */
	public Integer getCountByTableName(String tableName) {
		return dao.getCountByTableName(tableName);
	}

	/**
	 * 通过tableEntity（表实体）获取查询规则
	 * 
	 * @param tableEntity
	 * @return
	 */
	public BusQueryRule getByTableEntity(TableEntity tableEntity) {
		String tableName = tableEntity.getTableName();
		BusQueryRule busQueryRule = dao.getByTableName(tableName);
		if (BeanUtils.isEmpty(busQueryRule)) {
			busQueryRule = new BusQueryRule();
			busQueryRule.setTableName(tableName);
			busQueryRule.setDisplayField(this.getDisplayField(tableEntity, ""));
			busQueryRule.setExportField(this.getExportField(tableEntity, ""));
		} else {
			busQueryRule.setDisplayField(this.getDisplayField(tableEntity,
					busQueryRule.getDisplayField()));
			busQueryRule.setExportField(this.getExportField(tableEntity,
					busQueryRule.getExportField()));
		}
		return busQueryRule;

	}

	// 显示字段
	private String getDisplayField(TableEntity tableEntity, String displayField) {
		Map<String, String> map = getDisplayFieldRight(displayField);
		Map<String, String> descMap = getDisplayFieldDesc(displayField);
		if (BeanUtils.isEmpty(tableEntity))
			return displayField;
		List<TableField> fieldList = tableEntity.getTableFieldList();
		JSONArray jsonAry = new JSONArray();
		for (TableField tableField : fieldList) {
			if (tableField.getDataType().equals(FieldPool.DATATYPE_CLOB))// 如果是大文本的不加入
				continue;
			JSONObject json = new JSONObject();
			json.accumulate("name", tableField.getName());
			json.accumulate("variable", tableField.getVar());
			String desc = tableField.getDesc();
			if (BeanUtils.isNotEmpty(map))
				desc = descMap.get(tableField.getName());
			json.accumulate("desc", desc);
			json.accumulate("type", tableField.getFieldType());
			json.accumulate("style", "");
			String right = "";
			if (BeanUtils.isNotEmpty(map))
				right = map.get(tableField.getName());
			if (StringUtils.isEmpty(right))
				right = getDefaultDisplayFieldRight();

			json.accumulate("right", right);
			jsonAry.add(json);
		}
		displayField = jsonAry.toString();

		return displayField;
	}

	private Map<String, String> getDisplayFieldDesc(String displayField) {
		if (StringUtil.isEmpty(displayField))
			return null;
		Map<String, String> map = new HashMap<String, String>();
		JSONArray jsonAry = JSONArray.fromObject(displayField);

		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String name = (String) json.get("name");
			String desc = (String) json.get("desc");
			map.put(name, desc);
		}
		return map;
	}

	private Map<String, String> getDisplayFieldRight(String displayField) {
		if (StringUtil.isEmpty(displayField))
			return null;
		Map<String, String> map = new HashMap<String, String>();
		JSONArray jsonAry = JSONArray.fromObject(displayField);

		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String name = (String) json.get("name");
			JSONArray right = (JSONArray) json.get("right");
			map.put(name, right.toString());
		}
		return map;
	}

	private String getDefaultDisplayFieldRight() {
		JSONArray jsonAry = new JSONArray();
		for (int i = 0; i < 2; i++) {
			JSONObject json = new JSONObject();
			json.accumulate("s", i);
			json.accumulate("type", "none");
			json.accumulate("id", "");
			json.accumulate("name", "");
			json.accumulate("script", "");
			jsonAry.add(json);
		}
		return jsonAry.toString();
	}

	// ====end 显示字段
	// ==== 导出字段
	private String getExportField(TableEntity tableEntity, String field) {
		Map<String, String> map = getExportFieldRight(field);
		Map<String, String> descMap = getExportFieldDesc(field);
		if (BeanUtils.isEmpty(tableEntity))
			return field;

		JSONArray jsonAry = new JSONArray();

		List<TableField> fieldList = tableEntity.getTableFieldList();
		jsonAry.add(getTableField(tableEntity, fieldList, map, descMap));
		List<TableEntity> subTableList = tableEntity.getSubTableList();
		for (TableEntity subTable : subTableList) {
			jsonAry.add(getTableField(subTable, subTable.getTableFieldList(),
					map, descMap));
		}

		return jsonAry.toString();
	}

	private JSONObject getTableField(TableEntity tableEntity,
			List<TableField> fieldList, Map<String, String> map,
			Map<String, String> descMap) {
		JSONObject tableJson = new JSONObject();
		tableJson.element("tableName", tableEntity.getTableName());
		tableJson.element("tableDesc", tableEntity.getComment());
		tableJson.element("isMain", 1);
		JSONArray jsonAry = new JSONArray();
		for (TableField tableField : fieldList) {
			JSONObject json = new JSONObject();
			String key = tableField.getName() + tableField.getName();
			json.element("tableName", tableField.getName());
			json.element("isMain", 1);
			json.element("name", tableField.getName());
			String desc = tableField.getDesc();
			if (BeanUtils.isNotEmpty(map))
				desc = descMap.get(key);
			json.element("desc", desc);
			json.element("type", tableField.getFieldType());
			json.element("style", "");
			String right = "";
			if (BeanUtils.isNotEmpty(map))
				right = map.get(key);
			if (StringUtils.isEmpty(right))
				right = getDefaultExportFieldRight();
			json.element("right", right);
			jsonAry.add(json);
		}
		tableJson.element("fields", jsonAry.toArray());
		return tableJson;

	}

	private Map<String, String> getExportFieldDesc(String field) {
		if (StringUtil.isEmpty(field))
			return null;
		Map<String, String> map = new HashMap<String, String>();
		JSONArray jsonAry = JSONArray.fromObject(field);
		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			JSONArray fields = (JSONArray) json.get("fields");
			for (Object obj1 : fields) {
				JSONObject json1 = JSONObject.fromObject(obj1);
				String name = (String) json1.get("name");
				String tableName = (String) json1.get("tableName");
				String desc = (String) json1.get("desc");
				map.put(tableName + name, desc);
			}
		}
		return map;
	}

	private Map<String, String> getExportFieldRight(String field) {
		if (StringUtil.isEmpty(field))
			return null;
		Map<String, String> map = new HashMap<String, String>();
		JSONArray jsonAry = JSONArray.fromObject(field);

		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			JSONArray fields = (JSONArray) json.get("fields");
			for (Object obj1 : fields) {
				JSONObject json1 = JSONObject.fromObject(obj1);
				String name = (String) json1.get("name");
				String tableName = (String) json1.get("tableName");
				JSONArray right = (JSONArray) json1.get("right");
				map.put(tableName + name, right.toString());
			}
		}
		return map;
	}

	private String getDefaultExportFieldRight() {
		JSONArray jsonAry = new JSONArray();
		JSONObject json = new JSONObject();
		json.accumulate("s", 2);
		json.accumulate("type", "none");
		json.accumulate("id", "");
		json.accumulate("name", "");
		json.accumulate("script", "");
		jsonAry.add(json);
		return jsonAry.toString();
	}

	// ==== end 导出字段
}
