package com.hotent.core.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.mybatis.Dialect;
import com.hotent.core.page.PageBean;
import com.hotent.core.table.AbstractTableOperator;
import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.TableModel;
import com.hotent.core.util.StringUtil;
import com.hotent.platform.model.form.BpmFormTableIndex;

public class SqlserverTableOperator extends AbstractTableOperator {

	

	

	@Override
	public void createTable(TableModel model) throws SQLException {

		List<ColumnModel> columnList = model.getColumnList();

		// 建表语句
		StringBuffer createTableSql = new StringBuffer();
		// 主键字段
		String pkColumn = null;

		// List<String> fkList = new ArrayList<String>();
		// 例注释
		List<String> columnCommentList = new ArrayList<String>();
		// 建表开始
		createTableSql.append("CREATE TABLE " + model.getName() + " (\n");
		for (int i = 0; i < columnList.size(); i++) {
			// 建字段
			ColumnModel cm = columnList.get(i);
			createTableSql.append("    ").append(cm.getName()).append("    ");
			createTableSql.append(getColumnType(cm.getColumnType(),
					cm.getCharLen(), cm.getIntLen(), cm.getDecimalLen()));
			createTableSql.append(" ");

			// alter table Table_2 add a datetime default getdate() not null ;
			if (StringUtil.isNotEmpty(cm.getDefaultValue())) {
				createTableSql.append(" DEFAULT " + cm.getDefaultValue());
			}

			// 非空
			if (!cm.getIsNull()) {
				createTableSql.append(" NOT NULL ");
			}
			// 主键
			if (cm.getIsPk()) {
				if (pkColumn == null) {
					pkColumn = cm.getName();
				} else {
					pkColumn += "," + cm.getName();
				}
			}
			// if (cm.getIsFk()) {
			// fkList.add("    FOREIGN KEY (" + cm.getName() + ") REFERENCES " +
			// cm.getFkRefTable() + "(" + cm.getFkRefColumn() + ")");
			//
			// }
			// 字段注释
			if (cm.getComment() != null && cm.getComment().length() > 0) {
				StringBuffer comment = new StringBuffer(
						"EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'");
				comment.append(cm.getComment())
						.append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'")
						.append(model.getName())
						.append("', @level2type=N'COLUMN', @level2name=N'")
						.append(cm.getName()).append("'");
				columnCommentList.add(comment.toString());
			}
			createTableSql.append(",\n");
		}
		// 建主键
		if (pkColumn != null) {
			createTableSql.append("    CONSTRAINT PK_").append(model.getName())
					.append(" PRIMARY KEY (").append(pkColumn).append(")");
		}
		// 建外键
		// for (String fk : fkList) {
		// createTableSql.append(",\n" + fk);
		// }

		// 建表结束
		createTableSql.append("\n)");
		jdbcTemplate.execute(createTableSql.toString());

		// 表注释
		if (model.getComment() != null && model.getComment().length() > 0) {
			StringBuffer tableComment = new StringBuffer(
					"EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'");
			tableComment
					.append(model.getComment())
					.append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'")
					.append(model.getName()).append("'");
			jdbcTemplate.execute(tableComment.toString());
		}
		for (String columnComment : columnCommentList) {
			jdbcTemplate.execute(columnComment);
		}

	}

	@Override
	public void updateTableComment(String tableName, String comment)
			throws SQLException {
		// 假如不存在表的注释 ,会抛出异常
		StringBuffer commentSql = new StringBuffer(
				"EXEC sys.sp_updateextendedproperty @name=N'MS_Description', @value=N'");
		commentSql
				.append(comment)
				.append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'")
				.append(tableName).append("'");

		jdbcTemplate.execute(commentSql.toString());

	}

	@Override
	public void addColumn(String tableName, ColumnModel model)
			throws SQLException {
		StringBuffer alterSql = new StringBuffer();
		alterSql.append("ALTER TABLE ").append(tableName);
		alterSql.append(" ADD ");
		alterSql.append(model.getName()).append(" ");
		alterSql.append(getColumnType(model.getColumnType(),
				model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
		// 设置默认值
		if (StringUtil.isNotEmpty(model.getDefaultValue())) {
			alterSql.append(" DEFAULT " + model.getDefaultValue());
		}

//		if (!model.getIsNull()) {
//			alterSql.append(" NOT NULL ");
//		}
		alterSql.append("\n");
		jdbcTemplate.execute(alterSql.toString());
		if (model.getComment() != null && model.getComment().length() > 0) {
			StringBuffer comment = new StringBuffer(
					"EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'");
			comment.append(model.getComment())
					.append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'")
					.append(tableName)
					.append("', @level2type=N'COLUMN', @level2name=N'")
					.append(model.getName()).append("'");
			jdbcTemplate.execute(comment.toString());
		}

	}

	@Override
	public void updateColumn(String tableName, String columnName,
			ColumnModel model) throws SQLException {
		// 修改列名
		if (!columnName.equals(model.getName())) {
			StringBuffer modifyName = new StringBuffer("EXEC sp_rename '");
			modifyName.append(tableName).append(".[").append(columnName)
					.append("]','").append(model.getName())
					.append("', 'COLUMN'");
			jdbcTemplate.execute(modifyName.toString());
		}

		// 修改列的大小,此处不修改列的类型,若修改列的类型则在前面部分已抛出异常
		StringBuffer alterSql = new StringBuffer();
		alterSql.append("ALTER TABLE ").append(tableName);
		alterSql.append(" ALTER COLUMN " + model.getName()).append(" ");
		alterSql.append(getColumnType(model.getColumnType(),
				model.getCharLen(), model.getIntLen(), model.getDecimalLen()));
		if (!model.getIsNull()) {
			alterSql.append(" NOT NULL ");
		}

		jdbcTemplate.execute(alterSql.toString());

		// 修改注释
		if (model.getComment() != null && model.getComment().length() > 0) {
			// 更新字段注释假如不存在该列的注释 ,会抛出异常
			StringBuffer comment = new StringBuffer(
					"EXEC sys.sp_updateextendedproperty @name=N'MS_Description', @value=N'");
			comment.append(model.getComment())
					.append("' ,@level0type=N'SCHEMA', @level0name=N'dbo', @level1type=N'TABLE', @level1name=N'")
					.append(tableName)
					.append("', @level2type=N'COLUMN', @level2name=N'")
					.append(model.getName()).append("'");
			jdbcTemplate.execute(comment.toString());
		}
	}

	private String getColumnType(String columnType, int charLen, int intLen,
			int decimalLen) {
		if (ColumnModel.COLUMNTYPE_VARCHAR.equals(columnType)) {
			return "VARCHAR(" + charLen + ')';
		} else if (ColumnModel.COLUMNTYPE_NUMBER.equals(columnType)) {
			return "NUMERIC(" + (intLen + decimalLen) + "," + decimalLen + ")";
		} else if (ColumnModel.COLUMNTYPE_DATE.equals(columnType)) {
			return "DATETIME";
		} else if (ColumnModel.COLUMNTYPE_INT.equals(columnType)) {
			return "NUMERIC(" + intLen + ")";
		} else if (ColumnModel.COLUMNTYPE_CLOB.equals(columnType)) {
			return "TEXT";
		} else {
			return "";
		}
	}

	@Override
	public void dropTable(String tableName) {
		String sql = "IF OBJECT_ID(N'" + tableName
				+ "', N'U') IS NOT NULL  DROP TABLE " + tableName;
		jdbcTemplate.execute(sql);
	}

	/**
	 * 添加外键。
	 */
	public void addForeignKey(String pkTableName, String fkTableName,
			String pkField, String fkField) {
		String sql = "  ALTER TABLE " + fkTableName + " ADD CONSTRAINT fk_"
				+ fkTableName + " FOREIGN KEY (" + fkField + ") REFERENCES "
				+ pkTableName + " (" + pkField + ")   ON DELETE CASCADE";
		jdbcTemplate.execute(sql);
	}

	@Override
	public void dropForeignKey(String tableName, String keyName) {
		String sql = "ALTER   TABLE   " + tableName + "   DROP   CONSTRAINT  "
				+ keyName;
		jdbcTemplate.execute(sql);
	}

	@Override
	public String getDbType() {
		// TODO Auto-generated method stub
		return this.dbType;
	}

	@Override
	public void createIndex(BpmFormTableIndex index) throws SQLException {
		String sql = generateIndexDDL(index);
		jdbcTemplate.execute(sql);
	}

	@Override
	public void dropIndex(String tableName, String indexName) {
		String sql = "DROP INDEX "+indexName+" ON "+tableName;
		jdbcTemplate.execute(sql);
	}

	@Override
	public BpmFormTableIndex getIndex(String tableName, String indexName) {
		String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,"
				+ "OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, "
				+ "IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, "
				+ "IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, "
				+ "COL.NAME AS COLUMN_NAME "
				+ "FROM  SYS.INDEXES  IDX  "
				+ "JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  "
				+ "JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID "
				+ "JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID "
				+ "WHERE OBJ.NAME ='" + tableName + "' "
				+ "AND IDX.NAME ='"+indexName+"'";
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql,
				new RowMapper<BpmFormTableIndex>() {

					public BpmFormTableIndex mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						BpmFormTableIndex index = new BpmFormTableIndex();
						List<String> columns = new ArrayList<String>();
						index.setIndexName(rs.getString("INDEX_NAME"));
						index.setIndexType(mapIndexType(rs.getInt("INDEX_TYPE")));
						index.setIndexTable(rs.getString("TABLE_NAME"));
						index.setTableType(mapTableType(rs.getString("TABLE_TYPE")));
						index.setUnique(mapIndexUnique(rs.getInt("IS_UNIQUE")));
						index.setPkIndex(mapPKIndex(rs.getInt("IS_PK_INDEX")));
						columns.add(rs.getString("COLUMN_NAME"));
						index.setIndexStatus(mapIndexStatus(rs.getInt("IS_DISABLED")));
						index.setIndexFields(columns);
						return index;
					}
				});

		// indexes中，第一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = new ArrayList<BpmFormTableIndex>();
		for (BpmFormTableIndex index : indexes) {
			for (BpmFormTableIndex index1 : indexList) {
				if (index.getIndexName().equals(index1.getIndexName())
						&& index.getIndexTable().equals(index1.getIndexTable())) {
					index1.getIndexFields().add(index.getIndexFields().get(0));
					continue;
				}
			}
			indexList.add(index);
		}

		for (BpmFormTableIndex index : indexList) {
			index.setIndexDdl(generateIndexDDL(index));
		}
		
		if(indexList.size()>0){
			return indexList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByTable(String tableName) {
		String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,"
				+ "OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, "
				+ "IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, "
				+ "IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, "
				+ "COL.NAME AS COLUMN_NAME "
				+ "FROM  SYS.INDEXES  IDX  "
				+ "JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  "
				+ "JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID "
				+ "JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID "
				+ "WHERE OBJ.NAME ='" + tableName + "'";
		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql,
				new RowMapper<BpmFormTableIndex>() {

					public BpmFormTableIndex mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						BpmFormTableIndex index = new BpmFormTableIndex();
						List<String> columns = new ArrayList<String>();
						index.setIndexName(rs.getString("INDEX_NAME"));
						index.setIndexType(mapIndexType(rs.getInt("INDEX_TYPE")));
						index.setIndexTable(rs.getString("TABLE_NAME"));
						index.setTableType(mapTableType(rs.getString("TABLE_TYPE")));
						index.setUnique(mapIndexUnique(rs.getInt("IS_UNIQUE")));
						index.setPkIndex(mapPKIndex(rs.getInt("IS_PK_INDEX")));
						columns.add(rs.getString("COLUMN_NAME"));
						index.setIndexStatus(mapIndexStatus(rs.getInt("IS_DISABLED")));
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

		for (BpmFormTableIndex index : indexList) {
			index.setIndexDdl(generateIndexDDL(index));
		}

		return indexList;
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL) {
		return getIndexesByFuzzyMatching(tableName, indexName, getDDL, null);
	}

	@Override
	public List<BpmFormTableIndex> getIndexesByFuzzyMatching(String tableName,
			String indexName, Boolean getDDL, PageBean pageBean) {
		String sql = "SELECT IDX.NAME AS INDEX_NAME,IDX.TYPE AS INDEX_TYPE,"
				+ "OBJ.NAME AS TABLE_NAME,OBJ.TYPE AS TABLE_TYPE, "
				+ "IDX.IS_DISABLED AS IS_DISABLED,IDX.IS_UNIQUE AS IS_UNIQUE, "
				+ "IDX.IS_PRIMARY_KEY AS IS_PK_INDEX, "
				+ "COL.NAME AS COLUMN_NAME "
				+ "FROM  SYS.INDEXES  IDX  "
				+ "JOIN SYS.OBJECTS OBJ ON IDX.OBJECT_ID=OBJ.OBJECT_ID  "
				+ "JOIN SYS.INDEX_COLUMNS IDC ON OBJ.OBJECT_ID=IDC.OBJECT_ID AND IDX.INDEX_ID=IDC.INDEX_ID "
				+ "JOIN SYS.COLUMNS COL ON COL.OBJECT_ID=IDC.OBJECT_ID AND COL.COLUMN_ID = IDC.COLUMN_ID "
				+ "WHERE 1=1";
		if (!StringUtil.isEmpty(indexName)) {
			sql += " AND IDX.NAME LIKE '%" + indexName + "%'";
		}
		if (!StringUtil.isEmpty(tableName)) {
			sql += " AND OBJ.NAME LIKE '%" + tableName + "%' ";
		}

		if (pageBean != null) {
			int currentPage = pageBean.getCurrentPage();
			int pageSize = pageBean.getPageSize();
			int offset = (currentPage - 1) * pageSize;
			String totalSql = dialect.getCountSql(sql);
			int total = jdbcTemplate.queryForInt(totalSql);
			sql = dialect.getLimitString(sql, offset, pageSize);
			pageBean.setTotalCount(total);
		}

		List<BpmFormTableIndex> indexes = jdbcTemplate.query(sql,
				new RowMapper<BpmFormTableIndex>() {
					public BpmFormTableIndex mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						BpmFormTableIndex index = new BpmFormTableIndex();
						List<String> columns = new ArrayList<String>();
						index.setIndexName(rs.getString("INDEX_NAME"));
						index.setIndexType(mapIndexType(rs.getInt("INDEX_TYPE")));
						index.setIndexTable(rs.getString("TABLE_NAME"));
						index.setTableType(mapTableType(rs
								.getString("TABLE_TYPE")));
						index.setUnique(mapIndexUnique(rs.getInt("IS_UNIQUE")));
						index.setPkIndex(mapPKIndex(rs.getInt("IS_PK_INDEX")));
						columns.add(rs.getString("COLUMN_NAME"));
						index.setIndexFields(columns);
						index.setIndexStatus(mapIndexStatus(rs.getInt("IS_DISABLED")));
						return index;
					}
				});

		// indexes中，第一索引列，对就一元素。需要进行合并。
		List<BpmFormTableIndex> indexList = new ArrayList<BpmFormTableIndex>();
		for (BpmFormTableIndex index : indexes) {
			for (BpmFormTableIndex index1 : indexList) {
				if (index.getIndexName().equals(index1.getIndexName())
						&& index.getIndexTable().equals(index1.getIndexTable())) {
					index1.getIndexFields().add(index.getIndexFields().get(0));
					continue;
				}
			}
			indexList.add(index);
		}
		if (getDDL) {
			for (BpmFormTableIndex index : indexList) {
				index.setIndexDdl(generateIndexDDL(index));
			}
		}
		return indexList;
	}

	@Override
	public void rebuildIndex(String tableName, String indexName) {
		String sql = "DBCC DBREINDEX ('"+tableName+"','"+indexName+"',80)";
		jdbcTemplate.execute(sql);

	}

	@Override
	public List<String> getPKColumns(String tableName) throws SQLException {
		String sql = "SELECT C.COLUMN_NAME COLUMN_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS PK ,INFORMATION_SCHEMA.KEY_COLUMN_USAGE C "
				+ "WHERE 	PK.TABLE_NAME = '%S' "
				+ "AND	CONSTRAINT_TYPE = 'PRIMARY KEY' "
				+ "AND	C.TABLE_NAME = PK.TABLE_NAME "
				+ "AND	C.CONSTRAINT_NAME = PK.CONSTRAINT_NAME ";
		sql = String.format(sql, tableName);
		
		List<String> columns = jdbcTemplate.query(sql, new RowMapper<String>(){
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				String column = rs.getString(1);
				return column;
			}
		});
		return columns;
	}

	/**
	 * 生成BpmFormTableIndex对象对应的DDL语句
	 * 
	 * @param index
	 * @return
	 */
	private String generateIndexDDL(BpmFormTableIndex index) {
		StringBuffer sql = new StringBuffer();
		sql.append("CREATE ");
		// 是否惟一
		if (index.isUnique()) {
			sql.append(" UNIQUE ");
		}
		// 索引类型
		if (!StringUtil.isEmpty(index.getIndexType())) {
			if (index.getIndexType().equalsIgnoreCase("CLUSTERED")) {
				sql.append(" CLUSTERED ");
			}
		}
		sql.append(" INDEX ");
		// 索引名
		sql.append(index.getIndexName());
		// 表名
		sql.append(" ON ");
		sql.append(index.getIndexTable());
		// 列
		sql.append(" (");
		for (String field : index.getIndexFields()) {
			sql.append(field);
			sql.append(",");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");

		return sql.toString();
	}

	private String mapTableType(String type) {
		type=type.trim();
		String tableType = null;
		if (type.equalsIgnoreCase("U")){
			tableType = BpmFormTableIndex.TABLE_TYPE_TABLE;
		}else if (type.equalsIgnoreCase("V")) {
			tableType = BpmFormTableIndex.TABLE_TYPE_VIEW;
		}
		return tableType;
		
	}

	/**
	 * 0 = Heap 1 = Clustered 2 = Nonclustered 3 = XML 4 = Spatial
	 * 
	 * @param type
	 * @return
	 */
	private String mapIndexType(int type) {
		String indexType = null;
		switch (type) {
		case 0:
			indexType = BpmFormTableIndex.INDEX_TYPE_HEAP;
			break;
		case 1:
			indexType = BpmFormTableIndex.INDEX_TYPE_CLUSTERED;
			break;
		case 2:
			indexType = BpmFormTableIndex.INDEX_TYPE_NONCLUSTERED;
			break;
		case 3:
			indexType = BpmFormTableIndex.INDEX_TYPE_XML;
			break;
		case 4:
			indexType = BpmFormTableIndex.INDEX_TYPE_SPATIAL;
			break;
		default:
			break;
		}
		return indexType;
	}

	private boolean mapIndexUnique(int type) {
		boolean indexUnique = false;
		switch (type) {
		case 0:
			indexUnique = false;
			break;
		case 1:
			indexUnique = true;
			break;
		default:
			break;
		}
		return indexUnique;
	}

	private boolean mapPKIndex(int type) {
		boolean pkIndex = false;
		switch (type) {
		case 0:
			pkIndex = false;
			break;
		case 1:
			pkIndex = true;
			break;
		default:
			break;
		}
		return pkIndex;
	}
	
	private String mapIndexStatus(int type) {
		String tableType = null;
		switch (type) {
		case 0:
			tableType = BpmFormTableIndex.INDEX_STATUS_VALIDATE;
			break;
		case 1:
			tableType = BpmFormTableIndex.INDEX_STATUS_INVALIDATE;
			break;
		}
		return tableType;
	}
	
	/**
	 * 检查表是否存在
	 */
	@Override
	public boolean isTableExist(String tableName) {
		String sql="select count(1) from sysobjects where name='"+tableName.toUpperCase()+"'";
		return	jdbcTemplate.queryForInt(sql)>0?true:false;
	}
}
