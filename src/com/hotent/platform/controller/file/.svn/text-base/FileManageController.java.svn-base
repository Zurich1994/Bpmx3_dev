package com.hotent.platform.controller.file;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.ContextUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.file.FileManage;
import com.hotent.platform.model.file.FileManageLog;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.service.file.FileManageLogService;
import com.hotent.platform.service.file.FileManageService;
import com.hotent.platform.service.system.GlobalTypeService;

/**
 * <pre>
 * 对象功能:文件管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014年6月17日21:25:43
 * </pre>
 */
@Controller
@RequestMapping("/platform/file/fileManage/")
public class FileManageController extends BaseController {

	@Resource
	private FileManageService fileManageService;

	@Resource
	private FileManageLogService fileManageLogService;

	@Resource
	private GlobalTypeService globalTypeService;

	/**
	 * 主入口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("main")
	@Action(description = "文件管理管理页面")
	public ModelAndView main(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.getAutoView();
	}

	/**
	 * 取得文件分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看文件分页列表")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = new QueryFilter(request, "fileManageItem");
		queryFilter.addFilter("userId", ContextUtil.getCurrentUserId());
		List<FileManage> list = fileManageService.getAll(queryFilter);
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		return this.getAutoView().addObject("fileManageList", list)
				.addObject("categoryId", categoryId)
				.addObject("loginerId", ContextUtil.getCurrentUserId());
	}

	/**
	 * 上传文件对话框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uploadFieldDialog")
	@Action(description = "上传文件对话框")
	public ModelAndView uploadFieldDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		List<GlobalType> globalTypeList = globalTypeService.getByCatKey(
				GlobalType.CAT_FILE, true);
		return this.getAutoView().addObject("categoryId", categoryId)
				.addObject("globalTypeList", globalTypeList);
	}

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uploadFile")
	@Action(description = "上传文件", execOrder = ActionExecOrder.AFTER)
	public void uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String jsonParams = RequestUtil.getString(request, "jsonParams");
		FileManage fileManage = fileManageService
				.getFileManageByJson(jsonParams);
		if (request instanceof DefaultMultipartHttpServletRequest) {
			MultiValueMap<String, MultipartFile> files = ((DefaultMultipartHttpServletRequest) request)
					.getMultiFileMap();
			Set<String> set = files.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				LinkedList<MultipartFile> fileList = (LinkedList) files.get(it
						.next());
				for (MultipartFile m : fileList) {
					try {
						// 文件存盘,返回存盘路径
						String fileRelativePath = fileManageService
								.saveFileToDisk(m);
						// 保存文件信息到数据库中
						fileManageService.saveToDB(m, fileManage,
								fileRelativePath);
					} catch (Exception e) {
						writeResultMessage(response.getWriter(), "上传失败!" + ","
								+ e.getMessage(), ResultMessage.Fail);
					}
				}
			}
		}
		writeResultMessage(response.getWriter(), "上传成功!", ResultMessage.Success);
	}

	/**
	 * 删除附件和记录信息
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除SYS_QUERY_SQL")
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "fileId");
			fileManageService.delFileByIds(lAryId);
			fileManageService.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败"
					+ ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 下载文件的方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("downLoadFile")
	@Action(description = "文件下载")
	public void downLoadFile(HttpServletRequest request,
			HttpServletResponse response) {
		Long fileId = RequestUtil.getLong(request, "fileId", 0);
		if (fileId == 0) {
			return;
		}
		// 找到数据库中此条记录的信息
		FileManage fileManage = fileManageService.getById(fileId);
		String fileSavePath = fileManage.getFilePath();
		String fileName = fileManage.getFileName() + "." + fileManage.getExt();
		InputStream fileinput = fileManageService.getFile(fileSavePath);
		response.setContentType("application/x-download");
		try {
			if (System.getProperty("file.encoding").equals("GBK")) {
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=\""
								+ new String(fileName.getBytes(), "ISO-8859-1")
								+ "\"");
			} else {
				response.setHeader(
						"Content-Disposition",
						"attachment;filename=\""
								+ URLEncoder.encode(fileName, "utf-8") + "\"");
			}
			ServletOutputStream out = response.getOutputStream();
			// 插入文件下载操作日志
			fileManageLogService.add(fileId,
					FileManageLog.OPERATE_TYPE_DOWNLOAD);
			IOUtils.copy(fileinput, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 预览文件界面
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("previewFileDialog")
	@Action(description = "文件预览对话框")
	public ModelAndView previewFileDialog(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return getAutoView().addObject("fileId",
				RequestUtil.getLong(request, "fileId", 0)).addObject("ext",
				RequestUtil.getString(request, "ext"));
	}

	/**
	 * 预览文件的方法
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("previewFile")
	@Action(description = "文件预览")
	public void previewFile(HttpServletRequest request,
			HttpServletResponse response) {
		Long fileId = RequestUtil.getLong(request, "fileId", 0);
		if (fileId == 0) {
			return;
		}
		// 找到数据库中此条记录的信息
		FileManage fileManage = fileManageService.getById(fileId);
		String fileSavePath = fileManage.getFilePath();
		InputStream fileinput = fileManageService.getFile(fileSavePath);
		response.setContentType("application/x-download");
		try {
			ServletOutputStream out = response.getOutputStream();
			IOUtils.copy(fileinput, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 编辑共享授权
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑共享授权")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long fileId = RequestUtil.getLong(request, "fileId", 0L);
		FileManage fileManage = fileManageService.getById(fileId);
		List<GlobalType> globalTypeList = globalTypeService.getByCatKey(
				GlobalType.CAT_FILE, true);
		return getAutoView().addObject("fileManage", fileManage).addObject(
				"globalTypeList", globalTypeList);
	}

	/**
	 * 更新
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("update")
	@Action(description = "添加或更新业务查询数据模板", execOrder = ActionExecOrder.AFTER)
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String jsonParams = RequestUtil.getString(request, "jsonParams");
		FileManage fileManage = fileManageService
				.getFileManageByJson(jsonParams);
		try {
			fileManageService.update(fileManage);
			writeResultMessage(response.getWriter(), "更新成功!",
					ResultMessage.Success);
		} catch (Exception e) {
			writeResultMessage(response.getWriter(),
					"更新失败!" + "," + e.getMessage(), ResultMessage.Fail);
		}
	}

}
