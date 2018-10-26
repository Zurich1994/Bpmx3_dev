/**
 * 对象功能:流程定义扩展 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2011-11-21 22:50:46
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.bpm.BpmDefinition;

@Repository
public class BpmDefinitionDao extends BaseDao<BpmDefinition>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmDefinition.class;
	}

	/**
	 * 更新流程序的主版本状态。
	 * @param defId
	 * @param isMain
	 * @return
	 */
//	public int updateMain(Long defId,Short isMain)
//	{
//		Map<String,Object> params=new HashMap<String,Object>();
//		params.put("defId",defId);
//		params.put("isMain", isMain);
//		
//		return update("updateMain", params);
//	}
	
	public int updateSubVersions(Long parentDefId,String defKey)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defKey", defKey);
		params.put("parentDefId", parentDefId);
		
		return update("updateSubVersions",params);
	}
	
	/**
	 * 更新流程启动状态
	 * @param defId
	 * @param disableStatus
	 * @return
	 */
	public int updateDisableStatus(Long defId,Short disableStatus)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("disableStatus", disableStatus);
		return update("updateDisableStatus",params);
	}
	
	/**
	 * 通过DefId获取其所有的历史版本的流程定义
	 * @param defId 
	 * @return
	 */
	public List<BpmDefinition> getByParentDefId(Long defId)
	{
		return getBySqlKey("getByParentDefId", defId);
	}
	
	public List<BpmDefinition> getByParentDefIdIsMain(Long parentDefId,Short isMain)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		
		params.put("parentDefId", parentDefId);
		params.put("isMain", isMain);
		
		return getBySqlKey("getByParentDefIdIsMain",params);
	}
	/**
	 * 按Activiti流程定义Id获取BpmDefinition实体
	 * @param actDefId
	 * @return
	 */
	public BpmDefinition getByActDefId(String actDefId)
	{
		return getUnique("getByActDefId", actDefId);
	}
	/**
	 * 按Activiti的流程定义Key获取BpmDefinition实体
	 * @param actDefKey
	 * @return
	 */
	public List<BpmDefinition>  getByActDefKey(String actDefKey)
	{
		return getBySqlKey("getByActDefKey", actDefKey);
	}
	
	/**
	 * 按Key取得主版本的流程定义
	 * @param actDefKey
	 * @param isMain
	 * @return
	 */
	public BpmDefinition getByActDefKeyIsMain(String actDefKey)
	{
		return getUnique("getByActDefKeyIsMain", actDefKey);
	}
	
	
	/**
	 * 根据分类Id得到流程定义
	 * @param typeId
	 * @return
	 */
	public List<BpmDefinition> getByTypeId(Long typeId){
		return getBySqlKey("getByTypeId", typeId);
	}
	
	public List<BpmDefinition> getAllForAdmin(QueryFilter queryFilter)
	{
		return getBySqlKey("getAllForAdmin", queryFilter);
	}
	/**
	 * 设置流程标题规则
	 * @param defId
	 * @param isMain
	 * @return
	 */
	public int saveParam(BpmDefinition bpmDefinition)
	{
		return update("saveParam", bpmDefinition);
	}
	/**
	 * 根据actDeployId删除流程定义
	 * @param actDeployId
	 */
	public void delByDeployId(String actDeployId){
		
		 delBySqlKey("delByDeployId", actDeployId);
	}
	/**
	 * 根据actDeployId查询该流程定义
	 * @param actDeployId
	 * @return
	 */
	public BpmDefinition getByDeployId(String actDeployId){
		return getUnique("getByDeployId", actDeployId);
	}
	
	/**
	 * 判断流程key是否存在。
	 * @param key
	 * @return
	 */
	public boolean isActDefKeyExists(String key){
		Integer rtn=(Integer) getOne("isActDefKeyExists", key);
		return rtn>0;
	}
	
	/**
	 * 判断defkey是否存在。
	 * @param key
	 * @return
	 */
	public boolean isDefKeyExists(String defkey){
		Integer rtn=(Integer) getOne("isDefKeyExists", defkey);
		return rtn>0;
	}
	
	/**
	 * 按分管授权的内容查找我能访问的所有流程定义
	 * @param queryFilter
	 * @return
	 */
	public List<BpmDefinition> getMyDefList(QueryFilter queryFilter){
		return getBySqlKey("getByAuthorizeFilter", queryFilter);
	}
	
	
	public List<BpmDefinition> getByUserId(QueryFilter queryFilter)
	{
		return getBySqlKey("getByUserId", queryFilter);
	}
	/**
	 * 按用户Id及查询参数查找我能访问的所有流程定义
	 * @param queryFilter
	 * @return
	 */
	public List<BpmDefinition> getByUserIdFilter(QueryFilter queryFilter){
		return getBySqlKey("getByUserIdFilter", queryFilter);
	}
	
	public List<BpmDefinition> getByUserId(Map<String,Object> params){
		return getBySqlKey("getByUserIdFilter", params);
	}
	
	/**
	 * 通过标题模糊查询所有发布的、默认版本的流程
	 * @param subject
	 * @return
	 */
	public List<BpmDefinition> getAllPublished(String subject){
		Map filter=new HashMap();
		filter.put("subject", subject);
		return getBySqlKey("getAllPublished", filter);
	}
	
	/**
	 * 通过类型ID查询所有发布的、默认版本的流程
	 * @param typeId
	 * @return
	 */
	public List<BpmDefinition> getPublishedByTypeId(String typeId){
		return getBySqlKey("getPublishedByTypeId", typeId);
	}
	
	/**
	 * 根据流程定义key获得当前最新版本的流程定义
	 * @param defkey 
	 * @return
	 */
	public BpmDefinition getMainByDefKey(String defKey){
		return getUnique("getMainByDefKey", defKey);
	}

	/**
	 * 根据用户ID，获该用户所创建的流程定义
	 * @param userId 用户ID
	 * @param pb 分页Bean
	 * @return
	 */
	public List<BpmDefinition> getByUserId(Long userId ,Map<String,Object> params,PageBean pb) {
		return getBySqlKey("getByUserIdFilter", params,pb);
	}
	/**
	 * 根据actDeployId删除流程定义数据表
	 * @param actDeployId
	 * @return
	 */
	public void delProcDefByActDeployId(Long actDeployId){
		delBySqlKey("delBytearRayByActDeployId", actDeployId);
		delBySqlKey("delDeployMentByActDeployId", actDeployId);
		delBySqlKey("delProcDefByActDeployId", actDeployId);
	}

	public void updCategory(Long typeId, List<String> defKeyList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeId", typeId);
		map.put("defKeys", defKeyList);
		this.update("updCategory",  map);
		
	}
	
	//***********************************

    
	public void updCategory1(Long typeId, String[] defKey) {
		
		for(int i=0;i<defKey.length;i++){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("typeId", typeId);
		map.put("defKeys", defKey[i]);	
		
		this.insert("updCategory1",  map);}
	}
	


	/**
	 * 根据流程定义key获得流程定义
	 * @param defkey 
	 * @return
	 */
	public List<BpmDefinition> getByDefKey(String defKey){
		return getBySqlKey("getByDefKey", defKey);
	}
	
	public List<BpmDefinition> getBpmDefinitionByFormKey(String formKey){
		return getBySqlKey("getBpmDefinitionByFormKey", formKey);
	}
	
	/**
	 * 通过CreateBy获取其所有自己创建的流程定义KEY
	 * @param userId 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getByCreateBy(Long userId)
	{
		return getBySqlKeyGenericity("getByCreateBy", userId);
	}

	/**
	 * 桌面的新建流程
	 * @param actRights
	 * @return
	 */
	public List<BpmDefinition> getMyDefListForDesktop(String actRights) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isNeedRight", "yes");
		map.put("actRights", actRights);
		return getBySqlKey("getByAuthorizeFilter", map);
	}
	
	
	/**
	 * 查找流程定义列表
	 */
	public List<BpmDefinition> getUserBpmList(String actRights,String isNeedRight){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isNeedRight", isNeedRight);
		map.put("actRights", actRights);
		return getBySqlKey("getByAuthorizeFilter", map);
	}
	
	public List<BpmDefinition> getDefinitionById(String defId){
		//Map<String,Object> paramMap = new HashMap<String,Object>();
		//paramMap.put("defId", defId);
		
		return getBySqlKey("getDefinitionById", defId);
	}
	public List<BpmDefinition> getByDefId(Long defId){
		return getBySqlKey("getByDefId",defId);
	}
	public void updataDefXml(String defId,String defXml){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("defId", defId);
		paramMap.put("defXml", defXml);
		getBySqlKey("updataDefXml",paramMap);
	} 
	public 	List<BpmDefinition> getBySubjectAndTypeId(String subject,String typeId)
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(subject)) {
			map.put("subject", "%" + subject + "%");
		}
		map.put("typeId", typeId);
		return getBySqlKey("getBySubjectAndTypeId", map);
	}
}