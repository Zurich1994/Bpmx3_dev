package com.hotent.core.db.helper;

import javax.sql.DataSource;

/**
 * JDBC执行命令接口。<br>
 * 提供jdbctemplate上下文执行sql语句。<br>
 * 一般方法事务中执行<br>
 * <pre>
 * JdbcCommand cmd = new RoleCommand();
 * jdbcHlper.execute(cmd);
 * 或者
 * 
 * jdbcHelper.execute(new JdbcCommand() {
 *			
 *			@Override
 *			public void execute(DataSource source) throws Exception {
 *				NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(source);
 *				
 *			}
 *		});
 *	</pre>
 */
public interface JdbcCommand {
	
	/**
	 * 根据datasource 执行sql语句。
	 * @param source
	 * @throws Exception
	 */
	public void execute(DataSource source) throws Exception;
	
	

}
