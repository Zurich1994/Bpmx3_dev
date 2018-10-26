/**
 * 对象功能:邮箱设置 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-09 13:43:51
 */
package com.hotent.platform.dao.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.mail.OutMailUserSeting;


@Repository
public class OutMailUserSetingDao extends BaseDao<OutMailUserSeting>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return OutMailUserSeting.class;
	}
	
	/**
	 * 根据邮箱地址返回相应的邮箱配置实体
	 * @param mailAddress
	 * @return
	 */
	public OutMailUserSeting getMailByAddress(String address) {
		return this.getUnique("getMailByAddress", address);
	}
	
	/**
	 * 根据当前用户ID获得邮箱列表
	 * @param userId
	 * @return
	 */
	public List<OutMailUserSeting> getMailByUserId(Long userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return getBySqlKey("getMailByUserId",params);
	}
	
	/**
	 * 获得当前用户的默认邮箱
	 * @param userId
	 * @return
	 */
	public OutMailUserSeting getByIsDefault(long userId) {
		return this.getUnique("getByIsDefault", userId);
	}
	
	/**
	 * 根据当前用户ID获得外部邮箱的分页列表
	 * @param queryFilter
	 * @return
	 */
	public List<OutMailUserSeting> getAllByUserId(QueryFilter queryFilter) {
		return this.getBySqlKey("getAllByUserId", queryFilter);
	}
	
	/**
	 * 验证邮箱地址的唯一性
	 * @param address
	 * @return
	 */
	public int getCountByAddress(String address) {
		return (Integer)this.getOne("getCountByAddress", address);
	}
	
	/**
	 * 更改默认邮箱
	 * @param mail
	 * @return
	 */
	public int updateDefault(OutMailUserSeting mail) {
		return this.update("updateDefault", mail);
	}
	
	/**
	 * 统计当前用户设置的外部邮箱数量
	 * @param userId
	 * @return
	 */
	public int getCountByUserId(long userId) {
		return (Integer)this.getOne("getCountByUserId", userId);
	}
}