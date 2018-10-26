/**
 * 对象功能:邮件 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zyp
 * 创建时间:2012-04-09 14:16:18
 */
package com.hotent.platform.dao.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.page.PageBean;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.mail.OutMail;


@Repository
public class OutMailDao extends BaseDao<OutMail>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return OutMail.class;
	}
	
	/**
	 * 发件人列表
	 * @param senderAddress
	 * @return
	 */
	public List<OutMail> getListBySender(String senderAddress) {
		return this.getBySqlKey("getListBySender", senderAddress);
	}

	/**
	 * 获取分类邮件（收件箱，发件箱，草稿箱，垃圾箱)
	 * @param queryFilter
	 * @param types
	 * @return
	 */
	public List<OutMail> getFolderList(QueryFilter queryFilter) {
		return this.getBySqlKey("getFolderList", queryFilter);
		
	}
	

	/**
	 * 更新邮箱分类类型
	 * @param mailId
	 * @param types
	 * @return
	 */
	public int updateTypes(Long mailId,int types)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("types", types);
		params.put("mailId", mailId);
		return update("updateTypes",params);
	}
	
	/**
	 * 根据邮箱和邮箱类型得到邮件数量
	 * @param address
	 * @param type
	 * @return
	 */
	public int getFolderCount(long id, int type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("types", type);
		params.put("setId",id);
		return (Integer)this.getOne("getFolderCount", params);
	}
	
	/**
	 *  获得用户默认邮箱的邮件列表
	 * @param queryFilter
	 * @return
	 */
	public List<OutMail> getDefaultMailList(QueryFilter queryFilter) {
		return this.getBySqlKey("getDefaultMailList", queryFilter);
	}	
	
	/**
	 * 判断是否存在当前邮件uid
	 * @param uid
	 * @return
	 */
	public boolean getByEmailId(String uid,Long setId) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("emailId", uid);
		params.put("setId", setId);
		int num=(Integer)this.getOne("getByEmailId", params);
		if(num>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据邮件uid删除本地邮件
	 * @param uid
	 */
	public void delByEmailid(String uid) {
		getBySqlKey("delByEmailid",uid);
	}
	
	/**
	 * 根据用户ID得到最新已发的邮件
	 * @param userId
	 */
	public List<OutMail> getMailByUserId(long userId,PageBean pb) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return getBySqlKey("getMailByUserId",params,pb);
	}
	
	/**
	 * 根据setId获取所有的UID
	 * @param setId
	 * @return
	 */
	public List<String> getUIDBySetId(Long setId){
		return getSqlSessionTemplate().selectList(getIbatisMapperNamespace() + ".getUIDBySetId",setId);
	}

	public void delBySetId(Long setId) {
		 delBySqlKey("delBySetId", setId);
	}	
	
}