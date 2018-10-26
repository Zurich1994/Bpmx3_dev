package com.hotent.platform.service.form;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.hotent.core.table.ColumnModel;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.platform.dao.form.BpmFormFieldDao;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.util.FieldPool;
import com.hotent.core.util.BeanUtils;


/**
 * 对象功能:自定义表字段 Service类 
 * 开发公司:广州宏天软件有限公司 
 * 开发人员:xwy 
 * 创建时间:2011-11-30 15:20:14
 */
@Service
public class BpmFormFieldService extends BaseService<BpmFormField>
{
	@Resource
	private BpmFormFieldDao dao;

	public BpmFormFieldService()
	{
	}

	@Override
	protected IEntityDao<BpmFormField, Long> getEntityDao()
	{
		return dao;
	}

	/**
	 * 通过tableId查找
	 * 
	 * @param tableId 自定义表Id
	 * @return
	 */
	public List<BpmFormField> getByTableId(Long tableId)
	{
		return dao.getByTableId(tableId);
	}
	
	/**
	 * 通过tableId查找所有（包括已删除的）
	 * 
	 * @param tableId 自定义表Id
	 * @return
	 */
	public List<BpmFormField> getAllByTableId(Long tableId)
	{
		return dao.getAllByTableId(tableId);
	}
	
	/**
	 * 根据流程定义ID获取流程变量。
	 * @param defId		流程定义ID
	 * @return
	 */
//	public List<BpmFormField> getFlowVarByFlowDefId(Long defId)
//	{
//		List<BpmFormField> list =dao.getFlowVarByFlowDefId(defId);
//		list.addAll(getCommonFields());
//		return list;
//	}
	
	/**
	 * 获取所有流程默认自带的变量
	 * @return
	 */
	private List<BpmFormField> getCommonFields(){
		List<BpmFormField> list = new ArrayList<BpmFormField>();
		
		BpmFormField startUser=new BpmFormField();
		startUser.setFieldName("startUser");
		startUser.setFieldDesc("发起人ID");
		startUser.setFieldType(ColumnModel.COLUMNTYPE_NUMBER);
		list.add(startUser);
		
		BpmFormField businessKey=new BpmFormField();
		businessKey.setFieldName("businessKey");
		businessKey.setFieldDesc("表单主键");
		businessKey.setFieldType(ColumnModel.COLUMNTYPE_VARCHAR);
		list.add(businessKey);
		
		BpmFormField flowRunId=new BpmFormField();
		flowRunId.setFieldName("flowRunId");
		flowRunId.setFieldDesc("流程运行ID");
		flowRunId.setFieldType(ColumnModel.COLUMNTYPE_INT);
		list.add(flowRunId);
		
		return list;
	}
	
	/**
	 * 根据流程定义ID获取流程变量。
	 * @param defId		流程定义ID
	 * @return
	 */
	public List<BpmFormField> getFlowVarByFlowDefId(Long defId)
	{
		List<BpmFormField> list = getFormVarByFlowDefId(defId);
		list.addAll(getCommonFields());
		parseDateFormat(list);
		return list;
	}
	
	/**
	 * 根据流程定义ID获取流程变量。
	 * @param defId		流程定义ID
	 * @param parentActDefId
	 * @return
	 */
	public List<BpmFormField> getFlowVarByFlowDefId(Long defId, String parentActDefId)
	{
		List<BpmFormField> list = getFormVarByFlowDefId(defId, parentActDefId);
		list.addAll(getCommonFields());
		parseDateFormat(list);
		return list;
	}
	
	/**
	 * 根据流程定义ID获取流程变量。
	 * @param defId				流程定义ID
	 * @param excludeHidden		排除隐藏变量
	 * @return
	 */
	public List<BpmFormField> getFlowVarByFlowDefId(Long defId,boolean excludeHidden)
	{
		List<BpmFormField> flowVars=new ArrayList<BpmFormField>();
		//获取表单流程变量。
		List<BpmFormField> list = getFormVarByFlowDefId(defId);
		for(BpmFormField field:list){
			//排除重复，并且
			if(excludeHidden && field.isExecutorSelectorHidden()){
				continue;
			}
			flowVars.add(field);
		}
		flowVars.addAll(getCommonFields());
		parseDateFormat(flowVars);
		return flowVars;
	}
	
	/**
	 * 根据流程定义ID获取流程变量。
	 * @param defId				流程定义ID
	 * @param excludeHidden		排除隐藏变量
	 * @param parentActDefId	父流程定义ID
	 * @return
	 */
	public List<BpmFormField> getFlowVarByFlowDefId(Long defId, String parentActDefId, boolean excludeHidden)
	{
		List<BpmFormField> flowVars=new ArrayList<BpmFormField>();
		//获取表单流程变量。
		List<BpmFormField> list = getFormVarByFlowDefId(defId, parentActDefId);
		for(BpmFormField field:list){
			//排除重复，并且
			if(excludeHidden && field.isExecutorSelectorHidden()){
				continue;
			}
			flowVars.add(field);
		}
		flowVars.addAll(getCommonFields());
		parseDateFormat(flowVars);
		return flowVars;
	}
	
	public List<BpmFormField> getByTableIdContainHidden(Long tableId){
		return dao.getByTableIdContainHidden(tableId);
	}
	
	/**
	 * 处理日期类型
	 * @param list
	 */
	private void parseDateFormat(List<BpmFormField> list) {
		if(BeanUtils.isEmpty(list)) return;
		for (BpmFormField bpmFormField : list) {
			if(BeanUtils.isEmpty(bpmFormField.getCtlProperty()))
				continue;	
			if(  bpmFormField.getFieldType().equals(ColumnModel.COLUMNTYPE_DATE)||  bpmFormField.getControlType().shortValue() ==FieldPool.DATEPICKER )
					this.setDateFormat(bpmFormField);
			
		}
	}
	private void setDateFormat(BpmFormField bpmFormField){
		if(BeanUtils.isNotEmpty(bpmFormField.getCtlProperty())){
			JSONObject json = 	JSONObject.fromObject(bpmFormField.getCtlProperty());
			String format = (String) json.get("format");
			bpmFormField.setDatefmt(format);
		}
	}
	
	/**
	 * 根据流程定义ID获取流程表单变量。
	 * @param defId		流程定义ID
	 * @return
	 */
	public List<BpmFormField> getFormVarByFlowDefId(Long defId){
		return dao.getFlowVarByFlowDefId(defId);
	}
	
	/**
	 * 根据流程定义ID和父流程定义ID获取流程表单变量。
	 * @param defId		流程定义ID
	 * @param parentActDefId		父流程定义ID
	 * @return
	 */
	public List<BpmFormField> getFormVarByFlowDefId(Long defId, String parentActDefId){
		return dao.getFlowVarByFlowDefId(defId, parentActDefId);
	}
	
	/**
	 * 根据表ID和字段名称取得对应字段的数据（没有隐藏字段）。
	 * @param defId		流程定义ID
	 * @return
	 */
	public BpmFormField getFieldByTidFnaNh(Long tableId,String fieldName,String subTableName){
		return dao.getFieldByTidFnaNh(tableId,fieldName,subTableName);
	}
	
	/**
	 * 根据表ID和字段名称取得对应字段的数据。
	 * @param defId		流程定义ID
	 * @return
	 */
	public BpmFormField getFieldByTidFna(Long tableId,String fieldName){
		return dao.getFieldByTidFna(tableId,fieldName);
	}
}
