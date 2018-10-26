package com.hotent.platform.controller.system;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

//import org.activiti.engine.impl.util.json.JSONArray;
//import org.activiti.engine.impl.util.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.activiti.engine.impl.util.json.JSONStringer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.log.SysAuditThreadLocalHolder;
import com.hotent.core.report.ReportTemplateAnalysis;
import com.hotent.core.util.DateUtil;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.StringUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.ReportTemplate;
import com.hotent.platform.model.system.SysAuditModelType;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.ReportTemplateService;

/**
 * 对象功能:报表模板 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:cjj
 * 创建时间:2012-04-12 09:59:47
 */
@Controller
@RequestMapping("/platform/system/reportTemplate/")
@Action(ownermodel = SysAuditModelType.FORM_MANAGEMENT)
public class ReportTemplateController extends BaseController
{
	@Resource
	private ReportTemplateService reportTemplateService;
	
	@Resource
	private GlobalTypeService globalTypeService;
	
	@Resource
	private DbFuncService dbFuncService;
	
	/**
	 * 取得报表模板分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看报表模板分页列表",detail="查看报表模板分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ReportTemplate> list=reportTemplateService.getAll(new QueryFilter(request,"reportTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("reportTemplateList",list);
		
		return mv;
	}
	
	@RequestMapping("list1")
	@Action(description="查看报表模板分页列表",detail="查看报表模板分页列表")
	public ModelAndView list1(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<ReportTemplate> list=reportTemplateService.getAll(new QueryFilter(request,"reportTemplateItem"));
		ModelAndView mv=this.getAutoView().addObject("reportTemplateList",list);
		
		return mv;
	}
	
	/**
	 * 删除报表模板
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除报表模板",
			execOrder=ActionExecOrder.BEFORE,
			detail="删除报表模板：" +
					"<#list StringUtils.split(reportId,\",\") as item>" +
					"<#assign entity=reportTemplateService.getById(Long.valueOf(item))/>" +
					"【${entity.title}】" +
					"</#list>")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "reportId");
			reportTemplateService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除报表模板成功!");
		}
		catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败:" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("edit")
	@Action(description="添加或编辑班次",
			detail="<#if isAdd>添加班次:<#else>"+
					"编辑班次:" +
					"<#assign entity=reportTemplateService.getById(Long.valueOf(reportId))/>" +
					"【entity.title】</#if>")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long reportId=RequestUtil.getLong(request,"reportId");
		String returnUrl=RequestUtil.getPrePage(request);
		ReportTemplate reportTemplate=null;
		boolean isadd=true;
		if(reportId!=0){
			reportTemplate= reportTemplateService.getById(reportId);
			System.out.println("reportLocation:"+reportTemplate.getReportLocation());
			isadd=false;
		}else{
			reportTemplate=new ReportTemplate();
		}
		//添加系统日志信息 -B
		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
		
		List<GlobalType> list=globalTypeService.getByCatKey(GlobalType.CAT_REPORT, true);
		return getAutoView().addObject("reportTemplate",reportTemplate).addObject("returnUrl", returnUrl)
				.addObject("typelist", list);
	}

	/**
	 * 取得报表模板明细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看报表模板明细",detail="查看报表模板明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		long id=RequestUtil.getLong(request,"reportId");
		long canReturn=RequestUtil.getLong(request, "canReturn",0);
		ReportTemplate reportTemplate = reportTemplateService.getById(id);
		return getAutoView().addObject("reportTemplate", reportTemplate).addObject("canReturn",canReturn);
	}
	
	/**
	 * 添加或更新报表模板
	 * @param request
	 * @param response
	 * @param reportTemplate 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新流程任务评论",
			execOrder=ActionExecOrder.AFTER,
			detail="<#if isAdd>添加<#else>更新</#if>流程任务评论：" +
					"【${SysAuditLinkService.getReportTemplateLink(Long.valueOf(reportId))}】")
	public void save(MultipartHttpServletRequest request, HttpServletResponse response, ReportTemplate reportTemplate) throws Exception
	{
		String createTime = RequestUtil.getString(request, "tmpCreateTime");
		String st = "";
		if(StringUtil.isNotEmpty(createTime)){
			st = DateUtil.timeStrToDateStr(createTime);
		}
		boolean isadd=reportTemplate.getReportId()==null;
		
		Map<String, MultipartFile> files = request.getFileMap();
		Iterator<MultipartFile> it = files.values().iterator();
		
		if(it.hasNext()) {
			MultipartFile f = it.next();
			String oriFileName = f.getOriginalFilename();
		    
			//开始写入物理文件
			String filePath = FileUtil.getRootPath()+"WEB-INF"+File.separator+ReportTemplate.targetPath+File.separator+oriFileName.replace("\\", File.separator);	
			
			FileUtil.writeByte(filePath, f.getBytes()) ;
			
		    // 向数据库中添加数据 
			reportTemplateService.saveReportTemplate(reportTemplate,
					File.separator+"WEB-INF"+File.separator+ReportTemplate.targetPath+File.separator+oriFileName.replace("\\", File.separator),
					st.length()>0?DateUtil.parseDate(st):new Date());
		}
		
		PrintWriter writer =  response.getWriter();
		String result = "{\"result\":"+ResultMessage.Success+",\"message\":\"上传报表模板成功\""+"}";
		writer.print(result);
		//添加系统日志信息 -B
				try {
					SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
					SysAuditThreadLocalHolder.putParamerter("reportId", reportTemplate.getReportId().toString());
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(e.getMessage());
				}
	}
	
	
	@RequestMapping("searchdsname")
	@ResponseBody
	public String searchdsname(HttpServletRequest request , HttpServletResponse response) throws Exception{
		System.out.println("--searchdsname--");
		String title = request.getParameter("name");
		Long reportId = Long.parseLong(request.getParameter("reportId"));
		System.out.println("cpttitle:"+title);
		System.out.println("reportId:"+reportId);
		ReportTemplate reportTemplate = reportTemplateService.getById(reportId);
		String reportLocation = reportTemplate.getReportLocation();
		System.out.println("reportLocation:"+reportLocation);
		
		String needPath = FileUtil.getRootPath()+reportLocation;
		String targetStr = needPath.replace("\\\\", "\\");
		List<String> dsList = ReportTemplateAnalysis.dsNameAnalysis(targetStr);
		System.out.println("size:"+dsList.size());
		//System.out.println("needPath : "+needPath);
		System.out.println("needPath:"+needPath);
		String cptTitle = reportTemplate.getTitle();
		String dsJson = ReportTemplateAnalysis.toDsNameJson(cptTitle, dsList);
	
			
		return dsJson;
	}
	
	//@RequestMapping("searchcolumnname")
	//@ResponseBody
	//public String searchforfield(HttpServletRequest request , HttpServletResponse response) throws Exception{
		
	//	return null;
	//}
	
	
	/**
	 * 根据方法名查找字段。
	 * @param request
	 * @param response
	 * @author 王钊
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchforfield")
	@ResponseBody
	public String searchforfield(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		List<DbFunc> list = dbFuncService.getAll();	
		
		System.out.println("请求表名:"+request.getParameter("table_name"));
		System.out.println("请求方法名:"+request.getParameter("func_name"));
		System.out.println("请求类型:"+request.getParameter("type"));
    	String type = request.getParameter("type");
    	
    	String dsName = request.getParameter("func_name");
    	String title = request.getParameter("table_name");
    	System.out.println("title:"+title);
    	
    	String reportId = request.getParameter("reportId");
    	System.out.println("reportId:"+reportId);
    	
    	ReportTemplate reportTemplate = reportTemplateService.getReportTemplateByTitle(title);
    	String reportLocation = reportTemplate.getReportLocation();
		System.out.println("reportLocation:"+reportLocation);
		String needPath = FileUtil.getRootPath()+reportLocation;
		String targetStr = needPath.replace("\\\\", "\\");
		System.out.println("targetStr:"+targetStr);
		
		JSONObject jo1 = new JSONObject();
		jo1.put("success", true);
		jo1.put("msg", "获取成功.");
		jo1.put("wsdl",dsName);
		jo1.put("namespace",title);
		jo1.put("invokeUrl", "别名");
		jo1.put("method", dsName);
		jo1.put("soapaction", "report");
		jo1.put("serviceName", title);
		
		JSONObject conditionFieldJo = new JSONObject();
		conditionFieldJo.put("name", "条件字段");
		conditionFieldJo.put("type", "字段集");
		conditionFieldJo.put("nocheck", "false");
		JSONArray conditionFieldJa = new JSONArray();
		conditionFieldJo.put("children", conditionFieldJa);
		jo1.put("conditionField", conditionFieldJo);
		
		JSONObject setFieldJo = new JSONObject();
		setFieldJo.put("name", "设置字段");
		setFieldJo.put("type", "字段集");
		setFieldJo.put("nocheck", "false");
		JSONArray FieldJa = new JSONArray();
		JSONObject columnJo = new JSONObject();
		List<String> columnList = ReportTemplateAnalysis.getColumnName(targetStr, dsName);
		System.out.println("--columnListsize--:"+columnList.size());
		if(columnList.size() != 0){
			for(int i=0;i<columnList.size();i++){
				columnJo.put("name", columnList.get(i));
				columnJo.put("type", "varChar");
				columnJo.put("nocheck", "false");
				FieldJa.add(columnJo);
			}
		}
		setFieldJo.put("children", FieldJa);
		jo1.put("setField", setFieldJo);
		

			System.out.println("返回的字符串："+jo1.toString());
		   return jo1.toString();
	}

}
