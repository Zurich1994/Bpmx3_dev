package com.hotent.platform.model.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MobileFormData {
	
	// 表ID
	private long tableId;
	
	//表名
	private String tableName;
	//注释
	private String tableDesc;
	
	private boolean extForm = false;
	/** 是否是空表单 */
	private boolean emptyForm = false;
	//字段
	private String fields;
	
	private String formEditUrl;
	private String formDetailUrl;
	//是否是会签节点
	private boolean signTask = false;
	/** 表单 */
	private String formKey;
	
	/** 表单是否有效。 */
	private boolean isValid = true;
	
	/**是否是特殊流程 */
	private boolean isParticular = false;
	
	/**
	 * 表单数据。
	 * 数据格式为JSON。
	 */
	private String formData="";

	//意见<key,值>
	private Map<String, String> options = new HashMap<String, String>();
	//意见
	private String opinions = "";

	private List<MobileSubTableData> subTableList = new ArrayList<MobileSubTableData>();
	
	/**
	 * -1,没有设置表单。
	 * 0,在线表单
	 * 1,url表单
	 * 2, 有明细url。
	 */
	private int formType=-1;

	private boolean isOption= true;
	

	public MobileFormData() {

	}

	/**
	 * @return the tableId
	 */
	public long getTableId() {
		return tableId;
	}

	/**
	 * @param tableId the tableId to set
	 */
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the tableDesc
	 */
	public String getTableDesc() {
		return tableDesc;
	}

	/**
	 * @param tableDesc the tableDesc to set
	 */
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	/**
	 * @return the extForm
	 */
	public boolean isExtForm() {
		return extForm;
	}

	/**
	 * @param extForm the extForm to set
	 */
	public void setExtForm(boolean extForm) {
		this.extForm = extForm;
	}

	/**
	 * @return the emptyForm
	 */
	public boolean isEmptyForm() {
		return emptyForm;
	}

	/**
	 * @param emptyForm the emptyForm to set
	 */
	public void setEmptyForm(boolean emptyForm) {
		this.emptyForm = emptyForm;
	}

	/**
	 * @return the fields
	 */
	public String getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}

	/**
	 * @return the formEditUrl
	 */
	public String getFormEditUrl() {
		return formEditUrl;
	}

	/**
	 * @param formEditUrl the formEditUrl to set
	 */
	public void setFormEditUrl(String formEditUrl) {
		this.formEditUrl = formEditUrl;
	}

	/**
	 * @return the formDetailUrl
	 */
	public String getFormDetailUrl() {
		return formDetailUrl;
	}

	/**
	 * @param formDetailUrl the formDetailUrl to set
	 */
	public void setFormDetailUrl(String formDetailUrl) {
		this.formDetailUrl = formDetailUrl;
	}

	/**
	 * @return the signTask
	 */
	public boolean isSignTask() {
		return signTask;
	}

	/**
	 * @param signTask the signTask to set
	 */
	public void setSignTask(boolean signTask) {
		this.signTask = signTask;
	}

	/**
	 * @return the formKey
	 */
	public String getFormKey() {
		return formKey;
	}

	/**
	 * @param formKey the formKey to set
	 */
	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	/**
	 * @return the isValid
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * @param isValid the isValid to set
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	/**
	 * @return the options
	 */
	public Map<String, String> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	/**
	 * @return the subTableList
	 */
	public List<MobileSubTableData> getSubTableList() {
		return subTableList;
	}

	/**
	 * @param subTableList the subTableList to set
	 */
	public void setSubTableList(List<MobileSubTableData> subTableList) {
		this.subTableList = subTableList;
	}

	/**
	 * @return the isParticular
	 */
	public boolean isParticular() {
		return isParticular;
	}

	/**
	 * @param isParticular the isParticular to set
	 */
	public void setParticular(boolean isParticular) {
		this.isParticular = isParticular;
	}

	/**
	 * @return the formData
	 */
	public String getFormData() {
		return formData;
	}

	/**
	 * @param formData the formData to set
	 */
	public void setFormData(String formData) {
		this.formData = formData;
	}
	
	
	public int getFormType() {
		return formType;
	}

	public void setFormType(int formType) {
		this.formType = formType;
	}

	public String getOpinions() {
		return opinions;
	}

	public void setOpinions(String opinions) {
		this.opinions = opinions;
	}

	public boolean isOption() {
		return isOption;
	}

	public void setOption(boolean isOption) {
		this.isOption = isOption;
	}


}