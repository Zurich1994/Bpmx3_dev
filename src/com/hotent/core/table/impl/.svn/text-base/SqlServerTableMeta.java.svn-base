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

import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.page.PageBean;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.colmap.SqlServerColumnMap;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;

/**
 * MSSQL数据库表元数据的读取。
 * 
 * @author ray
 * 
 */
@Component 
public class SqlServerTableMeta extends BaseTableMeta {
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 取得主键
	 */
	private String sqlPk = "SELECT c.COLUMN_NAME COLUMN_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE c "
			+ "WHERE 	pk.TABLE_NAME LIKE '%s' "
			+ "and	CONSTRAINT_TYPE = 'PRIMARY KEY' "
			+ "and	c.TABLE_NAME = pk.TABLE_NAME "
			+ "and	c.CONSTRAINT_NAME = pk.CONSTRAINT_NAME ";
	
//	private String sqlTable="SELECT name f"

	/**
	 * 取得注释
	 */
    private String sqlTableComment = "select comment from (select a.name name, cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 union(select name,name comment from sys.tables where type='U' and object_id not in (select DISTINCT major_id from sys.extended_properties where minor_id=0))) t where 1=1 and t.name='%s'";
// private String sqlTableComment = "select cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 and a.name='%s'";
	/**
	 * 取得列表
	 */
	private String SQL_GET_COLUMNS = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE, " 
			+"  ( "
			+" 		SELECT COUNT(*) " 
			+" 		FROM  "
			+" 		SYS.IDENTITY_COLUMNS  "
			+" 		WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID"
			+"	) AS AUTOGEN, " 
			+" 	( "
			+" 		SELECT CAST(VALUE AS VARCHAR) "
			+" 		FROM SYS.EXTENDED_PROPERTIES  "
			+" 		WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID "
			+" 	) AS DESCRIPTION, "
			+" 	( "
			+" 		SELECT COUNT(*) "
			+" 		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu "
			+" 		WHERE 	pk.TABLE_NAME = B.NAME "
			+" 			 AND	CONSTRAINT_TYPE = 'PRIMARY KEY'  "
			+" 			 AND	KCU.TABLE_NAME = PK.TABLE_NAME  "
			+" 			 AND	KCU.CONSTRAINT_NAME = PK.CONSTRAINT_NAME "
			+" 			 AND 	KCU.COLUMN_NAME =A.NAME "
			+" 	) AS IS_PK "
			+" FROM SYS.COLUMNS A, SYS.TABLES B, SYS.TYPES C  "
			+" WHERE A.OBJECT_ID = B.OBJECT_ID AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID AND B.NAME='%s' " 
			+" 		AND C.NAME<>'SYSNAME' "
			+"		ORDER BY A.COLUMN_ID ";
	
	private String SQL_GET_COLUMNS_BATCH = "SELECT B.NAME TABLE_NAME,A.NAME NAME, C.NAME TYPENAME, A.MAX_LENGTH LENGTH, A.IS_NULLABLE IS_NULLABLE,A.PRECISION PRECISION,A.SCALE SCALE, " 
			+" ( "
			+" 	SELECT COUNT(*) " 
			+" 	FROM  "
			+" 	SYS.IDENTITY_COLUMNS  "
			+" WHERE SYS.IDENTITY_COLUMNS.OBJECT_ID = A.OBJECT_ID AND A.COLUMN_ID = SYS.IDENTITY_COLUMNS.COLUMN_ID) AS AUTOGEN, " 
			+" 	( "
			+" 			SELECT CAST(VALUE AS VARCHAR) "
			+" 			FROM SYS.EXTENDED_PROPERTIES  "
			+" 		WHERE SYS.EXTENDED_PROPERTIES.MAJOR_ID = A.OBJECT_ID AND SYS.EXTENDED_PROPERTIES.MINOR_ID = A.COLUMN_ID "
			+" 	) AS DESCRIPTION, "
			+" 	( "
			+" 		SELECT COUNT(*) "
			+" 		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS pk ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE kcu "
			+" 		WHERE 	pk.TABLE_NAME = B.NAME "
			+" 			 AND	CONSTRAINT_TYPE = 'PRIMARY KEY'  "
			+" 			 AND	KCU.TABLE_NAME = PK.TABLE_NAME  "
			+" 			 AND	KCU.CONSTRAINT_NAME = PK.CONSTRAINT_NAME "
			+" 			 AND 	KCU.COLUMN_NAME =A.NAME "
			+" 	) AS IS_PK "
			+" FROM SYS.COLUMNS A, SYS.TABLES B, SYS.TYPES C  "
			+" WHERE A.OBJECT_ID = B.OBJECT_ID AND A.SYSTEM_TYPE_ID=C.SYSTEM_TYPE_ID " 
			+" 		AND C.NAME<>'SYSNAME' ";

	/**
	 * 取得数据库所有表
	 */
	//SELECT name,name FROM SysObjects Where XType='U' ORDER BY Name
	//select a.name name, cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 union(select name,name comment from sys.tables where type='U' and object_id not in (select DISTINCT major_id from sys.extended_properties where minor_id=0))
    private String sqlAllTables = "select * from (select a.name name, cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 union(select name,name comment from sys.tables where type='U' and object_id not in (select DISTINCT major_id from sys.extended_properties where minor_id=0))) t where 1=1";

	/**
	 * 获取表对象
	 */
	@Override
	public TableModel getTableByName(String tableName) {
		TableModel model = getTableModel(tableName);
		// 获取列对象
		List<ColumnModel> columnList = getColumnsByTableName(tableName);
		model.setColumnList(columnList);
		return model;
	}

	/**
	 * 根据表名获取主键列名
	 * 
	 * @param tableName
	 * @return
	 */
	private String getPkColumn(String tableName) {
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql = String.format(sqlPk, tableName);
		Object rtn = jdbcTemplate.queryForObject(sql, null,
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int row)
							throws SQLException {
						return rs.getString("COLUMN_NAME");
					}
				});
		if (rtn == null)
			return "";

		return rtn.toString();
	}

	/**
	 * 根据表名获取tableModel。
	 * 
	 * @param tableName
	 * @return
	 */
	private TableModel getTableModel(final String tableName) {

//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		String sql = String.format(sqlTableComment, tableName);
		TableModel tableModel = (TableModel) jdbcTemplate.queryForObject(sql,
				null, new RowMapper<TableModel>() {

					public TableModel mapRow(ResultSet rs, int row)
							throws SQLException {
						TableModel tableModel = new TableModel();
						tableModel.setName(tableName);
						tableModel.setComment(rs.getString("comment"));
						return tableModel;
					}
				});
		if (BeanUtils.isEmpty(tableModel))
			tableModel = new TableModel();
		
		tableModel.setName(tableName);

		return tableModel;
	}

	/**
	 * 根据表名查询列表，如果表名为空则去系统所有的数据库表。
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql=sqlAllTables;
		if (StringUtil.isNotEmpty(tableName)){
			sql += " and  lower(t.name) like '%"
					+ tableName.toLowerCase() + "%'";
		}
		
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map parameter = new HashMap();
		List list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter,
				new RowMapper<Map<String, String>>() {
					public Map<String, String> mapRow(ResultSet rs, int row)
							throws SQLException {
						String tableName = rs.getString("name");
						String comments = rs.getString("comment");
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", tableName);
						map.put("comments", comments);
						return map;
					}
				});
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> tmp = (Map<String, String>) list.get(i);
			String name = tmp.get("name");
			String comments = tmp.get("comments");
			map.put(name, comments);
		}

		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, String> getTablesByName(List<String> names) {
		StringBuffer sb=new StringBuffer();
		for(String name:names){
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length()-1);
		String sql=sqlAllTables+ " and  t.name in (" + sb.toString().toLowerCase() +")";
		
		//jdbcHelper.setCurrentDb(currentDb);
		
//		jdbcHelper=JdbcHelper.getInstance();
		Map parameter=new HashMap();
		List list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, new RowMapper<Map<String,String>>() {
			public Map<String,String> mapRow(ResultSet rs, int row)
					throws SQLException {
				String tableName=rs.getString("name");
				String comments=rs.getString("comment");
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

	@Override
	public List<TableModel> getTablesByName(String tableName, PageBean pageBean)
			throws Exception {
		String sql=sqlAllTables;
		if (StringUtil.isNotEmpty(tableName))
			sql += " AND  LOWER(t.name) LIKE '%"
					+ tableName.toLowerCase() + "%'";
		RowMapper<TableModel> rowMapper=new RowMapper<TableModel>() {
			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel = new TableModel();
				tableModel.setName(rs.getString("NAME"));
				tableModel.setComment(rs.getString("COMMENT"));
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
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map map = new HashMap();
		List<ColumnModel> list = JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map,new SqlServerColumnMap());
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
			sql+=" AND B.NAME IN ("+buf.toString()+") ";
		}
		//jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		List<ColumnModel> columnModels= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String,Object>(), new SqlServerColumnMap());
		
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
