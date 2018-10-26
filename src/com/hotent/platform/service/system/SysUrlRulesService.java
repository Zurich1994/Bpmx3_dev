package com.hotent.platform.service.system;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysUrlRulesDao;
import com.hotent.platform.model.system.SysUrlRules;

/**
 *<pre>
 * 对象功能:URL地址拦截脚本管理 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:wdr
 * 创建时间:2014-03-27 16:32:01
 *</pre>
 */
@Service
public class SysUrlRulesService extends BaseService<SysUrlRules>
{
	@Resource
	private SysUrlRulesDao dao;
	
	
	
	public SysUrlRulesService()
	{
	}
	
	@Override
	protected IEntityDao<SysUrlRules, Long> getEntityDao() 
	{
		return dao;
	}

	public List<SysUrlRules> getByUrlPer(Long id) {
		
		return dao.getByUrlPer(id);
	}

	public void delByUrlPerId(Long[] ids) {
		if(BeanUtils.isEmpty(ids)) return;
		for (long id:ids) {
			dao.delByUrlPerId(id);
		}
	}
	
	
	
	/**
	 * 添加新的脚步规则
	 * @param sysUrlRulesList
	 * @param sysUrlId
	 */
	public void addRule(List<SysUrlRules> sysUrlRulesList, Long sysUrlId) {
		if (BeanUtils.isNotEmpty(sysUrlRulesList)) {
			for (SysUrlRules sysUrlRules : sysUrlRulesList) {
				sysUrlRules.setSysUrlId(sysUrlId);
				Long id=UniqueIdUtil.genId();
				sysUrlRules.setId(id);
				dao.add(sysUrlRules);
			}
		}
	}

	/**
	 * 更新脚步规则
	 * @param sysUrlRulesList
	 * @param id
	 */
	public void updateRule(List<SysUrlRules> sysUrlRulesList, Long id) {
		dao.delByUrlPerId(id);
		addRule(sysUrlRulesList,id);
	}
	
	
}
