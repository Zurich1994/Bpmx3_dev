package com.hotent.core.mybatis.dialect;

import com.hotent.core.mybatis.Dialect;

/**
 * mysql 方言处理。
 * @author badqiu
 */
public class MySQLDialect extends Dialect
{
	public boolean supportsLimitOffset()
	{
		return true;
	}

	public boolean supportsLimit()
	{
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		if (offset > 0)
		{
			return sql + " limit " + offsetPlaceholder + "," + limitPlaceholder;
		}
		else
		{
			return sql + " limit " + limitPlaceholder;
		}
	}

	
}
