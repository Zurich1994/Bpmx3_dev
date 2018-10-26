package com.hotent.core.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.mybatis.Dialect;
import com.hotent.core.page.PageBean;
import com.hotent.core.table.AbstractTableOperator;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormTableIndex;

/**
 * oracle 数据库表操作的接口实现。
 * @author ray
 *
 */
//@Repository
public class DmTableOperator extends AbstractTableOperator {
	
	//批量操作的
		protected int BATCHSIZE = 100;
	
	

	@Override
	public void createTable(TableModel model) throws SQLException {

		List<ColumnModel> columnList = model.getColumnList();

		// 建表语句
		StringBuffer sb = new StringBuffer();
		// 主键字段
		String pkColumn = null;


		// 例注释
		List<String> columnCommentList = new ArrayList<String>();
		// 建表开始
		sb.append("CREATE TABLE " + model.getName() + " (\n");
		for (int i = 0; i < columnList.size(); i++) {
			// 建字段
			ColumnModel cm = columnList.get(i);
			sb.append("    ").append(cm.getName()).append("    ");
			sb.append(getColumnType(cm.getColumnType(), cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
			sb.append(" ");
			
			// 主键
			if (cm.getIsPk()) {
				if (pkColumn == null) {
					pkColumn = cm.getName();
				} else {
					pkColumn += "," + cm.getName();
				}
			}
			//添加默认值
			if(StringUtil.isNotEmpty(cm.getDefaultValue())){
				sb.append(" DEFAULT " + cm.getDefaultValue());
			}
			
			// 非空
//			if (!cm.getIsNull()) {
//				sb.append(" NOT NULL ");
//			}

			// 字段注释
			if (cm.getComment() != null && cm.getComment().length() > 0) {
				// createTableSql.append(" COMMENT '" + cm.getComment() + "'");
				columnCommentList.add("COMMENT ON COLUMN " + model.getName() + "." + cm.getName() + " IS '" + cm.getComment() + "'\n");
			}
			sb.append(",\n");
		}
		// 建主键
		if (pkColumn != null) {
			sb.append("    CONSTRAINT PK_").append(model.getName()).append(" PRIMARY KEY (").append(pkColumn).append(")");
		}


		// 建表结束
		sb.append("\n)");
		// 表注释
		jdbcTemplate.execute(sb.toString());
		if (model.getComment() != null && model.getComment().length() > 0) {
			jdbcTemplate.execute("COMMENT ON TABLE " + model.getName() + " IS '" + model.getComment() + "'\n");
		}
		for (String columnComment : columnCommentList) {
			jdbcTemplate.execute(columnComment);
		}
	}

	@Override
	public void updateTableComment(String tableName, String comment) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("COMMENT ON TABLE ");
		sb.append(tableName);
		sb.append(" IS '");
		sb.append(comment);
		sb.append("'\n");
		jdbcTemplate.execute(sb.toString());
	}

	/**
	 * 添加列。
	 */
	public void addColumn(String tableName, ColumnModel model) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" ADD ");
		sb.append(model.getName()).append(" ");
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
		
		//添加默认值
		if(StringUtil.isNotEmpty(model.getDefaultValue())){
			sb.append(" DEFAULT " + model.getDefaultValue());
		}
		
//		if (!model.getIsNull()) {
//			sb.append(" NOT NULL ");
//		}
		sb.append("\n");
		jdbcTemplate.execute(sb.toString());
		if (model.getComment() != null && model.getComment().length() > 0) {
			jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS '" + model.getComment() + "'");
		}

	}

	@Override
	public void updateColumn(String tableName, String columnName, ColumnModel model) throws SQLException
	{
		//修改列名
		if(!columnName.equals(model.getName())){
			StringBuffer modifyName = new StringBuffer("ALTER TABLE ").append(tableName);
			modifyName.append(" RENAME COLUMN ").append(columnName).append(" TO ").append(model.getName());
			jdbcTemplate.execute(modifyName.toString());
		}
		
		//修改列的大小,此处不修改列的类型,若修改列的类型则在前面部分已抛出异常
		StringBuffer sb = new StringBuffer();
		//alter table test01 modify(test_01  NUMBER(1));
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" MODIFY("+ model.getName()).append(" ");
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(),
				model.getDecimalLen()));
		if (!model.getIsNull())
		{
			sb.append(" NOT NULL ");
		}
		sb.append(")");

		jdbcTemplate.execute(sb.toString());
		
		//修改注释
		if (model.getComment() != null && model.getComment().length() > 0){
			jdbcTemplate.execute("COMMENT ON COLUMN "+tableName+"."+model.getName()+" IS'" + model.getComment() + "'");
		}
	}

	/**
	 * 根据列类型。
	 * @param columnType	列类型
	 * @param charLen		字符长度
	 * @param intLen		整形长度
	 * @param decimalLen	小数精度
	 * @return
	 */
	private String getColumnType(String columnType, int charLen, int intLen, int decimalLen) {
		if (ColumnModel.COLUMNTYPE_VARCHAR.equals(columnType)) {
			return "VARCHAR2(" + charLen + ')';
		} else if (ColumnModel.COLUMNTYPE_NUMBER.equals(columnType)) {
			return "NUMBER(" + (intLen + decimalLen) + "," + decimalLen + ")";
		} else if (ColumnModel.COLUMNTYPE_DATE.equals(columnType)) {
			return "DATE";
		} else if (ColumnModel.COLUMNTYPE_INT.equals(columnType)) {
			return "NUMBER(" + intLen + ")";
		} else if(ColumnModel.COLUMNTYPE_CLOB.equals(columnType)){
			return "CLOB";
		}else{
			return "VARCHAR2(50)";
		}
	}

	/**
	 * 删除表。
	 */
	@Override
	public void dropTable(String tableName) {
		String selSql="select count(*) amount from user_objects where object_name = upper('"+tableName+"')";
		int rtn=jdbcTemplate.queryForInt(selSql);
		if(rtn>0){
			String sql="drop table " + tableName;
			jdbcTemplate.execute(sql);
		}
	}

	/**
	 * 添加外键。
	 */
	@Override
	public void addForeignKey(String pkTableName, String fkTableName, String pkField,
			String fkField) {
		 String sql=" ALTER TABLE "+fkTableName+" ADD CONSTRAINT fk_" + fkTableName +" FOREIGN KEY ("+fkField+") REFERENCES "+pkTableName+" ("+pkField+") ON DELETE CASCADE";
		 jdbcTemplate.execute(sql);
	}

	/**
	 * 删除外键
	 */
	@Override
	public void dropForeignKey(String tableName, String keyName) {
		String sql="ALTER   TABLE   "+tableName+"   DROP   CONSTRAINT  "+keyName;
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 	通过BpmFormTableIndex实例，在数据库中创建索引
	 */
	@Override
	public void createIndex(BpmFormTableIndex index){
		String sql = generateIndexDDL(index);
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * 生成BpmFormTableIndex对象对应的DDL语句
	 * @param index
	 * @return
	 */
	private String generateIndexDDL(BpmFormTableIndex index){
		StringBuffer sql=new StringBuffer();
		sql.append("CREATE ");
		if(!StringUtil.isEmpty(index.getIndexType())){
			if(index.getIndexType().equalsIgnoreCase("BITMAP")){
				sql.append("BITMAP ");
			}
		}
		sql.append("INDEX ");
		sql.append(index.getIndexName());
		sql.append(" ON ");
		sql.append(index.getIndexTable());
		sql.append("(");
		for(String field:index.getIndexFields()){
			sql.append(field);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		return sql.toString();
	}
	
	private boolean isIndexExist(String index){
		String sql="SELECT COUNT(*) FROM \"SYS\".\"USER_INDEXES\" WHERE INDEX_NAME = '"+index+"'";
		int count = jdbcTemplate.queryForInt(sql);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 取得数据库的类型
	 */
	public String getDbType(){
		return this.dbType;
	}

	public void dropIndex(String tableName,String indexName){
		String sql="DROP INDEX "+indexName;
		jdbcTemplate.execute(sql);
	}
	
	public void rebuildIndex(String tableName,String indexName){
		String sql ="ALTER INDEX "+indexName+" REBUILD";
		jdbcTemplate.execute(sql);
	}
	
	public BpmFormTableIndex getIndex(String tableName,String indexName){

		//indexName = "INDEX"+ indexName.substring(5, indexName.length());
		
		String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, "
				+ "IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,"
				+ "DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL "
				+ "FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  "
				+ "WHERE IDX.INDEX_NAME=UPPER('" + indexName + "')";
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, new RowMapper<BpmFormTableIndex>(){
	
			@Override
			public BpmFormTableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
				BpmFormTableIndex index=new BpmFormTableIndex();
				index.setIndexTable(rs.getString("TABLE_NAME"));
				index.setTableType(rs.getString("TABLE_TYPE"));
				index.setIndexName(rs.getString("INDEX_NAME"));
				index.setIndexType(rs.getString("INDEX_TYPE"));
				index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE")?true:false);
				index.setIndexStatus(rs.getString("STATUS"));
				index.setIndexDdl(rs.getString("DDL"));
				List<String> indexFields=new ArrayList<String>();
				indexFields.add(rs.getString("COLUMN_NAME"));
				index.setIndexFields(indexFields);
				return index;
			}
			
		});
		
		// indexes中，第一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = mergeIndex(indexes);
		if(indexList.size()>0){
			return dedicatePKIndex(indexList.get(0));
		}else{
			return null;
		}
	}

	public List<BpmFormTableIndex> getIndexesByTable(String tableName){
		String sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, "
				+ "IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,"
				+ "DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL "
				+ "FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME  "
				+ "WHERE IDX.TABLE_NAME=UPPER('" + tableName + "')";
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, new RowMapper<BpmFormTableIndex>(){
			@Override
			public BpmFormTableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
				BpmFormTableIndex index=new BpmFormTableIndex();
				index.setIndexTable(rs.getString("TABLE_NAME"));
				index.setTableType(rs.getString("TABLE_TYPE"));
				index.setIndexName(rs.getString("INDEX_NAME"));
				index.setIndexType(rs.getString("INDEX_TYPE"));
				index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE")?true:false);
				index.setIndexStatus(rs.getString("STATUS"));
				index.setIndexDdl(rs.getString("DDL"));
//				String sqlForColumns="SELECT COLUMN_NAME FROM USER_IND_COLUMNS  WHERE INDEX_NAME = UPPER('"+ index.getIndexName()+"')";
//				List<String> indexFields=jdbcTemplate.queryForList(sqlForColumns, String.class);
				List<String> indexFields=new ArrayList<String>();
				indexFields.add(rs.getString("COLUMN_NAME"));
				index.setIndexFields(indexFields);
				return index;
			}
			
		});
		
		// indexes中，第一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = mergeIndex(indexes);
		
		dedicateFKIndex(indexList);
		dedicatePKIndex(indexList);
		return indexList;
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName, String indexName,Boolean getDDL) {
		return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL, PageBean pageBean) {
		String sql;
		if(getDDL){
			sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, "
					+ "IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME,"
					+ "DBMS_METADATA.GET_DDL('INDEX',idx.INDEX_NAME) AS DDL "
					+ "FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME "
					+ "WHERE 1=1";
		}else{
			sql = "SELECT IDX.TABLE_NAME,IDX.TABLE_TYPE,IDX.INDEX_NAME, "
					+ "IDX.INDEX_TYPE,IDX.UNIQUENESS,IDX.STATUS,IDC.COLUMN_NAME "
					+ "FROM \"SYS\".\"USER_INDEXES\" IDX JOIN \"SYS\".\"USER_IND_COLUMNS\" IDC ON IDX.INDEX_NAME=IDC.INDEX_NAME "
					+ "WHERE 1=1";
		}
		if(!StringUtil.isEmpty(tableName)){
			 sql +=" AND UPPER(IDX.TABLE_NAME) LIKE UPPER('%"+tableName+"%')";
		}
		
		if(!StringUtil.isEmpty(indexName)){
			 sql +=" AND UPPER(IDX.INDEX_NAME) like UPPER('%"+indexName+"%')";
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
		
		logger.debug(sql);
		
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, new RowMapper<BpmFormTableIndex>(){
	
			@Override
			public BpmFormTableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
				BpmFormTableIndex index=new BpmFormTableIndex();
				index.setIndexTable(rs.getString("TABLE_NAME"));
				index.setTableType(rs.getString("TABLE_TYPE"));
				index.setIndexName(rs.getString("INDEX_NAME"));
				index.setIndexType(rs.getString("INDEX_TYPE"));
				index.setUnique(rs.getString("UNIQUENESS").equalsIgnoreCase("UNIQUE")?true:false);
				index.setIndexStatus(rs.getString("STATUS"));
//				String sqlForColumns="SELECT COLUMN_NAME FROM USER_IND_COLUMNS  WHERE INDEX_NAME = UPPER('"+ index.getIndexName()+"')";
//				List<String> indexFields=jdbcTemplate.queryForList(sqlForColumns, String.class);
				List<String> indexFields=new ArrayList<String>();
				indexFields.add(rs.getString("COLUMN_NAME"));
				index.setIndexFields(indexFields);
				return index;
			}
			
		});
		
		// indexes中，每一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = mergeIndex(indexes);
		
		
		//dedicateFKIndex(indexList);
		dedicatePKIndex(indexList);
		return indexList;
	}
	


	private List<BpmFormTableIndex> mergeIndex(List<BpmFormTableIndex> indexes){
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
		return indexList;
	}

	@Override
	public List<String> getPKColumns(String tableName) throws SQLException {
		String sql = "SELECT cols.column_name"
				+ " FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS"
				+ " WHERE UPPER(cols.table_name) = UPPER('"+tableName+"')"
				+ " AND cons.constraint_type = 'P'  AND cols.position=1"
				+ " AND cons.constraint_name = cols.constraint_name"
				+ " AND cons.owner = cols.owner";
		List<String> columns = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String column = rs.getString(1);
				return column;
			}
		});
		return columns;
	}
	
	
	@Override
	public Map<String,List<String>> getPKColumns(List<String> tableNames) throws SQLException {
		
		StringBuffer sb=new StringBuffer();
		for(String name:tableNames){
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length()-1);
		
		String sql = "SELECT cols.table_name,cols.column_name"
				+ " FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS"
				+ " WHERE UPPER(cols.table_name) in ("+sb.toString().toUpperCase()+")"
				+ " AND cons.constraint_type = 'P' AND COLS.POSITION=1"
				+ " AND cons.constraint_name = cols.constraint_name"
				+ " AND CONS.OWNER = COLS.OWNER";
		
		Map<String,List<String>> columnsMap=new HashMap<String, List<String>>();
		
		List<Map<String,String>> maps = jdbcTemplate.query(sql, new RowMapper<Map<String,String>>() {
			@Override
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

	@Override
	public void setDialect(Dialect dialect) {
		this.dialect=dialect;
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
	private boolean isListEqual(List<?> list1,List<?> list2){
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
	 * 判断索引是否是主键索引。如果是，则将索引index的pkIndex属性设置为true。此方法是批量操作，主要是减少对数据库访问次数。
	 * @param indexList
	 * @return
	 */
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
	
	/**
	 * 判断索引是否是主键索引。如果是，则将索引index的pkIndex属性设置为true。
	 * @param index
	 * @return
	 */
	private BpmFormTableIndex dedicatePKIndex(BpmFormTableIndex index){
		List<String> pkCols;
		try {
			pkCols = getPKColumns(index.getIndexName());
			if(isListEqual(index.getIndexFields(),pkCols)){
				index.setPkIndex(true);
			}else{
				index.setPkIndex(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return index;
	}
	
	/**
	 * 删除外键索引
	 * @param indexList
	 */
	private List<BpmFormTableIndex> dedicateFKIndex(List<BpmFormTableIndex> indexList) {
		//用于存放所有索引所包含的所有数据表名(去掉重复)
		List<String> tableNames=new ArrayList<String>();
		for(BpmFormTableIndex index:indexList){
			//将索引对应的表名放到tableNames中。
			if(!tableNames.contains(index.getIndexTable())){
				tableNames.add(index.getIndexTable());
			}
		}
		Map<String,List<String>> tableFKColsMaps=getTablesFKColsByNames(tableNames);
		for(BpmFormTableIndex index:indexList){
			if(isListEqual(index.getIndexFields(),tableFKColsMaps.get(index.getIndexTable()))){
				indexList.remove(index);
			}
		}
		return indexList;
	}

	private Map<String, List<String>> getTablesFKColsByNames(
			List<String> tableNames) {
		Map<String,List<String>> tableMaps=new HashMap<String,List<String>>();
		List<String> names=new ArrayList<String>();
		for(int i=1;i<=tableNames.size();i++){
			names.add(tableNames.get(i-1));
			if(i%BATCHSIZE==0 || i== tableNames.size()){
				Map<String, List<String>> map;
				try {
					map = this.getFKColumns(names);
					tableMaps.putAll(map);
					names.clear();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tableMaps;
	}

	/**
	 * 获取外键
	 * @param tableNames
	 * @return
	 * @throws SQLException
	 */
	private Map<String, List<String>> getFKColumns(List<String> tableNames) throws SQLException {
		StringBuffer sb=new StringBuffer();
		for(String name:tableNames){
			sb.append("'");
			sb.append(name);
			sb.append("',");
		}
		sb.deleteCharAt(sb.length()-1);
		
		String sql = "SELECT cols.table_name,cols.column_name"
				+ " FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" COLS"
				+ " WHERE UPPER(cols.table_name) in ("+sb.toString().toUpperCase()+")"
				+ " AND cons.constraint_type = 'F' AND COLS.POSITION=1"
				+ " AND cons.constraint_name = cols.constraint_name"
				+ " AND CONS.OWNER = COLS.OWNER";
		
		Map<String,List<String>> columnsMap=new HashMap<String, List<String>>();
		
		List<Map<String,String>> maps = jdbcTemplate.query(sql, new RowMapper<Map<String,String>>() {
			@Override
			public Map<String,String> mapRow(ResultSet rs, int rowNum) throws SQLException {
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
	 * 检查表是否存在
	 */
	@Override
	public boolean isTableExist(String tableName) {
		StringBuffer sql  = new StringBuffer();
		sql.append("select COUNT(1) from user_tables t where t.TABLE_NAME='").append(tableName.toUpperCase()).append("'");
		return	jdbcTemplate.queryForInt(sql.toString())>0?true:false;
	}
}
