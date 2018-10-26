package com.hotent.core.scheduler;

import java.util.Date;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.platform.dao.system.SysJobLogDao;
import com.hotent.platform.model.system.SysJobLog;



/**
 * 任务执行基类，这个类采用模版模式进行实现。
 * <br>子类继承这个类后，任务执行的日志就会自动记录下来，不需要在子类中在进行记录。
 * 
 */
@DisallowConcurrentExecution
public abstract class BaseJob implements Job {
	
	public BaseJob()
	{}
	
	public abstract void executeJob(JobExecutionContext context) throws Exception;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	

 
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//JobDataMap map= context.getJobDetail().getJobDataMap();
		String jobName=context.getJobDetail().getKey().getName();
		
		String trigName="directExec";
		Trigger trig=context.getTrigger();
		if(trig!=null)
			trigName=trig.getKey().getName();
		Date strStartTime=new Date();
		long startTime=System.currentTimeMillis();
		try
		{
			executeJob(context);
			long endTime=System.currentTimeMillis();
			Date strEndTime=new Date();
			//记录日志
			long runTime=(endTime-startTime) /1000;
			addLog(jobName, trigName, strStartTime, strEndTime, runTime, "任务执行成功!", 1);
		}
		catch(Exception ex)
		{
			long endTime=System.currentTimeMillis();
			Date strEndTime=new Date();
			long runTime=(endTime-startTime) /1000;
			try {
				addLog(jobName, trigName, strStartTime, strEndTime, runTime,ex.toString(),0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("执行任务出错:" + e.getMessage());
			}
		}
	}


	private void addLog(String jobName, String trigName, Date strStartTime,
			Date strEndTime, long runTime,String content,int state) throws Exception {
		SysJobLogDao dao=(SysJobLogDao)BeanUtils.getBean(SysJobLogDao.class);
		
		SysJobLog model=new SysJobLog();
		
		Long id=UniqueIdUtil.genId();
		
		model.setLogId(id);
		model.setJobName(jobName);
		model.setTrigName(trigName);
		model.setStartTime(strStartTime);
		model.setEndTime(strEndTime);
		model.setContent(content);
		model.setState(state);
		model.setRunTime(runTime);
		dao.add(model);
	}
	
	

}
