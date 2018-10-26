package com.hotent.core.mybatis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hotent.core.table.ITableOperator;
import com.hotent.core.table.SqlTypeConst;
import com.hotent.core.util.AppUtil;

public class IbatisSql {    
    private String sql;//运行期的sql，带?    
    private Object[] parameters;//;//运行期的参数，与?相匹配    
    private Class resultClass;//<select id="XXX" resultType="ZZZ">中的resultType    
   
    public Class getResultClass() {    
        return resultClass;    
    }    
   
    public void setResultClass(Class resultClass) {    
        this.resultClass = resultClass;    
    }    
   
    public void setSql(String sql) {    
        this.sql = sql;    
    }    
   
    public String getSql() {    
        return sql;    
    }    
   
    public void setParameters(Object[] parameters) {    
        this.parameters = parameters;    
    }    
   
    public Object[] getParameters() {    
        return parameters;    
    }    
    
    /**
     * 取得返回统计数量的sql。
     * @return
     */
    public String getCountSql()
    {
		String sqlCount = sql;
		ITableOperator tableOperator = (ITableOperator) AppUtil
				.getBean(ITableOperator.class);
		if (tableOperator.getDbType().equals(SqlTypeConst.SQLSERVER)) {
			sqlCount = sqlCount.trim();
			Pattern pattern = Pattern.compile("^SELECT(\\s+(ALL|DISTINCT))?",
					Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sqlCount);
			if (matcher.find()) {
				String matStr = matcher.group();
				sqlCount = sqlCount.toUpperCase().replaceFirst(matStr.toUpperCase(),
						matStr.toUpperCase() + " TOP 100 PERCENT");
			} else {
				throw new UnsupportedOperationException("SQL语句拼接出现错误！");
			}
		}
		sqlCount = "select count(*) amount from (" + sqlCount + ") A";
		return sqlCount;
    }
    
   
}   




