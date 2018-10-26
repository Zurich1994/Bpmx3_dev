package com.hotent.platform.dao.system;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysOrgType;
/**
 * 对象功能:组织结构类型 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-11-27 09:55:21
 */
@Repository
public class SysOrgTypeDao extends BaseDao<SysOrgType>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysOrgType.class;
	}

	/**
	 * 根据维度Id获取该维度下的最深等级。
	 * @param demId
	 * @return
	 */
	public Integer getMaxLevel(long demId){
		return (Integer)this.getOne("getMaxLevel", demId);
	}
	/**
	 * 根据维度Id获取该维度下所有类型。
	 * @param demId
	 * @return
	 */
	public List<SysOrgType> getByDemId(long demId){
		return this.getBySqlKey("getByDemId", demId);
	}
}