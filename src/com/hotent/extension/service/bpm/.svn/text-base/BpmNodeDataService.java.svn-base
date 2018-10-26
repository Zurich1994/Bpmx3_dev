package com.hotent.extension.service.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.extension.dao.bpm.BpmNodeDataDao;
import com.hotent.extension.model.bpm.BpmNodeData;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.bpm.BpmNodeSet;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.bpm.BpmNodeSetService;
import com.hotent.platform.service.util.ServiceUtil;

/**
 *<pre>
 * 对象功能:BPM_NODE_DATA Service类
 * 开发公司:哈尔滨工程大学
 * 开发人员:ray
 * 
 *</pre>
 */
@Service
public class BpmNodeDataService extends BaseService<BpmNodeData>
{
	@Resource
	private BpmNodeDataDao dao;
	
	@Resource
	private BpmNodeSetService bpmNodeSetService;
	
	@Resource
	private BpmDefinitionService bpmDefinitionService;
	
	public BpmNodeDataService()
	{
	}
	
	@Override
	protected IEntityDao<BpmNodeData, Long> getEntityDao() 
	{
		return dao;
	}

	public BpmNodeData getBySetId(Long setId) {
		return dao.getBySetId(setId);
	}

	public void deleteBySetId(long setId){
		dao.deleteBySetId(setId);
	}
	
	public List<BpmNodeData> getBpmNodeData(long defId){
		return dao.getBpmNodeData(defId);
	}
	
	public List<BpmNodeData> getGlobalData(long defId){
		return dao.getGlobalData(defId);
	}
	
	public BpmNodeData getByActDefIdAndNodeId(String actDefId,String nodeId){
		
		BpmDefinition bpmDefinition=bpmDefinitionService.getByActDefId(actDefId);
		BpmNodeSet bpmNodeSet=null;
		if(StringUtil.isNotEmpty(nodeId)){
			bpmNodeSet=bpmNodeSetService.getByActDefIdNodeId(actDefId, nodeId);
			BpmNodeData bpmNodeData=this.getBySetId(bpmNodeSet.getSetId());
			if(BeanUtils.isNotEmpty(bpmNodeData.getDialogAlias())){
				return bpmNodeData;
			}else{
				bpmNodeSet=bpmNodeSetService.getBySetType(bpmDefinition.getDefId(), BpmNodeSet.SetType_BpmForm);
				return this.getBySetId(bpmNodeSet.getSetId());
			}
		}else{
			bpmNodeSet=bpmNodeSetService.getBySetType(bpmDefinition.getDefId(), BpmNodeSet.SetType_BpmForm);
			return this.getBySetId(bpmNodeSet.getSetId());
		}
	}
	
	public String getSqlByDialog(BpmFormDialog bpmFormDialog,Map<String, Object>params){
		List<DialogField> displayList = bpmFormDialog.getDisplayList();
		List<DialogField> conditionList = bpmFormDialog.getConditionList();
		String objectName = bpmFormDialog.getObjname();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectName", objectName);
		map.put("displayList", displayList);
		map.put("conditionList", conditionList);
		map.put("sortList", bpmFormDialog.getSortList());
		String sql="";

		// 是否需要分页。
		if (bpmFormDialog.getNeedpage() == 1) {
			int currentPage = 1;
			Object pageObj = params.get(BpmFormDialog.Page);
			if (pageObj != null) {
				currentPage = Integer.parseInt(params.get(
						BpmFormDialog.Page).toString());
			}
			int pageSize = bpmFormDialog.getPagesize();
			Object pageSizeObj = params.get(BpmFormDialog.PageSize);
			if (pageSizeObj != null) {
				pageSize = Integer.parseInt(params.get(
						BpmFormDialog.PageSize).toString());
			}
			sql = ServiceUtil.getSql(map, params);
			
		} else {
			sql = ServiceUtil.getSql(map, params);
		}
		return sql;
	}
}
