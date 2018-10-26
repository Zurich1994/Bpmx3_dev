package com.hotent.platform.controller.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.file.FileManageLog;
import com.hotent.platform.service.file.FileManageLogService;

/**
 * <pre>
 * 对象功能:文件日志管理 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:ouxb
 * 创建时间:2014年6月17日21:25:43
 * </pre>
 */
@Controller
@RequestMapping("/platform/file/fileManageLog/")
public class FileManageLogController extends BaseController {

	@Resource
	private FileManageLogService fileManageLogService;
	
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
	 * 取得文件下载操作分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("downloadOperList")
	@Action(description = "查看文件下载操作分页列表")
	public ModelAndView downloadOperList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = new QueryFilter(request, "fileManageLogItem");
		queryFilter.addFilter("operateType",
				FileManageLog.OPERATE_TYPE_DOWNLOAD);
		List<FileManageLog> list = fileManageLogService.getAll(queryFilter);
		Long categoryId = RequestUtil.getLong(request, "categoryId");
		return this.getAutoView().addObject("fileManageLogList", list)
				.addObject("categoryId", categoryId);
	}
}
