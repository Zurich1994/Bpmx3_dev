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
import com.hotent.core.table.colmap.H2ColumnMap;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;

/**
 * h2 数据库表源数据读取的实现。
 * @author Raise
 *
 */
@Component 
public class H2TableMeta extends BaseTableMeta{
	@Resource
	private JdbcTemplate jdbcTemplate;

	private final String  SQL_GET_COLUMNS=
		"SELECT "+
			"A.TABLE_NAME, "+
			"A.COLUMN_NAME, "+
			"A.IS_NULLABLE, "+
			"A.TYPE_NAME, "+
			"A.CHARACTER_OCTET_LENGTH LENGTH, "+
			"A.NUMERIC_PRECISION PRECISIONS, "+
			"A.NUMERIC_SCALE SCALE, "+
			"B.COLUMN_LIST, "+
			"A.REMARKS "+ 
		"FROM "+
			"INFORMATION_SCHEMA.COLUMNS A  "+
			"JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME "+
		"WHERE  "+
			"A.TABLE_SCHEMA=SCHEMA() "+ 
			"AND B.CONSTRAINT_TYPE='PRIMARY KEY' "+
			"AND UPPER(A.TABLE_NAME)=UPPER('%s') ";
	
	private final String  SQL_GET_COLUMNS_BATCH=
			"SELECT "+
					"A.TABLE_NAME, "+
					"A.COLUMN_NAME, "+
					"A.IS_NULLABLE, "+
					"A.TYPE_NAME, "+
					"A.CHARACTER_OCTET_LENGTH LENGTH, "+
					"A.NUMERIC_PRECISION PRECISIONS, "+
					"A.NUMERIC_SCALE SCALE, "+
					"B.COLUMN_LIST, "+
					"A.REMARKS "+ 
				"FROM "+
					"INFORMATION_SCHEMA.COLUMNS A  "+
					"JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME "+
				"WHERE  "+
					"A.TABLE_SCHEMA=SCHEMA() "+
					"AND B.CONSTRAINT_TYPE='PRIMARY KEY' ";
	
//	private final String sqlComment ="select table_name,table_comment  from information_schema.tables t where t.table_schema=DATABASE() and table_name='%s' ";
	
	private final String SQL_GET_ALL_TABLE=
		"SELECT "+
			"TABLE_NAME, "+
			"REMARKS "+
			"FROM "+
			"INFORMATION_SCHEMA.TABLES T "+
		"WHERE "+
			"T.TABLE_TYPE='TABLE' "+
			"AND T.TABLE_SCHEMA=SCHEMA() ";

	
	
	/**
	 * 获取表对象
	 */
	@Override
	public TableModel getTableByName(String tableName) {
		TableModel model=getTableModel(tableName);
		//获取列对象
		List<ColumnModel> columnList= getColumnsByTableName(tableName);
		model.setColumnList(columnList);
		return model;
	}
	
	
	/**
	 * 根据表名获取列
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ColumnModel> getColumnsByTableName(String tableName){
		String sql=String.format(SQL_GET_COLUMNS, tableName.toUpperCase());
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map<String,Object> map=new HashMap<String,Object>();
		//sqlColumns语句的column_key包含了column是否为primary key，并在H2ColumnMap中进行了映射。
		List<ColumnModel> list= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map, new H2ColumnMap());
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
			sql+=" AND A.TABLE_NAME IN ("+buf.toString().toUpperCase()+") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		List<ColumnModel> columnModels= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String,Object>(), new H2ColumnMap());
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
	 * 根据表名获取tableModel。
	 * @param tableName
	 * @return
	 */
	private TableModel getTableModel(final String tableName){
	
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql = SQL_GET_ALL_TABLE+" AND UPPER(TABLE_NAME) = '"+tableName.toUpperCase()+"'";
		TableModel tableModel= (TableModel) jdbcTemplate.queryForObject(sql, null, tableRowMapper);
		if(BeanUtils.isEmpty(tableModel))
			tableModel=new TableModel();
		return tableModel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql=SQL_GET_ALL_TABLE;
		if(StringUtil.isNotEmpty(tableName))
			sql +=" AND UPPER(TABLE_NAME) LIKE '%" +tableName.toUpperCase() +"%'";
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map<String,Object> parameter=new HashMap<String,Object>();
		List<Map<String, Object>> list=jdbcTemplate.queryForList(sql, parameter, tableMapRowMapper);
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(int i=0;i<list.size();i++){
			Map<String,Object> tmp=list.get(i);
			String name=tmp.get("name").toString();
			String comments=tmp.get("comment").toString();
			map.put(name, comments);
		}
		return map;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getTablesByName(List<String> names) {
		StringBuffer sb=new StringBuffer();
		for(String name:names){
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length()-1);
		String sql=SQL_GET_ALL_TABLE+ " AND  UPPER(TABLE_NAME) IN (" + sb.toString().toUpperCase() +")";
		
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map<String,Object> parameter=new HashMap<String,Object>();
		List<Map<String,Object>> list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, tableMapRowMapper);
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(int i=0;i<list.size();i++){
			Map<String,Object> tmp=list.get(i);
			String name=tmp.get("name").toString();
			String comments=tmp.get("comment").toString();
			map.put(name, comments);
		}
		return map;
	}

	@Override
	public List<TableModel> getTablesByName(String tableName, PageBean pageBean) throws Exception {
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=SQL_GET_ALL_TABLE;
		if(StringUtil.isNotEmpty(tableName))
			sql +=" AND UPPER(TABLE_NAME) LIKE '%" +tableName.toUpperCase() +"%'";
		
		RowMapper<TableModel> rowMapper=new RowMapper<TableModel>() {
			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel=new TableModel();
				tableModel.setName(rs.getString("TABLE_NAME"));
				String comments=rs.getString("REMARKS");
				tableModel.setComment(comments);
				return tableModel;
			}
		};
		List<TableModel> tableModels=getForList(sql, pageBean, rowMapper, SqlTypeConst.H2);
		
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
	
	
	RowMapper<TableModel> tableRowMapper=new RowMapper<TableModel>() {
		public TableModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			TableModel model = new TableModel();
			String tableName = rs.getString("TABLE_NAME");
			String tableComment = rs.getString("REMARKS");
			model.setName(tableName);
			model.setComment(tableComment);
			return model;
		}
	};
	
	
	RowMapper<Map<String,Object>> tableMapRowMapper=new RowMapper<Map<String,Object>>() {
		public Map<String,Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
			Map<String,Object> model = new HashMap<String, Object>();
			String tableName = rs.getString("TABLE_NAME");
			String tableComment = rs.getString("REMARKS");
			model.put("name",tableName);
			model.put("comment",tableComment);
			return model;
		}
	};



	@Override
	public String getAllTableSql() {
		return SQL_GET_ALL_TABLE;
	}
	

}
