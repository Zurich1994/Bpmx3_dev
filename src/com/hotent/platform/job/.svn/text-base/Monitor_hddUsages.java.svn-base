package com.hotent.platform.job;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.monitorDevice.dao.monitorDevice.MonitorDeviceDao;
import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;
import com.hotent.monitorRecords.service.monitorRecordsPac.MonitorRecordsService;

import edu.hrbeu.snmp.GetStorageInfo;

public class Monitor_hddUsages extends BaseJob{

	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		// TODO Auto-generated method stub
		//获取单位
		MonitorQuotaService monitorQuotaService = AppUtil.getBean(MonitorQuotaService.class);
		long quotaId = context.getJobDetail().getJobDataMap().getLong("quotaId");
		MonitorQuota monitorQuota = monitorQuotaService.getById(quotaId);
		String unit = monitorQuota.getUnit();
		
		MonitorRecordsService monitorRecordsService = (MonitorRecordsService) AppUtil.getBean(MonitorRecordsService.class);
		MonitorDeviceDao monitorDao = AppUtil.getBean(MonitorDeviceDao.class);
		String deviceIp = context.getJobDetail().getJobDataMap().getString("deviceIp");
		String OID = context.getJobDetail().getJobDataMap().getString("OID");
		long snmp_port = context.getJobDetail().getJobDataMap().getLong("snmp_port");
		String community = context.getJobDetail().getJobDataMap().getString("community");
		GetStorageInfo getStorageInfo = new GetStorageInfo(deviceIp, String.valueOf(snmp_port), OID, community);
		String deviceId = String.valueOf(context.getJobDetail().getJobDataMap().getLong("deviceId"));
		//根据操作系统类型不同处理方式不同
		String ostype = monitorDao.getOSTYPEById(Long.valueOf(deviceId));
		Map<String,String> hddMap;
		String MonitorValue = "";
		if(ostype.equalsIgnoreCase("windows")){
			hddMap = getStorageInfo.getHardDiskRate();
			Iterator iter = hddMap.entrySet().iterator(); 
			while (iter.hasNext())
			{
				Entry entry = (Entry) iter.next(); 
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				MonitorValue = key + "="+val+unit;
				MonitorRecords mr = new MonitorRecords();
				mr.setId(UniqueIdUtil.genId());
				mr.setDevice_id(deviceId);
				mr.setQuota_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("quotaId")));
				mr.setMonitorValue(MonitorValue);
				monitorRecordsService.add(mr);
			}
		}else {
			hddMap = getStorageInfo.getLinuxStorageRate();
			String rootSize = hddMap.get("/");
			MonitorValue = "/=" + rootSize + unit;
			MonitorRecords mr = new MonitorRecords();
			mr.setId(UniqueIdUtil.genId());
			mr.setDevice_id(deviceId);
			mr.setQuota_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("quotaId")));
			mr.setMonitorValue(MonitorValue);
			monitorRecordsService.add(mr);
		}
	}

}