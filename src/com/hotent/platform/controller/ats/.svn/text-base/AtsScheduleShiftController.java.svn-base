package com.hotent.platform.controller.ats;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.excel.Excel;
import com.hotent.core.excel.editor.IFontEditor;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.font.BoldWeight;
import com.hotent.core.excel.style.font.Font;
import com.hotent.core.excel.util.ExcelUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.DateFormatUtil;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsConstant;
import com.hotent.platform.model.ats.AtsScheduleShift;
import com.hotent.platform.service.ats.AtsAttendanceFileService;
import com.hotent.platform.service.ats.AtsScheduleShiftService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.xml.util.MsgUtil;

/**
 * <pre>
 * 对象功能:排班列表 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-25 17:05:06
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsScheduleShift/")
public class AtsScheduleShiftController extends BaseController {
	@Resource
	private AtsScheduleShiftService atsScheduleShiftService;

	@Resource
	private AtsAttendanceFileService atsAttendanceFileService;
	/**
	 * 添加或更新排班列表。
	 * 
	 * @param request
	 * @param response
	 * @param atsScheduleShift
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新排班列表")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsScheduleShift atsScheduleShift) throws Exception {
		String resultMsg = null;
		try {
			if (atsScheduleShift.getId() == null
					|| atsScheduleShift.getId() == 0) {
				resultMsg = getText("添加", "排班列表");
			} else {
				resultMsg = getText("更新", "排班列表");
			}
			atsScheduleShiftService.save(atsScheduleShift);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得排班列表分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看排班列表分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsScheduleShift> list = atsScheduleShiftService
				.getList(new QueryFilter(request, "atsScheduleShiftItem"));
		return this.getAutoView().addObject("atsScheduleShiftList", list);
	}

	/**
	 * 删除排班列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除排班列表")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsScheduleShiftService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除排班列表成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑排班列表
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑排班列表")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsScheduleShift atsScheduleShift = atsScheduleShiftService.getById(id);

		return getAutoView().addObject("atsScheduleShift", atsScheduleShift)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得排班列表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看排班列表明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsScheduleShift atsScheduleShift = atsScheduleShiftService.getById(id);
		return getAutoView().addObject("atsScheduleShift", atsScheduleShift);
	}
	
	/**
	 * 导出排班记录
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportData")
	@Action(description = "导出排班记录")
	public void exportData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String [] userNames = RequestUtil.getStringAryByStr(request, "userNames");
		String [] orgNames = RequestUtil.getStringAryByStr(request, "orgNames");
		String [] accounts = RequestUtil.getStringAryByStr(request, "accounts");
		String [] shiftNames = RequestUtil.getStringAryByStr(request, "shiftNames");
		Date startTime = RequestUtil.getDate(request, "startTime");
		Date endTime = RequestUtil.getDate(request, "endTime");
		
		
		try {
			Excel excel = new Excel();
			excel.sheet().sheetName("员工排班列表");// 重命名当前处于工作状态的表的名称
			List<String> titleList  = new ArrayList<String>();
			
			titleList.add("员工编号");
			titleList.add("姓名");
			titleList.add("组织名称");
			titleList.add("考勤日期");
			titleList.add("日期类型");
			titleList.add("班次名称");
			excel.row(0, 0).value(titleList.toArray()).font(new IFontEditor() {
				// 设置字体
				@Override
				public void updateFont(Font font) {
					font.boldweight(BoldWeight.BOLD);// 粗体
				}
			}).bgColor(Color.GREY_25_PERCENT).width(3500);
			
			
			int betweenDays = DateUtil.daysBetween(startTime,endTime );
			List<String> attenceTimeList  = new ArrayList<String>();
	
			if(BeanUtils.isNotEmpty(accounts)){
				List<String> accountList  = new ArrayList<String>();
				List<String> userNameList  = new ArrayList<String>();
				List<String> orgNameList  = new ArrayList<String>();
				for (int i = 0; i < accounts.length; i++) {
					String account = accounts[i];
					String userName = userNames[i];
					String orgName = orgNames[i];
					for (int j = 0; j <= betweenDays; j++) {
						Date attenceTime = DateUtil.addDay(startTime, j);
						attenceTimeList.add(DateFormatUtil.formatDate( attenceTime));
						accountList.add(account);
						userNameList.add(userName);
						orgNameList.add(orgName);
					}
				}
				excel.column(0,1).value(accountList.toArray()).width(3000);
				excel.column(1,1).value(userNameList.toArray()).width(3000);
				excel.column(2,1).value(orgNameList.toArray()).width(3000);
			}else{
				for (int i = 0; i <= betweenDays; i++) {
					Date attenceTime = DateUtil.addDay(startTime, i);
					attenceTimeList.add(DateFormatUtil.formatDate( attenceTime));
				}
			}
	
			excel.column(3,1).value(attenceTimeList.toArray()).width(3000);
			excel.column(4,1).addValidationData( new String[] { AtsConstant.DATE_TYPE_WORKDAY_STRING, AtsConstant.DATE_TYPE_DAYOFF_STRING,AtsConstant.DATE_TYPE_HOLIDAY_STRING }).width(3000);;
			excel.column(5,1).addValidationData(shiftNames).width(4000);;
			// 取得表的数据
			String fileName = "员工排班列表";
			ExcelUtil.downloadExcel(excel.getWorkBook(), fileName, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	/**
	 * 导入数据保存
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importData")
	@Action(description = "导入数据保存",detail="导入业务数据模板数据")
	public void importData(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartFile file = request.getFile("file");
		Short holidayHandle = RequestUtil.getShort(request, "holidayHandle");
		
		ResultMessage message = null;
		try {
			atsScheduleShiftService.importExcel(file.getInputStream(),holidayHandle);
			message = new ResultMessage(ResultMessage.Success,
					MsgUtil.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			MsgUtil.clean();
			String str = MessageUtil.getMessage();
			if (StringUtil.isNotEmpty(str)) {
				message = new ResultMessage(ResultMessage.Fail, "导入失败:" + str);
			} else {
				String msg = ExceptionUtil.getExceptionMessage(e);
				message = new ResultMessage(ResultMessage.Fail, msg);
			}
		}
		writeResultMessage(response.getWriter(), message);

	}

}
