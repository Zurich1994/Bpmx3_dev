package com.hotent.platform.service.file;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.file.FileManageLogDao;
import com.hotent.platform.model.file.FileManageLog;

/**
 * <pre>
 * 对象功能:文件管理 service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014年6月17日21:25:43
 * </pre>
 */
@Service
public class FileManageLogService  extends BaseService<FileManageLog> {

	@Resource
	FileManageLogDao dao;

	@Override
	protected IEntityDao<FileManageLog, Long> getEntityDao() {
		return dao;
	}
	
	/**
	 * 增加操作记录
	 * @param fileId
	 * @param operateType
	 */
	public void add(Long fileId,String operateType) {
		FileManageLog fileManageLog = new FileManageLog();
		fileManageLog.setId(UniqueIdUtil.genId());
		fileManageLog.setFileId(fileId);
		fileManageLog.setOperationId(ContextUtil.getCurrentUserId());
		fileManageLog.setOperationDeptId(ContextUtil.getCurrentOrgId());
		fileManageLog.setOperateTime(new Date());
		fileManageLog.setOperateType(operateType);
		super.add(fileManageLog);
	}
	
	
}
