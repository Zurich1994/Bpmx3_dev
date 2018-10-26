package com.hotent.core.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import com.hotent.core.mybatis.Dialect;
import com.hotent.core.page.PageBean;

public class JdbcDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private Dialect dialect;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
	
	@SuppressWarnings({ "rawtypes" })
	public  List getPage(int currentPage,int pageSize,String sql,Map paraMap,PageBean pageBean) throws Exception
	{
		int offset=(currentPage-1)*pageSize ;
		String pageSql=dialect.getLimitString(sql, offset, pageSize);
		String totalSql=dialect.getCountSql(sql);
		List list= queryForList(pageSql,paraMap);
		int total=queryForInt(totalSql, paraMap);
		pageBean.setTotalCount(total);
		
		return list;
	}
	
	
	
	/**
	 * 分页，list中对象为model对象。<br>
	 * 如果使用分页查询需要设置方言。<br>
	 * 使用方法：<br>
	 * <pre>
	 * PageModel pageModel= jdbcHelper.getPage(1, 2, "select * from sys_role" , null,new RowMapper<Role>() {
	 *		@Override
	 *		public Role mapRow(ResultSet rs, int arg1) throws SQLException {
	 *			Role role=new Role();
	 *			role.setName(rs.getString("name"));
	 *			return role;
	 *		}
	 *	});
	 * </pre>
	 * @param currentPage 当前页， 从1开始
	 * @param pageSize 每页显示记录数
	 * @param sql sql语句，如果需要参数，请输入如下sql语句，sql="select * from user where name=:name";
	 * @param paraMap 参数，为map对象
	 * @param rowMap 映射对象的方法。
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  List getPage(PageBean pageBean,String sql,RowMapper rowMap) throws Exception
	{
		int pageSize=pageBean.getPageSize();
		 
		int offset=pageBean.getFirst();
	
		Map map=new HashMap();
		
		
		String pageSql=dialect.getLimitString(sql, offset, pageSize);
		String totalSql=dialect.getCountSql(sql);
		List list= queryForList(pageSql,map,rowMap);
		int total=queryForInt(totalSql, map);
		
		pageBean.setTotalCount(total);
		return list;
	}
	
	public  <T> T getPage(String sql,ResultSetExtractor<T> rse,PageBean pageBean) throws Exception
	{
		
		int pageSize=pageBean.getPageSize();
		int offset=pageBean.getFirst();
		String pageSql=dialect.getLimitString(sql, offset, pageSize);
		String totalSql=dialect.getCountSql(sql);
		T result =  jdbcTemplate.query(pageSql, rse);
		
		int total=jdbcTemplate.queryForInt(totalSql);
		pageBean.setTotalCount(total);
		return result;
	}
	
	/**
	 * 输入查询语句和查询条件查询列表。
	 * @param sql 查询语句  示例：select * from sys_role where name=:name
	 * @param parameter Map map=new HashMap(); 		map.put("name", "li");
	 * @param rowMap  需要实现RowMapper接口,有两种实现方式，一种是匿名类，另外一种是实现RowMapper接口。
	 * 使用方法如下：<br/>
	 * <pre>
	 * List<Role> list =jdbcHelper.queryForList(sql, null, new RowMapper<Role>() {
	 *		@Override
	 *		public Role mapRow(ResultSet rs, int arg1) throws SQLException {
	 *			Role role=new Role();
	 *			role.setName(rs.getString("name"));
	 *			return role;
	 *		}
	 *	});
	 * </pre>
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  List queryForList(String sql,Map parameter,RowMapper rowMap)
	{
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		return  template.query(sql, parameter, rowMap);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  List queryForList(String sql,Map parameter)
	{
		NamedParameterJdbcTemplate template =new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		return  template.queryForList(sql, parameter);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  int queryForInt( String sql,Map parameter)
	{
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());
		return template.queryForInt(sql, parameter);
	}
	
	public int upd(String sql){
		return jdbcTemplate.update(sql);
	}

}
