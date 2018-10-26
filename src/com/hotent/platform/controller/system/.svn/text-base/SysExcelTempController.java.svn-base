package com.hotent.platform.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fr.third.org.hsqldb.lib.StringUtil;
import com.hotent.core.annotion.Action;
import com.hotent.core.excel.util.ExcelUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysExcelTemp;
import com.hotent.platform.model.system.SysExcelTempDetail;
import com.hotent.platform.service.system.SysExcelTempService;

/**
 * <pre>
 * 对象功能:Excel模板 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2015-06-13 14:36:35
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysExcelTemp/")
public class SysExcelTempController extends BaseController {
	@Resource
	private SysExcelTempService sysExcelTempService;

	/**
	 * 添加或更新Excel模板。
	 * 
	 * @param request
	 * @param response
	 * @param sysExcelTemp
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新Excel模板")
	public void save(HttpServletRequest request, HttpServletResponse response,
			SysExcelTemp sysExcelTemp) throws Exception {
		String resultMsg = null;
		try {
			boolean isExist = sysExcelTempService.isTempCodeExist(
					sysExcelTemp.getTempCode(), sysExcelTemp.getId());
			if (isExist) {
				resultMsg = "该别名已经存在了！";
				writeResultMessage(response.getWriter(), resultMsg,
						ResultMessage.Fail);
				return;
			}

			if (sysExcelTemp.getId() == null || sysExcelTemp.getId() == 0) {
				sysExcelTempService.save(sysExcelTemp);
				resultMsg = getText("添加", "Excel模板");
			} else {
				sysExcelTempService.save(sysExcelTemp);
				resultMsg = getText("更新", "Excel模板");
			}
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

	/**
	 * 取得Excel模板分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看Excel模板分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<SysExcelTemp> list = sysExcelTempService.getAll(new QueryFilter(
				request, "sysExcelTempItem"));
		ModelAndView mv = this.getAutoView()
				.addObject("sysExcelTempList", list);
		return mv;
	}

	/**
	 * 删除Excel模板
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除Excel模板")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysExcelTempService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success,
					"删除Excel模板及其从表成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑Excel模板
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑Excel模板")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		String returnUrl = RequestUtil.getPrePage(request);
		SysExcelTemp sysExcelTemp = sysExcelTempService.getById(id);
		List<SysExcelTempDetail> sysExcelTempDetailList = sysExcelTempService
				.getSysExcelTempDetailList(id);

		return getAutoView().addObject("sysExcelTemp", sysExcelTemp)
				.addObject("sysExcelTempDetailList", sysExcelTempDetailList)
				.addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得Excel模板明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看Excel模板明细")
	public ModelAndView get(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysExcelTemp sysExcelTemp = sysExcelTempService.getById(id);
		List<SysExcelTempDetail> sysExcelTempDetailList = sysExcelTempService
				.getSysExcelTempDetailList(id);
		return getAutoView().addObject("sysExcelTemp", sysExcelTemp).addObject(
				"sysExcelTempDetailList", sysExcelTempDetailList);
	}

	/**
	 * 上传案例模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importTempDataSample")
	public void importTempDataSample(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String resultMsg = null;
		try {
			Long id = RequestUtil.getLong(request, "id");
			List<Map<String,Object>> dataSampleList = sysExcelTempService.readExcelContent(file);
			JSONArray jArry = JSONArray.fromObject(dataSampleList);
			String dataSample = jArry.toString();
			sysExcelTempService.updateTempDataSample(id,dataSample);
			resultMsg = "上传数据成功";
			writeResultMessage(response.getWriter(), resultMsg,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), "操作失败," + e.getMessage(),
					ResultMessage.Fail);
		}
	}

	/**
	 * 导出案例模板
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportTempDataSample")
	public void exportTempDataSample(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysExcelTemp excelTemp = sysExcelTempService.getById(id); 
		List<SysExcelTempDetail> sysExcelTempDetailList = sysExcelTempService.getSysExcelTempDetailList(id);
		
		HSSFWorkbook workBook = sysExcelTempService.getExcel(excelTemp,sysExcelTempDetailList,excelTemp.getTempDataSample());
		ExcelUtil.downloadExcel(workBook, excelTemp.getTempName(), response);
	}
	
	/**
	 * 取得所有的Excel模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAll")
	@ResponseBody
	public List<SysExcelTemp> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysExcelTemp> list = sysExcelTempService.getAll();
		return list;
	}

	/**
	 * 取得所有的Excel模板
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAllDetailByTempCode")
	@ResponseBody
	public List<SysExcelTempDetail> getAllDetailByTempCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String tempCode = RequestUtil.getString(request, "tempCode");
		List<SysExcelTempDetail> list = sysExcelTempService.getAllDetailByTempCode(tempCode);
		return list;
	}
	
	/**
	 * 上传对话框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importDialog")
	public ModelAndView importDialog(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getAutoView();
	}

	/**
	 * 上传数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importTempData")
	public void importTempData(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			List<Map<String,Object>> dataSampleList = sysExcelTempService.readExcelContent(file);
			JSONArray jArry = JSONArray.fromObject(dataSampleList);
			String dataSample = jArry.toString();
			writeResultMessage(response.getWriter(), dataSample,
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(), "操作失败," + e.getMessage(),
					ResultMessage.Fail);
		}
	}
	
	/**
	 * 导出数据
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("exportTempData")
	public void exportTempData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String tempCode = RequestUtil.getString(request, "tempCode");
		SysExcelTemp excelTemp = sysExcelTempService.getByTempCode(tempCode);
		List<SysExcelTempDetail> sysExcelTempDetailList = sysExcelTempService.getAllDetailByTempCode(tempCode);
		
		String exportData = RequestUtil.getString(request, "exportData");
		if(StringUtil.isEmpty(exportData) || JSONArray.fromObject(exportData) == null 
				|| JSONArray.fromObject(exportData).isEmpty()){
			exportData = excelTemp.getTempDataSample();
		}
		
		HSSFWorkbook workBook = sysExcelTempService.getExcel(excelTemp,sysExcelTempDetailList,exportData);
		ExcelUtil.downloadExcel(workBook, excelTemp.getTempName(), response);
	}
	

}
