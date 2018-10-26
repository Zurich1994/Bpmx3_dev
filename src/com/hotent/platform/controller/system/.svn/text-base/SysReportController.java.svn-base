package com.hotent.platform.controller.system;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.model.system.SysReport;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysDataSourceService;
import com.hotent.platform.service.system.SysReportService;
import com.hotent.platform.service.system.SysTypeKeyService;
import com.hotent.platform.service.system.SysUserService;
@Controller
@RequestMapping("/platform/system/sysReport/")
public class SysReportController extends BaseController {
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private SysTypeKeyService sysTypeKeyService;
	@Resource
	private SysUserService sysUserService;
	@Resource
	private SysReportService service;
	@Resource
	private SysDataSourceService sysDataSourceService;
	
	@RequestMapping("list")
	@Action(description="报表列表")
	public ModelAndView  list(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		Long typeId=RequestUtil.getLong(request, "typeId");
		QueryFilter filter=new QueryFilter(request,"sysReportItem");
		if(typeId!=0){
			filter.addFilter("typeId", typeId);
		}
		List<SysReport> list = service.getAll(filter);
		return	this.getAutoView().addObject("sysReportList", list).addObject("typeId",typeId);
	}
	
	/**
	 * 取得报表明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "添加或编辑报表")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "reportId",0);
		SysReport sysReport = service.getById(id);
		Map<String, Object> result = service.getParamList(sysReport);
		List<SysDataSource> sysDataSourceList = sysDataSourceService.getAll();
		ModelAndView mv = getAutoView();
		mv.addObject("report",sysReport);
		mv.addObject("dsList", sysDataSourceList);
		mv.addAllObjects(result);
		return mv;
	}
	
	/**
	 * 删除报表。
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("del")
	@Action(description = "删除报表", execOrder = ActionExecOrder.BEFORE, exectype = "管理日志", 
			detail = "删除报表" + "<#list StringUtils.split(ids,\",\") as item>"
			+ "<#assign entity=service.getById(Long.valueOf(item))/>" + "【${entity.fileName}】" + "</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResultMessage message = null;
		Long[] lAryId = RequestUtil.getLongAryByStr(request, "reportId");
		String returnUrl = RequestUtil.getPrePage(request);
		try {
			for (Long id : lAryId) {
				SysReport sysReport = service.getById(id);
				String filePath = sysReport.getFilePath();
				filePath = AppUtil.getRealPath(filePath);
				FileUtil.deleteDir(new File(filePath));
			}
			// 删除数据库中数据
			service.delByIds(lAryId);
			message = new ResultMessage(ResultMessage.Success, "删除报表成功");
		} catch (Exception e) {
			message = new ResultMessage(ResultMessage.Fail, "删除报表失败");
		}
		addMessage(message, request);
		response.sendRedirect(returnUrl);
	}
	
	@RequestMapping("show")
	@Action(description="报表展示")
	public ModelAndView show(HttpServletRequest request,HttpServletResponse response) throws Exception{

		Long reportId = RequestUtil.getLong(request, "reportId") ;
		Map<String,Object> map = new HashMap<String, Object>() ;
		int totalCount = RequestUtil.getInt(request, "t", 0);
		Map<String,Object> paramMap = RequestUtil.getParameterValueMap(request, true, false) ;
		int pageIndex = RequestUtil.getInt(request, "p", 1) ;
		int pageSize = RequestUtil.getInt(request, "z", 20) ;
		
		map.put("reportId", reportId) ;
		map.put("pageIndex", pageIndex) ;
		map.put("pageSize", pageSize) ;
		map.put("paramMap", paramMap) ;
		map.put("totalCount", totalCount);
		//设置报表图片上下文路径
		map.put("contextPath",request.getContextPath());
		Map<String,Object> result = service.show(map) ;
		result.put("paramMap", JSON.toJSON(paramMap));
		return this.getAutoView().addAllObjects(result) ;
	}
	
	@RequestMapping("export")
	@Action(description="导出报表")
	public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try{
			Long reportId = RequestUtil.getLong(request, "reportId",0) ;
			//要导出的报表类型	xls、pdf、html等
			String exportType = RequestUtil.getString(request, "exportType") ;
			SysReport sysReport=service.getById(reportId);
			String filePath = AppUtil.getRealPath(sysReport.getFilePath()) ;
			//取得预览时保存在session中的参数值
	        Map<String, Object> params = RequestUtil.getQueryMap(request);
	        if(params==null) 
	        	params = new HashMap<String, Object>();
	        params.put("totalCount", RequestUtil.getInt(request, "t",1));
	        params.put("filePath", filePath);
	        params.put("sysReport", sysReport);
	        
	        service.export(exportType,params) ;
	        FileUtil.downLoadFile(request, response, filePath.replace(sysReport.getExt(), exportType), sysReport.getFileName()+"."+exportType);
		}catch (Exception e) {
			writeResultMessage(response.getWriter(),"导出报表出错！",ResultMessage.Fail);
		}
	}
}
