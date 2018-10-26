package com.hotent.platform.service.form;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.form.BpmFormDefHiDao;
import com.hotent.platform.model.form.BpmFormDefHi;
import com.hotent.platform.model.system.SysUser;

/**
 * <pre>
 * 对象功能:BPM_FORM_DEF_HI Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014-10-11 17:49:41
 * </pre>
 */
@Service
public class BpmFormDefHiService extends BaseService<BpmFormDefHi> {
	@Resource
	private BpmFormDefHiDao dao;

	public BpmFormDefHiService() {
	}

	@Override
	protected IEntityDao<BpmFormDefHi, Long> getEntityDao() {
		return dao;
	}
	
	/**
	 * 添加表单操作记录信息，包括：增加、更新
	 * @param bpmFormDefHi
	 */
	public void addHisRecord(BpmFormDefHi bpmFormDefHi) {
		SysUser sysUser = ContextUtil.getCurrentUser();
		bpmFormDefHi.setHisId(UniqueIdUtil.genId());
		bpmFormDefHi.setCreateBy(sysUser.getUserId());// 为什么要重置用户id和用户名?因为之前的代码把用户id当成用户名了
		bpmFormDefHi.setCreator(sysUser.getFullname());
		bpmFormDefHi.setCreatetime(new Date()); // 重置时间点
		this.add(bpmFormDefHi);
	}

	
	
}
