package com.hotent.platform.dao.file;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.file.FileManageLog;

/**
 * <pre>
 * 对象功能:文件管理 DAO类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014年6月17日21:25:43
 * </pre>
 */
@Repository
public class FileManageLogDao extends BaseDao<FileManageLog> {

	@Override
	public Class getEntityClass() {
		return FileManageLog.class;
	}

}
