package com.hotent.platform.service.bpm;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.extension.model.bpm.BpmNodeData;
import com.hotent.extension.service.bpm.BpmNodeDataService;
import com.hotent.mobile.model.form.PlatformType;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.dao.form.BpmFormRightsDao;
import com.hotent.platform.dao.form.BpmFormTableDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.form.BpmFormTable;

/**
 * 对象功能:BpmNodeSetService类 开发公司:广州宏天软件有限公司 开发人员:cjj 创建时间:2011-12-06 13:41:44
 */
@Service
public class BpmNodeSetService extends BaseService<BpmNodeSet> {
	@Resource
	private BpmNodeSetDao dao;

	@Resource
	private BpmFormRightsDao bpmFormRightsDao;

	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	@Resource
	BpmFormTableDao bpmFormTableDao;

	public BpmNodeSetService() {
	}
	
	//add by liuzhenchang
	/**
	 * @author fangjie 
	 * 时间 2014.06.04
	 */
	@Resource
	private BpmNodeDataService bpmNodeDataService ;
	/**
	 * end by fangjie
	 */

	@Override
	protected IEntityDao<BpmNodeSet, Long> getEntityDao() {
		return dao;
	}

//	/**
//	 * 保存节点配置。
//	 * @param defId
//	 * @param nodeList
//	 * @param parentActDefId
//	 * @param isOnlineForm 是否在在线表单
//	 */
//	public void save(Long defId, List<BpmNodeSet> nodeList, String parentActDefId) {
//		if(StringUtil.isEmpty(parentActDefId)){
//			dao.delByStartGlobalDefId(defId);
//			
//		}else{
//			dao.delByStartGlobalDefId(defId, parentActDefId);
//		}
//		for (BpmNodeSet node : nodeList) {
//			if (node.getSetId() == null) {
//				long setId=UniqueIdUtil.genId();
//				node.setSetId(setId);
//				dao.add(node);
//			} else {
//				dao.update(node);
//				// 表单类型
//				if (StringUtil.isNotEmpty(node.getOldFormKey())) {
//					if (!(node.getOldFormKey() == 0)) {
//						if ((node.getFormKey().equals(node.getOldFormKey())))
//							continue;
//						// 原来定义过表单权限，并且新定义的在线表单和原定义的表单不一致。
//						// 删除原节点的权限定义
//						if(StringUtil.isEmpty(parentActDefId)){
//							bpmFormRightsDao.delByFlowFormNodeId(node.getActDefId(), node.getNodeId(),PlatformType.undefine);
//						}
//						else{
//							bpmFormRightsDao.delByParentFlowFormNodeId(node.getActDefId(), node.getNodeId(), parentActDefId,PlatformType.undefine);
//						}
//					}
//				} else {
//					// 设置其他表单类型时，清空权限设置
//					if(StringUtil.isEmpty(parentActDefId)){
//						bpmFormRightsDao.delByFlowFormNodeId(node.getActDefId(), node.getNodeId(),PlatformType.undefine);
//					}
//					else{
//						bpmFormRightsDao.delByParentFlowFormNodeId(node.getActDefId(), node.getNodeId(), parentActDefId,PlatformType.undefine);
//					}
//				}
//			}
//		}
//		bpmFormTableDao.judgeHasMoreThanOneTable(defId);
//	}

	/**
	 * 根据流程设置ID取流程节点设置
	 * 
	 * @param defId
	 * @return
	 */
	public List<BpmNodeSet> getByDefId(Long defId) {
		return dao.getByDefId(defId);
	}
	
	/**
	 * 根据流程设置ID取流程节点设置
	 * 
	 * @param defId
	 * @param parentActDefId
	 * @return
	 */
	public List<BpmNodeSet> getByDefId(Long defId, String parentActDefId) {
		return dao.getByDefIdAndParentActDefId(defId, parentActDefId,false);
	}

	/**
	 * 根据流程设置ID取流程所有的节点设置
	 * 
	 * @param defId
	 * @return
	 */
	public List<BpmNodeSet> getAllByDefId(Long defId) {
		return dao.getAllByDefId(defId);
	}

	/**
	 * 根据流程定义id和节点id获取BpmNodeSet对象。
	 * 
	 * @param defId
	 *            流程定义ID
	 * @param nodeId
	 *            节点ID
	 * @return
	 */
	public BpmNodeSet getByDefIdNodeId(Long defId, String nodeId) {
		return dao.getByDefIdNodeId(defId, nodeId);
	}
	
	/**
	 * 根据流程定义id和节点id获取BpmNodeSet对象。
	 * @param defId 流程定义ID
	 * @param nodeId 节点ID
	 * @param parentActDefId 父流程定义ID
	 * @return
	 */
	public BpmNodeSet getByDefIdNodeId(Long defId, String nodeId, String parentActDefId) {
		return dao.getByDefIdNodeId(defId, nodeId, parentActDefId);
	}

	/**
	 * 根据流程定义获取流程节点设置对象。
	 * @param defId
	 * @return
	 */
	public Map<String, BpmNodeSet> getMapByDefId(Long defId) {
		return dao.getMapByDefId(defId);
	}
	
	/**
	 * 根据流程定义和父流程定义获取流程节点设置对象。
	 * @param defId
	 * @param parentActDefId
	 * @return
	 */
	public Map<String, BpmNodeSet> getMapByDefId(Long defId, String parentActDefId) {
		return dao.getMapByDefId(defId, parentActDefId);
	}

	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体
	 * 
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeSet getByActDefIdNodeId(String actDefId, String nodeId) {
		BpmNodeSet bpmNodeSet=dao.getByActDefIdNodeId(actDefId, nodeId);
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		//节点未设置表单取全局表单
		if (isFormEmpty(bpmNodeSet) ) {
			BpmNodeSet globalNodeSet = getBySetType(bpmDefinition.getDefId(),BpmNodeSet.SetType_GloabalForm);
			if(BeanUtils.isNotEmpty(globalNodeSet)){
				globalNodeSet.setIsHideOption(bpmNodeSet.getIsHideOption());
				globalNodeSet.setIsHidePath(bpmNodeSet.getIsHidePath());
				globalNodeSet.setInformType(bpmNodeSet.getInformType());
				globalNodeSet.setIsAllowMobile(bpmNodeSet.getIsAllowMobile());
				globalNodeSet.setIsJumpForDef(bpmNodeSet.getIsJumpForDef());
				globalNodeSet.setJumpType(bpmNodeSet.getJumpType());
				globalNodeSet.setIsRequired(bpmNodeSet.getIsRequired());
				globalNodeSet.setIsPopup(bpmNodeSet.getIsPopup());
				globalNodeSet.setOpinionField(bpmNodeSet.getOpinionField());
				return globalNodeSet;
			}
		}
		return bpmNodeSet;
	}
	
	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体
	 * 
	 * @param deployId
	 * @param nodeId
	 * @param parentActDefId
	 * @return
	 */
	public BpmNodeSet getByActDefIdNodeId(String actDefId, String nodeId, String parentActDefId) {
		BpmNodeSet bpmNodeSet=dao.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		//节点未设置表单取全局表单
		if (isFormEmpty(bpmNodeSet) ) {
			BpmNodeSet globalNodeSet = getBySetType(bpmDefinition.getDefId(),BpmNodeSet.SetType_GloabalForm, parentActDefId);
			if(BeanUtils.isNotEmpty(globalNodeSet)){
				globalNodeSet.setIsHideOption(bpmNodeSet.getIsHideOption());
				globalNodeSet.setIsHidePath(bpmNodeSet.getIsHidePath());
				globalNodeSet.setInformType(bpmNodeSet.getInformType());
				globalNodeSet.setIsAllowMobile(bpmNodeSet.getIsAllowMobile());
				globalNodeSet.setIsJumpForDef(bpmNodeSet.getIsJumpForDef());
				globalNodeSet.setJumpType(bpmNodeSet.getJumpType());
				globalNodeSet.setIsRequired(bpmNodeSet.getIsRequired());
				globalNodeSet.setIsPopup(bpmNodeSet.getIsPopup());
				globalNodeSet.setOpinionField(bpmNodeSet.getOpinionField());
				return globalNodeSet;
			}
		}
		return bpmNodeSet;
	}
	
	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体(不用考虑是否有绑定表单)
	 * 
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeSet getByMyActDefIdNodeId(String actDefId, String nodeId) {
		BpmNodeSet bpmNodeSet=dao.getByActDefIdNodeId(actDefId, nodeId);
		return bpmNodeSet;
	}
	
	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体(不用考虑是否有绑定表单)
	 * 
	 * @param actDefId
	 * @param nodeId
	 * @param parentActDefId
	 * @return
	 */
	public BpmNodeSet getByMyActDefIdNodeId(String actDefId, String nodeId, String parentActDefId) {
		return dao.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
	}
	
	/**
	 * 通过流程发布ID及节点id获取流程设置节点实体(用于与“是否设置表单”无关的情况)
	 * 
	 * @param deployId
	 * @param nodeId
	 * @return
	 */
	public BpmNodeSet getBpmNodeSetByActDefIdNodeId(String actDefId, String nodeId){
		return dao.getByActDefIdNodeId(actDefId, nodeId);
	}
	
	public BpmNodeSet getBpmNodeSetByActDefIdNodeId(String actDefId, String nodeId, String parentActDefId){
		return dao.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
	}
	
	/**
	 * 判断表单是否为空。
	 * @param bpmNodeSet
	 * @return
	 */
	private boolean isFormEmpty(BpmNodeSet bpmNodeSet) {
		short formType = bpmNodeSet.getFormType();
		String formKey =bpmNodeSet.getFormKey();
		// 没有设置表单的情况
		if (formType == -1) {
			return true;
		}
		// 在线表单的情况
		if (formType == 0) {
			if (formKey == null || formKey.equals("0")) {
				return true;
			}
		}
		// url表单的情况。
		else {
			String formUrl = bpmNodeSet.getFormUrl();
			if (StringUtil.isEmpty(formUrl)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 取得某个流程定义中对应的某个节点为汇总节点的配置
	 * 
	 * @param actDefId
	 * @param joinTaskKey
	 * @return
	 */
	public BpmNodeSet getByActDefIdJoinTaskKey(String actDefId,
			String joinTaskKey) {
		return dao.getByActDefIdJoinTaskKey(actDefId, joinTaskKey);
	}

	/**
	 * 根据流程定义Id和 表单类型查询 默认表单和起始表单。
	 * 
	 * @param defId
	 * @param setType
	 *            值为(2，全局表单,3，流程业务表单)
	 * @return
	 */
	public BpmNodeSet getBySetType(Long defId, Short setType) {
		BpmNodeSet bpmNodeSet =dao.getBySetType(defId, setType);
		return bpmNodeSet;
	}
	
	/**
	 * 根据流程定义Id、父流程定义Id和 表单类型查询 默认表单和起始表单。
	 * 
	 * @param defId
	 * @param setType
	 *            值为(2，全局表单,3，流程业务表单)
	 * @param parentActDefId
	 * @return
	 * @developer helh
	 */
	public BpmNodeSet getBySetType(Long defId, Short setType, String parentActDefId) {
		BpmNodeSet bpmNodeSet =dao.getBySetTypeAndParentActDefId(defId, setType, parentActDefId);
		return bpmNodeSet; 
	}

	/**
	 * 根据流程定义获取当前的表单数据。
	 * 
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeSet> getByActDefId(String actDefId) {
		return dao.getByActDefId(actDefId);
	}
	
	/**
	 * 根据流程定义获取当前的表单数据。
	 * 
	 * @param actDefId
	 * @return
	 */
	public List<BpmNodeSet> getByActDefId(String actDefId, String parentActDefId) {
		return dao.getByActDefId(actDefId,parentActDefId);
	}

	/**
	 * 通过定义ID及节点Id更新isJumpForDef字段的设置
	 * 
	 * @param nodeId
	 * @param actDefId
	 * @param isJumpForDef
	 */
	public void updateIsJumpForDef(String nodeId, String actDefId,
			Short isJumpForDef) {
		dao.updateIsJumpForDef(nodeId, actDefId, isJumpForDef);
	}
	
	/**
	 * 从节点数据中获取子流程的表单
	 * @param defId
	 * @param actDefId
	 * @param parentActDefId
	 * @return
	 * @throws Exception
	 */
	public BpmNodeSet getStartBpmNodeSet(Long defId, String actDefId, String parentActDefId){
		FlowNode flowNode = null;
		try {
			flowNode = NodeCache.getFirstNodeId(actDefId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String nodeId = "";
		if(flowNode!=null){
			nodeId = flowNode.getNodeId();
		}
		BpmNodeSet firstNodeSet=dao.getByActDefIdNodeId(actDefId, nodeId, parentActDefId);
		if(firstNodeSet!=null && !BpmNodeSet.FORM_TYPE_NULL.equals(firstNodeSet.getFormType())){
			return firstNodeSet;
		}
		
		BpmNodeSet globalNodeSet= dao.getByStartGlobal(defId, parentActDefId);
		if(globalNodeSet!=null && !BpmNodeSet.FORM_TYPE_NULL.equals(globalNodeSet.getFormType())){
			return globalNodeSet;
		}
		
		return null;
	}
	
	/**
	 * 获取引用子流程的父流程Id
	 * @param defId
	 * @return
	 */
	public List<BpmNodeSet> getParentIdByDefId(Long defId){
		return dao.getParentIdByDefId(defId);
	}

	/**
	 * 获取当前任务节点的 url明细
	 * 
	 * @param actDefId
	 * @param nodeId
	 * @return
	 */
	// public String getDetailUrl(String actDefId, String nodeId,ProcessRun
	// processRun,String ctxPath) {
	// String detailUrl="";
	// BpmNodeSet bpmNodeSet=dao.getByActDefIdNodeId(actDefId, nodeId);
	// if(BpmNodeSet.FORM_TYPE_ONLINE==bpmNodeSet.getFormType()&&
	// bpmNodeSet.getFormDefId()==null){
	// return "";
	// }
	//
	// if(StringUtil.isNotEmpty(bpmNodeSet.getDetailUrl())){
	// detailUrl=bpmNodeSet.getDetailUrl();
	// }
	//
	// if(StringUtil.isEmpty(detailUrl)){
	// BpmDefinition bpmDefintion=bpmDefinitionService.getByActDefId(actDefId);
	// BpmNodeSet gloabalNodeSet=dao.getBySetType(bpmDefintion.getDefId(),
	// BpmNodeSet.SetType_GloabalForm);
	// if(BeanUtils.isNotEmpty(gloabalNodeSet)){
	// if(StringUtil.isNotEmpty(gloabalNodeSet.getDetailUrl())){
	// detailUrl=gloabalNodeSet.getDetailUrl();
	// }
	// }
	// }
	// if(StringUtil.isNotEmpty(detailUrl)&&
	// StringUtil.isNotEmpty(processRun.getBusinessKey())){
	// detailUrl=detailUrl.replaceFirst(BpmConst.FORM_PK_REGEX,
	// processRun.getBusinessKey());
	// if(!detailUrl.startsWith("http")){
	// detailUrl=ctxPath+detailUrl;
	// }
	// }
	// return detailUrl;
	// }
	
	//add by liuzhenchang
	/**
	 * 保存节点配置。
	 * 
	 * @param nodeList
	 * @throws Exception
	 */
	public void save(Long defId, List<BpmNodeSet> nodeList, String parentActDefId) throws Exception {
		Long setId=0L;
		//start by fangjie 
		Long id = 0L;
		//end
		
		List<BpmNodeSet> bpmNodeSets = dao.getByOther(defId);
		if(StringUtil.isEmpty(parentActDefId)){
			dao.delByStartGlobalDefId(defId);
		}else{
			dao.delByStartGlobalDefId(defId, parentActDefId);
		}
		//start by fangjie 2014.06.11
		for(BpmNodeSet bpmNodeSet:bpmNodeSets){
			bpmNodeDataService.deleteBySetId(bpmNodeSet.getSetId());
		}
		//end
		BpmNodeData bpmNodeData = null ;
		for (BpmNodeSet node : nodeList) {
			if (node.getSetId() == null) {
				setId=UniqueIdUtil.genId();
				node.setSetId(setId);
				dao.add(node);
				/**
				 * start by fangjie 2014.06.10
				 */
				bpmNodeData = new BpmNodeData();
				id = UniqueIdUtil.genId();
				bpmNodeData.setId(id);
				bpmNodeData.setSetId(setId);
				bpmNodeData.setDialogName(node.getName());
				bpmNodeData.setDialogAlias(node.getAlias());
				
				bpmNodeDataService.add(bpmNodeData);
				//end
				
			} else {
				dao.update(node);
				/**
				 * start by fangjie 
				 */
				bpmNodeData=bpmNodeDataService.getBySetId(node.getSetId());
				if(BeanUtils.isEmpty(bpmNodeData)){
					id = UniqueIdUtil.genId();
					bpmNodeData = new BpmNodeData();
					bpmNodeData.setId(id);
					bpmNodeData.setSetId(node.getSetId());
					bpmNodeData.setDialogName(node.getName());
					bpmNodeData.setDialogAlias(node.getAlias());
					bpmNodeDataService.add(bpmNodeData);
				}else{
					bpmNodeData.setDialogName(node.getName());
					bpmNodeData.setDialogAlias(node.getAlias());
					bpmNodeDataService.update(bpmNodeData);
				}
				//end
				// 表单类型
				if (node.getFormType() == 0) {
					if (!(node.getOldFormKey() == "0")) {
						if ((node.getFormKey().equals(node.getOldFormKey())))
							continue;
						// 原来定义过表单权限，并且新定义的在线表单和原定义的表单不一致。
						// 删除原节点的权限定义
						if(StringUtil.isEmpty(parentActDefId)){
							bpmFormRightsDao.delByFlowFormNodeId(node.getActDefId(), node.getNodeId());
						}
						else{
							bpmFormRightsDao.delByParentFlowFormNodeId(node.getActDefId(), node.getNodeId(), parentActDefId);
						}
						
					}
				} else {
					// 设置其他表单类型时，清空权限设置
					if(StringUtil.isEmpty(parentActDefId)){
						bpmFormRightsDao.delByFlowFormNodeId(node.getActDefId(), node.getNodeId());
					}
					else{
						bpmFormRightsDao.delByParentFlowFormNodeId(node.getActDefId(), node.getNodeId(), parentActDefId);
					}
				}
			}
			
		}
		
	}
	
	public void updateNodeProbability(Long defId,String nodeId,String nodeProbability){
		 dao.updateNodeProbability(defId, nodeId,nodeProbability);
	}
	public BpmNodeSet getScopeByNodeIdAndActDefId(String nodeId,String actDefId, String parentActDefId) {
		return dao.getScopeByNodeIdAndActDefId(nodeId,actDefId,parentActDefId);
	}

	public void updateScopeById(Long setId, String scope) {
		if (BeanUtils.isEmpty(setId)) return;
		dao.updateScopeById(setId,scope);
		
	}
	
	//取得UserTask节点个数
	public int getOperateCount(long defId){
		List<BpmNodeSet> nodeSetList = dao.getOperateCount(defId);
		return nodeSetList.size();
	}
	//取得ExternalSubGraph节点个数
	public List<BpmNodeSet> getTransactionCount(long defId){
		List<BpmNodeSet> nodeSetList = dao.getTransactionCount(defId);
		//return nodeSetList.size();
		return  nodeSetList;
	}
	

}