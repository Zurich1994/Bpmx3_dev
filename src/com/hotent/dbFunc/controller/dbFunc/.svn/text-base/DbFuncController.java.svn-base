
package com.hotent.dbFunc.controller.dbFunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.*;
import org.activiti.engine.impl.util.json.JSONArray;
import org.activiti.engine.impl.util.json.JSONObject;
import org.activiti.engine.impl.util.json.JSONStringer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.core.annotion.Action;
import com.hotent.core.db.datasource.DbContextHolder;
import com.hotent.core.table.BaseTableMeta;
import com.hotent.core.table.IDbView;
import com.hotent.core.table.TableModel;
import com.hotent.core.table.impl.TableMetaFactory;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.dbResult.model.dbResult.DbResult;
import com.hotent.platform.model.bpm.BpmFormQuery;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;


/**
 * 对象功能:数据库方法参数表 控制器类
 */
@Controller
@RequestMapping("/dbFunc/dbFunc/dbFunc/")
public class DbFuncController extends BaseController
{
	@Resource
	private DbFuncService dbFuncService;

	@Resource
	private TableParcelService tableParcelService;
	
	@Resource
	private BpmFormTableService service;

	
	private String tab="";
	
	protected Logger logger = LoggerFactory.getLogger(DbFuncController.class);
	
	/**
	 * 选择自定义对话框
	 * 
	 * @param request
	 * @param response
	 * @author 王百合
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getAllDialogs")
	@ResponseBody
	public List<DbFunc> getAllDialogs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("getAllDialogs");
		List<DbFunc> list = dbFuncService.getAll();
		return list;
	}
	
	
	/**
	 * 添加或更新数据库方法参数表。
	 * @param request
	 * @author 王百合
	 * @param dbFunc 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新数据库方法参数表")
	public void save(HttpServletRequest request, HttpServletResponse response,DbFunc dbFunc) throws Exception
	{
		System.out.println(dbFunc.getTable_name());
		String resultMsg=null;		
		try{
			if(dbFunc.getId()==0){
				Long id=UniqueIdUtil.genId();
				dbFunc.setId(id);
				
				dbFuncService.add(dbFunc);
				resultMsg=getText("添加","数据库方法参数表");
			}else{
			    dbFuncService.update(dbFunc);
				resultMsg=getText("更新","数据库方法参数表");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得数据库方法参数表分页列表  传tablename版
	 * @param request
	 * @author 王百合
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看数据库方法参数表分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
//	    (tab);
		List<DbFunc> list=dbFuncService.getAll(new QueryFilter(request,"dbFuncItem"));
		
		String tablename=RequestUtil.getString(request, "Q_table_name_S");
		
		ModelAndView mv=this.getAutoView().addObject("dbFuncList",list)
										  .addObject("tablename",tablename);
											
		
		return mv;
	}
	
	
	

	/**
	 * 删除数据库方法参数表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除数据库方法参数表")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			dbFuncService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除数据库方法参数表成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑数据库方法参数表
	 * @author 王百合
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑数据库方法参数表")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		String tablename = "";
		String funcname = "";
		Long id=RequestUtil.getLong(request,"id");
		tablename=RequestUtil.getString(request, "tablename");
		funcname=RequestUtil.getString(request, "funcname");
		
		String returnUrl=RequestUtil.getPrePage(request);
		
		//DbFunc dbFunc=dbFuncService.getById(id);
		DbFunc dbFunc = new DbFunc();
		if(dbFuncService.getById(id)!=null)
			dbFunc=dbFuncService.getById(id);
		else if((dbFuncService.getById(id)==null)&&(funcname!= ""))
		{
			dbFunc=dbFuncService.getByTableNameandFuncName(tablename,funcname);
		}
		else {
			if(!tab.equals(""))
	    	dbFunc.setTable_name(tab);
		}
		
		return getAutoView().addObject("dbFunc",dbFunc)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得数据库方法参数表明细 
	 * @param request   
	 * @param response
	 * @return
	 * @author 王百合
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看数据库方法参数表明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		DbFunc dbFunc=dbFuncService.getById(id);
		return getAutoView().addObject("dbFunc", dbFunc);
	}

	
	
	/**
	 * 根据表名获得所有的列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getByTableName")
	@Action(description="根据表名获得所有的列表")
	public ModelAndView getByTableName(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String tableName = request.getParameter("tableName");
		System.out.println("TableName:"+tableName);
		List<DbFunc> list=dbFuncService.getByTableName(tableName);
		//if(list.size()==0)
			tab=tableName;
		ModelAndView mv=this.getAutoView().addObject("dbFuncList",list);
		return mv;
	}
	
	

	/**
	 * 根据表名获得所有的列表
	 * @param request
	 * @author 王百合
	 * @param page
	 * @return
	 * @throws Exception
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("getByDsObjectName")
	@Action(description = "根据对象名称对象类型")
	@ResponseBody
	public Map getByDsObjectName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dsName = RequestUtil.getString(request, "dsName");
		
		String objectName = RequestUtil.getString(request, "objectName");
	
		int istable = RequestUtil.getInt(request, "istable");
		
		Map map = new HashMap();
		try {
			if (istable == 1) {
				BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
				Map<String, String> tableMap = meta.getTablesByName(objectName);
				map.put("tables", tableMap);
			} else {
				IDbView dbView = TableMetaFactory.getDbView(dsName);
				List<String> views = dbView.getViews(objectName);
				map.put("views", views);
			}
			DbContextHolder.clearDataSource();
			map.put("success", "true");
		} catch (Exception ex) {
			logger.info("getByDsObjectName:" + ex.getMessage());
			map.put("success", "false");
		}
		return map;
	}
	
	/**
	 * 取得表或者视图的元数据对象
	 * @param request
	 * @author 王百合
	 * @param page
	 * @return
	 * @throws Exception
	 */
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("getObjectByDsObjectName")
	@Action(description = "取得表或者视图的元数据对象")
	@ResponseBody
	public Map getObjectByDsObjectName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dsName = RequestUtil.getString(request, "dsName");
		
		String objectName = RequestUtil.getString(request, "objectName");
		
		int istable = RequestUtil.getInt(request, "istable");
		
		Map map = new HashMap();
		TableModel tableModel;
		try {
			// 加载表
			if (istable == 1) {
				BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
				tableModel = meta.getTableByName(objectName);
			} else {
				IDbView dbView = TableMetaFactory.getDbView(dsName);
				tableModel = dbView.getModelByViewName(objectName);
			}
			map.put("tableModel", tableModel);
			map.put("success", "true");
		} catch (Exception ex) {
			map.put("success", "false");
		}
		return map;
	}

	/**
	 * 设置字段
	 * 
	 * @param request
	 * @param response
	 * @author 王百合
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setting")
	public ModelAndView setting(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = RequestUtil.getLong(request, "id");
		

		long func_type_long = 0;
		String dsName = "";
		String objectName = " ";
		long istable = 0;
		int style = 0;
		ModelAndView mv = this.getAutoView();
		objectName = RequestUtil.getString(request, "objectName");
		
		String tablename=objectName.substring(2);
		

		if (id == 0) {
			dsName = RequestUtil.getString(request, "dsName");
			istable = RequestUtil.getInt(request, "istable");
			objectName = RequestUtil.getString(request, "objectName");
			
			
			func_type_long =  RequestUtil.getLong(request, "func_type");
			
		} else {
			DbFunc dbFunc = dbFuncService.getById(id);
			dsName = dbFunc.getDsName();
			istable = dbFunc.getIsTable();
			objectName = dbFunc.getObjname();
			func_type_long = dbFunc.getFunc_type();
			mv.addObject("dbFunc", dbFunc);
		}
		
		TableModel tableModel;
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
	
		
		List<TableParcel> ls = new ArrayList<TableParcel>();
		
		
		
		ls=tableParcelService.getParcelsbyTableName(tablename);
		
		
		if(ls.size()!=0){
			for(int i=0;i<ls.size();i++)
			{		
				
				TableParcel tableparcel = ls.get(i);
				String fromTablename=ls.get(i).getTable_name();
				tableparcel.setParcel_name(ls.get(i).getParcel_name()+"("+fromTablename+")");
				tableparcel.setParcel_alias(ls.get(i).getParcel_alias()+"(Data)");
				
		
			}
		}
		
		mv.addObject("tableModel", tableModel).addObject("func_type_long", func_type_long).addObject("style", style).addObject("tableparcel",ls);

		return mv;
	}
	/**
	 * 展示详细
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detailwb")
	public ModelAndView detailwb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = this.getAutoView();
		String table_name= "";
		String func_name= "";
		String dsName = "";
		String objectName = "";
		Long istable = 0L;
		long func_type_long = 0;
		table_name=RequestUtil.getString(request, "tablename");
		System.out.println("tablename="+table_name);
		func_name=RequestUtil.getString(request, "funcname"); 
		System.out.println("funcname="+func_name);
		DbFunc dbFunc=dbFuncService.getByTableNameandFuncName(table_name,func_name);
		long id = dbFunc.getId();
		
		istable = dbFunc.getIsTable();
		dsName = dbFunc.getDsName();
		objectName = dbFunc.getObjname();
		func_type_long=dbFunc.getFunc_type();
		
		List<DialogField> conditionList=dbFunc.getConditionList();
		List<DialogField> resultList=dbFunc.getReturnList();
		List<DialogField> setList=dbFunc.getSettingList();
		List<DialogField> sortList=dbFunc.getSortList();
		
		//System.out.println("conditionList:"+conditionList);
		net.sf.json.JSONArray conditionArray=net.sf.json.JSONArray.fromObject(conditionList);
		net.sf.json.JSONArray resultArray=net.sf.json.JSONArray.fromObject(resultList);
		net.sf.json.JSONArray setArray=net.sf.json.JSONArray.fromObject(setList);
		net.sf.json.JSONArray sortArray=net.sf.json.JSONArray.fromObject(sortList);
		
		JSONObject fields=new JSONObject();
		fields.accumulate("conditionField", conditionArray.toString());
		fields.accumulate("resultField", resultArray.toString());
		fields.accumulate("setField", setArray.toString());
		fields.accumulate("sortField", sortArray.toString());
		
		
		TableModel tableModel=null;
		if (istable == 1) {
			BaseTableMeta meta = TableMetaFactory.getMetaData(dsName);
			tableModel = meta.getTableByName(objectName);
		}
		// 视图处理
		else {
			IDbView dbView = TableMetaFactory.getDbView(dsName);
			tableModel = dbView.getModelByViewName(objectName);
		}
		
		System.out.println(fields.toString());
		mv.addObject("dbFunc", dbFunc);
		//mv.addObject("fields",fields);
		mv.addObject("tableModel", tableModel);
		mv.addObject("func_type_long",func_type_long);
		mv.addObject("conditionList",conditionList);
		System.out.println(conditionArray);
		return mv;
		/*
		long myid = RequestUtil.getLong(request, "id");
	//	long id = RequestUtil.getLong(request, "id");
		long id = Long.parseLong("10000007900000");
		(myid);
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
			(bpmFormQuery.toString());
			istable = bpmFormQuery.getIsTable();
			dsName = bpmFormQuery.getDsalias();
			objectName = bpmFormQuery.getObjName();
			mv.addObject("bpmFormQuery", bpmFormQuery);
			(bpmFormQuery.toString());
			
			//wb
			List<DialogField> conditionlist=bpmFormQuery.getConditionList();
			String resultField=bpmFormQuery.getReturnFields();
			String setField=bpmFormQuery.getConditionFields();
			String sortField=bpmFormQuery.getConditionfield();
			
		//	(conditionField);
			
			JSONArray condArray=JSONArray.fromObject(conditionlist);
			JSONObject fields=new JSONObject();
			fields.accumulate("conditionField", condArray.toString());
			fields.accumulate("resultField", condArray.toString());
			fields.accumulate("setField", condArray.toString());
			fields.accumulate("sortField", condArray.toString());
			//JSONArray fields=new JSONArray();
			mv.addObject("fields",fields);
//			mv.addObject("conditionField", condArray.toString());
//			(condArray.toString());
//			mv.addObject("resultField", condArray.toString());
//			mv.addObject("setField", condArray.toString());
//			mv.addObject("sortField", condArray.toString());
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
		*/
	}

	
/**
	 * 根据表名查找方法名。
	 * @param request
	 * @param response
	 * @author 王钊
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchformethod")
	@ResponseBody
	public String searchformethod(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("search method..");
        List<DbFunc> li = dbFuncService.getAll();
        /*for(DbFunc db:list)
        {
        	("数据库表名:"+db.getTable_name());
        	("数据库方法类型:"+db.getFunc_type());
        	("数据库方法名:"+db.getFunc_name());
        	if(db.getTable_name().equals(request.getParameter("name")))
        		("竟然相等了不输出!!!!");
        }*/
       /* for(DbFunc db:li)
        {
        	System.out.println("数据库表名:"+db.getTable_name());

        }*/
		long type =0;
		if(request.getParameter("type").equals("add"))
		{
			type=1;
		}
		else {  
			if(request.getParameter("type").equals("delete"))
			{
				type=2;
			}
			else {
				if(request.getParameter("type").equals("update"))
				{
					type=3;
				}
				else {
					if(request.getParameter("type").equals("select"))
					{
						type=4;
					}
				}
			}
		}
        JSONObject jo = new JSONObject();
            jo.put("name",request.getParameter("name"));
            JSONArray json2 = new JSONArray();
            int sum=1;
            for(int i=0;i<li.size();i++)
            {
            	if(li.get(i).getTable_name().equals(request.getParameter("name"))&&type==li.get(i).getFunc_type())
            	{
            		System.out.println(sum);
                	sum++;
            		JSONObject jo2 = new JSONObject();
            		jo2.put("id",li.get(i).getFunc_type());
            		jo2.put("name", li.get(i).getFunc_name());
            		json2.put(jo2);
            	}
            }
            jo.put("children", json2);
		return jo.toString();
	}
	
	/**
	 * 根据方法名查找数据包。
	 * @param request
	 * @param response
	 * @author 刘铭鑫
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchforfield1")
	@ResponseBody
	public String searchforfield1(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		List<DbFunc> list = dbFuncService.getAll();	
		
		/*System.out.println("请求表名:"+request.getParameter("table_name"));
		System.out.println("请求方法名:"+request.getParameter("func_name"));
		System.out.println("请求类型:"+request.getParameter("type"));*/
    	String type = request.getParameter("type");
		JSONStringer jo1= new JSONStringer();	
		 /*jo1.put("success",request.getParameter("true"));
		 jo1.put("msg",request.getParameter("获取成功"));
		 
		 jo1.put("wsdl",request.getParameter("func_name"));
		 */
		jo1.object();
		jo1.key("success");
		jo1.value(true);
		jo1.key("msg");
		jo1.value("获取成功.");
		jo1.key("wsdl");
		jo1.value(request.getParameter("func_name"));
		
		//("方法名返回now1:"+jo1.toString());
			for(DbFunc db:list)
			{
			 if(db.getTable_name().equals(request.getParameter("table_name"))&&db.getFunc_name().equals(request.getParameter("func_name")) )
			 {
				// ("方法名返回now2:"+jo1.toString());
				 jo1.key("namespace");
				 jo1.value(db.getTable_name());
				 jo1.key("invokeUrl");
				 jo1.value("别名");
				 jo1.key("method");
				 jo1.value(db.getFunc_name());
				 jo1.key("soapaction");
				 jo1.value("database");
				 jo1.key("serviceName");
				 jo1.value(db.getTable_name());
				
			    jo1.key("conditionField");
			    jo1.object();
			    jo1.key("name");
			    jo1.value("条件数据包");
			    jo1.key("type");
                jo1.value("数据包");
                jo1.key("nocheck");
                jo1.value(false);
			    jo1.key("children");
			    jo1.array();
                if(db.getConditionField()!=null)
                {
                  JSONArray ja = new JSONArray(db.getConditionField());
                
                 for(int i=0;i<ja.length();i++)
                 {
            	   JSONObject joj = ja.getJSONObject(i);
            	   String ss = joj.getString("field");
            	    String comment = joj.getString("comment");
           	        int length=comment.length();
           	        if(length!=0)
           	        { String comment2=comment.substring(length-1, length);
            	  if(")".equals(comment2))
            	  {
            		  jo1.object();
                      jo1.key("name");
                      jo1.value(ss);
                      jo1.key("type");
                      jo1.value("varChar");
                      jo1.key("nocheck");
                      jo1.value(false);
                      jo1.endObject();   
            		  
            	  }
           	        }
           	        else{
           	        	
           	        }
                  }
               
                 jo1.endArray();
				 jo1.endObject();
               //  ("方法名返回now4:"+jo1.toString());
                }
                else {
                
                	jo1.object();
                    jo1.key("name");
                    jo1.value("");
                    jo1.key("type");
                    jo1.value("varChar");
                    jo1.key("nocheck");
                    jo1.value(false);
                    jo1.endObject();
                    jo1.endArray();
   				    jo1.endObject();
                  
                //  ("方法名返回now4:"+jo1.toString());
				}
             
                jo1.key("setField");
			    jo1.object();
			    jo1.key("name");
			    jo1.value("设置数据包");
			    jo1.key("type");
                jo1.value("数据包");
                jo1.key("nocheck");
                jo1.value(false);
			    jo1.key("children");
			    jo1.array();
                if(db.getSettingField()!=null)
                {
                  
                  JSONArray ja2 = new JSONArray(db.getSettingField());
                  //System.out.println("数组为："+ja2);
                
                  for(int i=0;i<ja2.length();i++)
                  {
           	        JSONObject joj2 = ja2.getJSONObject(i);
           	        String ss2 = joj2.getString("field");
           	        String comment = joj2.getString("comment");
           	        int length=comment.length();
           	        if(length!=0)
           	        {String comment2=comment.substring(length-1, length);
            	  if(")".equals(comment2))
            	  {
            		  jo1.object();
                      jo1.key("name");
                      jo1.value(ss2);
                      jo1.key("type");
                      jo1.value("varChar");
                      jo1.key("nocheck");
                      jo1.value(false);
                      jo1.endObject(); 
            		  
            		  
            	  }else{
            		  
                	  }
           	        }
                 }
                
                 jo1.endArray();
				 jo1.endObject();
             
                }
                else {
                	jo1.object();
                    jo1.key("name");
                    jo1.value("");
                    jo1.key("type");
                    jo1.value("varChar");
                    jo1.key("nocheck");
                    jo1.value(false);
                    jo1.endObject();
                    jo1.endArray();
   				    jo1.endObject();
                 //  ("方法名返回now5:"+jo1.toString());
				}
                if(type.equals("select"))
                {
                	jo1.key("resultField");
    			    jo1.object();
    			    jo1.key("name");
    			    jo1.value("结果数据包");
    			    jo1.key("type");
                    jo1.value("数据包");
                    jo1.key("nocheck");
                    jo1.value(false);
    			    jo1.key("children");
    			    jo1.array();
                 
                    if(db.getConditionField()!=null)
                    {
                      JSONArray ja = new JSONArray(db.getResultField());
                    
                     for(int i=0;i<ja.length();i++)
                     {
                	   JSONObject joj = ja.getJSONObject(i);
                	   String ss = joj.getString("field");
                	    String comment = joj.getString("comment");
               	        int length=comment.length();
               	        if(length!=0)
               	        { String comment2=comment.substring(length-1, length);
                	  if(")".equals(comment2))
                	  {
                		  jo1.object();
                          jo1.key("name");
                          jo1.value(ss);
                          jo1.key("type");
                          jo1.value("varChar");
                          jo1.key("nocheck");
                          jo1.value(false);
                          jo1.endObject();  
                		 
                	  
                	  }else{
                	  }
               	        }
                      }
                    
                     jo1.endArray();
    				 jo1.endObject();
               
                    }
                    else {
                    
                    	jo1.object();
                        jo1.key("name");
                        jo1.value("");
                        jo1.key("type");
                        jo1.value("varChar");
                        jo1.key("nocheck");
                        jo1.value(false);
                        jo1.endObject();
                        jo1.endArray();
       				    jo1.endObject();
                      
                  
    				}
                }
                
                jo1.endObject();
                String strString = jo1.toString().replaceAll("\\\\","");
                return strString;
			   }
			  }

		   return null;
	}
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
		JSONStringer jo1= new JSONStringer();	
		 /*jo1.put("success",request.getParameter("true"));
		 jo1.put("msg",request.getParameter("获取成功"));
		 jo1.put("wsdl",request.getParameter("func_name"));
		 */
		jo1.object();
		jo1.key("success");
		jo1.value(true);
		jo1.key("msg");
		jo1.value("获取成功.");
		jo1.key("wsdl");
		jo1.value(request.getParameter("func_name"));
		
		//("方法名返回now1:"+jo1.toString());
			for(DbFunc db:list)
			{
			 if(db.getTable_name().equals(request.getParameter("table_name"))&&db.getFunc_name().equals(request.getParameter("func_name")) )
			 {
				// ("方法名返回now2:"+jo1.toString());
				 jo1.key("namespace");
				 jo1.value(db.getTable_name());
				 jo1.key("invokeUrl");
				 jo1.value("别名");
				 jo1.key("method");
				 jo1.value(db.getFunc_name());
				 jo1.key("soapaction");
				 jo1.value("database");
				 jo1.key("serviceName");
				 jo1.value(db.getTable_name());
				
			    jo1.key("conditionField");
			    jo1.object();
			    jo1.key("name");
			    jo1.value("条件字段");
			    jo1.key("type");
                jo1.value("字段集");
                jo1.key("nocheck");
                jo1.value(false);
			    jo1.key("children");
			    jo1.array();
              
                if(db.getConditionField()!=null)
                {
                  JSONArray ja = new JSONArray(db.getConditionField());
                 for(int i=0;i<ja.length();i++)
                 {
            	   JSONObject joj = ja.getJSONObject(i);
            	   String ss = joj.getString("field");
            	    String comment = joj.getString("comment");
           	        int length=comment.length();
           	        if(length!=0)
           	        {String comment2=comment.substring(length-1, length);
            	  if(")".equals(comment2))
            	  {
            		  //ja.remove(i);
            	  }else{
                   jo1.object();
                   jo1.key("name");
                   jo1.value(ss);
                   jo1.key("type");
                   jo1.value("varChar");
                   jo1.key("nocheck");
                   jo1.value(false);
                   jo1.endObject();   
            	  }
           	        }
           	        else{
           	        
                      jo1.object();
                      jo1.key("name");
                      jo1.value(ss);
                      jo1.key("type");
                      jo1.value("varChar");
                      jo1.key("nocheck");
                      jo1.value(false);
                      jo1.endObject();   
               	  
           	        }
                 }
               
                 jo1.endArray();
				 jo1.endObject();
               //  ("方法名返回now4:"+jo1.toString());
                }
                else {
                
                	jo1.object();
                    jo1.key("name");
                    jo1.value("");
                    jo1.key("type");
                    jo1.value("varChar");
                    jo1.key("nocheck");
                    jo1.value(false);
                    jo1.endObject();
                    jo1.endArray();
   				    jo1.endObject();
                  
                //  ("方法名返回now4:"+jo1.toString());
				}
             
                jo1.key("setField");
			    jo1.object();
			    jo1.key("name");
			    jo1.value("设置字段");
			    jo1.key("type");
                jo1.value("字段集");
                jo1.key("nocheck");
                jo1.value(false);
			    jo1.key("children");
			    jo1.array();
                if(db.getSettingField()!=null)
                {
                  
                  JSONArray ja2 = new JSONArray(db.getSettingField());
                  //System.out.println("数组为："+ja2);
                
                  for(int i=0;i<ja2.length();i++)
                  {
           	        JSONObject joj2 = ja2.getJSONObject(i);
           	        String ss2 = joj2.getString("field");
           	        String comment = joj2.getString("comment");
           	        int length=comment.length();
           	        if(length!=0)
           	        {String comment2=comment.substring(length-1, length);
            	  if(")".equals(comment2))
            	  {
            		 // ja2.remove(i);
            	  }else{
                	 jo1.object();
                     jo1.key("name");
                     jo1.value(ss2);
                     jo1.key("type");
                     jo1.value("varChar");
                     jo1.key("nocheck");
                     jo1.value(false);
                     jo1.endObject();  }
                 }
           	     else{
            	        
                     jo1.object();
                     jo1.key("name");
                     jo1.value(ss2);
                     jo1.key("type");
                     jo1.value("varChar");
                     jo1.key("nocheck");
                     jo1.value(false);
                     jo1.endObject();   
              	  
          	        }
                  }
                
                 jo1.endArray();
				 jo1.endObject();
             
                }
                else {
                 
                   
                	jo1.object();
                    jo1.key("name");
                    jo1.value("");
                    jo1.key("type");
                    jo1.value("varChar");
                    jo1.key("nocheck");
                    jo1.value(false);
                    jo1.endObject();
                    jo1.endArray();
   				    jo1.endObject();
                 //  ("方法名返回now5:"+jo1.toString());
				}
                if(type.equals("select"))
                {
                	jo1.key("resultField");
    			    jo1.object();
    			    jo1.key("name");
    			    jo1.value("结果字段");
    			    jo1.key("type");
                    jo1.value("字段集");
                    jo1.key("nocheck");
                    jo1.value(false);
    			    jo1.key("children");
    			    jo1.array();
                 
                    if(db.getConditionField()!=null)
                    {
                      JSONArray ja = new JSONArray(db.getResultField());
                    
                     for(int i=0;i<ja.length();i++)
                     {
                	   JSONObject joj = ja.getJSONObject(i);
                	   String ss = joj.getString("field");
                	    String comment = joj.getString("comment");
               	        int length=comment.length();
               	        if(length!=0)
               	        { String comment2=comment.substring(length-1, length);
                	  if(")".equals(comment2))
                	  {
                		  //ja.remove(i);
                	  
                	  }else{
                     
                       jo1.object();
                       jo1.key("name");
                       jo1.value(ss);
                       jo1.key("type");
                       jo1.value("varChar");
                       jo1.key("nocheck");
                       jo1.value(false);
                       jo1.endObject();  
                	  }
                      }
               	     else{
                	        
                         jo1.object();
                         jo1.key("name");
                         jo1.value(ss);
                         jo1.key("type");
                         jo1.value("varChar");
                         jo1.key("nocheck");
                         jo1.value(false);
                         jo1.endObject();   
                  	  
              	        }
                     }
                    
                     jo1.endArray();
    				 jo1.endObject();
               
                    }
                    else {
                    
                    	jo1.object();
                        jo1.key("name");
                        jo1.value("");
                        jo1.key("type");
                        jo1.value("varChar");
                        jo1.key("nocheck");
                        jo1.value(false);
                        jo1.endObject();
                        jo1.endArray();
       				    jo1.endObject();
                      
                  
    				}
                }
                
                jo1.endObject();
                String strString = jo1.toString().replaceAll("\\\\","");
                return strString;
			   }
			  }

		   return null;
	}
	
	/**
	 * 根据方法名查找参数字段。
	 * @param request
	 * @param response
	 * @author 王钊
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("searchforparam")
	
	@ResponseBody
	public ModelAndView searchforparam(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
			
		System.out.println("进来了！！！！");
		List<DbFunc> list = dbFuncService.getAll();
		System.out.println("请求表名:"+request.getParameter("table_name"));
		System.out.println("请求方法名:"+request.getParameter("func_name"));
		for(DbFunc db:list)
		{
			if(db.getTable_name().equals(request.getParameter("table_name"))&&db.getFunc_name().equals(request.getParameter("func_name")))
			{
				/*System.out.println("数据库表名:"+db.getTable_name());
				System.out.println("数据库方法名:"+db.getFunc_name());*/
			   JSONArray json1 = new JSONArray();
			   if(db.getConditionField()!=null)
			   {
				   String[] str1= db.getConditionField().split(",");
				   for(String ss:str1)
				   {
					   JSONObject jo1 = new JSONObject();
						jo1.put("comment","");
						jo1.put("condition","=");
						jo1.put("defaultType",1);
						jo1.put("defaultValue","");
						jo1.put("dialog","");
						jo1.put("fieldName",ss);
						jo1.put("fieldType",db.getParameterType());
						jo1.put("paraCt","");
						jo1.put("param","");
					    json1.put(jo1);
				   }
			   }
			   else {
				   JSONObject jo1 = new JSONObject();
					jo1.put("comment","");
					jo1.put("condition","=");
					jo1.put("defaultType",1);
					jo1.put("defaultValue","");
					jo1.put("dialog","");
					jo1.put("fieldName","");
					jo1.put("fieldType",db.getParameterType());
					jo1.put("paraCt","");
					jo1.put("param","");
				    json1.put(jo1);
			   }
			   JSONArray json2 = new JSONArray();
			   if(db.getResultField()!= null)
			   {
				   String[] str2= db.getResultField().split(",");
				   for(String ss:str2)
				   {
					   JSONObject jo2 = new JSONObject();
					   jo2.put("comment","");
					   jo2.put("condition","=");
					   jo2.put("defaultType",1);
					   jo2.put("defaultValue","");
					   jo2.put("dialog","");
					   jo2.put("fieldName",ss);
					   jo2.put("fieldType",db.getParameterType());
					   jo2.put("paraCt","");
					   jo2.put("param","");
					    json2.put(jo2);
				   }
			   }
			   else {
				   JSONObject jo2 = new JSONObject();
				   jo2.put("comment","");
				   jo2.put("condition","=");
				   jo2.put("defaultType",1);
				   jo2.put("defaultValue","");
				   jo2.put("dialog","");
				   jo2.put("fieldName","");
				   jo2.put("fieldType",db.getParameterType());
				   jo2.put("paraCt","");
				   jo2.put("param","");
				    json2.put(jo2);
			   }
			   JSONArray json3 = new JSONArray();
			   if(db.getSettingField()!= null)
			   {
				   String[] str3= db.getSettingField().split(",");
				   for(String ss:str3)
				   {
					   JSONObject jo3 = new JSONObject();
					   jo3.put("comment","");
					   jo3.put("condition","=");
					   jo3.put("defaultType",1);
					   jo3.put("defaultValue","");
					   jo3.put("dialog","");
						jo3.put("fieldName",ss);
						jo3.put("fieldType",db.getParameterType());
						jo3.put("paraCt","");
						jo3.put("param","");
					    json3.put(jo3);
				   }
			   }
			   else {
				   JSONObject jo3 = new JSONObject();
				   jo3.put("comment","");
				   jo3.put("condition","=");
				   jo3.put("defaultType",1);
				   jo3.put("defaultValue","");
				   jo3.put("dialog","");
					jo3.put("fieldName","");
					jo3.put("fieldType",db.getParameterType());
					jo3.put("paraCt","");
					jo3.put("param","");
				    json3.put(jo3);
			   }
			   JSONArray json4 = new JSONArray();
			   if(db.getSortField()!= null)
			   {
				   String[] str4= db.getSortField().split(",");
				   for(String ss:str4)
				   {
					   JSONObject jo4 = new JSONObject();
					   jo4.put("comment","");
					   jo4.put("condition","=");
					   jo4.put("defaultType",1);
					   jo4.put("defaultValue","");
					   jo4.put("dialog","");
					   jo4.put("fieldName",ss);
					   jo4.put("fieldType",db.getParameterType());
					   jo4.put("paraCt","");
					   jo4.put("param","");
					    json4.put(jo4);
				   } 
			   }
			   else {
				   JSONObject jo4 = new JSONObject();
				   jo4.put("comment","");
				   jo4.put("condition","=");
				   jo4.put("defaultType",1);
				   jo4.put("defaultValue","");
				   jo4.put("dialog","");
				   jo4.put("fieldName","");
				   jo4.put("fieldType",db.getParameterType());
				   jo4.put("paraCt","");
				   jo4.put("param","");
				    json4.put(jo4);
			   }
			   ModelAndView mv=this.getAutoView().addObject("resultField", json1.toString());
				
				mv.addObject("conditionField", json2.toString());
				mv.addObject("setField", json3.toString());
				mv.addObject("sortField", json4.toString());
				return mv;
			}
		}
		return null;
		

	}
	/**
	 * 表选择器 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request) throws Exception {
		System.out.println("get Result table..");
		ModelAndView mv = this.getAutoView();
        
		QueryFilter queryFilter = new QueryFilter(request, "dbFuncItem");
		// 只查询自定义表。
		// queryFilter.addFilter("genByForm", 0);
		List<DbFunc> list=dbFuncService.getAll(new QueryFilter(request,"dbFuncItem"));
		mv.addObject("dbFuncList",list);
		return mv;
	}
	
}
