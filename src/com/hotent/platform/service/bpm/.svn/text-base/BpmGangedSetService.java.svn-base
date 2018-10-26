package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmGangedSetDao;
import com.hotent.platform.model.bpm.BpmGangedSet;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormTableService;

/**
 * 
 *<pre>
 * 对象功能:联动设置表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:heyifan
 * 创建时间:2012-12-28 16:50:37
 *</pre>
 */
@Service
public class BpmGangedSetService extends BaseService<BpmGangedSet>
{
	@Resource
	private BpmGangedSetDao dao;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private BpmFormFieldService bpmFormFieldService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	
	public BpmGangedSetService()
	{
	}
	
	@Override
	protected IEntityDao<BpmGangedSet, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 过滤字段类型，只选取下拉框、单选按钮、复选框
	 * @param tableFields
	 */
	private List<BpmFormField> filterFields(List<BpmFormField> tableFields){
		List<BpmFormField> temp = new ArrayList<BpmFormField>();
		for(BpmFormField formFields:tableFields){
			Short cType = formFields.getControlType();
			if(cType==11||cType==13||cType==14){
				temp.add(formFields);
			}
		}
		return temp;
	}
	
	/**
	 * 通过流程定义ID获取联动设置
	 * @param defId
	 * @return
	 */
	public List<BpmGangedSet> getByDefId(Long defId){
		return dao.getByDefId(defId);
	}
	
	/**
	 * 通过流程定义ID和NODEID获取联动设置
	 * <pre>
	 * 	首先获取该节点的联动设置，如果不存在则获取所有节点的联动设置
	 * </pre>
	 * @param defId
	 * @param nodeId
	 * @return
	 */
	public List<BpmGangedSet> getByDefIdAndNodeId(Long defId,String nodeId){
		List<BpmGangedSet> list = dao.getByDefIdAndNodeId(defId, nodeId);
		if(BeanUtils.isEmpty(list))
			list = dao.getByDefIdAndNodeId(defId, BpmGangedSet.ALL_NODEID);
		return list;
	}
	
	/**
	 * 通过DEFID获取起始节点的联动设置
	 * <pre>
	 * 	首先获取起始节点的联动设置，如果不存在则获取所有节点的联动设置
	 * </pre>
	 * @param defId
	 * @return
	 */
	public List<BpmGangedSet> getStartNodeByDefId(Long defId){
		List<BpmGangedSet> list = dao.getByDefIdAndNodeId(defId, "1");
		if(BeanUtils.isEmpty(list))
			list = dao.getByDefIdAndNodeId(defId, "2");
		return list;
	}
	
	/**
	 * 批量保存联动设置，并删除不在保存列表中的记录
	 * @param defId
	 * @param json
	 */
	public void batchSave(Long defId,String json){
		List<Long> gangedSetId = new ArrayList<Long>();
		if(StringUtil.isEmpty(json)){
			delByDefIdExceptSetIds(defId, gangedSetId);
			return;
		}
		JSONArray jArray = (JSONArray)JSONArray.fromObject(json);
		for(Object object:jArray){
			JSONObject jObject = (JSONObject)object;
			Long id = jObject.getLong("id");
			BpmGangedSet gangedSet;
			if(id>0){
				gangedSet = dao.getById(id);
				gangedSetId.add(id);
			}
			else{
				Long newId = UniqueIdUtil.genId();
				gangedSet = new BpmGangedSet();
				gangedSet.setId(newId);
				gangedSetId.add(newId);
			}
			gangedSet.setDefid(jObject.getLong("defId"));
			gangedSet.setNodeid(jObject.getString("nodeId"));
			gangedSet.setNodename(jObject.getString("nodeName"));
			gangedSet.setChoisefield(jObject.getString("chooseField"));
			gangedSet.setChangefield(jObject.getString("changeField"));
			if(id>0)
				dao.update(gangedSet);
			else
				dao.add(gangedSet);
		}
		delByDefIdExceptSetIds(defId, gangedSetId);
	}
	
	/**
	 * 通过defid删除除了setIds以外的记录
	 * @param defId
	 * @param setIds
	 */
	private void delByDefIdExceptSetIds(Long defId,List<Long> setIds){
		String ids = "";
		for(Long setId:setIds){
			if(StringUtil.isEmpty(ids))
				ids += setId.toString();
			else{
				ids += ",";
				ids += setId.toString();
			}
		}
		dao.delByDefIdExceptSetId(defId, ids);
	}
	
	
	/**
	 * 获取表单的所有主表、子表字段
	 * @param formkey
	 * @return
	 */
	public String getFieldsByFormkey(String formkey,boolean ifFilter){
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		BpmFormDef bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formkey);
		Long tableId = bpmFormDef.getTableId();		

		BpmFormTable mainTable = bpmFormTableService.getById(tableId);
		List<BpmFormField> mainTableFields = bpmFormFieldService.getByTableId(tableId);
		//是否过滤字段类型（选择框字段需要过滤，变更字段无需过滤）
		if(ifFilter)
			mainTableFields = this.filterFields(mainTableFields);
		sb.append(getJson(mainTable, mainTableFields,true,ifFilter));
		List<BpmFormTable> subTables = bpmFormTableService.getSubTableByMainTableId(tableId);
		for(BpmFormTable subTable:subTables){
			Long subTableId = subTable.getTableId();
			List<BpmFormField> subFields = bpmFormFieldService.getByTableId(subTableId);
			//是否过滤字段类型（选择框字段需要过滤，变更字段无需过滤）
			if(ifFilter)
				subFields = this.filterFields(subFields);
				
			
			sb.append(getJson(subTable, subFields,false,ifFilter));
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * 获取json
	 * @param table 表
	 * @param fields 字段
	 * @param ifFilter  是否过滤
	 * @return
	 */
	public String  getJson(BpmFormTable table,List<BpmFormField> fields,Boolean isMain, Boolean ifFilter){
		if(BeanUtils.isEmpty(fields)) return "";
		StringBuffer sb = new StringBuffer();
		JSONArray jsonArray = new  JSONArray();	
		JSONArray jArray = (JSONArray)JSONArray.fromObject(fields);
		if(!isMain && !ifFilter){ //不是主表并且不是进行过滤
			BpmFormField field = new BpmFormField();
			field.setFieldId(table.getTableId());
			field.setType((short) 1);
			field.setFieldName(table.getTableName());
			field.setFieldDesc(table.getTableDesc());
			jsonArray.add(field);	
		}	
		jsonArray.addAll(jArray);
		
		if(!isMain)
			sb.append(",");
		sb.append("{name:\"")
		//表没有填写描述时，取表名
			.append(StringUtil.isEmpty(table.getTableDesc())?table.getTableName():table.getTableDesc())
			.append("\",key:\"")
			.append(table.getTableName())
			.append("\",type:")
			.append(isMain?"1":"0")
			.append(",fields:")
			.append(jsonArray.toString())
			.append("}");
		
		return sb.toString();
	}
}
