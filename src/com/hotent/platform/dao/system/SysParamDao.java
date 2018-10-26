/**
 * 对象功能:组织或人员参数属性 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-02-23 17:43:35
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysParam;

@Repository
public class SysParamDao extends BaseDao<SysParam>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysParam.class;
	}
	
	/**
	 * 取得用户参数列表。
	 * @return
	 */
	public List<SysParam> getUserParam(){
		Map p=new HashMap();
		p.put("effect", SysParam.EFFECT_USER);
		return this.getBySqlKey("getUserStatus", p);	
	}
	
	/**
	 * 取得生效的用户参数列表。
	 * @return
	 */
	public List<SysParam> getStatusParam(){
		Map p=new HashMap();
		p.put("effect", SysParam.EFFECT_USER);
		return this.getBySqlKey("getStatusParam", p);	
	}
	
	/**
	 * 取得组织参数。
	 * @return
	 */
	public List<SysParam> getOrgParam(long demId){
		Map p=new HashMap();
		p.put("effect", SysParam.EFFECT_ORG);
		return this.getBySqlKey("getDemStatus",demId);	}

	/**
	 * 取得组织参数。
	 * @return
	 */
	public List<SysParam> getOrgParam(){
		Map p=new HashMap();
		p.put("effect", SysParam.EFFECT_ORG);
		return this.getBySqlKey("getAll", p);	
	}
	
	
	public SysParam getByParamKey(String paramKey){
		return this.getUnique("getByParamKey", paramKey);
	}
	/**
	 * 
	 * @param type 1 用户
	 * @param dimId 2 维度Id
	 * @return
	 */
	public List<String> getDistinctCategory(Integer type, Long dimId) {
		if(type == null || type == 1) dimId = null; 
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		map.put("effect", type);
		map.put("belongDim", dimId);
		return getBySqlKeyGenericity("getDistinctCategory",map);
	}

}