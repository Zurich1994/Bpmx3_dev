package com.hotent.platform.service.bpm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.bpm.BpmNodeWsParamsDao;
import com.hotent.platform.model.bpm.BpmNodeWsParams;

/**
 * <pre>
 * 对象功能:流程webservice节点参数 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-21 16:51:20
 * </pre>
 */
@Service
public class BpmNodeWsParamsService extends BaseService<BpmNodeWsParams> {
	@Resource
	private BpmNodeWsParamsDao dao;

	public BpmNodeWsParamsService() {
	}

	@Override
	protected IEntityDao<BpmNodeWsParams, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存webservice节点参数
	 * 
	 * @param bpmNodeWsParams
	 * @return
	 */
	public BpmNodeWsParams save(BpmNodeWsParams bpmNodeWsParams) {
		if (BeanUtils.isEmpty(bpmNodeWsParams))
			return null;

		if (BeanUtils.isEmpty(bpmNodeWsParams.getId())) {
			bpmNodeWsParams.setId(UniqueIdUtil.genId());
			dao.add(bpmNodeWsParams);
		} else {
			dao.update(bpmNodeWsParams);
		}
		return bpmNodeWsParams;
	}

	/**
	 * 通过webserviceId
	 * @param webserviceId 
	 * @return
	 */
	public List<BpmNodeWsParams> getByWebserviceId(Long webserviceId) {
		return dao.getByWebserviceId(webserviceId);
	}

	/**
	 * 通过ebserviceId删除设置参数
	 * @param webserviceId
	 */
	public void delByWebserviceId(Long webserviceId) {
		 dao.delByWebserviceId(webserviceId);
	}
}
