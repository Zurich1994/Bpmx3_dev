package com.hotent.platform.job;

import java.util.List;

import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;
import com.hotent.monitorRecords.service.monitorRecordsPac.MonitorRecordsService;

import edu.hrbeu.snmp.DataAcquisition;

public class Monitor_custom extends BaseJob{

	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		// TODO Auto-generated method stub
		//获取单位
		MonitorQuotaService monitorQuotaService = AppUtil.getBean(MonitorQuotaService.class);
		long quotaId = context.getJobDetail().getJobDataMap().getLong("quotaId");
		MonitorQuota monitorQuota = monitorQuotaService.getById(quotaId);
		String unit = monitorQuota.getUnit();
		if(unit.equalsIgnoreCase("无"))
			unit="";
		
		MonitorRecordsService monitorRecordsService = (MonitorRecordsService) AppUtil.getBean(MonitorRecordsService.class);
		String deviceIp = context.getJobDetail().getJobDataMap().getString("deviceIp");
		String OID = context.getJobDetail().getJobDataMap().getString("OID");
		long snmp_port = context.getJobDetail().getJobDataMap().getLong("snmp_port");
		String community = context.getJobDetail().getJobDataMap().getString("community");
		DataAcquisition dataAcquisition = new DataAcquisition();
		dataAcquisition.setOid(OID);
		dataAcquisition.setTargetAddress(deviceIp,String.valueOf(snmp_port));
		dataAcquisition.setCommunity(community);
		dataAcquisition.init();
		List<String> result = dataAcquisition.getResultList();
		String MonitorValue = "";
		for(int i = 0;i < result.size();++i){
			MonitorValue += result.get(i) + unit+" ";
			
		}

		MonitorRecords mr = new MonitorRecords();
		mr.setId(UniqueIdUtil.genId());
		mr.setDevice_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("deviceId")));
		mr.setQuota_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("quotaId")));
		mr.setMonitorValue(MonitorValue);
		monitorRecordsService.add(mr);
	}

}
