package com.hotent.platform.job;

import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;
import com.hotent.monitorRecords.service.monitorRecordsPac.MonitorRecordsService;

import edu.hrbeu.snmp.GetStorageInfo;

public class Monitor_PhyMemoryAvail extends BaseJob{

	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		// TODO Auto-generated method stub
		//获取单位
		MonitorQuotaService monitorQuotaService = AppUtil.getBean(MonitorQuotaService.class);
		long quotaId = context.getJobDetail().getJobDataMap().getLong("quotaId");
		MonitorQuota monitorQuota = monitorQuotaService.getById(quotaId);
		String unit = monitorQuota.getUnit();
		
		MonitorRecordsService monitorRecordsService = (MonitorRecordsService) AppUtil.getBean(MonitorRecordsService.class);
		String deviceIp = context.getJobDetail().getJobDataMap().getString("deviceIp");
		String OID = context.getJobDetail().getJobDataMap().getString("OID");
		long snmp_port = context.getJobDetail().getJobDataMap().getLong("snmp_port");
		String community = context.getJobDetail().getJobDataMap().getString("community");
		GetStorageInfo getStorageInfo = new GetStorageInfo(deviceIp, String.valueOf(snmp_port), OID, community);
		long size = getStorageInfo.getPhyAvailSize();
		MonitorRecords mr = new MonitorRecords();
		mr.setId(UniqueIdUtil.genId());
		mr.setDevice_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("deviceId")));
		mr.setQuota_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("quotaId")));
		mr.setMonitorValue(String.valueOf(size) + unit);
		monitorRecordsService.add(mr);
	}

}
