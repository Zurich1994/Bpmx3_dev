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
import com.hotent.core.table.colmap.MySqlColumnMap;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;

/**
 * mysql 数据库表源数据读取的实现。
 * @author ray
 *
 */
@Component 
public class MySqlTableMeta extends BaseTableMeta{
	@Resource
	private JdbcTemplate jdbcTemplate;

	private final String  SQL_GET_COLUMNS="SELECT" +
			" TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH," +
			" NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT " +
			" FROM " +
			" INFORMATION_SCHEMA.COLUMNS " +
			" WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME='%s' ";
	
	private final String  SQL_GET_COLUMNS_BATCH="SELECT" +
			" TABLE_NAME,COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_OCTET_LENGTH LENGTH," +
			" NUMERIC_PRECISION PRECISIONS,NUMERIC_SCALE SCALE,COLUMN_KEY,COLUMN_COMMENT " +
			" FROM " +
			" INFORMATION_SCHEMA.COLUMNS " +
			" WHERE TABLE_SCHEMA=DATABASE() ";
	
	private final String sqlComment ="select table_name,table_comment  from information_schema.tables t where t.table_schema=DATABASE() and table_name='%s' ";
	
	private final String sqlAllTable="select table_name,table_comment from information_schema.tables t where t.table_type='BASE TABLE' AND t.table_schema=DATABASE()";

	private final String sqlPk="SELECT k.column_name name "
			+"FROM information_schema.table_constraints t "
			+"JOIN information_schema.key_column_usage k "
			+"USING(constraint_name,table_schema,table_name) "
			+"WHERE t.constraint_type='PRIMARY KEY' "
  			+"AND t.table_schema=DATABASE() "
  			+"AND t.table_name='%s'";

	
	
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
	 * 根据表名获取主键列名
	 * @param tableName
	 * @return
	 */
	private String getPkColumn(String tableName){
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=String.format(sqlPk, tableName);
		Object rtn=jdbcTemplate.queryForObject(sql, null, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int row)
					throws SQLException {
				 return rs.getString("name");
			}
		});
		if(rtn==null)
			return "";
		 
		return rtn.toString();
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
		//sqlColumns语句的column_key包含了column是否为primary key，并在MySqlColumnMap中进行了映射。
		List<ColumnModel> list= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map, new MySqlColumnMap());
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
			sql+=" AND TABLE_NAME IN ("+buf.toString()+") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		List<ColumnModel> columnModels= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String,Object>(), new MySqlColumnMap());
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
		String sql=String.format(sqlComment, tableName);
		TableModel tableModel= (TableModel) jdbcTemplate.queryForObject(sql, null, new RowMapper<TableModel>() {

			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel=new TableModel();
				String comments=rs.getString("table_comment");
				comments=getComments(comments,tableName);
				tableModel.setName(tableName);
				tableModel.setComment(comments);
				return tableModel;
			}
		});
		if(BeanUtils.isEmpty(tableModel))
			tableModel=new TableModel();
		
		return tableModel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql=sqlAllTable;
		if(StringUtil.isNotEmpty(tableName))
			sql +=" AND TABLE_NAME LIKE '%" +tableName +"%'";
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map parameter=new HashMap();
		List list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, new RowMapper<Map<String,String>>() {
			public Map<String,String> mapRow(ResultSet rs, int row)
					throws SQLException {
				String tableName=rs.getString("table_name");
				String comments=rs.getString("table_comment");
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
			comments=getComments(comments,name);
			map.put(name, comments);
		}

		return map;
		
	}
	
	/**
	 * 获取注释
	 * @param comments
	 * @param defaultValue
	 * @return
	 */
	public static String getComments(String comments,String defaultValue){
		if(StringUtil.isEmpty(comments)) return defaultValue;
		int idx=comments.indexOf("InnoDB free");
		if(idx>-1){
			comments=StringUtil.trimSufffix(comments.substring(0,idx).trim(),";");
		}
		if(StringUtil.isEmpty(comments)){
			comments=defaultValue;
		}
		return comments;
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
		String sql=sqlAllTable+ " and  lower(table_name) in (" + sb.toString().toLowerCase() +")";
		
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map parameter=new HashMap();
		List list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, new RowMapper<Map<String,String>>() {
			public Map<String,String> mapRow(ResultSet rs, int row)
					throws SQLException {
				String tableName=rs.getString("table_name");
				String comments=rs.getString("table_comment");
				Map<String,String> map=new HashMap<String, String>();
				map.put("tableName", tableName);
				map.put("tableComment", comments);
				return map;
			}
		});
		Map<String, String> map=new LinkedHashMap<String, String>();
		for(int i=0;i<list.size();i++){
			Map<String,String> tmp=(Map<String,String>)list.get(i);
			String name=tmp.get("tableName");
			String comments=tmp.get("tableComment");
			map.put(name, comments);
		}
	
		return map;
	}

	@Override
	public List<TableModel> getTablesByName(String tableName, PageBean pageBean) throws Exception {
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql=sqlAllTable;
		if(StringUtil.isNotEmpty(tableName))
			sql +=" AND TABLE_NAME LIKE '%" +tableName +"%'";
		
		RowMapper<TableModel> rowMapper=new RowMapper<TableModel>() {
			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel=new TableModel();
				tableModel.setName(rs.getString("TABLE_NAME"));
				String comments=rs.getString("TABLE_COMMENT");
				comments=getComments(comments,tableModel.getName());
				tableModel.setComment(comments);
				return tableModel;
			}
		};
		List<TableModel> tableModels=getForList(sql, pageBean, rowMapper, SqlTypeConst.MYSQL);
		
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

	@Override
	public String getAllTableSql() {
		return sqlAllTable;
	}
	
	

}
