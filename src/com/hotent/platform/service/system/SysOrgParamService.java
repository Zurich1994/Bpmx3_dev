package com.hotent.platform.service.system;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.platform.model.system.SysOrgParam;
import com.hotent.platform.model.system.SysParam;
import com.hotent.platform.model.system.SysUserParam;
import com.hotent.platform.dao.system.SysOrgParamDao;
import com.hotent.platform.dao.system.SysParamDao;

/**
 * 对象功能:组织参数属性 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-24 10:04:50
 */
@Service
public class SysOrgParamService extends BaseService<SysOrgParam>
{
	@Resource
	private SysOrgParamDao sysOrgParamDao;
	@Resource
	private SysParamDao sysParamDao;
	
	public SysOrgParamService()
	{
	}
	
	@Override
	protected IEntityDao<SysOrgParam, Long> getEntityDao() 
	{
		return sysOrgParamDao;
	}
	
	/**
	 * 添加组织参数。
	 * @param orgId			组织id
	 * @param valueList		组织属性列表。
	 */
	public void add(long orgId,List<SysOrgParam> valueList){

		sysOrgParamDao.delByOrgId(orgId);
		if(valueList==null||valueList.size()==0)return;
		for(SysOrgParam p:valueList){
			sysOrgParamDao.add(p);
		}
	
	}
	public List<SysOrgParam> getByOrgId(Long orgId){
		List<SysOrgParam>list= sysOrgParamDao.getByOrgId(orgId);
		return list;
	}

	public List<SysOrgParam> getListByOrgId(Long orgId) {
		
		List<SysOrgParam>list= sysOrgParamDao.getByOrgId(orgId);
		if(list.size()>0){
			for(SysOrgParam param:list){
				long paramId=param.getParamId();
				SysParam sysParam=sysParamDao.getById(paramId);
				if(BeanUtils.isNotEmpty(sysParam)){
					param.setParamName(sysParam.getParamName());
				}
			}
		}
		return list;
	}
	/**
	 * 根据部门ID或属性Key获取对象
	 * @param paramKey
	 * @param orgId
	 * @return
	 */
	public SysOrgParam getByParamKeyAndOrgId(String paramKey,Long orgId){
		return sysOrgParamDao.getByParamKeyAndOrgId(paramKey,orgId);
	}
}
