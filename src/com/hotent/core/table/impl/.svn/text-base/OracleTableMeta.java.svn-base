package com.hotent.core.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.colmap.OracleColumnMap;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;

/**
 * ORACLE数据库表元数据的读取。
 * @author ray
 *
 */
@Component
public class OracleTableMeta extends BaseTableMeta {
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 取得主键
	 */
	private String sqlPk = "select column_name from user_constraints c,user_cons_columns col where c.constraint_name=col.constraint_name and c.constraint_type='P'and c.table_name='%s'";

	/**
	 * 取得注释
	 */
	private String sqlTableComment = "select TABLE_NAME,DECODE(COMMENTS,null,TABLE_NAME,comments) comments from user_tab_comments  where table_type='TABLE' AND table_name ='%s'";

	/**
	 * 取得列表
	 */
	private final String SQL_GET_COLUMNS = "SELECT " +
			" 	A.TABLE_NAME TABLE_NAME, "+
			" 	A.COLUMN_NAME NAME, "+
			" 	A.DATA_TYPE TYPENAME, "+
			" 	A.DATA_LENGTH LENGTH,  "+
			" 	A.DATA_PRECISION PRECISION, "+
			" 	A.DATA_SCALE SCALE, "+
			" 	A.DATA_DEFAULT, "+
			" 	A.NULLABLE,  "+
			" 	DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, "+
			" 	( "+
			"   	  SELECT "+ 
			"   	    COUNT(*) "+ 
			"   	  FROM  "+
			"   	    USER_CONSTRAINTS CONS, "+
			"    	   USER_CONS_COLUMNS CONS_C  "+
			"    	 WHERE  "+
			"    	   CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME "+ 
			"    	   AND CONS.CONSTRAINT_TYPE='P' "+
			"    	   AND CONS.TABLE_NAME=B.TABLE_NAME "+
			"     	  AND CONS_C.COLUMN_NAME=A.COLUMN_NAME "+
			"  	 ) AS IS_PK "+
			" FROM  "+
			" 	 USER_TAB_COLUMNS A, "+
		    " 	USER_COL_COMMENTS B  "+
		    " WHERE  "+
		    " 	A.COLUMN_NAME=B.COLUMN_NAME "+ 
		    " 	AND A.TABLE_NAME = B.TABLE_NAME "+ 
		    " 	AND A.TABLE_NAME='%s' "+
			" ORDER BY " +
			"  	A.COLUMN_ID";
	/**
	 * 取得列表
	 */
	private final String SQL_GET_COLUMNS_BATCH ="SELECT " +
			" 	A.TABLE_NAME TABLE_NAME, "+
			" 	A.COLUMN_NAME NAME, "+
			" 	A.DATA_TYPE TYPENAME, "+
			" 	A.DATA_LENGTH LENGTH,  "+
			" 	A.DATA_PRECISION PRECISION, "+
			" 	A.DATA_SCALE SCALE, "+
			" 	A.DATA_DEFAULT, "+
			" 	A.NULLABLE,  "+
			" 	DECODE(B.COMMENTS,NULL,A.COLUMN_NAME,B.COMMENTS) DESCRIPTION, "+
			" 	( "+
			"   	  SELECT "+ 
			"   	    COUNT(*) "+ 
			"   	  FROM  "+
			"   	    USER_CONSTRAINTS CONS, "+
			"    	   USER_CONS_COLUMNS CONS_C  "+
			"    	 WHERE  "+
			"    	   CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME "+ 
			"    	   AND CONS.CONSTRAINT_TYPE='P' "+
			"    	   AND CONS.TABLE_NAME=B.TABLE_NAME "+
			"     	  AND CONS_C.COLUMN_NAME=A.COLUMN_NAME "+
			"  	 ) AS IS_PK "+
			" FROM  "+
			" 	USER_TAB_COLUMNS A, "+
		    " 	USER_COL_COMMENTS B  "+
		    " WHERE  "+
		    " 	A.COLUMN_NAME=B.COLUMN_NAME "+ 
		    " 	AND A.TABLE_NAME = B.TABLE_NAME ";

	/**
	 * 取得数据库所有表
	 */
	private String sqlAllTables = "select TABLE_NAME,DECODE(COMMENTS,null,TABLE_NAME,comments) comments from user_tab_comments where table_type='TABLE'  ";

	/**
	 * 根据表名查询列表，如果表名为空则去系统所有的数据库表。
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql=sqlAllTables;
		if(StringUtil.isNotEmpty(tableName))
			sql = sqlAllTables +" and  lower(table_name) like '%" + tableName.toLowerCase() +"%'";
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map parameter=new HashMap();
		List list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, new RowMapper<Map<String,String>>() {
			public Map<String,String> mapRow(ResultSet rs, int row)
					throws SQLException {
				String tableName=rs.getString("table_name");
				String comments=rs.getString("comments");
				Map<String,String> map=new HashMap<String, String>();
				map.put("name", tableName);
				map.put("comments", comments);
				return map;
			}
		});
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(int i=0;i<list.size();i++){
			Map<String,String> tmp=(Map<String,String>)list.get(i);
			String name=tmp.get("name");
			String comments=tmp.get("comments");
			map.put(name, comments);
		}
	
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, String> getTablesByName(List<String> names) {
		StringBuffer sb=new StringBuffer();
		for(String name:names){
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length()-1);
		String sql=sqlAllTables+ " and  lower(table_name) in (" + sb.toString().toLowerCase() +")";
		
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map parameter=new HashMap();
		List list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, new RowMapper<Map<String,String>>() {
			public Map<String,String> mapRow(ResultSet rs, int row)
					throws SQLException {
				String tableName=rs.getString("TABLE_NAME");
				String comments=rs.getString("COMMENTS");
				Map<String,String> map=new HashMap<String, String>();
				map.put("NAME", tableName);
				map.put("COMMENTS", comments);
				return map;
			}
		});
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(int i=0;i<list.size();i++){
			Map<String,String> tmp=(Map<String,String>)list.get(i);
			String name=tmp.get("NAME");
			String comments=tmp.get("COMMENTS");
			map.put(name, comments);
		}
	
		return map;
	}

	/**
	 * 获取表对象
	 */
	@Override
	public TableModel getTableByName(String tableName) {
		tableName=tableName.toUpperCase();
		TableModel model=getTableModel(tableName);
		//获取列对象
		List<ColumnModel> columnList= getColumnsByTableName(tableName);
		model.setColumnList(columnList);
		return model;
	}

	@Override
	public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
			throws Exception {
		String sql=sqlAllTables;
		
		if(StringUtil.isNotEmpty(tableName)){
			sql += " AND  LOWER(table_name) LIKE '%" + tableName.toLowerCase() +"%'";
		}
		RowMapper<TableModel> rowMapper=new RowMapper<TableModel>() {
			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel=new TableModel();
				tableModel.setName(rs.getString("TABLE_NAME"));
				tableModel.setComment(rs.getString("COMMENTS"));
				return tableModel;
			}
		};
		List<TableModel> tableModels=getForList(sql, pageBean, rowMapper, SqlTypeConst.ORACLE);
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
	 * 根据表名获取主键列名
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getPkColumn(String tableName){
		tableName=tableName.toUpperCase();
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=String.format(sqlPk, tableName);
		Object rtn=jdbcTemplate.queryForObject(sql, null, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int row)
					throws SQLException {
				 return rs.getString("COLUMN_NAME");
			}
		});
		if(rtn==null)
			return "";
		 
		return rtn.toString();
	}
	
	
	/**
	 * 根据表名获取主键列名列表
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private List<String> getPkColumns(String tableName){
		tableName=tableName.toUpperCase();
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=String.format(sqlPk, tableName);
		List<String> rtn=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String, Object>(), new RowMapper<String>(){
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("column_name");
			}
		});
		return rtn;
	}
	
	/**
	 * 根据表名获取tableModel。
	 * @param tableName
	 * @return
	 */
	private TableModel getTableModel(final String tableName){
	
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=String.format(sqlTableComment, tableName);
		TableModel tableModel= (TableModel) jdbcTemplate.queryForObject(sql, null, new RowMapper<TableModel>() {

			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel=new TableModel();
				tableModel.setName(tableName);
				tableModel.setComment(rs.getString("comments"));
				return tableModel;
			}
		});
		if(BeanUtils.isEmpty(tableModel))
			tableModel=new TableModel();
		
		return tableModel;
	}
	
	/**
	 * 根据表名获取列
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ColumnModel> getColumnsByTableName(String tableName){
		String sql=String.format(SQL_GET_COLUMNS, tableName);
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map<String,Object> map=new HashMap<String,Object>();
		List<ColumnModel> columnList= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map, new OracleColumnMap());
		return columnList;
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
		//jdbcHelper.setCurrentDb(currentDb);
		
//		jdbcHelper=JdbcHelper.getInstance();
		
		Long b=System.currentTimeMillis();
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

	@Override
	public String getAllTableSql() {
		return sqlAllTables;
	}
	
}
