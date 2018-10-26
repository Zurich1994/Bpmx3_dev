package com.hotent.core.mybatis;



import java.util.Properties;
import java.util.concurrent.Callable;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hotent.core.mybatis.support.PropertiesHelper;


/**
 * 为MyBatis提供基于方言(Dialect)的分页查询的插件
 * 
 * 将拦截Executor.query()方法实现分页方言的插入.
 * @author badqiu
 * @author miemiedev
 *
 */

@Intercepts({@Signature(
		type= Executor.class,
		method = "query",
		args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class OffsetLimitInterceptor implements Interceptor{
    private static Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;

//    static ExecutorService Pool;
    Dialect dialect;
//    boolean asyncTotalCount = false;
    
    private static String getSql(String sql){
    	return sql.trim().replaceAll("(?si)\\s+", " ");
    }
	
	public Object intercept(final Invocation invocation) throws Throwable {
     
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        final RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
    //    final DefaultPage pageBounds = new DefaultPage(rowBounds);
        //开始记录号
        final int offset = rowBounds.getOffset();
        //分页大小
        final int limit = rowBounds.getLimit();
        //页码
       

        final BoundSql boundSql = ms.getBoundSql(parameter);
        final StringBuffer bufferSql = new StringBuffer(boundSql.getSql().trim());
        if(bufferSql.lastIndexOf(";") == bufferSql.length()-1){
            bufferSql.deleteCharAt(bufferSql.length()-1);
        }
        //sql语句。
        String sql = getSql(bufferSql.toString().trim());

        
        //支持分页
        if(dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
        	//是否支持分页
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT);
        }

        queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms,boundSql,sql);
        return invocation.proceed();
	}

    private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql,String sql){
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
			String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
		    String prop = mapping.getProperty();
		    if (boundSql.hasAdditionalParameter(prop)) {
		        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
		    }
		}
		return newBoundSql;
	}
	
	//see: MapperBuilderAssistant
	private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
		
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if(ms.getKeyProperties() != null && ms.getKeyProperties().length !=0){
            StringBuffer keyProperties = new StringBuffer();
            for(String keyProperty : ms.getKeyProperties()){
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length()-1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		
		//setStatementTimeout()
		builder.timeout(ms.getTimeout());
		
		//setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		
		//setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
	    
		//setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		
		return builder.build();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
        //获取数据库类型
        String dbType=propertiesHelper.getProperty("jdbc.dbType");
        //获取方言
        Properties p= propertiesHelper.getStartsWithProperties("Dialect.");
		String dialectClass = p.getProperty(dbType);
		try {
            setDialect((Dialect) Class.forName(dialectClass).newInstance());
		} catch (Exception e) {
			throw new RuntimeException("cannot create dialect instance by dialectClass:"+dialectClass,e);
		}
	}
	
	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

    public void setDialect(Dialect dialect) {
        logger.debug("dialectClass: {} ", dialect.getClass().getName());
        this.dialect = dialect;
    }
    
}
