package com.hotent.core.table.colmap;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hotent.core.table.ColumnModel;
import com.hotent.core.util.StringUtil;

/**
 * mysql  列的元数据到columnmodel的映射。
 * 用于从数据库元数据读取到列对象。
 * @author ray
 *
 */
public class SqlServerColumnMap implements RowMapper<ColumnModel> {

	@Override
	public ColumnModel mapRow(ResultSet rs, int row) throws SQLException {
		ColumnModel column=new ColumnModel();

		String name=rs.getString("NAME");
		String is_nullable=rs.getString("IS_NULLABLE");
		String data_type=rs.getString("TYPENAME");
		String length=rs.getString("LENGTH");
		String precisions=rs.getString("PRECISION");
		String scale=rs.getString("SCALE");
		String tableName=rs.getString("TABLE_NAME");
		String comments=rs.getString("DESCRIPTION");
		int isPK=rs.getInt("IS_PK");
		
		int iLength=StringUtil.isEmpty(length)?0:Integer.parseInt(length);
		int iPrecisions=StringUtil.isEmpty(precisions)?0:Integer.parseInt(precisions);
		int iScale=StringUtil.isEmpty(scale)?0:Integer.parseInt(scale);
		
		column.setName(name);
		boolean isNull=is_nullable.equals("1");
		column.setIsNull(isNull);
		column.setTableName(tableName);
		column.setComment(comments);
		column.setIsPk(isPK==1?true:false);
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

			
		if(dbtype.equals("int")) 
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.equals("bigint")) 
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}

		
		if( dbtype.equals("smallint") )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.equals("tinyint")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.equals("bit")) 
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			return;
		}
		
		if(dbtype.equals("decimal")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.equals("numeric")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.equals("real")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			return;
		}
		
		if(dbtype.equals("float")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
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
		
		
		if(dbtype.equals("varchar")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
			
			return;
		}
		
		if(dbtype.equals("nchar")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
			return;
		}
		
		if(dbtype.equals("nvarchar")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_VARCHAR);
			columnModel.setCharLen(length);
			
			return;
		}
		
		
		
		if(dbtype.startsWith("datetime"))
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_DATE);
		
			return;
		}
		
		
		if(dbtype.endsWith("money")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_NUMBER);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.endsWith("smallmoney")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			columnModel.setIntLen(precision);
			columnModel.setDecimalLen(scale);
			return;
		}
		
		if(dbtype.endsWith("text")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			columnModel.setCharLen(length);
			return;
		}
		
		if(dbtype.endsWith("ntext")  )
		{
			columnModel.setColumnType(ColumnModel.COLUMNTYPE_CLOB);
			columnModel.setCharLen(length);
			return;
		}

	
	}

}
