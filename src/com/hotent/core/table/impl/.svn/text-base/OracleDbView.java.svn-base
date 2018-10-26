package com.hotent.core.table.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.page.PageBean;
import com.hotent.core.table.BaseDbView;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.colmap.OracleColumnMap;
import com.hotent.core.util.StringUtil;

/**
 * oracle 视图操作类。
 * @author ray
 *
 */
@Component 
public class OracleDbView extends BaseDbView implements IDbView {
	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final String sqlAllView = "select view_name from user_views ";
	private static final String SQL_GET_COLUMNS = "SELECT "
			+ "A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME," +
			"  A.DATA_LENGTH LENGTH,A.DATA_PRECISION PRECISION, "
			+ "A.DATA_SCALE SCALE,A.DATA_DEFAULT, A.NULLABLE NULLABLE, "
			+ "DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, "
			+ "0 AS IS_PK "
			+ "FROM  "
			+ "USER_TAB_COLUMNS A,USER_COL_COMMENTS B " 
			+ "WHERE " 
			+ "A.COLUMN_NAME=B.COLUMN_NAME  " 
			+ "AND  A.TABLE_NAME = B.TABLE_NAME  "
			+ "AND  UPPER(A.TABLE_NAME) =UPPER('%s') " 
			+ "ORDER BY A.COLUMN_ID";
	
	/**
	 * 取得列表
	 */
	private final String SQL_GET_COLUMNS_BATCH = "SELECT " +
			" A.TABLE_NAME TABLE_NAME,A.COLUMN_NAME NAME,A.DATA_TYPE TYPENAME,A.DATA_LENGTH LENGTH," +
			" A.DATA_PRECISION PRECISION,A.DATA_SCALE SCALE,A.DATA_DEFAULT,A.NULLABLE," +
			" DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, "+
			" 0 AS IS_PK "+
			" FROM" +
			" USER_TAB_COLUMNS A,USER_COL_COMMENTS B" +
			" WHERE" +
			" A.COLUMN_NAME=B.COLUMN_NAME AND   " +
			" A.TABLE_NAME = B.TABLE_NAME ";

	/**
	 * 获取系统视图列表。
	 */
	public List<String> getViews(String viewName) throws SQLException {
		String sql=sqlAllView;
		if(StringUtil.isNotEmpty(viewName)){
			sql+=" where lower(view_name) like '"+viewName.toLowerCase()+"%'";
		}
		return this.jdbcTemplate.queryForList(sql,String.class);
	}
	
	/**
	 *转换数据类型。
	 */
	public  String getType(String type){
		type=type.toLowerCase();
		if(type.indexOf("number")>-1 )
			return ColumnModel.COLUMNTYPE_NUMBER;
		else if(type.indexOf("date")>-1){
			return ColumnModel.COLUMNTYPE_DATE;
		}
		else if(type.indexOf("char")>-1){
			return ColumnModel.COLUMNTYPE_VARCHAR;
		}
		return ColumnModel.COLUMNTYPE_VARCHAR;
	}

	public List<String> getViews(String viewName, PageBean pageBean)
			throws Exception {
		String sql=sqlAllView;
		if(StringUtil.isNotEmpty(viewName)){
			sql+=" where lower(view_name) like '"+viewName.toLowerCase()+"%'";
		}
		return getForList(sql, pageBean, String.class,SqlTypeConst.ORACLE);
	}

	public List<TableModel> getViewsByName(String viewName, PageBean pageBean) throws Exception {
		String sql=sqlAllView;
		if(StringUtil.isNotEmpty(viewName)){
			sql+=" WHERE UPPER(VIEW_NAME) LIKE '%"+viewName.toUpperCase()+"%'";
		}
		RowMapper<TableModel> rowMapper=new RowMapper<TableModel>() {
			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel=new TableModel();
				tableModel.setName(rs.getString("VIEW_NAME"));
				return tableModel;
			}
		};
		List<TableModel> tableModels=getForList(sql, pageBean, rowMapper, SqlTypeConst.ORACLE);
		//获取列对象
		List<String> tableNames=new ArrayList<String>();
		//get all table names
		for(TableModel model:tableModels){
			tableNames.add(model.getName());
		}
		//batch get table columns
		Map<String,List<ColumnModel>> tableColumnsMap = getColumnsByTableName(tableNames);
		//extract table columns from paraTypeMap by table name;
		for(Entry<String, List<ColumnModel>> entry:tableColumnsMap.entrySet()){
			//set TableModel's columns
			for(TableModel model:tableModels){
				if(model.getName().equalsIgnoreCase(entry.getKey())){
					model.setColumnList(entry.getValue());
				}
			}
		}
		
		return tableModels;
	}

	/**
	 * 根据表名获取列
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<ColumnModel> getColumnsByTableName(String tableName) {
		String sql = String.format(SQL_GET_COLUMNS, tableName);
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map map = new HashMap();
		List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map,new OracleColumnMap());
		for(ColumnModel model:list){
			model.setTableName(tableName);
		}
		return list;
	}

	/**
	 * 根据表名获取列。此方法使用批量查询方式。
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String,List<ColumnModel>> getColumnsByTableName(List<String> tableNames){
		String sql=SQL_GET_COLUMNS_BATCH;
		Map<String, List<ColumnModel>> map = new HashMap<String, List<ColumnModel>>();
		if(tableNames!=null && tableNames.size()==0){
			return map;
		}else{
			StringBuffer buf=new StringBuffer();
			for(String str:tableNames){
				buf.append("'"+str+"',");
			}
			buf.deleteCharAt(buf.length()-1);
			sql+=" AND A.TABLE_NAME IN ("+buf.toString()+") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		List<ColumnModel> columnModels= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String,Object>(), new OracleColumnMap());
		for(ColumnModel columnModel:columnModels){
			String tableName= columnModel.getTableName();
			if(map.containsKey(tableName)){
				map.get(tableName).add(columnModel);
			}else{
				List<ColumnModel> cols=new ArrayList<ColumnModel>();
				cols.add(columnModel);
				map.put(tableName, cols);
			}
		}
		return map;
	}

	public void createOrRep(String viewName, String sql) throws Exception {
		String getSql="CREATE OR REPLACE VIEW "+viewName+" as ("+sql+")";
//		jdbcHelper=JdbcHelper.getInstance();
		jdbcTemplate.execute(getSql);
	}

}
