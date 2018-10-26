package com.hotent.mobile.service.form;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.engine.FreemarkEngine;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.mobile.dao.form.BpmMobileFormDao;
import com.hotent.mobile.model.form.BpmMobileForm;
import com.hotent.platform.dao.form.BpmFormDefDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormRun;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.TeamModel;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmFormRunService;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * <pre>
 * 对象功能:手机表单 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-04-02 15:09:58
 * </pre>
 */
@Service
public class BpmMobileFormService extends BaseService<BpmMobileForm> {
	@Resource
	private BpmMobileFormDao dao;

	@Resource
	private BpmFormDefDao bpmFormDefDao;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private FreemarkEngine freemarkEngine;
	@Resource
	private BpmDefinitionService definitionService;
	@Resource
	private BpmFormRunService bpmFormRunService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	public BpmMobileFormService() {
	}

	@Override
	protected IEntityDao<BpmMobileForm, Long> getEntityDao() {
		return dao;
	}
	
	/**
	 * 通过formId获取默认手机表单
	 * @param formId
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getByFormId(Long formId) throws Exception {
		return getByMap(null,formId,null, true);
	}
	/**
	 * 通过formKey获取默认手机表单
	 * @param formKey
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getByFormKey(String formKey) throws Exception {
		return getByMap(formKey, null,1, true);
	}
	/**
	 * 
	 * @param formKey
	 * @param formId 如果为空 
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getByMap(String formKey, Long formId,Integer isDefault) throws Exception {
		return getByMap(formKey, formId,isDefault, true);
	}

	/**
	 * 如果取不到表单数据，取默认表单
	 * @param formKey
	 * @param formId
	 * @param isNullGen 是否生成默认表单
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getByMap(String formKey, Long formId,Integer isDefault,boolean isNullGen) throws Exception {
		List<BpmMobileForm> list = null;

		list = dao.getByMap(formKey,formId,isDefault);
		if (BeanUtils.isNotEmpty(list))
			return list.get(0);
		else {
			if(isNullGen)
				return getDefaultForm(formKey, formId);
			else
				return null;
		}
	}

	/**
	 * 页面设置的获取数据
	 * 
	 * @param formKey
	 * @param formId
	 * @param isDefault 
	 * @param bpmFormTable
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getByMap(String formKey, Long formId,
			Integer isDefault, BpmFormTable bpmFormTable) throws Exception {
		BpmMobileForm bpmMobileForm = this.getByFormId(formId);
		bpmMobileForm.setFormKey(formKey);
		bpmMobileForm.setFormId(formId);
		bpmMobileForm.setIsDefault(isDefault);
		return bpmMobileForm;
	}

	/**
	 * 生产默认的模板
	 * @throws Exception
	 */
	public void  genDefaultForm() throws Exception{
		List<BpmFormDef> list = bpmFormDefDao.getDefaultFormDef();
	
		for (BpmFormDef bpmFormDef : list) {
			List<BpmMobileForm> list1 = dao.getByMap(bpmFormDef.getFormKey(), bpmFormDef.getFormDefId(),1);
			if (BeanUtils.isNotEmpty(list1))
				continue;
			
			this.genDefaultForm(bpmFormDef.getFormKey(), bpmFormDef.getFormDefId(), bpmFormDef.getTableId());
		}
	}
	
	/**
	 * 生产默认的模板
	 * @param formKey
	 * @param tableId
	 * @param long1 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void genDefaultForm(String formKey,Long formId,Long tableId ) throws Exception{
		if(BeanUtils.isEmpty(tableId) || tableId.longValue() <=0 )
			return;
		BpmMobileForm bpmMobileForm = new BpmMobileForm();
		bpmMobileForm.setId(UniqueIdUtil.genId());
		bpmMobileForm.setFormId(formId);
		bpmMobileForm.setFormKey(formKey);
		bpmMobileForm.setIsDefault(1);
		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);

		bpmMobileForm.setFormJson(getFormJson(bpmFormTable));
		bpmMobileForm = this.setBpmMobileForm(bpmMobileForm,bpmFormTable, true);
		
		dao.add(bpmMobileForm);
	}
	
	
	/**
	 * 获取默认的form
	 * @param formKey
	 * @param formId
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getDefaultForm(String formKey, Long formId)
			throws Exception {
		if(BeanUtils.isEmpty(formKey) && BeanUtils.isEmpty(formId))
			return null;
		if(BeanUtils.isEmpty(formKey) && formId == 0L)
			return null;
//		if(BeanUtils.isEmpty(formId) && formKey == 0L)
//			return null;
		BpmMobileForm bpmMobileForm = new BpmMobileForm();
		bpmMobileForm.setFormId(formId);
		bpmMobileForm.setFormKey(formKey);
		Long tableId = 0L;
		if (BeanUtils.isNotEmpty(formId) && formId > 0L) {
			BpmFormDef bpmFormDef = bpmFormDefDao.getById(formId);
			if (BeanUtils.isNotEmpty(bpmFormDef))
				tableId = bpmFormDef.getTableId();
		} else {
			BpmFormDef bpmFormDef = bpmFormDefDao
					.getDefaultVersionByFormKey(formKey);
			if (BeanUtils.isNotEmpty(bpmFormDef))
				tableId = bpmFormDef.getTableId();
		}
		if(tableId == 0)
			return null;
		
		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);

		bpmMobileForm.setFormJson(getFormJson(bpmFormTable));
		return this.setBpmMobileForm(bpmMobileForm,bpmFormTable, true);
	}

	/**
	 * 获取默认的表单数据
	 * 
	 * @param bpmFormTable
	 * @return
	 */
	private String getFormJson(BpmFormTable bpmFormTable) {
		String teams = bpmFormTable.getTeam();
		JSONObject json = new JSONObject();
		json.element("team", new JSONArray());
		JSONArray team = new JSONArray();
		if (StringUtils.isNotEmpty(teams)) {
			JSONObject jsonTeam = JSONObject.fromObject(teams);
			team = JSONArray.fromObject(jsonTeam.get("team"));
		}

		if (JSONUtil.isEmpty(team))
			setDefaultTeam(bpmFormTable, json);
		else
			json.element("team", team);

		// 子表
		List<BpmFormTable> subList = bpmFormTable.getSubTableList();
		JSONArray subAry = new JSONArray();
		for (BpmFormTable subTable : subList) {
			JSONObject subJson = new JSONObject();

			JSONArray fieldAry = new JSONArray();
			for (BpmFormField subField : subTable.getFieldList()) {
				JSONObject fieldJson = new JSONObject();
				fieldJson.element("fieldName", subField.getFieldName())
						.element("fieldDesc", subField.getFieldDesc());
				fieldAry.add(fieldJson);
			}
			subJson.element("tableName", subTable.getTableName())
					.element("tableDesc", subTable.getTableDesc())
					.element("fieldList", fieldAry);
			subAry.add(subJson);
		}
		json.accumulate("sub", subAry);
		json.accumulate("title", bpmFormTable.getTableDesc());
		return json.toString();
	}

	/**
	 * 设置默认分组
	 * 
	 * @param bpmFormTable
	 * @param json
	 */
	private void setDefaultTeam(BpmFormTable bpmFormTable, JSONObject json) {
		List<BpmFormField> fieldList = bpmFormTable.getFieldList();
		JSONArray teamField = new JSONArray();
		for (BpmFormField bpmFormField : fieldList) {
			JSONObject fieldJson = new JSONObject();
			fieldJson.element("fieldName", bpmFormField.getFieldName());
			fieldJson.element("fieldDesc", bpmFormField.getFieldDesc());
			teamField.add(fieldJson);
		}

		JSONArray team = new JSONArray();
		JSONObject teamJson = new JSONObject();
		teamJson.element("teamName", "");
		teamJson.element("teamField", teamField);
		team.add(teamJson);
		json.element("team", team);
	}

	/**
	 * 
	 * 保存手机表单
	 * 
	 * @param bpmMobileForm
	 * @throws Exception
	 */
	public void save(BpmMobileForm bpmMobileForm) throws Exception {
		
		if (BeanUtils.isEmpty(bpmMobileForm.getId())) {
			bpmMobileForm.setId(UniqueIdUtil.genId());
			bpmMobileForm = setBpmMobileForm(bpmMobileForm, true);
			bpmMobileForm.setGuid(UUID.randomUUID().toString());
			dao.add(bpmMobileForm);
		} else {
			bpmMobileForm = setBpmMobileForm(bpmMobileForm, true);
			bpmMobileForm.setGuid(UUID.randomUUID().toString());
			dao.update(bpmMobileForm);
		}

	}

	/**
	 * 
	 * TODO方法名称描述
	 * 
	 * @param bpmMobileForm
	 * @param isGenTemplate
	 *            是否生成模板
	 * @return
	 * @throws Exception
	 *             BpmMobileForm
	 */
	private BpmMobileForm setBpmMobileForm(BpmMobileForm bpmMobileForm,
			boolean isGenTemplate) throws Exception {
		Long tableId = 0L;
		Long formId = bpmMobileForm.getFormId();
		if (formId > 0L) {
			BpmFormDef bpmFormDef = bpmFormDefDao.getById(formId);
			if (BeanUtils.isNotEmpty(bpmFormDef))
				tableId = bpmFormDef.getTableId();
		} else {
			BpmFormDef bpmFormDef = bpmFormDefDao
					.getDefaultVersionByFormKey(bpmMobileForm.getFormKey());
			if (BeanUtils.isNotEmpty(bpmFormDef))
				tableId = bpmFormDef.getTableId();
		}

		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
		return this
				.setBpmMobileForm(bpmMobileForm, bpmFormTable, isGenTemplate);
	}

	/**
	 * 
	 * 设置手机表单
	 * 
	 * @param bpmMobileForm
	 * @param bpmFormTable
	 * @param isGenTemplate
	 *            是否生成模板
	 * @return  BpmMobileForm
	 * @throws Exception
	 *            
	 */
	private BpmMobileForm setBpmMobileForm(BpmMobileForm bpmMobileForm,
			BpmFormTable bpmFormTable, boolean isGenTemplate) throws Exception {
		String json = bpmMobileForm.getFormJson();
		if (StringUtils.isEmpty(json))
			return bpmMobileForm;
		JSONObject jsonObject = JSONObject.fromObject(json);
		if (JSONUtil.isEmpty(jsonObject))
			return bpmMobileForm;

		List<BpmFormField> fields = bpmFormTable.getFieldList();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("title", jsonObject.get("title"));
		// 设置主表分组
		this.setTeamFields(map, bpmFormTable, fields, jsonObject);
		// 设置子表
		this.setSubTable(map, bpmFormTable.getSubTableList(), jsonObject);

		String fieldControl = getMobileTemplate("fieldMacro");
		String formTemplate = getMobileTemplate("formTemplate");

		String html = freemarkEngine.parseByStringTemplate(map, fieldControl
				+ formTemplate);
		bpmMobileForm.setHtml(html);

		if (isGenTemplate) {
			String template = getTemplate(map);
			bpmMobileForm.setTemplate(template);
		}

		return bpmMobileForm;
	}

	/**
	 * 获取模板
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	private String getTemplate(Map<String, Object> map) throws Exception {
		String fieldControl = getMobileTemplate("mobileFieldMacro");
		String formTemplate = getMobileTemplate("mobileFormTemplate");
		return freemarkEngine.parseByStringTemplate(map, fieldControl
				+ formTemplate);
	}

	/**
	 * 
	 * 设置子表
	 * 
	 * @param map
	 * @param subTableList
	 * @param jsonObject
	 * @return List<BpmFormTable>
	 * @since 1.0.0
	 */
	private List<BpmFormTable> setSubTable(Map<String, Object> map,
			List<BpmFormTable> subTableList, JSONObject jsonObject) {
		Object sub = jsonObject.get("sub");
		if (JSONUtil.isEmpty(sub))
			return null;
		Map<String, List<BpmFormField>> subTableMap = new HashMap<String, List<BpmFormField>>();
		for (BpmFormTable bpmFormTable : subTableList) {
			subTableMap.put(bpmFormTable.getTableName(),
					bpmFormTable.getFieldList());
		}

		JSONArray subJson = JSONArray.fromObject(sub);
		List<BpmFormTable> list = new ArrayList<BpmFormTable>();
		for (Object obj : subJson) {
			BpmFormTable bpmFormTable = new BpmFormTable();
			JSONObject jsonObj = (JSONObject) obj;
			String tableName = (String) jsonObj.get("tableName");
			String tableDesc = (String) jsonObj.get("tableDesc");
			bpmFormTable.setTableName(tableName);
			bpmFormTable.setTableDesc(tableDesc);
			bpmFormTable.setIsMain(BpmFormTable.NOT_MAIN);
			List<BpmFormField> fields = subTableMap.get(tableName);
			// 获取字段
			JSONArray fieldArray = (JSONArray) jsonObj.get("fieldList");
			List<BpmFormField> fieldList = new ArrayList<BpmFormField>();
			for (Object object : fieldArray) {
				JSONObject fieldObj = (JSONObject) object;
				String fieldName = (String) fieldObj.get("fieldName");
				BpmFormField bpmFormField = this.getBpmFormField(bpmFormTable,
						fields, fieldName);
				if (BeanUtils.isNotEmpty(bpmFormField))
					fieldList.add(bpmFormField);
			}

			bpmFormTable.setFieldList(fieldList);
			list.add(bpmFormTable);
		}
		map.put("subTables", list);
		return list;
	}

	/**
	 * 获取模板的内容
	 * 
	 * @param templateName
	 * @return String
	 * @since 1.0.0
	 */
	private String getMobileTemplate(String templateName) {
		String path = FileUtil.getClassesPath() + "template" + File.separator
				+ "mobile" + File.separator + templateName + ".ftl";
		return FileUtil.readFile(path);
	}

	/**
	 * 设置分组字段
	 * 
	 * @param table
	 * 
	 * @param fieldsMap
	 * @param table
	 * @param jsonObject
	 * @return
	 */
	private List<TeamModel> setTeamFields(Map<String, Object> map,
			BpmFormTable table, List<BpmFormField> fields, JSONObject jsonObject) {
		Object team = jsonObject.get("team");
		if (JSONUtil.isEmpty(team))
			return null;
		JSONArray teamJson = JSONArray.fromObject(team);
		List<TeamModel> list = new ArrayList<TeamModel>();

		for (Object obj : teamJson) {
			TeamModel teamModel = new TeamModel();
			JSONObject jsonObj = (JSONObject) obj;
			String teamName = (String) jsonObj.get("teamName");
			teamModel.setTeamName(teamName);
			// 获取字段
			JSONArray fieldArray = (JSONArray) jsonObj.get("teamField");
			List<BpmFormField> teamFields = new ArrayList<BpmFormField>();
			for (Object object : fieldArray) {
				JSONObject fieldObj = (JSONObject) object;
				String fieldName = (String) fieldObj.get("fieldName");
				BpmFormField bpmFormField = this.getBpmFormField(table, fields,
						fieldName);
				if (BeanUtils.isNotEmpty(bpmFormField))
					teamFields.add(bpmFormField);
			}
			teamModel.setTeamFields(teamFields);
			list.add(teamModel);
		}
		map.put("teamFields", list);
		return list;
	}

	/**
	 * 获取分组字段
	 * 
	 * @param table
	 * 
	 * @param fields
	 * @param fieldName
	 * @return
	 */
	private BpmFormField getBpmFormField(BpmFormTable table,
			List<BpmFormField> fields, String fieldName) {
		for (BpmFormField bpmFormField : fields) {
			if (bpmFormField.getFieldName().equalsIgnoreCase(fieldName)) {
				// 更新字段的name
				return this.setBpmFormFieldName(table, bpmFormField);
			}
		}
		return null;
	}

	private BpmFormField setBpmFormFieldName(BpmFormTable table,
			BpmFormField bpmFormField) {
		String ngPrefix = (table.getIsMain().shortValue() == BpmFormTable.IS_MAIN
				.shortValue() ? "main." : "item.");
		bpmFormField.setNgModel(ngPrefix
				+ bpmFormField.getFieldName().toLowerCase());
		
		 String prefix = (table.getIsMain().shortValue() ==
		 BpmFormTable.IS_MAIN .shortValue() ? "m_" : "s_");
		
		 bpmFormField.setNewName(prefix + table.getTableName() + "_" + bpmFormField.getFieldName());
		return bpmFormField;

	}

	/**
	 * 
	 * 预览
	 * 
	 * @param bpmMobileForm
	 * @return String
	 * @throws Exception
	 * 
	 */
	public String preview(BpmMobileForm bpmMobileForm) throws Exception {
		bpmMobileForm = this.setBpmMobileForm(bpmMobileForm, false);
		return bpmMobileForm.getHtml();
	}


	/**
	 * <pre>
	 * 获取发起流程时的手机表单
	 * 
	 * 流程是否跳过第一个节点：
	 * 1 是
	 * 		1.1 取第一个节点的表单。如果取到，返回
	 * 		1.2 取全局表单。如果取到，返回
	 * 		1.3 取开始节点表单 。如果取到，返回
	 * 2 否
	 * 		2.1 取开始节点表单 。如果取到，返回
	 * 		2.2 取全局表单。如果取到，返回
	 * </pre>
	 * @param actDefId 流程定义ACTDEFID
	 * @return
	 */
	public BpmMobileForm getMobileFormForStart(String actDefId) {
		BpmDefinition bpmDefinition = definitionService.getByActDefId(actDefId);
		if (bpmDefinition == null) {
			throw new RuntimeException("流程未定义");
		}

		BpmMobileForm form = null;

		Short toFirstNode = bpmDefinition.getToFirstNode();
		//TODO 
		if (toFirstNode != null && toFirstNode.shortValue() ==  1
				) {
			try {
				String firstNodeId = NodeCache.getFirstNodeId(actDefId).getNodeId();
				form = this.getNodeMobileForm(actDefId, firstNodeId);
			} catch (Exception e) {
				new RuntimeException(e);
			}
			if(form==null) {
				form = this.getGlobalMobileForm(actDefId);
			}
			
			if(form==null) {
				form = this.getStartMobileForm(actDefId);
			}
		} else {
			form = this.getStartMobileForm(actDefId);
			if (form == null) {
				form = this.getGlobalMobileForm(actDefId);
			}
		}
		
		
		return form;
	}
	
	

	/**
	 * <pre>
	 * 获取发起流程时的手机表单
	 * 
	 * 流程是否跳过第一个节点：
	 * 1 是
	 * 		1.1 取第一个节点的表单。如果取到，返回
	 * 		1.2 取全局表单。如果取到，返回
	 * 		1.3 取开始节点表单 。如果取到，返回
	 * 2 否
	 * 		2.1 取开始节点表单 。如果取到，返回
	 * 		2.2 取全局表单。如果取到，返回
	 * </pre>
	 * @param defId 流程定义ID
	 * @return
	 */
	public BpmMobileForm getMobileFormForStart(Long defId) {
		BpmDefinition def = bpmDefinitionService.getById(defId);
		if(def==null) {
			return null;
		}
		return this.getMobileFormForStart(def.getActDefId()); 
	}
	

	/**
	 * <pre>
	 * 获取发起流程时的手机表单
	 * 	1.1 取节点的表单。如果取到，返回
	 * 	1.2 取全局表单。如果取到，返回
	 * 	1.3 取开始节点表单 。如果取到，返回
	 * </pre>
	 * @param actDefId 	流程定义ACTDEFID
	 * @param nodeId	节点ID
	 * @return
	 */
	public BpmMobileForm getMobileFormCasacade(String actDefId,String nodeId){
		BpmMobileForm form = this.getNodeMobileForm(actDefId, nodeId);
		if(form==null) {
			form = this.getGlobalMobileForm(actDefId);
		}
		if(form==null) {
			form = this.getStartMobileForm(actDefId);
		}
		
		if(form==null) {
			BpmFormDef formDef = bpmFormDefService.getNodeFormDef(actDefId, nodeId, true);
			if(formDef!=null) {
				try {
					form = this.getByFormKey(formDef.getFormKey());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return form;
	}

	

	/**
	 * <pre>
	 * 获取发起流程时的手机表单
	 * 	1.1 取节点的表单。如果取到，返回
	 * 	1.2 取全局表单。如果取到，返回
	 * 	1.3 取开始节点表单 。如果取到，返回
	 * </pre>
	 * @param defId 	流程定义ID
	 * @param nodeId	节点ID
	 * @return
	 */
	public BpmMobileForm getMobileFormCasacade(Long defId,String nodeId){
		BpmDefinition def = bpmDefinitionService.getById(defId);
		if(def==null) {
			return null;
		}
		return this.getMobileFormCasacade(def.getActDefId(),nodeId);
	}
	
	

	/**
	 * 根据流程定义ACTDEFID，节点ID，取得流程节点手机表单定义。
	 * @param actDefId 	流程定义ACTDEFID
	 * @param nodeId 	节点ID
	 * @return
	 */
	public BpmMobileForm getNodeMobileForm(String actDefId,String nodeId){
		
		if(StringUtil.isNotEmpty(nodeId)){
			List<BpmMobileForm> defs = dao.getByActDefIdAndNodeId(actDefId, nodeId);
			if(BeanUtils.isNotEmpty(defs)){
				return defs.get(0);
			}
		}else {
			return null;
		}
		return null;
	}
	
	
	/**
	 * 根据流程定义ACTDEFID，节点ID，取得流程节点手机表单定义。
	 * @param actDefId 	流程定义ACTDEFID
	 * @param nodeId 	节点ID
	 * @return
	 */
	public BpmMobileForm getNodeMobileForm(Long defId,String nodeId){
		BpmDefinition def = bpmDefinitionService.getById(defId);
		if(def==null) {
			return null;
		}
		return this.getNodeMobileForm(def.getActDefId(),nodeId);
		
	}
	
	/**
	 * 根据流程定义ACTDEFID，取得流程开始手机表单定义
	 * @param actDefId
	 * @return
	 */
	public BpmMobileForm getStartMobileForm(String actDefId){
		List<BpmMobileForm> defs = dao.getByActDefIdAndSetType(actDefId, BpmNodeSet.SetType_StartForm);
		if(BeanUtils.isNotEmpty(defs)){
			if(BeanUtils.isNotEmpty(defs)){
				return defs.get(0);
			}
		}else{
			return null;
		}
		return null;
	}
	
	/**
	 * 根据流程定义ID，取得流程开始手机表单定义
	 * @param actDefId
	 * @return
	 */
	public BpmMobileForm getStartMobileForm(Long defId){
		BpmDefinition def = bpmDefinitionService.getById(defId);
		if(def==null) {
			return null;
		}
		return this.getStartMobileForm(def.getActDefId());
	}
	
	
	
	/**
	 * 根据流程定义ACTDEFID，取得流程全局手机表单定义
	 * @param actDefId
	 * @return
	 */
	public BpmMobileForm getGlobalMobileForm(String actDefId){
		List<BpmMobileForm> defs = dao.getByActDefIdAndSetType(actDefId, BpmNodeSet.SetType_GloabalForm);
		if(BeanUtils.isNotEmpty(defs)){
			return defs.get(0);
		}
		return null;
	}


	
	/**
	 * 根据流程定义ID，取得流程全局手机表单定义
	 * @param actDefId
	 * @return
	 */
	public BpmMobileForm getGlobalMobileForm(Long defId){
		BpmDefinition def = bpmDefinitionService.getById(defId);
		if(def==null) {
			return null;
		}
		return this.getGlobalMobileForm(def.getActDefId());
	}
	
	/**
	 * 根据流程实例ID和节点ID，获取手机表单
	 * @param instanceId 流程实例ID
	 * @param nodeId 节点ID
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getByProcessInstacne(String instanceId,String nodeId) throws Exception {
		BpmMobileForm mobileForm=null;
		Long mobileFormId = null ;
		
		BpmFormRun nodeFormRun = null;
		BpmFormRun globalFormRun = null;
		BpmFormRun startFormRun = null;
		
		//TODO 
		//运行时节点表单
		if(StringUtil.isNotEmpty(nodeId)&&mobileFormId==null) {
			BpmFormRun bpmFormRun = bpmFormRunService.getNodeFormRun(instanceId, nodeId);
			nodeFormRun = bpmFormRun ;
			if (bpmFormRun!=null) {
				mobileFormId = bpmFormRun.getMobileFormId();
			}
		}
		
		//运行时全局表单
		if(mobileFormId==null) {
			BpmFormRun bpmFormRun = bpmFormRunService.getGlobalForm(instanceId);
			globalFormRun = bpmFormRun; 
			if (bpmFormRun!=null) {
				mobileFormId = bpmFormRun.getMobileFormId();
			}
		}
		
		//运行时开始表单
		if(mobileFormId==null) {
			BpmFormRun bpmFormRun = bpmFormRunService.getStartFormRun(instanceId);
			startFormRun = bpmFormRun; 
			if (bpmFormRun!=null) {
				mobileFormId = bpmFormRun.getMobileFormId();
			}
		}
		
		
		
		if (mobileFormId != null && mobileFormId.longValue()>0L ) {
			try {
				mobileForm = this.getById(mobileFormId);
				if(BeanUtils.isNotEmpty(mobileForm))
					return mobileForm;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		
		

		//从PC节点表单，取手机表单
		if (mobileForm == null && nodeFormRun != null) {
			if (nodeFormRun.getFormdefId() != null && nodeFormRun.getFormdefId().longValue() >0L) {
				return  this.getByFormId(nodeFormRun.getFormdefId());
			}
		}

		if (mobileForm == null & nodeFormRun != null) {
			String formKey=nodeFormRun.getFormdefKey();
			if (StringUtil.isNotEmpty(formKey)) {
				return this.getByFormKey(formKey);
			}
		}
		
		//PC全局表单，取手机表单
		if (mobileForm == null ) {
			if (globalFormRun!=null && globalFormRun.getFormdefId() != null && globalFormRun.getFormdefId().longValue() >0L) {
				return this.getByFormId(globalFormRun.getFormdefId());
			}
		}
		if (mobileForm == null) {
			if (globalFormRun!=null && StringUtil.isNotEmpty(globalFormRun.getFormdefKey()) ) {
				return this.getByFormKey(globalFormRun.getFormdefKey());
			}
		}
		
		
		//PC全局表单，取手机表单
		if (mobileForm == null) {
			if (startFormRun!=null && startFormRun.getFormdefId() != null && startFormRun.getFormdefId().longValue() >0L) {
				return this.getByFormId(startFormRun.getFormdefId());
			}
		}

		if (mobileForm == null) {
			if (startFormRun!=null && StringUtil.isNotEmpty(startFormRun.getFormdefKey())) {
				return this.getByFormKey(startFormRun.getFormdefKey());
			}
		}

		return mobileForm;

	}
	
	
	
	/**
	 * 根据流程实例ID和节点ID，获取手机表单
	 * @param instanceId 流程实例ID
	 * @param nodeId 节点ID
	 * @return
	 * @throws Exception
	 */
	public BpmMobileForm getByProcessRun(Long runId,String nodeId) throws Exception {
		ProcessRun run = processRunService.getById(runId);
		if(run==null) 
			return null;
		return this.getByProcessInstacne( run.getActInstId(), nodeId);
	}
	
}
