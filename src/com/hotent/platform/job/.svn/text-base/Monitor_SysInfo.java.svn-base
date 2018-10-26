package com.hotent.platform.job;

import java.util.List;

import org.quartz.JobExecutionContext;

import com.hotent.core.scheduler.BaseJob;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.monitorRecords.model.monitorRecordsPac.MonitorRecords;
import com.hotent.monitorRecords.service.monitorRecordsPac.MonitorRecordsService;

import edu.hrbeu.snmp.GetSysInfo;

public class Monitor_SysInfo  extends BaseJob{

	@Override
	public void executeJob(JobExecutionContext context) throws Exception {
		// TODO Auto-generated method stub
		MonitorRecordsService monitorRecordsService = (MonitorRecordsService) AppUtil.getBean(MonitorRecordsService.class);
		String deviceIp = context.getJobDetail().getJobDataMap().getString("deviceIp");
		String OID = context.getJobDetail().getJobDataMap().getString("OID");
		long snmp_port = context.getJobDetail().getJobDataMap().getLong("snmp_port");
		String community = context.getJobDetail().getJobDataMap().getString("community");
		GetSysInfo getSysInfo = new GetSysInfo(deviceIp, String.valueOf(snmp_port), OID, community);
		String info = getSysInfo.getResult();
		//判断数据库中是否已经存在该系统信息
		String deviceId = String.valueOf(context.getJobDetail().getJobDataMap().getLong("deviceId"));
		String quotaId = String.valueOf(context.getJobDetail().getJobDataMap().getLong("quotaId"));
		List<MonitorRecords> list = monitorRecordsService.getBydeviceidAndquotaid(deviceId, quotaId);
		for(int i = 0;i<list.size();++i){
			MonitorRecords oldRecord = list.get(i);
			if (oldRecord.getMonitorValue().equals(info)) {
				return;
			}
		}
		MonitorRecords mr = new MonitorRecords();
		mr.setId(UniqueIdUtil.genId());
		mr.setDevice_id(deviceId);
		mr.setQuota_id(quotaId);
		mr.setMonitorValue(String.valueOf(info));
		monitorRecordsService.add(mr);
	}

}
