/**
 * 描述：TODO
 * 包名：com.hotent.core.db.datasource
 * 文件名：JdbcTemplateUtil.java
 * 作者：User-mailto:chensx@jee-soft.cn
 * 日期2014-8-25-下午2:38:10
 *  2014广州宏天软件有限公司版权所有
 * 
 */
package edu.hrbeu.MDA.DBAccess.datasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.hotent.core.mybatis.Dialect;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.DialectUtil;
import com.hotent.platform.model.system.SysDataSourceL;
import com.hotent.platform.service.system.SysDataSourceLService;

/**
 * <pre>
 * jdbc关于pagebean的查询！！！
 * 注意：里面的alias参数都是数据源的别名！！
 * 构建组：bpm_3.3
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2014-8-25-下午2:38:10
 * 版权：广州宏天软件有限公司版权所有
 * </pre>
 */
public class JdbcTemplateUtil {
	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(JdbcTemplate jdbcTemplate) {
		return new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
	}

	public static NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String alias) {
		try {
			return new NamedParameterJdbcTemplate(DataSourceUtil.getDataSourceByAlias(alias));
		} catch (Exception e) {
			return null;
		}
	}

	public static List getPage(String alias, PageBean pageBean, String sql, RowMapper rowMap) {
		int pageSize = pageBean.getPageSize();
		int offset = pageBean.getFirst();

		Map map = new HashMap();

		Dialect dialect = null;
		try {
			dialect = DialectUtil.getDialectByDataSourceAlias(alias);
		} catch (Exception e) {
			return null;
		}
		String pageSql = dialect.getLimitString(sql, offset, pageSize);
		String totalSql = dialect.getCountSql(sql);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
		namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(alias);
		List list = namedParameterJdbcTemplate.query(pageSql, map, rowMap);
		int total = namedParameterJdbcTemplate.queryForInt(totalSql, map);

		pageBean.setTotalCount(total);
		return list;
	}

	public static <T> T getPage(String alias, String sql, ResultSetExtractor<T> rse, PageBean pageBean, Map<String, Object> params) {

		T result = null;
		NamedParameterJdbcTemplate template = null;
		template = getNamedParameterJdbcTemplate(alias);
		if (pageBean != null) {
			int pageSize = pageBean.getPageSize();
			int offset = pageBean.getFirst();
			Dialect dialect = null;
			try {
				dialect = DialectUtil.getDialectByDataSourceAlias(alias);
			} catch (Exception e) {
				return null;
			}

			String pageSql = dialect.getLimitString(sql, offset, pageSize);
			String totalSql = dialect.getCountSql(sql);
			result = template.query(pageSql, params, rse);
			int total = template.queryForInt(totalSql, params);
			pageBean.setTotalCount(total);
		} else {
			result = template.query(sql, params, rse);
		}
		return result;
	}

	public static List getPage(String alias, int currentPage, int pageSize, String sql, Map paraMap, PageBean pageBean) {

		int offset = (currentPage - 1) * pageSize;

		Dialect dialect = null;
		try {
			dialect = DialectUtil.getDialectByDataSourceAlias(alias);
		} catch (Exception e) {
			return null;
		}

		String pageSql = dialect.getLimitString(sql, offset, pageSize);
		String totalSql = dialect.getCountSql(sql);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
		namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(alias);
		List list = namedParameterJdbcTemplate.queryForList(pageSql, paraMap);
		int total = namedParameterJdbcTemplate.queryForInt(totalSql, paraMap);

		pageBean.setTotalCount(total);

		return list;
	}
	//yangxiao
	public static List getPage1(String alias, String sql, Map paraMap) {



		Dialect dialect = null;
		try {
			dialect = DialectUtil.getDialectByDataSourceAlias(alias);
		} catch (Exception e) {
			return null;
		}


		String totalSql = dialect.getCountSql(sql);
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = null;
		namedParameterJdbcTemplate = getNamedParameterJdbcTemplate(alias);
		
		
		//更改获取list结果方法的参数，使sql不再受固定显示一定数量数据的约束
		List list = namedParameterJdbcTemplate.queryForList(sql, paraMap);
		
		int total = namedParameterJdbcTemplate.queryForInt(totalSql, paraMap);



		return list;
	}
	//end
	
	public static List getPage( String alias, String sql, Map<?, ?> paraMap, PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		return getPage(alias, currentPage, pageSize, sql, paraMap, pageBean);
	}

	/**
	 * 因为service层不能切换当前的数据源，我们就手动new一个切换了数据源的JdbcTemplate
	 * 
	 * @param alias	:数据源别名
	 * @return JdbcTemplate
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @exception
	 * @since 1.0.0
	 */
	public static JdbcTemplate getNewJdbcTemplate(String alias) throws Exception {
		return new JdbcTemplate(DataSourceUtil.getDataSourceByAlias(alias));
	}
}
