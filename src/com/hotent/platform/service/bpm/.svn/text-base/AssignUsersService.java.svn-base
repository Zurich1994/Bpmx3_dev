package com.hotent.platform.service.bpm;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.AssignUsersDao;
import com.hotent.platform.model.bpm.AssignUsers;

/**
 *<pre>
 * 对象功能:bpm_assign_users Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2015-02-11 11:22:47
 *</pre>
 */
@Service
public class AssignUsersService extends  BaseService<AssignUsers>
{
	@Resource
	private AssignUsersDao dao;
	
	
	
	public AssignUsersService()
	{
	}
	
	@Override
	protected IEntityDao<AssignUsers, Long> getEntityDao() 
	{
		return dao;
	}


	public Long addAssignUser(List<AssignUsers> assignUserslList) {
		if (BeanUtils.isEmpty(assignUserslList)) return 0L;
		Long runId = UniqueIdUtil.genId();
		for (AssignUsers assignUsers : assignUserslList) {
			assignUsers.setId(UniqueIdUtil.genId());
			assignUsers.setRunId(runId);
			dao.add(assignUsers);
		}
		return runId;
	}

	public List<AssignUsers> getByRunIdAndNodeId(Long runId, String nodeId) {
		
		return dao.getByRunIdAndNodeId(runId,nodeId);
	}

	
	
	
}
