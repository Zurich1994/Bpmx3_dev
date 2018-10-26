package com.hotent.platform.job;

import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.monitorQuota.model.monitorQuotaPac.MonitorQuota;
import com.hotent.monitorQuota.service.monitorQuotaPac.MonitorQuotaService;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;
import com.hotent.monitorRecords.service.monitorRecordsPac.MonitorRecordsService;

import edu.hrbeu.snmp.GetCPUUsage;
import edu.hrbeu.snmp.GetNetStatus;

public class Monitor_NetStatus extends BaseJob {


	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		MonitorRecordsService monitorRecordsService = (MonitorRecordsService) AppUtil.getBean(MonitorRecordsService.class);
		String deviceIp = context.getJobDetail().getJobDataMap().getString("deviceIp");
		GetNetStatus getNetStatus = new GetNetStatus(deviceIp);
		//平均往返时间
		String aveTime = getNetStatus.getAverageTime();
		MonitorRecords mr = new MonitorRecords();
		mr.setId(UniqueIdUtil.genId());
		mr.setDevice_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("deviceId")));
		mr.setQuota_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("quotaId")));
		mr.setMonitorValue("平均往返时间："+aveTime);
		monitorRecordsService.add(mr);
		//服务成功率
	    String rate = getNetStatus.getSuccessRate();
	    String MonitorValue = "服务成功率："+rate+"%";
		MonitorRecords mr2 = new MonitorRecords();
		mr2.setId(UniqueIdUtil.genId());
		mr2.setDevice_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("deviceId")));
		mr2.setQuota_id(String.valueOf(context.getJobDetail().getJobDataMap().getLong("quotaId")));
		mr2.setMonitorValue(MonitorValue);
		monitorRecordsService.add(mr2);
	}

}
