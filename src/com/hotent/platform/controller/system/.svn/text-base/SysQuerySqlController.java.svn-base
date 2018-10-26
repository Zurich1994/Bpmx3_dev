package com.hotent.platform.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.db.IRollBack;
import com.hotent.core.db.RollbackJdbcTemplate;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.model.system.SysQueryField;
import com.hotent.platform.model.system.SysQuerySetting;
import com.hotent.platform.model.system.SysQuerySql;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysDataSourceService;
import com.hotent.platform.service.system.SysQueryFieldService;
import com.hotent.platform.service.system.SysQuerySettingService;
import com.hotent.platform.service.system.SysQuerySqlService;

/**
 * <pre>
 * 对象功能:SYS_QUERY_SQL 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Aschs
 * 创建时间:2014-05-22 12:42:13
 * </pre>
 */
@Controller
@RequestMapping("/platform/system/sysQuerySql/")
public class SysQuerySqlController extends BaseController {
	@Resource
	private SysQuerySqlService sysQuerySqlService;
	@Resource
	private SysQueryFieldService sysQueryFieldService;
	@Resource
	private SysQuerySettingService sysQuerySettingService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private RollbackJdbcTemplate rollbackJdbcTemplate;
	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 带分类的tab入口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manage")
	public ModelAndView manage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		return mv;
	}

	/**
	 * 带分类的入口
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("manageTree")
	public ModelAndView manageTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		return mv;
	}

	/**
	 * 添加或更新SYS_QUERY_SQL。
	 * 
	 * @param request
	 * @param response
	 * @param sysQuerySql
	 *            添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description = "添加或更新SYS_QUERY_SQL")
	public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String resultMsg = null;
		try {
			String jsonStr = RequestUtil.getString(request, "jsonStr", "", false);
			String fieldJson = RequestUtil.getString(request, "fieldJson", null);
			SysQuerySql sysQuerySql = sysQuerySqlService.getSysQuerySql(jsonStr);
			if (sysQuerySql.getId() == null || sysQuerySql.getId() == 0) {
				sysQuerySql.setId(UniqueIdUtil.genId());
				sysQuerySqlService.add(sysQuerySql);

				DbContextHolder.setDataSource(sysQuerySql.getDsalias());
				List<SysQueryField> sysQueryFields = sysQueryFieldService.getSqlField(sysQuerySql.getId(), sysQuerySql.getSql());
				DbContextHolder.setDefaultDataSource();
				for (SysQueryField sysQueryField : sysQueryFields) {
					sysQueryFieldService.add(sysQueryField);
				}

				resultMsg = getText("添加成功", "SYS_QUERY_SQL");
			} else {
				sysQuerySqlService.update(sysQuerySql);
				// -----------保存字段-----------start
				List<SysQueryField> sysQueryFields = sysQueryFieldService.getSysQueryFieldArr(fieldJson);
				sysQueryFieldService.saveOrUpdate(sysQueryFields);
				if (sysQueryFields != null && !sysQueryFields.isEmpty()) {
					SysQuerySetting sysQuerySetting = sysQuerySettingService.getBySqlId(sysQuerySql.getId());
					if (sysQuerySetting != null) {
						// 同步json
						sysQuerySettingService.syncSettingAndField(sysQuerySetting, sysQueryFields, sysQuerySql);
					}
				}
				// -----------保存字段-----------end

				resultMsg = getText("更新成功", "SYS_QUERY_SQL");
			}
			writeResultMessage(response.getWriter(), resultMsg, ResultMessage.Success);
		} catch (Exception e) {
			e.printStackTrace();
			writeResultMessage(response.getWriter(), resultMsg + "," + e.getMessage(), ResultMessage.Fail);
		}

	}

	/**
	 * 取得SYS_QUERY_SQL分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看SYS_QUERY_SQL分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysQuerySql> list = sysQuerySqlService.getAll(new QueryFilter(request, "sysQuerySqlItem"));
		List<GlobalType> globalTypeList = globalTypeService.getByCatKey(GlobalType.CAT_FORM, true);
		for (SysQuerySql sysQuerySql : list) {
			for (GlobalType globalType : globalTypeList) {
				if (sysQuerySql.getCategoryId() == globalType.getTypeId()) {
					sysQuerySql.setCategoryName(globalType.getTypeName());
				}
			}
		}
		return this.getAutoView().addObject("sysQuerySqlList", list);
	}

	/**
	 * 删除SYS_QUERY_SQL
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description = "删除SYS_QUERY_SQL")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String preUrl = RequestUtil.getPrePage(request);
		ResultMessage message = null;
		try {
			Long[] lAryId = RequestUtil.getLongAryByStr(request, "id");
			sysQuerySqlService.delByIds(lAryId);

			// 删除关联的field
			sysQueryFieldService.delBySqlIds(lAryId);

			// 关联的setting
			sysQuerySettingService.delBySqlIds(lAryId);

			message = new ResultMessage(ResultMessage.Success, "删除成功!");
		} catch (Exception ex) {
			message = new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	/**
	 * 编辑SYS_QUERY_SQL
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑SYS_QUERY_SQL")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id", 0L);
		SysQuerySql sysQuerySql = sysQuerySqlService.getById(id);

		List<SysDataSource> sysDataSources = AppUtil.getBean(SysDataSourceService.class).getAll();

		List<SysQueryField> sysQueryFields = new ArrayList<SysQueryField>();
		List<GlobalType> globalTypeCATFORMList = globalTypeService.getByCatKey(GlobalType.CAT_FORM, true);
		List<GlobalType> globalTypesDICList = globalTypeService.getByCatKey(GlobalType.CAT_DIC, true);
		if (id != 0L) {
			sysQueryFields = sysQueryFieldService.getListBySqlId(id);
		}
		if(BeanUtils.isEmpty(sysDataSources)||sysDataSources.size()==0){
			sysDataSources = new ArrayList<SysDataSource>();
		}
		SysDataSource sds = new SysDataSource();
		sds.setAlias("LOCAL");
		sds.setName("本地数据源");
		sysDataSources.add(sds);
		return getAutoView().addObject("sysQuerySql", JSONObject.fromObject(sysQuerySql))
				.addObject("sysQueryFields", JSONArray.fromObject(sysQueryFields))
				.addObject("dsList", JSONArray.fromObject(sysDataSources))
				.addObject("globalTypesDICList", JSONArray.fromObject(globalTypesDICList))
				.addObject("globalTypeCATFORMList", JSONArray.fromObject(globalTypeCATFORMList));
	}

	/**
	 * 取得SYS_QUERY_SQL明细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description = "查看SYS_QUERY_SQL明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		SysQuerySql sysQuerySql = sysQuerySqlService.getById(id);
		return getAutoView().addObject("sysQuerySql", sysQuerySql);
	}

	/**
	 * 验证创建视图查询语句 是否正确
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("validSql")
	@ResponseBody
	public boolean validSql(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
		String sql = request.getParameter("sql");
		String dsalias = RequestUtil.getString(request, "dsalias", "");
		String mapStr = RequestUtil.getString(request, "map", "{}");// 参数map
		JSONObject map = JSONObject.fromObject(mapStr);
		Boolean rollback = RequestUtil.getBoolean(request, "rollback", true);// 是否回滚
		try {
			DbContextHolder.setDataSource(dsalias);
			Boolean b = validSql(explainSql(sql, map), rollback);
			return b;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 验证sql
	 * @param sql	：sql
	 * @param rollback	:是否启动事务回滚
	 * @return 
	 * Boolean
	 * @exception 
	 * @since  1.0.0
	 */
	private Boolean validSql(String sql, boolean rollback) {
		if (!rollback) {
			jdbcTemplate.execute(sql);
			return true;
		}

		Map<String, Object> param = new HashMap<String, Object>();
		Boolean b = (Boolean) rollbackJdbcTemplate.executeRollBack(new IRollBack() {

			public Object execute(String script, Map<String, Object> map) {
				try {
					jdbcTemplate.execute(script);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}

				return true;
			}
		}, sql, param);
		
		return b;
	}
	
	/**
	 * 解释Sql，用jsonObject的map来解释sql中的特殊字符
	 * @param sql
	 * @param jsonObject
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	private  String explainSql(String sql, JSONObject jsonObject) {
		// 拼装sql
		for (Object obj : jsonObject.keySet()) {
			String key = obj.toString();
			String val = jsonObject.getString(key);
			sql = sql.replace(key, val);
		}
		return sql;
	}
}
