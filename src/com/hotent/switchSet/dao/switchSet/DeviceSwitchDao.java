
package com.hotent.switchSet.dao.switchSet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fr.bi.cube.engine.third.edu.emory.mathcs.backport.java.util.Arrays;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.AppUtil;
import com.hotent.switchSet.model.switchSet.DeviceSwitch;

@Repository
public class DeviceSwitchDao extends BaseDao<DeviceSwitch>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DeviceSwitch.class;
	}
	/**
	 * 获取指定节点记录。
	 * @param defId
	 * @param creatorId
	 * @param instanceAmount
	 * @return
	 */
	//zl...............................................
	public List<DeviceSwitch> getByNodeIdANDActdefId(String actdefID,String nodeID){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actdefid", actdefID);
		params.put("nodeid", nodeID);
		List<DeviceSwitch> list = getBySqlKey("getByNodeIdANDActdefId", params);
		
		return list;
	}
	//获取所有IP
	//zl...............................................
	@SuppressWarnings("unchecked")
	public List<String> getAllIP(){

		List<Map<String,Object>> allList = getAllRecord();
		int listSize = allList.size();
		String[] ipArray = new String[2 * listSize + 1];
		ipArray[0] = String.valueOf(listSize);
		for(int i = 1; i < listSize + 1; ++i){
			ipArray[i] = allList.get(i-1).get("F_manage_IP").toString();
			ipArray[i + listSize] = allList.get(i-1).get("ID").toString();
		}
		List<String> ipList = Arrays.asList(ipArray);
		return ipList;
	}
	//zl...............................................
	//获取所有记录
	public List<Map<String,Object>> getAllRecord(){

		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_manage_IP,ID from W_DEVICE_SWITCH";
		List<Map<String,Object>>list=template.queryForList(sql);

		return list;
	}
}
