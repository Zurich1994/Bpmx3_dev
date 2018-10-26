package com.hotent.platform.dao.ats;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.model.ats.AtsAttendanceFile;

/**
 * <pre>
 * 对象功能:考勤档案 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-20 09:11:05
 * </pre>
 */
@Repository
public class AtsAttendanceFileDao extends BaseDao<AtsAttendanceFile> {
	@Override
	public Class<?> getEntityClass() {
		return AtsAttendanceFile.class;
	}

	public List<AtsAttendanceFile> getByAttendPolicy(Long attendPolicy) {
		return this.getBySqlKey("getByAttendPolicy", attendPolicy);
	}

	public AtsAttendanceFile getByAccount(String account) {
		return this.getUnique("getByAccount", account);
	}

	public AtsAttendanceFile getByFileId(Long id) {
		return this.getUnique("getByFileId", id);
	}

	public List<AtsAttendanceFile> getList(QueryFilter filter) {
		return this.getBySqlKey("getList", filter);
	}
	public List<AtsAttendanceFile> getNoneCalList(QueryFilter filter) {
		return this.getBySqlKey("getNoneCalList", filter);
	}

	public List<AtsAttendanceFile> getByUserId(Long userId) {
		return this.getBySqlKey("getByUserId", userId);
	}

	public List<AtsAttendanceFile> getAllList(QueryFilter filter) {
		return this.getBySqlKey("getAllList", filter);
	}

	
}