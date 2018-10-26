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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.page.PageBean;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.colmap.DB2ColumnMap;
import com.hotent.core.util.StringUtil;

/**
 * mysql 数据库表源数据读取的实现。
 * @author ray
 *
 */
@Component 
public class Db2TableMeta extends BaseTableMeta{
	@Resource
	private JdbcTemplate jdbcTemplate;
	private final String  SQL_GET_COLUMNS=""+
		"SELECT "+ 
			"TABNAME TAB_NAME, "+
			"COLNAME COL_NAME, "+
			"TYPENAME COL_TYPE, "+
			"REMARKS COL_COMMENT, "+
			"NULLS IS_NULLABLE, "+
			"LENGTH LENGTH, "+
			"SCALE SCALE, "+
			"KEYSEQ  "+
		"FROM  "+
			"SYSCAT.COLUMNS "+ 
		"WHERE  "+
			"TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "+
			"AND UPPER(TABNAME) = UPPER('%s') ";
	
	private final String  SQL_GET_COLUMNS_BATCH=
		"SELECT "+ 
			"TABNAME TAB_NAME, "+
			"COLNAME COL_NAME, "+
			"TYPENAME COL_TYPE, "+
			"REMARKS COL_COMMENT, "+
			"NULLS IS_NULLABLE, "+
			"LENGTH LENGTH, "+
			"SCALE SCALE, "+
			"KEYSEQ  "+
		"FROM  "+
			"SYSCAT.COLUMNS "+ 
		"WHERE  "+
			"TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
	
	private final String SQL_GET_TABLE_COMMENT =""+
			"SELECT " +
				"TABNAME TAB_NAME, " +
				"REMARKS TAB_COMMENT " +
			"FROM " +
				"SYSCAT.TABLES " +
			"WHERE "+
				"TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "+
				"AND UPPER(TABNAME) =UPPER('%s')";
	
	private final String SQL_GET_ALL_TABLE_COMMENT="" +
			"SELECT " +
				"TABNAME TAB_NAME, " +
				"REMARKS TAB_COMMENT "+
			"FROM " +
				"SYSCAT.TABLES " +
			"WHERE " +
				"TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "+
				"AND UPPER(TABSCHEMA) = (SELECT UPPER(CURRENT SCHEMA) FROM SYSIBM.DUAL)";


	/**
	 * 根据表名，取得表模型。此处对表名进行非模糊匹配。
	 * @param tableName 表名
	 * @return 表模型
	 * 
	 * @see com.hotent.core.customertable.BaseTableMeta#getTableByName(java.lang.String)
	 */
	@Override
	public TableModel getTableByName(String tableName) {
		TableModel model=getTableModel(tableName);
		if(model==null){
			return null;
		}
		//获取列对象
		List<ColumnModel> columnList= getColumnsByTableName(tableName);
		model.setColumnList(columnList);
		return model;
	}

	/**
	 * 根据表名，取得列模型列表。此处对表名进行非模糊匹配。
	 * @param tableName 表名
	 * @return 列模型列表
	 */
	@SuppressWarnings("unchecked")
	private List<ColumnModel> getColumnsByTableName(String tableName){
		String sql=String.format(SQL_GET_COLUMNS, tableName);
//		jdbcHelper.setCurrentDb(currentDb);
		Map<String,Object> map=new HashMap<String,Object>();
		List<ColumnModel> list= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map, new DB2ColumnMap());
		return list;
	}
	/**
	 * 根据表名，取得列模型列表。此处对表名进行非模糊匹配。
	 * 此方法使用批量查询方式。
	 * @param tableNames 表名列表。
	 * @return Map，key=<table_name>，value=<List<columnModel>>
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
			sql+=" AND UPPER(TABNAME) IN ("+buf.toString().toUpperCase()+") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		List<ColumnModel> columnModels= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String,Object>(), new DB2ColumnMap());
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
	
	/**
	 * 根据表名获取tableModel。使用精确匹配表名。
	 * @param tableName
	 * @return
	 */
	private TableModel getTableModel(final String tableName){
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=String.format(SQL_GET_TABLE_COMMENT, tableName);
		TableModel tableModel= (TableModel) jdbcTemplate.queryForObject(sql, null, tableModelRowMapper);
		return tableModel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql=SQL_GET_ALL_TABLE_COMMENT;
		if(StringUtil.isNotEmpty(tableName))
			sql +=" AND UPPER(TABNAME) LIKE UPPER('%" +tableName +"%')";
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map<String,Object> parameter=new HashMap<String,Object>();
		List<Map<String,String>> list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter,tableMapRowMapper);
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(int i=0;i<list.size();i++){
			Map<String,String> tmp=(Map<String,String>)list.get(i);
			String name=tmp.get("name");
			String comments=tmp.get("comments");
			map.put(name, comments);
		}
		return map;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getTablesByName(List<String> tableNames) {
		Map<String, String> map = new HashMap<String, String>();
		String sql=SQL_GET_ALL_TABLE_COMMENT;
		if(tableNames ==null || tableNames.size()==0){
			return map;
		}else{
			StringBuffer buf=new StringBuffer();
			for(String str:tableNames){
				buf.append("'"+str+"',");
			}
			buf.deleteCharAt(buf.length()-1);
			sql+=" AND UPPER(TABNAME) IN ("+buf.toString().toUpperCase()+") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map<String,Object> parameter=new HashMap<String,Object>();
		List<Map<String,String>> list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter,tableMapRowMapper);
		for(int i=0;i<list.size();i++){
			Map<String,String> tmp=(Map<String,String>)list.get(i);
			String name=tmp.get("name");
			String comments=tmp.get("comments");
			map.put(name, comments);
		}
		return map;
	}

	@Override
	public List<TableModel> getTablesByName(String tableName, PageBean pageBean) throws Exception {
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=SQL_GET_ALL_TABLE_COMMENT;
		if(StringUtil.isNotEmpty(tableName))
			sql +=" AND UPPER(TABNAME) LIKE '%" +tableName.toUpperCase() +"%'";
		
		List<TableModel> tableModels=getForList(sql, pageBean, tableModelRowMapper, SqlTypeConst.DB2);
		
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
	
	RowMapper<TableModel> tableModelRowMapper = new RowMapper<TableModel>() {
		@Override
		public TableModel mapRow(ResultSet rs, int row) throws SQLException {
			TableModel tableModel=new TableModel();
			String tabName=rs.getString("TAB_NAME");
			String tabComment=rs.getString("TAB_COMMENT");
			tableModel.setName(tabName);
			tableModel.setComment(tabComment);
			return tableModel;
		}
	};
	
	RowMapper<Map<String,String>> tableMapRowMapper =  new RowMapper<Map<String,String>>() {
		@Override
		public Map<String,String> mapRow(ResultSet rs, int row)
				throws SQLException {
			String tableName=rs.getString("TAB_NAME");
			String comments=rs.getString("TAB_COMMENT");
			Map<String,String> map=new HashMap<String, String>();
			map.put("name", tableName);
			map.put("comments", comments);
			return map;
		}
	};


	@Override
	public String getAllTableSql() {
		return SQL_GET_ALL_TABLE_COMMENT;
	}
}
