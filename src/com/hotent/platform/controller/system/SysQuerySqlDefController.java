package com.hotent.platform.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.db.datasource.JdbcTemplateUtil;
import com.hotent.core.util.AppUtil;
import com.hotent.core.util.BeanUtils;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.platform.model.system.GlobalType;
import com.hotent.platform.model.system.SysDataSource;
import com.hotent.platform.model.system.SysQueryMetaField;
import com.hotent.platform.model.system.SysQuerySqlDef;
import com.hotent.platform.service.system.GlobalTypeService;
import com.hotent.platform.service.system.SysDataSourceService;
import com.hotent.platform.service.system.SysQueryMetaFieldService;
import com.hotent.platform.service.system.SysQuerySqlDefService;
/**
 *<pre>
 * 对象功能:自定义SQL定义 控制器类
 * 开发公司:宏天软件
 * 开发人员:ray
 * 创建时间:2015-06-08 16:02:04
 *</pre>
 */
@Controller
@RequestMapping("/platform/system/sysQuerySqlDef/")
public class SysQuerySqlDefController extends BaseController
{
	@Resource
	private SysQuerySqlDefService sysQuerySqlDefService;
	
	@Resource
	private GlobalTypeService globalTypeService;
	
	@Resource
	private SysQueryMetaFieldService sysQueryMetaFieldService;
	
	
	/**
	 * 添加或更新自定义SQL定义。
	 * @param request
	 * @param response
	 * @param sysQuerySqlDef 添加或更新的实体
	 * @param bindResult
	 * @param viewName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新自定义SQL定义")
	public void save(HttpServletRequest request, HttpServletResponse response ) throws Exception
	{
		String resultMsg=null;		
		try{
			String jsonStr = RequestUtil.getString(request, "jsonStr", "", false);
			SysQuerySqlDef sysQuerySqlDef = sysQuerySqlDefService.getSysQuerySqlDef(jsonStr);

			String fieldJson = RequestUtil.getString(request, "fieldJson",  "", false);
			
			String alias=sysQuerySqlDef.getAlias();
			
			if(sysQuerySqlDef.getId()==null||sysQuerySqlDef.getId()==0){
				boolean isExist=sysQuerySqlDefService.isAliasExists(alias,0L);
				if(isExist){
					resultMsg="指定别名已经存在!";
					writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
					return;
				}
				//切换数据源
				DbContextHolder.setDataSource(sysQuerySqlDef.getDsname());
				
				List<SysQueryMetaField> fieldList=sysQuerySqlDefService.obtainFieldListBySql(sysQuerySqlDef.getSql());
				
				DbContextHolder.setDefaultDataSource();
				//切换数据源
				sysQuerySqlDef.setMetaFields(fieldList);
					
				sysQuerySqlDefService.save(sysQuerySqlDef);
				resultMsg="添加自定义SQL定义成功!";
			}else{
				boolean isExist=sysQuerySqlDefService.isAliasExists(alias,sysQuerySqlDef.getId());
				if(isExist){
					resultMsg="指定别名已经存在!";
					writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Fail);
					return;
				}
				
				List<SysQueryMetaField> fieldList = sysQuerySqlDefService.getSysQueryMetaFieldArr(fieldJson);
				sysQuerySqlDef.setMetaFields(fieldList);
				sysQuerySqlDefService.save(sysQuerySqlDef);
			    
				resultMsg="更新自定义SQL定义成功!";
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			e.printStackTrace();
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	
	/**
	 * 取得自定义SQL定义分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看自定义SQL定义分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<SysQuerySqlDef> list=sysQuerySqlDefService.getAll(new QueryFilter(request,"sysQuerySqlDefItem"));
		ModelAndView mv=this.getAutoView().addObject("sysQuerySqlDefList",list);
		return mv;
	}
	
	/**
	 * 删除自定义SQL定义
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除自定义SQL定义")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[] lAryId =RequestUtil.getLongAryByStr(request, "id");
			sysQuerySqlDefService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除自定义SQL定义成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑自定义SQL定义
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑自定义SQL定义")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id",0L);
		SysQuerySqlDef sysQuerySqlDef=sysQuerySqlDefService.getById(id);
		if(sysQuerySqlDef==null){
			sysQuerySqlDef=new SysQuerySqlDef();
		}
		List<SysDataSource> sysDataSources = AppUtil.getBean(SysDataSourceService.class).getAll();
		List<SysQueryMetaField> sysQueryFields = new ArrayList<SysQueryMetaField>();
		List<GlobalType> globalTypeCATFORMList = globalTypeService.getByCatKey(GlobalType.CAT_FORM, true);
		List<GlobalType> globalTypesDICList = globalTypeService.getByCatKey(GlobalType.CAT_DIC, true);
		if (id != 0L) {
			sysQueryFields = sysQueryMetaFieldService.getListBySqlId(id);
		}
		if(BeanUtils.isEmpty(sysDataSources)||sysDataSources.size()==0){
			sysDataSources = new ArrayList<SysDataSource>();
		}
		SysDataSource sds = new SysDataSource();
		sds.setAlias(BpmConst.LOCAL_DATASOURCE);
		sds.setName("本地数据源");
		sysDataSources.add(sds);
		
//		com.alibaba.fastjson.JSONObject.toJSON(javaObject)
		
		com.alibaba.fastjson.JSONObject jsonObj= (com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(sysQuerySqlDef);
		
		return getAutoView().addObject("sysQuerySql",jsonObj)
				.addObject("sysQueryFields", JSONArray.fromObject(sysQueryFields))
				.addObject("dsList", JSONArray.fromObject(sysDataSources))
				.addObject("globalTypesDICList", JSONArray.fromObject(globalTypesDICList))
				.addObject("globalTypeCATFORMList", JSONArray.fromObject(globalTypeCATFORMList));
	}

	/**
	 * 取得自定义SQL定义明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看自定义SQL定义明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		SysQuerySqlDef sysQuerySqlDef = sysQuerySqlDefService.getById(id);	
		return getAutoView().addObject("sysQuerySqlDef", sysQuerySqlDef);
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
			sql=explainSql(sql, map);
			Boolean b = JdbcTemplateUtil.executeSql(sql, rollback);
			return b;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 解释Sql，用jsonObject的map来解释sql中的特殊字符
	 * @param sql
	 * @param jsonObject
	 * @return  String
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

