package com.hotent.core.service;

import com.hotent.core.db.WfBaseDao;
import com.hotent.core.page.PageList;
import com.hotent.core.web.query.QueryFilter;

/**
 * 实现业务的基本操作类，实体主键为Long类型
 * 
 * @author csx
 * 
 * @param <E>
 *            实体类型，如Role
 */
public abstract class WfBaseService<E> extends BaseService<E> {

	
	
	/**
	 * 获取草稿
	 * @param userId
	 * @param queryFilter
	 * @return
	 */
	public PageList getMyDraft(Long userId,QueryFilter queryFilter){
		return ((WfBaseDao) getEntityDao()).getDraftByUser(userId, queryFilter);
	}
	
	/**
	 * 获取我结束的流程。
	 * @param userId
	 * @param queryFilter
	 * @return
	 */
	public PageList getMyEnd(Long userId,QueryFilter queryFilter){
		return ((WfBaseDao) getEntityDao()).getEndByUser(userId, queryFilter);
	}
	
	/**
	 * 根据用户获取某人的任务列表。
	 * @param userId
	 * @param queryFilter
	 * @return
	 */
	public PageList getMyTodoTask(Long userId,QueryFilter queryFilter){
		return ((WfBaseDao) getEntityDao()).getMyTodoTask(userId, queryFilter);
	}

}
