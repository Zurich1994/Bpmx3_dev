package com.hotent.platform.controller.ats;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.page.PageBean;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.StringPool;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsAttenceCalculate;
import com.hotent.platform.model.ats.AtsAttenceCalculateSet;
import com.hotent.platform.model.ats.AtsAttenceCycleDetail;
import com.hotent.platform.model.ats.AtsAttencePolicy;
import com.hotent.platform.model.ats.AtsAttendanceFile;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.service.ats.AtsAttenceCalculateService;
import com.hotent.platform.service.ats.AtsAttenceCalculateSetService;
import com.hotent.platform.service.ats.AtsAttenceCycleDetailService;
import com.hotent.platform.service.ats.AtsAttencePolicyService;
import com.hotent.platform.service.ats.AtsAttendanceFileService;

/**
 * <pre>
 * 对象功能:考勤计算 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-31 13:51:09
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsAttenceCalculate/")
public class AtsAttenceCalculateController extends BaseController {
	@Resource
	private AtsAttenceCalculateService atsAttenceCalculateService;
	@Resource
	private AtsAttencePolicyService atsAttencePolicyService;

	@Resource
	private AtsAttenceCycleDetailService atsAttenceCycleDetailService;

	@Resource
	private AtsAttenceCalculateSetService atsAttenceCalculateSetService;
	@Resource
	private AtsAttendanceFileService atsAttendanceFileService;

	/**
	 * 取得考勤计算分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看考勤计算分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 考勤制度
		AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService
				.getByDefault();
		// 考勤周期
		List<AtsAttenceCycleDetail> cycleList = null;
		if (BeanUtils.isNotEmpty(atsAttencePolicy))
			cycleList = atsAttenceCycleDetailService.getByCycleId(
					atsAttencePolicy.getAttenceCycle(), true);
		return this.getAutoView()
				.addObject("atsAttencePolicy", atsAttencePolicy)
				.addObject("cycleList", cycleList);
	}

	/**
	 * 取得考勤计算分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getNoneCalList")
	@ResponseBody
	public JSONObject getNoneCalList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		try {
			QueryFilter filter = new QueryFilter(request);
			List<AtsAttendanceFile> list = atsAttendanceFileService
					.getNoneCalList(filter);
			PageBean page = filter.getPageBean();
			JSONArray jary = new JSONArray();
			for (AtsAttendanceFile atsAttendanceFile : list) {
				JSONObject j = new JSONObject();
				j.accumulate("fileId", atsAttendanceFile.getId())
						.accumulate("cardNumber",
								atsAttendanceFile.getCardNumber())
						.accumulate("userName", atsAttendanceFile.getUserName())
						.accumulate("account", atsAttendanceFile.getAccount())
						.accumulate(
								"orgName",
								BeanUtils.isEmpty(atsAttendanceFile
										.getOrgName()) ? "" : atsAttendanceFile
										.getOrgName());
				jary.add(j);
			}

			json.element("results", jary.toString())
					.element("records", page.getPageSize())
					.element("page", page.getCurrentPage())
					.element("total", page.getTotalPage());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 获取表格的行列数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getGridColModel")
	@ResponseBody
	public JSONObject getGridColModel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date startTime = RequestUtil.getDate(request, "startTime",
				StringPool.DATE_FORMAT_DATE);
		Date endTime = RequestUtil.getDate(request, "endTime",
				StringPool.DATE_FORMAT_DATE);
		int betweenDays = DateUtil.daysBetween(startTime, endTime);
		JSONObject json = new JSONObject();
		JSONArray colNamesAry = new JSONArray();
		JSONArray colModelAry = new JSONArray();

		this.setJsonAry(colNamesAry, colModelAry, "ID", "fileId", "fileId", 80,
				true, "");
		this.setJsonAry(colNamesAry, colModelAry, "组织ID", "orgName", "orgName",
				80, true, "");
		this.setJsonAry(colNamesAry, colModelAry, "工号", "account", "account",
				80, false, "");
		this.setJsonAry(colNamesAry, colModelAry, "姓名", "userName", "userName",
				80, false, "");
		// 汇总字段
		setJsonSummary(colNamesAry, colModelAry);

		for (int i = 0; i <= betweenDays; i++) {
			Date date = DateUtil.addDay(startTime, i);
			String week = DateUtil.getWeekOfDate(date);
			String time = DateFormatUtil.format(date, "dd");
			this.setJsonAry(colNamesAry, colModelAry, time + "(" + week + ")",
					time, DateFormatUtil.formatDate(date), 85, false, "");
		}
		json.accumulate("colNames", colNamesAry.toString()).accumulate(
				"colModel", colModelAry.toString());

		return json;

	}

	/**
	 * 设置汇总字段
	 * 
	 * @param colNamesAry
	 * @param colModelAry
	 */
	private void setJsonSummary(JSONArray colNamesAry, JSONArray colModelAry) {
		JSONArray jsonSet = this.getAtsAttenceCalculateSetSummary();
		for (Object obj : jsonSet) {
			JSONObject set = (JSONObject) obj;
			this.setJsonAry(colNamesAry, colModelAry, set.getString("lable"),
					set.getString("name"), set.getString("name"), 80, false,
					"sum");
		}
	}

	/**
	 * 获取汇总明细
	 * 
	 * @return
	 */
	private JSONArray getAtsAttenceCalculateSetSummary() {
		AtsAttenceCalculateSet atsAttenceCalculateSet = atsAttenceCalculateSetService
				.getDefault();
		JSONArray jary = new JSONArray();
		if (BeanUtils.isNotEmpty(atsAttenceCalculateSet)
				&& BeanUtils.isNotEmpty(atsAttenceCalculateSet.getSummary()))
			jary = JSONArray.fromObject(atsAttenceCalculateSet.getSummary());
		if (JSONUtil.isEmpty(jary)) {
			jary = new JSONArray();
			JSONObject json2 = new JSONObject();
			json2.accumulate("lable", "旷工次数").accumulate("name", "S21");
			jary.add(json2);
			JSONObject json3 = new JSONObject();
			json3.accumulate("lable", "迟到次数").accumulate("name", "S31");
			jary.add(json3);
			JSONObject json4 = new JSONObject();
			json4.accumulate("lable", "早退次数").accumulate("name", "S41");
			jary.add(json4);
		}
		return jary;
	}

	/**
	 * 设置展示字段
	 * 
	 * @param colNamesAry
	 * @param colModelAry
	 * @param lable
	 * @param name
	 * @param index
	 * @param width
	 * @param hidden
	 * @param summaryType
	 */
	private void setJsonAry(JSONArray colNamesAry, JSONArray colModelAry,
			String lable, String name, String index, int width, boolean hidden,
			String summaryType) {
		JSONObject json = new JSONObject();
		json.accumulate("label", lable).accumulate("width", width)
				.accumulate("name", name).accumulate("index", index)
				.accumulate("hidden", hidden);
		if (BeanUtils.isNotEmpty(summaryType))
			json.accumulate("summaryType", summaryType);
		colNamesAry.add(lable);
		colModelAry.add(json);
	}

	@RequestMapping("reportGrid")
	@ResponseBody
	public JSONObject reportGrid(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date startTime = RequestUtil.getDate(request, "Q_beginattenceTime_DL",
				StringPool.DATE_FORMAT_DATE);
		Date endTime = RequestUtil.getDate(request, "Q_endattenceTime_DE",
				StringPool.DATE_FORMAT_DATE);
		QueryFilter filter = new QueryFilter(request, false);
		List<AtsAttenceCalculate> list = atsAttenceCalculateService
				.getList(filter);
		int betweenDays = DateUtil.daysBetween(startTime, endTime);
		return getPageList(list, filter.getPageBean(), betweenDays, startTime);
	}

	/**
	 * 分页数据
	 * 
	 * @param list
	 * @param pageBean
	 * @param betweenDays
	 * @param startTime
	 * @return
	 */
	private JSONObject getPageList(List<AtsAttenceCalculate> list,
			PageBean page, int betweenDays, Date startTime) {

		Map<Long, Map<String, AtsAttenceCalculate>> map = new HashMap<Long, Map<String, AtsAttenceCalculate>>();
		Map<Long, AtsAttenceCalculate> fileMap = new HashMap<Long, AtsAttenceCalculate>();
		for (AtsAttenceCalculate calculate : list) {
			Long fileId = calculate.getFileId();
			String time = DateFormatUtil.format(calculate.getAttenceTime(),
					"dd");
			// Map<String,AtsAttenceCalculate>
			Map<String, AtsAttenceCalculate> map1 = map.get(fileId);
			if (BeanUtils.isEmpty(map1))
				map1 = new HashMap<String, AtsAttenceCalculate>();
			map1.put(time, calculate);
			map.put(fileId, map1);
			fileMap.put(fileId, calculate);
		}
		JSONArray jary = new JSONArray();
		AtsAttenceCalculateSet atsAttenceCalculateSet = atsAttenceCalculateSetService
				.getDefault();
		for (Entry<Long, Map<String, AtsAttenceCalculate>> entry : map
				.entrySet()) {
			JSONObject json = new JSONObject();
			Long key = entry.getKey();
			AtsAttenceCalculate calculate = fileMap.get(key);
			json.accumulate("fileId", key)
					.accumulate("account", BeanUtils.isEmpty(calculate.getAccount())?"":calculate.getAccount())
					.accumulate("userName",BeanUtils.isEmpty(calculate.getUserName())?"":calculate.getUserName())
					.accumulate(
							"orgName",
							BeanUtils.isEmpty(calculate.getOrgName()) ? ""
									: calculate.getOrgName());
			Map<String, AtsAttenceCalculate> map1 = entry.getValue();
			Double shouldAttenceHours = 0d;
			Double actualAttenceHours = 0d;
			Double absentNumber = 0d;
			Double lateNumber = 0d;
			Double leaveNumber = 0d;
			Double absentTime = 0d;
			Double lateTime = 0d;
			Double leaveTime = 0d;
			for (int i = 0; i <= betweenDays; i++) {
				Date date = DateUtil.addDay(startTime, i);
				String time = DateFormatUtil.format(date, "dd");
				AtsAttenceCalculate cal = map1.get(time);
				String tilte = getSetDetail(cal, atsAttenceCalculateSet);

				json.accumulate(time, tilte);

				if (BeanUtils.isNotEmpty(cal.getShouldAttenceHours()))
					shouldAttenceHours += cal.getShouldAttenceHours();
				if (BeanUtils.isNotEmpty(cal.getActualAttenceHours()))
					actualAttenceHours += cal.getActualAttenceHours();
				// 计算旷工、迟到、早退数
				if (BeanUtils.isNotEmpty(cal.getAbsentNumber()))
					absentNumber += cal.getAbsentNumber();
				if (BeanUtils.isNotEmpty(cal.getLateNumber()))
					lateNumber += cal.getLateNumber();
				if (BeanUtils.isNotEmpty(cal.getLeaveNumber()))
					leaveNumber += cal.getLeaveNumber();
				if (BeanUtils.isNotEmpty(cal.getAbsentTime()))
					absentTime += cal.getAbsentTime();
				if (BeanUtils.isNotEmpty(cal.getLateTime()))
					lateTime += cal.getLateTime();
				if (BeanUtils.isNotEmpty(cal.getLeaveTime()))
					leaveTime += cal.getLeaveTime();
			}
			JSONArray jsonSet = getAtsAttenceCalculateSetSummary();
			for (Object obj : jsonSet) {
				JSONObject set = (JSONObject) obj;
				String key1 = set.getString("name");
				double val = 0d;
				if (key1.contains("S11"))
					val = shouldAttenceHours;
				else if (key1.contains("S12"))
					val = actualAttenceHours;
				else if (key1.contains("S21"))
					val = absentNumber;
				else if (key1.contains("S22"))
					val = absentTime;
				else if (key1.contains("S31"))
					val = lateNumber;
				else if (key1.contains("S32"))
					val = lateTime;
				else if (key1.contains("S41"))
					val = leaveNumber;
				else if (key1.contains("S42"))
					val = leaveTime;
			

				json.accumulate(key1, val);
			}
			jary.add(json);
		}

		JSONObject json = new JSONObject();
		json.element("results", jary.toString())
				.element("records", jary.size()).element("page", 1)
				.element("total", 1);
		return json;
	}

	private String getSetDetail(AtsAttenceCalculate cal,
			AtsAttenceCalculateSet atsAttenceCalculateSet) {
		String tilte = "";
		if (cal.getIsScheduleShift() == AtsConstant.NO)
			return AtsConstant.NO_SHIFT;
		Short dateType = cal.getDateType();
		if (dateType == AtsConstant.DATE_TYPE_DAYOFF) {
			tilte = AtsConstant.DATE_TYPE_DAYOFF_STRING;
		} else if (dateType == AtsConstant.DATE_TYPE_HOLIDAY) {
			tilte = BeanUtils.isEmpty(cal.getHolidayName()) ? AtsConstant.DATE_TYPE_HOLIDAY_STRING
					: cal.getHolidayName();
		} else {
			if (cal.getIsCardRecord() == AtsConstant.NO)
				tilte = "无打卡记录";
		}
		if (BeanUtils.isNotEmpty(tilte))
			return tilte;
		if (BeanUtils.isEmpty(atsAttenceCalculateSet)
				|| BeanUtils.isEmpty(atsAttenceCalculateSet.getDetail()))
			return "实出勤时数:" + cal.getActualAttenceHours();
		JSONArray jary = JSONArray.fromObject(atsAttenceCalculateSet
				.getDetail());
		for (Object o : jary) {
			JSONObject json = (JSONObject) o;
			String name = json.getString("name");
			String lable = json.getString("lable");
			Double val = null;
			if (name.contains("S11"))
				val = cal.getShouldAttenceHours();
			else if (name.contains("S12"))
				val = cal.getActualAttenceHours();
			else if (name.contains("S21"))
				val = cal.getAbsentNumber();
			else if (name.contains("S22"))
				val = cal.getAbsentTime();
			else if (name.contains("S31"))
				val = cal.getLateNumber();
			else if (name.contains("S32"))
				val = cal.getLateTime();
			else if (name.contains("S41"))
				val = cal.getLeaveNumber();
			else if (name.contains("S42"))
				val = cal.getLeaveTime();
			if (BeanUtils.isNotEmpty(val) &&  val > 0)
				return lable + ":" + val;
		}

		return "实出勤时数:" + cal.getActualAttenceHours();
	}

	/**
	 * 全部计算
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("calculate")
	@ResponseBody
	public JSONObject calculate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date startTime = RequestUtil.getDate(request, "startTime",
				StringPool.DATE_FORMAT_DATE);
		Date endTime = RequestUtil.getDate(request, "endTime",
				StringPool.DATE_FORMAT_DATE);
		Long attendPolicyId = RequestUtil.getLong(request, "attendPolicyId");
		Long[] fileIds = RequestUtil.getLongAryByStr(request, "fileIds");

		JSONObject json = new JSONObject();
		json.element("success", true);
		try {
			if (BeanUtils.isEmpty(fileIds)) // 选中记录
				atsAttenceCalculateService.allCalculate(startTime, endTime,
						attendPolicyId);
			else
				atsAttenceCalculateService.calculate(startTime, endTime,
						attendPolicyId, fileIds);
		} catch (Exception e) {
			e.printStackTrace();
			json.element("success", false);
			json.element("results", "出错了" + e.getMessage());
		}

		return json;
	}

	/**
	 * 日历展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("calendar")
	@Action(description = "日历")
	public ModelAndView calendar(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date startTime = RequestUtil.getDate(request, "startTime",
				StringPool.DATE_FORMAT_DATE);
		Date endTime = RequestUtil.getDate(request, "endTime",
				StringPool.DATE_FORMAT_DATE);
		Long fileId = RequestUtil.getLong(request, "fileId");

		List<AtsAttenceCalculate> list = atsAttenceCalculateService
				.getByFileIdAttenceTime(fileId, startTime, endTime);
		AtsAttenceCalculateSet atsAttenceCalculateSet = atsAttenceCalculateSetService
				.getById(1L);
		JSONArray jary = new JSONArray();
		for (AtsAttenceCalculate cal : list) {
			String backgroundColor = AtsConstant.BACKGROUND_COLOR_WORKDAY;
			if (cal.getDateType() == AtsConstant.DATE_TYPE_DAYOFF)
				backgroundColor = AtsConstant.BACKGROUND_COLOR_DAYOFF;
			else if (cal.getDateType() == AtsConstant.DATE_TYPE_HOLIDAY)
				backgroundColor = AtsConstant.BACKGROUND_COLOR_HOLIDAY;
			
			JSONObject json = new JSONObject();
			json.accumulate("title", getSetDetail(cal, atsAttenceCalculateSet))
					.accumulate("start",DateFormatUtil.formatDate(cal.getAttenceTime()))
					.accumulate("backgroundColor", backgroundColor);
			jary.add(json);
		}
		return getAutoView().addObject("events", jary.toString()).addObject(
				"startTime", startTime);
	}

	/**
	 * 汇总展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("summary")
	@Action(description = "汇总")
	public ModelAndView summary(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date startTime = RequestUtil.getDate(request, "startTime",
				StringPool.DATE_FORMAT_DATE);
		Date endTime = RequestUtil.getDate(request, "endTime",
				StringPool.DATE_FORMAT_DATE);
		Long fileId = RequestUtil.getLong(request, "fileId");
		String colName = RequestUtil.getString(request, "colName");

		List<AtsAttenceCalculate> list = atsAttenceCalculateService
				.getByFileIdAttenceTime(fileId, startTime, endTime);
		List<AtsAttenceCalculate> calculateList = new ArrayList<AtsAttenceCalculate>();
		for (AtsAttenceCalculate cal : list) {
			Double val = null;
			String unit = "次";
			if (colName.equals("S11")) {
				val = cal.getShouldAttenceHours();
				unit = "小时";
			} else if (colName.contains("S12")) {
				val = cal.getActualAttenceHours();
				unit = "小时";
			}  else if (colName.contains("S21")) {
				val = cal.getAbsentNumber();
				unit = "次";
			} else if (colName.contains("S22")) {
				val = cal.getAbsentTime();
				unit = "小时";
			} else if (colName.contains("S31")) {
				val = cal.getLateNumber();
				unit = "次";
			} else if (colName.contains("S32")) {
				val = cal.getLateTime();
				unit = "分钟";
			} else if (colName.contains("S41")) {
				val = cal.getLeaveNumber();
			} else if (colName.contains("S42")) {
				val = cal.getLeaveTime();
				unit = "分钟";
			}

			if (BeanUtils.isNotEmpty(val) && val > 0) {
				AtsAttenceCalculate calculate = new AtsAttenceCalculate();
				calculate.setAttenceTime(cal.getAttenceTime());
				calculate.setShouldAttenceHours(val);
				calculate.setUnit(unit);
				calculateList.add(calculate);
			}
		}

		return getAutoView().addObject("calculateList", calculateList);
	}

	/**
	 * 考勤情况展示
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bill")
	@Action(description = "考勤情况")
	public ModelAndView bill(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Date attenceTime = RequestUtil.getDate(request, "colName",
				StringPool.DATE_FORMAT_DATE);
		Long fileId = RequestUtil.getLong(request, "fileId");

		AtsAttenceCalculate calculate = atsAttenceCalculateService
				.getByFileIdAttenceTime(fileId, attenceTime);

		if (BeanUtils.isNotEmpty(calculate.getShiftTime())) {
			JSONArray jary = JSONArray.fromObject(calculate.getShiftTime());
			for (Object obj : jary) {
				JSONObject json = (JSONObject) obj;
				Integer segment = (Integer) json.get("segment");
				String shiftTime = json.getString("onTime") + "--"
						+ json.getString("offTime");
				if (segment.intValue() == AtsConstant.SEGMENT_1) {
					calculate.setShiftTime1(shiftTime);
				} else if (segment.intValue() == AtsConstant.SEGMENT_2) {
					calculate.setShiftTime2(shiftTime);
				} else if (segment.intValue() == AtsConstant.SEGMENT_3) {
					calculate.setShiftTime3(shiftTime);
				}
			}
		}

		if (BeanUtils.isNotEmpty(calculate.getAbsentRecord())) {
			JSONArray jary = JSONArray.fromObject(calculate.getAbsentRecord());
			for (Object obj : jary) {
				JSONObject json = (JSONObject) obj;
				Integer segment = (Integer) json.get("segment");
				if (BeanUtils.isEmpty(segment))
					continue;
				Object onCardTime = json.get("onCardTime");
				Object offCardTime = json.get("offCardTime");
				String absentRecord = "无";
				if (BeanUtils.isNotEmpty(onCardTime))
					absentRecord = onCardTime.toString();
				else if (BeanUtils.isNotEmpty(offCardTime))
					absentRecord = offCardTime.toString();

				if (segment.intValue() == AtsConstant.SEGMENT_1) {
					calculate.setAbsentRecord1(absentRecord);
				} else if (segment.intValue() == AtsConstant.SEGMENT_2) {
					calculate.setAbsentRecord2(absentRecord);
				} else if (segment.intValue() == AtsConstant.SEGMENT_3) {
					calculate.setAbsentRecord3(absentRecord);
				}
			}
		}
		return getAutoView().addObject("calculate", calculate);
	}

}
