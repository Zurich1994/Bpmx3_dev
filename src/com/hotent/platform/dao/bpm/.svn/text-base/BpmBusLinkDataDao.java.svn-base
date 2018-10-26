/**
 * 对象功能:业务流程关联表数据 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:phl
 * 创建时间:2012-02-14 09:19:33
 */
package com.hotent.platform.dao.bpm;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmBusLinkData;

@Repository
public class BpmBusLinkDataDao extends BaseDao<BpmBusLinkData>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmBusLinkData.class;
	}
	/**
	 * 根据defId删除该流程流关联表的数据
	 * @param actDefId
	 */
	public void delByActDefIdLinkData(String actDefId){
		delBySqlKey("delByActDefIdLinkData",actDefId);
	}
}