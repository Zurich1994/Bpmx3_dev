package com.hotent.platform.service.system;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.system.SysPlanDao;
import com.hotent.platform.dao.system.SysPlanExchangeDao;
import com.hotent.platform.dao.system.SysPlanParticipantsDao;
import com.hotent.platform.dao.system.SysPlanSubscribeDao;
import com.hotent.platform.model.system.SysPlan;
import com.hotent.platform.model.system.SysPlanExchange;
import com.hotent.platform.model.system.SysPlanParticipants;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.model.system.UserUnder;

/**
 *<pre>
 * 对象功能:日程表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ray
 * 创建时间:2013-12-19 11:26:03
 *</pre>
 */
@Service
public class SysPlanService extends BaseService<SysPlan>
{
	@Resource
	private SysPlanDao sysPlanDao;

	@Resource
	private SysPlanParticipantsDao sysPlanParticipantsDao;
	
	@Resource
	private SysPlanExchangeDao sysPlanExchangeDao;
	
	@Resource
	private SysPlanSubscribeDao sysPlanSubscribeDao;
	
	@Resource
	private IUserUnderService iUserUnderService;
	
	public SysPlanService(){
	}
	
	@Override
	protected IEntityDao<SysPlan, Long> getEntityDao() {
		return sysPlanDao;
	}
	
	//查询我提交的日程
	public List<SysPlan> getBySubmitId(Long userId,Date startDate,Date endDate,Date theDate){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("submitId", userId);
		makeParamsMap(params, startDate, endDate, theDate);
		return sysPlanDao.getBySubmitId(params);
	};
	
	//查询我负责的日程
	public List<SysPlan> getByChargeId(Long userId,Date startDate,Date endDate,Date theDate){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("chargeId", userId);
		makeParamsMap(params, startDate, endDate, theDate);
		return sysPlanDao.getByChargeId(params);
	};
	
	//查询我参与的日程
	public List<SysPlan> getByParticipantId(Long userId,Date startDate,Date endDate,Date theDate){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("participantId", userId);
		makeParamsMap(params, startDate, endDate, theDate);
		return sysPlanDao.getByParticipantId(params);
	};
	
	//查询我订阅的日程(结合日程控件)
	public List<SysPlan> getBySubscribeId(Long userId,Date startDate,Date endDate,Date theDate){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("subscribeId", userId);
		makeParamsMap(params, startDate, endDate, theDate);
		return sysPlanDao.getBySubscribeId(params);
	};
	
	
	protected Map<String,Object> makeParamsMap(Map<String,Object> params,Date startDate,Date endDate,Date theDate) {
		if(params==null){
			params = new HashMap<String,Object>();
		}
		Date theFirstDay = null;
		Date theLastDay = null;
		if(theDate!=null){
			theFirstDay = DateUtil.getMonthStartDateTime(theDate);
			theLastDay = DateUtil.getMonthEndDateTime(theDate);
			params.put("theFirstDay", theFirstDay);
			params.put("theLastDay", theLastDay);
		}else if(startDate!=null&&endDate!=null){
			Calendar startCalendar=Calendar.getInstance();  
			startCalendar.setTime(startDate); 
			DateUtil.setStartDay(startCalendar);
			//theFirstDay = DateUtil.getMonthStartDateTime(startDate);
			//theLastDay = DateUtil.getMonthEndDateTime(endDate);
			params.put("theFirstDay", startCalendar.getTime());
			Calendar endCalendar=Calendar.getInstance();  
			endCalendar.setTime(endDate); 
			DateUtil.setEndDay(endCalendar);
			params.put("theLastDay", endCalendar.getTime());
		};
		
		return params;
	}
	
	
	//查询我订阅的日程
	public List<SysPlan> getListBySubscribeId(Long userId,QueryFilter queryFilter){
		if(queryFilter==null){
			return null;
		}
		queryFilter.addFilter("subscribeId", userId);
		return sysPlanDao.getListBySubscribeId(queryFilter);
	};
	
	//查询我下属的所有日程
	public List<SysPlan> getUnderSysPlanByUserId(Long userId,Long queryUserId, QueryFilter queryFilter){
		if(queryFilter==null){
			return null;
		}
		List<Long> userIds = new ArrayList<Long>();
		if(queryUserId!=null&&queryUserId>0){
			userIds.add(queryUserId);
			queryFilter.addFilter("userIds", userIds);
		}else{
			List<SysUser> list=iUserUnderService.getMyUnderUser(userId);
			if(list!=null&&list.size()>0){
				
				for (SysUser sysUser : list) {
					userIds.add(sysUser.getUserId());
				}
				queryFilter.addFilter("userIds", userIds);
			}
		}
		queryFilter.addFilter("userId", userId);
		return sysPlanDao.getByChargeUserIds(queryFilter);
	};
	
	//保存日程
	public String saveSysPlan(SysPlan sysPlan,Map<String,Object> params){
		String resultMsg="保存日程失败！";
		if(sysPlan==null){
			return resultMsg;
		}
		
		//如果是自定义类型的，以下属性都为空！
		if(sysPlan.getId()==null||sysPlan.getId()==0){
			sysPlan.setId(UniqueIdUtil.genId());
			sysPlanDao.add(sysPlan);
			resultMsg="新增日程成功！";
		}else{
			//先删除已有的参与者
			sysPlanParticipantsDao.delByPlanId(sysPlan.getId());
			//更新日程
			sysPlanDao.update(sysPlan);
			resultMsg="修改日程成功！";
		}
		
		if(params!=null){
			//插入参与者数据
			String participantIds = (String) params.get("participantIds");
			String participants = (String) params.get("participants");
			if(StringUtil.isNotEmpty(participantIds)&&StringUtil.isNotEmpty(participants)){
				String[] participantIdArry = participantIds.split("\\,");
				String[] participantArry = participants.split("\\,");
				for (int i = 0; i < participantIdArry.length; i++) {
					Long participantId = Long.valueOf(participantIdArry[i]);
					String participant = participantArry[i];
					SysPlanParticipants sysPlanParticipants = makeSysPlanParticipants(sysPlan.getId(), participantId, participant);
					sysPlanParticipantsDao.add(sysPlanParticipants);
				}
			}
		}
		
		return resultMsg;
	}
	
	protected SysPlanParticipants makeSysPlanParticipants(Long planId, Long participantId, String participant) {
		SysPlanParticipants sysPlanParticipants = new SysPlanParticipants();
		sysPlanParticipants.setId(UniqueIdUtil.genId());
		sysPlanParticipants.setPlanId(planId);
		sysPlanParticipants.setParticipantId(participantId);
		sysPlanParticipants.setParticipant(participant);
		return sysPlanParticipants;
	}
	
	//删除日程
	public String deleteSysPlans(String[] idArry){
		String resultMsg="删除日程失败！";
		if(idArry==null){
			return resultMsg;
		}
		//删除日程相关信息
		for (String idStr : idArry) {
			Long id = Long.valueOf(idStr);
            //删除日程			
			sysPlanDao.delById(id);
			//删除日程参与者
			sysPlanParticipantsDao.delByPlanId(id);
			//删除日程交流信息
			sysPlanExchangeDao.delByPlanId(id);
			//删除日程订阅者信息
			sysPlanSubscribeDao.delByPlanId(id);
			resultMsg = "删除日程成功！";
		}
		return resultMsg;
	}
	
	//保存日程交流信息
	public String saveSysPlanExchange(SysPlanExchange sysPlanExchange,Map<String,Object> params){
		String resultMsg="保存日程交流信息失败！";
		if(sysPlanExchange==null){
			return resultMsg;
		}
		//如果是自定义类型的，以下属性都为空！
		if(sysPlanExchange.getId()==null||sysPlanExchange.getId()==0){
			sysPlanExchange.setId(UniqueIdUtil.genId());
			sysPlanExchangeDao.add(sysPlanExchange);
			resultMsg="新增日程交流信息成功！";
		}else{
			//更新日程
			sysPlanExchangeDao.update(sysPlanExchange);
			resultMsg="修改日程交流信息成功！";
		}
		return resultMsg;
	}
	
	//删除日程交流信息
	public String deleteSysPlanExchanges(String[] idArry){
		String resultMsg="删除日程交流信息失败！";
		if(idArry==null){
			return resultMsg;
		}
		//删除日程交流相关信息
		for (String idStr : idArry) {
			Long id = Long.valueOf(idStr);
			sysPlanExchangeDao.delById(id);
			resultMsg = "删除日程交流信息成功！";
		}
		return resultMsg;
	}
	
	//更新日程状态（任务完成等）
	public String changeSysPlans(String[] idArry,Map<String,Object> params){
		String resultMsg=" 更新日程状态失败！";
		if(idArry==null){
			return resultMsg;
		}
		Long rate = (Long) params.get("rate");
		//删除日程相关信息
		for (String idStr : idArry) {
			Long id = Long.valueOf(idStr);
			SysPlan plan = sysPlanDao.getById(id);
			plan.setRate(rate);
			sysPlanDao.update(plan);
			resultMsg = "更新日程状态成功！";
		}
		return resultMsg;
	}
	
	/**
	 * 取得我负责的日程和我 参与的日程列表
	 * @param queryFilter
	 * @return
	 */
	public List<SysPlan> getMyList(QueryFilter queryFilter) {
		return sysPlanDao.getMyList(queryFilter);
	}
	
}
