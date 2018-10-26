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

/**
 * DB2 数据表的操作
 * 
 * @author Raise
 * 
 */
public class Db2TableOperator extends AbstractTableOperator {
	//批量操作的
	protected int BATCHSIZE = 100;
	
	
	private final String  SQL_GET_ALL_INDEX="" +
			"SELECT " +
			"A.TABNAME, " + //Table Name
			"A.INDNAME, " + //Index Name
			"B.COLNAME, " + // column names
			"A.UNIQUERULE, " + //Unique rule: D = Duplicates allowed; P = Primary index; U = Unique entries only allowed 
			"A.COLCOUNT, " + // column count
			"A.INDEXTYPE, " + // index type
			"A.REMARKS " + //index comment
		"FROM " +
			"SYSCAT.INDEXES A "+
			"JOIN SYSCAT.INDEXCOLUSE B ON A.INDNAME=B.INDNAME " +
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
	public void updateColumn(String tableName, String columnName, ColumnModel model) throws SQLException {
		//修改列名
//		if(!columnName.equalsIgnoreCase(model.getName())){
//			StringBuffer modifyName = new StringBuffer("ALTER TABLE ").append(tableName);
//			modifyName.append(" RENAME COLUMN ").append(columnName).append(" TO ").append(model.getName());
//			jdbcTemplate.execute(modifyName.toString());
//		}
		if(!columnName.equalsIgnoreCase(model.getName())){
			//add new column
			StringBuffer addColumn=new StringBuffer();
			addColumn.append("alter table ");
			addColumn.append(tableName);
			addColumn.append(" add column ");
			addColumn.append("    ").append(model.getName()).append("    ");
			addColumn.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
			addColumn.append(" ");
			// 添加默认值
			String defVal=getDefaultValueSQL(model);
			if (StringUtil.isNotEmpty(defVal)) {
				addColumn.append(defVal);
			}
			jdbcTemplate.execute(addColumn.toString());
			//dump column value
			String copyValue="update table "+tableName+" set "+model.getName()+"="+columnName;
			jdbcTemplate.execute(copyValue);
			//drop old column
			String dropColumn="alter table "+tableName+" drop column "+columnName;
			jdbcTemplate.execute(dropColumn);
		}
		
		
		//修改列的大小
		StringBuffer sb = new StringBuffer();
		//alter table NODES ALTER NODE_NAME SET DATA TYPE varchar(32);
		sb.append("ALTER TABLE ").append(tableName);
		sb.append("  ALTER "+ model.getName()).append(" ");
		sb.append(" SET	DATA TYPE ");
		sb.append(getColumnType(model.getColumnType(), model.getCharLen(), model.getIntLen(),
				model.getDecimalLen()));
		jdbcTemplate.execute(sb.toString());
		
		//alter null able
		if (!model.getIsNull()){
			String nullable = "ALTER TABLE "+tableName+" ALTER "+model.getName()+" DROP NOT NULL";
			jdbcTemplate.execute(nullable);
		}else{
			String notnull = "ALTER TABLE "+tableName+" ALTER "+model.getName()+" SET NOT NULL";
			jdbcTemplate.execute(notnull);
		}
		
		//修改注释
		if (model.getComment() != null && model.getComment().length() > 0){
			jdbcTemplate.execute("COMMENT ON COLUMN "+tableName+"."+model.getName()+" IS'" + model.getComment() + "'");
		}
	}

	@Override
	public void dropTable(String tableName) {
		String selSql=""+
			"SELECT " +
				"COUNT(*) AMOUNT FROM SYSCAT.TABLES  " +
			"WHERE " +
				"TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "+
				"AND TABNAME = UPPER('"+tableName+"')";
		int rtn=jdbcTemplate.queryForInt(selSql);
		if(rtn>0){
			String sql="drop table " + tableName;
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
		sql+=" AND A.INDNAME = '"+indexName+"' ";
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql, indexRowMapper);
		List<BpmFormTableIndex> indexList = mergeIndex(indexes);
		if(BeanUtils.isEmpty(indexList)){
			return null;
		}else{
			BpmFormTableIndex index = indexList.get(0);
			index.setIndexDdl(generateIndexDDL(index));
			return index;
		}
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByTable(String tableName) {
		String sql = SQL_GET_ALL_INDEX;
		sql += " AND UPPER(A.TABNAME) = UPPER('"+tableName+"')";
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
			 sql +=" AND UPPER(A.TABNAME) LIKE UPPER('%"+tableName+"%')";
		}
		
		if(!StringUtil.isEmpty(indexName)){
			 sql +=" AND UPPER(A.INDNAME) like UPPER('%"+indexName+"%')";
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
		throw new UnsupportedOperationException("DB2 不支持通过JDBC进行索引重建！");
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
		
		if(!StringUtil.isEmpty(index.getIndexType())){
			if(BpmFormTableIndex.INDEX_TYPE_CLUSTERED.equalsIgnoreCase(index.getIndexType())){
				sql.append(" CLUSTER ");
			}
		}
		
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
		@Override
		public BpmFormTableIndex mapRow(ResultSet rs, int rowNum) throws SQLException {
			BpmFormTableIndex index=new BpmFormTableIndex();
			index.setIndexTable(rs.getString("TABNAME"));
			index.setTableType(BpmFormTableIndex.TABLE_TYPE_TABLE);
			index.setIndexName(rs.getString("INDNAME"));
			// set unique type
			String uniqueRule=rs.getString("UNIQUERULE").trim();
			if("U".equalsIgnoreCase(uniqueRule)||"P".equalsIgnoreCase(uniqueRule)){
				index.setUnique(true);
			}
			// set primary index
			if("P".equalsIgnoreCase(uniqueRule)){
				index.setPkIndex(true);
			}
			// set index type
			String indexType=rs.getString("INDEXTYPE").trim();
			if("CLUS".equalsIgnoreCase(indexType)){
				index.setIndexType(BpmFormTableIndex.INDEX_TYPE_CLUSTERED);
			}else if("REG".equalsIgnoreCase(indexType)){
				index.setIndexType(BpmFormTableIndex.INDEX_TYPE_REG);
			}else if("DIM".equalsIgnoreCase(indexType)){
				index.setIndexType(BpmFormTableIndex.INDEX_TYPE_DIM);
			}else if("BLOK".equalsIgnoreCase(indexType)){
				index.setIndexType(BpmFormTableIndex.INDEX_TYPE_BLOK);
			}
			index.setIndexComment(rs.getString("REMARKS"));
			List<String> indexFields=new ArrayList<String>();
			indexFields.add(rs.getString("COLNAME"));
			index.setIndexFields(indexFields);
			//set index ddl
			index.setIndexDdl(generateIndexDDL(index));
			return index;
		}
	};
	
	/**
	 * 检查表是否存在
	 */
	@Override
	public boolean isTableExist(String tableName) {
		String selSql=""+
				"SELECT " +
					"COUNT(*) AMOUNT FROM SYSCAT.TABLES  " +
				"WHERE " +
					"TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) "+
					"AND TABNAME = UPPER('"+tableName+"')";
			int rtn=jdbcTemplate.queryForInt(selSql);
			return rtn>0?true:false;
					
	}
}
