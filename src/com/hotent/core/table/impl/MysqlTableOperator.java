package com.hotent.core.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.hotent.core.mybatis.Dialect;
import com.hotent.core.page.PageBean;
import com.hotent.core.table.AbstractTableOperator;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormTableIndex;

/**
 * mysql数据库表操作的实现。
 * @author ray
 *
 */
public class MysqlTableOperator extends AbstractTableOperator
{

	
	
	
	private int BATCHSIZE = 100;
	

	
	
	@Override
	public void createTable(TableModel model) throws SQLException
	{

		List<ColumnModel> columnList = model.getColumnList();

		// 建表语句
		StringBuffer sb = new StringBuffer();
		// 主键字段
		String pkColumn = null;
		// 建表开始
		sb.append("CREATE TABLE " + model.getName() + " (\n");
		for (int i = 0; i < columnList.size(); i++)
		{
			// 建字段
			ColumnModel cm = columnList.get(i);
			sb.append(cm.getName()).append(" ");
			sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
			sb.append(" ");
			
			String defaultValue=cm.getDefaultValue();
			
			//添加默认值。
			if(StringUtil.isNotEmpty(defaultValue)){
				sb.append(" default " + defaultValue);
			}
			
//			// 非空
//			if (!cm.getIsNull()){
//				sb.append(" NOT NULL ");
//			}
			// 主键
			if (cm.getIsPk()){
				if (pkColumn == null){
					pkColumn = cm.getName();
				} else{
					pkColumn += "," + cm.getName();
				}
			}
			// 字段注释
			if (cm.getComment() != null && cm.getComment().length() > 0){
				sb.append(" COMMENT '" + cm.getComment() + "'");
			}
			sb.append(",\n");
		}
		// 建主键
		if (pkColumn != null){
			sb.append(" PRIMARY KEY (" + pkColumn + ")");
		}
		// 表注释
		sb.append("\n)");
		if (model.getComment() != null && model.getComment().length() > 0){
			sb.append(" COMMENT='" + model.getComment() + "'");
		}
		// 建表结束
		sb.append(";");

		jdbcTemplate.execute(sb.toString());

	}

	@Override
	public void updateTableComment(String tableName, String comment) throws SQLException
	{
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ");
		sb.append(tableName);
		sb.append(" COMMENT '");
		sb.append(comment);
		sb.append("';\n");

		jdbcTemplate.execute(sb.toString());

	}

	@Override
	public void addColumn(String tableName, ColumnModel model) throws SQLException
	{
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" ADD (");
		sb.append(model.getName()).append(" ");
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(),
				model.getDecimalLen()));
		
		
		
		if (!model.getIsNull())
		{
			sb.append(" NOT NULL ");
		}
		if (model.getComment() != null && model.getComment().length() > 0)
		{
			sb.append(" COMMENT '" + model.getComment() + "'");
		}
		sb.append(");");

		jdbcTemplate.execute(sb.toString());

	}

	@Override
	public void updateColumn(String tableName, String columnName, ColumnModel model) throws SQLException
	{
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" CHANGE " + columnName + " " + model.getName()).append(" ");
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(),
				model.getDecimalLen()));
		if (!model.getIsNull())
		{
			sb.append(" NOT NULL ");
		}
		if (model.getComment() != null && model.getComment().length() > 0)
		{
			sb.append(" COMMENT '" + model.getComment() + "'");
		}
		sb.append(";");

		jdbcTemplate.execute(sb.toString());
	}

	private String getColumnType(String columnType, int charLen, int intLen, int decimalLen)
	{
		if (ColumnModel.COLUMNTYPE_VARCHAR.equals(columnType)){
			return "VARCHAR(" + charLen + ')';
		} 
		else if (ColumnModel.COLUMNTYPE_NUMBER.equals(columnType)){
			return "DECIMAL(" + (intLen + decimalLen) + "," + decimalLen + ")";
		} 
		else if (ColumnModel.COLUMNTYPE_DATE.equals(columnType)){
			return "DATETIME";
		} else if (ColumnModel.COLUMNTYPE_INT.equals(columnType))
		{
			return "BIGINT(" + intLen + ")";
		}
		else if (ColumnModel.COLUMNTYPE_CLOB.equals(columnType))
		{
			return "TEXT";
		}else
		{
			return "";
		}
	}

	@Override
	public void dropTable(String tableName) {
		
		String sql="drop table if exists " + tableName;
		jdbcTemplate.execute(sql);
	}

	@Override
	public void addForeignKey(String pkTableName, String fkTableName, String pkField,
			String fkField) {
		String sql="ALTER TABLE "+fkTableName+" ADD CONSTRAINT fk_"+fkTableName +" FOREIGN KEY ("+fkField+") REFERENCES "+pkTableName+" ("+pkField+") ON DELETE CASCADE";
		jdbcTemplate.execute(sql);
	}

	@Override
	public void dropForeignKey(String tableName, String keyName) {
		String sql="ALTER TABLE "+tableName+" DROP FOREIGN KEY "+keyName;
		
		jdbcTemplate.execute(sql);
	}

	@Override
	public String getDbType() {
		// TODO Auto-generated method stub
		return this.dbType;
	}

//	@Override
//	public void createIndex(String tableName, String indexName, String indexType, List<String> fields) throws Exception {
//		
//	}
	
	@Override
	public void createIndex(BpmFormTableIndex index) {
		String sql = generateIndexDDL(index);
		jdbcTemplate.execute(sql);
	};

	@Override
	public void dropIndex(String tableName, String indexName) {
		String sql="drop index "+indexName;
		sql+=" on "+tableName;
		jdbcTemplate.execute(sql);
	}

	@Override
	public BpmFormTableIndex getIndex(String tableName, String indexName) {
		if(getIndexesByFuzzyMatching(tableName,indexName,true).size()>0){
			BpmFormTableIndex index =  getIndexesByFuzzyMatching(tableName,indexName,true).get(0);
			try {
				index = dedicatePKIndex(index);
				return index;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public List<BpmFormTableIndex> getIndexesByTable(String tableName) {
		List<BpmFormTableIndex> indexList = getIndexesByFuzzyMatching(tableName,null,true);
		return dedicatePKIndex(indexList);
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName, String indexName,Boolean getDDL) {

		String	schema= getSchema();

		String sql = "SELECT TABLE_NAME,INDEX_NAME,COLUMN_NAME,NULLABLE,INDEX_TYPE,NON_UNIQUE "
				+ "FROM  INFORMATION_SCHEMA.STATISTICS "
				+ "WHERE 1=1";
		if(!StringUtil.isEmpty(schema)){
			sql+=" AND TABLE_SCHEMA='"+schema+"'";
		}
		if(!StringUtil.isEmpty(tableName)){
			 sql +=" AND UPPER(TABLE_NAME) LIKE UPPER('%"+tableName+"%')";
		}
		
		if(!StringUtil.isEmpty(indexName)){
			 sql +=" AND UPPER(INDEX_NAME) like UPPER('%"+indexName+"%')";
		}

		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, new RowMapper<BpmFormTableIndex>(){

			public BpmFormTableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
				BpmFormTableIndex index=new BpmFormTableIndex();
				List<String> columns=new ArrayList<String>();
				index.setIndexTable(rs.getString("TABLE_NAME"));
				index.setIndexName(rs.getString("INDEX_NAME"));
				index.setIndexType(rs.getString("INDEX_TYPE"));
				index.setUnique(rs.getInt("NON_UNIQUE")==0?true:false);
//				index.setIndexComment(rs.getString("INDEX_COMMENT"));
				columns.add(rs.getString("COLUMN_NAME"));
				index.setIndexFields(columns);
				return index;
			}
		});

		// indexes中，第一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = new ArrayList<BpmFormTableIndex>();
		for (BpmFormTableIndex index : indexes) {
			boolean found=false;
			for (BpmFormTableIndex index1 : indexList) {
				if (index.getIndexName().equals(index1.getIndexName())
						&& index.getIndexTable().equals(index1.getIndexTable())) {
					index1.getIndexFields().add(index.getIndexFields().get(0));
					found=true;
					break;
				}
			}
			if(!found){
				indexList.add(index);
			}
		}
		
		if(getDDL){
			for(BpmFormTableIndex index:indexList){
				index.setIndexDdl(generateIndexDDL(index));
			}
		}
		
		dedicatePKIndex(indexList);
		return indexList;
	}
	
	private List<BpmFormTableIndex> dedicatePKIndex(List<BpmFormTableIndex> indexList){
		//用于存放所有索引所包含的所有数据表名(去掉重复)
				List<String> tableNames=new ArrayList<String>();
				for(BpmFormTableIndex index:indexList){
					//将索引对应的表名放到tableNames中。
					if(!tableNames.contains(index.getIndexTable())){
						tableNames.add(index.getIndexTable());
					}
				}
				Map<String,List<String>> tablePKColsMaps=getTablesPKColsByNames(tableNames);
				for(BpmFormTableIndex index:indexList){
					if(isListEqual(index.getIndexFields(),tablePKColsMaps.get(index.getIndexTable()))){
						index.setPkIndex(true);
					}else{
						index.setPkIndex(false);
					}
				}

		return indexList;
	}
	
	private BpmFormTableIndex dedicatePKIndex(BpmFormTableIndex index) throws SQLException{
		List<String> pkCols=getPKColumns(index.getIndexName());
		if(isListEqual(index.getIndexFields(),pkCols)){
			index.setPkIndex(true);
		}else{
			index.setPkIndex(false);
		}
		return index;
	}
	
	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL, PageBean pageBean) {
		String	schema= getSchema();

		String sql = "SELECT TABLE_NAME,INDEX_NAME,COLUMN_NAME,NULLABLE,INDEX_TYPE,NON_UNIQUE "
				+ "FROM  INFORMATION_SCHEMA.STATISTICS "
				+ "WHERE 1=1";
		if(!StringUtil.isEmpty(schema)){
			sql+=" AND TABLE_SCHEMA='"+schema+"'";
		}
		if(!StringUtil.isEmpty(tableName)){
			 sql +=" AND UPPER(TABLE_NAME) LIKE UPPER('%"+tableName+"%')";
		}
		
		if(!StringUtil.isEmpty(indexName)){
			 sql +=" AND UPPER(INDEX_NAME) like UPPER('%"+indexName+"%')";
		}
		
		
		if(pageBean!=null){
			int currentPage=pageBean.getCurrentPage();
			int pageSize=pageBean.getPageSize();
			int offset = (currentPage-1)*pageSize;
			String totalSql=dialect.getCountSql(sql);
			int total = jdbcTemplate.queryForInt(totalSql);
			sql=dialect.getLimitString(sql, offset, pageSize);
			pageBean.setTotalCount(total);
		}
		
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, new RowMapper<BpmFormTableIndex>(){

			public BpmFormTableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
				BpmFormTableIndex index=new BpmFormTableIndex();
				List<String> columns=new ArrayList<String>();
				index.setIndexTable(rs.getString("TABLE_NAME"));
				index.setIndexName(rs.getString("INDEX_NAME"));
				index.setIndexType(rs.getString("INDEX_TYPE"));
				index.setUnique(rs.getInt("NON_UNIQUE")==0?true:false);
//				index.setIndexComment(rs.getString("INDEX_COMMENT"));
				columns.add(rs.getString("COLUMN_NAME"));
				index.setIndexFields(columns);
				return index;
			}
		});
		// indexes中，第一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = new ArrayList<BpmFormTableIndex>();
		for (BpmFormTableIndex index : indexes) {
			boolean found=false;
			for (BpmFormTableIndex index1 : indexList) {
				if (index.getIndexName().equals(index1.getIndexName())
						&& index.getIndexTable().equals(index1.getIndexTable())) {
					index1.getIndexFields().add(index.getIndexFields().get(0));
					found=true;
					break;
				}
			}
			if(!found){
				indexList.add(index);
			}
		}
		
		if(getDDL){
			for(BpmFormTableIndex index:indexList){
				index.setIndexDdl(generateIndexDDL(index));
			}
		}
		dedicatePKIndex(indexList);
		return indexList;
	}

	@Override
	public void rebuildIndex(String tableName, String indexName) {
		String sql="SHOW CREATE TABLE "+tableName;
		@SuppressWarnings("unchecked")
		List<String> ddls = jdbcTemplate.query(sql, new RowMapper<String>(){
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("Create Table");
			}
			
		});
		String ddl =  ddls.get(0);
		
		Pattern pattern=Pattern.compile("ENGINE\\s*=\\s*\\S+",Pattern.CASE_INSENSITIVE);
		Matcher matcher=pattern.matcher(ddl);
		if(matcher.find()){
			String  str = matcher.group();
			String sql_="ALTER TABLE "+tableName+" "+str;
			jdbcTemplate.execute(sql_);
		}
		
	}
	

	@Override
	public List<String> getPKColumns(String tableName) throws SQLException{
		String schema = getSchema();
		String sql = "SELECT k.column_name "
				+ "FROM information_schema.table_constraints t "
				+ "JOIN information_schema.key_column_usage k "
				+ "USING(constraint_name,table_schema,table_name) "
				+ "WHERE t.constraint_type='PRIMARY KEY' "
				+ "AND t.table_schema='"+schema+"' "
				+ "AND t.table_name='"+tableName+"'";
		List<String> columns = jdbcTemplate.query(sql, new RowMapper<String>(){

			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				String column = rs.getString(1);
				return column;
			}
		});
		return columns;
	}
	
	@Override
	public Map<String, List<String>> getPKColumns(List<String> tableNames) throws SQLException{
		StringBuffer sb=new StringBuffer();
		for(String name:tableNames){
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length()-1);
		
		String schema = getSchema();
		String sql = "SELECT t.table_name,k.column_name "
				+ "FROM information_schema.table_constraints t "
				+ "JOIN information_schema.key_column_usage k "
				+ "USING(constraint_name,table_schema,table_name) "
				+ "WHERE t.constraint_type='PRIMARY KEY' "
				+ "AND t.table_schema='"+schema+"' "
				+ "AND t.table_name in ("+sb.toString().toUpperCase()+")";
		
		Map<String,List<String>> columnsMap=new HashMap<String, List<String>>();
		
		List<Map<String,String>> maps = jdbcTemplate.query(sql, new RowMapper<Map<String,String>>() {
			public Map<String,String> mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				String table = rs.getString(1);
				String column = rs.getString(2);
				Map<String,String> map =new HashMap<String, String>();
				map.put("name", table);
				map.put("column", column);
				return map;
			}
		});
		
		for(Map<String,String> map:maps){
			if(columnsMap.containsKey(map.get("name"))){
				columnsMap.get(map.get("name")).add(map.get("column"));
			}else{
				List<String> cols=new ArrayList<String>();
				cols.add(map.get("column"));
				columnsMap.put(map.get("name"),cols);
			}
		}
		
		return columnsMap;
	}
	
	/**
	 * 取得当前连接的Schema
	 * @return
	 */
	private String getSchema(){
		String schema = null;
		try {
			schema = jdbcTemplate.getDataSource().getConnection().getCatalog();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schema;
	}

	/**
	 * 生成索引的DDL语句
	 * @param index
	 * @return
	 */
	private String  generateIndexDDL(BpmFormTableIndex index){
		StringBuffer ddl=new StringBuffer();
		ddl.append("CREATE");
		if(index.isUnique()){
			ddl.append(" UNIQUE");
		}
		ddl.append(" INDEX");
		ddl.append(" "+index.getIndexName());
		ddl.append(" USING");
		ddl.append(" "+index.getIndexType());
		ddl.append(" ON "+index.getIndexTable());
		
		for(String column:index.getIndexFields()){
			ddl.append(column+",");
		}
		if(!StringUtil.isEmpty(index.getIndexComment())){
			ddl.append("COMMENT '"+index.getIndexComment()+"'");
		}
		ddl.replace(ddl.length()-1, ddl.length(), ")");
		return ddl.toString();
	}
	/**
	 * 判断索引是否存在
	 * @param tableName
	 * @param indexName
	 * @return
	 */
	private boolean isIndexExist(String tableName,String indexName){
		String schema=getSchema();

		String sql = "SELECT COUNT(*) "
				+ "FROM  INFORMATION_SCHEMA.STATISTICS "
				+ "WHERE 1=1";
		if(!StringUtil.isEmpty(schema)){
			sql+=" AND TABLE_SCHEMA='"+schema+"'";
		}
		if(!StringUtil.isEmpty(tableName)){
			 sql +=" AND UPPER(TABLE_NAME) = UPPER('"+tableName+"')";
		}
		
		if(!StringUtil.isEmpty(indexName)){
			 sql +=" AND UPPER(INDEX_NAME) = UPPER('"+indexName+"')";
		}
		int count = jdbcTemplate.queryForInt(sql);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 根据表名，取得对应表的主键列。此方法是指操作，主要是减少对数据库的访问次数。返回的Map中：key=表名；value=表名对应的主键列列表。
	 * @param tableNames
	 * @return
	 */
	private Map<String,List<String>> getTablesPKColsByNames(List<String> tableNames){
		Map<String,List<String>> tableMaps=new HashMap<String,List<String>>();
		List<String> names=new ArrayList<String>();
		for(int i=1;i<=tableNames.size();i++){
			names.add(tableNames.get(i-1));
			if(i%BATCHSIZE==0 || i== tableNames.size()){
				Map<String, List<String>> map;
				try {
					map = getPKColumns(names);
					tableMaps.putAll(map);
					names.clear();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return tableMaps;
	}
	
	
	/**比较两个列表是否相等。在比较两个列表的元素时，比较的方式为(o==null ? e==null : o.equals(e)). 
	 * @param list1
	 * @param list2
	 * @return
	 */
	private boolean isListEqual(List list1,List list2){
		if(list1==null){
			if(list2==null){
				return true;
			}else{
				return false;
			}
		}else if(list2==null){
			return false;
		}
		if(list1.size()!=list2.size()){
			return false;
		}
		if(list1.containsAll(list2)){
			return true;
		}
		return false;
	}
	/**
	 * 检查表是否存在
	 */
	@Override
	public boolean isTableExist(String tableName) {
		String	schema= getSchema();
		String sql="select count(1) from information_schema.TABLES t where t.TABLE_SCHEMA='"+schema+"' and table_name ='"+tableName.toUpperCase()+"'";
		return	jdbcTemplate.queryForInt(sql)>0?true:false;
	}
}
