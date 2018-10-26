package com.hotent.platform.service.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.bpm.model.FlowNode;
import com.hotent.core.bpm.model.NodeCache;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmDefinitionDao;
import com.hotent.platform.dao.bpm.BpmFormRunDao;
import com.hotent.platform.dao.bpm.BpmNodeSetDao;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmFormRun;
import com.hotent.platform.model.bpm.BpmNodeSet;

/**
 * 对象功能:流程表单运行情况 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-05-21 16:28:40
 */
@Service
public class BpmFormRunService extends BaseService<BpmFormRun>
{
	@Resource
	private BpmFormRunDao dao;
	
	@Resource
	private BpmNodeSetDao bpmNodeSetDao;
	
	@Resource
	private BpmDefinitionDao bpmDefinitionDao;
	
	
	
	public BpmFormRunService()
	{
	}
	
	@Override
	protected IEntityDao<BpmFormRun, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 添加表单运行情况。
	 * <pre>
	 * 将当前最新的表单配置信息添加到表单运行情况。
	 * 之后的流程表单从表单运行情况表中取值。
	 * </pre>
	 * @param actDefId	表单定义ID
	 * @param runId		process runID
	 * @param actInstanceId		流程实例ID
	 * @throws Exception
	 */
	public void addFormRun(String actDefId,Long runId,String actInstanceId) {
	    List<BpmNodeSet> list=bpmNodeSetDao.getOnlineFormByActDefId(actDefId);
	    for(BpmNodeSet bpmNodeSet:list){
	    	BpmFormRun bpmFormRun=getByBpmNodeSet(runId, actInstanceId, bpmNodeSet);
	    	dao.add(bpmFormRun);
	    }
	}
	
	/**
	 * 添加表单运行情况。
	 * <pre>
	 * 将当前最新的表单配置信息添加到表单运行情况。
	 * 之后的流程表单从表单运行情况表中取值。
	 * 1、取子流程设置的表单
	 * </pre>
	 * @param actDefId	表单定义ID
	 * @param runId		process runID
	 * @param actInstanceId		流程实例ID
	 * @throws Exception
	 */
	public void addFormRun(String actDefId, Long runId, String actInstanceId, String parentActDefId) {
	    List<BpmNodeSet> list = bpmNodeSetDao.getOnlineFormByActDefId(actDefId, parentActDefId);
//	    if(BeanUtils.isEmpty(list)){
//	    	list = bpmNodeSetDao.getOnlineFormByActDefId(parentActDefId);
//	    }
	    for(BpmNodeSet bpmNodeSet:list){
	    	BpmFormRun bpmFormRun=getByBpmNodeSet(runId, actInstanceId, bpmNodeSet);
	    	dao.add(bpmFormRun);
	    }
	}
	
	/**
	 * 获取默认或起始的流程表单。
	 * @param list
	 * @param setType
	 * @return
	 */
	private BpmNodeSet getDefalutStartForm(List<BpmNodeSet> list,Short setType){
		BpmNodeSet bpmNodeSet=null;
		for(BpmNodeSet node:list){
			if(node.getSetType().equals(setType)){
				bpmNodeSet=node;
				break;
			}
		}
		return bpmNodeSet;
	}
	
	
	/**
	 * 获取开始节点的表单运行情况。
	 * @param list
	 * @param setType
	 * @return
	 */
	@SuppressWarnings("unused")
	private BpmNodeSet getGlobalForm(List<BpmNodeSet> list){
		BpmNodeSet bpmNodeSet =getDefalutStartForm(list,BpmNodeSet.SetType_GloabalForm);
		return bpmNodeSet;
	}
	
	/**
	 * 获取节点运行的form列表。
	 * @param list
	 * @return
	 */
	public Map<String, BpmNodeSet> getTaskForm(List<BpmNodeSet> list ){
		Map<String, BpmNodeSet> map=new HashMap<String, BpmNodeSet>();
		for(BpmNodeSet node:list){
			if(node.getSetType().equals(BpmNodeSet.SetType_TaskNode)){
				map.put(node.getNodeId(), node);
			}
		}
		return map;
	}
	
	
	/**
	 * 根据BpmNodeSet对象构建BpmFormRun对象。
	 * @param actDefId		流程定义id。
	 * @param runId			流程运行ID。
	 * @param actInstanceId	流程实例ID。
	 * @param bpmNodeSet	 流程节点对象。
	 * @return
	 * @throws Exception
	 */
	private BpmFormRun getByBpmNodeSet(Long runId,String actInstanceId,BpmNodeSet bpmNodeSet) {
		BpmFormRun bpmFormRun=new BpmFormRun();
		bpmFormRun.setId(UniqueIdUtil.genId());
		bpmFormRun.setRunId(runId);
		bpmFormRun.setActInstanceId(actInstanceId);
		bpmFormRun.setActDefId(bpmNodeSet.getActDefId());
		bpmFormRun.setActNodeId(bpmNodeSet.getNodeId());
		bpmFormRun.setFormdefId(bpmNodeSet.getFormDefId());
		bpmFormRun.setFormdefKey(bpmNodeSet.getFormKey());
		bpmFormRun.setFormType(bpmNodeSet.getFormType());
		bpmFormRun.setFormUrl(bpmNodeSet.getFormUrl());
		bpmFormRun.setSetType(bpmNodeSet.getSetType());
		if(bpmNodeSet.getMobileForm()!=null) {
			bpmFormRun.setMobileFormId(bpmNodeSet.getMobileForm().getFormId());
		}
		bpmFormRun.setMobileFormKey(bpmNodeSet.getMobileFormKey());
		return bpmFormRun;
	}
	
	
	/**
	 * 
	 * @param actDefId
	 * @param toFirstNode
	 * @return
	 * @throws Exception 
	 */
	public BpmNodeSet getStartBpmNodeSet(Long defId,String actDefId) throws Exception{
		FlowNode flowNode =NodeCache.getFirstNodeId(actDefId);
		String nodeId = "";
		if(flowNode!=null){
			nodeId = flowNode.getNodeId();
		}
		
		BpmNodeSet firstNodeSet=bpmNodeSetDao.getByActDefIdNodeId(actDefId, nodeId);
		if(firstNodeSet!=null && !BpmNodeSet.FORM_TYPE_NULL.equals(firstNodeSet.getFormType())){
			return firstNodeSet;
		}
		
		BpmNodeSet globalNodeSet= bpmNodeSetDao.getByStartGlobal(defId);
		if(globalNodeSet!=null && !BpmNodeSet.FORM_TYPE_NULL.equals(globalNodeSet.getFormType())){
			return globalNodeSet;
		}
		
		return null;
		
	}
	
	/**
	 * 判断是否可以直接启动。
	 * <pre>
	 * 	
	 * </pre>
	 * @param defId
	 * @return
	 */
	public boolean getCanDirectStart(Long defId){
		BpmDefinition bpmDefinition=bpmDefinitionDao.getById(defId);
		
		Integer directStart=bpmDefinition.getDirectstart();
		if(directStart==null)
			return true;
	
		return  directStart.intValue()==1;
		
	}

	
	/**
	 * 取得流程运行表单情况。
	 * @param actInstanceId
	 * @param actNodeId
	 * @return
	 */
	public BpmFormRun getByInstanceAndNode(String actInstanceId,String actNodeId){
		//根据流程实例id和任务节点id获取表单。
		BpmFormRun bpmFormRun=  dao.getByInstanceAndNode(actInstanceId, actNodeId);
		if(bpmFormRun!=null && bpmFormRun.getFormType()!=null && bpmFormRun.getFormType()!=-1){
			return bpmFormRun;
		}
		else{
			//没有获取到则再获取全局表单。
			bpmFormRun=dao.getGlobalForm(actInstanceId);
			if(bpmFormRun!=null && bpmFormRun.getFormType()!=null && bpmFormRun.getFormType()!=-1){
				return bpmFormRun;
			}
			return null;
		}
	}
	
	
	/**
	 * 取得流程运行表单情况，与getByInstanceAndNode不同，此方法不对取得的表单做任务判断和处理。
	 * @param actInstanceId
	 * @param actNodeId
	 * @return
	 */
	public BpmFormRun getByInstanceAndNodeId(String actInstanceId,String actNodeId){
		//根据流程实例id和任务节点id获取表单。
		BpmFormRun bpmFormRun=  dao.getByInstanceAndNode(actInstanceId, actNodeId);
		return bpmFormRun;
	}
	
	/**
	 * 根据流程实例ID，流程实例的运行表单列表
	 * @param actInstanceId
	 * @return
	 */
	public List<BpmFormRun> getByInstanceId(String actInstanceId){
		return dao.getByInstanceId(actInstanceId);
	}
	
	
	//========TODO 手机表单数据

	/**
	 *  根据流程实例ID、节点ID，获取流程实例运行时节点表单
	 * @param instId 流程实例ID
	 * @param nodeId 节点ID
	 * @return
	 */
	public BpmFormRun getNodeFormRun(String instId,String nodeId) {
		return dao.getByInstanceAndNode(instId, nodeId);
	}

	/**
	 * 根据流程实例ID，获取流程实例运行时全局表单
	 * @param instId 流程实例ID
	 * @return
	 */
	public BpmFormRun getGlobalForm(String instId) {
		return dao.getGlobalForm(instId);
	}

	/**
	 * 根据流程实例ID，获取流程实例运行时开始表单
	 * @param instId 流程实例ID
	 * @return
	 */
	public BpmFormRun getStartFormRun(String instId) {
		return dao.getStartFormRun(instId);
	}
}


