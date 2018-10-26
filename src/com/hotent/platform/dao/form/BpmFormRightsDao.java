/**
 * 对象功能:字段权限 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2012-02-10 10:48:16
 */
package com.hotent.platform.dao.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.model.form.BpmFormRights;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BpmFormRightsDao extends BaseDao<BpmFormRights>
{
	
	@Override
	public Class getEntityClass()
	{
		return BpmFormRights.class;
	}
	
	public List<BpmFormRights> getByFormDefId(String formDefId,PlatformType platformType) {
		Map params=new HashMap();
		params.put("formDefId", formDefId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getByFormDefId", params);
	}
	/**
	 * 根据actDefId 获取表单权限。
	 * @param actDefId
	 * @param cascade
	 * @return
	 */
	public List<BpmFormRights> getFormRightsByActDefId(String actDefId,PlatformType platformType) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getFormRightsByActDefId", params);
	}
	
	/**
	 * 根据表单key 获取表单权限。
	 * @param formKey
	 * @param cascade
	 * @return
	 */
	public List<BpmFormRights> getByFormKey(String formKey,boolean cascade,PlatformType platformType) {
		String statment = null;
		if(cascade){
			statment = "getByFormKey";
		}else{
			statment = "getByFormKeyExcActDefId";
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("formKey", formKey);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey(statment, params);
	}
	
	
	/**
	 * 获取表单权限。
	 * @param formKey
	 * @param actDefId
	 * @return cascade 
	 * 
	 */
	public List<BpmFormRights> getByActDefId(String formKey,String actDefId,boolean cascade,String parentActDefId,PlatformType platformType) {
		String statment = null;
		if(cascade){
			statment = "getByActDefId";
		}else{
			statment = "getByActDefIdAndParentActDefIdExcNodeId";
		}
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("formKey", formKey);
		params.put("actDefId", actDefId);
		params.put("parentActDefId", parentActDefId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey(statment,params);
	}
	
	/**
	 * 获取表单权限。
	 * @param formKey
	 * @param actDefId
	 * @return cascade 
	 * 
	 */
	public List<BpmFormRights> getByActDefId(String formKey,String actDefId,boolean cascade,PlatformType platformType) {
		String statment = null;
		if(cascade){
			statment = "getByActDefId";
		}else{
			statment = "getByActDefIdExcNodeId";
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("formKey", formKey);
		params.put("actDefId", actDefId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey(statment,params);
	}
	
	
	/**
	 * 根据流程定义id，节点id获取表单的权限。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public List<BpmFormRights> getByActDefIdAndNodeId(String formKey,String actDefId,String nodeId,PlatformType platformType) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("formKey", formKey);
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getByActDefIdAndNodeId", params);
	}
	
	/**
	 * 根据流程定义id，节点id获取表单的权限。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	public List<BpmFormRights> getByActDefIdAndNodeId(String formKey,String actDefId,String nodeId, String parentActDefId,PlatformType platformType) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("formKey", formKey);
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		params.put("parentActDefId", parentActDefId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getByActDefIdAndNodeIdAndParentActDefId", params);
	}
	
	/**
	 * 根据流程定义id，节点id删除表单权限。
	 * @param actDefId		流程定义ID
	 * @param nodeId		流程节点ID
	 */
	
	public void delByActDefIdAndNodeId(String actDefId,String nodeId,PlatformType platformType){
		Map params=new HashMap();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		this.delBySqlKey("delByActDefIdAndNodeId", params);
	}
	
	/**
	 * 根据流程定义id，节点id删除表单权限。
	 * @param actDefId		流程定义ID
	 * @param nodeId		流程节点ID
	 */
	
	public void delByActDefIdAndNodeId(String actDefId,String nodeId, String parentActDefId,PlatformType platformType){
		Map params=new HashMap();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		params.put("parentActDefId", parentActDefId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		this.delBySqlKey("delByActDefIdAndNodeIdAndParentActDefId", params);
	}
	
	/**
	 * 根据表id删除所有的表单权限数据。
	 * @param tableId
	 */
	public void deleteByTableId(Long tableId,PlatformType platformType){
		String statment="deleteByTableId";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("tableId", tableId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		this.delBySqlKey(statment, params);
	}
	
	/**
	 * 根据表单键删除权限。
	 * @param formKey
	 */
	public void delByFormKey(Long formKey,PlatformType platformType){
		String statment="delByFormKey";
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("formKey", formKey);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		
		this.delBySqlKey(statment, params);
	}
	
	/**
	 * 根据表单键删除权限。
	 * @param formKey
	 * @param cascade 是同时否删除表单的流程的流程节点表单权限设置
	 */
	public void delByFormKey(String formKey,boolean cascade,PlatformType platformType){
		String statment = null;
		if(cascade){
			statment="delByFormKey";	
		}else{
			statment="delByFormKeyExcActDefId";
		}
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("formKey", formKey);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		
		this.delBySqlKey(statment, params);
	}
	
	
	/**
	 * 根据流程定义ID删除流程表单权限设置
	 * @param actDefId 流程定义ID
	 * @param cascade 是同时否删除的流程节点表单权限设置
	 */
	public void delByActDefId(String actDefId,boolean cascade,PlatformType platformType){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("parentActDefId", actDefId);
		String statment=null;
		if(cascade){
			statment="delByActDefId";
		}else{
			statment="delByActDefIdExcNode";
		}
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		this.delBySqlKey(statment, params);
	}
	
	/**
	 * 根据流程定义ID删除流程表单权限设置
	 * @param actDefId 流程定义ID
	 * @param cascade 是同时否删除的流程节点表单权限设置
	 */
	public void delByActDefId(String actDefId,boolean cascade,String parentActDefId,PlatformType platformType){
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("actDefId", actDefId);
		params.put("parentActDefId", parentActDefId);
		String statment=null;
		if(cascade){
			statment="delByActDefId";
		}else{
			statment="delByActDefIdAndParentActDefIdExcNode";
		}
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		this.delBySqlKey(statment, params);
	}

	/**
	 * 根据表单key获得表单权限设置
	 * @param formKey 表单key
	 * 
	 */
	public List<BpmFormRights> getByFormKeyActDefIdIsNotNull(Long formKey,PlatformType platformType) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("formKey", formKey);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getByFormKeyActDefIdIsNotNull", params);
	}
	
	/**
	 * 根据流程定义id，节点id获取表单的权限。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	
	public List<BpmFormRights> getByFlowFormNodeId(String actDefId,String nodeId, String parentActDefId,PlatformType platformType) {
		Map params=new HashMap();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		params.put("parentActDefId", parentActDefId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getByParentFlowFormNodeId", params);
	}
	
	/**
	 * 根据流程定义id，节点id获取表单的权限。
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	
	public List<BpmFormRights> getByFlowFormNodeId(String actDefId,String nodeId,PlatformType platformType) {
		Map params=new HashMap();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getByFlowFormNodeId", params);
	}
	
	
	/**
	 * 根据表单id获取表单权限。
	 * @param formDefId
	 * @return
	 */
	public List<BpmFormRights> getByFormKeyNodeEmpty(String formKey,PlatformType platformType) {
		Map params=new HashMap();
		params.put("formKey", formKey);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getByFormKeyNodeEmpty", params);
	}
	
	/**
	 * 根据formKey删除权限。
	 * @param formDefId
	 */
	
//	public void delByFormDefId(Long formDefId,PlatformType platformType) {
//		Map params=new HashMap();
//		params.put("formDefId", formDefId);
//		if(platformType!=null&&PlatformType.undefine!=platformType) {
//			params.put("platform", platformType.toString());
//		}
//		this.delBySqlKey("delByFormDefId", params);
//	}
	
	/**
	 * 根据流程定义id，节点id删除表单权限。
	 * @param actDefId		流程定义ID
	 * @param nodeId		流程节点ID
	 */
	
	public void delByFlowFormNodeId(String actDefId, String nodeId,PlatformType platformType){
		Map params=new HashMap();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		this.delBySqlKey("delByFlowFormNodeId", params);
	}
	
	/**
	 * 根据父流程定义id，流程定义id，节点id删除表单权限。
	 * @param parentActDefId 流程定义ID
	 * @param actDefId		流程定义ID
	 * @param nodeId		流程节点ID
	 */
	
	public void delByParentFlowFormNodeId(String parentActDefId, String actDefId,String nodeId,PlatformType platformType){
		Map params=new HashMap();
		params.put("parentActDefId", parentActDefId);
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		this.delBySqlKey("delByFlowFormNodeId", params);
	}
	
	
	
	/**
	 * 
	 * TODO方法名称描述
	 * @param formKey
	 * @param actDefId
	 * @param actDefIdIsNull
	 * @param nodeId
	 * @param nodeIdIsNull
	 * @param parentActDefId
	 * @param parentActDefIdIsNull
	 * @param platform
	 * @return 
	 * List<BpmFormRights>
	 * @since  1.0.0
	 */
	public List<BpmFormRights> getBpmFormRights(String formKey,
			String actDefId,boolean actDefIdIsNull ,
			String nodeId, boolean nodeIdIsNull,
			String parentActDefId,boolean parentActDefIdIsNull,
			Integer platform,PlatformType platformType) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("formKey", formKey);
		
		if(actDefIdIsNull){
			params.put("actDefIdIsNull", actDefIdIsNull);
		}else{
			params.put("actDefId", actDefId);
		}
		
		if(nodeIdIsNull){
			params.put("nodeIdIsNull", nodeIdIsNull);
		}else{
			params.put("nodeId", nodeId);
		}
		
		if(parentActDefIdIsNull){
			params.put("parentActDefIdIsNull", parentActDefIdIsNull);
		}else{
			params.put("parentActDefId", parentActDefId);
		}	
		params.put("platform", platform);
		if(platformType!=null&&PlatformType.undefine!=platformType) {
			params.put("platform", platformType.toString());
		}
		return this.getBySqlKey("getBpmFormRights",params);	
	}


	public List<BpmFormRights> getFormRightsByActDefIdWithParent(
			String actDefId, String parentActdefId, PlatformType platform) {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("parentActDefId", parentActdefId);
		params.put("actDefId", actDefId);
		if(platform!=null&&PlatformType.undefine!=platform) {
			params.put("platform", platform.toString());
		}
		return this.getBySqlKey("getFormRightsByActDefIdWithParent",params);
	}
	
	/**
	 * LZC
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delByFlowFormNodeId(String actDefId, String nodeId){
		Map params=new HashMap();
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		this.delBySqlKey("delByFlowFormNodeId", params);
	}


	/**
	 * LZC
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delByParentFlowFormNodeId(String parentActDefId, String actDefId,String nodeId){
		Map params=new HashMap();
		params.put("parentActDefId", parentActDefId);
		params.put("actDefId", actDefId);
		params.put("nodeId", nodeId);
		this.delBySqlKey("delByFlowFormNodeId", params);
	}
}