
package com.hotent.monitorDevice.dao.monitorDevice;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.hotent.core.db.BaseDao;
import com.hotent.deviceRouter.model.deviceRouter.DeviceRouter;
import com.hotent.monitorDevice.model.monitorDevice.MonitorDevice;

@Repository
public class MonitorDeviceDao extends BaseDao<MonitorDevice>
{
	@Override
	public Class<?> getEntityClass()
	{
		return MonitorDevice.class;
	}
	public String getIPById(Long id){
		
			String ip =  getBySqlKey("getIPById", id).get(0).toString();
			
		
		return ip ;
	}
	@SuppressWarnings("null")
	public List<String> getQuotaIDSById(Long id) {
		
		List<MonitorDevice> quatorIDSM =getBySqlKey("getQuotaIDSById", id);
		List<String> quatorIDS = null;
		for(MonitorDevice mon : quatorIDSM){
			String string = mon.toString();
			quatorIDS.add(string);
		}
		return quatorIDS;
	}
	
	public String getOIDById(Long id) {
		
	    String ID = getBySqlKey("getOIDById",id).get(0).toString();
		
		return ID;
	}
	
    public String getOSTYPEById(Long id) {
		
	    String ostype =(String) getBySqlKey("getOSTYPEById",id).get(0).toString();
		
		return ostype;
	}
}
