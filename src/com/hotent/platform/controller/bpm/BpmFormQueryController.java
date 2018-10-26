package com.hotent.platform.controller.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.annotion.ActionExecOrder;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.AppUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.formQueryDefinition.service.com.FqrelationService;
import com.hotent.platform.model.bpm.BpmFormQuery;
import com.hotent.platform.model.form.QueryResult;
import com.hotent.platform.model.system.SysAuditModelType;
//import com.hotent.platform.model.system.SysDataSourceL;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.service.bpm.BpmFormQueryService;
//import com.hotent.platform.service.system.SysDataSourceLService;
import com.hotent.platform.service.system.SysDataSourceService;

/**
 * 对象功能:通用表单查询 控制器类 开发公司:广州宏天软件有限公司 开发人员:ray 创建时间:2012-11-27 10:37:13
 */
@Controller
@RequestMapping("/platform/bpm/bpmFormQuery/")
@Action(ownermodel = SysAuditModelType.FORM_MANAGEMENT)
public class BpmFormQueryController extends BaseController {
	@Resource
	private BpmFormQueryService bpmFormQueryService;
	@Resource
	private FqrelationService fqRelationService;
	/**
	 * 取得通用表单查询分页列表
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description = "查看通用表单查询分页列表")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BpmFormQuery> list = bpmFormQueryService.getAll(new QueryFilter(request, "bpmFormQueryItem"));
		ModelAndView mv = this.getAutoView().addObject("bpmFormQueryList", list);

		return mv;
	}

	/**
	 * 通用表单查询对话框
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	@Action(description = "通用表单查询对话框")
	public ModelAndView dialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BpmFormQuery> list = bpmFormQueryService.getAll(new QueryFilter(request, "bpmFormQueryItem"));
		ModelAndView mv = this.getAutoView().addObject("bpmFormQueryList", list);

		return mv;
	}

	/**
	 * 删除通用表单查询
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除原子操作-更新")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			Long fqId = RequestUtil.getLong(request, "id");
			fqRelationService.delByfqId(fqId);
			//fqRelationService.delByIds(lAryId);
			bpmFormQueryService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除原子操作-更新成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}

	@RequestMapping("queryObj")
	@Action(description = "查看通用表单查询明细")
	@ResponseBody
	public Map<String, Object> queryObj(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String alias = RequestUtil.getString(request, "alias");
		Map<String, Object> map = new HashMap<String, Object>();
		BpmFormQuery bpmFormQuery = bpmFormQueryService.getByAlias(alias);
		if (bpmFormQuery != null) {
			map.put("bpmFormQuery", bpmFormQuery);
			map.put("success", 1);
		} else {
			map.put("success", 0);
		}
		return map;
	}

	/**
	 * 显示查询条件构建界面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String alias = RequestUtil.getString(request, "query_alias_");

		BpmFormQuery bpmFormQuery = bpmFormQueryService.getByAlias(alias);
		ModelAndView mv = this.getAutoView();
		mv.addObject("bpmFormQuery", bpmFormQuery);
		return mv;
	}

	/**
	 * 进行查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("doQuery")
	@Action(description = "进行查询")
	@ResponseBody
	public QueryResult doQuery(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryResult result = new QueryResult();
		String alias = RequestUtil.getString(request, "alias");
		String queryData = RequestUtil.getString(request, "querydata");
		Integer page = RequestUtil.getInt(request, "page", 0);
		Integer pageSize = RequestUtil.getInt(request, "pagesize", 0);

		BpmFormQuery bpmFormQuery = bpmFormQueryService.getByAlias(alias);
		//TODO 为什么不能在bpmFormQueryService换？因为service有事务保护着，只用一个链接
//		DbContextHolder.setDataSource(bpmFormQuery.getDsalias());
		
		if (bpmFormQuery != null) {
			result = bpmFormQueryService.getData(bpmFormQuery, queryData, page, pageSize);
		} else
			result.setErrors("查询别名不正确，未能获取到该别名的查询对象。");
		
		//TODO 这样不太妥，应该在清空数据源之前调用的service中增加一句- -
		DbContextHolder.clearDataSource();
		DbContextHolder.setDefaultDataSource();
		return result;
	}

	/**
	 * 编辑通用表单查询
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description = "编辑通用表单查询")
	public ModelAndView edit(HttpServletRequest request) throws Exception {
		Long id = RequestUtil.getLong(request, "id");
		Map<String, Object> params = RequestUtil.getQueryMap(request);
		Long canReturn = RequestUtil.getLong(request, "canReturn", 0);
		String returnUrl = RequestUtil.getPrePage(request);

		BpmFormQuery bpmFormQuery = null;
		if (id != 0) {
			bpmFormQuery = bpmFormQueryService.getById(id);
		} else {
			bpmFormQuery = new BpmFormQuery();
		}
//		List<SysDataSource> dsList = sysDataSourceService.getAll();
	//	List<SysDataSourceL> dsList =AppUtil.getBean(SysDataSourceLService.class).getAll();
		List<SysDataSource> dsList =AppUtil.getBean(SysDataSourceService.class).getAll();
		return getAutoView().addObject("bpmFormQuery", bpmFormQuery).addObject("returnUrl", returnUrl).addObject("dsList", dsList).addObject("canReturn", canReturn);
	}

	/**
	 * 设置字段
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		String dsName = "";
		String objectName = "";
		int istable = 0;
		ModelAndView mv = this.getAutoView();
		if (id == 0) {
			dsName = RequestUtil.getString(request, "dsName");
			istable = RequestUtil.getInt(request, "istable");
			objectName = RequestUtil.getString(request, "objectName");
		} else {
			BpmFormQuery bpmFormQuery = bpmFormQueryService.getById(id);
			istable = bpmFormQuery.getIsTable();
			dsName = bpmFormQuery.getDsalias();
			objectName = bpmFormQuery.getObjName();
			mv.addObject("bpmFormQuery", bpmFormQuery);
		}

		TableModel tableModel=null;
		// 表
		if (istable == 1) {
			BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
			tableModel = meta.getTableByName(objectName);
		}
		// 视图处理
		else {
			IDbView dbView = TableMetaFactory.getDbView(dsName);
			tableModel = dbView.getModelByViewName(objectName);
		}

		mv.addObject("tableModel", tableModel);

		return mv;
	}

	/**
	 * 取得所有的自定义查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAllQueries")
	@ResponseBody
	public List<BpmFormQuery> getAllDialogs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BpmFormQuery> list = bpmFormQueryService.getAll();
		return list;
	}
	
	/**
	 * 自定义查询的参数填值对话框
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("btnDialog")
	public ModelAndView btnDialog(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String alias = RequestUtil.getString(request, "alias");

		BpmFormQuery bpmFormQuery = bpmFormQueryService.getByAlias(alias);
		ModelAndView mv = this.getAutoView();
		mv.addObject("bpmFormQuery", bpmFormQuery);
		return mv;
	}
}
