//
//package com.hotent.platform.controller.system;
//
//
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.hotent.core.annotion.Action;
//import com.hotent.core.annotion.ActionExecOrder;
//import com.hotent.core.log.SysAuditThreadLocalHolder;
//import com.hotent.core.web.ResultMessage;
//import com.hotent.core.web.controller.BaseController;
//import com.hotent.core.web.query.QueryFilter;
//import com.hotent.core.web.util.RequestUtil;
//import com.hotent.platform.model.system.SysAuditModelType;
//import com.hotent.platform.model.system.SysDataSource;
//import com.hotent.platform.service.system.SysDataSourceService;
//
///**
// * 对象功能:系统数据源管理 控制器类
// * 开发公司:广州宏天软件有限公司
// * 开发人员:ray
// * 创建时间:2011-11-16 16:34:16
// */
//@Controller
//@RequestMapping("/platform/system/sysDataSource/")
//@Action(ownermodel=SysAuditModelType.SYSTEM_SETTING)
//public class SysDataSourceController extends BaseController
//{
//	@Resource
//	private SysDataSourceService service;
//	
//	
//	/**
//	 * 取得系统数据源分页列表
//	 * @param request
//	 * @param response
//	 * @param page
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("list")
//	@Action(description="查看系统数据源列表",detail="查看系统数据源列表")
//	public ModelAndView list(HttpServletRequest request,HttpServletResponse response, @RequestParam(value="page", defaultValue="1")int page) throws Exception
//	{	
//		List<SysDataSource> list=service.getAll(new QueryFilter(request,"sysDataSourceItem"));
//		ModelAndView mv=this.getAutoView().addObject("sysDataSourceList",list);
//		
//		return mv;
//	}
//
//	/**
//	 * 删除系统数据源
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	@RequestMapping("del")
//	@Action(description="删除系统数据源",
//			execOrder=ActionExecOrder.BEFORE,
//			detail="删除系统数据源"+
//					"<#list StringUtils.split(id,\",\") as item>" +
//					"<#assign entity=sysDataSourceService.getById(Long.valueOf(item))/>" +
//					"【${entity.name}】"+
//				"</#list>"
//					)
//	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		ResultMessage message=null;
//		String preUrl= RequestUtil.getPrePage(request);
//		try {
//			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
//			service.delByIds(lAryId);
//			message=new ResultMessage(ResultMessage.Success,"删除系统数据源成功");
//		} catch (Exception e) {
//			message=new ResultMessage(ResultMessage.Fail,"删除系统数据源失败");
//		}
//		addMessage(message, request);
//		response.sendRedirect(preUrl);
//		
//	}
//	
//	 /**
//     * 编辑系统数据源
//     * @param request
//     * @return
//     * @throws Exception
//     */
//	@RequestMapping("edit")
//	@Action(description="编辑系统数据源",
//			execOrder=ActionExecOrder.AFTER,
//			detail="<#if isAdd>添加系统数据源<#else>编辑系统数据源" +
//			"<#assign entity=SubSystemService.getById(Long.valueOf(id))/>" +
//			"【${entity.name}】</#if>"
//	)
//	public ModelAndView edit(HttpServletRequest request) throws Exception
//	{
//		Long id=RequestUtil.getLong(request,"id");
//		long canReturn=RequestUtil.getLong(request, "canReturn",0);
//		String returnUrl=RequestUtil.getPrePage(request);
//		SysDataSource sysDataSource=null;
//		boolean isadd=true;
//		if(id!=0){
//			sysDataSource= service.getById(id);
//			isadd=false;
//		}else{
//			sysDataSource=new SysDataSource();
//		}
//		SysAuditThreadLocalHolder.putParamerter("isAdd", isadd);
//		return getAutoView().addObject("sysDataSource",sysDataSource).addObject("returnUrl", returnUrl).addObject("canReturn",canReturn);
//	}
//
//	/**
//	 * 取得系统数据源管理明细
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("get")
//	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		long id=RequestUtil.getLong(request,"id");
//		SysDataSource po = service.getById(id);		
//		return this.getAutoView().addObject("sysDataSource", po);
//		
//	}
//	
//	/**
//	 * 测试数据源是否可以连接
//	 * @param request
//	 * @param response
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping("testConnectById")
//	public List<Map<String, Object>> testConnectById(HttpServletRequest request, HttpServletResponse response) throws Exception
//	{
//		Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
//		//测试连接
//		List<Map<String, Object>> result = service.testConnectById(lAryId);
//		return result;
//	}
//	
//	/**
//	 * 测试数据源是否可以连接
//	 * @param request
//	 * @param response
//	 * @param po
//	 * @return
//	 * @throws Exception
//	 */
//	@ResponseBody
//	@RequestMapping("testConnectByForm")
//	public List<Map<String, Object>> testConnectByForm(HttpServletRequest request, HttpServletResponse response, SysDataSource po) throws Exception
//	{
//		//测试连接
//		List<Map<String, Object>> result = service.testConnectByForm(po);
//		
//		return result;
//	}
//}
package com.hotent.platform.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.db.IRollBack;
import com.hotent.core.db.RollbackJdbcTemplate;
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.system.SysDataSourceService;

/**
 * <pre>
 * 对象功能:SYS_DATA_SOURCE 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-08-21 10:50:18
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysDataSource/")
public class SysDataSourceController extends BaseController {
	@Resource
	private SysDataSourceService sysDataSourceService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	RollbackJdbcTemplate rollbackJdbcTemplate;
	

	/**
	 * 添加或更新SYS_DATA_SOURCE。
	 * 
	 * @param request
	 * @param response
	 * @param sysDataSource
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新SYS_DATA_SOURCE")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		String sql="";
//		
//		rollbackJdbcTemplate.executeRollBack(new IRollBack() {
//			
//			@Override
//			public Object execute(String script, Map<String, Object> map) {
//				
//				JdbcTemplateUtil.execute(script);
//				
//				return null;
//			}
//		}, sql, null);
		
		
		String json = FileUtil.inputStream2String(request.getInputStream());
		SysDataSource sysDataSource = JSONObjectUtil.toBean(json, SysDataSource.class);

		try {
			if (sysDataSource.getId() == null) {
				sysDataSource.setId(UniqueIdUtil.genId());
				sysDataSourceService.add(sysDataSource);
				writeResultMessage(response.getWriter(), "添加成功", ResultMessage.Success);

			} else {
				sysDataSourceService.update(sysDataSource);
				writeResultMessage(response.getWriter(), "更新成功", ResultMessage.Success);
			}
			
			// 加入系统数据源
			if (sysDataSource.getEnabled()) {
				try {
					DataSourceUtil.addDataSource(sysDataSource.getAlias(), sysDataSourceService.getDsFromSysSource(sysDataSource));
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), e.getMessage(), ResultMessage.Fail);
		}
		
	}

	/**
	 * 取得SYS_DATA_SOURCE分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看SYS_DATA_SOURCE分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysDataSource> list = sysDataSourceService.getAll(new QueryFilter(request, "sysDataSourceItem"));
		ModelAndView mv = this.getAutoView().addObject("sysDataSourceList", list);

		return mv;
	}

	/**
	 * 删除SYS_DATA_SOURCE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除SYS_DATA_SOURCE")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			if(lAryId.length>1){
				message = new ResultMessage(ResultMessage.Success, "删除SYS_DATA_SOURCE成功!");
			}else{
				message = new ResultMessage(ResultMessage.Success, "删除"+sysDataSourceService.getById(lAryId[0]).getName()+"成功!");
			}
			sysDataSourceService.delByIds(lAryId);
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑SYS_DATA_SOURCE
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑SYS_DATA_SOURCE")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		String returnUrl = RequestUtil.getPrePage(request);
		return getAutoView().addObject("returnUrl", returnUrl);
	}

	/**
	 * 取得SYS_DATA_SOURCE明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看SYS_DATA_SOURCE明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysDataSource sysDataSource = sysDataSourceService.getById(id);
		Long runId = 0L;
		ProcessRun processRun = processRunService.getByBusinessKey(id.toString());
		if (BeanUtils.isNotEmpty(processRun)) {
			runId = processRun.getRunId();
		}
		return getAutoView().addObject("sysDataSource", sysDataSource).addObject("runId", runId);
	}

	@RequestMapping("getById")
	@Action(description = "查看SYS_DATA_SOURCE明细")
	@ResponseBody
	public SysDataSource getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysDataSource sysDataSource = sysDataSourceService.getById(id);
		return sysDataSource;
	}

	/**
	 * 改变当前的数据源
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *             void
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("checkConnection")
	public void checkConnection(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = FileUtil.inputStream2String(request.getInputStream());
		SysDataSource sysDataSource = JSONObjectUtil.toBean(json, SysDataSource.class);
		boolean b = false;
		try {
			b = sysDataSourceService.checkConnection(sysDataSource);
		} catch (Exception e) {
			b=false;
		}

		String resultMsg = "";

		if (b) {
			resultMsg = sysDataSource.getName() + ":连接成功";
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} else {
			resultMsg = sysDataSource.getName() + ":连接失败";
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Fail);
		}

	}
	
	/**
	 * 获取在容器的数据源，包含本地数据源
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             List<SysDataSource>
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping("getAll")
	@ResponseBody
	public List<SysDataSource> getAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysDataSource> dsList = sysDataSourceService.getAll();
		//添加本地默认数据源
		SysDataSource defaultSysDataSource = new SysDataSource();
		defaultSysDataSource.setAlias(DataSourceUtil.DEFAULT_DATASOURCE);
		defaultSysDataSource.setName("本地数据源");
		
		dsList.add(defaultSysDataSource);
		return dsList;
	}
}
