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
import com.hotent.core.db.datasource.DataSourceUtil;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.FileUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.util.jsonobject.JSONObjectUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.bpm.ProcessRun;
import com.hotent.platform.model.system.SysDataSourceL;
import com.hotent.platform.service.bpm.ProcessRunService;
import com.hotent.platform.service.system.SysDataSourceLService;

/**
 * <pre>
 * 对象功能:SYS_DATA_SOURCE 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-08-21 10:50:18
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysDataSourceL/")
public class SysDataSourceLController extends BaseController {
	@Resource
	private SysDataSourceLService sysDataSourceLService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 添加或更新SYS_DATA_SOURCE。
	 * 
	 * @param request
	 * @param response
	 * @param sysDataSourceL
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新SYS_DATA_SOURCE")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String json = FileUtil.inputStream2String(request.getInputStream());
		SysDataSourceL sysDataSourceL = JSONObjectUtil.toBean(json, SysDataSourceL.class);

		try {
			if (sysDataSourceL.getId() == null) {
				sysDataSourceL.setId(UniqueIdUtil.genId());
				sysDataSourceLService.add(sysDataSourceL);
				writeResultMessage(response.getWriter(), "添加成功", ResultMessage.Success);

			} else {
				sysDataSourceLService.update(sysDataSourceL);
				writeResultMessage(response.getWriter(), "更新成功", ResultMessage.Success);
			}
			
			// 加入系统数据源
			if (sysDataSourceL.getEnabled()) {
				try {
					DataSourceUtil.addDataSource(sysDataSourceL.getAlias(), sysDataSourceLService.reflect(sysDataSourceL));
				} catch (Exception e) {
				}
				//DataSourceUtil.showAllDataSource();
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
		List<SysDataSourceL> list = sysDataSourceLService.getAll(new QueryFilter(request, "sysDataSourceLItem"));
		ModelAndView mv = this.getAutoView().addObject("sysDataSourceLList", list);

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
				message = new ResultMessage(ResultMessage.Success, "删除"+sysDataSourceLService.getById(lAryId[0]).getName()+"成功!");
			}
			sysDataSourceLService.delByIds(lAryId);
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
		SysDataSourceL sysDataSourceL = sysDataSourceLService.getById(id);
		Long runId = 0L;
		ProcessRun processRun = processRunService.getByBusinessKey(id.toString());
		if (BeanUtils.isNotEmpty(processRun)) {
			runId = processRun.getRunId();
		}
		return getAutoView().addObject("sysDataSourceL", sysDataSourceL).addObject("runId", runId);
	}

	@RequestMapping("getById")
	@Action(description = "查看SYS_DATA_SOURCE明细")
	@ResponseBody
	public SysDataSourceL getById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysDataSourceL sysDataSourceL = sysDataSourceLService.getById(id);
		return sysDataSourceL;
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
		SysDataSourceL sysDataSourceL = JSONObjectUtil.toBean(json, SysDataSourceL.class);
		boolean b = false;
		try {
			b = sysDataSourceLService.checkConnection(sysDataSourceL);
		} catch (Exception e) {
			b=false;
		}

		String resultMsg = "";

		if (b) {
			resultMsg = sysDataSourceL.getName() + ":连接成功";
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} else {
			resultMsg = sysDataSourceL.getName() + ":连接失败";
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Fail);
		}

	}

	private void testDs(SysDataSourceL sysDataSourceL) throws Exception {
		/*
		 * JdbcHelper jdbcHelper = JdbcHelper.getInstance(); jdbcHelper.addDataSource(sysDataSourceL.getAlias(), sysDataSourceLService.reflect(sysDataSourceL)); jdbcHelper.setCurrentDb(sysDataSourceL.getAlias()); List rows=jdbcHelper.queryForList("select * from sys_datasource", new HashMap()); for(Object row:rows){ Map map =((Map) row); for(Object key:map.keySet()){ System.out.println(key+":"+map.get(key)); } }
		 */
		try {
			DataSourceUtil.addDataSource(sysDataSourceL.getAlias(), sysDataSourceLService.reflect(sysDataSourceL));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//DataSourceUtil.showAllDataSource();

		DbContextHolder.setDataSource("dyg");
		List rows = jdbcTemplate.queryForList("select * from sys_datasource");
		for (Object row : rows) {
			Map map1 = ((Map) row);
			for (Object key : map1.keySet()) {
				System.out.println(key + ":" + map1.get(key));
			}
		}
	}
}
