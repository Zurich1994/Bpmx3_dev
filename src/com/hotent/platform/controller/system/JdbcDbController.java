package com.hotent.platform.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.displaytag.util.ParamEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.util.RequestUtil;
/**
 * 对象功能:JDBC 数据 控制器类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:Raise
 * 创建时间:2012-12-06
 */
@Controller
@RequestMapping("/platform/system/jdbcDb/")
public class JdbcDbController extends BaseController
{	
	@RequestMapping("getTables")
	@ResponseBody
	@Action(description="获取数据库的表和视图")
	public Map<String,Object> getTables(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String, Object>();
		String dsName=RequestUtil.getString(request, "dsName");
		String tableName=RequestUtil.getString(request, "tableName");
		String viewName=RequestUtil.getString(request, "viewName");
		BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
		//get table models
		List<TableModel> tables = meta.getTablesByName(tableName, null);
		IDbView dbView = TableMetaFactory.getDbView(dsName);
		//get views models
		List<TableModel> views = dbView.getViewsByName(viewName,null);
		map.put("tables",tables );
		map.put("views", views );
		return map;
	}
}
