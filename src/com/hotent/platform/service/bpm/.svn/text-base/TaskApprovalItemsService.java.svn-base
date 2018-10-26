package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.TaskApprovalItemsDao;
import com.hotent.platform.model.bpm.TaskApprovalItems;
import com.hotent.platform.service.system.GlobalTypeService;

/**
 * 对象功能:常用语管理 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-03-16 10:53:20
 */
@Service
public class TaskApprovalItemsService extends BaseService<TaskApprovalItems>
{
	@Resource
	private TaskApprovalItemsDao dao;
	@Resource
	private GlobalTypeService globalTypeService;
	
	public TaskApprovalItemsService()
	{
	}
	
	@Override
	protected IEntityDao<TaskApprovalItems, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 添加常用语
	 * @param exp
	 * @param isGlobal
	 * @param actDefId
	 * @param setId
	 * @param nodeId
	 * @throws Exception
	 */
	public void addTaskApproval(String exp, Short type, String typeIdArr,
			String  defKeyArr, Long curUserId) throws Exception
	{
		String[] expressions=exp.split("\n");
		if (type==1||type==4) {
			for (String expression:expressions) {
				if (StringUtil.isNotEmpty(expression)) {
					TaskApprovalItems taItem = null;
					taItem = new TaskApprovalItems();
					taItem.setItemId(UniqueIdUtil.genId());
					taItem.setType(type);
					taItem.setExpression(expression);
					taItem.setUserId(curUserId);
					dao.add(taItem);
				}
			}
		}else if (type==2) {
			String[] typeIds=typeIdArr.split(",");
			for (String typeId:typeIds) {
				for (String expression:expressions) {
					if (StringUtil.isNotEmpty(expression)) {
						TaskApprovalItems taItem = null;
						taItem = new TaskApprovalItems();
						taItem.setItemId(UniqueIdUtil.genId());
						taItem.setTypeId(Long.parseLong(typeId));
						taItem.setType(type);
						taItem.setExpression(expression);
						taItem.setUserId(curUserId);
						dao.add(taItem);
					}
				}
			}
		}else if (type==3) {
			String[] defKeys=defKeyArr.split(",");
			for (String defKey:defKeys) {
				for (String expression:expressions) {
					if (StringUtil.isNotEmpty(expression)) {
						TaskApprovalItems taItem = null;
						taItem = new TaskApprovalItems();
						taItem.setItemId(UniqueIdUtil.genId());
						taItem.setDefKey(defKey);
						taItem.setType(type);
						taItem.setExpression(expression);
						taItem.setUserId(curUserId);
						dao.add(taItem);
					}
				}
			}
		}
	}
	
	/**
	 * 取流程常用语。
	 * @param defKey	流程定义Key。
	 * @param typeId	流程分类Id。
	 * @return
	 */
	public List<String> getApprovalByDefKeyAndTypeId(String defKey,Long typeId){
		List<String> taskAppItemsList = new ArrayList<String>();
		Long curUserId=ContextUtil.getCurrentUserId();
		//先获取本人的，系统全局的，和流程下的常用语
		List<TaskApprovalItems> taskAppItem1=dao.getByDefKeyAndUserAndSys(defKey,curUserId);
		//获取分类为3的所有的常用语
		List<TaskApprovalItems> taskAppItem2=dao.getByType(TaskApprovalItems.TYPE_FLOWTYPE);
		
		if (BeanUtils.isNotEmpty(taskAppItem1)) {
			for(TaskApprovalItems taskAppItem:taskAppItem1){
				taskAppItemsList.add(taskAppItem.getExpression());
			}
		}
		if (BeanUtils.isNotEmpty(taskAppItem2)) {
			if(BeanUtils.isEmpty(typeId))return taskAppItemsList;
			//获取分类的父路径
			String typeIdPath=globalTypeService.getById(typeId).getNodePath();
			String[] typeIds=typeIdPath.split("\\.");
			for (int i=1; i< typeIds.length ;i++) {
				for (TaskApprovalItems taskAppItem:taskAppItem2) {
					if ((taskAppItem.getTypeId().toString()).equals(typeIds[i])) {
						taskAppItemsList.add(taskAppItem.getExpression());
					}
				}
			}
		}
		//去除重复的元素
		this.removeDuplicate(taskAppItemsList);
		return taskAppItemsList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void removeDuplicate (List<?> list){
		  HashSet h = new HashSet(list);
	      list.clear();
	      list.addAll(h);
	}
	
	public List<TaskApprovalItems> getByUserAndAdmin(Long currUserId) {
		List<TaskApprovalItems> taskAppItems = dao.getByUserAndAdmin(currUserId);
		return taskAppItems;
	}
	
}
