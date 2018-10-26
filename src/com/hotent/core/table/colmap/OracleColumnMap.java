package com.hotent.core.table.colmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.table.ColumnModel;

/**
 * oracle 列的元数据到columnmodel的映射。
 * 用于从数据库元数据读取到列对象。
 * @author ray
 *
 */
public class OracleColumnMap implements RowMapper<ColumnModel> {

	@Override
	public ColumnModel mapRow(ResultSet rs, int row) throws SQLException {
		ColumnModel column=new ColumnModel();
		String name=rs.getString("NAME");
		String typeName=rs.getString("TYPENAME");
		int length=rs.getInt("LENGTH");
		int precision=rs.getInt("PRECISION");
		int scale=rs.getInt("SCALE");
		boolean isNull=rs.getString("NULLABLE").equals("Y");
		String comments=rs.getString("DESCRIPTION");
		String tableName=rs.getString("TABLE_NAME");
		int isPK=rs.getInt("IS_PK");
		
		column.setName(name);
		column.setComment(comments);
		column.setIsNull(isNull);
		column.setTableName(tableName);
		column.setIsPk(isPK==1?true:false);
		setType(typeName, length, precision, scale, column);
		return column;
	}
	
	/**
	 * 设置列类型。
	 * @param dbtype	数据库类型
	 * @param length	字符串长度
	 * @param precision	整数位
	 * @param scale		小数位
	 * @param column	列对象
	 */
	private void setType(String dbtype,int length,int precision,int scale,ColumnModel column)
	{
		if(dbtype.indexOf("CHAR")>-1){
			column.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			column.setCharLen(length);
			return;
		}
		if(dbtype.equals( "NUMBER")){
			column.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			column.setIntLen(precision-scale);
			column.setDecimalLen(scale);
			int i= precision-scale;
			if(i==0 && scale==0){
				column.setIntLen(38);
			}
			return;
		}
		if(dbtype.equals( "INTEGER")){
			column.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			column.setIntLen(10);
			column.setDecimalLen(0);
			
			return;
		}
		if(dbtype.equals("DATE") || dbtype.indexOf("TIMESTAMP")!=-1){
			column.setColumnType(ColumnModel.COLUMNTYPE_DATE);
			return;
		}
		
		if(dbtype.equals("CLOB")){
			column.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			return;
		}
	}
	
	

}
