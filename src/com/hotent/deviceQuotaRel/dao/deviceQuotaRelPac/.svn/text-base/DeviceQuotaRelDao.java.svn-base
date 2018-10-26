
package com.hotent.deviceQuotaRel.dao.deviceQuotaRelPac;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.core.model.BaseModel;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRel;
import com.hotent.deviceQuotaRel.model.deviceQuotaRelPac.DeviceQuotaRelAll;

@Repository
public class DeviceQuotaRelDao extends BaseDao<DeviceQuotaRel>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DeviceQuotaRel.class;
	}
	
	public List<DeviceQuotaRel> getMonQuaID(String deviceid){
		Map par = new HashMap();
		par.put("deviceid", deviceid);
		List<DeviceQuotaRel> list = getBySqlKey("getMonQuaID", par);
		return list;
	}
	public List<DeviceQuotaRel> getDevQuoRelID(String deviceid,String mqid){
		Map par = new HashMap();
		par.put("deviceid", deviceid);
		par.put("mqid", mqid);
		List<DeviceQuotaRel> list = getBySqlKey("getDevQuoRelID", par);
		return list;
	}
	public List<DeviceQuotaRelAll> getAllAll() {
		String statementName = getIbatisMapperNamespace() + ".getAllAll";
		return getSqlSessionTemplate().selectList(statementName, null);
	}
	
	public List<DeviceQuotaRelAll> getDevQuaRelByDevId(String deviceid) {
		String statementName = getIbatisMapperNamespace() + ".getDevQuaRelByDevId";
		return getSqlSessionTemplate().selectList(statementName, deviceid);
	}
	
	public int updateFreq(DeviceQuotaRel deviceQuotaRel) {
		String updStatement=getIbatisMapperNamespace() + ".updateFreq";		
		int affectCount = getSqlSessionTemplate().update(updStatement, deviceQuotaRel);
		return affectCount;	
	}
	
	public boolean exist(String deviceid, String quotaid) {
		Map par = new HashMap();
		par.put("deviceid", deviceid);
		par.put("quotaid", quotaid);
		List<DeviceQuotaRel> list = getBySqlKey("exist", par);
		if(list.size()==0) {
			return false;
		}
		else {
			return true;
		}
	}
	//根据设备id删除关联
	public void delBydevIds(Long[] ids){
		if(BeanUtils.isEmpty(ids)) return;
		for (long p : ids){
			delBydevId(p);
		}
	}
	public int delBydevId(Long id) {
		String delStatement=getIbatisMapperNamespace() + ".delBydevId";
		int affectCount = getSqlSessionTemplate().delete(delStatement, id);
		return affectCount;
	}
}
