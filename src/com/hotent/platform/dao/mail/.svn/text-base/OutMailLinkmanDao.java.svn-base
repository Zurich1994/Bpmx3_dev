/**
 * 对象功能:OUT_MAIL_LINKMAN Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:james
 * 创建时间:2012-04-13 11:11:56
 */
package com.hotent.platform.dao.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.mail.OutMailLinkman;

@Repository
public class OutMailLinkmanDao extends BaseDao<OutMailLinkman>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return OutMailLinkman.class;
	}
	
	/**
	 * 根据联系人邮箱地址找到相应的联系人实体
	 * @param address
	 * @return
	 */
	public OutMailLinkman findLinkMan(String address,long userId) 
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("linkAddress", address);
		return this.getUnique("findLinkMan", params);
	}
	
	/**
	 * 根据当前用户id查询前10条最近联系人
	 * @param userId
	 * @return
	 */
	public List<OutMailLinkman> getAllByUserId(Long userId) 
	{
		String statement="getAllByUserId_"+this.getDbType();
		
		return this.getBySqlKey(statement,  userId);
	}
}