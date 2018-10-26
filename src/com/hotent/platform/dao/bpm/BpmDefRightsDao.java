/**
 * 对象功能:流程定义权限明细 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-19 09:00:53
 */
package com.hotent.platform.dao.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.bpm.BpmDefRights;

@Repository
public class BpmDefRightsDao extends BaseDao<BpmDefRights>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return BpmDefRights.class;
	}
	

	/**
	 * 根据流程定义ID和权限类型得到流程定义权限
	 * @param defId
	 * @param typeId
	 * @return
	 */
	public List<BpmDefRights> getDefRight(Long defId,Short rightType){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("defId", defId);
		params.put("rightType", rightType);
		params.put("searchType", BpmDefRights.SEARCH_TYPE_DEF);
		return getBySqlKey("getDefRight",params);
	}
	
	
	/**
	 * 根据流程分类和权限类型获取权限数据。
	 * @param typeId
	 * @param rightType
	 * @return
	 */
	public List<BpmDefRights> getTypeRight(Long typeId,Short rightType){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("typeId", typeId);
		params.put("rightType", rightType);
		params.put("searchType", BpmDefRights.SEARCH_TYPE_GLT);
		return getBySqlKey("getTypeRight",params);
	}
	
	/**
	 * 根据分类ID删除流程定义权限
	 * @param typeId
	 * @return
	 */
	public void delByTypeId(Long typeId){
		getBySqlKey("delByTypeId", typeId);
	}
	/**
	 * 根据流程定义ID删除权限。
	 * @param defId
	 */
	public void delByDefKey(String defKey){
		this.delBySqlKey("delByDefKey", defKey);
	}
	
	/**
	 * 根据流程ID获取权限。
	 * @param defId
	 * @return
	 */
	public List<BpmDefRights> getByDefKey(String defKey){
		return this.getBySqlKey("getByDefKey", defKey);
	}
	
	/**
	 * 根据分类id获取权限。
	 * @param typeId
	 * @return
	 */
	public List<BpmDefRights> getByTypeId(Long typeId){
		return this.getBySqlKey( "getByTypeId", typeId);
	}
	
	
}