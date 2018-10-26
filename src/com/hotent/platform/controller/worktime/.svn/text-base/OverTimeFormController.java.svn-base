package com.hotent.platform.controller.worktime;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.TimeUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseFormController;
import com.hotent.core.web.util.RequestUtil;

import com.hotent.platform.model.worktime.OverTime;
import com.hotent.platform.model.worktime.WorkTime;
import com.hotent.platform.service.worktime.CalendarAssignService;
import com.hotent.platform.service.worktime.CalendarSettingService;
import com.hotent.platform.service.worktime.OverTimeService;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;

/**
 * 对象功能:加班情况 控制器类 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2012-02-20 09:25:52
 */
@Controller
@RequestMapping("/platform/worktime/overTime/")
@Action(ownermodel=SysAuditModelType.WORK_CALENDAR)
public class OverTimeFormController extends BaseFormController {
	@Resource
	private OverTimeService overTimeService;

	@Resource
	private CalendarAssignService calendarAssignService;

	@Resource
	private CalendarSettingService calendarSettingService;


	/**
	 * 添加或更新加班情况。
	 * 
	 * @param request
	 * @param response
	 * @param overTime
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(
			description = "添加或更新加班请假管理",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>加班请假管理" +
					"【${SysAuditLinkService.getOverTimeLink(Long.valueOf(id))}】"
	)
	public void save(HttpServletRequest request, HttpServletResponse response,
			OverTime overTime) throws Exception {
		ResultMessage resultMessage = null;
		String resultMsg = null;
		String stime = RequestUtil.getString(request, "startTime");
		String etime = RequestUtil.getString(request, "endTime");
		overTime.setStartTime(TimeUtil.getDateTimeByTimeString(stime));
		overTime.setEndTime(TimeUtil.getDateTimeByTimeString(etime));

		boolean isadd=overTime.getId() == null;

		Long calendarId = calendarAssignService.getCalendarIdByUserId(overTime
				.getUserId());
		List<WorkTime> workTimes = calendarSettingService.getByCalendarId(
				calendarId, overTime.getStartTime(), overTime.getEndTime());

		long otStartTime = overTime.getStartTime().getTime();
		long otEndTime = overTime.getEndTime().getTime();
		if (overTime.getWorkType() == 1) {
			for (WorkTime workTime : workTimes) {
				long wkStartTime = workTime.getStartDateTime().getTime();
				long wkEndTime = workTime.getEndDateTime().getTime();
				if ((wkStartTime < otStartTime && wkEndTime > otStartTime)
						|| (wkStartTime > otStartTime && wkStartTime < otEndTime)) {
					resultMsg = "加班时间与正常上班时间有冲突";
					writeResultMessage(response.getWriter(), resultMsg,
							ResultMessage.Fail);
					return;
				}
			}
		} else if (overTime.getWorkType() == 2) {
			boolean conflict = false;
			for (WorkTime workTime : workTimes) {
				long wkStartTime = workTime.getStartDateTime().getTime();
				long wkEndTime = workTime.getEndDateTime().getTime();
				if ((wkStartTime < otStartTime && wkEndTime > otStartTime)
						|| (wkStartTime > otStartTime && wkStartTime < otEndTime)) {
					conflict = true;
					break;
				}
			}
			if (!conflict) {
				resultMsg = "所请假时间段无班次安排，不用请假";
				writeResultMessage(response.getWriter(), resultMsg,
						ResultMessage.Fail);
				return;
			}
		}


		if (overTime.getId() == null) {
			overTime.setId(UniqueIdUtil.genId());
			overTimeService.add(overTime);
			if (overTime.getWorkType() == 1) {
				resultMsg = "加班情况添加成功";
			} else {
				resultMsg = "请假情况添加成功";
			}
		} else {
			overTimeService.update(overTime);
			if (overTime.getWorkType() == 1) {
				resultMsg = "加班情况更新成功";
			} else {
				resultMsg = "请假情况更新成功";
			}
		}
		writeResultMessage(response.getWriter(), resultMsg,
				ResultMessage.Success);
		//添加系统日志信息 -B
		try {
			SysAuditThreadLocalHolder.putParamerter("id", overTime.getId().toString());
			SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 在实体对象进行封装前，从对应源获取原实体
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@ModelAttribute
	protected OverTime getFormObject(@RequestParam("id") Long id, Model model)
			throws Exception {
		logger.debug("enter OverTime getFormObject here....");
		OverTime overTime = null;
		if (id != null) {
			overTime = overTimeService.getById(id);
		} else {
			overTime = new OverTime();
		}
		return overTime;
	}

}
