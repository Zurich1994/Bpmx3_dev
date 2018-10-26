
package com.hotent.deviceRouter.dao.deviceRouter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fr.bi.cube.engine.third.edu.emory.mathcs.backport.java.util.Arrays;
import com.hotent.core.db.BaseDao;
import com.hotent.core.util.AppUtil;
import com.hotent.deviceRouter.model.deviceRouter.DeviceRouter;

@Repository
public class DeviceRouterDao extends BaseDao<DeviceRouter>
{
	@Override
	public Class<?> getEntityClass()
	{
		return DeviceRouter.class;
	}

	/**
	 * 获取指定设备信息。
	 * @param defId
	 * @param creatorId
	 * @param instanceAmount
	 * @return
	 */
	//zl...............................................
	public List<DeviceRouter> getByNodeIdANDActdefId(String actdefID,String nodeID){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("actdefID", actdefID);
		params.put("nodeID", nodeID);
		List<DeviceRouter> list = getBySqlKey("getByNodeIdANDActdefId", params);
		
		return list;
	}
	//获取所有IP
	@SuppressWarnings("unchecked")
	public List<String> getAllIP(){

		List<Map<String,Object>> allList = getAllRecord();
		int listSize = allList.size();
		String[] ipArray = new String[2 * listSize + 1];
		ipArray[0] = String.valueOf(listSize);
		for(int i = 1; i < listSize + 1; ++i){
			ipArray[i] = allList.get(i-1).get("F_MANAGE_IP").toString();
			ipArray[i + listSize] = allList.get(i-1).get("ID").toString();
		}
		List<String> ipList = Arrays.asList(ipArray);
		return ipList;
	}
	//获取所有记录
	public List<Map<String,Object>> getAllRecord(){

		JdbcTemplate template=(JdbcTemplate) AppUtil.getBean("jdbcTemplate");
		String sql="select F_MANAGE_IP,ID from W_DEVICE_ROUTER";
		List<Map<String,Object>> list=template.queryForList(sql);

		return list;
	}
}
