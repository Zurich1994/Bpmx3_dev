package edu.hrbeu.MDA.DBAccess.core;

import java.util.List;

/**
 * 实现业务的基本操作类，实体主键为Long类型
 * 
 * @author csx
 * 
 * @param <E>
 *            实体类型，如Role
 */
public abstract class BaseService<E> extends GenericService<E, Long> {

	/**
	 * 高级查询的方法
	 * 
	 * @param queryFilter 查询
	 * @param busQueryRule 查询规则
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<E> getAll(QueryFilter queryFilter) {
			return getEntityDao().getAll(queryFilter);
	}

}
