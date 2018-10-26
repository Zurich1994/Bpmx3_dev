package com.hotent.core.table.colmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.table.ColumnModel;
import com.hotent.core.util.StringUtil;

/**
 * DB2 RowMapper
 * @author Raise
 *
 */
public class DB2ColumnMap implements RowMapper<ColumnModel> {
	@Override
	public ColumnModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ColumnModel column=new ColumnModel();
		String tabName=rs.getString("TAB_NAME");
		String colName=rs.getString("COL_NAME");
		String colType = rs.getString("COL_TYPE");
		String colComment = rs.getString("COL_COMMENT");
		String nullable=rs.getString("IS_NULLABLE");
		String length=rs.getString("LENGTH");
		String scale=rs.getString("SCALE");
		String keySeq=rs.getString("KEYSEQ");
		int iLength=string2Int(length,0);
		int iPrecision=iLength;
		int iScale=string2Int(scale,0);
		int iKeySeq=string2Int(keySeq,0);

		column.setTableName(tabName);
		column.setName(colName);
		column.setComment(colComment);
		//is nullable
		column.setIsNull("Y".equalsIgnoreCase(nullable)?true:false);
		//is primary key
		column.setIsPk(iKeySeq>0?true:false);
		//column.setColumnType(colType);
		setType(colType,iLength,iPrecision,iScale,column);
		return column;
	}
	
	/**
	 * String到Int的类型转换，如果转换失败，返回默认值。
	 * @param str 要转换的String类型的值
	 * @param def 默认值
	 * @return
	 */
	private int string2Int(String str,int def){
		int retval=def;
		if(StringUtil.isNotEmpty(str)){
			try{
				retval=Integer.parseInt(str);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retval;
	}

	/**
	 * 设置列类型
	 * @param dbtype
	 * @param length
	 * @param precision
	 * @param scale
	 * @param columnModel
	 */
	private void setType(String type,int length,int precision,int scale,ColumnModel columnModel)
	{
		String dbtype=type.toLowerCase();
		if (dbtype.endsWith("bigint")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(19);
			columnModel.setDecimalLen(0);
		} else if (dbtype.endsWith("blob")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
		} else if (dbtype.endsWith("character")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
			columnModel.setDecimalLen(0);
		} else if (dbtype.endsWith("clob")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
		} else if (dbtype.endsWith("date")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
		} else if (dbtype.endsWith("dbclob")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
		} else if (dbtype.endsWith("decimal")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision-scale);
			columnModel.setDecimalLen(scale);
		} else if (dbtype.endsWith("double")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision-scale);
			columnModel.setDecimalLen(scale);
		} else if (dbtype.endsWith("graphic")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
		} else if (dbtype.endsWith("integer")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(10);
			columnModel.setDecimalLen(0);
		} else if (dbtype.endsWith("long varchar")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
		} else if (dbtype.endsWith("long vargraphic")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
		} else if (dbtype.endsWith("real")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(length);
			columnModel.setDecimalLen(scale);
		} else if (dbtype.endsWith("smallint")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(5);
			columnModel.setDecimalLen(0);
		} else if (dbtype.endsWith("time")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
		} else if (dbtype.endsWith("timestamp")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
		} else if (dbtype.endsWith("varchar")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
		} else if (dbtype.endsWith("vargraphic")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
		} else if (dbtype.endsWith("xml")) {
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
		}
	}

}
