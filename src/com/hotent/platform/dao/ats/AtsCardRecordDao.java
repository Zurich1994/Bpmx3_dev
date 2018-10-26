package com.hotent.platform.dao.ats;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.ats.AtsCardRecord;
/**
 *<pre>
 * 对象功能:打卡记录 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 11:21:21
 *</pre>
 */
@Repository
public class AtsCardRecordDao extends BaseDao<AtsCardRecord>
{
	@Override
	public Class<?> getEntityClass()
	{
		return AtsCardRecord.class;
	}
	public List<AtsCardRecord> getByCardNumber(String cardNumber) {
		return this.getBySqlKey("getByCardNumber", cardNumber);
	}
	public List<AtsCardRecord> getByCardNumberCardDate(String cardNumber,
			Date startTime, Date endTime) {
		Map<String,Object> params =  new HashMap<String,Object>();
		params.put("cardNumber", cardNumber);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return this.getBySqlKey("getByCardNumberCardDate", params);
	}
}