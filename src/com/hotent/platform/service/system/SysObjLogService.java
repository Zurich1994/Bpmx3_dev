package com.hotent.platform.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.model.bpm.ProcessRun;
import net.sf.json.util.JSONUtils;
import net.sf.ezmorph.object.DateMorpher;
import com.hotent.core.bpm.model.ProcessCmd;
import com.hotent.core.util.StringUtil;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.system.SysObjLog;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.dao.system.SysObjLogDao;
import com.hotent.core.service.BaseService;

/**
 * <pre>
 * 对象功能:SYS_OBJ_LOG Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:liyj
 * 创建时间:2015-04-27 11:09:44
 * </pre>
 */
@Service
public class SysObjLogService extends BaseService<SysObjLog> {
	@Resource
	private SysObjLogDao dao;

	public SysObjLogService() {
	}

	@Override
	protected IEntityDao<SysObjLog, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 SYS_OBJ_LOG 信息
	 * 
	 * @param sysObjLog
	 */
	public void save(SysObjLog sysObjLog) {
		Long id = sysObjLog.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			sysObjLog.setId(id);
			
			//其他自动生成的参数
			sysObjLog.setCreateTime(new Date());
			SysUser sysUser =ContextUtil.getCurrentUser();
			sysObjLog.setOperator(sysUser.getFullname());
			sysObjLog.setOperatorId(sysObjLog.getId());
			
			this.add(sysObjLog);
		} else {
			this.update(sysObjLog);
		}
	}
}
