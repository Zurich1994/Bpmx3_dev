package com.hotent.platform.service.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.hotent.core.bpm.util.BpmConst;
import com.hotent.core.db.IEntityDao;
import com.hotent.core.service.BaseService;
import com.hotent.core.util.StringUtil;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.dbFunc.service.dbFunc.DbFuncService;
import com.hotent.platform.dao.bpm.BpmNodeWebServiceDao;
import com.hotent.platform.model.bpm.BpmNodeWebService;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormField;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.model.form.DialogField;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormFieldService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.platform.service.util.ServiceUtil;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * <pre>
 * 对象功能:流程WebService节点 Service类
 * 开发公司:广州宏天软件有限公司
 * 开发人员:zxh
 * 创建时间:2012-12-21 10:23:36
 * </pre>
 */


@Service
public class BpmNodeWebServiceService extends BaseService<BpmNodeWebService> {
	@Resource
	private BpmNodeWebServiceDao dao;
	
	@Resource
	private DbFuncService dbFuncService;
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private TableParcelService tableParcelService;
	
	@Resource
	private BpmNodeWebServiceService bpmNodeWebServiceService;
	
	@Resource
	private SubSystemService subSystemService;
	@Resource
    private BpmFormFieldService bpmFormFieldService;


	public BpmNodeWebServiceService() {
	}

	@Override
	protected IEntityDao<BpmNodeWebService, Long> getEntityDao() {
		return dao;
	}

	/**
	 * 通过节点ID和流程节点ID和服务类型获得流程服务设置信息
	 * 
	 * @param nodeId
	 *            节点ID
	 * @param actDefId
	 *            流程节点ID
	 * @return
	 */
	public BpmNodeWebService getByNodeIdActDefId(String nodeId, String actDefId) {
		return dao.getByNodeIdActDefId(nodeId, actDefId);
	}
	
	
	/**
	 * 保存 流程WebService节点
	 * 
	 * @param nodeId
	 *            节点ID
	 * @param actDefId
	 *            流程节点ID
	 * @param json
	 * @throws Exception
	 */
	public void save(String nodeId, String actDefId, String json) throws Exception {
		BpmNodeWebService bpmNodeWebService = new BpmNodeWebService();
		bpmNodeWebService.setId(UniqueIdUtil.genId());
		bpmNodeWebService.setNodeId(nodeId);
		bpmNodeWebService.setActDefId(actDefId);
		bpmNodeWebService.setDocument(json);
		dao.add(bpmNodeWebService);
	}

	/**
	 * 取得 BpmNodeWebService 实体
	 * 
	 * @param actDefId
	 * @param nodeId
	 * @param jsonObject
	 * @return
	 * @throws Exception
	 */
	protected BpmNodeWebService getFormObject(String nodeId, String actDefId, JSONObject jsonObject) throws Exception {
		Long id = (Long) jsonObject.get("wsid");
		BpmNodeWebService bpmNodeWebService = new BpmNodeWebService();
		bpmNodeWebService.setId(id);
		bpmNodeWebService.setNodeId(nodeId);
		bpmNodeWebService.setActDefId(actDefId);
		return bpmNodeWebService;
	}
	/**
	 *  解析json中的信息
	 * @param DelegateExecution
	 * @param JSONArray
	 * @param Map
	 * @return Map
	 */
	public Map<String,Object>  resolve ( String actDefId,String nodeId,Map<String,Object> paramsMap){
		
		//定义集合
		ArrayList<Map<String,Object>> input_binding_list = new ArrayList<Map<String,Object>>(); 
		
		ArrayList<Map<String,Object>> output_binding_list = new ArrayList<Map<String,Object>>();
					
		ArrayList<Map<String,Object>> results_binding_list = new ArrayList<Map<String,Object>>(); 
		
		BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);	

		Map<String,String> variableMap = new HashMap<String, String>();
		//前序引擎集合
		Map<String, Map<String, String>> proMap = new HashMap<String, Map<String,String>>();
		String document = "";
		//判断bpmNodeWebService对象
		if(bpmNodeWebService!=null){
			//创建流程变量集合
			//Map<String,String> variableMap = new HashMap<String, String>();
			System.out.println("bpmNodeWebService不为空");
			System.out.println("主键ID"+bpmNodeWebService.getId());
			document = bpmNodeWebService.getDocument();
			System.out.println("@document:"+document);	
			JSONArray jsonArray=JSONArray.fromObject(document);
	
		String namespace = "";
		String method = "";
		String pnodeid="";
		String bianString="";
		
		//创建流程变量集合
				
		//分析json中的信息
		if(jsonArray.size()==0)
			System.out.println("The jsonArray is Empty!");
		else
		{
			for(Object obj:jsonArray){
				System.out.println("obj :"+obj);
				JSONObject jObject = (JSONObject)obj;
				/////////////////////////////////////////
				namespace = jObject.getString("namespace");
				
				JSONArray inputs =null;
				if(jObject.containsKey("inputs"))
					 inputs = jObject.getJSONArray("inputs");
				System.out.println("inputs:"+inputs.toString());					
				if(inputs.size()!=0)
				{
					System.out.println("into inputs:");
					for(Object small_obj:inputs){
						JSONObject small_Object = (JSONObject)small_obj;
						String name = small_Object.getString("name");
						String bindingVal = small_Object.getString("bindingVal");
						String bindingType = small_Object.getString("bindingType");
						String soapType = small_Object.getString("soapType");				
						pnodeid = small_Object.optString("zdragparents");
						//System.out.println("x:+++++++"+x);
						System.out.println("pnodeid:+++++"+pnodeid.replaceAll(".*\\(|\\).*", ""));
						//截取括号中字段				
						pnodeid=pnodeid.replaceAll(".*\\(|\\).*", "");
						Map<String,Object> input_binding=new HashMap<String,Object>();
						input_binding.put("name", name);
						System.out.println("name:"+name);
						input_binding.put("soapType", soapType);
						input_binding.put("bindingVal", bindingVal);
						System.out.println("bindingVal:++++++++"+bindingVal);
						List<String> parcellist = resolveParcle(bindingVal);
						System.out.println("parcellist============:"+parcellist.size());
						if(parcellist.size()>1){
							input_binding.put("bindingType", bindingType);
							input_binding.put("parcellist", parcellist);							
							input_binding_list.add(input_binding);
						}else {
						if(bindingVal.contains("("))
						{
							String bindingVals = bindingVal.substring(0, bindingVal.lastIndexOf("("));
							bindingVal = bindingVals;
						}
						input_binding.put("bindingType", bindingType);
						System.out.println("bindingVal:"+bindingVal);
						input_binding_list.add(input_binding);
						}
					}
				}
				////////////////////////////////////
				JSONArray outputs =null;
				if(jObject.containsKey("outputs"))
					outputs = jObject.getJSONArray("outputs");
				System.out.println("outputs:"+outputs.toString());
				
				if(outputs.size()!=0)
				{
					System.out.println("into outputs:");
					for(Object small_obj:outputs){
						JSONObject small_Object = (JSONObject)small_obj;
						String name = small_Object.getString("name");
						String bindingVal = small_Object.getString("bindingVal");
						String bindingType = small_Object.getString("bindingType");
						String soapType = small_Object.getString("soapType");
						Map<String,Object> output_binding=new HashMap<String,Object>();
						output_binding.put("name", saveF(name));
					
						output_binding.put("bindingVal", bindingVal);
						if(bindingVal.contains("("))
						{
							String bindingVals = bindingVal.substring(0, bindingVal.lastIndexOf("("));
							bindingVal = bindingVals;
						}
						output_binding.put("bindingType", bindingType);
						output_binding.put("soapType", soapType);
						System.out.println("name:"+saveF(name));
						System.out.println("bindingVal:"+bindingVal);
						output_binding_list.add(output_binding);

					}
				}
				
				JSONArray results =null;
				if(jObject.containsKey("results"))
					results = jObject.getJSONArray("results");
				System.out.println("results:"+results.toString());
				
				if(results.size()!=0)
				{
					System.out.println("into results:");
					for(Object small_obj:results){
						JSONObject small_Object = (JSONObject)small_obj;
						String name = small_Object.getString("name");
						String bindingVal = small_Object.getString("bindingVal");
						if(bindingVal.contains("("))
						{
							String bindingVals = bindingVal.substring(0, bindingVal.lastIndexOf("("));
							bindingVal = bindingVals;
						}
						String bindingType = small_Object.getString("bindingType");
						String soapType = small_Object.getString("soapType");
						Map<String,Object> results_binding=new HashMap<String,Object>();
						results_binding.put("name", name);
						results_binding.put("bindingVal", bindingVal);
						results_binding.put("bindingType", bindingType);
						results_binding.put("soapType", soapType);
						System.out.println("name:"+name);
						System.out.println("bindingVal:"+bindingVal);
						results_binding_list.add(results_binding);
						//输出变量
						variableMap.put(bindingVal, name);
						bianString = bindingVal;
						System.out.println("流程变量:"+bindingVal+",绑定的字段或数据包名"+name);
					}
				}

				String url = jObject.getString("url");
				System.out.println("url:"+url);
				method = jObject.getString("method");
				System.out.println("method:"+method);
				String soapaction = "";
				if(jObject.containsKey("soapaction")){
					soapaction = jObject.getString("soapaction");
				}
				System.out.println("soapaction:"+soapaction);
				String serviceName = "";
				if(jObject.containsKey("serviceName")){
					serviceName = jObject.getString("serviceName");
				}
				System.out.println("serviceName:"+serviceName);
			}
		}
		
		//////////////////////第二步////////////////////////////////////
		System.out.println("namespace+++++++++++++++++++++"+namespace+"method+++++++++++++++++++"+method);
		String type = dbFuncService.getType(namespace, method);
		System.out.println("方法类型 :+++++++++++++++"+dbFuncService.getType(namespace, method));
		String result_Json = dbFuncService.getResults(namespace, method);
		System.out.println("result_Json:"+result_Json);
		String condition_Json = dbFuncService.getCondition(namespace, method);
		System.out.println("condition_Json:"+condition_Json);
		String sort_Json = dbFuncService.getSort(namespace, method);
		System.out.println("sort_Json:"+sort_Json);
		String setting_Json = dbFuncService.getSetting(namespace, method);
		System.out.println("setting_Json:"+setting_Json);
		String display_Json = dbFuncService.getDisplay(namespace, method);
		System.out.println("display_Json:"+display_Json);
	
	    //System.out.println("方法类型 ：++++++++++++++++++++"+dbFuncService.getAll());
		////////////////////////////////////////////////////////////////
		
		/////////////////////第三步/////////////////////////////////////
		List<DialogField> displayList = getDisplayList(display_Json);
		System.out.println("into displayList:");
		for(int i=0;i<displayList.size();i++)
		{
			System.out.println("FieldName:"+displayList.get(i).getFieldName()+",Comment:"+displayList.get(i).getComment());
		}
		List<DialogField> conditionList = getOtherList(condition_Json);
		System.out.println("into conditionList:");
		for(int i=0;i<conditionList.size();i++)
		{
			System.out.println("FieldName:"+conditionList.get(i).getFieldName()+",Comment:"+conditionList.get(i).getComment());
		}
		List<DialogField> sortList = getOtherList(sort_Json);
		System.out.println("into sortList:");
		for(int i=0;i<sortList.size();i++)
		{
			System.out.println("FieldName:"+sortList.get(i).getFieldName()+",Comment:"+sortList.get(i).getComment());
		}
		
		List<DialogField> returnList = getOtherList(result_Json);
		System.out.println("into returnList:"+returnList);
		for(int i=0;i<returnList.size();i++)
		{
			System.out.println("FieldName:"+returnList.get(i).getFieldName()+",Comment:"+returnList.get(i).getComment());
		}
		List<DialogField> settingList = getOtherList(setting_Json);
		System.out.println("into returnList:");
		for(int i=0;i<returnList.size();i++)
		{
			System.out.println("FieldName:"+returnList.get(i).getFieldName()+",Comment:"+returnList.get(i).getComment());
		}
		
		System.out.println("returnList.size():"+returnList.size());
		System.out.println("displayList():"+displayList.size());
		System.out.println("conditionList():"+conditionList.size());
		System.out.println("sortList():"+sortList.size());
		System.out.println("settingList():"+settingList.size());
		Map<String, Object> map = new HashMap<String, Object>();
		namespace = tableName(namespace);
		map.put("objectName", namespace);
		map.put("settingList", settingList);
		System.out.println("settingList :"+settingList);
		map.put("displayList", displayList);
		map.put("conditionList", conditionList);
		map.put("sortList", sortList);
		map.put("returnList", returnList);

		System.out.println("paramsMap:"+paramsMap.toString());
		System.out.println("map :"+map.toString());
		System.out.println("input_binding_list :"+input_binding_list);
		///////////////////第四步//////////////////////////////////////////////////
        if(type.equals("1")){
        	//增加
        	String insert_sql = ServiceUtil.getinsertSqls(map, paramsMap,input_binding_list,output_binding_list); 
        	System.out.println("::::::::::insert_sql:"+insert_sql);
        	System.out.println("进来了么++++++++：");
        	int creatsql = jdbcTemplate.update(insert_sql);
		    System.out.println("增加语句结果:"+creatsql);
		    paramsMap.put("insert", creatsql);
		    return paramsMap;
        }else if(type.equals("2")){
        	//删除
        	String delete_sql = ServiceUtil.getdeleteSqls(map, paramsMap,input_binding_list,pnodeid);
        	System.out.println("::::::::::delete_sql:"+delete_sql);
        	int deletesql = jdbcTemplate.update(delete_sql);
        	System.out.println("查询语句结果:"+deletesql);
        	paramsMap.put("delete", deletesql);
        	return paramsMap;
        }else if(type.equals("3")){
        	//修改
        	String update_sql = ServiceUtil.getupdateSqls(map, paramsMap,input_binding_list,output_binding_list,pnodeid);
        	System.out.println("::::::::::update_sql:"+update_sql);
        	int updatesql = jdbcTemplate.update(update_sql);
        	System.out.println("修改语句结果:"+updatesql);
        	paramsMap.put("update", updatesql);
        	return paramsMap;
        }else if(type.equals("4")){
        	//查询
        	String query_sql = ServiceUtil.getquerySqls(map, paramsMap,input_binding_list,pnodeid);
        	System.out.println("::::::::::query_sql:"+query_sql);
        	List<Map<String, Object>> list=jdbcTemplate.queryForList(query_sql);
        	
        	proMap = preData(list,nodeId);

			System.out.println("执行了么===========================================");
			//List<Map<String, Object>> list=jdbcTemplate.queryForList(query_sql);

			System.out.println("一共有"+list.size()+"条结果！");
			System.out.println("查询语句执行结果"+list);
			System.out.println("执行完毕===========================================");
	
			// 将返回的结果集和对应的变量存入map集合中
			if (list.size() == 1) {
				
				List<Map<String, String>> rlist  = reList(variableMap,list);
				System.out.println("进来rlist:"+rlist+"rlist.size:"+rlist.size());
				variableMap = rlist.get(0);
				
			} else if(list.size()>1){
				variableMap.put(bianString,list.toString());			
			}
			paramsMap.putAll(variableMap);
			paramsMap.putAll(proMap);
			System.out.println("最终的结果集合+++++ :"+variableMap );	        
	        System.out.println("集合中的数据个数 :"+variableMap.size());
	        System.out.println("paramsMap===========:"+paramsMap);
	        return paramsMap;
		
        }	
		}
		return paramsMap;
	}
    //处理前序引擎数据
	public Map<String, Map<String, String>> preData(List<Map<String,Object>> list,String nodeId){
		
		Map<String,String> map = new HashMap<String, String>();
		Map<String,Map<String,String>> map2 = new HashMap<String, Map<String,String>>();
		System.out.println("list中数据个数："+list.size());
		for(int x = 0; x<list.size();x++){
			Map<String,Object> map1 = list.get(x);
			
		Set<Entry<String, Object>> entryset = map1.entrySet();
		Iterator<Entry<String, Object>> it = entryset.iterator();
		while(it.hasNext()){ 
			Map.Entry<String, Object> entry = it.next();
			String k = entry.getKey();
			String v = String.valueOf(entry.getValue());
			//System.out.println("K++:"+k+"v++:"+v);
			map.put(k, v);
		}
			
		}
		map2.put(nodeId, map);
		return map2;
	}
	
	public List<DialogField> getDisplayList(String display_Json) {
		if (StringUtil.isEmpty(display_Json)) {
			return new ArrayList<DialogField>();
		}
		if(display_Json.charAt(0)=='[')
		{
			List<DialogField> list = new ArrayList<DialogField>();
			JSONArray jsonArray = JSONArray.fromObject(display_Json);
			if(jsonArray.size()==0)
				return new ArrayList<DialogField>();
			for (Object obj : jsonArray) {
				JSONObject jsonObj = (JSONObject) obj;
				String field = jsonObj.getString("field");
				
				String comment = jsonObj.getString("comment");
				String Str = saveF(field);
				DialogField dialogField = new DialogField();
				dialogField.setFieldName(Str);
				dialogField.setComment(comment);
				list.add(dialogField);
				
			}
			return list;
		}
		else return new ArrayList<DialogField>();
	}

	
	public List<DialogField> getOtherList(String Json) {
		if (StringUtil.isEmpty(Json)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(Json);
		if (jsonArray.size() == 0)
			return new ArrayList<DialogField>();
		for (Object obj : jsonArray) {
			JSONObject jsonObj = (JSONObject) obj;
			String field = jsonObj.getString("field");
			// 提取出数据包名，和表名
				System.out.println("field :" + field);
				// 拆数据包
				List<String> rp = resolveParcle(field);
				for (String x : rp) {
					DialogField dialogField = new DialogField();
					dialogField.setFieldName(x);
					list.add(dialogField);
				}
			
		}
		// 去除集合中重复的字段
		List listWithoutDup = new ArrayList(new HashSet(list));

		return listWithoutDup;
	}
	/**
	 * 解析数据包方法
	 * @param String 
	 * @return List<String>
	 * @author an
	 */
	public List<String> resolveParcle(String parcelname){
		//定义返回的集合
	    List<String> fieldList = new ArrayList<String>();
	    //判断是否是数据包
	    if (parcelname.contains("(")) {
	    //进行数据包名解析
		int index1 = parcelname.indexOf('(');
		int index2 = parcelname.indexOf(')');
		String result = parcelname.substring(index1 + 1, index2);
		String dataparcelname = parcelname.substring(0, index1);
		System.out.println("dataparcelname---------:"+dataparcelname+"result:"+result);
		//得到数据包的json串
		List<TableParcel> parcelList = tableParcelService.getParcelbyName(dataparcelname, result);
		System.out.println("parcelList:"+parcelList);
		if(parcelList.size()==0){
	    	fieldList.add(parcelname);
		}else {
		JSONArray jsonArray1 =JSONArray.fromObject(parcelList.get(0).getParcel_value());
		System.out.println("jsonArray1 :"+jsonArray1);
		//对json串进行遍历获得数据包中的字段名 
		for(Object obj1 : jsonArray1){
			JSONObject jObject1 = (JSONObject)obj1;
			String field = jObject1.getString("field");
			//字段名字拼接 
			StringBuffer sb = new StringBuffer("F_");
			sb.append(field);
			String Str = sb.toString();
			System.out.println("field :"+Str);
			fieldList.add(Str);
		}
		}
	    }else{
	    	
	    	String Str = saveF(parcelname);
	    	fieldList.add(Str);
	    }
	    
		return fieldList;
	}
	/**
	 * 字段添加F——方法
	 */
	public String saveF(String oldfiled){
		if(oldfiled.equals("id"))
		{
			return oldfiled;
		}else if(oldfiled.equals("ID")){
			return oldfiled;
		}
		
		else if(oldfiled.contains("F_")){
    		return oldfiled;
    	}else{
    		StringBuffer sb = new StringBuffer("F_");
    		sb.append(oldfiled);
    		String Str = sb.toString();
    		return Str;

    	}
		
	}
	/**
	 * 取得 BpmNodeWebService 方法和操作的表
	 * 
	 * @param actDefId
	 * @param nodeId
	 * @param 
	 * @return
	 * @throws Exception
	 */
	
	public List<String> nameMethod (String nodeId,String actDefId){
		System.out.println("进来了 ");
		BpmNodeWebService bpmNodeWebService = this.getByNodeIdActDefId(nodeId, actDefId);
		List<String> list = new ArrayList<String>();
		
		String document = "";
		String namespace = "";
		String method = "";
			if(bpmNodeWebService!=null){
				System.out.println("bpmNodeWebService不为空");
				document = bpmNodeWebService.getDocument();
				System.out.println("@document:"+document);
			
				JSONArray jsonArray=JSONArray.fromObject(document);
				if(jsonArray.size()==0)
					System.out.println("The jsonArray is Empty!");
				else
				{
					for(Object obj:jsonArray){         //遍历
						JSONObject jObject = (JSONObject)obj;
						namespace = jObject.getString("namespace");
						System.out.println("namespace==================:"+namespace);
						method = jObject.getString("method");
						System.out.println("method=====================:"+method);
						list.add(namespace);
						list.add(method);
						
					}
				}
			}
			else{
				System.out.println("bpmNodeWebService为空");
				
			}
		return list;
	}
	/**
	 * 处理表名方法
	 */
	public String tableName(String tablename){
		if(tablename.contains("w_")){
    		return tablename;
    	}else{
    		StringBuffer sb = new StringBuffer("w_");
    		sb.append(tablename);
    		String Str = sb.toString();
    		return Str;

    	}
	}
	/**
	 * 解析resultJson
	 * 不拆包
	 */
	public List<DialogField> getResultList(String Json) {
		if (StringUtil.isEmpty(Json)) {
			return new ArrayList<DialogField>();
		}
		List<DialogField> list = new ArrayList<DialogField>();
		JSONArray jsonArray = JSONArray.fromObject(Json);
		if (jsonArray.size() == 0)
			return new ArrayList<DialogField>();
		for (Object obj : jsonArray) {
			JSONObject jsonObj = (JSONObject) obj;
			String field = jsonObj.getString("field");
			// 提取出数据包名，和表名
				System.out.println("field :" + field);
					DialogField dialogField = new DialogField();
					dialogField.setFieldName(field);
					list.add(dialogField);
		}
		return list;
	}
	/**
	 * 前序引擎
	 * @param actDefId
	 * @param nodeId
	 * @return 
	 */
	public Map<String,Object> PreOrderEngine (String actDefId,String nodeId) {
	  
		
		//定义返回集合 
		Map<String,Object> map = new HashMap<String, Object>();
		//定义集合
		BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);	
		
		String document = "";
		String subString = subSystemService.findScriptNodeDefKey11(actDefId,nodeId);
	
		
		//判断bpmNodeWebService对象
		if(bpmNodeWebService!=null){

			document = bpmNodeWebService.getDocument();
			System.out.println("@document:"+document);	
			JSONArray jsonArray=JSONArray.fromObject(document);
	
		String namespace = "";
		String method = "";
	    String tableId = "";
		//创建流程变量集合
				
		//分析json中的信息
		if(jsonArray.size()==0)
			System.out.println("The jsonArray is Empty!");
		else
		{
			for(Object obj:jsonArray){
				System.out.println("obj :"+obj);
				JSONObject jObject = (JSONObject)obj;
				/////////////////////////////////////////
				namespace = jObject.getString("namespace");
				method = jObject.getString("method");
				//判断是否为读页面
				if(subString=="Read"){
					tableId = jObject.getString("tId");	
				}
			}
		}
		//////////////////////第二步////////////////////////////////////
		System.out.println("namespace+++++++++++++++++++++"+namespace+"method+++++++++++++++++++"+method);
		String type = dbFuncService.getType(namespace, method);

		String result_Json = dbFuncService.getResults(namespace, method);

		List<DialogField> returnList = getResultList(result_Json);
		
	
		System.out.println("into returnList:");
		for(int i=0;i<returnList.size();i++)
		{
			System.out.println("FieldName:"+returnList.get(i).getFieldName()+",Comment:"+returnList.get(i).getComment());
		}
		map.put("namespace", namespace);
		map.put("type", type);
		map.put("returnList",returnList);
		map.put("tableId", tableId);
	
	}	
		return map;
		
		}
	/**
	 * 状态集
	 */
	public Map<String,String> condition(String actDefId,String nodeId){
		Map<String,Object> map1 = PreOrderEngine(actDefId,nodeId);
		String type = (String)map1.get("type");
		
		String namespace = (String)map1.get("namespace");
		
		Map<String,String> map = new HashMap<String, String>();		
		if(type==null){
			map.put("无方法", "无参数");
		}else{
		
		  if(type.equals("1")){
	        	//增加
	        	System.out.println("增加一条记录");		
	        	 map.put(namespace, "增加一条记录");
	        }else if(type.equals("2")){
	        	//删除
	        	System.out.println("删除一条记录");
	        	 map.put(namespace, "删除一条记录");
	        }else if(type.equals("3")){
	        	//修改
	           System.out.println("修改一条记录");
	           map.put(namespace, "修改一条记录");
	        }else if(type.equals("4")){
	        	System.out.println("查询一条记录");
	        	map.put(namespace, "查询一条记录");
	        }	
		}
		
		
		return map;
	}
	
	/**
	 * 结果集
	 */
	
	@SuppressWarnings("unchecked")
	public Map<String,String> results (String actDefId,String nodeId){
		Map<String,Object> map1 = PreOrderEngine(actDefId,nodeId);
		String namespace = (String)map1.get("namespace");
		String type = (String)map1.get("type");
		String subString = subSystemService.findScriptNodeDefKey11(actDefId,nodeId);
		Map<String,String> map = new HashMap<String, String>();		
		if(type==null){
			if(subString=="Read"){
				String tableId = (String)map1.get("tableId");
				System.out.println("tableId:++++++++++++"+tableId);
				List<BpmFormField> list = bpmFormFieldService.getByTableId(Long.parseLong(tableId));
				String returnStr = "";
				for(BpmFormField xBpmFormField :list){
					//System.out.println("表中的字段:"+xBpmFormField.getFieldName());
					returnStr += "," + xBpmFormField.getFieldName();
				}
				returnStr = returnStr.replaceFirst(",", "");
	    		System.out.println("结果字段为："+returnStr);
	    		
	    		map.put(namespace, returnStr);
				//map.put(key, value)
			}else if(subString=="ADD"||subString=="DEL"||subString=="UPD"||subString=="QUI"){
				
			}
			else {
				
				map.put("无方法", "无参数");
			}
		}else{
			List<DialogField> returnList = (List<DialogField>)map1.get("returnList");
		
		  if(type.equals("1")){
	        	//增加	
	        	 map.put(namespace, "无结果集");
	        }else if(type.equals("2")){
	        	//删除
	        	 map.put(namespace, "无结果集");
	        }else if(type.equals("3")){
	        	//修改
     	           map.put(namespace, "无结果集");
	        }else if(type.equals("4")){
	        	//查询
	    		String returnStr = "";
	    		for (DialogField dialogField : returnList) {

	    				returnStr += "," + dialogField.getFieldName();
	    			
	    		}
	    		returnStr = returnStr.replaceFirst(",", "");
	    		System.out.println("结果字段为："+returnStr);
	    		
	    		map.put(namespace, returnStr);
	        }	
		}    
		return map;
	}
	/**
	 * 对多条返回结果进行处理
	 * @param variableMap
	 * @param list
	 * @return
	 */
	public List<Map<String,String>> reList(Map<String,String> variableMap,List<Map<String, Object>> list){
         List<Map<String, String>> list1 = new ArrayList<Map<String,String>>();
		for (int i = 0; i < list.size(); i++) {
			Set<Entry<String, String>> entrySet = variableMap.entrySet(); // key-value的set集合
			Iterator<Entry<String, String>> it = entrySet
					.iterator();
			while (it.hasNext()) {
				Map.Entry me = (Entry) it.next();
				String v = (String) me.getValue();
				String K = (String) me.getKey();
				System.out.println(" V:" + v);
				//字符串拼接
				StringBuffer sb = new StringBuffer();
				// 拆包遍历
				for (String variable : resolveParcle(v)) {
					String value = String.valueOf(list.get(i).get(variable));
					//String value = (String)list.get(i).get(variable);
					System.out.println("看一下带属性名的结果集:"+list.get(i));
					sb.append(",");
					sb.append(value);
				}
				//System.out.println("拼接后的字符串 :"+ sb.toString()); 
				variableMap.put(K, sb.toString().replaceFirst(",", ""));
				list1.add(variableMap);
			}
		}
	
		return list1;
	}
	/**
	 * 获取读写页面中绑定模板的id
	 * an
	 */
	public String templateGet(String nodeId ,String actDefId){
		BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);	
		//判断bpmNodeWebService对象
		if(bpmNodeWebService!=null){
			System.out.println("bpmNodeWebService不为空");
			System.out.println("主键ID"+bpmNodeWebService.getId());
			String document = bpmNodeWebService.getDocument();
			System.out.println("@document:"+document);	
			JSONArray jsonArray=JSONArray.fromObject(document);
			String temlateId ="";
		//分析json中的信息
		if(jsonArray.size()==0)
			System.out.println("The jsonArray is Empty!");
		else
		{
			for(Object obj:jsonArray){
				System.out.println("obj :"+obj);
				JSONObject jObject = (JSONObject)obj;
				/////////////////////////////////////////
				temlateId = jObject.getString("temid");
			}
			}
		return temlateId;
		
		}else {
			
			return null ;
		}
	}
	/**
	 * 获取表单信息
	 * an
	 */
	public String tableGet(String nodeId ,String actDefId) {
		BpmNodeWebService bpmNodeWebService = bpmNodeWebServiceService.getByNodeIdActDefId(nodeId, actDefId);
		return null;
	}
}
