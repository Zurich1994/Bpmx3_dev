package com.hotent.platform.dao.ats;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.ats.AtsScheduleShift;

/**
 * <pre>
 * 对象功能:排班列表 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-25 17:05:06
 * </pre>
 */
@Repository
public class AtsScheduleShiftDao extends BaseDao<AtsScheduleShift> {
	@Override
	public Class<?> getEntityClass() {
		return AtsScheduleShift.class;
	}

	public List<AtsScheduleShift> getList(QueryFilter queryFilter) {
		return this.getBySqlKey("getList", queryFilter);
	}

	public AtsScheduleShift getByFileIdAttenceTime(Long fileId, Date attenceTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("attenceTime", attenceTime);
		return (AtsScheduleShift) this.getOne("getByFileIdAttenceTime", params);
	}

	public List<AtsScheduleShift> getByFileIdStartEndTime(Long fileId,
			Date startTime, Date endTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("startTime", startTime);
		params.put("endTime", endTime);
		return this.getBySqlKey("getByFileIdStartEndTime", params);
	}

	public void delByFileId(Long fileId) {
		this.delBySqlKey("delByFileId", fileId);
	}

}