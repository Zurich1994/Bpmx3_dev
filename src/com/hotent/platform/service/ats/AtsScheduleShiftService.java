package com.hotent.platform.service.ats;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.hotent.core.db.IEntityDao;
import com.hotent.core.excel.util.ExcelUtil;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.JSONUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.platform.dao.ats.AtsScheduleShiftDao;
import com.hotent.platform.model.ats.AtsAttendanceFile;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsScheduleShift;
import com.hotent.platform.model.ats.AtsShiftInfo;
import com.hotent.platform.xml.util.MsgUtil;

/**
 * <pre>
 * 对象功能:排班列表 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-25 17:05:06
 * </pre>
 */
@Service
public class AtsScheduleShiftService extends BaseService<AtsScheduleShift> {
	@Resource
	private AtsScheduleShiftDao dao;
	@Resource
	private AtsShiftInfoService atsShiftInfoService;
	@Resource
	private AtsAttendanceFileService atsAttendanceFileService;
	@Resource
	private AtsLegalHolidayDetailService atsLegalHolidayDetailService;

	public AtsScheduleShiftService() {
	}

	@Override
	protected IEntityDao<AtsScheduleShift, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 保存 排班列表 信息
	 * 
	 * @param atsScheduleShift
	 */
	public void save(AtsScheduleShift atsScheduleShift) {
		Long id = atsScheduleShift.getId();
		if (id == null || id == 0) {
			id = UniqueIdUtil.genId();
			atsScheduleShift.setId(id);
			this.add(atsScheduleShift);
		} else {
			this.update(atsScheduleShift);
		}
	}

	/**
	 * 处理保存排班向导信息
	 * 
	 * @param userJson
	 *            传入的用户json
	 * @param listRowDatasJson
	 *            排班列表数据
	 * @param attencePolicyName
	 *            考勤制度名
	 * @param shiftType
	 *            选择排班类型
	 * @return
	 * @throws Exception
	 */
	public List<AtsScheduleShift> save(JSONArray userJsonAry,
			JSONArray listRowDatasJson, String attencePolicyName,
			Short shiftType) throws Exception {
		List<AtsScheduleShift> list = new ArrayList<AtsScheduleShift>();

		if (shiftType == 1) { // 日历排班
			this.saveCalendar(userJsonAry, listRowDatasJson, attencePolicyName,
					list);
		} else { // 列表排班
			this.saveList(userJsonAry, listRowDatasJson, attencePolicyName,
					list);
		}
		return list;
	}

	/**
	 * 列表排班
	 * 
	 * @param userJson
	 *            传入的用户json
	 * @param listRowDatasJson
	 *            排班列表数据
	 * @param attencePolicyName
	 * @param list
	 * @throws Exception
	 */
	private void saveList(JSONArray userJsonAry, JSONArray listRowDatasJson,
			String attencePolicyName, List<AtsScheduleShift> list)
			throws Exception {
		Map<String, JSONObject> map = new HashMap<String, JSONObject>();

		for (Object userObj : userJsonAry) {
			JSONObject json = (JSONObject) userObj;
			Object id = json.get("id");
			map.put(id.toString(), json);
		}

		for (Object listObj : listRowDatasJson) {
			JSONObject listJson = (JSONObject) listObj;
			Object fileId = listJson.get("fileId");
			JSONObject userJson = map.get(fileId.toString());
			if(JSONUtil.isEmpty(userJson))
				continue;
			Iterator<?> it = listJson.keys();
			while (it.hasNext()) {
				String key = (String) it.next();
				if (key.equals("account") || key.equals("userName")
						|| key.equals("fileId"))
					continue;
				JSONObject json = (JSONObject) listJson.get(key);
				this.saveAtsScheduleShift(json, userJson, attencePolicyName,
						list);
			}
		}

	}

	/**
	 * 保存日历
	 * 
	 * @param userJsonAry
	 * @param listRowDatasJson
	 * @param attencePolicyName
	 * @param list
	 * @throws Exception
	 */
	private void saveCalendar(JSONArray userJsonAry,
			JSONArray listRowDatasJson, String attencePolicyName,
			List<AtsScheduleShift> list) throws Exception {
		for (Object userObj : userJsonAry) {
			JSONObject userJson = (JSONObject) userObj;
			for (Object eventObj : listRowDatasJson) {
				JSONObject eventJson = (JSONObject) eventObj;
				this.saveAtsScheduleShift(eventJson, userJson,
						attencePolicyName, list);
			}
		}
	}

	/**
	 * 保存编排的数据
	 * 
	 * @param json
	 * @param userJson
	 * @param attencePolicyName
	 * @param list
	 * @throws ParseException
	 */
	private void saveAtsScheduleShift(JSONObject json, JSONObject userJson,
			String attencePolicyName, List<AtsScheduleShift> list)
			throws ParseException {
		Object userId = userJson.get("id");
		Long fileId = Long.valueOf(userId.toString());

		Object start = json.get("start");
		Object dateType = json.get("dateType");
		if (BeanUtils.isEmpty(start.toString())
				|| BeanUtils.isEmpty(dateType.toString()))
			return;
		Date attenceTime = DateFormatUtil.parseDate(start.toString());
		Short dateType1 = AtsConstant.DATE_TYPE_DAYOFF;
		if (BeanUtils.isNotEmpty(dateType.toString()))
			dateType1 = Short.valueOf(dateType.toString());
		Object shiftId = json.get("shiftId");
		String title = (String) json.get("title");
		AtsScheduleShift atsScheduleShift = new AtsScheduleShift();

		atsScheduleShift.setFileId(fileId);
		atsScheduleShift.setDateType(dateType1);
		atsScheduleShift.setAttenceTime(attenceTime);

		if (BeanUtils.isNotEmpty(shiftId)) {
			if (!shiftId.equals("null"))
				atsScheduleShift.setShiftId(Long.valueOf(shiftId.toString()));
			else
				atsScheduleShift.setShiftId(null);
		} else {
			atsScheduleShift.setShiftId(null);
		}

		if (dateType1 == AtsConstant.DATE_TYPE_HOLIDAY) { // 节假日
			atsScheduleShift.setTitle(title);
			atsScheduleShift.setShiftId(null);
		} else if (dateType1 == AtsConstant.DATE_TYPE_DAYOFF) { // 休息日
			atsScheduleShift.setShiftId(null);
		}
		this.saveData(atsScheduleShift);

		// 处理返回页面展示
		handerList(list, atsScheduleShift, userJson, attencePolicyName);

	}

	/**
	 * 处理返回页面展示
	 * 
	 * @param list
	 * @param atsScheduleShift
	 * @param userJson
	 * @param attencePolicyName
	 */
	private void handerList(List<AtsScheduleShift> list,
			AtsScheduleShift atsScheduleShift, JSONObject userJson,
			String attencePolicyName) {
		Object userName = userJson.get("userName");
		Object orgName = userJson.get("orgName");
		Object cardNumber = userJson.get("cardNumber");

		if (BeanUtils.isNotEmpty(userName))
			atsScheduleShift.setUserName(userName.toString());
		if (BeanUtils.isNotEmpty(orgName))
			atsScheduleShift.setOrgName(orgName.toString());
		if (BeanUtils.isNotEmpty(cardNumber))
			atsScheduleShift.setCardNumber(cardNumber.toString());
		atsScheduleShift.setAttencePolicyName(attencePolicyName);
		list.add(atsScheduleShift);

	}

	/**
	 * 保存编排的数据
	 * 
	 * @param atsScheduleShift
	 */
	private void saveData(AtsScheduleShift atsScheduleShift) {
		AtsScheduleShift ass = dao
				.getByFileIdAttenceTime(atsScheduleShift.getFileId(),
						atsScheduleShift.getAttenceTime());
		if (BeanUtils.isEmpty(ass)) {
			atsScheduleShift.setId(UniqueIdUtil.genId());
			dao.add(atsScheduleShift);
		} else {
			BeanUtils.copyNotNullProperties(ass, atsScheduleShift);
			if (BeanUtils.isEmpty(atsScheduleShift.getShiftId()))
				ass.setShiftId(null);
			dao.update(ass);
		}
	}

	public List<AtsScheduleShift> getList(QueryFilter queryFilter) {
		return dao.getList(queryFilter);
	}

	public AtsScheduleShift getByFileIdAttenceTime(Long fileId, Date attenceTime) {
		return dao.getByFileIdAttenceTime(fileId, attenceTime);
	}

	public List<AtsScheduleShift> getByFileIdStartEndTime(Long fileId,
			Date startTime, Date endTime) {
		return dao.getByFileIdStartEndTime(fileId, startTime, endTime);
	}

	public Map<String, AtsScheduleShift> getByFileIdStartEndTimeMap(
			Long fileId, Date startTime, Date endTime) {
		List<AtsScheduleShift> list = dao.getByFileIdStartEndTime(fileId,
				startTime, endTime);
		Map<String, AtsScheduleShift> map = new HashMap<String, AtsScheduleShift>();
		for (AtsScheduleShift atsScheduleShift : list) {
			Short dateType = atsScheduleShift.getDateType();
			// 取到排班信息
			if (dateType.shortValue() == AtsConstant.DATE_TYPE_WORKDAY) {
				AtsShiftInfo atsShiftInfo = atsShiftInfoService
						.getShiftInfoById(atsScheduleShift.getShiftId());
				atsScheduleShift.setShiftInfo(atsShiftInfo);
			}
			String dateStr = DateFormatUtil.formatDate(atsScheduleShift
					.getAttenceTime());
			map.put(dateStr, atsScheduleShift);
		}
		return map;
	}

	public void importExcel(InputStream inputStream, Short holidayHandle)
			throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = workbook.getSheetAt(sheetIndex);
			// 第一行为标题，不取
			for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null)
					continue;
				AtsScheduleShift atsScheduleShift = new AtsScheduleShift();
			
				String holidayName = "";
				for (int columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					HSSFCell cell = row.getCell(columnIndex);
					String o = ExcelUtil.getCellFormatValue(cell);
					if (BeanUtils.isEmpty(o)
							&& (columnIndex == 0 || columnIndex == 3
									|| columnIndex == 4 || columnIndex == 5)) {
						String  v = "员工编号";
						if(columnIndex == 0){
							v = "员工编号";
						}else if(columnIndex == 3){
							v = "考勤日期";
						}else if(columnIndex == 4){
							v = "日期类型";
						}else if(columnIndex == 5){
							v = "班次名称";
						}
						MsgUtil.addMsg(MsgUtil.WARN, "第"+(rowIndex+1) +"行,"+v+"为空值,该行导入失败");
						atsScheduleShift = null;
						break;
					}
					if (columnIndex == 0) {// 考勤编号
						AtsAttendanceFile file = atsAttendanceFileService
								.getByAccount(o);
						if (BeanUtils.isEmpty(file)) {
							MsgUtil.addMsg(MsgUtil.ERROR, "第"+(rowIndex+1) +"行,找不到该员工编号:"+o+",该行导入失败");
							atsScheduleShift = null;
							break;
						}
						atsScheduleShift.setAttencePolicy(file
								.getAttencePolicy());
						atsScheduleShift.setFileId(file.getId());
					} else if (columnIndex == 3) {// 考勤日期
						atsScheduleShift.setAttenceTime(DateFormatUtil
								.parseDate(o));
					} else if (columnIndex == 4) {// 日期类型
						Short dateType = AtsConstant.DATE_TYPE_WORKDAY;
						if (AtsConstant.DATE_TYPE_WORKDAY_STRING
								.equalsIgnoreCase(o)) {
							dateType = AtsConstant.DATE_TYPE_WORKDAY;
						} else if (AtsConstant.DATE_TYPE_DAYOFF_STRING
								.equalsIgnoreCase(o)) {
							dateType = AtsConstant.DATE_TYPE_DAYOFF;
						} else if (AtsConstant.DATE_TYPE_HOLIDAY_STRING
								.equalsIgnoreCase(o)) {
							dateType = AtsConstant.DATE_TYPE_HOLIDAY;
						}
						atsScheduleShift.setDateType(dateType);
					} else if (columnIndex == 5) {// 班次
						AtsShiftInfo atsShiftInfo = atsShiftInfoService
								.getByShiftName(o);
						if (BeanUtils.isEmpty(atsShiftInfo)) {
							atsScheduleShift = null;
							MsgUtil.addMsg(MsgUtil.ERROR, "第"+(rowIndex+1) +"行，找不到该班次:"+o+",该行导入失败");
							break;
						}
						atsScheduleShift.setShiftId(atsShiftInfo.getId());
					} else if (columnIndex == 6) {// 假期名称
						holidayName = o;
					}

				}
				if (BeanUtils.isNotEmpty(atsScheduleShift)) {
					if (holidayHandle.shortValue() == AtsConstant.HOLIDAY_HANDLE_REPLACE) {
						holidayName = this.getHoliday(
								atsScheduleShift.getAttencePolicy(),
								atsScheduleShift.getAttenceTime());
					}
					if (BeanUtils.isNotEmpty(holidayName)) {
						atsScheduleShift
								.setDateType(AtsConstant.DATE_TYPE_HOLIDAY);
						atsScheduleShift.setTitle(holidayName);
					}
					MsgUtil.addMsg(MsgUtil.SUCCESS, "第"+(rowIndex+1) +"行，成功导入");
					this.saveData(atsScheduleShift);
				}
			

			}
		
		}
	}

	private String getHoliday(Long attencePolicy, Date attenceTime) {
		Map<String, String> map = atsLegalHolidayDetailService
				.getHolidayMap(attencePolicy);
		return map.get(DateFormatUtil.formatDate(attenceTime));
	}

}
