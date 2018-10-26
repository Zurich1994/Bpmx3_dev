package com.hotent.platform.controller.ats;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.excel.Excel;
import com.hotent.core.excel.editor.IFontEditor;
import com.hotent.core.excel.editor.SheetEditor;
import com.hotent.core.excel.style.Color;
import com.hotent.core.excel.style.font.BoldWeight;
import com.hotent.core.excel.style.font.Font;
import com.hotent.core.excel.util.ExcelUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.ExceptionUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.ats.AtsAttencePolicy;
import com.hotent.platform.model.ats.AtsAttendanceFile;
import com.hotent.platform.model.ats.AtsHolidayPolicy;
import com.hotent.platform.model.ats.AtsShiftInfo;
import com.hotent.platform.model.system.SysUser;
import com.hotent.platform.service.ats.AtsAttencePolicyService;
import com.hotent.platform.service.ats.AtsAttendanceFileService;
import com.hotent.platform.service.ats.AtsHolidayPolicyService;
import com.hotent.platform.service.ats.AtsShiftInfoService;
import com.hotent.platform.service.bpm.thread.MessageUtil;
import com.hotent.platform.service.system.SysUserService;
import com.hotent.platform.service.system.UserPositionService;
import com.hotent.platform.xml.util.MsgUtil;

/**
 * <pre>
 * 对象功能:考勤档案 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2015-05-20 09:11:05
 * </pre>
 */
@Controller
@RequestMapping("/platform/ats/atsAttendanceFile/")
public class AtsAttendanceFileController extends BaseController {
	@Resource
	private AtsAttendanceFileService atsAttendanceFileService;
	@Resource
	private AtsAttencePolicyService atsAttencePolicyService;
	@Resource
	private AtsHolidayPolicyService atsHolidayPolicyService;
	@Resource
	private AtsShiftInfoService atsShiftInfoService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private UserPositionService userPositionService;

	/**
	 * 添加或更新考勤档案。
	 * 
	 * @param request
	 * @param response
	 * @param atsAttendanceFile
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新考勤档案")
	public void save(HttpServletRequest request, HttpServletResponse response,
			AtsAttendanceFile atsAttendanceFile) throws Exception {
		String resultMsg = null;
		try {
			if (atsAttendanceFile.getId() == null
					|| atsAttendanceFile.getId() == 0) {
				// 判断是否已经新增
				List<AtsAttendanceFile> list = atsAttendanceFileService
						.getByUserId(atsAttendanceFile.getUserId());
				if (BeanUtils.isNotEmpty(list)) {
					resultMsg = "该用户已经添加了考勤档案！";
					writeResultMessage(response.getWriter(), resultMsg,
							ResultMessage.Fail);
					return;
				}
				resultMsg = getText("添加", "考勤档案");
			} else {
				resultMsg = getText("更新", "考勤档案");
			}
			atsAttendanceFileService.save(atsAttendanceFile);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	private void setAtsAttendanceFile(AtsAttendanceFile atsAttendanceFile) {
		if (BeanUtils.isEmpty(atsAttendanceFile))
			return;
		if (BeanUtils.isNotEmpty(atsAttendanceFile.getAttencePolicy())) {
			AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService
					.getById(atsAttendanceFile.getAttencePolicy());
			if (BeanUtils.isNotEmpty(atsAttencePolicy))
				atsAttendanceFile.setAttencePolicyName(atsAttencePolicy
						.getName());
		}
		if (BeanUtils.isNotEmpty(atsAttendanceFile.getHolidayPolicy())) {
			AtsHolidayPolicy atsHolidayPolicy = atsHolidayPolicyService
					.getById(atsAttendanceFile.getHolidayPolicy());
			if (BeanUtils.isNotEmpty(atsHolidayPolicy))
				atsAttendanceFile.setHolidayPolicyName(atsHolidayPolicy
						.getName());
		}

		if (BeanUtils.isNotEmpty(atsAttendanceFile.getDefaultShift())) {
			AtsShiftInfo atsShiftInfo = atsShiftInfoService
					.getById(atsAttendanceFile.getDefaultShift());
			if (BeanUtils.isNotEmpty(atsShiftInfo))
				atsAttendanceFile.setDefaultShiftName(atsShiftInfo.getName());
		}

		if (BeanUtils.isNotEmpty(atsAttendanceFile.getUserId())) {
			SysUser sysUser = sysUserService.getById(atsAttendanceFile
					.getUserId());
			if (BeanUtils.isNotEmpty(sysUser)) {
				atsAttendanceFile.setUserName(sysUser.getFullname());
				atsAttendanceFile.setAccount(sysUser.getAccount());
				String orgNames = userPositionService
						.getOrgnamesByUserId(sysUser.getUserId());
				atsAttendanceFile.setOrgName(orgNames.toString());
			}

		}
	}

	/**
	 * 取得考勤档案分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看考勤档案分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AtsAttendanceFile> list = atsAttendanceFileService
				.getAll(new QueryFilter(request, "atsAttendanceFileItem"));
		for (AtsAttendanceFile atsAttendanceFile : list) {
			setAtsAttendanceFile(atsAttendanceFile);
		}
		return this.getAutoView().addObject("atsAttendanceFileList", list);
	}

	/**
	 * 删除考勤档案
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除考勤档案")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			atsAttendanceFileService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除考勤档案成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑考勤档案
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑考勤档案")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		AtsAttendanceFile atsAttendanceFile = atsAttendanceFileService
				.getById(id);
		setAtsAttendanceFile(atsAttendanceFile);

		atsAttendanceFile = setDefaultAttendanceFile(atsAttendanceFile);
		return getAutoView().addObject("atsAttendanceFile", atsAttendanceFile)
				.addObject("returnUrl", returnUrl);
	}

	private AtsAttendanceFile setDefaultAttendanceFile(
			AtsAttendanceFile atsAttendanceFile) {
		if (BeanUtils.isNotEmpty(atsAttendanceFile))
			return atsAttendanceFile;
		atsAttendanceFile = new AtsAttendanceFile();
		AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService
				.getByDefault();
		if (BeanUtils.isNotEmpty(atsAttencePolicy)) {
			atsAttendanceFile.setAttencePolicy(atsAttencePolicy.getId());
			atsAttendanceFile.setAttencePolicyName(atsAttencePolicy.getName());
		}

		AtsHolidayPolicy atsHolidayPolicy = atsHolidayPolicyService
				.getByDefault();
		if (BeanUtils.isNotEmpty(atsHolidayPolicy)) {
			atsAttendanceFile.setHolidayPolicy(atsHolidayPolicy.getId());
			atsAttendanceFile.setHolidayPolicyName(atsHolidayPolicy.getName());
		}

		AtsShiftInfo atsShiftInfo = atsShiftInfoService.getByDefault();
		if (BeanUtils.isNotEmpty(atsShiftInfo)) {
			atsAttendanceFile.setDefaultShift(atsShiftInfo.getId());
			atsAttendanceFile.setDefaultShiftName(atsShiftInfo.getName());
		}
		return atsAttendanceFile;
	}

	/**
	 * 取得考勤档案明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看考勤档案明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		AtsAttendanceFile atsAttendanceFile = atsAttendanceFileService
				.getById(id);
		return getAutoView().addObject("atsAttendanceFile", atsAttendanceFile);
	}

	/**
	 * 取得考勤档案分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		return this.getAutoView().addObject("isSingle", isSingle);
	}

	/**
	 * 取得考勤档案分页列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("selector")
	@Action(description = "取得考勤档案分页列表")
	public ModelAndView selector(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String isSingle = RequestUtil.getString(request, "isSingle", "false");
		List<AtsAttendanceFile> list = atsAttendanceFileService
				.getAllList(new QueryFilter(request, "atsAttendanceFileItem"));
		for (AtsAttendanceFile atsAttendanceFile : list) {
			setAtsAttendanceFile(atsAttendanceFile);
		}
		return this.getAutoView().addObject("atsAttendanceFileList", list)
				.addObject("isSingle", isSingle);
	}

	/**
	 * 未建档人员
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("disUser")
	@Action(description = "未建档人员")
	public ModelAndView disUser(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<SysUser> list = atsAttendanceFileService
				.getDisUserList(new QueryFilter(request, "sysUserItem"));

		return this.getAutoView().addObject("sysUserList", list);
	}

	/**
	 * 保存新增
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAdd")
	@ResponseBody
	public String saveAdd(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long[] aryId = RequestUtil.getLongAryByStr(request, "aryId");

		JSONObject json = new JSONObject();
		AtsAttendanceFile atsAttendanceFile = new AtsAttendanceFile();
		AtsAttencePolicy atsAttencePolicy = atsAttencePolicyService
				.getByDefault();
		if (BeanUtils.isNotEmpty(atsAttencePolicy)) {
			atsAttendanceFile.setAttencePolicy(atsAttencePolicy.getId());
		} else {
			json.element("success", false).element("msg", "请至少设置一个考勤制度为默认");
			return json.toString();
		}

		AtsHolidayPolicy atsHolidayPolicy = atsHolidayPolicyService
				.getByDefault();
		if (BeanUtils.isNotEmpty(atsHolidayPolicy)) {
			atsAttendanceFile.setHolidayPolicy(atsHolidayPolicy.getId());
		} else {
			json.element("success", false).element("msg", "请至少设置一个假期制度为默认");
			return json.toString();
		}
		AtsShiftInfo atsShiftInfo = atsShiftInfoService.getByDefault();
		if (BeanUtils.isNotEmpty(atsShiftInfo)) {
			atsAttendanceFile.setDefaultShift(atsShiftInfo.getId());
		} else {
			json.element("success", false).element("msg", "请至少设置一个班次为默认");
			return json.toString();
		}

		json.element("success", true);
		try {
			atsAttendanceFileService.saveAdd(aryId, atsAttendanceFile, request);
		} catch (Exception e) {
			e.printStackTrace();
			json.element("success", false);
			json.element("msg", "出错了" + e.getMessage());
		}

		return json.toString();
	}

	/**
	 * 设置考勤档案
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("setting")
	@Action(description = "编辑考勤档案")
	public ModelAndView setting(HttpServletRequest request) throws Exception {
		AtsAttendanceFile atsAttendanceFile = setDefaultAttendanceFile(null);
		return getAutoView().addObject("atsAttendanceFile", atsAttendanceFile);
	}

	/**
	 * 添加或更新考勤档案。
	 * 
	 * @param request
	 * @param response
	 * @param atsAttendanceFile
	 *            添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("saveAddSetting")
	@Action(description = "添加或更新考勤档案")
	public void saveAddSetting(HttpServletRequest request,
			HttpServletResponse response, AtsAttendanceFile atsAttendanceFile)
			throws Exception {
		Long[] aryId = RequestUtil.getLongAryByStr(request, "aryId");
		String resultMsg = null;
		try {
			resultMsg = getText("添加成功");
			atsAttendanceFileService.saveAdd(aryId, atsAttendanceFile, request);
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 导出考勤档案
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportData")
	@Action(description = "导出考勤档案")
	public void exportData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String [] accounts = RequestUtil.getStringAryByStr(request, "accounts");
		String [] userNames = RequestUtil.getStringAryByStr(request, "userNames");
		String [] shiftNames = RequestUtil.getStringAryByStr(request, "shiftNames");
		String [] attencePolicys = RequestUtil.getStringAryByStr(request, "attencePolicys");
		String [] holidayPolicys = RequestUtil.getStringAryByStr(request, "holidayPolicys");
		
		
		try {
			// 取得表的数据
			String fileName = "考勤档案列表";
			Excel excel = new Excel();
			SheetEditor sheet = excel.sheet();
			sheet.sheetName(fileName);// 重命名当前处于工作状态的表的名称
			String [] titleAry =	new String[] {
					"员工编号",
					"姓名",
					"考勤编号",
					"打卡考勤",
					"考勤制度",
					"假期制度",
					"默认班次"
			};
			excel.row(0, 0).value(titleAry).font(new IFontEditor() {
				// 设置字体
				@Override
				public void updateFont(Font font) {
					font.boldweight(BoldWeight.BOLD);// 粗体
				}
			}).bgColor(Color.GREY_25_PERCENT).width(3500);
			
			
	
			if(BeanUtils.isNotEmpty(accounts)){
				List<String> accountList  = new ArrayList<String>();
				List<String> userNameList  = new ArrayList<String>();
				for (int i = 0; i < accounts.length; i++) {
					accountList.add(accounts[i]);
					userNameList.add(userNames[i]);
				}
				excel.column(0,1).value(accountList.toArray()).width(3000);
				excel.column(1,1).value(userNameList.toArray()).width(3000);
				excel.column(2,1).value(accountList.toArray()).width(3000);
			}else{
		
	
			}
			sheet.addValidationData(1,3,new String[] {"是","否"});
			sheet.addValidationData(1,4,attencePolicys);
			sheet.addValidationData(1,5,holidayPolicys);
			sheet.addValidationData(1,6,shiftNames);
			excel.column(1,1).width(3000);
			excel.column(2,1).width(3000);
			excel.column(3,1).width(3000);
			excel.column(4,1).width(3000);
			excel.column(5,1).width(3000);
			excel.column(6,1).width(4000);

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
		
		ResultMessage message = null;
		try {
			atsAttendanceFileService.importExcel(file.getInputStream());
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
