/**
 * 对象功能:系统分类键定义 Dao类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:csx
 * 创建时间:2012-03-10 10:18:36
 */
package com.hotent.platform.dao.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.hotent.core.db.BaseDao;
import com.hotent.platform.model.system.SysTypeKey;

@Repository
public class SysTypeKeyDao extends BaseDao<SysTypeKey>
{
	@SuppressWarnings("rawtypes")
	@Override
	public Class getEntityClass()
	{
		return SysTypeKey.class;
	}
	
	/**
	 * 按Key取得唯一键值
	 * @param key
	 * @return
	 */
	public SysTypeKey getByKey(String key){
		System.out.println(key);
		
		key=key.toLowerCase();
		return getUnique("getByKey", key);
	}

	
	/**
	 * 检查某个Key是否存在
	 * @param typeKey
	 * @param typeId 除本Key外
	 * @return
	 */
	public boolean isExistKey(String typeKey){
		Integer sn=(Integer)getOne("isExistKey", typeKey);
		return sn>0;
	}
	
	/**
	 * 检查某个Key是否存在
	 * @param typeKey
	 * @param typeId 除本Key外
	 * @return
	 */
	public boolean isKeyExistForUpdate(String typeKey,Long typeId){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("typeKey", typeKey);
		params.put("typeId", typeId);
		Integer sn=(Integer)getOne("isKeyExistForUpdate", params);
		return sn>0;
	}
	
	/**
	 * 更新排序。
	 * @param typeId
	 * @param sn
	 */
	public void updateSequence(Long typeId,int sn){
		SysTypeKey sysTypeKey=new SysTypeKey();
		sysTypeKey.setTypeId(typeId);
		sysTypeKey.setSn(sn);
		this.update("updateSequence", sysTypeKey);
	}
	
	
}