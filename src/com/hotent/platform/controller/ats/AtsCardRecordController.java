package com.hotent.platform.controller.ats;

import java.util.ArrayList;
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
import com.hotent.core.excel.style.DataFormat;
import com.hotent.core.excel.style.font.BoldWeight;
import com.hotent.core.excel.style.font.Font;
import com.hotent.core.excel.util.ExcelUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsCardRecord;
import com.hotent.platform.service.ats.AtsCardRecordService;

/**
 * <pre>
 * 对象功能:打卡记录 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-26 11:21:21
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsCardRecord/")
public class AtsCardRecordController extends BaseController {
	@Resource
	private AtsCardRecordService atsCardRecordService;

	/**
	 * 添加或更新打卡记录。
	 * 
	 * @param request
	 * @param response
	 * @param atsCardRecord
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新打卡记录")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsCardRecord atsCardRecord) throws Exception {
		String resultMsg = null;
		try {
			if (atsCardRecord.getId() == null || atsCardRecord.getId() == 0) {
				resultMsg = getText("添加", "打卡记录");
			} else {
				resultMsg = getText("更新", "打卡记录");
			}
			atsCardRecordService.save(atsCardRecord);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得打卡记录分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看打卡记录分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsCardRecord> list = atsCardRecordService.getAll(new QueryFilter(
				request, "atsCardRecordItem"));
		return this.getAutoView().addObject("atsCardRecordList", list);
	}

	/**
	 * 删除打卡记录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除打卡记录")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsCardRecordService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除打卡记录成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑打卡记录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑打卡记录")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsCardRecord atsCardRecord = atsCardRecordService.getById(id);

		return getAutoView().addObject("atsCardRecord", atsCardRecord)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得打卡记录明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看打卡记录明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsCardRecord atsCardRecord = atsCardRecordService.getById(id);
		return getAutoView().addObject("atsCardRecord", atsCardRecord);
	}

	/**
	 * 导出打卡记录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportData")
	@Action(description = "导出打卡记录")
	public void exportData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		boolean isTemplate = RequestUtil.getBoolean(request, "isTemplate");

//		List<AtsCardRecord> list = new ArrayList<AtsCardRecord>();
//		if (!isTemplate) {
//			list = atsCardRecordService.getAll();
//		}

		try {
			Excel excel = new Excel();
			excel.sheet().sheetName("员工打卡记录");// 重命名当前处于工作状态的表的名称
			List<String> titleList = new ArrayList<String>();
			titleList.add("考勤编号");
			titleList.add("打卡日期");
			titleList.add("打卡时间");
			titleList.add("打卡位置");
			excel.row(0, 0).value(titleList.toArray()).font(new IFontEditor() {
				// 设置字体
				@Override
				public void updateFont(Font font) {
					font.boldweight(BoldWeight.BOLD);// 粗体
				}
			}).bgColor(Color.GREY_25_PERCENT).width(3500);
			excel.row(0, 1).width(3500);
			excel.row(0, 2).width(3500);
			excel.row(0, 3).width(3500);

			excel.sheet().setDefaultColumnStyle(0, DataFormat.TEXT);
			excel.sheet().setDefaultColumnStyle(1, DataFormat.DATE);
			excel.sheet().setDefaultColumnStyle(2, DataFormat.TIME);
			excel.sheet().setDefaultColumnStyle(3, DataFormat.TEXT);
			// 取得表的数据
			String fileName = "员工打卡记录";
			ExcelUtil.downloadExcel(excel.getWorkBook(), fileName, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入数据保存
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("importData")
	@Action(description = "导入数据保存", detail = "导入业务数据模板数据")
	public void importData(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartFile file = request.getFile("file");
		String startDate = RequestUtil.getString(request, "startDate");
		String endDate = RequestUtil.getString(request, "endDate");
		ResultMessage message = null;
		try {
			String fileName = file.getOriginalFilename();
			String extName = fileName.substring(fileName.lastIndexOf(".") + 1,
					fileName.length());
			if (extName.equalsIgnoreCase("xlsx")
					|| extName.equalsIgnoreCase("xls")) {
				atsCardRecordService.importExcel(file.getInputStream(),
						startDate, endDate);
			} else if (extName.equalsIgnoreCase("txt")
					|| extName.equalsIgnoreCase("text")) {
				atsCardRecordService.importText(file.getInputStream(),
						startDate, endDate);
			} else {
				atsCardRecordService.importAccess(file, startDate, endDate);
			}
			message = new ResultMessage(ResultMessage.Success, "导入成功!");
		} catch (Exception e) {
			e.printStackTrace();
			message = new ResultMessage(ResultMessage.Fail,
					"导入出错了，请检查导入格式是否正确或者导入的数据是否有问题！");
		}
		writeResultMessage(response.getWriter(), message);

	}

}
