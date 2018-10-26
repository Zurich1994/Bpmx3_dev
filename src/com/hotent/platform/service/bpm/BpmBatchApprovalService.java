package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmBatchApprovalDao;
import com.hotent.platform.model.bpm.BpmBatchApproval;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.Dictionary;
import com.hotent.platform.model.util.FieldPool;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.system.DictionaryService;

/**
 * <pre>
 * 对象功能:流程批量审批定义设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-04-17 15:34:17
 * </pre>
 */
@Service
public class BpmBatchApprovalService extends BaseService<BpmBatchApproval> {
	@Resource
	private BpmBatchApprovalDao dao;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	@Resource
	private DictionaryService dictionaryService;

	public BpmBatchApprovalService() {
	}

	@Override
	protected IEntityDao<BpmBatchApproval, Long> getEntityDao() {
		return dao;
	}

	public boolean isExists(BpmBatchApproval bpmBatchApproval) {
		Long id = bpmBatchApproval.getId();
		if (id == null || id == 0) 
			id = null;
		List<BpmBatchApproval> list = dao.isExists(bpmBatchApproval.getDefKey(),bpmBatchApproval.getNodeId(),id);
		if(list.size() >0)
			return true;
		return false;
	}

	/**
	 * 保存 流程批量审批定义设置 信息
	 * 
	 * @param bpmBatchApproval
	 */
	public Boolean save(BpmBatchApproval bpmBatchApproval) {
		boolean isAdd = true;
		Long id = bpmBatchApproval.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			bpmBatchApproval.setId(id);
			this.add(bpmBatchApproval);
		} else {
			isAdd = false;
			this.update(bpmBatchApproval);
		}
		return isAdd;
	}

	public List<String> getFieldList(BpmBatchApproval bpmBatchApproval) {
		String fieldJson = bpmBatchApproval.getFieldJson(); // 字段设置
		return this.getFieldList(fieldJson, "fieldDesc"); // 获取需要显示的字段名称

	}
	public List<String> getFieldNameList(BpmBatchApproval bpmBatchApproval) {
		String fieldJson = bpmBatchApproval.getFieldJson(); // 字段设置
		List<String>  list = this.getFieldList(fieldJson, "fieldName"); // 获取需要显示的字段名称
		return list;

	}

	/**
	 * 获取数据列表
	 * 
	 * @param bpmBatchApproval
	 * @return
	 */
	public List<Map<String, Object>> getDataList(BpmBatchApproval bpmBatchApproval) {

		String fieldJson = bpmBatchApproval.getFieldJson(); // 字段设置
		String nodeId = bpmBatchApproval.getNodeId();
		String defKey = bpmBatchApproval.getDefKey(); // 流程定义
		Long tableId = bpmBatchApproval.getTableId(); // 表单id

		List<Map<String, Object>> dataList = this.getDataList(defKey, nodeId,
				tableId, fieldJson); // 获取数据
		return dataList;

	}

	private List<Map<String, Object>> getDataList(String defKey, String nodeId,
			Long tableId, String fieldJson) {
		// 获取要查询的字段
		List<String> fieldList = this.getFieldList(fieldJson, "fieldName");
		if (BeanUtils.isEmpty(fieldList))
			return null;
		// 获取该流程、该节点有哪些待办的实例的关联业务key(关联表数据的id)
		List<String> businessKeyList = dao.businessKeyList(defKey,nodeId);
		
		// 　获取表对象
		BpmFormTable bpmFormTable = bpmFormTableService
				.getByTableId(tableId, 1);

		String tableName = bpmFormTable.getTableName();
		int isExterNal = bpmFormTable.getIsExternal(); // 是内部表，还是外部表
		String pkField = bpmFormTable.getPkField(); // 表的主键
		String dsAlias = bpmFormTable.getDsAlias(); // 数据源别名

		// 组装sql语句
		String sql = this.transformSql(fieldList, businessKeyList, tableName,
				pkField, isExterNal);

		// 查询数据
		List<Map<String, Object>> dataList = JdbcTemplateUtil
				.getNamedParameterJdbcTemplate(dsAlias).queryForList(sql,
						new HashMap<String, Object>());

		// 格式化的数据
		if (dataList != null && dataList.size() > 0) {
			Map<String, Object> formatData = this.getFormatDataMap(
					bpmFormTable, isExterNal);
			dataList = this.formatDataList(dataList, formatData);
		}

		logger.debug(sql);
		return dataList;
	}

	/**
	 * 格式化数据
	 * 
	 * @param dataList
	 * @param formatData
	 * @return
	 */
	private List<Map<String, Object>> formatDataList(
			List<Map<String, Object>> list, Map<String, Object> formatData) {
		if (formatData.isEmpty())
			return list;
		List<Map<String, Object>> rtDataList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			for (String key : map.keySet()) {
				Object value = this
						.getFormatData(map.get(key), key, formatData);
				map.put(key, value);
			}
			rtDataList.add(map);
		}
		return rtDataList;
	}

	/**
	 * 获取数据格式化的值
	 * 
	 * @param o
	 * @param name
	 * @param parse
	 * @return
	 */
	private Object getFormatData(Object o, String name,
			Map<String, Object> formatData) {
		if (BeanUtils.isEmpty(o))
			return "";
		// 如果是日期的
		if (o instanceof Date) {
			String style = StringPool.DATE_FORMAT_DATE;
			if (formatData.containsKey(name)) {
				Object format = formatData.get(name);
				if (BeanUtils.isNotEmpty(format))
					style = (String) format;
			}
			o = DateFormatUtil.format((Date) o, style);
		}
		// 如果是 下拉框，单选框的
		else if (formatData.containsKey(name)) {
			Object obj = formatData.get(name);
			if (BeanUtils.isNotEmpty(obj)) {
				if (obj instanceof Map) {
					Map<?, ?> map = (Map<?, ?>) obj;
					String[] selectStrs = o.toString().split(",");
					//复选框多选
					if(selectStrs.length>1){
						String result = "";
						for(String str : selectStrs){
							result += map.get(str) + ",";
						}
						o = result.substring(0, result.length()-1);
					}else{
						o = map.get(o);
					}
					if (BeanUtils.isEmpty(o))
						o = "";
				}
			}
		}
		return o.toString();
	}

	/**
	 * 获取格式化的数据，目前只处理日期格式
	 * 
	 * @param bpmFormTable
	 * @return
	 */
	private Map<String, Object> getFormatDataMap(BpmFormTable bpmFormTable,
			Integer isExterNal) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 字段信息
		List<BpmFormField> fieldList = bpmFormTable.getFieldList();
		for (BpmFormField bpmFormField : fieldList) {
			String name = bpmFormField.getFieldName().toUpperCase();
			String fieldType = bpmFormField.getFieldType();
			Short controlType = bpmFormField.getControlType();
			// 下面那些判断主要是为了字段值不能直接获取到的，例如日期，下拉框之类需要特殊处理的字段，那些输入框直接输入的就不用
			if (BpmFormField.DATATYPE_DATE.equals(fieldType)) {
				map.put(name, bpmFormField.getDatefmt());
			} else {
				if (controlType.shortValue() == FieldPool.RADIO_INPUT || controlType.shortValue() == FieldPool.CHECKBOX) {
					String options = bpmFormField.getJsonOptions();

					if (StringUtils.isEmpty(options))
						continue;

					Map<String, String> optionMap = new HashMap<String, String>();
					JSONArray jarray = JSONArray.fromObject(options);
					for (Object obj : jarray) {
						JSONObject json = (JSONObject) obj;
						String key = (String) json.get("key");
						String value = (String) json.get("value");
						optionMap.put(key, value);
					}
					map.put(name, optionMap);
				} else if (controlType.shortValue() == FieldPool.SELECT_INPUT) {
					// 单独处理下拉框，1、定义字段时定义了值，2、定义了下拉框级联的情况处理
					// 1、处理普通情况，定义字段时的定义值
					String options = bpmFormField.getJsonOptions();

					Map<String, String> optionMap = new LinkedHashMap<String, String>();
					if (StringUtil.isNotEmpty(options)) {
						JSONArray jarray = JSONArray.fromObject(options);
						for (Object obj : jarray) {
							JSONObject json = (JSONObject) obj;
							String key = (String) json.get("key");
							String value = (String) json.get("value");
							optionMap.put(key, value);
						}
					}
					// 处理下拉框级联的情况
					//this.handCascadeOpinion(formKey, name, optionMap);
					map.put(name, optionMap);
				}else if(controlType.shortValue() == FieldPool.DICTIONARY){
					//数据字典情况
					String dicName = bpmFormField.getDictType();
					List<Dictionary> dictionarieList = dictionaryService.getByNodeKey(dicName);
					Map<String, String> dicMap = new HashMap<String, String>();
					for(Dictionary dictionary:dictionarieList){
						dicMap.put(dictionary.getItemName(), dictionary.getItemName());
					}
					map.put(name, dicMap);
				}
			}
		}
		return map;
	}

	/**
	 * json转list，排除掉不显示的字段
	 * 
	 * @param fieldJson
	 * @param dataKey
	 * @return
	 */
	private List<String> getFieldList(String fieldJson, String dataKey) {
		List<String> list = new ArrayList<String>();
		JSONArray jsonArray = JSONArray.fromObject(fieldJson);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			if (BpmBatchApproval.IS_SHOW_Y.equals(obj.getString("isShow"))) {
				list.add(obj.getString(dataKey));
			}
		}
		return list;

	}

	private String transformSql(List<String> fieldList,
			List<String> businessKeyList, String tableName, String pkField,
			int isExterNal) {
		StringBuffer sbf = new StringBuffer();
		String businessKeys = this.listToStrForPk(businessKeyList); // 实例对象id
		String fields = "";
		String pre = "";
		if (BpmFormTable.EXTERNAL == isExterNal) { // 如果是外部表，直接拼装
			fields = this.listToStr(fieldList, tableName, pre);
		} else { // 如果不是外部表，就需要对字段和表名加上特定的前缀
			tableName = TableModel.CUSTOMER_TABLE_PREFIX + tableName;
			pre = TableModel.CUSTOMER_COLUMN_PREFIX;
			fields = this.listToStr(fieldList, tableName, pre);
		}
		pkField = tableName + "." + pkField;
		sbf.append(
				" select task.ID_ as actTaskId ,task.DESCRIPTION_ AS actDescription , run.SUBJECT as actSubject, ")
				.append(fields)
				.append(" from ")
				.append(tableName)
				.append(" left join bpm_pro_run run on run.businessKey = ")
				.append(pkField)
				.append(" left join act_ru_task task on task.proc_inst_id_=run.actInstId ")
				.append(" where ").append(pkField).append(" in (")
				.append(businessKeys).append(")");
		return sbf.toString();
	}

	/**
	 * 字段list 转 str 中间以","隔开 每个字段都加上表名
	 * 
	 * @param fieldList
	 * @param pre
	 * @return
	 */
	private String listToStr(List<String> fieldList, String tableName,
			String pre) {
		String str = "";
		for (int i = 0; i < fieldList.size(); i++) {
			String fieldName = fieldList.get(i).toUpperCase();
			str += tableName + "." + pre + fieldName + " as " +fieldName;
			if (i + 1 < fieldList.size())
				str += ",";
		}
		return str;
	}

	/**
	 * 主键list 转 str 中间以","隔开，每个字段用''包围,防止主鍵不是number类型
	 * 
	 * @param businessKeyList
	 * @return
	 */
	private String listToStrForPk(List<String> businessKeyList) {
		if (businessKeyList == null || businessKeyList.size() == 0)
			return "''";
		String str = "";
		for (int i = 0; i < businessKeyList.size(); i++) {
			str += "'" + businessKeyList.get(i) + "'";
			if (i + 1 < businessKeyList.size())
				str += ",";
		}
		return str;
	}

}
