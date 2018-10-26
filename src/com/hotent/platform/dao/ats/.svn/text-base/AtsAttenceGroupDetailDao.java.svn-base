package com.hotent.platform.dao.ats;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsAttenceGroupDetail;
/**
 *<pre>
 * 对象功能:考勤组明细 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 10:07:59
 *</pre>
 */
@Repository
public class AtsAttenceGroupDetailDao extends BaseDao<AtsAttenceGroupDetail>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsAttenceGroupDetail.class;
	}

	public List<AtsAttenceGroupDetail> getByGroupId(Long groupId) {
		return this.getBySqlKey("getByGroupId", groupId);
	}

	public void delByGroupId(Long groupId) {
		this.delBySqlKey("delByGroupId", groupId);
	}

	public void delByFileId(Long fileId) {
		this.delBySqlKey("delByFileId", fileId);
	}
	
}