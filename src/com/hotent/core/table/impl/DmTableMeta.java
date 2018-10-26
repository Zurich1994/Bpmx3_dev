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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.hotent.core.table.colmap.DmColumnMap;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;

/**
 * ORACLE数据库表元数据的读取。
 * @author ray
 *
 */
@Component 
public class DmTableMeta extends BaseTableMeta {
	@Resource
	private JdbcTemplate jdbcTemplate;

	protected Logger logger = LoggerFactory.getLogger(DmTableMeta.class);
	/**
	 * 取得主键
	 */
	private String sqlPk = "SELECT  CONS_C.COLUMN_NAME FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME='%s'";

	/**
	 * 取得注释
	 */
	private String sqlTableComment = "SELECT TABLE_NAME,COMMENTS FROM (SELECT A.TABLE_NAME AS TABLE_NAME,DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS COMMENTS FROM \"SYS\".\"USER_TABLES\" A LEFT JOIN \"SYS\".\"SYSTABLECOMMENTS\" B ON  A.TABLE_NAME=B.TVNAME) WHERE  TABLE_NAME ='%s'";

	/**
	 * 取得列表
	 */
	private final String SQL_GET_COLUMNS = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH, "+
			    " T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION, "+
			    " (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE "+
			    " CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1 "+
			    " AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK"+
			    " FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH,"+
			    " A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE,"+
			    " DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION " +
			    " FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T  WHERE TABLE_NAME='%S' "+
			    " ORDER BY COLUMN_ID ";
	/**
	 * 取得列表
	 */
	private final String SQL_GET_COLUMNS_BATCH ="SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH, "+
		    " T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION, "+
		    " (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE "+
		    " CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1 "+
		    " AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK"+
		    " FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH,"+
		    " A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE,"+
		    " DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION " +
		    " FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T WHERE 1=1 ";

	/**
	 * 取得数据库所有表
	 */
	private String sqlAllTables = "SELECT TABLE_NAME,COMMENTS FROM (SELECT A.TABLE_NAME AS TABLE_NAME,DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS COMMENTS FROM \"SYS\".\"USER_TABLES\" A LEFT JOIN \"SYS\".\"SYSTABLECOMMENTS\" B ON  A.TABLE_NAME=B.TVNAME) WHERE 1=1";
	

	/**
	 * 根据表名查询列表，如果表名为空则去系统所有的数据库表。
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, String> getTablesByName(String tableName) {
		String sql=sqlAllTables;
		if(StringUtil.isNotEmpty(tableName))
			sql = sqlAllTables +" and  lower(TABLE_NAME) like '%" + tableName.toLowerCase() +"%'";
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		Map parameter=new HashMap();
		List list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, new RowMapper<Map<String,String>>() {
			@Override
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
		String sql=sqlAllTables+ " and  lower(TABLE_NAME) in (" + sb.toString().toLowerCase() +")";
		
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		
		Map parameter=new HashMap();
		List list=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, parameter, new RowMapper<Map<String,String>>() {
			@Override
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
			sql += " AND  LOWER(TABLE_NAME) LIKE '%" + tableName.toLowerCase() +"%'";
		}
		RowMapper<TableModel> rowMapper=new RowMapper<TableModel>() {
			@Override
			public TableModel mapRow(ResultSet rs, int row)
					throws SQLException {
				TableModel tableModel=new TableModel();
				tableModel.setName(rs.getString("TABLE_NAME"));
				tableModel.setComment(rs.getString("COMMENTS"));
				return tableModel;
			}
		};
		List<TableModel> tableModels=getForList(sql, pageBean, rowMapper, SqlTypeConst.DM);
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
//		jdbcHelper.setCurrentDb(currentDb);
		
//		jdbcHelper=JdbcHelper.getInstance();
		
		String sql=String.format(sqlPk, tableName);
		Object rtn=jdbcTemplate.queryForObject(sql, null, new RowMapper<String>() {
			@Override
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
//		jdbcHelper.setCurrentDb(currentDb);
		
//		jdbcHelper=JdbcHelper.getInstance();
		
		String sql=String.format(sqlPk, tableName);
		List<String> rtn=JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String, Object>(), new RowMapper<String>(){
			@Override
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
	
//		jdbcHelper.setCurrentDb(currentDb);
		
//		jdbcHelper=JdbcHelper.getInstance();
		
		String sql=String.format(sqlTableComment, tableName);
		TableModel tableModel= (TableModel) jdbcTemplate.queryForObject(sql, null, new RowMapper<TableModel>() {

			@Override
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
//		jdbcHelper.setCurrentDb(currentDb);
		
//		jdbcHelper=JdbcHelper.getInstance();
		
		Map<String,Object> map=new HashMap<String,Object>();
		List<ColumnModel> columnList= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map, new DmColumnMap());
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
			sql+=" AND T.TABLE_NAME IN ("+buf.toString()+") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
		
		Long b=System.currentTimeMillis();
		List<ColumnModel> columnModels= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, new HashMap<String,Object>(), new DmColumnMap());
		logger.info(String.valueOf(System.currentTimeMillis()-b));
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
