package com.hotent.platform.service.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.form.BpmFormRightsDao;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormRights;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.system.Position;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.system.SysRole;
import com.hotent.platform.model.system.SysUserOrg;
import com.hotent.platform.service.system.PositionService;
import com.hotent.platform.service.system.SysOrgService;
import com.hotent.platform.service.system.SysRoleService;
import com.hotent.platform.service.system.SysUserOrgService;


/**
 * <pre>
 * 对象功能:表单权限 Service类。 存储表单字段，子表，意见等权限。 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:xwy 
 * 创建时间:2012-02-10 10:48:16
 * </pre>
 */
@Service
public class BpmFormRightsService {
	@Resource
	private BpmFormRightsDao bpmFormRightsDao;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private PositionService positionService;
	@Resource
	private SysOrgService sysOrgService;
	@Resource
	private SysUserOrgService sysUserOrgService;
	@Resource
	private BpmFormDefService bpmFormDefService;

	/**
	 * 获取默认的权限数据。
	 * 
	 * @param title
	 * @param memo
	 * @param type
	 * @return
	 */
	public JSONObject getPermissionJson(String title, String memo,int type) {
		String defJson = "{type:'everyone',id:'', fullname:''}";
		JSONObject json = new JSONObject();
		json.element("title", title);
		json.element("memo", memo);
        if(type!=4){
        	json.element("read", defJson);
    		json.element("write", defJson);
    		if(type !=2){
    			json.element("required", "{type:'none',id:'', fullname:''}");
    		}
		}else{
			json.element("show", "{addBtn:'true'}");     //子表显示可写：w  子表显示只读：r  为显示  其它为隐藏（y）   可以增加默认
		}
		return json;
	}

	/**
	 * 保存表单权限。
	 * 
	 * @param formDefId
	 *            表单KEY
	 * @param permission
	 *            权限JSON数据。
	 * @throws Exception
	 */
	public void save(BpmFormDef bpmFormDef, JSONObject permission,PlatformType platformType) throws Exception {
		Map<String, BpmFormField[]> selectorFieldMap = bpmFormTableService.getExecutorSelectorField(bpmFormDef.getTableId());
		save(null,null,bpmFormDef.getFormKey(),permission,selectorFieldMap,"",platformType);
	}
	
	/**
	 * 保存表单权限。
	 * 
	 * @param formDefId
	 *            表单KEY
	 * @param permission
	 *            权限JSON数据。
	 * @throws Exception
	 */
	public void save(String formKey, JSONObject permission,PlatformType platformType) throws Exception {
		save(null,null,formKey,permission,"",platformType);
	}

	
	/**
	 * 保存任务节点权限。
	 * 
	 * @param actDefId
	 *            流程定义id
	 * @param formDefId
	 *            表单定义id
	 * @param permission
	 *            权限JSON数据。
	 * @throws Exception
	 */
	public void save(String actDefId, String formKey, JSONObject permission, String parentActDefId,PlatformType platformType)throws Exception {
		save(actDefId,null,formKey,permission,parentActDefId,platformType);
	}
	
	
	
	/**
	 * 保存任务节点权限。
	 * 
	 * @param actDefId
	 *            流程定义id
	 * @param nodeId
	 *            流程节点id
	 * @param formDefId
	 *            表单定义id
	 * @param permission
	 *            权限JSON数据。
	 * @throws Exception
	 */
	private void save(String actDefId, String nodeId,String formKey,JSONObject permission,Map<String, BpmFormField[]> selectorFieldMap,PlatformType platformType) throws Exception {
		JSONArray fieldPermissions = permission.getJSONArray("field");
		JSONArray tablePermissions = permission.getJSONArray("subtable");
		JSONArray opinionPermissions = permission.getJSONArray("opinion");
		JSONArray subTableShows = permission.getJSONArray("subTableShows");
		List<BpmFormRights> list = new ArrayList<BpmFormRights>();		
		
		// 表单字段权限。
		if(StringUtil.isNotEmpty(nodeId)){
			bpmFormRightsDao.delByActDefIdAndNodeId(actDefId, nodeId,platformType);	
		}else if(StringUtil.isNotEmpty(actDefId)){
			bpmFormRightsDao.delByActDefId(actDefId, false,platformType);
		}else{
			bpmFormRightsDao.delByFormKey(formKey,false,platformType);
		}
		
		if (BeanUtils.isNotEmpty(fieldPermissions)) {
			for (Object obj : fieldPermissions) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.FieldRights,platformType);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				list.add(bpmFormRights);
				
				if(selectorFieldMap.containsKey(name)){
					String idName =selectorFieldMap.get(name)[1].getFieldName(); 
					JSONObject idJson = JSONObject.fromObject(json);
					idJson.element("title", idName);
					String idJsonStr = idJson.toString();
					BpmFormRights hiddenIdRights =  (BpmFormRights) bpmFormRights.clone();
					hiddenIdRights.setId(UniqueIdUtil.genId());
					hiddenIdRights.setName(idName);
					hiddenIdRights.setPermission(idJsonStr);
					list.add(hiddenIdRights);
				}
			}
		}
		// 子表权限。
		if (BeanUtils.isNotEmpty(tablePermissions)) {
			for (Object obj : tablePermissions) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.TableRights,platformType);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				list.add(bpmFormRights);
			}
		}
		// 表单意见权限。
		if (BeanUtils.isNotEmpty(opinionPermissions)) {
			for (Object obj : opinionPermissions) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.OpinionRights,platformType);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				list.add(bpmFormRights);
			}
		}		

		//子表的显示与否
		if(BeanUtils.isNotEmpty(subTableShows)){
			for (Object obj : subTableShows) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.TableShowRights,platformType);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				list.add(bpmFormRights);				
			}
		}
		
		for (BpmFormRights right : list) {
			bpmFormRightsDao.add(right);
		}
	}
	
	/**
	 * 保存任务节点权限。
	 * 
	 * @param actDefId
	 *            流程定义id
	 * @param nodeId
	 *            流程节点id
	 * @param formDefId
	 *            表单定义id
	 * @param permission
	 *            权限JSON数据。
	 * @throws Exception
	 */
	private void save(String actDefId, String nodeId,String formKey,JSONObject permission,Map<String, BpmFormField[]> selectorFieldMap, String parentActDefId,PlatformType platformType) throws Exception {
		JSONArray fieldPermissions = permission.getJSONArray("field");
		JSONArray tablePermissions = permission.getJSONArray("subtable");
		JSONArray opinionPermissions = permission.getJSONArray("opinion");
		JSONArray subTableShows = permission.getJSONArray("subTableShows");
		List<BpmFormRights> list = new ArrayList<BpmFormRights>();		
		
		// 表单字段权限。
		if(StringUtil.isNotEmpty(nodeId)){
			bpmFormRightsDao.delByActDefIdAndNodeId(actDefId, nodeId, parentActDefId,platformType);	
		}else if(StringUtil.isNotEmpty(actDefId)){
			bpmFormRightsDao.delByActDefId(actDefId, false, parentActDefId,platformType);
		}else{
			bpmFormRightsDao.delByFormKey(formKey,false,platformType);
		}
		
		if (BeanUtils.isNotEmpty(fieldPermissions)) {
			for (Object obj : fieldPermissions) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.FieldRights);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				bpmFormRights.setParentActDefId(parentActDefId);
				list.add(bpmFormRights);
				
				if(selectorFieldMap.containsKey(name)){
					String idName =selectorFieldMap.get(name)[1].getFieldName(); 
					JSONObject idJson = JSONObject.fromObject(json);
					idJson.element("title", idName);
					String idJsonStr = idJson.toString();
					BpmFormRights hiddenIdRights =  (BpmFormRights) bpmFormRights.clone();
					hiddenIdRights.setId(UniqueIdUtil.genId());
					hiddenIdRights.setName(idName);
					hiddenIdRights.setPermission(idJsonStr);
					bpmFormRights.setParentActDefId(parentActDefId);
					list.add(hiddenIdRights);
				}
			}
		}
		// 子表权限。
		if (BeanUtils.isNotEmpty(tablePermissions)) {
			for (Object obj : tablePermissions) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.TableRights);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				bpmFormRights.setParentActDefId(parentActDefId);
				list.add(bpmFormRights);
			}
		}
		// 表单意见权限。
		if (BeanUtils.isNotEmpty(opinionPermissions)) {
			for (Object obj : opinionPermissions) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.OpinionRights);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				bpmFormRights.setParentActDefId(parentActDefId);
				list.add(bpmFormRights);
			}
		}		

		//子表的显示与否
		if(BeanUtils.isNotEmpty(subTableShows)){
			for (Object obj : subTableShows) {
				String json = obj.toString();
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("title");
				BpmFormRights bpmFormRights = new BpmFormRights(
						UniqueIdUtil.genId(), formKey, name, json,
						BpmFormRights.TableShowRights);
				bpmFormRights.setActDefId(actDefId);
				bpmFormRights.setNodeId(nodeId);
				bpmFormRights.setParentActDefId(parentActDefId);
				list.add(bpmFormRights);				
			}
		}
		
		for (BpmFormRights right : list) {
			bpmFormRightsDao.add(right);
		}
	}
	
	
	
	/**
	 * 保存任务节点权限。
	 * 
	 * @param actDefId
	 *            流程定义id
	 * @param nodeId
	 *            流程节点id
	 * @param formDefId
	 *            表单定义id
	 * @param permission
	 *            权限JSON数据。
	 * @throws Exception
	 */
	public void save(String actDefId, String nodeId, String formKey,JSONObject permission, String parentActDefId,PlatformType platformType) throws Exception {
		BpmFormDef bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formKey);
		Map<String, BpmFormField[]> selectorFieldMap = bpmFormTableService.getExecutorSelectorField(bpmFormDef.getTableId());
		if(StringUtil.isEmpty(parentActDefId)){//子流程表单权限
			save(actDefId,nodeId,bpmFormDef.getFormKey(),permission,selectorFieldMap,platformType);
		}else{
			save(actDefId,nodeId,bpmFormDef.getFormKey(),permission,selectorFieldMap,parentActDefId,platformType);
		}
	}


	
	/**
	 * 根据流程定义id，任务节点id和表单id获取权限数据。
	 * 
	 * @param actDefId
	 *            流程定义ID
	 * @param nodeId
	 *            任务节点
	 * @param formKey
	 *            表单定义ID
	 * @return
	 */
	public Map<String, List<JSONObject>> getPermission(String formKey, String actDefId, String nodeId, String parentActDefId,Integer platform,PlatformType platformType) {
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		List<BpmFormRights> rightList = null;
		if(StringUtils.isNotEmpty(nodeId) ){ //具体节点的权限
			if(StringUtils.isNotEmpty(parentActDefId))
				rightList = bpmFormRightsDao.getBpmFormRights(formKey, actDefId,false,nodeId,false,parentActDefId,false,platform,platformType);
			else
				rightList = bpmFormRightsDao.getBpmFormRights(formKey, actDefId,false,nodeId,false,null,true,platform,platformType);
		}
		
		if(StringUtil.isNotEmpty(actDefId) && BeanUtils.isEmpty(rightList)) {//流程的全局菜单
			if(StringUtils.isNotEmpty(parentActDefId))
				rightList = bpmFormRightsDao.getBpmFormRights(formKey,actDefId,false,null,true,parentActDefId,false,platform,platformType);
			else
				rightList = bpmFormRightsDao.getBpmFormRights(formKey,actDefId,false,null,true,null,true,platform,platformType);
		}
		
		if(BeanUtils.isEmpty(rightList)){//表单管理的表单权限
			rightList = bpmFormRightsDao.getBpmFormRights(formKey, null, true, null, true, null, true, platform,platformType);
		}
			
		BpmFormDef bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formKey);
		if (BeanUtils.isEmpty(rightList)) {			  //默认的权限
			map = getPermissionByFormKey(bpmFormDef);
		}else{
			map = getPermissionByFormKey(rightList,bpmFormDef);
		}
		return map;
		

	}
	
	/**
	 * 根据流程定义id，任务节点id和表单id获取权限数据。
	 * 
	 * @param actDefId
	 *            流程定义ID
	 * @param nodeId
	 *            任务节点
	 * @param formKey
	 *            表单定义ID
	 * @return
	 */
	public Map<String, List<JSONObject>> getPermission(String formKey, String actDefId, String nodeId, String parentActDefId,PlatformType platformType) {
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		List<BpmFormRights> rightList = null;
		if(StringUtil.isNotEmpty(nodeId)){
			rightList = bpmFormRightsDao.getByActDefIdAndNodeId(formKey,actDefId, nodeId, parentActDefId,platformType);
		}
		if(StringUtil.isNotEmpty(actDefId) && BeanUtils.isEmpty(rightList)) {
			rightList = bpmFormRightsDao.getByActDefId(formKey,actDefId,false,parentActDefId,platformType);
		}
		if(BeanUtils.isEmpty(rightList)){
			rightList = bpmFormRightsDao.getByFormKey(formKey, false,platformType);
		}
			
		BpmFormDef bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formKey);
		if (BeanUtils.isEmpty(rightList)) {			
			map = getPermissionByFormKey(bpmFormDef);
		}else{
			map = getPermissionByFormKey(rightList,bpmFormDef);
		}
		return map;
	}

	
	/**
	 * 根据表ID和表单id获取表表单的权限。
	 * 
	 * @param tableId
	 *            数据表id
	 * @param formKey
	 *            表单定义key
	 * @return
	 */
	public Map<String, List<JSONObject>> getPermissionByTableId(Long tableId) {
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);	
		
		// 主表字段权限
		List<BpmFormField> fieldList = bpmFormTable.getFieldList();
		List<JSONObject> fieldJsonList = new ArrayList<JSONObject>();
		for (BpmFormField field : fieldList) {
			JSONObject permission = getPermissionJson(field.getFieldName(),field.getFieldDesc(),1);
			
			//增加字段的控件类型
			permission.put("controlType", field.getControlType());
			
			permission.put("tableId", tableId);
			permission.put("tableName", bpmFormTable.getTableName());
			permission.put("mainTableId", "");
			permission.put("mainTableName","");
			fieldJsonList.add(permission);
		}
		map.put("field", fieldJsonList);

		// 子表权限。
		List<JSONObject> tableJsonList = new ArrayList<JSONObject>();
		List<BpmFormTable> tableList = bpmFormTable.getSubTableList();
		for (BpmFormTable table : tableList) {
			// 子表整个表的权限
			JSONObject permission = getPermissionJson(table.getTableName(),table.getTableDesc(),2);
			//每个子表中的每个字段
			List<BpmFormField> subFieldList = table.getFieldList();
			List<JSONObject> subFieldJsonList = new ArrayList<JSONObject>();
			for (BpmFormField field : subFieldList) {
				JSONObject subPermission = getPermissionJson(field.getFieldName(),field.getFieldDesc(),1);
				
				//增加字段的控件类型
				subPermission.put("controlType", field.getControlType());
				
				subPermission.put("tableId", table.getTableId());
				subPermission.put("tableName", table.getTableName());
				subPermission.put("mainTableId", tableId);
				subPermission.put("mainTableName",bpmFormTable.getTableName());
				subFieldJsonList.add(subPermission);
			}
			permission.put("tableId", table.getTableId());
			permission.put("tableName", table.getTableName());
			permission.put("mainTableId", tableId);
			permission.put("mainTableName",bpmFormTable.getTableName());
			permission.put("subField", subFieldJsonList);
			tableJsonList.add(permission);
		}

		map.put("table", tableJsonList);
		
		//子表显示与否
		List<JSONObject> tableShowJsonList = new ArrayList<JSONObject>();
		for (BpmFormTable table : tableList) {
			JSONObject permission = getPermissionJson(table.getTableName(),table.getTableDesc(),4);
			permission.put("tableId", table.getTableId());
			permission.put("tableName", table.getTableName());
			permission.put("mainTableId", tableId);
			permission.put("mainTableName",bpmFormTable.getTableName());
			tableShowJsonList.add(permission);
		}
		
		map.put("tableShow", tableShowJsonList);
		/*List<BpmFormField> fieldList = bpmFormTable.getFieldList();
		List<JSONObject> fieldJsonList = new ArrayList<JSONObject>();
		for (BpmFormField field : fieldList) {
			JSONObject permission = getPermissionJson(field.getFieldName(),
					field.getFieldDesc(),1);
			fieldJsonList.add(permission);
		}
		List<JSONObject> tableJsonList = new ArrayList<JSONObject>();
		List<BpmFormTable> tableList = bpmFormTable.getSubTableList();
		for (BpmFormTable table : tableList) {
			JSONObject permission = getPermissionJson(table.getTableName(),
					table.getTableDesc(),2);
			tableJsonList.add(permission);
		}
		map.put("field", fieldJsonList);
		map.put("table", tableJsonList);*/
		return map;
	}
	
	/**
	 * 调整字段顺序
	 * @param rightList
	 * @param bpmFormDef
	 * @return
	 */
//	private List<BpmFormRights> adjustOrder(List<BpmFormRights> rightList, BpmFormDef bpmFormDef) {
//		if(BeanUtils.isEmpty(bpmFormDef)){
//			return rightList;
//		}
//		List<BpmFormRights> bpmFormRightsList = new ArrayList<BpmFormRights>();
//		List<BpmFormRights> formRightsList = new ArrayList<BpmFormRights>();
//		List<BpmFormRights> otherRightsList = new ArrayList<BpmFormRights>();
//		for (BpmFormRights bpmFormRights : rightList) {
//			if(bpmFormRights.getType() == BpmFormRights.FieldRights){
//				formRightsList.add(bpmFormRights);		
//			}else{
//				otherRightsList.add(bpmFormRights);
//			}
//		}
//		BpmFormTable bpmFormTable=bpmFormTableService.getTableById(bpmFormDef.getTableId());
//		if(BeanUtils.isEmpty(bpmFormTable)){
//			return rightList;
//		}
//		List<BpmFormField> fieldList= bpmFormTable.getFieldList();
//		for (BpmFormField bpmFormField : fieldList) {
//			for (BpmFormRights bpmFormRights : formRightsList) {
//				if(bpmFormRights.getName().equalsIgnoreCase(bpmFormField.getFieldName())){
//					bpmFormRights.setSn(bpmFormField.getSn());
//					bpmFormRightsList.add(bpmFormRights);
//				}
//			}
//		}
//		
//		Collections.sort(bpmFormRightsList, new Comparator<BpmFormRights>() {   
//			@Override
//			public int compare(BpmFormRights o1, BpmFormRights o2) {
//			    int a = o1.getSn();   
//	            int b = o2.getSn();   
//	            return (a < b ? -1 : (a == b ? 0 : 1));  
//			}   
//	       }); 
//		
//		//处理其它
//		for (BpmFormRights bpmFormRights : otherRightsList) {
//			bpmFormRightsList.add(bpmFormRights);
//		}
//		
//		return bpmFormRightsList;
//	}
	
	/**
	 * 获取通过表单设定定义表单的权限数据。
	 * 
	 * <pre>
	 * 通过设计定义的表单，
	 * 如果没有还没有设置过权限，那么权限信息可以通过解析表单获取。
	 * </pre>
	 * 
	 * @param formKey
	 * @return
	 */
	private Map<String, List<JSONObject>> getPermissionByFormKey(BpmFormDef bpmFormDef) {
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		// 获取模版。
		String html = bpmFormDef.getHtml();

		Long tableId = bpmFormDef.getTableId();
		// 读取表。
		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
		// 主表字段权限
		List<BpmFormField> fieldList = bpmFormTable.getFieldList();
		List<JSONObject> fieldJsonList = new ArrayList<JSONObject>();
		for (BpmFormField field : fieldList) {
			JSONObject permission = getPermissionJson(field.getFieldName(),field.getFieldDesc(),1);
			
			//增加字段的控件类型
			permission.put("controlType", field.getControlType());
			
			permission.put("tableId", tableId);
			permission.put("tableName", bpmFormTable.getTableName());
			permission.put("mainTableId","");
			permission.put("mainTableName","");
			fieldJsonList.add(permission);
		}
		map.put("field", fieldJsonList);

		// 子表权限。
		List<JSONObject> tableJsonList = new ArrayList<JSONObject>();
		List<BpmFormTable> tableList = bpmFormTable.getSubTableList();

		for (BpmFormTable table : tableList) {
			// 子表整个表的权限
			JSONObject permission = getPermissionJson(table.getTableName(),table.getTableDesc(),2);
			//每个子表中的每个字段
			List<BpmFormField> subFieldList = table.getFieldList();
			List<JSONObject> subFieldJsonList = new ArrayList<JSONObject>();
			for (BpmFormField field : subFieldList) {
				JSONObject subPermission = getPermissionJson(field.getFieldName(),field.getFieldDesc(),1);
				
				//增加字段的控件类型
				subPermission.put("controlType", field.getControlType());
				
				subPermission.put("tableId", table.getTableId());
				subPermission.put("tableName", table.getTableName());
				subPermission.put("mainTableId", tableId);
				subPermission.put("mainTableName", bpmFormTable.getTableName());
				subFieldJsonList.add(subPermission);
			}
			permission.put("tableId", table.getTableId());
			permission.put("tableName", table.getTableName());
			permission.put("mainTableId", tableId);
			permission.put("mainTableName", bpmFormTable.getTableName());
			permission.put("subField", subFieldJsonList);
			tableJsonList.add(permission);
		}

		map.put("table", tableJsonList);

		// 意见权限。
		List<JSONObject> opinionJsonList = new ArrayList<JSONObject>();
		Map<String, String> opinionMap = FormUtil.parseOpinion(html);
		Set<Entry<String, String>> set = opinionMap.entrySet();
		for (Iterator<Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Entry<String, String> tmp = it.next();
			JSONObject permission = getPermissionJson(tmp.getKey(),tmp.getValue(),3);
			permission.put("tableId", tableId);
			permission.put("tableName", bpmFormTable.getTableName());
			permission.put("mainTableId","");
			permission.put("mainTableName","");
			opinionJsonList.add(permission);
		}
		map.put("opinion", opinionJsonList);
		
		//子表显示与否
		List<JSONObject> tableShowJsonList = new ArrayList<JSONObject>();
		for (BpmFormTable table : tableList) {
			JSONObject permission = getPermissionJson(table.getTableName(),table.getTableDesc(),4);
			permission.put("tableId", table.getTableId());
			permission.put("tableName", table.getTableName());
			permission.put("mainTableId", tableId);
			permission.put("mainTableName",bpmFormTable.getTableName());
			tableShowJsonList.add(permission);
		}
		
		map.put("tableShow", tableShowJsonList);		

		return map;
	}

	/**
	 * 根据用户获得权限。
	 * 
	 * <pre>
	 * field：{"NAME": "w", "SEX": "r"}
	 * table：{"TABLE1": "r", "TABLE2": "w"}
	 * opinion：{"领导意见": "w", "部门意见": "r"}
	 * </pre>
	 * 
	 * @param formDefId
	 * @param userId
	 * @return
	 * @throws DocumentException
	 */
	public Map<String, Map<String, String>> getByFormKeyAndUserId(String formKey,
			Long userId, String actDefId, String nodeId,PlatformType platformType) {
		List<BpmFormRights> rightList = getRightList(formKey, actDefId, nodeId, platformType);
		
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		List<Position> positions = positionService.getByUserId(userId);
		List<SysOrg> orgs = sysOrgService.getOrgsByUserId(userId);
		//获取可以管理的组织列表。
		List<SysUserOrg> ownOrgs = sysUserOrgService.getChargeOrgByUserId(userId);

		Map<String, Map<String, String>> permissions = new HashMap<String, Map<String, String>>();

		Map<String, String> fieldPermission = new HashMap<String, String>();
		Map<String, String> tablePermission = new HashMap<String, String>();
		Map<String, String> opinionPermission = new HashMap<String, String>();
		Map<String, String> menuRightPermission = new HashMap<String, String>();
		
		for (BpmFormRights rights : rightList) {
			if(BpmFormRights.TableShowRights!=rights.getType()){    //子表显示权限不用进行读写权限判断
				JSONObject permission = JSONObject.fromObject(rights
						.getPermission());
				String name = rights.getName().toLowerCase();
				// 取得权限
				String right = getRight(permission, roles, positions, orgs,
						ownOrgs, userId);
				
				
				//只读提交
				if(right.equals("r")){
					boolean rpost=false;
					if( permission.containsKey("rpost")){
						rpost = permission.getBoolean("rpost");
					}
					if(rpost){
						right="rp";
					}
				}
				
				if( permission.containsKey("menuRight")){
					JSONObject menuRight = permission.getJSONObject("menuRight");
					menuRightPermission.put(name, menuRight.toString());
				}
				
				switch (rights.getType()) {
				case BpmFormRights.FieldRights:
					fieldPermission.put(name, right);
					break;
				case BpmFormRights.TableRights:
					//tablePermission.put(name, right=="b"?"w":right);
					tablePermission.put(name, right);
					break;
				case BpmFormRights.OpinionRights:
					opinionPermission.put(name, right);
					break;
				}
			}			
		}
		permissions.put("field", fieldPermission);
		permissions.put("table", tablePermission);
		permissions.put("opinion", opinionPermission);
		permissions.put("menuRight", menuRightPermission);
		
		return permissions;
	}
	
	/**
	 * 根据用户获得权限(子流程)。
	 * 
	 * <pre>
	 * field：{"NAME": "w", "SEX": "r"}
	 * table：{"TABLE1": "r", "TABLE2": "w"}
	 * opinion：{"领导意见": "w", "部门意见": "r"}
	 * </pre>
	 * 
	 * @param formDefId
	 * @param userId
	 * @return
	 * @throws DocumentException
	 */
	public Map<String, Map<String, String>> getByFormKeyAndUserId(String formKey,
			Long userId, String actDefId, String nodeId, String parentActDefId,PlatformType platformType) {
		return getByFormKeyAndUserId(formKey, userId, actDefId, nodeId, parentActDefId, 1,platformType);
	}
	
	/**
	 * 根据用户获得权限(子流程)。
	 * 
	 * <pre>
	 * field：{"NAME": "w", "SEX": "r"}
	 * table：{"TABLE1": "r", "TABLE2": "w"}
	 * opinion：{"领导意见": "w", "部门意见": "r"}
	 * </pre>
	 * 
	 * @param formDefId
	 * @param userId
	 * @return
	 * @throws DocumentException
	 */
	public Map<String, Map<String, String>> getByFormKeyAndUserId(String formKey,
			Long userId, String actDefId, String nodeId, String parentActDefId,Integer platform,PlatformType platformType) {
		List<BpmFormRights> rightList = null;
		if(StringUtil.isNotEmpty(actDefId) && StringUtils.isNotEmpty(nodeId) ){ //具体节点的权限
			if(StringUtils.isNotEmpty(parentActDefId))
				rightList = bpmFormRightsDao.getBpmFormRights(formKey, actDefId,false,nodeId,false,parentActDefId,false,platform,platformType);
			else
				rightList = bpmFormRightsDao.getBpmFormRights(formKey, actDefId,false,nodeId,false,null,true,platform,platformType);
		}
		
		if(StringUtil.isNotEmpty(actDefId) && BeanUtils.isEmpty(rightList)) {//流程的全局菜单
			if(StringUtils.isNotEmpty(parentActDefId))
				rightList = bpmFormRightsDao.getBpmFormRights(formKey,actDefId,false,null,true,parentActDefId,false,platform,platformType);
			else
				rightList = bpmFormRightsDao.getBpmFormRights(formKey,actDefId,false,null,true,null,true,platform,platformType);
		}
		
		if(BeanUtils.isEmpty(rightList)){//表单管理的表单权限
			rightList = bpmFormRightsDao.getBpmFormRights(formKey, null,true,null,false,null,false,platform,platformType);
		}
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		List<Position> positions = positionService.getByUserId(userId);
		List<SysOrg> orgs = sysOrgService.getOrgsByUserId(userId);
		//获取可以管理的组织列表。
		List<SysUserOrg> ownOrgs = sysUserOrgService.getChargeOrgByUserId(userId);

		Map<String, Map<String, String>> permissions = new HashMap<String, Map<String, String>>();

		Map<String, String> fieldPermission = new HashMap<String, String>();
		Map<String, String> tablePermission = new HashMap<String, String>();
		Map<String, String> opinionPermission = new HashMap<String, String>();

		for (BpmFormRights rights : rightList) {
			if(BpmFormRights.TableShowRights!=rights.getType()){    //子表显示权限不用进行读写权限判断
				JSONObject permission = JSONObject.fromObject(rights
						.getPermission());
				String name = rights.getName().toLowerCase();
				// 取得权限
				String right = getRight(permission, roles, positions, orgs,
						ownOrgs, userId);
				
				
				//只读提交
				if(right.equals("r")){
					boolean rpost=false;
					if( permission.containsKey("rpost")){
						rpost = permission.getBoolean("rpost");
					}
					if(rpost){
						right="rp";
					}
				}
				
				switch (rights.getType()) {
				case BpmFormRights.FieldRights:
					fieldPermission.put(name, right);
					break;
				case BpmFormRights.TableRights:
					//tablePermission.put(name, right=="b"?"w":right);
					tablePermission.put(name, right);
					break;
				case BpmFormRights.OpinionRights:
					opinionPermission.put(name, right);
					break;
				}
			}			
		}
		permissions.put("field", fieldPermission);
		permissions.put("table", tablePermission);
		permissions.put("opinion", opinionPermission);

		return permissions;
	}

	/**
	 * 获取表单只读权限
	 * 
	 * @param formKey
	 *            表单key
	 * @return
	 */
	public Map<String, Map<String, String>> getByFormKey(BpmFormDef bpmFormDef) {

		Map<String, Map<String, String>> permissions = new HashMap<String, Map<String, String>>();
		Map<String, String> fieldPermission = new HashMap<String, String>();
		Map<String, String> tablePermission = new HashMap<String, String>();
		Map<String, String> opinionPermission = new HashMap<String, String>();
		String right = "r";

		String html = bpmFormDef.getHtml();
		Long tableId = bpmFormDef.getTableId();
		// 读取表。
		BpmFormTable bpmFormTable = bpmFormTableService.getTableById(tableId);
		// 主表字段权限
		List<BpmFormField> fieldList = bpmFormTable.getFieldList();
		for (BpmFormField field : fieldList) {
			fieldPermission.put(field.getFieldName().toLowerCase(), right);
		}
		permissions.put("field", fieldPermission);

		// 子表权限。
		List<BpmFormTable> tableList = bpmFormTable.getSubTableList();

		for (BpmFormTable table : tableList) {
			// 子表字段权限
			List<BpmFormField> subFieldList = table.getFieldList();
			for (BpmFormField field : subFieldList) {
				tablePermission.put(field.getFieldName().toLowerCase(), right);
			}
		}
		permissions.put("table", tablePermission);

		// 意见权限。
		Map<String, String> opinionMap = FormUtil.parseOpinion(html);
		Set<Entry<String, String>> set = opinionMap.entrySet();
		for (Iterator<Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Entry<String, String> tmp = it.next();
			opinionPermission.put(tmp.getKey().toLowerCase(), right);
		}
		permissions.put("opinion", opinionPermission);
		return permissions;
	}

	/**
	 * 获取权限。
	 * 
	 * @param jsonObject
	 *            数据格式为：{
	 *            'title':'orderId','memo':'订单ID','read':{'type':'everyone','id':'',
	 *            'fullname':''},'write':{'type':'everyone','id':'',
	 *            'fullname':''}}
	 * @param roles
	 *            用户所属的角色
	 * @param positions
	 *            用户所属的岗位
	 * @param orgs
	 *            用户所在的组织
	 * @param ownOrgs
	 *            组织负责人
	 * @param userId
	 *            用户ID
	 * @return
	 */
	private String getRight(JSONObject jsonObject, List<SysRole> roles,
			List<Position> positions, List<SysOrg> orgs,
			List<SysUserOrg> ownOrgs, Long userId) {
		String right = "";
		if(hasRight(jsonObject, "required", roles, positions, orgs, ownOrgs,userId)){
			right = "b";
		}else if (hasRight(jsonObject, "write", roles, positions, orgs, ownOrgs,userId)) {
			right = "w";
		} else if (hasRight(jsonObject, "read", roles, positions, orgs,ownOrgs, userId)) {
			right = "r";
		}
		return right;
	}

	/**
	 * 判断是否有权限。
	 * 
	 * @param permission
	 *            权限JSONOjbect
	 * @param mode
	 *            读或写 (write,read)
	 * @param roles
	 *            角色列表
	 * @param positions
	 *            岗位
	 * @param orgs
	 *            组织
	 * @param ownOrgs
	 *            组织负责人
	 * @param userId
	 *            用户ID
	 * @return
	 */
	private boolean hasRight(JSONObject permission, String mode,
			List<SysRole> roles, List<Position> positions, List<SysOrg> orgs,
			List<SysUserOrg> ownOrgs, Long userId) {
		boolean hasRight = false;
		JSONObject node = permission.getJSONObject(mode);
		if(JSONUtils.isNull(node)) return false;
		if(BeanUtils.isEmpty(node.get("type")))return false;
		String type = node.get("type").toString();
		String id = node.get("id").toString();
		if ("none".equals(type)) {
			return false;
		}
		if ("everyone".equals(type)) {
			return true;
		}
		// 指定用户
		if ("user".equals(type)) {
			hasRight = contain(id, userId.toString());
			return hasRight;
		}
		// 指定角色
		else if ("role".equals(type)) {
			for (SysRole role : roles) {
				if (contain(id, role.getRoleId().toString())) {
					return true;
				}
			}
		}
		// 指定组织
		else if ("org".equals(type)) {
			for (SysOrg org : orgs) {
				if (contain(id, org.getOrgId().toString())) {
					return true;
				}
			}
		}
		// 组织负责人
		else if ("orgMgr".equals(type)) {
			//判断ownOrgs不为空的情况，排除了如果当前人员没有设置为任何组织的主要负责人时ownOrgs为null会报错的情况
			if(BeanUtils.isNotEmpty(ownOrgs)){
				for (SysUserOrg sysUserOrg : ownOrgs) {
					if (contain(id, sysUserOrg.getOrgId().toString())) {
						return true;
					}
				}
			}
		}
		// 岗位
		else if ("pos".equals(type)) {
			for (Position position : positions) {
				if (contain(id, position.getPosId().toString())) {
					return true;
				}
			}
		}
		return false;
	}
	
	private List<BpmFormRights> getRightList(String formKey, String actDefId, String nodeId,PlatformType platformType){
		List<BpmFormRights> rightList = null;
		// 如果流程定义id和任务节点id不为空那么获取节点的权限。
		if (StringUtil.isNotEmpty(actDefId) && StringUtil.isNotEmpty(nodeId)) {
			rightList = bpmFormRightsDao.getByActDefIdAndNodeId(formKey,actDefId, nodeId,platformType);
		}
		if (BeanUtils.isEmpty(rightList) && StringUtil.isNotEmpty(actDefId) ) {
			rightList = bpmFormRightsDao.getByActDefId(formKey,actDefId,false,platformType);
		}
		if (BeanUtils.isEmpty(rightList)) {
			rightList = bpmFormRightsDao.getByFormKey(formKey,false,platformType);
		}
		return rightList;
	}

	public List<JSONObject> getSubTableShowRights(String actDefId,String nodeId,PlatformType platformType){
		BpmFormDef bpmFormDef = bpmFormDefService.getNodeFormDef(actDefId, nodeId, true);
		if(bpmFormDef==null) return new ArrayList<JSONObject>(); 
		String formKey = bpmFormDef.getFormKey();
		List<BpmFormRights> rightList = getRightList(formKey, actDefId, nodeId, platformType);
		
		List<JSONObject> subTableShowList = new ArrayList<JSONObject>();
		for (BpmFormRights rights : rightList) {
			if(rights.getType() != BpmFormRights.TableShowRights) continue;    //整个子表显示与否
			JSONObject permission = JSONObject.fromObject(rights.getPermission());
			subTableShowList.add(permission);
		}
		return subTableShowList;
	}

	public void updateRights() {
		List<BpmFormRights> bpmFormRightsList = bpmFormRightsDao.getAll();
		for (BpmFormRights bpmFormRights : bpmFormRightsList) {
			String permission = bpmFormRights.getPermission();
			JSONObject jsonObject = JSONObject.fromObject(permission);
			String title = jsonObject.get("title").toString();
			String memo =jsonObject.get("memo").toString();
			String read =jsonObject.get("read").toString(); 
			String write =jsonObject.get("write").toString(); 
			JSONObject json =getJson(title, memo, read, write);
			bpmFormRights.setPermission(json.toString());
			bpmFormRightsDao.update(bpmFormRights);
		}
	}
	
	private static JSONObject getJson(String title, String memo,String read,String write) {
		JSONObject json = new JSONObject();
		json.element("title", title);
		json.element("memo", memo);
		json.element("read", read);
		json.element("write", write);
		json.element("required", "{type:'none',id:'', fullname:''}");
		return json;
	}
	
	/**
	 * 判定逗号分隔的字符串是否包括指定的字符。
	 * 
	 * @param ids
	 * @param curId
	 * @return
	 */
	private boolean contain(String ids, String curId) {
		if (StringUtil.isEmpty(ids))
			return false;
		String[] aryId = ids.split(",");
		for (String id : aryId) {
			if (id.equalsIgnoreCase(curId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据formkey删除表单权限
	 * @param cascade 是同时否删除表单人流程的流程节点表单权限设置
	 * @param formKey
	 */
	public void deleteByFormKey(String formKey,boolean cascade,PlatformType platformType){
		bpmFormRightsDao.delByFormKey(formKey,cascade,platformType);
	}
	
	

	/**
	 * 根据表id删除表单的权限。
	 * 
	 * @param tableId
	 */
	public void deleteByTableId(Long tableId,PlatformType platformType) {
		bpmFormRightsDao.deleteByTableId(tableId,platformType);
	}
	
	
	/**
	 * 删除表单权限设置。
	 * @param formKey
	 * @param cascade 级联删除标志。如果为True，同时删除所有与表单相关联的流程、节点权限设置
	 */
	public void delFormRights(String formKey,boolean cascade,PlatformType platformType){
		bpmFormRightsDao.delByFormKey(formKey,cascade,platformType);
	}
	
	/**
	 * 删除表单流程权限设置
	 * @param actDefId
	 * @param cascade 级联删除标志。如果为True，同时删除所有流程的节点权限设置
	 */
	public void delFormFlowRights(String actDefId,boolean cascade,PlatformType platformType){
		bpmFormRightsDao.delByActDefId(actDefId,cascade,platformType);
	}
	
	/**
	 * 删除表单节点权限设置
	 * @param actDefId
	 * @param nodeId
	 */
	public void delFormFlowNodeRights(String actDefId,String nodeId,PlatformType platformType){
		bpmFormRightsDao.delByActDefIdAndNodeId(actDefId, nodeId,platformType);
	}
	
	/**
	 * 根据流程定义id，任务节点id和表单id获取权限数据。
	 * @param actDefId		流程定义ID
	 * @param nodeId		任务节点
	 * @param formKey		表单定义ID
	 * @return
	 */
	public  Map<String,List<JSONObject>> getPermissionByFormNode(String actDefId,String nodeId,String formKey,PlatformType platformType){
		Map<String,List<JSONObject>> map=new HashMap<String, List<JSONObject>>();
		List<BpmFormRights> rightList= bpmFormRightsDao.getByFlowFormNodeId(actDefId, nodeId,platformType);   //具体节点的权限
		if(rightList.size()==0){
			if(StringUtil.isNotEmpty(actDefId)) {
				rightList = bpmFormRightsDao.getByActDefId(formKey,actDefId,false,platformType);  //流程的全局菜单
			}
			if(BeanUtils.isEmpty(rightList)){
				rightList = bpmFormRightsDao.getByFormKey(formKey, false,platformType);      //表单管理的表单权限
			}			
			if(BeanUtils.isEmpty(rightList)){
				BpmFormDef bpmFormDef =bpmFormDefService.getDefaultVersionByFormKey(formKey);     //默认的权限
				map= getPermissionByFormKey(bpmFormDef);
				return map;
			}
		}
		
		BpmFormDef bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formKey);
		map = getPermissionByFormKey(rightList,bpmFormDef);
		return map;
		
	}
	
	/**
	 * 根据流程定义id，任务节点id和表单id获取权限数据。
	 * @param actDefId		流程定义ID
	 * @param nodeId		任务节点
	 * @param formKey		表单定义ID
	 * @return
	 */
	public  Map<String,List<JSONObject>> getPermissionByFormNode(String actDefId,String nodeId,String formKey, String parentActDefId,PlatformType platformType){
		Map<String,List<JSONObject>> map=new HashMap<String, List<JSONObject>>();
		List<BpmFormRights> rightList= bpmFormRightsDao.getByFlowFormNodeId(actDefId, nodeId, parentActDefId,platformType);   //具体节点的权限
		if(rightList.size()==0){
			if(StringUtil.isNotEmpty(actDefId)) {
				rightList = bpmFormRightsDao.getByActDefId(formKey,actDefId,false,parentActDefId,platformType);  //流程的全局菜单
			}
			if(BeanUtils.isEmpty(rightList)){
				rightList = bpmFormRightsDao.getByFormKey(formKey, false,platformType);      //表单管理的表单权限
			}			
			if(BeanUtils.isEmpty(rightList)){
				BpmFormDef bpmFormDef =bpmFormDefService.getDefaultVersionByFormKey(formKey);     //默认的权限
				return getPermissionByFormKey(bpmFormDef);
			}
		}
		
		BpmFormDef bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formKey);
		map = getPermissionByFormKey(rightList,bpmFormDef);
		return map;
	
	
	}
	
	/**
	 * 根据formkey删除表单权限
	 * @param formKey
	 */
//	public void deleteByFormKey(Long formKey,PlatformType platformType){
//		bpmFormRightsDao.delByFormDefId(formKey,platformType);
//	}
	
	
	/**
	 * 获取通过表单设定定义表单的权限数据。
	 * 
	 * <pre>
	 * 通过设计定义的表单，
	 * 如果有设置过权限，那么权限信息可以通过设置过权限与解析表单匹配获取。
	 * </pre>
	 * @param rightList
	 * @param formKey
	 * @return
	 */
	private Map<String, List<JSONObject>> getPermissionByFormKey(List<BpmFormRights> rightList,BpmFormDef bpmFormDef) {		
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		if(BeanUtils.isEmpty(bpmFormDef)){
			return map;
		}		
		List<BpmFormRights> formRightsList = new ArrayList<BpmFormRights>();
		List<BpmFormRights> tableRightsList = new ArrayList<BpmFormRights>();
		List<BpmFormRights> otherRightsList = new ArrayList<BpmFormRights>();
		List<BpmFormRights> tableShowRightsList = new ArrayList<BpmFormRights>();  //子表显示与否
		for (BpmFormRights bpmFormRights : rightList) {
			if(bpmFormRights.getType() == BpmFormRights.FieldRights){
				formRightsList.add(bpmFormRights);		
			}else if(bpmFormRights.getType() == BpmFormRights.TableRights){
				tableRightsList.add(bpmFormRights);	
			}else if(bpmFormRights.getType() == BpmFormRights.TableShowRights){
				tableShowRightsList.add(bpmFormRights);	
			}else{
				otherRightsList.add(bpmFormRights);
			}
		}
		BpmFormTable bpmFormTable=bpmFormTableService.getTableById(bpmFormDef.getTableId());
		if(BeanUtils.isEmpty(bpmFormTable)){
			return map;
		}
		//主表字段权限
		List<JSONObject> fieldJsonList = new ArrayList<JSONObject>();
		List<BpmFormField> fieldList= bpmFormTable.getFieldList();
		for (BpmFormField bpmFormField : fieldList) {
			boolean mark = true;
			for (BpmFormRights bpmFormRights : formRightsList) {
				if(!BeanUtils.isEmpty(bpmFormRights.getName()) && bpmFormRights.getName().equalsIgnoreCase(bpmFormField.getFieldName())){
				//	bpmFormRights.setSn(bpmFormField.getSn());
					JSONObject permission = JSONObject.fromObject(bpmFormRights.getPermission());
					if(!permission.containsKey("tableId")){
						
						// 增加控件类型
						permission.put("controlType", bpmFormField.getControlType());
						
						permission.put("tableId", bpmFormTable.getTableId());
						permission.put("tableName", bpmFormTable.getTableName());
						permission.put("mainTableId","");
						permission.put("mainTableName","");
					}
					fieldJsonList.add(permission);
					mark = false;
					break;
				}
			}
			if(mark){              //没有设置过匹配权限权限时直接到初始化中获取(兼容以前版本)
				JSONObject permission = getPermissionJson(bpmFormField.getFieldName(),bpmFormField.getFieldDesc(),1);
				
				// 增加控件类型
				permission.put("controlType", bpmFormField.getControlType());
				
				permission.put("tableId", bpmFormDef.getTableId());
				permission.put("tableName", bpmFormDef.getTableName());
				permission.put("mainTableId","");
				permission.put("mainTableName","");
				fieldJsonList.add(permission);
			}
		}
		map.put("field", fieldJsonList);
		
		//子表字段权限
		List<JSONObject> tableJsonList = new ArrayList<JSONObject>();
		List<BpmFormTable> tableList = bpmFormTable.getSubTableList();
		for (BpmFormTable table : tableList) {
			// 子表整个表的权限
			JSONObject permission = getPermissionJson(table.getTableName(),table.getTableDesc(),2);
			//每个子表中的每个字段
			List<BpmFormField> subFieldList = table.getFieldList();
			List<JSONObject> subFieldJsonList = new ArrayList<JSONObject>();
			for (BpmFormField field : subFieldList) {
				boolean mark = true;
				for (BpmFormRights bpmFormRights : tableRightsList) {
					if(!BeanUtils.isEmpty(bpmFormRights.getName()) && bpmFormRights.getName().equalsIgnoreCase(field.getFieldName())){
					//	bpmFormRights.setSn(field.getSn());
						JSONObject subPermission = JSONObject.fromObject(bpmFormRights.getPermission());
						if(subPermission.containsKey("tableId") && subPermission.getString("tableId").equals(table.getTableId().toString())){
							if(!subPermission.containsKey("mainTableId")){
								
								// 增加控件类型
								subPermission.put("controlType", field.getControlType());
								
								subPermission.put("tableId", table.getTableId());
								subPermission.put("tableName", table.getTableName());
								subPermission.put("mainTableId",bpmFormTable.getTableId());
								subPermission.put("mainTableName",bpmFormTable.getTableName());
							}
							subFieldJsonList.add(subPermission);
							mark = false;
							break; 
						}						
					}
				}
				if(mark){              //没有设置过匹配权限时直接到初始化中获取(兼容以前版本)
					JSONObject subPermission = getPermissionJson(field.getFieldName(),field.getFieldDesc(),1);
					//子表整个表的权限是重新生成的，所以不用判断
					
					// 增加控件类型
					subPermission.put("controlType", field.getControlType());
					
					subPermission.put("tableId", table.getTableId());
					subPermission.put("tableName", table.getTableName());
					subPermission.put("mainTableId",bpmFormTable.getTableId());
					subPermission.put("mainTableName",bpmFormTable.getTableId());
					subFieldJsonList.add(subPermission);
				}
			}
			//子表整个表的权限是重新生成的，所以不用判断
			permission.put("tableId", table.getTableId());
			permission.put("tableName", table.getTableName());
			permission.put("mainTableId",bpmFormTable.getTableId());
			permission.put("mainTableName",bpmFormTable.getTableName());
			permission.put("subField", subFieldJsonList);
			tableJsonList.add(permission);
		}
		map.put("table", tableJsonList);
		
		//处理其它  意见权限。
		String html = bpmFormDef.getHtml();   // 获取模版。
		List<JSONObject> opinionJsonList = new ArrayList<JSONObject>();
		Map<String, String> opinionMap = FormUtil.parseOpinion(html);
		Set<Entry<String, String>> set = opinionMap.entrySet();
		for (Iterator<Entry<String, String>> it = set.iterator(); it.hasNext();) {
			boolean mark = true;
			Entry<String, String> tmp = it.next();
			for (BpmFormRights bpmFormRights : otherRightsList) {
				JSONObject opinionPermission = JSONObject.fromObject(bpmFormRights.getPermission());
				if(!BeanUtils.isEmpty(bpmFormRights.getName())&& bpmFormRights.getName().equalsIgnoreCase(tmp.getKey())){
					if(!opinionPermission.containsKey("tableId")){
						opinionPermission.put("tableId", bpmFormTable.getTableId());
						opinionPermission.put("tableName", bpmFormTable.getTableName());
						opinionPermission.put("mainTableId","");
						opinionPermission.put("mainTableName","");
					}
					opinionJsonList.add(opinionPermission);
					mark = false;
					break;
				}	
			}
			if(mark){              //没有设置过匹配权限时直接到初始化中获取(兼容以前版本)
				JSONObject permission = getPermissionJson(tmp.getKey(),tmp.getValue(),3);
				permission.put("tableId", bpmFormTable.getTableId());
				permission.put("tableName", bpmFormTable.getTableName());
				permission.put("mainTableId","");
				permission.put("mainTableName","");
				opinionJsonList.add(permission);
			}
		}
		map.put("opinion", opinionJsonList);
		
		//子表显示与否
		List<JSONObject> tableShowJsonList = new ArrayList<JSONObject>();
		for (BpmFormTable table : tableList) {
			boolean mark = true;
			for (BpmFormRights bpmFormRights : tableShowRightsList) {
				if(!BeanUtils.isEmpty(bpmFormRights.getName()) && bpmFormRights.getName().equalsIgnoreCase(table.getTableName())){
					JSONObject permission = JSONObject.fromObject(bpmFormRights.getPermission());
					if(!permission.containsKey("tableId")){
						permission.put("tableId", table.getTableId());
						permission.put("tableName", table.getTableName());
						permission.put("mainTableId",bpmFormTable.getTableId());
						permission.put("mainTableName",bpmFormTable.getTableName());
					}
					tableShowJsonList.add(permission);
					mark = false;
					break;
				}
			}
			if(mark){              //没有设置过匹配权限权限时直接到初始化中获取(兼容以前版本)
				JSONObject permission = getPermissionJson(table.getTableName(),table.getTableDesc(),4);
				permission.put("tableId", table.getTableId());
				permission.put("tableName", table.getTableName());
				permission.put("mainTableId",bpmFormTable.getTableId());
				permission.put("mainTableName",bpmFormTable.getTableName());
				tableShowJsonList.add(permission);
			}
		}
		map.put("tableShow", tableShowJsonList);
		
		return map;
	}
	
	
	/**
	 * 获取通过表单设定定义表单的权限数据。
	 * 
	 * <pre>
	 * 通过设计定义的表单，
	 * 如果有设置过权限，那么权限信息可以通过设置过权限与解析表单匹配获取。
	 * </pre>
	 * @param rightList
	 * @param formKey
	 * @return
	 */
	public Map<String, List<JSONObject>> getSubTablePermission(String formKey,Long userId,String actDefId,String nodeId,PlatformType platformType){
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		List<BpmFormRights> rightList = getRightList(formKey, actDefId, nodeId, platformType);
		
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		List<Position> positions = positionService.getByUserId(userId);
		List<SysOrg> orgs = sysOrgService.getOrgsByUserId(userId);
		//获取可以管理的组织列表。
		List<SysUserOrg> ownOrgs = sysUserOrgService.getChargeOrgByUserId(userId);

		List<JSONObject> subJsonList = new ArrayList<JSONObject>();
		List<JSONObject> subTableShowList = new ArrayList<JSONObject>();
		for (BpmFormRights rights : rightList) {
			if(rights.getType()==BpmFormRights.TableRights){   //子表字段
				JSONObject permission = JSONObject.fromObject(rights.getPermission());
				// 取得权限
				String right = getRight(permission, roles, positions, orgs,ownOrgs, userId);
				//只读提交
				if(right.equals("r")){
					boolean rpost=false;
					if( permission.containsKey("rpost")){
						rpost = permission.getBoolean("rpost");
					}
					if(rpost){
						right="rp";
					}
				}
				permission.put("power",right);
				subJsonList.add(permission);
			}else if(rights.getType()==BpmFormRights.TableShowRights){    //整个子表显示与否
				JSONObject permission = JSONObject.fromObject(rights.getPermission());
				subTableShowList.add(permission);
			}	
		}
		map.put("subFileJsonList", subJsonList);
		map.put("subTableShowList", subTableShowList);
		return map;
	}
	
	/**
	 * 获取通过表单设定定义表单的权限数据。(子表)
	 * 
	 * <pre>
	 * 通过设计定义的表单，
	 * 如果有设置过权限，那么权限信息可以通过设置过权限与解析表单匹配获取。
	 * </pre>
	 * @param rightList
	 * @param formKey
	 * @return
	 */
	public Map<String, List<JSONObject>> getSubTablePermission(String formKey,Long userId,String actDefId,String nodeId, String parentActDefId,PlatformType platformType){
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		List<BpmFormRights> rightList = null;
		// 如果流程定义id和任务节点id不为空那么获取节点的权限。
		if (StringUtil.isNotEmpty(actDefId) && StringUtil.isNotEmpty(nodeId)) {
			rightList = bpmFormRightsDao.getByActDefIdAndNodeId(formKey,actDefId, nodeId, parentActDefId,platformType);
		}
		if (BeanUtils.isEmpty(rightList) && StringUtil.isNotEmpty(actDefId) ) {
			rightList = bpmFormRightsDao.getByActDefId(formKey,actDefId,false,parentActDefId,platformType);
		}
		if (BeanUtils.isEmpty(rightList)) {
			rightList = bpmFormRightsDao.getByFormKey(formKey,false,platformType);
		}
		
		List<SysRole> roles = sysRoleService.getByUserId(userId);
		List<Position> positions = positionService.getByUserId(userId);
		List<SysOrg> orgs = sysOrgService.getOrgsByUserId(userId);
		//获取可以管理的组织列表。
		List<SysUserOrg> ownOrgs = sysUserOrgService.getChargeOrgByUserId(userId);

		List<JSONObject> subJsonList = new ArrayList<JSONObject>();
		List<JSONObject> oldSubJsonList = new ArrayList<JSONObject>();
		List<JSONObject> subTableShowList = new ArrayList<JSONObject>();
		for (BpmFormRights rights : rightList) {
			if(rights.getType()==BpmFormRights.TableRights){   //子表字段
				JSONObject permission = JSONObject.fromObject(rights.getPermission());
				// 取得权限
				String right = getRight(permission, roles, positions, orgs,ownOrgs, userId);
				//只读提交
				if(right.equals("r")){
					boolean rpost=false;
					if( permission.containsKey("rpost")){
						rpost = permission.getBoolean("rpost");
					}
					if(rpost){
						right="rp";
					}
				}
				permission.put("power",right);
				if(permission.containsKey("tableId")){    //旧版本的没有tableId 新版本有的
					subJsonList.add(permission);
				}else{ 
					//旧版本
					oldSubJsonList.add(permission);
				}	
			}else if(rights.getType()==BpmFormRights.TableShowRights){    //整个子表显示与否
				JSONObject permission = JSONObject.fromObject(rights.getPermission());
				subTableShowList.add(permission);
			}	
		}
		map.put("subFileJsonList", subJsonList);
		map.put("oldSubFileJsonList", oldSubJsonList);
		map.put("subTableShowList", subTableShowList);
		return map;
	}
	
	
	/**
	 * 根据formkey\actDefId\nodeId删除表单权限
	 * @param formKey
	 * @param actDefId
	 * @param nodeId
	 */
	public void deleteRight(String formKey,String actDefId,String nodeId,PlatformType platformType){
		if(StringUtil.isNotEmpty(nodeId)){
			bpmFormRightsDao.delByActDefIdAndNodeId(actDefId, nodeId,platformType);	
		}else if(StringUtil.isNotEmpty(actDefId)){
			bpmFormRightsDao.delByActDefId(actDefId, false,platformType);
		}else{
			bpmFormRightsDao.delByFormKey(formKey,false,platformType);
		}
	}	
	
	/**
	 * 根据formkey\actDefId\nodeId删除表单权限
	 * @param formKey
	 * @param actDefId
	 * @param nodeId
	 */
	public void deleteRight(String formKey,String actDefId,String nodeId,String parentActDefId,PlatformType platformType){
		if(StringUtil.isNotEmpty(nodeId)){
			bpmFormRightsDao.delByActDefIdAndNodeId(actDefId, nodeId, parentActDefId,platformType);	
		}else if(StringUtil.isNotEmpty(actDefId)){
			bpmFormRightsDao.delByActDefId(actDefId, false, parentActDefId,platformType);
		}else{
			bpmFormRightsDao.delByFormKey(formKey,false,platformType);
		}
	}
	
	/**
	 * 获取默认只读的的权限数据。
	 * 
	 * @param title
	 * @param memo
	 * @param type
	 * @return
	 */
	public JSONObject getReadPermissionJson(String title, String memo,int type) {
	//	String defJson = "{type:'everyone',id:'', fullname:''}";
		String defJson = "{type:'none',id:'', fullname:''}";
		JSONObject json = new JSONObject();
		json.element("title", title);
		json.element("memo", memo);
        if(type!=4){
        	json.element("read", "{type:'everyone',id:'', fullname:''}");
    		json.element("write", defJson);
    		if(type !=2){
    			json.element("required",defJson );
    		}
		}else{
			json.element("show", "{addRow:'false'}");     //子表可增加记录：addRow:'true'  子表不可添加记录：addRow:'false'   可以增加默认
		}
		return json;
	}
	
}
