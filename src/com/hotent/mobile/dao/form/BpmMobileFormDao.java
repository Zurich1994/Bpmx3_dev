package com.hotent.mobile.dao.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.mobile.model.form.BpmMobileForm;
/**
 *<pre>
 * 对象功能:手机表单 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2014-04-02 15:09:58
 *</pre>
 */
@Repository
public class BpmMobileFormDao extends BaseDao<BpmMobileForm>
{
	@Override
	public Class<?> getEntityClass()
	{
		return BpmMobileForm.class;
	}

	public List<BpmMobileForm> getByMap(String formKey, Long formId, Integer isDefault) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("formKey",formKey);
		params.put("formId", formId);
		params.put("isDefault", isDefault);
		
		return getBySqlKey("getByMap", params);
	}

	public List<BpmMobileForm> getByFormId(Long formId) {
		return getBySqlKey("getByFormId", formId);
	}
	
	/**
	 * 根据流程定义ID和节点ID，获取手机表单定义
	 * @param actDefId 流程定义ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public List<BpmMobileForm> getByActDefIdAndNodeId(String actDefId,String nodeId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		List<BpmMobileForm> list = this.getBySqlKey("getByActDefIdAndNodeId", params);
		return list;
	}
	
	/**
	 * 根据流程定义ID，获取手机表单定义
	 * @param actDefId 流程定义ID
	 * @return
	 */
	public List<BpmMobileForm> getByActDefId(String actDefId) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		List<BpmMobileForm> list = this.getBySqlKey("getByActDefId", params);
		return list;
	}

	/**
	 * 根据流程定义ID和节点设置类型，获取手机表单定义
	 * @param actDefId 流程定义ID
	 * @param setType 节点设置类型
	 * @return
	 */
	public List<BpmMobileForm> getByActDefIdAndSetType(String actDefId, Short setType) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("setType", setType);
		List<BpmMobileForm> list = this.getBySqlKey("getByActDefIdAndSetType", params);
		return list;
	}
}