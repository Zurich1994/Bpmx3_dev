/**
 * 对象功能:自定义表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:xwy
 * 创建时间:2011-11-30 14:29:22
 */
package com.hotent.platform.dao.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.form.BpmFormTable;

@Repository
public class BpmFormTableDao extends BaseDao<BpmFormTable>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmFormTable.class;
	}

	/**
	 * 表名是否已存在。
	 * <pre>
	 * 表名区分大小写，使用多数据库。
	 * </pre>
	 * @param tableName
	 * @return
	 */
	public boolean isTableNameExisted(String tableName)
	{
		return (Integer) this.getOne("isTableNameExisted_"+this.getDbType(), tableName) > 0;
	}
	
	/**
	 * 判断表是否已经存在。
	 * <pre>
	 * 表名区分大小写，使用多数据库。
	 * </pre>
	 * @param tableId
	 * @param tableName
	 * @return
	 */
	public boolean isTableNameExistedForUpd(Long tableId, String tableName)
	{
		Map params =new HashMap();
		params.put("tableId", tableId);
		params.put("tableName", tableName);
		return (Integer) this.getOne("isTableNameExistedForUpd_"+this.getDbType(), params) > 0;
	}
	
	
	
	/**
	 * 判断表名是否存在。
	 * @param tableName
	 * @param dsAlias
	 * @return
	 */
	public boolean isTableNameExternalExisted(String tableName,String dsAlias)
	{
		BpmFormTable bpmFormTable=new BpmFormTable();
		bpmFormTable.setTableName(tableName);
		bpmFormTable.setDsAlias(dsAlias);
		return (Integer) this.getOne("isTableNameExternalExisted", bpmFormTable) > 0;
	}
	


	
	/**
	 * 通过mainTableId获得所有字表
	 * 
	 * @param mainTableId
	 * @return
	 */
	public List<BpmFormTable> getSubTableByMainTableId(Long mainTableId)
	{
		return this.getBySqlKey("getSubTableByMainTableId", mainTableId);
	}
	
	/**
	 * 按数据源别名获取所有主表
	 * @param dsName
	 * @return
	 */
	public List<BpmFormTable> getMainTableByDsName(String dsName){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("dsName",dsName);
		return this.getBySqlKey("getMainTableByDsName",params);
	}
	
	/**
	 * 获取可以关联的主表。
	 * 1.没有关联表单。
	 * 2.可以是发布或未发布的。
	 * @return
	 */
	public List<BpmFormTable> getAssignableMainTable()
	{
		return this.getBySqlKey("getAssignableMainTable");
	}
	
	/**
	 * 按过滤器查询记录列表
	 * @param queryFilter
	 * @return
	 */
	@Override
	public List<BpmFormTable> getAll(QueryFilter queryFilter){
		String sqlKey="getAll_"+getDbType();
		List<BpmFormTable> list =getBySqlKey(sqlKey, queryFilter);
		return list;
	}
	
	
	
	/**
	 * 获取未关联的主表。
	 * @return
	 */
	public List<BpmFormTable> getAllUnpublishedMainTable(){
		return this.getBySqlKey("getAllUnpublishedMainTable");
	}

	
	/**
	 * 获得所有未分配的子表
	 * @param tableName
	 * @return
	 */
	public List<BpmFormTable> getAllUnassignedSubTable( )
	{
		return this.getBySqlKey("getAllUnassignedSubTable", null);
	}
	
	
	
	/**
	 * 获取默认已发布的主表列表。
	 * @param queryFilter
	 * @return
	 */
	public List<BpmFormTable> getAllMainTable(QueryFilter queryFilter) {
		String statementName = getIbatisMapperNamespace() + ".getAllMainTable_"+getDbType();
		
		List<BpmFormTable> list= getList(statementName,queryFilter);
		//设置回请求对象
		queryFilter.setForWeb();
		return list;
	}
	
	/**
	 * 根据数据源查询子表
	 * @param dsName
	 * @return
	 */
	public List<BpmFormTable> getByDsSubTable(String dsName){
		 List<BpmFormTable> list= this.getBySqlKey("getByDsSubTable", dsName);
		 return list;
	}
	
	/**
	 * 更新关联字段
	 * @param bpmFormTable
	 */
	public void updateRelations(BpmFormTable bpmFormTable){
		this.update("updateRelations", bpmFormTable);
	}
	
	/**
	 * 更新主表字段。
	 * @param bpmFormTable
	 */
	public void updateMain(BpmFormTable bpmFormTable){
		this.update("updateMain", bpmFormTable);
	}
	
	/**
	 * 清空子表的主表字段。
	 * @param mainTableId
	 */
	public void updateMainEmpty(Long mainTableId){
		this.update("updateMainEmpty", mainTableId);
	}
	
	/**
	 * 修改表为发布。
	 * @param bpmFormTable
	 */
	public void updPublished(BpmFormTable bpmFormTable){
		this.update("updPublished", bpmFormTable);
	}
	
		
	
	/**
	 * 根据数据源和表名获取 BpmFormTable 对象。
	 * @param dsName
	 * @param tableName
	 * @return
	 */
	public BpmFormTable getByDsTablename(String dsName,String tableName){
		Map<String,String> params=new HashMap<String, String>();
		params.put("dsAlias", dsName);
		params.put("tableName", tableName);
		return (BpmFormTable) this.getOne("getByDsTablename", params);
	}
	
	/**
	 * 根据表名获得 BpmFormTable 对象
	 * @param tableName
	 * @return
	 */
	public BpmFormTable getByTableName(String tableName){
		return getUnique("getByTableName_" + this.getDbType(), tableName.toLowerCase());
	}

	public List<BpmFormTable> getMainTables(String tableName) {
		Map<String,String> params=new HashMap<String, String>();
		params.put("tableName", tableName);
		return this.getBySqlKey("getMainTables",params);
	}
	
	public BpmFormTable getByAliasTableName(String dsAlias,String tableName){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("dsAlias", dsAlias);
		params.put("tableName", tableName);
		BpmFormTable bpmFormTable= this.getUnique("getByAliasTableName",params);
		return bpmFormTable;
	}
	
	/**
	 * 根据流程定义ID返回表名称。
	 * 如果是外部表则返回 数据源别名 +“_" + 表名
	 * @param defId
	 * @return
	 */
	public List<BpmFormTable> getTableNameByDefId(Long defId){
		return  this.getBySqlKey("getTableNameByDefId",defId);
	}
	
	/**
	 * 根据流程定义ID和父流程定义ID返回表名称。（用于子流程）
	 * 如果是外部表则返回 数据源别名 +“_" + 表名
	 * @param defId
	 * @param parentActDefId
	 * @return
	 */
	public List<BpmFormTable> getTableNameByDefId(Long defId, String parentActDefId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("parentActDefId", parentActDefId);
		return  this.getBySqlKey("getTableNameByDefIdAndParentActDefId", params);
	}
	
	/**
	 * 根据流程定义获取流程表
	 * @param defId
	 * @return
	 * @throws Exception 
	 */
	public BpmFormTable getByDefId(Long defId) {
		List<BpmFormTable> list=this.getBySqlKey("getByDefId", defId);
		if(BeanUtils.isEmpty(list)){
			throw new RuntimeException("流程没有定义的表单!");
		}
		if(list.size()>1){
			throw new RuntimeException("流程定义只能对应一个表!");
		}
		return list.get(0);
	}
	
	/**
	 * 判断流程是否绑定了一个以上的表
	 * @param defId
	 */
	public void judgeHasMoreThanOneTable(Long defId){
		List<BpmFormTable> list=this.getBySqlKey("getByDefId", defId);
		if(list.size()>1){
			throw new RuntimeException("流程定义只能对应一个表!");
		}
	}
	
	/**
	 * 根据流程定义与父流程定义获取流程表
	 * @param defId
	 * @param parentActDefId
	 * @return
	 * @throws Exception 
	 */
	public BpmFormTable getByDefId(Long defId, String parentActDefId) throws Exception{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("parentActDefId", parentActDefId);
		List<BpmFormTable> list=this.getBySqlKey("getByDefIdAndParentActDefId", params);
		if(BeanUtils.isEmpty(list))
			throw new Exception("流程没有定义的表单!");
		if(list.size()>1)
			throw new Exception("流程定义只能对应一个表!");
		return list.get(0);
	}
	
	/**
	 * 获得所有的表
	 * @return
	 */
	public List<BpmFormTable> getAllTable(){
		return this.getBySqlKey("getAllTable");
		
	}
	/**
	 * 更新分类
	 * @param categoryId
	 * @param aryTableId
	 */
	public void updCategory(Long categoryId, String[] aryTableId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		map.put("aryTableId", aryTableId);
		this.update("updCategory",  map);
		
	}
	
}