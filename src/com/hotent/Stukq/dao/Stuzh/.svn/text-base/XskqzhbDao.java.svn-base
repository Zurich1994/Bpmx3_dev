
package com.hotent.Stukq.dao.Stuzh;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.Stukq.model.Stuzh.Xskqzhb;

@Repository
public class XskqzhbDao extends BaseDao<Xskqzhb>
{
	@Override
	public Class<?> getEntityClass()
	{
		return Xskqzhb.class;
	}

	/**
	 * 根据外键获取学生考勤综合表列表
	 * @param refId
	 * @return
	 */
	public List<Xskqzhb> getByMainId(Long refId) {
		return this.getBySqlKey("getXskqzhbList", refId);
	}
	
	/**
	 * 根据外键删除学生考勤综合表
	 * @param refId
	 * @return
	 */
	public void delByMainId(Long refId) {
		this.delBySqlKey("delByMainId", refId);
	}
}
