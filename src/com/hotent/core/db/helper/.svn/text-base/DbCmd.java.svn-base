package com.hotent.core.db.helper;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


/**
 * 根据对象实现添加，更新，删除。<br>
 * <pre>
 * 		Role obj;
 * 		DbCmd<T> cmd=new DbCmd<T>();
 * 		cmd.setModel(obj);
 * 		cmd.setOperatorType(OperatorType.Add);
 * j	dbcHelper.execute(cmd);
 * </pre>
 * @author hotent
 *
 * @param <T>
 */
public class DbCmd<T> implements JdbcCommand {

	public enum OperatorType {
		Add, Del, Upd, Get
	}

	private ObjectHelper<T> helper;
	private T obj;
	private OperatorType type;

	public void setModel(T obj) {
		helper = new ObjectHelper<T>();
		helper.setModel(obj);
		this.obj = obj;
	}

	public void setOperatorType(OperatorType type) {
		this.type = type;
	}

	@Override
	public void execute(DataSource source) throws Exception {
		String sql = "";
		if (type == OperatorType.Add) {
			sql = helper.getAddSql();
		} else if (type == OperatorType.Upd) {
			sql = helper.getUpdSql();
		} else if (type == OperatorType.Del) {
			sql = helper.getDelSql();
		}
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(this.obj);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(source);
		template.update(sql, namedParameters);
	}

}
