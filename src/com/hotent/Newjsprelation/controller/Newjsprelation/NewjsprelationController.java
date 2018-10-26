
package com.hotent.Newjsprelation.controller.Newjsprelation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.activiti.engine.impl.util.json.JSONStringer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotent.Newjsprelation.model.Newjsprelation.Newjsprelation;
import com.hotent.Newjsprelation.service.Newjsprelation.NewjsprelationService;
import com.hotent.core.annotion.Action;
import com.hotent.core.util.UniqueIdUtil;
import com.hotent.core.web.ResultMessage;
import com.hotent.core.web.controller.BaseController;
import com.hotent.core.web.query.QueryFilter;
import com.hotent.core.web.util.RequestUtil;
import com.hotent.dbFunc.model.dbFunc.DbFunc;
import com.hotent.formParcel.model.formParcel.FormParcel;
import com.hotent.formParcel.service.formParcel.FormParcelService;
import com.hotent.platform.dao.form.BpmFormDefDao;
import com.hotent.platform.model.bpm.BpmDefVar;
import com.hotent.platform.model.bpm.BpmDefinition;
import com.hotent.platform.model.form.BpmFormDef;
import com.hotent.platform.model.form.BpmFormTable;
import com.hotent.platform.service.bpm.BpmDefVarService;
import com.hotent.platform.service.bpm.BpmDefinitionService;
import com.hotent.platform.service.form.BpmFormDefService;
import com.hotent.platform.service.form.BpmFormTableService;
import com.hotent.platform.service.system.SubSystemService;
import com.hotent.tableParcel.model.tableParcel.TableParcel;
import com.hotent.tableParcel.service.tableParcel.TableParcelService;
/**
 * 对象功能:newjsprelation 控制器类
 */
@Controller
@RequestMapping("/Newjsprelation/Newjsprelation/newjsprelation/")
public class NewjsprelationController extends BaseController
{
	@Resource
	private NewjsprelationService newjsprelationService;
	@Resource
	private BpmFormDefService bpmFormDefService;
	@Resource
	private FormParcelService formParcelService;
	@Resource
	private BpmFormTableService bpmFormTableService;
	@Resource
	private TableParcelService tableParcelService;
	@Resource
	private BpmFormDefDao bpmFormDefDao;
	@Resource
	private BpmDefVarService bpmDefVarService;
	@Resource
	public BpmDefinitionService bpmDefinitionService;
	@Resource
	private SubSystemService subSystemService;

	/**
	 * 添加或更新newjsprelation。
	 * @param request
	 * @param response
	 * @param newjsprelation 添加或更新的实体
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("save")
	@Action(description="添加或更新newjsprelation")
	public void save(HttpServletRequest request, HttpServletResponse response,Newjsprelation newjsprelation) throws Exception
	{
		String resultMsg=null;		
		try{
			if(newjsprelation.getId()==null){
				Long id=UniqueIdUtil.genId();
				newjsprelation.setId(id);
				newjsprelationService.add(newjsprelation);
				resultMsg=getText("添加","newjsprelation");
			}else{
			    newjsprelationService.update(newjsprelation);
				resultMsg=getText("更新","newjsprelation");
			}
			writeResultMessage(response.getWriter(),resultMsg,ResultMessage.Success);
		}catch(Exception e){
			writeResultMessage(response.getWriter(),resultMsg+","+e.getMessage(),ResultMessage.Fail);
		}
	}
	
	/**
	 * 取得newjsprelation分页列表
	 * @param request
	 * @param response
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	@Action(description="查看newjsprelation分页列表")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		List<Newjsprelation> list=newjsprelationService.getAll(new QueryFilter(request,"newjsprelationItem"));
		ModelAndView mv=this.getAutoView().addObject("newjsprelationList",list);
		
		return mv;
	}
	
	/**
	 * 删除newjsprelation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("del")
	@Action(description="删除newjsprelation")
	public void del(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String preUrl= RequestUtil.getPrePage(request);
		ResultMessage message=null;
		try{
			Long[]  lAryId=RequestUtil.getLongAryByStr(request,"id");
			newjsprelationService.delByIds(lAryId);
			message=new ResultMessage(ResultMessage.Success, "删除newjsprelation成功!");
		}catch(Exception ex){
			message=new ResultMessage(ResultMessage.Fail, "删除失败" + ex.getMessage());
		}
		addMessage(message, request);
		response.sendRedirect(preUrl);
	}
	
	/**
	 * 	编辑newjsprelation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("edit")
	@Action(description="编辑newjsprelation")
	public ModelAndView edit(HttpServletRequest request) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		
		String returnUrl=RequestUtil.getPrePage(request);
		Newjsprelation newjsprelation=newjsprelationService.getById(id);
		
		return getAutoView().addObject("newjsprelation",newjsprelation)
							.addObject("returnUrl",returnUrl);
	}

	/**
	 * 取得newjsprelation明细
	 * @param request   
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("get")
	@Action(description="查看newjsprelation明细")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Long id=RequestUtil.getLong(request,"id");
		Newjsprelation newjsprelation=newjsprelationService.getById(id);
		return getAutoView().addObject("newjsprelation", newjsprelation);
	}
	/**
	 * 表选择器 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("dialog")
	public ModelAndView dialog(HttpServletRequest request) throws Exception {
		List<Newjsprelation> list=newjsprelationService.getAll(new QueryFilter(request,"newjsprelationItem"));
		for(int i=0;i<list.size();i++){
			Newjsprelation jsp=list.get(i);
			Long formid=jsp.getFORMDEFID();
			BpmFormDef bpmFormDef=bpmFormDefService.getById(formid);
			if(bpmFormDef!=null)
			{
				String sbuject=bpmFormDef.getSubject();
				jsp.setName(sbuject);
			}
		}
		ModelAndView mv=this.getAutoView().addObject("newjsprelationList",list);
		
		return mv;
	}
	/**
	 * 取表单参数
	 * wb
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getVarsTree")
	@ResponseBody
	public String getVarsTree(HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("start form vars");
		String formKey = RequestUtil.getString(request, "Id");
		System.out.println("王焕然："+formKey);
		String type=RequestUtil.getString(request, "type");
		BpmFormDef bpmFormDef=bpmFormDefService.getDefaultVersionByFormKey(formKey);
		Long Id = bpmFormDef.getFormDefId();
		String desc=bpmFormDef.getFormDesc();
		String subject=bpmFormDef.getSubject();
		String tabtitle=bpmFormDef.getTabTitle();
		String htmljson=null;
		System.out.println(Id);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("success", true).accumulate("msg", "获取成功.");
		jsonObject.accumulate("wsdl",desc);
		jsonObject.accumulate("namespace", subject);
		jsonObject.accumulate("invokeUrl",formKey);

		jsonObject.accumulate("method", tabtitle);
		jsonObject.accumulate("soapaction", "pager");
		JSONArray paramsTree = new JSONArray();
		
		Newjsprelation newjsprelation=newjsprelationService.getById(Id);
		if(newjsprelation!=null)
			htmljson=newjsprelation.getNameandvalue();
		else {
			String htmlString=bpmFormDef.getHtml();
			ArrayList<String> al = read(htmlString);
			for(int i=0;i<al.size();i++){
				System.out.println("input "+al.get(i));
			}
			ArrayList<String> names =getNames(al);
			for (int i = 0; i < names.size(); i++) {
				System.out.println("names" + names.get(i));
			}
			ArrayList<String> values = getValues(al);
			for (int i = 0; i < values.size(); i++) {
				System.out.println("values" + values.get(i));
				//html=html.replace(values.get(i),"");
			}
			htmljson=toJson(names,values);
			System.out.println("htmljson.."+htmljson);
		}

		JSONArray jsonArray=JSONArray.fromObject(htmljson);
		JSONObject formjsonObject=(JSONObject) jsonArray.get(0);
		Map<String, String> map=formjsonObject;
		Set<Entry<String, String>>  set=map.entrySet();
		for(Entry<String, String> entry:set){
			JSONObject paramJson = new JSONObject();
			String varName=entry.getKey();
			paramJson.accumulate("name", varName)
			.accumulate("type", "text")
			.accumulate("nocheck", false);;
			paramsTree.add(paramJson);
		}
		JSONObject inputTreeRoot = new JSONObject();
		inputTreeRoot.accumulate("name", "表单参数").accumulate("children", paramsTree);
		jsonObject.accumulate("inputParams", inputTreeRoot);
	
		return jsonObject.toString();
	}
	
	/**
	 * 取数据包名
	 * @author 王钊
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDataParcel")
	@ResponseBody
	public String getDataParcel(HttpServletRequest request,HttpServletResponse response) throws Exception {

		//Long formDefId = Long.valueOf(request.getParameter("formId"));
		System.out.println(request.getParameter("Id"));
		String formId = RequestUtil.getString(request, "Id");
        BpmFormDef  bpmFormDef = bpmFormDefService.getDefaultVersionByFormKey(formId);
        Long Id = bpmFormDef.getFormDefId();
		String desc=bpmFormDef.getFormDesc();
		String subject=bpmFormDef.getSubject();
		String tabtitle=bpmFormDef.getTabTitle();
        /*String F_FORM_NAME = bpmFormDefDao.getSubjectByFormDefId(formDefId);
        System.out.println("F_FORM_NAME::"+F_FORM_NAME);
        List<FormParcel> formparcels = formParcelService.getByFormName(F_FORM_NAME);
        System.out.println("formparcels:::"+formparcels.size());*/
        BpmFormTable bpmFormTable = bpmFormTableService.getTableById(bpmFormDef.getTableId());
        System.out.println("bpmFormTable::"+bpmFormTable.getTableName());
        List<TableParcel> tableParcels = tableParcelService.getParcelsbyTableName(bpmFormTable.getTableName());
        System.out.println("tableParcels:::"+tableParcels.size());
        JSONStringer jo1= new JSONStringer();	
		jo1.object();
		jo1.key("success");
		jo1.value(true);
		jo1.key("msg");
		jo1.value("获取成功.");
		jo1.key("wsdl");
		jo1.value(desc);
	    jo1.key("namespace");
		jo1.value(subject);
		jo1.key("invokeUrl");
		jo1.value(formId);
		jo1.key("method");
		jo1.value(tabtitle);
		jo1.key("soapaction");
		jo1.value("pager");
		jo1.key("inputParams");
	    jo1.object();
	    jo1.key("name");
	    jo1.value("数据包参数");
	    jo1.key("children");
	    jo1.array();
	    for(int i=0;i<tableParcels.size();i++){
	    	jo1.object();
	    	jo1.key("name");
	    	jo1.value(tableParcels.get(i).getParcel_name());
	    	jo1.key("type");
	    	jo1.value("text");
	    	jo1.key("nocheck");
	    	jo1.value(false);
	    	jo1.endObject();
	    }
	    jo1.endArray();
	    jo1.endObject();
	    jo1.endObject();
	    System.out.println("王钊"+jo1.toString());
		return jo1.toString();
	}
	
	/**
	 * 转换json
	 * @param names
	 * @param values
	 * @return
	 */
	private String toJson(ArrayList<String> names,ArrayList<String> values){
		Map<String, String> map1 = new HashMap<String, String>();
		for(int i=0;i<names.size();i++)
		{
			String name=names.get(i);
			int index=name.lastIndexOf(":");
			String subname=name.substring(index+1, name.length());
			map1.put(subname, " ");
			
		}
		JSONArray ja1 = JSONArray.fromObject(map1);
		return ja1.toString();
	}
	/**
	 * 解析html name
	 * @param a
	 * @return
	 */
	ArrayList<String> getNames(ArrayList<String> a) {
		ArrayList<String> names = new ArrayList<String>();
		String buf = null;
		String result = "";

		for (String s : a) {
			buf = s;
			int beginIx = buf.indexOf("name=\"");
			if (beginIx > 0) {
				buf = buf.substring(beginIx);
				buf = buf.replaceAll("name=\"", "");
				int endIx = buf.indexOf("\"");
				if (endIx > 0) {
					result = buf.substring(0, endIx);
					names.add(result);
				}
			}
		}
		return names;

	}
	/**
	 * 解析html value
	 * @param a
	 * @return
	 */
	ArrayList<String> getValues(ArrayList<String> a) {
		ArrayList<String> values = new ArrayList<String>();
		String buf = null;
		String result = "";

		for (String s : a) {
			buf = s;
			int beginIx = buf.indexOf("name=\"");
			if (beginIx > 0) {
				buf = buf.substring(beginIx);
				buf = buf.replaceAll("name=\"", "");
				int endIx = buf.indexOf("\"");
				if (buf.indexOf("value=", endIx) != -1)// 如果有value
				{
					beginIx = buf.indexOf("value=\"", endIx);
					if (beginIx != -1)// 如果name后面有value=“
					{
						buf = buf.substring(beginIx);
						buf = buf.replaceAll("value=\"", "");
						endIx = buf.indexOf("\"");
						if (endIx > 0) {
							result = buf.substring(0, endIx);
							values.add(result);
							System.out.println(result);
						}
					} else // 只有value=
					{
						beginIx = buf.indexOf("value=", endIx);
						buf = buf.substring(beginIx);
						buf = buf.replaceAll("value=", "");
						endIx = buf.indexOf("}");
						if (endIx > 0) {
							result = buf.substring(0, endIx + 1);
							values.add(result);
							System.out.println(result);
						}

					}

				}

			}
		}
		return values;

	}
	
	public ArrayList<String> read(String Html) {

		
		String html=new String(Html);
		BufferedReader reader = null;
		StringBuffer buff = new StringBuffer();
		try {
			//System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new StringReader(html));
			String tempString = null; 
			String a [ ] = new String [1000];
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			//String rs[]=new String[1000];
			ArrayList<String> inputList = new ArrayList<String>();
			while ((tempString = reader.readLine()) != null) {
				
				String as [ ] = tempString.split("<input");
			        
			         for(int i = 1; i < as.length; i++) {
			              a[i] = as[i];
			             System.out.println(a[i]);
			             inputList.add(a[i]);
		              	}
			  line++;
			}
			
			return inputList;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return null;

	}

	
	
	
	private String getHref(String str) {
		// 将给定的正则表达式编译到模式中
		Pattern pattern = Pattern.compile("<input.*>");
		// 创建匹配给定输入与此模式的匹配器
		Matcher matcher = pattern.matcher(str);
		if (matcher.find())// 尝试查找与该模式匹配的输入序列的下一个子序列
			return matcher.group(0);
		return null;
	}
	/**
	 * 取从参数
	 * an
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getVarsTree1")
	@ResponseBody
	public String getVarsTree1(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		  Long defId = RequestUtil.getLong(request, "defId");
			
			String nodeId = RequestUtil.getString(request, "nodeId");
			
			String actDefId = RequestUtil.getString(request, "actDefId");
		
			System.out.println("nodeId:"+nodeId+"actDefId:"+actDefId+"defId++"+defId);
			
			String subString = subSystemService.findScriptNodeDefKey11(actDefId,nodeId);
			System.out.println("subString:"+subString);
			JSONArray jarray = new JSONArray();
			List<BpmDefinition> list = bpmDefinitionService.getByDefKey(subString);
			//查找最新版本
			int i=0 ;
			for(int x=0;x<list.size();x++){
				if(list.get(x).getVersionNo()>i){
					i=list.get(x).getVersionNo();
					i=x;
				}	
				
			}
			Long defId1 = list.get(i).getDefId();
			List<BpmDefVar> list1 = bpmDefVarService.getVarsByFlowDefId(defId1);
			
			for(BpmDefVar bd :list1){
				System.out.println("id:"+bd.getVarId()+"varName:"+bd.getVarName()+"varKey:"+bd.getVarKey()+"type:"+bd.getVarType());
				
				JSONObject jobject = new JSONObject().accumulate("id", bd.getVarId())
				.accumulate("varName", bd.getVarName())
				.accumulate("varKey", bd.getVarKey())
				.accumulate("type", bd.getVarDataType());
				jarray.add(jobject);
			}
			
		
		String formKey = RequestUtil.getString(request, "Id");
		System.out.println("安宁："+formKey);
		BpmFormDef bpmFormDef=bpmFormDefService.getDefaultVersionByFormKey(formKey);
		Long Id = bpmFormDef.getFormDefId();
		String desc=bpmFormDef.getFormDesc();
		String subject=bpmFormDef.getSubject();
		String tabtitle=bpmFormDef.getTabTitle();
		System.out.println(Id);
		//List<String> x = new ArrayList<String>();
		
		 JSONStringer jo1= new JSONStringer();	
			jo1.object();
			jo1.key("success");
			jo1.value(true);
			jo1.key("msg");
			jo1.value("获取成功.");
			jo1.key("wsdl");
			jo1.value(desc);
		    jo1.key("namespace");
			jo1.value(subject);
			jo1.key("invokeUrl");
			jo1.value(formKey);
			jo1.key("method");
			jo1.value(tabtitle);
			jo1.key("soapaction");
			jo1.value("pager");
			jo1.key("inputParams");
		    jo1.object();
		    jo1.key("name");
		    jo1.value("从属参数");
		    jo1.key("children");
		    jo1.array();
		    for(int j=0;j<list1.size();j++){
		    	jo1.object();
		    	jo1.key("name");
		    	jo1.value(list1.get(j).getVarName());
		    	jo1.key("type");
		    	jo1.value(list1.get(j).getVarType());
		    	jo1.key("nocheck");
		    	jo1.value(false);
		    	jo1.endObject();
		    }
		    jo1.endArray();
		    jo1.endObject();
		    jo1.endObject();
			return jo1.toString();
	    
	}
}
