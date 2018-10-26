package com.hotent.platform.service.worktime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.util.TimeUtil;
import com.hotent.platform.dao.bpm.HistoryProcessInstanceDao;
import com.hotent.platform.dao.bpm.HistoryTaskInstanceDao;
import com.hotent.platform.dao.system.SysOrgDao;
import com.hotent.platform.dao.worktime.CalendarAssignDao;
import com.hotent.platform.dao.worktime.CalendarSettingDao;
import com.hotent.platform.dao.worktime.SysCalendarDao;
import com.hotent.platform.model.system.SysOrg;
import com.hotent.platform.model.worktime.CalendarAssign;
import com.hotent.platform.model.worktime.CalendarSetting;
import com.hotent.platform.model.worktime.OverTime;
import com.hotent.platform.model.worktime.SysCalendar;
import com.hotent.platform.model.worktime.WorkTime;


/**
 * 对象功能:日历分配 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2012-02-20 09:25:51
 */
@Service
public class CalendarAssignService extends BaseService<CalendarAssign>
{
	@Resource
	private CalendarAssignDao dao;
	
	@Resource
	private SysOrgDao sysOrgDao;
	
	@Resource
	private SysCalendarDao sysCalendarDao;
	
	@Resource
	private CalendarSettingService calendarSettingService;
	
	@Resource
	private CalendarSettingDao calendarSettingDao;
	
	@Resource
	private OverTimeService overTimeService;
	
	@Resource
	private HistoryProcessInstanceDao historyProcessInstanceDao;
	
	@Resource
	private HistoryTaskInstanceDao historyTaskInstanceDao;
	
	public CalendarAssignService()
	{
	}
	
	@Override
	protected IEntityDao<CalendarAssign, Long> getEntityDao() 
	{
		return dao;
	}
	
	/**
	 * 取日历设置。
	 * <pre>
	 * 	1.根据个人获取日历。
	 *  2.没有获取到则获取部门的日历。
	 *  3.部门也没有设置的情况，获取默认的日历。
	 *  4.没有获取到则返回为空。
	 * </pre>
	 * @param userId
	 * @return
	 */
	public Long getCalendarIdByUserId(Long userId){ 
		CalendarAssign calendarAssign =dao.getByAssignId(CalendarAssign.User, userId);
		if(calendarAssign==null){
			SysOrg sysOrg = sysOrgDao.getPrimaryOrgByUserId(userId);
			if(sysOrg!=null){
				long orgId=sysOrg.getOrgId();
				calendarAssign =dao.getByAssignId(CalendarAssign.Organization, orgId);
			}
		}
		if(calendarAssign!=null){
			return calendarAssign.getCanlendarId();
		}
		//获取默认的日历。
		SysCalendar sysCalendar=sysCalendarDao.getDefaultCalendar();
		if(sysCalendar!=null){
			return sysCalendar.getId();
		}
		//再取默认的日历
		return 0L;
	}
	/**
	 * 根据日历列表获取相应的工作时间分段列表
	 * @param list
	 * @return
	 */
	private List<WorkTime> getBycalList(List<CalendarSetting> list) {
		List<WorkTime> tmplist = new ArrayList<WorkTime>();
		List<WorkTime> worklist = new ArrayList<WorkTime>();
		for (CalendarSetting setting : list) {
			String calDay = setting.getCalDay();
			List<WorkTime> workTimeList = setting.getWorkTimeList();
			for (WorkTime work : workTimeList) {
				work.setCalDay(calDay);
				tmplist.add((WorkTime) work.clone());
			}
		}
		int len = tmplist.size();
		for (int i = 0; i < len; i++) {
			WorkTime workTime = tmplist.get(i);

			if (i < len - 1) {
				int j = i + 1;
				WorkTime nextTime = tmplist.get(j);
				// 本班次结束时间比下一班次开始时间大
				if (workTime.getEndDateTime().compareTo(
						nextTime.getStartDateTime()) > 0) {
					workTime.setEndDateTime(nextTime.getEndDateTime());//方便计算工作时长
					worklist.add(workTime);
					i++;
				} else {
					worklist.add(workTime);
				}
			} else {
				worklist.add(workTime);
			}
		}
		return worklist;
	}
	
	
	/**
	 * 与getRealWorkTime重复
	 * 需求：根据用户ID，任务开始时间，任务结束时间获取实际的工作时间
	 * 步骤：
	 * 根据用户ID取得用户工作日历
	 * 根据开始时间，结束时间取得日历列表片段
	 * 遍历日历列表根据calendarId获得工作时段列表
	 * 根据任务开始时间，任务结束时间，工作时间获得实际工作时间
	 * @param startDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	@Deprecated
	public String getTaskTime(Date startDate,Date endDate,long userId){
//		//任务执行时的正常工作时间
//		long taskTime=getTaskMillsTime(startDate, endDate, userId);
//		//在任务执行时间的加班时间
//		long overTime=getOverTime(userId, startDate, endDate);
//		long leaveTime=getLeaveTime(userId, startDate, endDate);
//		//在任务执行时间的请假时间
//		if((taskTime+overTime)<=leaveTime){
//			return TimeUtil.getTime(endDate.getTime()-startDate.getTime());
//		}
//		long workTime=taskTime+overTime-leaveTime;
//		return TimeUtil.getTime(workTime);
		long workTime=getRealWorkTime(startDate,endDate,userId);
		return TimeUtil.getTime(workTime);
	}
	
	
	/**需求：根据用户ID，任务开始时间，任务结束时间获取实际的工作时间
	 * 步骤：
	 * 根据用户ID取得用户工作日历
	 * 根据开始时间，结束时间取得日历列表片段
	 * 遍历日历列表根据calendarId获得工作时段列表
	 * 根据任务开始时间，任务结束时间，工作时间获得实际工作时间
	 * @param startDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	public Long getRealWorkTime(Date startTime,Date endTime,Long userId){
		if(userId==null){
			userId=0L;
		}
		//任务执行时的正常工作时间
		long taskTime=getTaskMillsTime(startTime, endTime, userId);
		//在任务执行时间的加班时间
		long overTime=getOverTime(userId, startTime, endTime);
		long leaveTime=getLeaveTime(userId, startTime, endTime);
		//在任务执行时间的请假时间
		if((taskTime+overTime)<=leaveTime){
			return endTime.getTime()-startTime.getTime();
		}
		long workTime=taskTime+overTime-leaveTime;
		return workTime;
	}
	
	
	/**
	 * 正常工作时间
	 * 根据用户ID，任务开始时间，任务结束时间获取实际的工作时间的毫秒数
	 * @param startDate
	 * @param endDate
	 * @param userId
	 * @return
	 */
	public long getTaskMillsTime(Date startDate,Date endDate,long userId){
		String start=TimeUtil.getDateString(startDate);
		String end=TimeUtil.getDateString(endDate);
		//根据用户ID获得日历。
		Long calendarId=getCalendarIdByUserId(userId);
		if(calendarId==0){
			return endDate.getTime()-startDate.getTime();
		}
		//根据开始时间，结束时间，日历ID得到时间段内的日历分段列表
		List<CalendarSetting> list=calendarSettingDao.getSegmentByCalId(calendarId, start, end);
		if(BeanUtils.isEmpty(list)){
			return endDate.getTime()-startDate.getTime();
		}
		//获得工作时间列表
		List<WorkTime> worklist=getBycalList(list);
		//实际的工作时间
		long taskTime=0L;
		int leng=worklist.size();
		for(int i=0;i<leng;i++){
			WorkTime workTime=worklist.get(i);
            //工作的开始时间			
			Date startWorkTime=workTime.getStartDateTime();
			//工作结束时间
			Date endWorkTime=workTime.getEndDateTime();
			//任务开始开始时间在工作时间之前
			if(startDate.compareTo(startWorkTime)<0){
				//任务结束时间在工作时间内
				if(endDate.compareTo(startWorkTime)>=0&&endDate.compareTo(endWorkTime)<=0){
					taskTime+=(endDate.getTime()-startWorkTime.getTime());
				}
				//任务结束时间在工作时间结束之后
				else if(endDate.compareTo(endWorkTime)>0){
					taskTime+=(endWorkTime.getTime()-startWorkTime.getTime());
				}
			}
			//任务开始时间在工作时间内
			else if(startDate.compareTo(startWorkTime)>=0&&startDate.compareTo(endWorkTime)<=0){
				//任务结束时间在工作时间内
				 if(endDate.compareTo(endWorkTime)<=0){
					 taskTime+=(endDate.getTime()-startDate.getTime());
				 }
				 //任务结束时间在工作时间外
				 else{
					 taskTime+=(endWorkTime.getTime()-startDate.getTime());
				 }
			}
		}
		return taskTime;
	}
	
	/**
	 * 用户在任务执行时间内的加班时间的毫秒数
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public long getOverTime(long userId,Date startTime,Date endTime){
		long overTime=0L;
		List<OverTime> listOverTime=overTimeService.getListByUserId(userId,1,startTime,endTime);
		if(listOverTime!=null){
			for(OverTime workTime:listOverTime){
				Date start=workTime.getStartTime();
				Date end=workTime.getEndTime();
				//任务开始开始时间在加班时间之前
				if(startTime.compareTo(start)<0){
					//任务结束时间在加班时间内
					if(endTime.compareTo(start)>=0&&endTime.compareTo(end)<=0){
						overTime+=(endTime.getTime()-start.getTime());
					}
					//任务结束时间在加班时间结束之后
					else if(endTime.compareTo(end)>0){
						overTime+=(end.getTime()-start.getTime());
					}
				}
				//任务开始时间在加班时间内
				else if(startTime.compareTo(start)>=0&&startTime.compareTo(end)<=0){
					//任务结束时间在加班时间内
					 if(endTime.compareTo(end)<=0){
						 overTime+=(endTime.getTime()-startTime.getTime());
					 }
					 //任务结束时间在加班时间外
					 else{
						 overTime+=(end.getTime()-start.getTime());
					 }
				}
			}
		}
		
		return overTime;
	}
	
	/**
	 * 用户在任务执行时间内的请假时间的毫秒数
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public long getLeaveTime(long userId,Date startTime,Date endTime){
		long leaveTime=0L;
		List<OverTime> listLeaveTime=overTimeService.getListByUserId(userId,2,startTime,endTime);
		if(listLeaveTime!=null){
            //得到实际请假时间列表
			List<OverTime>realLeaveTime=getRealLeaveList(listLeaveTime,startTime,endTime);
			for(OverTime leave:realLeaveTime){
			
				Date start=leave.getStartTime();
				Date end=leave.getEndTime();
				//根据用户ID获得日历
				//任务开始开始时间在请假时间之前
				if(startTime.compareTo(start)<0){
					//任务结束时间在请假时间内
					if(endTime.compareTo(start)>=0&&endTime.compareTo(end)<=0){
						leaveTime+=(endTime.getTime()-start.getTime());
					}
					//任务结束时间在请假时间结束之后
					else if(endTime.compareTo(end)>0){
						leaveTime+=(end.getTime()-start.getTime());
					}
				}
				//任务开始时间在请假时间内
				else if(startTime.compareTo(start)>=0&&startTime.compareTo(end)<=0){
					//任务结束时间在请假时间内
					 if(endTime.compareTo(end)<=0){
						 leaveTime+=(endTime.getTime()-startTime.getTime());
					 }
					 //任务结束时间在请假时间外
					 else{
						 leaveTime+=(end.getTime()-startTime.getTime());
					 }
				}
			}
		}
		return leaveTime;
	}
	
	/**
	 * 根据用户的请假表和工作时间表 得到实际的请假时间列表
	 * @param leaveList
	 * @return
	 */
	//XXX startTime endTime没有用到？？？
	public List<OverTime> getRealLeaveList(List<OverTime> leaveList,Date startTime,Date endTime){
		List<OverTime> realList=new ArrayList<OverTime>();
		for(OverTime leave:leaveList){
			Date start=leave.getStartTime();
			Date end=leave.getEndTime();
			long userId=leave.getUserId();
			Long calendarId=getCalendarIdByUserId(userId);
			if(calendarId==0){
				return leaveList;
			}
			//根据开始时间，结束时间，日历ID得到时间段内的日历分段列表
			List<CalendarSetting> list=calendarSettingDao.getSegmentByCalId(calendarId, TimeUtil.getDateString(start), TimeUtil.getDateString(end));
			//如果日志分段列表为空，则任务执行时间和请假时间进行比较得到在任务执行时间内的请假时间
			if(BeanUtils.isEmpty(list)){
				return leaveList;
			}else{
				//获得工作时间列表
				List<WorkTime> workList=getBycalList(list);
				if(BeanUtils.isEmpty(workList)){
					return leaveList;
				}
				//根据工作时间获得实际的请假时间表
				for(WorkTime workTime:workList){
					leave=new OverTime();
					Date startWork=workTime.getStartDateTime();
					Date endWork=workTime.getEndDateTime();
					//请假开始开始时间在工作时间之前
					if(start.compareTo(startWork)<0){
						//请假结束时间在工作时间内
						if(end.compareTo(startWork)>=0&&end.compareTo(endWork)<=0){
							leave.setStartTime(startWork);
							leave.setEndTime(end);
						}
						//请假结束时间在工作时间结束之后
						else if(end.compareTo(endWork)>0){
							leave.setStartTime(startWork);
							leave.setEndTime(endWork);
						}
					}
					//请假开始时间在工作时间内
					else if(start.compareTo(startWork)>=0&&start.compareTo(endWork)<=0){
						//请假结束时间在工作时间内
						 if(end.compareTo(endWork)<=0){
							 leave.setStartTime(start);
							 leave.setEndTime(end);
						 }
						 //请假结束时间在工作时间外
						 else{
							 leave.setStartTime(start);
							 leave.setEndTime(endWork);
						 }
					}
					if(leave.getEndTime()!=null&&leave.getStartTime()!=null){
						realList.add(leave);
					}
				}
			}
		}
		return realList;
	}
	
	/**
	 * 根据日历ID,用户ID,开始时间取得实际工作时间列表
	 * <pre>
	 * <b>
	 * 判断流程
	 * </b>
	 * 用户最多有一个日历
	 * 1.首先根据用户Id查找日历，如果查找不到日历，则判断1.1。
	 * 	1.1.根据用户ID查询用户所属的组织，根据组织Id获取日历。
	 * 2.根据用户Id，开始时间，和工作变更类型查找与开始时间相关（在开始时间之后，开始时间在工作变更时间段内）的工作时间变更记录
	 * 3.如果用户在任务开始时间内有请假情况 ，则重新比较时间 更新工作时间表
	 * 4.如果用户在任务开始时间内有加班情况 ，则把加班时间列表加入到工作时间表中
	 * <pre>
	 * @param calendarId 日历ID
	 * @param userId  用户ID
	 * @param startTime  开始时间
	 * @return
	 */
	public List<WorkTime> getRealWorkList(long calendarId,long userId,Date startTime){
		List<OverTime> overTimeList=overTimeService.getListByStart(startTime,userId,1);
		List<OverTime> leaveTimeList=overTimeService.getListByStart(startTime,userId,2);
		List<WorkTime> worklist= calendarSettingService.getByCalendarId(calendarId,startTime);
		//实际的工作列表
		List<WorkTime> realWorklist=new ArrayList<WorkTime>();
		if(leaveTimeList.size()==0){
			realWorklist.addAll(worklist);
		}else{
			for(OverTime leave:leaveTimeList){
				Date start=leave.getStartTime();
				Date end=leave.getEndTime();
				for(WorkTime workTime:worklist){
					Date startWork=workTime.getStartDateTime();
					Date endWork=workTime.getEndDateTime();
					//请假开始开始时间在工作时间之前
					if(start.compareTo(startWork)<=0){
						//请假结束时间在工作时间内
						if(end.compareTo(startWork)>=0&&end.compareTo(endWork)<=0){
							workTime.setStartDateTime(end);
							workTime.setEndDateTime(endWork);
							realWorklist.add(workTime);
						}//请假结束时间在工作时间结束之前
						else if(end.compareTo(endWork)<=0){
							realWorklist.add(workTime);
						}else{
							logger.info(workTime.getCalDay()+",请假了。。。");
						}
						
					}
					//请假开始时间在工作时间内
					else if(start.compareTo(startWork)>0&&start.compareTo(endWork)<=0){
						//请假结束时间在工作时间内
						 if(end.compareTo(endWork)<=0){
							 workTime.setStartDateTime(startWork);
							 workTime.setEndDateTime(start);
							 realWorklist.add(workTime);
							 WorkTime work=new WorkTime();
							 work.setStartDateTime(end);
							 work.setEndDateTime(endWork);
							 realWorklist.add(work);
						 }
						 //请假结束时间在工作时间外
						 else{
							 workTime.setStartDateTime(startWork);
							 workTime.setEndDateTime(start);
							 realWorklist.add(workTime);
						 }
					}else{//请假时间在工作时间外
						realWorklist.add(workTime);
					}
				}
			}
		}
		if(overTimeList.size() > 0){
			for(OverTime oTime:overTimeList){
				Date start=oTime.getStartTime();
				Date end=oTime.getEndTime();
				WorkTime work=new WorkTime();
				work.setStartDateTime(start);
				realWorklist.add(work);
			}
		}
		return realWorklist;
	}
	
	/**
	 * 返回系统默认工作日历的工作时段
	 * @param startDate 开始时间
	 * @return
	 */
	public List<WorkTime> getTaskTimeByDefault(Date startDate){
		SysCalendar sysCalendar=sysCalendarDao.getDefaultCalendar();
		if(sysCalendar!=null){
			Long calendarId = sysCalendar.getId();
			List<WorkTime> worklist= calendarSettingService.getByCalendarId(calendarId,startDate);
			return worklist;
		}	
		return null;	
	}
	
	/**
	 * 获取任务相对开始时间
	 * @param actInstanceId 流程实例ID
	 * @param nodeId 相对节点ID
	 * @param eventType 相对节点的事件类型0:创建,1:完成
	 * @return
	 */
	public Date getRelativeStartTime(String actInstanceId,String nodeId,Integer eventType){
		HistoricTaskInstanceEntity historicTaskInstanceEntity = historyTaskInstanceDao.getByInstanceIdAndNodeId(actInstanceId, nodeId);
		if(historicTaskInstanceEntity!=null){
			if(new Integer(0).equals(eventType))
				return historicTaskInstanceEntity.getStartTime();
			else
				return historicTaskInstanceEntity.getEndTime();
		}
		HistoricProcessInstanceEntity historicProcessInstanceEntity = historyProcessInstanceDao.getByInstanceIdAndNodeId(actInstanceId,nodeId);
		if(historicProcessInstanceEntity!=null)
			return historicProcessInstanceEntity.getStartTime();
		return null;
	}
	
	/**
	 * 根据开始时间，工时和用户ID取得任务实际的开始时间。
	 * <pre>
	 * <b>
	 * 判断流程
	 * </b>
	 * 用户最多有一个日历
	 * 1.首先根据用户Id查找日历，如果查找不到日历，则判断1.1。
	 * 	1.1.根据用户ID查询用户所属的组织，根据组织Id获取日历。
	 * 2.如果找到分配的日历则根据日历计算，任务起始时间。
	 * 3.未找到日历的情况直接计算起始时间。
	 * 4.当传入的userId为0时，返回按系统默认工作日历的任务实际开始时间。
	 * </pre>
	 * @param startDate		任务开始时间
	 * @param minutes		工时，使用分钟计数
	 * @param userId		用户ID
	 * @return
	 */
	public Date getTaskTimeByUser(Date startDate,int minutes,long userId){
		int remainMinute=0;
		List<WorkTime> list = null;
		//未传入用户ID时获取系统默认工作日历的时段
		if(userId == 0){
			list = getTaskTimeByDefault(startDate);
		}
		else{
			Long calendarId= getCalendarIdByUserId(userId);
			//没有找到日历直接返回日期。
			if(calendarId==0L){
				return new Date( TimeUtil.getNextTime(TimeUtil.MINUTE, minutes, startDate.getTime()));
			}
			//根据日历取得工作时间表段包括请假/加班情况。
			list= getRealWorkList(calendarId, userId, startDate);
		}	
		if(list==null)
			return new Date( TimeUtil.getNextTime(TimeUtil.MINUTE, minutes, startDate.getTime()));
		boolean isBegin=false;
		Date endTime=null;
		for(WorkTime workTime:list){
			Date startTime=workTime.getStartDateTime();
			endTime=workTime.getEndDateTime();
			//判断传入的时间是否在时间段之间。
			if(startDate.compareTo(startTime)>=0 && endTime.compareTo(startDate)>0){
				isBegin=true;
				int seconds=TimeUtil.getSecondDiff(endTime, startDate);
				int minute=(int)(seconds /60);
				remainMinute=minutes-minute;
				if(remainMinute<=0){
					return new Date( TimeUtil.getNextTime(TimeUtil.MINUTE, minutes, startDate.getTime()));
				}
				continue;
			}else if(startDate.compareTo(startTime)<0&&remainMinute==0){
				isBegin=true;
				int seconds=TimeUtil.getSecondDiff(endTime, startTime);
				int minute=(int)(seconds /60);
				remainMinute=minutes-minute;
				if(remainMinute<=0){
					return new Date( TimeUtil.getNextTime(TimeUtil.MINUTE, minutes, startTime.getTime()));
				}
			}
			//根据工时计算时间点。
			if(isBegin){
				Date tmpDate=new Date( TimeUtil.getNextTime(TimeUtil.MINUTE,remainMinute,startTime.getTime()));
				if(tmpDate.compareTo(endTime)>0){
					int seconds=TimeUtil.getSecondDiff(endTime, startTime);
					int minute=(int)(seconds /60);
					remainMinute=remainMinute-minute;
				}
				else{
					return new Date( TimeUtil.getNextTime(TimeUtil.MINUTE, remainMinute, startTime.getTime()));
				}
			}
		}
		if(remainMinute>0 && endTime!=null){
			return new Date( TimeUtil.getNextTime(TimeUtil.MINUTE, remainMinute, endTime.getTime()));
		}
		return new Date( TimeUtil.getNextTime(TimeUtil.MINUTE, minutes, startDate.getTime()));
	}
	
	
	
	/**
	 * 取被分配的类型,用户或组织
	 * @return
	 */
	public List<Map> getAssignUserType(){
		List<Map> list = new ArrayList<Map>();
		
		Map map = new HashMap();
		map.put("id", "1");
		map.put("name", "用户");
		list.add(map);
		
		map = new HashMap();
		map.put("id", "2");
		map.put("name", "组织");
		list.add(map);
		
		return list;
	}
	
	/**
	 * 根据日历id删除记录
	 * @param calId
	 */
	public void delByCalId(Long[] calIds){
		dao.delByCalId(calIds);
	}
	
	/**
	 * 根据用户ID得到唯一条分配信息
	 * @param assignId
	 * @return
	 */
	public CalendarAssign getbyAssignId(Long assignId){
		return dao.getbyAssignId(assignId);
	}
	
}
