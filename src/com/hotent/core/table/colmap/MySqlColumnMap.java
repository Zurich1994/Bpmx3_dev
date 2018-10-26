package com.hotent.core.table.colmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.table.ColumnModel;
import com.hotent.core.table.impl.MySqlTableMeta;
import com.hotent.core.util.StringUtil;

/**
 * mysql  列的元数据到columnmodel的映射。
 * 用于从数据库元数据读取到列对象。
 * @author ray
 *
 */
public class MySqlColumnMap implements RowMapper<ColumnModel> {

	@Override
	public ColumnModel mapRow(ResultSet rs, int row) throws SQLException {
		ColumnModel column=new ColumnModel();

		String name=rs.getString("column_name");
		String is_nullable=rs.getString("is_nullable");
		String data_type=rs.getString("data_type");
		String length=rs.getString("length");
		String precisions=rs.getString("precisions");
		String scale=rs.getString("scale");
		String column_key=rs.getString("column_key");
		String column_comment=rs.getString("column_comment");
		String table_name=rs.getString("table_name");
		column_comment=MySqlTableMeta.getComments(column_comment, name);
		int iLength;
		try{
			iLength=StringUtil.isEmpty(length)?0:Integer.parseInt(length);
		}catch (NumberFormatException e) {
			iLength=0;
		}
		int iPrecisions=StringUtil.isEmpty(precisions)?0:Integer.parseInt(precisions);
		int iScale=StringUtil.isEmpty(scale)?0:Integer.parseInt(scale);
		
		column.setName(name);
		column.setTableName(table_name);
		column.setComment(column_comment);
		if(StringUtil.isNotEmpty(column_key) && "PRI".equals(column_key))
			column.setIsPk(true);
		boolean isNull=is_nullable.equals("YES");
		column.setIsNull(isNull);
		
		setType(data_type,iLength,iPrecisions,iScale,column);
		
		return column;
	}
	

	/**
	 * 设置列类型
	 * @param dbtype
	 * @param length
	 * @param precision
	 * @param scale
	 * @param columnModel
	 */
	private void setType(String dbtype,int length,int precision,int scale,ColumnModel columnModel)
	{
		if(dbtype.equals("bigint")) 
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(19);
			columnModel.setDecimalLen(0);
			return;
		}
			
		if(dbtype.equals("int")) 
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(10);
			columnModel.setDecimalLen(0);
			return;
		}
		
		if(dbtype.equals("mediumint")) 
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(7);
			columnModel.setDecimalLen(0);
			return;
		}

		
		if( dbtype.equals("smallint") )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(5);
			columnModel.setDecimalLen(0);
			return;
		}
		
		if(dbtype.equals("tinyint")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(3);
			columnModel.setDecimalLen(0);
			return;
		}
		
		if(dbtype.equals("decimal")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision-scale);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.equals("double")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(18);
			columnModel.setDecimalLen(4);
			return;
		}
		
		if(dbtype.equals("float")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(8);
			columnModel.setDecimalLen(4);
			return;
		}
	
		
		if(dbtype.equals("varchar")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
			
			return;
		}
		
		if(dbtype.equals("char")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
			return;
		}
		
		
		
		if(dbtype.startsWith("date"))
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
		
			return;
		}
		
		if(dbtype.startsWith("time"))
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
		
			return;
		}
		
		
		if(dbtype.endsWith("text")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			columnModel.setCharLen(65535);
			return;
		}
		if(dbtype.endsWith("blob")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			columnModel.setCharLen(65535);
			return;
		}
		if(dbtype.endsWith("clob")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			columnModel.setCharLen(65535);
			return;
		}
		

	
	}

}
