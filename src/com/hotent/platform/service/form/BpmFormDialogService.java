package com.hotent.platform.service.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.hsqldb.jdbc.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.db.JdbcHelper;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.engine.GroovyScriptEngine;
import com.hotent.core.page.PageBean;
import com.hotent.core.page.PageList;
import com.hotent.core.service.BaseService;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.extension.model.bpm.BpmNodeData;
import com.hotent.extension.service.bpm.BpmNodeDataService;
import com.hotent.platform.dao.form.BpmFormDialogDao;
import com.hotent.platform.model.form.BpmFormDialog;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.util.ServiceUtil;

/**
 * 对象功能:通用表单对话框 Service类 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2012-06-25 11:05:09
 */
@Service
public class BpmFormDialogService extends BaseService<BpmFormDialog> {
	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private BpmFormDialogDao dao;

	@Resource
	private GroovyScriptEngine groovyScriptEngine;

	public BpmFormDialogService() {
	}

	@Resource
	private BpmNodeDataService bpmNodeDataService;//add by lzc
	
	@Override
	protected IEntityDao<BpmFormDialog, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 检查模板别名是否唯一
	 * 
	 * @param alias
	 * @return
	 */
	public boolean isExistAlias(String alias) {
		return dao.isExistAlias(alias) > 0;
	}

	/**
	 * 检查模板别名是否唯一。
	 * 
	 * @param alias
	 * @return
	 */
	public boolean isExistAliasForUpd(Long id, String alias) {
		return dao.isExistAliasForUpd(id, alias) > 0;
	}

	/**
	 * 根据别名获取对话框对象。
	 * 
	 * @param alias
	 * @return
	 */
	public BpmFormDialog getByAlias(String alias) {
		return dao.getByAlias(alias);
	}

	/**
	 * 返回树型结构的数据。
	 * 
	 * @param alias
	 * @return
	 * @throws Exception
	 */
	public List getTreeData(String alias) throws Exception {
		BpmFormDialog bpmFormDialog = dao.getByAlias(alias);
		DbContextHolder.setDataSource(bpmFormDialog.getDsalias());
		String sql = getTreeSql(bpmFormDialog);
		List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).queryForList(sql, new HashMap());

		return list;
	}

	/**
	 * 返回树型结构的数据。
	 * 
	 * @param alias
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getTreeData(String alias, Map<String, Object> params, boolean isRoot) throws Exception {
		BpmFormDialog bpmFormDialog = dao.getByAlias(alias);
		/*DbContextHolder.setDataSource(bpmFormDialog.getDsalias());*/
		JdbcTemplate template = JdbcTemplateUtil.getNewJdbcTemplate(bpmFormDialog.getDsalias());
		String sql = null;
		sql = getTreeSql(bpmFormDialog, params, isRoot);
		//System.out.println("sql语句-------------->：" + sql);
		List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(template).queryForList(sql, params);
		return list;
	}

	/**
	 * {"id":"id","pid":"fatherId","displayName":"name"}
	 * 
	 * @param displayField
	 * @param objName
	 * @return
	 * @throws Exception
	 */
	private String getTreeSql(BpmFormDialog bpmFormDialog, Map<String, Object> nodeMap, boolean isRoot) throws Exception {
		String objName = bpmFormDialog.getObjname();
		List<DialogField> conditionList = bpmFormDialog.getConditionList();
		Map<String, Object> params = new HashMap<String, Object>();
		if (BeanUtils.isNotEmpty(nodeMap)) {
			params = nodeMap;
		}
		String pname = (String) nodeMap.get("pname");
		String pvalue = (String) nodeMap.get("pvalue");
		String displayField = bpmFormDialog.getDisplayfield();
		JSONObject jsonObj = JSONObject.fromObject(displayField);
		String id = jsonObj.getString("id");
		String pid = jsonObj.getString("pid");
		if (StringUtil.isNotEmpty(pname) && !isRoot) {
			DialogField pfield = getDailogField(bpmFormDialog, pname);
			if (pfield != null) {
				pfield.setCondition("=");
				conditionList.add(pfield);
				params.put(pname, pvalue);
			}
		}
		// 获取条件的SQL语句
		String sqlWhere = ServiceUtil.getWhere(conditionList, params);
		if (!isRoot) {
			if (sqlWhere.indexOf(pid) < 0 && StringUtil.isNotEmpty(pvalue)) {
				// 若父节点ID的值不为空，则添加查找条件
				if (StringUtil.isEmpty(sqlWhere))
					sqlWhere += " where " + pid + "=" + pvalue;
				else
					sqlWhere += " and " + pid + "=" + pvalue;
			}
		} else {
			// 父节点ID的值，可为空字符串。在设置树形对话框时可对其进行赋值，以获取指定父节点的树
			pvalue = jsonObj.getString("pvalue");
			String isScript = jsonObj.getString("isScript");

			if (StringUtil.isNotEmpty(pvalue) && !"1".equals(pvalue)) {
				if ("true".equals(isScript)) {
					// 父节点ID的值为脚本表达式
					pvalue = groovyScriptEngine.executeObject(pvalue, null).toString();
				}
				// 若父节点ID的值不为空，则添加查找条件
				if (StringUtil.isNotEmpty(sqlWhere)) {
					if (sqlWhere.indexOf(id) < 0)
						sqlWhere += " AND " + id + "=" + pvalue;
				} else {
					sqlWhere += " WHERE " + id + "=" + pvalue;
				}
			}
			if (StringUtil.isEmpty(sqlWhere) && "1".equals(pvalue)) {
				sqlWhere += " WHERE " + pid + "=" + pvalue;
			}
		}
		String sqlSelect = getSelectSQl(bpmFormDialog);
		String isParent = ", ( case (select count(*)  from " + objName + " p where p." + pid + "=o." + id + " and p." + id + "!=p." + pid + ") when 0 then 'false' else 'true' end )isParent ";
		sqlSelect += isParent;
		List<DialogField> sortList = bpmFormDialog.getSortList();
		String orderBy = "";
		for (int i = 0; i < sortList.size(); i++) {
			DialogField df = sortList.get(i);
			if (i == 0) {
				orderBy = " order by ";
			}
			orderBy += df.getFieldName() + " " + df.getComment();
			if (i != sortList.size() - 1) {
				orderBy += ",";
			} else {
				sqlWhere += orderBy;
			}
		}
		String sql = "SELECT " + sqlSelect + " FROM " + objName + " o " + sqlWhere;
		return sql;
	}

	private DialogField getDailogField(BpmFormDialog bpmFormDialog, String fieldname) throws Exception {
		TableModel tableModel;
		int istable = bpmFormDialog.getIstable();
		String dsName = bpmFormDialog.getDsalias();
		String objectName = bpmFormDialog.getObjname();
		// 表
		if (istable == 1) {
			BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
			tableModel = meta.getTableByName(objectName);
		}
		// 视图处理
		else {
			IDbView dbView = TableMetaFactory.getDbView(dsName);
			tableModel = dbView.getModelByViewName(objectName);
		}
		List<ColumnModel> columns = tableModel.getColumnList();
		for (ColumnModel column : columns) {
			if (column.getName().equalsIgnoreCase(fieldname)) {
				DialogField field = new DialogField();
				field.setComment(column.getComment());
				field.setFieldName(column.getName());
				field.setFieldType(column.getColumnType());
				return field;
			}
		}
		return null;
	}

	/**
	 * 根据别名获取对应对话框的数据。
	 * 
	 * @param alias
	 *            对话框别名。
	 * @param params
	 *            参数集合。
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public BpmFormDialog getData(BpmFormDialog bpmFormDialog, Map<String, Object> params) throws Exception {
		
		List<DialogField> displayList = bpmFormDialog.getDisplayList();
		List<DialogField> conditionList = bpmFormDialog.getConditionList();
		String objectName = bpmFormDialog.getObjname();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectName", objectName);
		map.put("displayList", displayList);
		map.put("conditionList", conditionList);
		map.put("sortList", bpmFormDialog.getSortList());
		// 是否是列表
		if (bpmFormDialog.getStyle() == 0) {
			// 是否需要分页。
			if (bpmFormDialog.getNeedpage() == 1) {
				int currentPage = 1;
				Object pageObj = params.get(BpmFormDialog.Page);
				if (pageObj != null) {
					currentPage = Integer.parseInt(params.get(BpmFormDialog.Page).toString());
				}
				int pageSize = bpmFormDialog.getPagesize();
				Object pageSizeObj = params.get(BpmFormDialog.PageSize);
				if (pageSizeObj != null) {
					pageSize = Integer.parseInt(params.get(BpmFormDialog.PageSize).toString());
				}
				String sql = ServiceUtil.getSql(map, params);
				
				List list = JdbcTemplateUtil.getPage(bpmFormDialog.getDsalias(), currentPage, pageSize, sql, params);
/*				for(Object obj:list){
					Map<String,Object> m = (Map<String, Object>) obj;
					for(String key:m.keySet()){
						m.put(key, m.get(key).toString().replace("\"", "'"));
					}
				}
*/				
				bpmFormDialog.setList(list);
				bpmFormDialog.setPageBean(((PageList)list).getPageBean() );
			} else {
				String sql = ServiceUtil.getSql(map, params);
				List<Map<String, Object>> list=jdbcTemplate.queryForList(sql);
				bpmFormDialog.setList(list);
			}
		}

		return bpmFormDialog;

	}

	/**
	 * {"id":"id","pid":"fatherId","displayName":"name"}
	 * 
	 * @param displayField
	 * @param objName
	 * @return
	 */
	private String getTreeSql(BpmFormDialog bpmFormDialog) {

		String objName = bpmFormDialog.getObjname();
		List<DialogField> conditionList = bpmFormDialog.getConditionList();
		Map<String, Object> params = new HashMap<String, Object>();
		// 获取条件的SQL语句
		String sqlWhere = ServiceUtil.getWhere(conditionList, params);

		String sqlSelect = getSelectSQl(bpmFormDialog);
		String sql = "SELECT " + sqlSelect + " FROM " + objName + sqlWhere;

		return sql;
	}

	/**
	 * 从DisplayField和ReturnField中取得Select字段，用于拼接SQL语句
	 * 
	 * @param bpmFormDialog
	 * @return
	 */
	private String getSelectSQl(BpmFormDialog bpmFormDialog) {
		String displayField = bpmFormDialog.getDisplayfield();
		JSONObject jsonObj = JSONObject.fromObject(displayField);
		String id = jsonObj.getString("id");
		String pid = jsonObj.getString("pid");
		String displayName = jsonObj.getString("displayName");
		List<DialogField> returnFields = bpmFormDialog.getReturnList();
		String sqlSelect = id + "," + pid + "," + displayName;
		for (DialogField field : returnFields) {
			String name = field.getFieldName();
			if (name.equalsIgnoreCase(id) || name.equalsIgnoreCase(pid) || name.equalsIgnoreCase(displayName)) {
				continue;
			}
			sqlSelect += "," + name;
		}
		return sqlSelect;
	}

	/**
	 * LZC
	 */
	public BpmFormDialog getByDefIdAndNodeId(String actDefId, String nodeId) {
		
		BpmNodeData bpmNodeData=bpmNodeDataService.getByActDefIdAndNodeId(actDefId, nodeId);
		
		System.out.println("bpmNodeData:"+bpmNodeData);
		
		return dao.getByAlias(bpmNodeData.getDialogAlias());
	}

	/**
	 * LZC
	 */
	
	/**
	 * 根据别名获取对应对话框的数据。
	 * @param alias		对话框别名。
	 * @param params	参数集合。
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public BpmFormDialog getData(String alias,Map<String, Object> params) throws Exception{
		
		BpmFormDialog bpmFormDialog= dao.getByAlias(alias);		
		JdbcHelper jdbcHelper= ServiceUtil.getJdbcHelper(bpmFormDialog.getDsalias());
		
		List<DialogField> displayList=bpmFormDialog.getDisplayList();
		List<DialogField> conditionList=bpmFormDialog.getConditionList();
		String  objectName=bpmFormDialog.getObjname();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("objectName", objectName);
		map.put("displayList", displayList);
		map.put("conditionList", conditionList);
		map.put("sortList", bpmFormDialog.getSortList());
		//是否是列表
		if(bpmFormDialog.getStyle()==0){
			//是否需要分页。
			if(bpmFormDialog.getNeedpage()==1){
				int currentPage=1;
				Object pageObj=params.get(BpmFormDialog.Page);
				if(pageObj!=null){
					currentPage=Integer.parseInt( params.get(BpmFormDialog.Page).toString());
				}
				int pageSize=bpmFormDialog.getPagesize();
				Object pageSizeObj=params.get(BpmFormDialog.PageSize);
				if(pageSizeObj!=null){
					pageSize=Integer.parseInt( params.get(BpmFormDialog.PageSize).toString());
				}
				String sql = ServiceUtil.getSql(map, params);
				
				PageBean pageBean=new PageBean(currentPage,pageSize);
				
				List list= jdbcHelper.getPage(currentPage, pageSize, sql, params,pageBean);
				
				bpmFormDialog.setList(list);
				bpmFormDialog.setPageBean(pageBean);
			}
			else{
				String sql = ServiceUtil.getSql(map, params);
				List<Map<String, Object>> list=jdbcHelper.queryForList(sql, params);
				bpmFormDialog.setList(list);
			}
		}

		
		return bpmFormDialog;
		
	}

	/**
	 * 根据别名获取对应对话框的数据。
	 * @author yangxiao
	 * @param alias
	 *            对话框别名。
	 * @param params
	 *            参数集合。
	 * @return
	 * @throws Exception
	*/
		@SuppressWarnings("unchecked")
	public List getData1(BpmFormDialog bpmFormDialog, Map<String, Object> params) throws Exception {
//		JdbcHelper jdbcHelper = ServiceUtil.getJdbcHelper(bpmFormDialog.getDsalias());		
		List list = null;
		List<DialogField> displayList = bpmFormDialog.getDisplayList();
		List<DialogField> conditionList = bpmFormDialog.getConditionList();
		String objectName = bpmFormDialog.getObjname();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("objectName", objectName);
		map.put("displayList", displayList);
		map.put("conditionList", conditionList);
		map.put("sortList", bpmFormDialog.getSortList());

		if (bpmFormDialog.getStyle() == 0) {

				String sql = ServiceUtil.getSql(map, params);				
//				List list = jdbcHelper.getPage(currentPage, pageSize, sql, params, pageBean);
 				//返回list结果集，不再存入到bpmFormDialog对象中
			 list = JdbcTemplateUtil.getPage1(bpmFormDialog.getDsalias(), sql, params);
			} else {
				String sql = ServiceUtil.getSql(map, params);
//				List<Map<String, Object>> list = jdbcHelper.queryForList(sql, params);
			    list=jdbcTemplate.queryForList(sql);
			}		
		
		return list;
	}
	

}
