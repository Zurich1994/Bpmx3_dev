package com.hotent.core.mybatis;



import java.util.List;

import com.hotent.core.web.query.entity.FieldSort;



/**
 * 类似hibernate的Dialect,但只精简出分页部分
 * @author badqiu
 * @author miemiedev
 */
public class Dialect {
	
    public boolean supportsLimit(){
    	return false;
    }

    public boolean supportsLimitOffset() {
    	return supportsLimit();
    }
    
    /**
     * 
     *将sql变成分页sql语句,直接使用offset,limit的值作为占位符.</br>
     *源代码为: getLimitString(sql,offset,String.valueOf(offset),limit,String.valueOf(limit))
     * @param sql		sql语句
     * @param offset	记录编号
     * @param limit		页大小
     * @return 
     * String
     * @exception 
     * @since  1.0.0
     */
    public String getLimitString(String sql, int offset, int limit) {
    	return getLimitString(sql,offset,Integer.toString(offset),limit,Integer.toString(limit));
    }
    
    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符(placeholder)替换.
     * <pre>
     * 如mysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 将返回
     * select * from user limit :offset,:limit
     * </pre>
     * @return 包含占位符的分页sql
     */
    public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit,String limitPlaceholder) {
    	throw new UnsupportedOperationException("paged queries not supported");
    }

    /**
     * 将sql转换为总记录数SQL
     * @param sql SQL语句
     * @return 总记录数的sql
     */
    public String getCountSql(String sql){
        return "select count(1) from (" + sql + ") tmp_count";
    }

    
    /**
     * 将sql转换为带排序的SQL
     * @param sql		SQL语句
     * @param orders	排序对象
     * @return 
     * String
     * @exception 
     * @since  1.0.0
     */
    public String getSortString(String sql, List<FieldSort> orders){
        if(orders == null || orders.isEmpty()){
            return sql;
        }

        StringBuffer buffer = new StringBuffer("select * from (").append(sql).append(") temp_order order by ");
        for(FieldSort order : orders){
            if(order != null){
                buffer.append(order.toString())
                        .append(", ");
            }
        }
        buffer.delete(buffer.length()-2, buffer.length());
        return buffer.toString();
    }
    
}
