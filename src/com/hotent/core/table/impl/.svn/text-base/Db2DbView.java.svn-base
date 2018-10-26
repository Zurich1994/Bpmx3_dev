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
import com.hotent.core.table.colmap.DB2ColumnMap;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;

/**
 * DB2 视图操作类。
 * @author ray
 *
 */
@Component
public class Db2DbView extends BaseDbView implements IDbView {
	@Resource
	private JdbcTemplate jdbcTemplate;
	private static final String SQL_GET_ALL_VIEW =
			"SELECT " +
				"VIEWNAME " +
			"FROM " +
				"SYSCAT.VIEWS "+
			"WHERE  "+
				"VIEWSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";
	private static final String SQL_GET_COLUMNS = 
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

	private final String SQL_GET_COLUMNS_BATCH =
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
	/**
	 * 获取系统视图列表。
	 */
	@Override
	public List<String> getViews(String viewName) throws SQLException {
		String sql=SQL_GET_ALL_VIEW;
		if(StringUtil.isNotEmpty(viewName)){
			sql+=" AND UPPER(VIEWNAME) like '"+viewName.toUpperCase()+"%'";
		}
		return this.jdbcTemplate.queryForList(sql,String.class);
	}
	
	/**
	 *转换数据类型。
	 */
	public String getType(String type)
	{
		String dbtype=type.toLowerCase();
		if (dbtype.endsWith("bigint")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.endsWith("blob")) {
			return ColumnModel.COLUMNTYPE_CLOB;
		} else if (dbtype.endsWith("character")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;
		} else if (dbtype.endsWith("clob")) {
			return ColumnModel.COLUMNTYPE_CLOB;
		} else if (dbtype.endsWith("date")) {
			return ColumnModel.COLUMNTYPE_DATE;
		} else if (dbtype.endsWith("dbclob")) {
			return ColumnModel.COLUMNTYPE_CLOB;
		} else if (dbtype.endsWith("decimal")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.endsWith("double")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.endsWith("graphic")) {
			return ColumnModel.COLUMNTYPE_CLOB;
		} else if (dbtype.endsWith("integer")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.endsWith("long varchar")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;
		} else if (dbtype.endsWith("long vargraphic")) {
			return ColumnModel.COLUMNTYPE_CLOB;
		} else if (dbtype.endsWith("real")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.endsWith("smallint")) {
			return ColumnModel.COLUMNTYPE_NUMBER;
		} else if (dbtype.endsWith("time")) {
			return ColumnModel.COLUMNTYPE_DATE;
		} else if (dbtype.endsWith("timestamp")) {
			return ColumnModel.COLUMNTYPE_DATE;
		} else if (dbtype.endsWith("varchar")) {
			return ColumnModel.COLUMNTYPE_VARCHAR;
		} else if (dbtype.endsWith("vargraphic")) {
			return ColumnModel.COLUMNTYPE_CLOB;
		} else if (dbtype.endsWith("xml")) {
			return ColumnModel.COLUMNTYPE_CLOB;
		}else{
			return ColumnModel.COLUMNTYPE_VARCHAR;
		}
	}

	@Override
	public List<String> getViews(String viewName, PageBean pageBean)
			throws Exception {
		String sql=SQL_GET_ALL_VIEW;
		if(StringUtil.isNotEmpty(viewName)){
			sql+=" AND UPPER(VIEWNAME) LIKE '%"+viewName.toUpperCase()+"%'";
		}
		RowMapper<String> rowMapper=new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("VIEWNAME");
			}
		};
		return getForList(sql, pageBean,rowMapper,SqlTypeConst.DB2);
	}
	
	@Override
	public TableModel getModelByViewName(String viewName) throws SQLException {
		String sql=SQL_GET_ALL_VIEW;
		sql+=" AND UPPER(VIEWNAME) = '"+viewName.toUpperCase()+"'";
//		TableModel tableModel= (TableModel) jdbcTemplate.queryForObject(sql, null, tableModelRowMapper);
		TableModel tableModel=null;
		List<TableModel> tableModels =  jdbcTemplate.query(sql, tableModelRowMapper);
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
		String sql=SQL_GET_ALL_VIEW;
		if(StringUtil.isNotEmpty(viewName)){
			sql+=" AND UPPER(VIEWNAME) LIKE '%"+viewName.toUpperCase()+"%'";
		}

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

	/**
	 * 根据表名获取列
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private List<ColumnModel> getColumnsByTableName(String tableName) {
		String sql=String.format(SQL_GET_COLUMNS, tableName);
		Map<String,Object> map=new HashMap<String,Object>();
		
		List<ColumnModel> list= JdbcTemplateUtil.getNamedParameterJdbcTemplate(jdbcTemplate).query(sql, map, new DB2ColumnMap());
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
			sql+=" AND UPPER(TABNAME) IN ("+buf.toString().toUpperCase()+") ";
		}
//		jdbcHelper.setCurrentDb(currentDb);
//		jdbcHelper=JdbcHelper.getInstance();
//		int v = jdbcHelper.queryForInt("SELECT COUNT(*) FROM SYS_USER", paraTypeMap);
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
	
	RowMapper<TableModel> tableModelRowMapper = new RowMapper<TableModel>() {
		@Override
		public TableModel mapRow(ResultSet rs, int row) throws SQLException {
			TableModel tableModel=new TableModel();
			String tabName=rs.getString("VIEWNAME");
			tableModel.setName(tabName);
			tableModel.setComment(tabName);
			return tableModel;
		}
	};


	@Override
	public void createOrRep(String viewName, String sql) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
