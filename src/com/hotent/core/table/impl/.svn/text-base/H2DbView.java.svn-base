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
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.page.PageBean;
import com.hotent.core.table.BaseDbView;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.colmap.H2ColumnMap;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
/**
 * oracle 视图操作类。
 * 
 * @author ray
 * 
 */
@Component 
public class H2DbView extends BaseDbView implements IDbView {
	@Resource
	private JdbcTemplate jdbcTemplate;
	// private static final String
	// sqlAllView="select view_name from user_views ";
	private static final String SQL_GET_ALL_VIEW = 
			"SELECT " + 
				"TABLE_NAME ," +
				"REMARKS  "+
			"FROM " + 
				"INFORMATION_SCHEMA.TABLES " + 
			"WHERE " + 
				"TABLE_TYPE = 'VIEW' "
			+ "AND TABLE_SCHEMA=SCHEMA() ";
	private static final String SQL_GET_COLUMNS = 
			"SELECT " + 
				"A.TABLE_NAME, " + 
				"A.COLUMN_NAME, " + 
				"A.IS_NULLABLE, " + 
				"A.DATA_TYPE, "
				+ "A.CHARACTER_OCTET_LENGTH LENGTH, " + 
				"A.NUMERIC_PRECISION PRECISIONS, " + 
				"A.NUMERIC_SCALE SCALE, " + 
				"B.COLUMN_LIST, " + 
				"A.REMARKS " + 
			"FROM "+ 
				"INFORMATION_SCHEMA.COLUMNS A  " + 
				"JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME " + 
			"WHERE  " + 
				"A.TABLE_SCHEMA=SCHEMA() "+
				"AND UPPER(A.TABLE_NAME)='%s' ";
	static final String SQL_GET_COLUMNS_BATCH = 
			"SELECT " + 
					"A.TABLE_NAME, " + 
					"A.COLUMN_NAME, " + 
					"A.IS_NULLABLE, " + 
					"A.DATA_TYPE, "+
					"A.CHARACTER_OCTET_LENGTH LENGTH, " + 
					"A.NUMERIC_PRECISION PRECISIONS, " + 
					"A.NUMERIC_SCALE SCALE, " + 
					"B.COLUMN_LIST, " + 
					"A.REMARKS " + 
				"FROM "+
					"INFORMATION_SCHEMA.COLUMNS A  " + 
					"JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME " + 
				"WHERE  " + 
					"A.TABLE_SCHEMA=SCHEMA() ";
	/**
	 * 获取系统视图列表。
	 */
	@Override
	public List<String> getViews(String viewName) throws SQLException {
		String sql = SQL_GET_ALL_VIEW;
		if (StringUtil.isNotEmpty(viewName)) {
			sql += " AND TABLE_NAME LIKE '%" + viewName + "%'";
		}
		
		RowMapper<String> rowMapper = new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String name = rs.getString("TABLE_NAME");
				return name;
			}
		};
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	/**
	 * 获取系统视图列表。
	 * 
	 * @throws Exception
	 */
	@Override
	public List<String> getViews(String viewName, PageBean pageBean) throws Exception {
		String sql = SQL_GET_ALL_VIEW;
		if (StringUtil.isNotEmpty(viewName)) {
			sql += " AND TABLE_NAME LIKE '%" + viewName + "%'";
		}
		RowMapper<String> rowMapper = new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String name = rs.getString("TABLE_NAME");
				return name;
			}
		};
		return getForList(sql, pageBean, rowMapper, SqlTypeConst.H2);
	}
	public String getType(String dbtype) {
		dbtype = dbtype.toUpperCase();
		if (dbtype.equals("BIGINT")) { // int
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("INT8")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("INT")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("INTEGER")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("MEDIUMINT")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("INT4")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("SIGNED")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("TINYINT")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("SMALLINT")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("INT2")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("YEAR")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("IDENTITY")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("DECIMAL")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.equals("BOOLEAN")) { // boolean
			return ColumnModel.COLUMNTYPE_VARCHAR;
		} else if (dbtype.equals("BIT")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;
		} else if (dbtype.equals("BOOL")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;
		} else if (dbtype.equals("SIGNED")) {
			return ColumnModel.COLUMNTYPE_NUMBER;

		} else if (dbtype.equals("DOUBLE")) {
			return ColumnModel.COLUMNTYPE_NUMBER;

		} else if (dbtype.equals("REAL")) {
			return ColumnModel.COLUMNTYPE_NUMBER;

		} else if (dbtype.equals("TIME")) {
			return ColumnModel.COLUMNTYPE_DATE;

		} else if (dbtype.equals("TIMESTAMP")) {
			return ColumnModel.COLUMNTYPE_DATE;

		} else if (dbtype.equals("BINARY")) {
			return ColumnModel.COLUMNTYPE_CLOB;

		} else if (dbtype.equals("BLOB")) {
			return ColumnModel.COLUMNTYPE_CLOB;

		} else if (dbtype.equals("CLOB")) {
			return ColumnModel.COLUMNTYPE_CLOB;

		} else if (dbtype.equals("VARCHAR")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;

		} else if (dbtype.equals("CHAR")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;

		} else if (dbtype.equals("UUID")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;

		} else if (dbtype.equals("ARRAY")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;

		} else {
			return ColumnModel.COLUMNTYPE_VARCHAR;

		}
	}
	@Override
	public TableModel getModelByViewName(String viewName) throws SQLException {
		String sql=SQL_GET_ALL_VIEW;
		sql+=" AND UPPER(TABLE_NAME) = '"+viewName.toUpperCase()+"'";
//		TableModel tableModel= (TableModel) jdbcTemplate.queryForObject(sql, null, tableModelRowMapper);
		TableModel tableModel=null;
		List<TableModel> tableModels =  jdbcTemplate.query(sql, tableRowMapper);
		if(BeanUtils.isEmpty(tableModels)){
			return null;
		}else{
			tableModel=tableModels.get(0);
		}
		//获取列对象
		List<ColumnModel> columnList= getColumnsByTableName(viewName);
		tableModel.setColumnList(columnList);
		return tableModel;
	}
	@Override
	public List<TableModel> getViewsByName(String viewName, PageBean pageBean) throws Exception {
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql = SQL_GET_ALL_VIEW;
		if (StringUtil.isNotEmpty(viewName)) {
			sql += " AND TABLE_NAME LIKE '%" + viewName + "%'";
		}
		RowMapper<TableModel> rowMapper = new RowMapper<TableModel>() {
			@Override
			public TableModel mapRow(ResultSet rs, int row) throws SQLException {
				TableModel tableModel = new TableModel();
				tableModel.setName(rs.getString("table_name"));
				tableModel.setComment(tableModel.getName());
				return tableModel;
			}
		};
		List<TableModel> tableModels = getForList(sql, pageBean, rowMapper, SqlTypeConst.H2);
		List<String> tableNames = new ArrayList<String>();
		// get all table names
		for (TableModel model : tableModels) {
			tableNames.add(model.getName());
		}
		// batch get table columns
		Map<String, List<ColumnModel>> tableColumnsMap = getColumnsByTableName(tableNames);
		// extract table columns from paraTypeMap by table name;
		for (Entry<String, List<ColumnModel>> entry : tableColumnsMap.entrySet()) {
			// set TableModel's columns
			for (TableModel model : tableModels) {
				if (model.getName().equalsIgnoreCase(entry.getKey())) {
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
	@SuppressWarnings("unchecked")
	private List<ColumnModel> getColumnsByTableName(String tableName) {
		String sql = String.format(SQL_GET_COLUMNS, tableName);
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map<String, Object> map = new HashMap<String, Object>();
		// sqlColumns语句的column_key包含了column是否为primary
		List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map, new H2ColumnMap());
		for (ColumnModel model : list) {
			model.setTableName(tableName);
		}
		return list;
	}
	/**
	 * 根据表名获取列。此方法使用批量查询方式。
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, List<ColumnModel>> getColumnsByTableName(List<String> tableNames) {
		String sql = SQL_GET_COLUMNS_BATCH;
		Map<String, List<ColumnModel>> map = new HashMap<String, List<ColumnModel>>();
		if (tableNames != null && tableNames.size() == 0) {
			return map;
		} else {
			StringBuffer buf = new StringBuffer();
			for (String str : tableNames) {
				buf.append("'" + str + "',");
			}
			buf.deleteCharAt(buf.length() - 1);
			sql += " AND A.TABLE_NAME IN (" + buf.toString() + ") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		List<ColumnModel> columnModels = JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String, Object>(), new H2ColumnMap());
		for (ColumnModel columnModel : columnModels) {
			String tableName = columnModel.getTableName();
			if (map.containsKey(tableName)) {
				map.get(tableName).add(columnModel);
			} else {
				List<ColumnModel> cols = new ArrayList<ColumnModel>();
				cols.add(columnModel);
				map.put(tableName, cols);
			}
		}
		return map;
	}
	
	RowMapper<TableModel> tableRowMapper = new RowMapper<TableModel>() {
		@Override
		public TableModel mapRow(ResultSet rs, int row) throws SQLException {
			TableModel tableModel=new TableModel();
			String tabName=rs.getString("TABLE_NAME");
			String tabComment=rs.getString("REMARKS");
			tableModel.setName(tabName);
			tableModel.setComment(tabComment);
			return tableModel;
		}
	};
	
	@Override
	public void createOrRep(String viewName, String sql) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
