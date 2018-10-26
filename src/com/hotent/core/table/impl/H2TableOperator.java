package com.hotent.core.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.page.PageBean;
import com.hotent.core.table.AbstractTableOperator;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormTableIndex;

public class H2TableOperator extends AbstractTableOperator {

	//批量操作的
	protected int BATCHSIZE = 100;
	
	
	private final String  SQL_GET_ALL_INDEX="" +
		"SELECT " +
			"A.TABLE_NAME  , " + //Table Name
			"A.INDEX_NAME  , " + //Index Name
			"A.NON_UNIQUE  , " + //Unique rule: D = Duplicates allowed; P = Primary index; U = Unique entries only allowed 
			"A.COLUMN_NAME  , " + // column count
			"A.INDEX_TYPE_NAME  , " + // index type
			"A.REMARKS , "+
			"A.SQL "+ //index comment
		"FROM " +
			"INFORMATION_SCHEMA.INDEXES  A "+
		"WHERE " +
			"1=1 ";

	
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
			// 添加默认值
			String defVal=getDefaultValueSQL(cm);
			if (StringUtil.isNotEmpty(defVal)) {
				sb.append(defVal);
			}

			// 非空
			 if (!cm.getIsNull() || cm.getIsPk()) {
				 sb.append(" NOT NULL ");
			 }

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
		} else {
			sb = new StringBuffer(sb.substring(0, sb.length() - ",\n".length()));
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

	@Override
	public void addColumn(String tableName, ColumnModel model) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" ADD ");
		sb.append(model.getName()).append(" ");
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
		
		//添加默认值
		// 添加默认值
		String defVal=getDefaultValueSQL(model);
		if (StringUtil.isNotEmpty(defVal)) {
			sb.append(defVal);
		}
		sb.append("\n");
		jdbcTemplate.execute(sb.toString());
		if (model.getComment() != null && model.getComment().length() > 0) {
			jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS '" + model.getComment() + "'");
		}
	}

	@Override
	public void updateColumn(String tableName, String columnName, ColumnModel model) throws SQLException {
		//修改列名
		if (!columnName.equals(model.getName())) {
			StringBuffer modifyName = new StringBuffer("ALTER TABLE ").append(tableName);
			modifyName.append(" ALTER COLUMN ").append(columnName).append(" RENAME TO ").append(model.getName());
			jdbcTemplate.execute(modifyName.toString());
		}
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ").append(tableName);
		sb.append(" ALTER COLUMN ").append(model.getName());
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
		if (!model.getIsNull()) {
			sb.append(" NOT NULL ");
		}
		jdbcTemplate.execute(sb.toString());
		// 修改注释
		if (model.getComment() != null && model.getComment().length() > 0) {
			jdbcTemplate.execute("COMMENT ON COLUMN " + tableName + "." + model.getName() + " IS'" + model.getComment() + "'");
		}
	}

	@Override
	public void dropTable(String tableName) {
		String selSql=""+
			"SELECT " +
				"COUNT(*) AMOUNT " +
			"FROM " +
				"INFORMATION_SCHEMA.TABLES  " +
			"WHERE " +
				"TABLE_SCHEMA = SCHEMA() "+
				"AND TABLE_NAME = UPPER('"+tableName+"')";
		int rtn=jdbcTemplate.queryForInt(selSql);
		if(rtn>0){
			String sql="DROP TABLE " + tableName;
			jdbcTemplate.execute(sql);
		}
	}

	@Override
	public void addForeignKey(String pkTableName, String fkTableName, String pkField, String fkField) {
		 String sql=" ALTER TABLE "+fkTableName+" ADD CONSTRAINT FK_" + fkTableName +" FOREIGN KEY ("+fkField+") REFERENCES "+pkTableName+" ("+pkField+") ON DELETE CASCADE";
		 jdbcTemplate.execute(sql);
	}

	@Override
	public void dropForeignKey(String tableName, String keyName) {
		String sql="ALTER   TABLE   "+tableName+"   DROP   CONSTRAINT  "+keyName;
		jdbcTemplate.execute(sql);
	}


	@Override
	public void dropIndex(String tableName, String indexName) {
		String sql="DROP INDEX "+indexName;
		jdbcTemplate.execute(sql);
	}

	@Override
	public BpmFormTableIndex getIndex(String tableName, String indexName) {
		String sql =SQL_GET_ALL_INDEX;
//		sql+=" AND A.TABLE_NAME = '"+tableName+"' ";
		sql+=" AND A.INDEX_NAME = '"+indexName+"' ";
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, indexRowMapper);
		List<BpmFormTableIndex> indexList = mergeIndex(indexes);
		if(BeanUtils.isEmpty(indexList)){
			return null;
		}else{
			BpmFormTableIndex index = indexList.get(0);
			return index;
		}
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByTable(String tableName) {
		String sql = SQL_GET_ALL_INDEX;
		sql += " AND UPPER(A.TABLE_NAME) = UPPER('"+tableName+"')";
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, indexRowMapper);
		// indexes中，每一索引列，对应一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = mergeIndex(indexes);
		for(BpmFormTableIndex index:indexList){
			index.setIndexDdl(generateIndexDDL(index));
		}
		return indexList;
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName, String indexName,Boolean getDDL) {
		return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName, String indexName, Boolean getDDL, PageBean pageBean) {
		String sql = SQL_GET_ALL_INDEX;
	
		if(!StringUtil.isEmpty(tableName)){
			 sql +=" AND UPPER(A.TABLE_NAME) LIKE UPPER('%"+tableName+"%')";
		}
		
		if(!StringUtil.isEmpty(indexName)){
			 sql +=" AND UPPER(A.INDEX_NAME) like UPPER('%"+indexName+"%')";
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
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, indexRowMapper);
		// indexes中，每一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = mergeIndex(indexes);
		
		for(BpmFormTableIndex index:indexList){
			index.setIndexDdl(generateIndexDDL(index));
		}
		return indexList;
	}
	
	/**
	 * 索引重建。DB2只能以表为单位进行索引重建。只需要传入表名。
	 * @see com.hotent.core.customertable.AbstractTableOperator#rebuildIndex(java.lang.String, java.lang.String)
	 */
	@Override
	public void rebuildIndex(String tableName, String indexName) {
		//String sql =" REORG INDEXES ALL FOR TABLE "+tableName+" ALLOW WRITE ACCESS CLEANUP ONLY ALL ";
		//jdbcTemplate.execute(sql);
		throw new UnsupportedOperationException("h2 不支持通过JDBC进行索引重建！");
	}

	@Override
	public void createIndex(BpmFormTableIndex index) throws SQLException {
		String sql = generateIndexDDL(index);
		jdbcTemplate.execute(sql);
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

	/**
	 * 生成BpmFormTableIndex对象对应的DDL语句
	 * @param index
	 * @return
	 */
	private String generateIndexDDL(BpmFormTableIndex index){
		StringBuffer sql=new StringBuffer();
		sql.append("CREATE ");
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

	/**
	 * 根据列类型。
	 * 
	 * @param columnType
	 *            列类型
	 * @param charLen
	 *            字符长度
	 * @param intLen
	 *            整形长度
	 * @param decimalLen
	 *            小数精度
	 * @return
	 */
	private String getColumnType(String columnType, int charLen, int intLen, int decimalLen) {
		if (ColumnModel.COLUMNTYPE_VARCHAR.equals(columnType)) {
			return "VARCHAR(" + charLen + ')';
		} else if (ColumnModel.COLUMNTYPE_NUMBER.equals(columnType)) {
			return "DECIMAL(" + (intLen + decimalLen) + "," + decimalLen + ")";
		} else if (ColumnModel.COLUMNTYPE_DATE.equals(columnType)) {
			return "DATE";
		} else if (ColumnModel.COLUMNTYPE_INT.equals(columnType)) {
			if(intLen>0 && intLen<=5){
				return "SMALLINT";
			}else if(intLen>5 && intLen<=10){
				return "INTEGER";
			}else{
				return "BIGINT";
			}
		} else if (ColumnModel.COLUMNTYPE_CLOB.equals(columnType)) {
			return "CLOB";
		} else {
			return "VARCHAR(50)";
		}
	}
	
	private String getDefaultValueSQL(ColumnModel cm){
		String sql=null;
		if (StringUtil.isNotEmpty(cm.getDefaultValue())) {
			if (ColumnModel.COLUMNTYPE_INT.equalsIgnoreCase(cm.getColumnType())) {
				sql = " DEFAULT " + cm.getDefaultValue() +" ";
			} else if (ColumnModel.COLUMNTYPE_NUMBER.equalsIgnoreCase(cm.getColumnType())) {
				sql = " DEFAULT " + cm.getDefaultValue() +" ";
			} else if (ColumnModel.COLUMNTYPE_VARCHAR.equalsIgnoreCase(cm.getColumnType())) {
				sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
			} else if (ColumnModel.COLUMNTYPE_CLOB.equalsIgnoreCase(cm.getColumnType())) {
				sql = " DEFAULT '" + cm.getDefaultValue() + "' ";
			} else if (ColumnModel.COLUMNTYPE_DATE.equalsIgnoreCase(cm.getColumnType())){
				sql = " DEFAULT " + cm.getDefaultValue() + " ";
			} else {
				sql = " DEFAULT " + cm.getDefaultValue() + " ";
			}
		}
		return sql;
	}
	
	private RowMapper<BpmFormTableIndex> indexRowMapper=new RowMapper<BpmFormTableIndex>() {
		public BpmFormTableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
			BpmFormTableIndex index=new BpmFormTableIndex();
			index.setIndexTable(rs.getString("TABLE_NAME"));
			index.setTableType(BpmFormTableIndex.TABLE_TYPE_TABLE);
			index.setIndexName(rs.getString("INDEX_NAME"));
			// set unique type
			String non_unique=rs.getString("NON_UNIQUE").trim();
			String index_type_name=rs.getString("INDEX_TYPE_NAME").trim();
			if("TRUE".equalsIgnoreCase(non_unique)){
				index.setUnique(true);
			}
			// set primary index
			if("PRIMARY KEY".equalsIgnoreCase(index_type_name)){
				index.setPkIndex(true);
			}
			// set index type
			index.setIndexType(BpmFormTableIndex.INDEX_TYPE_BTREE);
			
			index.setIndexComment(rs.getString("REMARKS"));
			List<String> indexFields=new ArrayList<String>();
			indexFields.add(rs.getString("COLUMN_NAME"));
			index.setIndexFields(indexFields);
			//set index ddl
			index.setIndexDdl(rs.getString("SQL"));
			return index;
		}
	};
	
	/**
	 * 检查表是否存在
	 */
	//参考方法 dropTable
	@Override
	public boolean isTableExist(String tableName) {
		String selSql=""+
				"SELECT " +
					"COUNT(*) AMOUNT " +
				"FROM " +
					"INFORMATION_SCHEMA.TABLES  " +
				"WHERE " +
					"TABLE_SCHEMA = SCHEMA() "+
					"AND TABLE_NAME = UPPER('"+tableName+"')";
			int rtn=jdbcTemplate.queryForInt(selSql);
			return rtn>0?true:false;
	}
}
