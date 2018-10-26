package com.hotent.core.db;

import java.io.Serializable;
import java.util.List;

import com.hotent.core.page.PageBean;
import com.hotent.core.web.query.QueryFilter;

/**
 * DAO接口。<br>
 * 默认支持添加，删除，更新，查询，分页方法。
 * @author hotent
 */
public interface IEntityDao<E, PK extends Serializable>
{
	/**
	 * 添加对象
	 * @param entity
	 * @return
	 */
	public void add(E entity);

	/**
	 * 根据主键删除
	 * @param id
	 */
	public int delById(PK id);

	/**
	 * 更新对象
	 * @param entity
	 */
	public int update(E entity);

	/**
	 * 根据主键ID取得对象
	 * @param id
	 * @return
	 */
	public Object getById(PK id);

	/**
	 * 根据条件获取列表数据
	 * @param statementName
	 * @param params
	 * @return
	 */
	public List<E> getList(String statementName,Object params);
	
	
	/**
	 * 按某一SQL取得分页的数据列表
	 * @return
	 */
	public List<E> getList(String statementName,Object params,PageBean pb);
	
	/**
	 * 获取该表的所有记录
	 * @return
	 */
	public List<E> getAll();
	
	/**
	 * 按过滤器查询记录列表
	 * @param queryFilter
	 * @return
	 */
	public List<E> getAll(QueryFilter queryFilter);
	
	/**
	 * 返回某一条单独的数据
	 * @param sqlKey map xml 中的语句Id
	 * @param params
	 * @return
	 */
	public E getUnique(String sqlKey,Object params);
}
