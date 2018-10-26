package com.hotent.platform.service.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.bpm.ProcessRunDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormData;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.SubTable;
import com.hotent.platform.model.mobile.MobileFormData;
import com.hotent.platform.model.mobile.MobileSubTableData;
import com.hotent.platform.model.util.FieldPool;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormHandlerService;
import com.hotent.platform.service.form.BpmFormRightsService;
import com.hotent.platform.service.form.FormUtil;
import com.hotent.platform.service.form.ParseReult;

/**
 * 手机表单
 * 
 * @author zxh
 * 
 */
@Service
public class MobileFormHandlerService extends
		BaseService<MobileFormHandlerService> {

	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmFormTableDao bpmFormTableDao;
	@Resource
	private BpmFormHandlerService bpmFormHandlerService;
	@Resource
	private BpmFormRightsService bpmFormRightsService;
	@Resource
	private ProcessRunDao processRunDao;

	@Override
	protected IEntityDao<MobileFormHandlerService, Long> getEntityDao() {
		return null;
	}

	/**
	 * 获取mobileFormData 实例
	 * 
	 * @param mobileFormData
	 * @param bpmNodeSet
	 * @param processRun
	 * @param taskId
	 * @param ctxPath
	 * @param nodeId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public MobileFormData getMobileForm(MobileFormData mobileFormData,
			BpmNodeSet bpmNodeSet, ProcessRun processRun, String taskId,
			String ctxPath, String nodeId, Long userId) throws Exception {
		String pkValue = processRun.getBusinessKey();
		Long defId = processRun.getDefId();
		boolean isEmptyForm = false;
		boolean isExtForm = false;
		String formUrl = "";
		String detailUrl = "";
		String formKey=bpmNodeSet.getFormKey();
		if (StringUtil.isEmpty(formKey)) {
			isEmptyForm = true;
		}
		if (bpmNodeSet.getFormType().shortValue() == 1) {
			isExtForm = true;
			formUrl = bpmFormDefService.getFormUrl(taskId, defId, nodeId,
					processRun.getBusinessKey(), ctxPath);
			if (StringUtil.isNotEmpty(bpmNodeSet.getDetailUrl())) {
				detailUrl = bpmNodeSet.getDetailUrl().replaceFirst(
						BpmConst.FORM_PK_REGEX, pkValue);
				if (!detailUrl.startsWith("http")) {
					detailUrl = ctxPath + detailUrl;
				}
			}
		}

		mobileFormData.setEmptyForm(isEmptyForm);
		mobileFormData.setExtForm(isExtForm);
		mobileFormData.setFormEditUrl(formUrl);
		mobileFormData.setFormDetailUrl(detailUrl);

		if (!isExtForm) {
			BpmFormDef bpmFormDef = null;
			if (bpmNodeSet.getFormDefId() != 0) {
				bpmFormDef = bpmFormDefService.getById(bpmNodeSet
						.getFormDefId());
			} else {
				bpmFormDef = bpmFormDefService
						.getDefaultVersionByFormKey(bpmNodeSet.getFormKey());
			}
			mobileFormData = this.getMobileFormData(bpmFormDef, pkValue,
					processRun, mobileFormData,userId,nodeId,ctxPath);
		}
		return mobileFormData;
	}

	/**
	 * 特殊的流程处理
	 * 
	 * @param mobileFormData
	 * @param bpmNodeSet
	 * @param processRun
	 * @param taskId
	 * @param ctxPath
	 * @param nodeId
	 * @param userId
	 * @param bpmDefinition
	 * @throws Exception
	 */
	public MobileFormData getMobileParticularForm(
			MobileFormData mobileFormData, BpmNodeSet bpmNodeSet,
			ProcessRun processRun, String taskId, String ctxPath,
			String nodeId, Long userId, BpmDefinition bpmDefinition)
			throws Exception {
		String actInstId = processRun.getActInstId();
		String pkValue = processRun.getBusinessKey();
		Long defId = processRun.getDefId();
		boolean isEmptyForm = false;
		boolean isExtForm = false;
		String formUrl = "";
		String detailUrl = "";
		String formKey=bpmNodeSet.getFormKey();
		if (StringUtil.isEmpty(formKey)) {
			isEmptyForm = true;
		}
		if (bpmNodeSet.getFormType() == 1) {
			isExtForm = true;
			formUrl = bpmFormDefService.getFormUrl(taskId, defId, nodeId,
					processRun.getBusinessKey(), ctxPath);
			if (StringUtil.isNotEmpty(bpmNodeSet.getDetailUrl())) {
				detailUrl = bpmNodeSet.getDetailUrl().replaceFirst(
						BpmConst.FORM_PK_REGEX, pkValue);
				if (!detailUrl.startsWith("http")) {
					detailUrl = ctxPath + detailUrl;
				}
			}
		}

		mobileFormData.setEmptyForm(isEmptyForm);
		mobileFormData.setExtForm(isExtForm);
		mobileFormData.setFormEditUrl(formUrl);
		mobileFormData.setFormDetailUrl(detailUrl);

		BpmFormDef bpmFormDef = null;
		if (bpmNodeSet.getFormDefId() != 0) {
			bpmFormDef = bpmFormDefService.getById(bpmNodeSet.getFormDefId());
		} else {
			bpmFormDef = bpmFormDefService
					.getDefaultVersionByFormKey(bpmNodeSet.getFormKey());
		}

		return getMobileParticularFormDate(bpmDefinition, bpmFormDef, pkValue,
				actInstId, mobileFormData);
	}

	/**
	 * 不进行处理
	 * 
	 * @param bpmDefinition
	 * @param bpmFormDef
	 * @param pkValue
	 * @param actInstId
	 * @param mobileFormData
	 * @return
	 */
	public MobileFormData getMobileParticularFormDate(
			BpmDefinition bpmDefinition, BpmFormDef bpmFormDef, String pkValue,
			String actInstId, MobileFormData mobileFormData) {
		// TODO Auto-generated method stub
		return mobileFormData;
	}

	/**
	 * 设置字段JSON
	 * 
	 * @param fieldJson
	 * @param key
	 * @param label
	 * @param value
	 * @param controlType
	 */
	private void setFieldJson(JSONArray fieldJson, String key, String label,
			Object value) {
		if (BeanUtils.isEmpty(value))
			value = "";
		JSONObject fieldObj = new JSONObject();
		fieldObj.element("key", key);
		fieldObj.element("label", label);
		fieldObj.element("value", value);
		fieldJson.add(fieldObj);
	}

	/**
	 * 获得手机表单的数据
	 * 
	 * @param bpmFormDef
	 * @param pkValue
	 * @param processRun
	 * @param mobileFormData
	 * @param nodeId 
	 * @return
	 * @throws Exception
	 */
	public MobileFormData getMobileFormData(BpmFormDef bpmFormDef,
			String pkValue, ProcessRun processRun, MobileFormData mobileFormData,Long userId, String nodeId,String ctxPath)
			throws Exception {
		if (BeanUtils.isEmpty(bpmFormDef))
			return mobileFormData;

		Long tableId = bpmFormDef.getTableId();
		if (BeanUtils.isEmpty(tableId) || tableId.longValue() == 0L)
			return mobileFormData;
		BpmFormTable table = bpmFormTableDao.getById(tableId);
		Map<String, String> tableMap = this.parseTableMap(bpmFormDef, table);
		//表单中的当前可以用的意见
		//判断是否为子流程
		String parentActDefId = processRunDao.getParentProcessRunActDefId(processRun);
		Map<String, Map<String, String>> permission = null;
		if(StringUtil.isEmpty(parentActDefId)){
			permission = bpmFormRightsService.getByFormKeyAndUserId(bpmFormDef.getFormKey(), userId,processRun.getActDefId(), nodeId,PlatformType.mobile);
		}else{
			permission = bpmFormRightsService.getByFormKeyAndUserId(bpmFormDef.getFormKey(), userId,processRun.getActDefId(), nodeId, parentActDefId,PlatformType.mobile);
		}
		return this.getFormData(table, pkValue, processRun.getActInstId(), tableMap,permission,ctxPath);
	}

	/**
	 * 解析表单的表对应的字段（避免出现版本问题）
	 * 
	 * @param bpmFormDef
	 * @param table
	 * @return
	 */
	private Map<String, String> parseTableMap(BpmFormDef bpmFormDef,
			BpmFormTable table) {
		Map<String, String> tableMap = new HashMap<String, String>();
		String html = bpmFormDef.getHtml();
		// if (BeanUtils.isNotEmpty(bpmFormDef.getMobileHtml()))//
		// 如果存在手机表单则取手机表单
		// html = bpmFormDef.getMobileHtml();

		if (BeanUtils.isEmpty(html))
			return null;
		Document doc = Jsoup.parseBodyFragment(html);
		// 设计类型：通过表生成
		if (bpmFormDef.getDesignType() == BpmFormDef.DesignType_FromTable) {

			// 遍历主表字段
			parseMainField(doc, tableMap);
			// 处理子表。
			parseSubTable(doc, tableMap);
			// 解析意见
			parseOpinion(doc, tableMap);
		} else {
			ParseReult result = new ParseReult();
			// 解析子表
			List<BpmFormTable> subTableList = FormUtil.parseSubTableHtml(doc,
					result);
			// 解析主表。
			BpmFormTable mainTable = FormUtil.parseMainTable(doc,
					table.getTableName(), table.getTableDesc(), result);

			// 设置子表。
			mainTable.setSubTableList(subTableList);

			this.setTableMap(mainTable, tableMap);
			// 解析意见
			this.parseOpinion(doc, tableMap);
		}

		return tableMap;

	}

	/**
	 * 
	 * @param doc
	 * @param tableMap
	 *            key:（表名+:+字段）value：字段
	 */
	private void parseOpinion(Document doc, Map<String, String> tableMap) {
		Elements list = doc.select("[name^=opinion:]");
		JSONArray jsonAry = new JSONArray();
		for (Iterator<Element> it = list.iterator(); it.hasNext();) {
			Element el = it.next();
			String name = el.attr("name");
			String memo = el.attr("opinionname");
			JSONObject obj = new JSONObject();
			obj.put("name", name);
			obj.put("memo", memo);
			jsonAry.add(obj);
		}
		tableMap.put("opinion", jsonAry.toString());
	}

	/**
	 * 
	 * @param bpmFormTable
	 * @param tableMap
	 *            key:（表名+:+字段）value：字段
	 */
	private void setTableMap(BpmFormTable bpmFormTable,
			Map<String, String> tableMap) {
		if (BeanUtils.isEmpty(bpmFormTable))
			return;
		this.setTableMapByTable(bpmFormTable, tableMap);
		for (BpmFormTable subTable : bpmFormTable.getSubTableList()) {
			this.setTableMapByTable(subTable, tableMap);
		}

	}

	/**
	 * 
	 * @param bpmFormTable
	 * @param tableMap
	 */
	private void setTableMapByTable(BpmFormTable bpmFormTable,
			Map<String, String> tableMap) {
		String mainTable = bpmFormTable.getTableName();

		for (BpmFormField bpmFormField : bpmFormTable.getFieldList()) {
			String fieldName = bpmFormField.getFieldName();
			String key = mainTable + ":" + fieldName;
			tableMap.put(key.toLowerCase(), fieldName.toLowerCase());
		}
	}

	/**
	 * 解析主表
	 * 
	 * @param doc
	 * @param tableMap
	 *            key:（表名+:+字段）value：字段
	 */
	private void parseMainField(Document doc, Map<String, String> tableMap) {
		Elements list = doc.select("[name^=m:]");
		for (Iterator<Element> it = list.iterator(); it.hasNext();) {
			Element el = it.next();
			String name = el.attr("name");
			this.setTableMap(name, tableMap);
		}
	}

	/**
	 * 解析子表
	 * 
	 * @param doc
	 * @param tableMap
	 *            key:（表名+:+字段）value：字段
	 */
	private void parseSubTable(Document doc, Map<String, String> tableMap) {
		Elements list1 = doc.select("div[type=subtable]");
		for (Iterator<Element> it1 = list1.iterator(); it1.hasNext();) {
			Element subTable = it1.next();
			Elements list = subTable.select("[name^=s:]");
			for (Iterator<Element> it = list.iterator(); it.hasNext();) {
				Element el = it.next();
				String name = el.attr("name");
				this.setTableMap(name, tableMap);
			}
		}
	}

	/**
	 * 存放表单
	 * 
	 * @param name
	 * @param tableMap
	 *            <String,String> key:（表名+:+字段）value：字段
	 */
	private void setTableMap(String name, Map<String, String> tableMap) {
		String[] names = name.split(":");
		if (BeanUtils.isNotEmpty(names) && names.length >= 2) {
			String key = (names[1] + ":" + names[2]).toLowerCase();
			tableMap.put(key, names[2].toLowerCase());
		}
	}

	/**
	 * 手机表单的数据
	 * 
	 * @param tableId
	 * @param pkValue
	 * @param actInstId
	 * @param tableMap
	 * @param permission 
	 * @return
	 * @throws Exception
	 */
	private MobileFormData getFormData(BpmFormTable table, String pkValue,
			String actInstId, Map<String, String> tableMap, Map<String, Map<String, String>> permission,String ctxPath) throws Exception {
		MobileFormData formData = new MobileFormData();
		Long tableId = table.getTableId();

		// 取出表单数据
		BpmFormData bpmFormData = bpmFormHandlerService.getBpmFormData(tableId,
				pkValue, actInstId, null, null,false);

		formData.setTableId(table.getTableId());
		formData.setTableName(table.getTableDesc());

		// 设置主表字段数据
		this.setMainTableData(formData, bpmFormData, tableMap, table,ctxPath);
		// 设置意见
		formData.setOptions(bpmFormData.getOptions());
		Map<String, String> optionPermission = getOptionPermission(permission);
		// 设置意见
		formData.setOpinions(this.setOptionMaps(bpmFormData.getOptions(),
				tableMap,optionPermission));
		formData.setOption(isOption(optionPermission));
		// 设置子表的数据
		this.setSubDataList(formData, bpmFormData, tableMap, tableId);

		return formData;
	}

	private boolean isOption(Map<String, String> optionPermission) {
		boolean flag = true;
		for (Iterator<Entry<String,String>> it = optionPermission
				.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String,String> e = (Map.Entry<String,String>) it
					.next();
			String val = e.getValue();
			if("w".equalsIgnoreCase(val)||"b".equalsIgnoreCase(val))
				return false;
		}
		return flag;
	}

	private Map<String, String> getOptionPermission(
			Map<String, Map<String, String>> permission) {
		return permission.get("opinion");
	}

	private String setOptionMaps(Map<String, String> options,
			Map<String, String> tableMap, Map<String, String> optionPermission) {
		if (BeanUtils.isEmpty(tableMap))
			return "";
		String option = tableMap.get("opinion");
		if (StringUtils.isEmpty(option))
			return "";
		JSONArray jsonAry = JSONArray.fromObject(option);
		JSONArray jAry  = new JSONArray();
		for (Object obj : jsonAry) {
			JSONObject json = JSONObject.fromObject(obj);
			String name = json.getString("name");
			String key  = name.split(":")[1];
			String memo = json.getString("memo");
			String value = options.get(key);
			String permission =optionPermission.get(key);
			JSONObject j = new JSONObject();
			j.put("label", memo);
			j.put("key", key);
			j.put("name", name);
			j.put("value", StringUtils.isEmpty(value)?"":value);
			j.put("isShow", "w".equalsIgnoreCase(permission)||"b".equalsIgnoreCase(permission)?true:false);
			jAry.add(j);
		}
		return jAry.toString();

	}

	/**
	 * 设置主表的数据
	 * 
	 * @param formData
	 * @param bpmFormData
	 * @param tableMap
	 * @param table
	 */
	private void setMainTableData(MobileFormData formData,
			BpmFormData bpmFormData, Map<String, String> tableMap,
			BpmFormTable table,String ctxPath) {
		// 根据主表ID取出主表字段列表
		List<BpmFormField> mainFieldList = bpmFormFieldService
				.getAllByTableId(table.getTableId());
		JSONArray fieldJson = new JSONArray();
		if (BeanUtils.isEmpty(mainFieldList))
			return;
		for (int i = 0; i < mainFieldList.size(); i++) {
			BpmFormField field = mainFieldList.get(i);
			// 字段是否支持手机客户端访问
			// if(field.getIsAllowMobile().shortValue() ==(short)1){
			String fieldName = field.getFieldName();
			String fieldDesc = field.getFieldDesc();
			String value = "";
			// 检查字段是否存在
			if (!checkField(table, fieldName, tableMap))
				continue;

			if (!bpmFormData.getMainFields().isEmpty()) {
				if (BeanUtils.isNotEmpty(bpmFormData.getMainFields().get(
						fieldName.toLowerCase())))
					value = bpmFormData.getMainFields()
							.get(fieldName.toLowerCase()).toString();
			}
			if (value.equalsIgnoreCase("\"null\""))
				value = "";
			// 处理附件
			if (field.getControlType().shortValue() == FieldPool.ATTACHEMENT)
				value = parseFile(value,ctxPath);

			this.setFieldJson(fieldJson, fieldName, fieldDesc, value);
			// }
		}
		if (!JSONUtils.isNull(fieldJson))
			formData.setFields(fieldJson.toString());
	}

	/**
	 * 设置子表的数据
	 * 
	 * @param formData
	 *            手机表单数据
	 * @param bpmFormData
	 *            表单数据（从数据库取出）
	 * @param tableMap
	 *            表字段对应表<表:字段,字段>
	 * @param tableId
	 *            表ID
	 */
	private void setSubDataList(MobileFormData formData,
			BpmFormData bpmFormData, Map<String, String> tableMap, Long tableId) {
		// 手机表单子表
		List<MobileSubTableData> subDataList = new ArrayList<MobileSubTableData>();
		// 子表数据列表
		List<SubTable> subTableDataList = bpmFormData.getSubTableList();
		// 根据主表Id获取子表列表
		List<BpmFormTable> subTableList = bpmFormTableDao
				.getSubTableByMainTableId(tableId);
		if(BeanUtils.isEmpty(subTableDataList))
			return;
		for (int i = 0; i < subTableList.size(); i++) {
			BpmFormTable subFormTable = subTableList.get(i);
			SubTable subTable = subTableDataList.get(i);
			// 构建手机表单子表
			MobileSubTableData subData = new MobileSubTableData();
			// 子表字段
			Map<String, String> subFields = null;
			// 子表 表名
			subData.setTableName(subFormTable.getTableName());
			// 子表 表描述
			subData.setTableDesc(subFormTable.getTableDesc());
			// 子表 ID
			subData.setTableId(subFormTable.getTableId());
			// 子表数据
			List<Map<String, Object>> subtableDataList = subTable.getDataList();
			List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
			List<Map<String, String>> subMobileFields = new ArrayList<Map<String, String>>();
			List<BpmFormField> subFieldList = bpmFormFieldService
					.getByTableId(subFormTable.getTableId());
			for (BpmFormField subField : subFieldList) {
				// if(subField.getIsAllowMobile()==(short)1){
				subFields = new HashMap<String, String>();
				// 检查字段是否存在
				if (!checkField(subFormTable, subField.getFieldName(), tableMap))
					continue;
				subFields.put("fieldKey", subField.getFieldName());
				subFields.put("fieldVal", subField.getFieldDesc());
				subMobileFields.add(subFields);
				// }
			}
			subData.setFields(subMobileFields);
			for (Map<String, Object> data : subtableDataList) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				for (BpmFormField subField : subFieldList) {
					// if(subField.getIsAllowMobile()==(short)1){
					String val = "";
					if (BeanUtils.isNotEmpty(data.get(subField.getFieldName()
							.toLowerCase())))
						val = data.get(subField.getFieldName().toLowerCase())
								.toString();
					if (val.equalsIgnoreCase("\"null\""))
						val = "";

					dataMap.put(subField.getFieldName(), val);
					// }
				}
				dataList.add(dataMap);
			}
			subData.setDataList(dataList);
			subDataList.add(subData);
		}
		formData.setSubTableList(subDataList);
	}

	/**
	 * 检查该字段是否存在
	 * 
	 * @param table
	 * @param fieldName
	 * @param tableMap
	 * @return
	 */
	private boolean checkField(BpmFormTable table, String fieldName,
			Map<String, String> tableMap) {
		if (BeanUtils.isEmpty(table))
			return false;
		String key = (table.getTableName() + ":" + fieldName).toLowerCase();
		String value = tableMap.get(key);
		if (BeanUtils.isNotEmpty(value))
			return true;
		return false;

	}

	/**
	 * 处理附件问题
	 * 
	 * @param value
	 * @return
	 */
	private String parseFile(String value,String ctxPath) {
		if (StringUtils.isEmpty(value))
			return "";
		StringBuffer fileValue = new StringBuffer();
		value = value.replaceAll("￥@@￥", "\"");
		if (value.equalsIgnoreCase("\"null\""))
			return "";

		try {
			JSONArray jsonAry = JSONArray.fromObject(value);
			for (int i = 0; i < jsonAry.size(); i++) {
				JSONObject json = (JSONObject) jsonAry.get(i);
				String id = (String) json.get("id");
				String name = (String) json.get("name");
				fileValue.append(MobileFormHandlerService.getFile(id, name,ctxPath));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileValue.toString();
	}

	/**
	 * 获取附件
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public static String getFile(String id, String name,String ctxPath) {
		String file = name + "," + name;
		return "<span class='attachement-span'><span fileId='" + id
				+ "' name='attach' file='" + file
				+ "' ><a class='attachment' target='_blank' onclick='downloadFile(\""+id+"\",\""+name+"\");'   href='#' title='" + name + "'>" + name + "</a></span>&nbsp;</span>";
	}

	// TODO ============= 以下是起始表单的数据 =====================

	public MobileFormData getMobileFormData(BpmFormDef bpmFormDef,
			String pkValue, MobileFormData mobileFormData,String ctxPath) throws Exception {
		if (BeanUtils.isEmpty(bpmFormDef))
			return mobileFormData;

		Long tableId = bpmFormDef.getTableId();
		if (BeanUtils.isEmpty(tableId) || tableId == 0L)
			return mobileFormData;
		BpmFormTable table = bpmFormTableDao.getById(tableId);
		Map<String, String> tableMap = this.parseTableMap(bpmFormDef, table);
		return this.getFormData(tableId, pkValue, tableMap,ctxPath);
	}

	private MobileFormData getFormData(Long tableId, String pkValue,
			Map<String, String> tableMap,String ctxPath) throws Exception {
		// TODO Auto-generated method stub

		MobileFormData formData = new MobileFormData();
		BpmFormTable table = bpmFormTableDao.getById(tableId);

		// 取出表单数据
		BpmFormData bpmFormData = bpmFormHandlerService.getBpmFormData(tableId,
				pkValue, null, null, null,false);

		formData.setTableId(table.getTableId());
		formData.setTableName(table.getTableDesc());

		// 设置主表字段数据
		this.setMainData(formData, bpmFormData, tableMap, table,ctxPath);
		// 设置意见
		formData.setOptions(bpmFormData.getOptions());
		// 设置子表的数据
		this.setSubDataList(formData, bpmFormData, tableMap, tableId);
		return formData;
	}

	/**
	 * 设置主表的数据
	 * 
	 * @param formData
	 * @param bpmFormData
	 * @param tableMap
	 * @param table
	 */
	private void setMainData(MobileFormData formData, BpmFormData bpmFormData,
			Map<String, String> tableMap, BpmFormTable table,String ctxPath) {
		// 根据主表ID取出主表字段列表
		List<BpmFormField> mainFieldList = bpmFormFieldService
				.getAllByTableId(table.getTableId());
		JSONArray fieldJson = new JSONArray();
		if (BeanUtils.isEmpty(mainFieldList))
			return;
		for (int i = 0; i < mainFieldList.size(); i++) {
			BpmFormField field = mainFieldList.get(i);
			// 字段是否支持手机客户端访问
			// if(field.getIsAllowMobile().shortValue() ==(short)1){
			String fieldName = field.getFieldName();
			String fieldDesc = field.getFieldDesc();
			Short controlType = field.getControlType();
			String value = "";
			// 检查字段是否存在
			if (!checkField(table, fieldName, tableMap))
				continue;

			if (!bpmFormData.getMainFields().isEmpty()) {
				if (BeanUtils.isNotEmpty(bpmFormData.getMainFields().get(
						fieldName.toLowerCase())))
					value = bpmFormData.getMainFields()
							.get(fieldName.toLowerCase()).toString();
			}
			if (value.equalsIgnoreCase("\"null\""))
				value = "";
			// 处理附件
			if (field.getControlType().shortValue() == FieldPool.ATTACHEMENT)
				value = parseFile(value,ctxPath);

			this.setFieldJson(fieldJson, fieldName, fieldDesc, value,
					controlType);
			// }
		}
		if (!JSONUtils.isNull(fieldJson))
			formData.setFields(fieldJson.toString());
	}

	private void setFieldJson(JSONArray fieldJson, String key, String label,
			String value, Short controlType) {
		if (BeanUtils.isEmpty(value))
			value = "";
		JSONObject fieldObj = new JSONObject();
		fieldObj.element("key", key);
		fieldObj.element("label", label);
		fieldObj.element("value", value);
		fieldObj.element("controlType", controlType);
		fieldJson.add(fieldObj);
	}

}
