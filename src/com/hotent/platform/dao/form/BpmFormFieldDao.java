/**
 * 对象功能:自定义表字段 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2011-11-30 15:20:14
 */
package com.hotent.platform.dao.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.form.BpmFormField;

@Repository
public class BpmFormFieldDao extends BaseDao<BpmFormField>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmFormField.class;
	}
	
	/**
	 * 通过tableId查找
	 * @param tableId
	 * @return
	 */
	public List<BpmFormField> getByTableId(Long tableId) {
		return this.getBySqlKey("getByTableId", tableId);
	}
	
	public List<BpmFormField> getByTableIdContainHidden(Long tableId) {
		return this.getBySqlKey("getByTableIdContainHidden", tableId);
	}
	
	
	
	/**
	 * 通过tableId查找所有（包括已删除的）
	 * @param tableId
	 * @return
	 */
	public List<BpmFormField> getAllByTableId(Long tableId) {
		return this.getBySqlKey("getAllByTableId", tableId) ;
	}
	
	
	/**
	 * 将该表所有字段标识为删除
	 * @param tableId
	 * @return
	 */
	public int markDeletedByTableId(Long tableId) {
		return this.update("markDeletedByTableId", tableId);
		
	}
	
	/**
	 * 根据流程defId获取自定义表中的流程变量列表。
	 * @param defId
	 * @return
	 */
	public List<BpmFormField> getFlowVarByFlowDefId(Long defId){
		return this.getBySqlKey("getFlowVarByFlowDefId", defId);
	}
	
	/**
	 * 根据流程defId和父流程定义ID获取自定义表中的流程变量列表。
	 * @param defId
	 * @param parentActDefId
	 * @return
	 */
	public List<BpmFormField> getFlowVarByFlowDefId(Long defId, String parentActDefId){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("defId", defId);
		params.put("parentActDefId", parentActDefId);
		return this.getBySqlKey("getFlowVarByFlowDefIdAndParentActDefId", params);
	}
	
	/**
	 * 根据表id删除表的定义。
	 * @param tableId
	 */
	public void delByTableId(Long tableId){
		 this.delBySqlKey("delByTableId", tableId);
	}
	
	/**
	 * 根据 tableid 和 fieldname 获得 BpmFormField
	 * @param tableId
	 * @param fieldName
	 * @return
	 */
	public BpmFormField getFieldByTidFna(Long tableId,String fieldName) {
		
		Map map = new HashMap();
		map.put("tableId", tableId);
		map.put("fieldName", fieldName);
		return this.getUnique("getFieldByTidFna", map);
	}
	
	/**
	 * 根据表获取所有的字段。
	 * @param tableId
	 * @return
	 */
	public List<BpmFormField> getFieldsByTableId(Long tableId){
		return this.getBySqlKey("getFieldsByTableId", tableId);
	}
	
	/**
	 * 根据 tableid 和 fieldname 和subTableName 获得 不是隐藏字段的BpmFormField
	 * @param tableId
	 * @param fieldName
	 * @param subTableName
	 * @return
	 */
	public BpmFormField getFieldByTidFnaNh(Long tableId,String fieldName,String subTableName) {
		Map map = new HashMap();
		map.put("tableId", tableId);
		map.put("fieldName", fieldName);
		map.put("subTableName", subTableName);
		return this.getUnique("getFieldByTidFnaNh", map);
	}
	
}