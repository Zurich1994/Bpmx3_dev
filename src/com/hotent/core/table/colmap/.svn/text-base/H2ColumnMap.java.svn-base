package com.hotent.core.table.colmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.table.ColumnModel;
import com.hotent.core.util.StringUtil;

/**
 * mysql 列的元数据到columnmodel的映射。 用于从数据库元数据读取到列对象。
 * 
 * @author ray
 * 
 */
public class H2ColumnMap implements RowMapper<ColumnModel> {

	@Override
	public ColumnModel mapRow(ResultSet rs, int row) throws SQLException {
		ColumnModel column = new ColumnModel();

		String name = rs.getString("COLUMN_NAME");
		String is_nullable = rs.getString("IS_NULLABLE");
		String data_type = rs.getString("TYPE_NAME");
		String length = rs.getString("LENGTH");
		String precisions = rs.getString("PRECISIONS");
		String scale = rs.getString("SCALE");
		String column_list = rs.getString("COLUMN_LIST");
		String column_comment = rs.getString("REMARKS");
		String table_name = rs.getString("TABLE_NAME");
		int iLength;
		try {
			iLength = StringUtil.isEmpty(length) ? 0 : Integer.parseInt(length);
		} catch (NumberFormatException e) {
			iLength = 0;
		}
		int iPrecisions = StringUtil.isEmpty(precisions) ? 0 : Integer.parseInt(precisions);
		int iScale = StringUtil.isEmpty(scale) ? 0 : Integer.parseInt(scale);

		column.setName(name);
		column.setTableName(table_name);
		column.setComment(column_comment);

		boolean isPkColumn = false;
		if (StringUtil.isNotEmpty(column_list)) {
			String[] pkColumns = column_list.split(",");
			for (String pkColumn : pkColumns) {
				if (name.trim().equalsIgnoreCase(pkColumn.trim())) {
					isPkColumn = true;
					break;
				}
			}
		}
		column.setIsPk(isPkColumn);

		boolean isNull = is_nullable.equals("YES");
		column.setIsNull(isNull);

		setType(data_type, iLength, iPrecisions, iScale, column);

		return column;
	}

	/**
	 * 设置列类型
	 * 
	 * @param dbtype
	 * @param length
	 * @param precision
	 * @param scale
	 * @param columnModel
	 */
	private void setType(String dbtype, int length, int precision, int scale, ColumnModel columnModel) {
		dbtype = dbtype.toUpperCase();
		if (dbtype.equals("BIGINT")) { // int
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(19);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("INT8")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(19);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("INT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(10);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("INTEGER")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(10);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("MEDIUMINT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(7);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("INT4")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(5);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("SIGNED")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(3);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("TINYINT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(2);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("SMALLINT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(5);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("INT2")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(5);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("YEAR")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(5);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("IDENTITY")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_INT);
			columnModel.setIntLen(5);
			columnModel.setDecimalLen(0);
			return;
		} else if (dbtype.equals("DECIMAL")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision - scale);
			columnModel.setDecimalLen(scale);
			return;
		} else if (dbtype.equals("DOUBLE")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			return;
		} else if (dbtype.equals("FLOAT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			return;
		} else if (dbtype.equals("FLOAT4")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			return;
		} else if (dbtype.equals("FLOAT8")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			return;
		} else if (dbtype.equals("REAL")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			return;
		} else if (dbtype.equals("TIME")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
			return;
		} else if (dbtype.equals("DATE")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
			return;
		} else if (dbtype.equals("DATETIME")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
			return;
		} else if (dbtype.equals("SMALLDATETIME")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
			return;
		} else if (dbtype.equals("TIMESTAMP")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
			return;
		} else if (dbtype.equals("BINARY")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("VARBINARY")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("LONGVARBINARY")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("RAW")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("BYTEA")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("TINYBLOB")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("MEDIUMBLOB")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("LONGBLOB")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("IMAGE")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("OID")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("CLOB")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("TINYTEXT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("TEXT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("MEDIUMTEXT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("LONGTEXT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("NTEXT")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else if (dbtype.equals("NCLOB")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		} else {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
		}
	}

}
