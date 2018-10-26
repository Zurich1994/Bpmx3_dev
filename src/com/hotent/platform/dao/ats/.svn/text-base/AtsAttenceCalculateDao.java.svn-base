package com.hotent.platform.dao.ats;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.ats.AtsAttenceCalculate;

/**
 * <pre>
 * 对象功能:考勤计算 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-31 13:51:08
 * </pre>
 */
@Repository
public class AtsAttenceCalculateDao extends BaseDao<AtsAttenceCalculate> {
	@Override
	public Class<?> getEntityClass() {
		return AtsAttenceCalculate.class;
	}

	public AtsAttenceCalculate getByFileIdAttenceTime(Long fileId,
			Date attenceTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("attenceTime", attenceTime);
		return (AtsAttenceCalculate) this.getOne("getByFileIdAttenceTime",
				params);
	}

	public List<AtsAttenceCalculate> getByFileIdAttenceTime(Long fileId,
			Date beginattenceTime, Date endattenceTime) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("fileId", fileId);
		params.put("beginattenceTime", beginattenceTime);
		params.put("endattenceTime", endattenceTime);
		return this.getBySqlKey("getByFileIdTime",
				params);
	}

	public List<AtsAttenceCalculate> getList(QueryFilter filter) {
		return this.getBySqlKey("getList", filter);
	}

	public void delByFileId(Long fileId) {
		this.delBySqlKey("delByFileId", fileId);
	}

}