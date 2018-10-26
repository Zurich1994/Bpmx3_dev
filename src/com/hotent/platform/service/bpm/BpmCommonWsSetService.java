package com.hotent.platform.service.bpm;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.model.bpm.BpmCommonWsParams;
import com.hotent.platform.model.bpm.BpmCommonWsSet;
import com.hotent.platform.dao.bpm.BpmCommonWsSetDao;
import com.hotent.platform.model.system.WsDataTemplate;
import com.hotent.platform.dao.bpm.BpmCommonWsParamsDao;
import com.hotent.platform.dao.system.WsDataTemplateDao;

/**
 *<pre>
 * 对象功能:通用webservice调用设置 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-10-17 10:09:20
 *</pre>
 */
@Service
public class BpmCommonWsSetService extends BaseService<BpmCommonWsSet>
{
	@Resource
	private BpmCommonWsSetDao dao;
	
	@Resource
	private BpmCommonWsParamsDao bpmCommonWsParamsDao;
	@Resource
	private WsDataTemplateDao wsDataTemplateDao;
	
	public BpmCommonWsSetService()
	{
	}
	
	@Override
	protected IEntityDao<BpmCommonWsSet, Long> getEntityDao() 
	{
		return dao;
	}
	
	private void delByPk(Long id){
		bpmCommonWsParamsDao.delByMainId(id);
	}
	
	public void delAll(Long[] lAryId) {
		for(Long id:lAryId){	
			List<WsDataTemplate> list = wsDataTemplateDao.getByWsSetId(id);
			if(BeanUtils.isNotEmpty(list)){
				throw new RuntimeException("webservice调用设置已被引用，无法删除！");
			}
			delByPk(id);
			dao.delById(id);	
		}	
	}
	
	public void addAll(BpmCommonWsSet bpmCommonWsSet) throws Exception{
		add(bpmCommonWsSet);
		addSubList(bpmCommonWsSet);
	}
	
	public void updateAll(BpmCommonWsSet bpmCommonWsSet) throws Exception{
		update(bpmCommonWsSet);
		delByPk(bpmCommonWsSet.getId());
		addSubList(bpmCommonWsSet);
	}
	
	public void addSubList(BpmCommonWsSet bpmCommonWsSet) throws Exception{
		List<BpmCommonWsParams> bpmCommonWsParamsList=bpmCommonWsSet.getBpmCommonWsParamsList();
		if(BeanUtils.isNotEmpty(bpmCommonWsParamsList)){
			for(BpmCommonWsParams bpmCommonWsParams:bpmCommonWsParamsList){
				bpmCommonWsParams.setSetid(bpmCommonWsSet.getId());
				bpmCommonWsParams.setId(UniqueIdUtil.genId());
				bpmCommonWsParamsDao.add(bpmCommonWsParams);
			}
		}
	}
	
	public BpmCommonWsSet getByAlias(String alias){
		return dao.getByAlias(alias);
	}
	
	public List<BpmCommonWsParams> getBpmCommonWsParamsList(Long id) {
		return bpmCommonWsParamsDao.getByMainId(id);
	}
}
