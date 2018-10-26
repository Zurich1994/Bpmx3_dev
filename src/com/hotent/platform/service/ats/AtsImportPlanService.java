package com.hotent.platform.service.ats;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.ats.AtsImportPlanDao;
import com.hotent.platform.model.ats.AtsImportPlan;

/**
 *<pre>
 * 对象功能:打卡导入方案 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 13:50:13
 *</pre>
 */
@Service
public class AtsImportPlanService extends  BaseService<AtsImportPlan>
{
	@Resource
	private AtsImportPlanDao dao;
	
	
	
	public AtsImportPlanService()
	{
	}
	
	@Override
	protected IEntityDao<AtsImportPlan, Long> getEntityDao() 
	{
		return dao;
	}
	
	
	/**
	 * 保存 打卡导入方案 信息
	 * @param atsImportPlan
	 */
	public void save(AtsImportPlan atsImportPlan){
		Long id=atsImportPlan.getId();
		if(id==null || id==0){
			id=UniqueIdUtil.genId();
			atsImportPlan.setId(id);
			this.add(atsImportPlan);
		}
		else{
			this.update(atsImportPlan);
		}
	}
	
}
